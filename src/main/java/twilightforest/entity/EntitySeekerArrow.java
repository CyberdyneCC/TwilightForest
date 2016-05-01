package twilightforest.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySeekerArrow extends EntityArrow {

    private EntityLivingBase homingTarget;
    double seekDistance = 5.0D;

    public EntitySeekerArrow(World par1World) {
        super(par1World);
    }

    public EntitySeekerArrow(World world, EntityPlayer player, float velocity) {
        super(world, player, velocity);
    }

    public void func_70071_h_() {
        if (this.isThisArrowFlying()) {
            double dotProduct;

            if (this.homingTarget == null) {
                double targetVec = this.field_70142_S;

                dotProduct = this.field_70137_T;
                double currentSpeed = this.field_70136_U;
                double maxX = this.field_70142_S;
                double maxY = this.field_70137_T;
                double maxZ = this.field_70136_U;
                AxisAlignedBB targetBB = AxisAlignedBB.func_72330_a(targetVec, dotProduct, currentSpeed, maxX, maxY, maxZ);
                Vec3 courseVec1 = Vec3.func_72443_a(this.field_70159_w * this.seekDistance, this.field_70181_x * this.seekDistance, this.field_70179_y * this.seekDistance);

                courseVec1.func_72442_b(0.5235988F);
                targetBB = targetBB.func_72321_a(courseVec1.field_72450_a, courseVec1.field_72448_b, courseVec1.field_72449_c);
                courseVec1 = Vec3.func_72443_a(this.field_70159_w * this.seekDistance, this.field_70181_x * this.seekDistance, this.field_70179_y * this.seekDistance);
                courseVec1.func_72442_b(-0.5235988F);
                targetBB = targetBB.func_72321_a(courseVec1.field_72450_a, courseVec1.field_72448_b, courseVec1.field_72449_c);
                targetBB.field_72338_b -= 3.0D;
                targetBB.field_72337_e += 3.0D;
                List targets = this.field_70170_p.func_94576_a(this, targetBB, IEntitySelector.field_94557_a);
                double closestDot = 1.0D;
                Iterator iterator = targets.iterator();

                while (iterator.hasNext()) {
                    Object thing = iterator.next();

                    if (thing instanceof EntityLivingBase && !(thing instanceof EntityPlayer)) {
                        EntityLivingBase living = (EntityLivingBase) thing;

                        System.out.println("Possible target : " + living);
                        courseVec1 = Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                        courseVec1 = courseVec1.func_72432_b();
                        Vec3 targetVec1 = Vec3.func_72443_a(this.field_70165_t - living.field_70165_t, this.field_70163_u - (living.field_70163_u + (double) living.func_70047_e()), this.field_70161_v - living.field_70161_v);

                        targetVec1 = targetVec1.func_72432_b();
                        double dot = courseVec1.func_72430_b(targetVec1);

                        if (dot < closestDot) {
                            this.homingTarget = living;
                            closestDot = dot;
                        }
                    }
                }

                if (targets.size() > 0) {
                    ;
                }
            } else {
                Vec3 targetVec2 = Vec3.func_72443_a(this.field_70165_t - this.homingTarget.field_70165_t, this.field_70163_u - (this.homingTarget.field_70163_u + (double) this.homingTarget.func_70047_e()), this.field_70161_v - this.homingTarget.field_70161_v);

                targetVec2 = targetVec2.func_72432_b();
                Vec3 courseVec = Vec3.func_72443_a(this.field_70159_w * this.seekDistance, this.field_70181_x * this.seekDistance, this.field_70179_y * this.seekDistance);

                courseVec = courseVec.func_72432_b();
                dotProduct = courseVec.func_72430_b(targetVec2);
                if (dotProduct < 0.0D) {
                    float currentSpeed1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);

                    currentSpeed1 = (float) ((double) currentSpeed1 * 1.0D);
                    targetVec2.field_72450_a *= (double) currentSpeed1;
                    targetVec2.field_72448_b *= (double) currentSpeed1;
                    targetVec2.field_72449_c *= (double) currentSpeed1;
                    double dx = MathHelper.func_151237_a(targetVec2.field_72450_a, -2.0D, 2.0D);
                    double dy = MathHelper.func_151237_a(targetVec2.field_72448_b, -1.0D, 1.0D);
                    double dz = MathHelper.func_151237_a(targetVec2.field_72449_c, -2.0D, 2.0D);

                    this.field_70159_w -= dx;
                    this.field_70181_x -= dy;
                    this.field_70179_y -= dz;
                } else {
                    this.homingTarget = null;
                }
            }

            this.field_70181_x += 0.04500000178813934D;
        }

        super.func_70071_h_();
    }

    private boolean isThisArrowFlying() {
        return (double) MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y) > 1.0D;
    }
}
