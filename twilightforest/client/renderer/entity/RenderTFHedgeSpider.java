package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTFHedgeSpider extends RenderSpider {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/hedgespider.png");

    protected ResourceLocation func_110775_a(Entity entity) {
        return RenderTFHedgeSpider.textureLoc;
    }
}
