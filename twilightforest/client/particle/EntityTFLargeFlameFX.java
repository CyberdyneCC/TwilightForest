package twilightforest.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityTFLargeFlameFX extends EntityFX {

    private float flameScale;

    public EntityTFLargeFlameFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.field_70159_w = this.field_70159_w * 0.009999999776482582D + par8;
        this.field_70181_x = this.field_70181_x * 0.009999999776482582D + par10;
        this.field_70179_y = this.field_70179_y * 0.009999999776482582D + par12;
        this.field_70544_f = (float) ((double) this.field_70544_f * 5.0D);
        this.flameScale = this.field_70544_f;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
        this.field_70547_e = (int) (8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.func_70536_a(48);
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f = ((float) this.field_70546_d + par2) / (float) this.field_70547_e;

        this.field_70544_f = this.flameScale * (1.0F - f * f * 0.5F);
        super.func_70539_a(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public int func_70070_b(float par1) {
        float f = ((float) this.field_70546_d + par1) / (float) this.field_70547_e;

        if (f < 0.0F) {
            f = 0.0F;
        }

        if (f > 1.0F) {
            f = 1.0F;
        }

        int i = super.func_70070_b(par1);
        int j = i & 255;
        int k = i >> 16 & 255;

        j += (int) (f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public float func_70013_c(float par1) {
        float f = ((float) this.field_70546_d + par1) / (float) this.field_70547_e;

        if (f < 0.0F) {
            f = 0.0F;
        }

        if (f > 1.0F) {
            f = 1.0F;
        }

        float f1 = super.func_70013_c(par1);

        return f1 * f + (1.0F - f);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }

        this.field_70181_x += 0.004D;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.9599999785423279D;
        this.field_70181_x *= 0.9599999785423279D;
        this.field_70179_y *= 0.9599999785423279D;
        if (this.field_70122_E) {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
        }

    }
}
