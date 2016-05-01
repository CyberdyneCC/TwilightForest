package twilightforest.item;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class TFMapCloningRecipe implements IRecipe {

    public Item fullMapID;
    public Item blankMapID;

    public TFMapCloningRecipe(Item magicMap, Item emptyMagicMap) {
        this.fullMapID = magicMap;
        this.blankMapID = emptyMagicMap;
    }

    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World) {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < par1InventoryCrafting.func_70302_i_(); ++j) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(j);

            if (itemstack1 != null) {
                if (itemstack1.func_77973_b() == this.fullMapID) {
                    if (itemstack != null) {
                        return false;
                    }

                    itemstack = itemstack1;
                } else {
                    if (itemstack1.func_77973_b() != this.blankMapID) {
                        return false;
                    }

                    ++i;
                }
            }
        }

        return itemstack != null && i > 0;
    }

    public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < par1InventoryCrafting.func_70302_i_(); ++j) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(j);

            if (itemstack1 != null) {
                if (itemstack1.func_77973_b() == this.fullMapID) {
                    if (itemstack != null) {
                        return null;
                    }

                    itemstack = itemstack1;
                } else {
                    if (itemstack1.func_77973_b() != this.blankMapID) {
                        return null;
                    }

                    ++i;
                }
            }
        }

        if (itemstack != null && i >= 1) {
            ItemStack itemstack2 = new ItemStack(this.fullMapID, i + 1, itemstack.func_77960_j());

            if (itemstack.func_82837_s()) {
                itemstack2.func_151001_c(itemstack.func_82833_r());
            }

            return itemstack2;
        } else {
            return null;
        }
    }

    public int func_77570_a() {
        return 9;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}
