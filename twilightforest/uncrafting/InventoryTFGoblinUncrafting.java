package twilightforest.uncrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import twilightforest.TwilightForestMod;

public class InventoryTFGoblinUncrafting implements IInventory {

    private ItemStack[] contents = new ItemStack[9];
    public int numberOfInputItems;
    public int uncraftingCost;
    public int recraftingCost;

    public InventoryTFGoblinUncrafting(ContainerTFUncrafting containerTFGoblinCrafting) {}

    public int func_70302_i_() {
        return 9;
    }

    public ItemStack func_70301_a(int i) {
        return this.contents[i];
    }

    public ItemStack func_70298_a(int slotNum, int amount) {
        if (this.contents[slotNum] != null) {
            ItemStack takenStack;

            if (this.contents[slotNum].field_77994_a <= amount) {
                takenStack = this.contents[slotNum];
                this.contents[slotNum] = null;
                return takenStack;
            } else {
                takenStack = this.contents[slotNum].func_77979_a(amount);
                if (this.contents[slotNum].field_77994_a == 0) {
                    this.contents[slotNum] = null;
                }

                return takenStack;
            }
        } else {
            return null;
        }
    }

    public ItemStack func_70304_b(int par1) {
        if (this.contents[par1] != null) {
            ItemStack itemstack = this.contents[par1];

            this.contents[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.contents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }

        this.func_70296_d();
    }

    public String func_145825_b() {
        return "twilightforest.goblincrafting";
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {}

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return !TwilightForestMod.disableUncrafting;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return false;
    }

    public boolean func_145818_k_() {
        return false;
    }
}
