package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.boss.EntityTFHydra;

public class RenderTFHydra extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/hydra4.png");

    public RenderTFHydra(ModelBase modelbase, float f) {
        super(modelbase, f);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        super.func_76986_a(entity, d, d1, d2, f, f1);
        BossStatus.func_82824_a((EntityTFHydra) entity, false);
    }

    protected float func_77037_a(EntityLivingBase par1EntityLiving) {
        return 0.0F;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFHydra.textureLoc;
    }
}
