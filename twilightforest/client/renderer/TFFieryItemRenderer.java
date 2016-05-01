package twilightforest.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class TFFieryItemRenderer implements IItemRenderer {

    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private TextureManager texturemanager;

    public TFFieryItemRenderer(GameSettings gameSettings, TextureManager textureManager) {
        this.texturemanager = textureManager;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.ENTITY_BOBBING);
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        IIcon entityItem;

        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            entityItem = ((EntityLivingBase) data[1]).func_70620_b(item, 0);
            this.renderFieryItemEquipped(entityItem, item);
        } else if (type == ItemRenderType.EQUIPPED) {
            entityItem = ((EntityLivingBase) data[1]).func_70620_b(item, 0);
            this.renderFieryItemEquipped(entityItem, item);
        } else if (type == ItemRenderType.ENTITY) {
            EntityItem entityItem1 = (EntityItem) data[1];

            this.renderDroppedItem(entityItem1, item);
        }

    }

    private void renderFieryItemEquipped(IIcon iicon, ItemStack par2ItemStack) {
        byte par3 = 0;

        if (iicon == null) {
            GL11.glPopMatrix();
        } else {
            this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(par2ItemStack.func_94608_d()));
            TextureUtil.func_152777_a(false, false, 1.0F);
            Tessellator tessellator = Tessellator.field_78398_a;
            float f = iicon.func_94209_e();
            float f1 = iicon.func_94212_f();
            float f2 = iicon.func_94206_g();
            float f3 = iicon.func_94210_h();

            Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0D);
            ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, iicon.func_94211_a(), iicon.func_94216_b(), 0.0625F);
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 772);
            float f7 = (float) Math.sin((double) ((float) (Minecraft.func_71386_F() % 6000L) / 6000.0F * 2.0F * 3.14159F)) * 0.2F + 0.2F;
            float f8 = 0.4F;

            GL11.glColor4f(f8, f8, 0.0F, f7);
            ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, iicon.func_94211_a(), iicon.func_94216_b(), 0.0625F);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
            if (par2ItemStack.hasEffect(par3)) {
                GL11.glDepthFunc(514);
                GL11.glDisable(2896);
                this.texturemanager.func_110577_a(TFFieryItemRenderer.RES_ITEM_GLINT);
                GL11.glEnable(3042);
                OpenGlHelper.func_148821_a(768, 1, 1, 0);
                f7 = 0.76F;
                GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
                GL11.glMatrixMode(5890);
                GL11.glPushMatrix();
                f8 = 0.125F;
                GL11.glScalef(f8, f8, f8);
                float f9 = (float) (Minecraft.func_71386_F() % 3000L) / 3000.0F * 8.0F;

                GL11.glTranslatef(f9, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (float) (Minecraft.func_71386_F() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.func_78439_a(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(5888);
                GL11.glDisable(3042);
                GL11.glEnable(2896);
                GL11.glDepthFunc(515);
            }

            this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(par2ItemStack.func_94608_d()));
            TextureUtil.func_147945_b();
            Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0D);
        }
    }

    private void renderDroppedItem(EntityItem entityItem, ItemStack item) {
        Tessellator tessellator = Tessellator.field_78398_a;
        float f9 = 0.5F;
        float f10 = 0.25F;

        GL11.glPushMatrix();
        float f12 = 0.0625F;
        float f11 = 0.021875F;

        GL11.glTranslatef(-f9, -f10, -(f12 + f11));
        GL11.glTranslatef(0.0F, 0.0F, f12 + f11);
        this.texturemanager.func_110577_a(TextureMap.field_110576_c);
        IIcon par2Icon = item.func_77954_c();

        Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0D);
        ItemRenderer.func_78439_a(tessellator, par2Icon.func_94212_f(), par2Icon.func_94206_g(), par2Icon.func_94209_e(), par2Icon.func_94210_h(), par2Icon.func_94211_a(), par2Icon.func_94216_b(), f12);
        Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0D);
        GL11.glPopMatrix();
    }
}
