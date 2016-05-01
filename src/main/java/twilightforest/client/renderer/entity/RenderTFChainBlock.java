package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.EntityTFChainBlock;

public class RenderTFChainBlock extends Render {

    private ModelBase model;
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/blockgoblin.png");

    public RenderTFChainBlock(ModelBase modelTFSpikeBlock, float f) {
        this.model = modelTFSpikeBlock;
    }

    public void renderSpikeBlock(EntityTFChainBlock par1Entity, double par2, double par4, double par6, float par8, float time) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        this.func_110777_b(par1Entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glRotatef(MathHelper.func_76142_g((float) par4), 1.0F, 0.0F, 1.0F);
        GL11.glRotatef(MathHelper.func_76142_g(((float) par2 + (float) par6) * 11.0F), 0.0F, 1.0F, 0.0F);
        this.model.func_78088_a(par1Entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityTFChainBlock chainBlock = (EntityTFChainBlock) par1Entity;

        this.renderSpikeBlock(chainBlock, par2, par4, par6, par8, par9);
        RenderManager.field_78727_a.func_147937_a(chainBlock.chain1, par9);
        RenderManager.field_78727_a.func_147937_a(chainBlock.chain2, par9);
        RenderManager.field_78727_a.func_147937_a(chainBlock.chain3, par9);
        RenderManager.field_78727_a.func_147937_a(chainBlock.chain4, par9);
        RenderManager.field_78727_a.func_147937_a(chainBlock.chain5, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFChainBlock.textureLoc;
    }
}
