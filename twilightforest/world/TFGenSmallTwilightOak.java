package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenSmallTwilightOak extends TFTreeGenerator {

    protected final int minTreeHeight;

    public TFGenSmallTwilightOak(boolean par1) {
        this(par1, 4);
    }

    public TFGenSmallTwilightOak(boolean par1, int par2) {
        super(par1);
        this.minTreeHeight = par2;
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 0;
        this.branchMeta = this.treeMeta | 12;
        this.leafBlock = TFBlocks.leaves;
        this.leafMeta = 0;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z) {
        int height = par2Random.nextInt(3) + this.minTreeHeight;
        boolean allClear = true;

        if (y >= 1 && y + height + 1 <= 256) {
            byte width;
            int cz;
            Block blockID;

            for (int cy = y; cy <= y + 1 + height; ++cy) {
                width = 1;
                if (cy == y) {
                    width = 0;
                }

                if (cy >= y + 1 + height - 2) {
                    width = 2;
                }

                for (int blockUsing = x - width; blockUsing <= x + width && allClear; ++blockUsing) {
                    for (cz = z - width; cz <= z + width && allClear; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            blockID = par1World.func_147439_a(blockUsing, cy, cz);
                            if (blockID != Blocks.field_150350_a && !blockID.isLeaves(par1World, blockUsing, cy, cz) && blockID != Blocks.field_150349_c && blockID != Blocks.field_150346_d && !blockID.isWood(par1World, blockUsing, cy, cz)) {
                                allClear = false;
                            }
                        } else {
                            allClear = false;
                        }
                    }
                }
            }

            if (!allClear) {
                return false;
            } else {
                Block block = par1World.func_147439_a(x, y - 1, z);

                if ((block == Blocks.field_150349_c || block == Blocks.field_150346_d) && y < 256 - height - 1) {
                    this.setBlock(par1World, x, y - 1, z, Blocks.field_150346_d);
                    width = 3;
                    byte b0 = 0;

                    for (cz = y - width + height; cz <= y + height; ++cz) {
                        int block = cz - (y + height);
                        int treeWidth = b0 + 1 - block / 2;

                        for (int tx = x - treeWidth; tx <= x + treeWidth; ++tx) {
                            int i = tx - x;

                            for (int tz = z - treeWidth; tz <= z + treeWidth; ++tz) {
                                int j = tz - z;
                                Block block1 = par1World.func_147439_a(tx, cz, tz);

                                if ((Math.abs(i) != treeWidth || Math.abs(j) != treeWidth || par2Random.nextInt(2) != 0 && block != 0) && (block1 == null || block1.canBeReplacedByLeaves(par1World, tx, cz, tz))) {
                                    this.setBlockAndMetadata(par1World, tx, cz, tz, this.leafBlock, this.leafMeta);
                                }
                            }
                        }
                    }

                    for (cz = 0; cz < height; ++cz) {
                        blockID = par1World.func_147439_a(x, y + cz, z);
                        if (blockID == Blocks.field_150350_a || blockID == null || blockID.isLeaves(par1World, x, y + cz, z)) {
                            this.setBlockAndMetadata(par1World, x, y + cz, z, this.treeBlock, this.treeMeta);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
