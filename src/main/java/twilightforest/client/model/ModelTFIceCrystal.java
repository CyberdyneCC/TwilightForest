package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTFIceCrystal extends ModelBase {

    public ModelRenderer[] spikes = new ModelRenderer[16];

    public ModelTFIceCrystal() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        float par1 = 0.0F;
        float par2 = 0.0F;

        for (int i = 0; i < this.spikes.length; ++i) {
            this.spikes[i] = new ModelRenderer(this, 0, 16);
            int spikeLength = i % 2 == 0 ? 6 : 8;

            this.spikes[i].func_78790_a(-1.0F, -1.0F, -1.0F, 2, spikeLength, 2, par1);
            this.spikes[i].func_78793_a(0.0F, 0.0F + par2, 0.0F);
            ModelRenderer cube = new ModelRenderer(this, 8, 16);

            cube.func_78789_a(-1.5F, -1.5F, -1.5F, 3, 3, 3);
            cube.func_78793_a(0.0F, (float) spikeLength, 0.0F);
            cube.field_78808_h = 0.7853982F;
            this.spikes[i].func_78792_a(cube);
        }

    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);

        for (int i = 0; i < this.spikes.length; ++i) {
            if (entity.func_70089_S()) {
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
            }

            this.spikes[i].func_78785_a(f5);
            GL11.glDisable(3042);
        }

    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float time) {
        for (int i = 0; i < this.spikes.length; ++i) {
            this.spikes[i].field_78795_f = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
            this.spikes[i].field_78796_g = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
            this.spikes[i].field_78808_h = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
            this.spikes[i].field_78795_f = (float) ((double) this.spikes[i].field_78795_f + (double) i * 0.39269908169872414D);
            if (i % 4 == 0) {
                ++this.spikes[i].field_78796_g;
            } else if (i % 4 == 2) {
                --this.spikes[i].field_78796_g;
            }
        }

    }
}
