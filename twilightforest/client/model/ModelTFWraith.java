package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTFWraith extends ModelBiped {

    public ModelRenderer dress;

    public ModelTFWraith() {
        float f = 0.0F;

        this.dress = new ModelRenderer(this, 40, 16);
        this.dress.func_78790_a(-4.0F, 12.0F, -2.0F, 8, 12, 4, f);
        this.dress.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.field_78116_c.func_78785_a(f5);
        this.field_78115_e.func_78785_a(f5);
        this.field_78112_f.func_78785_a(f5);
        this.field_78113_g.func_78785_a(f5);
        this.dress.func_78785_a(f5);
        this.field_78114_d.func_78785_a(f5);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        float f = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
        float f1 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);

        this.field_78112_f.field_78808_h = 0.0F;
        this.field_78113_g.field_78808_h = 0.0F;
        this.field_78112_f.field_78796_g = -(0.1F - f * 0.6F);
        this.field_78113_g.field_78796_g = 0.1F - f * 0.6F;
        this.field_78112_f.field_78795_f = -1.5707964F;
        this.field_78113_g.field_78795_f = -1.5707964F;
        this.field_78112_f.field_78795_f -= f * 1.2F - f1 * 0.4F;
        this.field_78113_g.field_78795_f -= f * 1.2F - f1 * 0.4F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
    }
}
