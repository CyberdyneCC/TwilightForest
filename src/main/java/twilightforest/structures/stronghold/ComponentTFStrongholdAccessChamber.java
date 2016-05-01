package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;

public class ComponentTFStrongholdAccessChamber extends StructureTFStrongholdComponent {

    public ComponentTFStrongholdAccessChamber() {}

    public ComponentTFStrongholdAccessChamber(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureBoundingBox.func_78889_a(x, y, z, -4, 1, 0, 9, 5, 9, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addNewUpperComponent(parent, list, random, 0, 4, 1, 9);
        this.addNewUpperComponent(parent, list, random, 1, -1, 1, 4);
        this.addNewUpperComponent(parent, list, random, 2, 4, 1, -1);
        this.addNewUpperComponent(parent, list, random, 3, 9, 1, 4);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_74882_a(world, sbb, 0, 0, 0, 8, 4, 8, true, rand, this.deco.randomBlocks);
        this.placeSmallDoorwayAt(world, rand, 0, 4, 1, 8, sbb);
        this.placeSmallDoorwayAt(world, rand, 1, 0, 1, 4, sbb);
        this.placeSmallDoorwayAt(world, rand, 2, 4, 1, 0, sbb);
        this.placeSmallDoorwayAt(world, rand, 3, 8, 1, 4, sbb);
        this.func_151556_a(world, sbb, 2, -2, 2, 6, 0, 6, Blocks.field_150417_aV, 1, Blocks.field_150350_a, 0, false);
        this.func_74878_a(world, sbb, 3, -2, 3, 5, 2, 5);
        this.func_151556_a(world, sbb, 2, 0, 3, 2, 0, 6, this.deco.stairID, this.getStairMeta(2), Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 6, 0, 2, 6, 0, 6, this.deco.stairID, this.getStairMeta(0), Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 3, 0, 2, 5, 0, 2, this.deco.stairID, this.getStairMeta(3), Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 3, 0, 6, 5, 0, 6, this.deco.stairID, this.getStairMeta(1), Blocks.field_150350_a, 0, false);
        this.func_151550_a(world, this.deco.pillarID, this.deco.pillarMeta, 2, 0, 2, sbb);
        this.func_151550_a(world, TFBlocks.trophyPedestal, 15, 2, 1, 2, sbb);
        this.func_151556_a(world, sbb, 2, -1, 2, 6, -1, 6, TFBlocks.shield, 15, Blocks.field_150350_a, 0, false);
        return true;
    }

    public boolean isComponentProtected() {
        return false;
    }
}
