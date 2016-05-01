package twilightforest.tileentity;

public class TileEntityTFMoonworm extends TileEntityTFCritter {

    public int yawDelay = 0;
    public int currentYaw = -1;
    public int desiredYaw = 0;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.currentYaw == -1) {
            this.currentYaw = this.field_145850_b.field_73012_v.nextInt(4) * 90;
        }

        if (this.yawDelay > 0) {
            --this.yawDelay;
        } else {
            if (this.desiredYaw == 0) {
                this.yawDelay = 200 + this.field_145850_b.field_73012_v.nextInt(200);
                this.desiredYaw = this.field_145850_b.field_73012_v.nextInt(4) * 90;
            }

            ++this.currentYaw;
            if (this.currentYaw > 360) {
                this.currentYaw = 0;
            }

            if (this.currentYaw == this.desiredYaw) {
                this.desiredYaw = 0;
            }
        }

    }
}
