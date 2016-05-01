package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenLargeWinter extends TFTreeGenerator {

    public TFGenLargeWinter() {
        this(false);
    }

    public TFGenLargeWinter(boolean par1) {
        super(par1);
        this.treeBlock = Blocks.field_150364_r;
        this.treeMeta = 1;
        this.branchMeta = 13;
        this.leafBlock = Blocks.field_150362_t;
        this.leafMeta = 1;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        int treeHeight = 35;

        if (random.nextInt(3) == 0) {
            treeHeight += random.nextInt(10);
            if (random.nextInt(8) == 0) {
                treeHeight += random.nextInt(10);
            }
        }

        Block blockUnder = world.func_147439_a(x, y - 1, z);

        if ((blockUnder == Blocks.field_150349_c || blockUnder == Blocks.field_150346_d) && y < TFWorld.MAXHEIGHT - treeHeight) {
            this.buildTrunk(world, x, y, z, treeHeight);
            this.makeLeaves(world, x, y, z, treeHeight);
            int numRoots = 4 + random.nextInt(3);
            float offset = random.nextFloat();

            for (int b = 0; b < numRoots; ++b) {
                this.buildRoot(world, x, y, z, (double) offset, b);
            }

            return true;
        } else {
            return false;
        }
    }

    private void makeLeaves(World world, int x, int y, int z, int treeHeight) {
        byte offGround = 3;
        byte leafType = 1;

        for (int dy = 0; dy < treeHeight; ++dy) {
            int radius = this.leafRadius(treeHeight, dy, leafType);

            this.makeLeafCircle2(world, x, y + offGround + treeHeight - dy, z, radius, this.leafBlock, this.leafMeta, false);
            this.makePineBranches(world, x, y + offGround + treeHeight - dy, z, radius);
        }

    }

    private void makePineBranches(World world, int x, int y, int z, int radius) {
        int branchLength = radius > 4 ? radius - 1 : radius - 2;
        int i;

        switch (y % 2) {
        case 0:
            for (i = 1; i <= branchLength; ++i) {
                this.setBlockAndMetadata(world, x + 0 - i, y, z + 0, this.treeBlock, this.branchMeta & 3 | 4);
                this.setBlockAndMetadata(world, x + 0, y, z + 1 + i, this.treeBlock, this.branchMeta & 3 | 8);
                this.setBlockAndMetadata(world, x + 1 + i, y, z + 1, this.treeBlock, this.branchMeta & 3 | 4);
                this.setBlockAndMetadata(world, x + 1, y, z - 0 - i, this.treeBlock, this.branchMeta & 3 | 8);
            }

            return;

        case 1:
            for (i = 1; i <= branchLength; ++i) {
                this.setBlockAndMetadata(world, x + 0 - i, y, z + 1, this.treeBlock, this.branchMeta & 3 | 4);
                this.setBlockAndMetadata(world, x + 1, y, z + 1 + i, this.treeBlock, this.branchMeta & 3 | 8);
                this.setBlockAndMetadata(world, x + 1 + i, y, z + 0, this.treeBlock, this.branchMeta & 3 | 4);
                this.setBlockAndMetadata(world, x + 0, y, z - 0 - i, this.treeBlock, this.branchMeta & 3 | 8);
            }
        }

    }

    private int leafRadius(int treeHeight, int dy, int functionType) {
        switch (functionType) {
        case 0:
        default:
            return (dy - 1) % 4;

        case 1:
            return (int) (4.0F * (float) dy / (float) treeHeight + 0.75F * (float) dy % 3.0F);

        case 99:
            return (treeHeight - dy / 2 - 1) % 4;
        }
    }

    private void buildTrunk(World world, int x, int y, int z, int treeHeight) {
        for (int dy = 0; dy < treeHeight; ++dy) {
            this.setBlockAndMetadata(world, x + 0, y + dy, z + 0, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, x + 1, y + dy, z + 0, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, x + 0, y + dy, z + 1, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, x + 1, y + dy, z + 1, this.treeBlock, this.treeMeta);
        }

    }
}
