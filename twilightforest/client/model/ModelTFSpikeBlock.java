package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTFSpikeBlock extends ModelBase {

    ModelRenderer block = new ModelRenderer(this, 32, 16);
    ModelRenderer[] spikes = new ModelRenderer[27];

    public ModelTFSpikeBlock() {
        this.block.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.block.func_78793_a(0.0F, 0.0F, 0.0F);

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
        this.block.func_78785_a(f5);
    }
}
