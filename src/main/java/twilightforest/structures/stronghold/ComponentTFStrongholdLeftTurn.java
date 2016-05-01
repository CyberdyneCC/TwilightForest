package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdLeftTurn extends StructureTFStrongholdComponent {

    public ComponentTFStrongholdLeftTurn() {}

    public ComponentTFStrongholdLeftTurn(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 9, 7, 9, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addDoor(4, 1, 0);
        this.addNewComponent(parent, list, random, 3, 9, 1, 4);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 8, 6, 8, rand, this.deco.randomBlocks);
        this.func_74878_a(world, sbb, 1, 1, 1, 7, 5, 7);
        this.placeCornerStatue(world, 2, 1, 6, 1, sbb);
        this.placeDoors(world, rand, sbb);
        return true;
    }
}
