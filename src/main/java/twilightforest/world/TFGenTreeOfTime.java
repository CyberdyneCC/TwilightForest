package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenTreeOfTime extends TFGenHollowTree {

    public TFGenTreeOfTime(boolean par1) {
        super(par1);
        this.treeBlock = TFBlocks.magicLog;
        this.treeMeta = 0;
        this.branchMeta = this.treeMeta | 12;
        this.leafBlock = TFBlocks.magicLeaves;
        this.leafMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        byte height = 8;
        byte diameter = 1;

        if (y >= 1 && y + height + diameter <= TFWorld.MAXHEIGHT) {
            Block j1 = world.func_147439_a(x, y - 1, z);

            if (j1 != Blocks.field_150349_c && j1 != Blocks.field_150346_d) {
                return false;
            } else {
                this.buildTrunk(world, random, x, y, z, diameter, height);
                this.buildTinyCrown(world, random, x, y, z, diameter, height);
                this.buildBranchRing(world, random, x, y, z, diameter, 1, 0, 12, 0, 0.75D, 0.0D, 3, 5, 3, false);
                this.buildBranchRing(world, random, x, y, z, diameter, 1, 2, 18, 0, 0.9D, 0.0D, 3, 5, 3, false);
                this.setBlockAndMetadata(world, x - 1, y + 2, z, TFBlocks.magicLogSpecial, 0);
                return true;
            }
        } else {
            return false;
        }
    }

    protected void buildTinyCrown(World world, Random random, int x, int y, int z, int diameter, int height) {
        byte crownRadius = 4;
        byte bvar = 1;

        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius, 0, crownRadius, 0, 0.35D, 0.0D, bvar, bvar + 2, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius / 2, 0, crownRadius, 0, 0.28D, 0.0D, bvar, bvar + 2, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius, 0, 0.15D, 0.0D, 2, 4, 0, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius / 2, 0, 0.05D, 0.0D, bvar, bvar + 2, 0, true);
    }
}
