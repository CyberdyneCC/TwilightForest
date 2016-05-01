package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import twilightforest.item.TFItems;

public class BlockTFAuroraSlab extends BlockSlab {

    private IIcon sideIcon;

    public BlockTFAuroraSlab(boolean isDouble) {
        super(isDouble, Material.field_151598_x);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
        this.func_149713_g(isDouble ? 255 : 0);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        return TFBlocks.auroraPillar.func_149720_d(par1IBlockAccess, -x, y, -z);
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return this.func_149720_d((IBlockAccess) null, 0, 0, 16);
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int meta) {
        return this.func_149635_D();
    }

    public String func_150002_b(int i) {
        return super.func_149739_a();
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side != 0 && side != 1 ? this.sideIcon : this.field_149761_L;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.field_149761_L = TFBlocks.auroraPillar.func_149691_a(0, 0);
        this.sideIcon = iconRegister.func_94245_a("TwilightForest:aurora_slab_side");
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a(TFBlocks.auroraSlab);
    }

    protected ItemStack func_149644_j(int meta) {
        return new ItemStack(Item.func_150898_a(TFBlocks.auroraSlab), 2, 0);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        if (this.field_150004_a) {
            return super.func_149646_a(world, x, y, z, side);
        } else if (side != 1 && side != 0 && !super.func_149646_a(world, x, y, z, side)) {
            return false;
        } else {
            int i1 = x + Facing.field_71586_b[Facing.field_71588_a[side]];
            int j1 = y + Facing.field_71587_c[Facing.field_71588_a[side]];
            int k1 = z + Facing.field_71585_d[Facing.field_71588_a[side]];
            boolean flag = (world.func_72805_g(i1, j1, k1) & 8) != 0;

            return flag ? (side == 0 ? true : (side == 1 && super.func_149646_a(world, x, y, z, side) ? true : !isSingleSlab(world.func_147439_a(x, y, z)) || (world.func_72805_g(x, y, z) & 8) == 0)) : (side == 1 ? true : (side == 0 && super.func_149646_a(world, x, y, z, side) ? true : !isSingleSlab(world.func_147439_a(x, y, z)) || (world.func_72805_g(x, y, z) & 8) != 0));
        }
    }

    @SideOnly(Side.CLIENT)
    private static boolean isSingleSlab(Block p_150003_0_) {
        return p_150003_0_ == TFBlocks.auroraSlab;
    }
}
