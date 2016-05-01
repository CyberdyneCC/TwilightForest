package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.passive.EntityTFBird;

public class RenderTFBird extends RenderLiving {

    private final ResourceLocation textureLoc;

    public RenderTFBird(ModelBase par1ModelBase, float par2, String textureName) {
        super(par1ModelBase, par2);
        this.textureLoc = new ResourceLocation("twilightforest:textures/model/" + textureName);
    }

    public void renderTFBird(EntityTFBird par1EntityTFBird, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a(par1EntityTFBird, par2, par4, par6, par8, par9);
    }

    protected float getWingRotation(EntityTFBird par1EntityTFBird, float time) {
        float f = par1EntityTFBird.lastFlapLength + (par1EntityTFBird.flapLength - par1EntityTFBird.lastFlapLength) * time;
        float f1 = par1EntityTFBird.lastFlapIntensity + (par1EntityTFBird.flapIntensity - par1EntityTFBird.lastFlapIntensity) * time;

        return (MathHelper.func_76126_a(f) + 1.0F) * f1;
    }

    protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
        return this.getWingRotation((EntityTFBird) par1EntityLiving, par2);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderTFBird((EntityTFBird) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderTFBird((EntityTFBird) par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.textureLoc;
    }
}
