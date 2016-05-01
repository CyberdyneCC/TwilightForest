package twilightforest.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import twilightforest.entity.IBreathAttacker;

public class EntityAITFBreathAttack extends EntityAIBase {

    private EntityLiving entityHost;
    private EntityLivingBase attackTarget;
    protected double breathX;
    protected double breathY;
    protected double breathZ;
    private int maxDuration;
    private float attackChance;
    private float breathRange;
    private int durationLeft;

    public EntityAITFBreathAttack(EntityLiving par1EntityLiving, float speed, float range, int time, float chance) {
        this.entityHost = par1EntityLiving;
        this.breathRange = range;
        this.maxDuration = time;
        this.attackChance = chance;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        this.attackTarget = this.entityHost.func_70638_az();
        if (this.attackTarget != null && this.entityHost.func_70032_d(this.attackTarget) <= this.breathRange && this.entityHost.func_70685_l(this.attackTarget)) {
            this.breathX = this.attackTarget.field_70165_t;
            this.breathY = this.attackTarget.field_70163_u + (double) this.attackTarget.func_70047_e();
            this.breathZ = this.attackTarget.field_70161_v;
            return this.entityHost.func_70681_au().nextFloat() < this.attackChance;
        } else {
            return false;
        }
    }

    public void func_75249_e() {
        this.durationLeft = this.maxDuration;
        if (this.entityHost instanceof IBreathAttacker) {
            ((IBreathAttacker) this.entityHost).setBreathing(true);
        }

    }

    public boolean func_75253_b() {
        return this.durationLeft > 0 && !this.entityHost.field_70128_L && !this.attackTarget.field_70128_L && this.entityHost.func_70032_d(this.attackTarget) <= this.breathRange && this.entityHost.func_70685_l(this.attackTarget);
    }

    public void func_75246_d() {
        --this.durationLeft;
        this.entityHost.func_70671_ap().func_75650_a(this.breathX, this.breathY, this.breathZ, 100.0F, 100.0F);
        this.faceVec(this.breathX, this.breathY, this.breathZ, 100.0F, 100.0F);
        if (this.maxDuration - this.durationLeft > 5) {
            Entity target = this.getHeadLookTarget();

            if (target != null) {
                ((IBreathAttacker) this.entityHost).doBreathAttack(target);
            }
        }

    }

    public void func_75251_c() {
        this.durationLeft = 0;
        if (this.entityHost instanceof IBreathAttacker) {
            ((IBreathAttacker) this.entityHost).setBreathing(false);
        }

    }

    private Entity getHeadLookTarget() {
        Entity pointedEntity = null;
        double range = 30.0D;
        Vec3 srcVec = Vec3.func_72443_a(this.entityHost.field_70165_t, this.entityHost.field_70163_u + 0.25D, this.entityHost.field_70161_v);
        Vec3 lookVec = this.entityHost.func_70676_i(1.0F);
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        float f = 3.0F;
        List possibleList = this.entityHost.field_70170_p.func_72839_b(this.entityHost, this.entityHost.field_70121_D.func_72321_a(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range).func_72314_b((double) f, (double) f, (double) f));
        double hitDist = 0.0D;
        Iterator iterator = possibleList.iterator();

        while (iterator.hasNext()) {
            Entity possibleEntity = (Entity) iterator.next();

            if (possibleEntity.func_70067_L() && possibleEntity != this.entityHost) {
                float borderSize = possibleEntity.func_70111_Y();
                AxisAlignedBB collisionBB = possibleEntity.field_70121_D.func_72314_b((double) borderSize, (double) borderSize, (double) borderSize);
                MovingObjectPosition interceptPos = collisionBB.func_72327_a(srcVec, destVec);

                if (collisionBB.func_72318_a(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos != null) {
                    double possibleDist = srcVec.func_72438_d(interceptPos.field_72307_f);

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }

        return pointedEntity;
    }

    public void faceVec(double xCoord, double yCoord, double zCoord, float yawConstraint, float pitchConstraint) {
        double xOffset = xCoord - this.entityHost.field_70165_t;
        double zOffset = zCoord - this.entityHost.field_70161_v;
        double yOffset = this.entityHost.field_70163_u + 0.25D - yCoord;
        double distance = (double) MathHelper.func_76133_a(xOffset * xOffset + zOffset * zOffset);
        float xyAngle = (float) (Math.atan2(zOffset, xOffset) * 180.0D / 3.141592653589793D) - 90.0F;
        float zdAngle = (float) (-(Math.atan2(yOffset, distance) * 180.0D / 3.141592653589793D));

        this.entityHost.field_70125_A = -this.updateRotation(this.entityHost.field_70125_A, zdAngle, pitchConstraint);
        this.entityHost.field_70177_z = this.updateRotation(this.entityHost.field_70177_z, xyAngle, yawConstraint);
    }

    private float updateRotation(float par1, float par2, float par3) {
        float f = MathHelper.func_76142_g(par2 - par1);

        if (f > par3) {
            f = par3;
        }

        if (f < -par3) {
            f = -par3;
        }

        return par1 + f;
    }
}
