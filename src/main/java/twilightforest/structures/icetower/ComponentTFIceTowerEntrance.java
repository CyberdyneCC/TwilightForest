package twilightforest.structures.icetower;

import java.util.List;
import java.util.Random;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFIceTowerEntrance extends ComponentTFIceTowerWing {

    public ComponentTFIceTowerEntrance() {}

    public ComponentTFIceTowerEntrance(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    protected boolean shouldHaveBase(Random rand) {
        return true;
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.addStairs(list, rand, this.func_74877_c() + 1, this.size - 1, 1, this.size / 2, 0);
        this.addStairs(list, rand, this.func_74877_c() + 1, this.size / 2, 1, 0, 3);
        this.addStairs(list, rand, this.func_74877_c() + 1, this.size / 2, 1, this.size - 1, 1);
        this.hasBase = this.shouldHaveBase(rand);
        this.makeARoof(parent, list, rand);
    }

    private boolean addStairs(List list, Random rand, int index, int x, int y, int z, int rotation) {
        this.addOpening(x, y, z, rotation);
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        ChunkCoordinates dx = this.offsetTowerCCoords(x, y, z, this.size, direction);
        ComponentTFIceTowerStairs entrance = new ComponentTFIceTowerStairs(index, dx.field_71574_a, dx.field_71572_b, dx.field_71573_c, this.size, this.height, direction);

        list.add(entrance);
        entrance.func_74861_a((StructureComponent) list.get(0), list, rand);
        return true;
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        return false;
    }

    protected void makeFloorsForTower(World world, Random rand, StructureBoundingBox sbb) {
        this.decoratePillarsCornersHigh(world, rand, 0, 11, 0, sbb);
    }

    protected void decoratePillarsCornersHigh(World world, Random rand, int bottom, int top, int rotation, StructureBoundingBox sbb) {
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;

        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 1, 3, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 1, 7, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 3, 9, bottom + 5, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 7, 9, bottom + 5, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.fillAirRotated(world, sbb, 3, bottom + 5, 3, 7, bottom + 5, 7, rotation);
        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 3, 3, top - 1, 3, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 3, 7, top - 1, 3, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 7, 3, top - 1, 7, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 7, 7, top - 1, 7, this.deco.pillarID, this.deco.pillarMeta, rotation);
    }
}
