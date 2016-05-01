package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.passive.EntityTFBunny;

public class RenderTFBunny extends RenderLiving {

    final ResourceLocation textureLocDutch = new ResourceLocation("twilightforest:textures/model/bunnydutch.png");
    final ResourceLocation textureLocWhite = new ResourceLocation("twilightforest:textures/model/bunnywhite.png");
    final ResourceLocation textureLocBrown = new ResourceLocation("twilightforest:textures/model/bunnybrown.png");

    public RenderTFBunny(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        if (par1Entity instanceof EntityTFBunny) {
            switch (((EntityTFBunny) par1Entity).getBunnyType()) {
            case 0:
            case 1:
            default:
                return this.textureLocDutch;

            case 2:
                return this.textureLocWhite;

            case 3:
                return this.textureLocBrown;
            }
        } else {
            return this.textureLocDutch;
        }
    }
}
