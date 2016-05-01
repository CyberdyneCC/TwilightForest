package twilightforest.uncrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryTFGoblinInput implements IInventory {

    private ItemStack[] stackInput = new ItemStack[1];
    private ContainerTFUncrafting craftingContainer;

    public InventoryTFGoblinInput(ContainerTFUncrafting containerTFGoblinCrafting) {
        this.craftingContainer = containerTFGoblinCrafting;
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.stackInput[par1];
    }

    public String func_145825_b() {
        return "Input";
    }

    public ItemStack func_70298_a(int slotNum, int amount) {
        if (this.stackInput[slotNum] != null) {
            ItemStack takenStack;

            if (this.stackInput[slotNum].field_77994_a <= amount) {
                takenStack = this.stackInput[slotNum];
                this.stackInput[slotNum] = null;
                this.craftingContainer.func_75130_a(this);
                return takenStack;
            } else {
                takenStack = this.stackInput[slotNum].func_77979_a(amount);
                if (this.stackInput[slotNum].field_77994_a == 0) {
                    this.stackInput[slotNum] = null;
                }

                this.craftingContainer.func_75130_a(this);
                return takenStack;
            }
        } else {
            return null;
        }
    }

    public ItemStack func_70304_b(int par1) {
        if (this.stackInput[par1] != null) {
            ItemStack itemstack = this.stackInput[par1];

            this.stackInput[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.stackInput[par1] = par2ItemStack;
        this.craftingContainer.func_75130_a(this);
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
        this.craftingContainer.func_75130_a(this);
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public boolean func_145818_k_() {
        return false;
    }
}
