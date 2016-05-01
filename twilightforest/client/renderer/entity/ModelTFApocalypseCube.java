package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFApocalypseCube extends ModelQuadruped {

    public ModelTFApocalypseCube() {
        this(0.0F);
    }

    public ModelTFApocalypseCube(float fNumber) {
        super(6, fNumber);
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.field_78150_a = new ModelRenderer(this, 0, 0);
        this.field_78148_b = new ModelRenderer(this, 0, 0);
        this.field_78148_b.func_78789_a(-16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.field_78148_b.func_78793_a(0.0F, 0.0F, -2.0F);
        this.field_78149_c = new ModelRenderer(this, 0, 0);
        this.field_78149_c.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 8, 8);
        this.field_78149_c.func_78793_a(-6.0F, 16.0F, 9.0F);
        this.field_78146_d = new ModelRenderer(this, 0, 0);
        this.field_78146_d.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 8, 8);
        this.field_78146_d.func_78793_a(6.0F, 16.0F, 9.0F);
        this.field_78147_e = new ModelRenderer(this, 0, 0);
        this.field_78147_e.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 8, 8);
        this.field_78147_e.func_78793_a(-9.0F, 16.0F, -14.0F);
        this.field_78144_f = new ModelRenderer(this, 0, 0);
        this.field_78144_f.func_78789_a(-4.0F, 0.0F, -4.0F, 8, 8, 8);
        this.field_78144_f.func_78793_a(9.0F, 16.0F, -14.0F);
        this.field_78145_g = 4.0F;
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.field_78148_b.field_78795_f = 0.0F;
    }
}
