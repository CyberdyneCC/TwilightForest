package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TFGenGroveRuins extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        return rand.nextBoolean() ? this.generateLargeArch(world, rand, x, y, z) : this.generateSmallArch(world, rand, x, y, z);
    }

    private boolean generateLargeArch(World world, Random rand, int x, int y, int z) {
        if (!this.isAreaClear(world, rand, x, y, z, 2, 7, 6)) {
            return false;
        } else {
            for (int dy = -2; dy <= 7; ++dy) {
                this.setBlockAndMetadata(world, x + 0, y + dy, z + 1, Blocks.field_150417_aV, 1);
                this.setBlockAndMetadata(world, x + 1, y + dy, z + 1, Blocks.field_150417_aV, 1);
                this.setBlockAndMetadata(world, x + 0, y + dy, z + 2, Blocks.field_150417_aV, 1);
                this.setBlockAndMetadata(world, x + 1, y + dy, z + 2, Blocks.field_150417_aV, 1);
            }

            this.setBlockAndMetadata(world, x + 0, y - 1, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y - 1, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y - 2, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y - 2, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y - 1, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y - 1, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y - 2, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y - 2, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y - 1, z + 5, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y - 2, z + 5, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y + 6, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y + 6, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y + 7, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y + 7, z + 3, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y + 6, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y + 6, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y + 7, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y + 7, z + 4, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 1, y + 7, z + 5, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 0, y + 5, z + 0, Blocks.field_150417_aV, 3);
            return true;
        }
    }

    private boolean generateSmallArch(World world, Random rand, int x, int y, int z) {
        if (!this.isAreaClear(world, rand, x, y, z, 7, 5, 9)) {
            return false;
        } else {
            this.setBlockAndMetadata(world, x + 0, y + 4, z + 0, Blocks.field_150417_aV, 3);
            this.setBlockAndMetadata(world, x + 0, y + 3, z + 0, Blocks.field_150417_aV, 3);
            this.setBlockAndMetadata(world, x + 1, y + 4, z + 0, Blocks.field_150417_aV, 3);
            this.setBlockAndMetadata(world, x + 2, y + 4, z + 0, Blocks.field_150417_aV, 3);
            this.setBlockAndMetadata(world, x + 0, y + 4, z + 1, Blocks.field_150417_aV, 3);
            this.setBlockAndMetadata(world, x + 0, y + 4, z + 2, Blocks.field_150417_aV, 3);

            int dz;

            for (dz = -1; dz <= 5; ++dz) {
                this.setBlockAndMetadata(world, x + 3, y + dz, z + 0, Blocks.field_150417_aV, 1);
            }

            this.setBlockAndMetadata(world, x + 4, y - 1, z + 0, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 5, y - 1, z + 0, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 6, y - 1, z + 0, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 4, y + 5, z + 0, Blocks.field_150417_aV, 1);
            this.setBlockAndMetadata(world, x + 5, y + 5, z + 0, Blocks.field_150417_aV, 1);

            for (dz = -1; dz <= 5; ++dz) {
                this.setBlockAndMetadata(world, x + 0, y + dz, z + 3, Blocks.field_150417_aV, 1);
                this.setBlockAndMetadata(world, x + 0, y + dz, z + 7, Blocks.field_150417_aV, 1);
            }

            for (dz = 4; dz < 7; ++dz) {
                this.setBlockAndMetadata(world, x + 0, y - 1, z + dz, Blocks.field_150417_aV, 1);
                this.setBlockAndMetadata(world, x + 0, y + 5, z + dz, Blocks.field_150417_aV, 1);
            }

            this.setBlockAndMetadata(world, x + 0, y + 4, z + 8, Blocks.field_150417_aV, 3);
            return true;
        }
    }
}
