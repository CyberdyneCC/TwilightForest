package twilightforest.client.renderer;

import java.util.Iterator;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapData.MapCoord;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.TFMagicMapData;
import twilightforest.item.ItemTFMagicMap;
import twilightforest.item.TFItems;

public class TFMagicMapRenderer implements IItemRenderer {

    private static final ResourceLocation vanillaMapIcons = new ResourceLocation("textures/map/map_icons.png");
    private static final ResourceLocation twilightMapIcons = new ResourceLocation("twilightforest:textures/gui/mapicons.png");
    private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
    private int[] intArray = new int[16384];
    private DynamicTexture bufferedImage = new DynamicTexture(128, 128);
    private final ResourceLocation textureLoc;

    public TFMagicMapRenderer(GameSettings par1GameSettings, TextureManager par2TextureManager) {
        this.textureLoc = par2TextureManager.func_110578_a("map", this.bufferedImage);
        this.intArray = this.bufferedImage.func_110565_c();

        for (int i = 0; i < this.intArray.length; ++i) {
            this.intArray[i] = 0;
        }

    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return item.func_77973_b() == TFItems.magicMap && (type == ItemRenderType.FIRST_PERSON_MAP || RenderItem.field_82407_g && type == ItemRenderType.ENTITY);
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.FIRST_PERSON_MAP) {
            EntityPlayer entity = (EntityPlayer) data[0];
            TextureManager mapData = (TextureManager) data[1];
            MapData mapData1 = (MapData) data[2];

            if (mapData1 != null && mapData1 instanceof TFMagicMapData) {
                this.renderMap(entity, mapData, (TFMagicMapData) mapData1);
            }
        } else if (RenderItem.field_82407_g) {
            EntityItem entity1 = (EntityItem) data[1];
            TFMagicMapData mapData2 = ((ItemTFMagicMap) TFItems.magicMap).getMapData(item, entity1.field_70170_p);

            if (mapData2 != null) {
                this.renderMapInFrame(item, RenderManager.field_78727_a, mapData2);
            }
        }

    }

    public void renderMap(EntityPlayer par1EntityPlayer, TextureManager par2TextureManager, TFMagicMapData par3MapData) {
        for (int i = 0; i < 16384; ++i) {
            int j = par3MapData.field_76198_e[i] & 255;

            if (j == 0) {
                this.intArray[i] = (i + i / 128 & 1) * 8 + 16 << 24;
            } else {
                int tesselator = j - 1;
                BiomeGenBase biomegenbase = BiomeGenBase.func_150565_n()[tesselator];

                if (biomegenbase != null) {
                    this.intArray[i] = -16777216 | biomegenbase.field_76790_z;
                }
            }
        }

        this.bufferedImage.func_110564_a();
        byte b0 = 0;
        byte b1 = 0;
        Tessellator tessellator = Tessellator.field_78398_a;
        float f = 0.0F;

        par2TextureManager.func_110577_a(this.textureLoc);
        GL11.glEnable(3042);
        GL11.glBlendFunc(1, 771);
        GL11.glDisable(3008);
        tessellator.func_78382_b();
        tessellator.func_78374_a((double) ((float) (b0 + 0) + f), (double) ((float) (b1 + 128) - f), -0.009999999776482582D, 0.0D, 1.0D);
        tessellator.func_78374_a((double) ((float) (b0 + 128) - f), (double) ((float) (b1 + 128) - f), -0.009999999776482582D, 1.0D, 1.0D);
        tessellator.func_78374_a((double) ((float) (b0 + 128) - f), (double) ((float) (b1 + 0) + f), -0.009999999776482582D, 1.0D, 0.0D);
        tessellator.func_78374_a((double) ((float) (b0 + 0) + f), (double) ((float) (b1 + 0) + f), -0.009999999776482582D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glEnable(3008);
        GL11.glDisable(3042);
        par2TextureManager.func_110577_a(TFMagicMapRenderer.vanillaMapIcons);
        Iterator iterator = par3MapData.field_76203_h.values().iterator();

        MapCoord mapCoord;
        float f1;
        float f2;
        float f3;
        float f4;

        while (iterator.hasNext()) {
            mapCoord = (MapCoord) iterator.next();
            GL11.glPushMatrix();
            GL11.glTranslatef((float) b0 + (float) mapCoord.field_76214_b / 2.0F + 64.0F, (float) b1 + (float) mapCoord.field_76215_c / 2.0F + 64.0F, -0.04F);
            GL11.glRotatef((float) (mapCoord.field_76212_d * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(4.0F, 4.0F, 3.0F);
            GL11.glTranslatef(-0.125F, 0.125F, 0.0F);
            f1 = (float) (mapCoord.field_76216_a % 4 + 0) / 4.0F;
            f2 = (float) (mapCoord.field_76216_a / 4 + 0) / 4.0F;
            f3 = (float) (mapCoord.field_76216_a % 4 + 1) / 4.0F;
            f4 = (float) (mapCoord.field_76216_a / 4 + 1) / 4.0F;
            tessellator.func_78382_b();
            tessellator.func_78374_a(-1.0D, 1.0D, 0.0D, (double) f1, (double) f2);
            tessellator.func_78374_a(1.0D, 1.0D, 0.0D, (double) f3, (double) f2);
            tessellator.func_78374_a(1.0D, -1.0D, 0.0D, (double) f3, (double) f4);
            tessellator.func_78374_a(-1.0D, -1.0D, 0.0D, (double) f1, (double) f4);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }

        par2TextureManager.func_110577_a(TFMagicMapRenderer.twilightMapIcons);
        iterator = par3MapData.featuresVisibleOnMap.iterator();

        while (iterator.hasNext()) {
            mapCoord = (MapCoord) iterator.next();
            GL11.glPushMatrix();
            GL11.glTranslatef((float) b0 + (float) mapCoord.field_76214_b / 2.0F + 64.0F, (float) b1 + (float) mapCoord.field_76215_c / 2.0F + 64.0F, -0.02F);
            GL11.glRotatef((float) (mapCoord.field_76212_d * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(4.0F, 4.0F, 3.0F);
            GL11.glTranslatef(-0.125F, 0.125F, 0.0F);
            f1 = (float) (mapCoord.field_76216_a % 8 + 0) / 8.0F;
            f2 = (float) (mapCoord.field_76216_a / 8 + 0) / 8.0F;
            f3 = (float) (mapCoord.field_76216_a % 8 + 1) / 8.0F;
            f4 = (float) (mapCoord.field_76216_a / 8 + 1) / 8.0F;
            tessellator.func_78382_b();
            tessellator.func_78374_a(-1.0D, 1.0D, 0.0D, (double) f1, (double) f2);
            tessellator.func_78374_a(1.0D, 1.0D, 0.0D, (double) f3, (double) f2);
            tessellator.func_78374_a(1.0D, -1.0D, 0.0D, (double) f3, (double) f4);
            tessellator.func_78374_a(-1.0D, -1.0D, 0.0D, (double) f1, (double) f4);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }

    }

    private void renderMapInFrame(ItemStack item, RenderManager renderManager, TFMagicMapData mapData) {
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(0.0078125F, 0.0078125F, 0.0078125F);
        GL11.glTranslatef(-65.0F, -111.0F, -3.0F);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        renderManager.field_78724_e.func_110577_a(TFMagicMapRenderer.mapBackgroundTextures);
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78382_b();
        byte b0 = 7;

        tessellator.func_78374_a((double) (0 - b0), (double) (128 + b0), 0.0D, 0.0D, 1.0D);
        tessellator.func_78374_a((double) (128 + b0), (double) (128 + b0), 0.0D, 1.0D, 1.0D);
        tessellator.func_78374_a((double) (128 + b0), (double) (0 - b0), 0.0D, 1.0D, 0.0D);
        tessellator.func_78374_a((double) (0 - b0), (double) (0 - b0), 0.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glTranslatef(0.0F, 0.0F, -1.0F);
        this.renderMap((EntityPlayer) null, renderManager.field_78724_e, mapData);
    }
}
