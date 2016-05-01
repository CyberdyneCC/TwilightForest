package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFRedcap extends ModelBiped {

    ModelRenderer goblinRightEar;
    ModelRenderer goblinLeftEar;

    public ModelTFRedcap() {
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78790_a(-3.4F, 1.0F, -4.0F, 7, 7, 7, 0.0F);
        this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78114_d = new ModelRenderer(this, 32, 0);
        this.field_78114_d.func_78790_a(-2.0F, 0.0F, -3.0F, 4, 5, 7, 0.0F);
        this.field_78114_d.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 12, 19);
        this.field_78115_e.func_78790_a(-4.0F, 6.0F, -2.0F, 8, 9, 4, 0.0F);
        this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 36, 17);
        this.field_78112_f.func_78790_a(-2.0F, -2.0F, -2.0F, 3, 12, 3, 0.0F);
        this.field_78112_f.func_78793_a(-5.0F, 8.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 36, 17);
        this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 3, 12, 3, 0.0F);
        this.field_78113_g.func_78793_a(5.0F, 8.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 20);
        this.field_78123_h.func_78790_a(-2.0F, 2.0F, -1.0F, 3, 9, 3, 0.0F);
        this.field_78123_h.func_78793_a(-2.0F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 20);
        this.field_78124_i.func_78790_a(-1.0F, 3.0F, -1.0F, 3, 9, 3, 0.0F);
        this.field_78124_i.func_78793_a(2.0F, 12.0F, 0.0F);
        this.goblinRightEar = new ModelRenderer(this, 48, 20);
        this.goblinRightEar.func_78790_a(3.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.goblinRightEar.func_78793_a(0.0F, 3.0F, 0.0F);
        this.goblinLeftEar = new ModelRenderer(this, 48, 24);
        this.goblinLeftEar.func_78790_a(-5.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.goblinLeftEar.func_78793_a(0.0F, 3.0F, 0.0F);
        this.goblinLeftEar.field_78809_i = true;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.goblinRightEar.field_78795_f = this.field_78116_c.field_78795_f;
        this.goblinRightEar.field_78796_g = this.field_78116_c.field_78796_g;
        this.goblinRightEar.field_78808_h = this.field_78116_c.field_78808_h;
        this.goblinLeftEar.field_78795_f = this.field_78116_c.field_78795_f;
        this.goblinLeftEar.field_78796_g = this.field_78116_c.field_78796_g;
        this.goblinLeftEar.field_78808_h = this.field_78116_c.field_78808_h;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.goblinRightEar.func_78785_a(f5);
        this.goblinLeftEar.func_78785_a(f5);
    }
}
