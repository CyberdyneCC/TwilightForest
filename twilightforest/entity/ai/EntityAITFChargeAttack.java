package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.EntityTFMinotaur;

public class EntityAITFChargeAttack extends EntityAIBase {

    protected static final double MIN_RANGE_SQ = 16.0D;
    protected static final double MAX_RANGE_SQ = 64.0D;
    protected static final int FREQ = 1;
    protected EntityCreature charger;
    protected EntityLivingBase chargeTarget;
    protected double chargeX;
    protected double chargeY;
    protected double chargeZ;
    protected float speed;
    protected int windup;
    protected boolean hasAttacked;

    public EntityAITFChargeAttack(EntityCreature entityLiving, float f) {
        this.charger = entityLiving;
        this.speed = f;
        this.windup = 0;
        this.hasAttacked = false;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        this.chargeTarget = this.charger.func_70638_az();
        if (this.chargeTarget == null) {
            return false;
        } else {
            double distance = this.charger.func_70068_e(this.chargeTarget);

            if (distance >= 16.0D && distance <= 64.0D) {
                if (!this.charger.field_70122_E) {
                    return false;
                } else {
                    Vec3 chargePos = this.findChargePoint(this.charger, this.chargeTarget, 2.1D);
                    boolean canSeeTargetFromDest = this.chargeTarget.field_70170_p.func_72933_a(Vec3.func_72443_a(this.chargeTarget.field_70165_t, this.chargeTarget.field_70163_u + (double) this.chargeTarget.func_70047_e(), this.chargeTarget.field_70161_v), chargePos) == null;

                    if (chargePos != null && canSeeTargetFromDest) {
                        this.chargeX = chargePos.field_72450_a;
                        this.chargeY = chargePos.field_72448_b;
                        this.chargeZ = chargePos.field_72449_c;
                        return this.charger.func_70681_au().nextInt(1) == 0;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }

    public void func_75249_e() {
        this.windup = 15 + this.charger.func_70681_au().nextInt(30);
    }

    public boolean func_75253_b() {
        return this.windup > 0 || !this.charger.func_70661_as().func_75500_f();
    }

    public void func_75246_d() {
        this.charger.func_70671_ap().func_75650_a(this.chargeX, this.chargeY - 1.0D, this.chargeZ, 10.0F, (float) this.charger.func_70646_bf());
        if (this.windup > 0) {
            if (--this.windup == 0) {
                this.charger.func_70661_as().func_75492_a(this.chargeX, this.chargeY, this.chargeZ, (double) this.speed);
            } else {
                this.charger.field_70721_aZ = (float) ((double) this.charger.field_70721_aZ + 0.8D);
                if (this.charger instanceof EntityTFMinotaur) {
                    ((EntityTFMinotaur) this.charger).setCharging(true);
                }
            }
        }

        double d0 = (double) (this.charger.field_70130_N * 2.1F * this.charger.field_70130_N * 2.1F);

        if (this.charger.func_70092_e(this.chargeTarget.field_70165_t, this.chargeTarget.field_70121_D.field_72338_b, this.chargeTarget.field_70161_v) <= d0 && !this.hasAttacked) {
            this.hasAttacked = true;
            this.charger.func_70652_k(this.chargeTarget);
        }

    }

    public void func_75251_c() {
        this.windup = 0;
        this.chargeTarget = null;
        this.hasAttacked = false;
        if (this.charger instanceof EntityTFMinotaur) {
            ((EntityTFMinotaur) this.charger).setCharging(false);
        }

    }

    protected Vec3 findChargePoint(Entity attacker, Entity target, double overshoot) {
        double vecx = target.field_70165_t - attacker.field_70165_t;
        double vecz = target.field_70161_v - attacker.field_70161_v;
        float rangle = (float) Math.atan2(vecz, vecx);
        double distance = (double) MathHelper.func_76133_a(vecx * vecx + vecz * vecz);
        double dx = (double) MathHelper.func_76134_b(rangle) * (distance + overshoot);
        double dz = (double) MathHelper.func_76126_a(rangle) * (distance + overshoot);

        return Vec3.func_72443_a(attacker.field_70165_t + dx, target.field_70163_u, attacker.field_70161_v + dz);
    }
}
