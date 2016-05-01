package twilightforest.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import twilightforest.block.BlockTFSlider;
import twilightforest.entity.EntityTFSlideBlock;

public class RenderTFSlideBlock extends Render {

    private final RenderBlocks renderBlocks = new RenderBlocks();

    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float time) {
        EntityTFSlideBlock sliderEntity = (EntityTFSlideBlock) entity;
        World world = entity.field_70170_p;
        Block block = sliderEntity.getBlock();
        int meta = sliderEntity.getMeta();
        int bx = MathHelper.func_76128_c(entity.field_70165_t);
        int by = MathHelper.func_76128_c(entity.field_70163_u);
        int bz = MathHelper.func_76128_c(entity.field_70161_v);

        if (block != null && block != world.func_147439_a(bx, by, bz)) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y + 0.5F, (float) z);
            if ((meta & 12) == 0) {
                GL11.glRotatef(((float) entity.field_70173_aa + time) * 60.0F, 0.0F, 1.0F, 0.0F);
            } else if ((meta & 12) == 4) {
                GL11.glRotatef(((float) entity.field_70173_aa + time) * 60.0F, 1.0F, 0.0F, 0.0F);
            } else if ((meta & 12) == 8) {
                GL11.glRotatef(((float) entity.field_70173_aa + time) * 60.0F, 0.0F, 0.0F, 1.0F);
            }

            this.func_110777_b(entity);
            GL11.glDisable(2896);
            ((BlockTFSlider) block).setBlockBoundsBasedOnMeta(meta);
            this.renderBlocks.func_147775_a(block);
            this.renderBlocks.func_147749_a(block, world, bx, by, bz, meta);
            GL11.glEnable(2896);
            GL11.glPopMatrix();
        }

    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return TextureMap.field_110575_b;
    }
}
