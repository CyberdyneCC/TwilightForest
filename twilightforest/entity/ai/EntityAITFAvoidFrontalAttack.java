package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.EntityTFRedcap;

public class EntityAITFAvoidFrontalAttack extends EntityAIBase {

    EntityLivingBase entityTarget;
    EntityLiving me;
    float speed;
    boolean lefty;
    double xPosition;
    double yPosition;
    double zPosition;
    double minDistance = 3.0D;
    double maxDistance = 6.0D;

    public EntityAITFAvoidFrontalAttack(EntityTFRedcap entityTFRedcap, float moveSpeed) {
        this.me = entityTFRedcap;
        this.speed = moveSpeed;
        this.lefty = this.me.field_70170_p.field_73012_v.nextBoolean();
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityLivingBase attackTarget = this.me.func_70638_az();

        if (attackTarget != null && (double) attackTarget.func_70032_d(this.me) <= this.maxDistance && (double) attackTarget.func_70032_d(this.me) >= this.minDistance && this.isTargetLookingAtMe(attackTarget)) {
            this.entityTarget = attackTarget;
            Vec3 avoidPos = this.findCirclePoint(this.me, this.entityTarget, 5.0D, this.lefty ? 1.0D : -1.0D);

            if (avoidPos == null) {
                return false;
            } else {
                this.xPosition = avoidPos.field_72450_a;
                this.yPosition = avoidPos.field_72448_b;
                this.zPosition = avoidPos.field_72449_c;
                return true;
            }
        } else {
            return false;
        }
    }

    public void func_75249_e() {
        this.me.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, (double) this.speed);
    }

    public boolean func_75253_b() {
        EntityLivingBase attackTarget = this.me.func_70638_az();

        if (attackTarget == null) {
            return false;
        } else if (!this.entityTarget.func_70089_S()) {
            return false;
        } else if (this.me.func_70661_as().func_75500_f()) {
            return false;
        } else {
            boolean shouldContinue = (double) attackTarget.func_70032_d(this.me) < this.maxDistance && (double) attackTarget.func_70032_d(this.me) > this.minDistance && this.isTargetLookingAtMe(attackTarget);

            return shouldContinue;
        }
    }

    public void func_75246_d() {
        this.me.func_70671_ap().func_75651_a(this.entityTarget, 30.0F, 30.0F);
    }

    public void func_75251_c() {
        this.entityTarget = null;
        this.me.func_70661_as().func_75499_g();
    }

    protected Vec3 findCirclePoint(Entity circler, Entity toCircle, double radius, double rotation) {
        double vecx = circler.field_70165_t - toCircle.field_70165_t;
        double vecz = circler.field_70161_v - toCircle.field_70161_v;
        float rangle = (float) Math.atan2(vecz, vecx);

        rangle = (float) ((double) rangle + rotation);
        double dx = (double) MathHelper.func_76134_b(rangle) * radius;
        double dz = (double) MathHelper.func_76126_a(rangle) * radius;

        return Vec3.func_72443_a(toCircle.field_70165_t + dx, circler.field_70121_D.field_72338_b, toCircle.field_70161_v + dz);
    }

    public boolean isTargetLookingAtMe(EntityLivingBase attackTarget) {
        double dx = this.me.field_70165_t - attackTarget.field_70165_t;
        double dz = this.me.field_70161_v - attackTarget.field_70161_v;
        float angle = (float) (Math.atan2(dz, dx) * 180.0D / 3.1415927410125732D) - 90.0F;
        float difference = MathHelper.func_76135_e((attackTarget.field_70177_z - angle) % 360.0F);

        return difference < 60.0F || difference > 300.0F;
    }
}
