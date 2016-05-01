package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.passive.EntityTFBird;

public class ModelTFRaven extends ModelBase {

    ModelRenderer head;
    ModelRenderer beak1;
    ModelRenderer beak2;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer rightfoot;
    ModelRenderer leftfoot;
    ModelRenderer tail;

    public ModelTFRaven() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78789_a(-1.5F, -1.5F, -3.0F, 3, 3, 3);
        this.head.func_78793_a(0.0F, 18.0F, 0.0F);
        this.head.func_78787_b(32, 32);
        this.head.field_78809_i = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.beak1 = new ModelRenderer(this, 12, 0);
        this.beak1.func_78789_a(-0.5F, -1.0F, -2.0F, 1, 1, 2);
        this.beak1.func_78793_a(0.0F, 0.0F, -2.5F);
        this.beak1.field_78795_f = 0.2617994F;
        this.head.func_78792_a(this.beak1);
        this.beak2 = new ModelRenderer(this, 12, 0);
        this.beak2.func_78789_a(-0.5F, 0.0F, -2.0F, 1, 1, 2);
        this.beak2.func_78793_a(0.0F, 0.0F, -2.5F);
        this.beak2.field_78795_f = -0.2617994F;
        this.head.func_78792_a(this.beak2);
        this.body = new ModelRenderer(this, 0, 6);
        this.body.func_78789_a(-1.5F, 0.0F, -1.0F, 3, 4, 6);
        this.body.func_78793_a(0.0F, 17.0F, 1.0F);
        this.body.func_78787_b(32, 32);
        this.setRotation(this.body, -0.5235988F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 0, 16);
        this.rightarm.func_78789_a(-1.0F, 0.0F, -1.5F, 1, 3, 6);
        this.rightarm.func_78793_a(-1.5F, 18.0F, 1.0F);
        this.rightarm.func_78787_b(32, 32);
        this.leftarm = new ModelRenderer(this, 0, 16);
        this.leftarm.func_78789_a(0.0F, 0.0F, -1.5F, 1, 3, 6);
        this.leftarm.func_78793_a(1.5F, 18.0F, 1.0F);
        this.leftarm.func_78787_b(32, 32);
        this.rightleg = new ModelRenderer(this, 14, 16);
        this.rightleg.func_78789_a(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.rightleg.func_78793_a(-1.5F, 21.0F, 1.0F);
        this.rightleg.func_78787_b(32, 32);
        this.rightfoot = new ModelRenderer(this, 14, 20);
        this.rightfoot.func_78789_a(0.0F, -1.0F, -2.0F, 1, 1, 2);
        this.rightfoot.func_78793_a(0.0F, 2.0F, 1.0F);
        this.rightfoot.func_78787_b(32, 32);
        this.setRotation(this.rightfoot, 0.5235988F, 0.0F, 0.0F);
        this.rightleg.func_78792_a(this.rightfoot);
        this.leftleg = new ModelRenderer(this, 14, 16);
        this.leftleg.func_78789_a(0.0F, 0.0F, 0.0F, 1, 2, 1);
        this.leftleg.func_78793_a(0.5F, 21.0F, 1.0F);
        this.leftleg.func_78787_b(32, 32);
        this.leftfoot = new ModelRenderer(this, 14, 20);
        this.leftfoot.func_78789_a(0.0F, -1.0F, -2.0F, 1, 1, 2);
        this.leftfoot.func_78793_a(0.0F, 2.0F, 1.0F);
        this.leftfoot.func_78787_b(32, 32);
        this.setRotation(this.leftfoot, 0.5235988F, 0.0F, 0.0F);
        this.leftleg.func_78792_a(this.leftfoot);
        this.tail = new ModelRenderer(this, 0, 25);
        this.tail.func_78789_a(-1.5F, -0.5F, 0.0F, 3, 1, 3);
        this.tail.func_78793_a(0.0F, 21.0F, 4.0F);
        this.tail.func_78787_b(32, 32);
        this.setRotation(this.tail, -0.5235988F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        this.tail.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.head.field_78808_h = par4 > 5.0F ? -0.2617994F : 0.0F;
        this.rightleg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.rightarm.field_78808_h = par3;
        this.leftarm.field_78808_h = -par3;
        if (((EntityTFBird) par7Entity).isBirdLanded()) {
            this.rightleg.field_78797_d = 21.0F;
            this.leftleg.field_78797_d = 21.0F;
        } else {
            this.rightleg.field_78797_d = 20.0F;
            this.leftleg.field_78797_d = 20.0F;
        }

    }
}
