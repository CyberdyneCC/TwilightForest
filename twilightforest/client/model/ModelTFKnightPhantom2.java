package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.boss.EntityTFKnightPhantom;

public class ModelTFKnightPhantom2 extends ModelBiped {

    public ModelTFKnightPhantom2() {
        this(0.0F);
    }

    public ModelTFKnightPhantom2(float par1) {
        super(par1, 0.0F, 64, 32);
        this.field_78112_f = new ModelRenderer(this, 40, 16);
        this.field_78112_f.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
        this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 40, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
        this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 16);
        this.field_78123_h.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
        this.field_78123_h.func_78793_a(-2.0F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
        this.field_78124_i.func_78793_a(2.0F, 12.0F, 0.0F);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        if (((EntityTFKnightPhantom) par1Entity).isChargingAtPlayer()) {
            super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        }

    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        this.field_78124_i.field_78795_f = 0.0F;
        this.field_78124_i.field_78796_g = 0.0F;
        this.field_78124_i.field_78808_h = 0.0F;
        this.field_78123_h.field_78795_f = 0.0F;
        this.field_78123_h.field_78796_g = 0.0F;
        this.field_78123_h.field_78808_h = 0.0F;
        this.field_78123_h.field_78795_f = 0.2F * MathHelper.func_76126_a(par3 * 0.3F) + 0.4F;
        this.field_78124_i.field_78795_f = 0.2F * MathHelper.func_76126_a(par3 * 0.3F) + 0.4F;
    }
}
