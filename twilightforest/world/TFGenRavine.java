package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;

public class TFGenRavine extends MapGenBase {

    private float[] field_35627_a = new float[1024];

    protected void generateRavine(long l, int i, int j, Block[] blockStorage, double d, double d1, double d2, float f, float f1, float f2, int k, int i1, double d3) {
        Random random = new Random(l);
        double d4 = (double) (i * 16 + 8);
        double d5 = (double) (j * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;

        if (i1 <= 0) {
            int flag = this.field_75040_a * 16 - 16;

            i1 = flag - random.nextInt(flag / 4);
        }

        boolean flag = false;

        if (k == -1) {
            k = i1 / 2;
            flag = true;
        }

        float f5 = 1.0F;

        for (int d6 = 0; d6 < TFWorld.CHUNKHEIGHT; ++d6) {
            if (d6 == 0 || random.nextInt(3) == 0) {
                f5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
            }

            this.field_35627_a[d6] = f5 * f5;
        }

        for (; k < i1; ++k) {
            double d0 = 1.5D + (double) (MathHelper.func_76126_a((float) k * 3.141593F / (float) i1) * f * 1.0F);
            double d7 = d0 * d3;

            d0 *= (double) random.nextFloat() * 0.25D + 0.75D;
            d7 *= (double) random.nextFloat() * 0.25D + 0.75D;
            float f6 = MathHelper.func_76134_b(f2);
            float f7 = MathHelper.func_76126_a(f2);

            d += (double) (MathHelper.func_76134_b(f1) * f6);
            d1 += (double) f7;
            d2 += (double) (MathHelper.func_76126_a(f1) * f6);
            f2 *= 0.7F;
            f2 += f4 * 0.05F;
            f1 += f3 * 0.05F;
            f4 *= 0.8F;
            f3 *= 0.5F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (flag || random.nextInt(4) != 0) {
                double d8a = d - d4;
                double d9a = d2 - d5;
                double d10a = (double) (i1 - k);
                double d11 = (double) (f + 2.0F + 16.0F);

                if (d8a * d8a + d9a * d9a - d10a * d10a > d11 * d11) {
                    return;
                }

                if (d >= d4 - 16.0D - d0 * 2.0D && d2 >= d5 - 16.0D - d0 * 2.0D && d <= d4 + 16.0D + d0 * 2.0D && d2 <= d5 + 16.0D + d0 * 2.0D) {
                    int d8 = MathHelper.func_76128_c(d - d0) - i * 16 - 1;
                    int l1 = MathHelper.func_76128_c(d + d0) - i * 16 + 1;
                    int d9 = MathHelper.func_76128_c(d1 - d7) - 1;
                    int i2 = MathHelper.func_76128_c(d1 + d7) + 1;
                    int d10 = MathHelper.func_76128_c(d2 - d0) - j * 16 - 1;
                    int j2 = MathHelper.func_76128_c(d2 + d0) - j * 16 + 1;

                    if (d8 < 0) {
                        d8 = 0;
                    }

                    if (l1 > 16) {
                        l1 = 16;
                    }

                    if (d9 < 1) {
                        d9 = 1;
                    }

                    if (i2 > TFWorld.CHUNKHEIGHT - 8) {
                        i2 = TFWorld.CHUNKHEIGHT - 8;
                    }

                    if (d10 < 0) {
                        d10 = 0;
                    }

                    if (j2 > 16) {
                        j2 = 16;
                    }

                    boolean flag1 = false;

                    int l2;
                    int l3;

                    for (l2 = d8; !flag1 && l2 < l1; ++l2) {
                        for (int d12 = d10; !flag1 && d12 < j2; ++d12) {
                            for (int j3 = i2 + 1; !flag1 && j3 >= d9 - 1; --j3) {
                                l3 = (l2 * 16 + d12) * TFWorld.CHUNKHEIGHT + j3;
                                if (j3 >= 0 && j3 < TFWorld.CHUNKHEIGHT) {
                                    if (blockStorage[l3] == Blocks.field_150355_j || blockStorage[l3] == Blocks.field_150355_j) {
                                        flag1 = true;
                                    }

                                    if (j3 != d9 - 1 && l2 != d8 && l2 != l1 - 1 && d12 != d10 && d12 != j2 - 1) {
                                        j3 = d9;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag1) {
                        for (l2 = d8; l2 < l1; ++l2) {
                            double d1 = ((double) (l2 + i * 16) + 0.5D - d) / d0;

                            for (l3 = d10; l3 < j2; ++l3) {
                                double d13 = ((double) (l3 + j * 16) + 0.5D - d2) / d0;
                                int i4 = (l2 * 16 + l3) * TFWorld.CHUNKHEIGHT + i2;
                                boolean flag2 = false;

                                if (d1 * d1 + d13 * d13 < 1.0D) {
                                    for (int j4 = i2 - 1; j4 >= d9; --j4) {
                                        double d14 = ((double) j4 + 0.5D - d1) / d7;

                                        if ((d1 * d1 + d13 * d13) * (double) this.field_35627_a[j4] + d14 * d14 / 6.0D < 1.0D) {
                                            Block curentBlock = blockStorage[i4];

                                            if (curentBlock == Blocks.field_150349_c) {
                                                flag2 = true;
                                            }

                                            if (curentBlock == Blocks.field_150348_b || curentBlock == TFBlocks.trollSteinn || curentBlock == Blocks.field_150346_d || curentBlock == Blocks.field_150349_c) {
                                                blockStorage[i4] = Blocks.field_150350_a;
                                                if (flag2 && blockStorage[i4 - 1] == Blocks.field_150346_d) {
                                                    blockStorage[i4 - 1] = this.field_75039_c.func_72807_a(l2 + i * 16, l3 + j * 16).field_76752_A;
                                                }
                                            }
                                        }

                                        --i4;
                                    }
                                }
                            }
                        }

                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }

    }

    protected void func_151538_a(World world, int centerChunkX, int centerChunkZ, int currentChunkX, int currentChunkZ, Block[] shortStorage) {
        if (this.field_75038_b.nextInt(127) == 0) {
            if (TFFeature.getNearestFeature(currentChunkX, currentChunkZ, world).areChunkDecorationsEnabled) {
                double d = (double) (centerChunkX * 16 + this.field_75038_b.nextInt(16));
                double d1 = (double) (this.field_75038_b.nextInt(this.field_75038_b.nextInt(40) + 8) + 20);
                double d2 = (double) (centerChunkZ * 16 + this.field_75038_b.nextInt(16));
                byte i1 = 1;

                for (int j1 = 0; j1 < i1; ++j1) {
                    float f = this.field_75038_b.nextFloat() * 3.141593F * 2.0F;
                    float f1 = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
                    float f2 = (this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat()) * 2.0F;

                    this.generateRavine(this.field_75038_b.nextLong(), currentChunkX, currentChunkZ, shortStorage, d, d1, d2, f2, f, f1, 0, 0, 3.0D);
                }

            }
        }
    }
}
