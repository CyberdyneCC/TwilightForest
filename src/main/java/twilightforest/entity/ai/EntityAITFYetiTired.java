package twilightforest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import twilightforest.entity.boss.EntityTFYetiAlpha;

public class EntityAITFYetiTired extends EntityAIBase {

    private EntityTFYetiAlpha yeti;
    private int tiredDuration;
    private int tiredTimer;

    public EntityAITFYetiTired(EntityTFYetiAlpha entityTFYetiAlpha, int i) {
        this.yeti = entityTFYetiAlpha;
        this.tiredDuration = i;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        return this.yeti.isTired();
    }

    public boolean func_75253_b() {
        return this.tiredTimer < this.tiredDuration;
    }

    public boolean func_75252_g() {
        return false;
    }

    public void func_75249_e() {
        this.tiredTimer = 0;
    }

    public void func_75251_c() {
        this.tiredTimer = 0;
        this.yeti.setTired(false);
    }

    public void func_75246_d() {
        ++this.tiredTimer;
    }
}
