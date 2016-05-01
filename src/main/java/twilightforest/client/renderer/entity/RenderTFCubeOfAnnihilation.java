package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFCubeOfAnnihilation;

public class RenderTFCubeOfAnnihilation extends Render {

    private ModelBase model = new ModelTFCubeOfAnnihilation();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/cubeofannihilation.png");

    public void renderSpikeBlock(Entity entity, double x, double y, double z, float par8, float time) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        this.func_110777_b(entity);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glRotatef(MathHelper.func_76142_g(((float) x + (float) z + (float) entity.field_70173_aa + time) * 11.0F), 0.0F, 1.0F, 0.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glTranslatef(0.0F, -0.5F, 0.0F);
        this.model.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, time, 0.03125F);
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderSpikeBlock(par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFCubeOfAnnihilation.textureLoc;
    }
}
