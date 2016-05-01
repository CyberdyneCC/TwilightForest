package twilightforest.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFBreathAttack;

public class EntityTFFireBeetle extends EntityMob implements IBreathAttacker {

    public static final int BREATH_DURATION = 10;
    public static final int BREATH_DAMAGE = 2;

    public EntityTFFireBeetle(World world) {
        super(world);
        this.func_70105_a(1.1F, 0.75F);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAITFBreathAttack(this, 1.0F, 5.0F, 30, 0.1F));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityTFFireBeetle(World world, double x, double y, double z) {
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
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(25.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return "mob.spider.say";
    }

    protected String func_70673_aS() {
        return "mob.spider.death";
    }

    protected void func_145780_a(int i, int j, int k, Block block) {
        this.field_70170_p.func_72956_a(this, "mob.spider.step", 0.15F, 1.0F);
    }

    protected Item func_146068_u() {
        return Items.field_151016_H;
    }

    public boolean isBreathing() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setBreathing(boolean flag) {
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 127));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 0));
        }

    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.isBreathing()) {
            Vec3 look = this.func_70040_Z();
            double dist = 0.9D;
            double px = this.field_70165_t + look.field_72450_a * dist;
            double py = this.field_70163_u + 0.25D + look.field_72448_b * dist;
            double pz = this.field_70161_v + look.field_72449_c * dist;

            for (int i = 0; i < 2; ++i) {
                double dx = look.field_72450_a;
                double dy = look.field_72448_b;
                double dz = look.field_72449_c;
                double spread = 5.0D + this.func_70681_au().nextDouble() * 2.5D;
                double velocity = 0.15D + this.func_70681_au().nextDouble() * 0.15D;

                dx += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dy += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dz += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dx *= velocity;
                dy *= velocity;
                dz *= velocity;
                this.field_70170_p.func_72869_a(this.getFlameParticle(), px, py, pz, dx, dy, dz);
            }

            this.playBreathSound();
        }

    }

    public String getFlameParticle() {
        return "flame";
    }

    public void playBreathSound() {
        this.field_70170_p.func_72908_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "mob.ghast.fireball", this.field_70146_Z.nextFloat() * 0.5F, this.field_70146_Z.nextFloat() * 0.5F);
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float par1) {
        return this.isBreathing() ? 15728880 : super.func_70070_b(par1);
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public int func_70646_bf() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        return 1.1F;
    }

    public float func_70047_e() {
        return 0.25F;
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public void doBreathAttack(Entity target) {
        if (!target.func_70045_F() && target.func_70097_a(DamageSource.field_76372_a, 2.0F)) {
            target.func_70015_d(10);
        }

    }
}
