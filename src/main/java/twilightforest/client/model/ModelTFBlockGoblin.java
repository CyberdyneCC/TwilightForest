package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFBlockGoblin extends ModelBiped {

    public ModelRenderer helmet;
    ModelRenderer block;
    ModelRenderer[] spikes = new ModelRenderer[27];

    public ModelTFBlockGoblin() {
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78790_a(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.field_78116_c.func_78793_a(0.0F, 11.0F, 0.0F);
        this.field_78114_d = new ModelRenderer(this, 0, 0);
        this.field_78114_d.func_78790_a(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.5F);
        this.field_78114_d.func_78793_a(0.0F, 11.0F, 0.0F);
        this.helmet = new ModelRenderer(this, 24, 0);
        this.helmet.func_78789_a(-2.5F, -9.0F, -2.5F, 5, 9, 5);
        this.helmet.field_78796_g = 0.7853982F;
        this.field_78114_d.func_78792_a(this.helmet);
        this.field_78115_e = new ModelRenderer(this, 0, 21);
        this.field_78115_e.func_78790_a(-3.5F, 0.0F, -2.0F, 7, 7, 4, 0.0F);
        this.field_78115_e.func_78793_a(0.0F, 11.0F, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 52, 0);
        this.field_78112_f.func_78790_a(-3.0F, -1.0F, -2.0F, 3, 12, 3, 0.0F);
        this.field_78112_f.func_78793_a(-3.5F, 12.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 52, 0);
        this.field_78113_g.func_78790_a(0.0F, -1.0F, -1.5F, 3, 12, 3, 0.0F);
        this.field_78113_g.func_78793_a(3.5F, 12.0F, 0.0F);
        this.field_78123_h = new ModelRenderer(this, 0, 12);
        this.field_78123_h.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.field_78123_h.func_78793_a(-2.0F, 18.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 12);
        this.field_78124_i.func_78790_a(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.field_78124_i.func_78793_a(2.0F, 18.0F, 0.0F);
        this.block = new ModelRenderer(this, 32, 16);
        this.block.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.block.func_78793_a(6.0F, 0.0F, 0.0F);

        for (int fourtyFive = 0; fourtyFive < this.spikes.length; ++fourtyFive) {
            this.spikes[fourtyFive] = new ModelRenderer(this, 56, 16);
            this.spikes[fourtyFive].func_78790_a(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
            this.block.func_78792_a(this.spikes[fourtyFive]);
        }

        this.spikes[2].field_78800_c = 4.0F;
        this.spikes[3].field_78800_c = 4.0F;
        this.spikes[4].field_78800_c = 4.0F;
        this.spikes[11].field_78800_c = 4.0F;
        this.spikes[12].field_78800_c = 5.0F;
        this.spikes[13].field_78800_c = 4.0F;
        this.spikes[20].field_78800_c = 4.0F;
        this.spikes[21].field_78800_c = 4.0F;
        this.spikes[22].field_78800_c = 4.0F;
        this.spikes[6].field_78800_c = -4.0F;
        this.spikes[7].field_78800_c = -4.0F;
        this.spikes[8].field_78800_c = -4.0F;
        this.spikes[15].field_78800_c = -4.0F;
        this.spikes[16].field_78800_c = -5.0F;
        this.spikes[17].field_78800_c = -4.0F;
        this.spikes[24].field_78800_c = -4.0F;
        this.spikes[25].field_78800_c = -4.0F;
        this.spikes[26].field_78800_c = -4.0F;
        this.spikes[0].field_78797_d = -9.0F;
        this.spikes[1].field_78797_d = -8.0F;
        this.spikes[2].field_78797_d = -8.0F;
        this.spikes[3].field_78797_d = -8.0F;
        this.spikes[4].field_78797_d = -8.0F;
        this.spikes[5].field_78797_d = -8.0F;
        this.spikes[6].field_78797_d = -8.0F;
        this.spikes[7].field_78797_d = -8.0F;
        this.spikes[8].field_78797_d = -8.0F;
        this.spikes[9].field_78797_d = -4.0F;
        this.spikes[10].field_78797_d = -4.0F;
        this.spikes[11].field_78797_d = -4.0F;
        this.spikes[12].field_78797_d = -4.0F;
        this.spikes[13].field_78797_d = -4.0F;
        this.spikes[14].field_78797_d = -4.0F;
        this.spikes[15].field_78797_d = -4.0F;
        this.spikes[16].field_78797_d = -4.0F;
        this.spikes[17].field_78797_d = -4.0F;
        this.spikes[18].field_78797_d = 1.0F;
        this.spikes[1].field_78798_e = 4.0F;
        this.spikes[2].field_78798_e = 4.0F;
        this.spikes[8].field_78798_e = 4.0F;
        this.spikes[10].field_78798_e = 4.0F;
        this.spikes[11].field_78798_e = 5.0F;
        this.spikes[17].field_78798_e = 4.0F;
        this.spikes[19].field_78798_e = 4.0F;
        this.spikes[20].field_78798_e = 4.0F;
        this.spikes[26].field_78798_e = 4.0F;
        this.spikes[4].field_78798_e = -4.0F;
        this.spikes[5].field_78798_e = -4.0F;
        this.spikes[6].field_78798_e = -4.0F;
        this.spikes[13].field_78798_e = -4.0F;
        this.spikes[14].field_78798_e = -5.0F;
        this.spikes[15].field_78798_e = -4.0F;
        this.spikes[22].field_78798_e = -4.0F;
        this.spikes[23].field_78798_e = -4.0F;
        this.spikes[24].field_78798_e = -4.0F;
        float f = 0.7853982F;

        this.spikes[1].field_78795_f = f;
        this.spikes[5].field_78795_f = f;
        this.spikes[19].field_78795_f = f;
        this.spikes[23].field_78795_f = f;
        this.spikes[11].field_78796_g = f;
        this.spikes[13].field_78796_g = f;
        this.spikes[15].field_78796_g = f;
        this.spikes[17].field_78796_g = f;
        this.spikes[3].field_78808_h = f;
        this.spikes[7].field_78808_h = f;
        this.spikes[21].field_78808_h = f;
        this.spikes[25].field_78808_h = f;
        this.spikes[2].field_78795_f = -0.95993114F;
        this.spikes[2].field_78796_g = f;
        this.spikes[24].field_78795_f = -0.95993114F;
        this.spikes[24].field_78796_g = f;
        this.spikes[4].field_78795_f = -0.6108653F;
        this.spikes[4].field_78796_g = -f;
        this.spikes[26].field_78795_f = -0.6108653F;
        this.spikes[26].field_78796_g = -f;
        this.spikes[6].field_78796_g = f;
        this.spikes[6].field_78795_f = -0.6108653F;
        this.spikes[20].field_78796_g = f;
        this.spikes[20].field_78795_f = -0.6108653F;
        this.spikes[8].field_78795_f = -0.95993114F;
        this.spikes[8].field_78796_g = -f;
        this.spikes[22].field_78795_f = -0.95993114F;
        this.spikes[22].field_78796_g = -f;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    }

    public void func_78087_a(float f, float f1, float f2, float yaw, float pitch, float time, Entity entity) {
        this.field_78118_o = false;
        super.func_78087_a(f, f1, f2, yaw, pitch, time, entity);
        this.field_78116_c.field_78797_d = 11.0F;
        this.field_78114_d.field_78797_d = 11.0F;
        this.field_78115_e.field_78797_d = 11.0F;
        this.field_78123_h.field_78797_d = 18.0F;
        this.field_78124_i.field_78797_d = 18.0F;
        this.field_78112_f.func_78793_a(-3.5F, 12.0F, 0.0F);
        this.field_78112_f.field_78795_f = (float) ((double) this.field_78112_f.field_78795_f + 3.141592653589793D);
        this.field_78113_g.func_78793_a(3.5F, 12.0F, 0.0F);
        this.field_78113_g.field_78795_f = (float) ((double) this.field_78113_g.field_78795_f + 3.141592653589793D);
        float angle = f2 / 4.0F;
        float length = 0.0F;

        this.block.field_78800_c = (float) Math.sin((double) angle) * length;
        this.block.field_78798_e = (float) (-Math.cos((double) angle)) * length;
        this.block.field_78796_g = -angle;
    }
}
