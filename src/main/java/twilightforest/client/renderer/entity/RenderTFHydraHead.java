package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.boss.EntityTFHydra;
import twilightforest.entity.boss.EntityTFHydraPart;
import twilightforest.entity.boss.HydraHeadContainer;

public class RenderTFHydraHead extends RenderLiving {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/hydra4.png");

    public RenderTFHydraHead(ModelBase modelbase, float f) {
        super(modelbase, f);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        HydraHeadContainer headCon = this.getHeadObject(entity);

        if (headCon != null) {
            if (headCon.shouldRenderHead()) {
                super.func_76986_a(entity, d, d1, d2, f, f1);
            }

            if (headCon.shouldRenderNeck(0)) {
                RenderManager.field_78727_a.func_147937_a(headCon.necka, f1);
            }

            if (headCon.shouldRenderNeck(1)) {
                RenderManager.field_78727_a.func_147937_a(headCon.neckb, f1);
            }

            if (headCon.shouldRenderNeck(2)) {
                RenderManager.field_78727_a.func_147937_a(headCon.neckc, f1);
            }

            if (headCon.shouldRenderNeck(3)) {
                RenderManager.field_78727_a.func_147937_a(headCon.neckd, f1);
            }

            if (headCon.shouldRenderNeck(4)) {
                RenderManager.field_78727_a.func_147937_a(headCon.necke, f1);
            }
        } else {
            super.func_76986_a(entity, d, d1, d2, f, f1);
        }

    }

    private HydraHeadContainer getHeadObject(Entity entity) {
        EntityTFHydra hydra = ((EntityTFHydraPart) entity).hydraObj;

        if (hydra != null) {
            for (int i = 0; i < hydra.numHeads; ++i) {
                if (hydra.hc[i].headEntity == entity) {
                    return hydra.hc[i];
                }
            }
        }

        return null;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFHydraHead.textureLoc;
    }
}
