package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;

public class EntityTFWraith extends EntityFlying implements IMob {

    public int courseChangeCooldown;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity;
    private int aggroCooldown;
    public int prevAttackCounter;
    public int attackCounter;

    public EntityTFWraith(World world) {
        super(world);
    }

    public EntityTFWraith(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    }

    public void func_70636_d() {
        if (this.field_70170_p.func_72935_r()) {
            float f = this.func_70013_c(1.0F);

            if (f > 0.5F && this.field_70170_p.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)) && this.field_70146_Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                ;
            }
        }

        super.func_70636_d();
    }

    public boolean func_70041_e_() {
        return false;
    }

    protected void func_70626_be() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        }

        this.func_70623_bb();
        this.prevAttackCounter = this.attackCounter;
        double d = this.waypointX - this.field_70165_t;
        double d1 = this.waypointY - this.field_70163_u;
        double d2 = this.waypointZ - this.field_70161_v;
        double d3 = (double) MathHelper.func_76133_a(d * d + d1 * d1 + d2 * d2);

        if (d3 < 1.0D || d3 > 60.0D) {
            this.waypointX = this.field_70165_t + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.field_70163_u + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.field_70161_v + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
                this.field_70159_w += d / d3 * 0.1D;
                this.field_70181_x += d1 / d3 * 0.1D;
                this.field_70179_y += d2 / d3 * 0.1D;
            } else {
                this.waypointX = this.field_70165_t;
                this.waypointY = this.field_70163_u;
                this.waypointZ = this.field_70161_v;
                this.targetedEntity = null;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.field_70128_L) {
            this.targetedEntity = null;
        }

        if (this.targetedEntity != null && this.aggroCooldown-- > 0) {
            float d4 = this.targetedEntity.func_70032_d(this);

            if (this.func_70685_l(this.targetedEntity)) {
                this.attackEntity(this.targetedEntity, d4);
            } else {
                this.attackBlockedEntity(this.targetedEntity, d4);
            }
        } else {
            this.targetedEntity = this.findPlayerToAttack();
            if (this.targetedEntity != null) {
                this.aggroCooldown = 20;
            }
        }

        double d0 = 64.0D;

        if (this.targetedEntity != null && this.targetedEntity.func_70068_e(this) < d0 * d0) {
            double d5 = this.targetedEntity.field_70165_t - this.field_70165_t;
            double d7 = this.targetedEntity.field_70161_v - this.field_70161_v;

            this.field_70761_aq = this.field_70177_z = -((float) Math.atan2(d5, d7)) * 180.0F / 3.141593F;
            if (this.func_70685_l(this.targetedEntity)) {
                if (this.attackCounter == 10) {
                    ;
                }

                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    this.waypointX = this.targetedEntity.field_70165_t;
                    this.waypointY = this.targetedEntity.field_70163_u - (double) this.targetedEntity.field_70131_O + 0.5D;
                    this.waypointZ = this.targetedEntity.field_70161_v;
                    this.attackCounter = -40;
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        } else {
            this.field_70761_aq = this.field_70177_z = -((float) Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.141593F;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }

    }

    protected void attackEntity(Entity entity, float f) {
        if (this.field_70724_aR <= 0 && f < 2.0F && entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            float damage = (float) this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();

            entity.func_70097_a(DamageSource.func_76358_a(this), damage);
        }

    }

    protected void attackBlockedEntity(Entity entity, float f) {}

    public boolean func_70097_a(DamageSource damagesource, float i) {
        if (super.func_70097_a(damagesource, i)) {
            Entity entity = damagesource.func_76346_g();

            if (this.field_70153_n != entity && this.field_70154_o != entity) {
                if (entity != this) {
                    this.targetedEntity = entity;
                }

                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    protected Entity findPlayerToAttack() {
        EntityPlayer entityplayer = this.field_70170_p.func_72856_b(this, 16.0D);

        return entityplayer != null && this.func_70685_l(entityplayer) ? entityplayer : null;
    }

    private boolean isCourseTraversable(double d, double d1, double d2, double d3) {
        double d4 = (this.waypointX - this.field_70165_t) / d3;
        double d5 = (this.waypointY - this.field_70163_u) / d3;
        double d6 = (this.waypointZ - this.field_70161_v) / d3;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();

        for (int i = 1; (double) i < d3; ++i) {
            axisalignedbb.func_72317_d(d4, d5, d6);
            if (this.field_70170_p.func_72945_a(this, axisalignedbb).size() > 0) {
                return false;
            }
        }

        return true;
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.wraith.wraith";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.wraith.wraith";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.wraith.wraith";
    }

    protected Item func_146068_u() {
        return Items.field_151114_aO;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

            if (TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hill3) {
                ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHill3);
            }
        }

    }

    protected boolean isValidLightLevel() {
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32)) {
            return false;
        } else {
            int l = this.field_70170_p.func_72957_l(i, j, k);

            if (this.field_70170_p.func_72911_I()) {
                int i1 = this.field_70170_p.field_73008_k;

                this.field_70170_p.field_73008_k = 10;
                l = this.field_70170_p.func_72957_l(i, j, k);
                this.field_70170_p.field_73008_k = i1;
            }

            return l <= this.field_70146_Z.nextInt(8);
        }
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.func_70601_bi();
    }
}
