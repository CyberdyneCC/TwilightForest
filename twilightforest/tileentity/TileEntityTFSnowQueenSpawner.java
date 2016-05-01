package twilightforest.tileentity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import twilightforest.entity.TFCreatures;

public class TileEntityTFSnowQueenSpawner extends TileEntityTFBossSpawner {

    public TileEntityTFSnowQueenSpawner() {
        this.mobID = TFCreatures.getSpawnerNameFor("Snow Queen");
    }

    public boolean anyPlayerInRange() {
        EntityPlayer closestPlayer = this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 9.0D);

        return closestPlayer != null && closestPlayer.field_70163_u > (double) (this.field_145848_d - 4);
    }

    protected void spawnMyBoss() {
        EntityLiving myCreature = this.makeMyCreature();
        double rx = (double) this.field_145851_c + 0.5D;
        double ry = (double) this.field_145848_d + 0.5D;
        double rz = (double) this.field_145849_e + 0.5D;

        myCreature.func_70012_b(rx, ry, rz, this.field_145850_b.field_73012_v.nextFloat() * 360.0F, 0.0F);
        this.initializeCreature(myCreature);
        this.field_145850_b.func_72838_d(myCreature);
    }
}
