package twilightforest.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMosquitoSwarm;
import twilightforest.world.TFGenHugeLilyPad;
import twilightforest.world.TFWorld;

public class TFBiomeSwamp extends TFBiomeBase {

    private static final int MONSTER_SPAWN_RATE = 20;
    Random monsterRNG = new Random(53439L);
    ArrayList emptyList = new ArrayList();
    WorldGenVines worldgenvines = new WorldGenVines();
    WorldGenerator hugeLilyPadGen = new TFGenHugeLilyPad();
    WorldGenerator hugeWaterLilyGen = new TFGenHugeWaterLily();

    public TFBiomeSwamp(int i) {
        super(i);
        this.field_76750_F = 0.8F;
        this.field_76751_G = 0.9F;
        this.getTFBiomeDecorator().setDeadBushPerChunk(1);
        this.getTFBiomeDecorator().setMushroomsPerChunk(8);
        this.getTFBiomeDecorator().setReedsPerChunk(10);
        this.getTFBiomeDecorator().setClayPerChunk(1);
        this.getTFBiomeDecorator().setTreesPerChunk(2);
        this.getTFBiomeDecorator().setWaterlilyPerChunk(20);
        this.field_76759_H = 14745518;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().lakesPerChunk = 2;
        this.getTFBiomeDecorator().mangrovesPerChunk = 3;
        this.field_76761_J.add(new SpawnListEntry(EntityTFMosquitoSwarm.class, 10, 1, 1));
        this.field_76761_J.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(3) == 0 ? new WorldGenShrub(3, 0) : this.field_76763_Q);
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(TFBlocks.plant, 4) : new WorldGenTallGrass(Blocks.field_150329_H, 1));
    }

    public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
        super.func_76728_a(par1World, par2Random, par3, par4);

        int i;
        int x;
        int z;

        for (i = 0; i < 50; ++i) {
            x = par3 + par2Random.nextInt(16) + 8;
            byte y = (byte) TFWorld.SEALEVEL;

            z = par4 + par2Random.nextInt(16) + 8;
            this.worldgenvines.func_76484_a(par1World, par2Random, x, y, z);
        }

        int i;

        for (i = 0; i < 25; ++i) {
            x = par3 + par2Random.nextInt(16) + 8;
            i = TFWorld.SEALEVEL;
            z = par4 + par2Random.nextInt(16) + 8;
            this.hugeLilyPadGen.func_76484_a(par1World, par2Random, x, i, z);
        }

        for (i = 0; i < 2; ++i) {
            x = par3 + par2Random.nextInt(16) + 8;
            i = TFWorld.SEALEVEL;
            z = par4 + par2Random.nextInt(16) + 8;
            this.hugeWaterLilyGen.func_76484_a(par1World, par2Random, x, i, z);
        }

    }

    public int func_150558_b(int x, int y, int z) {
        double d0 = (double) this.func_150564_a(x, y, z);
        double d1 = (double) this.func_76727_i();

        return ((ColorizerGrass.func_77480_a(d0, d1) & 16711422) + 5115470) / 2;
    }

    public int func_150571_c(int x, int y, int z) {
        double d0 = (double) this.func_150564_a(x, y, z);
        double d1 = (double) this.func_76727_i();

        return ((ColorizerFoliage.func_77470_a(d0, d1) & 16711422) + 5115470) / 2;
    }

    public List func_76747_a(EnumCreatureType par1EnumCreatureType) {
        return (List) (par1EnumCreatureType == EnumCreatureType.monster ? (this.monsterRNG.nextInt(20) == 0 ? this.field_76761_J : this.emptyList) : (par1EnumCreatureType == EnumCreatureType.creature ? this.field_76762_K : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.field_76755_L : (par1EnumCreatureType == EnumCreatureType.ambient ? this.field_82914_M : null))));
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressLich;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 60L == 0L) {
            PotionEffect currentHunger = player.func_70660_b(Potion.field_76438_s);
            int hungerLevel = currentHunger != null ? currentHunger.func_76458_c() + 1 : 1;

            player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, 100, hungerLevel));
            if (world.field_73012_v.nextInt(4) == 0) {
                TFFeature.labyrinth.trySpawnHintMonster(world, player);
            }
        }

    }
}
