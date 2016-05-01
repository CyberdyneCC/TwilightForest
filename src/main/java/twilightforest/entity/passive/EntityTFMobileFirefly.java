package twilightforest.entity.passive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTFMobileFirefly extends EntityAmbientCreature {

    private ChunkCoordinates currentFlightTarget;

    public EntityTFMobileFirefly(World par1World) {
        super(par1World);
        this.func_70105_a(0.5F, 0.5F);
    }

    protected float func_70599_aP() {
        return 0.1F;
    }

    protected float func_70647_i() {
        return super.func_70647_i() * 0.95F;
    }

    protected String func_70621_aR() {
        return "mob.bat.hurt";
    }

    protected String func_70673_aS() {
        return "mob.bat.death";
    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_82167_n(Entity par1Entity) {}

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(6.0D);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70181_x *= 0.6000000238418579D;
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.currentFlightTarget != null && (!this.field_70170_p.func_147437_c(this.currentFlightTarget.field_71574_a, this.currentFlightTarget.field_71572_b, this.currentFlightTarget.field_71573_c) || this.currentFlightTarget.field_71572_b < 1)) {
            this.currentFlightTarget = null;
        }

        if (this.currentFlightTarget == null || this.field_70146_Z.nextInt(30) == 0 || this.currentFlightTarget.func_71569_e((int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v) < 4.0F) {
            this.currentFlightTarget = new ChunkCoordinates((int) this.field_70165_t + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7), (int) this.field_70163_u + this.field_70146_Z.nextInt(6) - 2, (int) this.field_70161_v + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7));
        }

        double d0 = (double) this.currentFlightTarget.field_71574_a + 0.5D - this.field_70165_t;
        double d1 = (double) this.currentFlightTarget.field_71572_b + 0.1D - this.field_70163_u;
        double d2 = (double) this.currentFlightTarget.field_71573_c + 0.5D - this.field_70161_v;
        double speed = 0.05000000149011612D;

        this.field_70159_w += (Math.signum(d0) * 0.5D - this.field_70159_w) * speed;
        this.field_70181_x += (Math.signum(d1) * 0.699999988079071D - this.field_70181_x) * speed * 2.0D;
        this.field_70179_y += (Math.signum(d2) * 0.5D - this.field_70179_y) * speed;
        float f = (float) (Math.atan2(this.field_70179_y, this.field_70159_w) * 180.0D / 3.141592653589793D) - 90.0F;
        float f1 = MathHelper.func_76142_g(f - this.field_70177_z);

        this.field_70701_bs = 0.5F;
        this.field_70177_z += f1;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70069_a(float par1) {}

    protected void func_70064_a(double par1, boolean par3) {}

    public boolean func_145773_az() {
        return true;
    }

    public boolean func_70601_bi() {
        int i = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);

        if (i >= 63) {
            return false;
        } else {
            int j = MathHelper.func_76128_c(this.field_70165_t);
            int k = MathHelper.func_76128_c(this.field_70161_v);
            int l = this.field_70170_p.func_72957_l(j, i, k);
            byte b0 = 4;

            return l > this.field_70146_Z.nextInt(b0) ? false : super.func_70601_bi();
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float par1) {
        return 15728880;
    }

    public float getGlowBrightness() {
        return (float) Math.sin((double) this.field_70173_aa / 7.0D) + 1.0F;
    }
}
