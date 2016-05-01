package twilightforest.client.renderer.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFIceShooter;

public class RenderTFIceShooter extends RenderTFBiped {

    public RenderTFIceShooter() {
        super(new ModelTFIceShooter(), 1.0F, "iceshooter.png");
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float partialTick) {
        float bounce = (float) par1EntityLivingBase.field_70173_aa + partialTick;

        GL11.glTranslatef(0.0F, MathHelper.func_76126_a(bounce * 0.2F) * 0.15F, 0.0F);
    }
}
