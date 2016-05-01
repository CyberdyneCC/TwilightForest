package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFMinotaur extends ModelBiped {

    public ModelRenderer righthorn1 = new ModelRenderer(this, 24, 0);
    public ModelRenderer righthorn2;
    public ModelRenderer lefthorn1;
    public ModelRenderer lefthorn2;
    ModelRenderer snout;

    public ModelTFMinotaur() {
        this.righthorn1.func_78789_a(-5.5F, -1.5F, -1.5F, 5, 3, 3);
        this.righthorn1.func_78793_a(-2.5F, -6.5F, 0.0F);
        this.righthorn1.field_78796_g = -0.43633235F;
        this.righthorn1.field_78808_h = 0.17453294F;
        this.righthorn2 = new ModelRenderer(this, 40, 0);
        this.righthorn2.func_78789_a(-3.5F, -1.0F, -1.0F, 3, 2, 2);
        this.righthorn2.func_78793_a(-4.5F, 0.0F, 0.0F);
        this.righthorn2.field_78796_g = -0.2617994F;
        this.righthorn2.field_78808_h = 0.7853982F;
        this.righthorn1.func_78792_a(this.righthorn2);
        this.lefthorn1 = new ModelRenderer(this, 24, 0);
        this.lefthorn1.field_78809_i = true;
        this.lefthorn1.func_78789_a(0.5F, -1.5F, -1.5F, 5, 3, 3);
        this.lefthorn1.func_78793_a(2.5F, -6.5F, 0.0F);
        this.lefthorn1.field_78796_g = 0.43633235F;
        this.lefthorn1.field_78808_h = -0.17453294F;
        this.lefthorn2 = new ModelRenderer(this, 40, 0);
        this.lefthorn2.func_78789_a(0.5F, -1.0F, -1.0F, 3, 2, 2);
        this.lefthorn2.func_78793_a(4.5F, 0.0F, 0.0F);
        this.lefthorn2.field_78796_g = 0.2617994F;
        this.lefthorn2.field_78808_h = -0.7853982F;
        this.lefthorn1.func_78792_a(this.lefthorn2);
        this.field_78116_c.func_78792_a(this.righthorn1);
        this.field_78116_c.func_78792_a(this.lefthorn1);
        this.snout = new ModelRenderer(this, 9, 12);
        this.snout.func_78789_a(-2.0F, -1.0F, -1.0F, 4, 3, 1);
        this.snout.func_78793_a(0.0F, -2.0F, -4.0F);
        this.field_78116_c.func_78792_a(this.snout);
        this.field_78114_d = new ModelRenderer(this, 0, 0);
    }
}
