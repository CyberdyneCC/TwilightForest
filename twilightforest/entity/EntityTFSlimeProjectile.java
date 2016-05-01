package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTFSlimeProjectile extends EntityThrowable {

    public EntityTFSlimeProjectile(World par1World) {
        super(par1World);
    }

    public EntityTFSlimeProjectile(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    protected float func_70185_h() {
        return 0.006F;
    }

    public void makeTrail() {
        for (int i = 0; i < 2; ++i) {
            double dx = this.field_70165_t + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dy = this.field_70163_u + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dz = this.field_70161_v + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());

            this.field_70170_p.func_72869_a("slime", dx, dy, dz, 0.0D, 0.0D, 0.0D);
        }

    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        this.func_70018_K();
        this.pop();
        return true;
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.field_72308_g != null && par1MovingObjectPosition.field_72308_g instanceof EntityLivingBase && par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 8.0F)) {
            ;
        }

        this.pop();
    }

    protected void pop() {
        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("slime", this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.05D, this.field_70146_Z.nextDouble() * 0.2D, this.field_70146_Z.nextGaussian() * 0.05D);
        }

        this.field_70170_p.func_72956_a(this, "mob.slime.big", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }
}
