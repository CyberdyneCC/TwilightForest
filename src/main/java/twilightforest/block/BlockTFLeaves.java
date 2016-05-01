package twilightforest.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFLeaves extends BlockLeaves {

    int oakColor = 4764952;
    int canopyColor = 6330464;
    int mangroveColor = 8431445;
    public static final String[] unlocalizedNameArray = new String[] { "twilightoak", "canopy", "mangrove", "rainboak"};

    protected BlockTFLeaves() {
        this.func_149711_c(0.2F);
        this.func_149713_g(2);
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149635_D() {
        double d0 = 0.5D;
        double d1 = 1.0D;

        return ColorizerFoliage.func_77470_a(d0, d1);
    }

    public int func_149741_i(int par1) {
        return (par1 & 3) == 1 ? this.canopyColor : ((par1 & 3) == 2 ? this.mangroveColor : this.oakColor);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z);
        int red = 0;
        int green = 0;
        int blue = 0;

        int normalColor;

        for (normalColor = -1; normalColor <= 1; ++normalColor) {
            for (int i = -1; i <= 1; ++i) {
                int j = par1IBlockAccess.func_72807_a(x + i, z + normalColor).func_150571_c(x, y, z);

                red += (j & 16711680) >> 16;
                green += (j & '\uff00') >> 8;
                blue += j & 255;
            }
        }

        normalColor = (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
        if ((meta & 3) == 1) {
            return ((normalColor & 16711422) + 4627046) / 2;
        } else if ((meta & 3) == 2) {
            return ((normalColor & 16711422) + 12641940) / 2;
        } else if ((meta & 3) == 3) {
            red = x * 32 + y * 16;
            if ((red & 256) != 0) {
                red = 255 - (red & 255);
            }

            red &= 255;
            blue = y * 32 + z * 16;
            if ((blue & 256) != 0) {
                blue = 255 - (blue & 255);
            }

            blue ^= 255;
            green = x * 16 + z * 32;
            if ((green & 256) != 0) {
                green = 255 - (green & 255);
            }

            green &= 255;
            return red << 16 | blue << 8 | green;
        } else {
            return normalColor;
        }
    }

    public boolean func_149662_c() {
        return Blocks.field_150362_t.func_149662_c();
    }

    public IIcon func_149691_a(int i, int j) {
        return Blocks.field_150362_t.func_149691_a(i, (j & 3) == 3 ? 0 : j);
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return Blocks.field_150362_t.func_149646_a(iblockaccess, i, j, k, l);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(this, 1, 2));
        par3List.add(new ItemStack(this, 1, 3));
    }

    public int func_149745_a(Random par1Random) {
        return par1Random.nextInt(40) == 0 ? 1 : 0;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a(TFBlocks.sapling);
    }

    public int func_149692_a(int par1) {
        int leafType = par1 & 3;

        return leafType;
    }

    public void func_149690_a(World par1World, int par2, int par3, int par4, int meta, float par6, int par7) {
        if (!par1World.field_72995_K) {
            byte chance = 40;

            if ((meta & 3) == 2) {
                chance = 20;
            }

            if (par1World.field_73012_v.nextInt(chance) == 0) {
                Item item = this.func_149650_a(meta, par1World.field_73012_v, par7);

                this.func_149642_a(par1World, par2, par3, par4, new ItemStack(item, 1, this.getSaplingMeta(meta)));
            }
        }

    }

    public int getSaplingMeta(int leafMeta) {
        int leafType = leafMeta & 3;

        return leafType == 3 ? 9 : leafType;
    }

    public String[] func_150125_e() {
        return BlockTFLeaves.unlocalizedNameArray;
    }
}
