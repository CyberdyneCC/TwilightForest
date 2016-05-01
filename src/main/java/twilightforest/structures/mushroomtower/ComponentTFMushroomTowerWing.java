package twilightforest.structures.mushroomtower;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFMushroomTowerWing extends ComponentTFTowerWing {

    private static final int RANGE = 200;
    protected static final int FLOOR_HEIGHT = 4;
    protected static final int MAIN_SIZE = 15;
    boolean hasBase = false;
    public boolean isAscender = false;

    public ComponentTFMushroomTowerWing() {}

    protected ComponentTFMushroomTowerWing(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("hasBase", this.hasBase);
        par1NBTTagCompound.func_74757_a("isAscender", this.isAscender);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.hasBase = par1NBTTagCompound.func_74767_n("hasBase");
        this.isAscender = par1NBTTagCompound.func_74767_n("isAscender");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.hasBase = this.size > 3;
        if (this.isAscender) {
            int[] i = this.getValidOpening(rand, 2);

            i[1] = this.height - 3;
            int dest = (rand.nextInt(3) + rand.nextInt(3) + 2) * 4 + 1;
            boolean childHeight = this.makeMainBridge(list, rand, this.func_74877_c() + 1, i[0], i[1], i[2], this.size + 4, dest, 2);

            if (!childHeight) {
                System.out.println("Did not make bridge back to new main");
            } else {
                System.out.println("Made bridge back to new main");
            }
        }

        if (this.func_74877_c() < 5 && this.size > 6) {
            for (int i = 0; i < 4; ++i) {
                if (this.size >= 15 || i != 2) {
                    int[] aint = this.getValidOpening(rand, i);
                    int j = (rand.nextInt(2) + rand.nextInt(2) + 2) * 4 + 1;

                    this.makeBridge(list, rand, this.func_74877_c() + 1, aint[0], aint[1], aint[2], this.size - 4, j, i);
                }
            }
        }

        if (this.isHighest(this.field_74887_e, this.size, list) || !this.hasBase) {
            this.makeARoof(parent, list, rand);
        }

    }

    private boolean isOutOfRange(StructureComponent parent, int nx, int ny, int nz, int range) {
        return Math.abs(nx - parent.func_74874_b().func_78881_e()) > range || Math.abs(nz - parent.func_74874_b().func_78891_g()) > range;
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);

        if (this.isOutOfRange((StructureComponent) list.get(0), dx[0], dx[1], dx[2], 200)) {
            return false;
        } else {
            if (wingSize > 3) {
                dx = this.adjustCoordinates(dx[0], dx[1], dx[2], wingSize, direction, list);
            }

            ComponentTFMushroomTowerWing wing = new ComponentTFMushroomTowerWing(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);
            StructureComponent intersect = StructureComponent.func_74883_a(list, wing.func_74874_b());

            if (intersect != null && intersect != this && !(intersect instanceof ComponentTFTowerRoofMushroom)) {
                return false;
            } else {
                if (this instanceof ComponentTFMushroomTowerBridge && this.isAscender) {
                    wing.isAscender = true;
                }

                list.add(wing);
                wing.func_74861_a((StructureComponent) list.get(0), list, rand);
                this.addOpening(x, y, z, rotation);
                return true;
            }
        }
    }

    protected int[] adjustCoordinates(int x, int y, int z, int wingSize, int direction, List list) {
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (obj instanceof ComponentTFTowerWing && !(obj instanceof ComponentTFMushroomTowerBridge)) {
                ComponentTFTowerWing otherWing = (ComponentTFTowerWing) obj;

                if (wingSize == otherWing.size && otherWing.func_74874_b().func_78885_a(x - 3, z - 3, x + 3, z + 3)) {
                    switch (direction) {
                    case 0:
                        return new int[] { otherWing.func_74874_b().field_78897_a, y, otherWing.func_74874_b().field_78896_c};

                    case 1:
                        return new int[] { otherWing.func_74874_b().field_78893_d, y, otherWing.func_74874_b().field_78896_c};

                    case 2:
                        return new int[] { otherWing.func_74874_b().field_78893_d, y, otherWing.func_74874_b().field_78892_f};

                    case 3:
                        return new int[] { otherWing.func_74874_b().field_78897_a, y, otherWing.func_74874_b().field_78892_f};
                    }
                }
            }
        }

        return new int[] { x, y, z};
    }

    private boolean isHighest(StructureBoundingBox boundingBox, int size, List list) {
        StructureBoundingBox boxAbove = new StructureBoundingBox(boundingBox);

        boxAbove.field_78894_e = 256;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Object obj = iterator.next();

            if (this != obj && obj instanceof ComponentTFTowerWing && !(obj instanceof ComponentTFMushroomTowerBridge)) {
                ComponentTFTowerWing otherWing = (ComponentTFTowerWing) obj;

                if (size == otherWing.size && otherWing.func_74874_b().func_78884_a(boxAbove)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        ComponentTFTowerRoofMushroom roof = new ComponentTFTowerRoofMushroom(this.func_74877_c() + 1, this, 1.6F);

        if (StructureComponent.func_74883_a(list, roof.func_74874_b()) instanceof ComponentTFTowerRoofMushroom) {
            list.add(roof);
            roof.func_74861_a(this, list, rand);
        } else {
            roof = new ComponentTFTowerRoofMushroom(this.func_74877_c() + 1, this, 1.0F);
            if (StructureComponent.func_74883_a(list, roof.func_74874_b()) instanceof ComponentTFTowerRoofMushroom) {
                list.add(roof);
                roof.func_74861_a(this, list, rand);
            } else {
                roof = new ComponentTFTowerRoofMushroom(this.func_74877_c() + 1, this, 0.6F);
                list.add(roof);
                roof.func_74861_a(this, list, rand);
            }
        }

    }

    protected boolean makeBridge(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        return this.makeBridge(list, rand, index, x, y, z, wingSize, wingHeight, rotation, false);
    }

    protected boolean makeBridge(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation, boolean ascender) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 3, direction);

        if (wingSize == 3) {
            wingHeight = 4;
        }

        ComponentTFMushroomTowerBridge bridge = new ComponentTFMushroomTowerBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        bridge.isAscender = ascender;
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

    private boolean makeMainBridge(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 3, direction);
        ComponentTFMushroomTowerMainBridge bridge = new ComponentTFMushroomTowerMainBridge(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        list.add(bridge);
        bridge.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }

    public int[] getValidOpening(Random rand, int direction) {
        int wLength = Math.min(this.size / 3, 3);
        int offset = (this.size - wLength) / 2;
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
        int floors = this.height / 4;

        return 5 + rand.nextInt(floors - 1) * 4;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.makeTrunk(world, sbb);
        this.makeFloorsForTower(world, decoRNG, sbb);
        this.nullifySkyLightForBoundingBox(world);
        this.makeOpenings(world, sbb);
        return true;
    }

    private void makeTrunk(World world, StructureBoundingBox sbb) {
        int diameter = this.size / 2;
        int hollow = (int) ((double) diameter * 0.8D);
        int cx = diameter;
        int cz = diameter;

        for (int dx = -diameter; dx <= diameter; ++dx) {
            for (int dz = -diameter; dz <= diameter; ++dz) {
                int ax = Math.abs(dx);
                int az = Math.abs(dz);
                int dist = (int) ((double) Math.max(ax, az) + (double) Math.min(ax, az) * 0.4D);

                if (dist <= diameter) {
                    this.func_151550_a(world, this.deco.floorID, this.deco.floorMeta, dx + cx, 0, dz + cz, sbb);
                    this.func_151550_a(world, this.deco.floorID, this.deco.floorMeta, dx + cx, this.height, dz + cz, sbb);
                    int dy;

                    if (dist > hollow) {
                        for (dy = 0; dy <= this.height; ++dy) {
                            this.func_151550_a(world, this.deco.blockID, this.deco.blockMeta, dx + cx, dy, dz + cz, sbb);
                        }
                    } else {
                        for (dy = 1; dy <= this.height - 1; ++dy) {
                            this.func_151550_a(world, Blocks.field_150350_a, 0, dx + cx, dy, dz + cz, sbb);
                        }
                    }

                    if (this.hasBase) {
                        this.func_151554_b(world, this.deco.blockID, this.deco.blockMeta, dx + cx, -1, dz + cz, sbb);
                    }
                }
            }
        }

    }

    private void makeFloorsForTower(World world, Random decoRNG, StructureBoundingBox sbb) {
        int floors = this.height / 4;
        int ladderDir = 3;
        boolean downLadderDir = true;

        for (int i = 0; i < floors; ++i) {
            this.placeFloor(world, i * 4, sbb);
            ++ladderDir;
            ladderDir %= 4;
        }

    }

    private void placeFloor(World world, int dy, StructureBoundingBox sbb) {
        int diameter = this.size / 2;
        int hollow = (int) ((double) diameter * 0.8D);
        int cx = diameter;
        int cz = diameter;

        for (int dx = -diameter; dx <= diameter; ++dx) {
            for (int dz = -diameter; dz <= diameter; ++dz) {
                int ax = Math.abs(dx);
                int az = Math.abs(dz);
                int dist = (int) ((double) Math.max(ax, az) + (double) Math.min(ax, az) * 0.4D);

                if (dist <= hollow) {
                    this.func_151550_a(world, this.deco.floorID, this.isAscender ? 3 : this.deco.floorMeta, dx + cx, dy, dz + cz, sbb);
                }
            }
        }

    }

    protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        super.makeDoorOpening(world, dx, dy, dz, sbb);
        if (this.func_151548_a(world, dx, dy + 2, dz, sbb) != Blocks.field_150350_a) {
            this.func_151550_a(world, this.deco.accentID, this.deco.accentMeta, dx, dy + 2, dz, sbb);
        }

    }

    protected void decorateFloor(World world, Random rand, int floor, int bottom, int top, int ladderUpDir, int ladderDownDir, StructureBoundingBox sbb) {}
}
