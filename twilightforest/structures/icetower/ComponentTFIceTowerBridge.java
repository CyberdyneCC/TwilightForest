package twilightforest.structures.icetower;

import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFIceTowerBridge extends StructureTFComponent {

    private int length;

    public ComponentTFIceTowerBridge() {}

    public ComponentTFIceTowerBridge(int index, int x, int y, int z, int length, int direction) {
        super(index);
        this.length = length;
        this.setCoordBaseMode(direction);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, length, 6, 5, direction);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("bridgeLength", this.length);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.length = par1NBTTagCompound.func_74762_e("bridgeLength");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_74878_a(world, sbb, 0, 1, 0, this.length, 5, 4);
        this.func_151549_a(world, sbb, 0, 0, 0, this.length, 0, 4, this.deco.blockID, this.deco.blockID, false);
        this.func_151549_a(world, sbb, 0, 6, 0, this.length, 6, 4, this.deco.blockID, this.deco.blockID, false);

        for (int x = 2; x < this.length; x += 3) {
            this.func_151556_a(world, sbb, x, 1, 0, x, 5, 0, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
            this.func_151556_a(world, sbb, x, 1, 4, x, 5, 4, this.deco.pillarID, this.deco.pillarMeta, this.deco.pillarID, this.deco.pillarMeta, false);
        }

        return true;
    }
}
