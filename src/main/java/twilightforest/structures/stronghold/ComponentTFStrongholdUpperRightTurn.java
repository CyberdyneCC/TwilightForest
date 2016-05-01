package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdUpperRightTurn extends StructureTFStrongholdComponent {

    public ComponentTFStrongholdUpperRightTurn() {}

    public ComponentTFStrongholdUpperRightTurn(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureBoundingBox.func_78889_a(x, y, z, -2, -1, 0, 5, 5, 5, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addNewUpperComponent(parent, list, random, 1, -1, 1, 2);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.func_74860_a(world, sbb)) {
            return false;
        } else {
            this.placeUpperStrongholdWalls(world, sbb, 0, 0, 0, 4, 4, 4, rand, this.deco.randomBlocks);
            this.placeSmallDoorwayAt(world, rand, 2, 2, 1, 0, sbb);
            this.placeSmallDoorwayAt(world, rand, 1, 0, 1, 2, sbb);
            return true;
        }
    }

    public boolean isComponentProtected() {
        return false;
    }
}
