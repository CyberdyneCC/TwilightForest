package twilightforest.client.renderer.entity;

import java.nio.FloatBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFTinyFirefly;
import twilightforest.entity.passive.EntityTFMobileFirefly;
import twilightforest.entity.passive.EntityTFTinyFirefly;

public class RenderTFTinyFirefly extends Render {

    ModelTFTinyFirefly fireflyModel = new ModelTFTinyFirefly();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/firefly-tiny.png");

    public void doRenderTinyFirefly(EntityTFTinyFirefly firefly, double x, double y, double z, float brightness, float size) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y + 0.5F, (float) z);
        FloatBuffer modelview = BufferUtils.createFloatBuffer(16);

        GL11.glGetFloat(2982, modelview);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                int index = i * 4 + j;

                if (i == j) {
                    modelview.put(index, 1.0F);
                } else {
                    modelview.put(index, 0.0F);
                }
            }
        }

        GL11.glLoadMatrix(modelview);
        this.field_76990_c.field_78724_e.func_110577_a(RenderTFTinyFirefly.textureLoc);
        GL11.glColorMask(true, true, true, true);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3008);
        GL11.glDisable(2896);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, brightness);
        this.fireflyModel.glow1.func_78785_a(0.0625F * size);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        if (entity instanceof EntityTFTinyFirefly) {
            EntityTFTinyFirefly firefly = (EntityTFTinyFirefly) entity;

            this.doRenderTinyFirefly(firefly, d, d1, d2, firefly.getGlowBrightness(), firefly.glowSize);
        } else if (entity instanceof EntityTFMobileFirefly) {
            EntityTFMobileFirefly firefly1 = (EntityTFMobileFirefly) entity;

            this.doRenderTinyFirefly((EntityTFTinyFirefly) null, d, d1, d2, firefly1.getGlowBrightness(), 1.0F);
        }

    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFTinyFirefly.textureLoc;
    }
}
