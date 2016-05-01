package twilightforest.uncrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import twilightforest.TwilightForestMod;

public class SlotTFGoblinUncrafting extends Slot {

    protected EntityPlayer thePlayer;
    protected IInventory inputSlot;
    protected InventoryTFGoblinUncrafting uncraftingMatrix;
    protected IInventory assemblyMatrix;

    public SlotTFGoblinUncrafting(EntityPlayer par1EntityPlayer, IInventory inputSlot, InventoryTFGoblinUncrafting uncraftingMatrix, IInventory assemblyMatrix, int slotNum, int x, int y) {
        super(uncraftingMatrix, slotNum, x, y);
        this.thePlayer = par1EntityPlayer;
        this.inputSlot = inputSlot;
        this.uncraftingMatrix = uncraftingMatrix;
        this.assemblyMatrix = assemblyMatrix;
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return false;
    }

    public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
        for (int i = 0; i < this.assemblyMatrix.func_70302_i_(); ++i) {
            if (this.assemblyMatrix.func_70301_a(i) != null) {
                return false;
            }
        }

        if (TwilightForestMod.disableUncrafting) {
            return false;
        } else if (this.uncraftingMatrix.uncraftingCost > par1EntityPlayer.field_71068_ca && !par1EntityPlayer.field_71075_bZ.field_75098_d) {
            return false;
        } else {
            return true;
        }
    }

    public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par1ItemStack) {
        super.func_82870_a(par1EntityPlayer, par1ItemStack);
        if (this.uncraftingMatrix.uncraftingCost > 0) {
            this.thePlayer.func_82242_a(-this.uncraftingMatrix.uncraftingCost);
        }

        for (int inputStack = 0; inputStack < 9; ++inputStack) {
            ItemStack transferStack = this.uncraftingMatrix.func_70301_a(inputStack);

            if (transferStack != null && transferStack.field_77994_a > 0) {
                this.assemblyMatrix.func_70299_a(inputStack, transferStack.func_77946_l());
            }
        }

        ItemStack itemstack = this.inputSlot.func_70301_a(0);

        if (itemstack != null) {
            this.inputSlot.func_70298_a(0, this.uncraftingMatrix.numberOfInputItems);
        }

    }
}
