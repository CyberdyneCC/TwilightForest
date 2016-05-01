package twilightforest.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTFUrGhastFireball extends EntityLargeFireball {

    public EntityTFUrGhastFireball(World worldObj, EntityTFUrGhast entityTFTowerBoss, double x, double y, double z) {
        super(worldObj, entityTFTowerBoss, x, y, z);
    }

    protected void func_70227_a(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.field_70170_p.field_72995_K && !(par1MovingObjectPosition.field_72308_g instanceof EntityFireball)) {
            if (par1MovingObjectPosition.field_72308_g != null) {
                par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76362_a(this, this.field_70235_a), 16.0F);
            }

            this.field_70170_p.func_72885_a((Entity) null, this.field_70165_t, this.field_70163_u, this.field_70161_v, (float) this.field_92057_e, true, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
            this.func_70106_y();
        }

    }
}
