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
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFMagicLog extends BlockLog {

    public static final int META_TIME = 0;
    public static final int META_TRANS = 1;
    public static final int META_MINE = 2;
    public static final int META_SORT = 3;
    public static IIcon SPR_TIMESIDE;
    public static IIcon SPR_TIMETOP;
    public static IIcon SPR_TIMECLOCK;
    public static IIcon SPR_TIMECLOCKOFF;
    public static IIcon SPR_TRANSSIDE;
    public static IIcon SPR_TRANSTOP;
    public static IIcon SPR_TRANSHEART;
    public static IIcon SPR_TRANSHEARTOFF;
    public static IIcon SPR_MINESIDE;
    public static IIcon SPR_MINETOP;
    public static IIcon SPR_MINEGEM;
    public static IIcon SPR_MINEGEMOFF;
    public static IIcon SPR_SORTSIDE;
    public static IIcon SPR_SORTTOP;
    public static IIcon SPR_SORTEYE;
    public static IIcon SPR_SORTEYEOFF;

    protected BlockTFMagicLog() {
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
            return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLog.SPR_TIMETOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLog.SPR_TIMETOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLog.SPR_TIMETOP : BlockTFMagicLog.SPR_TIMESIDE));

        case 1:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLog.SPR_TRANSTOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLog.SPR_TRANSTOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLog.SPR_TRANSTOP : BlockTFMagicLog.SPR_TRANSSIDE));

        case 2:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLog.SPR_MINETOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLog.SPR_MINETOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLog.SPR_MINETOP : BlockTFMagicLog.SPR_MINESIDE));

        case 3:
            return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLog.SPR_SORTTOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLog.SPR_SORTTOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLog.SPR_SORTTOP : BlockTFMagicLog.SPR_SORTSIDE));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFMagicLog.SPR_TIMESIDE = par1IconRegister.func_94245_a("TwilightForest:time_side");
        BlockTFMagicLog.SPR_TIMETOP = par1IconRegister.func_94245_a("TwilightForest:time_section");
        BlockTFMagicLog.SPR_TIMECLOCK = par1IconRegister.func_94245_a("TwilightForest:time_clock");
        BlockTFMagicLog.SPR_TIMECLOCKOFF = par1IconRegister.func_94245_a("TwilightForest:time_clock_off");
        BlockTFMagicLog.SPR_TRANSSIDE = par1IconRegister.func_94245_a("TwilightForest:trans_side");
        BlockTFMagicLog.SPR_TRANSTOP = par1IconRegister.func_94245_a("TwilightForest:trans_section");
        BlockTFMagicLog.SPR_TRANSHEART = par1IconRegister.func_94245_a("TwilightForest:trans_heart");
        BlockTFMagicLog.SPR_TRANSHEARTOFF = par1IconRegister.func_94245_a("TwilightForest:trans_heart_off");
        BlockTFMagicLog.SPR_MINESIDE = par1IconRegister.func_94245_a("TwilightForest:mine_side");
        BlockTFMagicLog.SPR_MINETOP = par1IconRegister.func_94245_a("TwilightForest:mine_section");
        BlockTFMagicLog.SPR_MINEGEM = par1IconRegister.func_94245_a("TwilightForest:mine_gem");
        BlockTFMagicLog.SPR_MINEGEMOFF = par1IconRegister.func_94245_a("TwilightForest:mine_gem_off");
        BlockTFMagicLog.SPR_SORTSIDE = par1IconRegister.func_94245_a("TwilightForest:sort_side");
        BlockTFMagicLog.SPR_SORTTOP = par1IconRegister.func_94245_a("TwilightForest:sort_section");
        BlockTFMagicLog.SPR_SORTEYE = par1IconRegister.func_94245_a("TwilightForest:sort_eye");
        BlockTFMagicLog.SPR_SORTEYEOFF = par1IconRegister.func_94245_a("TwilightForest:sort_eye_off");
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a(this);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {}

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
}
