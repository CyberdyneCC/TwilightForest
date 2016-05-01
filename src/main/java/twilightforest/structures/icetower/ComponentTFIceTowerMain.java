package twilightforest.structures.icetower;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFIceTowerMain extends ComponentTFIceTowerWing {

    public boolean hasBossWing;

    public ComponentTFIceTowerMain() {
        this.hasBossWing = false;
    }

    public ComponentTFIceTowerMain(World world, Random rand, int index, int x, int y, int z) {
        this(world, rand, index, x + 11, y + 40, z + 11, 2);
    }

    public ComponentTFIceTowerMain(World world, Random rand, int index, int x, int y, int z, int rotation) {
        super(index, x, y, z, 11, 31 + rand.nextInt(3) * 10, rotation);
        this.hasBossWing = false;
        if (this.deco == null) {
            this.deco = new StructureDecoratorIceTower();
        }

    }

    protected ComponentTFIceTowerMain(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
        this.hasBossWing = false;
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("hasBossWing", this.hasBossWing);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.hasBossWing = par1NBTTagCompound.func_74767_n("hasBossWing");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        super.func_74861_a(parent, list, rand);
        StructureBoundingBox towerBB = StructureBoundingBox.func_78887_a();
        Iterator myDoor = list.iterator();

        while (myDoor.hasNext()) {
            StructureComponent entranceDoor = (StructureComponent) myDoor.next();

            towerBB.func_78888_b(entranceDoor.func_74874_b());
        }

        ChunkCoordinates myDoor1 = (ChunkCoordinates) this.openings.get(0);
        ChunkCoordinates entranceDoor1 = new ChunkCoordinates(myDoor1);

        if (myDoor1.field_71574_a == 0) {
            int length = this.func_74874_b().field_78897_a - towerBB.field_78897_a;

            if (length >= 0) {
                entranceDoor1.field_71574_a -= length;
                this.makeEntranceBridge(list, rand, this.func_74877_c() + 1, myDoor1.field_71574_a, myDoor1.field_71572_b, myDoor1.field_71573_c, length, 2);
            }
        }

        if (myDoor1.field_71574_a == this.size - 1) {
            entranceDoor1.field_71574_a += towerBB.field_78893_d - this.func_74874_b().field_78893_d;
        }

        if (myDoor1.field_71573_c == 0) {
            entranceDoor1.field_71573_c += towerBB.field_78896_c - this.func_74874_b().field_78896_c;
        }

        if (myDoor1.field_71574_a == this.size - 1) {
            entranceDoor1.field_71573_c += towerBB.field_78892_f - this.func_74874_b().field_78892_f;
        }

        this.makeEntranceTower(list, rand, this.func_74877_c() + 1, entranceDoor1.field_71574_a, entranceDoor1.field_71572_b, entranceDoor1.field_71573_c, 11, 11, this.getCoordBaseMode());
    }

    private void makeEntranceBridge(List list, Random rand, int index, int x, int y, int z, int length, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        ChunkCoordinates dest = this.offsetTowerCCoords(x, y, z, 5, direction);
        ComponentTFIceTowerBridge bridge = new ComponentTFIceTowerBridge(index, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, length, direction);

        list.add(bridge);
        bridge.func_74861_a((StructureComponent) list.get(0), list, rand);
    }

    public boolean makeEntranceTower(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);
        ComponentTFIceTowerEntrance entrance = new ComponentTFIceTowerEntrance(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        list.add(entrance);
        entrance.func_74861_a((StructureComponent) list.get(0), list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }
}
