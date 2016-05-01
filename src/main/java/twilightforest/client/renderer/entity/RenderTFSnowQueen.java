package twilightforest.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFSnowQueen;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class RenderTFSnowQueen extends RenderBiped {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/snowqueen.png");

    public RenderTFSnowQueen() {
        super(new ModelTFSnowQueen(), 0.625F);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFSnowQueen.textureLoc;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        float scale = 1.2F;

        GL11.glScalef(scale, scale, scale);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityTFSnowQueen queen = (EntityTFSnowQueen) entity;

        BossStatus.func_82824_a(queen, false);
        super.func_76986_a(entity, d, d1, d2, f, f1);

        for (int i = 0; i < queen.iceArray.length; ++i) {
            RenderManager.field_78727_a.func_147937_a(queen.iceArray[i], f1);
        }

    }
}
