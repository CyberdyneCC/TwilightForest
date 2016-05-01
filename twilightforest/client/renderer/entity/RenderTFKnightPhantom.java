package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFKnightPhantom2;
import twilightforest.entity.boss.EntityTFKnightPhantom;

public class RenderTFKnightPhantom extends RenderBiped {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/phantomskeleton.png");

    public RenderTFKnightPhantom(ModelTFKnightPhantom2 modelTFKnightPhantom2, float f) {
        super(modelTFKnightPhantom2, f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return RenderTFKnightPhantom.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = ((EntityTFKnightPhantom) par1EntityLivingBase).isChargingAtPlayer() ? 1.8F : 1.2F;

        GL11.glScalef(scale, scale, scale);
    }
}
