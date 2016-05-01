package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeCorridorIronFence extends ComponentTFMazeCorridor {

    public ComponentTFMazeCorridorIronFence() {}

    public ComponentTFMazeCorridorIronFence(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151556_a(world, sbb, 1, 4, 2, 4, 4, 3, TFBlocks.mazestone, 3, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 1, 2, 4, 3, 3, TFBlocks.mazestone, 2, Blocks.field_150350_a, 0, false);
        this.func_151549_a(world, sbb, 2, 1, 2, 3, 3, 3, Blocks.field_150411_aY, Blocks.field_150350_a, false);
        return true;
    }
}
