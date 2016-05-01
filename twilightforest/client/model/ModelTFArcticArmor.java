package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTFArcticArmor extends ModelBiped {

    public ModelTFArcticArmor(int part, float expand) {
        super(expand);
        ModelRenderer rightHood = new ModelRenderer(this, 0, 0);

        rightHood.func_78790_a(-1.0F, -2.0F, -1.0F, 1, 4, 1, expand);
        rightHood.func_78793_a(-2.5F, -3.0F, -5.0F);
        this.field_78116_c.func_78792_a(rightHood);
        ModelRenderer leftHood = new ModelRenderer(this, 0, 0);

        leftHood.func_78790_a(0.0F, -2.0F, -1.0F, 1, 4, 1, expand);
        leftHood.func_78793_a(2.5F, -3.0F, -5.0F);
        this.field_78116_c.func_78792_a(leftHood);
        ModelRenderer topHood = new ModelRenderer(this, 24, 0);

        topHood.func_78790_a(-2.0F, -1.0F, -1.0F, 4, 1, 1, expand);
        topHood.func_78793_a(0.0F, -5.5F, -5.0F);
        this.field_78116_c.func_78792_a(topHood);
        ModelRenderer bottomHood = new ModelRenderer(this, 24, 0);

        bottomHood.func_78790_a(-2.0F, -1.0F, -1.0F, 4, 1, 1, expand);
        bottomHood.func_78793_a(0.0F, 0.5F, -5.0F);
        this.field_78116_c.func_78792_a(bottomHood);
        switch (part) {
        case 0:
            this.field_78116_c.field_78806_j = true;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = false;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.field_78123_h.field_78806_j = false;
            this.field_78124_i.field_78806_j = false;
            break;

        case 1:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = true;
            this.field_78112_f.field_78806_j = true;
            this.field_78113_g.field_78806_j = true;
            this.field_78123_h.field_78806_j = false;
            this.field_78124_i.field_78806_j = false;
            break;

        case 2:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = true;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.field_78123_h.field_78806_j = true;
            this.field_78124_i.field_78806_j = true;
            break;

        case 3:
            this.field_78116_c.field_78806_j = false;
            this.field_78114_d.field_78806_j = false;
            this.field_78115_e.field_78806_j = false;
            this.field_78112_f.field_78806_j = false;
            this.field_78113_g.field_78806_j = false;
            this.field_78123_h.field_78806_j = true;
            this.field_78124_i.field_78806_j = true;
        }

    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (par1Entity != null) {
            this.field_78117_n = par1Entity.func_70093_af();
        }

        if (par1Entity != null && par1Entity instanceof EntityLivingBase) {
            this.field_78120_m = ((EntityLivingBase) par1Entity).func_70694_bm() != null ? 1 : 0;
        }

        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
    }
}
