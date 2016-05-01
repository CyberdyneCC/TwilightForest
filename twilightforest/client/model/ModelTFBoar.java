package twilightforest.client.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFBoar extends ModelQuadruped {

    public ModelTFBoar() {
        super(6, 0.0F);
        this.field_78145_g = 4.0F;
        this.field_78150_a = new ModelRenderer(this, 0, 0);
        this.field_78150_a.func_78790_a(-4.0F, -2.0F, -6.0F, 8, 7, 6, 0.0F);
        this.field_78150_a.func_78793_a(0.0F, 12.0F, -6.0F);
        this.field_78148_b = new ModelRenderer(this, 28, 10);
        this.field_78148_b.func_78790_a(-5.0F, -8.0F, -7.0F, 10, 14, 8, 0.0F);
        this.field_78148_b.func_78793_a(0.0F, 11.0F, 2.0F);
        this.field_78148_b.field_78795_f = 1.570796F;
        this.field_78149_c = new ModelRenderer(this, 0, 16);
        this.field_78149_c.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78149_c.func_78793_a(-3.0F, 18.0F, 7.0F);
        this.field_78146_d = new ModelRenderer(this, 0, 16);
        this.field_78146_d.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78146_d.func_78793_a(3.0F, 18.0F, 7.0F);
        this.field_78147_e = new ModelRenderer(this, 0, 16);
        this.field_78147_e.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78147_e.func_78793_a(-3.0F, 18.0F, -5.0F);
        this.field_78144_f = new ModelRenderer(this, 0, 16);
        this.field_78144_f.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.field_78144_f.func_78793_a(3.0F, 18.0F, -5.0F);
        this.field_78150_a.func_78784_a(28, 0).func_78790_a(-3.0F, 1.0F, -9.0F, 6, 4, 3, 0.0F);
        this.field_78150_a.func_78784_a(17, 17).func_78790_a(3.0F, 2.0F, -9.0F, 1, 2, 1, 0.0F);
        this.field_78150_a.func_78784_a(17, 17).func_78790_a(-4.0F, 2.0F, -9.0F, 1, 2, 1, 0.0F);
    }
}
