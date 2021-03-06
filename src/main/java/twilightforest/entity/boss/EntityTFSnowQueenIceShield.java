package twilightforest.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTFSnowQueenIceShield extends Entity {

    EntityTFSnowQueen queen;

    public EntityTFSnowQueenIceShield(World par1World) {
        super(par1World);
        this.func_70105_a(0.75F, 0.75F);
    }

    public EntityTFSnowQueenIceShield(EntityTFSnowQueen goblin) {
        this(goblin.func_82194_d());
        this.queen = goblin;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        this.field_70170_p.func_72956_a(this, "random.break", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        return false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        ++this.field_70173_aa;
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;

        for (this.field_70136_U = this.field_70161_v; this.field_70177_z - this.field_70126_B < -180.0F; this.field_70126_B -= 360.0F) {
            ;
        }

        while (this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
        }

        while (this.field_70125_A - this.field_70127_C < -180.0F) {
            this.field_70127_C -= 360.0F;
        }

        while (this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
        }

    }

    public boolean func_70067_L() {
        return true;
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_70028_i(Entity entity) {
        return this == entity || this.queen == entity;
    }

    protected void func_70088_a() {}

    protected void func_70037_a(NBTTagCompound nbttagcompound) {}

    protected void func_70014_b(NBTTagCompound nbttagcompound) {}
}
