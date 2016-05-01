package twilightforest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import twilightforest.entity.EntityTFGoblinKnightLower;
import twilightforest.entity.EntityTFGoblinKnightUpper;

public class EntityAITFRiderSpearAttack extends EntityAIBase {

    private EntityTFGoblinKnightLower entity;

    public EntityAITFRiderSpearAttack(EntityTFGoblinKnightLower par1EntityCreature) {
        this.entity = par1EntityCreature;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        return this.isRiderDoingSpearAttack();
    }

    public boolean isRiderDoingSpearAttack() {
        if (this.entity.field_70153_n != null && this.entity.field_70153_n instanceof EntityTFGoblinKnightUpper) {
            int timer = ((EntityTFGoblinKnightUpper) this.entity.field_70153_n).heavySpearTimer;

            return timer > 0 && timer < 50;
        } else {
            return false;
        }
    }

    public boolean func_75253_b() {
        return this.isRiderDoingSpearAttack();
    }
}
