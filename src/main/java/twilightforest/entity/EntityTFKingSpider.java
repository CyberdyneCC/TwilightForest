package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityTFKingSpider extends EntitySpider {

    public EntityTFKingSpider(World world) {
        super(world);
        this.func_70105_a(1.6F, 1.6F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.3F));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 0.20000000298023224D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
    }

    protected Entity func_70782_k() {
        double d0 = 16.0D;

        return this.field_70170_p.func_72856_b(this, d0);
    }

    public float spiderScaleAmount() {
        return 1.9F;
    }

    public float func_70603_bj() {
        return 2.0F;
    }

    public boolean func_70617_f_() {
        return false;
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        EntityTFSkeletonDruid druid = new EntityTFSkeletonDruid(this.field_70170_p);

        druid.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
        druid.func_110161_a((IEntityLivingData) null);
        this.field_70170_p.func_72838_d(druid);
        druid.func_70078_a(this);
        return (IEntityLivingData) par1EntityLivingData1;
    }

    public double func_70042_X() {
        return (double) this.field_70131_O * 0.5D;
    }
}
