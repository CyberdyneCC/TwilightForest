package twilightforest.tileentity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import twilightforest.entity.TFCreatures;

public class TileEntityTFNagaSpawner extends TileEntityTFBossSpawner {

    public TileEntityTFNagaSpawner() {
        this.mobID = TFCreatures.getSpawnerNameFor("Naga");
    }

    public boolean anyPlayerInRange() {
        return this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 50.0D) != null;
    }

    protected void initializeCreature(EntityLiving myCreature) {
        if (myCreature instanceof EntityCreature) {
            ((EntityCreature) myCreature).func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 46);
        }

    }

    protected int getRange() {
        return 50;
    }
}
