package twilightforest.tileentity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import twilightforest.entity.TFCreatures;
import twilightforest.entity.boss.EntityTFKnightPhantom;

public class TileEntityTFKnightPhantomsSpawner extends TileEntityTFBossSpawner {

    public TileEntityTFKnightPhantomsSpawner() {
        this.mobID = TFCreatures.getSpawnerNameFor("Knight Phantom");
    }

    public boolean anyPlayerInRange() {
        EntityPlayer closestPlayer = this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 9.0D);

        return closestPlayer != null && closestPlayer.field_70163_u > (double) (this.field_145848_d - 2);
    }

    protected void spawnMyBoss() {
        for (int i = 0; i < 6; ++i) {
            EntityLiving myCreature = this.makeMyCreature();
            float angle = 60.0F * (float) i;
            float distance = 4.0F;
            double rx = (double) this.field_145851_c + 0.5D + Math.cos((double) angle * 3.141592653589793D / 180.0D) * (double) distance;
            double ry = (double) this.field_145848_d + 0.5D;
            double rz = (double) this.field_145849_e + 0.5D + Math.sin((double) angle * 3.141592653589793D / 180.0D) * (double) distance;

            myCreature.func_70012_b(rx, ry, rz, this.field_145850_b.field_73012_v.nextFloat() * 360.0F, 0.0F);
            this.initializeCreature(myCreature);
            ((EntityTFKnightPhantom) myCreature).setNumber(i);
            this.field_145850_b.func_72838_d(myCreature);
        }

    }

    protected void initializeCreature(EntityLiving myCreature) {
        if (myCreature instanceof EntityTFKnightPhantom) {
            ((EntityTFKnightPhantom) myCreature).setHomeArea(this.field_145851_c, this.field_145848_d, this.field_145849_e, 46);
        }

    }
}
