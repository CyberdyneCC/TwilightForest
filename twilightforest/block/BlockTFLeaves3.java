package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class BlockTFLeaves3 extends BlockLeaves {

    public static final String[] names = new String[] { "thorn", "beanstalk"};

    protected BlockTFLeaves3() {
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public String[] func_150125_e() {
        return BlockTFLeaves3.names;
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int meta) {
        return (meta & 3) == 1 ? ColorizerFoliage.func_77466_a() : ((meta & 3) == 2 ? ColorizerFoliage.func_77469_b() : super.func_149741_i(meta));
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return (meta & 3) == 1 ? ColorizerFoliage.func_77466_a() : ((meta & 3) == 2 ? ColorizerFoliage.func_77469_b() : super.func_149720_d(world, x, y, z));
    }

    public int func_149692_a(int p_149692_1_) {
        return super.func_149692_a(p_149692_1_) + 4;
    }

    public boolean func_149662_c() {
        return Blocks.field_150362_t.func_149662_c();
    }

    public int func_149643_k(World world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) & 3;
    }

    public int func_149745_a(Random rand) {
        return 0;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        return Blocks.field_150362_t.func_149646_a(iblockaccess, i, j, k, side);
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return null;
    }

    public IIcon func_149691_a(int i, int j) {
        return Blocks.field_150362_t.func_149691_a(i, 0);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < BlockTFLeaves3.names.length; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
