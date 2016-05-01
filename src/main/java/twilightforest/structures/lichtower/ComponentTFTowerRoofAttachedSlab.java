package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofAttachedSlab extends ComponentTFTowerRoofSlab {

    public ComponentTFTowerRoofAttachedSlab() {}

    public ComponentTFTowerRoofAttachedSlab(int i, ComponentTFTowerWing wing) {
        super(i, wing);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        byte slabMeta = 2;

        return this.makeConnectedCap(world, slabMeta, sbb);
    }
}
