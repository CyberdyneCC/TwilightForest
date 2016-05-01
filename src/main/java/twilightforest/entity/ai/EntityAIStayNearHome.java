package twilightforest.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIStayNearHome extends EntityAIBase {

    private EntityCreature entity;
    private float speed;

    public EntityAIStayNearHome(EntityCreature entityTFYetiAlpha, float sp) {
        this.entity = entityTFYetiAlpha;
        this.speed = sp;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        boolean isOutOfRange = !this.entity.func_110173_bK();

        return isOutOfRange;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        if (this.entity.func_70092_e((double) this.entity.func_110172_bL().field_71574_a, (double) this.entity.func_110172_bL().field_71572_b, (double) this.entity.func_110172_bL().field_71573_c) > 256.0D) {
            Vec3 vec3 = RandomPositionGenerator.func_75464_a(this.entity, 14, 3, Vec3.func_72443_a((double) this.entity.func_110172_bL().field_71574_a + 0.5D, (double) this.entity.func_110172_bL().field_71572_b, (double) this.entity.func_110172_bL().field_71573_c + 0.5D));

            if (vec3 != null) {
                this.entity.func_70661_as().func_75492_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, (double) this.speed);
            }
        } else {
            this.entity.func_70661_as().func_75492_a((double) this.entity.func_110172_bL().field_71574_a + 0.5D, (double) this.entity.func_110172_bL().field_71572_b, (double) this.entity.func_110172_bL().field_71573_c + 0.5D, (double) this.speed);
        }

    }
}
