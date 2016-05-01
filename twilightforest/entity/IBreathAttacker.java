package twilightforest.entity;

import net.minecraft.entity.Entity;

public interface IBreathAttacker {

    boolean isBreathing();

    void setBreathing(boolean flag);

    void doBreathAttack(Entity entity);
}
