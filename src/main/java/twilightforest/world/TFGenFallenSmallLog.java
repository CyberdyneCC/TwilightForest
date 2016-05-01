package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenFallenSmallLog extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        boolean goingX = rand.nextBoolean();
        int length = rand.nextInt(4) + 3;

        if (goingX) {
            if (!this.isAreaClear(world, rand, x, y, z, length, 3, 2)) {
                return false;
            }
        } else if (!this.isAreaClear(world, rand, x, y, z, 3, length, 2)) {
            return false;
        }

        Block logID;
        boolean logMeta;

        switch (rand.nextInt(7)) {
        case 0:
        default:
            logID = TFBlocks.log;
            logMeta = false;

        case 1:
            logID = TFBlocks.log;
            logMeta = true;

        case 2:
            logID = TFBlocks.log;
            logMeta = true;

        case 3:
            logID = Blocks.field_150364_r;
            logMeta = false;

        case 4:
            logID = Blocks.field_150364_r;
            logMeta = true;

        case 5:
            logID = Blocks.field_150364_r;
            logMeta = true;

        case 6:
        }

        logID = Blocks.field_150364_r;
        byte b0 = 3;
        int bx;
        int i;
        int j;

        if (goingX) {
            i = b0 | 4;
            j = b0 | 8;

            for (bx = 0; bx < length; ++bx) {
                this.setBlockAndMetadata(world, x + bx, y + 0, z + 1, logID, i);
                if (rand.nextInt(3) > 0) {
                    this.setBlockAndMetadata(world, x + bx, y + 1, z + 1, TFBlocks.plant, 3);
                }
            }
        } else {
            i = b0 | 8;
            j = b0 | 4;

            for (bx = 0; bx < length; ++bx) {
                this.setBlockAndMetadata(world, x + 1, y + 0, z + bx, logID, i);
                if (rand.nextInt(3) > 0) {
                    this.setBlockAndMetadata(world, x + 1, y + 1, z + bx, TFBlocks.plant, 3);
                }
            }
        }

        if (rand.nextInt(3) > 0) {
            int bz;

            if (goingX) {
                bx = rand.nextInt(length);
                bz = rand.nextBoolean() ? 2 : 0;
                this.setBlockAndMetadata(world, x + bx, y + 0, z + bz, logID, j);
                if (rand.nextBoolean()) {
                    this.setBlockAndMetadata(world, x + bx, y + 1, z + bz, TFBlocks.plant, 3);
                }
            } else {
                bx = rand.nextBoolean() ? 2 : 0;
                bz = rand.nextInt(length);
                this.setBlockAndMetadata(world, x + bx, y + 0, z + bz, logID, j);
                if (rand.nextBoolean()) {
                    this.setBlockAndMetadata(world, x + bx, y + 1, z + bz, TFBlocks.plant, 3);
                }
            }
        }

        return true;
    }
}
