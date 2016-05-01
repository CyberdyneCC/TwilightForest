package twilightforest.structures.lichtower;

import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public abstract class ComponentTFTowerRoof extends StructureTFComponent {

    protected int size;
    protected int height;

    public ComponentTFTowerRoof() {}

    public ComponentTFTowerRoof(int i, ComponentTFTowerWing wing) {
        super(i);
        this.spawnListIndex = -1;
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("roofSize", this.size);
        par1NBTTagCompound.func_74768_a("roofHeight", this.height);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.size = par1NBTTagCompound.func_74762_e("roofSize");
        this.height = par1NBTTagCompound.func_74762_e("roofHeight");
    }

    protected void makeAttachedOverhangBB(ComponentTFTowerWing wing) {
        switch (this.getCoordBaseMode()) {
        case 0:
            this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c - 1, wing.func_74874_b().field_78893_d + 1, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f + 1);
            break;

        case 1:
            this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - 1, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c, wing.func_74874_b().field_78893_d + 1, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f + 1);
            break;

        case 2:
            this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - 1, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c - 1, wing.func_74874_b().field_78893_d, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f + 1);
            break;

        case 3:
            this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - 1, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c - 1, wing.func_74874_b().field_78893_d + 1, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f);
        }

    }

    protected void makeCapBB(ComponentTFTowerWing wing) {
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c, wing.func_74874_b().field_78893_d, wing.func_74874_b().field_78894_e + this.height, wing.func_74874_b().field_78892_f);
    }

    protected void makeOverhangBB(ComponentTFTowerWing wing) {
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a - 1, wing.func_74874_b().field_78894_e, wing.func_74874_b().field_78896_c - 1, wing.func_74874_b().field_78893_d + 1, wing.func_74874_b().field_78894_e + this.height - 1, wing.func_74874_b().field_78892_f + 1);
    }

    public boolean func_74875_a(World world, Random random, StructureBoundingBox structureboundingbox) {
        return false;
    }

    public boolean fits(ComponentTFTowerWing parent, List list, Random rand) {
        return StructureComponent.func_74883_a(list, this.field_74887_e) == parent;
    }
}
