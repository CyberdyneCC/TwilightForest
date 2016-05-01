package twilightforest.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.TFMazeMapData;
import twilightforest.item.ItemTFMazeMap;
import twilightforest.item.TFItems;

public class TFMazeMapRenderer implements IItemRenderer {

    private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");

    public TFMazeMapRenderer(GameSettings gameSettings, TextureManager textureManager) {}

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return (item.func_77973_b() == TFItems.mazeMap || item.func_77973_b() == TFItems.oreMap) && RenderItem.field_82407_g && type == ItemRenderType.ENTITY;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (RenderItem.field_82407_g) {
            EntityItem entity = (EntityItem) data[1];
            TFMazeMapData mapData = ((ItemTFMazeMap) TFItems.mazeMap).getMapData(item, entity.field_70170_p);

            if (mapData != null) {
                this.renderMapInFrame(item, RenderManager.field_78727_a, mapData);
            }
        }

    }

    private void renderMapInFrame(ItemStack item, RenderManager renderManager, TFMazeMapData mapData) {
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.0078125F, 0.0078125F, 0.0078125F);
        GL11.glTranslatef(-65.0F, -111.0F, -3.0F);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        renderManager.field_78724_e.func_110577_a(TFMazeMapRenderer.mapBackgroundTextures);
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78382_b();
        byte b0 = 7;

        tessellator.func_78374_a((double) (0 - b0), (double) (128 + b0), 0.0D, 0.0D, 1.0D);
        tessellator.func_78374_a((double) (128 + b0), (double) (128 + b0), 0.0D, 1.0D, 1.0D);
        tessellator.func_78374_a((double) (128 + b0), (double) (0 - b0), 0.0D, 1.0D, 0.0D);
        tessellator.func_78374_a((double) (0 - b0), (double) (0 - b0), 0.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.0F, 0.0F, -1.0F);
        Minecraft.func_71410_x().field_71460_t.func_147701_i().func_148250_a(mapData, false);
    }
}
