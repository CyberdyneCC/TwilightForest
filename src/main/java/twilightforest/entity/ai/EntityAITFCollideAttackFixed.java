package twilightforest.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAITFCollideAttackFixed extends EntityAIBase {

    World worldObj;
    EntityCreature attacker;
    int attackTick;
    double speedTowardsTarget;
    boolean longMemory;
    PathEntity entityPathEntity;
    Class classTarget;
    private int delayTicks;
    private double pathX;
    private double pathY;
    private double pathZ;
    private int failedPathFindingPenalty;

    public EntityAITFCollideAttackFixed(EntityCreature par1EntityCreature, Class par2Class, double par3, boolean par5) {
        this(par1EntityCreature, par3, par5);
        this.classTarget = par2Class;
    }

    public EntityAITFCollideAttackFixed(EntityCreature par1EntityCreature, double par2, boolean par4) {
        this.attacker = par1EntityCreature;
        this.worldObj = par1EntityCreature.field_70170_p;
        this.speedTowardsTarget = par2;
        this.longMemory = par4;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();

        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.func_70089_S()) {
            return false;
        } else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass())) {
            return false;
        } else if (--this.delayTicks <= 0) {
            this.entityPathEntity = this.attacker.func_70661_as().func_75494_a(entitylivingbase);
            this.delayTicks = 4 + this.attacker.func_70681_au().nextInt(7);
            return this.entityPathEntity != null;
        } else {
            return true;
        }
    }

    public boolean func_75253_b() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();

        return entitylivingbase == null ? false : (!entitylivingbase.func_70089_S() ? false : (!this.longMemory ? !this.attacker.func_70661_as().func_75500_f() : this.attacker.func_110176_b(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v))));
    }

    public void func_75249_e() {
        this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, this.speedTowardsTarget);
        this.delayTicks = 0;
    }

    public void func_75251_c() {
        this.attacker.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();

        this.attacker.func_70671_ap().func_75651_a(entitylivingbase, 30.0F, 30.0F);
        double distanceToAttacker = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
        double attackRange = (double) (this.attacker.field_70130_N * 2.0F * this.attacker.field_70130_N * 2.0F + entitylivingbase.field_70130_N);

        --this.delayTicks;
        if ((this.longMemory || this.attacker.func_70635_at().func_75522_a(entitylivingbase)) && this.delayTicks <= 0 && (this.pathX == 0.0D && this.pathY == 0.0D && this.pathZ == 0.0D || entitylivingbase.func_70092_e(this.pathX, this.pathY, this.pathZ) >= 1.0D || this.attacker.func_70681_au().nextFloat() < 0.05F)) {
            this.pathX = entitylivingbase.field_70165_t;
            this.pathY = entitylivingbase.field_70121_D.field_72338_b;
            this.pathZ = entitylivingbase.field_70161_v;
            this.delayTicks = this.failedPathFindingPenalty + 4 + this.attacker.func_70681_au().nextInt(7);
            if (this.attacker.func_70661_as().func_75505_d() != null) {
                PathPoint finalPathPoint = this.attacker.func_70661_as().func_75505_d().func_75870_c();

                if (finalPathPoint != null && entitylivingbase.func_70092_e((double) finalPathPoint.field_75839_a, (double) finalPathPoint.field_75837_b, (double) finalPathPoint.field_75838_c) < 1.0D) {
                    this.failedPathFindingPenalty = 0;
                } else {
                    this.failedPathFindingPenalty += 10;
                }
            } else {
                this.failedPathFindingPenalty += 10;
            }

            if (distanceToAttacker > 1024.0D) {
                this.delayTicks += 10;
            } else if (distanceToAttacker > 256.0D) {
                this.delayTicks += 5;
            }

            if (!this.attacker.func_70661_as().func_75497_a(entitylivingbase, this.speedTowardsTarget)) {
                this.delayTicks += 15;
            }
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        if (distanceToAttacker <= attackRange && this.attackTick <= 0) {
            this.attackTick = 20;
            if (this.attacker.func_70694_bm() != null) {
                this.attacker.func_71038_i();
            }

            this.attacker.func_70652_k(entitylivingbase);
        }

    }
}
