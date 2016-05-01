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
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.entity.ai.EntityAITFChargeAttack;
import twilightforest.entity.ai.EntityAITFKidnapRider;

public class EntityTFPinchBeetle extends EntityMob {

    public EntityTFPinchBeetle(World world) {
        super(world);
        this.func_70105_a(1.2F, 1.1F);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAITFKidnapRider(this, 2.0F));
        this.field_70714_bg.func_75776_a(2, new EntityAITFChargeAttack(this, 2.0F));
        this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 2;

        if (i > 20) {
            i = 20;
        }

        return i;
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

    public void func_70636_d() {
        if (this.field_70153_n != null) {
            this.func_70105_a(1.9F, 2.0F);
            if (this.field_70153_n.func_70093_af()) {
                this.field_70153_n.func_70095_a(false);
            }
        } else {
            this.func_70105_a(1.2F, 1.1F);
        }

        super.func_70636_d();
        if (this.field_70153_n != null) {
            this.func_70671_ap().func_75651_a(this.field_70153_n, 100.0F, 100.0F);
            Vec3 riderPos = this.getRiderPosition();

            this.func_145771_j(riderPos.field_72450_a, riderPos.field_72448_b, riderPos.field_72449_c);
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public int getAttackStrength(Entity par1Entity) {
        return 8;
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        return 1.1F;
    }

    public boolean func_70652_k(Entity par1Entity) {
        if (this.field_70153_n == null && par1Entity.field_70154_o == null) {
            par1Entity.func_70078_a(this);
        }

        return super.func_70652_k(par1Entity);
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return super.func_70085_c(par1EntityPlayer);
    }

    public float func_70047_e() {
        return 0.25F;
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public void func_70043_V() {
        if (this.field_70153_n != null) {
            Vec3 riderPos = this.getRiderPosition();

            this.field_70153_n.func_70107_b(riderPos.field_72450_a, riderPos.field_72448_b, riderPos.field_72449_c);
        }

    }

    public double func_70042_X() {
        return 0.75D;
    }

    public Vec3 getRiderPosition() {
        if (this.field_70153_n != null) {
            float distance = 0.9F;
            double d0 = Math.cos((double) (this.field_70177_z + 90.0F) * 3.141592653589793D / 180.0D) * (double) distance;
            double d1 = Math.sin((double) (this.field_70177_z + 90.0F) * 3.141592653589793D / 180.0D) * (double) distance;

            return Vec3.func_72443_a(this.field_70165_t + d0, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
        } else {
            return Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }

    public boolean canRiderInteract() {
        return true;
    }
}
