package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTFMinoshroom extends ModelBiped {

    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer udders;
    ModelRenderer snout;
    public ModelRenderer righthorn1;
    public ModelRenderer righthorn2;
    public ModelRenderer lefthorn1;
    public ModelRenderer lefthorn2;

    public ModelTFMinoshroom() {
        this.field_78090_t = 128;
        this.field_78089_u = 32;
        this.field_78116_c = new ModelRenderer(this, 96, 16);
        this.field_78116_c.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.field_78116_c.func_78793_a(0.0F, -6.0F, -9.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.func_78789_a(-6.0F, -10.0F, -7.0F, 12, 18, 10);
        this.body.func_78793_a(0.0F, 5.0F, 2.0F);
        this.setRotation(this.body, 1.570796F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.func_78789_a(-3.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg1.func_78793_a(-3.0F, 12.0F, 7.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.func_78789_a(-1.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg2.func_78793_a(3.0F, 12.0F, 7.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.func_78789_a(-3.0F, 0.0F, -3.0F, 4, 12, 4);
        this.leg3.func_78793_a(-3.0F, 12.0F, -5.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.func_78789_a(-1.0F, 0.0F, -3.0F, 4, 12, 4);
        this.leg4.func_78793_a(3.0F, 12.0F, -5.0F);
        this.udders = new ModelRenderer(this, 52, 0);
        this.udders.func_78789_a(-2.0F, -3.0F, 0.0F, 4, 6, 2);
        this.udders.func_78793_a(0.0F, 14.0F, 6.0F);
        this.setRotation(this.udders, 1.570796F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 64, 0);
        this.field_78115_e.func_78789_a(-4.0F, 0.0F, -2.5F, 8, 12, 5);
        this.field_78115_e.func_78793_a(0.0F, -6.0F, -9.0F);
        this.field_78113_g = new ModelRenderer(this, 90, 0);
        this.field_78113_g.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78113_g.func_78793_a(5.0F, -4.0F, -9.0F);
        this.field_78113_g.field_78809_i = true;
        this.field_78112_f = new ModelRenderer(this, 90, 0);
        this.field_78112_f.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78112_f.func_78793_a(-5.0F, -4.0F, -9.0F);
        this.righthorn1 = new ModelRenderer(this, 0, 0);
        this.righthorn1.func_78789_a(-5.5F, -1.5F, -1.5F, 5, 3, 3);
        this.righthorn1.func_78793_a(-2.5F, -6.5F, 0.0F);
        this.righthorn1.field_78796_g = -0.43633235F;
        this.righthorn1.field_78808_h = 0.17453294F;
        this.righthorn2 = new ModelRenderer(this, 16, 0);
        this.righthorn2.func_78789_a(-3.5F, -1.0F, -1.0F, 3, 2, 2);
        this.righthorn2.func_78793_a(-4.5F, 0.0F, 0.0F);
        this.righthorn2.field_78796_g = -0.2617994F;
        this.righthorn2.field_78808_h = 0.7853982F;
        this.righthorn1.func_78792_a(this.righthorn2);
        this.lefthorn1 = new ModelRenderer(this, 0, 0);
        this.lefthorn1.field_78809_i = true;
        this.lefthorn1.func_78789_a(0.5F, -1.5F, -1.5F, 5, 3, 3);
        this.lefthorn1.func_78793_a(2.5F, -6.5F, 0.0F);
        this.lefthorn1.field_78796_g = 0.43633235F;
        this.lefthorn1.field_78808_h = -0.17453294F;
        this.lefthorn2 = new ModelRenderer(this, 16, 0);
        this.lefthorn2.func_78789_a(0.5F, -1.0F, -1.0F, 3, 2, 2);
        this.lefthorn2.func_78793_a(4.5F, 0.0F, 0.0F);
        this.lefthorn2.field_78796_g = 0.2617994F;
        this.lefthorn2.field_78808_h = -0.7853982F;
        this.lefthorn1.func_78792_a(this.lefthorn2);
        this.field_78116_c.func_78792_a(this.righthorn1);
        this.field_78116_c.func_78792_a(this.lefthorn1);
        this.snout = new ModelRenderer(this, 105, 28);
        this.snout.func_78789_a(-2.0F, -1.0F, -1.0F, 4, 3, 1);
        this.snout.func_78793_a(0.0F, -2.0F, -4.0F);
        this.field_78116_c.func_78792_a(this.snout);
        this.field_78114_d = new ModelRenderer(this, 0, 0);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.field_78116_c.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.leg1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.leg3.func_78785_a(f5);
        this.leg4.func_78785_a(f5);
        this.udders.func_78785_a(f5);
        this.field_78115_e.func_78785_a(f5);
        this.field_78113_g.func_78785_a(f5);
        this.field_78112_f.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.field_78116_c.field_78796_g = par4 / 57.295776F;
        this.field_78116_c.field_78795_f = par5 / 57.295776F;
        this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
        this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.field_78112_f.field_78808_h = 0.0F;
        this.field_78113_g.field_78808_h = 0.0F;
        this.field_78123_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.field_78124_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.field_78123_h.field_78796_g = 0.0F;
        this.field_78124_i.field_78796_g = 0.0F;
        if (this.field_78119_l != 0) {
            this.field_78113_g.field_78795_f = this.field_78113_g.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78119_l;
        }

        if (this.field_78120_m != 0) {
            this.field_78112_f.field_78795_f = this.field_78112_f.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78120_m;
        }

        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        if (this.field_78118_o) {
            float f = 0.0F;
            float f1 = 0.0F;

            this.field_78112_f.field_78808_h = 0.0F;
            this.field_78113_g.field_78808_h = 0.0F;
            this.field_78112_f.field_78796_g = -(0.1F - f * 0.6F) + this.field_78116_c.field_78796_g;
            this.field_78113_g.field_78796_g = 0.1F - f * 0.6F + this.field_78116_c.field_78796_g + 0.4F;
            this.field_78112_f.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
            this.field_78113_g.field_78795_f = -1.5707964F + this.field_78116_c.field_78795_f;
            this.field_78112_f.field_78795_f -= f * 1.2F - f1 * 0.4F;
            this.field_78113_g.field_78795_f -= f * 1.2F - f1 * 0.4F;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        }

        this.body.field_78795_f = 1.5707964F;
        this.leg1.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg3.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg4.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    }
}
