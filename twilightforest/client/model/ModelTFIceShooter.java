package twilightforest.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelTFIceShooter extends ModelTFIceExploder {

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float time) {
        for (int i = 0; i < this.spikes.length; ++i) {
            this.spikes[i].field_78796_g = 1.570795F + MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) * 0.5F;
            this.spikes[i].field_78795_f = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
            this.spikes[i].field_78808_h = MathHelper.func_76134_b((float) i / 5.0F) / 4.0F;
            this.spikes[i].field_78795_f = (float) ((double) this.spikes[i].field_78795_f + (double) i * 0.39269908169872414D);
            ((ModelRenderer) this.spikes[i].field_78805_m.get(0)).field_78797_d = 9.5F + MathHelper.func_76126_a(((float) (i + par1EntityLiving.field_70173_aa) + time) / 3.0F) * 3.0F;
        }

    }
}
