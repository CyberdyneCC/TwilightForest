package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenCaves;
import twilightforest.biomes.TFBiomeHighlands;
import twilightforest.block.TFBlocks;

public class TFGenCaves extends MapGenCaves {

    protected void generateLargeCaveNode(long caveSeed, int centerX, int centerZ, Block[] blockStorage, double randX, double randY, double randZ, boolean isHighlands) {
        this.generateCaveNode(caveSeed, centerX, centerZ, blockStorage, randX, randY, randZ, 1.0F + this.field_75038_b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D, isHighlands);
    }

    protected void generateCaveNode(long caveSeed, int centerX, int centerZ, Block[] blockStorage, double randX, double randY, double randZ, float caveSize, float randPI, float angleToGenerate, int loopOne, int loopEnd, double yScale, boolean isHighlands) {
        double offsetCenterX = (double) (centerX * 16 + 8);
        double offsetCenterZ = (double) (centerZ * 16 + 8);
        float f = 0.0F;
        float f1 = 0.0F;
        Random caveRNG = new Random(caveSeed);
        Random mossRNG = new Random(caveSeed);

        if (isHighlands && caveSize < 6.0F) {
            caveSize *= 2.5F;
        }

        if (loopEnd <= 0) {
            int shouldStop = this.field_75040_a * 16 - 16;

            loopEnd = shouldStop - caveRNG.nextInt(shouldStop / 4);
        }

        boolean flag = false;

        if (loopOne == -1) {
            loopOne = loopEnd / 2;
            flag = true;
        }

        int i = caveRNG.nextInt(loopEnd / 2) + loopEnd / 4;

        for (boolean flag1 = caveRNG.nextInt(6) == 0; loopOne < loopEnd; ++loopOne) {
            double sizeVar = 1.5D + (double) (MathHelper.func_76126_a((float) loopOne * 3.1415927F / (float) loopEnd) * caveSize * 1.0F);
            double scaledSize = sizeVar * yScale;
            float cosAngle = MathHelper.func_76134_b(angleToGenerate);
            float sinAngle = MathHelper.func_76126_a(angleToGenerate);

            randX += (double) (MathHelper.func_76134_b(randPI) * cosAngle);
            randY += (double) sinAngle;
            randZ += (double) (MathHelper.func_76126_a(randPI) * cosAngle);
            if (flag1) {
                angleToGenerate *= 0.92F;
            } else {
                angleToGenerate *= 0.7F;
            }

            angleToGenerate += f1 * 0.1F;
            randPI += f * 0.1F;
            f1 *= 0.9F;
            f *= 0.75F;
            f1 += (caveRNG.nextFloat() - caveRNG.nextFloat()) * caveRNG.nextFloat() * 2.0F;
            f += (caveRNG.nextFloat() - caveRNG.nextFloat()) * caveRNG.nextFloat() * 4.0F;
            if (!flag && loopOne == i && caveSize > 1.0F && loopEnd > 0) {
                this.generateCaveNode(caveRNG.nextLong(), centerX, centerZ, blockStorage, randX, randY, randZ, caveRNG.nextFloat() * 0.5F + 0.5F, randPI - 1.5707964F, angleToGenerate / 3.0F, loopOne, loopEnd, 1.0D, isHighlands);
                this.generateCaveNode(caveRNG.nextLong(), centerX, centerZ, blockStorage, randX, randY, randZ, caveRNG.nextFloat() * 0.5F + 0.5F, randPI + 1.5707964F, angleToGenerate / 3.0F, loopOne, loopEnd, 1.0D, isHighlands);
                return;
            }

            if (flag || caveRNG.nextInt(4) != 0) {
                double distX = randX - offsetCenterX;
                double distZ = randZ - offsetCenterZ;
                double d0 = (double) (loopEnd - loopOne);
                double sizeSixteen = (double) (caveSize + 2.0F + 16.0F);

                if (distX * distX + distZ * distZ - d0 * d0 > sizeSixteen * sizeSixteen) {
                    return;
                }

                if (randX >= offsetCenterX - 16.0D - sizeVar * 2.0D && randZ >= offsetCenterZ - 16.0D - sizeVar * 2.0D && randX <= offsetCenterX + 16.0D + sizeVar * 2.0D && randZ <= offsetCenterZ + 16.0D + sizeVar * 2.0D) {
                    int minX = MathHelper.func_76128_c(randX - sizeVar) - centerX * 16 - 1;
                    int maxX = MathHelper.func_76128_c(randX + sizeVar) - centerX * 16 + 1;
                    int maxY = MathHelper.func_76128_c(randY - scaledSize) - 1;
                    int minY = MathHelper.func_76128_c(randY + scaledSize) + 1;
                    int minZ = MathHelper.func_76128_c(randZ - sizeVar) - centerZ * 16 - 1;
                    int maxZ = MathHelper.func_76128_c(randZ + sizeVar) - centerZ * 16 + 1;

                    if (minX < 0) {
                        minX = 0;
                    }

                    if (maxX > 16) {
                        maxX = 16;
                    }

                    if (maxY < 1) {
                        maxY = 1;
                    }

                    if (minY > 120) {
                        minY = 120;
                    }

                    if (minZ < 0) {
                        minZ = 0;
                    }

                    if (maxZ > 16) {
                        maxZ = 16;
                    }

                    boolean hasHitWater = false;

                    int genX;
                    int genZ;

                    for (genX = minX; !hasHitWater && genX < maxX; ++genX) {
                        for (genZ = minZ; !hasHitWater && genZ < maxZ; ++genZ) {
                            for (int j = minY + 1; !hasHitWater && j >= maxY - 1; --j) {
                                int waterIndex = (genX * 16 + genZ) * 128 + j;

                                if (j >= 0 && j < 128) {
                                    if (this.isOceanBlock(blockStorage, waterIndex, genX, j, genZ, centerX, centerZ)) {
                                        hasHitWater = true;
                                    }

                                    if (j != maxY - 1 && genX != minX && genX != maxX - 1 && genZ != minZ && genZ != maxZ - 1) {
                                        j = maxY;
                                    }
                                }
                            }
                        }
                    }

                    if (!hasHitWater) {
                        for (genX = minX; genX < maxX; ++genX) {
                            double d1 = ((double) (genX + centerX * 16) + 0.5D - randX) / sizeVar;

                            for (genZ = minZ; genZ < maxZ; ++genZ) {
                                double d2 = ((double) (genZ + centerZ * 16) + 0.5D - randZ) / sizeVar;
                                int caveIndex = (genX * 16 + genZ) * TFWorld.CHUNKHEIGHT + minY;
                                boolean hitGrass = false;

                                if (d1 * d1 + d2 * d2 < 1.0D) {
                                    for (int caveY = minY - 1; caveY >= maxY; --caveY) {
                                        double d3 = ((double) caveY + 0.5D - randY) / scaledSize;

                                        if (d3 > -0.7D && d1 * d1 + d3 * d3 + d2 * d2 < 20.0D) {
                                            Block blockAt = blockStorage[caveIndex];

                                            if (blockAt == Blocks.field_150349_c) {
                                                hitGrass = true;
                                            }

                                            if (blockAt != null && (blockAt == Blocks.field_150348_b || blockAt == TFBlocks.trollSteinn || blockAt.func_149688_o() == Material.field_151578_c || blockAt.func_149688_o() == Material.field_151577_b)) {
                                                if (d1 * d1 + d3 * d3 + d2 * d2 < 0.85D) {
                                                    blockStorage[caveIndex] = caveY < 10 ? Blocks.field_150355_j : Blocks.field_150350_a;
                                                } else {
                                                    Block localBlock = isHighlands ? (mossRNG.nextInt(6) == 0 ? TFBlocks.trollSteinn : Blocks.field_150348_b) : Blocks.field_150346_d;

                                                    blockStorage[caveIndex] = (Block) (hitGrass ? Blocks.field_150349_c : localBlock);
                                                    hitGrass = false;
                                                }

                                                if (hitGrass && blockStorage[caveIndex - 1] == Blocks.field_150346_d) {
                                                    blockStorage[caveIndex - 1] = this.field_75039_c.func_72807_a(genX + centerX * 16, genZ + centerZ * 16).field_76752_A;
                                                }
                                            }
                                        }

                                        --caveIndex;
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

    protected void func_151538_a(World par1World, int genX, int genZ, int centerX, int centerZ, Block[] blockStorage) {
        int numberOfCaves = this.field_75038_b.nextInt(this.field_75038_b.nextInt(this.field_75038_b.nextInt(40) + 1) + 1);
        boolean isHighlands = par1World.func_72807_a(genX * 16, genZ * 16) instanceof TFBiomeHighlands;

        if (this.field_75038_b.nextInt(15) != 0) {
            numberOfCaves = 0;
        }

        for (int i = 0; i < numberOfCaves; ++i) {
            double randX = (double) (genX * 16 + this.field_75038_b.nextInt(16));
            double randY = (double) this.field_75038_b.nextInt(this.field_75038_b.nextInt(120) + 8);
            double randZ = (double) (genZ * 16 + this.field_75038_b.nextInt(16));
            int numberOfNormalNodes = 1;

            if (this.field_75038_b.nextInt(4) == 0) {
                this.generateLargeCaveNode(this.field_75038_b.nextLong(), centerX, centerZ, blockStorage, randX, randY, randZ, isHighlands);
                numberOfNormalNodes += this.field_75038_b.nextInt(4);
            }

            for (int j = 0; j < numberOfNormalNodes; ++j) {
                float randPi = this.field_75038_b.nextFloat() * 3.1415927F * 2.0F;
                float randEight = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float caveSize = this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat();

                if (this.field_75038_b.nextInt(10) == 0) {
                    caveSize *= this.field_75038_b.nextFloat() * this.field_75038_b.nextFloat() * 3.0F + 1.0F;
                }

                this.generateCaveNode(this.field_75038_b.nextLong(), centerX, centerZ, blockStorage, randX, randY, randZ, caveSize, randPi, randEight, 0, 0, 1.0D, isHighlands);
            }
        }

    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return data[index] == Blocks.field_150358_i || data[index] == Blocks.field_150355_j;
    }
}
