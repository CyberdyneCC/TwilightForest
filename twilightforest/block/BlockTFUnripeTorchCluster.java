package twilightforest.block;

import java.util.Random;
import net.minecraft.world.World;

public class BlockTFUnripeTorchCluster extends BlockTFTrollRoot {

    private static final int RIPEN_THRESHHOLD = 6;

    protected BlockTFUnripeTorchCluster() {
        this.func_149658_d("TwilightForest:unripe_torch_cluster");
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        super.func_149674_a(world, x, y, z, rand);
        if (world.func_72957_l(x, y, z) >= 6) {
            world.func_147449_b(x, y, z, TFBlocks.trollBer);
        }

    }
}
