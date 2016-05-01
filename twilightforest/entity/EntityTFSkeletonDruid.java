package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.item.TFItems;

public class EntityTFSkeletonDruid extends EntityMob implements IRangedAttackMob {

    public EntityTFSkeletonDruid(World world) {
        super(world);
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIRestrictSun(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIFleeSun(this, 1.0D));
        this.field_70714_bg.func_75776_a(4, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70062_b(0, new ItemStack(Items.field_151013_M));
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    }

    protected String func_70639_aQ() {
        return "mob.skeleton.say";
    }

    protected String func_70621_aR() {
        return "mob.skeleton.hurt";
    }

    protected String func_70673_aS() {
        return "mob.skeleton.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.skeleton.step", 0.15F, 1.0F);
    }

    protected Item func_146068_u() {
        return TFItems.torchberries;
    }

    protected void func_70628_a(boolean par1, int lootingModifier) {
        int numberOfItemsToDrop = this.field_70146_Z.nextInt(3 + lootingModifier);

        int i;

        for (i = 0; i < numberOfItemsToDrop; ++i) {
            this.func_145779_a(TFItems.torchberries, 1);
        }

        numberOfItemsToDrop = this.field_70146_Z.nextInt(3 + lootingModifier);

        for (i = 0; i < numberOfItemsToDrop; ++i) {
            this.func_145779_a(Items.field_151103_aS, 1);
        }

    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public void func_82196_d(EntityLivingBase attackTarget, float extraDamage) {
        EntityTFNatureBolt natureBolt = new EntityTFNatureBolt(this.field_70170_p, this);

        this.field_70170_p.func_72956_a(this, "mob.ghast.fireball", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
        natureBolt.setTarget(attackTarget);
        double tx = attackTarget.field_70165_t - this.field_70165_t;
        double ty = attackTarget.field_70163_u + (double) attackTarget.func_70047_e() - 2.699999988079071D - this.field_70163_u;
        double tz = attackTarget.field_70161_v - this.field_70161_v;
        float heightOffset = MathHelper.func_76133_a(tx * tx + tz * tz) * 0.2F;

        natureBolt.func_70186_c(tx, ty + (double) heightOffset, tz, 0.6F, 6.0F);
        this.field_70170_p.func_72838_d(natureBolt);
    }

    protected boolean func_70814_o() {
        boolean valid = false;
        int dx = MathHelper.func_76128_c(this.field_70165_t);
        int dy = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int dz = MathHelper.func_76128_c(this.field_70161_v);

        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, dx, dy, dz) > this.field_70146_Z.nextInt(32)) {
            valid = false;
        } else {
            int light = this.field_70170_p.func_72957_l(dx, dy, dz);

            valid = light <= this.field_70146_Z.nextInt(12);
        }

        return valid;
    }
}
