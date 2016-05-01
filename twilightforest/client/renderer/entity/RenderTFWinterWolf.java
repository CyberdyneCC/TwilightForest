package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFWinterWolf extends RenderWolf {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/winterwolf.png");

    public RenderTFWinterWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        float wolfScale = 1.9F;

        GL11.glScalef(wolfScale, wolfScale, wolfScale);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return -1;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFWinterWolf.textureLoc;
    }
}
