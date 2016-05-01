package twilightforest.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class TFGiantBlockRenderer implements IItemRenderer {

    public TFGiantBlockRenderer(GameSettings gameSettings, TextureManager textureManager) {}

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.ENTITY_BOBBING) || helper == ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        float scale;
        IIcon renderBlocks;
        RenderBlocks entityItem;

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            scale = 4.0F;
            GL11.glScalef(scale, scale, scale);
            GL11.glTranslatef(1.0F, 0.3F, 0.75F);
            GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
            renderBlocks = ((EntityLivingBase) data[1]).func_70620_b(item, 0);
            entityItem = (RenderBlocks) data[0];
            this.renderGiantItemEquipped(renderBlocks, entityItem, item);
        } else if (type == ItemRenderType.EQUIPPED) {
            scale = 4.0F;
            GL11.glScalef(scale, scale, scale);
            GL11.glTranslatef(0.25F, 0.3F, -0.5F);
            GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
            renderBlocks = ((EntityLivingBase) data[1]).func_70620_b(item, 0);
            entityItem = (RenderBlocks) data[0];
            this.renderGiantItemEquipped(renderBlocks, entityItem, item);
        } else if (type == ItemRenderType.ENTITY) {
            scale = 4.0F;
            GL11.glScalef(scale, scale, scale);
            GL11.glTranslatef(0.0F, 0.5F, 0.0F);
            RenderBlocks renderBlocks1 = (RenderBlocks) data[0];
            EntityItem entityItem1 = (EntityItem) data[1];

            this.renderDroppedItem(entityItem1, renderBlocks1, item);
        }

    }

    private void renderGiantItemEquipped(IIcon iicon, RenderBlocks renderBlocks, ItemStack itemStack) {
        Block block = Block.func_149634_a(itemStack.func_77973_b());

        renderBlocks.func_147800_a(block, itemStack.func_77960_j(), 1.0F);
    }

    private void renderDroppedItem(EntityItem entityItem, RenderBlocks renderBlocks, ItemStack itemStack) {
        Block block = Block.func_149634_a(itemStack.func_77973_b());

        renderBlocks.func_147800_a(block, itemStack.func_77960_j(), 1.0F);
    }
}
