package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFGoblinKnightLower;

public class ModelTFGoblinKnightLower extends ModelBiped {

    public ModelRenderer tunic;

    public ModelTFGoblinKnightLower() {
        this.field_78119_l = 0;
        this.field_78120_m = 0;
        this.field_78117_n = false;
        this.field_78118_o = false;
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.field_78122_k = new ModelRenderer(this, 0, 0);
        this.field_78122_k.func_78789_a(-5.0F, 0.0F, -1.0F, 10, 16, 1);
        this.field_78121_j = new ModelRenderer(this, 24, 0);
        this.field_78121_j.func_78789_a(-3.0F, -6.0F, -1.0F, 6, 6, 1);
        this.field_78116_c = new ModelRenderer(this, 0, 32);
        this.field_78116_c.func_78789_a(-2.5F, -5.0F, -3.5F, 5, 5, 5);
        this.field_78116_c.func_78793_a(0.0F, 10.0F, 1.0F);
        this.field_78114_d = new ModelRenderer(this, 0, 0);
        this.field_78114_d.func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.field_78115_e = new ModelRenderer(this, 16, 48);
        this.field_78115_e.func_78789_a(-3.5F, 0.0F, -2.0F, 7, 8, 4);
        this.field_78115_e.func_78793_a(0.0F, 8.0F, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 40, 48);
        this.field_78112_f.func_78789_a(-2.0F, -2.0F, -1.5F, 2, 8, 3);
        this.field_78112_f.func_78793_a(-3.5F, 10.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 40, 48);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(0.0F, -2.0F, -1.5F, 2, 8, 3);
        this.field_78113_g.func_78793_a(3.5F, 10.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 48);
        this.field_78123_h.func_78789_a(-3.0F, 0.0F, -2.0F, 4, 8, 4);
        this.field_78123_h.func_78793_a(-2.5F, 16.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 48);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-1.0F, 0.0F, -2.0F, 4, 8, 4);
        this.field_78124_i.func_78793_a(2.5F, 16.0F, 0.0F);
        this.tunic = new ModelRenderer(this, 64, 19);
        this.tunic.func_78789_a(-6.0F, 0.0F, -3.0F, 12, 9, 6);
        this.tunic.func_78793_a(0.0F, 7.5F, 0.0F);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        if (((EntityTFGoblinKnightLower) par1Entity).hasArmor()) {
            this.renderTunic(par7);
        }

    }

    public void renderTunic(float par1) {
        this.tunic.func_78785_a(par1);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
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
            this.field_78116_c.field_78796_g = 0.0F;
            this.field_78116_c.field_78795_f = 0.0F;
            this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
            this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
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
    }
}
