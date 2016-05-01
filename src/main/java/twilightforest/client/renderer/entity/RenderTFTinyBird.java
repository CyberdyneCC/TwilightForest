package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.passive.EntityTFTinyBird;

public class RenderTFTinyBird extends RenderTFBird {

    final ResourceLocation textureLocSparrow = new ResourceLocation("twilightforest:textures/model/tinybirdbrown.png");
    final ResourceLocation textureLocFinch = new ResourceLocation("twilightforest:textures/model/tinybirdgold.png");
    final ResourceLocation textureLocCardinal = new ResourceLocation("twilightforest:textures/model/tinybirdred.png");
    final ResourceLocation textureLocBluebird = new ResourceLocation("twilightforest:textures/model/tinybirdblue.png");

    public RenderTFTinyBird(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2, "tinybirdbrown.png");
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        if (par1Entity instanceof EntityTFTinyBird) {
            switch (((EntityTFTinyBird) par1Entity).getBirdType()) {
            case 0:
            default:
                return this.textureLocSparrow;

            case 1:
                return this.textureLocBluebird;

            case 2:
                return this.textureLocCardinal;

            case 3:
                return this.textureLocFinch;
            }
        } else {
            return this.textureLocSparrow;
        }
    }
}
