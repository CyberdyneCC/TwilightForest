package twilightforest.client.model;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class ModelTFLoyalZombie extends ModelZombie {

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float time) {
        GL11.glColor3f(0.25F, 2.0F, 0.25F);
    }
}
