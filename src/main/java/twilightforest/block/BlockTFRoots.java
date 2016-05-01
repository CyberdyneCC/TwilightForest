package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import twilightforest.item.TFItems;

public class BlockTFRoots extends Block {

    public static IIcon spRootSide;
    public static IIcon spOreRootSide;
    public static final int ROOT_META = 0;
    public static final int OREROOT_META = 1;

    public BlockTFRoots() {
        super(Material.field_151575_d);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(2.0F);
        this.func_149672_a(Block.field_149766_f);
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta == 1 ? BlockTFRoots.spOreRootSide : BlockTFRoots.spRootSide;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFRoots.spRootSide = par1IconRegister.func_94245_a("TwilightForest:rootblock");
        BlockTFRoots.spOreRootSide = par1IconRegister.func_94245_a("TwilightForest:oreroots");
    }

    public Item func_149650_a(int meta, Random random, int j) {
        switch (meta) {
        case 0:
            return Items.field_151055_y;

        case 1:
            return TFItems.liveRoot;

        default:
            return Item.func_150898_a(this);
        }
    }

    public int func_149692_a(int meta) {
        switch (meta) {
        case 0:
            return 0;

        case 1:
            return 0;

        default:
            return meta | 8;
        }
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        switch (meta) {
        case 0:
            return 3 + random.nextInt(2);

        default:
            return super.quantityDropped(meta, fortune, random);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }
}
