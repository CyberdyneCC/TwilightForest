package twilightforest.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityTFGhastTrapFX extends EntityFX {

    float reddustParticleScale;
    private double originX;
    private double originY;
    private double originZ;

    public EntityTFGhastTrapFX(World par1World, double par2, double par4, double par6, double par8, double par9, double par10) {
        this(par1World, par2, par4, par6, 3.0F, par8, par9, par10);
    }

    public EntityTFGhastTrapFX(World par1World, double x, double y, double z, float scale, double mx, double my, double mz) {
        super(par1World, x + mx, y + my, z + mz, mx, my, mz);
        this.field_70159_w = mx;
        this.field_70181_x = my;
        this.field_70179_y = mz;
        this.originX = x;
        this.originY = y;
        this.originZ = z;
        float f4 = (float) Math.random() * 0.4F;

        this.field_70553_i = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * f4;
        this.field_70551_j = ((float) (Math.random() * 0.20000000298023224D) + 0.8F) * f4;
        this.field_70552_h = 1.0F;
        this.field_70544_f *= 0.75F;
        this.field_70544_f *= scale;
        this.reddustParticleScale = this.field_70544_f;
        this.field_70547_e = (int) (10.0D / (Math.random() * 0.8D + 0.2D));
        this.field_70145_X = false;
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = ((float) this.field_70546_d + par2) / (float) this.field_70547_e * 32.0F;

        if (f6 < 0.0F) {
            f6 = 0.0F;
        }

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.field_70544_f = this.reddustParticleScale * f6;
        super.func_70539_a(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public void func_70071_h_() {
        this.func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float proportion = (float) this.field_70546_d / (float) this.field_70547_e;

        proportion = 1.0F - proportion;
        this.field_70165_t = this.originX + this.field_70159_w * (double) proportion;
        this.field_70163_u = this.originY + this.field_70181_x * (double) proportion;
        this.field_70161_v = this.originZ + this.field_70179_y * (double) proportion;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }

    }
}
