package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import twilightforest.block.TFBlocks;

public class ItemBlockTFLeaves extends ItemBlock {

    public ItemBlockTFLeaves(Block par1) {
        super(par1);
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    public IIcon func_77617_a(int par1) {
        return TFBlocks.leaves.func_149691_a(2, par1);
    }

    public String func_77667_c(ItemStack itemstack) {
        int meta = itemstack.func_77960_j();

        return super.func_77658_a() + "." + meta;
    }

    public int func_77647_b(int i) {
        return i;
    }
}
