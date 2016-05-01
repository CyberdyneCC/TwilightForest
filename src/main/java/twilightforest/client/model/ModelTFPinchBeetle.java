package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFPinchBeetle;

public class ModelTFPinchBeetle extends ModelBase {

    ModelRenderer thorax;
    ModelRenderer head;
    ModelRenderer connector2;
    ModelRenderer RearEnd;
    ModelRenderer Leg6;
    ModelRenderer Leg4;
    ModelRenderer Leg2;
    ModelRenderer Leg5;
    ModelRenderer Leg3;
    ModelRenderer Leg1;
    ModelRenderer connector1;
    ModelRenderer jaw1a;
    ModelRenderer jaw1b;
    ModelRenderer jaw2a;
    ModelRenderer jaw2b;
    ModelRenderer antenna1;
    ModelRenderer antenna2;
    ModelRenderer eye1;
    ModelRenderer eye2;
    ModelRenderer tooth1a;
    ModelRenderer tooth1b;
    ModelRenderer tooth1c;
    ModelRenderer tooth2a;
    ModelRenderer tooth2b;
    ModelRenderer tooth2c;

    public ModelTFPinchBeetle() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.thorax = new ModelRenderer(this, 0, 22);
        this.thorax.func_78789_a(-4.5F, -4.0F, 0.0F, 9, 8, 2);
        this.thorax.func_78793_a(0.0F, 18.0F, -4.5F);
        this.connector1 = new ModelRenderer(this, 0, 12);
        this.connector1.func_78789_a(-3.0F, -3.0F, 0.0F, 6, 6, 1);
        this.connector1.func_78793_a(0.0F, 18.0F, -3.0F);
        this.connector2 = new ModelRenderer(this, 0, 12);
        this.connector2.func_78789_a(-3.0F, -3.0F, -1.0F, 6, 6, 1);
        this.connector2.func_78793_a(0.0F, 18.0F, -4.0F);
        this.RearEnd = new ModelRenderer(this, 28, 14);
        this.RearEnd.func_78789_a(-5.0F, -9.0F, -4.0F, 10, 10, 8);
        this.RearEnd.func_78793_a(0.0F, 18.0F, 7.0F);
        this.setRotation(this.RearEnd, 1.570796F, 0.0F, 0.0F);
        this.Leg6 = new ModelRenderer(this, 40, 0);
        this.Leg6.func_78789_a(-1.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg6.func_78793_a(4.0F, 21.0F, -4.0F);
        this.setRotation(this.Leg6, 0.0F, 0.2792527F, 0.3490659F);
        this.Leg5 = new ModelRenderer(this, 40, 0);
        this.Leg5.field_78809_i = true;
        this.Leg5.func_78789_a(-9.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg5.func_78793_a(-4.0F, 21.0F, -4.0F);
        this.setRotation(this.Leg5, 0.0F, -0.2792527F, -0.3490659F);
        this.Leg4 = new ModelRenderer(this, 40, 0);
        this.Leg4.func_78789_a(-1.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg4.func_78793_a(4.0F, 21.0F, -1.0F);
        this.setRotation(this.Leg4, 0.0F, -0.2792527F, 0.3490659F);
        this.Leg2 = new ModelRenderer(this, 40, 0);
        this.Leg2.func_78789_a(-1.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg2.func_78793_a(4.0F, 21.0F, 4.0F);
        this.setRotation(this.Leg2, 0.0F, -0.6981317F, 0.3490659F);
        this.Leg3 = new ModelRenderer(this, 40, 0);
        this.Leg3.field_78809_i = true;
        this.Leg3.func_78789_a(-9.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg3.func_78793_a(-4.0F, 21.0F, -1.0F);
        this.setRotation(this.Leg3, 0.0F, 0.2792527F, -0.3490659F);
        this.Leg1 = new ModelRenderer(this, 40, 0);
        this.Leg1.field_78809_i = true;
        this.Leg1.func_78789_a(-9.0F, -1.0F, -1.0F, 10, 2, 2);
        this.Leg1.func_78793_a(-4.0F, 21.0F, 4.0F);
        this.Leg1.func_78787_b(64, 32);
        this.setRotation(this.Leg1, 0.0F, 0.6981317F, -0.3490659F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78789_a(-4.0F, -4.0F, -6.0F, 8, 6, 6);
        this.head.func_78793_a(0.0F, 19.0F, -5.0F);
        this.jaw1a = new ModelRenderer(this, 40, 6);
        this.jaw1a.func_78789_a(-1.0F, -1.0F, -1.5F, 8, 2, 3);
        this.jaw1a.func_78793_a(-3.0F, 1.0F, -6.0F);
        this.setRotation(this.jaw1a, 0.0F, 2.635447F, 0.0F);
        this.jaw1b = new ModelRenderer(this, 40, 10);
        this.jaw1b.func_78789_a(-1.0F, -1.0F, -1.0F, 10, 2, 2);
        this.jaw1b.func_78793_a(7.0F, 0.0F, 0.0F);
        this.setRotation(this.jaw1b, 0.0F, -1.047197F, 0.0F);
        this.jaw2a = new ModelRenderer(this, 40, 6);
        this.jaw2a.func_78789_a(-1.0F, -1.0F, -1.5F, 8, 2, 3);
        this.jaw2a.func_78793_a(3.0F, 1.0F, -6.0F);
        this.setRotation(this.jaw2a, 0.0F, 0.541052F, 0.0F);
        this.jaw2b = new ModelRenderer(this, 40, 10);
        this.jaw2b.func_78789_a(-1.0F, -1.0F, -1.0F, 10, 2, 2);
        this.jaw2b.func_78793_a(7.0F, 0.0F, 0.0F);
        this.setRotation(this.jaw2b, 0.0F, 1.047197F, 0.0F);
        this.antenna1 = new ModelRenderer(this, 42, 4);
        this.antenna1.func_78789_a(0.0F, -0.5F, -0.5F, 10, 1, 1);
        this.antenna1.func_78793_a(1.0F, -3.0F, -5.0F);
        this.setRotation(this.antenna1, 0.0F, 1.047198F, -0.296706F);
        this.antenna2 = new ModelRenderer(this, 42, 4);
        this.antenna2.func_78789_a(0.0F, -0.5F, -0.5F, 10, 1, 1);
        this.antenna2.func_78793_a(-1.0F, -3.0F, -5.0F);
        this.setRotation(this.antenna2, 0.0F, 2.094395F, 0.296706F);
        this.eye1 = new ModelRenderer(this, 15, 12);
        this.eye1.func_78789_a(-1.5F, -1.5F, -1.5F, 3, 3, 3);
        this.eye1.func_78793_a(-3.0F, -2.0F, -5.0F);
        this.eye2 = new ModelRenderer(this, 15, 12);
        this.eye2.func_78789_a(-1.5F, -1.5F, -1.5F, 3, 3, 3);
        this.eye2.func_78793_a(3.0F, -2.0F, -5.0F);
        this.tooth1a = new ModelRenderer(this, 0, 0);
        this.tooth1a.func_78789_a(0.0F, -0.5F, -0.0F, 2, 1, 1);
        this.tooth1a.func_78793_a(9.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth1a, 0.0F, -0.5235987F, 0.0F);
        this.tooth1b = new ModelRenderer(this, 0, 0);
        this.tooth1b.func_78789_a(-2.5F, -0.5F, -0.0F, 2, 1, 1);
        this.tooth1b.func_78793_a(6.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth1b, 0.0F, 1.5707963F, 0.0F);
        this.tooth1c = new ModelRenderer(this, 0, 0);
        this.tooth1c.func_78789_a(-2.5F, -0.5F, -0.0F, 2, 1, 1);
        this.tooth1c.func_78793_a(3.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth1c, 0.0F, 1.5707963F, 0.0F);
        this.tooth2a = new ModelRenderer(this, 0, 0);
        this.tooth2a.func_78789_a(0.0F, -0.5F, -1.0F, 2, 1, 1);
        this.tooth2a.func_78793_a(9.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth2a, 0.0F, 0.5235987F, 0.0F);
        this.tooth2b = new ModelRenderer(this, 0, 0);
        this.tooth2b.func_78789_a(-2.5F, -0.5F, -1.0F, 2, 1, 1);
        this.tooth2b.func_78793_a(6.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth2b, 0.0F, -1.5707963F, 0.0F);
        this.tooth2c = new ModelRenderer(this, 0, 0);
        this.tooth2c.func_78789_a(-2.5F, -0.5F, -1.0F, 2, 1, 1);
        this.tooth2c.func_78793_a(3.0F, 0.0F, 0.0F);
        this.setRotation(this.tooth2c, 0.0F, -1.5707963F, 0.0F);
        this.head.func_78792_a(this.jaw1a);
        this.jaw1a.func_78792_a(this.jaw1b);
        this.jaw1b.func_78792_a(this.tooth1a);
        this.jaw1b.func_78792_a(this.tooth1b);
        this.jaw1b.func_78792_a(this.tooth1c);
        this.jaw2b.func_78792_a(this.tooth2a);
        this.jaw2b.func_78792_a(this.tooth2b);
        this.jaw2b.func_78792_a(this.tooth2c);
        this.head.func_78792_a(this.jaw2a);
        this.jaw2a.func_78792_a(this.jaw2b);
        this.head.func_78792_a(this.antenna1);
        this.head.func_78792_a(this.antenna2);
        this.head.func_78792_a(this.eye1);
        this.head.func_78792_a(this.eye2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.thorax.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.connector2.func_78785_a(f5);
        this.RearEnd.func_78785_a(f5);
        this.Leg6.func_78785_a(f5);
        this.Leg4.func_78785_a(f5);
        this.Leg2.func_78785_a(f5);
        this.Leg5.func_78785_a(f5);
        this.Leg3.func_78785_a(f5);
        this.Leg1.func_78785_a(f5);
        this.connector1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78796_g = par4 / 57.295776F;
        this.head.field_78795_f = par5 / 57.295776F;
        float legZ = 0.28559935F;

        this.Leg1.field_78808_h = -legZ;
        this.Leg2.field_78808_h = legZ;
        this.Leg3.field_78808_h = -legZ * 0.74F;
        this.Leg4.field_78808_h = legZ * 0.74F;
        this.Leg5.field_78808_h = -legZ;
        this.Leg6.field_78808_h = legZ;
        float f = -0.0F;
        float f1 = 0.3926991F;

        this.Leg1.field_78796_g = f1 * 2.0F + f;
        this.Leg2.field_78796_g = -f1 * 2.0F - f;
        this.Leg3.field_78796_g = f1 * 1.0F + f;
        this.Leg4.field_78796_g = -f1 * 1.0F - f;
        this.Leg5.field_78796_g = -f1 * 2.0F + f;
        this.Leg6.field_78796_g = f1 * 2.0F - f;
        float f2 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
        float f3 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * par2;
        float f4 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
        float f5 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 0.0F) * 0.4F) * par2;
        float f6 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 3.1415927F) * 0.4F) * par2;
        float f7 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;

        this.Leg1.field_78796_g += f2;
        this.Leg2.field_78796_g += -f2;
        this.Leg3.field_78796_g += f3;
        this.Leg4.field_78796_g += -f3;
        this.Leg5.field_78796_g += f4;
        this.Leg6.field_78796_g += -f4;
        this.Leg1.field_78808_h += f5;
        this.Leg2.field_78808_h += -f5;
        this.Leg3.field_78808_h += f6;
        this.Leg4.field_78808_h += -f6;
        this.Leg5.field_78808_h += f7;
        this.Leg6.field_78808_h += -f7;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float partialTick) {
        EntityTFPinchBeetle beetle = (EntityTFPinchBeetle) par1EntityLiving;

        if (beetle.field_70153_n == null) {
            this.jaw1a.field_78796_g = 2.9670596F;
            this.jaw2a.field_78796_g = 0.34906584F;
        } else {
            this.jaw1a.field_78796_g = 2.3561945F;
            this.jaw2a.field_78796_g = 0.7853982F;
        }

    }
}
