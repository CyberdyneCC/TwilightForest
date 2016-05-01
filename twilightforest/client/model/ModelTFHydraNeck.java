package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFHydraNeck extends ModelBase {

    ModelRenderer neck;

    public ModelTFHydraNeck() {
        this.field_78090_t = 512;
        this.field_78089_u = 256;
        this.func_78085_a("neck.box", 128, 136);
        this.func_78085_a("neck.fin", 128, 200);
        this.neck = new ModelRenderer(this, "neck");
        this.neck.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.neck.func_78785_a(f5);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        this.neck.field_78796_g = f3 / 57.29578F;
        this.neck.field_78795_f = f4 / 57.29578F;
    }
}
