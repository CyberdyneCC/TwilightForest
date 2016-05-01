package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.TFBlocks;

public class TFGenHugeWaterLily extends WorldGenerator {

    private Random rand = new Random();

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 4; ++i) {
            int dx = x + random.nextInt(8) - random.nextInt(8);
            int dy = y + random.nextInt(4) - random.nextInt(4);
            int dz = z + random.nextInt(8) - random.nextInt(8);

            if (this.shouldPlacePadAt(world, dx, dy, dz)) {
                world.func_147449_b(dx, dy, dz, TFBlocks.hugeWaterLily);
            }
        }

        return true;
    }

    private boolean shouldPlacePadAt(World world, int dx, int dy, int dz) {
        return world.func_147437_c(dx, dy, dz) && world.func_147439_a(dx, dy - 1, dz).func_149688_o() == Material.field_151586_h;
    }
}
