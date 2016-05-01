package twilightforest.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.EntityTFKingSpider;
import twilightforest.entity.EntityTFKobold;
import twilightforest.entity.EntityTFMistWolf;
import twilightforest.entity.EntityTFSkeletonDruid;

public class TFBiomeDarkForest extends TFBiomeBase {

    private static final int MONSTER_SPAWN_RATE = 20;
    Random monsterRNG;
    ArrayList emptyList = new ArrayList();

    public TFBiomeDarkForest(int i) {
        super(i);
        this.field_76750_F = 0.7F;
        this.field_76751_G = 0.8F;
        this.getTFBiomeDecorator().canopyPerChunk = 5.5F;
        this.getTFBiomeDecorator().setTreesPerChunk(10);
        this.getTFBiomeDecorator().setGrassPerChunk(-99);
        this.getTFBiomeDecorator().setFlowersPerChunk(-99);
        this.getTFBiomeDecorator().setMushroomsPerChunk(2);
        this.getTFBiomeDecorator().setDeadBushPerChunk(10);
        this.field_76748_D = 0.05F;
        this.field_76749_E = 0.05F;
        this.monsterRNG = new Random();
        this.field_76761_J.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityZombie.class, 5, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntitySkeleton.class, 5, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityTFMistWolf.class, 10, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityTFSkeletonDruid.class, 10, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityTFKingSpider.class, 10, 1, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityTFKobold.class, 10, 4, 8));
        this.field_76761_J.add(new SpawnListEntry(EntityWitch.class, 1, 1, 1));
        this.field_76760_I.field_76808_K = false;
    }

    public BiomeDecorator func_76729_a() {
        return new TFDarkForestBiomeDecorator();
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(5) == 0 ? new WorldGenShrub(3, 0) : (random.nextInt(8) == 0 ? this.birchGen : this.field_76757_N));
    }

    public int func_150558_b(int x, int y, int z) {
        double d0 = (double) this.func_150564_a(x, y, z);
        double d1 = (double) this.func_76727_i();

        return ((ColorizerGrass.func_77480_a(d0, d1) & 16711422) + 1969742) / 2;
    }

    public int func_150571_c(int x, int y, int z) {
        double d0 = (double) this.func_150564_a(x, y, z);
        double d1 = (double) this.func_76727_i();

        return ((ColorizerFoliage.func_77470_a(d0, d1) & 16711422) + 1969742) / 2;
    }

    public List func_76747_a(EnumCreatureType par1EnumCreatureType) {
        return (List) (par1EnumCreatureType == EnumCreatureType.monster ? (this.monsterRNG.nextInt(20) == 0 ? this.field_76761_J : this.emptyList) : (par1EnumCreatureType == EnumCreatureType.creature ? this.field_76762_K : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.field_76755_L : (par1EnumCreatureType == EnumCreatureType.ambient ? this.field_82914_M : null))));
    }

    public boolean func_76736_e() {
        return true;
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressHydra;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 60L == 0L) {
            player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 100, 0));
            if (world.field_73012_v.nextInt(4) == 0) {
                TFFeature.tfStronghold.trySpawnHintMonster(world, player);
            }
        }

    }
}
