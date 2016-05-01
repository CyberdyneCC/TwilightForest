package twilightforest.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import twilightforest.block.BlockTFPlant;

public class RenderBlockTFPlants implements ISimpleBlockRenderingHandler {

    final int renderID;

    public RenderBlockTFPlants(int blockRenderID) {
        this.renderID = blockRenderID;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int meta = world.func_72805_g(x, y, z);

        if (meta == 3) {
            this.renderMossPatch(x, y, z, block, renderer);
        } else if (meta == 5) {
            this.renderCloverPatch(x, y, z, block, renderer);
        } else if (meta == 4) {
            this.renderMayapple(x, y, z, block, renderer);
        } else if (meta == 14) {
            renderer.func_147796_n(block, x, y, z);
        } else {
            renderer.func_147746_l(block, x, y, z);
        }

        return true;
    }

    private void renderMayapple(int x, int y, int z, Block block, RenderBlocks renderer) {
        renderer.func_147771_a();
        renderer.func_147782_a(0.25D, 0.375D, 0.25D, 0.8125D, 0.375D, 0.8125D);
        renderer.func_147784_q(block, x, y, z);
        renderer.field_147840_d = BlockTFPlant.mayappleSide;
        renderer.func_147782_a(0.5D, 0.0D, 0.5D, 0.5625D, 0.37437498569488525D, 0.5625D);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147771_a();
    }

    private void renderCloverPatch(int x, int y, int z, Block block, RenderBlocks renderer) {
        renderer.field_147855_j = renderer.field_147857_k;
        renderer.func_147784_q(block, x, y, z);
        renderer.field_147855_j = 0.0D;
        renderer.field_147857_k -= 0.009999999776482582D;
        renderer.field_147859_h += 0.0625D;
        renderer.field_147851_l += 0.0625D;
        renderer.field_147861_i -= 0.0625D;
        renderer.field_147853_m -= 0.0625D;
        renderer.func_147784_q(block, x, y, z);
    }

    private void renderMossPatch(int x, int y, int z, Block block, RenderBlocks renderer) {
        renderer.func_147784_q(block, x, y, z);
        double originalMaxX;
        long seed;
        int num0;
        int num1;
        int num2;
        int num3;

        if (renderer.field_147859_h > 0.0D) {
            originalMaxX = renderer.field_147853_m;
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 7L;
            num0 = (int) (seed >> 12 & 3L) + 1;
            num1 = (int) (seed >> 15 & 3L) + 1;
            num2 = (int) (seed >> 18 & 3L) + 1;
            num3 = (int) (seed >> 21 & 3L) + 1;
            renderer.field_147861_i = renderer.field_147859_h;
            renderer.field_147859_h -= 0.0625D;
            renderer.field_147851_l += (double) ((float) num0 / 16.0F);
            if (renderer.field_147853_m - (double) ((float) (num1 + num2 + num3) / 16.0F) > renderer.field_147851_l) {
                renderer.field_147853_m = renderer.field_147851_l + (double) ((float) num1 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
                renderer.field_147853_m = originalMaxX - (double) ((float) num2 / 16.0F);
                renderer.field_147851_l = renderer.field_147853_m - (double) ((float) num3 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
            } else {
                renderer.field_147853_m -= (double) ((float) num2 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
            }

            renderer.func_147775_a(block);
        }

        if (renderer.field_147861_i < 1.0D) {
            originalMaxX = renderer.field_147853_m;
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 17L;
            num0 = (int) (seed >> 12 & 3L) + 1;
            num1 = (int) (seed >> 15 & 3L) + 1;
            num2 = (int) (seed >> 18 & 3L) + 1;
            num3 = (int) (seed >> 21 & 3L) + 1;
            renderer.field_147859_h = renderer.field_147861_i;
            renderer.field_147861_i += 0.0625D;
            renderer.field_147851_l += (double) ((float) num0 / 16.0F);
            if (renderer.field_147853_m - (double) ((float) (num1 + num2 + num3) / 16.0F) > renderer.field_147851_l) {
                renderer.field_147853_m = renderer.field_147851_l + (double) ((float) num1 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
                renderer.field_147853_m = originalMaxX - (double) ((float) num2 / 16.0F);
                renderer.field_147851_l = renderer.field_147853_m - (double) ((float) num3 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
            } else {
                renderer.field_147853_m -= (double) ((float) num2 / 16.0F);
                renderer.func_147784_q(block, x, y, z);
            }

            renderer.func_147775_a(block);
        }

        if (renderer.field_147851_l > 0.0D) {
            originalMaxX = renderer.field_147861_i;
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 23L;
            num0 = (int) (seed >> 12 & 3L) + 1;
            num1 = (int) (seed >> 15 & 3L) + 1;
            num2 = (int) (seed >> 18 & 3L) + 1;
            num3 = (int) (seed >> 21 & 3L) + 1;
            renderer.field_147853_m = renderer.field_147851_l;
            renderer.field_147851_l -= 0.0625D;
            renderer.field_147859_h += (double) ((float) num0 / 16.0F);
            renderer.field_147861_i = renderer.field_147859_h + (double) ((float) num1 / 16.0F);
            renderer.func_147784_q(block, x, y, z);
            renderer.field_147861_i = originalMaxX - (double) ((float) num2 / 16.0F);
            renderer.field_147859_h = renderer.field_147861_i - (double) ((float) num3 / 16.0F);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147775_a(block);
        }

        if (renderer.field_147853_m < 1.0D) {
            originalMaxX = renderer.field_147861_i;
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 11L;
            num0 = (int) (seed >> 12 & 3L) + 1;
            num1 = (int) (seed >> 15 & 3L) + 1;
            num2 = (int) (seed >> 18 & 3L) + 1;
            num3 = (int) (seed >> 21 & 3L) + 1;
            renderer.field_147851_l = renderer.field_147853_m;
            renderer.field_147853_m += 0.0625D;
            renderer.field_147859_h += (double) ((float) num0 / 16.0F);
            renderer.field_147861_i = renderer.field_147859_h + (double) ((float) num1 / 16.0F);
            renderer.func_147784_q(block, x, y, z);
            renderer.field_147861_i = originalMaxX - (double) ((float) num2 / 16.0F);
            renderer.field_147859_h = renderer.field_147861_i - (double) ((float) num3 / 16.0F);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147775_a(block);
        }

    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return this.renderID;
    }
}
