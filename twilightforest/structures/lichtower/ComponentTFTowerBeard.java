package twilightforest.structures.lichtower;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFTowerBeard extends StructureTFComponent {

    int size;
    int height;

    public ComponentTFTowerBeard() {}

    public ComponentTFTowerBeard(int i, ComponentTFTowerWing wing) {
        super(i);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size - 2;
        this.height = this.size / 2;
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a + 1, wing.func_74874_b().field_78895_b - this.height - 1, wing.func_74874_b().field_78896_c + 1, wing.func_74874_b().field_78893_d - 1, wing.func_74874_b().field_78895_b - 1, wing.func_74874_b().field_78892_f - 1);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("beardSize", this.size);
        par1NBTTagCompound.func_74768_a("beardHeight", this.height);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.size = par1NBTTagCompound.func_74762_e("beardSize");
        this.height = par1NBTTagCompound.func_74762_e("beardHeight");
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        return this.makePyramidBeard(world, rand, sbb);
    }

    private boolean makePyramidBeard(World world, Random rand, StructureBoundingBox sbb) {
        for (int y = 0; y <= this.height; ++y) {
            int max = this.size - y - 1;

            this.func_74882_a(world, sbb, y, this.height - y, y, max, this.height - y, max, false, rand, StructureTFComponent.getStrongholdStones());
        }

        return true;
    }
}
