package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFAuroraBrick extends Block {

    private static IIcon[] icons;

    public BlockTFAuroraBrick() {
        super(Material.field_151598_x);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFAuroraBrick.icons = new IIcon[8];
        BlockTFAuroraBrick.icons[0] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick0");
        BlockTFAuroraBrick.icons[1] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick1");
        BlockTFAuroraBrick.icons[2] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick2");
        BlockTFAuroraBrick.icons[3] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick3");
        BlockTFAuroraBrick.icons[4] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick4");
        BlockTFAuroraBrick.icons[5] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick5");
        BlockTFAuroraBrick.icons[6] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick6");
        BlockTFAuroraBrick.icons[7] = par1IconRegister.func_94245_a("TwilightForest:aurorabrick7");
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta < 8 ? BlockTFAuroraBrick.icons[meta] : BlockTFAuroraBrick.icons[15 - meta];
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        byte red1 = 16;
        int blue1 = x * 12 + z * 6;

        if ((blue1 & 256) != 0) {
            blue1 = 255 - (blue1 & 255);
        }

        blue1 ^= 255;
        blue1 &= 255;
        int green1 = x * 4 + z * 8;

        if ((green1 & 256) != 0) {
            green1 = 255 - (green1 & 255);
        }

        green1 &= 255;
        if (green1 + blue1 < 128) {
            green1 = 128 - blue1;
        }

        return red1 << 16 | blue1 << 8 | green1;
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return this.func_149720_d((IBlockAccess) null, 16, 0, 16);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int meta) {
        return this.func_149635_D();
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149692_a(int meta) {
        return 0;
    }

    public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return Math.abs(x + z) % 16;
    }

    public void func_149726_b(World world, int x, int y, int z) {
        world.func_72921_c(x, y, z, Math.abs(x + z) % 16, 2);
    }
}
