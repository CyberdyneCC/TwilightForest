package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import twilightforest.block.TFBlocks;

public class TFGenHugeLilyPad extends WorldGenerator {

    private Random rand = new Random();

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        for (int i = 0; i < 10; ++i) {
            int dx = x + random.nextInt(8) - random.nextInt(8);
            int dy = y + random.nextInt(4) - random.nextInt(4);
            int dz = z + random.nextInt(8) - random.nextInt(8);

            dx = dx >> 1 << 1;
            dz = dz >> 1 << 1;
            if (this.shouldPlacePadAt(world, dx, dy, dz)) {
                this.rand.setSeed(8890919293L);
                this.rand.setSeed((long) dx * this.rand.nextLong() ^ (long) dz * this.rand.nextLong() ^ 8890919293L);
                int orient = this.rand.nextInt(4) << 2;

                world.func_147465_d(dx, dy, dz, TFBlocks.hugeLilyPad, 0 | orient, 2);
                world.func_147465_d(dx + 1, dy, dz, TFBlocks.hugeLilyPad, 1 | orient, 2);
                world.func_147465_d(dx + 1, dy, dz + 1, TFBlocks.hugeLilyPad, 2 | orient, 2);
                world.func_147465_d(dx, dy, dz + 1, TFBlocks.hugeLilyPad, 3 | orient, 2);
            }
        }

        return true;
    }

    private boolean shouldPlacePadAt(World world, int dx, int dy, int dz) {
        return world.func_147437_c(dx, dy, dz) && world.func_147439_a(dx, dy - 1, dz).func_149688_o() == Material.field_151586_h && world.func_147437_c(dx + 1, dy, dz) && world.func_147439_a(dx + 1, dy - 1, dz).func_149688_o() == Material.field_151586_h && world.func_147437_c(dx, dy, dz + 1) && world.func_147439_a(dx, dy - 1, dz + 1).func_149688_o() == Material.field_151586_h && world.func_147437_c(dx + 1, dy, dz + 1) && world.func_147439_a(dx + 1, dy - 1, dz + 1).func_149688_o() == Material.field_151586_h;
    }
}
