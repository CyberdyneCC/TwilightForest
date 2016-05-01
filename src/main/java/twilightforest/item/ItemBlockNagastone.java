package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import twilightforest.block.TFBlocks;

public class ItemBlockNagastone extends ItemBlock {

    public ItemBlockNagastone(Block block) {
        super(block);
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    public IIcon func_77617_a(int i) {
        int j = MathHelper.func_76125_a(i, 0, 15);

        return TFBlocks.nagastone.func_149691_a(2, j);
    }

    public String func_77667_c(ItemStack itemstack) {
        int meta = itemstack.func_77960_j();

        return super.func_77658_a() + "." + meta;
    }

    public int func_77647_b(int i) {
        return i;
    }
}
