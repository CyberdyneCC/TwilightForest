package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFChargeAttack;
import twilightforest.item.TFItems;

public class EntityTFMinotaur extends EntityMob {

    public EntityTFMinotaur(World par1World) {
        super(par1World);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAITFChargeAttack(this, 2.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.func_70062_b(0, new ItemStack(Items.field_151006_E));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public boolean isCharging() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setCharging(boolean flag) {
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 127));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 0));
        }

    }

    public boolean func_70652_k(Entity par1Entity) {
        boolean success = super.func_70652_k(par1Entity);

        if (success && this.isCharging()) {
            par1Entity.field_70181_x += 0.4000000059604645D;
            this.field_70170_p.func_72956_a(this, "mob.irongolem.throw", 1.0F, 1.0F);
        }

        return success;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.isCharging()) {
            this.field_70721_aZ = (float) ((double) this.field_70721_aZ + 0.6D);
        }

    }

    protected String func_70639_aQ() {
        return "mob.cow.say";
    }

    protected String func_70621_aR() {
        return "mob.cow.hurt";
    }

    protected String func_70673_aS() {
        return "mob.cow.hurt";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.field_70170_p.func_72956_a(this, "mob.cow.step", 0.15F, 0.8F);
    }

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.7F;
    }

    protected Item func_146068_u() {
        return TFItems.meefRaw;
    }

    protected void func_70628_a(boolean par1, int par2) {
        int numDrops = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + par2);

        for (int i = 0; i < numDrops; ++i) {
            if (this.func_70027_ad()) {
                this.func_145779_a(TFItems.meefSteak, 1);
            } else {
                this.func_145779_a(TFItems.meefRaw, 1);
            }
        }

    }

    protected void func_70600_l(int par1) {
        this.func_145779_a(TFItems.mazeMapFocus, 1);
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
