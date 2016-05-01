package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.entity.ai.EntityAITFThrowRider;
import twilightforest.item.TFItems;

public class EntityTFYeti extends EntityMob {

    private static final int ANGER_FLAG = 16;

    public EntityTFYeti(World par1World) {
        super(par1World);
        this.func_70105_a(1.4F, 2.4F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, new EntityAITFThrowRider(this, 1.0F));
        this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(4, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.38D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(4.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte) 0));
    }

    public void func_70636_d() {
        if (this.field_70153_n != null) {
            this.func_70105_a(1.4F, 2.4F);
            if (this.field_70153_n.func_70093_af()) {
                this.field_70153_n.func_70095_a(false);
            }
        } else {
            this.func_70105_a(1.4F, 2.4F);
        }

        super.func_70636_d();
        if (this.field_70153_n != null) {
            this.func_70671_ap().func_75651_a(this.field_70153_n, 100.0F, 100.0F);
            Vec3 riderPos = this.getRiderPosition();

            this.func_145771_j(riderPos.field_72450_a, riderPos.field_72448_b, riderPos.field_72449_c);
        }

    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return super.func_70085_c(par1EntityPlayer);
    }

    public boolean func_70652_k(Entity par1Entity) {
        if (this.field_70153_n == null && par1Entity.field_70154_o == null) {
            par1Entity.func_70078_a(this);
        }

        return super.func_70652_k(par1Entity);
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.func_76364_f() != null) {
            this.setAngry(true);
        }

        return super.func_70097_a(par1DamageSource, par2);
    }

    public boolean isAngry() {
        return (this.field_70180_af.func_75683_a(16) & 2) != 0;
    }

    public void setAngry(boolean anger) {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (anger) {
            this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 & -3)));
        }

    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("Angry", this.isAngry());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setAngry(par1NBTTagCompound.func_74767_n("Angry"));
    }

    public void func_70043_V() {
        if (this.field_70153_n != null) {
            Vec3 riderPos = this.getRiderPosition();

            this.field_70153_n.func_70107_b(riderPos.field_72450_a, riderPos.field_72448_b, riderPos.field_72449_c);
        }

    }

    public double func_70042_X() {
        return 2.25D;
    }

    public Vec3 getRiderPosition() {
        if (this.field_70153_n != null) {
            float distance = 0.4F;
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

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_72807_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v)) != TFBiomeBase.tfSnow ? super.func_70601_bi() : this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).size() == 0;
    }

    protected boolean func_70814_o() {
        int x = MathHelper.func_76128_c(this.field_70165_t);
        int z = MathHelper.func_76128_c(this.field_70161_v);

        return this.field_70170_p.func_72807_a(x, z) == TFBiomeBase.tfSnow ? true : super.func_70814_o();
    }

    protected Item func_146068_u() {
        return TFItems.arcticFur;
    }
}
