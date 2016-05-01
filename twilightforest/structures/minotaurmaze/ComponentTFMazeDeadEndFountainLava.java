package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFMazeDeadEndFountainLava extends ComponentTFMazeDeadEndFountain {

    public ComponentTFMazeDeadEndFountainLava() {}

    public ComponentTFMazeDeadEndFountainLava(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 2, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150356_k, 0, 2, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150356_k, 0, 3, 3, 4, sbb);
        return true;
    }
}
