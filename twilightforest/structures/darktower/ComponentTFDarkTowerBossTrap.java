package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFDarkTowerBossTrap extends ComponentTFDarkTowerWing {

    public ComponentTFDarkTowerBossTrap() {}

    protected ComponentTFDarkTowerBossTrap(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
        this.spawnListIndex = -1;
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.makeABeard(parent, list, rand);

        for (int i = 0; i < 4; ++i) {
            if (i != 2 && !rand.nextBoolean()) {
                int[] dest = this.getValidOpening(rand, i);

                dest[1] = 1;
                this.makeTowerBalcony(list, rand, this.func_74877_c(), dest[0], dest[1], dest[2], i);
            }
        }

    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.makeEncasedWalls(world, rand, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        this.makeOpenings(world, sbb);
        this.addBossTrapFloors(world, decoRNG, sbb, 4, this.height - 1);
        this.destroyTower(world, decoRNG, 5, this.height + 2, 5, 4, sbb);
        this.destroyTower(world, decoRNG, 0, this.height, 0, 3, sbb);
        this.destroyTower(world, decoRNG, 0, this.height, 8, 4, sbb);
        this.destroyTower(world, decoRNG, 5, 6, 5, 2, sbb);
        this.func_151556_a(world, sbb, 1, 0, 1, this.size / 2, 0, this.size - 2, this.deco.blockID, this.deco.blockMeta, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 1, 1, this.size / 2, 1, this.size - 2, Blocks.field_150350_a, 0, Blocks.field_150350_a, 0, false);
        this.func_151550_a(world, TFBlocks.towerDevice, 10, 5, 1, 5, sbb);
        this.func_151550_a(world, Blocks.field_150488_af, 0, 5, 1, 6, sbb);
        this.func_151550_a(world, Blocks.field_150488_af, 0, 5, 1, 7, sbb);
        this.func_151550_a(world, Blocks.field_150488_af, 0, 5, 1, 8, sbb);
        this.func_151550_a(world, Blocks.field_150488_af, 0, 4, 1, 8, sbb);
        this.func_151550_a(world, Blocks.field_150488_af, 0, 3, 1, 8, sbb);
        this.func_151550_a(world, Blocks.field_150452_aw, 0, 2, 1, 8, sbb);
        return true;
    }

    protected void addBossTrapFloors(World world, Random rand, StructureBoundingBox sbb, int bottom, int top) {
        this.makeFullFloor(world, sbb, 3, 4, 4);
        this.addStairsDown(world, sbb, 3, 4, this.size - 2, 4);
        this.addStairsDown(world, sbb, 3, 4, this.size - 3, 4);
        this.addStairsDown(world, sbb, 1, this.height - 1, this.size - 2, 4);
        this.addStairsDown(world, sbb, 1, this.height - 1, this.size - 3, 4);
    }
}
