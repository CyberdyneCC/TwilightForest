package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFMazeDeadEndFountain extends ComponentTFMazeDeadEnd {

    public ComponentTFMazeDeadEndFountain() {}

    public ComponentTFMazeDeadEndFountain(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151556_a(world, sbb, 1, 1, 4, 4, 4, 4, TFBlocks.mazestone, 1, Blocks.field_150350_a, 0, false);
        this.func_151550_a(world, Blocks.field_150358_i, 0, 2, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150358_i, 0, 3, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 2, 0, 3, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, 0, 3, sbb);
        return true;
    }
}
