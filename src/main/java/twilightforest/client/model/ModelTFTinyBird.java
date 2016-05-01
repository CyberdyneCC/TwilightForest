package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.passive.EntityTFBird;

public class ModelTFTinyBird extends ModelBase {

    ModelRenderer beak;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer tail;

    public ModelTFTinyBird() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78789_a(-1.5F, -1.5F, -1.5F, 3, 3, 3);
        this.head.func_78793_a(0.0F, 20.5F, -0.5F);
        this.head.func_78787_b(32, 32);
        this.head.field_78809_i = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.beak = new ModelRenderer(this, 12, 0);
        this.beak.func_78789_a(-0.5F, -0.5F, -0.5F, 1, 1, 1);
        this.beak.func_78793_a(0.0F, 0.5F, -2.0F);
        this.head.func_78792_a(this.beak);
        this.body = new ModelRenderer(this, 0, 6);
        this.body.func_78789_a(-1.5F, 0.0F, -1.0F, 3, 3, 3);
        this.body.func_78793_a(0.0F, 20.0F, 0.0F);
        this.body.func_78787_b(32, 32);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 12, 2);
        this.rightarm.func_78789_a(-1.0F, 0.0F, -1.5F, 1, 2, 3);
        this.rightarm.func_78793_a(-1.5F, 20.5F, 1.0F);
        this.rightarm.func_78787_b(32, 32);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 12, 2);
        this.leftarm.func_78789_a(0.0F, 0.0F, -1.5F, 1, 2, 3);
        this.leftarm.func_78793_a(1.5F, 20.5F, 1.0F);
        this.leftarm.func_78787_b(32, 32);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 12);
        this.rightleg.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
        this.rightleg.func_78793_a(-1.5F, 23.0F, 0.0F);
        this.rightleg.func_78787_b(32, 32);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 12);
        this.leftleg.func_78789_a(0.5F, 0.0F, 0.0F, 1, 1, 1);
        this.leftleg.func_78793_a(0.0F, 23.0F, 0.0F);
        this.leftleg.func_78787_b(32, 32);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 14);
        this.tail.func_78789_a(-1.5F, -0.5F, 0.0F, 3, 1, 2);
        this.tail.func_78793_a(0.0F, 22.0F, 2.0F);
        this.tail.func_78787_b(32, 32);
        this.tail.field_78809_i = true;
        this.setRotation(this.tail, 0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        if (this.field_78091_s) {
            float f = 2.0F;

            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5.0F * par7, 0.75F * par7);
            this.head.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f, 1.0F / f, 1.0F / f);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.body.func_78785_a(par7);
            this.rightleg.func_78785_a(par7);
            this.leftleg.func_78785_a(par7);
            this.rightarm.func_78785_a(par7);
            this.leftarm.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.head.func_78785_a(par7);
            this.body.func_78785_a(par7);
            this.rightleg.func_78785_a(par7);
            this.leftleg.func_78785_a(par7);
            this.rightarm.func_78785_a(par7);
            this.leftarm.func_78785_a(par7);
            this.tail.func_78785_a(par7);
        }

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.rightleg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.rightarm.field_78808_h = par3;
        this.leftarm.field_78808_h = -par3;
        if (((EntityTFBird) par7Entity).isBirdLanded()) {
            this.rightleg.field_78797_d = 23.0F;
            this.leftleg.field_78797_d = 23.0F;
        } else {
            this.rightleg.field_78797_d = 22.5F;
            this.leftleg.field_78797_d = 22.5F;
        }

    }
}
