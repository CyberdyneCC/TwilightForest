package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelTFSquirrel extends ModelBase {

    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer head;
    ModelRenderer tail;
    ModelRenderer fluff1;
    ModelRenderer fluff2;
    ModelRenderer fluff3;

    public ModelTFSquirrel() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.func_78085_a("head.head", 0, 0);
        this.func_78085_a("head.ear2", 16, 0);
        this.func_78085_a("head.ear1", 16, 0);
        this.func_78085_a("tail.fluff1", 0, 20);
        this.func_78085_a("tail.base", 0, 18);
        this.func_78085_a("tail.fluff2", 0, 20);
        this.func_78085_a("tail.fluff3", 0, 26);
        this.body = new ModelRenderer(this, 0, 8);
        this.body.func_78789_a(-2.0F, -1.0F, -2.0F, 4, 3, 5);
        this.body.func_78793_a(0.0F, 21.0F, 0.0F);
        this.body.func_78787_b(32, 32);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.leg1.func_78793_a(-2.0F, 23.0F, 2.0F);
        this.leg1.func_78787_b(32, 32);
        this.leg1.field_78809_i = true;
        this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.leg2.func_78793_a(1.0F, 23.0F, 2.0F);
        this.leg2.func_78787_b(32, 32);
        this.leg2.field_78809_i = true;
        this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.leg3.func_78793_a(-2.0F, 23.0F, -2.0F);
        this.leg3.func_78787_b(32, 32);
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.leg4.func_78793_a(1.0F, 23.0F, -2.0F);
        this.leg4.func_78787_b(32, 32);
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, "head");
        this.head.func_78793_a(0.0F, 22.0F, -2.0F);
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.head.func_78786_a("head", -2.0F, -5.0F, -3.0F, 4, 4, 4);
        this.head.func_78786_a("ear2", -2.0F, -6.0F, -0.5F, 1, 1, 1);
        this.head.func_78786_a("ear1", 1.0F, -6.0F, -0.5F, 1, 1, 1);
        this.tail = new ModelRenderer(this, "tail");
        this.tail.func_78793_a(0.0F, 21.0F, 2.0F);
        this.tail.func_78786_a("base", -0.5F, -1.5F, 0.5F, 1, 1, 1);
        this.fluff1 = new ModelRenderer(this, 0, 20);
        this.fluff1.func_78789_a(-1.5F, -4.0F, 1.0F, 3, 3, 3);
        this.tail.func_78792_a(this.fluff1);
        this.fluff2 = new ModelRenderer(this, 0, 20);
        this.fluff2.func_78789_a(0.0F, -3.0F, -1.5F, 3, 3, 3);
        this.fluff2.func_78793_a(-1.5F, -4.0F, 2.5F);
        this.fluff1.func_78792_a(this.fluff2);
        this.fluff3 = new ModelRenderer(this, 0, 26);
        this.fluff3.func_78789_a(1.5F, -3.0F, -1.5F, 3, 3, 3);
        this.fluff3.func_78793_a(-1.5F, -3.0F, 0.0F);
        this.fluff2.func_78792_a(this.fluff3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.body.func_78785_a(f5);
        this.leg1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.leg3.func_78785_a(f5);
        this.leg4.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.tail.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {}

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.leg1.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg3.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg4.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        if ((double) par2 > 0.2D) {
            float wiggle = Math.min(par2, 0.6F);

            this.tail.field_78795_f = (MathHelper.func_76134_b(par3 * 0.6662F) * 1.0F - 1.0471976F) * wiggle;
            this.fluff2.field_78795_f = MathHelper.func_76134_b(par3 * 0.7774F) * 1.2F * wiggle;
            this.fluff3.field_78795_f = MathHelper.func_76134_b(par3 * 0.8886F + 1.5707964F) * 1.4F * wiggle;
        } else {
            this.tail.field_78795_f = 0.2F + MathHelper.func_76134_b(par3 * 0.3335F) * 0.15F;
            this.fluff2.field_78795_f = 0.1F + MathHelper.func_76134_b(par3 * 0.4445F) * 0.2F;
            this.fluff3.field_78795_f = 0.1F + MathHelper.func_76134_b(par3 * 0.5555F) * 0.25F;
        }

    }
}
