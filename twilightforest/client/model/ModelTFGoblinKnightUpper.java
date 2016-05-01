package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFGoblinKnightUpper;

public class ModelTFGoblinKnightUpper extends ModelBiped {

    public ModelRenderer breastplate;
    public ModelRenderer helmet;
    public ModelRenderer righthorn1;
    public ModelRenderer righthorn2;
    public ModelRenderer lefthorn1;
    public ModelRenderer lefthorn2;
    public ModelRenderer shield;
    public ModelRenderer spear;

    public ModelTFGoblinKnightUpper() {
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
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.field_78116_c.func_78793_a(0.0F, 12.0F, 0.0F);
        this.field_78114_d = new ModelRenderer(this, 0, 0);
        this.field_78114_d.func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.field_78114_d.func_78793_a(0.0F, 12.0F, 0.0F);
        this.helmet = new ModelRenderer(this, 0, 0);
        this.helmet.func_78789_a(-3.5F, -11.0F, -3.5F, 7, 11, 7);
        this.helmet.field_78796_g = 0.7853982F;
        this.righthorn1 = new ModelRenderer(this, 28, 0);
        this.righthorn1.func_78789_a(-6.0F, -1.5F, -1.5F, 7, 3, 3);
        this.righthorn1.func_78793_a(-3.5F, -9.0F, 0.0F);
        this.righthorn1.field_78796_g = 0.2617994F;
        this.righthorn1.field_78808_h = 0.17453294F;
        this.righthorn2 = new ModelRenderer(this, 28, 6);
        this.righthorn2.func_78789_a(-3.0F, -1.0F, -1.0F, 3, 2, 2);
        this.righthorn2.func_78793_a(-5.5F, 0.0F, 0.0F);
        this.righthorn2.field_78808_h = 0.17453294F;
        this.righthorn1.func_78792_a(this.righthorn2);
        this.lefthorn1 = new ModelRenderer(this, 28, 0);
        this.lefthorn1.field_78809_i = true;
        this.lefthorn1.func_78789_a(-1.0F, -1.5F, -1.5F, 7, 3, 3);
        this.lefthorn1.func_78793_a(3.5F, -9.0F, 0.0F);
        this.lefthorn1.field_78796_g = -0.2617994F;
        this.lefthorn1.field_78808_h = -0.17453294F;
        this.lefthorn2 = new ModelRenderer(this, 28, 6);
        this.lefthorn2.func_78789_a(0.0F, -1.0F, -1.0F, 3, 2, 2);
        this.lefthorn2.func_78793_a(5.5F, 0.0F, 0.0F);
        this.lefthorn2.field_78808_h = -0.17453294F;
        this.lefthorn1.func_78792_a(this.lefthorn2);
        this.field_78114_d.func_78792_a(this.helmet);
        this.field_78114_d.func_78792_a(this.righthorn1);
        this.field_78114_d.func_78792_a(this.lefthorn1);
        this.field_78115_e = new ModelRenderer(this, 0, 18);
        this.field_78115_e.func_78793_a(0.0F, 12.0F, 0.0F);
        this.field_78115_e.func_78789_a(-5.5F, 0.0F, -2.0F, 11, 8, 4);
        this.field_78115_e.func_78784_a(30, 24).func_78789_a(-6.5F, 0.0F, -2.0F, 1, 4, 4);
        this.field_78115_e.func_78784_a(30, 24).func_78789_a(5.5F, 0.0F, -2.0F, 1, 4, 4);
        this.field_78112_f = new ModelRenderer(this, 44, 16);
        this.field_78112_f.func_78789_a(-4.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78112_f.func_78793_a(-6.5F, 14.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 44, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(0.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78113_g.func_78793_a(6.5F, 14.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 30, 16);
        this.field_78123_h.func_78789_a(-1.5F, 0.0F, -2.0F, 3, 4, 4);
        this.field_78123_h.func_78793_a(-4.0F, 20.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 30, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-1.5F, 0.0F, -2.0F, 3, 4, 4);
        this.field_78124_i.func_78793_a(4.0F, 20.0F, 0.0F);
        this.shield = new ModelRenderer(this, 63, 36);
        this.shield.func_78789_a(-6.0F, -6.0F, -2.0F, 12, 20, 2);
        this.shield.func_78793_a(0.0F, 12.0F, 0.0F);
        this.shield.field_78795_f = 1.5707964F;
        this.field_78113_g.func_78792_a(this.shield);
        this.spear = new ModelRenderer(this, 108, 0);
        this.spear.func_78789_a(-1.0F, -19.0F, -1.0F, 2, 40, 2);
        this.spear.func_78793_a(-2.0F, 8.5F, 0.0F);
        this.spear.field_78795_f = 1.5707964F;
        this.field_78112_f.func_78792_a(this.spear);
        this.breastplate = new ModelRenderer(this, 64, 0);
        this.breastplate.func_78789_a(-6.5F, 0.0F, -3.0F, 13, 12, 6);
        this.breastplate.func_78793_a(0.0F, 11.5F, 0.0F);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.shield.field_78807_k = !((EntityTFGoblinKnightUpper) par1Entity).hasShield();
        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        if (((EntityTFGoblinKnightUpper) par1Entity).hasArmor()) {
            this.renderBreastplate(par7);
        }

    }

    public void renderBreastplate(float par1) {
        this.breastplate.func_78785_a(par1);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        EntityTFGoblinKnightUpper upperKnight = (EntityTFGoblinKnightUpper) par7Entity;
        boolean hasShield = upperKnight.hasShield();

        this.field_78116_c.field_78796_g = par4 / 57.295776F;
        this.field_78116_c.field_78795_f = par5 / 57.295776F;
        this.field_78116_c.field_78808_h = 0.0F;
        this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78114_d.field_78808_h = this.field_78116_c.field_78808_h;
        this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
        float leftConstraint = hasShield ? 0.2F : par2;

        this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * leftConstraint * 0.5F;
        this.field_78112_f.field_78808_h = 0.0F;
        this.field_78113_g.field_78808_h = 0.0F;
        this.field_78123_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.field_78124_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.field_78123_h.field_78796_g = 0.0F;
        this.field_78124_i.field_78796_g = 0.0F;
        if (this.field_78093_q) {
            this.field_78112_f.field_78795_f += -0.62831855F;
            this.field_78113_g.field_78795_f += -0.62831855F;
            this.field_78123_h.field_78795_f = 0.0F;
            this.field_78124_i.field_78795_f = 0.0F;
        }

        if (this.field_78119_l != 0) {
            this.field_78113_g.field_78795_f = this.field_78113_g.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78119_l;
        }

        this.field_78120_m = 1;
        if (this.field_78120_m != 0) {
            this.field_78112_f.field_78795_f = this.field_78112_f.field_78795_f * 0.5F - 0.31415927F * (float) this.field_78120_m;
        }

        this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f - 2.0734511513692637D);
        if (upperKnight.heavySpearTimer > 0) {
            this.field_78112_f.field_78795_f -= this.getArmRotationDuringSwing((float) (60 - upperKnight.heavySpearTimer) + par6) / 57.295776F;
        }

        this.field_78112_f.field_78796_g = 0.0F;
        this.field_78113_g.field_78796_g = 0.0F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78808_h = -this.field_78113_g.field_78808_h;
        this.shield.field_78795_f = (float) (6.283185307179586D - (double) this.field_78113_g.field_78795_f);
    }

    private float getArmRotationDuringSwing(float attackTime) {
        return attackTime <= 10.0F ? attackTime * 1.0F : (attackTime > 10.0F && attackTime <= 30.0F ? 10.0F : (attackTime > 30.0F && attackTime <= 33.0F ? (attackTime - 30.0F) * -8.0F + 10.0F : (attackTime > 33.0F && attackTime <= 50.0F ? -15.0F : (attackTime > 50.0F && attackTime <= 60.0F ? (10.0F - (attackTime - 50.0F)) * -1.5F : 0.0F))));
    }
}
