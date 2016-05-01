package twilightforest.structures.icetower;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFIceTowerWing extends ComponentTFTowerWing {

    protected static final int SIZE = 11;
    private static final int RANGE = 17;
    boolean hasBase = false;
    protected int treasureFloor = -1;

    public ComponentTFIceTowerWing() {}

    protected ComponentTFIceTowerWing(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("hasBase", this.hasBase);
        par1NBTTagCompound.func_74768_a("treasureFloor", this.treasureFloor);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.hasBase = par1NBTTagCompound.func_74767_n("hasBase");
        this.treasureFloor = par1NBTTagCompound.func_74762_e("treasureFloor");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.hasBase = this.shouldHaveBase(rand);
        int floors;

        if (this.func_74877_c() < 5) {
            floors = rand.nextInt(4);

            for (int i = 0; i < 4; ++i) {
                int dir = (floors + i) % 4;
                int[] dest = this.getValidOpening(rand, dir);

                if (this.func_74877_c() == 4 && parent instanceof ComponentTFIceTowerMain && !((ComponentTFIceTowerMain) parent).hasBossWing) {
                    boolean flag = this.makeBossTowerWing(list, rand, this.func_74877_c() + 1, dest[0], dest[1], dest[2], 15, 41, dir);

                    ((ComponentTFIceTowerMain) parent).hasBossWing = flag;
                } else {
                    int childHeight = (rand.nextInt(2) + rand.nextInt(2) + 2) * 10 + 1;

                    this.makeTowerWing(list, rand, this.func_74877_c() + 1, dest[0], dest[1], dest[2], 11, childHeight, dir);
                }
            }
        }

        floors = this.height / 10;
        if (this.treasureFloor == -1 && floors > 1) {
            this.treasureFloor = rand.nextInt(floors - 1);
        }

        this.makeARoof(parent, list, rand);
        if (!this.hasBase) {
            this.makeABeard(parent, list, rand);
        }

    }

    protected boolean shouldHaveBase(Random rand) {
        return this.func_74877_c() == 0 || rand.nextBoolean();
    }

    private boolean isOutOfRange(StructureComponent parent, int nx, int ny, int nz, int range) {
        return Math.abs(nx - parent.func_74874_b().func_78881_e()) > range || Math.abs(nz - parent.func_74874_b().func_78891_g()) > range;
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);

        if (this.isOutOfRange((StructureComponent) list.get(0), dx[0], dx[1], dx[2], 17)) {
            return false;
        } else {
            ComponentTFIceTowerWing wing = new ComponentTFIceTowerWing(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
            StructureComponent intersect = StructureComponent.func_74883_a(list, wing.func_74874_b());

            if (intersect != null && intersect != this) {
                return false;
            } else {
                list.add(wing);
                wing.func_74861_a((StructureComponent) list.get(0), list, rand);
                this.addOpening(x, y, z, rotation);
                return true;
            }
        }
    }

    public boolean makeBossTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);
        ComponentTFIceTowerBossWing wing = new ComponentTFIceTowerBossWing(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, wing.func_74874_b());

        if (intersect != null && intersect != this) {
            return false;
        } else {
            list.add(wing);
            wing.func_74861_a((StructureComponent) list.get(0), list, rand);
            this.addOpening(x, y, z, rotation);
            return true;
        }
    }

    protected int getYByStairs(int rx, Random rand, int direction) {
        int floors = this.height / 10;

        return 11 + rand.nextInt(floors - 1) * 10;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.func_74882_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, false, rand, this.deco.randomBlocks);
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        if (this.hasBase) {
            for (int x = 0; x < this.size; ++x) {
                for (int z = 0; z < this.size; ++z) {
                    this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, x, -1, z, sbb);
                }
            }
        }

        this.nullifySkyLightForBoundingBox(world);
        this.makeFloorsForTower(world, decoRNG, sbb);
        this.makeOpenings(world, sbb);
        return true;
    }

    public void nullifySkyLightForBoundingBox(World world) {
        this.nullifySkyLight(world, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 1, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e - 1, this.field_74887_e.field_78892_f - 1);
    }

    protected void makeFloorsForTower(World world, Random decoRNG, StructureBoundingBox sbb) {
        int floors = this.height / 10;
        int ladderDir = 3;
        int downLadderDir = -1;
        byte floorHeight = 10;

        int topFloor;

        for (topFloor = 0; topFloor < floors - 1; ++topFloor) {
            this.placeFloor(world, decoRNG, sbb, floorHeight, topFloor);
            downLadderDir = ladderDir++;
            ladderDir %= 4;
            this.decorateFloor(world, decoRNG, topFloor, topFloor * floorHeight, topFloor * floorHeight + floorHeight, ladderDir, downLadderDir, sbb);
        }

        topFloor = floors - 1;
        this.decorateTopFloor(world, decoRNG, topFloor, topFloor * floorHeight, topFloor * floorHeight + floorHeight, ladderDir, downLadderDir, sbb);
    }

    protected void placeFloor(World world, Random rand, StructureBoundingBox sbb, int floorHeight, int floor) {
        for (int x = 1; x < this.size - 1; ++x) {
            for (int z = 1; z < this.size - 1; ++z) {
                this.func_151550_a(world, this.deco.floorID, this.deco.floorMeta, x, floor * floorHeight + floorHeight, z, sbb);
            }
        }

    }

    protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        super.makeDoorOpening(world, dx, dy, dz, sbb);
        if (this.func_151548_a(world, dx, dy + 2, dz, sbb) != Blocks.field_150350_a) {
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, dx, dy + 2, dz, sbb);
        }

    }

    protected void decorateFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        boolean hasTreasure = this.treasureFloor == floor;

        switch (rand.nextInt(8)) {
        case 0:
            if (this.isNoDoorAreaRotated(9, bottom + 5, 1, 10, top + 1, 7, ladderUpDir)) {
                this.decorateWraparoundWallSteps(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
                break;
            }

        case 1:
            if (this.isNoDoorAreaRotated(7, bottom, 0, 10, top + 1, 10, ladderUpDir)) {
                this.decorateFarWallSteps(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
                break;
            }

        case 2:
            if (this.isNoDoorAreaRotated(9, bottom + 5, 1, 10, top + 1, 7, ladderUpDir)) {
                this.decorateWraparoundWallStepsPillars(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
                break;
            }

        case 3:
            this.decoratePlatform(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
            break;

        case 4:
            this.decoratePillarParkour(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
            break;

        case 5:
            this.decoratePillarPlatforms(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
            break;

        case 6:
            this.decoratePillarPlatformsOutside(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
            break;

        case 7:
        default:
            this.decorateQuadPillarStairs(world, rand, bottom, top, ladderUpDir, ladderDownDir, hasTreasure, sbb);
        }

    }

    private boolean isNoDoorAreaRotated(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, int rotation) {
        boolean isClear = true;
        StructureBoundingBox exclusionBox;

        switch (rotation) {
        case 0:
        default:
            exclusionBox = new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
            break;

        case 1:
            exclusionBox = new StructureBoundingBox(this.size - 1 - maxZ, minY, minX, this.size - 1 - minZ, maxY, maxX);
            break;

        case 2:
            exclusionBox = new StructureBoundingBox(this.size - 1 - maxX, minY, this.size - 1 - maxZ, this.size - 1 - minX, maxY, this.size - 1 - minZ);
            break;

        case 3:
            exclusionBox = new StructureBoundingBox(minZ, minY, this.size - 1 - maxX, maxZ, maxY, this.size - 1 - minX);
        }

        Iterator iterator = this.openings.iterator();

        while (iterator.hasNext()) {
            ChunkCoordinates door = (ChunkCoordinates) iterator.next();

            if (exclusionBox.func_78890_b(door.field_71574_a, door.field_71572_b, door.field_71573_c)) {
                isClear = false;
            }
        }

        return isClear;
    }

    protected void decorateTopFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        if (rand.nextBoolean()) {
            this.decoratePillarsCorners(world, rand, bottom, top, ladderDownDir, sbb);
        } else {
            this.decoratePillarsGrid(world, rand, bottom, top, ladderDownDir, sbb);
        }

        if (this.isDeadEnd()) {
            this.decorateTopFloorTreasure(world, rand, bottom, top, ladderDownDir, sbb);
        }

    }

    private void decorateTopFloorTreasure(World world, Random rand, int bottom, int top, int rotation, StructureBoundingBox sbb) {
        this.fillBlocksRotated(world, sbb, 5, bottom + 1, 5, 5, bottom + 4, 5, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.placeTreasureAtCurrentPosition(world, (Random) null, 5, bottom + 5, 5, TFTreasure.aurora_room, sbb);
    }

    private void decoratePillars(World world, Random rand, int bottom, int top, int rotation, StructureBoundingBox sbb) {
        this.fillBlocksRotated(world, sbb, 3, bottom + 1, 3, 3, top - 1, 3, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 1, 3, 7, top - 1, 3, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 3, bottom + 1, 7, 3, top - 1, 7, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 1, 7, 7, top - 1, 7, this.deco.pillarID, this.deco.pillarMeta, rotation);
    }

    private void decoratePillarsGrid(World world, Random rand, int bottom, int top, int rotation, StructureBoundingBox sbb) {
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;

        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 1, 3, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 1, 7, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 3, 9, bottom + 5, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 7, 9, bottom + 5, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.decoratePillars(world, rand, bottom, top, rotation, sbb);
    }

    private void decoratePillarsCorners(World world, Random rand, int bottom, int top, int rotation, StructureBoundingBox sbb) {
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;

        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 1, 3, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 1, 7, bottom + 5, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 3, 9, bottom + 5, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 7, 9, bottom + 5, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, rotation);
        this.fillAirRotated(world, sbb, 3, bottom + 5, 3, 7, bottom + 5, 7, rotation);
        this.decoratePillars(world, rand, bottom, top, rotation, sbb);
    }

    private void decorateFarWallSteps(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        int beamMetaNS;
        int y;
        int by;

        for (beamMetaNS = 1; beamMetaNS < 10; ++beamMetaNS) {
            y = bottom + 10 - beamMetaNS / 2;
            this.placeBlockRotated(world, beamMetaNS % 2 == 0 ? this.deco.pillarID : this.deco.platformID, beamMetaNS % 2 == 0 ? this.deco.pillarMeta : this.deco.platformMeta, 9, y, beamMetaNS, ladderUpDir, sbb);

            for (by = bottom + 1; by < y; ++by) {
                this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 9, by, beamMetaNS, ladderUpDir, sbb);
            }
        }

        for (beamMetaNS = 1; beamMetaNS < 10; ++beamMetaNS) {
            y = bottom + 1 + beamMetaNS / 2;
            this.placeBlockRotated(world, beamMetaNS % 2 == 0 ? this.deco.platformID : this.deco.pillarID, beamMetaNS % 2 == 0 ? this.deco.platformMeta : this.deco.pillarMeta, 8, y, beamMetaNS, ladderUpDir, sbb);

            for (by = bottom + 1; by < y; ++by) {
                this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 8, by, beamMetaNS, ladderUpDir, sbb);
            }
        }

        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta, 7, bottom + 1, 1, ladderUpDir, sbb);

        for (beamMetaNS = 2; beamMetaNS < 7; ++beamMetaNS) {
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, 9, top, beamMetaNS, ladderUpDir, sbb);
        }

        if (hasTreasure) {
            this.placeTreasureRotated(world, 1, bottom + 8, 5, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
            beamMetaNS = (this.field_74885_f + ladderUpDir) % 2 == 0 ? 4 : 8;
            this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, 1, bottom + 7, 5, ladderUpDir, sbb);
        }

    }

    private void decorateWraparoundWallSteps(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        int beamMetaNS;
        int y;

        for (beamMetaNS = 1; beamMetaNS < 10; ++beamMetaNS) {
            y = bottom + 10 - beamMetaNS / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (beamMetaNS % 2 == 0 ? 8 : 0), 9, y, beamMetaNS, ladderUpDir, sbb);
        }

        for (beamMetaNS = 1; beamMetaNS < 9; ++beamMetaNS) {
            y = bottom + 2 + (beamMetaNS - 1) / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (beamMetaNS % 2 == 0 ? 8 : 0), beamMetaNS, y, 9, ladderUpDir, sbb);
        }

        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + 8, 1, bottom + 1, 8, ladderUpDir, sbb);
        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta, 1, bottom + 1, 7, ladderUpDir, sbb);

        for (beamMetaNS = 2; beamMetaNS < 7; ++beamMetaNS) {
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, 9, top, beamMetaNS, ladderUpDir, sbb);
        }

        if (hasTreasure) {
            this.placeTreasureRotated(world, 1, bottom + 5, 5, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
            beamMetaNS = (this.field_74885_f + ladderUpDir) % 2 == 0 ? 4 : 8;
            this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, 1, bottom + 4, 5, ladderUpDir, sbb);
        }

    }

    private void decorateWraparoundWallStepsPillars(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        int beamMetaNS = (this.field_74885_f + ladderDownDir) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;

        this.decorateWraparoundWallSteps(world, rand, bottom, top, ladderUpDir, ladderDownDir, false, sbb);
        this.decoratePillars(world, rand, bottom, top, ladderDownDir, sbb);
        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 1, 3, bottom + 5, 2, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 7, bottom + 5, 1, 7, bottom + 5, 2, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 8, bottom + 5, 3, 9, bottom + 5, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 8, bottom + 5, 7, 9, bottom + 5, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 2, 3, 2, bottom + 2, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 6, 3, 2, bottom + 6, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 4, 7, 2, bottom + 4, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 8, 7, 2, bottom + 8, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 3, bottom + 6, 8, 3, bottom + 6, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 7, bottom + 8, 8, 7, bottom + 8, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        if (hasTreasure) {
            this.placeTreasureRotated(world, 7, bottom + 6, 1, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    private void decoratePlatform(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        this.decoratePillars(world, rand, bottom, top, ladderDownDir, sbb);
        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 3, 7, bottom + 5, 7, this.deco.floorID, this.deco.floorMeta, ladderDownDir);

        int z;
        int y;

        for (z = 6; z < 10; ++z) {
            y = bottom - 2 + z / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (z % 2 == 1 ? 8 : 0), 1, y, z, ladderDownDir, sbb);
        }

        for (z = 2; z < 6; ++z) {
            y = bottom + 2 + z / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (z % 2 == 1 ? 8 : 0), z, y, 9, ladderDownDir, sbb);
        }

        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta, 5, bottom + 5, 8, ladderDownDir, sbb);
        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta, 5, bottom + 6, 2, ladderUpDir, sbb);

        for (z = 5; z < 10; ++z) {
            y = bottom + 4 + z / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (z % 2 == 1 ? 8 : 0), z, y, 1, ladderUpDir, sbb);
            if (z > 6) {
                this.placeBlockRotated(world, Blocks.field_150350_a, 0, z, top, 1, ladderUpDir, sbb);
            }
        }

        for (z = 2; z < 5; ++z) {
            y = bottom + 8 + z / 2;
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, 9, top, z, ladderUpDir, sbb);
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (z % 2 == 1 ? 8 : 0), 9, y, z, ladderUpDir, sbb);
        }

        if (hasTreasure) {
            this.placeTreasureRotated(world, 3, bottom + 6, 5, ladderDownDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    private void decorateQuadPillarStairs(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        this.decoratePillars(world, rand, bottom, top, ladderDownDir, sbb);

        int x;
        int y;

        for (x = 6; x < 9; ++x) {
            y = bottom - 2 + x / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (x % 2 == 1 ? 8 : 0), 2, y, x, ladderDownDir, sbb);
        }

        for (x = 3; x < 9; ++x) {
            y = bottom + 1 + x / 2;
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (x % 2 == 1 ? 8 : 0), x, y, 8, ladderDownDir, sbb);
        }

        for (x = 7; x > 1; --x) {
            y = top - 2 - (x - 1) / 2;
            if (x < 4) {
                this.placeBlockRotated(world, Blocks.field_150350_a, 0, 8, top, x, ladderDownDir, sbb);
            }

            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (x % 2 == 1 ? 8 : 0), 8, y, x, ladderDownDir, sbb);
        }

        for (x = 7; x > 3; --x) {
            y = top + 1 - (x - 1) / 2;
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, x, top, 2, ladderDownDir, sbb);
            this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta + (x % 2 == 1 ? 8 : 0), x, y, 2, ladderDownDir, sbb);
        }

        if (hasTreasure) {
            this.placeTreasureRotated(world, 3, bottom + 7, 7, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    private void decoratePillarPlatforms(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        for (int i = 1; i < 10; ++i) {
            int rotation = (ladderUpDir + i) % 4;

            this.fillBlocksRotated(world, sbb, 2, bottom + i, 2, 4, bottom + i, 4, this.deco.floorID, this.deco.floorMeta, rotation);
        }

        this.fillAirRotated(world, sbb, 2, top, 2, 8, top, 4, ladderUpDir);
        this.fillAirRotated(world, sbb, 2, top, 2, 4, top, 6, ladderUpDir);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 7, top, 3, ladderUpDir, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 3, top, 3, ladderUpDir, sbb);
        this.decoratePillars(world, rand, bottom, top, ladderUpDir, sbb);
        if (hasTreasure) {
            this.placeTreasureRotated(world, 3, bottom + 5, 2, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    private void decoratePillarPlatformsOutside(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        int rotation;

        for (rotation = 1; rotation < 8; ++rotation) {
            int rotation1 = (ladderUpDir + rotation) % 4;

            this.fillBlocksRotated(world, sbb, 1, bottom + rotation, 1, 3, bottom + rotation, 3, this.deco.platformID, this.deco.platformMeta, rotation1);
            this.fillBlocksRotated(world, sbb, 4, bottom + rotation, 1, 6, bottom + rotation, 3, this.deco.floorID, this.deco.floorMeta, rotation1);
        }

        rotation = (ladderUpDir + 2) % 4;
        this.fillAirRotated(world, sbb, 5, top, 8, 9, top, 9, rotation);
        this.fillAirRotated(world, sbb, 8, top, 6, 9, top, 9, rotation);
        this.fillBlocksRotated(world, sbb, 8, top - 2, 7, 9, top - 2, 7, this.deco.platformID, this.deco.platformMeta, rotation);
        this.fillBlocksRotated(world, sbb, 8, top - 2, 8, 9, top - 2, 9, this.deco.floorID, this.deco.floorMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, top - 1, 8, 7, top - 1, 9, this.deco.platformID, this.deco.platformMeta, rotation);
        this.fillBlocksRotated(world, sbb, 6, top - 1, 8, 6, top - 1, 9, this.deco.platformID, this.deco.platformMeta | 8, rotation);
        this.fillBlocksRotated(world, sbb, 5, top - 0, 8, 5, top - 0, 9, this.deco.platformID, this.deco.platformMeta, rotation);
        this.decoratePillars(world, rand, bottom, top, ladderUpDir, sbb);
        if (hasTreasure) {
            this.placeTreasureRotated(world, 3, bottom + 5, 2, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    private void decoratePillarParkour(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, boolean hasTreasure, StructureBoundingBox sbb) {
        int beamMetaNS = (this.field_74885_f + ladderDownDir) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;

        this.decoratePillars(world, rand, bottom, top, ladderDownDir, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 5, bottom + 1, 5, ladderDownDir, sbb);
        this.fillBlocksRotated(world, sbb, 5, bottom + 2, 7, 5, bottom + 2, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 3, 7, 2, bottom + 3, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 3, bottom + 3, 8, 3, bottom + 3, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 7, 7, 2, bottom + 7, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 3, bottom + 7, 8, 3, bottom + 7, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillAirRotated(world, sbb, 3, bottom + 4, 7, 3, bottom + 6, 7, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 4, 5, 2, bottom + 4, 5, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 3, bottom + 5, 1, 3, bottom + 5, 2, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 1, bottom + 5, 3, 2, bottom + 5, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillAirRotated(world, sbb, 3, bottom + 6, 3, 3, bottom + 8, 3, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 5, bottom + 6, 1, 5, bottom + 6, 2, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillAirRotated(world, sbb, 7, bottom + 8, 3, 7, bottom + 10, 3, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 7, bottom + 7, 1, 7, bottom + 7, 2, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 8, bottom + 7, 3, 9, bottom + 7, 3, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 8, bottom + 8, 5, 9, bottom + 8, 5, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 8, bottom + 9, 7, 9, bottom + 9, 7, this.deco.pillarID, this.deco.pillarMeta + beamMetaNS, ladderDownDir);
        this.fillBlocksRotated(world, sbb, 7, bottom + 9, 8, 7, bottom + 9, 9, this.deco.pillarID, this.deco.pillarMeta + beamMetaEW, ladderDownDir);
        this.fillAirRotated(world, sbb, 2, top, 2, 8, top, 4, ladderUpDir);
        this.fillAirRotated(world, sbb, 2, top, 2, 4, top, 6, ladderUpDir);
        if (hasTreasure) {
            this.placeTreasureRotated(world, 8, bottom + 8, 7, ladderUpDir, TFTreasure.aurora_cache, false, sbb);
        }

    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        int index = this.func_74877_c();

        this.tryToFitRoof(list, rand, new ComponentTFIceTowerRoof(index + 1, this));
    }

    public void makeABeard(StructureComponent parent, List list, Random rand) {
        int index = this.func_74877_c();
        ComponentTFIceTowerBeard beard = new ComponentTFIceTowerBeard(index + 1, this);

        list.add(beard);
        beard.func_74861_a(this, list, rand);
    }
}
