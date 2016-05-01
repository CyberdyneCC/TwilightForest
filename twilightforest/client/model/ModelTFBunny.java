package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTFBunny extends ModelBase {

    ModelRenderer tail;
    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer head;

    public ModelTFBunny() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.func_78085_a("head.head", 0, 0);
        this.func_78085_a("head.ear2", 16, 0);
        this.func_78085_a("head.ear1", 16, 0);
        this.tail = new ModelRenderer(this, 0, 18);
        this.tail.func_78789_a(-1.0F, -1.0F, 0.0F, 2, 2, 2);
        this.tail.func_78793_a(0.0F, 20.0F, 3.0F);
        this.tail.func_78787_b(32, 32);
        this.tail.field_78809_i = true;
        this.setRotation(this.tail, 0.0F, 0.0F, 0.0F);
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
        this.leg3.field_78809_i = true;
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.leg4.func_78793_a(1.0F, 23.0F, -2.0F);
        this.leg4.func_78787_b(32, 32);
        this.leg4.field_78809_i = true;
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, "head");
        this.head.func_78793_a(0.0F, 22.0F, -1.0F);
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.head.field_78809_i = true;
        this.head.func_78786_a("head", -2.0F, -4.0F, -3.0F, 4, 4, 4);
        this.head.func_78786_a("ear2", -2.5F, -8.0F, -0.5F, 2, 4, 1);
        this.head.func_78786_a("ear1", 0.5F, -8.0F, -0.5F, 2, 4, 1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.tail.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.leg1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.leg3.func_78785_a(f5);
        this.leg4.func_78785_a(f5);
        this.head.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.leg1.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.leg2.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg3.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.leg4.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    }
}
