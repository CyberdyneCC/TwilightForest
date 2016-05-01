package twilightforest.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderTFKobold extends RenderTFBiped {

    public RenderTFKobold(ModelBiped modelBiped, float scale, String textureName) {
        super(modelBiped, scale, textureName);
    }

    protected void func_77029_c(EntityLiving p_77029_1_, float p_77029_2_) {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack itemstack = p_77029_1_.func_70694_bm();
        ItemStack itemstack1 = p_77029_1_.func_130225_q(3);

        if (itemstack != null && itemstack.func_77973_b() != null) {
            Item item = itemstack.func_77973_b();

            GL11.glPushMatrix();
            this.field_77071_a.field_78112_f.func_78794_c(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, ItemRenderType.EQUIPPED);
            boolean is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(ItemRenderType.EQUIPPED, itemstack, ItemRendererHelper.BLOCK_3D);
            float f1;

            if (item instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a(Block.func_149634_a(item).func_149645_b()))) {
                f1 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f1 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f1, -f1, f1);
            } else if (item == Items.field_151031_f) {
                f1 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (item.func_77662_d()) {
                f1 = 0.625F;
                if (item.func_77629_n_()) {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                this.func_82422_c();
                GL11.glScalef(f1, -f1, f1);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                f1 = 0.375F;
                GL11.glTranslatef(0.25F, 0.0F, -0.1875F);
                this.func_82422_c();
                GL11.glScalef(f1, f1, f1);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f2;
            int i;
            float f5;

            if (itemstack.func_77973_b().func_77623_v()) {
                for (i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++i) {
                    int f4 = itemstack.func_77973_b().func_82790_a(itemstack, i);

                    f5 = (float) (f4 >> 16 & 255) / 255.0F;
                    f2 = (float) (f4 >> 8 & 255) / 255.0F;
                    float f3 = (float) (f4 & 255) / 255.0F;

                    GL11.glColor4f(f5, f2, f3, 1.0F);
                    this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, i);
                }
            } else {
                i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f = (float) (i >> 16 & 255) / 255.0F;

                f5 = (float) (i >> 8 & 255) / 255.0F;
                f2 = (float) (i & 255) / 255.0F;
                GL11.glColor4f(f, f5, f2, 1.0F);
                this.field_76990_c.field_78721_f.func_78443_a(p_77029_1_, itemstack, 0);
            }

            GL11.glPopMatrix();
        }

    }

    protected void func_82422_c() {
        GL11.glTranslatef(0.0F, 0.01875F, 0.0F);
    }
}
