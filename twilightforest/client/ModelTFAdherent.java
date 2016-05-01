package twilightforest.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelTFAdherent extends ModelBiped {

    ModelRenderer leftSleeve;
    ModelRenderer rightSleeve;

    public ModelTFAdherent() {
        this.field_78114_d = new ModelRenderer(this, 0, 0);
        this.field_78124_i = new ModelRenderer(this, 0, 0);
        this.field_78123_h = new ModelRenderer(this, 0, 0);
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.field_78116_c.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 32, 0);
        this.field_78115_e.func_78789_a(-4.0F, 0.0F, -2.0F, 8, 24, 4);
        this.field_78115_e.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78112_f = new ModelRenderer(this, 0, 16);
        this.field_78112_f.func_78789_a(-3.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78112_f.func_78793_a(-5.0F, 2.0F, 0.0F);
        this.field_78113_g = new ModelRenderer(this, 0, 16);
        this.field_78113_g.func_78789_a(-1.0F, -2.0F, -2.0F, 4, 12, 4);
        this.field_78113_g.func_78793_a(5.0F, 2.0F, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 16, 16);
        this.leftSleeve.func_78789_a(-1.0F, -2.0F, 2.0F, 4, 12, 4);
        this.leftSleeve.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78113_g.func_78792_a(this.leftSleeve);
        this.rightSleeve = new ModelRenderer(this, 16, 16);
        this.rightSleeve.func_78789_a(-3.0F, -2.0F, 2.0F, 4, 12, 4);
        this.rightSleeve.func_78793_a(0.0F, 0.0F, 0.0F);
        this.field_78112_f.func_78792_a(this.rightSleeve);
    }
}
