package twilightforest.tileentity;

import twilightforest.TwilightForestMod;

public class TileEntityTFCicada extends TileEntityTFCritter {

    public int yawDelay;
    public int currentYaw;
    public int desiredYaw;
    public int singDuration;
    public boolean singing;
    public int singDelay;

    public void func_145845_h() {
        super.func_145845_h();
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

        if (this.singDelay > 0) {
            --this.singDelay;
        } else {
            if (this.singing && this.singDuration == 0) {
                this.playSong();
            }

            if (this.singing && this.singDuration >= 100) {
                this.singing = false;
                this.singDuration = 0;
            }

            if (this.singing && this.singDuration < 100) {
                ++this.singDuration;
                this.doSingAnimation();
            }

            if (!this.singing && this.singDuration <= 0) {
                this.singing = true;
                this.singDelay = 100 + this.field_145850_b.field_73012_v.nextInt(100);
            }
        }

    }

    public void doSingAnimation() {
        if (this.field_145850_b.field_73012_v.nextInt(5) == 0) {
            double rx = (double) ((float) this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat());
            double ry = (double) ((float) this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat());
            double rz = (double) ((float) this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());

            this.field_145850_b.func_72869_a("note", rx, ry, rz, 0.0D, 0.0D, 0.0D);
        }

    }

    public void playSong() {
        if (!TwilightForestMod.silentCicadas) {
            this.field_145850_b.func_72908_a((double) this.field_145851_c, (double) this.field_145848_d, (double) this.field_145849_e, "TwilightForest:mob.cicada", 1.0F, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2F + 1.0F);
        }

    }
}
