package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFMagicLeaves extends BlockLeaves {

    int oakColor = 4764952;
    int canopyColor = 6330464;
    int mangroveColor = 8431445;
    public static final int META_TIME = 0;
    public static final int META_TRANS = 1;
    public static final int META_MINE = 2;
    public static final int META_SORT = 3;
    public static IIcon SPR_TIMELEAVES;
    public static IIcon SPR_TIMEFX;
    public static IIcon SPR_TRANSLEAVES;
    public static IIcon SPR_TRANSFX;
    public static IIcon SPR_SORTLEAVES;
    public static IIcon SPR_SORTFX;

    protected BlockTFMagicLeaves() {
        this.func_149711_c(0.2F);
        this.func_149713_g(2);
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149741_i(int par1) {
        switch (par1 & 3) {
        case 0:
            return 6986775;

        case 1:
            return 7130346;

        case 2:
            return 16576836;

        case 3:
            return 3558403;

        default:
            return 16777215;
        }
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        int leafType = world.func_72805_g(x, y, z) & 3;
        int red = 0;
        int green = 0;
        int blue = 0;
        int fade;
        float spring;
        float fall;

        if (leafType == 0) {
            fade = x * 16 + y * 16 + z * 16;
            if ((fade & 256) != 0) {
                fade = 255 - (fade & 255);
            }

            fade &= 255;
            spring = (float) (255 - fade) / 255.0F;
            fall = (float) fade / 255.0F;
            red = (int) (spring * 106.0F + fall * 251.0F);
            green = (int) (spring * 156.0F + fall * 108.0F);
            blue = (int) (spring * 23.0F + fall * 27.0F);
        } else if (leafType == 2) {
            fade = x * 31 + y * 33 + z * 32;
            if ((fade & 256) != 0) {
                fade = 255 - (fade & 255);
            }

            fade &= 255;
            spring = (float) (255 - fade) / 255.0F;
            fall = (float) fade / 255.0F;
            red = (int) (spring * 252.0F + fall * 237.0F);
            green = (int) (spring * 241.0F + fall * 172.0F);
            blue = (int) (spring * 68.0F + fall * 9.0F);
        } else if (leafType == 1) {
            fade = x * 27 + y * 63 + z * 39;
            if ((fade & 256) != 0) {
                fade = 255 - (fade & 255);
            }

            fade &= 255;
            spring = (float) (255 - fade) / 255.0F;
            fall = (float) fade / 255.0F;
            red = (int) (spring * 108.0F + fall * 96.0F);
            green = (int) (spring * 204.0F + fall * 107.0F);
            blue = (int) (spring * 234.0F + fall * 121.0F);
        } else if (leafType == 3) {
            fade = x * 63 + y * 63 + z * 63;
            if ((fade & 256) != 0) {
                fade = 255 - (fade & 255);
            }

            fade &= 255;
            spring = (float) (255 - fade) / 255.0F;
            fall = (float) fade / 255.0F;
            red = (int) (spring * 54.0F + fall * 168.0F);
            green = (int) (spring * 76.0F + fall * 199.0F);
            blue = (int) (spring * 3.0F + fall * 43.0F);
        }

        return red << 16 | green << 8 | blue;
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getMagicLeavesBlockRenderID();
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta & 3) {
        case 1:
            return BlockTFMagicLeaves.SPR_TRANSLEAVES;

        case 3:
            return BlockTFMagicLeaves.SPR_SORTLEAVES;

        default:
            return BlockTFMagicLeaves.SPR_TIMELEAVES;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFMagicLeaves.SPR_TIMELEAVES = par1IconRegister.func_94245_a("TwilightForest:time_leaves");
        BlockTFMagicLeaves.SPR_TRANSLEAVES = par1IconRegister.func_94245_a("TwilightForest:trans_leaves");
        BlockTFMagicLeaves.SPR_SORTLEAVES = par1IconRegister.func_94245_a("TwilightForest:sort_leaves");
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        return Blocks.field_150362_t.func_149646_a(iblockaccess, i, j, k, side);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public void func_149734_b(World par1World, int x, int y, int z, Random par5Random) {
        int meta = par1World.func_72805_g(x, y, z);

        if ((meta & 3) == 1) {
            for (int i = 0; i < 1; ++i) {
                this.sparkleRunes(par1World, x, y, z, par5Random);
            }
        }

    }

    private void sparkleRunes(World world, int x, int y, int z, Random rand) {
        double offset = 0.0625D;
        int side = rand.nextInt(5) + 1;
        double rx = (double) ((float) x + rand.nextFloat());
        double ry = (double) ((float) y + rand.nextFloat());
        double rz = (double) ((float) z + rand.nextFloat());

        if (side == 0 && world.func_147437_c(x, y + 1, z)) {
            ry = (double) (y + 1) + offset;
        }

        if (side == 1 && world.func_147437_c(x, y - 1, z)) {
            ry = (double) (y + 0) - offset;
        }

        if (side == 2 && world.func_147437_c(x, y, z + 1)) {
            rz = (double) (z + 1) + offset;
        }

        if (side == 3 && world.func_147437_c(x, y, z - 1)) {
            rz = (double) (z + 0) - offset;
        }

        if (side == 4 && world.func_147437_c(x + 1, y, z)) {
            rx = (double) (x + 1) + offset;
        }

        if (side == 5 && world.func_147437_c(x - 1, y, z)) {
            rx = (double) (x + 0) - offset;
        }

        if (rx < (double) x || rx > (double) (x + 1) || ry < (double) y || ry > (double) (y + 1) || rz < (double) z || rz > (double) (z + 1)) {
            TwilightForestMod.proxy.spawnParticle(world, "leafrune", rx, ry, rz, 0.0D, 0.0D, 0.0D);
        }

    }

    public String[] func_150125_e() {
        return null;
    }
}
