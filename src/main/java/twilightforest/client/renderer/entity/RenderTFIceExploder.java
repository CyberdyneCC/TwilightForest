package twilightforest.client.renderer.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFIceExploder;

public class RenderTFIceExploder extends RenderTFBiped {

    public RenderTFIceExploder() {
        super(new ModelTFIceExploder(), 1.0F, "iceexploder.png");
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float partialTick) {
        float bounce = (float) par1EntityLivingBase.field_70173_aa + partialTick;

        GL11.glTranslatef(0.0F, MathHelper.func_76126_a(bounce * 0.2F) * 0.15F, 0.0F);
        float f1 = (float) par1EntityLivingBase.field_70725_aQ;

        if (f1 > 0.0F) {
            float f2 = 1.0F + MathHelper.func_76126_a(f1 * 100.0F) * f1 * 0.01F;

            if (f1 < 0.0F) {
                f1 = 0.0F;
            }

            if (f1 > 1.0F) {
                f1 = 1.0F;
            }

            f1 *= f1;
            f1 *= f1;
            float f3 = (1.0F + f1 * 0.4F) * f2;
            float f4 = (1.0F + f1 * 0.1F) / f2;

            GL11.glScalef(f3, f4, f3);
        }

    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);
    }

    protected int func_77030_a(EntityLivingBase par1EntityLivingBase, float par2, float par3) {
        if (par1EntityLivingBase.field_70725_aQ > 0) {
            float f2 = (float) par1EntityLivingBase.field_70725_aQ + par3;

            if ((int) (f2 / 2.0F) % 2 == 0) {
                return 0;
            } else {
                int i = (int) (f2 * 0.2F * 255.0F);

                if (i < 0) {
                    i = 0;
                }

                if (i > 255) {
                    i = 255;
                }

                short short1 = 255;
                short short2 = 255;
                short short3 = 255;

                return i << 24 | short1 << 16 | short2 << 8 | short3;
            }
        } else {
            return 0;
        }
    }
}
