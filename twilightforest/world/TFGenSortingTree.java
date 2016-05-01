package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenSortingTree extends TFGenerator {

    protected Block treeBlock;
    protected int treeMeta;
    protected int branchMeta;
    protected Block leafBlock;
    protected int leafMeta;
    protected Block rootBlock;
    protected int rootMeta;

    public TFGenSortingTree() {
        this(false);
    }

    public TFGenSortingTree(boolean notify) {
        super(notify);
        this.treeBlock = TFBlocks.magicLog;
        this.treeMeta = 3;
        this.branchMeta = this.treeMeta | 12;
        this.leafBlock = TFBlocks.magicLeaves;
        this.leafMeta = 3;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        Material materialUnder = world.func_147439_a(x, y - 1, z).func_149688_o();

        if ((materialUnder == Material.field_151577_b || materialUnder == Material.field_151578_c) && y < TFWorld.MAXHEIGHT - 12) {
            for (int dy = 0; dy < 4; ++dy) {
                this.setBlockAndMetadata(world, x, y + dy, z, this.treeBlock, this.treeMeta);
            }

            this.putLeaves(world, x, y + 2, z, false);
            this.putLeaves(world, x, y + 3, z, false);
            this.setBlockAndMetadata(world, x, y + 1, z, TFBlocks.magicLogSpecial, 3);
            return true;
        } else {
            return false;
        }
    }

    protected void putLeaves(World world, int bx, int by, int bz, boolean bushy) {
        for (int lx = -1; lx <= 1; ++lx) {
            for (int ly = -1; ly <= 1; ++ly) {
                for (int lz = -1; lz <= 1; ++lz) {
                    if (bushy || Math.abs(ly) <= 0 || Math.abs(lx) + Math.abs(lz) <= 1) {
                        this.putLeafBlock(world, bx + lx, by + ly, bz + lz, this.leafBlock, this.leafMeta);
                    }
                }
            }
        }

    }
}
