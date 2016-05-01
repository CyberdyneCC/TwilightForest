package twilightforest.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.EntityTFWinterWolf;
import twilightforest.entity.EntityTFYeti;
import twilightforest.world.TFGenLargeWinter;

public class TFBiomeSnow extends TFBiomeBase {

    private static final int MONSTER_SPAWN_RATE = 10;
    Random monsterRNG = new Random(53439L);
    ArrayList emptyList = new ArrayList();

    public TFBiomeSnow(int i) {
        super(i);
        this.getTFBiomeDecorator().setTreesPerChunk(7);
        this.getTFBiomeDecorator().setGrassPerChunk(1);
        this.field_76750_F = 0.125F;
        this.field_76751_G = 0.9F;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().field_76808_K = false;
        this.field_76761_J.add(new SpawnListEntry(EntityTFYeti.class, 20, 4, 4));
        this.field_76761_J.add(new SpawnListEntry(EntityTFWinterWolf.class, 5, 1, 4));
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(3) == 0 ? new WorldGenTaiga1() : (random.nextInt(8) == 0 ? new TFGenLargeWinter() : new WorldGenTaiga2(true)));
    }

    public boolean func_76746_c() {
        return true;
    }

    public boolean func_76738_d() {
        return false;
    }

    public List func_76747_a(EnumCreatureType par1EnumCreatureType) {
        return (List) (par1EnumCreatureType == EnumCreatureType.monster ? (this.monsterRNG.nextInt(10) == 0 ? this.field_76761_J : this.emptyList) : (par1EnumCreatureType == EnumCreatureType.creature ? this.field_76762_K : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.field_76755_L : (par1EnumCreatureType == EnumCreatureType.ambient ? this.field_82914_M : null))));
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressUrghast;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 60L == 0L) {
            player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 2));
            if (world.field_73012_v.nextInt(4) == 0) {
                TFFeature.yetiCave.trySpawnHintMonster(world, player);
            }
        }

    }
}
