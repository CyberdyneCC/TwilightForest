package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTFBoar extends RenderPig {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/wildboar.png");

    public RenderTFBoar(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFBoar.textureLoc;
    }
}
