package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenCanopyOak extends TFGenCanopyTree {

    public TFGenCanopyOak() {
        this(false);
    }

    public TFGenCanopyOak(boolean par1) {
        super(par1);
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 0;
        this.branchMeta = 12;
        this.leafBlock = TFBlocks.leaves;
        this.leafMeta = 0;
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

            this.buildTrunk(world, x, y, z, treeHeight);
            int numBranches = 12 + random.nextInt(9);
            float bangle = random.nextFloat();

            for (int b = 0; b < numBranches; ++b) {
                float btilt = 0.15F + random.nextFloat() * 0.35F;

                this.buildBranch(world, x, y, z, treeHeight - 10 + b / 2, 5.0D, (double) bangle, (double) btilt, false, random);
                bangle += random.nextFloat() * 0.4F;
                if (bangle > 1.0F) {
                    --bangle;
                }
            }

            this.makeRoots(world, random, x + 0, y, z + 0);
            this.makeRoots(world, random, x + 1, y, z + 0);
            this.makeRoots(world, random, x + 0, y, z + 1);
            this.makeRoots(world, random, x + 1, y, z + 1);
            return true;
        } else {
            return false;
        }
    }

    private void makeRoots(World world, Random random, int x, int y, int z) {
        if (hasAirAround(world, x, y - 1, z)) {
            this.setBlockAndMetadata(world, x, y - 1, z, this.treeBlock, this.treeMeta);
        } else {
            this.setBlockAndMetadata(world, x, y - 1, z, this.rootBlock, this.rootMeta);
        }

        int numRoots = 1 + random.nextInt(2);
        float offset = random.nextFloat();

        for (int b = 0; b < numRoots; ++b) {
            this.buildRoot(world, x, y, z, (double) offset, b);
        }

    }

    private void buildTrunk(World world, int sx, int sy, int sz, int treeHeight) {
        for (int dy = 0; dy < treeHeight; ++dy) {
            this.setBlockAndMetadata(world, sx + 0, sy + dy, sz + 0, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, sx + 1, sy + dy, sz + 0, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, sx + 0, sy + dy, sz + 1, this.treeBlock, this.treeMeta);
            this.setBlockAndMetadata(world, sx + 1, sy + dy, sz + 1, this.treeBlock, this.treeMeta);
        }

        this.drawLeafBlob(world, sx + 0, sy + treeHeight, sz + 0, 3, this.leafBlock, this.leafMeta);
    }

    void buildBranch(World world, int x, int y, int z, int height, double length, double angle, double tilt, boolean trunk, Random treeRNG) {
        ChunkCoordinates src = new ChunkCoordinates(x, y + height, z);
        ChunkCoordinates dest = translateCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, length, angle, tilt);
        byte limit = 5;

        if (dest.field_71574_a - x < -limit) {
            dest.field_71574_a = x - limit;
        }

        if (dest.field_71574_a - x > limit) {
            dest.field_71574_a = x + limit;
        }

        if (dest.field_71573_c - z < -limit) {
            dest.field_71573_c = z - limit;
        }

        if (dest.field_71573_c - z > limit) {
            dest.field_71573_c = z + limit;
        }

        this.drawBresehnam(world, src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, this.treeBlock, trunk ? this.treeMeta : this.branchMeta);
        if (trunk) {
            this.addFirefly(world, x, y, z, 3 + treeRNG.nextInt(7), treeRNG.nextDouble());
        }

        byte blobSize = 2;

        this.drawLeafBlob(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, blobSize, this.leafBlock, this.leafMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a + 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a - 1, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c + 1, this.treeBlock, this.branchMeta);
        this.setBlockAndMetadata(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c - 1, this.treeBlock, this.branchMeta);
    }
}
