package twilightforest.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.entity.ai.EntityAITFHeavySpearAttack;
import twilightforest.item.TFItems;

public class EntityTFGoblinKnightUpper extends EntityMob {

    private static final int SHIELD_DAMAGE_THRESHOLD = 10;
    private static final int DATA_EQUIP = 17;
    public int shieldHits;
    public int heavySpearTimer;

    public EntityTFGoblinKnightUpper(World par1World) {
        super(par1World);
        this.func_70105_a(1.1F, 1.3F);
        this.field_70714_bg.func_75776_a(0, new EntityAITFHeavySpearAttack(this));
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.setHasArmor(true);
        this.setHasShield(true);
        this.shieldHits = 0;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    public boolean hasArmor() {
        return (this.field_70180_af.func_75683_a(17) & 1) > 0;
    }

    public void setHasArmor(boolean flag) {
        byte otherFlags = this.field_70180_af.func_75683_a(17);

        otherFlags = (byte) (otherFlags & 126);
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) (otherFlags | 1)));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf(otherFlags));
        }

    }

    public boolean hasShield() {
        return (this.field_70180_af.func_75683_a(17) & 2) > 0;
    }

    public void setHasShield(boolean flag) {
        byte otherFlags = this.field_70180_af.func_75683_a(17);

        otherFlags = (byte) (otherFlags & 125);
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) (otherFlags | 2)));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf(otherFlags));
        }

    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("hasArmor", this.hasArmor());
        par1NBTTagCompound.func_74757_a("hasShield", this.hasShield());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setHasArmor(par1NBTTagCompound.func_74767_n("hasArmor"));
        this.setHasShield(par1NBTTagCompound.func_74767_n("hasShield"));
    }

    public void func_70071_h_() {
        if (this.func_70089_S()) {
            if (this.field_70154_o != null && this.field_70154_o instanceof EntityLiving && this.func_70638_az() == null) {
                this.func_70624_b(((EntityLiving) this.field_70154_o).func_70638_az());
            }

            if (this.heavySpearTimer > 0) {
                --this.heavySpearTimer;
                if (this.heavySpearTimer == 25) {
                    this.landHeavySpearAttack();
                }
            }

            if (this.field_70154_o == null && this.hasShield()) {
                this.breakShield();
            }
        }

        super.func_70071_h_();
    }

    private void landHeavySpearAttack() {
        Vec3 vector = this.func_70040_Z();
        double dist = 1.25D;
        double px = this.field_70165_t + vector.field_72450_a * dist;
        double py = this.field_70121_D.field_72338_b - 0.75D;
        double pz = this.field_70161_v + vector.field_72449_c * dist;

        for (int radius = 0; radius < 50; ++radius) {
            this.field_70170_p.func_72869_a("largesmoke", px, py, pz, (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.25F), 0.0D, (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.25F));
        }

        double d0 = 1.5D;
        AxisAlignedBB spearBB = AxisAlignedBB.func_72330_a(px - d0, py - d0, pz - d0, px + d0, py + d0, pz + d0);
        List inBox = this.field_70170_p.func_72872_a(Entity.class, spearBB);
        Iterator iterator = inBox.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (this.field_70154_o != null && entity != this.field_70154_o && entity != this) {
                super.func_70652_k(entity);
            }
        }

    }

    public int getAttackStrength(Entity par1Entity) {
        return this.heavySpearTimer > 0 ? 20 : 8;
    }

    public void func_70098_U() {
        super.func_70098_U();
        if (this.field_70154_o != null) {
            this.field_70761_aq = ((EntityLiving) this.field_70154_o).field_70761_aq;
        }

    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.heavySpearTimer = 60;
        } else {
            super.func_70103_a(par1);
        }

    }

    public boolean func_70652_k(Entity par1Entity) {
        if (this.heavySpearTimer > 0) {
            return false;
        } else if (this.field_70146_Z.nextInt(2) == 0) {
            this.startHeavySpearAttack();
            return false;
        } else {
            this.func_71038_i();
            return super.func_70652_k(par1Entity);
        }
    }

    private void startHeavySpearAttack() {
        this.heavySpearTimer = 60;
        this.field_70170_p.func_72960_a(this, (byte) 4);
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float damageAmount) {
        if (par1DamageSource == DamageSource.field_76368_d && this.field_70154_o != null) {
            return false;
        } else {
            Entity attacker = null;

            if (par1DamageSource.func_76364_f() != null) {
                attacker = par1DamageSource.func_76364_f();
            }

            if (par1DamageSource.func_76346_g() != null) {
                attacker = par1DamageSource.func_76346_g();
            }

            if (attacker != null) {
                double attackSuccess = this.field_70165_t - attacker.field_70165_t;
                double dz = this.field_70161_v - attacker.field_70161_v;
                float angle = (float) (Math.atan2(dz, attackSuccess) * 180.0D / 3.141592653589793D) - 90.0F;
                float difference = MathHelper.func_76135_e((this.field_70761_aq - angle) % 360.0F);

                if (this.hasShield() && difference > 150.0F && difference < 230.0F) {
                    if (this.takeHitOnShield(par1DamageSource, damageAmount)) {
                        return false;
                    }
                } else if (this.hasShield() && this.field_70146_Z.nextBoolean()) {
                    this.damageShield();
                }

                if (this.hasArmor() && (difference > 300.0F || difference < 60.0F)) {
                    this.breakArmor();
                }
            }

            boolean attackSuccess1 = super.func_70097_a(par1DamageSource, damageAmount);

            if (attackSuccess1 && this.field_70154_o != null && this.field_70154_o instanceof EntityLiving && attacker != null) {
                ((EntityLiving) this.field_70154_o).func_70653_a(attacker, damageAmount, 0.1D, 0.1D);
            }

            return attackSuccess1;
        }
    }

    public void breakArmor() {
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.setHasArmor(false);
    }

    public void breakShield() {
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.setHasShield(false);
    }

    public boolean takeHitOnShield(DamageSource par1DamageSource, float damageAmount) {
        if (damageAmount > 10.0F && !this.field_70170_p.field_72995_K) {
            this.damageShield();
        } else {
            this.field_70170_p.func_72956_a(this, "random.break", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }

        Object toKnockback = this.field_70154_o != null && this.field_70154_o instanceof EntityLiving ? (EntityLiving) this.field_70154_o : this;

        if (par1DamageSource.func_76346_g() != null) {
            double d0 = par1DamageSource.func_76346_g().field_70165_t - this.field_70165_t;

            double d1;

            for (d1 = par1DamageSource.func_76346_g().field_70161_v - this.field_70161_v; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                d0 = (Math.random() - Math.random()) * 0.01D;
            }

            ((EntityLiving) toKnockback).func_70653_a(par1DamageSource.func_76346_g(), 0.0F, d0 / 4.0D, d1 / 4.0D);
            if (par1DamageSource.func_76346_g() instanceof EntityLiving) {
                this.func_70604_c((EntityLiving) par1DamageSource.func_76346_g());
            }
        }

        return true;
    }

    private void damageShield() {
        this.field_70170_p.func_72956_a(this, "mob.zombie.metal", 0.25F, 0.25F);
        ++this.shieldHits;
        if (!this.field_70170_p.field_72995_K && this.shieldHits >= 3) {
            this.breakShield();
        }

    }

    public int func_70658_aO() {
        int armor = super.func_70658_aO();

        if (this.hasArmor()) {
            armor += 20;
        }

        if (armor > 20) {
            armor = 20;
        }

        return armor;
    }

    protected Item func_146068_u() {
        return TFItems.armorShard;
    }
}
