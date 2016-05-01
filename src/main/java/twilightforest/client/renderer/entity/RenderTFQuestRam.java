package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFQuestRam;
import twilightforest.entity.passive.EntityTFQuestRam;

public class RenderTFQuestRam extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/questram.png");
    private static final ResourceLocation textureLocLines = new ResourceLocation("twilightforest:textures/model/questram_lines.png");

    public RenderTFQuestRam() {
        super(new ModelTFQuestRam(), 1.0F);
        this.func_77042_a(new ModelTFQuestRam());
    }

    protected int setQuestRamLineBrightness(EntityTFQuestRam par1EntityQuestRam, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        } else {
            this.func_110776_a(RenderTFQuestRam.textureLocLines);
            float f = 1.0F;

            GL11.glEnable(3042);
            GL11.glDisable(3008);
            GL11.glBlendFunc(770, 1);
            GL11.glScalef(1.025F, 1.025F, 1.025F);
            char c0 = '\uf0f0';
            int i = c0 % 65536;
            int j = c0 / 65536;

            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float) i / 1.0F, (float) j / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
            return 1;
        }
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.setQuestRamLineBrightness((EntityTFQuestRam) par1EntityLiving, par2, par3);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFQuestRam.textureLoc;
    }
}
