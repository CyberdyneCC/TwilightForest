package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import twilightforest.item.TFItems;

public class BlockTFGiantLeaves extends BlockTFGiantBlock {

    public BlockTFGiantLeaves() {
        super(Blocks.field_150362_t);
        this.func_149711_c(12.8F);
        this.func_149713_g(1);
        this.func_149647_a(TFItems.creativeTab);
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        double d0 = 0.5D;
        double d1 = 1.0D;

        return ColorizerFoliage.func_77470_a(d0, d1);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_) {
        return ColorizerFoliage.func_77468_c();
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        int red = 0;
        int grn = 0;
        int blu = 0;

        for (int dz = -1; dz <= 1; ++dz) {
            for (int dx = -1; dx <= 1; ++dx) {
                int nearbyColor = world.func_72807_a(x + dx, z + dz).func_150571_c(x + dx, y, z + dz);

                red += (nearbyColor & 16711680) >> 16;
                grn += (nearbyColor & '\uff00') >> 8;
                blu += nearbyColor & 255;
            }
        }

        return (red / 9 & 255) << 16 | (grn / 9 & 255) << 8 | blu / 9 & 255;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        switch (side) {
        case 0:
            return (y & 3) == 3;

        case 1:
            return (y & 3) == 0;

        case 2:
            return (z & 3) == 3;

        case 3:
            return (z & 3) == 0;

        case 4:
            return (x & 3) == 3;

        case 5:
            return (x & 3) == 0;

        default:
            return super.func_149646_a(world, x, y, z, side);
        }
    }
}
