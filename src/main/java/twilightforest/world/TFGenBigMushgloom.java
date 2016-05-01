package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenBigMushgloom extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int height = 3 + rand.nextInt(2) + rand.nextInt(2);

        if (!this.isAreaClear(world, rand, x - 1, y, z - 1, 3, height, 3)) {
            return false;
        } else {
            Block blockUnder = world.func_147439_a(x, y - 1, z);

            if (blockUnder != Blocks.field_150346_d && blockUnder != Blocks.field_150349_c && blockUnder != Blocks.field_150391_bh) {
                return false;
            } else {
                for (int dy = 0; dy < height - 2; ++dy) {
                    this.func_150516_a(world, x, y + dy, z, TFBlocks.hugeGloomBlock, 10);
                }

                this.makeMushroomCap(world, x, z, y + (height - 2));
                if (rand.nextBoolean()) {
                    this.makeMushroomCap(world, x, z, y + (height - 1));
                }

                return true;
            }
        }
    }

    private void makeMushroomCap(World world, int x, int z, int dy) {
        this.func_150516_a(world, x - 1, dy, z - 1, TFBlocks.hugeGloomBlock, 1);
        this.func_150516_a(world, x + 0, dy, z - 1, TFBlocks.hugeGloomBlock, 2);
        this.func_150516_a(world, x + 1, dy, z - 1, TFBlocks.hugeGloomBlock, 3);
        this.func_150516_a(world, x - 1, dy, z + 0, TFBlocks.hugeGloomBlock, 4);
        this.func_150516_a(world, x + 0, dy, z + 0, TFBlocks.hugeGloomBlock, 5);
        this.func_150516_a(world, x + 1, dy, z + 0, TFBlocks.hugeGloomBlock, 6);
        this.func_150516_a(world, x - 1, dy, z + 1, TFBlocks.hugeGloomBlock, 7);
        this.func_150516_a(world, x + 0, dy, z + 1, TFBlocks.hugeGloomBlock, 8);
        this.func_150516_a(world, x + 1, dy, z + 1, TFBlocks.hugeGloomBlock, 9);
    }
}
