package twilightforest.entity.boss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTFLichBomb extends EntityThrowable {

    public EntityTFLichBomb(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFLichBomb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityTFLichBomb(World par1World) {
        super(par1World);
    }

    protected float func_40077_c() {
        return 0.35F;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    public void makeTrail() {
        for (int i = 0; i < 1; ++i) {
            double sx = 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) + this.field_70159_w;
            double sy = 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) + this.field_70181_x;
            double sz = 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) + this.field_70179_y;
            double dx = this.field_70165_t + sx;
            double dy = this.field_70163_u + sy;
            double dz = this.field_70161_v + sz;

            this.field_70170_p.func_72869_a("flame", dx, dy, dz, sx * -0.25D, sy * -0.25D, sz * -0.25D);
        }

    }

    public boolean func_70027_ad() {
        return true;
    }

    public boolean func_70067_L() {
        return true;
    }

    public float func_70111_Y() {
        return 1.0F;
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        this.func_70018_K();
        if (damagesource.func_76346_g() != null) {
            this.explode();
            return true;
        } else {
            return false;
        }
    }

    protected void explode() {
        float explosionPower = 2.0F;

        this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, explosionPower, false, false);
        this.func_70106_y();
    }

    protected float func_70185_h() {
        return 0.001F;
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        boolean passThrough = false;

        if (par1MovingObjectPosition.field_72308_g != null && (par1MovingObjectPosition.field_72308_g instanceof EntityTFLichBolt || par1MovingObjectPosition.field_72308_g instanceof EntityTFLichBomb)) {
            passThrough = true;
        }

        if (par1MovingObjectPosition.field_72308_g != null && par1MovingObjectPosition.field_72308_g instanceof EntityTFLich) {
            passThrough = true;
        }

        if (!passThrough) {
            this.explode();
        }

    }
}
