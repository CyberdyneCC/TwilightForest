package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFTroll;

public class ModelTFTroll extends ModelBiped {

    public ModelRenderer nose;

    public ModelTFTroll() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-5.0F, -8.0F, -3.0F, 10, 10, 10);
        this.field_78116_c.func_78793_a(0.0F, -9.0F, -6.0F);
        this.field_78114_d = new ModelRenderer(this, 32, 0);
        this.field_78114_d.func_78789_a(-4.0F, -8.0F, -4.0F, 0, 0, 0);
        this.field_78115_e = new ModelRenderer(this, 40, 0);
        this.field_78115_e.func_78789_a(-8.0F, 0.0F, -5.0F, 16, 26, 10);
        this.field_78115_e.func_78793_a(0.0F, -14.0F, 0.0F);
        this.nose = new ModelRenderer(this, 0, 21);
        this.nose.func_78789_a(-2.0F, -2.0F, -2.0F, 4, 8, 4);
        this.nose.func_78793_a(0.0F, -2.0F, -4.0F);
        this.field_78116_c.func_78792_a(this.nose);
        this.field_78112_f = new ModelRenderer(this, 32, 36);
        this.field_78112_f.func_78789_a(-5.0F, -2.0F, -3.0F, 6, 22, 6);
        this.field_78112_f.func_78793_a(-9.0F, -9.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 32, 36);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(-1.0F, -2.0F, -3.0F, 6, 22, 6);
        this.field_78113_g.func_78793_a(9.0F, -9.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 44);
        this.field_78123_h.func_78789_a(-3.0F, 0.0F, -4.0F, 6, 12, 8);
        this.field_78123_h.func_78793_a(-5.0F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 44);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-3.0F, 0.0F, -4.0F, 6, 12, 8);
        this.field_78124_i.func_78793_a(5.0F, 12.0F, 0.0F);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        EntityTFTroll troll = (EntityTFTroll) par7Entity;

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

        if (this.field_78119_l != 0 || this.field_78120_m != 0) {
            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
        }

        if (this.field_78095_p > 0.0F) {
            float swing = 1.0F - this.field_78095_p;

            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f - 3.141592653589793D * (double) swing);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f - 3.141592653589793D * (double) swing);
        }

        this.field_78112_f.field_78796_g = 0.0F;
        this.field_78113_g.field_78796_g = 0.0F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    }

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        EntityTFTroll troll = (EntityTFTroll) par1EntityLivingBase;

        if (troll.func_70638_az() != null) {
            this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
            this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
        }

    }
}
