package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import twilightforest.entity.EntityTFTowerGolem;

public class ModelTFTowerGolem extends ModelBase {

    ModelRenderer head;
    ModelRenderer jaw;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer leftleg;
    ModelRenderer leftfoot;
    ModelRenderer ribs;
    ModelRenderer hips;
    ModelRenderer rightfoot;
    ModelRenderer rightleg;
    ModelRenderer spine;

    public ModelTFTowerGolem() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.func_78793_a(0.0F, -11.0F, -2.0F);
        this.head.func_78784_a(0, 0).func_78789_a(-3.5F, -10.0F, -3.0F, 7, 8, 6);
        this.head.func_78784_a(0, 14).func_78789_a(-4.0F, -6.0F, -3.5F, 8, 4, 6);
        this.body = new ModelRenderer(this, 0, 26);
        this.body.func_78789_a(-8.0F, 0.0F, -5.0F, 16, 10, 10);
        this.body.func_78793_a(0.0F, -13.0F, 0.0F);
        this.ribs = new ModelRenderer(this, 0, 46);
        this.ribs.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 6, 6);
        this.ribs.func_78793_a(0.0F, -3.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 52, 0);
        this.rightarm.func_78793_a(-8.0F, -12.0F, 0.0F);
        this.rightarm.func_78784_a(52, 0).func_78789_a(-5.0F, -2.0F, -1.5F, 3, 14, 3);
        this.rightarm.func_78784_a(52, 17).func_78789_a(-7.0F, 12.0F, -3.0F, 6, 12, 6);
        this.rightarm.func_78784_a(52, 36).func_78789_a(-7.0F, -3.0F, -3.5F, 7, 2, 7);
        this.rightarm.func_78784_a(52, 45).func_78789_a(-7.0F, -1.0F, -3.5F, 7, 5, 2);
        this.rightarm.func_78784_a(52, 45).func_78789_a(-7.0F, -1.0F, 1.5F, 7, 5, 2);
        this.rightarm.func_78784_a(52, 54).func_78789_a(-2.0F, -1.0F, -2.0F, 2, 5, 3);
        this.leftarm = new ModelRenderer(this, 52, 0);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78793_a(8.0F, -12.0F, 0.0F);
        this.leftarm.func_78784_a(52, 0).func_78789_a(2.0F, -2.0F, -1.5F, 3, 14, 3);
        this.leftarm.func_78784_a(52, 17).func_78789_a(1.0F, 12.0F, -3.0F, 6, 12, 6);
        this.leftarm.func_78784_a(52, 36).func_78789_a(0.0F, -3.0F, -3.5F, 7, 2, 7);
        this.leftarm.func_78784_a(52, 45).func_78789_a(0.0F, -1.0F, -3.5F, 7, 5, 2);
        this.leftarm.func_78784_a(52, 45).func_78789_a(0.0F, -1.0F, 1.5F, 7, 5, 2);
        this.leftarm.func_78784_a(52, 54).func_78789_a(0.0F, -1.0F, -2.0F, 2, 5, 3);
        this.hips = new ModelRenderer(this, 84, 25);
        this.hips.func_78789_a(-5.0F, 0.0F, -2.0F, 10, 3, 4);
        this.hips.func_78793_a(0.0F, 1.0F, 0.0F);
        this.spine = new ModelRenderer(this, 84, 18);
        this.spine.func_78789_a(-1.5F, 0.0F, -1.5F, 3, 4, 3);
        this.spine.func_78793_a(0.0F, -3.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 84, 32);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78793_a(1.0F, 2.0F, 0.0F);
        this.leftleg.func_78784_a(84, 32).func_78789_a(0.0F, 0.0F, -1.5F, 3, 8, 3);
        this.leftleg.func_78784_a(84, 43).func_78789_a(-0.5F, 8.0F, -4.0F, 6, 14, 7);
        this.rightleg = new ModelRenderer(this, 84, 32);
        this.rightleg.func_78793_a(-1.0F, 2.0F, 0.0F);
        this.rightleg.func_78784_a(84, 32).func_78789_a(-3.0F, 0.0F, -1.5F, 3, 8, 3);
        this.rightleg.func_78784_a(84, 43).func_78789_a(-5.5F, 8.0F, -4.0F, 6, 14, 7);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        this.ribs.func_78785_a(f5);
        this.hips.func_78785_a(f5);
        this.spine.func_78785_a(f5);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78796_g = par4 / 57.295776F;
        this.head.field_78795_f = par5 / 57.295776F;
        this.leftleg.field_78795_f = -1.5F * this.func_78172_a(par1, 13.0F) * par2;
        this.rightleg.field_78795_f = 1.5F * this.func_78172_a(par1, 13.0F) * par2;
        this.leftleg.field_78796_g = 0.0F;
        this.rightleg.field_78796_g = 0.0F;
        this.rightarm.field_78808_h = MathHelper.func_76134_b(par3 * 0.09F) * 0.05F + 0.05F;
        this.leftarm.field_78808_h = -MathHelper.func_76134_b(par3 * 0.09F) * 0.05F - 0.05F;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityTFTowerGolem entitytftowergolem = (EntityTFTowerGolem) par1EntityLiving;
        int i = entitytftowergolem.getAttackTimer();

        if (i > 0) {
            this.rightarm.field_78795_f = -2.0F + 1.5F * this.func_78172_a((float) i - par4, 10.0F);
            this.leftarm.field_78795_f = -2.0F + 1.5F * this.func_78172_a((float) i - par4, 10.0F);
        } else {
            this.rightarm.field_78795_f = (-0.2F + 1.5F * this.func_78172_a(par2, 25.0F)) * par3;
            this.leftarm.field_78795_f = (-0.2F - 1.5F * this.func_78172_a(par2, 25.0F)) * par3;
        }

    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
    }
}
