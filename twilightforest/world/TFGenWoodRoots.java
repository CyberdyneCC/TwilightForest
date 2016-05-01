package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenWoodRoots extends TFGenerator {

    private Block rootBlock;
    private int rootMeta;
    private Block oreBlock;
    private int oreMeta;

    public TFGenWoodRoots() {
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
        this.oreBlock = TFBlocks.root;
        this.oreMeta = 1;
    }

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) != Blocks.field_150348_b) {
            return false;
        } else {
            float length = rand.nextFloat() * 6.0F + rand.nextFloat() * 6.0F + 4.0F;

            if (length > (float) y) {
                length = (float) y;
            }

            float tilt = 0.6F + rand.nextFloat() * 0.3F;

            return this.drawRoot(world, rand, x, y, z, length, rand.nextFloat(), tilt);
        }
    }

    private boolean drawRoot(World world, Random rand, int x, int y, int z, float length, float angle, float tilt) {
        return this.drawRoot(world, rand, x, y, z, x, y, z, length, angle, tilt);
    }

    private boolean drawRoot(World world, Random rand, int ox, int oy, int oz, int x, int y, int z, float length, float angle, float tilt) {
        int[] dest = translate(x, y, z, (double) length, (double) angle, (double) tilt);
        byte limit = 6;

        if (ox + limit < dest[0]) {
            dest[0] = ox + limit;
        }

        if (ox - limit > dest[0]) {
            dest[0] = ox - limit;
        }

        if (oz + limit < dest[2]) {
            dest[2] = oz + limit;
        }

        if (oz - limit > dest[2]) {
            dest[2] = oz - limit;
        }

        if (world.func_147439_a(dest[0], dest[1], dest[2]) != Blocks.field_150348_b) {
            return false;
        } else {
            ChunkCoordinates[] lineArray = getBresehnamArrayCoords(x, y, z, dest[0], dest[1], dest[2]);
            ChunkCoordinates[] ballSrc = lineArray;
            int ballDest = lineArray.length;

            for (int nextTilt = 0; nextTilt < ballDest; ++nextTilt) {
                ChunkCoordinates coord = ballSrc[nextTilt];

                this.placeRootBlock(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, this.rootBlock, this.rootMeta);
            }

            int[] aint;

            if (length > 8.0F && rand.nextInt(3) > 0) {
                aint = translate(x, y, z, (double) (length / 2.0F), (double) angle, (double) tilt);
                float f = (angle + 0.25F + rand.nextFloat() * 0.5F) % 1.0F;
                float f1 = 0.6F + rand.nextFloat() * 0.3F;

                this.drawRoot(world, rand, ox, oy, oz, aint[0], aint[1], aint[2], length / 2.0F, f, f1);
            }

            if (length > 6.0F && rand.nextInt(4) == 0) {
                aint = translate(x, y, z, (double) (length / 2.0F), (double) angle, (double) tilt);
                int[] aint1 = translate(aint[0], aint[1], aint[2], 1.5D, (double) ((angle + 0.5F) % 1.0F), 0.75D);

                this.placeRootBlock(world, aint[0], aint[1], aint[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint[0], aint[1], aint1[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint1[0], aint[1], aint[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint1[0], aint[1], aint1[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint[0], aint1[1], aint[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint[0], aint1[1], aint1[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint1[0], aint1[1], aint[2], this.oreBlock, this.oreMeta);
                this.placeRootBlock(world, aint1[0], aint1[1], aint1[2], this.oreBlock, this.oreMeta);
            }

            return true;
        }
    }

    protected void placeRootBlock(World world, int x, int y, int z, Block rootBlock2, int meta) {
        if (TFTreeGenerator.canRootGrowIn(world, x, y, z)) {
            this.setBlockAndMetadata(world, x, y, z, rootBlock2, meta);
        }

    }
}
