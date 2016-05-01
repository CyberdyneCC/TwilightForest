package twilightforest.uncrafting;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import twilightforest.TwilightForestMod;

public class ContainerTFUncrafting extends Container {

    public InventoryTFGoblinUncrafting uncraftingMatrix = new InventoryTFGoblinUncrafting(this);
    public InventoryCrafting assemblyMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCrafting combineMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory tinkerInput = new InventoryTFGoblinInput(this);
    public IInventory tinkerResult = new InventoryCraftResult();
    private World worldObj;

    public ContainerTFUncrafting(InventoryPlayer inventory, World world, int x, int y, int z) {
        this.worldObj = world;
        this.func_75146_a(new Slot(this.tinkerInput, 0, 13, 35));
        this.func_75146_a(new SlotTFGoblinCraftResult(inventory.field_70458_d, this.tinkerInput, this.uncraftingMatrix, this.assemblyMatrix, this.tinkerResult, 0, 147, 35));

        int invX;
        int invY;

        for (invX = 0; invX < 3; ++invX) {
            for (invY = 0; invY < 3; ++invY) {
                this.func_75146_a(new SlotTFGoblinUncrafting(inventory.field_70458_d, this.tinkerInput, this.uncraftingMatrix, this.assemblyMatrix, invY + invX * 3, 300000 + invY * 18, 17 + invX * 18));
            }
        }

        for (invX = 0; invX < 3; ++invX) {
            for (invY = 0; invY < 3; ++invY) {
                this.func_75146_a(new SlotTFGoblinAssembly(inventory.field_70458_d, this.tinkerInput, this.assemblyMatrix, this.uncraftingMatrix, invY + invX * 3, 62 + invY * 18, 17 + invX * 18));
            }
        }

        for (invX = 0; invX < 3; ++invX) {
            for (invY = 0; invY < 9; ++invY) {
                this.func_75146_a(new Slot(inventory, invY + invX * 9 + 9, 8 + invY * 18, 84 + invX * 18));
            }
        }

        for (invX = 0; invX < 9; ++invX) {
            this.func_75146_a(new Slot(inventory, invX, 8 + invX * 18, 142));
        }

        this.func_75130_a(this.assemblyMatrix);
    }

    public void func_75130_a(IInventory par1IInventory) {
        ItemStack result;

        if (par1IInventory == this.tinkerInput) {
            result = this.tinkerInput.func_70301_a(0);
            IRecipe input = this.getRecipeFor(result);
            int inputTags;

            if (input != null) {
                inputTags = this.getRecipeWidth(input);
                int resultInnateEnchantments = this.getRecipeHeight(input);
                ItemStack[] inputEnchantments = this.getRecipeItems(input);

                int i;

                for (i = 0; i < this.uncraftingMatrix.func_70302_i_(); ++i) {
                    this.uncraftingMatrix.func_70299_a(i, (ItemStack) null);
                }

                int key;

                for (i = 0; i < resultInnateEnchantments; ++i) {
                    for (key = 0; key < inputTags; ++key) {
                        ItemStack enchID = ItemStack.func_77944_b(inputEnchantments[key + i * inputTags]);

                        if (enchID != null && enchID.field_77994_a > 1) {
                            enchID.field_77994_a = 1;
                        }

                        if (enchID != null && (enchID.func_77952_i() == -1 || enchID.func_77952_i() == 32767)) {
                            enchID.func_77964_b(0);
                        }

                        this.uncraftingMatrix.func_70299_a(key + i * 3, enchID);
                    }
                }

                if (result.func_77951_h()) {
                    i = this.countDamagedParts(result);

                    for (key = 0; key < 9 && i > 0; ++key) {
                        if (this.isDamageableComponent(this.uncraftingMatrix.func_70301_a(key))) {
                            this.uncraftingMatrix.func_70301_a(key).field_77994_a = 0;
                            --i;
                        }
                    }
                }

                for (i = 0; i < 9; ++i) {
                    ItemStack itemstack = this.uncraftingMatrix.func_70301_a(i);

                    if (this.isIngredientProblematic(itemstack)) {
                        itemstack.field_77994_a = 0;
                    }
                }

                this.uncraftingMatrix.numberOfInputItems = input.func_77571_b().field_77994_a;
                this.uncraftingMatrix.uncraftingCost = this.calculateUncraftingCost();
                this.uncraftingMatrix.recraftingCost = 0;
            } else {
                for (inputTags = 0; inputTags < 9; ++inputTags) {
                    this.uncraftingMatrix.func_70299_a(inputTags, (ItemStack) null);
                }

                this.uncraftingMatrix.numberOfInputItems = 0;
                this.uncraftingMatrix.uncraftingCost = 0;
            }
        }

        if (par1IInventory == this.assemblyMatrix || par1IInventory == this.tinkerInput) {
            if (this.isMatrixEmpty(this.tinkerInput)) {
                this.tinkerResult.func_70299_a(0, CraftingManager.func_77594_a().func_82787_a(this.assemblyMatrix, this.worldObj));
                this.uncraftingMatrix.recraftingCost = 0;
            } else {
                this.tinkerResult.func_70299_a(0, (ItemStack) null);
                this.uncraftingMatrix.uncraftingCost = this.calculateUncraftingCost();
                this.uncraftingMatrix.recraftingCost = 0;
            }
        }

        if (par1IInventory != this.combineMatrix && !this.isMatrixEmpty(this.uncraftingMatrix) && !this.isMatrixEmpty(this.assemblyMatrix)) {
            for (int i = 0; i < 9; ++i) {
                if (this.assemblyMatrix.func_70301_a(i) != null) {
                    this.combineMatrix.func_70299_a(i, this.assemblyMatrix.func_70301_a(i));
                } else if (this.uncraftingMatrix.func_70301_a(i) != null && this.uncraftingMatrix.func_70301_a(i).field_77994_a > 0) {
                    this.combineMatrix.func_70299_a(i, this.uncraftingMatrix.func_70301_a(i));
                } else {
                    this.combineMatrix.func_70299_a(i, (ItemStack) null);
                }
            }

            result = CraftingManager.func_77594_a().func_82787_a(this.combineMatrix, this.worldObj);
            ItemStack itemstack1 = this.tinkerInput.func_70301_a(0);

            if (result != null && this.isValidMatchForInput(itemstack1, result)) {
                NBTTagCompound nbttagcompound = itemstack1.func_77978_p();

                if (nbttagcompound != null) {
                    nbttagcompound = (NBTTagCompound) nbttagcompound.func_74737_b();
                }

                Map map = null;

                if (result.func_77948_v()) {
                    map = EnchantmentHelper.func_82781_a(result);
                }

                Map map1 = null;
                Iterator iterator;
                Object object;
                int j;

                if (itemstack1.func_77948_v()) {
                    map1 = EnchantmentHelper.func_82781_a(itemstack1);
                    iterator = map1.keySet().iterator();

                    while (iterator.hasNext()) {
                        object = iterator.next();
                        j = ((Integer) object).intValue();
                        Enchantment level = Enchantment.field_77331_b[j];

                        if (!level.func_92089_a(result)) {
                            map1.remove(object);
                        }
                    }
                }

                if (nbttagcompound != null) {
                    nbttagcompound.func_82580_o("ench");
                    result.func_77982_d((NBTTagCompound) nbttagcompound.func_74737_b());
                    if (map1 != null) {
                        EnchantmentHelper.func_82782_a(map1, result);
                    }
                }

                this.tinkerResult.func_70299_a(0, result);
                this.uncraftingMatrix.uncraftingCost = 0;
                this.uncraftingMatrix.recraftingCost = this.calculateRecraftingCost();
                if (this.uncraftingMatrix.recraftingCost > 0 && !result.func_82837_s()) {
                    result.func_82841_c(itemstack1.func_82838_A() + 2);
                }

                if (map != null && map.size() > 0) {
                    iterator = map.keySet().iterator();

                    while (iterator.hasNext()) {
                        object = iterator.next();
                        j = ((Integer) object).intValue();
                        int k = ((Integer) map.get(object)).intValue();
                        Enchantment ench = Enchantment.field_77331_b[j];

                        if (EnchantmentHelper.func_77506_a(j, result) > k) {
                            k = EnchantmentHelper.func_77506_a(j, result);
                        }

                        if (EnchantmentHelper.func_77506_a(j, result) < k) {
                            result.func_77966_a(ench, k);
                        }
                    }
                }
            }
        }

    }

    protected boolean isIngredientProblematic(ItemStack ingredient) {
        return ingredient != null && (ingredient.func_77973_b().hasContainerItem(ingredient) || ingredient.func_77977_a().contains("itemMatter"));
    }

    public IRecipe getRecipeFor(ItemStack inputStack) {
        if (inputStack != null) {
            Iterator iterator = CraftingManager.func_77594_a().func_77592_b().iterator();

            while (iterator.hasNext()) {
                IRecipe recipe = (IRecipe) iterator.next();

                if ((recipe instanceof ShapedRecipes || recipe instanceof ShapedOreRecipe) && recipe.func_77571_b().func_77973_b() == inputStack.func_77973_b() && inputStack.field_77994_a >= recipe.func_77571_b().field_77994_a && (!recipe.func_77571_b().func_77981_g() || recipe.func_77571_b().func_77960_j() == inputStack.func_77960_j())) {
                    return recipe;
                }
            }
        }

        return null;
    }

    public boolean isValidMatchForInput(ItemStack inputStack, ItemStack resultStack) {
        if (inputStack.func_77973_b() instanceof ItemPickaxe && resultStack.func_77973_b() instanceof ItemPickaxe) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemAxe && resultStack.func_77973_b() instanceof ItemAxe) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemSpade && resultStack.func_77973_b() instanceof ItemSpade) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemHoe && resultStack.func_77973_b() instanceof ItemHoe) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemSword && resultStack.func_77973_b() instanceof ItemSword) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemBow && resultStack.func_77973_b() instanceof ItemBow) {
            return true;
        } else if (inputStack.func_77973_b() instanceof ItemArmor && resultStack.func_77973_b() instanceof ItemArmor) {
            ItemArmor inputArmor = (ItemArmor) inputStack.func_77973_b();
            ItemArmor resultArmor = (ItemArmor) resultStack.func_77973_b();

            return inputArmor.field_77881_a == resultArmor.field_77881_a;
        } else {
            return false;
        }
    }

    public int getUncraftingCost() {
        return this.uncraftingMatrix.uncraftingCost;
    }

    public int getRecraftingCost() {
        return this.uncraftingMatrix.recraftingCost;
    }

    public int calculateUncraftingCost() {
        return !this.isMatrixEmpty(this.assemblyMatrix) ? 0 : this.countDamageableParts(this.uncraftingMatrix);
    }

    public int calculateRecraftingCost() {
        if (this.tinkerInput.func_70301_a(0) != null && this.tinkerInput.func_70301_a(0).func_77948_v() && this.tinkerResult.func_70301_a(0) != null) {
            ItemStack input = this.tinkerInput.func_70301_a(0);
            ItemStack output = this.tinkerResult.func_70301_a(0);
            byte cost = 0;
            int cost1 = cost + input.func_82838_A();
            int enchantCost = this.countTotalEnchantmentCost(input);

            cost1 += enchantCost;
            int damagedCost = (1 + this.countDamagedParts(input)) * EnchantmentHelper.func_82781_a(output).size();

            cost1 += damagedCost;
            int enchantabilityDifference = input.func_77973_b().func_77619_b() - output.func_77973_b().func_77619_b();

            cost1 += enchantabilityDifference;
            cost1 = Math.max(1, cost1);
            return cost1;
        } else {
            return 0;
        }
    }

    public int countHighestEnchantmentCost(ItemStack itemStack) {
        int count = 0;
        Enchantment[] aenchantment = Enchantment.field_77331_b;
        int i = aenchantment.length;

        for (int j = 0; j < i; ++j) {
            Enchantment ench = aenchantment[j];

            if (ench != null) {
                int level = EnchantmentHelper.func_77506_a(ench.field_77352_x, itemStack);

                if (level > count) {
                    count += this.getWeightModifier(ench) * level;
                }
            }
        }

        return count;
    }

    public int countTotalEnchantmentCost(ItemStack itemStack) {
        int count = 0;
        Enchantment[] aenchantment = Enchantment.field_77331_b;
        int i = aenchantment.length;

        for (int j = 0; j < i; ++j) {
            Enchantment ench = aenchantment[j];

            if (ench != null) {
                int level = EnchantmentHelper.func_77506_a(ench.field_77352_x, itemStack);

                if (level > 0) {
                    count += this.getWeightModifier(ench) * level;
                    ++count;
                }
            }
        }

        return count;
    }

    public int getWeightModifier(Enchantment ench) {
        switch (ench.func_77324_c()) {
        case 1:
            return 8;

        case 2:
            return 4;

        case 3:
        case 4:
        case 5:
            return 2;

        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        default:
            return 1;
        }
    }

    public ItemStack func_75144_a(int slotNum, int mouseButton, int shiftHeld, EntityPlayer par4EntityPlayer) {
        if (slotNum > 0 && par4EntityPlayer.field_71071_by.func_70445_o() == null && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.assemblyMatrix && !((Slot) this.field_75151_b.get(slotNum)).func_75216_d() && this.isMatrixEmpty(this.assemblyMatrix)) {
            slotNum -= 9;
        }

        if (slotNum > 0 && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.tinkerResult && this.calculateRecraftingCost() > par4EntityPlayer.field_71068_ca && !par4EntityPlayer.field_71075_bZ.field_75098_d) {
            return null;
        } else if (slotNum > 0 && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.uncraftingMatrix && this.calculateUncraftingCost() > par4EntityPlayer.field_71068_ca && !par4EntityPlayer.field_71075_bZ.field_75098_d) {
            return null;
        } else if (slotNum > 0 && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.uncraftingMatrix && TwilightForestMod.disableUncrafting) {
            return null;
        } else if (slotNum > 0 && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.uncraftingMatrix && (((Slot) this.field_75151_b.get(slotNum)).func_75211_c() == null || ((Slot) this.field_75151_b.get(slotNum)).func_75211_c().field_77994_a == 0)) {
            return null;
        } else {
            ItemStack ret = super.func_75144_a(slotNum, mouseButton, shiftHeld, par4EntityPlayer);

            if (slotNum > 0 && ((Slot) this.field_75151_b.get(slotNum)).field_75224_c instanceof InventoryTFGoblinInput) {
                this.func_75130_a(this.tinkerInput);
            }

            return ret;
        }
    }

    protected void func_75133_b(int slotNum, int mouseButton, boolean par3, EntityPlayer par4EntityPlayer) {
        if (((Slot) this.field_75151_b.get(slotNum)).field_75224_c == this.uncraftingMatrix) {
            slotNum += 9;
        }

        this.func_75144_a(slotNum, mouseButton, 1, par4EntityPlayer);
    }

    private boolean isMatrixEmpty(IInventory matrix) {
        boolean matrixEmpty = true;

        for (int i = 0; i < matrix.func_70302_i_(); ++i) {
            if (matrix.func_70301_a(i) != null) {
                matrixEmpty = false;
            }
        }

        return matrixEmpty;
    }

    public boolean isDamageableComponent(ItemStack itemStack) {
        return itemStack != null && itemStack.func_77973_b() != Items.field_151055_y;
    }

    public int countDamageableParts(IInventory matrix) {
        int count = 0;

        for (int i = 0; i < matrix.func_70302_i_(); ++i) {
            if (this.isDamageableComponent(matrix.func_70301_a(i))) {
                ++count;
            }
        }

        return count;
    }

    public int countDamagedParts(ItemStack input) {
        int totalMax4 = Math.max(4, this.countDamageableParts(this.uncraftingMatrix));
        float damage = (float) input.func_77960_j() / (float) input.func_77958_k();
        int damagedParts = (int) Math.ceil((double) ((float) totalMax4 * damage));

        return damagedParts;
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotNum) {
        ItemStack copyItem = null;
        Slot transferSlot = (Slot) this.field_75151_b.get(slotNum);

        if (transferSlot != null && transferSlot.func_75216_d()) {
            ItemStack transferStack = transferSlot.func_75211_c();

            copyItem = transferStack.func_77946_l();
            if (slotNum != 0 && slotNum != 1) {
                if (slotNum >= 20 && slotNum < 47) {
                    if (!this.func_75135_a(transferStack, 47, 56, false)) {
                        return null;
                    }
                } else if (slotNum >= 47 && slotNum < 56) {
                    if (!this.func_75135_a(transferStack, 20, 47, false)) {
                        return null;
                    }
                } else if (!this.func_75135_a(transferStack, 20, 56, false)) {
                    return null;
                }
            } else {
                if (!this.func_75135_a(transferStack, 20, 56, true)) {
                    return null;
                }

                transferSlot.func_75220_a(transferStack, copyItem);
            }

            if (transferStack.field_77994_a == 0) {
                transferSlot.func_75215_d((ItemStack) null);
            } else {
                transferSlot.func_75218_e();
            }

            if (transferStack.field_77994_a == copyItem.field_77994_a) {
                return null;
            }

            transferSlot.func_82870_a(player, transferStack);
        }

        return copyItem;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            for (int inputStack = 0; inputStack < 9; ++inputStack) {
                ItemStack assemblyStack = this.assemblyMatrix.func_70304_b(inputStack);

                if (assemblyStack != null) {
                    par1EntityPlayer.func_71019_a(assemblyStack, false);
                }
            }

            ItemStack itemstack = this.tinkerInput.func_70304_b(0);

            if (itemstack != null) {
                par1EntityPlayer.func_71019_a(itemstack, false);
            }
        }

    }

    public ItemStack[] getRecipeItems(IRecipe recipe) {
        return recipe instanceof ShapedRecipes ? this.getRecipeItemsShaped((ShapedRecipes) recipe) : (recipe instanceof ShapedOreRecipe ? this.getRecipeItemsOre((ShapedOreRecipe) recipe) : null);
    }

    public ItemStack[] getRecipeItemsShaped(ShapedRecipes shaped) {
        return shaped.field_77574_d;
    }

    public ItemStack[] getRecipeItemsOre(ShapedOreRecipe shaped) {
        try {
            Object[] e = (Object[]) ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, shaped, 3);
            ItemStack[] items = new ItemStack[e.length];

            for (int i = 0; i < e.length; ++i) {
                if (e[i] instanceof ItemStack) {
                    items[i] = (ItemStack) e[i];
                }

                if (e[i] instanceof ArrayList && ((ArrayList) e[i]).size() > 0) {
                    items[i] = (ItemStack) ((ArrayList) e[i]).get(0);
                }
            }

            return items;
        } catch (IllegalArgumentException illegalargumentexception) {
            illegalargumentexception.printStackTrace();
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
        }

        return null;
    }

    public int getRecipeWidth(IRecipe recipe) {
        return recipe instanceof ShapedRecipes ? this.getRecipeWidthShaped((ShapedRecipes) recipe) : (recipe instanceof ShapedOreRecipe ? this.getRecipeWidthOre((ShapedOreRecipe) recipe) : -1);
    }

    public int getRecipeWidthShaped(ShapedRecipes shaped) {
        return shaped.field_77576_b;
    }

    public int getRecipeWidthOre(ShapedOreRecipe shaped) {
        try {
            return ((Integer) ((Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, shaped, 4))).intValue();
        } catch (IllegalArgumentException illegalargumentexception) {
            illegalargumentexception.printStackTrace();
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
        }

        return 0;
    }

    public int getRecipeHeight(IRecipe recipe) {
        return recipe instanceof ShapedRecipes ? this.getRecipeHeightShaped((ShapedRecipes) recipe) : (recipe instanceof ShapedOreRecipe ? this.getRecipeHeightOre((ShapedOreRecipe) recipe) : -1);
    }

    public int getRecipeHeightShaped(ShapedRecipes shaped) {
        return shaped.field_77577_c;
    }

    public int getRecipeHeightOre(ShapedOreRecipe shaped) {
        try {
            return ((Integer) ((Integer) ObfuscationReflectionHelper.getPrivateValue(ShapedOreRecipe.class, shaped, 5))).intValue();
        } catch (IllegalArgumentException illegalargumentexception) {
            illegalargumentexception.printStackTrace();
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
        }

        return 0;
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }
}
