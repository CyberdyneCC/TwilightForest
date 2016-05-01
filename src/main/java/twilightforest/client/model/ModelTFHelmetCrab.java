package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTFHelmetCrab extends ModelBase {

    ModelRenderer body;
    ModelRenderer helmetBase;
    ModelRenderer Leg8;
    ModelRenderer Leg6;
    ModelRenderer Leg4;
    ModelRenderer rightArm;
    ModelRenderer Leg5;
    ModelRenderer Leg3;
    ModelRenderer clawbase;
    ModelRenderer clawtop;
    ModelRenderer clawbottom;
    ModelRenderer righteye;
    ModelRenderer lefteye;
    public ModelRenderer helmet;
    public ModelRenderer righthorn1;
    public ModelRenderer righthorn2;
    public ModelRenderer lefthorn1;
    public ModelRenderer lefthorn2;

    public ModelTFHelmetCrab() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.body = new ModelRenderer(this, 32, 4);
        this.body.func_78789_a(-2.5F, -2.5F, -5.0F, 5, 5, 5);
        this.body.func_78793_a(0.0F, 19.0F, 0.0F);
        this.helmetBase = new ModelRenderer(this, 0, 0);
        this.helmetBase.func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.helmetBase.func_78793_a(0.0F, 18.0F, 0.0F);
        this.helmetBase.field_78795_f = -1.7453294F;
        this.helmetBase.field_78796_g = -0.5235988F;
        this.helmet = new ModelRenderer(this, 0, 14);
        this.helmet.func_78789_a(-3.5F, -11.0F, -3.5F, 7, 11, 7);
        this.helmet.field_78796_g = 0.7853982F;
        this.righthorn1 = new ModelRenderer(this, 28, 14);
        this.righthorn1.func_78789_a(-6.0F, -1.5F, -1.5F, 7, 3, 3);
        this.righthorn1.func_78793_a(-3.5F, -9.0F, 0.0F);
        this.righthorn1.field_78796_g = -0.2617994F;
        this.righthorn1.field_78808_h = 0.17453294F;
        this.righthorn2 = new ModelRenderer(this, 28, 20);
        this.righthorn2.func_78789_a(-3.0F, -1.0F, -1.0F, 3, 2, 2);
        this.righthorn2.func_78793_a(-5.5F, 0.0F, 0.0F);
        this.righthorn2.field_78796_g = -0.2617994F;
        this.righthorn2.field_78808_h = 0.17453294F;
        this.righthorn1.func_78792_a(this.righthorn2);
        this.lefthorn1 = new ModelRenderer(this, 28, 14);
        this.lefthorn1.field_78809_i = true;
        this.lefthorn1.func_78789_a(-1.0F, -1.5F, -1.5F, 7, 3, 3);
        this.lefthorn1.func_78793_a(3.5F, -9.0F, 0.0F);
        this.lefthorn1.field_78796_g = 0.2617994F;
        this.lefthorn1.field_78808_h = -0.17453294F;
        this.lefthorn2 = new ModelRenderer(this, 28, 20);
        this.lefthorn2.func_78789_a(0.0F, -1.0F, -1.0F, 3, 2, 2);
        this.lefthorn2.func_78793_a(5.5F, 0.0F, 0.0F);
        this.lefthorn2.field_78796_g = 0.2617994F;
        this.lefthorn2.field_78808_h = -0.17453294F;
        this.lefthorn1.func_78792_a(this.lefthorn2);
        this.helmetBase.func_78792_a(this.helmet);
        this.helmetBase.func_78792_a(this.righthorn1);
        this.helmetBase.func_78792_a(this.lefthorn1);
        this.Leg8 = new ModelRenderer(this, 18, 0);
        this.Leg8.func_78789_a(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.Leg8.func_78793_a(3.0F, 20.0F, -3.0F);
        this.setRotation(this.Leg8, 0.0F, 0.5759587F, 0.1919862F);
        this.Leg6 = new ModelRenderer(this, 18, 0);
        this.Leg6.func_78789_a(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.Leg6.func_78793_a(3.0F, 20.0F, -2.0F);
        this.setRotation(this.Leg6, 0.0F, 0.2792527F, 0.1919862F);
        this.Leg4 = new ModelRenderer(this, 18, 0);
        this.Leg4.func_78789_a(-1.0F, -1.0F, -1.0F, 8, 2, 2);
        this.Leg4.func_78793_a(3.0F, 20.0F, -1.0F);
        this.setRotation(this.Leg4, 0.0F, -0.2792527F, 0.1919862F);
        this.rightArm = new ModelRenderer(this, 38, 0);
        this.rightArm.func_78789_a(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.rightArm.func_78793_a(-3.0F, 20.0F, -3.0F);
        this.setRotation(this.rightArm, 0.0F, -1.319531F, -0.1919862F);
        this.Leg5 = new ModelRenderer(this, 18, 0);
        this.Leg5.func_78789_a(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.Leg5.func_78793_a(-3.0F, 20.0F, -2.0F);
        this.setRotation(this.Leg5, 0.0F, -0.2792527F, -0.1919862F);
        this.Leg3 = new ModelRenderer(this, 18, 0);
        this.Leg3.func_78789_a(-7.0F, -1.0F, -1.0F, 8, 2, 2);
        this.Leg3.func_78793_a(-3.0F, 20.0F, -1.0F);
        this.setRotation(this.Leg3, 0.0F, 0.2792527F, -0.1919862F);
        this.clawbase = new ModelRenderer(this, 0, 0);
        this.clawbase.func_78789_a(0.0F, -1.5F, -1.0F, 3, 3, 2);
        this.clawbase.func_78793_a(-6.0F, 0.0F, -0.5F);
        this.setRotation(this.clawbase, 0.0F, 1.5707964F, 0.0F);
        this.clawtop = new ModelRenderer(this, 0, 5);
        this.clawtop.func_78789_a(0.0F, -0.5F, -1.0F, 3, 1, 2);
        this.clawtop.func_78793_a(3.0F, -1.0F, 0.0F);
        this.setRotation(this.clawtop, 0.0F, 0.0F, -0.1858931F);
        this.clawbottom = new ModelRenderer(this, 0, 8);
        this.clawbottom.func_78789_a(0.0F, -0.5F, -1.0F, 3, 2, 2);
        this.clawbottom.func_78793_a(3.0F, 0.0F, 0.0F);
        this.setRotation(this.clawbottom, 0.0F, 0.0F, 0.2602503F);
        this.clawbase.func_78792_a(this.clawtop);
        this.clawbase.func_78792_a(this.clawbottom);
        this.rightArm.func_78792_a(this.clawbase);
        this.righteye = new ModelRenderer(this, 10, 0);
        this.righteye.func_78789_a(-1.0F, -3.0F, -1.0F, 2, 3, 2);
        this.righteye.func_78793_a(-1.0F, -1.0F, -4.0F);
        this.setRotation(this.righteye, 0.7853982F, 0.0F, -0.7853982F);
        this.lefteye = new ModelRenderer(this, 10, 0);
        this.lefteye.func_78789_a(-1.0F, -3.0F, -1.0F, 2, 3, 2);
        this.lefteye.func_78793_a(1.0F, -1.0F, -4.0F);
        this.setRotation(this.lefteye, 0.7853982F, 0.0F, 0.7853982F);
        this.body.func_78792_a(this.righteye);
        this.body.func_78792_a(this.lefteye);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        this.body.func_78785_a(par7);
        this.helmetBase.func_78785_a(par7);
        this.Leg8.func_78785_a(par7);
        this.Leg6.func_78785_a(par7);
        this.Leg4.func_78785_a(par7);
        this.rightArm.func_78785_a(par7);
        this.Leg5.func_78785_a(par7);
        this.Leg3.func_78785_a(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        this.body.field_78796_g = par4 / 57.295776F;
        this.body.field_78795_f = par5 / 57.295776F;
        float f6 = 0.7853982F;

        this.Leg3.field_78808_h = -f6 * 0.74F;
        this.Leg4.field_78808_h = f6 * 0.74F;
        this.Leg5.field_78808_h = -f6 * 0.74F;
        this.Leg6.field_78808_h = f6 * 0.74F;
        this.Leg8.field_78808_h = f6;
        float f7 = -0.0F;
        float f8 = 0.3926991F;

        this.Leg3.field_78796_g = f8 * 1.0F + f7;
        this.Leg4.field_78796_g = -f8 * 1.0F - f7;
        this.Leg5.field_78796_g = -f8 * 1.0F + f7;
        this.Leg6.field_78796_g = f8 * 1.0F - f7;
        this.Leg8.field_78796_g = f8 * 2.0F - f7;
        float f10 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * par2;
        float f11 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * par2;
        float f12 = -(MathHelper.func_76134_b(par1 * 0.6662F * 2.0F + 4.712389F) * 0.4F) * par2;
        float f14 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 3.1415927F) * 0.4F) * par2;
        float f15 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 1.5707964F) * 0.4F) * par2;
        float f16 = Math.abs(MathHelper.func_76126_a(par1 * 0.6662F + 4.712389F) * 0.4F) * par2;

        this.Leg3.field_78796_g += f10;
        this.Leg4.field_78796_g += -f10;
        this.Leg5.field_78796_g += f11;
        this.Leg6.field_78796_g += -f11;
        this.Leg8.field_78796_g += -f12;
        this.Leg3.field_78808_h += f14;
        this.Leg4.field_78808_h += -f14;
        this.Leg5.field_78808_h += f15;
        this.Leg6.field_78808_h += -f15;
        this.Leg8.field_78808_h += -f16;
        this.rightArm.field_78796_g = -1.319531F;
        this.rightArm.field_78796_g += MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
    }
}
