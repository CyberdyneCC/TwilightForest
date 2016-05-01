package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.EntityTFTowerGolem;

public class RenderTFTowerGolem extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/carminitegolem.png");

    public RenderTFTowerGolem(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        this.rotateTowerGolem((EntityTFTowerGolem) par1EntityLiving, par2, par3, par4);
    }

    private void rotateTowerGolem(EntityTFTowerGolem par1EntityLiving, float par2, float par3, float par4) {
        super.func_77043_a(par1EntityLiving, par2, par3, par4);
        if ((double) par1EntityLiving.field_70721_aZ >= 0.01D) {
            float f = 13.0F;
            float f1 = par1EntityLiving.field_70754_ba - par1EntityLiving.field_70721_aZ * (1.0F - par4) + 6.0F;
            float f2 = (Math.abs(f1 % f - f * 0.5F) - f * 0.25F) / (f * 0.25F);

            GL11.glRotatef(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }

    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFTowerGolem.textureLoc;
    }
}
