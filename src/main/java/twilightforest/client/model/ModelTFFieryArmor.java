package twilightforest.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelTFFieryArmor extends ModelBiped {

    public ModelTFFieryArmor(int part, float expand) {
        super(expand);
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

        Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0D);
        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0D);
    }
}
