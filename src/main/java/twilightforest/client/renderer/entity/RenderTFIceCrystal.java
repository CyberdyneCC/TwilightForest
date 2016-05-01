package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFIceCrystal;

public class RenderTFIceCrystal extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/icecrystal.png");

    public RenderTFIceCrystal() {
        super(new ModelTFIceCrystal(), 1.0F);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float partialTick) {
        float bounce = (float) par1EntityLivingBase.field_70173_aa + partialTick;

        GL11.glTranslatef(0.0F, MathHelper.func_76126_a(bounce * 0.2F) * 0.15F, 0.0F);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return RenderTFIceCrystal.textureLoc;
    }
}
