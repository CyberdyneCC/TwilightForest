package twilightforest.entity.passive;

import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTFTinyFirefly extends EntityWeatherEffect {

    int lifeTime;
    int halfLife;
    public float glowSize;

    public EntityTFTinyFirefly(World world, double d, double d1, double d2) {
        super(world);
        this.func_70012_b(d, d1, d2, 0.0F, 0.0F);
        this.lifeTime = 10 + this.field_70146_Z.nextInt(21);
        this.halfLife = this.lifeTime / 2;
        this.glowSize = 0.2F + this.field_70146_Z.nextFloat() * 0.6F;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.lifeTime <= 1) {
            this.func_70106_y();
        } else {
            --this.lifeTime;
        }

    }

    public float getGlowBrightness() {
        return this.lifeTime <= this.halfLife ? (float) this.lifeTime / (float) this.halfLife : 1.0F - ((float) this.lifeTime - (float) this.halfLife) / (float) this.halfLife;
    }

    protected void func_70088_a() {}

    protected void func_70037_a(NBTTagCompound nbttagcompound) {}

    protected void func_70014_b(NBTTagCompound nbttagcompound) {}
}
