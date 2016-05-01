package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFLich;
import twilightforest.entity.boss.EntityTFLich;

public class RenderTFLich extends RenderBiped {

    public static EntityTFLich entityLich = null;
    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/twilightlich64.png");

    public RenderTFLich(ModelBiped modelbiped, float f) {
        super(modelbiped, f);
        this.func_77042_a(new ModelTFLich(true));
    }

    protected int func_77032_a(EntityLivingBase entity, int i, float f) {
        EntityTFLich lich = (EntityTFLich) entity;

        if (i == 2) {
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            if (lich.isShadowClone()) {
                float shadow = 0.33F;

                GL11.glColor4f(shadow, shadow, shadow, 0.8F);
                return 2;
            } else {
                if (lich.field_70173_aa > 0) {
                    BossStatus.func_82824_a(lich, false);
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                return 1;
            }
        } else {
            return 0;
        }
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFLich.textureLoc;
    }
}
