package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.entity.EntityTFTowerTermite;
import twilightforest.item.TFItems;

public class BlockTFTowerWood extends Block {

    private static IIcon TEX_PLAIN;
    private static IIcon TEX_ENCASED;
    private static IIcon TEX_CRACKED;
    private static IIcon TEX_MOSSY;
    private static IIcon TEX_INFESTED;
    public static final int META_INFESTED = 4;

    public BlockTFTowerWood() {
        super(Material.field_151575_d);
        this.func_149711_c(40.0F);
        this.func_149752_b(10.0F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z);

        if (meta != 0 && meta != 2 && meta != 3 && meta != 4) {
            return 16777215;
        } else {
            int value = x * 31 + y * 15 + z * 33;

            if ((value & 256) != 0) {
                value = 255 - (value & 255);
            }

            value &= 255;
            value >>= 1;
            value |= 128;
            return value << 16 | value << 8 | value;
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFTowerWood.TEX_PLAIN;

        case 1:
            return BlockTFTowerWood.TEX_ENCASED;

        case 2:
            return BlockTFTowerWood.TEX_CRACKED;

        case 3:
            return BlockTFTowerWood.TEX_MOSSY;

        case 4:
            return BlockTFTowerWood.TEX_INFESTED;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFTowerWood.TEX_PLAIN = par1IconRegister.func_94245_a("TwilightForest:towerwood_planks");
        BlockTFTowerWood.TEX_ENCASED = par1IconRegister.func_94245_a("TwilightForest:towerwood_encased");
        BlockTFTowerWood.TEX_CRACKED = par1IconRegister.func_94245_a("TwilightForest:towerwood_cracked");
        BlockTFTowerWood.TEX_MOSSY = par1IconRegister.func_94245_a("TwilightForest:towerwood_mossy");
        BlockTFTowerWood.TEX_INFESTED = par1IconRegister.func_94245_a("TwilightForest:towerwood_infested");
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return meta == 4 ? 0 : super.quantityDropped(meta, fortune, random);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return meta == 4 ? 0.75F : super.func_149712_f(world, x, y, z);
    }

    public void func_149690_a(World par1World, int x, int y, int z, int meta, float chance, int something) {
        if (!par1World.field_72995_K && meta == 4) {
            EntityTFTowerTermite termite = new EntityTFTowerTermite(par1World);

            termite.func_70012_b((double) x + 0.5D, (double) y, (double) z + 0.5D, 0.0F, 0.0F);
            par1World.func_72838_d(termite);
            termite.func_70656_aK();
        }

        super.func_149690_a(par1World, x, y, z, meta, chance, something);
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 1;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 0;
    }
}
