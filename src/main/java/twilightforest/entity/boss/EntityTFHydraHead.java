package twilightforest.entity.boss;

import net.minecraft.world.World;

public class EntityTFHydraHead extends EntityTFHydraPart {

    public EntityTFHydraHead(World world) {
        super(world);
        this.field_70158_ak = true;
    }

    public EntityTFHydraHead(EntityTFHydra hydra, String s, float f, float f1) {
        super(hydra, s, f, f1);
    }

    public int func_70646_bf() {
        return 500;
    }

    protected void func_70609_aI() {
        ++this.field_70725_aQ;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(19, Byte.valueOf((byte) 0));
    }

    public float getMouthOpen() {
        return (float) (this.field_70180_af.func_75683_a(18) & 255) / 255.0F;
    }

    public int getState() {
        return this.field_70180_af.func_75683_a(19) & 255;
    }

    public void setMouthOpen(float openness) {
        if (openness < 0.0F) {
            openness = 0.0F;
        }

        if (openness > 1.0F) {
            openness = 1.0F;
        }

        int openByte = Math.round(openness * 255.0F);

        openByte &= 255;
        this.field_70180_af.func_75692_b(18, Byte.valueOf((byte) openByte));
    }

    public void setState(int state) {
        state &= 255;
        this.field_70180_af.func_75692_b(19, Byte.valueOf((byte) state));
    }
}
