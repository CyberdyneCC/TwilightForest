package twilightforest.tileentity;

import net.minecraft.tileentity.TileEntity;
import twilightforest.TwilightForestMod;

public class TileEntityTFSmoker extends TileEntity {

    public long counter = 0L;

    public void func_145845_h() {
        if (++this.counter % 4L == 0L) {
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "hugesmoke", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.95D, (double) this.field_145849_e + 0.5D, Math.cos((double) this.counter / 10.0D) * 0.05D, 0.25D, Math.sin((double) this.counter / 10.0D) * 0.05D);
        }

    }
}
