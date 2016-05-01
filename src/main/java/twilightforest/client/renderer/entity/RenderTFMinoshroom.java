package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTFMinoshroom extends RenderBiped {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/minoshroomtaur.png");

    public RenderTFMinoshroom(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected void renderMooshroomEquippedItems(EntityLivingBase par1EntityLiving, float par2) {
        super.func_77029_c(par1EntityLiving, par2);
        this.func_110776_a(TextureMap.field_110575_b);
        GL11.glEnable(2884);
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, -1.0F, 1.0F);
        GL11.glTranslatef(0.2F, 0.375F, 0.5F);
        GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
        this.field_147909_c.func_147800_a(Blocks.field_150337_Q, 0, 1.0F);
        GL11.glTranslatef(0.1F, 0.0F, -0.6F);
        GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
        this.field_147909_c.func_147800_a(Blocks.field_150337_Q, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        ((ModelBiped) this.field_77045_g).field_78116_c.func_78794_c(0.0625F);
        GL11.glScalef(1.0F, -1.0F, 1.0F);
        GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
        this.field_147909_c.func_147800_a(Blocks.field_150337_Q, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glDisable(2884);
    }

    protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2) {
        this.renderMooshroomEquippedItems(par1EntityLiving, par2);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFMinoshroom.textureLoc;
    }
}
