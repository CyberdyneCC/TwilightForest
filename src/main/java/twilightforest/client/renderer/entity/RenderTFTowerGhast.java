package twilightforest.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.monster.EntityGhast;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFGhast;
import twilightforest.entity.boss.EntityTFUrGhast;

public class RenderTFTowerGhast extends RenderTFMiniGhast {

    private float ghastScale = 8.0F;

    public RenderTFTowerGhast(ModelTFGhast modelTFGhast, float f) {
        super(modelTFGhast, f);
    }

    public RenderTFTowerGhast(ModelTFGhast modelTFGhast, float f, float scale) {
        super(modelTFGhast, f);
        this.ghastScale = scale;
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        super.func_76986_a(entity, d, d1, d2, f, f1);
        if (entity instanceof EntityTFUrGhast && entity.field_70173_aa > 0) {
            BossStatus.func_82824_a((EntityTFUrGhast) entity, false);
        }

    }

    protected void preRenderGhast(EntityGhast par1EntityGhast, float par2) {
        float scaleVariable = ((float) par1EntityGhast.field_70794_e + (float) (par1EntityGhast.field_70791_f - par1EntityGhast.field_70794_e) * par2) / 20.0F;

        if (scaleVariable < 0.0F) {
            scaleVariable = 0.0F;
        }

        scaleVariable = 1.0F / (scaleVariable * scaleVariable * scaleVariable * scaleVariable * scaleVariable * 2.0F + 1.0F);
        float yScale = (this.ghastScale + scaleVariable) / 2.0F;
        float xzScale = (this.ghastScale + 1.0F / scaleVariable) / 2.0F;

        GL11.glScalef(xzScale, yScale, xzScale);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.preRenderGhast((EntityGhast) par1EntityLiving, par2);
    }
}
