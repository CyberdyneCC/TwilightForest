package twilightforest.biomes;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenVines;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenFireJet;
import twilightforest.world.TFWorld;

public class TFBiomeFireSwamp extends TFBiomeBase {

    protected TFBiomeFireSwamp(int i) {
        super(i);
        this.field_76750_F = 1.0F;
        this.field_76751_G = 0.4F;
        this.getTFBiomeDecorator().setDeadBushPerChunk(2);
        this.getTFBiomeDecorator().setMushroomsPerChunk(8);
        this.getTFBiomeDecorator().setReedsPerChunk(4);
        this.getTFBiomeDecorator().setClayPerChunk(1);
        this.getTFBiomeDecorator().setTreesPerChunk(3);
        this.getTFBiomeDecorator().setWaterlilyPerChunk(6);
        this.field_76759_H = 7089196;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().lavaPoolChance = 0.125F;
        this.getTFBiomeDecorator().mangrovesPerChunk = 3;
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(3) == 0 ? new WorldGenShrub(3, 0) : this.field_76763_Q);
    }

    public void func_76728_a(World par1World, Random par2Random, int mapX, int mapZ) {
        super.func_76728_a(par1World, par2Random, mapX, mapZ);
        TFFeature nearFeature = TFFeature.getNearestFeature(mapX >> 4, mapZ >> 4, par1World);

        if (nearFeature.areChunkDecorationsEnabled) {
            WorldGenVines worldgenvines = new WorldGenVines();

            int genFireJet;
            byte i;
            int j;

            for (int genSmoker = 0; genSmoker < 50; ++genSmoker) {
                genFireJet = mapX + par2Random.nextInt(16) + 8;
                i = (byte) TFWorld.SEALEVEL;
                j = mapZ + par2Random.nextInt(16) + 8;
                worldgenvines.func_76484_a(par1World, par2Random, genFireJet, i, j);
            }

            TFGenFireJet tfgenfirejet = new TFGenFireJet(TFBlocks.fireJet, 0);

            if (par2Random.nextInt(4) == 0) {
                genFireJet = mapX + par2Random.nextInt(16) + 8;
                i = (byte) TFWorld.SEALEVEL;
                j = mapZ + par2Random.nextInt(16) + 8;
                tfgenfirejet.func_76484_a(par1World, par2Random, genFireJet, i, j);
            }

            TFGenFireJet tfgenfirejet1 = new TFGenFireJet(TFBlocks.fireJet, 8);

            for (int i = 0; i < 1; ++i) {
                j = mapX + par2Random.nextInt(16) + 8;
                byte byte0 = (byte) TFWorld.SEALEVEL;
                int k = mapZ + par2Random.nextInt(16) + 8;

                tfgenfirejet1.func_76484_a(par1World, par2Random, j, byte0, k);
            }
        }

    }

    public int func_150558_b(int x, int y, int z) {
        return 5713443;
    }

    public int func_150571_c(int x, int y, int z) {
        return 6563343;
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressLabyrinth;
    }

    public void enforceProgession(EntityPlayer player, World world) {
        if (!world.field_72995_K && world.func_72820_D() % 60L == 0L) {
            player.func_70015_d(8);
        }

        if (world.field_73012_v.nextInt(4) == 0) {
            TFFeature.hydraLair.trySpawnHintMonster(world, player);
        }

    }
}
