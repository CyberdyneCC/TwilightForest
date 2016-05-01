package twilightforest.structures.hollowtree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFLeafSphere extends StructureTFComponent {

    int radius;

    public ComponentTFLeafSphere() {}

    protected ComponentTFLeafSphere(int index, int x, int y, int z, int radius) {
        super(index);
        this.setCoordBaseMode(0);
        this.field_74887_e = new StructureBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        this.radius = radius;
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("leafRadius", this.radius);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.radius = par1NBTTagCompound.func_74762_e("leafRadius");
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int sx = this.radius;
        int sy = this.radius;
        int sz = this.radius;

        for (byte dx = 0; dx <= this.radius; ++dx) {
            for (byte dy = 0; dy <= this.radius; ++dy) {
                for (byte dz = 0; dz <= this.radius; ++dz) {
                    boolean dist = false;
                    byte b0;

                    if (dx >= dy && dx >= dz) {
                        b0 = (byte) (dx + (byte) ((int) ((double) Math.max(dy, dz) * 0.5D + (double) Math.min(dy, dz) * 0.25D)));
                    } else if (dy >= dx && dy >= dz) {
                        b0 = (byte) (dy + (byte) ((int) ((double) Math.max(dx, dz) * 0.5D + (double) Math.min(dx, dz) * 0.25D)));
                    } else {
                        b0 = (byte) (dz + (byte) ((int) ((double) Math.max(dx, dy) * 0.5D + (double) Math.min(dx, dy) * 0.25D)));
                    }

                    if (b0 <= this.radius) {
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz + dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz - dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz + dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz - dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz + dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz - dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz + dz, sbb);
                        this.placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz - dz, sbb);
                    }
                }
            }
        }

        return true;
    }

    protected void placeBlockIfEmpty(World world, Block blockID, int meta, int x, int y, int z, StructureBoundingBox sbb) {
        if (this.func_151548_a(world, x, y, z, sbb) == Blocks.field_150350_a) {
            this.func_151550_a(world, blockID, meta, x, y, z, sbb);
        }

    }
}
