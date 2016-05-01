package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderBlockTFHugeLilyPad implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFHugeLilyPad(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

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
        int orient = meta >> 2;

        if (orient == 2) {
            orient = 3;
        } else if (orient == 3) {
            orient = 2;
        }

        renderer.field_147867_u = orient;
        renderer.field_147865_v = orient;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return this.renderID;
    }
}
