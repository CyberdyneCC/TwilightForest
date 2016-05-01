package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderBlockTFNagastone implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFNagastone(int nagastoneRenderID) {
        this.renderID = nagastoneRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        this.setRenderRotate(renderer, metadata);
        renderInvNormalBlock(renderer, block, metadata);
        this.restoreRendererRotate(renderer);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int meta = world.func_72805_g(x, y, z);

        this.setRenderRotate(renderer, meta);
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
        int type = meta & 12;
        int orient = meta & 3;

        if (type == 0) {
            switch (orient) {
            case 0:
                renderer.field_147867_u = 1;
                renderer.field_147865_v = 2;
                break;

            case 1:
                renderer.field_147867_u = 2;
                renderer.field_147865_v = 1;
                break;

            case 2:
                renderer.field_147867_u = 0;
                renderer.field_147865_v = 3;
                break;

            case 3:
                renderer.field_147867_u = 3;
                renderer.field_147865_v = 0;
            }
        } else if (type != 4 && type != 8) {
            if (type == 12) {
                switch (orient) {
                case 0:
                    renderer.field_147867_u = 0;
                    renderer.field_147865_v = 0;
                    break;

                case 1:
                    renderer.field_147867_u = 1;
                    renderer.field_147865_v = 1;
                    break;

                case 2:
                    renderer.field_147869_t = 2;
                    renderer.field_147871_s = 1;
                    renderer.field_147875_q = 1;
                    renderer.field_147873_r = 2;
                }
            }
        } else {
            switch (orient) {
            case 0:
                renderer.field_147867_u = 2;
                renderer.field_147865_v = 1;
                renderer.field_147873_r = 2;
                break;

            case 1:
                renderer.field_147867_u = 1;
                renderer.field_147865_v = 2;
                renderer.field_147875_q = 2;
                break;

            case 2:
                renderer.field_147867_u = 3;
                renderer.field_147865_v = 0;
                renderer.field_147871_s = 2;
                break;

            case 3:
                renderer.field_147867_u = 0;
                renderer.field_147865_v = 3;
                renderer.field_147869_t = 2;
            }
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

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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
