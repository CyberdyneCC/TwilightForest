package twilightforest.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.item.TFItems;

public class BlockTFThornRose extends Block {

    protected BlockTFThornRose() {
        super(Material.field_151585_k);
        this.func_149711_c(10.0F);
        this.func_149672_a(BlockTFThornRose.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
        float radius = 0.4F;

        this.func_149676_a(0.5F - radius, 0.5F - radius, 0.5F - radius, 0.5F + radius, 0.5F + radius, 0.5F + radius);
    }

    public int func_149645_b() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149742_c(World world, int x, int y, int z) {
        return this.func_149718_j(world, x, y, z);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (!this.func_149718_j(world, x, y, z)) {
            this.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
            world.func_147468_f(x, y, z);
        }

    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        boolean supported = false;
        ForgeDirection[] aforgedirection = ForgeDirection.VALID_DIRECTIONS;
        int i = aforgedirection.length;

        for (int j = 0; j < i; ++j) {
            ForgeDirection dir = aforgedirection[j];
            int dx = x + dir.offsetX;
            int dy = y + dir.offsetY;
            int dz = z + dir.offsetZ;

            if (world.func_147439_a(dx, dy, dz).canSustainLeaves(world, dx, dy, dz)) {
                supported = true;
            }
        }

        return supported;
    }
}
