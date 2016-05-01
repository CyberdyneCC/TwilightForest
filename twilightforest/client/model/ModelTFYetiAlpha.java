package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.boss.EntityTFYetiAlpha;

public class ModelTFYetiAlpha extends ModelBiped {

    public ModelRenderer mouth;
    public ModelRenderer leftEye;
    public ModelRenderer rightEye;

    public ModelTFYetiAlpha() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-4.0F, -8.0F, -4.0F, 0, 0, 0);
        this.field_78114_d = new ModelRenderer(this, 32, 0);
        this.field_78114_d.func_78789_a(-4.0F, -8.0F, -4.0F, 0, 0, 0);
        this.field_78115_e = new ModelRenderer(this, 80, 0);
        this.field_78115_e.func_78789_a(-24.0F, -60.0F, -18.0F, 48, 72, 36);
        this.field_78115_e.func_78793_a(0.0F, -6.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 121, 50);
        this.mouth.func_78789_a(-17.0F, -7.0F, -1.5F, 34, 29, 2);
        this.mouth.func_78793_a(0.0F, -37.0F, -18.0F);
        this.field_78115_e.func_78792_a(this.mouth);
        this.rightEye = new ModelRenderer(this, 64, 0);
        this.rightEye.func_78789_a(-6.0F, -6.0F, -1.5F, 12, 12, 2);
        this.rightEye.func_78793_a(-14.0F, -50.0F, -18.0F);
        this.field_78115_e.func_78792_a(this.rightEye);
        this.leftEye = new ModelRenderer(this, 64, 0);
        this.leftEye.func_78789_a(-6.0F, -6.0F, -1.5F, 12, 12, 2);
        this.leftEye.func_78793_a(14.0F, -50.0F, -18.0F);
        this.field_78115_e.func_78792_a(this.leftEye);
        this.field_78112_f = new ModelRenderer(this, 0, 0);
        this.field_78112_f.func_78789_a(-15.0F, -6.0F, -8.0F, 16, 48, 16);
        this.field_78112_f.func_78793_a(-25.0F, -26.0F, 0.0F);
        this.field_78115_e.func_78792_a(this.field_78112_f);
        this.field_78113_g = new ModelRenderer(this, 0, 0);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(-1.0F, -6.0F, -8.0F, 16, 48, 16);
        this.field_78113_g.func_78793_a(25.0F, -26.0F, 0.0F);
        this.field_78115_e.func_78792_a(this.field_78113_g);
        this.field_78123_h = new ModelRenderer(this, 0, 66);
        this.field_78123_h.func_78789_a(-10.0F, 0.0F, -10.0F, 20, 20, 20);
        this.field_78123_h.func_78793_a(-13.5F, 4.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 66);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-10.0F, 0.0F, -10.0F, 20, 20, 20);
        this.field_78124_i.func_78793_a(13.5F, 4.0F, 0.0F);
        this.addPairHorns(-58.0F, 35.0F);
        this.addPairHorns(-46.0F, 15.0F);
        this.addPairHorns(-36.0F, -5.0F);
    }

    private void addPairHorns(float height, float zangle) {
        ModelRenderer horn1a = new ModelRenderer(this, 0, 108);

        horn1a.func_78789_a(-9.0F, -5.0F, -5.0F, 10, 10, 10);
        horn1a.func_78793_a(-24.0F, height, -8.0F);
        horn1a.field_78796_g = -0.5235988F;
        horn1a.field_78808_h = zangle / 57.295776F;
        this.field_78115_e.func_78792_a(horn1a);
        ModelRenderer horn1b = new ModelRenderer(this, 40, 108);

        horn1b.func_78789_a(-14.0F, -4.0F, -4.0F, 18, 8, 8);
        horn1b.func_78793_a(-8.0F, 0.0F, 0.0F);
        horn1b.field_78796_g = -0.34906587F;
        horn1b.field_78808_h = zangle / 57.295776F;
        horn1a.func_78792_a(horn1b);
        ModelRenderer horn2a = new ModelRenderer(this, 0, 108);

        horn2a.func_78789_a(-1.0F, -5.0F, -5.0F, 10, 10, 10);
        horn2a.func_78793_a(24.0F, height, 0.0F);
        horn2a.field_78796_g = 0.5235988F;
        horn2a.field_78808_h = -zangle / 57.295776F;
        this.field_78115_e.func_78792_a(horn2a);
        ModelRenderer horn2b = new ModelRenderer(this, 40, 108);

        horn2b.func_78789_a(-2.0F, -4.0F, -4.0F, 18, 8, 8);
        horn2b.func_78793_a(8.0F, 0.0F, 0.0F);
        horn2b.field_78796_g = 0.34906587F;
        horn2b.field_78808_h = -zangle / 57.295776F;
        horn2a.func_78792_a(horn2b);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        this.field_78115_e.func_78785_a(par7);
        this.field_78123_h.func_78785_a(par7);
        this.field_78124_i.func_78785_a(par7);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        EntityTFYetiAlpha yeti = (EntityTFYetiAlpha) par7Entity;

        this.field_78116_c.field_78796_g = par4 / 57.295776F;
        this.field_78116_c.field_78795_f = par5 / 57.295776F;
        this.field_78115_e.field_78795_f = par5 / 57.295776F;
        this.field_78123_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.field_78124_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.field_78123_h.field_78796_g = 0.0F;
        this.field_78124_i.field_78796_g = 0.0F;
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
        this.field_78115_e.field_78797_d = -6.0F;
        this.field_78123_h.field_78797_d = 4.0F;
        this.field_78124_i.field_78797_d = 4.0F;
        if (yeti.isTired()) {
            this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
            this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
            this.field_78112_f.field_78808_h = 0.0F;
            this.field_78113_g.field_78808_h = 0.0F;
            this.field_78112_f.field_78795_f += -0.62831855F;
            this.field_78113_g.field_78795_f += -0.62831855F;
            this.field_78123_h.field_78795_f = -1.2566371F;
            this.field_78124_i.field_78795_f = -1.2566371F;
            this.field_78123_h.field_78796_g = 0.31415927F;
            this.field_78124_i.field_78796_g = -0.31415927F;
            this.field_78115_e.field_78797_d = 6.0F;
            this.field_78123_h.field_78797_d = 12.0F;
            this.field_78124_i.field_78797_d = 12.0F;
        }

        if (yeti.isRampaging()) {
            this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.66F + 3.1415927F) * 2.0F * par2 * 0.5F;
            this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.66F) * 2.0F * par2 * 0.5F;
            this.field_78112_f.field_78796_g += MathHelper.func_76134_b(par1 * 0.25F) * 0.5F + 0.5F;
            this.field_78113_g.field_78796_g -= MathHelper.func_76134_b(par1 * 0.25F) * 0.5F + 0.5F;
            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.9269908169872414D);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.9269908169872414D);
            this.field_78112_f.field_78808_h = 0.0F;
            this.field_78113_g.field_78808_h = 0.0F;
        }

        if (par7Entity.field_70153_n != null) {
            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
        }

    }
}
