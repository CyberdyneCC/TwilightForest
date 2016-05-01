package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFNagaSegment extends Render {

    private ModelBase model;
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/nagasegment.png");

    public RenderTFNagaSegment(ModelBase model, float f) {
        this.model = model;
    }

    public void renderMe(Entity par1Entity, double par2, double par4, double par6, float par8, float time) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glRotatef(180.0F - MathHelper.func_76142_g(par8), 0.0F, 1.0F, 0.0F);
        float pitch = par1Entity.field_70127_C + (par1Entity.field_70125_A - par1Entity.field_70127_C) * time;

        GL11.glRotatef(pitch, 1.0F, 0.0F, 0.0F);
        this.func_110776_a(RenderTFNagaSegment.textureLoc);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.model.func_78088_a(par1Entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderMe(par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFNagaSegment.textureLoc;
    }
}
