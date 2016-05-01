package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFYeti;

public class ModelTFYeti extends ModelBiped {

    public ModelRenderer mouth;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;
    public ModelRenderer angryLeftEye;
    public ModelRenderer angryRightEye;

    public ModelTFYeti() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-4.0F, -8.0F, -4.0F, 0, 0, 0);
        this.field_78114_d = new ModelRenderer(this, 32, 0);
        this.field_78114_d.func_78789_a(-4.0F, -8.0F, -4.0F, 0, 0, 0);
        this.field_78115_e = new ModelRenderer(this, 32, 0);
        this.field_78115_e.func_78789_a(-10.0F, 0.0F, -6.0F, 20, 26, 12);
        this.field_78115_e.func_78793_a(0.0F, -14.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 96, 6);
        this.mouth.func_78789_a(-7.0F, -5.0F, -0.5F, 14, 10, 1);
        this.mouth.func_78793_a(0.0F, 12.0F, -6.0F);
        this.field_78115_e.func_78792_a(this.mouth);
        this.rightEye = new ModelRenderer(this, 96, 0);
        this.rightEye.func_78789_a(-2.5F, -2.5F, -0.5F, 5, 5, 1);
        this.rightEye.func_78793_a(-5.5F, 4.5F, -6.0F);
        this.field_78115_e.func_78792_a(this.rightEye);
        this.leftEye = new ModelRenderer(this, 96, 0);
        this.leftEye.func_78789_a(-2.5F, -2.5F, -0.5F, 5, 5, 1);
        this.leftEye.func_78793_a(5.5F, 4.5F, -6.0F);
        this.field_78115_e.func_78792_a(this.leftEye);
        this.angryRightEye = new ModelRenderer(this, 109, 0);
        this.angryRightEye.func_78789_a(-2.5F, -2.5F, -0.5F, 5, 5, 1);
        this.angryRightEye.func_78793_a(5.5F, 4.5F, -6.0F);
        this.field_78115_e.func_78792_a(this.angryRightEye);
        this.angryLeftEye = new ModelRenderer(this, 109, 0);
        this.angryLeftEye.func_78789_a(-2.5F, -2.5F, -0.5F, 5, 5, 1);
        this.angryLeftEye.func_78793_a(-5.5F, 4.5F, -6.0F);
        this.field_78115_e.func_78792_a(this.angryLeftEye);
        this.field_78112_f = new ModelRenderer(this, 0, 0);
        this.field_78112_f.func_78789_a(-5.0F, -2.0F, -3.0F, 6, 16, 6);
        this.field_78112_f.func_78793_a(-11.0F, -4.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 0, 0);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(-1.0F, -2.0F, -3.0F, 6, 16, 6);
        this.field_78113_g.func_78793_a(11.0F, -4.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 22);
        this.field_78123_h.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 12, 8);
        this.field_78123_h.func_78793_a(-6.0F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 22);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 12, 8);
        this.field_78124_i.func_78793_a(6.0F, 12.0F, 0.0F);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        EntityTFYeti yeti = (EntityTFYeti) par7Entity;

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
        if (par7Entity.field_70153_n != null) {
            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
        }

        if (this.field_78119_l != 0) {
            this.field_78113_g.field_78795_f = this.field_78113_g.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78119_l;
        }

        if (this.field_78120_m != 0) {
            this.field_78112_f.field_78795_f = this.field_78112_f.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78120_m;
        }

        this.field_78112_f.field_78796_g = 0.0F;
        this.field_78113_g.field_78796_g = 0.0F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        if (yeti.isAngry()) {
            float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
            float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);

            this.field_78112_f.field_78808_h = 0.0F;
            this.field_78113_g.field_78808_h = 0.0F;
            this.field_78112_f.field_78796_g = -(0.1F - f6 * 0.6F);
            this.field_78113_g.field_78796_g = 0.1F - f6 * 0.6F;
            this.field_78112_f.field_78795_f = -1.5707964F;
            this.field_78113_g.field_78795_f = -1.5707964F;
            this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        }

    }

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        EntityTFYeti yeti = (EntityTFYeti) par1EntityLivingBase;

        if (yeti.isAngry()) {
            this.rightEye.field_78807_k = true;
            this.leftEye.field_78807_k = true;
            this.angryRightEye.field_78807_k = false;
            this.angryLeftEye.field_78807_k = false;
        } else {
            this.rightEye.field_78807_k = false;
            this.leftEye.field_78807_k = false;
            this.angryRightEye.field_78807_k = true;
            this.angryLeftEye.field_78807_k = true;
        }

    }
}
