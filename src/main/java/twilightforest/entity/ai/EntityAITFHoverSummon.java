package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class EntityAITFHoverSummon extends EntityAIBase {

    private static final float HOVER_HEIGHT = 6.0F;
    private static final float HOVER_RADIUS = 6.0F;
    private static final int MAX_MINIONS_AT_ONCE = 4;
    private Class classTarget;
    private EntityTFSnowQueen attacker;
    private double hoverPosX;
    private double hoverPosY;
    private double hoverPosZ;
    private int seekTimer;
    private int maxSeekTime;

    public EntityAITFHoverSummon(EntityTFSnowQueen entityTFSnowQueen, Class class1, double speed) {
        this.attacker = entityTFSnowQueen;
        this.classTarget = class1;
        this.func_75248_a(3);
        this.maxSeekTime = 80;
    }

    public boolean func_75250_a() {
        EntityLivingBase target = this.attacker.func_70638_az();

        return target == null ? false : (!target.func_70089_S() ? false : (this.classTarget != null && !this.classTarget.isAssignableFrom(target.getClass()) ? false : (this.attacker.getCurrentPhase() != EntityTFSnowQueen.Phase.SUMMON ? false : this.attacker.func_70685_l(target))));
    }

    public boolean func_75253_b() {
        EntityLivingBase target = this.attacker.func_70638_az();

        if (target != null && target.func_70089_S()) {
            if (this.attacker.getCurrentPhase() != EntityTFSnowQueen.Phase.SUMMON) {
                return false;
            } else if (this.seekTimer > this.maxSeekTime) {
                return false;
            } else {
                boolean isVisible = this.canEntitySee(this.attacker, this.hoverPosX, this.hoverPosY, this.hoverPosZ);

                if (!isVisible) {
                    ;
                }

                return isVisible;
            }
        } else {
            return false;
        }
    }

    public void func_75249_e() {
        EntityLivingBase target = this.attacker.func_70638_az();

        if (target != null) {
            this.makeNewHoverSpot(target);
        }

    }

    public void func_75251_c() {}

    public void func_75246_d() {
        ++this.seekTimer;
        EntityLivingBase target = this.attacker.func_70638_az();

        if (this.attacker.func_70092_e(this.hoverPosX, this.hoverPosY, this.hoverPosZ) <= 1.0D) {
            this.checkAndSummon();
            this.makeNewHoverSpot(target);
        }

        double offsetX = this.hoverPosX - this.attacker.field_70165_t;
        double offsetY = this.hoverPosY - this.attacker.field_70163_u;
        double offsetZ = this.hoverPosZ - this.attacker.field_70161_v;
        double distanceDesired = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;

        distanceDesired = (double) MathHelper.func_76133_a(distanceDesired);
        double velX = offsetX / distanceDesired * 0.05D;
        double velY = offsetY / distanceDesired * 0.1D;
        double velZ = offsetZ / distanceDesired * 0.05D;

        velY += 0.05000000074505806D;
        this.attacker.func_70024_g(velX, velY, velZ);
        if (target != null) {
            this.attacker.func_70625_a(target, 30.0F, 30.0F);
            this.attacker.func_70671_ap().func_75651_a(target, 30.0F, 30.0F);
        }

    }

    private void makeNewHoverSpot(EntityLivingBase target) {
        double hx = 0.0D;
        double hy = 0.0D;
        double hz = 0.0D;
        byte tries = 100;

        for (int i = 0; i < tries; ++i) {
            hx = target.field_70165_t + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 6.0F);
            hy = target.field_70163_u + 6.0D;
            hz = target.field_70161_v + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 6.0F);
            if (!this.isPositionOccupied(hx, hy, hz) && this.canEntitySee(this.attacker, hx, hy, hz) && this.canEntitySee(target, hx, hy, hz)) {
                break;
            }
        }

        if (tries == 99) {
            System.out.println("Found no spots, giving up");
        }

        this.hoverPosX = hx;
        this.hoverPosY = hy;
        this.hoverPosZ = hz;
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

    private void checkAndSummon() {
        if (this.attacker.getSummonsRemaining() > 0 && this.attacker.countMyMinions() < 4) {
            this.attacker.summonMinionAt(this.attacker.func_70638_az());
        }

    }
}
