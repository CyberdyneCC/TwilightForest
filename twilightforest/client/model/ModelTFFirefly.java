package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFFirefly extends ModelBase {

    public ModelRenderer legs = new ModelRenderer(this, 0, 21);
    public ModelRenderer fatbody;
    public ModelRenderer skinnybody;
    public ModelRenderer glow;

    public ModelTFFirefly() {
        this.legs.func_78790_a(-4.0F, 7.9F, -5.0F, 8, 1, 10, 0.0F);
        this.fatbody = new ModelRenderer(this, 0, 11);
        this.fatbody.func_78790_a(-2.0F, 6.0F, -4.0F, 4, 2, 6, 0.0F);
        this.skinnybody = new ModelRenderer(this, 0, 0);
        this.skinnybody.func_78790_a(-1.0F, 7.0F, -5.0F, 2, 1, 8, 0.0F);
        this.glow = new ModelRenderer(this, 20, 0);
        this.glow.func_78790_a(-5.0F, 5.9F, -9.0F, 10, 0, 10, 0.0F);
    }

    public void render(float f5) {
        this.legs.func_78785_a(f5);
        this.fatbody.func_78785_a(f5);
        this.skinnybody.func_78785_a(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}
}
