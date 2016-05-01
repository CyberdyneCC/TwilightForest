package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.boss.EntityTFNaga;

public class RenderTFNaga extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/nagahead.png");

    public RenderTFNaga(ModelBase modelbase, float f) {
        super(modelbase, f);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        super.func_76986_a(entity, d, d1, d2, f, f1);
        if (entity instanceof EntityTFNaga && ((EntityTFNaga) entity).func_70021_al() != null) {
            EntityTFNaga naga = (EntityTFNaga) entity;

            for (int i = 0; i < naga.func_70021_al().length; ++i) {
                if (!naga.func_70021_al()[i].field_70128_L) {
                    RenderManager.field_78727_a.func_147937_a(naga.func_70021_al()[i], f1);
                }
            }

            BossStatus.func_82824_a(naga, false);
        }

    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFNaga.textureLoc;
    }
}
