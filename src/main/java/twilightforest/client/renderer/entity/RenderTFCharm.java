package twilightforest.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.EntityTFCharmEffect;
import twilightforest.item.TFItems;

@SideOnly(Side.CLIENT)
public class RenderTFCharm extends Render {

    private IIcon itemIcon;

    public RenderTFCharm(IIcon par1) {
        this.itemIcon = par1;
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (par1Entity instanceof EntityTFCharmEffect) {
            EntityTFCharmEffect entitytfcharmeffect = (EntityTFCharmEffect) par1Entity;

            if (entitytfcharmeffect.getItemID() > 0) {
                this.itemIcon = TFItems.charmOfKeeping1.func_77617_a(0);
            }
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.func_110777_b(par1Entity);
        Tessellator tessellator = Tessellator.field_78398_a;

        this.func_77026_a(tessellator, this.itemIcon);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    private void func_77026_a(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.func_94209_e();
        float f1 = par2Icon.func_94212_f();
        float f2 = par2Icon.func_94206_g();
        float f3 = par2Icon.func_94210_h();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;

        GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
        par1Tessellator.func_78382_b();
        par1Tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
        par1Tessellator.func_78374_a((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
        par1Tessellator.func_78374_a((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
        par1Tessellator.func_78374_a((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
        par1Tessellator.func_78374_a((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
        par1Tessellator.func_78381_a();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return TextureMap.field_110576_c;
    }
}
