package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFUncraftingTable extends Block {

    public static IIcon tinkerTop;
    public static IIcon tinkerSide;

    protected BlockTFUncraftingTable() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == 1 ? BlockTFUncraftingTable.tinkerTop : BlockTFUncraftingTable.tinkerSide;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFUncraftingTable.tinkerTop = par1IconRegister.func_94245_a("TwilightForest:uncrafting_top");
        BlockTFUncraftingTable.tinkerSide = par1IconRegister.func_94245_a("TwilightForest:uncrafting_side");
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        player.openGui(TwilightForestMod.instance, 1, world, x, y, z);
        return true;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
}
