package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import twilightforest.block.TFBlocks;

public class ItemBlockTFLog extends ItemBlock {

    public static final String[] woodNames = new String[] { "oak", "canopy", "mangrove", "darkwood", "x", "root", "oreroot", "rotten"};

    public ItemBlockTFLog(Block log) {
        super(log);
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    public IIcon func_77617_a(int par1) {
        return TFBlocks.log.func_149691_a(2, par1);
    }

    public String func_77667_c(ItemStack itemstack) {
        int meta = itemstack.func_77960_j();
        int i;

        if ((meta & 8) == 0) {
            i = MathHelper.func_76125_a(meta, 0, 7);
            return super.func_77658_a() + "." + ItemBlockTFLog.woodNames[i];
        } else {
            meta &= 7;
            i = MathHelper.func_76125_a(meta, 0, 7);
            return super.func_77658_a() + "." + ItemBlockTFLog.woodNames[i] + ".log";
        }
    }

    public int func_77647_b(int i) {
        return i;
    }
}
