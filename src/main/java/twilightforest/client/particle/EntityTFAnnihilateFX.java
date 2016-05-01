package twilightforest.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import twilightforest.item.ItemTFCubeOfAnnihilation;
import twilightforest.item.TFItems;

@SideOnly(Side.CLIENT)
public class EntityTFAnnihilateFX extends EntityFX {

    float initialParticleScale;

    public EntityTFAnnihilateFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntityTFAnnihilateFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.field_70159_w *= 0.10000000149011612D;
        this.field_70181_x *= 0.10000000149011612D;
        this.field_70179_y *= 0.10000000149011612D;
        this.field_70159_w += par8 * 0.4D;
        this.field_70181_x += par10 * 0.4D;
        this.field_70179_y += par12 * 0.4D;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
        this.field_70544_f *= 0.75F;
        this.field_70544_f *= par14;
        this.initialParticleScale = this.field_70544_f;
        this.field_70547_e = (int) (60.0D / (Math.random() * 0.8D + 0.6D));
        this.field_70547_e = (int) ((float) this.field_70547_e * par14);
        this.field_70145_X = false;
        this.func_110125_a(((ItemTFCubeOfAnnihilation) TFItems.cubeOfAnnihilation).getAnnihilateIcon());
        this.func_70071_h_();
    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.func_70539_a(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }

        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.9599999785423279D;
        this.field_70181_x *= 0.9599999785423279D;
        this.field_70179_y *= 0.9599999785423279D;
        if (this.field_70122_E) {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
        }

        this.field_70544_f = (float) ((double) this.field_70544_f * 0.97D);
        if ((double) this.field_70544_f < 0.4D) {
            this.func_70106_y();
        }

        float blacken = 0.985F;

        this.field_70552_h *= blacken;
        this.field_70553_i *= blacken;
        this.field_70551_j *= blacken;
    }

    public int func_70070_b(float par1) {
        return 15728880;
    }

    public int func_70537_b() {
        return 2;
    }
}
