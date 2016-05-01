package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTFGenericLiving extends RenderLiving {

    final ResourceLocation textureLoc;

    public RenderTFGenericLiving(ModelBase par1ModelBase, float par2, String textureName) {
        super(par1ModelBase, par2);
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
