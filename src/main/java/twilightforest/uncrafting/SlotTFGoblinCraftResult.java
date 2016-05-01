package twilightforest.uncrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class SlotTFGoblinCraftResult extends SlotCrafting {

    protected EntityPlayer field_75238_b;
    protected IInventory inputSlot;
    protected InventoryTFGoblinUncrafting uncraftingMatrix;
    protected InventoryCrafting assemblyMatrix;
    protected IInventory resultSlot;

    public SlotTFGoblinCraftResult(EntityPlayer player, IInventory input, IInventory uncraftingMatrix, IInventory assemblyMatrix, IInventory result, int slotIndex, int x, int y) {
        super(player, assemblyMatrix, result, slotIndex, x, y);
        this.field_75238_b = player;
        this.inputSlot = input;
        this.uncraftingMatrix = (InventoryTFGoblinUncrafting) uncraftingMatrix;
        this.assemblyMatrix = (InventoryCrafting) assemblyMatrix;
    }

    public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par1ItemStack) {
        boolean combined = true;

        if (ItemStack.func_77989_b(CraftingManager.func_77594_a().func_82787_a(this.assemblyMatrix, this.field_75238_b.field_70170_p), par1ItemStack)) {
            combined = false;
        }

        if (combined) {
            if (this.uncraftingMatrix.recraftingCost > 0) {
                this.field_75238_b.func_82242_a(-this.uncraftingMatrix.recraftingCost);
            }

            for (int i = 0; i < this.uncraftingMatrix.func_70302_i_(); ++i) {
                this.uncraftingMatrix.func_70299_a(i, (ItemStack) null);
                this.inputSlot.func_70298_a(0, this.uncraftingMatrix.numberOfInputItems);
            }
        }

        super.func_82870_a(par1EntityPlayer, par1ItemStack);
    }
}
