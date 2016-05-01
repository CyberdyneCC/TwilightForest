package twilightforest.structures.darktower;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFDarkTowerBeard extends StructureTFComponent {

    protected int size;
    protected int height;

    public ComponentTFDarkTowerBeard() {}

    public ComponentTFDarkTowerBeard(int i, ComponentTFTowerWing wing) {
        super(i);
        this.setCoordBaseMode(wing.getCoordBaseMode());
        this.size = wing.size;
        this.height = this.size / 2;
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
        this.makeDarkBeard(world, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
        return true;
    }

    protected void makeDarkBeard(World world, StructureBoundingBox sbb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        Block frameID = TFBlocks.towerWood;
        byte frameMeta = 1;

        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                if (x == minX || x == maxX || z == minZ || z == maxZ) {
                    int length = Math.min(Math.abs(x - this.height) - 1, Math.abs(z - this.height) - 1);

                    if (length == this.height - 1) {
                        ++length;
                    }

                    if (length == -1) {
                        length = 1;
                    }

                    for (int y = maxY; y >= this.height - length; --y) {
                        this.func_151550_a(world, frameID, frameMeta, x, y, z, sbb);
                    }
                }
            }
        }

    }
}
