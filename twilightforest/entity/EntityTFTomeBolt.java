package twilightforest.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTFTomeBolt extends EntityThrowable {

    public EntityTFTomeBolt(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFTomeBolt(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityTFTomeBolt(World par1World) {
        super(par1World);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    protected float func_70185_h() {
        return 0.003F;
    }

    public void makeTrail() {
        for (int i = 0; i < 5; ++i) {
            double dx = this.field_70165_t + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dy = this.field_70163_u + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dz = this.field_70161_v + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());

            this.field_70170_p.func_72869_a("crit", dx, dy, dz, 0.0D, 0.0D, 0.0D);
        }

    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.field_72308_g != null && par1MovingObjectPosition.field_72308_g instanceof EntityLivingBase && par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 6.0F)) {
            byte i = (byte) (this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL ? 3 : (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL ? 7 : 9));

            if (i > 0) {
                ((EntityLivingBase) par1MovingObjectPosition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, i * 20, 1));
            }
        }

        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b(Items.field_151059_bz), this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.05D, this.field_70146_Z.nextDouble() * 0.2D, this.field_70146_Z.nextGaussian() * 0.05D);
        }

        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }
}
