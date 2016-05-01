package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;

public class ComponentTFHedgeMaze extends StructureTFComponent {

    static int MSIZE = 16;
    static int RADIUS = ComponentTFHedgeMaze.MSIZE / 2 * 3 + 1;
    static int DIAMETER = 2 * ComponentTFHedgeMaze.RADIUS;
    static int FLOOR_LEVEL = 3;

    public ComponentTFHedgeMaze() {}

    public ComponentTFHedgeMaze(World world, Random rand, int i, int x, int y, int z) {
        super(i);
        this.setCoordBaseMode(0);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -ComponentTFHedgeMaze.RADIUS, -3, -ComponentTFHedgeMaze.RADIUS, ComponentTFHedgeMaze.RADIUS * 2, 10, ComponentTFHedgeMaze.RADIUS * 2, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        TFMaze maze = new TFMaze(ComponentTFHedgeMaze.MSIZE, ComponentTFHedgeMaze.MSIZE);

        maze.oddBias = 2;
        maze.torchBlockID = TFBlocks.firefly;
        maze.wallBlockID = TFBlocks.hedge;
        maze.wallBlockMeta = 0;
        maze.type = 4;
        maze.tall = 3;
        maze.roots = 3;
        maze.setSeed(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * this.field_74887_e.field_78896_c));

        int nrooms;

        for (nrooms = 0; nrooms <= ComponentTFHedgeMaze.DIAMETER; ++nrooms) {
            for (int rcoords = 0; rcoords <= ComponentTFHedgeMaze.DIAMETER; ++rcoords) {
                this.func_151550_a(world, Blocks.field_150349_c, 0, nrooms, ComponentTFHedgeMaze.FLOOR_LEVEL - 1, rcoords, sbb);
            }
        }

        this.func_151550_a(world, Blocks.field_150428_aP, 1, 0, ComponentTFHedgeMaze.FLOOR_LEVEL, 24, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 1, 0, ComponentTFHedgeMaze.FLOOR_LEVEL, 29, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 3, 50, ComponentTFHedgeMaze.FLOOR_LEVEL, 24, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 3, 50, ComponentTFHedgeMaze.FLOOR_LEVEL, 29, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 2, 24, ComponentTFHedgeMaze.FLOOR_LEVEL, 0, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 2, 29, ComponentTFHedgeMaze.FLOOR_LEVEL, 0, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 0, 24, ComponentTFHedgeMaze.FLOOR_LEVEL, 50, sbb);
        this.func_151550_a(world, Blocks.field_150428_aP, 0, 29, ComponentTFHedgeMaze.FLOOR_LEVEL, 50, sbb);
        nrooms = ComponentTFHedgeMaze.MSIZE / 3;
        int[] aint = new int[nrooms * 2];

        for (int i = 0; i < nrooms; ++i) {
            int rx;
            int rz;

            do {
                rx = maze.rand.nextInt(ComponentTFHedgeMaze.MSIZE - 2) + 1;
                rz = maze.rand.nextInt(ComponentTFHedgeMaze.MSIZE - 2) + 1;
            } while (this.isNearRoom(rx, rz, aint));

            maze.carveRoom1(rx, rz);
            aint[i * 2] = rx;
            aint[i * 2 + 1] = rz;
        }

        maze.generateRecursiveBacktracker(0, 0);
        maze.add4Exits();
        maze.copyToStructure(world, 1, ComponentTFHedgeMaze.FLOOR_LEVEL, 1, this, sbb);
        this.decorate3x3Rooms(world, aint, sbb);
        return true;
    }

    protected boolean isNearRoom(int dx, int dz, int[] rcoords) {
        if (dx == 1 && dz == 1) {
            return true;
        } else {
            for (int i = 0; i < rcoords.length / 2; ++i) {
                int rx = rcoords[i * 2];
                int rz = rcoords[i * 2 + 1];

                if ((rx != 0 || rz != 0) && Math.abs(dx - rx) < 3 && Math.abs(dz - rz) < 3) {
                    return true;
                }
            }

            return false;
        }
    }

    void decorate3x3Rooms(World world, int[] rcoords, StructureBoundingBox sbb) {
        for (int i = 0; i < rcoords.length / 2; ++i) {
            int dx = rcoords[i * 2];
            int dz = rcoords[i * 2 + 1];

            dx = dx * 3 + 3;
            dz = dz * 3 + 3;
            this.decorate3x3Room(world, dx, dz, sbb);
        }

    }

    void decorate3x3Room(World world, int x, int z, StructureBoundingBox sbb) {
        Random roomRNG = new Random(world.func_72905_C() ^ (long) (x + z));

        this.roomJackO(world, roomRNG, x, z, 8, sbb);
        if (roomRNG.nextInt(4) == 0) {
            this.roomJackO(world, roomRNG, x, z, 8, sbb);
        }

        this.roomSpawner(world, roomRNG, x, z, 8, sbb);
        this.roomTreasure(world, roomRNG, x, z, 8, sbb);
        if (roomRNG.nextInt(4) == 0) {
            this.roomTreasure(world, roomRNG, x, z, 8, sbb);
        }

    }

    private void roomSpawner(World world, Random rand, int x, int z, int diameter, StructureBoundingBox sbb) {
        int rx = x + rand.nextInt(diameter) - diameter / 2;
        int rz = z + rand.nextInt(diameter) - diameter / 2;
        String mobID;

        switch (rand.nextInt(3)) {
        case 0:
        default:
            mobID = TFCreatures.getSpawnerNameFor("Hedge Spider");
            break;

        case 1:
            mobID = TFCreatures.getSpawnerNameFor("Swarm Spider");
            break;

        case 2:
            mobID = TFCreatures.getSpawnerNameFor("Hostile Wolf");
        }

        this.placeSpawnerAtCurrentPosition(world, rand, rx, ComponentTFHedgeMaze.FLOOR_LEVEL, rz, mobID, sbb);
    }

    private void roomTreasure(World world, Random rand, int x, int z, int diameter, StructureBoundingBox sbb) {
        int rx = x + rand.nextInt(diameter) - diameter / 2;
        int rz = z + rand.nextInt(diameter) - diameter / 2;

        this.placeTreasureAtCurrentPosition(world, rand, rx, ComponentTFHedgeMaze.FLOOR_LEVEL, rz, TFTreasure.hedgemaze, sbb);
    }

    private void roomJackO(World world, Random rand, int x, int z, int diameter, StructureBoundingBox sbb) {
        int rx = x + rand.nextInt(diameter) - diameter / 2;
        int rz = z + rand.nextInt(diameter) - diameter / 2;

        this.func_151550_a(world, Blocks.field_150428_aP, rand.nextInt(4), rx, ComponentTFHedgeMaze.FLOOR_LEVEL, rz, sbb);
    }
}
