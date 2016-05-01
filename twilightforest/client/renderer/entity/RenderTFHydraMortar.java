package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFHydraMortar;
import twilightforest.entity.boss.EntityTFHydraMortar;

public class RenderTFHydraMortar extends Render {

    private ModelTFHydraMortar mortarModel = new ModelTFHydraMortar();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/hydramortar.png");

    public RenderTFHydraMortar() {
        this.field_76989_e = 0.5F;
    }

    public void func_76986_a(Entity entity, double x, double y, double z, float f, float partialTick) {
        EntityTFHydraMortar mortar = (EntityTFHydraMortar) entity;

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        float f1;

        if ((float) mortar.fuse - partialTick + 1.0F < 10.0F) {
            f1 = 1.0F - ((float) mortar.fuse - partialTick + 1.0F) / 10.0F;
            if (f1 < 0.0F) {
                f1 = 0.0F;
            }

            if (f1 > 1.0F) {
                f1 = 1.0F;
            }

            f1 *= f1;
            f1 *= f1;
            float f2 = 1.0F + f1 * 0.3F;

            GL11.glScalef(f2, f2, f2);
        }

        f1 = (1.0F - ((float) mortar.fuse - partialTick + 1.0F) / 100.0F) * 0.8F;
        this.func_110776_a(RenderTFHydraMortar.textureLoc);
        this.mortarModel.render(0.075F);
        if (mortar.fuse / 5 % 2 == 0) {
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 772);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
            this.mortarModel.render(0.075F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
        }

        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFHydraMortar.textureLoc;
    }
}
