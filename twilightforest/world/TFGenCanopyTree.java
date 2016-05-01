package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenCanopyTree extends TFTreeGenerator {

    protected int minHeight;
    protected int chanceAddFirstFive;
    protected int chanceAddSecondFive;

    public TFGenCanopyTree() {
        this(false);
    }

    public TFGenCanopyTree(boolean par1) {
        super(par1);
        this.minHeight = 20;
        this.chanceAddFirstFive = 3;
        this.chanceAddSecondFive = 8;
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 1;
        this.branchMeta = 13;
        this.leafBlock = TFBlocks.leaves;
        this.leafMeta = 1;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        Material materialUnder = world.func_147439_a(x, y - 1, z).func_149688_o();

        if ((materialUnder == Material.field_151577_b || materialUnder == Material.field_151578_c) && y < TFWorld.MAXHEIGHT - 12) {
            int treeHeight = this.minHeight;

            if (random.nextInt(this.chanceAddFirstFive) == 0) {
                treeHeight += random.nextInt(5);
                if (random.nextInt(this.chanceAddSecondFive) == 0) {
                    treeHeight += random.nextInt(5);
                }
            }

            this.buildBranch(world, x, y, z, 0, (double) treeHeight, 0.0D, 0.0D, true, random);
            int numBranches = 3 + random.nextInt(2);
            float offset = random.nextFloat();

            int numRoots;

            for (numRoots = 0; numRoots < numBranches; ++numRoots) {
                this.buildBranch(world, x, y, z, treeHeight - 10 + numRoots, 9.0D, 0.3D * (double) numRoots + (double) offset, 0.2D, false, random);
            }

            if (hasAirAround(world, x, y - 1, z)) {
                this.setBlockAndMetadata(world, x, y - 1, z, this.treeBlock, this.treeMeta);
            } else {
                this.setBlockAndMetadata(world, x, y - 1, z, this.rootBlock, this.rootMeta);
            }

            numRoots = 3 + random.nextInt(2);
            offset = random.nextFloat();

            for (int b = 0; b < numRoots; ++b) {
                this.buildRoot(world, x, y, z, (double) offset, b);
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

        this.drawBresehnam(world, src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, this.treeBlock, trunk ? this.treeMeta : this.branchMeta);
        if (trunk) {
            this.addFirefly(world, x, y, z, 3 + treeRNG.nextInt(7), treeRNG.nextDouble());
        }

        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b - 1, dest.field_71573_c, 3, this.leafBlock, this.leafMeta, true);
        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, 4, this.leafBlock, this.leafMeta, true);
        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b + 1, dest.field_71573_c, 2, this.leafBlock, this.leafMeta, true);
        this.setBlockAndMetadata(world, dest.field_71574_a + 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a - 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c + 1, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c - 1, this.treeBlock, this.branchMeta);
    }

    protected void addFirefly(World world, int x, int y, int z, int height, double angle) {
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
