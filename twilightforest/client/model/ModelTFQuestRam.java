package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.passive.EntityTFQuestRam;

public class ModelTFQuestRam extends ModelBase {

    ModelRenderer frontbody;
    ModelRenderer rearbody;
    ModelRenderer leg1;
    ModelRenderer haunch1;
    ModelRenderer leg2;
    ModelRenderer haunch2;
    ModelRenderer leg3;
    ModelRenderer haunch3;
    ModelRenderer leg4;
    ModelRenderer haunch4;
    ModelRenderer neck;
    ModelRenderer nose;
    ModelRenderer head;
    ModelRenderer[] segments;
    boolean[] segmentEnabled;
    int[] colorOrder = new int[] { 0, 8, 7, 15, 14, 1, 4, 5, 13, 3, 9, 11, 10, 2, 6, 12};

    public ModelTFQuestRam() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.func_78085_a("head.head", 0, 70);
        this.func_78085_a("head.horn1a", 0, 94);
        this.func_78085_a("head.horn1b", 20, 96);
        this.func_78085_a("head.horn1c", 34, 95);
        this.func_78085_a("head.horn1d", 46, 98);
        this.func_78085_a("head.horn1e", 58, 95);
        this.func_78085_a("head.horn1f", 76, 95);
        this.func_78085_a("head.horn1g", 88, 97);
        this.func_78085_a("head.horn1a", 0, 94);
        this.func_78085_a("head.horn1b", 20, 96);
        this.func_78085_a("head.horn1c", 34, 95);
        this.func_78085_a("head.horn1d", 46, 98);
        this.func_78085_a("head.horn1e", 58, 95);
        this.func_78085_a("head.horn1f", 76, 95);
        this.func_78085_a("head.horn1g", 88, 97);
        this.frontbody = new ModelRenderer(this, 0, 0);
        this.frontbody.func_78789_a(-9.0F, -7.5F, -15.0F, 18, 15, 15);
        this.frontbody.func_78793_a(0.0F, -1.0F, 2.0F);
        this.rearbody = new ModelRenderer(this, 0, 30);
        this.rearbody.func_78789_a(-9.0F, -7.5F, 0.0F, 18, 15, 15);
        this.rearbody.func_78793_a(0.0F, -1.0F, 4.0F);
        this.leg1 = new ModelRenderer(this, 66, 0);
        this.leg1.func_78789_a(-3.0F, 10.0F, -3.0F, 6, 12, 6);
        this.leg1.func_78793_a(-6.0F, 2.0F, 13.0F);
        this.haunch1 = new ModelRenderer(this, 90, 0);
        this.haunch1.func_78789_a(-3.5F, 0.0F, -6.0F, 7, 10, 10);
        this.haunch1.func_78793_a(-6.0F, 2.0F, 13.0F);
        this.leg2 = new ModelRenderer(this, 66, 0);
        this.leg2.func_78789_a(-3.0F, 10.0F, -3.0F, 6, 12, 6);
        this.leg2.func_78793_a(6.0F, 2.0F, 13.0F);
        this.haunch2 = new ModelRenderer(this, 90, 0);
        this.haunch2.func_78789_a(-3.5F, 0.0F, -6.0F, 7, 10, 10);
        this.haunch2.func_78793_a(6.0F, 2.0F, 13.0F);
        this.leg3 = new ModelRenderer(this, 66, 18);
        this.leg3.func_78789_a(-3.0F, 10.0F, -3.0F, 6, 13, 6);
        this.leg3.func_78793_a(-6.0F, 1.0F, -8.0F);
        this.haunch3 = new ModelRenderer(this, 90, 20);
        this.haunch3.func_78789_a(-3.5F, 0.0F, -4.0F, 7, 10, 7);
        this.haunch3.func_78793_a(-6.0F, 1.0F, -8.0F);
        this.leg4 = new ModelRenderer(this, 66, 18);
        this.leg4.func_78789_a(-3.0F, 10.0F, -3.0F, 6, 13, 6);
        this.leg4.func_78793_a(6.0F, 1.0F, -8.0F);
        this.haunch4 = new ModelRenderer(this, 90, 20);
        this.haunch4.func_78789_a(-3.5F, 0.0F, -4.0F, 7, 10, 7);
        this.haunch4.func_78793_a(6.0F, 1.0F, -8.0F);
        this.neck = new ModelRenderer(this, 66, 37);
        this.neck.func_78789_a(-5.5F, -8.0F, -8.0F, 11, 14, 12);
        this.neck.func_78793_a(0.0F, -8.0F, -7.0F);
        this.setRotation(this.neck, 0.2617994F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, "head");
        this.head.func_78793_a(0.0F, -13.0F, -5.0F);
        this.head.func_78786_a("head", -6.0F, -4.5F, -15.0F, 12, 9, 15);
        this.head.func_78786_a("horn1a", 5.0F, -9.0F, -7.0F, 4, 4, 6);
        this.head.func_78786_a("horn1b", 7.0F, -8.0F, -2.0F, 3, 4, 4);
        this.head.func_78786_a("horn1c", 8.0F, -6.0F, 0.0F, 3, 6, 3);
        this.head.func_78786_a("horn1d", 9.5F, -2.0F, -2.0F, 3, 3, 3);
        this.head.func_78786_a("horn1e", 11.0F, 0.0F, -7.0F, 3, 3, 6);
        this.head.func_78786_a("horn1f", 12.0F, -4.0F, -9.0F, 3, 6, 3);
        this.head.func_78786_a("horn1g", 13.0F, -6.0F, -7.0F, 3, 3, 4);
        this.head.func_78786_a("horn1a", -9.0F, -9.0F, -7.0F, 4, 4, 6);
        this.head.func_78786_a("horn1b", -10.0F, -8.0F, -2.0F, 3, 4, 4);
        this.head.func_78786_a("horn1c", -11.0F, -6.0F, 0.0F, 3, 6, 3);
        this.head.func_78786_a("horn1d", -12.5F, -2.0F, -2.0F, 3, 3, 3);
        this.head.func_78786_a("horn1e", -14.0F, 0.0F, -7.0F, 3, 3, 6);
        this.head.func_78786_a("horn1f", -15.0F, -4.0F, -9.0F, 3, 6, 3);
        this.head.func_78786_a("horn1g", -16.0F, -6.0F, -7.0F, 3, 3, 4);
        this.nose = new ModelRenderer(this, 54, 73);
        this.nose.func_78789_a(-5.5F, -5.0F, -13.0F, 11, 9, 12);
        this.nose.func_78793_a(0.0F, -7.0F, -1.0F);
        this.nose.func_78787_b(128, 128);
        this.setRotation(this.nose, 0.5235988F, 0.0F, 0.0F);
        this.head.func_78792_a(this.nose);
        this.segments = new ModelRenderer[16];
        this.segmentEnabled = new boolean[16];

        for (int i = 0; i < 16; ++i) {
            this.segments[i] = new ModelRenderer(this, 0, 104);
            this.segments[i].func_78789_a(-9.0F, -7.5F, 0.0F, 18, 15, 2);
            this.segments[i].func_78793_a(0.0F, -1.0F, 2.0F);
            this.segmentEnabled[i] = false;
        }

    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.frontbody.func_78785_a(f5);
        this.rearbody.func_78785_a(f5);
        this.leg1.func_78785_a(f5);
        this.haunch1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.haunch2.func_78785_a(f5);
        this.leg3.func_78785_a(f5);
        this.haunch3.func_78785_a(f5);
        this.leg4.func_78785_a(f5);
        this.haunch4.func_78785_a(f5);
        this.neck.func_78785_a(f5);
        this.head.func_78785_a(f5);

        for (int i = 0; i < 16; ++i) {
            if (this.segmentEnabled[i]) {
                float f = 1.0F;

                GL11.glColor3f(f * EntitySheep.field_70898_d[i][0], f * EntitySheep.field_70898_d[i][1], f * EntitySheep.field_70898_d[i][2]);
                this.segments[i].func_78785_a(f5);
            }
        }

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.head.field_78795_f = par5 / 57.295776F;
        this.head.field_78796_g = par4 / 57.295776F;
        this.neck.field_78796_g = this.head.field_78796_g;
        this.leg1.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
        this.leg2.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2 * 0.5F;
        this.leg3.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2 * 0.5F;
        this.leg4.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2 * 0.5F;
        this.haunch1.field_78795_f = this.leg1.field_78795_f;
        this.haunch2.field_78795_f = this.leg2.field_78795_f;
        this.haunch3.field_78795_f = this.leg3.field_78795_f;
        this.haunch4.field_78795_f = this.leg4.field_78795_f;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float partialTick) {
        EntityTFQuestRam ram = (EntityTFQuestRam) par1EntityLiving;
        int count = ram.countColorsSet();

        this.rearbody.field_78798_e = (float) (2 + 2 * count);
        this.leg1.field_78798_e = (float) (11 + 2 * count);
        this.leg2.field_78798_e = (float) (11 + 2 * count);
        this.haunch1.field_78798_e = (float) (11 + 2 * count);
        this.haunch2.field_78798_e = (float) (11 + 2 * count);
        int segmentOffset = 2;
        int[] aint = this.colorOrder;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int color = aint[j];

            if (ram.isColorPresent(color)) {
                this.segmentEnabled[color] = true;
                this.segments[color].field_78798_e = (float) segmentOffset;
                segmentOffset += 2;
            } else {
                this.segmentEnabled[color] = false;
            }
        }

    }
}
