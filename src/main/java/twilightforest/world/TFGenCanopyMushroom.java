package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenCanopyMushroom extends TFTreeGenerator {

    public TFGenCanopyMushroom() {
        this(false);
    }

    public TFGenCanopyMushroom(boolean par1) {
        super(par1);
        this.treeBlock = Blocks.field_150419_aX;
        this.treeMeta = 10;
        this.branchMeta = 15;
        this.leafBlock = Blocks.field_150419_aX;
        this.leafMeta = 5;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        int treeHeight = 12;

        if (random.nextInt(3) == 0) {
            treeHeight += random.nextInt(5);
            if (random.nextInt(8) == 0) {
                treeHeight += random.nextInt(5);
            }
        }

        Block blockUnder = world.func_147439_a(x, y - 1, z);

        if ((blockUnder == Blocks.field_150349_c || blockUnder == Blocks.field_150346_d || blockUnder == Blocks.field_150391_bh) && y < 256 - treeHeight - 1) {
            this.treeBlock = random.nextInt(3) == 0 ? Blocks.field_150419_aX : Blocks.field_150420_aW;
            this.leafBlock = this.treeBlock;
            this.buildBranch(world, x, y, z, 0, (double) treeHeight, 0.0D, 0.0D, true, random);
            int numBranches = 3 + random.nextInt(2);
            double offset = random.nextDouble();

            for (int b = 0; b < numBranches; ++b) {
                this.buildBranch(world, x, y, z, treeHeight - 5 + b, 9.0D, 0.3D * (double) b + offset, 0.2D, false, random);
            }

            return true;
        } else {
            return false;
        }
    }

    void buildBranch(World world, int x, int y, int z, int height, double length, double angle, double tilt, boolean trunk, Random treeRNG) {
        ChunkCoordinates src = new ChunkCoordinates(x, y + height, z);
        ChunkCoordinates dest = translateCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, length, angle, tilt);

        if (dest.field_71574_a - x < -4) {
            dest.field_71574_a = x - 4;
        }

        if (dest.field_71574_a - x > 4) {
            dest.field_71574_a = x + 4;
        }

        if (dest.field_71573_c - z < -4) {
            dest.field_71573_c = z - 4;
        }

        if (dest.field_71573_c - z > 4) {
            dest.field_71573_c = z + 4;
        }

        if (src.field_71574_a == dest.field_71574_a && src.field_71573_c == dest.field_71573_c) {
            this.drawBresehnam(world, src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, dest.field_71572_b - 1, dest.field_71573_c, this.treeBlock, this.treeMeta);
        } else {
            this.drawBresehnam(world, src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, src.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
            this.drawBresehnam(world, dest.field_71574_a, src.field_71572_b + 1, dest.field_71573_c, dest.field_71574_a, dest.field_71572_b - 1, dest.field_71573_c, this.treeBlock, this.treeMeta);
        }

        if (trunk) {
            this.addFirefly(world, x, y, z, 3 + treeRNG.nextInt(7), treeRNG.nextDouble());
        }

        this.drawMushroomCircle(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, 4, this.leafBlock);
    }

    public void drawMushroomCircle(World world, int sx, int sy, int sz, int rad, Block blockValue) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dz = 0; dz <= rad; ++dz) {
                int dist = (int) ((double) Math.max(dx, dz) + (double) Math.min(dx, dz) * 0.5D);

                if (dx == 3 && dz == 3) {
                    dist = 6;
                }

                if (dx == 0) {
                    if (dz < rad) {
                        this.setBlockAndMetadata(world, sx + 0, sy, sz + dz, blockValue, 5);
                        this.setBlockAndMetadata(world, sx + 0, sy, sz - dz, blockValue, 5);
                    } else {
                        this.setBlockAndMetadata(world, sx + 0, sy, sz + dz, blockValue, 8);
                        this.setBlockAndMetadata(world, sx + 0, sy, sz - dz, blockValue, 2);
                    }
                } else if (dz == 0) {
                    if (dx < rad) {
                        this.setBlockAndMetadata(world, sx + dx, sy, sz + 0, blockValue, 5);
                        this.setBlockAndMetadata(world, sx - dx, sy, sz + 0, blockValue, 5);
                    } else {
                        this.setBlockAndMetadata(world, sx + dx, sy, sz + 0, blockValue, 6);
                        this.setBlockAndMetadata(world, sx - dx, sy, sz + 0, blockValue, 4);
                    }
                } else if (dist < rad) {
                    this.setBlockAndMetadata(world, sx + dx, sy, sz + dz, blockValue, 5);
                    this.setBlockAndMetadata(world, sx + dx, sy, sz - dz, blockValue, 5);
                    this.setBlockAndMetadata(world, sx - dx, sy, sz + dz, blockValue, 5);
                    this.setBlockAndMetadata(world, sx - dx, sy, sz - dz, blockValue, 5);
                } else if (dist == rad) {
                    this.setBlockAndMetadata(world, sx + dx, sy, sz + dz, blockValue, 9);
                    this.setBlockAndMetadata(world, sx + dx, sy, sz - dz, blockValue, 3);
                    this.setBlockAndMetadata(world, sx - dx, sy, sz + dz, blockValue, 7);
                    this.setBlockAndMetadata(world, sx - dx, sy, sz - dz, blockValue, 1);
                }
            }
        }

    }

    private void addFirefly(World world, int x, int y, int z, int height, double angle) {
        int iAngle = (int) (angle * 4.0D);

        if (iAngle == 0) {
            this.setBlockAndMetadata(world, x + 1, y + height, z, TFBlocks.firefly, 0);
        } else if (iAngle == 1) {
            this.setBlockAndMetadata(world, x - 1, y + height, z, TFBlocks.firefly, 0);
        } else if (iAngle == 2) {
            this.setBlockAndMetadata(world, x, y + height, z + 1, TFBlocks.firefly, 0);
        } else if (iAngle == 3) {
            this.setBlockAndMetadata(world, x, y + height, z - 1, TFBlocks.firefly, 0);
        }

    }
}
