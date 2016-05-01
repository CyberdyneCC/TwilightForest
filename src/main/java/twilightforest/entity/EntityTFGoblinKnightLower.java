package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.entity.ai.EntityAITFRiderSpearAttack;
import twilightforest.item.TFItems;

public class EntityTFGoblinKnightLower extends EntityMob {

    private static final int DATA_EQUIP = 17;

    public EntityTFGoblinKnightLower(World par1World) {
        super(par1World);
        this.func_70105_a(0.7F, 1.1F);
        this.field_70714_bg.func_75776_a(0, new EntityAITFRiderSpearAttack(this));
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.setHasArmor(true);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    protected boolean func_70650_aV() {
        return true;
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

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("hasArmor", this.hasArmor());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setHasArmor(par1NBTTagCompound.func_74767_n("hasArmor"));
    }

    public void initCreature() {
        EntityTFGoblinKnightUpper upper = new EntityTFGoblinKnightUpper(this.field_70170_p);

        upper.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
        upper.func_110161_a((IEntityLivingData) null);
        this.field_70170_p.func_72838_d(upper);
        upper.func_70078_a(this);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        EntityTFGoblinKnightUpper upper = new EntityTFGoblinKnightUpper(this.field_70170_p);

        upper.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
        upper.func_110161_a((IEntityLivingData) null);
        this.field_70170_p.func_72838_d(upper);
        upper.func_70078_a(this);
        return (IEntityLivingData) par1EntityLivingData1;
    }

    public double func_70042_X() {
        return 1.0D;
    }

    public void func_70071_h_() {
        if (this.func_70089_S() && this.field_70153_n != null && this.field_70153_n instanceof EntityLiving && this.func_70638_az() == null) {
            this.func_70624_b(((EntityLiving) this.field_70153_n).func_70638_az());
        }

        super.func_70071_h_();
    }

    public boolean func_70652_k(Entity par1Entity) {
        return this.field_70153_n != null && this.field_70153_n instanceof EntityLiving ? ((EntityLiving) this.field_70153_n).func_70652_k(par1Entity) : super.func_70652_k(par1Entity);
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float damageAmount) {
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
            EntityTFGoblinKnightUpper upper = null;

            if (this.field_70153_n != null && this.field_70153_n instanceof EntityTFGoblinKnightUpper) {
                upper = (EntityTFGoblinKnightUpper) this.field_70153_n;
            }

            if (upper != null && upper.hasShield() && difference > 150.0F && difference < 230.0F && upper.takeHitOnShield(par1DamageSource, damageAmount)) {
                return false;
            }

            if (this.hasArmor() && (difference > 300.0F || difference < 60.0F)) {
                this.breakArmor();
            }
        }

        boolean attackSuccess1 = super.func_70097_a(par1DamageSource, damageAmount);

        return attackSuccess1;
    }

    public void breakArmor() {
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.func_70669_a(new ItemStack(Items.field_151030_Z));
        this.setHasArmor(false);
    }

    public int func_70658_aO() {
        int armor = super.func_70658_aO();

        if (this.hasArmor()) {
            armor += 17;
        }

        if (armor > 20) {
            armor = 20;
        }

        return armor;
    }

    protected Item getDropItemId() {
        return TFItems.armorShard;
    }
}
