package twilightforest.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.util.MathHelper;

public class EntityTFRavenLookHelper extends EntityLookHelper {

    private EntityLiving entity;
    private float field_46149_b;
    private float field_46150_c;
    private boolean field_46147_d = false;
    private double posX;
    private double posY;
    private double posZ;

    public EntityTFRavenLookHelper(EntityLiving par1EntityLiving) {
        super(par1EntityLiving);
        this.entity = par1EntityLiving;
    }

    public void func_75651_a(Entity par1Entity, float par2, float par3) {
        this.posX = par1Entity.field_70165_t;
        if (par1Entity instanceof EntityLiving) {
            this.posY = par1Entity.field_70163_u + (double) ((EntityLiving) par1Entity).func_70047_e();
        } else {
            this.posY = (par1Entity.field_70121_D.field_72338_b + par1Entity.field_70121_D.field_72337_e) / 2.0D;
        }

        this.posZ = par1Entity.field_70161_v;
        this.field_46149_b = par2;
        this.field_46150_c = par3;
        this.field_46147_d = true;
    }

    public void func_75650_a(double par1, double par3, double par5, float par7, float par8) {
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        this.field_46149_b = par7;
        this.field_46150_c = par8;
        this.field_46147_d = true;
    }

    public void func_75649_a() {
        this.entity.field_70125_A = 0.0F;
        if (this.field_46147_d) {
            this.field_46147_d = false;
            double d0 = this.posX - this.entity.field_70165_t;
            double d1 = this.posY - (this.entity.field_70163_u + (double) this.entity.func_70047_e());
            double d2 = this.posZ - this.entity.field_70161_v;
            double d3 = (double) MathHelper.func_76133_a(d0 * d0 + d2 * d2);
            float f = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 30.0F;
            float f1 = (float) (-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));

            this.entity.field_70125_A = this.updateRotation(this.entity.field_70125_A, f1, this.field_46150_c);
            this.entity.field_70759_as = this.updateRotation(this.entity.field_70759_as, f, this.field_46149_b);
        } else {
            this.entity.field_70759_as = this.updateRotation(this.entity.field_70759_as, this.entity.field_70761_aq, 10.0F);
        }

        float f2;

        for (f2 = this.entity.field_70759_as - this.entity.field_70761_aq; f2 < -180.0F; f2 += 360.0F) {
            ;
        }

        while (f2 >= 180.0F) {
            f2 -= 360.0F;
        }

        if (!this.entity.func_70661_as().func_75500_f()) {
            if (f2 < -75.0F) {
                this.entity.field_70759_as = this.entity.field_70761_aq - 75.0F;
            }

            if (f2 > 75.0F) {
                this.entity.field_70759_as = this.entity.field_70761_aq + 75.0F;
            }
        }

    }

    private float updateRotation(float par1, float par2, float par3) {
        float f;

        for (f = par2 - par1; f < -180.0F; f += 360.0F) {
            ;
        }

        while (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f > par3) {
            f = par3;
        }

        if (f < -par3) {
            f = -par3;
        }

        return par1 + f;
    }
}
