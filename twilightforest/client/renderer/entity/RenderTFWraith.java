package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFWraith extends RenderBiped {

    private static final ResourceLocation textureWraith = new ResourceLocation("twilightforest:textures/model/ghost.png");
    private static final ResourceLocation textureCrown = new ResourceLocation("twilightforest:textures/model/ghost-crown.png");

    public RenderTFWraith(ModelBiped modelbiped, float f) {
        super(modelbiped, f);
    }

    protected int func_77032_a(EntityLivingBase entityliving, int i, float f) {
        this.func_77042_a(this.field_77045_g);
        if (i == 1) {
            this.func_110776_a(RenderTFWraith.textureWraith);
            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(1, 1);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            return 1;
        } else {
            return 0;
        }
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFWraith.textureCrown;
    }
}
