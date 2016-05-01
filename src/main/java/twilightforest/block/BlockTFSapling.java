package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.item.TFItems;
import twilightforest.world.TFGenCanopyTree;
import twilightforest.world.TFGenDarkCanopyTree;
import twilightforest.world.TFGenHollowTree;
import twilightforest.world.TFGenLargeRainboak;
import twilightforest.world.TFGenMangroveTree;
import twilightforest.world.TFGenMinersTree;
import twilightforest.world.TFGenSmallRainboak;
import twilightforest.world.TFGenSmallTwilightOak;
import twilightforest.world.TFGenSortingTree;
import twilightforest.world.TFGenTreeOfTime;
import twilightforest.world.TFGenTreeOfTransformation;

public class BlockTFSapling extends BlockSapling {

    private IIcon[] icons;
    private String[] iconNames = new String[] { "sapling_oak", "sapling_canopy", "sapling_mangrove", "sapling_darkwood", "sapling_hollow_oak", "sapling_time", "sapling_transformation", "sapling_mining", "sapling_sorting", "sapling_rainboak"};

    protected BlockTFSapling() {
        float f = 0.4F;

        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public void func_149674_a(World par1World, int x, int y, int z, Random par5Random) {
        if (!par1World.field_72995_K && par1World.func_72957_l(x, y + 1, z) >= 9 && par5Random.nextInt(7) == 0) {
            this.func_149878_d(par1World, x, y, z, par5Random);
        }

    }

    public void func_149878_d(World world, int x, int y, int z, Random rand) {
        int meta = world.func_72805_g(x, y, z);
        Object treeGenerator = null;
        byte b0 = 0;
        byte b1 = 0;
        boolean largeTree = false;

        if (meta == 1) {
            treeGenerator = new TFGenCanopyTree(true);
        } else if (meta == 2) {
            treeGenerator = new TFGenMangroveTree(true);
        } else if (meta == 3) {
            treeGenerator = new TFGenDarkCanopyTree(true);
        } else if (meta == 4) {
            treeGenerator = new TFGenHollowTree(true);
        } else if (meta == 5) {
            treeGenerator = new TFGenTreeOfTime(true);
        } else if (meta == 6) {
            treeGenerator = new TFGenTreeOfTransformation(true);
        } else if (meta == 7) {
            treeGenerator = new TFGenMinersTree(true);
        } else if (meta == 8) {
            treeGenerator = new TFGenSortingTree(true);
        } else if (meta == 9) {
            treeGenerator = rand.nextInt(7) == 0 ? new TFGenLargeRainboak(true) : new TFGenSmallRainboak(true);
        } else {
            treeGenerator = new TFGenSmallTwilightOak(true);
        }

        if (largeTree) {
            world.func_147465_d(x + b0, y, z + b1, Blocks.field_150350_a, 0, 4);
            world.func_147465_d(x + b0 + 1, y, z + b1, Blocks.field_150350_a, 0, 4);
            world.func_147465_d(x + b0, y, z + b1 + 1, Blocks.field_150350_a, 0, 4);
            world.func_147465_d(x + b0 + 1, y, z + b1 + 1, Blocks.field_150350_a, 0, 4);
        } else {
            world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 4);
        }

        if (!((WorldGenerator) treeGenerator).func_76484_a(world, rand, x + b0, y, z + b1)) {
            if (largeTree) {
                world.func_147465_d(x + b0, y, z + b1, this, meta, 4);
                world.func_147465_d(x + b0 + 1, y, z + b1, this, meta, 4);
                world.func_147465_d(x + b0, y, z + b1 + 1, this, meta, 4);
                world.func_147465_d(x + b0 + 1, y, z + b1 + 1, this, meta, 4);
            } else {
                world.func_147465_d(x, y, z, this, meta, 4);
            }
        }

    }

    public IIcon func_149691_a(int side, int metadata) {
        return metadata < this.icons.length ? this.icons[metadata] : null;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.iconNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = par1IconRegister.func_94245_a("TwilightForest:" + this.iconNames[i]);
        }

    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
    }
}
