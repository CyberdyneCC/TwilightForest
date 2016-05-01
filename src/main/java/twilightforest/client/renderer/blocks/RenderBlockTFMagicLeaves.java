package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderBlockTFMagicLeaves implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFMagicLeaves(int myRenderID) {
        this.renderID = myRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        this.setRenderRotate(renderer, metadata);
        renderInvNormalBlock(renderer, block, metadata);
        this.restoreRendererRotate(renderer);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int meta = world.func_72805_g(x, y, z);

        this.setRenderRotate(renderer, meta, x, y, z);
        boolean didRender = renderer.func_147784_q(block, x, y, z);

        this.restoreRendererRotate(renderer);
        return didRender;
    }

    private void restoreRendererRotate(RenderBlocks renderer) {
        renderer.field_147871_s = 0;
        renderer.field_147875_q = 0;
        renderer.field_147873_r = 0;
        renderer.field_147869_t = 0;
        renderer.field_147867_u = 0;
        renderer.field_147865_v = 0;
    }

    private void setRenderRotate(RenderBlocks renderer, int meta) {
        this.setRenderRotate(renderer, meta, 0, 0, 0);
    }

    private void setRenderRotate(RenderBlocks renderer, int meta, int x, int y, int z) {
        int type = meta & 3;

        if (type == 0) {
            renderer.field_147875_q = 3;
            renderer.field_147865_v = 0;
            renderer.field_147869_t = 2;
            renderer.field_147871_s = 2;
        } else if (type == 1) {
            renderer.field_147865_v = x + y + z & 3;
            renderer.field_147867_u = x + y + z & 3;
            renderer.field_147875_q = 1;
            renderer.field_147873_r = 2;
            renderer.field_147869_t = 2;
            renderer.field_147871_s = 1;
        } else if (type == 2) {
            renderer.field_147865_v = x + y + z & 3;
            renderer.field_147867_u = x + y + z & 3;
            renderer.field_147875_q = 2;
            renderer.field_147873_r = 1;
            renderer.field_147869_t = 1;
            renderer.field_147871_s = 2;
        } else if (type == 3) {
            renderer.field_147865_v = x + y + z & 3;
            renderer.field_147867_u = x + y + z & 3;
            renderer.field_147875_q = x + y + z & 3;
            renderer.field_147873_r = x + y + z & 3;
            renderer.field_147869_t = x + y + z & 3;
            renderer.field_147871_s = x + y + z & 3;
        }

    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return this.renderID;
    }

    public static void renderInvNormalBlock(RenderBlocks renderblocks, Block par1Block, int meta) {
        Tessellator tessellator = Tessellator.field_78398_a;

        if (renderblocks.field_147844_c) {
            int colorInt = par1Block.func_149741_i(meta);
            float red = (float) (colorInt >> 16 & 255) / 255.0F;
            float green = (float) (colorInt >> 8 & 255) / 255.0F;
            float blue = (float) (colorInt & 255) / 255.0F;

            GL11.glColor4f(red, green, blue, 1.0F);
        }

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        par1Block.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
        renderblocks.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(0, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
        renderblocks.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(1, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
        renderblocks.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(2, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
        renderblocks.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(3, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
        renderblocks.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(4, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
        renderblocks.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, par1Block.func_149691_a(5, meta));
        tessellator.func_78381_a();
    }
}
