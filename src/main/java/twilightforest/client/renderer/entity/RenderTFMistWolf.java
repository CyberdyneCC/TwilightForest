package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFMistWolf extends RenderWolf {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/mistwolf.png");

    public RenderTFMistWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        float wolfScale = 1.9F;

        GL11.glScalef(wolfScale, wolfScale, wolfScale);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(770, 771);
        float misty = par1EntityLiving.func_70013_c(0.0F) * 3.0F + 0.25F;

        misty = Math.min(1.0F, misty);
        float smoky = par1EntityLiving.func_70013_c(0.0F) * 2.0F + 0.6F;

        GL11.glColor4f(misty, misty, misty, smoky);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return -1;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFMistWolf.textureLoc;
    }
}
