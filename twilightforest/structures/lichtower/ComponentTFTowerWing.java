package twilightforest.structures.lichtower;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.entity.TFCreatures;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFTowerWing extends StructureTFComponent {

    public int size;
    protected int height;
    protected Class roofType;
    protected ArrayList openings = new ArrayList();
    protected int highestOpening;
    protected boolean[] openingTowards = new boolean[] { false, false, true, false};

    public ComponentTFTowerWing() {}

    protected ComponentTFTowerWing(int i) {
        super(i);
        this.highestOpening = 0;
    }

    protected ComponentTFTowerWing(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i);
        this.size = pSize;
        this.height = pHeight;
        this.setCoordBaseMode(direction);
        this.highestOpening = 0;
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("towerSize", this.size);
        par1NBTTagCompound.func_74768_a("towerHeight", this.height);
        par1NBTTagCompound.func_74783_a("doorInts", this.getDoorsAsIntArray());
        par1NBTTagCompound.func_74768_a("highestOpening", this.highestOpening);
        par1NBTTagCompound.func_74757_a("openingTowards0", this.openingTowards[0]);
        par1NBTTagCompound.func_74757_a("openingTowards1", this.openingTowards[1]);
        par1NBTTagCompound.func_74757_a("openingTowards2", this.openingTowards[2]);
        par1NBTTagCompound.func_74757_a("openingTowards3", this.openingTowards[3]);
    }

    private int[] getDoorsAsIntArray() {
        IntBuffer ibuffer = IntBuffer.allocate(this.openings.size() * 3);
        Iterator iterator = this.openings.iterator();

        while (iterator.hasNext()) {
            ChunkCoordinates door = (ChunkCoordinates) iterator.next();

            ibuffer.put(door.field_71574_a);
            ibuffer.put(door.field_71572_b);
            ibuffer.put(door.field_71573_c);
        }

        return ibuffer.array();
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.size = par1NBTTagCompound.func_74762_e("towerSize");
        this.height = par1NBTTagCompound.func_74762_e("towerHeight");
        this.readOpeningsFromArray(par1NBTTagCompound.func_74759_k("doorInts"));
        this.highestOpening = par1NBTTagCompound.func_74762_e("highestOpening");
        this.openingTowards[0] = par1NBTTagCompound.func_74767_n("openingTowards0");
        this.openingTowards[1] = par1NBTTagCompound.func_74767_n("openingTowards1");
        this.openingTowards[2] = par1NBTTagCompound.func_74767_n("openingTowards2");
        this.openingTowards[3] = par1NBTTagCompound.func_74767_n("openingTowards3");
    }

    private void readOpeningsFromArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i += 3) {
            ChunkCoordinates door = new ChunkCoordinates(intArray[i], intArray[i + 1], intArray[i + 2]);

            this.openings.add(door);
        }

    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        this.addOpening(0, 1, this.size / 2, 2);
        this.makeARoof(parent, list, rand);
        this.makeABeard(parent, list, rand);
        if (this.size > 4) {
            for (int i = 0; i < 4; ++i) {
                if (i != 2) {
                    int[] dest = this.getValidOpening(rand, i);

                    if (!this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], this.size - 2, this.height - 4, i) && this.size > 8 && !this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], this.size - 4, this.height - 6, i)) {
                        this.makeTowerWing(list, rand, 1, dest[0], dest[1], dest[2], this.size - 6, this.height - 12, i);
                    }
                }
            }
        }

    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        if (wingHeight < 6) {
            return false;
        } else {
            int direction = (this.getCoordBaseMode() + rotation) % 4;
            int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);

            if (rand.nextInt(6) == 0) {
                return this.makeBridge(list, rand, index, x, y, z, wingSize, wingHeight, rotation);
            } else {
                ComponentTFTowerWing wing = new ComponentTFTowerWing(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
                StructureComponent intersect = StructureComponent.func_74883_a(list, wing.field_74887_e);

                if (intersect != null && intersect != this) {
                    return rand.nextInt(3) > 0 ? this.makeBridge(list, rand, index, x, y, z, wingSize, wingHeight, rotation) : false;
                } else {
                    list.add(wing);
                    wing.func_74861_a(this, list, rand);
                    this.addOpening(x, y, z, rotation);
                    return true;
                }
            }
        }
    }

    protected boolean makeBridge(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 3, direction);

        if (wingSize == 3 && wingHeight > 10) {
            wingHeight = 6 + rand.nextInt(5);
        }

        ComponentTFTowerBridge bridge = new ComponentTFTowerBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, bridge.field_74887_e);

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

    public void addOpening(int dx, int dy, int dz, int direction) {
        this.openingTowards[direction] = true;
        if (dy > this.highestOpening) {
            this.highestOpening = dy;
        }

        this.openings.add(new ChunkCoordinates(dx, dy, dz));
    }

    public void makeABeard(StructureComponent parent, List list, Random rand) {
        boolean attached = parent.func_74874_b().field_78895_b < this.field_74887_e.field_78895_b;
        int index = this.func_74877_c();
        Object beard;

        if (attached) {
            beard = new ComponentTFTowerBeardAttached(index + 1, this);
        } else {
            beard = new ComponentTFTowerBeard(index + 1, this);
        }

        list.add(beard);
        ((ComponentTFTowerBeard) beard).func_74861_a(this, list, rand);
    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        boolean attached = parent.func_74874_b().field_78894_e > this.field_74887_e.field_78894_e;

        if (attached) {
            this.makeAttachedRoof(list, rand);
        } else {
            this.makeFreestandingRoof(list, rand);
        }

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

    protected void tryToFitRoof(List list, Random rand, ComponentTFTowerRoof roof) {
        if (roof.fits(this, list, rand)) {
            list.add(roof);
            roof.func_74861_a(this, list, rand);
            this.roofType = roof.getClass();
        }

    }

    protected void makeFreestandingRoof(List list, Random rand) {
        int index = this.func_74877_c();

        if (this.roofType == null && rand.nextInt(8) != 0) {
            ComponentTFTowerRoofPointyOverhang roof = new ComponentTFTowerRoofPointyOverhang(index + 1, this);

            this.tryToFitRoof(list, rand, roof);
        }

        if (this.roofType == null) {
            ComponentTFTowerRoofStairsOverhang roof1 = new ComponentTFTowerRoofStairsOverhang(index + 1, this);

            this.tryToFitRoof(list, rand, roof1);
        }

        if (this.roofType == null) {
            ComponentTFTowerRoofStairs roof2 = new ComponentTFTowerRoofStairs(index + 1, this);

            this.tryToFitRoof(list, rand, roof2);
        }

        if (this.roofType == null && rand.nextInt(53) != 0) {
            ComponentTFTowerRoofSlab roof3 = new ComponentTFTowerRoofSlab(index + 1, this);

            this.tryToFitRoof(list, rand, roof3);
        }

        if (this.roofType == null) {
            ComponentTFTowerRoofFence roof4 = new ComponentTFTowerRoofFence(index + 1, this);

            this.tryToFitRoof(list, rand, roof4);
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_74882_a(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, false, rand, StructureTFComponent.getStrongholdStones());
        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        this.nullifySkyLightForBoundingBox(world);
        if (this.highestOpening > 1) {
            this.makeStairs(world, rand, sbb);
        }

        this.decorateThisTower(world, rand, sbb);
        this.makeWindows(world, rand, sbb, this.size < 4);
        this.makeOpenings(world, sbb);
        return true;
    }

    protected void makeOpeningMarkers(World world, Random rand, int numMarkers, StructureBoundingBox sbb) {
        if (this.size > 4) {
            int i;
            int[] spot;

            for (i = 0; i < numMarkers; ++i) {
                spot = this.getValidOpening(rand, 0);
                this.func_151550_a(world, Blocks.field_150325_L, 0, spot[0], spot[1], spot[2], sbb);
            }

            for (i = 0; i < numMarkers; ++i) {
                spot = this.getValidOpening(rand, 1);
                this.func_151550_a(world, Blocks.field_150325_L, 1, spot[0], spot[1], spot[2], sbb);
            }

            for (i = 0; i < numMarkers; ++i) {
                spot = this.getValidOpening(rand, 2);
                this.func_151550_a(world, Blocks.field_150325_L, 2, spot[0], spot[1], spot[2], sbb);
            }

            for (i = 0; i < numMarkers; ++i) {
                spot = this.getValidOpening(rand, 3);
                this.func_151550_a(world, Blocks.field_150325_L, 3, spot[0], spot[1], spot[2], sbb);
            }
        }

    }

    protected void decorateThisTower(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781 * this.field_74887_e.field_78896_c * 756839));

        if (this.size > 3) {
            if (this.isDeadEnd()) {
                this.decorateDeadEnd(world, decoRNG, sbb);
            } else {
                this.decorateStairTower(world, decoRNG, sbb);
            }
        }

    }

    protected void decorateDeadEnd(World world, Random rand, StructureBoundingBox sbb) {
        int floors = (this.height - 1) / 5;
        int floorHeight = this.height / floors;

        int ladderDir;
        int downLadderDir;
        int i;

        for (ladderDir = 1; ladderDir < floors; ++ladderDir) {
            for (downLadderDir = 1; downLadderDir < this.size - 1; ++downLadderDir) {
                for (i = 1; i < this.size - 1; ++i) {
                    this.func_151550_a(world, Blocks.field_150344_f, 2, downLadderDir, ladderDir * floorHeight, i, sbb);
                }
            }
        }

        if (floors > 1) {
            ladderDir = 3;
            boolean flag = true;

            this.decorateFloor(world, rand, 0, 1, floorHeight, ladderDir, -1, sbb);

            for (i = 1; i < floors - 1; ++i) {
                int bottom = 1 + floorHeight * i;
                int top = floorHeight * (i + 1);

                downLadderDir = ladderDir++;
                ladderDir %= 4;
                this.decorateFloor(world, rand, i, bottom, top, ladderDir, downLadderDir, sbb);
            }

            this.decorateFloor(world, rand, floors, 1 + floorHeight * (floors - 1), this.height - 1, -1, ladderDir, sbb);
        } else {
            this.decorateFloor(world, rand, 0, 1, this.height - 1, -1, -1, sbb);
        }

    }

    protected void decorateFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        int meta;
        int dx;
        int dz;
        int dy;

        if (ladderUpDir > -1) {
            meta = this.getLadderMeta(ladderUpDir);
            dx = this.getLadderX(ladderUpDir);
            dz = this.getLadderZ(ladderUpDir);

            for (dy = bottom; dy < top; ++dy) {
                this.func_151550_a(world, Blocks.field_150468_ap, meta, dx, dy, dz, sbb);
            }
        }

        if (ladderDownDir > -1) {
            meta = this.getLadderMeta(ladderDownDir);
            dx = this.getLadderX(ladderDownDir);
            dz = this.getLadderZ(ladderDownDir);

            for (dy = bottom - 1; dy < bottom + 2; ++dy) {
                this.func_151550_a(world, Blocks.field_150468_ap, meta, dx, dy, dz, sbb);
            }
        }

        if (rand.nextInt(7) == 0 && ladderDownDir == -1) {
            this.decorateWell(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(7) == 0 && ladderDownDir == -1) {
            this.decorateSkeletonRoom(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(6) == 0 && ladderDownDir == -1) {
            this.decorateZombieRoom(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(5) == 0 && ladderDownDir == -1) {
            this.decorateCactusRoom(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(4) == 0 && ladderDownDir > -1) {
            this.decorateTreasureChest(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(5) == 0) {
            this.decorateSpiderWebs(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(12) == 0 && ladderDownDir > -1) {
            this.decorateSolidRock(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else if (rand.nextInt(3) == 0) {
            this.decorateFullLibrary(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        } else {
            this.decorateLibrary(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        }

    }

    protected void decorateWell(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        int cx = this.size / 2;
        Block waterOrLava = rand.nextInt(4) == 0 ? Blocks.field_150353_l : Blocks.field_150355_j;

        if (this.size > 5) {
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, bottom + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx - 1, bottom + 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, bottom + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, bottom + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx + 1, bottom + 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, bottom + 0, cx + 0, sbb);
            this.func_151550_a(world, waterOrLava, 0, cx + 0, bottom + 0, cx + 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, bottom + 0, cx + 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, bottom + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx - 1, bottom + 1, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, bottom + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, bottom + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx + 1, bottom + 1, cx + 1, sbb);
        }

        this.func_151550_a(world, waterOrLava, 0, cx + 0, bottom - 1, cx + 0, sbb);
    }

    protected void decorateSkeletonRoom(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        this.placeSpawnerAtCurrentPosition(world, rand, this.size / 2, bottom + 2, this.size / 2, "Skeleton", sbb);
        ArrayList chainList = new ArrayList();

        chainList.add(new ChunkCoordinates(this.size / 2, bottom + 2, this.size / 2));

        int dx;

        for (dx = 0; dx < this.size + 2; ++dx) {
            ChunkCoordinates dz = new ChunkCoordinates(2 + rand.nextInt(this.size - 4), this.height - 2, 2 + rand.nextInt(this.size - 4));

            if (!this.chainCollides(dz, chainList)) {
                for (int dy = bottom; dy < top; ++dy) {
                    this.func_151550_a(world, Blocks.field_150411_aY, 0, dz.field_71574_a, dy, dz.field_71573_c, sbb);
                }

                chainList.add(dz);
            }
        }

        for (dx = 1; dx <= this.size - 2; ++dx) {
            for (int i = 1; i <= this.size - 2; ++i) {
                if ((dx == 1 || dx == this.size - 2 || i == 1 || i == this.size - 2) && !this.isWindowPos(dx, i) && !this.isLadderPos(dx, i, ladderUpDir, ladderDownDir)) {
                    this.func_151550_a(world, Blocks.field_150321_G, 0, dx, top - 1, i, sbb);
                }
            }
        }

    }

    protected void decorateZombieRoom(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        this.placeSpawnerAtCurrentPosition(world, rand, this.size / 2, bottom + 2, this.size / 2, "Zombie", sbb);

        int i;

        for (int slabList = 1; slabList <= this.size - 2; ++slabList) {
            for (i = 1; i <= this.size - 2; ++i) {
                if (!this.isWindowPos(slabList, i) && !this.isLadderPos(slabList, i, ladderUpDir, ladderDownDir) && rand.nextInt(5) == 0) {
                    this.func_151550_a(world, Blocks.field_150338_P, 0, slabList, bottom, i, sbb);
                }
            }
        }

        ArrayList arraylist = new ArrayList();

        arraylist.add(new ChunkCoordinates(this.size / 2, bottom + 2, this.size / 2));

        for (i = 0; i < this.size - 1; ++i) {
            ChunkCoordinates slab = new ChunkCoordinates(2 + rand.nextInt(this.size - 4), this.height - 2, 2 + rand.nextInt(this.size - 4));

            if (!this.chainCollides(slab, arraylist)) {
                this.func_151550_a(world, Blocks.field_150411_aY, 0, slab.field_71574_a, bottom + 0, slab.field_71573_c, sbb);
                this.func_151550_a(world, Blocks.field_150376_bx, 2, slab.field_71574_a, bottom + 1, slab.field_71573_c, sbb);
                this.func_151550_a(world, Blocks.field_150425_aM, 0, slab.field_71574_a, bottom + 2, slab.field_71573_c, sbb);
                arraylist.add(slab);
            }
        }

    }

    protected void decorateCactusRoom(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        int i;

        for (int cactusList = 1; cactusList <= this.size - 2; ++cactusList) {
            for (i = 1; i <= this.size - 2; ++i) {
                this.func_151550_a(world, Blocks.field_150354_m, 0, cactusList, bottom - 1, i, sbb);
                if (!this.isWindowPos(cactusList, i) && !this.isLadderPos(cactusList, i, ladderUpDir, ladderDownDir) && rand.nextInt(4) == 0) {
                    this.func_151550_a(world, Blocks.field_150330_I, 0, cactusList, bottom, i, sbb);
                }
            }
        }

        ArrayList arraylist = new ArrayList();

        arraylist.add(new ChunkCoordinates(this.size / 2, bottom + 2, this.size / 2));

        for (i = 0; i < this.size + 12; ++i) {
            ChunkCoordinates cactus = new ChunkCoordinates(2 + rand.nextInt(this.size - 4), this.height - 2, 2 + rand.nextInt(this.size - 4));

            if (!this.chainCollides(cactus, arraylist)) {
                for (int dy = bottom; dy < top; ++dy) {
                    this.func_151550_a(world, Blocks.field_150434_aF, 0, cactus.field_71574_a, dy, cactus.field_71573_c, sbb);
                }

                arraylist.add(cactus);
            }
        }

    }

    protected void decorateTreasureChest(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        int cx = this.size / 2;
        int cz = cx;

        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(1), cx + 0, bottom, cx - 1, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(0), cx - 1, bottom, cx + 0, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(2), cx + 1, bottom, cx + 0, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(3), cx + 0, bottom, cx + 1, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, bottom, cx + 0, sbb);
        if (this.size > 5) {
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, bottom, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, bottom, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, bottom, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, bottom, cx + 1, sbb);
        }

        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(1) + 4, cx + 0, top - 1, cx - 1, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(0) + 4, cx - 1, top - 1, cx + 0, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(2) + 4, cx + 1, top - 1, cx + 0, sbb);
        this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(3) + 4, cx + 0, top - 1, cx + 1, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, top - 1, cx + 0, sbb);
        if (this.size > 5) {
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, top - 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, top - 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, top - 1, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, top - 1, cx + 1, sbb);
        }

        int i;

        if (this.size > 5) {
            for (i = bottom + 1; i < top - 1; ++i) {
                this.func_151550_a(world, Blocks.field_150417_aV, 5, cx - 1, i, cz - 1, sbb);
                this.func_151550_a(world, Blocks.field_150417_aV, 5, cx + 1, i, cz - 1, sbb);
                this.func_151550_a(world, Blocks.field_150417_aV, 5, cx - 1, i, cz + 1, sbb);
                this.func_151550_a(world, Blocks.field_150417_aV, 5, cx + 1, i, cz + 1, sbb);
            }
        }

        this.placeTreasureAtCurrentPosition(world, rand, cx + 0, bottom + 1, cz + 0, TFTreasure.tower_room, sbb);

        for (i = 0; i < 4; ++i) {
            ;
        }

    }

    protected void decorateSpiderWebs(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int spiderName = bottom; spiderName < top; ++spiderName) {
            int chance = top - spiderName + 2;

            for (int dx = 1; dx <= this.size - 2; ++dx) {
                for (int dz = 1; dz <= this.size - 2; ++dz) {
                    if (!this.isLadderPos(dx, dz, ladderUpDir, ladderDownDir) && rand.nextInt(chance) == 0) {
                        this.func_151550_a(world, Blocks.field_150321_G, 0, dx, spiderName, dz, sbb);
                    }
                }
            }
        }

        if (rand.nextInt(5) == 0) {
            String s;

            switch (rand.nextInt(4)) {
            case 0:
            default:
                s = "Spider";
                break;

            case 1:
                s = TFCreatures.getSpawnerNameFor("Hedge Spider");
                break;

            case 2:
                s = TFCreatures.getSpawnerNameFor("Swarm Spider");
                break;

            case 3:
                s = "CaveSpider";
            }

            this.placeSpawnerAtCurrentPosition(world, rand, this.size / 2, bottom + 2, this.size / 2, s, sbb);
        } else {
            this.decorateFurniture(world, rand, bottom, this.size - 2, sbb);
        }

    }

    protected void decorateFurniture(World world, Random rand, int bottom, int freeSpace, StructureBoundingBox sbb) {
        if (rand.nextInt(3) > 0) {
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, this.size / 2, bottom, this.size / 2, sbb);
            this.func_151550_a(world, Blocks.field_150452_aw, 0, this.size / 2, bottom + 1, this.size / 2, sbb);
        }

        if (rand.nextInt(3) == 0 && freeSpace > 1) {
            this.func_151550_a(world, Blocks.field_150485_bF, this.getStairMeta(0), this.size / 2 + 1, bottom, this.size / 2, sbb);
        }

        if (rand.nextInt(3) == 0 && freeSpace > 1) {
            this.func_151550_a(world, Blocks.field_150485_bF, this.getStairMeta(1), this.size / 2, bottom, this.size / 2 + 1, sbb);
        }

        if (rand.nextInt(3) == 0 && freeSpace > 1) {
            this.func_151550_a(world, Blocks.field_150485_bF, this.getStairMeta(2), this.size / 2 - 1, bottom, this.size / 2, sbb);
        }

        if (rand.nextInt(3) == 0 && freeSpace > 1) {
            this.func_151550_a(world, Blocks.field_150485_bF, this.getStairMeta(3), this.size / 2, bottom, this.size / 2 - 1, sbb);
        }

    }

    protected void decorateSolidRock(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int dy = bottom; dy < top; ++dy) {
            for (int dx = 1; dx <= this.size - 2; ++dx) {
                for (int dz = 1; dz <= this.size - 2; ++dz) {
                    if (!this.isLadderPos(dx, dz, ladderUpDir, ladderDownDir) && rand.nextInt(9) != 0) {
                        this.func_151550_a(world, Blocks.field_150348_b, 0, dx, dy, dz, sbb);
                    }
                }
            }
        }

    }

    protected void decorateLibrary(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int dx = 1; dx <= this.size - 2; ++dx) {
            for (int dz = 1; dz <= this.size - 2; ++dz) {
                for (int dy = bottom; dy < top - 1; ++dy) {
                    if ((dx == 1 || dx == this.size - 2 || dz == 1 || dz == this.size - 2) && !this.isWindowPos(dx, dz) && !this.isLadderPos(dx, dz, ladderUpDir, ladderDownDir)) {
                        this.func_151550_a(world, Blocks.field_150342_X, 0, dx, dy, dz, sbb);
                    }
                }
            }
        }

        if (rand.nextInt(2) == 0 && this.size > 5) {
            this.decorateLibraryTreasure(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        }

        if (rand.nextInt(2) == 0 && this.size > 5) {
            this.decorateFurniture(world, rand, bottom, this.size - 2, sbb);
        }

    }

    protected void decorateLibraryTreasure(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        switch (rand.nextInt(4)) {
        case 0:
        default:
            if (!this.isLadderPos(2, 1, ladderUpDir, ladderDownDir)) {
                this.placeTreasureAtCurrentPosition(world, rand, 2, top - 2, 1, TFTreasure.tower_library, sbb);
                break;
            }

        case 1:
            if (!this.isLadderPos(this.size - 2, 2, ladderUpDir, ladderDownDir)) {
                this.placeTreasureAtCurrentPosition(world, rand, this.size - 2, top - 2, 2, TFTreasure.tower_library, sbb);
                break;
            }

        case 2:
            if (!this.isLadderPos(this.size - 3, this.size - 2, ladderUpDir, ladderDownDir)) {
                this.placeTreasureAtCurrentPosition(world, rand, this.size - 3, top - 2, this.size - 2, TFTreasure.tower_library, sbb);
                break;
            }

        case 3:
            if (!this.isLadderPos(1, this.size - 3, ladderUpDir, ladderDownDir)) {
                this.placeTreasureAtCurrentPosition(world, rand, 1, top - 2, this.size - 3, TFTreasure.tower_library, sbb);
            }
        }

    }

    protected void decorateFullLibrary(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        for (int dx = 1; dx <= this.size - 2; ++dx) {
            for (int dz = 1; dz <= this.size - 2; ++dz) {
                for (int dy = bottom; dy < top; ++dy) {
                    if ((dx % 2 != 0 && (dz >= dx && dz <= this.size - dx - 1 || dz >= this.size - dx - 1 && dz <= dx) || dz % 2 != 0 && (dx >= dz && dx <= this.size - dz - 1 || dx >= this.size - dz - 1 && dx <= dz)) && !this.isWindowPos(dx, dy, dz) && !this.isOpeningPos(dx, dy, dz) && !this.isLadderPos(dx, dz, ladderUpDir, ladderDownDir)) {
                        this.func_151550_a(world, Blocks.field_150342_X, 0, dx, dy, dz, sbb);
                    }
                }
            }
        }

        if (rand.nextInt(2) == 0 && this.size > 5) {
            this.decorateLibraryTreasure(world, rand, bottom, top, ladderUpDir, ladderDownDir, sbb);
        }

    }

    protected void decorateTrap(World world, Random rand, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {
        int dy;

        for (dy = 2; dy <= this.size - 3; ++dy) {
            for (int dz = 2; dz <= this.size - 3; ++dz) {
                if (dy == 2 || dy == this.size - 3 || dz == 2 || dz == this.size - 3) {
                    this.func_151550_a(world, Blocks.field_150335_W, 0, dy, -1, dz, sbb);
                }
            }
        }

        for (dy = bottom - 2; dy < top - 2; ++dy) {
            this.func_151550_a(world, Blocks.field_150335_W, 0, 1, dy, 1, sbb);
            this.func_151550_a(world, Blocks.field_150335_W, 0, 1, dy, this.size - 2, sbb);
            this.func_151550_a(world, Blocks.field_150335_W, 0, this.size - 2, dy, 1, sbb);
            this.func_151550_a(world, Blocks.field_150335_W, 0, this.size - 2, dy, this.size - 2, sbb);
        }

    }

    protected boolean isWindowPos(int x, int z) {
        return x == 1 && z == this.size / 2 ? true : (x == this.size - 2 && z == this.size / 2 ? true : (x == this.size / 2 && z == 1 ? true : x == this.size / 2 && z == this.size - 2));
    }

    protected boolean isWindowPos(int x, int y, int z) {
        byte checkYDir = -1;

        if (x == 1 && z == this.size / 2) {
            checkYDir = 2;
        } else if (x == this.size - 2 && z == this.size / 2) {
            checkYDir = 0;
        } else if (x == this.size / 2 && z == 1) {
            checkYDir = 3;
        } else if (x == this.size / 2 && z == this.size - 2) {
            checkYDir = 1;
        }

        return checkYDir <= -1 ? false : !this.openingTowards[checkYDir] && (y == 2 || y == 3 || this.height > 8 && (y == this.height - 3 || y == this.height - 4));
    }

    protected boolean isOpeningPos(int x, int y, int z) {
        Iterator itr = this.openings.iterator();

        ChunkCoordinates inside;

        do {
            do {
                do {
                    if (!itr.hasNext()) {
                        return false;
                    }

                    ChunkCoordinates door = (ChunkCoordinates) itr.next();

                    inside = new ChunkCoordinates(door);
                    if (inside.field_71574_a == 0) {
                        ++inside.field_71574_a;
                    } else if (inside.field_71574_a == this.size - 1) {
                        --inside.field_71574_a;
                    } else if (inside.field_71573_c == 0) {
                        ++inside.field_71573_c;
                    } else if (inside.field_71573_c == this.size - 1) {
                        --inside.field_71573_c;
                    }
                } while (inside.field_71574_a != x);
            } while (inside.field_71573_c != z);
        } while (inside.field_71572_b != y && inside.field_71572_b + 1 != y);

        return true;
    }

    protected boolean isLadderPos(int x, int z, int ladderUpDir, int ladderDownDir) {
        return x == this.getLadderX(ladderUpDir) && z == this.getLadderZ(ladderUpDir) ? true : x == this.getLadderX(ladderDownDir) && z == this.getLadderZ(ladderDownDir);
    }

    protected int getLadderX(int ladderDir) {
        switch (ladderDir) {
        case 0:
            return this.size - 2;

        case 1:
            return this.size / 2 + 1;

        case 2:
            return 1;

        case 3:
            return this.size / 2 - 1;

        default:
            return this.size / 2;
        }
    }

    protected int getLadderZ(int ladderDir) {
        switch (ladderDir) {
        case 0:
            return this.size / 2 - 1;

        case 1:
            return this.size - 2;

        case 2:
            return this.size / 2 + 1;

        case 3:
            return 1;

        default:
            return this.size / 2;
        }
    }

    protected void decorateStairTower(World world, Random rand, StructureBoundingBox sbb) {
        if (this.height - this.highestOpening > 8) {
            int base = this.highestOpening + 3;
            int floors = (this.height - base) / 5;
            int floorHeight = (this.height - base) / floors;

            int ladderDir;
            int downLadderDir;
            int meta;

            for (ladderDir = 0; ladderDir < floors; ++ladderDir) {
                for (downLadderDir = 1; downLadderDir < this.size - 1; ++downLadderDir) {
                    for (meta = 1; meta < this.size - 1; ++meta) {
                        this.func_151550_a(world, Blocks.field_150344_f, 2, downLadderDir, ladderDir * floorHeight + base, meta, sbb);
                    }
                }
            }

            ladderDir = 3;
            boolean flag = true;

            meta = this.getLadderMeta(ladderDir);
            int dx = this.getLadderX(ladderDir);
            int dz = this.getLadderZ(ladderDir);

            int i;

            for (i = 1; i < 3; ++i) {
                this.func_151550_a(world, Blocks.field_150468_ap, meta, dx, base - i, dz, sbb);
            }

            for (i = 0; i < floors - 1; ++i) {
                int bottom = base + 1 + floorHeight * i;
                int top = base + floorHeight * (i + 1);

                downLadderDir = ladderDir++;
                ladderDir %= 4;
                this.decorateFloor(world, rand, i, bottom, top, ladderDir, downLadderDir, sbb);
            }

            this.decorateFloor(world, rand, floors, base + 1 + floorHeight * (floors - 1), this.height - 1, -1, ladderDir, sbb);
            if (base > 8) {
                switch (rand.nextInt(4)) {
                case 0:
                    this.decorateChandelier(world, rand, base + 1, sbb);
                    break;

                case 1:
                    this.decorateHangingChains(world, rand, base + 1, sbb);
                    break;

                case 2:
                    this.decorateFloatingBooks(world, rand, base + 1, sbb);
                    break;

                case 3:
                    this.decorateFloatingVines(world, rand, base + 1, sbb);
                }
            }
        } else if (this.size > 5) {
            switch (rand.nextInt(4)) {
            case 0:
                this.decorateChandelier(world, rand, this.height, sbb);
                break;

            case 1:
                this.decorateHangingChains(world, rand, this.height, sbb);
                break;

            case 2:
                this.decorateFloatingBooks(world, rand, this.height, sbb);
                break;

            case 3:
                this.decorateFloatingVines(world, rand, this.height, sbb);
            }
        } else if (this.size > 3) {
            switch (rand.nextInt(3)) {
            case 0:
                this.decorateHangingChains(world, rand, this.height, sbb);
                break;

            case 1:
                this.decorateFloatingBooks(world, rand, this.height, sbb);
                break;

            case 2:
                this.decorateFloatingVines(world, rand, this.height, sbb);
            }
        }

        this.decorateStairFloor(world, rand, sbb);
    }

    protected void decorateStairFloor(World world, Random rand, StructureBoundingBox sbb) {
        if (this.size > 5) {
            if (rand.nextInt(3) == 0) {
                this.decorateStairWell(world, rand, sbb);
            } else if (rand.nextInt(3) > 0 || this.size >= 15) {
                this.decoratePlanter(world, rand, sbb);
            }
        }

    }

    protected void decorateChandelier(World world, Random rand, int decoTop, StructureBoundingBox sbb) {
        if (decoTop >= 8 && this.size >= 8) {
            int cx = this.size / 2;
            int cy = decoTop - rand.nextInt(decoTop - 7) - 2;
            int cz = this.size / 2;

            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy + 0, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx - 1, cy + 0, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 1, cy + 0, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy + 0, cz - 1, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy + 0, cz + 1, sbb);
            this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, cy + 1, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150478_aa, 0, cx - 1, cy + 1, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 1, cy + 1, cz + 0, sbb);
            this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy + 1, cz - 1, sbb);
            this.func_151550_a(world, Blocks.field_150478_aa, 0, cx + 0, cy + 1, cz + 1, sbb);

            for (int y = cy; y < decoTop - 1; ++y) {
                this.func_151550_a(world, Blocks.field_150422_aJ, 0, cx + 0, y, cz + 0, sbb);
            }

        }
    }

    protected void decorateHangingChains(World world, Random rand, int decoTop, StructureBoundingBox sbb) {
        ArrayList chainList = new ArrayList();

        for (int i = 0; i < this.size + 2; ++i) {
            int filled = this.size < 15 ? 2 : 4;
            ChunkCoordinates chain = new ChunkCoordinates(filled + rand.nextInt(this.size - filled * 2), decoTop - 2, filled + rand.nextInt(this.size - filled * 2));

            if (!this.chainCollides(chain, chainList)) {
                int length = 1 + rand.nextInt(decoTop - 7);

                this.decorateOneChain(world, rand, chain.field_71574_a, decoTop, length, chain.field_71573_c, sbb);
                chainList.add(chain);
            }
        }

    }

    protected boolean chainCollides(ChunkCoordinates coords, List list) {
        Iterator itr = list.iterator();

        ChunkCoordinates existing;

        do {
            if (!itr.hasNext()) {
                return false;
            }

            existing = (ChunkCoordinates) itr.next();
            if (coords.field_71573_c == existing.field_71573_c && Math.abs(coords.field_71574_a - existing.field_71574_a) <= 1) {
                return true;
            }
        } while (coords.field_71574_a != existing.field_71574_a || Math.abs(coords.field_71573_c - existing.field_71573_c) > 1);

        return true;
    }

    protected void decorateOneChain(World world, Random rand, int dx, int decoTop, int length, int dz, StructureBoundingBox sbb) {
        for (int ballBlock = 1; ballBlock <= length; ++ballBlock) {
            this.func_151550_a(world, Blocks.field_150411_aY, 0, dx, decoTop - ballBlock - 1, dz, sbb);
        }

        byte ballMeta;
        Block block;

        switch (rand.nextInt(10)) {
        case 0:
            block = Blocks.field_150339_S;
            ballMeta = 0;
            break;

        case 1:
            block = Blocks.field_150342_X;
            ballMeta = 0;
            break;

        case 2:
            block = Blocks.field_150424_aL;
            ballMeta = 0;
            break;

        case 3:
            block = Blocks.field_150425_aM;
            ballMeta = 0;
            break;

        case 4:
            block = Blocks.field_150359_w;
            ballMeta = 0;
            break;

        case 5:
            block = Blocks.field_150368_y;
            ballMeta = 0;
            break;

        case 6:
            block = Blocks.field_150418_aU;
            ballMeta = 2;
            break;

        case 7:
        default:
            block = Blocks.field_150426_aN;
            ballMeta = 0;
        }

        this.func_151550_a(world, block, ballMeta, dx, decoTop - length - 2, dz, sbb);
    }

    protected void decorateFloatingBooks(World world, Random rand, int decoTop, StructureBoundingBox sbb) {
        ArrayList shelfList = new ArrayList();

        for (int i = 0; i < this.size + 2; ++i) {
            int filled = this.size < 15 ? 2 : 4;
            ChunkCoordinates shelf = new ChunkCoordinates(filled + rand.nextInt(this.size - filled * 2), decoTop - 2, filled + rand.nextInt(this.size - filled * 2));

            if (!this.chainCollides(shelf, shelfList)) {
                int bottom = 2 + rand.nextInt(decoTop - 7);
                int top = rand.nextInt(bottom - 1) + 2;

                for (int y = top; y <= bottom; ++y) {
                    this.func_151550_a(world, Blocks.field_150342_X, 0, shelf.field_71574_a, decoTop - y, shelf.field_71573_c, sbb);
                }

                shelfList.add(shelf);
            }
        }

    }

    protected void decorateFloatingVines(World world, Random rand, int decoTop, StructureBoundingBox sbb) {
        ArrayList mossList = new ArrayList();

        int y;
        int z;

        for (y = 0; y < this.size + 2; ++y) {
            z = this.size < 15 ? 2 : 4;
            ChunkCoordinates moss = new ChunkCoordinates(z + rand.nextInt(this.size - z * 2), decoTop - 2, z + rand.nextInt(this.size - z * 2));

            if (!this.chainCollides(moss, mossList)) {
                int bottom = 2 + rand.nextInt(decoTop - 7);
                int top = rand.nextInt(bottom - 1) + 2;

                for (int y1 = top; y1 <= bottom; ++y1) {
                    this.func_151550_a(world, Blocks.field_150341_Y, 0, moss.field_71574_a, decoTop - y1, moss.field_71573_c, sbb);
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(2), moss.field_71574_a + 1, decoTop - y1, moss.field_71573_c + 0, sbb);
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(0), moss.field_71574_a - 1, decoTop - y1, moss.field_71573_c + 0, sbb);
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(3), moss.field_71574_a + 0, decoTop - y1, moss.field_71573_c + 1, sbb);
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(1), moss.field_71574_a + 0, decoTop - y1, moss.field_71573_c - 1, sbb);
                }

                mossList.add(moss);
            }
        }

        for (y = this.highestOpening + 3; y < decoTop - 1; ++y) {
            for (z = 1; z < this.size - 1; ++z) {
                if (rand.nextInt(3) == 0) {
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(3), z, y, 1, sbb);
                }

                if (rand.nextInt(3) == 0) {
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(1), z, y, this.size - 2, sbb);
                }
            }

            for (z = 1; z < this.size - 1; ++z) {
                if (rand.nextInt(3) == 0) {
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(2), 1, y, z, sbb);
                }

                if (rand.nextInt(3) == 0) {
                    this.func_151550_a(world, Blocks.field_150395_bd, this.getVineMeta(0), this.size - 2, y, z, sbb);
                }
            }
        }

    }

    protected int getVineMeta(int vineDir) {
        switch ((this.getCoordBaseMode() + vineDir) % 4) {
        case 0:
            return 8;

        case 1:
            return 1;

        case 2:
            return 2;

        case 3:
            return 4;

        default:
            return -1;
        }
    }

    protected void decoratePlanter(World world, Random rand, StructureBoundingBox sbb) {
        int cx = this.size / 2;

        this.func_151550_a(world, Blocks.field_150333_U, 0, cx + 0, 1, cx + 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, cx + 0, 1, cx - 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, cx + 1, 1, cx + 0, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, cx - 1, 1, cx + 0, sbb);
        if (this.size > 7) {
            this.func_151550_a(world, Blocks.field_150334_T, 0, cx - 1, 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150334_T, 0, cx + 1, 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150334_T, 0, cx + 1, 1, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150334_T, 0, cx - 1, 1, cx + 1, sbb);
        }

        this.func_151550_a(world, Blocks.field_150349_c, 0, cx + 0, 1, cx + 0, sbb);
        Object planterBlock;
        byte planterMeta;

        switch (rand.nextInt(6)) {
        case 0:
            planterBlock = Blocks.field_150345_g;
            planterMeta = 0;
            break;

        case 1:
            planterBlock = Blocks.field_150345_g;
            planterMeta = 1;
            break;

        case 2:
            planterBlock = Blocks.field_150345_g;
            planterMeta = 2;
            break;

        case 3:
            planterBlock = Blocks.field_150345_g;
            planterMeta = 3;
            break;

        case 4:
            planterBlock = Blocks.field_150338_P;
            planterMeta = 0;
            break;

        case 5:
        default:
            planterBlock = Blocks.field_150337_Q;
            planterMeta = 0;
        }

        this.func_151550_a(world, (Block) planterBlock, planterMeta, cx + 0, 2, cx + 0, sbb);
        int whatHappened;
        int potMeta;
        int wz;

        if (planterBlock == Blocks.field_150345_g) {
            whatHappened = this.func_74865_a(cx, cx);
            potMeta = this.func_74862_a(2);
            wz = this.func_74873_b(cx, cx);
            ((BlockSapling) Blocks.field_150345_g).func_149878_d(world, whatHappened, potMeta, wz, world.field_73012_v);
        }

        if (planterBlock == Blocks.field_150338_P || planterBlock == Blocks.field_150337_Q) {
            whatHappened = this.func_74865_a(cx, cx);
            potMeta = this.func_74862_a(2);
            wz = this.func_74873_b(cx, cx);
            ((BlockMushroom) planterBlock).func_149674_a(world, whatHappened, potMeta, wz, world.field_73012_v);
        }

        Block whatHappened1 = this.func_151548_a(world, cx + 0, 2, cx + 0, sbb);

        if (whatHappened1 == planterBlock || whatHappened1 == Blocks.field_150350_a) {
            byte potMeta1 = 0;

            this.func_151550_a(world, Blocks.field_150457_bL, potMeta1, cx + 0, 2, cx + 0, sbb);
        }

    }

    protected void decorateStairWell(World world, Random rand, StructureBoundingBox sbb) {
        int cx = this.size / 2;
        byte cy = 1;
        Block waterOrLava = rand.nextInt(4) == 0 ? Blocks.field_150353_l : Blocks.field_150355_j;

        if (this.size > 7) {
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, cy + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx - 1, cy + 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, cy + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, cy + 0, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx + 1, cy + 1, cx - 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, cy + 0, cx + 0, sbb);
            this.func_151550_a(world, waterOrLava, 0, cx + 0, cy + 0, cx + 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, cy + 0, cx + 0, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx - 1, cy + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx - 1, cy + 1, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 0, cy + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150417_aV, 0, cx + 1, cy + 0, cx + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, cx + 1, cy + 1, cx + 1, sbb);
        }

        this.func_151550_a(world, waterOrLava, 0, cx + 0, cy - 1, cx + 0, sbb);
    }

    public boolean isDeadEnd() {
        return this.openings.size() == 1;
    }

    public boolean hasExitsOnAllWalls() {
        int exits = 0;

        for (int i = 0; i < 4; ++i) {
            exits += this.openingTowards[i] ? 1 : 0;
        }

        return exits == 4;
    }

    public boolean hasStairs() {
        return this.highestOpening > 1;
    }

    protected void makeOpenings(World world, StructureBoundingBox sbb) {
        Iterator iterator = this.openings.iterator();

        while (iterator.hasNext()) {
            ChunkCoordinates door = (ChunkCoordinates) iterator.next();

            this.makeDoorOpening(world, door.field_71574_a, door.field_71572_b, door.field_71573_c, sbb);
        }

    }

    protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 0, dz, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 1, dz, sbb);
        if (this.func_151548_a(world, dx, dy + 2, dz, sbb) != Blocks.field_150350_a) {
            this.func_151550_a(world, Blocks.field_150334_T, 0, dx, dy + 2, dz, sbb);
        }

        if (dx == 0) {
            this.updateLight(world, dx - 1, dy + 0, dz);
            this.updateLight(world, dx - 1, dy + 1, dz);
        }

        if (dx == this.size - 1) {
            this.updateLight(world, dx + 1, dy + 0, dz);
            this.updateLight(world, dx + 1, dy + 1, dz);
        }

        if (dz == 0) {
            this.updateLight(world, dx, dy + 0, dz - 1);
            this.updateLight(world, dx, dy + 1, dz - 1);
        }

        if (dz == this.size - 1) {
            this.updateLight(world, dx, dy + 0, dz + 1);
            this.updateLight(world, dx, dy + 1, dz + 1);
        }

    }

    public void updateLight(World world, int dx, int dy, int dz) {}

    public int[] getValidOpening(Random rand, int direction) {
        int wLength = this.size - 2;
        byte offset = 1;

        if (this.size == 15) {
            wLength = 11;
            offset = 2;
        }

        int rx;
        int rz;
        int ry;

        if (direction != 0 && direction != 2) {
            if (direction != 1 && direction != 3) {
                return new int[] { 0, 0, 0};
            } else {
                rx = offset + rand.nextInt(wLength);
                rz = direction == 1 ? this.size - 1 : 0;
                ry = this.getYByStairs(rx, rand, direction);
                return new int[] { rx, ry, rz};
            }
        } else {
            rx = direction == 0 ? this.size - 1 : 0;
            rz = offset + rand.nextInt(wLength);
            ry = this.getYByStairs(rz, rand, direction);
            return new int[] { rx, ry, rz};
        }
    }

    protected int getYByStairs(int rx, Random rand, int direction) {
        byte rise = 1;
        int base = 0;

        if (this.size == 15) {
            rise = 10;
            base = direction != 0 && direction != 2 ? 28 : 23;
        }

        if (this.size == 9) {
            rise = 6;
            base = direction != 0 && direction != 2 ? 5 : 2;
        }

        if (this.size == 7) {
            rise = 4;
            base = direction != 0 && direction != 2 ? 4 : 2;
        }

        if (this.size == 5) {
            rise = 4;
            switch (direction) {
            case 0:
                base = 3;
                break;

            case 1:
                base = 2;
                break;

            case 2:
                base = 5;
                break;

            case 3:
                base = 4;
            }
        }

        int flights = (this.height - 6 - base) / rise + 1;

        if (base > 0 && flights > 0) {
            int flightChosen = rand.nextInt(flights);
            int dy = flightChosen * rise + base;

            if (this.size == 15) {
                dy -= direction != 0 && direction != 3 ? (this.size - rx - 3) / 2 : (rx - 2) / 2;
            } else {
                dy -= direction != 0 && direction != 3 ? (this.size - rx - 2) / 2 : (rx - 1) / 2;
            }

            if (dy < 1) {
                dy = 1;
            }

            return dy;
        } else {
            return 0;
        }
    }

    protected void makeWindows(World world, Random rand, StructureBoundingBox sbb, boolean real) {
        for (int i = 0; i < 4; ++i) {
            boolean realWindows = real && !this.openingTowards[i];

            this.makeWindowBlock(world, this.size - 1, 2, this.size / 2, i, sbb, realWindows);
            this.makeWindowBlock(world, this.size - 1, 3, this.size / 2, i, sbb, realWindows);
            this.makeWindowBase(world, this.size - 1, 1, this.size / 2, i, sbb);
            if (this.height > 8) {
                this.makeWindowBlock(world, this.size - 1, this.height - 3, this.size / 2, i, sbb, realWindows);
                this.makeWindowBlock(world, this.size - 1, this.height - 4, this.size / 2, i, sbb, realWindows);
                this.makeWindowBase(world, this.size - 1, this.height - 5, this.size / 2, i, sbb);
            }
        }

    }

    protected void makeWindowBlock(World world, int x, int y, int z, int rotation, StructureBoundingBox sbb, boolean realWindows) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        Block outside = this.func_151548_a(world, x + 1, y, z, sbb);
        Block inside = this.func_151548_a(world, x - 1, y, z, sbb);

        if (realWindows && inside == Blocks.field_150350_a && outside == Blocks.field_150350_a) {
            this.func_151550_a(world, Blocks.field_150410_aZ, 0, x, y, z, sbb);
        } else {
            this.func_151550_a(world, Blocks.field_150347_e, 0, x, y, z, sbb);
        }

        this.setCoordBaseMode(temp);
    }

    protected void makeWindowBase(World world, int x, int y, int z, int rotation, StructureBoundingBox sbb) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        this.func_151550_a(world, Blocks.field_150334_T, 0, x, y, z, sbb);
        this.setCoordBaseMode(temp);
    }

    protected boolean makeStairs(World world, Random rand, StructureBoundingBox sbb) {
        return this.size == 15 ? this.makeStairs15(world, rand, sbb) : (this.size == 9 ? this.makeStairs9(world, rand, sbb) : (this.size == 7 ? this.makeStairs7(world, rand, sbb) : (this.size == 5 ? this.makeStairs5(world, rand, sbb) : false)));
    }

    protected boolean makeStairs5(World world, Random rand, StructureBoundingBox sbb) {
        byte rise = 1;
        int numFlights = this.highestOpening / rise;

        for (int i = 0; i < numFlights; ++i) {
            this.makeStairs5flight(world, rand, sbb, i * rise, 0 + i * 3, 2);
        }

        return true;
    }

    protected void makeStairs5flight(World world, Random rand, StructureBoundingBox sbb, int height, int rotation, int meta) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        BlockSlab singleSlabBlock = meta == 0 ? Blocks.field_150333_U : Blocks.field_150376_bx;
        Object doubleSlabBlock = meta == 0 ? Blocks.field_150334_T : Blocks.field_150344_f;

        this.func_151550_a(world, singleSlabBlock, meta, 2, 1 + height, 3, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 3, 1 + height, 3, sbb);
        this.setCoordBaseMode(temp);
    }

    protected boolean makeStairs7(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 1, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 1, 1, 5, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 5, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 5, 1, 1, sbb);
        byte rise = 2;
        int numFlights = this.highestOpening / rise;

        for (int i = 0; i < numFlights; ++i) {
            this.makeStairs7flight(world, rand, sbb, 1 + i * rise, 0 + i * 3, 2);
            this.makeStairs7flight(world, rand, sbb, 1 + i * rise, 2 + i * 3, 0);
        }

        return true;
    }

    protected void makeStairs7flight(World world, Random rand, StructureBoundingBox sbb, int height, int rotation, int meta) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        BlockSlab singleSlabBlock = meta == 0 ? Blocks.field_150333_U : Blocks.field_150376_bx;
        Object doubleSlabBlock = meta == 0 ? Blocks.field_150334_T : Blocks.field_150344_f;

        this.func_151550_a(world, singleSlabBlock, meta, 2, 1 + height, 5, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 3, 1 + height, 5, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 4, 2 + height, 5, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 5, 2 + height, 5, sbb);
        this.setCoordBaseMode(temp);
    }

    protected boolean makeStairs9(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 1, 1, 6, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 1, 1, 7, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 7, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 7, 1, 1, sbb);
        byte rise = 3;
        int numFlights = this.highestOpening / rise;

        for (int i = 0; i < numFlights; ++i) {
            this.makeStairs9flight(world, rand, sbb, 1 + i * rise, 0 + i * 3, 2);
            this.makeStairs9flight(world, rand, sbb, 1 + i * rise, 2 + i * 3, 0);
        }

        return true;
    }

    protected void makeStairs9flight(World world, Random rand, StructureBoundingBox sbb, int height, int rotation, int meta) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        BlockSlab singleSlabBlock = meta == 0 ? Blocks.field_150333_U : Blocks.field_150376_bx;
        Object doubleSlabBlock = meta == 0 ? Blocks.field_150334_T : Blocks.field_150344_f;

        this.func_151550_a(world, singleSlabBlock, meta, 2, 1 + height, 7, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 3, 1 + height, 7, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 4, 2 + height, 7, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 5, 2 + height, 7, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 6, 3 + height, 7, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 7, 3 + height, 7, sbb);
        this.setCoordBaseMode(temp);
    }

    protected boolean makeStairs15(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 1, 1, 9, sbb);
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 2, 1, 9, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 1, 1, 10, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 2, 1, 10, sbb);
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 1, 2, 11, sbb);
        this.func_151550_a(world, Blocks.field_150376_bx, 2, 2, 2, 11, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 1, 2, 12, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 2, 2, 12, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 1, 2, 13, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 2, 2, 13, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 3, 2, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 3, 3, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 3, 4, 11, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 3, 1, 10, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 3, 2, 10, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 3, 3, 10, sbb);
        this.func_151550_a(world, Blocks.field_150344_f, 2, 3, 1, 9, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 3, 2, 9, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 13, 1, 5, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 12, 1, 5, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 13, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 12, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 13, 2, 3, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, 12, 2, 3, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 13, 2, 2, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 12, 2, 2, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 13, 2, 1, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 12, 2, 1, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 11, 2, 3, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 3, 3, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 4, 3, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 11, 1, 4, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 2, 4, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 3, 4, sbb);
        this.func_151550_a(world, Blocks.field_150334_T, 0, 11, 1, 5, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 2, 5, sbb);
        byte rise = 5;
        int numFlights = this.highestOpening / rise;

        for (int i = 0; i < numFlights; ++i) {
            this.makeStairs15flight(world, rand, sbb, 2 + i * rise, 0 + i * 3, 2);
            this.makeStairs15flight(world, rand, sbb, 2 + i * rise, 2 + i * 3, 0);
        }

        return true;
    }

    protected void makeStairs15flight(World world, Random rand, StructureBoundingBox sbb, int height, int rotation, int meta) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode((this.getCoordBaseMode() + rotation) % 4);
        BlockSlab singleSlabBlock = meta == 0 ? Blocks.field_150333_U : Blocks.field_150376_bx;
        Object doubleSlabBlock = meta == 0 ? Blocks.field_150334_T : Blocks.field_150344_f;

        this.func_151550_a(world, singleSlabBlock, meta, 3, 1 + height, 13, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 4, 1 + height, 13, (Block) doubleSlabBlock, meta);
        this.func_151550_a(world, singleSlabBlock, meta, 5, 2 + height, 13, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 6, 2 + height, 13, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 7, 3 + height, 13, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 8, 3 + height, 13, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 9, 4 + height, 13, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 10, 4 + height, 13, (Block) doubleSlabBlock, meta);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 11, 5 + height, 13, singleSlabBlock, meta);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 12, 5 + height, 13, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 13, 5 + height, 13, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 3, 1 + height, 12, singleSlabBlock, meta);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 4, 1 + height, 12, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 5, 2 + height, 12, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 6, 2 + height, 12, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 7, 3 + height, 12, singleSlabBlock, meta);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 8, 3 + height, 12, sbb);
        this.func_151550_a(world, singleSlabBlock, meta, 9, 4 + height, 12, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 10, 4 + height, 12, (Block) doubleSlabBlock, meta);
        this.func_151550_a(world, singleSlabBlock, meta, 11, 5 + height, 12, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 12, 5 + height, 12, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 13, 5 + height, 12, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 4, 1 + height, 11, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 5, 2 + height, 11, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 6, 2 + height, 11, (Block) doubleSlabBlock, meta);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 7, 3 + height, 11, sbb);
        this.randomlyPlaceBlock(world, sbb, rand, 0.9F, 8, 3 + height, 11, (Block) doubleSlabBlock, meta);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 9, 4 + height, 11, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 10, 4 + height, 11, sbb);
        this.func_151550_a(world, (Block) doubleSlabBlock, meta, 11, 5 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 4, 2 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 5, 3 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 6, 3 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 7, 4 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 8, 4 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 9, 5 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 10, 5 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 6 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 4, 3 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 6, 4 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 8, 5 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 10, 6 + height, 11, sbb);
        this.func_151550_a(world, Blocks.field_150422_aJ, 0, 11, 7 + height, 11, sbb);
        this.setCoordBaseMode(temp);
    }

    protected void generatePaintingsOnWall(World world, Random rand, int howMany, int floorLevel, int direction, int minSize, StructureBoundingBox sbb) {
        for (int i = 0; i < howMany; ++i) {
            ChunkCoordinates pCoords = this.getRandomWallSpot(rand, floorLevel, direction, sbb);
            EntityPainting painting = new EntityPainting(world, pCoords.field_71574_a, pCoords.field_71572_b, pCoords.field_71573_c, direction);

            painting.field_70522_e = this.getPaintingOfSize(rand, minSize);
            painting.func_82328_a(direction);
            if (this.checkPainting(world, painting, sbb)) {
                world.func_72838_d(painting);
            }
        }

    }

    protected EnumArt getPaintingOfSize(Random rand, int minSize) {
        ArrayList valid = new ArrayList();
        EnumArt[] aenumart = EnumArt.values();
        int i = aenumart.length;

        for (int j = 0; j < i; ++j) {
            EnumArt art = aenumart[j];

            if (art.field_75703_B >= minSize || art.field_75704_C >= minSize) {
                valid.add(art);
            }
        }

        if (valid.size() > 0) {
            return (EnumArt) valid.get(rand.nextInt(valid.size()));
        } else {
            return null;
        }
    }

    protected boolean checkPainting(World world, EntityPainting painting, StructureBoundingBox sbb) {
        if (painting == null) {
            return false;
        } else {
            AxisAlignedBB largerBox;

            if (painting.field_82332_a != 0 && painting.field_82332_a != 2) {
                largerBox = painting.field_70121_D.func_72314_b(0.06D, 1.0D, 1.0D);
            } else {
                largerBox = painting.field_70121_D.func_72314_b(1.0D, 1.0D, 0.06D);
            }

            if (world.func_72945_a(painting, largerBox).size() > 0) {
                return false;
            } else {
                List collidingEntities = world.func_72839_b(painting, largerBox);
                Iterator iterator = collidingEntities.iterator();

                Entity entityOnList;

                do {
                    if (!iterator.hasNext()) {
                        return true;
                    }

                    entityOnList = (Entity) iterator.next();
                } while (!(entityOnList instanceof EntityHanging));

                return false;
            }
        }
    }

    protected ChunkCoordinates getRandomWallSpot(Random rand, int floorLevel, int direction, StructureBoundingBox sbb) {
        int minX = this.field_74887_e.field_78897_a + 2;
        int maxX = this.field_74887_e.field_78893_d - 2;
        int minY = this.field_74887_e.field_78895_b + floorLevel + 2;
        int maxY = this.field_74887_e.field_78894_e - 2;
        int minZ = this.field_74887_e.field_78896_c + 2;
        int maxZ = this.field_74887_e.field_78892_f - 2;

        if (direction == 0) {
            minZ = this.field_74887_e.field_78896_c;
            maxZ = this.field_74887_e.field_78896_c;
        }

        if (direction == 1) {
            maxX = this.field_74887_e.field_78893_d;
            minX = this.field_74887_e.field_78893_d;
        }

        if (direction == 2) {
            maxZ = this.field_74887_e.field_78892_f;
            minZ = this.field_74887_e.field_78892_f;
        }

        if (direction == 3) {
            minX = this.field_74887_e.field_78897_a;
            maxX = this.field_74887_e.field_78897_a;
        }

        for (int i = 0; i < 30; ++i) {
            int cx = minX + (maxX > minX ? rand.nextInt(maxX - minX) : 0);
            int cy = minY + (maxY > minY ? rand.nextInt(maxY - minY) : 0);
            int cz = minZ + (maxZ > minZ ? rand.nextInt(maxZ - minZ) : 0);

            if (sbb.func_78890_b(cx, cy, cz)) {
                return new ChunkCoordinates(cx, cy, cz);
            }
        }

        return null;
    }
}
