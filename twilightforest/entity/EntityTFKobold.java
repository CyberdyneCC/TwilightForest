package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
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
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFFlockToSameKind;
import twilightforest.entity.ai.EntityAITFPanicOnFlockDeath;

public class EntityTFKobold extends EntityMob {

    private boolean shy;

    public EntityTFKobold(World world) {
        super(world);
        this.func_70105_a(0.8F, 1.1F);
        this.shy = true;
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAITFPanicOnFlockDeath(this, 2.0F));
        this.field_70714_bg.func_75776_a(2, new EntityAILeapAtTarget(this, 0.3F));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(4, new EntityAITFFlockToSameKind(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityTFKobold(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(13.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.kobold.kobold";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.kobold.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.kobold.die";
    }

    protected Item func_146068_u() {
        return Items.field_151015_O;
    }

    protected void func_70628_a(boolean flag, int i) {
        super.func_70628_a(flag, i);
        if (this.field_70146_Z.nextInt(2) == 0) {
            this.func_145779_a(Items.field_151074_bl, 1 + i);
        }

    }

    public boolean isShy() {
        return this.shy && this.field_70718_bc <= 0;
    }

    public boolean isPanicked() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setPanicked(boolean flag) {
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 127));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 0));
        }

    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.isPanicked()) {
            for (int i = 0; i < 2; ++i) {
                this.field_70170_p.func_72869_a("splash", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 0.5D, this.field_70163_u + (double) this.func_70047_e(), this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 0.5D, 0.0D, 0.0D, 0.0D);
            }
        }

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
}
