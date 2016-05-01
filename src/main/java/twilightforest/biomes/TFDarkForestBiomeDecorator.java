package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenDarkCanopyTree;
import twilightforest.world.TFGenTallGrass;
import twilightforest.world.TFTreeGenerator;
import twilightforest.world.TFWorld;

public class TFDarkForestBiomeDecorator extends TFBiomeDecorator {

    TFTreeGenerator darkCanopyTreeGen = new TFGenDarkCanopyTree();
    TFGenTallGrass worldGenDeadBush;
    WorldGenTallGrass worldGenForestGrass;
    WorldGenTallGrass worldGenMushgloom;

    public TFDarkForestBiomeDecorator() {
        this.worldGenDeadBush = new TFGenTallGrass(TFBlocks.plant, 11, 8);
        this.worldGenForestGrass = new WorldGenTallGrass(TFBlocks.plant, 10);
        this.worldGenMushgloom = new WorldGenTallGrass(TFBlocks.plant, 9);
    }

    public void func_150512_a(World world, Random rand, BiomeGenBase biome, int mapX, int mapZ) {
        TFFeature nearFeature = TFFeature.getNearestFeature(mapX >> 4, mapZ >> 4, world);

        if (nearFeature.areChunkDecorationsEnabled) {
            int nc = (int) this.canopyPerChunk + (rand.nextFloat() < this.canopyPerChunk - (float) ((int) this.canopyPerChunk) ? 1 : 0);

            int rx;
            int rz;
            int ry;
            int ry1;

            for (rx = 0; rx < nc; ++rx) {
                rz = mapX + rand.nextInt(16) + 8;
                ry = mapZ + rand.nextInt(16) + 8;
                ry1 = world.func_72976_f(rz, ry);
                this.darkCanopyTreeGen.func_76484_a(world, rand, rz, ry1, ry);
            }

            for (rx = 0; rx < this.field_76832_z; ++rx) {
                rz = mapX + rand.nextInt(16) + 8;
                ry = mapZ + rand.nextInt(16) + 8;
                ry1 = this.getGroundLevel(world, rz, ry);
                WorldGenAbstractTree worldgenabstracttree = biome.func_150567_a(rand);

                worldgenabstracttree.func_76487_a(1.0D, 1.0D, 1.0D);
                worldgenabstracttree.func_76484_a(world, rand, rz, ry1, ry);
            }

            for (rx = 0; rx < this.field_76804_C; ++rx) {
                rz = mapX + rand.nextInt(16) + 8;
                ry = mapZ + rand.nextInt(16) + 8;
                ry1 = rand.nextInt(128);
                this.worldGenDeadBush.func_76484_a(world, rand, rz, ry1, ry);
            }

            for (rx = 0; rx < this.field_76804_C; ++rx) {
                rz = mapX + rand.nextInt(16) + 8;
                ry = mapZ + rand.nextInt(16) + 8;
                ry1 = rand.nextInt(128);
                this.worldGenForestGrass.func_76484_a(world, rand, rz, ry1, ry);
            }

            for (rx = 0; rx < this.field_76798_D; ++rx) {
                if (rand.nextInt(8) == 0) {
                    rz = mapX + rand.nextInt(16) + 8;
                    ry = mapZ + rand.nextInt(16) + 8;
                    ry1 = this.getGroundLevel(world, rz, ry);
                    this.field_76828_s.func_76484_a(world, rand, rz, ry1, ry);
                }

                if (rand.nextInt(16) == 0) {
                    rz = mapX + rand.nextInt(16) + 8;
                    ry = mapZ + rand.nextInt(16) + 8;
                    ry1 = this.getGroundLevel(world, rz, ry);
                    this.field_76827_t.func_76484_a(world, rand, rz, ry1, ry);
                }

                if (rand.nextInt(24) == 0) {
                    rz = mapX + rand.nextInt(16) + 8;
                    ry = mapZ + rand.nextInt(16) + 8;
                    ry1 = this.getGroundLevel(world, rz, ry);
                    this.worldGenMushgloom.func_76484_a(world, rand, rz, ry1, ry);
                }
            }

            if (rand.nextInt(4) == 0) {
                rx = mapX + rand.nextInt(16) + 8;
                rz = mapZ + rand.nextInt(16) + 8;
                ry = rand.nextInt(128);
                this.field_76828_s.func_76484_a(world, rand, rx, ry, rz);
            }

            if (rand.nextInt(8) == 0) {
                rx = mapX + rand.nextInt(16) + 8;
                rz = mapZ + rand.nextInt(16) + 8;
                ry = rand.nextInt(128);
                this.field_76827_t.func_76484_a(world, rand, rx, ry, rz);
            }

            if (rand.nextInt(32) == 0) {
                rx = mapX + rand.nextInt(16) + 8;
                rz = mapZ + rand.nextInt(16) + 8;
                ry = this.getGroundLevel(world, rx, rz);
                (new WorldGenPumpkin()).func_76484_a(world, rand, rx, ry, rz);
            }
        }

        this.decorateUnderground(world, rand, mapX, mapZ);
        this.decorateOnlyOres(world, rand, mapX, mapZ);
    }

    public int getGroundLevel(World world, int x, int z) {
        Chunk chunk = world.func_72938_d(x, z);
        int lastDirt = TFWorld.SEALEVEL;

        for (int y = TFWorld.SEALEVEL; y < TFWorld.CHUNKHEIGHT - 1; ++y) {
            Block blockID = chunk.func_150810_a(x & 15, y, z & 15);

            if (blockID == Blocks.field_150349_c) {
                return y + 1;
            }

            if (blockID == Blocks.field_150346_d || blockID == Blocks.field_150348_b || blockID == Blocks.field_150351_n) {
                lastDirt = y + 1;
            }
        }

        return lastDirt;
    }
}
