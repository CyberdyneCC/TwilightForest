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
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.item.ItemTFMazebreakerPick;
import twilightforest.item.TFItems;

public class BlockTFCastleBlock extends Block {

    private static IIcon brickIcon;
    private static IIcon crackIcon;
    private static IIcon fadedIcon;
    private static IIcon roofIcon;

    public BlockTFCastleBlock() {
        super(Material.field_151576_e);
        this.func_149711_c(100.0F);
        this.func_149752_b(15.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFCastleBlock.brickIcon;

        case 1:
            return BlockTFCastleBlock.fadedIcon;

        case 2:
            return BlockTFCastleBlock.crackIcon;

        case 3:
            return BlockTFCastleBlock.roofIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFCastleBlock.brickIcon = par1IconRegister.func_94245_a("TwilightForest:castleblock_brick");
        BlockTFCastleBlock.fadedIcon = par1IconRegister.func_94245_a("TwilightForest:castleblock_faded");
        BlockTFCastleBlock.crackIcon = par1IconRegister.func_94245_a("TwilightForest:castleblock_cracked");
        BlockTFCastleBlock.roofIcon = par1IconRegister.func_94245_a("TwilightForest:castleblock_roof");
    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {
        ItemStack cei = entityplayer.func_71045_bC();

        if (cei != null && cei.func_77973_b() instanceof ItemTool && !(cei.func_77973_b() instanceof ItemTFMazebreakerPick)) {
            cei.func_77972_a(16, entityplayer);
        }

        super.func_149636_a(world, entityplayer, x, y, z, meta);
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
