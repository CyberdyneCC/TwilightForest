package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFSlimeBeetle;

public class RenderTFSlimeBeetle extends RenderLiving {

    ModelBase renderModel = new ModelTFSlimeBeetle(true);
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/slimebeetle.png");

    public RenderTFSlimeBeetle(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.shouldSlimeRenderPass(par1EntityLiving, par2, par3);
    }

    protected int shouldSlimeRenderPass(EntityLivingBase par1EntitySlime, int par2, float par3) {
        if (par1EntitySlime.func_82150_aj()) {
            return 0;
        } else if (par2 == 0) {
            this.func_77042_a(this.renderModel);
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            return 1;
        } else {
            if (par2 == 1) {
                GL11.glDisable(3042);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return -1;
        }
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFSlimeBeetle.textureLoc;
    }
}
