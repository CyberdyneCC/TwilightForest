package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFMoonworm;

public class RenderTFMoonwormShot extends Render {

    private ModelTFMoonworm wormModel = new ModelTFMoonworm();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/moonworm.png");

    public RenderTFMoonwormShot() {
        this.field_76989_e = 0.5F;
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float f, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 1.0F);
        this.func_110776_a(RenderTFMoonwormShot.textureLoc);
        this.wormModel.render(0.075F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFMoonwormShot.textureLoc;
    }
}
