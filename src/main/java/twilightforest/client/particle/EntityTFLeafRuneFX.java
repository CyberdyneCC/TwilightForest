package twilightforest.client.particle;

import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.world.World;

public class EntityTFLeafRuneFX extends EntityEnchantmentTableParticleFX {

    public EntityTFLeafRuneFX(World world, double x, double y, double z, double velX, double velY, double velZ) {
        super(world, x, y, z, velX, velY, velZ);
        this.field_70544_f = this.field_70146_Z.nextFloat() + 1.0F;
        this.field_70547_e += 10;
        this.field_70545_g = 0.003F + this.field_70146_Z.nextFloat() * 0.006F;
        this.field_70145_X = false;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70181_x -= (double) this.field_70545_g;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }

    }
}
