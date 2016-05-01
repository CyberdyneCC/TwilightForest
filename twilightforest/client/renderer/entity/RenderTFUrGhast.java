package twilightforest.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.client.model.ModelTFGhast;
import twilightforest.entity.EntityTFTowerGhast;

public class RenderTFUrGhast extends RenderTFTowerGhast {

    final ResourceLocation textureLocClosed = new ResourceLocation("twilightforest:textures/model/towerboss.png");
    final ResourceLocation textureLocOpen = new ResourceLocation("twilightforest:textures/model/towerboss_openeyes.png");
    final ResourceLocation textureLocAttack = new ResourceLocation("twilightforest:textures/model/towerboss_fire.png");

    public RenderTFUrGhast(ModelTFGhast modelTFGhast, float f, float scale) {
        super(modelTFGhast, f, scale);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        if (par1Entity instanceof EntityTFTowerGhast) {
            switch (((EntityTFTowerGhast) par1Entity).getAttackStatus()) {
            case 0:
            default:
                return this.textureLocClosed;

            case 1:
                return this.textureLocOpen;

            case 2:
                return this.textureLocAttack;
            }
        } else {
            return this.textureLocClosed;
        }
    }
}
