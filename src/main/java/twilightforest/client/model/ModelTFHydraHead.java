package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import twilightforest.entity.boss.EntityTFHydraHead;
import twilightforest.entity.boss.EntityTFHydraPart;

public class ModelTFHydraHead extends ModelBase {

    ModelRenderer head;
    ModelRenderer jaw;
    ModelRenderer frill;

    public ModelTFHydraHead() {
        this.field_78090_t = 512;
        this.field_78089_u = 256;
        this.func_78085_a("head.box", 272, 0);
        this.func_78085_a("head.upperlip", 272, 56);
        this.func_78085_a("head.rearjaw", 272, 132);
        this.func_78085_a("head.fin", 128, 200);
        this.func_78085_a("head.fang1", 272, 156);
        this.func_78085_a("head.fang2", 272, 156);
        this.func_78085_a("head.teeth", 280, 156);
        this.func_78085_a("head.teeth2", 280, 160);
        this.func_78085_a("head.teeth3", 280, 160);
        this.func_78085_a("jaw.jaw", 272, 92);
        this.func_78085_a("jaw.fang1", 272, 156);
        this.func_78085_a("jaw.fang2", 272, 156);
        this.func_78085_a("jaw.teeth", 280, 156);
        this.func_78085_a("jaw.teeth2", 280, 160);
        this.func_78085_a("jaw.teeth3", 280, 160);
        this.func_78085_a("frill.frill", 272, 200);
        this.head = new ModelRenderer(this, "head");
        this.head.func_78786_a("box", -16.0F, -14.0F, -32.0F, 32, 24, 32);
        this.head.func_78786_a("upperlip", -15.0F, -2.0F, -56.0F, 30, 12, 24);
        this.head.func_78786_a("rearjaw", -15.0F, 10.0F, -20.0F, 30, 8, 16);
        this.head.func_78786_a("fin", -2.0F, -30.0F, -12.0F, 4, 24, 24);
        this.head.func_78786_a("fang1", -12.0F, 10.0F, -49.0F, 2, 5, 2);
        this.head.func_78786_a("fang2", 10.0F, 10.0F, -49.0F, 2, 5, 2);
        this.head.func_78786_a("teeth", -8.0F, 9.0F, -49.0F, 16, 2, 2);
        this.head.func_78786_a("teeth2", -10.0F, 9.0F, -45.0F, 2, 2, 16);
        this.head.func_78786_a("teeth3", 8.0F, 9.0F, -45.0F, 2, 2, 16);
        this.head.func_78793_a(0.0F, 0.0F, 0.0F);
        this.jaw = new ModelRenderer(this, "jaw");
        this.jaw.func_78793_a(0.0F, 10.0F, -20.0F);
        this.jaw.func_78786_a("jaw", -15.0F, 0.0F, -32.0F, 30, 8, 32);
        this.jaw.func_78786_a("fang1", -10.0F, -5.0F, -29.0F, 2, 5, 2);
        this.jaw.func_78786_a("fang2", 8.0F, -5.0F, -29.0F, 2, 5, 2);
        this.jaw.func_78786_a("teeth", -8.0F, -1.0F, -29.0F, 16, 2, 2);
        this.jaw.func_78786_a("teeth2", -10.0F, -1.0F, -25.0F, 2, 2, 16);
        this.jaw.func_78786_a("teeth3", 8.0F, -1.0F, -25.0F, 2, 2, 16);
        this.setRotation(this.jaw, 0.0F, 0.0F, 0.0F);
        this.head.func_78792_a(this.jaw);
        this.frill = new ModelRenderer(this, "frill");
        this.frill.func_78793_a(0.0F, 0.0F, -14.0F);
        this.frill.func_78786_a("frill", -24.0F, -40.0F, 0.0F, 48, 48, 4);
        this.setRotation(this.frill, -0.5235988F, 0.0F, 0.0F);
        this.head.func_78792_a(this.frill);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
    }

    public void render(float f5) {
        this.head.func_78785_a(f5);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {}

    public void func_78086_a(EntityLivingBase entityliving, float f, float f1, float time) {
        EntityTFHydraHead whichHead = (EntityTFHydraHead) entityliving;

        this.head.field_78796_g = this.getRotationY(whichHead, time);
        this.head.field_78795_f = this.getRotationX(whichHead, time);
        float mouthOpen = whichHead.getMouthOpen();

        this.head.field_78795_f -= (float) ((double) mouthOpen * 0.2617993877991494D);
        this.jaw.field_78795_f = (float) ((double) mouthOpen * 1.0471975511965976D);
    }

    public void openMouthForTrophy(float mouthOpen) {
        this.head.field_78796_g = 0.0F;
        this.head.field_78795_f = 0.0F;
        this.head.field_78795_f -= (float) ((double) mouthOpen * 0.2617993877991494D);
        this.jaw.field_78795_f = (float) ((double) mouthOpen * 1.0471975511965976D);
    }

    public float getRotationY(EntityTFHydraPart whichHead, float time) {
        float yaw = whichHead.field_70126_B + (whichHead.field_70177_z - whichHead.field_70126_B) * time;

        return yaw / 57.29578F;
    }

    public float getRotationX(EntityTFHydraPart whichHead, float time) {
        return (whichHead.field_70127_C + (whichHead.field_70125_A - whichHead.field_70127_C) * time) / 57.29578F;
    }
}
