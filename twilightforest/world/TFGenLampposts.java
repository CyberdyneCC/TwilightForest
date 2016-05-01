package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenLampposts extends TFGenerator {

    private static final int MAX_HANG = 8;

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        if (world.func_147439_a(x, y - 1, z) != Blocks.field_150349_c) {
            return false;
        } else {
            int height = 1 + rand.nextInt(4);
            boolean clear = true;

            int dy;

            for (dy = 0; dy < height; ++dy) {
                if (!world.func_147437_c(x, y + dy, z) && !world.func_147439_a(x, y + dy, z).isReplaceable(world, x, y + dy, z)) {
                    clear = false;
                }
            }

            if (clear) {
                for (dy = 0; dy < height; ++dy) {
                    world.func_147449_b(x, y + dy, z, Blocks.field_150422_aJ);
                    world.func_147449_b(x, y + height, z, TFBlocks.fireflyJar);
                }
            }

            return clear;
        }
    }
}
