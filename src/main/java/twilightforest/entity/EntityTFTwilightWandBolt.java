package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTFTwilightWandBolt extends EntityThrowable {

    public EntityTFTwilightWandBolt(World par1World) {
        super(par1World);
    }

    public EntityTFTwilightWandBolt(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFTwilightWandBolt(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    public void makeTrail() {
        for (int i = 0; i < 5; ++i) {
            double dx = this.field_70165_t + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dy = this.field_70163_u + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dz = this.field_70161_v + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double s1 = (double) ((this.field_70146_Z.nextFloat() * 0.5F + 0.5F) * 0.17F);
            double s2 = (double) ((this.field_70146_Z.nextFloat() * 0.5F + 0.5F) * 0.8F);
            double s3 = (double) ((this.field_70146_Z.nextFloat() * 0.5F + 0.5F) * 0.69F);

            this.field_70170_p.func_72869_a("mobSpell", dx, dy, dz, s1, s2, s3);
        }

    }

    protected float func_70185_h() {
        return 0.003F;
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.field_72308_g != null && par1MovingObjectPosition.field_72308_g instanceof EntityLivingBase && par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, this.func_85052_h()), 6.0F)) {
            ;
        }

        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b(Items.field_151079_bi), this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.05D, this.field_70146_Z.nextDouble() * 0.2D, this.field_70146_Z.nextGaussian() * 0.05D);
        }

        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }
}
