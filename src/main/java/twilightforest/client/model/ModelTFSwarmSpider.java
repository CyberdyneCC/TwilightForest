package twilightforest.client.model;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.entity.Entity;

public class ModelTFSwarmSpider extends ModelSpider {

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5 * 0.5F);
    }
}
