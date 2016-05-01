package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFKingSpider extends RenderSpider {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/kingspider.png");

    protected ResourceLocation func_110775_a(Entity entity) {
        return RenderTFKingSpider.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = 1.9F;

        GL11.glScalef(scale, scale, scale);
    }
}
