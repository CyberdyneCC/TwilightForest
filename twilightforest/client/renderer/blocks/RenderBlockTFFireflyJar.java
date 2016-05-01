package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import twilightforest.block.BlockTFFireflyJar;

public class RenderBlockTFFireflyJar implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFFireflyJar(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        renderInvJar(renderer, block, metadata);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return renderJar(renderer, world, x, y, z, block);
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return this.renderID;
    }

    public static boolean renderJar(RenderBlocks renderblocks, IBlockAccess world, int x, int y, int z, Block block) {
        renderblocks.func_147771_a();
        renderblocks.func_147782_a(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
        renderblocks.func_147784_q(block, x, y, z);
        renderblocks.field_147840_d = BlockTFFireflyJar.jarCork;
        renderblocks.func_147782_a(0.25D, 0.75D, 0.25D, 0.75D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, x, y, z);
        renderblocks.func_147771_a();
        block.func_149683_g();
        return true;
    }

    public static void renderInvJar(RenderBlocks renderblocks, Block par1Block, int meta) {
        Tessellator tessellator = Tessellator.field_78398_a;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        renderblocks.func_147782_a(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.875D, 0.8125D);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
        renderblocks.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarTop);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
        renderblocks.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarTop);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
        renderblocks.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarSide);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
        renderblocks.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarSide);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
        renderblocks.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarSide);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
        renderblocks.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarSide);
        tessellator.func_78381_a();
        renderblocks.func_147782_a(0.25D, 0.75D, 0.25D, 0.75D, 1.0D, 0.75D);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
        renderblocks.func_147768_a(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
        renderblocks.func_147806_b(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
        renderblocks.func_147764_f(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
        renderblocks.func_147798_e(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
        renderblocks.func_147761_c(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
        renderblocks.func_147734_d(par1Block, 0.0D, 0.0D, 0.0D, BlockTFFireflyJar.jarCork);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        par1Block.func_149683_g();
    }
}
