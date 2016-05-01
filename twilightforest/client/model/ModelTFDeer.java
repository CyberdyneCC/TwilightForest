package twilightforest.client.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFDeer extends ModelQuadruped {

    public ModelRenderer neck;

    public ModelTFDeer() {
        super(12, 0.0F);
        this.field_78145_g = 10.0F;
        this.field_78150_a = new ModelRenderer(this, 0, 5);
        this.field_78150_a.func_78790_a(-2.0F, -8.0F, -6.0F, 4, 6, 6, 0.0F);
        this.field_78150_a.func_78793_a(0.0F, 4.0F, -7.0F);
        this.field_78148_b = new ModelRenderer(this, 36, 6);
        this.field_78148_b.func_78790_a(-4.0F, -10.0F, -7.0F, 6, 18, 8, 0.0F);
        this.field_78148_b.func_78793_a(1.0F, 5.0F, 2.0F);
        this.field_78148_b.field_78795_f = 1.570796F;
        this.field_78149_c = new ModelRenderer(this, 0, 17);
        this.field_78149_c.func_78790_a(-3.0F, 0.0F, -2.0F, 2, 12, 3, 0.0F);
        this.field_78149_c.func_78793_a(0.0F, 12.0F, 9.0F);
        this.field_78146_d = new ModelRenderer(this, 0, 17);
        this.field_78146_d.func_78790_a(-1.0F, 0.0F, -2.0F, 2, 12, 3, 0.0F);
        this.field_78146_d.func_78793_a(2.0F, 12.0F, 9.0F);
        this.field_78147_e = new ModelRenderer(this, 0, 17);
        this.field_78147_e.func_78790_a(-3.0F, 0.0F, -3.0F, 2, 12, 3, 0.0F);
        this.field_78147_e.func_78793_a(0.0F, 12.0F, -5.0F);
        this.field_78144_f = new ModelRenderer(this, 0, 17);
        this.field_78144_f.func_78790_a(-1.0F, 0.0F, -3.0F, 2, 12, 3, 0.0F);
        this.field_78144_f.func_78793_a(2.0F, 12.0F, -5.0F);
        this.neck = new ModelRenderer(this, 10, 19);
        this.neck.func_78790_a(-2.5F, -8.0F, -11.0F, 3, 9, 4, 0.0F);
        this.neck.field_78795_f = 4.974188F;
        this.field_78148_b.func_78792_a(this.neck);
        this.field_78150_a.func_78784_a(52, 0).func_78790_a(-1.5F, -5.0F, -9.0F, 3, 3, 3, 0.0F);
        this.field_78150_a.func_78784_a(20, 0);
        this.field_78150_a.func_78790_a(-3.0F, -10.0F, -2.0F, 2, 2, 2, 0.0F);
        this.field_78150_a.func_78790_a(-3.0F, -10.0F, -2.0F, 2, 2, 2, 0.0F);
        this.field_78150_a.func_78790_a(-4.0F, -10.0F, -1.0F, 1, 1, 3, 0.0F);
        this.field_78150_a.func_78790_a(-5.0F, -11.0F, 1.0F, 1, 1, 5, 0.0F);
        this.field_78150_a.func_78790_a(-5.0F, -14.0F, 2.0F, 1, 4, 1, 0.0F);
        this.field_78150_a.func_78790_a(-6.0F, -17.0F, 3.0F, 1, 4, 1, 0.0F);
        this.field_78150_a.func_78790_a(-6.0F, -13.0F, 0.0F, 1, 1, 3, 0.0F);
        this.field_78150_a.func_78790_a(-6.0F, -14.0F, -3.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(-7.0F, -15.0F, -6.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(-6.0F, -16.0F, -9.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(-7.0F, -18.0F, -1.0F, 1, 5, 1, 0.0F);
        this.field_78150_a.func_78790_a(-6.0F, -19.0F, -6.0F, 1, 5, 1, 0.0F);
        this.field_78150_a.func_78790_a(1.0F, -10.0F, -2.0F, 2, 2, 2, 0.0F);
        this.field_78150_a.func_78790_a(3.0F, -10.0F, -1.0F, 1, 1, 3, 0.0F);
        this.field_78150_a.func_78790_a(4.0F, -11.0F, 1.0F, 1, 1, 5, 0.0F);
        this.field_78150_a.func_78790_a(4.0F, -14.0F, 2.0F, 1, 4, 1, 0.0F);
        this.field_78150_a.func_78790_a(5.0F, -17.0F, 3.0F, 1, 4, 1, 0.0F);
        this.field_78150_a.func_78790_a(5.0F, -13.0F, 0.0F, 1, 1, 3, 0.0F);
        this.field_78150_a.func_78790_a(5.0F, -14.0F, -3.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(6.0F, -15.0F, -6.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(5.0F, -16.0F, -9.0F, 1, 1, 4, 0.0F);
        this.field_78150_a.func_78790_a(6.0F, -18.0F, -1.0F, 1, 5, 1, 0.0F);
        this.field_78150_a.func_78790_a(5.0F, -19.0F, -6.0F, 1, 5, 1, 0.0F);
    }
}
