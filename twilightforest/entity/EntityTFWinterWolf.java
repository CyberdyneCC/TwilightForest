package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.entity.ai.EntityAITFBreathAttack;
import twilightforest.item.TFItems;

public class EntityTFWinterWolf extends EntityTFHostileWolf implements IBreathAttacker {

    private static final int BREATH_FLAG = 21;
    public static final int BREATH_DURATION = 10;
    public static final int BREATH_DAMAGE = 2;

    public EntityTFWinterWolf(World world) {
        super(world);
        this.func_70105_a(1.4F, 1.9F);
        this.field_70714_bg.field_75782_a.clear();
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAITFBreathAttack(this, 1.0F, 5.0F, 30, 0.1F));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70715_bh.field_75782_a.clear();
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, Byte.valueOf((byte) 0));
    }

    public int getAttackStrength(Entity par1Entity) {
        return 6;
    }

    public boolean func_70652_k(Entity par1Entity) {
        int damage = this.getAttackStrength(par1Entity);

        return par1Entity.func_70097_a(DamageSource.func_76358_a(this), (float) damage);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.isBreathing()) {
            Vec3 look = this.func_70040_Z();
            double dist = 0.5D;
            double px = this.field_70165_t + look.field_72450_a * dist;
            double py = this.field_70163_u + 1.25D + look.field_72448_b * dist;
            double pz = this.field_70161_v + look.field_72449_c * dist;

            for (int i = 0; i < 10; ++i) {
                double dx = look.field_72450_a;
                double dy = look.field_72448_b;
                double dz = look.field_72449_c;
                double spread = 5.0D + this.func_70681_au().nextDouble() * 2.5D;
                double velocity = 3.0D + this.func_70681_au().nextDouble() * 0.15D;

                dx += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dy += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dz += this.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dx *= velocity;
                dy *= velocity;
                dz *= velocity;
                TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowstuff", px, py, pz, dx, dy, dz);
            }

            this.playBreathSound();
        }

    }

    public void playBreathSound() {
        this.field_70170_p.func_72908_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "mob.ghast.fireball", this.field_70146_Z.nextFloat() * 0.5F, this.field_70146_Z.nextFloat() * 0.5F);
    }

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.6F;
    }

    public boolean isBreathing() {
        return this.func_70096_w().func_75683_a(21) == 1;
    }

    public void setBreathing(boolean flag) {
        this.func_70096_w().func_75692_b(21, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public void doBreathAttack(Entity target) {}

    protected boolean isValidLightLevel() {
        int x = MathHelper.func_76128_c(this.field_70165_t);
        int z = MathHelper.func_76128_c(this.field_70161_v);

        return this.field_70170_p.func_72807_a(x, z) == TFBiomeBase.tfSnow ? true : super.isValidLightLevel();
    }

    protected Item func_146068_u() {
        return TFItems.arcticFur;
    }
}
