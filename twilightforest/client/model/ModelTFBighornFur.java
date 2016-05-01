package twilightforest.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSheep2;

public class ModelTFBighornFur extends ModelSheep2 {

    public ModelTFBighornFur() {
        this.field_78150_a = new ModelRenderer(this, 0, 0);
        this.field_78150_a.func_78790_a(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
        this.field_78150_a.func_78793_a(0.0F, 6.0F, -8.0F);
        this.field_78148_b = new ModelRenderer(this, 28, 8);
        this.field_78148_b.func_78790_a(-4.0F, -9.0F, -7.0F, 8, 15, 6, 0.5F);
        this.field_78148_b.func_78793_a(0.0F, 5.0F, 2.0F);
        float f = 0.4F;

        this.field_78149_c = new ModelRenderer(this, 0, 16);
        this.field_78149_c.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
        this.field_78149_c.func_78793_a(-3.0F, 12.0F, 7.0F);
        this.field_78146_d = new ModelRenderer(this, 0, 16);
        this.field_78146_d.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
        this.field_78146_d.func_78793_a(3.0F, 12.0F, 7.0F);
        this.field_78147_e = new ModelRenderer(this, 0, 16);
        this.field_78147_e.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
        this.field_78147_e.func_78793_a(-3.0F, 12.0F, -5.0F);
        this.field_78144_f = new ModelRenderer(this, 0, 16);
        this.field_78144_f.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 6, 4, f);
        this.field_78144_f.func_78793_a(3.0F, 12.0F, -5.0F);
    }
}
