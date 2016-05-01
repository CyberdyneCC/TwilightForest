package twilightforest.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.world.layer.GenLayerTF;

public class TFWorldChunkManager extends WorldChunkManager {

    private GenLayer unzoomedBiomes;
    private GenLayer zoomedBiomes;
    private BiomeCache myBiomeCache;
    private List myBiomesToSpawnIn;

    protected TFWorldChunkManager() {
        this.myBiomeCache = new BiomeCache(this);
        this.myBiomesToSpawnIn = new ArrayList();
        this.myBiomesToSpawnIn.add(TFBiomeBase.twilightForest);
        this.myBiomesToSpawnIn.add(TFBiomeBase.twilightForest2);
        this.myBiomesToSpawnIn.add(TFBiomeBase.clearing);
        this.myBiomesToSpawnIn.add(TFBiomeBase.tfSwamp);
        this.myBiomesToSpawnIn.add(TFBiomeBase.mushrooms);
    }

    public TFWorldChunkManager(long par1, WorldType par3WorldType) {
        this();
        GenLayer[] agenlayer;

        if (TwilightForestMod.oldMapGen) {
            agenlayer = GenLayerTF.makeTheWorldOldMapGen(par1);
        } else {
            agenlayer = GenLayerTF.makeTheWorld(par1);
        }

        this.unzoomedBiomes = agenlayer[0];
        this.zoomedBiomes = agenlayer[1];
    }

    public TFWorldChunkManager(World par1World) {
        this(par1World.func_72905_C(), par1World.func_72912_H().func_76067_t());
    }

    public List func_76932_a() {
        return this.myBiomesToSpawnIn;
    }

    public BiomeGenBase func_76935_a(int par1, int par2) {
        BiomeGenBase biome = this.myBiomeCache.func_76837_b(par1, par2);

        return biome == null ? TFBiomeBase.twilightForest : biome;
    }

    public float[] func_76936_a(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
        IntCache.func_76446_a();
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] ai = this.zoomedBiomes.func_75904_a(par2, par3, par4, par5);

        for (int i = 0; i < par4 * par5; ++i) {
            if (ai[i] >= 0 && BiomeGenBase.func_150568_d(ai[i]) != null) {
                float f = (float) BiomeGenBase.func_150568_d(ai[i]).func_76744_g() / 65536.0F;

                if (f > 1.0F) {
                    f = 1.0F;
                }

                par1ArrayOfFloat[i] = f;
            }
        }

        return par1ArrayOfFloat;
    }

    public float func_76939_a(float par1, int par2) {
        return par1;
    }

    public BiomeGenBase[] func_76937_a(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int z, int length, int width) {
        IntCache.func_76446_a();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < length * width) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[length * width];
        }

        int[] arrayOfInts = this.unzoomedBiomes.func_75904_a(x, z, length, width);

        for (int i = 0; i < length * width; ++i) {
            if (arrayOfInts[i] >= 0) {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.func_150568_d(arrayOfInts[i]);
            } else {
                par1ArrayOfBiomeGenBase[i] = TFBiomeBase.twilightForest;
            }
        }

        return par1ArrayOfBiomeGenBase;
    }

    public BiomeGenBase[] func_76933_b(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        return this.func_76931_a(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] func_76931_a(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int y, int width, int length, boolean cacheFlag) {
        IntCache.func_76446_a();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < width * length) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (y & 15) == 0) {
            BiomeGenBase[] abiomegenbase = this.myBiomeCache.func_76839_e(x, y);

            System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, width * length);
            return par1ArrayOfBiomeGenBase;
        } else {
            int[] ai = this.zoomedBiomes.func_75904_a(x, y, width, length);

            for (int i = 0; i < width * length; ++i) {
                if (ai[i] >= 0) {
                    par1ArrayOfBiomeGenBase[i] = BiomeGenBase.func_150568_d(ai[i]);
                } else {
                    par1ArrayOfBiomeGenBase[i] = TFBiomeBase.twilightForest;
                }
            }

            return par1ArrayOfBiomeGenBase;
        }
    }

    public boolean func_76940_a(int par1, int par2, int par3, List par4List) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.func_75904_a(i, j, i1, j1);

        for (int k1 = 0; k1 < i1 * j1; ++k1) {
            BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(ai[k1]);

            if (!par4List.contains(biomegenbase)) {
                return false;
            }
        }

        return true;
    }

    public ChunkPosition func_150795_a(int par1, int par2, int par3, List par4List, Random par5Random) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.func_75904_a(i, j, i1, j1);
        ChunkPosition chunkposition = null;
        int k1 = 0;

        for (int l1 = 0; l1 < ai.length; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(ai[l1]);

            if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(k1 + 1) == 0)) {
                chunkposition = new ChunkPosition(i2, 0, j2);
                ++k1;
            }
        }

        return chunkposition;
    }

    public void func_76938_b() {
        this.myBiomeCache.func_76838_a();
    }

    public int getFeatureID(int mapX, int mapZ, World world) {
        return this.getFeatureAt(mapX, mapZ, world).featureID;
    }

    public TFFeature getFeatureAt(int mapX, int mapZ, World world) {
        return TFFeature.generateFeatureFor1Point7(mapX >> 4, mapZ >> 4, world);
    }

    public boolean isInFeatureChunk(World world, int mapX, int mapZ) {
        if (TwilightForestMod.oldMapGen) {
            return this.isInFeatureChunkOld(world, mapX, mapZ);
        } else {
            int chunkX = mapX >> 4;
            int chunkZ = mapZ >> 4;
            ChunkCoordinates cc = TFFeature.getNearestCenterXYZ(chunkX, chunkZ, world);

            return chunkX == cc.field_71574_a >> 4 && chunkZ == cc.field_71573_c >> 4;
        }
    }

    public boolean isInFeatureChunkOld(World world, int mapX, int mapZ) {
        int chunkX = mapX >> 4;
        int chunkZ = mapZ >> 4;

        return chunkX % 16 == 0 && chunkZ % 16 == 0;
    }
}
