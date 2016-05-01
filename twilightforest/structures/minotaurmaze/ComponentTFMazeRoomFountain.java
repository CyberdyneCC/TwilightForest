package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeRoomFountain extends ComponentTFMazeRoom {

    public ComponentTFMazeRoomFountain() {}

    public ComponentTFMazeRoomFountain(int i, Random rand, int x, int y, int z) {
        super(i, rand, x, y, z);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151556_a(world, sbb, 5, 1, 5, 10, 1, 10, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151549_a(world, sbb, 6, 1, 6, 9, 1, 9, Blocks.field_150355_j, Blocks.field_150350_a, false);
        return true;
    }
}
