package twilightforest.biomes;

import java.util.Random;
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
import twilightforest.entity.passive.EntityTFPenguin;
import twilightforest.world.TFGenPenguins;
import twilightforest.world.TFWorld;

public class TFBiomeGlacier extends TFBiomeBase {

    public TFBiomeGlacier(int i) {
        super(i);
        this.getTFBiomeDecorator().setTreesPerChunk(1);
        this.getTFBiomeDecorator().setGrassPerChunk(0);
        this.field_76750_F = 0.0F;
        this.field_76751_G = 0.1F;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.field_76762_K.add(new SpawnListEntry(EntityTFPenguin.class, 10, 4, 4));
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(true));
    }

    public boolean func_76746_c() {
        return true;
    }

    public boolean func_76738_d() {
        return false;
    }

    public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
        super.func_76728_a(par1World, par2Random, par3, par4);
        TFGenPenguins penguins = new TFGenPenguins();

        if (par2Random.nextInt(4) == 0) {
            int j = par3 + par2Random.nextInt(16) + 8;
            byte byte0 = (byte) TFWorld.SEALEVEL;
            int k = par4 + par2Random.nextInt(16) + 8;

            penguins.func_76484_a(par1World, par2Random, j, byte0, k);
        }

    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressUrghast;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 60L == 0L) {
            player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 3));
        }

        if (world.field_73012_v.nextInt(4) == 0) {
            TFFeature.iceTower.trySpawnHintMonster(world, player);
        }

    }
}
