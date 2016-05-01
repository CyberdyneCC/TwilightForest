package twilightforest.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import twilightforest.item.TFItems;

public class BlockTFTrollRoot extends Block implements IShearable {

    protected BlockTFTrollRoot() {
        super(Material.field_151585_k);
        this.func_149675_a(true);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149672_a(BlockTFTrollRoot.field_149779_h);
        this.func_149658_d("TwilightForest:troll_root");
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList ret = new ArrayList();

        ret.add(new ItemStack(this));
        return ret;
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        return canPlaceRootBelow(world, x, y + 1, z);
    }

    public static boolean canPlaceRootBelow(World world, int x, int y, int z) {
        Block blockAbove = world.func_147439_a(x, y, z);

        return blockAbove.func_149688_o() == Material.field_151576_e || blockAbove == TFBlocks.trollVidr || blockAbove == TFBlocks.trollBer || blockAbove == TFBlocks.unripeTrollBer;
    }

    public boolean func_149742_c(World world, int x, int y, int z) {
        return super.func_149742_c(world, x, y, z) && this.func_149718_j(world, x, y, z);
    }

    public AxisAlignedBB func_149668_a(World par1World, int x, int y, int z) {
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 1;
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        this.checkAndDropBlock(world, x, y, z);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        this.checkAndDropBlock(world, x, y, z);
    }

    protected void checkAndDropBlock(World world, int x, int y, int z) {
        if (!this.func_149718_j(world, x, y, z)) {
            this.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
            world.func_147468_f(x, y, z);
        }

    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }
}
