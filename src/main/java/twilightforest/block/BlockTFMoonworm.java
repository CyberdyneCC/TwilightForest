package twilightforest.block;

import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.tileentity.TileEntityTFMoonworm;

public class BlockTFMoonworm extends BlockTFCritter {

    public static int sprMoonworm = 52;

    public int tickRate() {
        return 50;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        int facing = world.func_72805_g(x, y, z) & 7;
        float wide = 0.25F;

        if (facing == 1) {
            this.func_149676_a(0.0F, 0.25F, 0.5F - wide, wide, 0.75F, 0.5F + wide);
        } else if (facing == 2) {
            this.func_149676_a(1.0F - wide, 0.25F, 0.5F - wide, 1.0F, 0.75F, 0.5F + wide);
        } else if (facing == 3) {
            this.func_149676_a(0.5F - wide, 0.25F, 0.0F, 0.5F + wide, 0.75F, wide);
        } else if (facing == 4) {
            this.func_149676_a(0.5F - wide, 0.25F, 1.0F - wide, 0.5F + wide, 0.75F, 1.0F);
        } else if (facing == 5) {
            this.func_149676_a(0.5F - wide, 0.0F, 0.25F, 0.5F + wide, wide, 0.75F);
        } else if (facing == 6) {
            this.func_149676_a(0.5F - wide, 1.0F - wide, 0.25F, 0.5F + wide, 1.0F, 0.75F);
        } else {
            float f1 = 0.1F;

            this.func_149676_a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, 0.6F, 0.5F + f1);
        }

    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 14;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTFMoonworm();
    }

    public void func_149726_b(World world, int x, int y, int z) {
        super.func_149726_b(world, x, y, z);
        world.func_147464_a(x, y, z, this, this.tickRate());
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (world.func_72957_l(x, y, z) < 12) {
            world.func_147464_a(x, y, z, this, this.tickRate());
        }

    }

    public boolean dropCritterIfCantStay(World world, int x, int y, int z) {
        if (!this.func_149742_c(world, x, y, z)) {
            world.func_147468_f(x, y, z);
            return false;
        } else {
            return true;
        }
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }
}
