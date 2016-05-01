package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenHangingLamps;
import twilightforest.world.TFGenLampposts;
import twilightforest.world.TFWorld;

public class TFBiomeFireflyForest extends TFBiomeTwilightForest {

    private static final int LAMPPOST_CHANCE = 4;
    TFGenHangingLamps tfGenHangingLamps;
    TFGenLampposts tfGenLampposts;
    WorldGenTallGrass worldGenMushgloom;

    public TFBiomeFireflyForest(int i) {
        super(i);
        this.worldGenMushgloom = new WorldGenTallGrass(TFBlocks.plant, 9);
        this.tfGenHangingLamps = new TFGenHangingLamps();
        this.tfGenLampposts = new TFGenLampposts();
        this.field_76750_F = 0.5F;
        this.field_76751_G = 1.0F;
        this.field_76760_I.field_76802_A = 4;
        this.field_76760_I.field_76803_B = 1;
        this.getTFBiomeDecorator().setTreesPerChunk(2);
    }

    public void func_76728_a(World world, Random rand, int mapX, int mapZ) {
        int flowerCycles = rand.nextInt(3) - 1;
        int successfulFlowers = 0;

        int rx;
        int rz;
        int ry;
        int ry1;

        while (successfulFlowers < flowerCycles) {
            rx = rand.nextInt(3);
            if (rx == 0) {
                TFBiomeFireflyForest.field_150610_ae.func_150548_a(1);
            } else if (rx == 1) {
                TFBiomeFireflyForest.field_150610_ae.func_150548_a(4);
            } else if (rx == 2) {
                TFBiomeFireflyForest.field_150610_ae.func_150548_a(5);
            }

            rz = 0;

            while (true) {
                if (rz < 1) {
                    ry = mapX + rand.nextInt(16) + 8;
                    ry1 = mapZ + rand.nextInt(16) + 8;
                    int l1 = rand.nextInt(world.func_72976_f(ry, ry1) + 32);

                    if (!TFBiomeFireflyForest.field_150610_ae.func_76484_a(world, rand, ry, l1, ry1)) {
                        ++rz;
                        continue;
                    }
                }

                ++successfulFlowers;
                break;
            }
        }

        super.func_76728_a(world, rand, mapX, mapZ);
        if (rand.nextInt(24) == 0) {
            rx = mapX + rand.nextInt(16) + 8;
            rz = mapZ + rand.nextInt(16) + 8;
            ry = this.getGroundLevel(world, rx, rz);
            this.worldGenMushgloom.func_76484_a(world, rand, rx, ry, rz);
        }

        for (rx = 0; rx < 30; ++rx) {
            rz = mapX + rand.nextInt(16) + 8;
            ry = mapZ + rand.nextInt(16) + 8;
            ry1 = TFWorld.SEALEVEL + rand.nextInt(TFWorld.CHUNKHEIGHT - TFWorld.SEALEVEL);
            this.tfGenHangingLamps.func_76484_a(world, rand, rz, ry1, ry);
        }

        if (rand.nextInt(4) == 0) {
            rx = mapX + rand.nextInt(16) + 8;
            rz = mapZ + rand.nextInt(16) + 8;
            ry = this.getGroundLevel(world, rx, rz);
            this.tfGenLampposts.func_76484_a(world, rand, rx, ry, rz);
        }

        if (rand.nextInt(32) == 0) {
            rx = mapX + rand.nextInt(16) + 8;
            rz = mapZ + rand.nextInt(16) + 8;
            ry = this.getGroundLevel(world, rx, rz);
            (new WorldGenPumpkin()).func_76484_a(world, rand, rx, ry, rz);
        }

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

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_) {
        double flowerVar = MathHelper.func_151237_a((1.0D + TFBiomeFireflyForest.field_150606_ad.func_151601_a((double) p_150572_2_ / 48.0D, (double) p_150572_4_ / 48.0D)) / 2.0D, 0.0D, 0.9999D);
        int flowerIndex = (int) (flowerVar * (double) BlockFlower.field_149859_a.length);

        if (flowerIndex == 1) {
            flowerIndex = 0;
        }

        return BlockFlower.field_149859_a[flowerIndex];
    }
}
