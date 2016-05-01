package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import twilightforest.block.TFBlocks;

public class RenderBlockTFThorns implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFThorns(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        renderInvBlock(renderer, block, metadata);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int l = block.func_149720_d(world, x, y, z);
        float f = (float) (l >> 16 & 255) / 255.0F;
        float f1 = (float) (l >> 8 & 255) / 255.0F;
        float f2 = (float) (l & 255) / 255.0F;
        int metadata = world.func_72805_g(x, y, z);
        int type = metadata & 12;

        switch (type) {
        case 0:
        default:
            return this.renderCactusLikeY(block, x, y, z, f, f1, f2, metadata, world, renderer);

        case 4:
            return this.renderCactusLikeX(block, x, y, z, f, f1, f2, metadata, world, renderer);

        case 8:
            return this.renderCactusLikeZ(block, x, y, z, f, f1, f2, metadata, world, renderer);
        }
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return this.renderID;
    }

    public boolean renderCactusLikeX(Block block, int x, int y, int z, float red, float green, float blue, int metadata, IBlockAccess world, RenderBlocks renderer) {
        this.setUVRotationX(renderer);
        Tessellator tessellator = Tessellator.field_78398_a;
        float middle = 0.5F;
        float full = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float bRed = middle * red;
        float tRed = full * red;
        float zRed = f5 * red;
        float xRed = f6 * red;
        float bGreen = middle * green;
        float tGreen = full * green;
        float zGreen = f5 * green;
        float xGreen = f6 * green;
        float bBlue = middle * blue;
        float tBlue = full * blue;
        float zBlue = f5 * blue;
        float xBlue = f6 * blue;
        float onePixel = 0.1875F;
        int blockBrightness = block.func_149677_c(renderer.field_147845_a, x, y, z);

        if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x - 1, y, z, 4)) {
            tessellator.func_78380_c(renderer.field_147859_h > 0.0D ? blockBrightness : block.func_149677_c(renderer.field_147845_a, x - 1, y, z));
            tessellator.func_78386_a(bRed, bGreen, bBlue);
            renderer.func_147798_e(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 4));
        }

        if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x + 1, y, z, 5)) {
            tessellator.func_78380_c(renderer.field_147861_i < 1.0D ? blockBrightness : block.func_149677_c(renderer.field_147845_a, x + 1, y, z));
            tessellator.func_78386_a(tRed, tGreen, tBlue);
            renderer.func_147764_f(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 5));
        }

        this.drawXSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, onePixel, blockBrightness);
        this.resetUVRotation(renderer);
        if (this.canConnectTo(world, x, y, z + 1)) {
            this.setUVRotationZ(renderer);
            renderer.func_147782_a(0.0D, 0.0D, (double) (1.0F - onePixel), 1.0D, 1.0D, 1.0D);
            this.drawZSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x, y, z - 1)) {
            this.setUVRotationZ(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double) onePixel);
            this.drawZSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x, y + 1, z)) {
            this.resetUVRotation(renderer);
            renderer.func_147782_a(0.0D, (double) (1.0F - onePixel), 0.0D, 1.0D, 1.0D, 1.0D);
            this.drawYSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
        }

        if (this.canConnectTo(world, x, y - 1, z)) {
            this.resetUVRotation(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, (double) onePixel, 1.0D);
            this.drawYSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
        }

        return true;
    }

    public boolean renderCactusLikeY(Block block, int x, int y, int z, float red, float green, float blue, int metadata, IBlockAccess world, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.field_78398_a;
        float middle = 0.5F;
        float full = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float bRed = middle * red;
        float tRed = full * red;
        float zRed = f5 * red;
        float xRed = f6 * red;
        float bGreen = middle * green;
        float tGreen = full * green;
        float zGreen = f5 * green;
        float xGreen = f6 * green;
        float bBlue = middle * blue;
        float tBlue = full * blue;
        float zBlue = f5 * blue;
        float xBlue = f6 * blue;
        float onePixel = 0.1875F;
        int blockBrightness = block.func_149677_c(world, x, y, z);

        if (renderer.field_147837_f || block.func_149646_a(world, x, y - 1, z, 0)) {
            tessellator.func_78380_c(renderer.field_147855_j > 0.0D ? blockBrightness : block.func_149677_c(world, x, y - 1, z));
            tessellator.func_78386_a(bRed, bGreen, bBlue);
            renderer.func_147768_a(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, world, x, y, z, 0));
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x, y + 1, z, 1)) {
            tessellator.func_78380_c(renderer.field_147857_k < 1.0D ? blockBrightness : block.func_149677_c(world, x, y + 1, z));
            tessellator.func_78386_a(tRed, tGreen, tBlue);
            renderer.func_147806_b(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, world, x, y, z, 1));
        }

        this.drawYSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, onePixel, blockBrightness);
        if (this.canConnectTo(world, x + 1, y, z)) {
            this.setUVRotationX(renderer);
            renderer.func_147782_a((double) (1.0F - onePixel), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            this.drawXSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x - 1, y, z)) {
            this.setUVRotationX(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, (double) onePixel, 1.0D, 1.0D);
            this.drawXSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x, y, z + 1)) {
            this.setUVRotationZ(renderer);
            renderer.func_147782_a(0.0D, 0.0D, (double) (1.0F - onePixel), 1.0D, 1.0D, 1.0D);
            this.drawZSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x, y, z - 1)) {
            this.setUVRotationZ(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double) onePixel);
            this.drawZSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        return true;
    }

    public boolean renderCactusLikeZ(Block block, int x, int y, int z, float red, float green, float blue, int metadata, IBlockAccess world, RenderBlocks renderer) {
        this.setUVRotationZ(renderer);
        Tessellator tessellator = Tessellator.field_78398_a;
        float middle = 0.5F;
        float full = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float bRed = middle * red;
        float tRed = full * red;
        float zRed = f5 * red;
        float xRed = f6 * red;
        float bGreen = middle * green;
        float tGreen = full * green;
        float zGreen = f5 * green;
        float xGreen = f6 * green;
        float bBlue = middle * blue;
        float tBlue = full * blue;
        float zBlue = f5 * blue;
        float xBlue = f6 * blue;
        float onePixel = 0.1875F;
        int blockBrightness = block.func_149677_c(renderer.field_147845_a, x, y, z);

        if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z - 1, 0)) {
            tessellator.func_78380_c(renderer.field_147851_l > 0.0D ? blockBrightness : block.func_149677_c(renderer.field_147845_a, x, y, z - 1));
            tessellator.func_78386_a(bRed, bGreen, bBlue);
            renderer.func_147761_c(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 2));
        }

        if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z + 1, 1)) {
            tessellator.func_78380_c(renderer.field_147853_m < 1.0D ? blockBrightness : block.func_149677_c(renderer.field_147845_a, x, y, z + 1));
            tessellator.func_78386_a(tRed, tGreen, tBlue);
            renderer.func_147734_d(block, (double) x, (double) y, (double) z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 3));
        }

        this.drawZSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, onePixel, blockBrightness);
        this.resetUVRotation(renderer);
        if (this.canConnectTo(world, x + 1, y, z)) {
            this.setUVRotationX(renderer);
            renderer.func_147782_a((double) (1.0F - onePixel), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            this.drawXSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x - 1, y, z)) {
            this.setUVRotationX(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, (double) onePixel, 1.0D, 1.0D);
            this.drawXSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
            this.resetUVRotation(renderer);
        }

        if (this.canConnectTo(world, x, y + 1, z)) {
            this.resetUVRotation(renderer);
            renderer.func_147782_a(0.0D, (double) (1.0F - onePixel), 0.0D, 1.0D, 1.0D, 1.0D);
            this.drawYSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
        }

        if (this.canConnectTo(world, x, y - 1, z)) {
            this.resetUVRotation(renderer);
            renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, (double) onePixel, 1.0D);
            this.drawYSides(block, x, y, z, renderer, metadata, zRed, xRed, zGreen, xGreen, zBlue, xBlue, 0.18900001F, blockBrightness);
        }

        return true;
    }

    private void drawXSides(Block block, int x, int y, int z, RenderBlocks renderer, int metadata, float zRed, float xRed, float zGreen, float xGreen, float zBlue, float xBlue, float onePixel, int l) {
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78380_c(l);
        tessellator.func_78386_a(zRed, zGreen, zBlue);
        tessellator.func_78372_c(0.0F, 0.0F, onePixel);
        renderer.func_147761_c(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, 0.0F, -onePixel);
        tessellator.func_78372_c(0.0F, 0.0F, -onePixel);
        renderer.func_147734_d(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, 0.0F, onePixel);
        tessellator.func_78386_a(xRed, xGreen, xBlue);
        tessellator.func_78372_c(0.0F, onePixel, 0.0F);
        renderer.func_147768_a(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, -onePixel, 0.0F);
        tessellator.func_78372_c(0.0F, -onePixel, 0.0F);
        renderer.func_147806_b(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, onePixel, 0.0F);
    }

    private void drawYSides(Block block, int x, int y, int z, RenderBlocks renderer, int metadata, float zRed, float xRed, float zGreen, float xGreen, float zBlue, float xBlue, float onePixel, int blockBrightness) {
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78380_c(blockBrightness);
        tessellator.func_78386_a(zRed, zGreen, zBlue);
        tessellator.func_78372_c(0.0F, 0.0F, onePixel);
        renderer.func_147761_c(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, 0.0F, -onePixel);
        tessellator.func_78372_c(0.0F, 0.0F, -onePixel);
        renderer.func_147734_d(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, 0.0F, onePixel);
        tessellator.func_78386_a(xRed, xGreen, xBlue);
        tessellator.func_78372_c(onePixel, 0.0F, 0.0F);
        renderer.func_147798_e(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(-onePixel, 0.0F, 0.0F);
        tessellator.func_78372_c(-onePixel, 0.0F, 0.0F);
        renderer.func_147764_f(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(onePixel, 0.0F, 0.0F);
    }

    private void drawZSides(Block block, int x, int y, int z, RenderBlocks renderer, int metadata, float zRed, float xRed, float zGreen, float xGreen, float zBlue, float xBlue, float onePixel, int blockBrightness) {
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78380_c(blockBrightness);
        tessellator.func_78386_a(xRed, xGreen, xBlue);
        tessellator.func_78372_c(onePixel, 0.0F, 0.0F);
        renderer.func_147798_e(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(-onePixel, 0.0F, 0.0F);
        tessellator.func_78372_c(-onePixel, 0.0F, 0.0F);
        renderer.func_147764_f(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(onePixel, 0.0F, 0.0F);
        tessellator.func_78386_a(zRed, zGreen, zBlue);
        tessellator.func_78372_c(0.0F, onePixel, 0.0F);
        renderer.func_147768_a(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, -onePixel, 0.0F);
        tessellator.func_78372_c(0.0F, -onePixel, 0.0F);
        renderer.func_147806_b(block, (double) x, (double) y, (double) z, this.getSideIcon(block, metadata));
        tessellator.func_78372_c(0.0F, onePixel, 0.0F);
    }

    private boolean canConnectTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);

        return block == TFBlocks.thorns || block == TFBlocks.burntThorns || block == TFBlocks.thornRose;
    }

    private void setUVRotationX(RenderBlocks renderer) {
        renderer.field_147875_q = 1;
        renderer.field_147873_r = 1;
        renderer.field_147867_u = 1;
        renderer.field_147865_v = 1;
    }

    private void setUVRotationZ(RenderBlocks renderer) {
        renderer.field_147871_s = 1;
        renderer.field_147869_t = 1;
    }

    private void resetUVRotation(RenderBlocks renderer) {
        renderer.field_147867_u = 0;
        renderer.field_147865_v = 0;
        renderer.field_147875_q = 0;
        renderer.field_147873_r = 0;
        renderer.field_147869_t = 0;
        renderer.field_147871_s = 0;
    }

    private IIcon getSideIcon(Block block, int metadata) {
        return block.func_149691_a(2, metadata & 3);
    }

    public static void renderInvBlock(RenderBlocks renderblocks, Block par1Block, int meta) {
        Tessellator tessellator = Tessellator.field_78398_a;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        renderInvBlock(renderblocks, par1Block, meta, tessellator);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        par1Block.func_149683_g();
    }

    protected static void renderInvBlock(RenderBlocks renderblocks, Block par1Block, int meta, Tessellator tessellator) {
        float onePixel = 0.1875F;

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
        renderblocks.func_147764_f(par1Block, (double) (-onePixel), 0.0D, 0.0D, par1Block.func_149691_a(2, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
        renderblocks.func_147798_e(par1Block, (double) onePixel, 0.0D, 0.0D, par1Block.func_149691_a(3, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
        renderblocks.func_147761_c(par1Block, 0.0D, 0.0D, (double) onePixel, par1Block.func_149691_a(4, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
        renderblocks.func_147734_d(par1Block, 0.0D, 0.0D, (double) (-onePixel), par1Block.func_149691_a(5, meta));
        tessellator.func_78381_a();
    }
}
