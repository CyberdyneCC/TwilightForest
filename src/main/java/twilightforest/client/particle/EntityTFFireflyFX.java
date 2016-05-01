package twilightforest.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EntityTFFireflyFX extends EntityFX {

    float fireflyParticleScale;
    int fireflyHalfLife;

    public EntityTFFireflyFX(World world, double d, double d1, double d2, float f, float f1, float f2) {
        this(world, d, d1, d2, 1.0F, f, f1, f2);
    }

    public EntityTFFireflyFX(World world, double d, double d1, double d2, float f, float f1, float f2, float f3) {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        this.field_70159_w *= 2.100000001490116D;
        this.field_70181_x *= 2.100000001490116D;
        this.field_70179_y *= 2.100000001490116D;
        if (f1 == 0.0F) {
            f1 = 1.0F;
        }

        this.field_70552_h = this.field_70553_i = 1.0F * f;
        this.field_70552_h *= 0.9F;
        this.field_70551_j = 0.0F;
        this.field_70544_f *= 1.0F;
        this.field_70544_f *= f;
        this.fireflyParticleScale = this.field_70544_f;
        this.field_70547_e = (int) (32.0D / (Math.random() * 0.8D + 0.2D));
        this.field_70547_e = (int) ((float) this.field_70547_e * f);
        this.fireflyHalfLife = this.field_70547_e / 2;
        this.field_70145_X = false;
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = (float) this.field_94054_b / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = (float) this.field_94055_c / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.field_70544_f;

        this.field_70544_f = this.fireflyParticleScale;
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glColorMask(true, true, true, true);
        GL11.glBlendFunc(770, 1);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        float f11 = (float) (this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double) par2 - EntityTFFireflyFX.field_70556_an);
        float f12 = (float) (this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double) par2 - EntityTFFireflyFX.field_70554_ao);
        float f13 = (float) (this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double) par2 - EntityTFFireflyFX.field_70555_ap);
        float f14 = 1.0F;

        par1Tessellator.func_78369_a(this.field_70552_h * f14, this.field_70553_i * f14, this.field_70551_j * f14, this.field_82339_as);
        par1Tessellator.func_78374_a((double) (f11 - par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 - par5 * f10 - par7 * f10), (double) f7, (double) f9);
        par1Tessellator.func_78374_a((double) (f11 - par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 - par5 * f10 + par7 * f10), (double) f7, (double) f8);
        par1Tessellator.func_78374_a((double) (f11 + par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 + par5 * f10 + par7 * f10), (double) f6, (double) f8);
        par1Tessellator.func_78374_a((double) (f11 + par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 + par5 * f10 - par7 * f10), (double) f6, (double) f9);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }

        if (this.field_70546_d < this.fireflyHalfLife) {
            this.func_70536_a(this.field_70546_d * 8 / this.fireflyHalfLife);
        } else {
            this.func_70536_a(7 - (this.field_70546_d - this.fireflyHalfLife) * 8 / this.field_70547_e);
        }

        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        if (this.field_70163_u == this.field_70167_r) {
            this.field_70159_w *= 1.1D;
            this.field_70179_y *= 1.1D;
        }

        this.field_70159_w *= 0.9599999785423279D;
        this.field_70181_x *= 0.9599999785423279D;
        this.field_70179_y *= 0.9599999785423279D;
        if (this.field_70122_E) {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
        }

    }
}
