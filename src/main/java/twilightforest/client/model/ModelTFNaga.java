package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import twilightforest.entity.boss.EntityTFNaga;
import twilightforest.entity.boss.EntityTFNagaSegment;

public class ModelTFNaga extends ModelBase {

    public ModelRenderer head = new ModelRenderer(this, 0, 0);
    public ModelRenderer body;

    public ModelTFNaga() {
        this.head.func_78790_a(-8.0F, -12.0F, -8.0F, 16, 16, 16, 0.0F);
        this.head.func_78793_a(0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.func_78790_a(-8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F);
        this.body.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        if (entity instanceof EntityTFNaga) {
            this.head.func_78785_a(f5 * 2.0F);
        } else if (entity instanceof EntityTFNagaSegment) {
            this.body.func_78785_a(f5 * 2.0F);
        } else {
            this.head.func_78785_a(f5 * 2.0F);
        }

    }
}
