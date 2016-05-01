package twilightforest.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFGiant extends RenderBiped {

    private ResourceLocation textureLoc = new ResourceLocation("textures/entity/steve.png");

    public RenderTFGiant() {
        super(new ModelBiped(), 0.625F);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return Minecraft.func_71410_x().field_71439_g.func_110306_p() != null ? Minecraft.func_71410_x().field_71439_g.func_110306_p() : this.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = 4.0F;

        GL11.glScalef(scale, scale, scale);
    }
}
