package twilightforest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import twilightforest.entity.passive.EntityTFTinyBird;

public class EntityAITFBirdFly extends EntityAIBase {

    private EntityTFTinyBird entity;

    public EntityAITFBirdFly(EntityTFTinyBird par1EntityCreature) {
        this.entity = par1EntityCreature;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        return !this.entity.isBirdLanded();
    }

    public boolean func_75253_b() {
        return !this.entity.isBirdLanded();
    }
}
