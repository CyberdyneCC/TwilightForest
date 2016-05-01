package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFMazeDeadEndTorches extends ComponentTFMazeDeadEnd {

    public ComponentTFMazeDeadEndTorches() {}

    public ComponentTFMazeDeadEndTorches(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151556_a(world, sbb, 2, 1, 4, 3, 4, 4, Blocks.field_150478_aa, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 1, 1, 1, 4, 4, Blocks.field_150478_aa, 0, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 4, 1, 1, 4, 4, 4, Blocks.field_150478_aa, 0, Blocks.field_150350_a, 0, false);
        return true;
    }
}
