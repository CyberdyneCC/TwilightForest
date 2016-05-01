package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TFGenStoneCircle extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        if (!this.isAreaClear(world, rand, x - 3, y, z - 3, 6, 4, 6)) {
            return false;
        } else {
            for (int cy = 0; cy <= 2; ++cy) {
                this.setBlock(world, x - 3, y + cy, z, Blocks.field_150341_Y);
                this.setBlock(world, x + 3, y + cy, z, Blocks.field_150341_Y);
                this.setBlock(world, x, y + cy, z - 3, Blocks.field_150341_Y);
                this.setBlock(world, x, y + cy, z + 3, Blocks.field_150341_Y);
                this.setBlock(world, x - 2, y + cy, z - 2, Blocks.field_150341_Y);
                this.setBlock(world, x + 2, y + cy, z - 2, Blocks.field_150341_Y);
                this.setBlock(world, x - 2, y + cy, z + 2, Blocks.field_150341_Y);
                this.setBlock(world, x + 2, y + cy, z + 2, Blocks.field_150341_Y);
            }

            return true;
        }
    }
}
