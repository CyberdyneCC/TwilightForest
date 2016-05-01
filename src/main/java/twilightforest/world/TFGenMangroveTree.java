package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenMangroveTree extends TFTreeGenerator {

    boolean checkForWater;

    public TFGenMangroveTree() {
        this(false);
    }

    public TFGenMangroveTree(boolean par1) {
        super(par1);
        this.checkForWater = !par1;
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 2;
        this.branchMeta = 14;
        this.leafBlock = TFBlocks.leaves;
        this.leafMeta = 2;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        if ((!this.checkForWater || world.func_147439_a(x, y - 1, z) == Blocks.field_150355_j) && y < 109) {
            this.buildBranch(world, random, x, y, z, 5, (double) (6 + random.nextInt(3)), 0.0D, 0.0D, true);
            int numBranches = random.nextInt(3);
            double offset = random.nextDouble();

            int numRoots;

            for (numRoots = 0; numRoots < numBranches; ++numRoots) {
                this.buildBranch(world, random, x, y, z, 7 + numRoots, (double) (6 + random.nextInt(2)), 0.3D * (double) numRoots + offset, 0.25D, false);
            }

            numRoots = 3 + random.nextInt(2);
            offset = random.nextDouble();

            for (int i = 0; i < numRoots; ++i) {
                double rTilt = 0.75D + random.nextDouble() * 0.1D;

                this.buildRoot(world, x, y, z, 5, 12.0D, 0.4D * (double) i + offset, rTilt);
            }

            this.addFirefly(world, x, y, z, 5 + random.nextInt(5), random.nextDouble());
            return true;
        } else {
            return false;
        }
    }

    void buildBranch(World world, Random random, int x, int y, int z, int height, double length, double angle, double tilt, boolean trunk) {
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
        int bSize = 2 + random.nextInt(3);

        if (bSize > 2) {
            this.setBlockAndMetadata(world, dest.field_71574_a + 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
            this.setBlockAndMetadata(world, dest.field_71574_a - 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
            this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c + 1, this.treeBlock, this.branchMeta);
            this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c - 1, this.treeBlock, this.branchMeta);
        }

        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b - 1, dest.field_71573_c, (byte) (bSize - 1), this.leafBlock, this.leafMeta);
        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, (byte) bSize, this.leafBlock, this.leafMeta);
        this.makeLeafCircle(world, dest.field_71574_a, dest.field_71572_b + 1, dest.field_71573_c, (byte) (bSize - 2), this.leafBlock, this.leafMeta);
    }

    void buildRoot(World world, int x, int y, int z, int height, double length, double angle, double tilt) {
        ChunkCoordinates src = new ChunkCoordinates(x, y + height, z);
        ChunkCoordinates dest = translateCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, length, angle, tilt);
        ChunkCoordinates[] lineArray = getBresehnamArrayCoords(src, dest);
        boolean stillAboveGround = true;
        ChunkCoordinates[] achunkcoordinates = lineArray;
        int i = lineArray.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordinates coord = achunkcoordinates[j];

            if (stillAboveGround && hasAirAround(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c)) {
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, this.treeBlock, this.branchMeta);
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b - 1, coord.field_71573_c, this.treeBlock, this.branchMeta);
            } else {
                this.placeRootBlock(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, this.rootBlock, this.rootMeta);
                this.placeRootBlock(world, coord.field_71574_a, coord.field_71572_b - 1, coord.field_71573_c, this.rootBlock, this.rootMeta);
                stillAboveGround = false;
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
