package twilightforest.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFBurntThorns extends BlockTFThorns {

    protected BlockTFBurntThorns() {
        this.func_149711_c(0.01F);
        this.func_149752_b(0.0F);
        this.func_149672_a(BlockTFBurntThorns.field_149776_m);
        this.func_149647_a(TFItems.creativeTab);
        this.setNames(new String[] { "burnt"});
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            int metadata = world.func_72805_g(x, y, z);

            world.func_72926_e(2001, x, y, z, Block.func_149682_b(this) + (metadata << 12));
            world.func_147468_f(x, y, z);
        }

    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
        world.func_147468_f(x, y, z);
        return true;
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public void func_149749_a(World world, int x, int y, int z, Block logBlock, int metadata) {}
}
