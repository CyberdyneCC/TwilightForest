package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTFKnightlyArmor extends ModelBiped {

    public ModelRenderer righthorn1 = new ModelRenderer(this, 32, 0);
    public ModelRenderer righthorn2;
    public ModelRenderer lefthorn1;
    public ModelRenderer lefthorn2;
    public ModelRenderer shoulderSpike1;
    public ModelRenderer shoulderSpike2;
    public ModelRenderer shoeSpike1;
    public ModelRenderer shoeSpike2;

    public ModelTFKnightlyArmor(int part, float expand) {
        super(expand);
        this.righthorn1.func_78789_a(-5.5F, -1.5F, -1.5F, 5, 3, 3);
        this.righthorn1.func_78793_a(-4.0F, -6.5F, 0.0F);
        this.righthorn1.field_78796_g = -0.2617994F;
        this.righthorn1.field_78808_h = 0.17453294F;
        this.righthorn2 = new ModelRenderer(this, 32, 6);
        this.righthorn2.func_78789_a(-3.5F, -1.0F, -1.0F, 3, 2, 2);
        this.righthorn2.func_78793_a(-4.5F, 0.0F, 0.0F);
        this.righthorn2.field_78808_h = 0.17453294F;
        this.righthorn1.func_78792_a(this.righthorn2);
        this.lefthorn1 = new ModelRenderer(this, 32, 0);
        this.lefthorn1.field_78809_i = true;
        this.lefthorn1.func_78789_a(0.5F, -1.5F, -1.5F, 5, 3, 3);
        this.lefthorn1.func_78793_a(4.0F, -6.5F, 0.0F);
        this.lefthorn1.field_78796_g = 0.2617994F;
        this.lefthorn1.field_78808_h = -0.17453294F;
        this.lefthorn2 = new ModelRenderer(this, 32, 6);
        this.lefthorn2.func_78789_a(0.5F, -1.0F, -1.0F, 3, 2, 2);
        this.lefthorn2.func_78793_a(4.5F, 0.0F, 0.0F);
        this.lefthorn2.field_78808_h = -0.17453294F;
        this.lefthorn1.func_78792_a(this.lefthorn2);
        this.field_78116_c.func_78792_a(this.righthorn1);
        this.field_78116_c.func_78792_a(this.lefthorn1);
        this.shoulderSpike1 = new ModelRenderer(this, 32, 10);
        this.shoulderSpike1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.5F);
        this.shoulderSpike1.func_78793_a(-3.75F, -2.5F, 0.0F);
        this.shoulderSpike1.field_78795_f = 0.7853982F;
        this.shoulderSpike1.field_78796_g = 0.17453294F;
        this.shoulderSpike1.field_78808_h = 0.6108653F;
        this.field_78112_f.func_78792_a(this.shoulderSpike1);
        this.shoulderSpike2 = new ModelRenderer(this, 32, 10);
        this.shoulderSpike2.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.5F);
        this.shoulderSpike2.func_78793_a(3.75F, -2.5F, 0.0F);
        this.shoulderSpike2.field_78795_f = -0.7853982F;
        this.shoulderSpike2.field_78796_g = -0.17453294F;
        this.shoulderSpike2.field_78808_h = 0.95993114F;
        this.field_78113_g.func_78792_a(this.shoulderSpike2);
        this.shoeSpike1 = new ModelRenderer(this, 32, 10);
        this.shoeSpike1.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.5F);
        this.shoeSpike1.func_78793_a(-2.5F, 11.0F, 2.0F);
        this.shoeSpike1.field_78796_g = -0.7853982F;
        this.field_78123_h.func_78792_a(this.shoeSpike1);
        this.shoeSpike2 = new ModelRenderer(this, 32, 10);
        this.shoeSpike2.func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.5F);
        this.shoeSpike2.func_78793_a(2.5F, 11.0F, 2.0F);
        this.shoeSpike2.field_78796_g = 0.7853982F;
        this.field_78124_i.func_78792_a(this.shoeSpike2);
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
