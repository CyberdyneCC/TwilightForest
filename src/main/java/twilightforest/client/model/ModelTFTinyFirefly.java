package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFTinyFirefly extends ModelBase {

    public ModelRenderer glow1 = new ModelRenderer(this, 20, 0);

    public ModelTFTinyFirefly() {
        this.glow1.func_78790_a(-5.0F, -5.0F, 0.0F, 10, 10, 0, 0.0F);
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5) {
        this.glow1.func_78785_a(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
}
