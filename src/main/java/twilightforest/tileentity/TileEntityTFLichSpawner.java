package twilightforest.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import twilightforest.entity.TFCreatures;

public class TileEntityTFLichSpawner extends TileEntityTFBossSpawner {

    public TileEntityTFLichSpawner() {
        this.mobID = TFCreatures.getSpawnerNameFor("Twilight Lich");
    }

    public boolean anyPlayerInRange() {
        EntityPlayer closestPlayer = this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 9.0D);

        return closestPlayer != null && closestPlayer.field_70163_u > (double) (this.field_145848_d - 4);
    }
}
