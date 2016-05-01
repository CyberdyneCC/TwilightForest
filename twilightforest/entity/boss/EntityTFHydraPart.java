package twilightforest.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTFHydraPart extends EntityLiving {

    public EntityTFHydra hydraObj;

    public EntityTFHydraPart(World world) {
        super(world);
        this.field_70178_ae = true;
    }

    public EntityTFHydraPart(EntityTFHydra hydra, String s, float f, float f1) {
        super(hydra.field_70170_p);
        this.func_70105_a(f, f1);
        this.hydraObj = hydra;
        this.setPartName(s);
        this.field_70178_ae = true;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, "");
    }

    public String getPartName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setPartName(String name) {
        this.field_70180_af.func_75692_b(17, name);
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74778_a("PartName", this.getPartName());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setPartName(nbttagcompound.func_74779_i("PartName"));
    }

    public void func_70071_h_() {
        if (this.hydraObj != null && this.hydraObj.field_70725_aQ > 190) {
            this.func_70106_y();
        }

        if (this.hydraObj == null && this.field_70173_aa > 1200) {
            this.func_70106_y();
        }

        super.func_70030_z();
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;
        this.field_70136_U = this.field_70161_v;
        if (this.field_70716_bi > 0) {
            double d0 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double) this.field_70716_bi;
            double d1 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double) this.field_70716_bi;
            double d2 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double) this.field_70716_bi;
            double d3 = MathHelper.func_76138_g(this.field_70712_bm - (double) this.field_70177_z);

            this.field_70177_z = (float) ((double) this.field_70177_z + d3 / (double) this.field_70716_bi);
            this.field_70125_A = (float) ((double) this.field_70125_A + (this.field_70705_bn - (double) this.field_70125_A) / (double) this.field_70716_bi);
            --this.field_70716_bi;
            this.func_70107_b(d0, d1, d2);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
        }

        this.field_70759_as = this.field_70177_z;

        for (this.field_70758_at = this.field_70126_B; this.field_70177_z - this.field_70126_B < -180.0F; this.field_70126_B -= 360.0F) {
            ;
        }

        while (this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
        }

        while (this.field_70761_aq - this.field_70760_ar < -180.0F) {
            this.field_70760_ar -= 360.0F;
        }

        while (this.field_70761_aq - this.field_70760_ar >= 180.0F) {
            this.field_70760_ar += 360.0F;
        }

        while (this.field_70125_A - this.field_70127_C < -180.0F) {
            this.field_70127_C -= 360.0F;
        }

        while (this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
        }

        while (this.field_70759_as - this.field_70758_at < -180.0F) {
            this.field_70758_at -= 360.0F;
        }

        while (this.field_70759_as - this.field_70758_at >= 180.0F) {
            this.field_70758_at += 360.0F;
        }

    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        return this.hydraObj != null ? this.hydraObj.attackEntityFromPart(this, damagesource, i) : false;
    }

    public boolean func_70028_i(Entity entity) {
        return this == entity || this.hydraObj == entity;
    }

    protected void func_70101_b(float par1, float par2) {
        this.field_70177_z = par1 % 360.0F;
        this.field_70125_A = par2 % 360.0F;
    }
}
