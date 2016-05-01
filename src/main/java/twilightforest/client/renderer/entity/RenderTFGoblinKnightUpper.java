package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import twilightforest.entity.EntityTFGoblinKnightUpper;

public class RenderTFGoblinKnightUpper extends RenderTFBiped {

    public RenderTFGoblinKnightUpper(ModelBiped par1ModelBiped, float par2) {
        super(par1ModelBiped, par2, "doublegoblin.png");
    }

    protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        this.rotateGoblinKnight((EntityTFGoblinKnightUpper) par1EntityLiving, par2, par3, par4);
    }

    protected void rotateGoblinKnight(EntityTFGoblinKnightUpper upperKnight, float par2, float par3, float par4) {
        super.func_77043_a(upperKnight, par2, par3, par4);
        if (upperKnight.heavySpearTimer > 0) {
            GL11.glRotatef(this.getPitchForAttack((float) (60 - upperKnight.heavySpearTimer) + par4), 1.0F, 0.0F, 0.0F);
        }

    }

    public float getPitchForAttack(float attackTime) {
        return attackTime <= 10.0F ? attackTime * 3.0F : (attackTime > 10.0F && attackTime <= 30.0F ? 30.0F : (attackTime > 30.0F && attackTime <= 33.0F ? (attackTime - 30.0F) * -25.0F + 30.0F : (attackTime > 33.0F && attackTime <= 50.0F ? -45.0F : (attackTime > 50.0F && attackTime <= 60.0F ? (10.0F - (attackTime - 50.0F)) * -4.5F : 0.0F))));
    }
}
