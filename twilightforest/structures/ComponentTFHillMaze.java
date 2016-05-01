package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;

public class ComponentTFHillMaze extends StructureTFComponent {

    private static final int FLOOR_LEVEL = 1;
    private int hillSize;

    public ComponentTFHillMaze() {}

    public ComponentTFHillMaze(int i, int x, int y, int z, int hsize) {
        super(i);
        this.hillSize = hsize;
        this.setCoordBaseMode(0);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -this.getRadius(), 0, -this.getRadius(), this.getRadius() * 2, 5, this.getRadius() * 2, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151549_a(world, sbb, 0, 1, 0, this.getDiameter(), 3, this.getDiameter(), Blocks.field_150350_a, Blocks.field_150350_a, false);
        this.func_151549_a(world, sbb, 0, 0, 0, this.getDiameter(), 0, this.getDiameter(), TFBlocks.mazestone, TFBlocks.mazestone, false);
        this.func_151549_a(world, sbb, 0, 4, 0, this.getDiameter(), 4, this.getDiameter(), TFBlocks.mazestone, TFBlocks.mazestone, true);
        TFMaze maze = new TFMaze(this.getMazeSize(), this.getMazeSize());

        maze.wallBlockID = TFBlocks.mazestone;
        maze.wallBlockMeta = 3;
        maze.torchRarity = 0.05F;
        maze.setSeed(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * this.field_74887_e.field_78896_c));
        int nrooms = this.getMazeSize() / 3;
        int[] rcoords = new int[nrooms * 2];

        for (int i = 0; i < nrooms; ++i) {
            int rx;
            int rz;

            do {
                rx = maze.rand.nextInt(this.getMazeSize() - 2) + 1;
                rz = maze.rand.nextInt(this.getMazeSize() - 2) + 1;
            } while (this.isNearRoom(rx, rz, rcoords));

            maze.carveRoom1(rx, rz);
            rcoords[i * 2] = rx;
            rcoords[i * 2 + 1] = rz;
        }

        maze.generateRecursiveBacktracker(0, 0);
        maze.add4Exits();
        maze.copyToStructure(world, 0, 1, 0, this, sbb);
        this.decorate3x3Rooms(world, rcoords, sbb);
        return true;
    }

    public int getMazeSize() {
        return this.hillSize == 1 ? 11 : (this.hillSize == 2 ? 19 : 27);
    }

    public int getRadius() {
        return this.getMazeSize() * 2;
    }

    public int getDiameter() {
        return this.getMazeSize() * 4;
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

            dx = dx * 4 + 2;
            dz = dz * 4 + 2;
            this.decorate3x3Room(world, dx, dz, sbb);
        }

    }

    void decorate3x3Room(World world, int x, int z, StructureBoundingBox sbb) {
        Random roomRNG = new Random(world.func_72905_C() ^ (long) (x + z));

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
            mobID = "Spider";
            break;

        case 1:
            mobID = "Skeleton";
            break;

        case 2:
            mobID = "Zombie";
        }

        this.placeSpawnerAtCurrentPosition(world, rand, rx, 1, rz, mobID, sbb);
    }

    private void roomTreasure(World world, Random rand, int x, int z, int diameter, StructureBoundingBox sbb) {
        int rx = x + rand.nextInt(diameter) - diameter / 2;
        int rz = z + rand.nextInt(diameter) - diameter / 2;

        this.placeTreasureAtCurrentPosition(world, rand, rx, 1, rz, TFTreasure.labyrinth_room, sbb);
    }
}
