package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.biomes.TFBiomeBase;

public class EntityTFMosquitoSwarm extends EntityMob {

    public EntityTFMosquitoSwarm(World par1World) {
        super(par1World);
        this.func_70105_a(0.7F, 1.9F);
        this.field_70138_W = 2.1F;
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(12.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.mosquito.mosquito";
    }

    public boolean func_70652_k(Entity par1Entity) {
        if (super.func_70652_k(par1Entity)) {
            if (par1Entity instanceof EntityLivingBase) {
                byte duration = 7;

                if (this.field_70170_p.field_73013_u != EnumDifficulty.EASY) {
                    if (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL) {
                        duration = 15;
                    } else if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
                        duration = 30;
                    }
                }

                if (duration > 0) {
                    ((EntityLivingBase) par1Entity).func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, duration * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_72807_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v)) != TFBiomeBase.tfSwamp ? super.func_70601_bi() : this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).size() == 0;
    }

    public int func_70641_bl() {
        return 1;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
