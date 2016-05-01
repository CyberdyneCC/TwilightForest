package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeRoomCollapse extends ComponentTFMazeRoom {

    public ComponentTFMazeRoomCollapse() {}

    public ComponentTFMazeRoomCollapse(int i, Random rand, int x, int y, int z) {
        super(i, rand, x, y, z);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);

        for (int x = 1; x < 14; ++x) {
            for (int z = 1; z < 14; ++z) {
                int dist = (int) Math.round(7.0D / Math.sqrt((7.5D - (double) x) * (7.5D - (double) x) + (7.5D - (double) z) * (7.5D - (double) z)));
                int gravel = rand.nextInt(dist);
                int root = rand.nextInt(dist);

                if (gravel > 0) {
                    ++gravel;
                    this.func_151549_a(world, sbb, x, 1, z, x, gravel, z, Blocks.field_150351_n, Blocks.field_150350_a, false);
                    this.func_74878_a(world, sbb, x, gravel, z, x, gravel + 5, z);
                } else if (root > 0) {
                    this.func_151549_a(world, sbb, x, 5, z, x, 5 + root, z, Blocks.field_150346_d, Blocks.field_150350_a, true);
                    this.func_151556_a(world, sbb, x, 5 - rand.nextInt(5), z, x, 5, z, TFBlocks.plant, 14, Blocks.field_150350_a, 0, false);
                } else if (rand.nextInt(dist + 1) > 0) {
                    this.func_74878_a(world, sbb, x, 5, z, x, 5, z);
                }
            }
        }

        return true;
    }
}
