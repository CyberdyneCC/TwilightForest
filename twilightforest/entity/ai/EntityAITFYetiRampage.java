package twilightforest.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import twilightforest.entity.boss.EntityTFYetiAlpha;

public class EntityAITFYetiRampage extends EntityAIBase {

    private EntityTFYetiAlpha yeti;
    private int currentTimeOut;
    private int currentDuration;
    private int maxTantrumTimeOut;
    private int tantrumDuration;

    public EntityAITFYetiRampage(EntityTFYetiAlpha entityTFYetiAlpha, int timeout, int duration) {
        this.yeti = entityTFYetiAlpha;
        this.currentTimeOut = timeout;
        this.maxTantrumTimeOut = timeout;
        this.tantrumDuration = duration;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        if (this.yeti.func_70638_az() != null && this.yeti.canRampage()) {
            --this.currentTimeOut;
        }

        return this.currentTimeOut <= 0;
    }

    public void func_75249_e() {
        this.currentDuration = this.tantrumDuration;
        this.yeti.setRampaging(true);
    }

    public boolean func_75253_b() {
        return this.currentDuration > 0;
    }

    public void func_75246_d() {
        --this.currentDuration;
        if (this.yeti.func_70638_az() != null) {
            this.yeti.func_70671_ap().func_75651_a(this.yeti.func_70638_az(), 10.0F, (float) this.yeti.func_70646_bf());
        }

        if (this.yeti.field_70122_E) {
            this.yeti.field_70159_w = 0.0D;
            this.yeti.field_70179_y = 0.0D;
            this.yeti.field_70181_x = 0.4000000059604645D;
        }

        this.yeti.destroyBlocksInAABB(this.yeti.field_70121_D.func_72314_b(1.0D, 2.0D, 1.0D).func_72317_d(0.0D, 2.0D, 0.0D));
        if (this.currentDuration % 20 == 0) {
            this.yeti.makeRandomBlockFall();
        }

        if (this.currentDuration % 40 == 0) {
            this.yeti.makeBlockAboveTargetFall();
        }

        if (this.currentDuration < 40 && this.currentDuration % 10 == 0) {
            this.yeti.makeNearbyBlockFall();
        }

    }

    public void func_75251_c() {
        this.currentTimeOut = this.maxTantrumTimeOut;
        this.yeti.setRampaging(false);
        this.yeti.setTired(true);
    }
}
