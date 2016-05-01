package twilightforest.entity.ai;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.EntityTFRedcap;

public class EntityAITFRedcapShy extends EntityAITFRedcapBase {

    EntityLivingBase entityTarget;
    float speed;
    boolean lefty;
    double xPosition;
    double yPosition;
    double zPosition;
    double minDistance = 3.0D;
    double maxDistance = 6.0D;

    public EntityAITFRedcapShy(EntityTFRedcap entityTFRedcap, float moveSpeed) {
        this.entityObj = entityTFRedcap;
        this.speed = moveSpeed;
        this.lefty = (new Random()).nextBoolean();
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityLivingBase attackTarget = this.entityObj.func_70638_az();

        if (attackTarget != null && this.entityObj.isShy() && (double) attackTarget.func_70032_d(this.entityObj) <= this.maxDistance && (double) attackTarget.func_70032_d(this.entityObj) >= this.minDistance && this.isTargetLookingAtMe(attackTarget)) {
            this.entityTarget = attackTarget;
            Vec3 avoidPos = this.findCirclePoint(this.entityObj, this.entityTarget, 5.0D, this.lefty ? 1.0D : -1.0D);

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
        this.entityObj.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, (double) this.speed);
    }

    public boolean func_75253_b() {
        EntityLivingBase attackTarget = this.entityObj.func_70638_az();

        if (attackTarget == null) {
            return false;
        } else if (!this.entityTarget.func_70089_S()) {
            return false;
        } else if (this.entityObj.func_70661_as().func_75500_f()) {
            return false;
        } else {
            boolean shouldContinue = this.entityObj.isShy() && (double) attackTarget.func_70032_d(this.entityObj) < this.maxDistance && (double) attackTarget.func_70032_d(this.entityObj) > this.minDistance && this.isTargetLookingAtMe(attackTarget);

            return shouldContinue;
        }
    }

    public void func_75246_d() {
        this.entityObj.func_70671_ap().func_75651_a(this.entityTarget, 30.0F, 30.0F);
    }

    public void func_75251_c() {
        this.entityTarget = null;
        this.entityObj.func_70661_as().func_75499_g();
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
}
