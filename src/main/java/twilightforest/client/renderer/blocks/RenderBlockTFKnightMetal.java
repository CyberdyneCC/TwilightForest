package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderBlockTFKnightMetal implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFKnightMetal(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        renderInvJar(renderer, block, metadata);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return renderSpikeBlock(renderer, world, x, y, z, block);
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return this.renderID;
    }

    public static boolean renderSpikeBlock(RenderBlocks renderblocks, IBlockAccess world, int x, int y, int z, Block block) {
        float p = 0.0625F;
        float a = 9.765625E-4F;
        float p4 = 0.25F - a;

        for (int rx = 0; rx < 3; ++rx) {
            for (int ry = 0; ry < 3; ++ry) {
                for (int rz = 0; rz < 3; ++rz) {
                    renderblocks.func_147782_a((double) ((float) rx * 6.0F * p + a), (double) ((float) ry * 6.0F * p + a), (double) ((float) rz * 6.0F * p + a), (double) ((float) rx * 6.0F * p + p4), (double) ((float) ry * 6.0F * p + p4), (double) ((float) rz * 6.0F * p + p4));
                    renderblocks.func_147784_q(block, x, y, z);
                }
            }
        }

        renderblocks.func_147782_a((double) p, (double) p, (double) p, (double) (15.0F * p), (double) (15.0F * p), (double) (15.0F * p));
        renderblocks.func_147784_q(block, x, y, z);
        block.func_149683_g();
        return true;
    }

    public static void renderInvJar(RenderBlocks renderblocks, Block par1Block, int meta) {
        Tessellator tessellator = Tessellator.field_78398_a;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float p = 0.0625F;
        float a = 9.765625E-4F;
        float p4 = 0.25F - a;

        for (int rx = 0; rx < 3; ++rx) {
            for (int ry = 0; ry < 3; ++ry) {
                for (int rz = 0; rz < 3; ++rz) {
                    renderblocks.func_147782_a((double) ((float) rx * 6.0F * p + a), (double) ((float) ry * 6.0F * p + a), (double) ((float) rz * 6.0F * p + a), (double) ((float) rx * 6.0F * p + p4), (double) ((float) ry * 6.0F * p + p4), (double) ((float) rz * 6.0F * p + p4));
                    renderInvBlock(renderblocks, par1Block, meta, tessellator);
                }
            }
        }

        renderblocks.func_147782_a((double) p, (double) p, (double) p, (double) (15.0F * p), (double) (15.0F * p), (double) (15.0F * p));
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
    }
}
