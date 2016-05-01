package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTFMistWolf extends EntityTFHostileWolf {

    public EntityTFMistWolf(World world) {
        super(world);
        this.func_70105_a(1.4F, 1.9F);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
    }

    public int getAttackStrength(Entity par1Entity) {
        return 6;
    }

    public boolean func_70652_k(Entity par1Entity) {
        int damage = this.getAttackStrength(par1Entity);

        if (par1Entity.func_70097_a(DamageSource.func_76358_a(this), (float) damage)) {
            float myBrightness = this.func_70013_c(1.0F);

            if (par1Entity instanceof EntityLivingBase && myBrightness < 0.1F) {
                byte effectDuration = 0;

                if (this.field_70170_p.field_73013_u != EnumDifficulty.EASY) {
                    if (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL) {
                        effectDuration = 7;
                    } else if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
                        effectDuration = 15;
                    }
                }

                if (effectDuration > 0) {
                    ((EntityLivingBase) par1Entity).func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, effectDuration * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.6F;
    }
}
