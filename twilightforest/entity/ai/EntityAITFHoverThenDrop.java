package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class EntityAITFHoverThenDrop extends EntityAIBase {

    private static final float HOVER_HEIGHT = 6.0F;
    private static final float HOVER_RADIUS = 0.0F;
    private Class classTarget;
    private EntityTFSnowQueen attacker;
    private double hoverPosX;
    private double hoverPosY;
    private double hoverPosZ;
    private int hoverTimer;
    private int dropTimer;
    private int maxHoverTime;
    private int maxDropTime;
    private int seekTimer;
    private int maxSeekTime;
    private double dropY;

    public EntityAITFHoverThenDrop(EntityTFSnowQueen entityTFSnowQueen, Class class1, int hoverTime, int dropTime) {
        this.attacker = entityTFSnowQueen;
        this.classTarget = class1;
        this.func_75248_a(3);
        this.maxHoverTime = hoverTime;
        this.maxSeekTime = hoverTime;
        this.maxDropTime = dropTime;
        this.hoverTimer = 0;
    }

    public boolean func_75250_a() {
        EntityLivingBase target = this.attacker.func_70638_az();

        return target == null ? false : (!target.func_70089_S() ? false : (this.classTarget != null && !this.classTarget.isAssignableFrom(target.getClass()) ? false : this.attacker.getCurrentPhase() == EntityTFSnowQueen.Phase.DROP));
    }

    public boolean func_75253_b() {
        EntityLivingBase target = this.attacker.func_70638_az();

        if (target != null && target.func_70089_S()) {
            if (this.attacker.getCurrentPhase() != EntityTFSnowQueen.Phase.DROP) {
                return false;
            } else if (this.seekTimer > this.maxSeekTime) {
                return false;
            } else if (this.attacker.func_70092_e(this.hoverPosX, this.hoverPosY, this.hoverPosZ) <= 1.0D) {
                ++this.hoverTimer;
                return true;
            } else if (this.dropTimer < this.maxDropTime) {
                return true;
            } else {
                this.attacker.incrementSuccessfulDrops();
                return false;
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

    public void func_75251_c() {
        this.hoverTimer = 0;
        this.dropTimer = 0;
    }

    public void func_75246_d() {
        if (this.hoverTimer > 0) {
            ++this.hoverTimer;
        } else {
            ++this.seekTimer;
        }

        if (this.hoverTimer < this.maxHoverTime) {
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
            EntityLivingBase target = this.attacker.func_70638_az();

            if (target != null) {
                this.attacker.func_70625_a(target, 30.0F, 30.0F);
                this.attacker.func_70671_ap().func_75651_a(target, 30.0F, 30.0F);
            }
        } else {
            ++this.dropTimer;
            if (this.attacker.field_70163_u > this.dropY) {
                this.attacker.destroyBlocksInAABB(this.attacker.field_70121_D.func_72314_b(1.0D, 0.5D, 1.0D));
            }
        }

    }

    private void makeNewHoverSpot(EntityLivingBase target) {
        double hx = 0.0D;
        double hy = 0.0D;
        double hz = 0.0D;
        byte tries = 100;

        for (int i = 0; i < tries; ++i) {
            hx = target.field_70165_t + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 0.0F);
            hy = target.field_70163_u + 6.0D;
            hz = target.field_70161_v + (double) ((this.attacker.func_70681_au().nextFloat() - this.attacker.func_70681_au().nextFloat()) * 0.0F);
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
        this.dropY = target.field_70163_u - 1.0D;
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
