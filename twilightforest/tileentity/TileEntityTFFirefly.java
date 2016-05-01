package twilightforest.tileentity;

import twilightforest.entity.passive.EntityTFTinyFirefly;

public class TileEntityTFFirefly extends TileEntityTFCritter {

    public int yawDelay;
    public int currentYaw;
    public int desiredYaw;
    public float glowIntensity;
    public boolean glowing;
    public int glowDelay;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.anyPlayerInRange() && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.doFireflyFX();
        }

        if (this.yawDelay > 0) {
            --this.yawDelay;
        } else {
            if (this.currentYaw == 0 && this.desiredYaw == 0) {
                this.yawDelay = 200 + this.field_145850_b.field_73012_v.nextInt(200);
                this.desiredYaw = this.field_145850_b.field_73012_v.nextInt(15) - this.field_145850_b.field_73012_v.nextInt(15);
            }

            if (this.currentYaw < this.desiredYaw) {
                ++this.currentYaw;
            }

            if (this.currentYaw > this.desiredYaw) {
                --this.currentYaw;
            }

            if (this.currentYaw == this.desiredYaw) {
                this.desiredYaw = 0;
            }
        }

        if (this.glowDelay > 0) {
            --this.glowDelay;
        } else {
            if (this.glowing && (double) this.glowIntensity >= 1.0D) {
                this.glowing = false;
            }

            if (this.glowing && (double) this.glowIntensity < 1.0D) {
                this.glowIntensity = (float) ((double) this.glowIntensity + 0.05D);
            }

            if (!this.glowing && this.glowIntensity > 0.0F) {
                this.glowIntensity = (float) ((double) this.glowIntensity - 0.05D);
            }

            if (!this.glowing && this.glowIntensity <= 0.0F) {
                this.glowing = true;
                this.glowDelay = this.field_145850_b.field_73012_v.nextInt(50);
            }
        }

    }

    public boolean anyPlayerInRange() {
        return this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 16.0D) != null;
    }

    void doFireflyFX() {
        double rx = (double) ((float) this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat());
        double ry = (double) ((float) this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat());
        double rz = (double) ((float) this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
        EntityTFTinyFirefly tinyfly = new EntityTFTinyFirefly(this.field_145850_b, rx, ry, rz);

        this.field_145850_b.func_72942_c(tinyfly);
    }
}
