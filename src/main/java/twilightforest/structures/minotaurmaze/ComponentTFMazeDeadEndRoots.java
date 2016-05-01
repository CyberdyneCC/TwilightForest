package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeDeadEndRoots extends ComponentTFMazeDeadEnd {

    public ComponentTFMazeDeadEndRoots() {}

    public ComponentTFMazeDeadEndRoots(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int x = 1; x < 5; ++x) {
            for (int z = 0; z < 5; ++z) {
                if (rand.nextInt(z + 2) > 0) {
                    int length = rand.nextInt(6);

                    this.func_151550_a(world, Blocks.field_150346_d, 0, x, 6, z, sbb);

                    for (int y = 6 - length; y < 6; ++y) {
                        this.func_151550_a(world, TFBlocks.plant, 14, x, y, z, sbb);
                    }

                    if (rand.nextInt(z + 1) > 1) {
                        this.func_151550_a(world, Blocks.field_150351_n, 0, x, 1, z, sbb);
                        if (rand.nextInt(z + 1) > 1) {
                            this.func_151550_a(world, Blocks.field_150351_n, 0, x, 2, z, sbb);
                        }
                    }
                }
            }
        }

        return true;
    }
}
