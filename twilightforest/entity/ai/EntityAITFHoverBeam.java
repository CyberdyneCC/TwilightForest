package twilightforest.entity.ai;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class EntityAITFHoverBeam extends EntityAIBase {

    private static final float HOVER_HEIGHT = 3.0F;
    private static final float HOVER_RADIUS = 4.0F;
    private Class classTarget;
    private EntityTFSnowQueen attacker;
    private double hoverPosX;
    private double hoverPosY;
    private double hoverPosZ;
    private int hoverTimer;
    private int beamTimer;
    private int maxHoverTime;
    private int maxBeamTime;
    private int seekTimer;
    private int maxSeekTime;
    private double beamY;
    private boolean isInPosition;

    public EntityAITFHoverBeam(EntityTFSnowQueen entityTFSnowQueen, Class class1, int hoverTime, int dropTime) {
        this.attacker = entityTFSnowQueen;
        this.classTarget = class1;
        this.func_75248_a(3);
        this.maxHoverTime = hoverTime;
        this.maxSeekTime = hoverTime;
        this.maxBeamTime = dropTime;
        this.hoverTimer = 0;
        this.isInPosition = false;
    }

    public boolean func_75250_a() {
        EntityLivingBase target = this.attacker.func_70638_az();

        return target == null ? false : (!target.func_70089_S() ? false : (this.classTarget != null && !this.classTarget.isAssignableFrom(target.getClass()) ? false : this.attacker.getCurrentPhase() == EntityTFSnowQueen.Phase.BEAM));
    }

    public boolean func_75253_b() {
        EntityLivingBase target = this.attacker.func_70638_az();

        return target != null && target.func_70089_S() ? (this.attacker.getCurrentPhase() != EntityTFSnowQueen.Phase.BEAM ? false : (this.seekTimer >= this.maxSeekTime ? false : this.beamTimer < this.maxBeamTime)) : false;
    }

    public void func_75249_e() {
        EntityLivingBase target = this.attacker.func_70638_az();

        if (target != null) {
            this.makeNewHoverSpot(target);
        }

    }

    public void func_75251_c() {
        this.seekTimer = 0;
        this.hoverTimer = 0;
        this.beamTimer = 0;
        this.isInPosition = false;
        this.attacker.setBreathing(false);
    }

    public void func_75246_d() {
        if (this.attacker.func_70092_e(this.hoverPosX, this.hoverPosY, this.hoverPosZ) <= 1.0D) {
            this.isInPosition = true;
        }

        if (this.isInPosition) {
            ++this.hoverTimer;
        } else {
            ++this.seekTimer;
        }

        if (this.hoverTimer >= this.maxHoverTime) {
            ++this.beamTimer;
            this.attacker.setBreathing(true);
            Entity offsetX = this.getHeadLookTarget();

            if (offsetX != null) {
                this.attacker.doBreathAttack(offsetX);
            }

            this.hoverPosY -= 0.05000000074505806D;
            if (this.hoverPosY < this.beamY) {
                this.hoverPosY = this.beamY;
            }
        }

        double d0 = this.hoverPosX - this.attacker.field_70165_t;
        double offsetY = this.hoverPosY - this.attacker.field_70163_u;
        double offsetZ = this.hoverPosZ - this.attacker.field_70161_v;
        double distanceDesired = d0 * d0 + offsetY * offsetY + offsetZ * offsetZ;

        distanceDesired = (double) MathHelper.func_76133_a(distanceDesired);
        if (distanceDesired > 0.5D) {
            double target = d0 / distanceDesired * 0.05D;
            double velY = offsetY / distanceDesired * 0.1D;
            double velZ = offsetZ / distanceDesired * 0.05D;

            velY += 0.019999999552965164D;
            this.attacker.func_70024_g(target, velY, velZ);
        }

        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();

        if (entitylivingbase != null) {
            float tracking = this.isInPosition ? 1.0F : 20.0F;

            this.attacker.func_70625_a(entitylivingbase, tracking, tracking);
            this.attacker.func_70671_ap().func_75651_a(entitylivingbase, tracking, tracking);
        }

    }

    private Entity getHeadLookTarget() {
        Entity pointedEntity = null;
        double range = 30.0D;
        Vec3 srcVec = Vec3.func_72443_a(this.attacker.field_70165_t, this.attacker.field_70163_u + 0.25D, this.attacker.field_70161_v);
        Vec3 lookVec = this.attacker.func_70676_i(1.0F);
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        float f = 3.0F;
        List possibleList = this.attacker.field_70170_p.func_72839_b(this.attacker, this.attacker.field_70121_D.func_72321_a(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range).func_72314_b((double) f, (double) f, (double) f));
        double hitDist = 0.0D;
        Iterator iterator = possibleList.iterator();

        while (iterator.hasNext()) {
            Entity possibleEntity = (Entity) iterator.next();

            if (possibleEntity.func_70067_L() && possibleEntity != this.attacker) {
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

    private void makeNewHoverSpot(EntityLivingBase target) {
        double hx = 0.0D;
        double hy = 0.0D;
        double hz = 0.0D;
        byte tries = 100;

        for (int i = 0; i < tries; ++i) {
            hx = target.field_70165_t + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 4.0F);
            hy = target.field_70163_u + 3.0D;
            hz = target.field_70161_v + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 4.0F);
            if (!this.isPositionOccupied(hx, hy, hz) && this.canEntitySee(this.attacker, hx, hy, hz) && this.canEntitySee(target, hx, hy, hz)) {
                break;
            }
        }

        if (tries == 99) {
            ;
        }

        this.hoverPosX = hx;
        this.hoverPosY = hy;
        this.hoverPosZ = hz;
        this.beamY = target.field_70163_u;
        this.seekTimer = 0;
    }

    private boolean isPositionOccupied(double hx, double hy, double hz) {
        float radius = this.attacker.field_70130_N / 2.0F;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(hx - (double) radius, hy, hz - (double) radius, hx + (double) radius, hy + (double) this.attacker.field_70131_O, hz + (double) radius);
        boolean isOccupied = this.attacker.field_70170_p.func_72945_a(this.attacker, aabb).isEmpty();

        return isOccupied;
    }

    protected boolean canEntitySee(Entity entity, double dx, double dy, double dz) {
        return entity.field_70170_p.func_72933_a(Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + (double) entity.func_70047_e(), entity.field_70161_v), Vec3.func_72443_a(dx, dy, dz)) == null;
    }
}
