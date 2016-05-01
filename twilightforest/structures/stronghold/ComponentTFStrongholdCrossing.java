package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdCrossing extends StructureTFStrongholdComponent {

    public ComponentTFStrongholdCrossing() {}

    public ComponentTFStrongholdCrossing(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -13, -1, 0, 18, 7, 18, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addDoor(13, 1, 0);
        this.addNewComponent(parent, list, random, 0, 4, 1, 18);
        this.addNewComponent(parent, list, random, 1, -1, 1, 13);
        this.addNewComponent(parent, list, random, 3, 18, 1, 4);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 17, 6, 17, rand, this.deco.randomBlocks);
        this.placeCornerStatue(world, 2, 1, 2, 0, sbb);
        this.placeCornerStatue(world, 15, 1, 15, 3, sbb);
        this.fillBlocksRotated(world, sbb, 8, 1, 8, 9, 5, 9, this.deco.pillarID, this.deco.pillarMeta, 0);
        this.placeWallStatue(world, 8, 1, 7, 0, sbb);
        this.placeWallStatue(world, 7, 1, 9, 3, sbb);
        this.placeWallStatue(world, 9, 1, 10, 2, sbb);
        this.placeWallStatue(world, 10, 1, 8, 1, sbb);
        this.placeTableAndChairs(world, sbb, 0);
        this.placeTableAndChairs(world, sbb, 1);
        this.placeTableAndChairs(world, sbb, 2);
        this.placeTableAndChairs(world, sbb, 3);
        this.placeDoors(world, rand, sbb);
        return true;
    }

    private void placeTableAndChairs(World world, StructureBoundingBox sbb, int rotation) {
        this.placeBlockRotated(world, Blocks.field_150476_ad, this.getStairMeta(0 + rotation) + 4, 5, 1, 3, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150476_ad, this.getStairMeta(3 + rotation) + 4, 5, 1, 4, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150476_ad, this.getStairMeta(1 + rotation) + 4, 6, 1, 3, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150476_ad, this.getStairMeta(2 + rotation) + 4, 6, 1, 4, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150485_bF, this.getStairMeta(3 + rotation), 5, 1, 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150485_bF, this.getStairMeta(0 + rotation), 7, 1, 3, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150485_bF, this.getStairMeta(1 + rotation), 6, 1, 5, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150485_bF, this.getStairMeta(2 + rotation), 4, 1, 4, rotation, sbb);
    }
}
