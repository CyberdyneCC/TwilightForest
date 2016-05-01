package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFThrownAxe extends RenderItem {

    Item myItem;

    public RenderTFThrownAxe(Item knightlyAxe) {
        this.myItem = knightlyAxe;
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        float spin = ((float) entity.field_70173_aa + par9) * -10.0F + 90.0F;

        this.doRenderItem((EntityItem) null, par2, par4, par6, par8, spin);
        GL11.glPopMatrix();
    }

    public void doRenderItem(EntityItem par1EntityItem, double x, double y, double z, float rotation, float spin) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glEnable('耺');
        GL11.glScalef(1.25F, 1.25F, 1.25F);
        IIcon icon1 = this.myItem.func_77617_a(0);

        this.renderDroppedItem(icon1, rotation, spin);
        GL11.glDisable('耺');
        GL11.glPopMatrix();
    }

    private void renderDroppedItem(IIcon par2Icon, float rotation, float spin) {
        Tessellator tessellator = Tessellator.field_78398_a;
        float f9 = 0.5F;
        float f10 = 0.25F;

        GL11.glPushMatrix();
        GL11.glRotatef(rotation + 270.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(spin, 0.0F, 0.0F, 1.0F);
        float f12 = 0.0625F;
        float f11 = 0.021875F;

        GL11.glTranslatef(-f9, -f10, -(f12 + f11));
        GL11.glTranslatef(0.0F, 0.0F, f12 + f11);
        this.func_110776_a(TextureMap.field_110576_c);
        ItemRenderer.func_78439_a(tessellator, par2Icon.func_94212_f(), par2Icon.func_94206_g(), par2Icon.func_94209_e(), par2Icon.func_94210_h(), par2Icon.func_94211_a(), par2Icon.func_94216_b(), f12);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return this.field_76990_c.field_78724_e.func_130087_a(this.myItem.func_94901_k());
    }

    public boolean shouldBob() {
        return false;
    }
}
