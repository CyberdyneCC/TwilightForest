package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTFDeer extends RenderCow {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/wilddeer.png");

    public RenderTFDeer(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFDeer.textureLoc;
    }
}
