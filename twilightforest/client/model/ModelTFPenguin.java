package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTFPenguin extends ModelBase {

    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer head;
    ModelRenderer beak;

    public ModelTFPenguin() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.body = new ModelRenderer(this, 32, 0);
        this.body.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 9, 8);
        this.body.func_78793_a(0.0F, 14.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 34, 18);
        this.rightarm.func_78789_a(-1.0F, -1.0F, -2.0F, 1, 8, 4);
        this.rightarm.func_78793_a(-4.0F, 15.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 24, 18);
        this.leftarm.func_78789_a(0.0F, -1.0F, -2.0F, 1, 8, 4);
        this.leftarm.func_78793_a(4.0F, 15.0F, 0.0F);
        this.leftarm.field_78809_i = true;
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 1, 8);
        this.rightleg.func_78793_a(-2.0F, 23.0F, 0.0F);
        this.rightleg.func_78787_b(64, 32);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.func_78789_a(-2.0F, 0.0F, -5.0F, 4, 1, 8);
        this.leftleg.func_78793_a(2.0F, 23.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78789_a(-3.5F, -4.0F, -3.5F, 7, 5, 7);
        this.head.func_78793_a(0.0F, 13.0F, 0.0F);
        this.beak = new ModelRenderer(this, 0, 13);
        this.beak.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 1, 2);
        this.beak.func_78793_a(0.0F, -1.0F, -4.0F);
        this.head.func_78792_a(this.beak);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7);
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
        }

    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.rightleg.field_78795_f = MathHelper.func_76134_b(par1) * 0.7F * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b(par1 + 3.1415927F) * 0.7F * par2;
        this.rightarm.field_78808_h = par3;
        this.leftarm.field_78808_h = -par3;
    }
}
