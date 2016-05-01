package twilightforest.biomes;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.TFBlocks;

public class TFBiomeTwilightForestVariant extends TFBiomeBase {

    public TFBiomeTwilightForestVariant(int i) {
        super(i);
        this.field_76750_F = 0.7F;
        this.field_76751_G = 0.8F;
        this.getTFBiomeDecorator().setTreesPerChunk(25);
        this.getTFBiomeDecorator().setGrassPerChunk(15);
        this.getTFBiomeDecorator().setFlowersPerChunk(8);
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(5) == 0 ? new WorldGenShrub(3, 0) : (random.nextInt(10) == 0 ? new WorldGenBigTree(false) : this.field_76757_N));
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(4) != 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : (par1Random.nextBoolean() ? new WorldGenTallGrass(TFBlocks.plant, 4) : new WorldGenTallGrass(Blocks.field_150329_H, 1));
    }

    public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
        TFBiomeTwilightForestVariant.field_150610_ae.func_150548_a(3);

        for (int i = 0; i < 7; ++i) {
            int rx = par3 + par2Random.nextInt(16) + 8;
            int rz = par4 + par2Random.nextInt(16) + 8;
            int ry = par2Random.nextInt(par1World.func_72976_f(rx, rz) + 32);

            TFBiomeTwilightForestVariant.field_150610_ae.func_76484_a(par1World, par2Random, rx, ry, rz);
        }

        super.func_76728_a(par1World, par2Random, par3, par4);
    }
}
