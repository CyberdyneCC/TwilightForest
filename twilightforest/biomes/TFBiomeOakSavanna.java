package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenCanopyOak;
import twilightforest.world.TFGenNoTree;

public class TFBiomeOakSavanna extends TFBiomeTwilightForest {

    public TFBiomeOakSavanna(int i) {
        super(i);
        this.getTFBiomeDecorator().canopyTreeGen = new TFGenCanopyOak();
        this.getTFBiomeDecorator().alternateCanopyChance = 0.8F;
        this.getTFBiomeDecorator().alternateCanopyGen = new TFGenNoTree();
        this.field_76750_F = 0.9F;
        this.field_76751_G = 0.0F;
        this.field_76760_I.field_76832_z = 1;
        this.field_76760_I.field_76802_A = 4;
        this.field_76760_I.field_76803_B = 20;
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(10) == 0 ? new WorldGenBigTree(false) : this.field_76757_N);
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(10) == 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : (par1Random.nextInt(10) == 0 ? new WorldGenTallGrass(TFBlocks.plant, 4) : new WorldGenTallGrass(Blocks.field_150329_H, 1));
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_) {
        double d0 = TFBiomeOakSavanna.field_150606_ad.func_151601_a((double) p_150572_2_ / 200.0D, (double) p_150572_4_ / 200.0D);
        int l;

        if (d0 < -0.8D) {
            l = p_150572_1_.nextInt(4);
            return BlockFlower.field_149859_a[4 + l];
        } else if (p_150572_1_.nextInt(3) > 0) {
            l = p_150572_1_.nextInt(3);
            return l == 0 ? BlockFlower.field_149859_a[0] : (l == 1 ? BlockFlower.field_149859_a[3] : BlockFlower.field_149859_a[8]);
        } else {
            return BlockFlower.field_149858_b[0];
        }
    }

    public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
        TFBiomeOakSavanna.field_150610_ae.func_150548_a(2);

        for (int k = 0; k < 7; ++k) {
            int l = par3 + par2Random.nextInt(16) + 8;
            int i1 = par4 + par2Random.nextInt(16) + 8;
            int j1 = par2Random.nextInt(par1World.func_72976_f(l, i1) + 32);

            TFBiomeOakSavanna.field_150610_ae.func_76484_a(par1World, par2Random, l, j1, i1);
        }

        super.func_76728_a(par1World, par2Random, par3, par4);
    }
}
