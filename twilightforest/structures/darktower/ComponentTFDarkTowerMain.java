package twilightforest.structures.darktower;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;
import twilightforest.item.TFItems;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.StructureTFDecorator;
import twilightforest.structures.TFMaze;
import twilightforest.world.TFGenSmallRainboak;
import twilightforest.world.TFGenSmallTwilightOak;

public class ComponentTFDarkTowerMain extends ComponentTFDarkTowerWing {

    private boolean placedKeys;

    public ComponentTFDarkTowerMain() {
        this.placedKeys = false;
    }

    public ComponentTFDarkTowerMain(World world, Random rand, int index, int x, int y, int z) {
        this(world, rand, index, x + 10, y, z + 10, 2);
    }

    public ComponentTFDarkTowerMain(World world, Random rand, int index, int x, int y, int z, int rotation) {
        super(index, x, y, z, 19, 56 + rand.nextInt(32) / 5 * 5, rotation);
        this.placedKeys = false;
        if (this.field_74887_e.field_78894_e > 245) {
            int amtToLower = (this.field_74887_e.field_78894_e - 245) / 5 * 5 + 5;

            FMLLog.info("[TwilightForest] Lowering Dark Tower max height by %d to be within world bounds", new Object[] { Integer.valueOf(amtToLower)});
            this.height -= amtToLower;
            this.field_74887_e.field_78894_e -= amtToLower;
        }

        if (this.deco == null) {
            this.deco = new StructureDecoratorDarkTower();
        }

    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        if (this.func_74877_c() > 0) {
            this.addOpening(0, 1, this.size / 2, 2);
        }

        int mainDir = -1;
        int possibleKeyTowers;
        int[] smallTowers;
        int i;

        if (this.func_74877_c() < 2) {
            mainDir = rand.nextInt(4);

            for (possibleKeyTowers = 0; possibleKeyTowers < 4; ++possibleKeyTowers) {
                if (possibleKeyTowers != mainDir) {
                    smallTowers = this.getValidOpening(rand, possibleKeyTowers);
                    i = this.validateChildHeight(21 + rand.nextInt(10), 11);
                    this.makeTowerWing(list, rand, this.func_74877_c(), smallTowers[0], smallTowers[1], smallTowers[2], 11, i, possibleKeyTowers);
                }
            }
        } else {
            for (possibleKeyTowers = 0; possibleKeyTowers < 4; ++possibleKeyTowers) {
                smallTowers = this.getValidOpening(rand, possibleKeyTowers);
                this.makeBossTrapWing(list, rand, this.func_74877_c(), smallTowers[0], smallTowers[1], smallTowers[2], possibleKeyTowers);
            }
        }

        if (this.func_74877_c() > 0) {
            for (possibleKeyTowers = 0; possibleKeyTowers < 4; ++possibleKeyTowers) {
                if (possibleKeyTowers != 2) {
                    smallTowers = this.getValidOpening(rand, possibleKeyTowers);
                    smallTowers[1] = 1;
                    i = this.validateChildHeight(21 + rand.nextInt(10), 11);
                    this.makeTowerWing(list, rand, this.func_74877_c(), smallTowers[0], smallTowers[1], smallTowers[2], 11, i, possibleKeyTowers);
                }
            }

            this.makeABeard(parent, list, rand);
        } else {
            for (possibleKeyTowers = 0; possibleKeyTowers < 4; possibleKeyTowers += 2) {
                smallTowers = this.getValidOpening(rand, possibleKeyTowers);
                smallTowers[1] = 1;
                i = this.validateChildHeight(10 + rand.nextInt(5), 9);
                this.makeEntranceTower(list, rand, 5, smallTowers[0], smallTowers[1], smallTowers[2], 9, i, possibleKeyTowers);
            }
        }

        if (mainDir > -1) {
            int[] aint = this.getValidOpening(rand, mainDir);

            this.makeNewLargeTower(list, rand, this.func_74877_c() + 1, aint[0], aint[1], aint[2], mainDir);
        }

        this.makeARoof(parent, list, rand);
        if (!this.placedKeys && this.func_74877_c() < 2) {
            ArrayList arraylist = new ArrayList();
            int i = 0;
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Object towerNum = iterator.next();

                if (towerNum instanceof ComponentTFDarkTowerWing) {
                    ComponentTFDarkTowerWing wing = (ComponentTFDarkTowerWing) towerNum;

                    if (wing.size == 9 && wing.func_74877_c() == this.func_74877_c()) {
                        ++i;
                        arraylist.add(wing);
                    }
                }
            }

            for (i = 0; i < 4; ++i) {
                if (arraylist.size() < 1) {
                    FMLLog.warning("[TwilightForest] Dark forest tower could not find four small towers to place keys in.", new Object[0]);
                    break;
                }

                int j = rand.nextInt(arraylist.size());

                ((ComponentTFDarkTowerWing) arraylist.get(j)).setKeyTower(true);
                arraylist.remove(j);
            }

            this.placedKeys = true;
        }

    }

    private boolean makeEntranceTower(List list, Random rand, int index, int x, int y, int z, int childSize, int childHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 5, direction);
        ComponentTFDarkTowerEntranceBridge bridge = new ComponentTFDarkTowerEntranceBridge(index, dx[0], dx[1], dx[2], childSize, childHeight, direction);

        list.add(bridge);
        bridge.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }

    private boolean makeNewLargeTower(List list, Random rand, int index, int x, int y, int z, int rotation) {
        byte wingSize = 15;
        byte wingHeight = 56;
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 5, direction);
        ComponentTFDarkTowerMainBridge bridge = new ComponentTFDarkTowerMainBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        list.add(bridge);
        bridge.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation, EnumDarkTowerDoor.LOCKED);
        return true;
    }

    private boolean makeBossTrapWing(List list, Random rand, int index, int x, int y, int z, int rotation) {
        byte wingSize = 11;
        byte wingHeight = 9;
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 5, direction);
        ComponentTFDarkTowerBossBridge bridge = new ComponentTFDarkTowerBossBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        list.add(bridge);
        bridge.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        if (this.func_74877_c() < 2) {
            super.makeARoof(parent, list, rand);
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.makeEncasedWalls(world, rand, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        int totalFloors;

        if (this.func_74877_c() == 0) {
            for (totalFloors = 0; totalFloors < this.size; ++totalFloors) {
                for (int beamMaze = 0; beamMaze < this.size; ++beamMaze) {
                    this.func_151554_b(world, this.deco.accentID, this.deco.accentMeta, totalFloors, -1, beamMaze, sbb);
                }
            }
        }

        this.nullifySkyLightForBoundingBox(world);
        totalFloors = this.height / 5;
        boolean flag = decoRNG.nextBoolean();
        int centerFloors = flag ? 4 : totalFloors / 2;
        int bottomFloors = (totalFloors - centerFloors) / 2;
        int i = totalFloors - bottomFloors * 2;
        int topFloorsStartY = this.height - (bottomFloors * 5 + 1);

        this.addThreeQuarterFloors(world, decoRNG, sbb, 0, bottomFloors * 5);
        if (this.func_74877_c() < 2) {
            this.addThreeQuarterFloors(world, decoRNG, sbb, topFloorsStartY, this.height - 1);
        } else {
            this.addThreeQuarterFloorsDecorateBoss(world, decoRNG, sbb, topFloorsStartY, this.height - 1);
            this.destroyTower(world, decoRNG, 12, this.height + 4, 3, 4, sbb);
            this.destroyTower(world, decoRNG, 3, this.height + 4, 12, 4, sbb);
            this.destroyTower(world, decoRNG, 3, this.height + 4, 3, 4, sbb);
            this.destroyTower(world, decoRNG, 12, this.height + 4, 12, 4, sbb);
            this.destroyTower(world, decoRNG, 8, this.height + 4, 8, 5, sbb);
            this.decorateBossSpawner(world, decoRNG, sbb, 0, this.height - 6);
        }

        if (flag) {
            this.addTimberMaze(world, decoRNG, sbb, bottomFloors * 5, topFloorsStartY);
        } else {
            this.addBuilderPlatforms(world, decoRNG, sbb, bottomFloors * 5, topFloorsStartY);
        }

        this.makeOpenings(world, sbb);
        return true;
    }

    protected void addThreeQuarterFloors(World world, Random decoRNG, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 5;
        int rotation = (this.field_74887_e.field_78895_b + bottom) % 4;

        if (bottom == 0) {
            this.makeLargeStairsUp(world, sbb, rotation, 0);
            rotation += 3;
            rotation %= 4;
            this.makeBottomEntrance(world, decoRNG, sbb, rotation, bottom);
            bottom += spacing;
        }

        for (int y = bottom; y < top; y += spacing) {
            boolean isBottomFloor = y == bottom && bottom != spacing;
            boolean isTopFloor = y >= top - spacing;
            boolean isTowerTopFloor = y >= this.height - spacing - 2;

            this.makeThreeQuarterFloor(world, sbb, rotation, y, isBottomFloor, isTowerTopFloor);
            if (!isTopFloor) {
                this.makeLargeStairsUp(world, sbb, rotation, y);
            }

            if (!isTopFloor || isTowerTopFloor) {
                this.decorateFloor(world, decoRNG, sbb, rotation, y, isBottomFloor, isTopFloor);
            }

            rotation += 3;
            rotation %= 4;
        }

    }

    protected void addThreeQuarterFloorsDecorateBoss(World world, Random decoRNG, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 5;
        int rotation = (this.field_74887_e.field_78895_b + bottom) % 4;

        if (bottom == 0) {
            this.makeLargeStairsUp(world, sbb, rotation, 0);
            rotation += 3;
            rotation %= 4;
            bottom += spacing;
        }

        for (int y = bottom; y < top; y += spacing) {
            boolean isBottomFloor = y == bottom && bottom != spacing;
            boolean isTopFloor = y >= top - spacing;
            boolean isTowerTopFloor = y >= this.height - spacing - 2;

            this.makeThreeQuarterFloor(world, sbb, rotation, y, isBottomFloor, isTowerTopFloor);
            if (!isTopFloor) {
                this.makeLargeStairsUp(world, sbb, rotation, y);
                this.decorateExperiment(world, decoRNG, sbb, rotation, y);
            }

            rotation += 3;
            rotation %= 4;
        }

    }

    private void decorateFloor(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y, boolean isBottom, boolean isTop) {
        if (isTop) {
            switch (decoRNG.nextInt(3)) {
            case 0:
            default:
                this.decorateAquarium(world, decoRNG, sbb, rotation, y);
                break;

            case 1:
                this.decorateBotanical(world, decoRNG, sbb, rotation, y);
                break;

            case 2:
                this.decorateNetherwart(world, decoRNG, sbb, rotation, y, isTop);
            }
        } else if (isBottom) {
            switch (decoRNG.nextInt(4)) {
            case 0:
            default:
                this.decorateAquarium(world, decoRNG, sbb, rotation, y);
                break;

            case 1:
                this.decorateBotanical(world, decoRNG, sbb, rotation, y);
                break;

            case 2:
                if (y + this.field_74887_e.field_78895_b > 64) {
                    this.decorateNetherwart(world, decoRNG, sbb, rotation, y, isTop);
                    break;
                }

            case 3:
                this.decorateForge(world, decoRNG, sbb, rotation, y);
            }
        } else {
            switch (decoRNG.nextInt(8)) {
            case 0:
            case 1:
            default:
                this.decorateReappearingMaze(world, decoRNG, sbb, rotation, y);
                break;

            case 2:
                this.decorateUnbuilderMaze(world, decoRNG, sbb, rotation, y);
                break;

            case 3:
                this.decorateAquarium(world, decoRNG, sbb, rotation, y);
                break;

            case 4:
                this.decorateBotanical(world, decoRNG, sbb, rotation, y);
                break;

            case 5:
                if (y + this.field_74887_e.field_78895_b > 64) {
                    this.decorateNetherwart(world, decoRNG, sbb, rotation, y, isTop);
                    break;
                }

            case 6:
                this.decorateLounge(world, decoRNG, sbb, rotation, y);
                break;

            case 7:
                this.decorateForge(world, decoRNG, sbb, rotation, y);
            }
        }

    }

    protected void makeThreeQuarterFloor(World world, StructureBoundingBox sbb, int rotation, int y, boolean isBottom, boolean isTowerTopFloor) {
        int half = this.size / 2;

        this.fillBlocksRotated(world, sbb, half + 1, y, 1, this.size - 2, y, half + 1, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 1, y, half + 1, this.size - 2, y, this.size - 2, this.deco.blockID, this.deco.blockMeta, rotation);
        int startZ = isBottom ? 1 : 3;

        this.fillBlocksRotated(world, sbb, 1, y, half, half, y, half, this.deco.accentID, this.deco.accentMeta, rotation);
        this.fillBlocksRotated(world, sbb, half, y, startZ, half, y, half, this.deco.accentID, this.deco.accentMeta, rotation);
        this.fillBlocksRotated(world, sbb, 1, y + 1, half, half, y + 1, half, this.deco.fenceID, this.deco.fenceMeta, rotation);
        this.fillBlocksRotated(world, sbb, half, y + 1, startZ, half, y + 1, half, this.deco.fenceID, this.deco.fenceMeta, rotation);
        if (isTowerTopFloor) {
            this.fillBlocksRotated(world, sbb, 1, y + 0, half - 2, 3, y + 0, half, this.deco.accentID, this.deco.accentMeta, rotation);
            this.fillBlocksRotated(world, sbb, 1, y + 1, half - 2, 3, y + 1, half, this.deco.fenceID, this.deco.fenceMeta, rotation);
            this.fillBlocksRotated(world, sbb, 1, y + 0, half - 1, 2, y + 0, half, this.deco.blockID, this.deco.blockMeta, rotation);
            this.fillBlocksRotated(world, sbb, 1, y + 1, half - 1, 2, y + 1, half, Blocks.field_150350_a, 0, rotation);
        }

    }

    protected void makeLargeStairsUp(World world, StructureBoundingBox sbb, int rotation, int y) {
        for (int i = 0; i < 5; ++i) {
            int z = this.size / 2 - i + 4;
            int sy = y + i + 1;

            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 1, sy, z, rotation, sbb);
            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 2, sy, z, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 1, sy, z - 1, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 2, sy, z - 1, rotation, sbb);
            this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, 3, sy, z - 1, rotation, sbb);
            if (i > 0 && i < 4) {
                this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 3, sy, z, rotation, sbb);
                this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 3, sy + 1, z, rotation, sbb);
                this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 3, sy + 2, z, rotation, sbb);
            } else if (i == 0) {
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 3, sy, z, rotation, sbb);
            }
        }

    }

    private void decorateReappearingMaze(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        byte mazeSize = 6;
        TFMaze maze = new TFMaze(mazeSize, mazeSize);

        maze.setSeed(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 90342903) + (long) (y * 90342903) ^ (long) this.field_74887_e.field_78896_c);

        int x;

        for (x = 0; x < 13; ++x) {
            maze.putRaw(x, 0, 5);
            maze.putRaw(x, 12, 5);
            maze.putRaw(0, x, 5);
            maze.putRaw(12, x, 5);
        }

        maze.doorRarity = 0.3F;
        int z;

        switch (rotation) {
        case 0:
            for (x = 1; x < 6; ++x) {
                for (z = 1; z < 6; ++z) {
                    maze.putRaw(x, z, 5);
                }
            }

            maze.putRaw(1, 6, 5);
            maze.putRaw(1, 7, 5);
            maze.putRaw(1, 8, 5);
            maze.putRaw(1, 9, 5);
            maze.putRaw(1, 10, 6);
            maze.putRaw(6, 1, 5);
            maze.putRaw(7, 1, 5);
            maze.putRaw(8, 1, 6);
            maze.generateRecursiveBacktracker(0, 5);
            break;

        case 1:
            for (x = 7; x < 12; ++x) {
                for (z = 1; z < 6; ++z) {
                    maze.putRaw(x, z, 5);
                }
            }

            maze.putRaw(6, 1, 5);
            maze.putRaw(5, 1, 5);
            maze.putRaw(4, 1, 5);
            maze.putRaw(3, 1, 5);
            maze.putRaw(2, 1, 6);
            maze.putRaw(11, 6, 5);
            maze.putRaw(11, 7, 5);
            maze.putRaw(11, 8, 6);
            maze.generateRecursiveBacktracker(0, 0);
            break;

        case 2:
            for (x = 7; x < 12; ++x) {
                for (z = 7; z < 12; ++z) {
                    maze.putRaw(x, z, 5);
                }
            }

            maze.putRaw(11, 6, 5);
            maze.putRaw(11, 5, 5);
            maze.putRaw(11, 4, 5);
            maze.putRaw(11, 3, 5);
            maze.putRaw(11, 2, 6);
            maze.putRaw(6, 11, 5);
            maze.putRaw(5, 11, 5);
            maze.putRaw(4, 11, 6);
            maze.generateRecursiveBacktracker(5, 0);
            break;

        case 3:
            for (x = 1; x < 6; ++x) {
                for (z = 7; z < 12; ++z) {
                    maze.putRaw(x, z, 5);
                }
            }

            maze.putRaw(6, 11, 5);
            maze.putRaw(7, 11, 5);
            maze.putRaw(8, 11, 5);
            maze.putRaw(9, 11, 5);
            maze.putRaw(10, 11, 6);
            maze.putRaw(1, 6, 5);
            maze.putRaw(1, 5, 5);
            maze.putRaw(1, 4, 6);
            maze.generateRecursiveBacktracker(5, 5);
        }

        maze.wallBlockID = this.deco.blockID;
        maze.wallBlockMeta = this.deco.blockMeta;
        maze.headBlockID = this.deco.accentID;
        maze.headBlockMeta = this.deco.accentMeta;
        maze.pillarBlockID = this.deco.accentID;
        maze.pillarBlockMeta = this.deco.accentMeta;
        maze.doorBlockID = TFBlocks.towerDevice;
        maze.doorBlockMeta = 0;
        maze.torchRarity = 0.0F;
        maze.tall = 3;
        maze.head = 1;
        maze.oddBias = 2;
        maze.copyToStructure(world, 0, y + 1, 0, this, sbb);
        this.decorateMazeDeadEnds(world, decoRNG, maze, y, rotation, sbb);
    }

    protected void decorateMazeDeadEnds(World world, Random decoRNG, TFMaze maze, int y, int rotation, StructureBoundingBox sbb) {
        for (int x = 0; x < maze.width; ++x) {
            for (int z = 0; z < maze.depth; ++z) {
                if (!maze.isWall(x, z, x - 1, z) && maze.isWall(x, z, x + 1, z) && maze.isWall(x, z, x, z - 1) && maze.isWall(x, z, x, z + 1)) {
                    this.decorateDeadEnd(world, decoRNG, maze, x, y, z, 3, rotation, sbb);
                }

                if (maze.isWall(x, z, x - 1, z) && !maze.isWall(x, z, x + 1, z) && maze.isWall(x, z, x, z - 1) && maze.isWall(x, z, x, z + 1)) {
                    this.decorateDeadEnd(world, decoRNG, maze, x, y, z, 1, rotation, sbb);
                }

                if (maze.isWall(x, z, x - 1, z) && maze.isWall(x, z, x + 1, z) && !maze.isWall(x, z, x, z - 1) && maze.isWall(x, z, x, z + 1)) {
                    this.decorateDeadEnd(world, decoRNG, maze, x, y, z, 0, rotation, sbb);
                }

                if (maze.isWall(x, z, x - 1, z) && maze.isWall(x, z, x + 1, z) && maze.isWall(x, z, x, z - 1) && !maze.isWall(x, z, x, z + 1)) {
                    this.decorateDeadEnd(world, decoRNG, maze, x, y, z, 2, rotation, sbb);
                }
            }
        }

    }

    private void decorateDeadEnd(World world, Random decoRNG, TFMaze maze, int mx, int y, int mz, int facing, int rotation, StructureBoundingBox sbb) {
        int x = mx * 3 + 1;
        int z = mz * 3 + 1;

        switch (facing) {
        case 0:
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 0, y + 1, z + 1, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 1, y + 1, z + 1, sbb);
            this.func_151550_a(world, Blocks.field_150486_ae, 0, x + 0, y + 2, z + 1, sbb);
            this.placeTreasureAtCurrentPosition(world, decoRNG, x + 1, y + 2, z + 1, TFTreasure.darktower_cache, sbb);
            break;

        case 1:
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 0, y + 1, z + 0, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 0, y + 1, z + 1, sbb);
            this.func_151550_a(world, Blocks.field_150486_ae, rotation, x + 0, y + 2, z + 0, sbb);
            this.placeTreasureAtCurrentPosition(world, decoRNG, x + 0, y + 2, z + 1, TFTreasure.darktower_cache, sbb);
            break;

        case 2:
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 0, y + 1, z + 0, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 1, y + 1, z + 0, sbb);
            this.func_151550_a(world, Blocks.field_150486_ae, rotation, x + 0, y + 2, z + 0, sbb);
            this.placeTreasureAtCurrentPosition(world, decoRNG, x + 1, y + 2, z + 0, TFTreasure.darktower_cache, sbb);
            break;

        case 3:
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 1, y + 1, z + 0, sbb);
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x + 1, y + 1, z + 1, sbb);
            this.func_151550_a(world, Blocks.field_150486_ae, rotation, x + 1, y + 2, z + 0, sbb);
            this.placeTreasureAtCurrentPosition(world, decoRNG, x + 1, y + 2, z + 1, TFTreasure.darktower_cache, sbb);
        }

    }

    private void decorateUnbuilderMaze(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        for (int x = this.size / 2; x < this.size - 1; ++x) {
            for (int z = 3; z < this.size - 1; ++z) {
                int ay;

                if (x % 2 == 1 && z % 2 == 1) {
                    for (ay = 1; ay < 5; ++ay) {
                        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x, y + ay, z, rotation, sbb);
                    }
                } else if (x % 2 == 1 || z % 2 == 1) {
                    for (ay = 1; ay < 5; ++ay) {
                        this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, x, y + ay, z, rotation, sbb);
                    }

                    if (x != this.size / 2 && x != this.size - 2 && z != this.size - 2) {
                        ay = decoRNG.nextInt(4) + 1;
                        this.placeBlockRotated(world, Blocks.field_150350_a, 0, x, y + ay, z, rotation, sbb);
                        if (x > this.size - 7) {
                            ay = decoRNG.nextInt(3) + 1;
                            this.placeBlockRotated(world, Blocks.field_150350_a, 0, x, y + ay, z, rotation, sbb);
                        }
                    }
                }
            }
        }

        this.placeBlockRotated(world, TFBlocks.towerDevice, 9, 15, y + 2, 7, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 9, 11, y + 3, 7, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 9, 15, y + 2, 13, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 9, 11, y + 3, 13, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 9, 5, y + 3, 13, rotation, sbb);
    }

    private void decorateLounge(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        this.fillBlocksRotated(world, sbb, 17, y + 1, 1, 17, y + 4, 6, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 1, 17, y + 4, 1, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 2, 16, y + 1, 5, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 2, 12, y + 1, 6, this.deco.stairID, this.getStairMeta(0 + rotation), rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 6, 16, y + 1, 6, this.deco.stairID, this.getStairMeta(3 + rotation), rotation);
        this.makeDispenserPillar(world, this.deco, 13, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeDispenserPillar(world, this.deco, 15, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeDispenserPillar(world, this.deco, 17, y, 3, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeDispenserPillar(world, this.deco, 17, y, 5, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, this.deco, 12, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeStonePillar(world, this.deco, 17, y, 6, this.getStairMeta(0 + rotation), rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150382_bo, 0, 13, y + 2, 5, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150383_bp, 3, 15, y + 2, 3, rotation, sbb);
        this.fillBlocksRotated(world, sbb, 10, y + 1, 17, 17, y + 4, 17, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 17, y + 1, 10, 17, y + 4, 17, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 11, y + 1, 17, 12, y + 4, 17, Blocks.field_150342_X, 0, rotation);
        this.fillBlocksRotated(world, sbb, 14, y + 1, 17, 15, y + 4, 17, Blocks.field_150342_X, 0, rotation);
        this.fillBlocksRotated(world, sbb, 17, y + 1, 11, 17, y + 4, 12, Blocks.field_150342_X, 0, rotation);
        this.fillBlocksRotated(world, sbb, 17, y + 1, 14, 17, y + 4, 15, Blocks.field_150342_X, 0, rotation);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation) + 4, 13, y + 1, 14, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, 14, y + 1, 14, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 14, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation) + 4, 13, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 11, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 13, y + 1, 11, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150379_bu, 0, 8, y + 3, 8, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, decoRNG.nextBoolean() ? 0 : 7, 8, y + 2, 8, rotation, sbb);
        this.placeTreePlanter(world, decoRNG.nextInt(5), 6, y + 1, 12, rotation, sbb);
    }

    private void makeDispenserPillar(World world, StructureTFDecorator forgeDeco, int x, int y, int z, int stairMeta, int rotation, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, forgeDeco.stairID, stairMeta + 4, x, y + 2, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150367_z, stairMeta + 4, x, y + 3, z, rotation, sbb);
        this.placeBlockRotated(world, forgeDeco.stairID, stairMeta, x, y + 4, z, rotation, sbb);
    }

    private void decorateBossSpawner(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        this.placeBlockRotated(world, TFBlocks.bossSpawner, 3, 9, y + 4, 9, rotation, sbb);
    }

    private void decorateExperiment(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        this.fillBlocksRotated(world, sbb, 17, y + 1, 1, 17, y + 4, 6, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 1, 17, y + 4, 1, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 2, 16, y + 1, 5, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 2, 12, y + 1, 6, this.deco.stairID, this.getStairMeta(0 + rotation), rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 6, 16, y + 1, 6, this.deco.stairID, this.getStairMeta(3 + rotation), rotation);
        this.makeWoodPillar(world, this.deco, 13, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeWoodPillar(world, this.deco, 15, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeWoodPillar(world, this.deco, 17, y, 3, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeWoodPillar(world, this.deco, 17, y, 5, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, this.deco, 12, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeStonePillar(world, this.deco, 17, y, 6, this.getStairMeta(0 + rotation), rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150462_ai, 0, 14, y + 2, 4, rotation, sbb);
        this.placeItemFrameRotated(world, 13, y + 2, 1, rotation, 0, new ItemStack(TFItems.borerEssence), sbb);
        this.placeItemFrameRotated(world, 14, y + 2, 1, rotation, 0, new ItemStack(Items.field_151137_ax), sbb);
        this.placeItemFrameRotated(world, 15, y + 2, 1, rotation, 0, new ItemStack(TFItems.borerEssence), sbb);
        this.placeItemFrameRotated(world, 13, y + 3, 1, rotation, 0, new ItemStack(Items.field_151137_ax), sbb);
        this.placeItemFrameRotated(world, 14, y + 3, 1, rotation, 0, new ItemStack(Items.field_151073_bk), sbb);
        this.placeItemFrameRotated(world, 15, y + 3, 1, rotation, 0, new ItemStack(Items.field_151137_ax), sbb);
        this.placeItemFrameRotated(world, 13, y + 4, 1, rotation, 0, new ItemStack(TFItems.borerEssence), sbb);
        this.placeItemFrameRotated(world, 14, y + 4, 1, rotation, 0, new ItemStack(Items.field_151137_ax), sbb);
        this.placeItemFrameRotated(world, 15, y + 4, 1, rotation, 0, new ItemStack(TFItems.borerEssence), sbb);
        this.placeItemFrameRotated(world, 17, y + 2, 3, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 1), sbb);
        this.placeItemFrameRotated(world, 17, y + 2, 4, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 0), sbb);
        this.placeItemFrameRotated(world, 17, y + 2, 5, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 1), sbb);
        this.placeItemFrameRotated(world, 17, y + 3, 3, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 0), sbb);
        this.placeItemFrameRotated(world, 17, y + 3, 4, rotation, 1, new ItemStack(TFItems.carminite), sbb);
        this.placeItemFrameRotated(world, 17, y + 3, 5, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 0), sbb);
        this.placeItemFrameRotated(world, 17, y + 4, 3, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 1), sbb);
        this.placeItemFrameRotated(world, 17, y + 4, 4, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 0), sbb);
        this.placeItemFrameRotated(world, 17, y + 4, 5, rotation, 1, new ItemStack(TFBlocks.towerWood, 1, 1), sbb);
        if (y < this.height - 13) {
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 13, y + 1, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 15, y + 1, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 13, y + 1, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 15, y + 1, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 13, y + 1, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 14, y + 1, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 15, y + 1, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 14, y + 1, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 14, y + 1, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 13, y + 2, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 15, y + 2, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 13, y + 2, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 15, y + 2, 15, rotation, sbb);
            this.placeBlockRotated(world, TFBlocks.towerDevice, 12, 14, y + 2, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 13, y + 3, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 15, y + 3, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 13, y + 3, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150343_Z, 0, 15, y + 3, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 13, y + 3, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 14, y + 3, 13, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 15, y + 3, 14, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150424_aL, 0, 14, y + 3, 15, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 14, y + 3, 14, rotation, sbb);
        }

        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 14, y + 1, 17, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 4), 13, y + 1, 17, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150331_J, 5 - this.getStairMeta(3 + rotation), 14, y + 2, 17, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 14, y + 2, 16, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 17, y + 1, 14, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 2), 17, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150331_J, 5 - this.getStairMeta(2 + rotation), 17, y + 2, 14, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 16, y + 2, 14, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 14, y + 2, 11, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 14, y + 1, 11, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 4) + 8, 13, y + 1, 11, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150331_J, 5 - this.getStairMeta(1 + rotation), 14, y + 2, 10, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 14, y + 1, 9, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 4), 13, y + 1, 9, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150320_F, 5 - this.getStairMeta(1 + rotation), 14, y + 2, 9, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150451_bX, 0, 11, y + 2, 14, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 11, y + 1, 14, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 2) + 8, 11, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150331_J, 5 - this.getStairMeta(0 + rotation), 10, y + 2, 14, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 9, y + 1, 14, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 2), 9, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150320_F, 5 - this.getStairMeta(0 + rotation), 9, y + 2, 14, rotation, sbb);
    }

    private void makeWoodPillar(World world, StructureTFDecorator forgeDeco, int x, int y, int z, int stairMeta, int rotation, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, TFBlocks.log, 3, x, y + 2, z, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.log, 3, x, y + 3, z, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.log, 3, x, y + 4, z, rotation, sbb);
    }

    private void placeItemFrameRotated(World world, int x, int y, int z, int rotation, int direction, ItemStack itemStack, StructureBoundingBox sbb) {
        int dx = this.getXWithOffsetAsIfRotated(x, z, rotation);
        int dy = this.func_74862_a(y);
        int dz = this.getZWithOffsetAsIfRotated(x, z, rotation);

        if (sbb.func_78890_b(dx, dy, dz)) {
            EntityItemFrame frame = new EntityItemFrame(world, dx, dy, dz, (this.getCoordBaseMode() + direction + rotation) % 4);

            if (itemStack != null) {
                frame.func_82334_a(itemStack);
            }

            world.func_72838_d(frame);
        }

    }

    private void decorateAquarium(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        this.makePillarFrame(world, sbb, this.deco, rotation, 12, y, 3, 4, 4, 13, false);
        this.fillBlocksRotated(world, sbb, 13, y + 4, 4, 14, y + 4, 14, Blocks.field_150358_i, 0, rotation);
        this.makePillarFrame(world, sbb, this.deco, rotation, 6, y, 12, 4, 4, 4, false);
        this.fillBlocksRotated(world, sbb, 6, y + 5, 12, 9, y + 5, 15, this.deco.accentID, this.deco.accentMeta, rotation);
        this.fillBlocksRotated(world, sbb, 7, y + 4, 13, 8, y + 5, 14, Blocks.field_150358_i, 0, rotation);
    }

    private void decorateForge(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        StructureTFDecorator forgeDeco = this.deco;

        this.fillBlocksRotated(world, sbb, 17, y + 1, 1, 17, y + 4, 6, forgeDeco.pillarID, forgeDeco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 1, 17, y + 4, 1, forgeDeco.pillarID, forgeDeco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 17, 17, y + 4, 17, forgeDeco.pillarID, forgeDeco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 17, y + 1, 12, 17, y + 4, 17, forgeDeco.pillarID, forgeDeco.pillarMeta, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 2, 16, y + 1, 5, forgeDeco.blockID, forgeDeco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 2, 12, y + 1, 6, forgeDeco.stairID, this.getStairMeta(0 + rotation), rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 6, 16, y + 1, 6, forgeDeco.stairID, this.getStairMeta(3 + rotation), rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 13, 16, y + 1, 16, forgeDeco.blockID, forgeDeco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 12, 12, y + 1, 16, forgeDeco.stairID, this.getStairMeta(0 + rotation), rotation);
        this.fillBlocksRotated(world, sbb, 12, y + 1, 12, 16, y + 1, 12, forgeDeco.stairID, this.getStairMeta(1 + rotation), rotation);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 13, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 15, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 17, y, 3, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 17, y, 5, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 13, y, 17, this.getStairMeta(1 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 15, y, 17, this.getStairMeta(1 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 17, y, 13, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeFurnacePillar(world, forgeDeco, decoRNG, 17, y, 15, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 12, y, 1, this.getStairMeta(3 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 17, y, 6, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 12, y, 17, this.getStairMeta(1 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 17, y, 12, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 17, y, 9, this.getStairMeta(0 + rotation), rotation, sbb);
        this.makeStonePillar(world, forgeDeco, 9, y, 17, this.getStairMeta(1 + rotation), rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150467_bQ, decoRNG.nextInt(16), 13, y + 2, 5, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150467_bQ, decoRNG.nextInt(16), 13, y + 2, 13, rotation, sbb);
        this.makeFirePit(world, forgeDeco, 6, y + 1, 12, rotation, sbb);
    }

    private void makeFurnacePillar(World world, StructureTFDecorator forgeDeco, Random rand, int x, int y, int z, int stairMeta, int rotation, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, forgeDeco.stairID, stairMeta + 4, x, y + 2, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150460_al, stairMeta + 4, x, y + 3, z, rotation, sbb);
        int amount = rand.nextBoolean() ? rand.nextInt(5) + 4 : 0;

        if (amount > 0) {
            int dx = this.getXWithOffsetAsIfRotated(x, z, rotation);
            int dy = this.func_74862_a(y + 3);
            int dz = this.getZWithOffsetAsIfRotated(x, z, rotation);

            if (sbb.func_78890_b(dx, dy, dz) && world.func_147439_a(dx, dy, dz) == Blocks.field_150460_al) {
                IInventory inv = (IInventory) world.func_147438_o(dx, dy, dz);

                inv.func_70299_a(1, new ItemStack(Items.field_151044_h, amount, 1));
            }
        }

        this.placeBlockRotated(world, forgeDeco.stairID, stairMeta, x, y + 4, z, rotation, sbb);
    }

    private void makeStonePillar(World world, StructureTFDecorator forgeDeco, int x, int y, int z, int stairMeta, int rotation, StructureBoundingBox sbb) {
        int sx;

        for (sx = 1; sx <= 4; ++sx) {
            this.placeBlockRotated(world, forgeDeco.pillarID, forgeDeco.pillarMeta, x, y + sx, z, rotation, sbb);
        }

        sx = this.getXWithOffsetAsIfRotated(x, z, rotation);
        int sy = this.func_74862_a(y + 1);
        int sz = this.getZWithOffsetAsIfRotated(x, z, rotation);

        switch (stairMeta) {
        case 0:
            --sx;
            break;

        case 1:
            ++sx;
            break;

        case 2:
            --sz;
            break;

        case 3:
            ++sz;
        }

        if (sbb.func_78890_b(sx, sy, sz)) {
            world.func_147465_d(sx, sy + 0, sz, forgeDeco.stairID, stairMeta, 0);
            world.func_147465_d(sx, sy + 3, sz, forgeDeco.stairID, stairMeta + 4, 0);
        }

    }

    private void makeFirePit(World world, StructureTFDecorator myDeco, int x, int y, int z, int rotation, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x + 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x + 1, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x - 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x - 1, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(0 + rotation), x - 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(2 + rotation), x + 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(3 + rotation), x + 0, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(1 + rotation), x + 0, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150424_aL, 0, x, y, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150480_ab, 0, x, y + 1, z, rotation, sbb);
    }

    private void decorateNetherwart(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y, boolean isTop) {
        StructureTFDecorator netherDeco = this.deco;

        this.makePillarFrame(world, sbb, netherDeco, rotation, 12, y, 9, 4, 4, 7, true);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 10, 14, y + 1, 14, Blocks.field_150425_aM, 0, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 2, 10, 14, y + 2, 14, Blocks.field_150388_bm, 0, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 4, 10, 14, y + 4, 14, Blocks.field_150425_aM, 0, rotation);
        this.makePillarFrame(world, sbb, netherDeco, rotation, 5, y, 12, 3, isTop ? 4 : 9, 3, true);
        this.placeBlockRotated(world, netherDeco.blockID, netherDeco.blockMeta, 6, y + 1, 13, rotation, sbb);
        this.placeBlockRotated(world, netherDeco.blockID, netherDeco.blockMeta, 6, y + (isTop ? 4 : 9), 13, rotation, sbb);
        this.placeSpawnerRotated(world, 6, y + 3, 13, rotation, "Blaze", sbb);
        this.destroyTower(world, decoRNG, 12, y, 3, 2, sbb);
    }

    private void decorateBotanical(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        this.makePillarFrame(world, sbb, this.deco, rotation, 12, y, 12, 4, 4, 4, true);
        this.fillBlocksRotated(world, sbb, 13, y + 1, 13, 14, y + 1, 14, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, 13, y + 4, 13, 14, y + 4, 14, this.deco.blockID, this.deco.blockMeta, rotation);
        this.placeRandomPlant(world, decoRNG, 13, y + 2, 13, rotation, sbb);
        this.placeRandomPlant(world, decoRNG, 13, y + 2, 14, rotation, sbb);
        this.placeRandomPlant(world, decoRNG, 14, y + 2, 13, rotation, sbb);
        this.placeRandomPlant(world, decoRNG, 14, y + 2, 14, rotation, sbb);

        int x;

        for (x = 1; x <= 4; ++x) {
            this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 12, y + x, 4, rotation, sbb);
            this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, 15, y + x, 4, rotation, sbb);
        }

        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 13, y + 1, 4, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation) + 4, 14, y + 1, 4, rotation, sbb);
        this.placeTreasureRotated(world, 13, y + 2, 4, rotation, TFTreasure.basement, sbb);
        this.placeBlockRotated(world, Blocks.field_150462_ai, 0, 14, y + 2, 4, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 12, y + 1, 7, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150376_bx, 9, 13, y + 1, 7, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150376_bx, 9, 14, y + 1, 7, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation) + 4, 15, y + 1, 7, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 12, y + 1, 10, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150376_bx, 9, 13, y + 1, 10, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150376_bx, 9, 14, y + 1, 10, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation) + 4, 15, y + 1, 10, rotation, sbb);

        for (x = 12; x <= 15; ++x) {
            this.placeRandomPlant(world, decoRNG, x, y + 2, 7, rotation, sbb);
            this.placeRandomPlant(world, decoRNG, x, y + 2, 10, rotation, sbb);
        }

        this.placeTreePlanter(world, decoRNG.nextInt(5), 6, y + 1, 12, rotation, sbb);
    }

    private void placeTreePlanter(World world, int treeNum, int x, int y, int z, int rotation, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x + 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x + 1, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x - 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.pillarID, this.deco.pillarMeta, x - 1, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), x - 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), x + 1, y, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), x + 0, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), x + 0, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150346_d, 0, x, y, z, rotation, sbb);
        int dx = this.getXWithOffsetAsIfRotated(x, z, rotation);
        int dy = this.func_74862_a(y + 1);
        int dz = this.getZWithOffsetAsIfRotated(x, z, rotation);

        if (sbb.func_78890_b(dx, dy, dz)) {
            Object treeGen;

            switch (treeNum) {
            case 0:
            default:
                treeGen = new WorldGenTrees(false);
                break;

            case 1:
                treeGen = new WorldGenTrees(true, 3, 3, 3, false);
                break;

            case 2:
                treeGen = new WorldGenForest(true, false);
                break;

            case 3:
                treeGen = new TFGenSmallTwilightOak(false);
                break;

            case 4:
                treeGen = new TFGenSmallRainboak(false);
            }

            for (int i = 0; i < 100 && !((WorldGenerator) treeGen).func_76484_a(world, world.field_73012_v, dx, dy, dz); ++i) {
                ;
            }
        }

    }

    private void placeRandomPlant(World world, Random decoRNG, int x, int y, int z, int rotation, StructureBoundingBox sbb) {
        int potMeta = decoRNG.nextInt(12);

        this.placeBlockRotated(world, Blocks.field_150457_bL, potMeta, x, y, z, rotation, sbb);
    }

    private void makeBottomEntrance(World world, Random decoRNG, StructureBoundingBox sbb, int rotation, int y) {
        this.makeFirePit(world, this.deco, 13, y + 1, 3, rotation, sbb);
        this.makeFirePit(world, this.deco, 3, y + 1, 13, rotation, sbb);
        this.makeFirePit(world, this.deco, 13, y + 1, 13, rotation, sbb);
        this.makePillarFrame(world, sbb, this.deco, rotation, 7, y, 7, 3, 4, 3, false);
    }

    protected void addTimberMaze(World world, Random rand, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 5;
        int floorside = 0;

        if (bottom == 0) {
            bottom += spacing;
        }

        for (int y = bottom; y < top; y += spacing) {
            ++floorside;
            floorside %= 4;
            this.makeTimberBeams(world, rand, sbb, floorside, y, y == bottom && bottom != spacing, y >= top - spacing, top);
        }

    }

    protected void makeTimberBeams(World world, Random rand, StructureBoundingBox sbb, int rotation, int y, boolean isBottom, boolean isTop, int top) {
        Block beamID = TFBlocks.log;
        byte beamMetaBase = 3;
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;
        byte beamMetaUD = 0;

        int z;

        for (z = 1; z < this.size - 1; ++z) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, 4, y, z, rotation, sbb);
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, 9, y, z, rotation, sbb);
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, 14, y, z, rotation, sbb);
        }

        z = this.pickBetweenExcluding(3, this.size - 3, rand, 4, 9, 14);

        int x1;

        for (x1 = 5; x1 < 9; ++x1) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaNS, x1, y, z, rotation, sbb);
        }

        z = this.pickBetweenExcluding(3, this.size - 3, rand, 4, 9, 14);

        for (x1 = 10; x1 < 14; ++x1) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaNS, x1, y, z, rotation, sbb);
        }

        byte b0 = 4;
        int z1 = this.pickFrom(rand, 4, 9, 14);
        byte x2 = 9;
        int z2 = this.pickFrom(rand, 4, 9, 14);
        byte x3 = 14;
        int z3 = this.pickFrom(rand, 4, 9, 14);

        int lx;

        for (lx = 1; lx < 5; ++lx) {
            if (!isBottom || this.checkPost(world, b0, y - 5, z1, rotation, sbb)) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, b0, y - lx, z1, rotation, sbb);
                this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, rotation), b0 + 1, y - lx, z1, rotation, sbb);
            }

            if (!isBottom || this.checkPost(world, x2, y - 5, z2, rotation, sbb)) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, x2, y - lx, z2, rotation, sbb);
            }

            if (!isBottom || this.checkPost(world, x3, y - 5, z3, rotation, sbb)) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, x3, y - lx, z3, rotation, sbb);
                this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(4, rotation), x3 - 1, y - lx, z3, rotation, sbb);
            }
        }

        if (isTop) {
            lx = (this.field_74887_e.field_78895_b + top + 1) % 4;
            byte lz = 4;
            byte ladderZ = 10;
            byte ladderMeta = 3;

            for (int by = 1; by < 5; ++by) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, lz, y + by, 9, lx, sbb);
                this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(ladderMeta, lx), lz, y + by, ladderZ, lx, sbb);
            }

            this.placeBlockRotated(world, Blocks.field_150350_a, 0, lz, y + 6, 9, lx, sbb);
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, lz + 1, y + 5, ladderZ, lx, sbb);
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, lz - 1, y + 5, ladderZ, lx, sbb);
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, lz + 1, y + 6, ladderZ, lx, sbb);
            this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, lz - 1, y + 6, ladderZ, lx, sbb);
        }

        int i;

        if (!isBottom && !isTop) {
            lx = this.pickFrom(rand, 6, 7, 11);
            i = this.pickFrom(rand, 6, 11, 12);
            this.makeMiniGhastSpawner(world, rand, y, lx, i, sbb);
        }

        lx = this.pickFrom(rand, 2, 12, 16);
        i = 2 + rand.nextInt(15);
        this.placeBlockRotated(world, Blocks.field_150379_bu, 0, lx, y + 2, i, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, rand.nextBoolean() ? 0 : 7, lx, y + 1, i, rotation, sbb);
    }

    private void makeMiniGhastSpawner(World world, Random rand, int y, int sx, int sz, StructureBoundingBox sbb) {
        TileEntityMobSpawner spawner = this.placeSpawnerAtCurrentPosition(world, rand, sx, y + 2, sz, TFCreatures.getSpawnerNameFor("Mini Ghast"), sbb);

        if (spawner != null) {
            NBTTagCompound tags = new NBTTagCompound();

            spawner.func_145841_b(tags);
            tags.func_74777_a("SpawnRange", (short) 16);
            tags.func_74777_a("MaxNearbyEntities", (short) 2);
            tags.func_74777_a("SpawnCount", (short) 1);
            spawner.func_145839_a(tags);
        }

    }

    protected void addBuilderPlatforms(World world, Random rand, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 5;
        int floorside = 0;

        if (bottom == 0) {
            bottom += spacing;
        }

        int y;

        for (y = bottom; y < top - spacing; y += spacing) {
            this.makeBuilderPlatforms(world, rand, sbb, floorside, y, y == bottom && bottom != spacing, y >= top - spacing);
            floorside += 1 + rand.nextInt(3);
            floorside %= 4;
        }

        this.makeBuilderPlatform(world, rand, 1, bottom, 5, true, sbb);
        this.makeBuilderPlatform(world, rand, 3, bottom, 5, true, sbb);

        for (y = bottom - 4; y < bottom; ++y) {
            this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, 1), 1, y, 5, 1, sbb);
            this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, 3), 1, y, 5, 3, sbb);
        }

        this.addTopBuilderPlatform(world, rand, bottom, top, spacing, sbb);
    }

    protected void makeBuilderPlatforms(World world, Random rand, StructureBoundingBox sbb, int rotation, int y, boolean bottom, boolean top) {
        int z = this.size / 2 + rand.nextInt(5) - rand.nextInt(5);

        this.makeBuilderPlatform(world, rand, rotation, y, z, false, sbb);
        this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, rotation), 1, y + 1, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, rotation), 1, y + 2, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, rotation), 1, y + 3, z, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2, rotation), 1, y + 4, z, rotation, sbb);
        this.makeBuilderPlatform(world, rand, rotation, y + 5, z, true, sbb);
        int sx;
        int sz;

        if (y % 2 == 1) {
            sx = this.pickFrom(rand, 5, 9, 13);
            sz = sx == 9 ? (rand.nextBoolean() ? 5 : 13) : 9;
            this.placeBlockRotated(world, TFBlocks.towerDevice, 9, sx, y + 2, sz, rotation, sbb);
        } else {
            sx = rand.nextBoolean() ? 5 : 13;
            sz = rand.nextBoolean() ? 5 : 13;
            this.makeLampCluster(world, rand, sx, y, sz, rotation, sbb);
        }

    }

    private void addTopBuilderPlatform(World world, Random rand, int bottom, int top, int spacing, StructureBoundingBox sbb) {
        int rotation = (this.field_74887_e.field_78895_b + top + 1) % 4;

        this.fillBlocksRotated(world, sbb, 5, top - spacing, 9, 7, top - spacing, 11, this.deco.accentID, this.deco.accentMeta, rotation);
        this.fillBlocksRotated(world, sbb, 6, top - spacing, 9, 6, top, 9, this.deco.accentID, this.deco.accentMeta, rotation);
        this.fillBlocksRotated(world, sbb, 6, top - spacing + 1, 10, 6, top - 1, 10, Blocks.field_150468_ap, this.getLadderMeta(3, rotation), rotation);
        this.placeBlockRotated(world, Blocks.field_150350_a, 0, 6, top + 1, 9, rotation, sbb);
        this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 5, top + 0, 10, rotation, sbb);
        this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 7, top + 0, 10, rotation, sbb);
        this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 5, top + 1, 10, rotation, sbb);
        this.placeBlockRotated(world, this.deco.fenceID, this.deco.fenceMeta, 7, top + 1, 10, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 6, 7, top - spacing, 10, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, rand.nextBoolean() ? 5 : 6, 7, top - spacing + 1, 11, rotation, sbb);
    }

    private void makeBuilderPlatform(World world, Random rand, int rotation, int y, int z, boolean hole, StructureBoundingBox sbb) {
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 1, y, z - 1, rotation, sbb);
        if (!hole) {
            this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 1, y, z - 0, rotation, sbb);
        }

        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 1, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 2, y, z - 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 2, y, z - 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, 2, y, z + 1, rotation, sbb);
        this.placeBlockRotated(world, TFBlocks.towerDevice, 6, 2, y, hole ? z + 1 : z - 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, rand.nextBoolean() ? 5 : 6, 2, y + 1, z + 0, rotation, sbb);
    }

    private void makeLampCluster(World world, Random rand, int sx, int y, int sz, int rotation, StructureBoundingBox sbb) {
        byte radius = 4;
        int i = 0;

        int lx;
        int ly;
        int lz;
        int move;

        while (i < 5) {
            lx = sx;
            ly = y;
            lz = sz;
            int directions = 0;

            while (true) {
                if (directions < 10) {
                    this.placeBlockRotated(world, Blocks.field_150379_bu, 0, lx, ly, lz, rotation, sbb);
                    move = rand.nextInt(8);
                    if (move > 5) {
                        move -= 2;
                    }

                    lx += Facing.field_71586_b[move];
                    ly += Facing.field_71587_c[move];
                    lz += Facing.field_71585_d[move];
                    if (lx <= sx + radius && lx >= sx - radius && ly <= y + radius && ly >= y - radius && lz <= sz + radius && lz >= sz - radius) {
                        ++directions;
                        continue;
                    }
                }

                ++i;
                break;
            }
        }

        for (i = 0; i < 5; ++i) {
            lx = sx;
            ly = y;
            lz = sz;
            int[] aint = new int[10];

            for (move = 0; move < 10; ++move) {
                aint[move] = rand.nextInt(8);
                if (aint[move] > 5) {
                    aint[move] -= 2;
                }
            }

            for (move = 0; move < 10; ++move) {
                int direction = aint[move];

                lx += Facing.field_71586_b[direction];
                ly += Facing.field_71587_c[direction];
                lz += Facing.field_71585_d[direction];
                if (lx > sx + radius || lx < sx - radius || ly > y + radius || ly < y - radius || lz > sz + radius || lz < sz - radius) {
                    break;
                }

                if (this.getBlockIDRotated(world, lx, ly, lz, rotation, sbb) != Blocks.field_150379_bu) {
                    this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, direction), lx, ly, lz, rotation, sbb);
                    break;
                }
            }
        }

    }
}
