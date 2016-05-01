package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTFBiped extends RenderBiped {

    private final ResourceLocation textureLoc;

    public RenderTFBiped(ModelBiped modelBiped, float scale, String textureName) {
        super(modelBiped, scale);
        if (textureName.startsWith("textures")) {
            this.textureLoc = new ResourceLocation(textureName);
        } else {
            this.textureLoc = new ResourceLocation("twilightforest:textures/model/" + textureName);
        }

    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.textureLoc;
    }
}
