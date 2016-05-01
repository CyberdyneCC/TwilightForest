package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.boss.EntityTFSnowQueen;

public class ModelTFSnowQueen extends ModelBiped {

    public ModelTFSnowQueen() {
        float par1 = 0.0F;
        float par2 = 0.0F;

        this.field_78114_d = new ModelRenderer(this, 0, 0);
        this.field_78114_d.func_78792_a(this.makeFrontCrown(-1.0F, -4.0F, 10.0F));
        this.field_78114_d.func_78792_a(this.makeFrontCrown(0.0F, 4.0F, -10.0F));
        this.field_78114_d.func_78792_a(this.makeSideCrown(-1.0F, -4.0F, 10.0F));
        this.field_78114_d.func_78792_a(this.makeSideCrown(0.0F, 4.0F, -10.0F));
        this.field_78115_e = new ModelRenderer(this, 32, 0);
        this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 23, 4, par1);
        this.field_78115_e.func_78793_a(0.0F, 0.0F + par2, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 16, 16);
        this.field_78112_f.func_78790_a(-2.0F, -2.0F, -1.5F, 3, 12, 3, par1);
        this.field_78112_f.func_78793_a(-5.0F, 2.0F + par2, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 16, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0F, -2.0F, -1.3F, 3, 12, 3, par1);
        this.field_78113_g.func_78793_a(5.0F, 2.0F + par2, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 16);
        this.field_78123_h.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 12, 3, par1);
        this.field_78123_h.func_78793_a(-1.9F, 12.0F + par2, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 12, 3, par1);
        this.field_78124_i.func_78793_a(1.9F, 12.0F + par2, 0.0F);
    }

    private ModelRenderer makeSideCrown(float spikeDepth, float crownX, float angle) {
        ModelRenderer crownSide = new ModelRenderer(this, 28, 28);

        crownSide.func_78789_a(-3.5F, -0.5F, -0.5F, 7, 1, 1);
        crownSide.func_78793_a(crownX, -6.0F, 0.0F);
        crownSide.field_78796_g = 1.570795F;
        ModelRenderer spike4 = new ModelRenderer(this, 48, 27);

        spike4.func_78789_a(-0.5F, -3.5F, spikeDepth, 1, 4, 1);
        spike4.field_78795_f = angle * 1.5F / 180.0F * 3.14159F;
        ModelRenderer spike3l = new ModelRenderer(this, 52, 28);

        spike3l.func_78789_a(-0.5F, -2.5F, spikeDepth, 1, 3, 1);
        spike3l.func_78793_a(-2.5F, 0.0F, 0.0F);
        spike3l.field_78795_f = angle / 180.0F * 3.14159F;
        spike3l.field_78808_h = -0.17453279F;
        ModelRenderer spike3r = new ModelRenderer(this, 52, 28);

        spike3r.func_78789_a(-0.5F, -2.5F, spikeDepth, 1, 3, 1);
        spike3r.func_78793_a(2.5F, 0.0F, 0.0F);
        spike3r.field_78795_f = angle / 180.0F * 3.14159F;
        spike3r.field_78808_h = 0.17453279F;
        crownSide.func_78792_a(spike4);
        crownSide.func_78792_a(spike3l);
        crownSide.func_78792_a(spike3r);
        return crownSide;
    }

    private ModelRenderer makeFrontCrown(float spikeDepth, float crownZ, float angle) {
        ModelRenderer crownFront = new ModelRenderer(this, 28, 30);

        crownFront.func_78789_a(-4.5F, -0.5F, -0.5F, 9, 1, 1);
        crownFront.func_78793_a(0.0F, -6.0F, crownZ);
        ModelRenderer spike4 = new ModelRenderer(this, 48, 27);

        spike4.func_78789_a(-0.5F, -3.5F, spikeDepth, 1, 4, 1);
        spike4.field_78795_f = angle * 1.5F / 180.0F * 3.14159F;
        ModelRenderer spike3l = new ModelRenderer(this, 52, 28);

        spike3l.func_78789_a(-0.5F, -2.5F, spikeDepth, 1, 3, 1);
        spike3l.func_78793_a(-2.5F, 0.0F, 0.0F);
        spike3l.field_78795_f = angle / 180.0F * 3.14159F;
        spike3l.field_78808_h = -0.17453279F;
        ModelRenderer spike3r = new ModelRenderer(this, 52, 28);

        spike3r.func_78789_a(-0.5F, -2.5F, spikeDepth, 1, 3, 1);
        spike3r.func_78793_a(2.5F, 0.0F, 0.0F);
        spike3r.field_78795_f = angle / 180.0F * 3.14159F;
        spike3r.field_78808_h = 0.17453279F;
        crownFront.func_78792_a(spike4);
        crownFront.func_78792_a(spike3l);
        crownFront.func_78792_a(spike3r);
        return crownFront;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        EntityTFSnowQueen queen = (EntityTFSnowQueen) par7Entity;

        if (queen.getCurrentPhase() == EntityTFSnowQueen.Phase.BEAM) {
            if (queen.isBreathing()) {
                float f6 = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
                float f7 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);

                this.field_78112_f.field_78808_h = 0.0F;
                this.field_78113_g.field_78808_h = 0.0F;
                this.field_78112_f.field_78796_g = -(0.1F - f6 * 0.6F);
                this.field_78113_g.field_78796_g = 0.1F - f6 * 0.6F;
                this.field_78112_f.field_78795_f = -1.5707964F;
                this.field_78113_g.field_78795_f = -1.5707964F;
                this.field_78112_f.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
                this.field_78113_g.field_78795_f -= f6 * 1.2F - f7 * 0.4F;
                this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
                this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
                this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
                this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.067F) * 0.05F;
            } else {
                this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
                this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
            }
        }

    }
}
