package twilightforest.client.model;

import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class ModelTFMosquitoSwarm extends ModelBase {

    ModelRenderer core;
    ModelRenderer node1;
    ModelRenderer node2;
    ModelRenderer node3;
    ModelRenderer node4;
    ModelRenderer node5;
    ModelRenderer node6;
    Random rand = new Random();

    public ModelTFMosquitoSwarm() {
        this.core = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.core.func_78789_a(-4.0F, 0.0F, -2.0F, 1, 1, 1);
        this.core.func_78793_a(0.0F, -4.0F, 0.0F);
        this.node1 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node1.func_78789_a(-5.5F, -5.0F, -13.0F, 1, 1, 1);
        this.node1.func_78793_a(2.0F, -1.0F, -6.0F);
        this.core.func_78792_a(this.node1);
        this.node2 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node2.func_78789_a(-5.5F, -13.0F, -5.0F, 1, 1, 1);
        this.node2.func_78793_a(0.0F, -7.0F, -1.0F);
        this.core.func_78792_a(this.node2);
        this.node3 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node3.func_78789_a(-13.0F, -5.0F, -5.0F, 1, 1, 1);
        this.node3.func_78793_a(5.0F, -2.0F, -1.0F);
        this.core.func_78792_a(this.node3);
        this.node4 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node4.func_78789_a(-5.5F, -5.0F, -13.0F, 1, 1, 1);
        this.node4.func_78793_a(2.0F, -1.0F, -6.0F);
        this.core.func_78792_a(this.node4);
        this.node5 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node5.func_78789_a(-5.5F, -13.0F, -5.0F, 1, 1, 1);
        this.node5.func_78793_a(0.0F, -7.0F, -1.0F);
        this.core.func_78792_a(this.node5);
        this.node6 = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
        this.node6.func_78789_a(-13.0F, -5.0F, -5.0F, 1, 1, 1);
        this.node6.func_78793_a(5.0F, -2.0F, -1.0F);
        this.core.func_78792_a(this.node6);
        this.addBugsToNodes(this.node1);
        this.addBugsToNodes(this.node2);
        this.addBugsToNodes(this.node3);
        this.addBugsToNodes(this.node4);
        this.addBugsToNodes(this.node5);
        this.addBugsToNodes(this.node6);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.core.func_78785_a(par7 / 2.0F);
    }

    public void addBugsToNodes(ModelRenderer node) {
        byte bugs = 16;

        for (int i = 0; i < bugs; ++i) {
            Vec3 vec = Vec3.func_72443_a(11.0D, 0.0D, 0.0D);
            float rotateY = (float) i * (360.0F / (float) bugs) * 3.141593F / 180.0F;

            vec.func_72442_b(rotateY);
            ModelRenderer bug = new ModelRenderer(this, this.rand.nextInt(28), this.rand.nextInt(28));
            float bugX = (this.rand.nextFloat() - this.rand.nextFloat()) * 4.0F;
            float bugY = (this.rand.nextFloat() - this.rand.nextFloat()) * 4.0F;
            float bugZ = (this.rand.nextFloat() - this.rand.nextFloat()) * 4.0F;

            bug.func_78789_a(bugX, bugY, bugZ, 1, 1, 1);
            bug.func_78793_a((float) vec.field_72450_a, (float) vec.field_72448_b, (float) vec.field_72449_c);
            bug.field_78796_g = rotateY;
            node.func_78792_a(bug);
        }

    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float time) {
        this.core.field_78796_g = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
        this.core.field_78795_f = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.core.field_78808_h = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node1.field_78796_g = ((float) par1EntityLiving.field_70173_aa + time) / 2.0F;
        this.node1.field_78795_f = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 6.0F) / 2.0F;
        this.node1.field_78808_h = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node2.field_78796_g = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 2.0F) / 3.0F;
        this.node2.field_78795_f = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
        this.node2.field_78808_h = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node3.field_78796_g = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 7.0F) / 3.0F;
        this.node3.field_78795_f = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 4.0F) / 2.0F;
        this.node3.field_78808_h = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
        this.node4.field_78795_f = ((float) par1EntityLiving.field_70173_aa + time) / 2.0F;
        this.node4.field_78808_h = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 6.0F) / 2.0F;
        this.node4.field_78796_g = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node5.field_78808_h = MathHelper.func_76126_a(((float) par1EntityLiving.field_70173_aa + time) / 2.0F) / 3.0F;
        this.node5.field_78796_g = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node5.field_78795_f = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 5.0F) / 4.0F;
        this.node6.field_78808_h = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 7.0F) / 3.0F;
        this.node6.field_78795_f = MathHelper.func_76134_b(((float) par1EntityLiving.field_70173_aa + time) / 4.0F) / 2.0F;
        this.node6.field_78796_g = ((float) par1EntityLiving.field_70173_aa + time) / 5.0F;
    }
}
