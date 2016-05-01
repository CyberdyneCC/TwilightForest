package twilightforest.structures.mushroomtower;

import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMushroomTowerBridge extends ComponentTFMushroomTowerWing {

    int dSize;
    int dHeight;

    public ComponentTFMushroomTowerBridge() {}

    protected ComponentTFMushroomTowerBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, this.size - 1, this.height - 1, 3, direction);
        this.dSize = pSize;
        this.dHeight = pHeight;
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("destSize", this.dSize);
        par1NBTTagCompound.func_74768_a("destHeight", this.dHeight);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.dSize = par1NBTTagCompound.func_74762_e("destSize");
        this.dHeight = par1NBTTagCompound.func_74762_e("destHeight");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        int[] dest = new int[] { this.dSize - 1, 1, 1};
        boolean madeWing = this.makeTowerWing(list, rand, this.func_74877_c(), dest[0], dest[1], dest[2], this.dSize, this.dHeight, 0);

        if (!madeWing) {
            int[] dx = this.offsetTowerCoords(dest[0], dest[1], dest[2], this.dSize, 0);

            System.out.println("Making tower wing failed when bridge was already made.  Size = " + this.dSize + ", x = " + dx[0] + " z = " + dx[2]);
        }

    }

    public StructureBoundingBox getWingBB() {
        int[] dest = this.offsetTowerCoords(this.dSize - 1, 1, 1, this.dSize, this.getCoordBaseMode());

        return StructureTFComponent.getComponentToAddBoundingBox(dest[0], dest[1], dest[2], 0, 0, 0, this.dSize - 1, this.dHeight - 1, this.dSize - 1, this.getCoordBaseMode());
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        for (int x = 0; x < this.dSize; ++x) {
            this.func_151550_a(world, this.deco.fenceID, this.deco.fenceMeta, x, 1, 0, sbb);
            this.func_151550_a(world, this.deco.fenceID, this.deco.fenceMeta, x, 1, 2, sbb);
            this.func_151550_a(world, this.deco.floorID, this.isAscender ? 3 : this.deco.floorMeta, x, 0, 1, sbb);
        }

        this.func_74878_a(world, sbb, 0, 1, 1, 2, 2, 1);
        return true;
    }
}
