package twilightforest.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumDifficulty;

public abstract class TileEntityTFBossSpawner extends TileEntity {

    protected String mobID = "Pig";
    protected int counter;
    protected Entity displayCreature = null;

    public boolean anyPlayerInRange() {
        return this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, (double) this.getRange()) != null;
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        ++this.counter;
        if (this.anyPlayerInRange()) {
            if (this.field_145850_b.field_72995_K) {
                double rx = (double) ((float) this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat());
                double ry = (double) ((float) this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat());
                double rz = (double) ((float) this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());

                this.field_145850_b.func_72869_a("smoke", rx, ry, rz, 0.0D, 0.0D, 0.0D);
                this.field_145850_b.func_72869_a("flame", rx, ry, rz, 0.0D, 0.0D, 0.0D);
            } else if (this.field_145850_b.field_73013_u != EnumDifficulty.PEACEFUL) {
                this.spawnMyBoss();
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a, 0, 2);
            }
        }

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

    public Entity getDisplayEntity() {
        if (this.displayCreature == null) {
            this.displayCreature = this.makeMyCreature();
        }

        return this.displayCreature;
    }

    protected void initializeCreature(EntityLiving myCreature) {
        if (myCreature instanceof EntityCreature) {
            ((EntityCreature) myCreature).func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 46);
        }

    }

    protected int getRange() {
        return 50;
    }

    protected EntityLiving makeMyCreature() {
        return (EntityLiving) EntityList.func_75620_a(this.mobID, this.field_145850_b);
    }
}
