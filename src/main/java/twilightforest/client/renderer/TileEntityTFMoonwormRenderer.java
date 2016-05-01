package twilightforest.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFMoonworm;
import twilightforest.tileentity.TileEntityTFMoonworm;

public class TileEntityTFMoonwormRenderer extends TileEntitySpecialRenderer {

    private ModelTFMoonworm moonwormModel = new ModelTFMoonworm();
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/moonworm.png");

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderTileEntityFireflyAt((TileEntityTFMoonworm) tileentity, d, d1, d2, f);
    }

    private void renderTileEntityFireflyAt(TileEntityTFMoonworm tileentity, double d, double d1, double d2, float partialTime) {
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
        this.func_147499_a(TileEntityTFMoonwormRenderer.textureLoc);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        this.moonwormModel.setLivingAnimations(tileentity, partialTime);
        this.moonwormModel.render(0.0625F);
        GL11.glPopMatrix();
    }
}
