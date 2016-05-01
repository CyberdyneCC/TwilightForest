package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenTreeOfTransformation extends TFGenCanopyTree {

    public TFGenTreeOfTransformation() {
        this(false);
    }

    public TFGenTreeOfTransformation(boolean notify) {
        super(notify);
        this.treeBlock = TFBlocks.magicLog;
        this.treeMeta = 1;
        this.branchMeta = this.treeMeta | 12;
        this.leafBlock = TFBlocks.magicLeaves;
        this.leafMeta = 1;
        this.minHeight = 11;
        this.chanceAddFirstFive = Integer.MAX_VALUE;
        this.chanceAddSecondFive = Integer.MAX_VALUE;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        if (super.func_76484_a(world, random, x, y, z)) {
            this.setBlockAndMetadata(world, x, y + 3, z, TFBlocks.magicLogSpecial, 1);
            return true;
        } else {
            return false;
        }
    }
}
