package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFProtectionBox;
import twilightforest.entity.EntityTFProtectionBox;

public class RenderTFProtectionBox extends Render {

    private ModelTFProtectionBox boxModel = new ModelTFProtectionBox();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/protectionbox.png");

    public RenderTFProtectionBox() {
        this.field_76989_e = 0.5F;
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float f, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        this.func_110776_a(RenderTFProtectionBox.textureLoc);
        float f1 = (float) entity.field_70173_aa + partialTick;

        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        float f2 = f1 * 0.05F;
        float f3 = f1 * 0.05F;

        GL11.glTranslatef(f2, f3, 0.0F);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        GL11.glMatrixMode(5888);
        GL11.glColorMask(true, true, true, true);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glDisable(2896);
        float alpha = 1.0F;

        if (((EntityTFProtectionBox) entity).lifeTime < 20) {
            alpha = (float) ((EntityTFProtectionBox) entity).lifeTime / 20.0F;
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
        this.boxModel.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glMatrixMode(5890);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
    }

    protected void preRenderCallback(EntityLivingBase par1EntityLiving, float par2) {
        float scale = 1.0F;

        GL11.glScalef(scale, scale, scale);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFProtectionBox.textureLoc;
    }
}
