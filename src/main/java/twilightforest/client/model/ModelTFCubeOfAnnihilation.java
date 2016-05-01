package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFCubeOfAnnihilation extends ModelBase {

    public ModelRenderer box;
    public ModelRenderer boxX;
    public ModelRenderer boxY;
    public ModelRenderer boxZ;

    public ModelTFCubeOfAnnihilation() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.box = new ModelRenderer(this, 0, 0);
        this.box.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.box.func_78793_a(0.0F, 0.0F, 0.0F);
        this.boxX = new ModelRenderer(this, 0, 32);
        this.boxX.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.boxX.func_78793_a(0.0F, 0.0F, 0.0F);
        this.boxY = new ModelRenderer(this, 0, 32);
        this.boxY.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.boxY.func_78793_a(0.0F, 0.0F, 0.0F);
        this.boxZ = new ModelRenderer(this, 0, 32);
        this.boxZ.func_78790_a(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
        this.boxZ.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.box.func_78785_a(p_78088_7_);
        this.boxX.func_78785_a(p_78088_7_);
        this.boxY.func_78785_a(p_78088_7_);
        this.boxZ.func_78785_a(p_78088_7_);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float time, float f5, Entity entity) {
        this.boxX.field_78795_f = (float) Math.sin((double) ((float) entity.field_70173_aa + time)) / 5.0F;
        this.boxY.field_78796_g = (float) Math.sin((double) ((float) entity.field_70173_aa + time)) / 5.0F;
        this.boxZ.field_78808_h = (float) Math.sin((double) ((float) entity.field_70173_aa + time)) / 5.0F;
    }
}
