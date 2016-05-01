package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFMazeDeadEndPainting extends ComponentTFMazeDeadEnd {

    public ComponentTFMazeDeadEndPainting() {}

    public ComponentTFMazeDeadEndPainting(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, 1, 3, 3, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, 4, 3, 3, sbb);
        return true;
    }
}
