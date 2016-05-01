package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenLargeRainboak;
import twilightforest.world.TFGenSmallRainboak;
import twilightforest.world.TFWorld;

public class TFBiomeEnchantedForest extends TFBiomeBase {

    Random colorRNG = new Random();

    public TFBiomeEnchantedForest(int i) {
        super(i);
        this.getTFBiomeDecorator().setGrassPerChunk(12);
        this.getTFBiomeDecorator().setFlowersPerChunk(8);
    }

    public int func_150558_b(int x, int y, int z) {
        return (super.func_150558_b(x, y, z) & 16776960) + this.getEnchantedColor(x, z);
    }

    public int func_150571_c(int x, int y, int z) {
        return (super.func_150571_c(x, y, z) & 16776960) + this.getEnchantedColor(x, z);
    }

    private int getEnchantedColor(int x, int z) {
        int cx = 256 * Math.round((float) (x - 8) / 256.0F) + 8;
        int cz = 256 * Math.round((float) (z - 8) / 256.0F) + 8;
        int dist = (int) MathHelper.func_76129_c((float) ((cx - x) * (cx - x) + (cz - z) * (cz - z)));
        int color = dist * 64;

        color %= 512;
        if (color > 255) {
            color = 511 - color;
        }

        color = 255 - color;
        int randomFlicker = this.colorRNG.nextInt(32) - 16;

        if (0 < color + randomFlicker && color + randomFlicker > 255) {
            color += randomFlicker;
        }

        return color;
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(15) == 0 ? new TFGenSmallRainboak() : (random.nextInt(50) == 0 ? new TFGenLargeRainboak() : (random.nextInt(5) == 0 ? this.birchGen : (random.nextInt(10) == 0 ? new WorldGenBigTree(false) : this.field_76757_N))));
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(3) > 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(TFBlocks.plant, 8) : new WorldGenTallGrass(Blocks.field_150329_H, 1));
    }

    public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
        super.func_76728_a(par1World, par2Random, par3, par4);
        WorldGenVines worldgenvines = new WorldGenVines();

        int i;
        int rx;
        int ry;

        for (i = 0; i < 20; ++i) {
            rx = par3 + par2Random.nextInt(16) + 8;
            byte rz = (byte) TFWorld.SEALEVEL;

            ry = par4 + par2Random.nextInt(16) + 8;
            worldgenvines.func_76484_a(par1World, par2Random, rx, rz, ry);
        }

        TFBiomeEnchantedForest.field_150610_ae.func_150548_a(3);

        for (i = 0; i < 20; ++i) {
            rx = par3 + par2Random.nextInt(16) + 8;
            int i = par4 + par2Random.nextInt(16) + 8;

            ry = par2Random.nextInt(par1World.func_72976_f(rx, i) + 32);
            TFBiomeEnchantedForest.field_150610_ae.func_76484_a(par1World, par2Random, rx, ry, i);
        }

        super.func_76728_a(par1World, par2Random, par3, par4);
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_) {
        return p_150572_1_.nextInt(3) > 0 ? BlockFlower.field_149859_a[1] : (p_150572_1_.nextBoolean() ? BlockFlower.field_149859_a[2] : BlockFlower.field_149859_a[3]);
    }
}
