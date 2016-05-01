package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFSkeletonDruid extends ModelBiped {

    public ModelRenderer dress;

    public ModelTFSkeletonDruid() {
        float f = 0.0F;

        this.field_78115_e = new ModelRenderer(this, 8, 16);
        this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, f);
        this.field_78115_e.func_78793_a(0.0F, 0.0F + f, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 0, 16);
        this.field_78112_f.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
        this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 0, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0F, -2.0F, -1.0F, 2, 12, 2, f);
        this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 16);
        this.field_78123_h.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
        this.field_78123_h.func_78793_a(-2.0F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 12, 2);
        this.field_78124_i.func_78793_a(2.0F, 12.0F, 0.0F);
        this.dress = new ModelRenderer(this, 32, 16);
        this.dress.func_78790_a(-4.0F, 12.0F, -2.0F, 8, 12, 4, f);
        this.dress.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.dress.func_78785_a(f5);
    }
}
