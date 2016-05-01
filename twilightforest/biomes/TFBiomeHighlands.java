package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFTroll;
import twilightforest.world.TFGenTrollRoots;

public class TFBiomeHighlands extends TFBiomeBase {

    private static final WorldGenTaiga1 taigaGen1 = new WorldGenTaiga1();
    private static final WorldGenTaiga2 taigaGen2 = new WorldGenTaiga2(false);
    private static final WorldGenMegaPineTree megaPineGen1 = new WorldGenMegaPineTree(false, false);
    private static final WorldGenMegaPineTree megaPineGen2 = new WorldGenMegaPineTree(false, true);
    private static final WorldGenBlockBlob genBoulder = new WorldGenBlockBlob(Blocks.field_150341_Y, 0);
    private static final TFGenTrollRoots genTrollRoots = new TFGenTrollRoots();
    private static final WorldGenTallGrass worldGenMushgloom = new WorldGenTallGrass(TFBlocks.plant, 9);

    public TFBiomeHighlands(int i) {
        super(i);
        this.field_76750_F = 0.4F;
        this.field_76751_G = 0.7F;
        ((TFBiomeDecorator) this.field_76760_I).canopyPerChunk = -999.0F;
        this.field_76760_I.field_76803_B = 7;
        this.field_76760_I.field_76804_C = 1;
        this.field_76760_I.field_76808_K = false;
        this.undergroundMonsterList.clear();
        this.undergroundMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityCreeper.class, 1, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityTFTroll.class, 10, 4, 4));
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(4) == 0 ? TFBiomeHighlands.taigaGen1 : (random.nextInt(10) == 0 ? TFBiomeHighlands.taigaGen2 : (random.nextInt(3) == 0 ? TFBiomeHighlands.megaPineGen1 : (random.nextInt(13) == 0 ? TFBiomeHighlands.megaPineGen2 : this.birchGen))));
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(5) > 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : new WorldGenTallGrass(Blocks.field_150329_H, 1);
    }

    public void func_150573_a(World world, Random rand, Block[] blockStorage, byte[] metaStorage, int x, int z, double noiseVal) {
        this.field_76752_A = Blocks.field_150349_c;
        this.field_150604_aj = 0;
        this.field_76753_B = Blocks.field_150346_d;
        if (noiseVal > 1.75D) {
            this.field_76752_A = Blocks.field_150346_d;
            this.field_150604_aj = 1;
        } else if (noiseVal > -0.95D) {
            this.field_76752_A = Blocks.field_150346_d;
            this.field_150604_aj = 2;
        }

        this.genTwilightBiomeTerrain(world, rand, blockStorage, metaStorage, x, z, noiseVal);
    }

    public void func_76728_a(World par1World, Random par2Random, int mapX, int mapZ) {
        int maxBoulders = par2Random.nextInt(2);

        int dx;
        int dy;
        int dz;
        int i;

        for (i = 0; i < maxBoulders; ++i) {
            dx = mapX + par2Random.nextInt(16) + 8;
            dz = mapZ + par2Random.nextInt(16) + 8;
            dy = par1World.func_72976_f(dx, dz);
            TFBiomeHighlands.genBoulder.func_76484_a(par1World, par2Random, dx, dy, dz);
        }

        TFBiomeHighlands.field_150610_ae.func_150548_a(3);

        for (i = 0; i < 7; ++i) {
            dx = mapX + par2Random.nextInt(16) + 8;
            dz = mapZ + par2Random.nextInt(16) + 8;
            dy = par2Random.nextInt(par1World.func_72976_f(dx, dz) + 32);
            TFBiomeHighlands.field_150610_ae.func_76484_a(par1World, par2Random, dx, dy, dz);
        }

        int rx;
        int rz;

        for (i = 0; i < 1; ++i) {
            rx = mapX + par2Random.nextInt(16) + 8;
            int ry = mapZ + par2Random.nextInt(16) + 8;

            rz = par2Random.nextInt(64);
            TFBiomeHighlands.worldGenMushgloom.func_76484_a(par1World, par2Random, rx, rz, ry);
        }

        for (i = 0; i < 24; ++i) {
            rx = mapX + par2Random.nextInt(16) + 8;
            byte b0 = 64;

            rz = mapZ + par2Random.nextInt(16) + 8;
            TFBiomeHighlands.genTrollRoots.func_76484_a(par1World, par2Random, rx, b0, rz);
        }

        super.func_76728_a(par1World, par2Random, mapX, mapZ);
    }

    public String func_150572_a(Random rand, int x, int y, int z) {
        return rand.nextBoolean() ? BlockFlower.field_149858_b[0] : BlockFlower.field_149859_a[8];
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressGlacier;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 5L == 0L) {
            player.func_70097_a(DamageSource.field_76376_m, 0.5F);
            world.func_72956_a(player, "random.fizz", 1.0F, 1.0F);
            if (world.field_73012_v.nextInt(4) == 0) {
                TFFeature.trollCave.trySpawnHintMonster(world, player);
            }
        }

    }
}
