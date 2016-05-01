package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMazeCorridor extends StructureTFComponent {

    public ComponentTFMazeCorridor() {}

    public ComponentTFMazeCorridor(int i, int x, int y, int z, int rotation) {
        super(i);
        this.field_74885_f = rotation;
        this.field_74887_e = new StructureBoundingBox(x, y, z, x + 5, y + 5, z + 5);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151556_a(world, sbb, 1, 1, 2, 4, 4, 3, Blocks.field_150422_aJ, 0, Blocks.field_150350_a, 0, false);
        this.func_74878_a(world, sbb, 2, 1, 2, 3, 3, 3);
        return true;
    }
}
