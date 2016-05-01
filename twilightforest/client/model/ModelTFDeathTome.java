package twilightforest.client.model;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTFDeathTome extends ModelBook {

    ModelRenderer everything = (new ModelRenderer(this)).func_78784_a(0, 0).func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
    ModelRenderer book = (new ModelRenderer(this)).func_78784_a(0, 0).func_78789_a(0.0F, 0.0F, 0.0F, 0, 0, 0);
    ModelRenderer loosePage1;
    ModelRenderer loosePage2;
    ModelRenderer loosePage3;
    ModelRenderer loosePage4;

    public ModelTFDeathTome() {
        this.book.func_78792_a(this.field_78102_a);
        this.book.func_78792_a(this.field_78100_b);
        this.book.func_78792_a(this.field_78097_g);
        this.book.func_78792_a(this.field_78101_c);
        this.book.func_78792_a(this.field_78098_d);
        this.book.func_78792_a(this.field_78099_e);
        this.book.func_78792_a(this.field_78096_f);
        this.loosePage1 = (new ModelRenderer(this)).func_78784_a(24, 10).func_78789_a(0.0F, -4.0F, -8.0F, 5, 8, 0);
        this.loosePage2 = (new ModelRenderer(this)).func_78784_a(24, 10).func_78789_a(0.0F, -4.0F, 9.0F, 5, 8, 0);
        this.loosePage3 = (new ModelRenderer(this)).func_78784_a(24, 10).func_78789_a(0.0F, -4.0F, 11.0F, 5, 8, 0);
        this.loosePage4 = (new ModelRenderer(this)).func_78784_a(24, 10).func_78789_a(0.0F, -4.0F, 7.0F, 5, 8, 0);
        this.everything.func_78792_a(this.book);
        this.everything.func_78792_a(this.loosePage1);
        this.everything.func_78792_a(this.loosePage2);
        this.everything.func_78792_a(this.loosePage3);
        this.everything.func_78792_a(this.loosePage4);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float scale) {
        GL11.glEnable(2884);
        this.setRotationAngles((float) par1Entity.field_70173_aa, 0.4F, 0.6F, 0.9F, par6, 0.0625F);
        this.everything.func_78785_a(scale);
        GL11.glDisable(2884);
    }

    public void setRotationAngles(float bounce, float flipRight, float flipLeft, float open, float rotate, float scale) {
        this.book.field_78808_h = -0.87266463F;
        this.everything.field_78796_g = rotate / 57.295776F + 1.5707964F;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float partialTick) {
        float bounce = (float) par1EntityLiving.field_70173_aa + partialTick;
        float open = 0.9F;
        float flipRight = 0.4F;
        float flipLeft = 0.6F;

        this.book.func_78793_a(0.0F, 4.0F + MathHelper.func_76126_a(bounce * 0.3F) * 2.0F, 0.0F);
        float openAngle = (MathHelper.func_76126_a(bounce * 0.4F) * 0.3F + 1.25F) * open;

        this.field_78102_a.field_78796_g = 3.1415927F + openAngle;
        this.field_78100_b.field_78796_g = -openAngle;
        this.field_78101_c.field_78796_g = openAngle;
        this.field_78098_d.field_78796_g = -openAngle;
        this.field_78099_e.field_78796_g = openAngle - openAngle * 2.0F * flipRight;
        this.field_78096_f.field_78796_g = openAngle - openAngle * 2.0F * flipLeft;
        this.field_78101_c.field_78800_c = MathHelper.func_76126_a(openAngle);
        this.field_78098_d.field_78800_c = MathHelper.func_76126_a(openAngle);
        this.field_78099_e.field_78800_c = MathHelper.func_76126_a(openAngle);
        this.field_78096_f.field_78800_c = MathHelper.func_76126_a(openAngle);
        this.loosePage1.field_78796_g = bounce / 4.0F;
        this.loosePage1.field_78795_f = MathHelper.func_76126_a(bounce / 5.0F) / 3.0F;
        this.loosePage1.field_78808_h = MathHelper.func_76134_b(bounce / 5.0F) / 5.0F;
        this.loosePage2.field_78796_g = bounce / 3.0F;
        this.loosePage2.field_78795_f = MathHelper.func_76126_a(bounce / 5.0F) / 3.0F;
        this.loosePage2.field_78808_h = MathHelper.func_76134_b(bounce / 5.0F) / 4.0F + 2.0F;
        this.loosePage3.field_78796_g = bounce / 4.0F;
        this.loosePage3.field_78795_f = -MathHelper.func_76126_a(bounce / 5.0F) / 3.0F;
        this.loosePage3.field_78808_h = MathHelper.func_76134_b(bounce / 5.0F) / 5.0F - 1.0F;
        this.loosePage4.field_78796_g = bounce / 4.0F;
        this.loosePage4.field_78795_f = -MathHelper.func_76126_a(bounce / 2.0F) / 4.0F;
        this.loosePage4.field_78808_h = MathHelper.func_76134_b(bounce / 7.0F) / 5.0F;
    }
}
