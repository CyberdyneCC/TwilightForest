package twilightforest.entity.boss;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTFThrownAxe extends EntityThrowable {

    private static final float PROJECTILE_DAMAGE = 6.0F;

    public EntityTFThrownAxe(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
        this.func_70105_a(0.5F, 0.5F);
    }

    public EntityTFThrownAxe(World par1World) {
        super(par1World);
        this.func_70105_a(0.5F, 0.5F);
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        boolean passThru = false;

        if (par1MovingObjectPosition.field_72308_g != null) {
            if (par1MovingObjectPosition.field_72308_g instanceof EntityTFKnightPhantom) {
                passThru = true;
            }

            if (!passThru) {
                par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 6.0F);
            }
        }

        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("largesmoke", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D);
        }

        if (!passThru && !this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }

    public boolean func_70067_L() {
        return true;
    }

    public float func_70111_Y() {
        return 1.0F;
    }

    protected float func_70182_d() {
        return 0.1F;
    }

    protected float func_70185_h() {
        return 0.001F;
    }
}
