package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFGoblinChain extends ModelBase {

    ModelRenderer chain = new ModelRenderer(this, 56, 16);

    public ModelTFGoblinChain() {
        this.chain.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.chain.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.chain.func_78785_a(f5);
    }
}
