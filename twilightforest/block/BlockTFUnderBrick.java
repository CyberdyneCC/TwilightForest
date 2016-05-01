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
import twilightforest.item.TFItems;

public class BlockTFUnderBrick extends Block {

    private static IIcon[] iconSide = new IIcon[4];

    public BlockTFUnderBrick() {
        super(Material.field_151576_e);
        this.func_149711_c(1.5F);
        this.func_149752_b(10.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFUnderBrick.iconSide[0] = par1IconRegister.func_94245_a("TwilightForest:knightbrick");
        BlockTFUnderBrick.iconSide[1] = par1IconRegister.func_94245_a("TwilightForest:knightbrick_mossy");
        BlockTFUnderBrick.iconSide[2] = par1IconRegister.func_94245_a("TwilightForest:knightbrick_cracked");
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta < BlockTFUnderBrick.iconSide.length ? (side != 0 && side != 1 ? BlockTFUnderBrick.iconSide[meta] : BlockTFUnderBrick.iconSide[meta]) : (side != 0 && side != 1 ? BlockTFUnderBrick.iconSide[0] : BlockTFUnderBrick.iconSide[0]);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        par1IBlockAccess.func_72805_g(x, y, z);
        return 16777215;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149692_a(int meta) {
        return meta;
    }
}
