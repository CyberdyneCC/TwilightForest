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

public class BlockTFMazestone extends Block {

    private static IIcon TEX_PLAIN;
    private static IIcon TEX_BRICK;
    private static IIcon TEX_PILLAR;
    private static IIcon TEX_DECO;
    private static IIcon TEX_CRACKED;
    private static IIcon TEX_MOSSY;
    private static IIcon TEX_MOSAIC;
    private static IIcon TEX_BORDER;

    public BlockTFMazestone() {
        super(Material.field_151576_e);
        this.func_149711_c(100.0F);
        this.func_149752_b(5.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFMazestone.TEX_PLAIN;

        case 1:
            return BlockTFMazestone.TEX_BRICK;

        case 2:
            return side > 1 ? BlockTFMazestone.TEX_PILLAR : BlockTFMazestone.TEX_PLAIN;

        case 3:
            return side > 1 ? BlockTFMazestone.TEX_DECO : BlockTFMazestone.TEX_BRICK;

        case 4:
            return BlockTFMazestone.TEX_CRACKED;

        case 5:
            return BlockTFMazestone.TEX_MOSSY;

        case 6:
            return side > 1 ? BlockTFMazestone.TEX_BRICK : BlockTFMazestone.TEX_MOSAIC;

        case 7:
            return side > 1 ? BlockTFMazestone.TEX_BRICK : BlockTFMazestone.TEX_BORDER;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFMazestone.TEX_PLAIN = par1IconRegister.func_94245_a("TwilightForest:mazestone_plain");
        BlockTFMazestone.TEX_BRICK = par1IconRegister.func_94245_a("TwilightForest:mazestone_brick");
        BlockTFMazestone.TEX_PILLAR = par1IconRegister.func_94245_a("TwilightForest:mazestone_pillar");
        BlockTFMazestone.TEX_DECO = par1IconRegister.func_94245_a("TwilightForest:mazestone_decorative");
        BlockTFMazestone.TEX_CRACKED = par1IconRegister.func_94245_a("TwilightForest:mazestone_cracked");
        BlockTFMazestone.TEX_MOSSY = par1IconRegister.func_94245_a("TwilightForest:mazestone_mossy");
        BlockTFMazestone.TEX_MOSAIC = par1IconRegister.func_94245_a("TwilightForest:mazestone_mosaic");
        BlockTFMazestone.TEX_BORDER = par1IconRegister.func_94245_a("TwilightForest:mazestone_border");
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
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    public int func_149692_a(int meta) {
        return meta;
    }
}
