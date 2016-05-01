package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import twilightforest.item.TFItems;

public class BlockTFLog extends BlockLog {

    public static IIcon sprOakSide;
    public static IIcon sprOakTop;
    public static IIcon sprCanopySide;
    public static IIcon sprCanopyTop;
    public static IIcon sprMangroveSide;
    public static IIcon sprMangroveTop;
    public static IIcon sprDarkwoodSide;
    public static IIcon sprDarkwoodTop;

    protected BlockTFLog() {
        this.func_149711_c(2.0F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        int orient = meta & 12;
        int woodType = meta & 3;

        switch (woodType) {
        case 0:
        default:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFLog.sprOakTop : (orient == 4 && (side == 5 || side == 4) ? BlockTFLog.sprOakTop : (orient == 8 && (side == 2 || side == 3) ? BlockTFLog.sprOakTop : BlockTFLog.sprOakSide));

        case 1:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFLog.sprCanopyTop : (orient == 4 && (side == 5 || side == 4) ? BlockTFLog.sprCanopyTop : (orient == 8 && (side == 2 || side == 3) ? BlockTFLog.sprCanopyTop : BlockTFLog.sprCanopySide));

        case 2:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFLog.sprMangroveTop : (orient == 4 && (side == 5 || side == 4) ? BlockTFLog.sprMangroveTop : (orient == 8 && (side == 2 || side == 3) ? BlockTFLog.sprMangroveTop : BlockTFLog.sprMangroveSide));

        case 3:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFLog.sprDarkwoodTop : (orient == 4 && (side == 5 || side == 4) ? BlockTFLog.sprDarkwoodTop : (orient == 8 && (side == 2 || side == 3) ? BlockTFLog.sprDarkwoodTop : BlockTFLog.sprDarkwoodSide));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFLog.sprOakSide = par1IconRegister.func_94245_a("TwilightForest:oak_side");
        BlockTFLog.sprOakTop = par1IconRegister.func_94245_a("TwilightForest:oak_top");
        BlockTFLog.sprCanopySide = par1IconRegister.func_94245_a("TwilightForest:canopy_side");
        BlockTFLog.sprCanopyTop = par1IconRegister.func_94245_a("TwilightForest:canopy_top");
        BlockTFLog.sprMangroveSide = par1IconRegister.func_94245_a("TwilightForest:mangrove_side");
        BlockTFLog.sprMangroveTop = par1IconRegister.func_94245_a("TwilightForest:mangrove_top");
        BlockTFLog.sprDarkwoodSide = par1IconRegister.func_94245_a("TwilightForest:darkwood_side");
        BlockTFLog.sprDarkwoodTop = par1IconRegister.func_94245_a("TwilightForest:darkwood_top");
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a(TFBlocks.log);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
}
