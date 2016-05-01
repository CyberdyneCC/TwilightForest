package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFFeature;

public class EntityTFTowerGhast extends EntityGhast {

    private static final int AGGRO_STATUS = 16;
    protected EntityLivingBase field_70792_g;
    protected boolean isAggressive;
    protected int field_70798_h;
    protected int explosionPower;
    protected int aggroCounter;
    protected float aggroRange;
    protected float stareRange;
    protected float wanderFactor;
    protected int inTrapCounter;
    private ChunkCoordinates homePosition = new ChunkCoordinates(0, 0, 0);
    private float maximumHomeDistance = -1.0F;

    public EntityTFTowerGhast(World par1World) {
        super(par1World);
        this.func_70105_a(4.0F, 6.0F);
        this.aggroRange = 64.0F;
        this.stareRange = 32.0F;
        this.wanderFactor = 16.0F;
        this.inTrapCounter = 0;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
    }

    protected float func_70599_aP() {
        return 0.5F;
    }

    public int func_70627_aG() {
        return 160;
    }

    public int func_70641_bl() {
        return 8;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    public int getAttackStatus() {
        return this.field_70180_af.func_75683_a(16);
    }

    public void func_70636_d() {
        float f = this.func_70013_c(1.0F);

        if (f > 0.5F) {
            this.field_70708_bq += 2;
        }

        if (this.field_70146_Z.nextBoolean()) {
            this.field_70170_p.func_72869_a("reddust", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double) this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, 0.0D, 0.0D, 0.0D);
        }

        super.func_70636_d();
    }

    protected void func_70626_be() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        }

        this.func_70623_bb();
        this.checkForTowerHome();
        if (this.inTrapCounter > 0) {
            --this.inTrapCounter;
            this.field_70792_g = null;
        } else {
            this.field_70794_e = this.field_70791_f;
            if (this.field_70792_g != null && this.field_70792_g.field_70128_L) {
                this.field_70792_g = null;
            }

            if (this.field_70792_g == null) {
                this.field_70792_g = this.findPlayerInRange();
            } else if (!this.isAggressive && this.field_70792_g instanceof EntityPlayer) {
                this.checkToIncreaseAggro((EntityPlayer) this.field_70792_g);
            }

            double offsetX = this.field_70795_b - this.field_70165_t;
            double offsetY = this.field_70796_c - this.field_70163_u;
            double offsetZ = this.field_70793_d - this.field_70161_v;
            double distanceDesired = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;

            if ((distanceDesired < 1.0D || distanceDesired > 3600.0D) && this.wanderFactor > 0.0F) {
                this.field_70795_b = this.field_70165_t + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.wanderFactor);
                this.field_70796_c = this.field_70163_u + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.wanderFactor);
                this.field_70793_d = this.field_70161_v + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.wanderFactor);
            }

            if (this.field_70792_g == null && this.wanderFactor > 0.0F) {
                if (this.field_70797_a-- <= 0) {
                    this.field_70797_a += this.field_70146_Z.nextInt(20) + 20;
                    distanceDesired = (double) MathHelper.func_76133_a(distanceDesired);
                    if (!this.isWithinHomeDistance(MathHelper.func_76128_c(this.field_70795_b), MathHelper.func_76128_c(this.field_70796_c), MathHelper.func_76128_c(this.field_70793_d))) {
                        ChunkCoordinates targetRange = TFFeature.getNearestCenterXYZ(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v), this.field_70170_p);
                        Vec3 homeVector = Vec3.func_72443_a((double) targetRange.field_71574_a - this.field_70165_t, (double) (targetRange.field_71572_b + 128) - this.field_70163_u, (double) targetRange.field_71573_c - this.field_70161_v);

                        homeVector = homeVector.func_72432_b();
                        this.field_70795_b = this.field_70165_t + homeVector.field_72450_a * 16.0D + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
                        this.field_70796_c = this.field_70163_u + homeVector.field_72448_b * 16.0D + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
                        this.field_70793_d = this.field_70161_v + homeVector.field_72449_c * 16.0D + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
                    }

                    if (this.func_70790_a(this.field_70795_b, this.field_70796_c, this.field_70793_d, distanceDesired)) {
                        this.field_70159_w += offsetX / distanceDesired * 0.1D;
                        this.field_70181_x += offsetY / distanceDesired * 0.1D;
                        this.field_70179_y += offsetZ / distanceDesired * 0.1D;
                    } else {
                        this.field_70795_b = this.field_70165_t;
                        this.field_70796_c = this.field_70163_u;
                        this.field_70793_d = this.field_70161_v;
                    }
                }
            } else {
                this.field_70159_w *= 0.75D;
                this.field_70181_x *= 0.75D;
                this.field_70179_y *= 0.75D;
            }

            double d0 = this.aggroCounter <= 0 && !this.isAggressive ? (double) this.stareRange : (double) this.aggroRange;

            if (this.field_70792_g != null && this.field_70792_g.func_70068_e(this) < d0 * d0 && this.func_70685_l(this.field_70792_g)) {
                this.func_70625_a(this.field_70792_g, 10.0F, (float) this.func_70646_bf());
                if (this.isAggressive) {
                    if (this.field_70791_f == 10) {
                        this.field_70170_p.func_72889_a((EntityPlayer) null, 1007, (int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v, 0);
                    }

                    ++this.field_70791_f;
                    if (this.field_70791_f == 20) {
                        this.spitFireball();
                        this.field_70791_f = -40;
                    }
                }
            } else {
                this.isAggressive = false;
                this.field_70792_g = null;
                this.field_70761_aq = this.field_70177_z = -((float) Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F;
                this.field_70125_A = 0.0F;
            }

            if (this.field_70791_f > 0 && !this.isAggressive) {
                --this.field_70791_f;
            }

            byte currentAggroStatus = this.field_70180_af.func_75683_a(16);
            byte newAggroStatus = (byte) (this.field_70791_f > 10 ? 2 : (this.aggroCounter <= 0 && !this.isAggressive ? 0 : 1));

            if (currentAggroStatus != newAggroStatus) {
                this.field_70180_af.func_75692_b(16, Byte.valueOf(newAggroStatus));
            }

        }
    }

    public int func_70646_bf() {
        return 500;
    }

    protected void spitFireball() {
        double offsetX = this.field_70792_g.field_70165_t - this.field_70165_t;
        double offsetY = this.field_70792_g.field_70121_D.field_72338_b + (double) (this.field_70792_g.field_70131_O / 2.0F) - (this.field_70163_u + (double) (this.field_70131_O / 2.0F));
        double offsetZ = this.field_70792_g.field_70161_v - this.field_70161_v;

        this.field_70170_p.func_72889_a((EntityPlayer) null, 1008, (int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v, 0);
        EntityLargeFireball entityFireball = new EntityLargeFireball(this.field_70170_p, this, offsetX, offsetY, offsetZ);
        double shotSpawnDistance = 0.5D;
        Vec3 lookVec = this.func_70676_i(1.0F);

        entityFireball.field_70165_t = this.field_70165_t + lookVec.field_72450_a * shotSpawnDistance;
        entityFireball.field_70163_u = this.field_70163_u + (double) (this.field_70131_O / 2.0F) + lookVec.field_72448_b * shotSpawnDistance;
        entityFireball.field_70161_v = this.field_70161_v + lookVec.field_72449_c * shotSpawnDistance;
        this.field_70170_p.func_72838_d(entityFireball);
        if (this.field_70146_Z.nextInt(6) == 0) {
            this.isAggressive = false;
        }

    }

    protected EntityLivingBase findPlayerInRange() {
        EntityPlayer closest = this.field_70170_p.func_72856_b(this, (double) this.aggroRange);

        if (closest != null) {
            float range = this.func_70032_d(closest);

            if (range < this.stareRange || this.shouldAttackPlayer(closest)) {
                return closest;
            }
        }

        return null;
    }

    protected void checkToIncreaseAggro(EntityPlayer par1EntityPlayer) {
        if (this.shouldAttackPlayer(par1EntityPlayer)) {
            if (this.aggroCounter == 0) {
                this.field_70170_p.func_72956_a(this, "mob.ghast.moan", 1.0F, 1.0F);
            }

            if (this.aggroCounter++ >= 20) {
                this.aggroCounter = 0;
                this.isAggressive = true;
            }
        } else {
            this.aggroCounter = 0;
        }

    }

    protected boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
        int dx = MathHelper.func_76128_c(par1EntityPlayer.field_70165_t);
        int dy = MathHelper.func_76128_c(par1EntityPlayer.field_70163_u);
        int dz = MathHelper.func_76128_c(par1EntityPlayer.field_70161_v);

        return this.field_70170_p.func_72937_j(dx, dy, dz) && par1EntityPlayer.func_70685_l(this);
    }

    protected boolean func_70790_a(double par1, double par3, double par5, double par7) {
        double d0 = (this.field_70795_b - this.field_70165_t) / par7;
        double d1 = (this.field_70796_c - this.field_70163_u) / par7;
        double d2 = (this.field_70793_d - this.field_70161_v) / par7;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();

        for (int i = 1; (double) i < par7; ++i) {
            axisalignedbb.func_72317_d(d0, d1, d2);
            if (!this.field_70170_p.func_72945_a(this, axisalignedbb).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        boolean wasAttacked = super.func_70097_a(par1DamageSource, par2);

        if (wasAttacked && par1DamageSource.func_76364_f() instanceof EntityLivingBase) {
            this.field_70792_g = (EntityLivingBase) par1DamageSource.func_76364_f();
            this.isAggressive = true;
            return true;
        } else {
            return wasAttacked;
        }
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D) && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL && this.isValidLightLevel();
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    protected void checkForTowerHome() {
        if (!this.hasHome()) {
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;
            TFFeature nearFeature = TFFeature.getFeatureForRegion(chunkX, chunkZ, this.field_70170_p);

            if (nearFeature != TFFeature.darkTower) {
                this.detachHome();
                this.field_70708_bq += 5;
            } else {
                ChunkCoordinates cc = TFFeature.getNearestCenterXYZ(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v), this.field_70170_p);

                this.setHomeArea(cc.field_71574_a, cc.field_71572_b + 128, cc.field_71573_c, 64);
            }
        }

    }

    public boolean isWithinHomeDistance(int x, int y, int z) {
        if (this.getMaximumHomeDistance() == -1.0F) {
            return true;
        } else {
            ChunkCoordinates home = this.getHomePosition();

            return y > 64 && y < 210 && home.func_71569_e(x, home.field_71572_b, z) < this.getMaximumHomeDistance() * this.getMaximumHomeDistance();
        }
    }

    public void setInTrap() {
        this.inTrapCounter = 10;
    }

    public void setHomeArea(int par1, int par2, int par3, int par4) {
        this.homePosition.func_71571_b(par1, par2, par3);
        this.maximumHomeDistance = (float) par4;
    }

    public ChunkCoordinates getHomePosition() {
        return this.homePosition;
    }

    public float getMaximumHomeDistance() {
        return this.maximumHomeDistance;
    }

    public void detachHome() {
        this.maximumHomeDistance = -1.0F;
    }

    public boolean hasHome() {
        return this.maximumHomeDistance != -1.0F;
    }
}
