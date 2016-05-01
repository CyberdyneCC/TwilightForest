package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.EntityTFTowerGhast;

public class RenderTFMiniGhast extends RenderLiving {

    final ResourceLocation textureLocClosed = new ResourceLocation("twilightforest:textures/model/towerghast.png");
    final ResourceLocation textureLocOpen = new ResourceLocation("twilightforest:textures/model/towerghast_openeyes.png");
    final ResourceLocation textureLocAttack = new ResourceLocation("twilightforest:textures/model/towerghast_fire.png");

    public RenderTFMiniGhast(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
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
