package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFTrollSteinn extends Block {

    private static final int LIGHT_THRESHHOLD = 7;
    private IIcon blockIconLight;

    protected BlockTFTrollSteinn() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0F);
        this.func_149752_b(15.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149658_d("TwilightForest:trollsteinn");
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(2) == 0) {
            this.sparkle(world, x, y, z, rand);
        }

    }

    private void sparkle(World world, int x, int y, int z, Random rand) {
        Random random = rand;
        double pixel = 0.0625D;
        byte threshhold = 7;

        for (int side = 0; side < 6; ++side) {
            double rx = (double) ((float) x + random.nextFloat());
            double ry = (double) ((float) y + random.nextFloat());
            double rz = (double) ((float) z + random.nextFloat());

            if (side == 0 && !world.func_147439_a(x, y - 1, z).func_149662_c() && world.func_72957_l(x, y - 1, z) <= threshhold) {
                ry = (double) (y + 0) - pixel;
            }

            if (side == 1 && !world.func_147439_a(x, y + 1, z).func_149662_c() && world.func_72957_l(x, y + 1, z) <= threshhold) {
                ry = (double) (y + 1) + pixel;
            }

            if (side == 2 && !world.func_147439_a(x, y, z + 1).func_149662_c() && world.func_72957_l(x, y, z + 1) <= threshhold) {
                rz = (double) (z + 1) + pixel;
            }

            if (side == 3 && !world.func_147439_a(x, y, z - 1).func_149662_c() && world.func_72957_l(x, y, z - 1) <= threshhold) {
                rz = (double) (z + 0) - pixel;
            }

            if (side == 4 && !world.func_147439_a(x + 1, y, z).func_149662_c() && world.func_72957_l(x + 1, y, z) <= threshhold) {
                rx = (double) (x + 1) + pixel;
            }

            if (side == 5 && !world.func_147439_a(x - 1, y, z).func_149662_c() && world.func_72957_l(x - 1, y, z) <= threshhold) {
                rx = (double) (x + 0) - pixel;
            }

            if (rx < (double) x || rx > (double) (x + 1) || ry < 0.0D || ry > (double) (y + 1) || rz < (double) z || rz > (double) (z + 1)) {
                world.func_72869_a("reddust", rx, ry, rz, 0.25D, -1.0D, 0.5D);
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return side == 0 && this.isBlockLit(world, x, y - 1, z) ? this.blockIconLight : (side == 1 && this.isBlockLit(world, x, y + 1, z) ? this.blockIconLight : (side == 2 && this.isBlockLit(world, x, y, z - 1) ? this.blockIconLight : (side == 3 && this.isBlockLit(world, x, y, z + 1) ? this.blockIconLight : (side == 4 && this.isBlockLit(world, x - 1, y, z) ? this.blockIconLight : (side == 5 && this.isBlockLit(world, x + 1, y, z) ? this.blockIconLight : this.func_149691_a(side, world.func_72805_g(x, y, z)))))));
    }

    private boolean isBlockLit(IBlockAccess world, int x, int y, int z) {
        byte threshhold = 112;

        if (world.func_147439_a(x, y, z).func_149662_c()) {
            return false;
        } else {
            int light = world.func_72802_i(x, y, z, 0);
            int sky = light % 65536;
            int block = light / 65536;

            return sky > threshhold || block > threshhold;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(this.func_149641_N());
        this.blockIconLight = par1IconRegister.func_94245_a(this.func_149641_N() + "_light");
    }
}
