package twilightforest.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelTFKobold extends ModelBiped {

    ModelRenderer rightear;
    ModelRenderer leftear;
    ModelRenderer snout;
    ModelRenderer jaw;
    boolean isJumping = false;

    public ModelTFKobold() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.func_78789_a(-3.5F, -7.0F, -3.0F, 7, 6, 6);
        this.field_78116_c.func_78793_a(0.0F, 13.0F, 0.0F);
        this.field_78115_e = new ModelRenderer(this, 12, 19);
        this.field_78115_e.func_78789_a(0.0F, 0.0F, 0.0F, 7, 7, 4);
        this.field_78115_e.func_78793_a(-3.5F, 12.0F, -2.0F);
        this.field_78112_f = new ModelRenderer(this, 36, 17);
        this.field_78112_f.func_78789_a(-3.0F, -1.0F, -1.5F, 3, 7, 3);
        this.field_78112_f.func_78793_a(-3.5F, 12.0F, 0.0F);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g = new ModelRenderer(this, 36, 17);
        this.field_78113_g.func_78789_a(0.0F, -1.0F, -1.5F, 3, 7, 3);
        this.field_78113_g.func_78793_a(3.5F, 12.0F, 0.0F);
        this.field_78113_g.field_78809_i = false;
        this.field_78123_h = new ModelRenderer(this, 0, 20);
        this.field_78123_h.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 5, 3);
        this.field_78123_h.func_78793_a(-2.0F, 19.0F, 0.0F);
        this.field_78124_i = new ModelRenderer(this, 0, 20);
        this.field_78124_i.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 5, 3);
        this.field_78124_i.func_78793_a(2.0F, 19.0F, 0.0F);
        this.rightear = new ModelRenderer(this, 48, 20);
        this.rightear.func_78789_a(0.0F, -4.0F, 0.0F, 4, 4, 1);
        this.rightear.func_78793_a(3.5F, -3.0F, -1.0F);
        this.rightear.field_78796_g = 0.2617994F;
        this.rightear.field_78808_h = -0.3490659F;
        this.field_78116_c.func_78792_a(this.rightear);
        this.leftear = new ModelRenderer(this, 48, 25);
        this.leftear.func_78789_a(-4.0F, -4.0F, 0.0F, 4, 4, 1);
        this.leftear.func_78793_a(-3.5F, -3.0F, -1.0F);
        this.leftear.field_78796_g = -0.2617994F;
        this.leftear.field_78808_h = 0.3490659F;
        this.field_78116_c.func_78792_a(this.leftear);
        this.snout = new ModelRenderer(this, 28, 0);
        this.snout.func_78789_a(-1.5F, -2.0F, -2.0F, 3, 2, 3);
        this.snout.func_78793_a(0.0F, -2.0F, -3.0F);
        this.field_78116_c.func_78792_a(this.snout);
        this.jaw = new ModelRenderer(this, 28, 5);
        this.jaw.func_78789_a(-1.5F, 0.0F, -2.0F, 3, 1, 3);
        this.jaw.func_78793_a(0.0F, -2.0F, -3.0F);
        this.jaw.field_78795_f = 0.20944F;
        this.field_78116_c.func_78792_a(this.jaw);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.field_78116_c.field_78796_g = par4 / 57.295776F;
        this.field_78116_c.field_78795_f = par5 / 57.295776F;
        this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F;
        this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.field_78112_f.field_78808_h = 0.0F;
        this.field_78113_g.field_78808_h = 0.0F;
        this.field_78112_f.field_78795_f = -0.47123894F;
        this.field_78113_g.field_78795_f = -0.47123894F;
        this.field_78123_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
        this.field_78124_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
        this.field_78123_h.field_78796_g = 0.0F;
        this.field_78124_i.field_78796_g = 0.0F;
        this.field_78112_f.field_78808_h += MathHelper.func_76134_b(par3 * 0.19F) * 0.15F + 0.05F;
        this.field_78113_g.field_78808_h -= MathHelper.func_76134_b(par3 * 0.19F) * 0.15F + 0.05F;
        this.field_78112_f.field_78795_f += MathHelper.func_76126_a(par3 * 0.267F) * 0.25F;
        this.field_78113_g.field_78795_f -= MathHelper.func_76126_a(par3 * 0.267F) * 0.25F;
        if (this.isJumping) {
            this.jaw.field_78795_f = 1.44F;
        } else {
            this.jaw.field_78795_f = 0.20944F;
        }

    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float partialTick) {
        this.isJumping = par1EntityLiving.field_70181_x > 0.0D;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.field_78116_c.func_78785_a(f5);
        this.field_78115_e.func_78785_a(f5);
        this.field_78112_f.func_78785_a(f5);
        this.field_78113_g.func_78785_a(f5);
        this.field_78123_h.func_78785_a(f5);
        this.field_78124_i.func_78785_a(f5);
    }
}
