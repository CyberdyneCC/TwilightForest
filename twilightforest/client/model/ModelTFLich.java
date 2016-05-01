package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import twilightforest.entity.boss.EntityTFLich;

public class ModelTFLich extends ModelBiped {

    ModelRenderer collar;
    ModelRenderer cloak;
    ModelRenderer shieldBelt;
    boolean renderPass;

    public ModelTFLich() {
        this.renderPass = false;
        this.renderPass = false;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.field_78115_e = new ModelRenderer(this, 8, 16);
        this.field_78115_e.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 24, 4);
        this.field_78115_e.func_78793_a(0.0F, -4.0F, 0.0F);
        this.field_78115_e.func_78787_b(64, 64);
        this.field_78112_f = new ModelRenderer(this, 0, 16);
        this.field_78112_f.func_78789_a(-2.0F, -2.0F, -1.0F, 2, 12, 2);
        this.field_78112_f.func_78787_b(64, 64);
        this.field_78112_f.func_78793_a(-5.0F, -2.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 0, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78789_a(-2.0F, -2.0F, -1.0F, 2, 12, 2);
        this.field_78113_g.func_78793_a(5.0F, -2.0F, 0.0F);
        this.field_78113_g.func_78787_b(64, 64);
        this.field_78114_d = new ModelRenderer(this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0F, -12.0F, -4.0F, 8, 8, 8, 0.5F);
        this.field_78114_d.func_78793_a(0.0F, -4.0F, 0.0F);
        this.field_78114_d.func_78787_b(64, 64);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.field_78116_c.func_78793_a(0.0F, -4.0F, 0.0F);
        this.field_78116_c.func_78787_b(64, 64);
        this.field_78123_h = new ModelRenderer(this, 0, 16);
        this.field_78123_h.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
        this.field_78123_h.func_78793_a(-2.0F, 9.5F, 0.0F);
        this.field_78123_h.func_78787_b(64, 64);
        this.field_78124_i = new ModelRenderer(this, 0, 16);
        this.field_78124_i.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
        this.field_78124_i.func_78793_a(2.0F, 9.5F, 0.0F);
        this.field_78124_i.func_78787_b(64, 64);
        this.field_78124_i.field_78809_i = true;
        this.collar = new ModelRenderer(this, 32, 16);
        this.collar.func_78789_a(-6.0F, 0.0F, 0.0F, 12, 12, 1);
        this.collar.func_78793_a(0.0F, -3.0F, -1.0F);
        this.collar.func_78787_b(64, 64);
        this.setRotation(this.collar, 2.164208F, 0.0F, 0.0F);
        this.cloak = new ModelRenderer(this, 0, 44);
        this.cloak.func_78789_a(-6.0F, 0.0F, 0.0F, 12, 19, 1);
        this.cloak.func_78793_a(0.0F, -4.0F, 2.5F);
        this.cloak.func_78787_b(64, 64);
        this.setRotation(this.cloak, 0.0F, 0.0F, 0.0F);
        this.shieldBelt = new ModelRenderer(this);
        this.shieldBelt.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public ModelTFLich(boolean specialRenderModel) {
        this();
        this.renderPass = specialRenderModel;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        EntityTFLich lich = (EntityTFLich) entity;

        if (!this.renderPass) {
            if (!lich.isShadowClone()) {
                super.func_78088_a(entity, f, f1, f2, f3, f4, f5 * 1.125F);
                this.collar.func_78785_a(f5 * 1.125F);
                this.cloak.func_78785_a(f5 * 1.125F);
            }
        } else if (lich.isShadowClone()) {
            super.func_78088_a(entity, f, f1, f2, f3, f4, f5 * 1.125F);
        } else if (lich.getShieldStrength() > 0) {
            this.shieldBelt.func_78785_a(f5 * 1.125F);
        }

    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float time) {
        EntityTFLich lich = (EntityTFLich) par1EntityLiving;
        byte shields = lich.getShieldStrength();

        if (!lich.isShadowClone() && shields > 0) {
            if (this.shieldBelt.field_78805_m == null || this.shieldBelt.field_78805_m.size() != shields) {
                if (this.shieldBelt.field_78805_m != null) {
                    this.shieldBelt.field_78805_m.clear();
                }

                for (int i = 0; i < shields; ++i) {
                    Vec3 vec = Vec3.func_72443_a(11.0D, 0.0D, 0.0D);
                    float rotateY = (float) i * (360.0F / (float) shields) * 3.141593F / 180.0F;

                    vec.func_72442_b(rotateY);
                    ModelRenderer shield = new ModelRenderer(this, 26, 40);

                    shield.func_78789_a(0.5F, -6.0F, -6.0F, 1, 12, 12);
                    shield.func_78793_a((float) vec.field_72450_a, (float) vec.field_72448_b, (float) vec.field_72449_c);
                    shield.func_78787_b(64, 64);
                    shield.field_78796_g = rotateY;
                    this.shieldBelt.func_78792_a(shield);
                }
            }

            this.shieldBelt.field_78796_g = ((float) lich.field_70173_aa + time) / 5.0F;
            this.shieldBelt.field_78795_f = MathHelper.func_76126_a(((float) lich.field_70173_aa + time) / 5.0F) / 4.0F;
            this.shieldBelt.field_78808_h = MathHelper.func_76134_b(((float) lich.field_70173_aa + time) / 5.0F) / 4.0F;
        }

    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.field_78118_o = false;
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        float ogSin = MathHelper.func_76126_a(this.field_78095_p * 3.141593F);
        float otherSin = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.141593F);

        this.field_78112_f.field_78808_h = 0.0F;
        this.field_78113_g.field_78808_h = 0.5F;
        this.field_78112_f.field_78796_g = -(0.1F - ogSin * 0.6F);
        this.field_78113_g.field_78796_g = 0.1F - ogSin * 0.6F;
        this.field_78112_f.field_78795_f = -1.570796F;
        this.field_78113_g.field_78795_f = -3.141593F;
        this.field_78112_f.field_78795_f -= ogSin * 1.2F - otherSin * 0.4F;
        this.field_78113_g.field_78795_f -= ogSin * 1.2F - otherSin * 0.4F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(f2 * 0.26F) * 0.15F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(f2 * 0.26F) * 0.15F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(f2 * 0.167F) * 0.15F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(f2 * 0.167F) * 0.15F;
        this.field_78116_c.field_78797_d = -4.0F;
        this.field_78114_d.field_78797_d = -4.0F;
        this.field_78123_h.field_78797_d = 9.5F;
        this.field_78124_i.field_78797_d = 9.5F;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}
