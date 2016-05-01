package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTFYetiArmor extends ModelBiped {

    private ModelRenderer bipedLegBody;
    private ModelRenderer rightRuff;
    private ModelRenderer leftRuff;
    private ModelRenderer rightToe;
    private ModelRenderer leftToe;

    public ModelTFYetiArmor(int part, float expand) {
        super(expand);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78790_a(-4.5F, -7.5F, -4.0F, 9, 8, 8, expand);
        this.field_78116_c.func_78793_a(0.0F, 0.0F + expand, 0.0F);
        this.addPairHorns(-8.0F, 35.0F);
        this.addPairHorns(-6.0F, 15.0F);
        this.addPairHorns(-4.0F, -5.0F);
        this.field_78123_h = new ModelRenderer(this, 40, 0);
        this.field_78123_h.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, expand);
        this.field_78123_h.func_78793_a(-1.9F, 12.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 40, 0);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, expand);
        this.field_78124_i.func_78793_a(1.9F, 12.0F, 0.0F);
        this.rightRuff = new ModelRenderer(this, 40, 22);
        this.rightRuff.func_78790_a(-2.5F, 0.0F, -2.5F, 5, 2, 5, expand);
        this.rightRuff.func_78793_a(0.0F, 6.0F, 0.0F);
        this.field_78123_h.func_78792_a(this.rightRuff);
        this.leftRuff = new ModelRenderer(this, 40, 22);
        this.leftRuff.func_78790_a(-2.5F, 0.0F, -2.5F, 5, 2, 5, expand);
        this.leftRuff.func_78793_a(0.0F, 6.0F, 0.0F);
        this.field_78124_i.func_78792_a(this.leftRuff);
        this.rightToe = new ModelRenderer(this, 40, 17);
        this.rightToe.func_78790_a(-2.0F, 0.0F, -1.0F, 4, 2, 1, expand);
        this.rightToe.func_78793_a(0.0F, 10.0F, -2.0F);
        this.field_78123_h.func_78792_a(this.rightToe);
        this.leftToe = new ModelRenderer(this, 40, 17);
        this.leftToe.func_78790_a(-2.0F, 0.0F, -1.0F, 4, 2, 1, expand);
        this.leftToe.func_78793_a(0.0F, 10.0F, -2.0F);
        this.field_78124_i.func_78792_a(this.leftToe);
        this.field_78115_e = new ModelRenderer(this, 0, 0);
        this.field_78115_e.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 11, 4, expand);
        this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
        this.bipedLegBody = new ModelRenderer(this, 40, 16);
        this.bipedLegBody.func_78790_a(-4.0F, 0.0F, -2.0F, 8, 12, 4, expand);
        this.bipedLegBody.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 0, 16);
        this.field_78112_f.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 10, 4, expand);
        this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 0, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 10, 4, expand);
        this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
        switch (part) {
        case 0:
            this.field_78116_c.field_78806_j = true;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = false;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.bipedLegBody.field_78806_j = false;
            this.field_78123_h.field_78806_j = false;
            this.field_78124_i.field_78806_j = false;
            break;

        case 1:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = true;
            this.field_78112_f.field_78806_j = true;
            this.field_78113_g.field_78806_j = true;
            this.bipedLegBody.field_78806_j = false;
            this.field_78123_h.field_78806_j = false;
            this.field_78124_i.field_78806_j = false;
            break;

        case 2:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = false;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.bipedLegBody.field_78806_j = true;
            this.field_78123_h.field_78806_j = true;
            this.field_78124_i.field_78806_j = true;
            this.leftRuff.field_78806_j = false;
            this.leftToe.field_78806_j = false;
            this.rightRuff.field_78806_j = false;
            this.rightToe.field_78806_j = false;
            break;

        case 3:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = false;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.bipedLegBody.field_78806_j = false;
            this.field_78123_h.field_78806_j = true;
            this.field_78124_i.field_78806_j = true;
            this.leftRuff.field_78806_j = true;
            this.leftToe.field_78806_j = true;
            this.rightRuff.field_78806_j = true;
            this.rightToe.field_78806_j = true;
        }

    }

    private void addPairHorns(float height, float zangle) {
        ModelRenderer horn1a = new ModelRenderer(this, 0, 19);

        horn1a.func_78789_a(-3.0F, -1.5F, -1.5F, 3, 3, 3);
        horn1a.func_78793_a(-4.5F, height, -1.0F);
        horn1a.field_78796_g = -0.5235988F;
        horn1a.field_78808_h = zangle / 57.295776F;
        this.field_78116_c.func_78792_a(horn1a);
        ModelRenderer horn1b = new ModelRenderer(this, 0, 26);

        horn1b.func_78789_a(-4.0F, -1.0F, -1.0F, 5, 2, 2);
        horn1b.func_78793_a(-3.0F, 0.0F, 0.0F);
        horn1b.field_78796_g = -0.34906587F;
        horn1b.field_78808_h = zangle / 57.295776F;
        horn1a.func_78792_a(horn1b);
        ModelRenderer horn2a = new ModelRenderer(this, 0, 19);

        horn2a.func_78789_a(0.0F, -1.5F, -1.5F, 3, 3, 3);
        horn2a.func_78793_a(4.5F, height, -1.0F);
        horn2a.field_78796_g = 0.5235988F;
        horn2a.field_78808_h = -zangle / 57.295776F;
        this.field_78116_c.func_78792_a(horn2a);
        ModelRenderer horn2b = new ModelRenderer(this, 0, 26);

        horn2b.func_78789_a(-1.0F, -1.0F, -1.0F, 5, 2, 2);
        horn2b.func_78793_a(3.0F, 0.0F, 0.0F);
        horn2b.field_78796_g = 0.34906587F;
        horn2b.field_78808_h = -zangle / 57.295776F;
        horn2a.func_78792_a(horn2b);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (par1Entity != null) {
            this.field_78117_n = par1Entity.func_70093_af();
        }

        if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
            this.field_78120_m = ((EntityLivingBase) par1Entity).func_70694_bm() != null ? 1 : 0;
        }

        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        this.bipedLegBody.func_78785_a(par7);
    }
}
