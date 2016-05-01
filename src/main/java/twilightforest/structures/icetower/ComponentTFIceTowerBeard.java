package twilightforest.structures.icetower;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFIceTowerBeard extends StructureTFComponent {

    protected int size;
    protected int height;

    public ComponentTFIceTowerBeard() {}

    public ComponentTFIceTowerBeard(int i, ComponentTFTowerWing wing) {
        super(i);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = Math.round((float) this.size * 1.414F);
        this.deco = wing.deco;
        this.field_74887_e = new StructureBoundingBox(wing.func_74874_b().field_78897_a, wing.func_74874_b().field_78895_b - this.height, wing.func_74874_b().field_78896_c, wing.func_74874_b().field_78893_d, wing.func_74874_b().field_78895_b, wing.func_74874_b().field_78892_f);
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
        for (int x = 0; x < this.size; ++x) {
            for (int z = 0; z < this.size; ++z) {
                int rHeight = Math.round(MathHelper.func_76129_c((float) (x * x + z * z)));

                for (int y = 0; y < rHeight; ++y) {
                    this.func_151550_a(world, this.deco.blockID, this.deco.blockMeta, x, this.height - y, z, sbb);
                }
            }
        }

        return true;
    }
}
