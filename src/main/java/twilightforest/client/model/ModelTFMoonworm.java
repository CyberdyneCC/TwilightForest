package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.tileentity.TileEntityTFMoonworm;

public class ModelTFMoonworm extends ModelBase {

    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer head;

    public ModelTFMoonworm() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Shape1 = new ModelRenderer(this, 0, 4);
        this.Shape1.func_78789_a(-1.0F, -1.0F, -1.0F, 4, 2, 2);
        this.Shape1.func_78793_a(-1.0F, 7.0F, 3.0F);
        this.Shape2 = new ModelRenderer(this, 0, 8);
        this.Shape2.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 2, 4);
        this.Shape2.func_78793_a(3.0F, 7.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 14);
        this.Shape3.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.Shape3.func_78793_a(2.0F, 7.0F, -2.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78789_a(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        this.head.func_78793_a(-3.0F, 7.0F, 2.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.head.func_78785_a(f5);
    }

    public void render(float f5) {
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.head.func_78785_a(f5);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void setLivingAnimations(TileEntityTFMoonworm moonworm, float partialTime) {
        this.head.field_78797_d = 7.0F;
        this.Shape1.field_78797_d = 7.0F;
        this.Shape2.field_78797_d = 7.0F;
        this.Shape3.field_78797_d = 7.0F;
        if (moonworm.yawDelay == 0) {
            float time = (float) (moonworm.desiredYaw - moonworm.currentYaw) - partialTime;

            this.head.field_78797_d += Math.min(0.0F, MathHelper.func_76126_a(time / 2.0F));
            this.Shape1.field_78797_d += Math.min(0.0F, MathHelper.func_76126_a(time / 2.0F + 1.0F));
            this.Shape2.field_78797_d += Math.min(0.0F, MathHelper.func_76126_a(time / 2.0F + 2.0F));
            this.Shape3.field_78797_d += Math.min(0.0F, MathHelper.func_76126_a(time / 2.0F + 3.0F));
        }

    }
}
