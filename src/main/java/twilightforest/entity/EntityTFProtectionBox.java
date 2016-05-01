package twilightforest.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTFProtectionBox extends Entity {

    public int lifeTime;
    public int sizeX;
    public int sizeY;
    public int sizeZ;

    public EntityTFProtectionBox(World worldObj, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        super(worldObj);
        this.func_70012_b((double) minX, (double) minY, (double) minZ, 0.0F, 0.0F);
        this.sizeX = Math.abs(maxX - minX) + 1;
        this.sizeY = Math.abs(maxY - minY) + 1;
        this.sizeZ = Math.abs(maxZ - minZ) + 1;
        this.func_70105_a((float) Math.max(this.sizeX, this.sizeZ), (float) this.sizeY);
        this.lifeTime = 60;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.lifeTime <= 1) {
            this.func_70106_y();
        } else {
            --this.lifeTime;
        }

    }

    public float func_70013_c(float par1) {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float par1) {
        return 15728880;
    }

    protected void func_70088_a() {}

    protected void func_70037_a(NBTTagCompound nbttagcompound) {}

    protected void func_70014_b(NBTTagCompound nbttagcompound) {}

    @SideOnly(Side.CLIENT)
    public boolean func_90999_ad() {
        return false;
    }
}
