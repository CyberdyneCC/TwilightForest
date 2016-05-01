package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFApocalypseCube extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/apocalypse2.png");

    public RenderTFApocalypseCube() {
        super(new ModelTFApocalypseCube(), 1.0F);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFApocalypseCube.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = 1.0F;

        GL11.glScalef(scale, scale, scale);
    }
}
