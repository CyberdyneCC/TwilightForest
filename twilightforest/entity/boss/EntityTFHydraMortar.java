package twilightforest.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityTFHydraMortar extends EntityThrowable {

    private static final int BURN_FACTOR = 5;
    private static final int DIRECT_DAMAGE = 18;
    public EntityLivingBase playerReflects = null;
    public int fuse = 80;
    public boolean megaBlast = false;

    public EntityTFHydraMortar(World par1World) {
        super(par1World);
        this.func_70105_a(0.75F, 0.75F);
    }

    public EntityTFHydraMortar(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.func_70105_a(0.75F, 0.75F);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0D, this.field_70161_v);
        if (this.field_70122_E) {
            if (!this.field_70170_p.field_72995_K) {
                this.field_70159_w *= 0.9D;
                this.field_70181_x *= 0.9D;
                this.field_70179_y *= 0.9D;
            }

            if (this.fuse-- <= 0) {
                this.detonate();
            }
        }

    }

    public void setToBlasting() {
        this.megaBlast = true;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (mop.field_72308_g == null && !this.megaBlast) {
            this.field_70181_x = 0.0D;
            this.field_70122_E = true;
        } else {
            this.detonate();
        }

    }

    public float func_145772_a(Explosion par1Explosion, World par2World, int par3, int par4, int par5, Block par6Block) {
        float f = super.func_145772_a(par1Explosion, par2World, par3, par4, par5, par6Block);

        if (this.megaBlast && par6Block != Blocks.field_150357_h && par6Block != Blocks.field_150384_bq && par6Block != Blocks.field_150378_br) {
            f = Math.min(0.8F, f);
        }

        return f;
    }

    protected void detonate() {
        float explosionPower = this.megaBlast ? 4.0F : 0.1F;

        this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, explosionPower, true, true);
        if (!this.field_70170_p.field_72995_K) {
            ArrayList nearbyList = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D)));
            Iterator iterator = nearbyList.iterator();

            while (iterator.hasNext()) {
                Entity nearby = (Entity) iterator.next();

                if (nearby.func_70097_a(DamageSource.func_76362_a((EntityFireball) null, this.func_85052_h()), 18.0F) && !nearby.func_70045_F()) {
                    nearby.func_70015_d(5);
                }
            }
        }

        this.func_70106_y();
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        this.func_70018_K();
        if (damagesource.func_76346_g() != null && !this.field_70170_p.field_72995_K) {
            Vec3 vec3d = damagesource.func_76346_g().func_70040_Z();

            if (vec3d != null) {
                this.func_70186_c(vec3d.field_72450_a, vec3d.field_72448_b + 1.0D, vec3d.field_72449_c, 1.5F, 0.1F);
                this.field_70122_E = false;
                this.fuse += 20;
            }

            if (damagesource.func_76346_g() instanceof EntityLivingBase) {
                this.playerReflects = (EntityLivingBase) damagesource.func_76346_g();
            }

            return true;
        } else {
            return false;
        }
    }

    public EntityLivingBase func_85052_h() {
        return this.playerReflects != null ? this.playerReflects : super.func_85052_h();
    }

    public boolean func_70027_ad() {
        return true;
    }

    public boolean func_70067_L() {
        return true;
    }

    public float func_70111_Y() {
        return 1.5F;
    }

    protected float func_70185_h() {
        return 0.05F;
    }

    protected float func_70182_d() {
        return 0.75F;
    }

    protected float func_70183_g() {
        return -20.0F;
    }
}
