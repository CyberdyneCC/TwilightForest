package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeRoomExit extends ComponentTFMazeRoom {

    public ComponentTFMazeRoomExit() {}

    public ComponentTFMazeRoomExit(int i, Random rand, int x, int y, int z) {
        super(i, rand, x, y, z);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151556_a(world, sbb, 5, -5, 5, 10, 0, 10, TFBlocks.mazestone, 1, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 1, 5, 10, 1, 10, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 2, 5, 10, 3, 10, Blocks.field_150411_aY, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 5, 4, 5, 10, 4, 10, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_74878_a(world, sbb, 6, -5, 6, 9, 4, 9);
        return true;
    }
}
