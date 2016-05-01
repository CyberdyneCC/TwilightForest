package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.TFBlocks;

public class TFGenTorchBerries extends TFGenerator {

    public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z) {
        int copyX = x;

        for (int copyZ = z; y > 5; --y) {
            if (par1World.func_147437_c(x, y, z) && BlockTFPlant.canPlaceRootBelow(par1World, x, y + 1, z) && par2Random.nextInt(6) > 0) {
                par1World.func_147465_d(x, y, z, TFBlocks.plant, 13, 2);
                break;
            }

            x = copyX + par2Random.nextInt(4) - par2Random.nextInt(4);
            z = copyZ + par2Random.nextInt(4) - par2Random.nextInt(4);
        }

        return true;
    }
}
