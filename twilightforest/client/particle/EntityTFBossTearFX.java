package twilightforest.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityTFBossTearFX extends EntityFX {

    public EntityTFBossTearFX(World par1World, double par2, double par4, double par6, Item par8Item) {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.func_110125_a(par8Item.func_77617_a(0));
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
        this.field_70545_g = Blocks.field_150433_aE.field_149763_I * 2.0F;
        this.field_70544_f = 16.0F;
        this.field_70547_e = 20 + this.field_70146_Z.nextInt(40);
    }

    public EntityTFBossTearFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, Item par14Item) {
        this(par1World, par2, par4, par6, par14Item);
        this.field_70159_w *= 0.10000000149011612D;
        this.field_70181_x *= 0.10000000149011612D;
        this.field_70179_y *= 0.10000000149011612D;
        this.field_70159_w += par8;
        this.field_70181_x += par10;
        this.field_70179_y += par12;
    }

    public int func_70537_b() {
        return 2;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70122_E && this.field_70146_Z.nextBoolean()) {
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, "random.glass", 0.5F, 1.0F, false);

            for (int i = 0; i < 50; ++i) {
                double gaussX = this.field_70146_Z.nextGaussian() * 0.1D;
                double gaussY = this.field_70146_Z.nextGaussian() * 0.2D;
                double gaussZ = this.field_70146_Z.nextGaussian() * 0.1D;
                Item popItem = Items.field_151073_bk;

                this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b(popItem), this.field_70165_t + (double) this.field_70146_Z.nextFloat() - (double) this.field_70146_Z.nextFloat(), this.field_70163_u + 0.5D, this.field_70161_v + (double) this.field_70146_Z.nextFloat(), gaussX, gaussY, gaussZ);
            }

            this.func_70106_y();
        }

    }

    public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6 = (float) this.field_94054_b / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = (float) this.field_94055_c / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.field_70544_f;

        if (this.field_70550_a != null) {
            f6 = this.field_70550_a.func_94214_a(0.0D);
            f7 = this.field_70550_a.func_94214_a(16.0D);
            f8 = this.field_70550_a.func_94207_b(0.0D);
            f9 = this.field_70550_a.func_94207_b(16.0D);
        }

        float f11 = (float) (this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double) par2 - EntityTFBossTearFX.field_70556_an);
        float f12 = (float) (this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double) par2 - EntityTFBossTearFX.field_70554_ao);
        float f13 = (float) (this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double) par2 - EntityTFBossTearFX.field_70555_ap);
        float f14 = 1.0F;

        par1Tessellator.func_78386_a(f14 * this.field_70552_h, f14 * this.field_70553_i, f14 * this.field_70551_j);
        par1Tessellator.func_78374_a((double) (f11 - par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 - par5 * f10 - par7 * f10), (double) f6, (double) f9);
        par1Tessellator.func_78374_a((double) (f11 - par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 - par5 * f10 + par7 * f10), (double) f6, (double) f8);
        par1Tessellator.func_78374_a((double) (f11 + par3 * f10 + par6 * f10), (double) (f12 + par4 * f10), (double) (f13 + par5 * f10 + par7 * f10), (double) f7, (double) f8);
        par1Tessellator.func_78374_a((double) (f11 + par3 * f10 - par6 * f10), (double) (f12 - par4 * f10), (double) (f13 + par5 * f10 - par7 * f10), (double) f7, (double) f9);
    }
}
