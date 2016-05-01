package twilightforest.structures.lichtower;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFTowerMain extends ComponentTFTowerWing {

    public ComponentTFTowerMain() {}

    public ComponentTFTowerMain(World world, Random rand, int index, int x, int y, int z) {
        super(index, x, y, z, 15, 55 + rand.nextInt(32), 0);
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        this.makeARoof(parent, list, rand);

        int i;
        int[] dest;
        int childHeight;

        for (i = 0; i < 4; ++i) {
            dest = this.getValidOpening(rand, i);
            if (dest[1] < this.height / 2) {
                dest[1] += 20;
            }

            childHeight = Math.min(21 + rand.nextInt(10), this.height - dest[1] - 3);
            if (!this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 9, childHeight, i)) {
                this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 7, childHeight, i);
            }
        }

        for (i = 0; i < 4; ++i) {
            dest = this.getValidOpening(rand, i);
            if (dest[1] < this.height / 2) {
                dest[1] += 10;
            }

            childHeight = Math.min(21 + rand.nextInt(10), this.height - dest[1] - 3);
            if (!this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 9, childHeight, i)) {
                this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 7, childHeight, i);
            }
        }

        for (i = 0; i < 4; ++i) {
            dest = this.getValidOpening(rand, i);
            childHeight = Math.min(7 + rand.nextInt(6), this.height - dest[1] - 3);
            if (!this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 5, childHeight, i)) {
                this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 3, childHeight, i);
            }
        }

        for (i = 0; i < 4; ++i) {
            dest = this.getOutbuildingOpening(rand, i);
            childHeight = 11 + rand.nextInt(10);
            int childSize = 7 + rand.nextInt(2) * 2;

            this.makeTowerOutbuilding(list, rand, 1, dest[0], dest[1], dest[2], childSize, childHeight, i);
        }

        for (i = 0; i < 16; ++i) {
            dest = this.getValidOpening(rand, i % 4);
            childHeight = 6 + rand.nextInt(5);
            if (rand.nextInt(3) == 0 || !this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 5, childHeight, i % 4)) {
                this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], 3, childHeight, i % 4);
            }
        }

    }

    public int[] getOutbuildingOpening(Random rand, int rotation) {
        int rx = 0;
        byte ry = 1;
        int rz = 0;

        switch (rotation) {
        case 0:
            rx = this.size - 1;
            rz = 6 + rand.nextInt(8);
            break;

        case 1:
            rx = 1 + rand.nextInt(11);
            rz = this.size - 1;
            break;

        case 2:
            rx = 0;
            rz = 1 + rand.nextInt(8);
            break;

        case 3:
            rx = 3 + rand.nextInt(11);
            rz = 0;
        }

        return new int[] { rx, ry, rz};
    }

    public boolean makeTowerOutbuilding(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);
        ComponentTFTowerOutbuilding outbuilding = new ComponentTFTowerOutbuilding(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, outbuilding.func_74874_b());

        if (intersect != null && intersect != this) {
            return false;
        } else {
            list.add(outbuilding);
            outbuilding.func_74861_a(this, list, rand);
            this.addOpening(x, y, z, rotation);
            return true;
        }
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_74882_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, false, rand, StructureTFComponent.getStrongholdStones());
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);

        for (int x = 0; x < this.size; ++x) {
            for (int z = 0; z < this.size; ++z) {
                this.func_151554_b(world, Blocks.field_150347_e, 0, x, -1, z, sbb);
            }
        }

        this.nullifySkyLightForBoundingBox(world);
        if (this.height - this.highestOpening > 15) {
            this.highestOpening = this.height - 15;
        }

        this.makeStairs(world, rand, sbb);
        this.makeOpenings(world, sbb);
        this.decorateStairFloor(world, rand, sbb);
        this.makeStairwayCrossings(world, rand, sbb);
        this.makeLichRoom(world, rand, sbb);
        this.makeTowerPaintings(world, rand, sbb);
        return true;
    }

    protected void makeStairwayCrossings(World world, Random rand, StructureBoundingBox sbb) {
        int flights = this.highestOpening / 5 - 2;

        for (int i = 2 + rand.nextInt(2); i < flights; i += 1 + rand.nextInt(5)) {
            this.makeStairCrossing(world, rand, i, sbb);
        }

    }

    protected void makeStairCrossing(World world, Random rand, int flight, StructureBoundingBox sbb) {
        int temp = this.getCoordBaseMode();

        if (flight % 2 == 0) {
            this.setCoordBaseMode((this.getCoordBaseMode() + 1) % 4);
        }

        int floorMeta = rand.nextInt(2) == 0 ? 0 : 2;
        int floorLevel = 0 + flight * 5;

        int dx;
        int mobID;

        for (dx = 6; dx <= 8; ++dx) {
            for (mobID = 4; mobID <= 10; ++mobID) {
                this.func_151550_a(world, Blocks.field_150334_T, floorMeta, dx, floorLevel, mobID, sbb);
            }
        }

        ++floorLevel;
        byte b0 = 6;

        for (mobID = 3; mobID <= 11; ++mobID) {
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, b0, floorLevel, mobID, sbb);
        }

        dx = b0 + 1;

        for (mobID = 3; mobID <= 11; ++mobID) {
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx, floorLevel, mobID, sbb);
        }

        ++dx;

        for (mobID = 3; mobID <= 11; ++mobID) {
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, dx, floorLevel, mobID, sbb);
        }

        this.func_151550_a(world, Blocks.field_150334_T, floorMeta, 6, floorLevel - 1, 11, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, floorMeta, 8, floorLevel - 1, 3, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 5, floorLevel, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 9, floorLevel, 3, sbb);
        String s = "Skeleton";

        switch (rand.nextInt(4)) {
        case 0:
        case 1:
            s = "Skeleton";
            break;

        case 2:
            s = "Zombie";
            break;

        case 3:
            s = TFCreatures.getSpawnerNameFor("Swarm Spider");
        }

        this.placeSpawnerAtCurrentPosition(world, rand, 7, floorLevel + 2, 7, s, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 6, floorLevel + 1, 7, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 8, floorLevel + 1, 7, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 6, floorLevel + 2, 7, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 8, floorLevel + 2, 7, sbb);
        this.setCoordBaseMode(temp);
    }

    protected void makeLichRoom(World world, Random rand, StructureBoundingBox sbb) {
        int floorLevel = 2 + this.highestOpening / 5 * 5;

        this.makeLichFloor(world, floorLevel, this.highestOpening / 5 % 2, sbb);
        this.decorateLichChandelier(world, floorLevel, sbb);
        this.decoratePaintings(world, rand, floorLevel, sbb);
        this.decorateTorches(world, rand, floorLevel, sbb);
        this.func_151550_a(world, TFBlocks.bossSpawner, 1, this.size / 2, floorLevel + 2, this.size / 2, sbb);
    }

    protected void makeTowerPaintings(World world, Random rand, StructureBoundingBox sbb) {
        byte howMany = 10;

        this.generatePaintingsOnWall(world, rand, howMany, 0, 0, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 0, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 0, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 1, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 1, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 1, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 2, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 2, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 2, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 3, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 3, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, 0, 3, 0, sbb);
    }

    protected void makeLichFloor(World world, int floorLevel, int rotation, StructureBoundingBox sbb) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);

        for (int fx = 1; fx < 14; ++fx) {
            for (int fz = 1; fz < 14; ++fz) {
                if ((fx == 1 || fx == 2) && fz >= 6 && fz <= 12) {
                    if (fz == 6) {
                        this.func_151550_a(world, Blocks.field_150376_bx, 10, fx, floorLevel, fz, sbb);
                    }
                } else if ((fx == 12 || fx == 13) && fz >= 3 && fz <= 8) {
                    if (fz == 8) {
                        this.func_151550_a(world, Blocks.field_150376_bx, 10, fx, floorLevel, fz, sbb);
                    }
                } else if (fx >= 4 && fx <= 10 && fz >= 4 && fz <= 10) {
                    if ((fx != 4 || fz != 4) && (fx != 10 || fz != 10)) {
                        this.func_151550_a(world, Blocks.field_150359_w, 0, fx, floorLevel, fz, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150344_f, 2, fx, floorLevel, fz, sbb);
                    }
                } else if ((fx == 2 || fx == 3) && (fz == 2 || fz == 3)) {
                    this.func_151550_a(world, Blocks.field_150359_w, 0, fx, floorLevel, fz, sbb);
                } else if ((fx == 11 || fx == 12) && (fz == 11 || fz == 12)) {
                    this.func_151550_a(world, Blocks.field_150359_w, 0, fx, floorLevel, fz, sbb);
                } else {
                    this.func_151550_a(world, Blocks.field_150344_f, 2, fx, floorLevel, fz, sbb);
                }
            }
        }

        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, floorLevel + 1, 11, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, floorLevel + 1, 10, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, floorLevel + 2, 11, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 11, floorLevel + 1, 3, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 11, floorLevel + 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 11, floorLevel + 2, 3, sbb);
        this.setCoordBaseMode(temp);
    }

    protected void decorateLichChandelier(World world, int floorLevel, StructureBoundingBox sbb) {
        int cx = this.size / 2;
        int cy = floorLevel + 4;
        int cz = this.size / 2;

        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 2, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 1, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz + 2, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 1, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 2, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 1, cy, cz - 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz - 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz - 2, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 1, cy, cz - 1, sbb);
        ++cy;
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 2, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 1, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy, cz + 2, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx - 1, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx - 2, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx - 1, cy, cz - 1, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy, cz - 1, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy, cz - 2, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 1, cy, cz - 1, sbb);
        ++cy;
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy, cz + 1, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx - 1, cy, cz + 0, sbb);
        this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy, cz - 1, sbb);

        for (int y = floorLevel + 5; y < this.height - 1; ++y) {
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, y, cz + 0, sbb);
        }

    }

    protected void decoratePaintings(World world, Random rand, int floorLevel, StructureBoundingBox sbb) {
        byte howMany = 100;

        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 0, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 0, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 0, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 1, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 1, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 1, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 2, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 2, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 2, 0, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 3, 48, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 3, 32, sbb);
        this.generatePaintingsOnWall(world, rand, howMany, floorLevel, 3, 0, sbb);
    }

    protected void decorateTorches(World world, Random rand, int floorLevel, StructureBoundingBox sbb) {
        this.generateTorchesOnWall(world, rand, floorLevel, 0, sbb);
        this.generateTorchesOnWall(world, rand, floorLevel, 1, sbb);
        this.generateTorchesOnWall(world, rand, floorLevel, 2, sbb);
        this.generateTorchesOnWall(world, rand, floorLevel, 3, sbb);
    }

    protected void generateTorchesOnWall(World world, Random rand, int floorLevel, int direction, StructureBoundingBox sbb) {
        for (int i = 0; i < 10; ++i) {
            ChunkCoordinates wCoords = this.getRandomWallSpot(rand, floorLevel, direction, sbb);
            ChunkCoordinates tCoords = new ChunkCoordinates(wCoords);

            if (direction == 0) {
                ++tCoords.field_71573_c;
            }

            if (direction == 1) {
                --tCoords.field_71574_a;
            }

            if (direction == 2) {
                --tCoords.field_71573_c;
            }

            if (direction == 3) {
                ++tCoords.field_71574_a;
            }

            AxisAlignedBB torchBox = AxisAlignedBB.func_72330_a((double) tCoords.field_71574_a, (double) tCoords.field_71572_b, (double) tCoords.field_71573_c, (double) tCoords.field_71574_a + 1.0D, (double) tCoords.field_71572_b + 2.0D, (double) tCoords.field_71573_c + 1.0D);

            if (world.func_147439_a(tCoords.field_71574_a, tCoords.field_71572_b, tCoords.field_71573_c) == Blocks.field_150350_a && world.func_147439_a(tCoords.field_71574_a, tCoords.field_71572_b + 1, tCoords.field_71573_c) == Blocks.field_150350_a && world.func_72839_b((Entity) null, torchBox).size() == 0) {
                world.func_147465_d(tCoords.field_71574_a, tCoords.field_71572_b, tCoords.field_71573_c, Blocks.field_150422_aJ, 0, 2);
                world.func_147465_d(tCoords.field_71574_a, tCoords.field_71572_b + 1, tCoords.field_71573_c, Blocks.field_150478_aa, 5, 2);
            }
        }

    }
}
