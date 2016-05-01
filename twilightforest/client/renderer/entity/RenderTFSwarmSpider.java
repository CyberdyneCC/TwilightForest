package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFSwarmSpider extends RenderSpider {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/swarmspider.png");

    protected ResourceLocation func_110775_a(Entity entity) {
        return RenderTFSwarmSpider.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = 0.5F;

        GL11.glScalef(scale, scale, scale);
    }
}
