package twilightforest.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelTFGhast extends ModelBase {

    ModelRenderer body;
    protected ModelRenderer[] tentacles = new ModelRenderer[9];

    public ModelTFGhast() {
        byte yOffset = -16;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.func_78789_a(-8.0F, -8.0F, -8.0F, 16, 16, 16);
        this.body.field_78797_d += (float) (24 + yOffset);
        Random rand = new Random(1660L);

        for (int i = 0; i < this.tentacles.length; ++i) {
            this.makeTentacle(yOffset, rand, i);
        }

    }

    protected void makeTentacle(byte yOffset, Random random, int i) {
        this.tentacles[i] = new ModelRenderer(this, 0, 0);
        float xPoint = (((float) (i % 3) - (float) (i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
        float zPoint = ((float) (i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
        int length = random.nextInt(7) + 8;

        this.tentacles[i].func_78789_a(-1.0F, 0.0F, -1.0F, 2, length, 2);
        this.tentacles[i].field_78800_c = xPoint;
        this.tentacles[i].field_78798_e = zPoint;
        this.tentacles[i].field_78797_d = (float) (23 + yOffset);
        this.body.func_78792_a(this.tentacles[i]);
    }

    public void func_78087_a(float par1, float par2, float par3, float yaw, float pitch, float par6, Entity par7Entity) {
        for (int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].field_78795_f = 0.2F * MathHelper.func_76126_a(par3 * 0.3F + (float) i) + 0.4F;
        }

        this.body.field_78795_f = pitch / 57.295776F;
        this.body.field_78796_g = yaw / 57.295776F;
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float yaw, float pitch, float time) {
        this.func_78087_a(par2, par3, par4, yaw, pitch, time, par1Entity);
        this.body.func_78785_a(time);
    }
}
