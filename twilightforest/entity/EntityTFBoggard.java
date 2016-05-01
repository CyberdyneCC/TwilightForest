package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
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
import twilightforest.TFFeature;
import twilightforest.entity.ai.EntityAITFChargeAttack;
import twilightforest.item.TFItems;

public class EntityTFBoggard extends EntityMob {

    private boolean shy;

    public EntityTFBoggard(World world) {
        super(world);
        this.func_70105_a(0.8F, 1.1F);
        this.shy = true;
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAITFChargeAttack(this, 2.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
    }

    public EntityTFBoggard(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(14.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.redcap.redcap";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.redcap.redcaphurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.redcap.redcapdie";
    }

    protected Item func_146068_u() {
        return Items.field_151167_ab;
    }

    protected void func_70628_a(boolean flag, int i) {
        if (this.field_70146_Z.nextInt(5) == 0) {
            this.func_145779_a(TFItems.mazeMapFocus, 1 + i);
        }

        if (this.field_70146_Z.nextInt(6) == 0) {
            this.func_145779_a(Items.field_151167_ab, 1 + i);
        }

        if (this.field_70146_Z.nextInt(9) == 0) {
            this.func_145779_a(Items.field_151035_b, 1 + i);
        }

    }

    public boolean isShy() {
        return this.shy && this.field_70718_bc <= 0;
    }

    public boolean isTargetLookingAtMe() {
        double dx = this.field_70165_t - this.field_70789_a.field_70165_t;
        double dz = this.field_70161_v - this.field_70789_a.field_70161_v;
        float angle = (float) (Math.atan2(dz, dx) * 180.0D / 3.1415927410125732D) - 90.0F;
        float difference = MathHelper.func_76135_e((this.field_70789_a.field_70177_z - angle) % 360.0F);

        return difference < 60.0F || difference > 300.0F;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

            if (TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hill1) {
                ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHill1);
            }
        }

    }

    public void func_70612_e(float par1, float par2) {
        super.func_70612_e(par1, par2);
    }
}
