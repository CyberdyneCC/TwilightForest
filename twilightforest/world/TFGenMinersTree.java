package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenMinersTree extends TFTreeGenerator {

    public TFGenMinersTree() {
        this(false);
    }

    public TFGenMinersTree(boolean notify) {
        super(notify);
        this.treeBlock = TFBlocks.magicLog;
        this.treeMeta = 2;
        this.branchMeta = this.treeMeta | 12;
        this.leafBlock = TFBlocks.magicLeaves;
        this.leafMeta = 2;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        Material materialUnder = world.func_147439_a(x, y - 1, z).func_149688_o();

        if ((materialUnder == Material.field_151577_b || materialUnder == Material.field_151578_c) && y < TFWorld.MAXHEIGHT - 12) {
            int numRoots;

            for (numRoots = 0; numRoots < 10; ++numRoots) {
                this.setBlockAndMetadata(world, x, y + numRoots, z, this.treeBlock, this.branchMeta);
            }

            this.putBranchWithLeaves(world, x, y + 9, z + 1, true);
            this.putBranchWithLeaves(world, x, y + 9, z + 2, false);
            this.putBranchWithLeaves(world, x, y + 8, z + 3, false);
            this.putBranchWithLeaves(world, x, y + 7, z + 4, false);
            this.putBranchWithLeaves(world, x, y + 6, z + 5, false);
            this.putBranchWithLeaves(world, x, y + 9, z - 1, true);
            this.putBranchWithLeaves(world, x, y + 9, z - 2, false);
            this.putBranchWithLeaves(world, x, y + 8, z - 3, false);
            this.putBranchWithLeaves(world, x, y + 7, z - 4, false);
            this.putBranchWithLeaves(world, x, y + 6, z - 5, false);
            this.setBlockAndMetadata(world, x, y + 1, z, TFBlocks.magicLogSpecial, 2);
            world.func_147464_a(x, y + 1, z, TFBlocks.magicLogSpecial, TFBlocks.magicLogSpecial.func_149738_a(world));
            if (hasAirAround(world, x, y - 1, z)) {
                this.setBlockAndMetadata(world, x, y - 1, z, this.treeBlock, this.treeMeta);
            } else {
                this.setBlockAndMetadata(world, x, y - 1, z, this.rootBlock, this.rootMeta);
            }

            numRoots = 3 + rand.nextInt(2);
            double offset = rand.nextDouble();

            for (int b = 0; b < numRoots; ++b) {
                this.buildRoot(world, x, y, z, offset, b);
            }

            return true;
        } else {
            return false;
        }
    }

    protected void putBranchWithLeaves(World world, int bx, int by, int bz, boolean bushy) {
        this.setBlockAndMetadata(world, bx, by, bz, this.treeBlock, this.branchMeta);

        for (int lx = -1; lx <= 1; ++lx) {
            for (int ly = -1; ly <= 1; ++ly) {
                for (int lz = -1; lz <= 1; ++lz) {
                    if (bushy || Math.abs(ly) <= 0 || Math.abs(lx) <= 0) {
                        this.putLeafBlock(world, bx + lx, by + ly, bz + lz, this.leafBlock, this.leafMeta);
                    }
                }
            }
        }

    }
}
