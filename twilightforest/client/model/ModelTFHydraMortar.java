package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFHydraMortar extends ModelBase {

    public ModelRenderer box;

    public ModelTFHydraMortar() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.box = new ModelRenderer(this, 0, 0);
        this.box.func_78790_a(-4.0F, 0.0F, -4.0F, 8, 8, 8, 0.0F);
        this.box.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void render(float f5) {
        this.box.func_78785_a(f5);
    }
}
