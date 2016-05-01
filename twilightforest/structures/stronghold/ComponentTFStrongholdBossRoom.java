package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;

public class ComponentTFStrongholdBossRoom extends StructureTFStrongholdComponent {

    public ComponentTFStrongholdBossRoom() {}

    public ComponentTFStrongholdBossRoom(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
        this.spawnListIndex = Integer.MAX_VALUE;
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -13, -1, 0, 27, 7, 27, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addDoor(13, 1, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 26, 6, 26, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 1, 1, 1, 3, 5, 25, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 23, 1, 1, 25, 5, 25, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 4, 1, 1, 22, 5, 3, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 4, 1, 23, 22, 5, 25, false, rand, this.deco.randomBlocks);
        this.func_151556_a(world, sbb, 1, 1, 1, 2, 5, 25, Blocks.field_150343_Z, 0, Blocks.field_150343_Z, 0, false);
        this.func_151556_a(world, sbb, 24, 1, 1, 25, 5, 25, Blocks.field_150343_Z, 0, Blocks.field_150343_Z, 0, false);
        this.func_151556_a(world, sbb, 4, 1, 1, 22, 5, 2, Blocks.field_150343_Z, 0, Blocks.field_150343_Z, 0, false);
        this.func_151556_a(world, sbb, 4, 1, 24, 22, 5, 25, Blocks.field_150343_Z, 0, Blocks.field_150343_Z, 0, false);
        this.func_74882_a(world, sbb, 4, 1, 4, 4, 5, 7, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 5, 1, 4, 5, 5, 5, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 6, 1, 4, 7, 5, 4, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 4, 1, 19, 4, 5, 22, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 5, 1, 21, 5, 5, 22, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 6, 1, 22, 7, 5, 22, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 22, 1, 4, 22, 5, 7, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 21, 1, 4, 21, 5, 5, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 19, 1, 4, 20, 5, 4, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 22, 1, 19, 22, 5, 22, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 21, 1, 21, 21, 5, 22, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 19, 1, 22, 20, 5, 22, false, rand, this.deco.randomBlocks);
        this.placePillarDecorations(world, sbb, 0);
        this.placePillarDecorations(world, sbb, 1);
        this.placePillarDecorations(world, sbb, 2);
        this.placePillarDecorations(world, sbb, 3);
        this.placeSarcophagus(world, sbb, 8, 1, 8, 0);
        this.placeSarcophagus(world, sbb, 13, 1, 8, 0);
        this.placeSarcophagus(world, sbb, 18, 1, 8, 0);
        this.placeSarcophagus(world, sbb, 8, 1, 15, 0);
        this.placeSarcophagus(world, sbb, 13, 1, 15, 0);
        this.placeSarcophagus(world, sbb, 18, 1, 15, 0);
        this.func_74878_a(world, sbb, 12, 1, 1, 14, 4, 2);
        this.func_151549_a(world, sbb, 12, 1, 3, 14, 4, 3, Blocks.field_150411_aY, Blocks.field_150411_aY, false);
        int i = this.func_74865_a(0, 0);
        int j = this.func_74862_a(0);
        int k = this.func_74873_b(0, 0);

        this.func_151550_a(world, TFBlocks.bossSpawner, 4, 13, 2, 13, sbb);
        this.placeDoors(world, rand, sbb);
        return true;
    }

    private void placeSarcophagus(World world, StructureBoundingBox sbb, int x, int y, int z, int rotation) {
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x + 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x - 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x + 1, y, z + 3, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x - 1, y, z + 3, rotation, sbb);
        if (world.field_73012_v.nextInt(7) == 0) {
            this.placeBlockRotated(world, Blocks.field_150478_aa, 0, x + 1, y + 1, z + 0, rotation, sbb);
        } else {
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, x + 1, y + 1, z + 0, rotation, sbb);
        }

        if (world.field_73012_v.nextInt(7) == 0) {
            this.placeBlockRotated(world, Blocks.field_150478_aa, 0, x - 1, y + 1, z + 0, rotation, sbb);
        } else {
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, x - 1, y + 1, z + 0, rotation, sbb);
        }

        if (world.field_73012_v.nextInt(7) == 0) {
            this.placeBlockRotated(world, Blocks.field_150478_aa, 0, x + 1, y + 1, z + 3, rotation, sbb);
        } else {
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, x + 1, y + 1, z + 3, rotation, sbb);
        }

        if (world.field_73012_v.nextInt(7) == 0) {
            this.placeBlockRotated(world, Blocks.field_150478_aa, 0, x - 1, y + 1, z + 3, rotation, sbb);
        } else {
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, x - 1, y + 1, z + 3, rotation, sbb);
        }

        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), x + 0, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), x + 0, y, z + 3, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), x + 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), x + 1, y, z + 2, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), x - 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), x - 1, y, z + 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150333_U, 0, x + 0, y + 1, z + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150333_U, 0, x + 0, y + 1, z + 2, rotation, sbb);
    }

    protected void placePillarDecorations(World world, StructureBoundingBox sbb, int rotation) {
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 4, 1, 8, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 8, 1, 4, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, 4, 5, 8, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 8, 5, 4, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 5, 1, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 6, 1, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 6, 1, 5, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, 5, 5, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 6, 5, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 6, 5, 5, rotation, sbb);
    }

    protected void placeDoorwayAt(World world, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        if (x != 0 && x != this.getXSize()) {
            this.func_151556_a(world, sbb, x - 1, y, z, x + 1, y + 3, z, Blocks.field_150411_aY, 0, Blocks.field_150350_a, 0, false);
        } else {
            this.func_151556_a(world, sbb, x, y, z - 1, x, y + 3, z + 1, Blocks.field_150411_aY, 0, Blocks.field_150350_a, 0, false);
        }

    }

    protected boolean isValidBreakInPoint(int wx, int wy, int wz) {
        return false;
    }
}
