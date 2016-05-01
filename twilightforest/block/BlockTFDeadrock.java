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
import twilightforest.item.TFItems;

public class BlockTFDeadrock extends Block {

    public static final String[] names = new String[] { "surface", "cracked", "solid"};
    private IIcon[] icons;

    protected BlockTFDeadrock() {
        super(Material.field_151576_e);
        this.func_149711_c(100.0F);
        this.func_149752_b(6000000.0F);
        this.func_149672_a(BlockTFDeadrock.field_149780_i);
        this.func_149649_H();
        this.func_149647_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icons = new IIcon[BlockTFDeadrock.names.length];

        for (int i = 0; i < BlockTFDeadrock.names.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a("TwilightForest:deadrock_" + BlockTFDeadrock.names[i]);
        }

    }

    public IIcon func_149691_a(int side, int meta) {
        if (meta > BlockTFDeadrock.names.length) {
            meta = 0;
        }

        return this.icons[meta];
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < BlockTFDeadrock.names.length; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    public int func_149692_a(int meta) {
        return meta;
    }
}
