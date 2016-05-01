package twilightforest.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.boss.EntityTFSnowQueenIceShield;

public class RenderTFSnowQueenIceShield extends Render {

    private final RenderBlocks renderBlocks = new RenderBlocks();

    public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.doRender((EntityTFSnowQueenIceShield) entity, d0, d1, d2, f, f1);
    }

    public void doRender(EntityTFSnowQueenIceShield entity, double x, double y, double z, float p_147918_8_, float p_147918_9_) {
        World world = entity.field_70170_p;
        Block block = Blocks.field_150403_cj;
        int i = MathHelper.func_76128_c(entity.field_70165_t);
        int j = MathHelper.func_76128_c(entity.field_70163_u);
        int k = MathHelper.func_76128_c(entity.field_70161_v);

        if (block != null && block != world.func_147439_a(i, j, k)) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x, (float) y, (float) z);
            this.func_110777_b(entity);
            GL11.glDisable(2896);
            this.renderBlocks.func_147775_a(block);
            this.renderBlocks.func_147749_a(block, world, i, j, k, 0);
            GL11.glEnable(2896);
            GL11.glPopMatrix();
        }

    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return TextureMap.field_110575_b;
    }
}
