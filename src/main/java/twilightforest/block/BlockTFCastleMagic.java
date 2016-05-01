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
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFCastleMagic extends Block {

    public static IIcon[] magicIcons = new IIcon[8];
    public static int[] magicColors = new int[] { 16711935, '\uffff', 16776960, 4915330};

    public BlockTFCastleMagic() {
        super(Material.field_151576_e);
        this.func_149711_c(100.0F);
        this.func_149752_b(15.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("TwilightForest:castleblock_brick");

        for (int i = 0; i < 8; ++i) {
            BlockTFCastleMagic.magicIcons[i] = par1IconRegister.func_94245_a("TwilightForest:castleblock_magic_" + i);
        }

    }

    public static IIcon getMagicIconFor(int x, int y, int z) {
        long seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;

        seed = seed * seed * 42317861L + seed * 11L;
        int index = (int) (seed >> 12 & 7L);

        return BlockTFCastleMagic.magicIcons[index];
    }

    public static int getMagicColorFor(int meta) {
        return BlockTFCastleMagic.magicColors[meta & 3];
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getCastleMagicBlockRenderID();
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public int func_149692_a(int meta) {
        return meta;
    }
}
