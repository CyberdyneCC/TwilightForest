package twilightforest.structures.darktower;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureComponent.BlockSelector;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;
import twilightforest.item.TFItems;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.StructureTFDecorator;
import twilightforest.structures.lichtower.ComponentTFTowerRoof;
import twilightforest.structures.lichtower.ComponentTFTowerRoofAttachedSlab;
import twilightforest.structures.lichtower.ComponentTFTowerRoofFence;
import twilightforest.structures.lichtower.ComponentTFTowerRoofGableForwards;
import twilightforest.structures.lichtower.ComponentTFTowerRoofSlabForwards;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFDarkTowerWing extends ComponentTFTowerWing {

    protected boolean keyTower = false;
    protected ArrayList openingTypes = new ArrayList();

    public ComponentTFDarkTowerWing() {}

    protected ComponentTFDarkTowerWing(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("keyTower", this.keyTower);
        par1NBTTagCompound.func_74783_a("doorTypeInts", this.getDoorsTypesAsIntArray());
    }

    private int[] getDoorsTypesAsIntArray() {
        IntBuffer ibuffer = IntBuffer.allocate(this.openingTypes.size());
        Iterator iterator = this.openingTypes.iterator();

        while (iterator.hasNext()) {
            EnumDarkTowerDoor doorType = (EnumDarkTowerDoor) iterator.next();

            ibuffer.put(doorType.ordinal());
        }

        return ibuffer.array();
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.keyTower = par1NBTTagCompound.func_74767_n("keyTower");
        this.readDoorsTypesFromArray(par1NBTTagCompound.func_74759_k("doorTypeInts"));
    }

    private void readDoorsTypesFromArray(int[] intArray) {
        int[] aint = intArray;
        int i = intArray.length;

        for (int j = 0; j < i; ++j) {
            int typeInt = aint[j];

            this.openingTypes.add(EnumDarkTowerDoor.values()[typeInt]);
        }

    }

    private void readOpeningsFromArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i += 3) {
            ChunkCoordinates door = new ChunkCoordinates(intArray[i], intArray[i + 1], intArray[i + 2]);

            this.openings.add(door);
        }

    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.makeARoof(parent, list, rand);
        this.makeABeard(parent, list, rand);
        int direction;
        int[] dest;

        if (this.size > 10) {
            for (direction = 0; direction < 4; ++direction) {
                dest = this.getValidOpening(rand, direction);
                int childSize = this.size - 2;
                int childHeight = this.validateChildHeight(this.height - 4 + rand.nextInt(10) - rand.nextInt(10), childSize);
                boolean madeWing = this.makeTowerWing(list, rand, this.func_74877_c(), dest[0], dest[1], dest[2], this.size - 2, childHeight, direction);

                if (!madeWing && (direction == 2 || rand.nextBoolean())) {
                    this.makeTowerBalcony(list, rand, this.func_74877_c(), dest[0], dest[1], dest[2], direction);
                }
            }
        } else if (rand.nextInt(4) == 0) {
            direction = rand.nextInt(4);
            dest = this.getValidOpening(rand, direction);
            this.makeTowerBalcony(list, rand, this.func_74877_c(), dest[0], dest[1], dest[2], direction);
        }

    }

    protected int validateChildHeight(int childHeight, int childSize) {
        return childHeight / 4 * 4 + 1;
    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        int index = this.func_74877_c();
        Object roof;

        switch (rand.nextInt(5)) {
        case 0:
        case 1:
        default:
            roof = new ComponentTFDarkTowerRoofAntenna(index, this);
            break;

        case 2:
            roof = new ComponentTFDarkTowerRoofCactus(index, this);
            break;

        case 3:
            roof = new ComponentTFDarkTowerRoofRings(index, this);
            break;

        case 4:
            roof = new ComponentTFDarkTowerRoofFourPost(index, this);
        }

        list.add(roof);
        ((ComponentTFTowerRoof) roof).func_74861_a(this, list, rand);
        this.roofType = roof.getClass();
    }

    protected void makeAttachedRoof(List list, Random rand) {
        int index = this.func_74877_c();

        if (this.roofType == null && rand.nextInt(32) != 0) {
            this.tryToFitRoof(list, rand, new ComponentTFTowerRoofGableForwards(index + 1, this));
        }

        if (this.roofType == null && rand.nextInt(8) != 0) {
            this.tryToFitRoof(list, rand, new ComponentTFTowerRoofSlabForwards(index + 1, this));
        }

        if (this.roofType == null && rand.nextInt(32) != 0) {
            ComponentTFTowerRoofAttachedSlab roof = new ComponentTFTowerRoofAttachedSlab(index + 1, this);

            this.tryToFitRoof(list, rand, roof);
        }

        if (this.roofType == null) {
            ComponentTFTowerRoofFence roof1 = new ComponentTFTowerRoofFence(index + 1, this);

            this.tryToFitRoof(list, rand, roof1);
        }

    }

    public void makeABeard(StructureComponent parent, List list, Random rand) {
        ComponentTFDarkTowerBeard beard = new ComponentTFDarkTowerBeard(this.func_74877_c() + 1, this);

        list.add(beard);
        beard.func_74861_a(this, list, rand);
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        if (wingHeight < 8) {
            return false;
        } else {
            int direction = (this.getCoordBaseMode() + rotation) % 4;
            int[] dx = this.offsetTowerCoords(x, y, z, 5, direction);

            if (dx[1] + wingHeight > 250) {
                return false;
            } else {
                ComponentTFDarkTowerBridge bridge = new ComponentTFDarkTowerBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
                StructureComponent intersect = StructureComponent.func_74883_a(list, bridge.func_74874_b());

                if (intersect != null && intersect != this) {
                    return false;
                } else {
                    intersect = StructureComponent.func_74883_a(list, bridge.getWingBB());
                    if (intersect != null && intersect != this) {
                        return false;
                    } else {
                        list.add(bridge);
                        bridge.func_74861_a(this, list, rand);
                        this.addOpening(x, y, z, rotation);
                        return true;
                    }
                }
            }
        }
    }

    protected boolean makeTowerBalcony(List list, Random rand, int index, int x, int y, int z, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 5, direction);
        ComponentTFDarkTowerBalcony balcony = new ComponentTFDarkTowerBalcony(index, dx[0], dx[1], dx[2], direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, balcony.func_74874_b());

        if (intersect != null && intersect != this) {
            return false;
        } else {
            list.add(balcony);
            balcony.func_74861_a(this, list, rand);
            this.addOpening(x, y, z, rotation, EnumDarkTowerDoor.REAPPEARING);
            return true;
        }
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.makeEncasedWalls(world, rand, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        this.nullifySkyLightForBoundingBox(world);
        if (this.size > 9) {
            this.addHalfFloors(world, decoRNG, sbb, 4, this.height - 1);
        } else if (decoRNG.nextInt(3) == 0) {
            this.addSmallTimberBeams(world, decoRNG, sbb, 4, this.height - 1);
        } else {
            this.addHalfFloors(world, decoRNG, sbb, 4, this.height - 1);
        }

        this.makeOpenings(world, sbb);
        if (decoRNG.nextBoolean() && !this.isKeyTower() && this.height > 8) {
            int blobs = 1;

            if (this.size > 9 && decoRNG.nextBoolean()) {
                ++blobs;
            }

            for (int i = 0; i < blobs; ++i) {
                int x = decoRNG.nextInt(this.size);
                int y = decoRNG.nextInt(this.height - 7) + 2;
                int z = decoRNG.nextInt(this.size);

                this.destroyTower(world, decoRNG, x, y, z, 3, sbb);
            }
        }

        return true;
    }

    protected void destroyTower(World world, Random decoRNG, int x, int y, int z, int amount, StructureBoundingBox sbb) {
        int initialRadius = decoRNG.nextInt(amount) + amount;

        this.drawBlob(world, x, y, z, initialRadius, Blocks.field_150350_a, 0, sbb);

        for (int i = 0; i < 3; ++i) {
            int dx = x + (initialRadius - 1) * (decoRNG.nextBoolean() ? 1 : -1);
            int dy = y + (initialRadius - 1) * (decoRNG.nextBoolean() ? 1 : -1);
            int dz = z + (initialRadius - 1) * (decoRNG.nextBoolean() ? 1 : -1);

            this.netherTransformBlob(world, decoRNG, dx, dy, dz, initialRadius - 1, sbb);
            this.drawBlob(world, dx, dy, dz, initialRadius - 2, Blocks.field_150350_a, 0, sbb);
        }

    }

    private void netherTransformBlob(World world, Random inRand, int sx, int sy, int sz, int rad, StructureBoundingBox sbb) {
        Random rand = new Random(inRand.nextLong());

        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dy = 0; dy <= rad; ++dy) {
                for (byte dz = 0; dz <= rad; ++dz) {
                    boolean dist = false;
                    byte b0;

                    if (dx >= dy && dx >= dz) {
                        b0 = (byte) (dx + (byte) ((int) ((double) Math.max(dy, dz) * 0.5D + (double) Math.min(dy, dz) * 0.25D)));
                    } else if (dy >= dx && dy >= dz) {
                        b0 = (byte) (dy + (byte) ((int) ((double) Math.max(dx, dz) * 0.5D + (double) Math.min(dx, dz) * 0.25D)));
                    } else {
                        b0 = (byte) (dz + (byte) ((int) ((double) Math.max(dx, dy) * 0.5D + (double) Math.min(dx, dy) * 0.25D)));
                    }

                    if (b0 <= rad) {
                        this.testAndChangeToNetherrack(world, rand, sx + dx, sy + dy, sz + dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx + dx, sy + dy, sz + dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx + dx, sy + dy, sz - dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx - dx, sy + dy, sz + dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx - dx, sy + dy, sz - dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx + dx, sy - dy, sz + dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx + dx, sy - dy, sz - dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx - dx, sy - dy, sz + dz, sbb);
                        this.testAndChangeToNetherrack(world, rand, sx - dx, sy - dy, sz - dz, sbb);
                    }
                }
            }
        }

    }

    private void testAndChangeToNetherrack(World world, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        if (this.func_151548_a(world, x, y, z, sbb) != Blocks.field_150350_a) {
            this.func_151550_a(world, Blocks.field_150424_aL, 0, x, y, z, sbb);
            if (this.func_151548_a(world, x, y + 1, z, sbb) == Blocks.field_150350_a && rand.nextBoolean()) {
                this.func_151550_a(world, Blocks.field_150480_ab, 0, x, y + 1, z, sbb);
            }
        }

    }

    public void drawBlob(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue, StructureBoundingBox sbb) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dy = 0; dy <= rad; ++dy) {
                for (byte dz = 0; dz <= rad; ++dz) {
                    boolean dist = false;
                    byte b0;

                    if (dx >= dy && dx >= dz) {
                        b0 = (byte) (dx + (byte) ((int) ((double) Math.max(dy, dz) * 0.5D + (double) Math.min(dy, dz) * 0.25D)));
                    } else if (dy >= dx && dy >= dz) {
                        b0 = (byte) (dy + (byte) ((int) ((double) Math.max(dx, dz) * 0.5D + (double) Math.min(dx, dz) * 0.25D)));
                    } else {
                        b0 = (byte) (dz + (byte) ((int) ((double) Math.max(dx, dy) * 0.5D + (double) Math.min(dx, dy) * 0.25D)));
                    }

                    if (b0 <= rad) {
                        this.func_151550_a(world, blockValue, metaValue, sx + dx, sy + dy, sz + dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx + dx, sy + dy, sz - dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx - dx, sy + dy, sz + dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx - dx, sy + dy, sz - dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx + dx, sy - dy, sz + dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx + dx, sy - dy, sz - dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx - dx, sy - dy, sz + dz, sbb);
                        this.func_151550_a(world, blockValue, metaValue, sx - dx, sy - dy, sz - dz, sbb);
                    }
                }
            }
        }

    }

    protected void addHalfFloors(World world, Random rand, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 4;
        int rotation = (this.field_74887_e.field_78895_b + bottom) % 3;

        if (bottom == 0) {
            bottom += spacing;
        }

        for (int y = bottom; y < top; y += spacing) {
            rotation += 2;
            rotation %= 4;
            if (y >= top - spacing) {
                this.makeFullFloor(world, sbb, rotation, y, spacing);
                if (this.isDeadEnd()) {
                    this.decorateTreasureRoom(world, sbb, rotation, y, 4, this.deco);
                }
            } else {
                this.makeHalfFloor(world, sbb, rotation, y, spacing);
                switch (rand.nextInt(8)) {
                case 0:
                    if (this.size < 11) {
                        this.decorateReappearingFloor(world, rand, sbb, rotation, y);
                        break;
                    }

                case 1:
                    this.decorateSpawner(world, rand, sbb, rotation, y);
                    break;

                case 2:
                    this.decorateLounge(world, rand, sbb, rotation, y);
                    break;

                case 3:
                    this.decorateLibrary(world, rand, sbb, rotation, y);
                    break;

                case 4:
                    this.decorateExperimentPulser(world, rand, sbb, rotation, y);
                    break;

                case 5:
                    this.decorateExperimentLamp(world, rand, sbb, rotation, y);
                    break;

                case 6:
                    this.decoratePuzzleChest(world, rand, sbb, rotation, y);
                }
            }

            this.addStairsDown(world, sbb, rotation, y, this.size - 2, spacing);
            if (this.size > 9) {
                this.addStairsDown(world, sbb, rotation, y, this.size - 3, spacing);
            }
        }

        rotation += 2;
        rotation %= 4;
        this.addStairsDown(world, sbb, rotation, this.height - 1, this.size - 2, spacing);
    }

    protected void makeHalfFloor(World world, StructureBoundingBox sbb, int rotation, int y, int spacing) {
        this.fillBlocksRotated(world, sbb, this.size / 2, y, 1, this.size - 2, y, this.size - 2, this.deco.blockID, this.deco.blockMeta, rotation);
        this.fillBlocksRotated(world, sbb, this.size / 2 - 1, y, 1, this.size / 2 - 1, y, this.size - 2, this.deco.accentID, this.deco.accentMeta, rotation);
    }

    protected void makeFullFloor(World world, StructureBoundingBox sbb, int rotation, int y, int spacing) {
        this.func_151556_a(world, sbb, 1, y, 1, this.size - 2, y, this.size - 2, this.deco.blockID, this.deco.blockMeta, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, this.size / 2, y, 1, this.size / 2, y, this.size - 2, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, true);
    }

    protected void decorateTreasureRoom(World world, StructureBoundingBox sbb, int rotation, int y, int spacing, StructureTFDecorator myDeco) {
        int x = this.size / 2;
        int z = this.size / 2;

        int dy;

        for (dy = 1; dy < spacing; ++dy) {
            this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x - 1, y + dy, z - 1, rotation, sbb);
            this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x + 1, y + dy, z - 1, rotation, sbb);
            this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x - 1, y + dy, z + 1, rotation, sbb);
            this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x + 1, y + dy, z + 1, rotation, sbb);
        }

        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(1 + rotation), x + 0, y + 1, z - 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(0 + rotation), x - 1, y + 1, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(2 + rotation), x + 1, y + 1, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(3 + rotation), x + 0, y + 1, z + 1, rotation, sbb);

        for (dy = 2; dy < spacing - 1; ++dy) {
            this.placeBlockRotated(world, myDeco.fenceID, myDeco.fenceMeta, x + 0, y + dy, z - 1, rotation, sbb);
            this.placeBlockRotated(world, myDeco.fenceID, myDeco.fenceMeta, x - 1, y + dy, z + 0, rotation, sbb);
            this.placeBlockRotated(world, myDeco.fenceID, myDeco.fenceMeta, x + 1, y + dy, z + 0, rotation, sbb);
            this.placeBlockRotated(world, myDeco.fenceID, myDeco.fenceMeta, x + 0, y + dy, z + 1, rotation, sbb);
        }

        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(1 + rotation) + 4, x + 0, y + spacing - 1, z - 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(0 + rotation) + 4, x - 1, y + spacing - 1, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(2 + rotation) + 4, x + 1, y + spacing - 1, z + 0, rotation, sbb);
        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(3 + rotation) + 4, x + 0, y + spacing - 1, z + 1, rotation, sbb);
        this.placeBlockRotated(world, myDeco.platformID, myDeco.platformMeta, x, y + 1, z, rotation, sbb);
        this.placeTreasureAtCurrentPosition(world, (Random) null, x, y + 2, z, this.isKeyTower() ? TFTreasure.darktower_key : TFTreasure.darktower_cache, sbb);
        if (this.isKeyTower()) {
            this.putItemInTreasure(world, x, y + 2, z, new ItemStack(TFItems.towerKey), sbb);
        }

    }

    private void decorateSpawner(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int x = this.size > 9 ? 4 : 3;
        int z = this.size > 9 ? 5 : 4;
        String mobID;

        if (this.size > 9) {
            mobID = rand.nextBoolean() ? TFCreatures.getSpawnerNameFor("Tower Golem") : TFCreatures.getSpawnerNameFor("Redscale Broodling");
        } else {
            mobID = TFCreatures.getSpawnerNameFor("Redscale Broodling");
        }

        this.makePillarFrame(world, sbb, this.deco, rotation, x, y, z, true);
        this.placeSpawnerRotated(world, x + 1, y + 2, z + 1, rotation, mobID, sbb);
    }

    private void decorateLounge(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int cx = this.size > 9 ? 9 : 7;
        int cz = this.size > 9 ? 4 : 3;

        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), cx, y + 1, cz + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), cx, y + 1, cz + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), cx, y + 1, cz + 2, rotation, sbb);
        cx = this.size > 9 ? 5 : 3;
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, cx, y + 1, cz + 0, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150376_bx, 9, cx, y + 1, cz + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation) + 4, cx, y + 1, cz + 2, rotation, sbb);
    }

    private void decorateReappearingFloor(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        this.fillBlocksRotated(world, sbb, 4, y, 3, 7, y, 5, TFBlocks.towerDevice, 0, rotation);
        this.fillBlocksRotated(world, sbb, 4, y + 1, 2, 7, y + 1, 2, Blocks.field_150452_aw, 0, rotation);
        this.fillBlocksRotated(world, sbb, 4, y + 1, 6, 7, y + 1, 6, Blocks.field_150452_aw, 0, rotation);
    }

    private void decorateExperimentLamp(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int cx = this.size > 9 ? 5 : 3;
        int cz = this.size > 9 ? 5 : 4;

        this.placeBlockRotated(world, Blocks.field_150320_F, 1, cx, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150379_bu, 0, cx, y + 2, cz, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, cx, y + 1, cz + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 3), cx, y + 1, cz + 2, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, cx, y + 3, cz - 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 2) + 8, cx, y + 3, cz - 2, rotation, sbb);
    }

    private void decorateExperimentPulser(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int cx = this.size > 9 ? 6 : 5;
        int cz = this.size > 9 ? 4 : 3;

        this.placeBlockRotated(world, Blocks.field_150320_F, 5 - this.getStairMeta(3 + rotation), cx, y + 1, cz + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, cx, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150488_af, 0, cx + 1, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150452_aw, 0, cx + 2, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150413_aR, (rotation + 1) % 4 + 4, cx - 1, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150488_af, 0, cx - 2, y + 1, cz, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150488_af, 0, cx - 2, y + 1, cz + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150488_af, 0, cx - 1, y + 1, cz + 1, rotation, sbb);
    }

    private void decorateLibrary(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int bx = this.size > 9 ? 4 : 3;
        int bz = this.size > 9 ? 3 : 2;

        this.makeSmallBookshelf(world, sbb, rotation, y, bx, bz);
        bx = this.size > 9 ? 9 : 7;
        bz = this.size > 9 ? 3 : 2;
        this.makeSmallBookshelf(world, sbb, rotation, y, bx, bz);
    }

    protected void makeSmallBookshelf(World world, StructureBoundingBox sbb, int rotation, int y, int bx, int bz) {
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation) + 0, bx, y + 1, bz + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation) + 4, bx, y + 2, bz + 0, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150342_X, 0, bx, y + 1, bz + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150342_X, 0, bx, y + 2, bz + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150342_X, 0, bx, y + 1, bz + 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150342_X, 0, bx, y + 2, bz + 2, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 0, bx, y + 1, bz + 3, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, bx, y + 2, bz + 3, rotation, sbb);
    }

    private void decoratePuzzleChest(World world, Random rand, StructureBoundingBox sbb, int rotation, int y) {
        int x = this.size > 9 ? 4 : 3;
        int z = this.size > 9 ? 5 : 4;

        this.makePillarFrame(world, sbb, this.deco, rotation, x, y, z, true);
        this.placeBlockRotated(world, this.deco.platformID, this.deco.platformMeta, x + 1, y + 1, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 2, y + 1, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 0, y + 1, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, y + 1, z + 2, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, y + 1, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 2, y + 3, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 0, y + 3, z + 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, y + 3, z + 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150350_a, 0, x + 1, y + 3, z + 0, rotation, sbb);
        this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x + 1, y + 3, z + 1, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150320_F, 5 - this.getStairMeta(1 + rotation), x + 1, y + 3, z - 1, rotation, sbb);
        this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, x + 1, y + 3, z - 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150442_at, this.getLeverMeta(rotation, 5), x + 2, y + 3, z - 2, rotation, sbb);
        this.placeTreasureRotated(world, x + 1, y + 2, z + 1, rotation, TFTreasure.darktower_cache, sbb);
    }

    protected void makePillarFrame(World world, StructureBoundingBox sbb, StructureTFDecorator myDeco, int rotation, int x, int y, int z, boolean fenced) {
        this.makePillarFrame(world, sbb, myDeco, rotation, x, y, z, 3, 3, 3, fenced);
    }

    protected void makePillarFrame(World world, StructureBoundingBox sbb, StructureTFDecorator myDeco, int rotation, int x, int y, int z, int width, int height, int length, boolean fenced) {
        for (int dx = 0; dx < width; ++dx) {
            for (int dz = 0; dz < length; ++dz) {
                int fy;

                if ((dx % 3 == 0 || dx == width - 1) && (dz % 3 == 0 || dz == length - 1)) {
                    for (fy = 1; fy <= height; ++fy) {
                        this.placeBlockRotated(world, myDeco.pillarID, myDeco.pillarMeta, x + dx, y + fy, z + dz, rotation, sbb);
                    }
                } else {
                    if (dx == 0) {
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(0 + rotation), x + dx, y + 1, z + dz, rotation, sbb);
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(0 + rotation) + 4, x + dx, y + height, z + dz, rotation, sbb);
                    } else if (dx == width - 1) {
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(2 + rotation), x + dx, y + 1, z + dz, rotation, sbb);
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(2 + rotation) + 4, x + dx, y + height, z + dz, rotation, sbb);
                    } else if (dz == 0) {
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(1 + rotation), x + dx, y + 1, z + dz, rotation, sbb);
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(1 + rotation) + 4, x + dx, y + height, z + dz, rotation, sbb);
                    } else if (dz == length - 1) {
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(3 + rotation), x + dx, y + 1, z + dz, rotation, sbb);
                        this.placeBlockRotated(world, myDeco.stairID, this.getStairMeta(3 + rotation) + 4, x + dx, y + height, z + dz, rotation, sbb);
                    }

                    if (fenced && (dx == 0 || dx == width - 1 || dz == 0 || dz == length - 1)) {
                        for (fy = 2; fy <= height - 1; ++fy) {
                            this.placeBlockRotated(world, myDeco.fenceID, myDeco.fenceMeta, x + dx, y + fy, z + dz, rotation, sbb);
                        }
                    }
                }
            }
        }

    }

    protected void putItemInTreasure(World world, int x, int y, int z, ItemStack itemToAdd, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz)) {
            TileEntity tileEntity = world.func_147438_o(dx, dy, dz);

            if (tileEntity != null && tileEntity instanceof IInventory) {
                IInventory inventory = (IInventory) tileEntity;
                boolean alreadyPresent = false;
                int emptySlots = 0;

                int slotsUntilPlaced;

                for (slotsUntilPlaced = 0; slotsUntilPlaced < inventory.func_70302_i_(); ++slotsUntilPlaced) {
                    ItemStack i = inventory.func_70301_a(slotsUntilPlaced);

                    if (i == null) {
                        ++emptySlots;
                    } else if (ItemStack.func_77989_b(i, itemToAdd)) {
                        alreadyPresent = true;
                        break;
                    }
                }

                if (!alreadyPresent && emptySlots > 0) {
                    slotsUntilPlaced = world.field_73012_v.nextInt(emptySlots);

                    for (int i = 0; i < inventory.func_70302_i_(); ++i) {
                        ItemStack inSlot = inventory.func_70301_a(i);

                        if (inSlot == null) {
                            if (slotsUntilPlaced == 0) {
                                inventory.func_70299_a(i, itemToAdd);
                                break;
                            }

                            --slotsUntilPlaced;
                        }
                    }
                }
            }
        }

    }

    protected void addStairsDown(World world, StructureBoundingBox sbb, int rotation, int y, int sz, int spacing) {
        for (int i = 0; i < spacing; ++i) {
            int sx = this.size - 3 - i;

            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), sx, y - i, sz, rotation, sbb);
            this.placeBlockRotated(world, this.deco.accentID, this.deco.accentMeta, sx, y - 1 - i, sz, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, sx, y + 1 - i, sz, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, sx, y + 2 - i, sz, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, sx - 1, y + 2 - i, sz, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, sx, y + 3 - i, sz, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150350_a, 0, sx - 1, y + 3 - i, sz, rotation, sbb);
        }

    }

    protected void addSmallTimberBeams(World world, Random rand, StructureBoundingBox sbb, int bottom, int top) {
        byte spacing = 4;
        int rotation = 0;

        if (bottom == 0) {
            bottom += spacing;
        }

        for (int y = bottom; y < top; y += spacing) {
            ++rotation;
            rotation %= 4;
            if (y >= top - spacing && this.isDeadEnd()) {
                this.makeTimberFloor(world, rand, sbb, rotation, y, spacing);
                StructureDecoratorDarkTower logDeco = new StructureDecoratorDarkTower();

                logDeco.pillarID = TFBlocks.log;
                logDeco.pillarMeta = 3;
                logDeco.platformID = TFBlocks.log;
                logDeco.pillarMeta = 3;
                this.decorateTreasureRoom(world, sbb, rotation, y, 4, logDeco);
            } else {
                this.makeSmallTimberBeams(world, rand, sbb, rotation, y, y == bottom && bottom != spacing, y >= top - spacing);
            }
        }

    }

    protected void makeTimberFloor(World world, Random rand, StructureBoundingBox sbb, int rotation, int y, int spacing) {
        Block beamID = TFBlocks.log;
        byte beamMetaBase = 3;
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;
        byte beamMetaUD = 0;

        int by;

        for (by = 1; by < this.size - 1; ++by) {
            for (int x = 1; x < this.size - 1; ++x) {
                if (x < by) {
                    this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaNS, x, y, by, rotation, sbb);
                } else {
                    this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, x, y, by, rotation, sbb);
                }
            }
        }

        for (by = 1; by < 4; ++by) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, 2, y - by, 2, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2 + rotation), 3, y - by, 2, rotation, sbb);
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, 6, y - by, 6, rotation, sbb);
            this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(4 + rotation), 5, y - by, 6, rotation, sbb);
        }

        this.placeBlockRotated(world, Blocks.field_150350_a, 0, 3, y, 2, rotation, sbb);
        this.placeBlockRotated(world, Blocks.field_150350_a, 0, 5, y, 6, rotation, sbb);
    }

    protected void makeSmallTimberBeams(World world, Random rand, StructureBoundingBox sbb, int rotation, int y, boolean bottom, boolean top) {
        Block beamID = TFBlocks.log;
        byte beamMetaBase = 3;
        int beamMetaNS = (this.field_74885_f + rotation) % 2 == 0 ? 4 : 8;
        int beamMetaEW = beamMetaNS == 4 ? 8 : 4;
        byte beamMetaUD = 0;

        int z;

        for (z = 1; z < this.size - 1; ++z) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, 2, y, z, rotation, sbb);
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaEW, 6, y, z, rotation, sbb);
        }

        z = this.pickBetweenExcluding(3, this.size - 3, rand, 2, 2, 6);

        for (int x1 = 3; x1 < 6; ++x1) {
            this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaNS, x1, y, z, rotation, sbb);
        }

        byte b0 = 2;
        int z1 = rand.nextBoolean() ? 2 : 6;
        byte x3 = 6;
        int z3 = rand.nextBoolean() ? 2 : 6;

        for (int by = 1; by < 4; ++by) {
            if (!bottom || this.checkPost(world, b0, y - 4, z1, rotation, sbb)) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, b0, y - by, z1, rotation, sbb);
                this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(2 + rotation), b0 + 1, y - by, z1, rotation, sbb);
            }

            if (!bottom || this.checkPost(world, x3, y - 4, z3, rotation, sbb)) {
                this.placeBlockRotated(world, beamID, beamMetaBase + beamMetaUD, x3, y - by, z3, rotation, sbb);
                this.placeBlockRotated(world, Blocks.field_150468_ap, this.getLadderMeta(4 + rotation), x3 - 1, y - by, z3, rotation, sbb);
            }
        }

    }

    protected int pickBetweenExcluding(int low, int high, Random rand, int k, int l, int m) {
        int result;

        do {
            result = rand.nextInt(high - low) + low;
        } while (result == k || result == l || result == m);

        return result;
    }

    protected int pickFrom(Random rand, int i, int j, int k) {
        switch (rand.nextInt(3)) {
        case 0:
        default:
            return i;

        case 1:
            return j;

        case 2:
            return k;
        }
    }

    protected boolean checkPost(World world, int x, int y, int z, int rotation, StructureBoundingBox sbb) {
        int worldX = this.getXWithOffsetAsIfRotated(x, z, rotation);
        int worldY = this.func_74862_a(y);
        int worldZ = this.getZWithOffsetAsIfRotated(x, z, rotation);
        Block blockID = sbb.func_78890_b(worldX, worldY, worldZ) ? world.func_147439_a(worldX, worldY, worldZ) : Blocks.field_150350_a;

        return blockID != Blocks.field_150350_a && (blockID != this.deco.accentID || world.func_72805_g(worldX, worldY, worldZ) != this.deco.accentMeta);
    }

    protected void makeEncasedWalls(World world, Random rand, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    if (x == minX || x == maxX || y == minY || y == maxY || z == minZ || z == maxZ) {
                        if ((x != minY && x != maxX || y != minY && y != maxY && z != minZ && z != maxZ) && (y != minY && y != maxY || x != minY && x != maxX && z != minZ && z != maxZ) && (z != minZ && z != maxZ || x != minY && x != maxX && y != minY && y != maxY)) {
                            BlockSelector blocker = this.deco.randomBlocks;

                            blocker.func_75062_a(rand, x, y, z, true);
                            this.func_151550_a(world, blocker.func_151561_a(), blocker.func_75064_b(), x, y, z, sbb);
                        } else {
                            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, x, y, z, sbb);
                        }
                    }
                }
            }
        }

        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, minY + 1, minZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, minY + 1, maxZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, minY + 1, minZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, minY + 1, maxZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, maxY - 1, minZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, maxY - 1, maxZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, maxY - 1, minZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, maxY - 1, maxZ, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX, minY + 1, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX, minY + 1, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX, minY + 1, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX, minY + 1, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX, maxY - 1, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX, maxY - 1, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX, maxY - 1, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX, maxY - 1, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, minY, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, minY, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, minY, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, minY, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, maxY, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, minX + 1, maxY, maxZ - 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, maxY, minZ + 1, sbb);
        this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, maxX - 1, maxY, maxZ - 1, sbb);
    }

    public int[] getValidOpening(Random rand, int direction) {
        int verticalOffset = this.size == 19 ? 5 : 4;
        int rx;
        int rz;
        int ry;

        if (direction != 0 && direction != 2) {
            if (direction != 1 && direction != 3) {
                return new int[] { 0, 0, 0};
            } else {
                rx = this.size / 2;
                rz = direction == 1 ? this.size - 1 : 0;
                ry = this.height - verticalOffset;
                return new int[] { rx, ry, rz};
            }
        } else {
            rx = direction == 0 ? this.size - 1 : 0;
            rz = this.size / 2;
            ry = this.height - verticalOffset;
            return new int[] { rx, ry, rz};
        }
    }

    public void addOpening(int dx, int dy, int dz, int direction) {
        this.addOpening(dx, dy, dz, direction, EnumDarkTowerDoor.VANISHING);
    }

    protected void addOpening(int dx, int dy, int dz, int direction, EnumDarkTowerDoor type) {
        super.addOpening(dx, dy, dz, direction);
        this.openingTypes.add(this.openings.indexOf(new ChunkCoordinates(dx, dy, dz)), type);
    }

    protected void makeOpenings(World world, StructureBoundingBox sbb) {
        for (int i = 0; i < this.openings.size(); ++i) {
            ChunkCoordinates doorCoords = (ChunkCoordinates) this.openings.get(i);
            EnumDarkTowerDoor doorType;

            if (this.openingTypes.size() > i) {
                doorType = (EnumDarkTowerDoor) this.openingTypes.get(i);
            } else {
                doorType = EnumDarkTowerDoor.VANISHING;
            }

            switch (ComponentTFDarkTowerWing.SyntheticClass_1.$SwitchMap$twilightforest$structures$darktower$EnumDarkTowerDoor[doorType.ordinal()]) {
            case 1:
            default:
                this.makeDoorOpening(world, doorCoords.field_71574_a, doorCoords.field_71572_b, doorCoords.field_71573_c, sbb);
                break;

            case 2:
                this.makeReappearingDoorOpening(world, doorCoords.field_71574_a, doorCoords.field_71572_b, doorCoords.field_71573_c, sbb);
                break;

            case 3:
                this.makeLockedDoorOpening(world, doorCoords.field_71574_a, doorCoords.field_71572_b, doorCoords.field_71573_c, sbb);
            }
        }

    }

    protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        this.nullifySkyLightAtCurrentPosition(world, dx - 3, dy - 1, dz - 3, dx + 3, dy + 3, dz + 3);
        if (dx == 0 || dx == this.size - 1) {
            this.func_151556_a(world, sbb, dx, dy - 1, dz - 2, dx, dy + 3, dz + 2, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx, dy, dz - 1, dx, dy + 2, dz + 1, TFBlocks.towerDevice, 2, Blocks.field_150350_a, 0, false);
        }

        if (dz == 0 || dz == this.size - 1) {
            this.func_151556_a(world, sbb, dx - 2, dy - 1, dz, dx + 2, dy + 3, dz, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx - 1, dy, dz, dx + 1, dy + 2, dz, TFBlocks.towerDevice, 2, Blocks.field_150350_a, 0, false);
        }

    }

    protected void makeReappearingDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        this.nullifySkyLightAtCurrentPosition(world, dx - 3, dy - 1, dz - 3, dx + 3, dy + 3, dz + 3);
        if (dx == 0 || dx == this.size - 1) {
            this.func_151556_a(world, sbb, dx, dy - 1, dz - 2, dx, dy + 3, dz + 2, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx, dy, dz - 1, dx, dy + 2, dz + 1, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
        }

        if (dz == 0 || dz == this.size - 1) {
            this.func_151556_a(world, sbb, dx - 2, dy - 1, dz, dx + 2, dy + 3, dz, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx - 1, dy, dz, dx + 1, dy + 2, dz, TFBlocks.towerDevice, 0, Blocks.field_150350_a, 0, false);
        }

    }

    protected void makeLockedDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        this.nullifySkyLightAtCurrentPosition(world, dx - 3, dy - 1, dz - 3, dx + 3, dy + 3, dz + 3);
        if (dx == 0 || dx == this.size - 1) {
            this.func_151556_a(world, sbb, dx, dy - 1, dz - 2, dx, dy + 3, dz + 2, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx, dy, dz - 1, dx, dy + 2, dz + 1, TFBlocks.towerDevice, 2, Blocks.field_150350_a, 0, false);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx, dy + 0, dz + 1, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx, dy + 0, dz - 1, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx, dy + 2, dz + 1, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx, dy + 2, dz - 1, sbb);
        }

        if (dz == 0 || dz == this.size - 1) {
            this.func_151556_a(world, sbb, dx - 2, dy - 1, dz, dx + 2, dy + 3, dz, this.deco.accentID, this.deco.accentMeta, Blocks.field_150350_a, 0, false);
            this.func_151556_a(world, sbb, dx - 1, dy, dz, dx + 1, dy + 2, dz, TFBlocks.towerDevice, 2, Blocks.field_150350_a, 0, false);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx + 1, dy + 0, dz, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx - 1, dy + 0, dz, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx + 1, dy + 2, dz, sbb);
            this.func_151550_a(world, TFBlocks.towerDevice, 4, dx - 1, dy + 2, dz, sbb);
        }

    }

    public boolean isDeadEnd() {
        int nonBalconies = 0;
        Iterator iterator = this.openingTypes.iterator();

        while (iterator.hasNext()) {
            EnumDarkTowerDoor type = (EnumDarkTowerDoor) iterator.next();

            if (type != EnumDarkTowerDoor.REAPPEARING) {
                ++nonBalconies;
            }
        }

        return nonBalconies <= 1;
    }

    public boolean isKeyTower() {
        return this.keyTower;
    }

    public void setKeyTower(boolean keyTower) {
        this.keyTower = keyTower;
    }

    protected int getLeverMeta(int rotation, int direction) {
        if (direction == 0) {
            return 0;
        } else if (direction == 1) {
            return 5;
        } else {
            rotation += this.getCoordBaseMode();
            rotation %= 4;
            if (rotation == 0) {
                switch (direction) {
                case 2:
                    return 4;

                case 3:
                    return 3;

                case 4:
                    return 2;

                case 5:
                    return 1;
                }
            } else if (rotation == 1) {
                switch (direction) {
                case 2:
                    return 1;

                case 3:
                    return 2;

                case 4:
                    return 4;

                case 5:
                    return 3;
                }
            } else if (rotation == 2) {
                switch (direction) {
                case 2:
                    return 3;

                case 3:
                    return 4;

                case 4:
                    return 1;

                case 5:
                    return 2;
                }
            } else if (rotation == 3) {
                switch (direction) {
                case 2:
                    return 2;

                case 3:
                    return 1;

                case 4:
                    return 3;

                case 5:
                    return 4;
                }
            }

            return -1;
        }
    }

    static class SyntheticClass_1 {

        static final int[] $SwitchMap$twilightforest$structures$darktower$EnumDarkTowerDoor = new int[EnumDarkTowerDoor.values().length];

        static {
            try {
                ComponentTFDarkTowerWing.SyntheticClass_1.$SwitchMap$twilightforest$structures$darktower$EnumDarkTowerDoor[EnumDarkTowerDoor.VANISHING.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                ComponentTFDarkTowerWing.SyntheticClass_1.$SwitchMap$twilightforest$structures$darktower$EnumDarkTowerDoor[EnumDarkTowerDoor.REAPPEARING.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                ComponentTFDarkTowerWing.SyntheticClass_1.$SwitchMap$twilightforest$structures$darktower$EnumDarkTowerDoor[EnumDarkTowerDoor.LOCKED.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

        }
    }
}
