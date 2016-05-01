package twilightforest.client.model;

import java.util.Random;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTFTowerBoss extends ModelTFGhast {

    protected ModelRenderer[][] subTentacles;
    protected ModelRenderer[][] smallTentacles = new ModelRenderer[2][3];

    public ModelTFTowerBoss() {
        for (int i = 0; i < this.smallTentacles.length; ++i) {
            this.makeSmallTentacle(i);
        }

    }

    protected void makeTentacle(byte yOffset, Random random, int num) {
        this.tentacles[num] = new ModelRenderer(this, num % 3, 0);
        byte length = 5;

        this.tentacles[num].func_78789_a(-1.5F, 0.0F, -1.5F, 3, length, 3);
        if (num == 0) {
            this.tentacles[num].field_78800_c = 4.5F;
            this.tentacles[num].field_78798_e = 4.5F;
            this.tentacles[num].field_78797_d = (float) (23 + yOffset);
        }

        if (num == 1) {
            this.tentacles[num].field_78800_c = -4.5F;
            this.tentacles[num].field_78798_e = 4.5F;
            this.tentacles[num].field_78797_d = (float) (23 + yOffset);
        }

        if (num == 2) {
            this.tentacles[num].field_78800_c = 0.0F;
            this.tentacles[num].field_78798_e = 0.0F;
            this.tentacles[num].field_78797_d = (float) (23 + yOffset);
        }

        if (num == 3) {
            this.tentacles[num].field_78800_c = 5.5F;
            this.tentacles[num].field_78798_e = -4.5F;
            this.tentacles[num].field_78797_d = (float) (23 + yOffset);
        }

        if (num == 4) {
            this.tentacles[num].field_78800_c = -5.5F;
            this.tentacles[num].field_78798_e = -4.5F;
            this.tentacles[num].field_78797_d = (float) (23 + yOffset);
        } else if (num == 5) {
            this.tentacles[num].field_78800_c = -7.5F;
            this.tentacles[num].field_78797_d = 3.5F;
            this.tentacles[num].field_78798_e = -1.0F;
            this.tentacles[num].field_78808_h = 0.7853982F;
        } else if (num == 6) {
            this.tentacles[num].field_78800_c = -7.5F;
            this.tentacles[num].field_78797_d = -1.5F;
            this.tentacles[num].field_78798_e = 3.5F;
            this.tentacles[num].field_78808_h = 1.0471976F;
        } else if (num == 7) {
            this.tentacles[num].field_78800_c = 7.5F;
            this.tentacles[num].field_78797_d = 3.5F;
            this.tentacles[num].field_78798_e = -1.0F;
            this.tentacles[num].field_78808_h = -0.7853982F;
        } else if (num == 8) {
            this.tentacles[num].field_78800_c = 7.5F;
            this.tentacles[num].field_78797_d = -1.5F;
            this.tentacles[num].field_78798_e = 3.5F;
            this.tentacles[num].field_78808_h = -1.0471976F;
        }

        if (this.subTentacles == null) {
            this.subTentacles = new ModelRenderer[this.tentacles.length][3];
        }

        for (int i = 0; i < 3; ++i) {
            length = 4;
            this.subTentacles[num][i] = new ModelRenderer(this, num % 4, i * 5 - 1);
            this.subTentacles[num][i].func_78789_a(-1.5F, -0.5F, -1.5F, 3, length, 3);
            this.subTentacles[num][i].field_78800_c = 0.0F;
            this.subTentacles[num][i].field_78798_e = 0.0F;
            this.subTentacles[num][i].field_78797_d = (float) length;
            if (i == 0) {
                this.tentacles[num].func_78792_a(this.subTentacles[num][i]);
            } else {
                this.subTentacles[num][i - 1].func_78792_a(this.subTentacles[num][i]);
            }
        }

        this.body.func_78792_a(this.tentacles[num]);
    }

    protected void makeSmallTentacle(int num) {}

    public void func_78087_a(float par1, float par2, float timeAlive, float yaw, float pitch, float par6, Entity par7Entity) {
        super.func_78087_a(par1, par2, timeAlive, yaw, pitch, par6, par7Entity);

        for (int i = 0; i < this.subTentacles.length; ++i) {
            float wiggle = Math.min(par2, 0.6F);
            float time = (timeAlive + (float) (i * 9)) / 2.0F;

            this.subTentacles[i][0].field_78795_f = (MathHelper.func_76134_b(time * 0.6662F) * 1.0F - 1.0471976F) * wiggle;
            this.subTentacles[i][1].field_78795_f = MathHelper.func_76134_b(time * 0.7774F) * 1.2F * wiggle;
            this.subTentacles[i][2].field_78795_f = MathHelper.func_76134_b(time * 0.8886F + 1.5707964F) * 1.4F * wiggle;
            this.subTentacles[i][0].field_78795_f = 0.2F + MathHelper.func_76134_b(time * 0.3335F) * 0.15F;
            this.subTentacles[i][1].field_78795_f = 0.1F + MathHelper.func_76134_b(time * 0.4445F) * 0.2F;
            this.subTentacles[i][2].field_78795_f = 0.1F + MathHelper.func_76134_b(time * 0.5555F) * 0.25F;
            float yTwist = 0.4F;

            this.tentacles[i].field_78796_g = yTwist * MathHelper.func_76126_a(time * 0.3F);
        }

    }
}
