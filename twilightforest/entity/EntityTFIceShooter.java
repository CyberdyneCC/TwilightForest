package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TwilightForestMod;

public class EntityTFIceShooter extends EntityMob implements IRangedAttackMob {

    public EntityTFIceShooter(World par1World) {
        super(par1World);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70105_a(0.8F, 1.8F);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.6F;
    }

    protected Item func_146068_u() {
        return Items.field_151126_ay;
    }

    public void func_70636_d() {
        super.func_70636_d();

        for (int i = 0; i < 3; ++i) {
            float px = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;
            float py = this.func_70047_e() + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
            float pz = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;

            TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowguardian", this.field_70142_S + (double) px, this.field_70137_T + (double) py, this.field_70136_U + (double) pz, 0.0D, 0.0D, 0.0D);
        }

    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.ice.noise";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.ice.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.ice.death";
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public int func_70641_bl() {
        return 8;
    }

    public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2) {
        EntityTFIceSnowball entitysnowball = new EntityTFIceSnowball(this.field_70170_p, this);
        double d0 = par1EntityLivingBase.field_70165_t - this.field_70165_t;
        double d1 = par1EntityLivingBase.field_70163_u + (double) par1EntityLivingBase.func_70047_e() - 1.100000023841858D - entitysnowball.field_70163_u;
        double d2 = par1EntityLivingBase.field_70161_v - this.field_70161_v;
        float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2) * 0.2F;

        entitysnowball.func_70186_c(d0, d1 + (double) f1, d2, 0.6F, 12.0F);
        this.func_85030_a("random.bow", 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
        this.field_70170_p.func_72838_d(entitysnowball);
    }
}
