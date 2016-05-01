package twilightforest.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFFirefly;
import twilightforest.tileentity.TileEntityTFFirefly;

public class TileEntityTFFireflyRenderer extends TileEntitySpecialRenderer {

    private ModelTFFirefly fireflyModel = new ModelTFFirefly();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/firefly-tiny.png");

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderTileEntityFireflyAt((TileEntityTFFirefly) tileentity, d, d1, d2, f);
    }

    private void renderTileEntityFireflyAt(TileEntityTFFirefly tileentity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
        int facing = tileentity.func_145832_p();
        float rotX = 90.0F;
        float rotZ = 0.0F;

        if (facing == 3) {
            rotZ = 0.0F;
        }

        if (facing == 4) {
            rotZ = 180.0F;
        }

        if (facing == 1) {
            rotZ = -90.0F;
        }

        if (facing == 2) {
            rotZ = 90.0F;
        }

        if (facing == 5) {
            rotX = 0.0F;
        }

        if (facing == 6) {
            rotX = 180.0F;
        }

        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
        GL11.glRotatef(rotX, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(rotZ, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef((float) tileentity.currentYaw, 0.0F, 1.0F, 0.0F);
        this.func_147499_a(TileEntityTFFireflyRenderer.textureLoc);
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glColorMask(true, true, true, true);
        GL11.glDisable(3042);
        this.fireflyModel.render(0.0625F);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glDisable(2896);
        GL11.glBlendFunc(770, 1);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, tileentity.glowIntensity);
        this.fireflyModel.glow.func_78785_a(0.0625F);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
}
