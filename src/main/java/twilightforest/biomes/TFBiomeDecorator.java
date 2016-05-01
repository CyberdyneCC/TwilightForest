package twilightforest.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.world.TFGenCanopyMushroom;
import twilightforest.world.TFGenCanopyTree;
import twilightforest.world.TFGenFallenHollowLog;
import twilightforest.world.TFGenFallenSmallLog;
import twilightforest.world.TFGenFoundation;
import twilightforest.world.TFGenGroveRuins;
import twilightforest.world.TFGenHollowStump;
import twilightforest.world.TFGenHollowTree;
import twilightforest.world.TFGenMangroveTree;
import twilightforest.world.TFGenMonolith;
import twilightforest.world.TFGenMyceliumBlob;
import twilightforest.world.TFGenOutsideStalagmite;
import twilightforest.world.TFGenPlantRoots;
import twilightforest.world.TFGenStoneCircle;
import twilightforest.world.TFGenTorchBerries;
import twilightforest.world.TFGenWell;
import twilightforest.world.TFGenWitchHut;
import twilightforest.world.TFGenWoodRoots;
import twilightforest.world.TFGenerator;
import twilightforest.world.TFTreeGenerator;

public class TFBiomeDecorator extends BiomeDecorator {

    TFGenCanopyTree canopyTreeGen = new TFGenCanopyTree();
    TFTreeGenerator alternateCanopyGen = new TFGenCanopyMushroom();
    TFGenHollowTree hollowTreeGen = new TFGenHollowTree();
    TFGenMyceliumBlob myceliumBlobGen = new TFGenMyceliumBlob(5);
    WorldGenLakes extraLakeGen;
    WorldGenLakes extraLavaPoolGen;
    TFGenMangroveTree mangroveTreeGen = new TFGenMangroveTree();
    TFGenPlantRoots plantRootGen;
    TFGenWoodRoots woodRootGen;
    WorldGenLiquids caveWaterGen;
    TFGenTorchBerries torchBerryGen;
    public float canopyPerChunk;
    public float alternateCanopyChance;
    public int myceliumPerChunk;
    public int mangrovesPerChunk;
    public int lakesPerChunk;
    public float lavaPoolChance;
    static final List ruinList = new ArrayList();

    public TFBiomeDecorator() {
        this.extraLakeGen = new WorldGenLakes(Blocks.field_150355_j);
        this.extraLavaPoolGen = new WorldGenLakes(Blocks.field_150353_l);
        this.plantRootGen = new TFGenPlantRoots();
        this.woodRootGen = new TFGenWoodRoots();
        this.caveWaterGen = new WorldGenLiquids(Blocks.field_150358_i);
        this.torchBerryGen = new TFGenTorchBerries();
        this.canopyPerChunk = TwilightForestMod.canopyCoverage;
        this.alternateCanopyChance = 0.0F;
        this.myceliumPerChunk = 0;
        this.lakesPerChunk = 0;
        this.lavaPoolChance = 0.0F;
        this.mangrovesPerChunk = 0;
    }

    public void func_150512_a(World world, Random rand, BiomeGenBase biome, int mapX, int mapZ) {
        TFFeature nearFeature = TFFeature.getNearestFeature(mapX >> 4, mapZ >> 4, world);

        if (!nearFeature.areChunkDecorationsEnabled) {
            this.decorateUnderground(world, rand, mapX, mapZ);
            this.decorateOnlyOres(world, rand, mapX, mapZ);
        } else {
            this.field_76815_a = null;
            super.func_150512_a(world, rand, biome, mapX, mapZ);
        }

    }

    protected void func_150513_a(BiomeGenBase biome) {
        int nc;
        int i;
        int rx;

        if (this.field_76813_b.nextInt(6) == 0) {
            nc = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            rx = this.field_76815_a.func_72976_f(nc, i);
            TFGenerator rz = this.randomFeature(this.field_76813_b);

            if (rz.func_76484_a(this.field_76815_a, this.field_76813_b, nc, rx, i)) {
                ;
            }
        }

        nc = (int) this.canopyPerChunk + (this.field_76813_b.nextFloat() < this.canopyPerChunk - (float) ((int) this.canopyPerChunk) ? 1 : 0);

        int ry;
        int i;

        for (i = 0; i < nc; ++i) {
            rx = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            ry = this.field_76815_a.func_72976_f(rx, i);
            if (this.alternateCanopyChance > 0.0F && this.field_76813_b.nextFloat() <= this.alternateCanopyChance) {
                this.alternateCanopyGen.func_76484_a(this.field_76815_a, this.field_76813_b, rx, ry, i);
            } else {
                this.canopyTreeGen.func_76484_a(this.field_76815_a, this.field_76813_b, rx, ry, i);
            }
        }

        for (i = 0; i < this.mangrovesPerChunk; ++i) {
            rx = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            ry = this.field_76815_a.func_72976_f(rx, i);
            this.mangroveTreeGen.func_76484_a(this.field_76815_a, this.field_76813_b, rx, ry, i);
        }

        for (i = 0; i < this.lakesPerChunk; ++i) {
            rx = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            ry = this.field_76815_a.func_72976_f(rx, i);
            this.extraLakeGen.func_76484_a(this.field_76815_a, this.field_76813_b, rx, ry, i);
        }

        if (this.field_76813_b.nextFloat() <= this.lavaPoolChance) {
            i = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            rx = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76815_a.func_72976_f(i, rx);
            this.extraLavaPoolGen.func_76484_a(this.field_76815_a, this.field_76813_b, i, i, rx);
        }

        for (i = 0; i < this.myceliumPerChunk; ++i) {
            rx = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            i = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            ry = this.field_76815_a.func_72976_f(rx, i);
            this.myceliumBlobGen.func_76484_a(this.field_76815_a, this.field_76813_b, rx, ry, i);
        }

        super.func_150513_a(biome);
        this.decorateUnderground(this.field_76815_a, this.field_76813_b, this.field_76814_c, this.field_76811_d);
    }

    protected void decorateUnderground(World world, Random rand, int mapX, int mapZ) {
        int i;
        int rx;
        byte ry;
        int rz;

        for (i = 0; i < 12; ++i) {
            rx = mapX + rand.nextInt(16) + 8;
            ry = 64;
            rz = mapZ + rand.nextInt(16) + 8;
            this.plantRootGen.func_76484_a(world, rand, rx, ry, rz);
        }

        int i;

        for (i = 0; i < 20; ++i) {
            rx = mapX + rand.nextInt(16) + 8;
            i = rand.nextInt(64);
            rz = mapZ + rand.nextInt(16) + 8;
            this.woodRootGen.func_76484_a(world, rand, rx, i, rz);
        }

        if (this.field_76808_K) {
            for (i = 0; i < 50; ++i) {
                rx = mapX + rand.nextInt(16) + 8;
                i = rand.nextInt(24) + 4;
                rz = mapZ + rand.nextInt(16) + 8;
                this.caveWaterGen.func_76484_a(world, rand, rx, i, rz);
            }
        }

        for (i = 0; i < 3; ++i) {
            rx = mapX + rand.nextInt(16) + 8;
            ry = 64;
            rz = mapZ + rand.nextInt(16) + 8;
            this.torchBerryGen.func_76484_a(world, rand, rx, ry, rz);
        }

    }

    public void decorateOnlyOres(World world, Random rand, int mapX, int mapZ) {
        this.field_76815_a = world;
        this.field_76813_b = rand;
        this.field_76814_c = mapX;
        this.field_76811_d = mapZ;
        this.func_76797_b();
        this.field_76815_a = null;
        this.field_76813_b = null;
    }

    public TFGenerator randomFeature(Random rand) {
        return ((TFBiomeDecorator.RuinEntry) WeightedRandom.func_76271_a(rand, TFBiomeDecorator.ruinList)).generator;
    }

    public void setTreesPerChunk(int treesPerChunk) {
        this.field_76832_z = treesPerChunk;
    }

    public void setBigMushroomsPerChunk(int bigMushroomsPerChunk) {
        this.field_76807_J = bigMushroomsPerChunk;
    }

    public void setClayPerChunk(int clayPerChunk) {
        this.field_76806_I = clayPerChunk;
    }

    public void setDeadBushPerChunk(int deadBushPerChunk) {
        this.field_76804_C = deadBushPerChunk;
    }

    public void setMushroomsPerChunk(int mushroomsPerChunk) {
        this.field_76798_D = mushroomsPerChunk;
    }

    public void setFlowersPerChunk(int flowersPerChunk) {
        this.field_76802_A = flowersPerChunk;
    }

    public void setReedsPerChunk(int reedsPerChunk) {
        this.field_76799_E = reedsPerChunk;
    }

    public void setWaterlilyPerChunk(int waterlilyPerChunk) {
        this.field_76833_y = waterlilyPerChunk;
    }

    public void setGrassPerChunk(int grassPerChunk) {
        this.field_76803_B = grassPerChunk;
    }

    static {
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenStoneCircle(), 10));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenWell(), 10));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenWitchHut(), 5));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenOutsideStalagmite(), 12));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenFoundation(), 10));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenMonolith(), 10));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenGroveRuins(), 5));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenHollowStump(), 12));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenFallenHollowLog(), 10));
        TFBiomeDecorator.ruinList.add(new TFBiomeDecorator.RuinEntry(new TFGenFallenSmallLog(), 10));
    }

    static class RuinEntry extends Item {

        public final TFGenerator generator;

        public RuinEntry(TFGenerator generator, int weight) {
            super(weight);
            this.generator = generator;
        }
    }
}
