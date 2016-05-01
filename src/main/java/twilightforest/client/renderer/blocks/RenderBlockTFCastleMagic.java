package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import twilightforest.block.BlockTFCastleMagic;

public class RenderBlockTFCastleMagic implements ISimpleBlockRenderingHandler {

    private int renderID;

    public RenderBlockTFCastleMagic(int castleMagicBlockRenderID) {
        this.renderID = castleMagicBlockRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        renderInvBlock(renderer, block, metadata);
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
        float pixel = 0.0625F;
        IIcon icon = BlockTFCastleMagic.getMagicIconFor(meta, 0, 0);
        int color = BlockTFCastleMagic.getMagicColorFor(meta);
        float red = (float) (color >> 16 & 255) / 255.0F;
        float grn = (float) (color >> 8 & 255) / 255.0F;
        float blu = (float) (color & 255) / 255.0F;

        renderblocks.field_147863_w = false;
        GL11.glDisable(2896);
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147768_a(par1Block, 0.0D, (double) (-pixel), 0.0D, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147806_b(par1Block, 0.0D, (double) pixel, 0.0D, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147764_f(par1Block, (double) pixel, 0.0D, 0.0D, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147798_e(par1Block, (double) (-pixel), 0.0D, 0.0D, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147761_c(par1Block, 0.0D, 0.0D, (double) (-pixel), icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderblocks.func_147734_d(par1Block, 0.0D, 0.0D, (double) pixel, icon);
        tessellator.func_78381_a();
        GL11.glEnable(2896);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.func_147771_a();
        renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderer.func_147784_q(block, x, y, z);
        renderer.field_147840_d = BlockTFCastleMagic.getMagicIconFor(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        int color = BlockTFCastleMagic.getMagicColorFor(meta);
        float red = (float) (color >> 16 & 255) / 255.0F;
        float grn = (float) (color >> 8 & 255) / 255.0F;
        float blu = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78380_c(15728880);
        tessellator.func_78386_a(red, grn, blu);
        renderer.field_147863_w = false;
        float pixel = 0.0625F;

        if (renderer.field_147837_f || block.func_149646_a(world, x, y - 1, z, 0)) {
            renderer.func_147768_a(block, (double) x, (double) y - (double) pixel, (double) z, renderer.field_147840_d);
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x, y + 1, z, 1)) {
            renderer.func_147806_b(block, (double) x, (double) y + (double) pixel, (double) z, renderer.field_147840_d);
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z - 1, 2)) {
            renderer.func_147761_c(block, (double) x, (double) y, (double) z - (double) pixel, renderer.field_147840_d);
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x, y, z + 1, 3)) {
            renderer.func_147734_d(block, (double) x, (double) y, (double) z + (double) pixel, renderer.field_147840_d);
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x - 1, y, z, 4)) {
            renderer.func_147798_e(block, (double) x - (double) pixel, (double) y, (double) z, renderer.field_147840_d);
        }

        if (renderer.field_147837_f || block.func_149646_a(world, x + 1, y, z, 5)) {
            renderer.func_147764_f(block, (double) x + (double) pixel, (double) y, (double) z, renderer.field_147840_d);
        }

        renderer.func_147771_a();
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return this.renderID;
    }
}
