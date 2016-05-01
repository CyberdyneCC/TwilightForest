package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFMazeDeadEndShrooms extends ComponentTFMazeDeadEndRoots {

    public ComponentTFMazeDeadEndShrooms() {}

    public ComponentTFMazeDeadEndShrooms(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int mushY;

        for (int mushType = 1; mushType < 5; ++mushType) {
            for (mushY = 0; mushY < 5; ++mushY) {
                if (rand.nextInt(mushY + 2) > 0) {
                    this.func_151550_a(world, Blocks.field_150391_bh, 0, mushType, 0, mushY, sbb);
                }

                if (rand.nextInt(mushY + 2) > 0) {
                    this.func_151550_a(world, rand.nextBoolean() ? Blocks.field_150337_Q : Blocks.field_150338_P, 0, mushType, 1, mushY, sbb);
                }
            }
        }

        Block block = rand.nextBoolean() ? Blocks.field_150419_aX : Blocks.field_150420_aW;

        mushY = rand.nextInt(4) + 1;
        int mushZ = rand.nextInt(3) + 1;

        this.func_151550_a(world, block, 15, 1, mushY - 1, mushZ, sbb);
        this.func_151556_a(world, sbb, 1, 1, mushZ, 1, mushY, mushZ, block, 10, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, mushY, mushZ - 1, 2, mushY, mushZ + 1, block, 14, Blocks.field_150350_a, 0, false);
        block = block == Blocks.field_150420_aW ? Blocks.field_150419_aX : Blocks.field_150420_aW;
        mushY = rand.nextInt(4) + 1;
        mushZ = rand.nextInt(3) + 1;
        this.func_151556_a(world, sbb, 4, 1, mushZ, 4, mushY, mushZ, block, 10, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 3, mushY, mushZ - 1, 4, mushY, mushZ + 1, block, 14, Blocks.field_150350_a, 0, false);
        block = rand.nextBoolean() ? Blocks.field_150419_aX : Blocks.field_150420_aW;
        mushY = rand.nextInt(4) + 1;
        int mushX = rand.nextInt(3) + 2;

        this.func_151556_a(world, sbb, mushX, 1, 4, mushX, mushY, 4, block, 10, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, mushX - 1, mushY, 3, mushX + 1, mushY, 4, block, 14, Blocks.field_150350_a, 0, false);
        return true;
    }
}
