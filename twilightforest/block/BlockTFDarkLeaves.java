package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.item.TFItems;

public class BlockTFDarkLeaves extends Block {

    protected BlockTFDarkLeaves() {
        super(Material.field_151584_j);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
        this.field_149768_d = "TwilightForest:darkwood_leaves2";
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        double d0 = 0.5D;
        double d1 = 1.0D;

        return ColorizerFoliage.func_77470_a(d0, d1);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_) {
        return ColorizerFoliage.func_77468_c();
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        int red = 0;
        int grn = 0;
        int blu = 0;

        for (int dz = -1; dz <= 1; ++dz) {
            for (int dx = -1; dx <= 1; ++dx) {
                int i2 = world.func_72807_a(x + dx, z + dz).func_150571_c(x + dx, y, z + dz);

                red += (i2 & 16711680) >> 16;
                grn += (i2 & '\uff00') >> 8;
                blu += i2 & 255;
            }
        }

        return (red / 9 & 255) << 16 | (grn / 9 & 255) << 8 | blu / 9 & 255;
    }

    public int func_149692_a(int meta) {
        return 3;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 1;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 0;
    }

    public int func_149745_a(Random par1Random) {
        return par1Random.nextInt(40) == 0 ? 1 : 0;
    }

    public Item func_149650_a(int meta, Random par2Random, int par3) {
        return Item.func_150898_a(TFBlocks.sapling);
    }

    public void func_149690_a(World par1World, int par2, int par3, int par4, int meta, float par6, int fortune) {
        if (!par1World.field_72995_K && par1World.field_73012_v.nextInt(40) == 0) {
            Item item = this.func_149650_a(meta, par1World.field_73012_v, fortune);

            this.func_149642_a(par1World, par2, par3, par4, new ItemStack(item, 1, this.func_149692_a(meta)));
        }

    }
}
