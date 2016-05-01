package twilightforest.block;

import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.tileentity.TileEntityTFFirefly;

public class BlockTFFirefly extends BlockTFCritter {

    public static int sprFirefly = 4;
    public static Random rand = new Random();

    protected BlockTFFirefly() {
        this.func_149715_a(0.9375F);
    }

    public int tickRate() {
        return 50 + BlockTFFirefly.rand.nextInt(50);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTFFirefly();
    }

    public void func_149726_b(World world, int x, int y, int z) {
        super.func_149726_b(world, x, y, z);
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K && world.func_72957_l(x, y, z) < 12) {
            world.func_147471_g(x, y, z);
            world.func_147464_a(x, y, z, this, this.tickRate());
        }

    }
}
