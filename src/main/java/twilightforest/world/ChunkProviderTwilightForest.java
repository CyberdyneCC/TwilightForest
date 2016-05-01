package twilightforest.world;

import cpw.mods.fml.common.eventhandler.Event.Result;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;
import twilightforest.TFFeature;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;

public class ChunkProviderTwilightForest implements IChunkProvider {

    private Random rand;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private double[] stoneNoise = new double[256];
    private TFGenCaves caveGenerator = new TFGenCaves();
    private TFGenRavine ravineGenerator = new TFGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] squareTable;
    int[][] unusedIntArray32x32 = new int[32][32];
    private WorldType field_147435_p;
    private NoiseGeneratorOctaves field_147431_j;
    private NoiseGeneratorOctaves field_147432_k;
    private NoiseGeneratorOctaves field_147429_l;
    private NoiseGeneratorPerlin field_147430_m;
    private final double[] terrainCalcs;
    private final float[] parabolicField;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];
    private MapGenTFMajorFeature majorFeatureGenerator = new MapGenTFMajorFeature();
    private MapGenTFHollowTree hollowTreeGenerator = new MapGenTFHollowTree();

    public ChunkProviderTwilightForest(World world, long l, boolean flag) {
        this.worldObj = world;
        this.rand = new Random(l);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147435_p = world.func_72912_H().func_76067_t();
        this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
        this.terrainCalcs = new double[825];
        this.parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0F / MathHelper.func_76129_c((float) (j * j + k * k) + 0.2F);

                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

    }

    public Chunk func_73154_d(int cx, int cz) {
        this.rand.setSeed((long) cx * 341873128712L + (long) cz * 132897987541L);
        Block[] blockStorage = new Block[256 * TFWorld.CHUNKHEIGHT];
        byte[] metaStorage = new byte[256 * TFWorld.CHUNKHEIGHT];

        this.generateTerrain2(cx, cz, blockStorage);
        this.squishTerrain(blockStorage);
        this.addDarkForestCanopy2(cx, cz, blockStorage, metaStorage);
        this.biomesForGeneration = this.worldObj.func_72959_q().func_76933_b(this.biomesForGeneration, cx * 16, cz * 16, 16, 16);
        this.addGlaciers(cx, cz, blockStorage, metaStorage, this.biomesForGeneration);
        this.deformTerrainForFeature(cx, cz, blockStorage, metaStorage);
        this.replaceBlocksForBiome(cx, cz, blockStorage, metaStorage, this.biomesForGeneration);
        this.caveGenerator.func_151539_a(this, this.worldObj, cx, cz, blockStorage);
        this.ravineGenerator.func_151539_a(this, this.worldObj, cx, cz, blockStorage);
        Block[] fake = new Block[0];

        this.majorFeatureGenerator.func_151539_a(this, this.worldObj, cx, cz, fake);
        this.hollowTreeGenerator.func_151539_a(this, this.worldObj, cx, cz, fake);
        Chunk chunk = new Chunk(this.worldObj, blockStorage, metaStorage, cx, cz);
        byte[] chunkBiomes = chunk.func_76605_m();

        for (int i = 0; i < chunkBiomes.length; ++i) {
            chunkBiomes[i] = (byte) this.biomesForGeneration[i].field_76756_M;
        }

        chunk.func_76603_b();
        return chunk;
    }

    public void generateTerrain2(int chunkX, int chunkZ, Block[] blockStorage) {
        byte seaLevel = 63;

        this.biomesForGeneration = this.worldObj.func_72959_q().func_76937_a(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.makeLandPerBiome2(chunkX * 4, 0, chunkZ * 4);

        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125D;
                    double d1 = this.terrainCalcs[k1 + k2];
                    double d2 = this.terrainCalcs[l1 + k2];
                    double d3 = this.terrainCalcs[i2 + k2];
                    double d4 = this.terrainCalcs[j2 + k2];
                    double d5 = (this.terrainCalcs[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.terrainCalcs[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.terrainCalcs[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.terrainCalcs[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;

                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int k3 = 0; k3 < 4; ++k3) {
                                if ((d15 += d16) > 0.0D) {
                                    blockStorage[j3 += short1] = Blocks.field_150348_b;
                                } else if (k2 * 8 + l2 < seaLevel) {
                                    blockStorage[j3 += short1] = Blocks.field_150355_j;
                                } else {
                                    blockStorage[j3 += short1] = null;
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }

    }

    private void makeLandPerBiome2(int x, int zero, int z) {
        this.field_147426_g = this.noiseGen6.func_76305_a(this.field_147426_g, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.field_147429_l.func_76304_a(this.field_147427_d, x, zero, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.field_147431_j.func_76304_a(this.field_147428_e, x, zero, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.field_147432_k.func_76304_a(this.field_147425_f, x, zero, z, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int terrainIndex = 0;
        int noiseIndex = 0;

        for (int ax = 0; ax < 5; ++ax) {
            for (int az = 0; az < 5; ++az) {
                float totalVariation = 0.0F;
                float totalHeight = 0.0F;
                float totalFactor = 0.0F;
                byte two = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[ax + 2 + (az + 2) * 10];

                for (int terrainNoise = -two; terrainNoise <= two; ++terrainNoise) {
                    for (int oz = -two; oz <= two; ++oz) {
                        BiomeGenBase heightCalc = this.biomesForGeneration[ax + terrainNoise + 2 + (az + oz + 2) * 10];
                        float rootHeight = heightCalc.field_76748_D;
                        float f = heightCalc.field_76749_E;

                        if (this.field_147435_p == WorldType.field_151360_e && rootHeight > 0.0F) {
                            rootHeight = 1.0F + rootHeight * 2.0F;
                            f = 1.0F + f * 4.0F;
                        }

                        float heightFactor = this.parabolicField[terrainNoise + 2 + (oz + 2) * 5] / (rootHeight + 2.0F);

                        if (heightCalc.field_76748_D > biomegenbase.field_76748_D) {
                            heightFactor /= 2.0F;
                        }

                        totalVariation += f * heightFactor;
                        totalHeight += rootHeight * heightFactor;
                        totalFactor += heightFactor;
                    }
                }

                totalVariation /= totalFactor;
                totalHeight /= totalFactor;
                totalVariation = totalVariation * 0.9F + 0.1F;
                totalHeight = (totalHeight * 4.0F - 1.0F) / 8.0F;
                double d0 = this.field_147426_g[noiseIndex] / 8000.0D;

                if (d0 < 0.0D) {
                    d0 = -d0 * 0.3D;
                }

                d0 = d0 * 3.0D - 2.0D;
                if (d0 < 0.0D) {
                    d0 /= 2.0D;
                    if (d0 < -1.0D) {
                        d0 = -1.0D;
                    }

                    d0 /= 1.4D;
                    d0 /= 2.0D;
                } else {
                    if (d0 > 1.0D) {
                        d0 = 1.0D;
                    }

                    d0 /= 8.0D;
                }

                ++noiseIndex;
                double d1 = (double) totalHeight;
                double d2 = (double) totalVariation;

                d1 += d0 * 0.2D;
                d1 = d1 * 8.5D / 8.0D;
                double d5 = 8.5D + d1 * 4.0D;

                for (int ay = 0; ay < 33; ++ay) {
                    double d6 = ((double) ay - d5) * 12.0D * 128.0D / 256.0D / d2;

                    if (d6 < 0.0D) {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[terrainIndex] / 512.0D;
                    double d8 = this.field_147425_f[terrainIndex] / 512.0D;
                    double d9 = (this.field_147427_d[terrainIndex] / 10.0D + 1.0D) / 2.0D;
                    double terrainCalc = MathHelper.func_151238_b(d7, d8, d9) - d6;

                    if (ay > 29) {
                        double d11 = (double) ((float) (ay - 29) / 3.0F);

                        terrainCalc = terrainCalc * (1.0D - d11) + -10.0D * d11;
                    }

                    this.terrainCalcs[terrainIndex] = terrainCalc;
                    ++terrainIndex;
                }
            }
        }

    }

    private void squishTerrain(Block[] blockStorage) {
        int squishHeight = TFWorld.MAXHEIGHT / 2;

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < TFWorld.CHUNKHEIGHT; ++y) {
                    int index = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | y;

                    if (y < squishHeight) {
                        int twiceIndex = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | y * 2 + 1;

                        blockStorage[index] = blockStorage[twiceIndex];
                    } else {
                        blockStorage[index] = Blocks.field_150350_a;
                    }
                }
            }
        }

    }

    public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blockStorage, byte[] metaStorage, BiomeGenBase[] biomes) {
        ReplaceBiomeBlocks event = new ReplaceBiomeBlocks(this, chunkX, chunkZ, blockStorage, biomes);

        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() != Result.DENY) {
            double d0 = 0.03125D;

            this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, (double) (chunkX * 16), (double) (chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    BiomeGenBase biomegenbase = biomes[x + z * 16];

                    biomegenbase.func_150573_a(this.worldObj, this.rand, blockStorage, metaStorage, chunkX * 16 + z, chunkZ * 16 + x, this.stoneNoise[x + z * 16]);
                }
            }

        }
    }

    public Chunk func_73158_c(int i, int j) {
        return this.func_73154_d(i, j);
    }

    public void deformTerrainForFeature(int cx, int cz, Block[] blockStorage, byte[] metaStorage) {
        TFFeature nearFeature = TFFeature.getNearestFeature(cx, cz, this.worldObj);

        if (nearFeature.isTerrainAltered) {
            int[] nearCenter = TFFeature.getNearestCenter(cx, cz, this.worldObj);
            int hx = nearCenter[0];
            int hz = nearCenter[1];

            if (nearFeature == TFFeature.trollCave) {
                this.deformTerrainForTrollCloud2(blockStorage, metaStorage, nearFeature, cx, cz, hx, hz);
            }

            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    int dx = x - hx;
                    int dz = z - hz;

                    if (nearFeature != TFFeature.hill1 && nearFeature != TFFeature.hill2 && nearFeature != TFFeature.hill3 && nearFeature != TFFeature.hydraLair) {
                        if (nearFeature != TFFeature.hedgeMaze && nearFeature != TFFeature.nagaCourtyard && nearFeature != TFFeature.questGrove) {
                            if (nearFeature == TFFeature.yetiCave) {
                                this.deformTerrainForYetiLair(blockStorage, nearFeature, x, z, dx, dz);
                            } else if (nearFeature == TFFeature.trollCave) {
                                ;
                            }
                        } else {
                            this.flattenTerrainForFeature(blockStorage, nearFeature, x, z, dx, dz);
                        }
                    } else {
                        int hdiam = (nearFeature.size * 2 + 1) * 16;
                        int dist = (int) Math.sqrt((double) (dx * dx + dz * dz));
                        int hheight = (int) (Math.cos((double) ((float) dist / (float) hdiam) * 3.141592653589793D) * (double) ((float) hdiam / 3.0F));

                        this.raiseHills(blockStorage, nearFeature, hdiam, x, z, dx, dz, hheight);
                    }
                }
            }

        }
    }

    private void raiseHills(Block[] storage, TFFeature nearFeature, int hdiam, int x, int z, int dx, int dz, int hillHeight) {
        int newGround = -1;
        boolean foundGroundLevel = false;

        int hollow;
        int y;

        for (hollow = 0; hollow < TFWorld.CHUNKHEIGHT; ++hollow) {
            y = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | hollow;
            Block index = storage[y];

            if (index != Blocks.field_150348_b && !foundGroundLevel) {
                newGround = hollow + hillHeight;
                foundGroundLevel = true;
            }

            if (foundGroundLevel && hollow <= newGround) {
                storage[y] = Blocks.field_150348_b;
            }
        }

        hollow = hillHeight - 4 - nearFeature.size;
        int hollowFloor;
        int i;

        if (nearFeature == TFFeature.hydraLair) {
            y = dx + 16;
            i = dz + 16;
            hollowFloor = (int) Math.sqrt((double) (y * y + i * i));
            int mheight = (int) (Math.cos((double) hollowFloor / ((double) hdiam / 1.5D) * 3.141592653589793D) * ((double) hdiam / 1.5D));

            hollow = Math.max(mheight - 4, hollow);
        }

        if (hollow < 0) {
            hollow = 0;
        }

        for (y = 0; y < TFWorld.CHUNKHEIGHT; ++y) {
            i = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | y;
            if (hillHeight > 0 && y < TFWorld.SEALEVEL && storage[i] != Blocks.field_150348_b) {
                storage[i] = Blocks.field_150348_b;
            }

            hollowFloor = TFWorld.SEALEVEL - 3 - hollow / 8;
            if (nearFeature == TFFeature.hydraLair) {
                hollowFloor = TFWorld.SEALEVEL;
            }

            if (y > hollowFloor && y < hollowFloor + hollow) {
                storage[i] = Blocks.field_150350_a;
            }
        }

    }

    private void flattenTerrainForFeature(Block[] storage, TFFeature nearFeature, int x, int z, int dx, int dz) {
        float squishfactor = 0.0F;
        int mazeheight = TFWorld.SEALEVEL + 1;
        int FEATUREBOUNDRY = (nearFeature.size * 2 + 1) * 8 - 8;

        if (dx <= -FEATUREBOUNDRY) {
            squishfactor = (float) (-dx - FEATUREBOUNDRY) / 8.0F;
        }

        if (dx >= FEATUREBOUNDRY) {
            squishfactor = (float) (dx - FEATUREBOUNDRY) / 8.0F;
        }

        if (dz <= -FEATUREBOUNDRY) {
            squishfactor = Math.max(squishfactor, (float) (-dz - FEATUREBOUNDRY) / 8.0F);
        }

        if (dz >= FEATUREBOUNDRY) {
            squishfactor = Math.max(squishfactor, (float) (dz - FEATUREBOUNDRY) / 8.0F);
        }

        int y;
        int index;

        if (squishfactor > 0.0F) {
            int newGround = -1;

            for (y = 0; y <= 127; ++y) {
                index = (x * 16 + z) * TFWorld.CHUNKHEIGHT + y;
                Block currentTerrain = storage[index];

                if (currentTerrain != Blocks.field_150348_b && newGround == -1) {
                    mazeheight = (int) ((float) mazeheight + (float) (y - mazeheight) * squishfactor);
                    newGround = y;
                }
            }
        }

        for (y = 0; y <= 127; ++y) {
            index = (x * 16 + z) * TFWorld.CHUNKHEIGHT + y;
            if (y < mazeheight && (storage[index] == Blocks.field_150350_a || storage[index] == Blocks.field_150355_j)) {
                storage[index] = Blocks.field_150348_b;
            }

            if (y >= mazeheight && storage[index] != Blocks.field_150355_j) {
                storage[index] = Blocks.field_150350_a;
            }
        }

    }

    private void deformTerrainForYetiLair(Block[] storage, TFFeature nearFeature, int x, int z, int dx, int dz) {
        float squishfactor = 0.0F;
        int topHeight = TFWorld.SEALEVEL + 24;
        int outerBoundry = (nearFeature.size * 2 + 1) * 8 - 8;

        if (dx <= -outerBoundry) {
            squishfactor = (float) (-dx - outerBoundry) / 8.0F;
        }

        if (dx >= outerBoundry) {
            squishfactor = (float) (dx - outerBoundry) / 8.0F;
        }

        if (dz <= -outerBoundry) {
            squishfactor = Math.max(squishfactor, (float) (-dz - outerBoundry) / 8.0F);
        }

        if (dz >= outerBoundry) {
            squishfactor = Math.max(squishfactor, (float) (dz - outerBoundry) / 8.0F);
        }

        int caveBoundry = nearFeature.size * 2 * 8 - 8;
        int hollowCeiling = TFWorld.SEALEVEL + 16;
        int offset = Math.min(Math.abs(dx), Math.abs(dz));

        hollowCeiling = TFWorld.SEALEVEL + 40 - offset * 4;
        if (dx >= -caveBoundry && dz >= -caveBoundry && dx <= caveBoundry && dz <= caveBoundry) {
            hollowCeiling = TFWorld.SEALEVEL + 16;
        }

        hollowCeiling -= offset / 6;
        hollowCeiling = Math.min(hollowCeiling, TFWorld.SEALEVEL + 16);
        int hollowFloor = TFWorld.SEALEVEL - 1 + offset / 6;
        int y;
        int index;

        if (squishfactor > 0.0F) {
            int newGround = -1;

            for (y = 0; y <= 127; ++y) {
                index = (x * 16 + z) * TFWorld.CHUNKHEIGHT + y;
                Block currentTerrain = storage[index];

                if (currentTerrain != Blocks.field_150348_b && newGround == -1) {
                    topHeight = (int) ((float) topHeight + (float) (y - topHeight) * squishfactor);
                    hollowFloor = (int) ((float) hollowFloor + (float) (y - hollowFloor) * squishfactor);
                    newGround = y;
                }
            }
        }

        for (y = 0; y <= 127; ++y) {
            index = (x * 16 + z) * TFWorld.CHUNKHEIGHT + y;
            if (y < topHeight && (storage[index] == null || storage[index] == Blocks.field_150350_a || storage[index] == Blocks.field_150355_j)) {
                storage[index] = Blocks.field_150348_b;
            }

            if (y > hollowFloor && y < hollowCeiling) {
                storage[index] = Blocks.field_150350_a;
            }

            if (y == hollowFloor && y < hollowCeiling && y < TFWorld.SEALEVEL + 3) {
                storage[index] = Blocks.field_150403_cj;
            }
        }

    }

    private void deformTerrainForTrollCloud(Block[] storage, byte[] metaStorage, TFFeature nearFeature, int x, int z, int dx, int dz) {
        short y = 164;
        int index = (x * 16 + z) * TFWorld.CHUNKHEIGHT + y;
        int bx = dx >> 2;
        int bz = dz >> 2;
        double dist = Math.sqrt((double) (bx * bx + bz * bz));
        float pr = this.pseudoRand(x >> 2, z >> 2);

        System.out.println("pr = " + pr + ", dist = " + dist + "dx = " + dx + " dz = " + dz);
        double cv = dist - 9.0D - (double) (pr * 4.0F);

        if (dist >= 9.0D && cv >= 0.05000000074505806D) {
            if (dist < 10.0D || cv < 1.0D) {
                storage[index - 1] = Blocks.field_150399_cn;
                storage[index - 2] = Blocks.field_150399_cn;
                storage[index - 3] = Blocks.field_150399_cn;
            }
        } else {
            storage[index] = Blocks.field_150399_cn;
            storage[index - 1] = Blocks.field_150371_ca;
            storage[index - 2] = Blocks.field_150371_ca;
            storage[index - 3] = Blocks.field_150371_ca;
            storage[index - 4] = Blocks.field_150399_cn;
        }

    }

    private void deformTerrainForTrollCloud2(Block[] storage, byte[] metaStorage, TFFeature nearFeature, int cx, int cz, int hx, int hz) {
        for (int bx = 0; bx < 4; ++bx) {
            for (int bz = 0; bz < 4; ++bz) {
                int dx = bx * 4 - hx - 2;
                int dz = bz * 4 - hz - 2;
                int regionX = cx + 8 >> 4;
                int regionZ = cx + 8 >> 4;
                long seed = (long) (regionX * 3129871) ^ (long) regionZ * 116129781L;

                seed = seed * seed * 42317861L + seed * 7L;
                int num0 = (int) (seed >> 12 & 3L);
                int num1 = (int) (seed >> 15 & 3L);
                int num2 = (int) (seed >> 18 & 3L);
                int num3 = (int) (seed >> 21 & 3L);
                int num4 = (int) (seed >> 9 & 3L);
                int num5 = (int) (seed >> 6 & 3L);
                int num6 = (int) (seed >> 3 & 3L);
                int num7 = (int) (seed >> 0 & 3L);
                int dx2 = dx + num0 * 5 - num1 * 4;
                int dz2 = dz + num2 * 4 - num3 * 5;
                int dx3 = dx + num4 * 5 - num5 * 4;
                int dz3 = dz + num6 * 4 - num7 * 5;
                double dist0 = Math.sqrt((double) (dx * dx + dz * dz)) / 4.0D;
                double dist2 = Math.sqrt((double) (dx2 * dx2 + dz2 * dz2)) / 3.5D;
                double dist3 = Math.sqrt((double) (dx3 * dx3 + dz3 * dz3)) / 4.5D;
                double dist = Math.min(dist0, Math.min(dist2, dist3));
                float pr = this.worldObj.field_73012_v.nextFloat();
                double cv = dist - 7.0D - (double) (pr * 3.0F);
                int y = 166;
                int depth = 4;

                if (pr < 0.1F) {
                    ++y;
                }

                if (pr > 0.6F) {
                    ++depth;
                }

                if (pr > 0.9F) {
                    ++depth;
                }

                for (int sx = 0; sx < 4; ++sx) {
                    for (int sz = 0; sz < 4; ++sz) {
                        int index = ((bx * 4 + sx) * 16 + bz * 4 + sz) * TFWorld.CHUNKHEIGHT + y;
                        int d;

                        if (dist >= 7.0D && cv >= 0.05000000074505806D) {
                            if (dist < 8.0D || cv < 1.0D) {
                                for (d = 1; d < depth; ++d) {
                                    storage[index - d] = TFBlocks.wispyCloud;
                                }
                            }
                        } else {
                            storage[index] = TFBlocks.wispyCloud;

                            for (d = 1; d < depth; ++d) {
                                storage[index - d] = TFBlocks.fluffyCloud;
                            }

                            storage[index - depth] = TFBlocks.wispyCloud;
                        }
                    }
                }
            }
        }

    }

    private float pseudoRand(int bx, int bz) {
        Random rand = new Random(this.worldObj.func_72905_C() + (long) (bx * 321534781) ^ (long) (bz * 756839));

        rand.setSeed(rand.nextLong());
        return rand.nextFloat();
    }

    public void addGlaciers(int chunkX, int chunkZ, Block[] blocks, byte[] meta, BiomeGenBase[] biomes) {
        for (int z = 0; z < 16; ++z) {
            for (int x = 0; x < 16; ++x) {
                BiomeGenBase biome = biomes[x & 15 | (z & 15) << 4];

                if (biome == TFBiomeBase.glacier) {
                    int topLevel = -1;

                    int gTop;

                    for (int gHeight = 127; gHeight >= 0; --gHeight) {
                        gTop = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | gHeight;
                        Block y = blocks[gTop];

                        if (y == Blocks.field_150348_b) {
                            topLevel = gHeight;
                            blocks[gTop] = Blocks.field_150351_n;
                            break;
                        }
                    }

                    byte b0 = 32;

                    gTop = topLevel + b0 + 1;

                    for (int i = topLevel + 1; i <= gTop && i < 128; ++i) {
                        int index = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | i;

                        blocks[index] = Blocks.field_150432_aD;
                    }
                }
            }
        }

    }

    public void addDarkForestCanopy2(int chunkX, int chunkZ, Block[] blocks, byte[] meta) {
        int[] thicks = new int[25];

        int z;
        int x;
        int qx;
        int qz;

        for (z = 0; z < 5; ++z) {
            for (x = 0; x < 5; ++x) {
                for (qx = -1; qx <= 1; ++qx) {
                    for (qz = -1; qz <= 1; ++qz) {
                        BiomeGenBase xweight = this.biomesForGeneration[x + qx + 2 + (z + qz + 2) * 10];

                        if (xweight == TFBiomeBase.darkForest || xweight == TFBiomeBase.darkForestCenter) {
                            ++thicks[x + z * 5];
                        }
                    }
                }
            }
        }

        for (z = 0; z < 16; ++z) {
            for (x = 0; x < 16; ++x) {
                qx = x / 4;
                qz = z / 4;
                float f = (float) (x % 4) * 0.25F + 0.125F;
                float zweight = (float) (z % 4) * 0.25F + 0.125F;
                float thickness = 0.0F;

                thickness += (float) thicks[qx + qz * 5] * (1.0F - f) * (1.0F - zweight);
                thickness += (float) thicks[qx + 1 + qz * 5] * f * (1.0F - zweight);
                thickness += (float) thicks[qx + (qz + 1) * 5] * (1.0F - f) * zweight;
                thickness += (float) thicks[qx + 1 + (qz + 1) * 5] * f * zweight;
                thickness -= 4.0F;
                TFFeature nearFeature = TFFeature.getNearestFeature(chunkX, chunkZ, this.worldObj);
                int topLevel;
                int noise;
                int treeBottom;

                if (nearFeature == TFFeature.darkTower) {
                    int[] generateForest = TFFeature.getNearestCenter(chunkX, chunkZ, this.worldObj);
                    int d = generateForest[0];
                    int hz = generateForest[1];

                    topLevel = x - d;
                    noise = z - hz;
                    treeBottom = (int) Math.sqrt((double) (topLevel * topLevel + noise * noise));
                    if (treeBottom < 24) {
                        thickness -= (float) (24 - treeBottom);
                    }
                }

                boolean flag = thickness > 1.0F;

                if (flag) {
                    double d0 = 0.03125D;

                    this.stoneNoise = this.noiseGen4.func_76304_a(this.stoneNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);
                    topLevel = -1;

                    for (noise = 127; noise >= 0; --noise) {
                        treeBottom = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | noise;
                        Block treeTop = blocks[treeBottom];

                        if (treeTop == Blocks.field_150355_j) {
                            break;
                        }

                        if (treeTop == Blocks.field_150348_b) {
                            topLevel = noise;
                            break;
                        }
                    }

                    if (topLevel != -1) {
                        noise = Math.min(3, (int) (this.stoneNoise[z & 15 | (x & 15) << 4] / 1.25D));
                        treeBottom = topLevel + 12 - (int) (thickness * 0.5F);
                        int i = treeBottom + (int) (thickness * 1.5F);

                        treeBottom -= noise;

                        for (int y = treeBottom; y < i; ++y) {
                            int index = x * TFWorld.CHUNKHEIGHT * 16 | z * TFWorld.CHUNKHEIGHT | y;

                            blocks[index] = TFBlocks.darkleaves;
                            meta[index] = 0;
                        }
                    }
                }
            }
        }

    }

    public boolean func_73149_a(int i, int j) {
        return true;
    }

    public void func_73153_a(IChunkProvider ichunkprovider, int chunkX, int chunkZ) {
        BlockFalling.field_149832_M = true;
        int mapX = chunkX * 16;
        int mapY = chunkZ * 16;
        BiomeGenBase biomeGen = this.worldObj.func_72807_a(mapX + 16, mapY + 16);

        this.rand.setSeed(this.worldObj.func_72905_C());
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        long l2 = this.rand.nextLong() / 2L * 2L + 1L;

        this.rand.setSeed((long) chunkX * l1 + (long) chunkZ * l2 ^ this.worldObj.func_72905_C());
        boolean disableFeatures = false;

        disableFeatures |= this.majorFeatureGenerator.func_75051_a(this.worldObj, this.rand, chunkX, chunkZ);
        disableFeatures |= !TFFeature.getNearestFeature(chunkX, chunkZ, this.worldObj).areChunkDecorationsEnabled;
        this.hollowTreeGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);
        int i2;
        int j3;
        int j4;

        if (!disableFeatures && this.rand.nextInt(4) == 0 && biomeGen.field_76760_I.field_76808_K) {
            i2 = mapX + this.rand.nextInt(16) + 8;
            j3 = this.rand.nextInt(TFWorld.CHUNKHEIGHT);
            j4 = mapY + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.field_150355_j)).func_76484_a(this.worldObj, this.rand, i2, j3, j4);
        }

        if (!disableFeatures && this.rand.nextInt(32) == 0) {
            i2 = mapX + this.rand.nextInt(16) + 8;
            j3 = this.rand.nextInt(this.rand.nextInt(TFWorld.CHUNKHEIGHT - 8) + 8);
            j4 = mapY + this.rand.nextInt(16) + 8;
            if (j3 < TFWorld.SEALEVEL || this.rand.nextInt(10) == 0) {
                (new WorldGenLakes(Blocks.field_150353_l)).func_76484_a(this.worldObj, this.rand, i2, j3, j4);
            }
        }

        for (i2 = 0; i2 < 8; ++i2) {
            j3 = mapX + this.rand.nextInt(16) + 8;
            j4 = this.rand.nextInt(TFWorld.CHUNKHEIGHT);
            int l3 = mapY + this.rand.nextInt(16) + 8;

            (new WorldGenDungeons()).func_76484_a(this.worldObj, this.rand, j3, j4, l3);
        }

        biomeGen.func_76728_a(this.worldObj, this.rand, mapX, mapY);
        SpawnerAnimals.func_77191_a(this.worldObj, biomeGen, mapX + 8, mapY + 8, 16, 16, this.rand);
        mapX += 8;
        mapY += 8;

        for (i2 = 0; i2 < 16; ++i2) {
            for (j3 = 0; j3 < 16; ++j3) {
                j4 = this.worldObj.func_72874_g(mapX + i2, mapY + j3);
                if (this.worldObj.func_72884_u(i2 + mapX, j4 - 1, j3 + mapY)) {
                    this.worldObj.func_147465_d(i2 + mapX, j4 - 1, j3 + mapY, Blocks.field_150432_aD, 0, 2);
                }

                if (this.worldObj.func_147478_e(i2 + mapX, j4, j3 + mapY, true)) {
                    this.worldObj.func_147465_d(i2 + mapX, j4, j3 + mapY, Blocks.field_150431_aC, 0, 2);
                }
            }
        }

        BlockFalling.field_149832_M = false;
    }

    public boolean func_73151_a(boolean flag, IProgressUpdate iprogressupdate) {
        return true;
    }

    public boolean func_73157_c() {
        return true;
    }

    public String func_73148_d() {
        return "TwilightLevelSource";
    }

    public List func_73155_a(EnumCreatureType creatureType, int mapX, int mapY, int mapZ) {
        TFFeature nearestFeature = TFFeature.getFeatureForRegion(mapX >> 4, mapZ >> 4, this.worldObj);

        if (nearestFeature != TFFeature.nothing) {
            if (this.isStructureConquered(mapX, mapY, mapZ)) {
                return null;
            }

            int biome = this.majorFeatureGenerator.getSpawnListIndexAt(mapX, mapY, mapZ);

            if (biome >= 0) {
                return nearestFeature.getSpawnableList(creatureType, biome);
            }
        }

        BiomeGenBase biome1 = this.worldObj.func_72807_a(mapX, mapZ);

        return biome1 == null ? null : (mapY < TFWorld.SEALEVEL && creatureType == EnumCreatureType.monster && biome1 instanceof TFBiomeBase ? ((TFBiomeBase) biome1).getUndergroundSpawnableList() : biome1.func_76747_a(creatureType));
    }

    public boolean isBlockInStructureBB(int mapX, int mapY, int mapZ) {
        return this.majorFeatureGenerator.func_75048_a(mapX, mapY, mapZ);
    }

    public void setStructureConquered(int mapX, int mapY, int mapZ, boolean flag) {
        this.majorFeatureGenerator.setStructureConquered(mapX, mapY, mapZ, flag);
    }

    public StructureBoundingBox getSBBAt(int mapX, int mapY, int mapZ) {
        return this.majorFeatureGenerator.getSBBAt(mapX, mapY, mapZ);
    }

    public boolean isBlockProtected(int x, int y, int z) {
        return this.majorFeatureGenerator.isBlockProtectedAt(x, y, z);
    }

    public boolean isStructureConquered(int mapX, int mapY, int mapZ) {
        return this.majorFeatureGenerator.isStructureConquered(mapX, mapY, mapZ);
    }

    public boolean isBlockInFullStructure(int x, int z) {
        return this.majorFeatureGenerator.isBlockInFullStructure(x, z);
    }

    public boolean isBlockNearFullStructure(int x, int z, int range) {
        return this.majorFeatureGenerator.isBlockNearFullStructure(x, z, range);
    }

    public StructureBoundingBox getFullSBBAt(int mapX, int mapZ) {
        return this.majorFeatureGenerator.getFullSBBAt(mapX, mapZ);
    }

    public StructureBoundingBox getFullSBBNear(int mapX, int mapZ, int range) {
        return this.majorFeatureGenerator.getFullSBBNear(mapX, mapZ, range);
    }

    public int func_73152_e() {
        return 0;
    }

    public void func_82695_e(int i, int j) {
        this.majorFeatureGenerator.func_151539_a(this, this.worldObj, i, j, (Block[]) null);
        this.hollowTreeGenerator.func_151539_a(this, this.worldObj, i, j, (Block[]) null);
    }

    public boolean func_73156_b() {
        return false;
    }

    public void func_104112_b() {}

    public ChunkPosition func_147416_a(World world, String s, int i, int j, int k) {
        return null;
    }
}
