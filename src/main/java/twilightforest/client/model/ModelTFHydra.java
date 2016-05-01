package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import twilightforest.entity.boss.EntityTFHydra;
import twilightforest.entity.boss.EntityTFHydraPart;

public class ModelTFHydra extends ModelBase {

    ModelRenderer body;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer tail1;
    ModelRenderer tail2;
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer neck1a;
    ModelRenderer neck1b;
    ModelRenderer neck1c;
    ModelRenderer neck1d;
    ModelRenderer head1;
    ModelRenderer jaw1;
    ModelRenderer frill1;
    ModelRenderer neck2a;
    ModelRenderer neck2b;
    ModelRenderer neck2c;
    ModelRenderer neck2d;
    ModelRenderer head2;
    ModelRenderer jaw2;
    ModelRenderer frill2;
    ModelRenderer neck3a;
    ModelRenderer neck3b;
    ModelRenderer neck3c;
    ModelRenderer neck3d;
    ModelRenderer head3;
    ModelRenderer jaw3;
    ModelRenderer frill3;

    public ModelTFHydra() {
        this.field_78090_t = 512;
        this.field_78089_u = 256;
        this.func_78085_a("body.body", 0, 0);
        this.func_78085_a("leg.main", 0, 136);
        this.func_78085_a("leg.toe", 184, 200);
        this.func_78085_a("tail.box", 128, 136);
        this.func_78085_a("tail.fin", 128, 200);
        this.func_78085_a("neck.box", 128, 136);
        this.func_78085_a("neck.fin", 128, 200);
        this.func_78085_a("head.box", 272, 0);
        this.func_78085_a("head.upperlip", 272, 56);
        this.func_78085_a("head.fin", 128, 200);
        this.func_78085_a("jaw.jaw", 272, 92);
        this.func_78085_a("frill.frill", 272, 200);
        this.body = new ModelRenderer(this, "body");
        this.body.func_78793_a(0.0F, -12.0F, 0.0F);
        this.body.func_78786_a("body", -48.0F, 0.0F, 0.0F, 96, 96, 40);
        this.setRotation(this.body, 1.22173F, 0.0F, 0.0F);
        this.leg1 = new ModelRenderer(this, "leg");
        this.leg1.func_78793_a(48.0F, -24.0F, 0.0F);
        this.leg1.func_78786_a("main", -16.0F, 0.0F, -16.0F, 32, 48, 32);
        this.leg1.func_78786_a("toe", -20.0F, 40.0F, -20.0F, 8, 8, 8);
        this.leg1.func_78786_a("toe", -4.0F, 40.0F, -22.0F, 8, 8, 8);
        this.leg1.func_78786_a("toe", 12.0F, 40.0F, -20.0F, 8, 8, 8);
        this.leg2 = new ModelRenderer(this, "leg");
        this.leg2.func_78793_a(-48.0F, -24.0F, 0.0F);
        this.leg2.field_78809_i = true;
        this.leg2.func_78786_a("main", -16.0F, 0.0F, -16.0F, 32, 48, 32);
        this.leg2.func_78786_a("toe", -20.0F, 40.0F, -20.0F, 8, 8, 8);
        this.leg2.func_78786_a("toe", -4.0F, 40.0F, -22.0F, 8, 8, 8);
        this.leg2.func_78786_a("toe", 12.0F, 40.0F, -20.0F, 8, 8, 8);
        this.tail1 = new ModelRenderer(this, "tail");
        this.tail1.func_78793_a(0.0F, 6.0F, 108.0F);
        this.tail1.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.tail1.func_78786_a("fin", -2.0F, -28.0F, -11.0F, 4, 24, 24);
        this.tail2 = new ModelRenderer(this, "tail");
        this.tail2.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.tail2.func_78786_a("fin", -2.0F, -28.0F, -11.0F, 4, 24, 24);
        this.tail2.func_78793_a(0.0F, 7.0F, 142.0F);
        this.tail3 = new ModelRenderer(this, "tail");
        this.tail3.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.tail3.func_78786_a("fin", -2.0F, -28.0F, -11.0F, 4, 24, 24);
        this.tail3.func_78793_a(0.0F, 8.0F, 176.0F);
        this.tail4 = new ModelRenderer(this, "tail");
        this.tail4.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.tail4.func_78786_a("fin", -2.0F, -28.0F, -11.0F, 4, 24, 24);
        this.tail4.func_78793_a(0.0F, 8.0F, 210.0F);
        this.neck1a = new ModelRenderer(this, "neck");
        this.neck1a.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck1a.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck1a.func_78793_a(0.0F, -48.0F, 16.0F);
        this.neck1b = new ModelRenderer(this, "neck");
        this.neck1b.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck1b.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck1b.func_78793_a(0.0F, -68.0F, 0.0F);
        this.neck1c = new ModelRenderer(this, "neck");
        this.neck1c.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck1c.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck1c.func_78793_a(0.0F, -93.0F, -14.0F);
        this.neck1d = new ModelRenderer(this, "neck");
        this.neck1d.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck1d.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck1d.func_78793_a(0.0F, -116.0F, -37.0F);
        this.head1 = new ModelRenderer(this, "head");
        this.head1.func_78786_a("box", -16.0F, -14.0F, -32.0F, 32, 24, 32);
        this.head1.func_78786_a("upperlip", -15.0F, -2.0F, -56.0F, 30, 12, 24);
        this.head1.func_78786_a("fin", -2.0F, -30.0F, -12.0F, 4, 24, 24);
        this.head1.func_78793_a(0.0F, -128.0F, -53.0F);
        this.jaw1 = new ModelRenderer(this, "jaw");
        this.jaw1.func_78793_a(0.0F, 10.0F, -4.0F);
        this.jaw1.func_78786_a("jaw", -15.0F, 0.0F, -48.0F, 30, 8, 48);
        this.setRotation(this.jaw1, 0.0F, 0.0F, 0.0F);
        this.head1.func_78792_a(this.jaw1);
        this.frill1 = new ModelRenderer(this, "frill");
        this.frill1.func_78793_a(0.0F, 0.0F, -10.0F);
        this.frill1.func_78786_a("frill", -24.0F, -40.0F, 0.0F, 48, 48, 4);
        this.setRotation(this.frill1, -0.5235988F, 0.0F, 0.0F);
        this.head1.func_78792_a(this.frill1);
        this.neck2a = new ModelRenderer(this, "neck");
        this.neck2a.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck2a.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck2a.func_78793_a(48.0F, -48.0F, 16.0F);
        this.neck2b = new ModelRenderer(this, "neck");
        this.neck2b.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck2b.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck2b.func_78793_a(71.0F, -68.0F, 0.0F);
        this.neck2c = new ModelRenderer(this, "neck");
        this.neck2c.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck2c.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck2c.func_78793_a(96.0F, -93.0F, -14.0F);
        this.neck2d = new ModelRenderer(this, "neck");
        this.neck2d.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck2d.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck2d.func_78793_a(108.0F, -116.0F, -37.0F);
        this.head2 = new ModelRenderer(this, "head");
        this.head2.func_78786_a("box", -16.0F, -14.0F, -32.0F, 32, 24, 32);
        this.head2.func_78786_a("upperlip", -15.0F, -2.0F, -56.0F, 30, 12, 24);
        this.head2.func_78786_a("fin", -2.0F, -30.0F, -12.0F, 4, 24, 24);
        this.head2.func_78793_a(108.0F, -128.0F, -53.0F);
        this.jaw2 = new ModelRenderer(this, "jaw");
        this.jaw2.func_78793_a(0.0F, 10.0F, -4.0F);
        this.jaw2.func_78786_a("jaw", -15.0F, 0.0F, -48.0F, 30, 8, 48);
        this.setRotation(this.jaw2, 0.0F, 0.0F, 0.0F);
        this.head2.func_78792_a(this.jaw2);
        this.frill2 = new ModelRenderer(this, "frill");
        this.frill2.func_78793_a(0.0F, 0.0F, -10.0F);
        this.frill2.func_78786_a("frill", -24.0F, -40.0F, 0.0F, 48, 48, 4);
        this.setRotation(this.frill2, -0.5235988F, 0.0F, 0.0F);
        this.head2.func_78792_a(this.frill2);
        this.neck3a = new ModelRenderer(this, "neck");
        this.neck3a.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 31);
        this.neck3a.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck3a.func_78793_a(-48.0F, -48.0F, 16.0F);
        this.neck3b = new ModelRenderer(this, "neck");
        this.neck3b.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck3b.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck3b.func_78793_a(-71.0F, -43.0F, 0.0F);
        this.neck3c = new ModelRenderer(this, "neck");
        this.neck3c.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck3c.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck3c.func_78793_a(-96.0F, -33.0F, -14.0F);
        this.neck3d = new ModelRenderer(this, "neck");
        this.neck3d.func_78786_a("box", -16.0F, -16.0F, -16.0F, 32, 32, 32);
        this.neck3d.func_78786_a("fin", -2.0F, -23.0F, 0.0F, 4, 24, 24);
        this.neck3d.func_78793_a(-108.0F, -24.0F, -37.0F);
        this.head3 = new ModelRenderer(this, "head");
        this.head3.func_78786_a("box", -16.0F, -14.0F, -32.0F, 32, 24, 32);
        this.head3.func_78786_a("upperlip", -15.0F, -2.0F, -56.0F, 30, 12, 24);
        this.head3.func_78786_a("fin", -2.0F, -30.0F, -12.0F, 4, 24, 24);
        this.head3.func_78793_a(-108.0F, -24.0F, -53.0F);
        this.jaw3 = new ModelRenderer(this, "jaw");
        this.jaw3.func_78793_a(0.0F, 10.0F, -4.0F);
        this.jaw3.func_78786_a("jaw", -15.0F, 0.0F, -48.0F, 30, 8, 48);
        this.setRotation(this.jaw3, 0.125F, 0.0F, 0.0F);
        this.head3.func_78792_a(this.jaw3);
        this.frill3 = new ModelRenderer(this, "frill");
        this.frill3.func_78793_a(0.0F, 0.0F, -10.0F);
        this.frill3.func_78786_a("frill", -24.0F, -40.0F, 0.0F, 48, 48, 4);
        this.setRotation(this.frill3, -0.5235988F, 0.0F, 0.0F);
        this.head3.func_78792_a(this.frill3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.body.func_78785_a(f5);
        this.leg1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.tail1.func_78785_a(f5);
        this.tail2.func_78785_a(f5);
        this.tail3.func_78785_a(f5);
        this.tail4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.leg1.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
        this.leg2.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.141593F) * 1.4F * f1;
        this.leg1.field_78796_g = 0.0F;
        this.leg2.field_78796_g = 0.0F;
    }

    public float getRotationY(EntityTFHydra hydra, EntityTFHydraPart whichHead, float time) {
        float yawOffset = hydra.field_70760_ar + (hydra.field_70761_aq - hydra.field_70760_ar) * time;
        float yaw = whichHead.field_70126_B + (whichHead.field_70177_z - whichHead.field_70126_B) * time;

        return (yaw - yawOffset) / 57.29578F;
    }

    public float getRotationX(EntityTFHydra hydra, EntityTFHydraPart whichHead, float time) {
        return (whichHead.field_70127_C + (whichHead.field_70125_A - whichHead.field_70127_C) * time) / 57.29578F;
    }
}
