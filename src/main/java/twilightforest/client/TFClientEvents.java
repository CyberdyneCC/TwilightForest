package twilightforest.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import org.lwjgl.opengl.GL11;
import twilightforest.item.ItemTFBowBase;

public class TFClientEvents {

    private Random random = new Random();

    @SubscribeEvent
    public void renderLivingPost(Post event) {
        if (event.entity.func_70096_w().func_75679_c(7) == Potion.field_76425_a[Potion.field_76421_d.func_76396_c()].func_76401_j() && event.entity.func_70096_w().func_75683_a(8) > 0) {
            this.renderIcedEntity(event.entity, event.renderer, event.x, event.y, event.z);
        }

    }

    @SubscribeEvent
    public void fovUpdate(FOVUpdateEvent event) {
        if (event.entity.func_71039_bw() && event.entity.func_71011_bu().func_77973_b() instanceof ItemTFBowBase) {
            int i = event.entity.func_71057_bx();
            float f1 = (float) i / 20.0F;

            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 *= f1;
            }

            event.newfov *= 1.0F - f1 * 0.15F;
        }

    }

    private void renderIcedEntity(EntityLivingBase entity, RendererLivingEntity renderer, double x, double y, double z) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110575_b);
        this.random.setSeed((long) (entity.func_145782_y() * entity.func_145782_y() * 3121 + entity.func_145782_y() * 45238971));
        int numCubes = (int) (entity.field_70131_O / 0.4F);

        for (int i = 0; i < numCubes; ++i) {
            GL11.glPushMatrix();
            float dx = (float) (x + this.random.nextGaussian() * 0.20000000298023224D * (double) entity.field_70130_N);
            float dy = (float) (y + this.random.nextGaussian() * 0.20000000298023224D * (double) entity.field_70131_O) + entity.field_70131_O / 2.0F;
            float dz = (float) (z + this.random.nextGaussian() * 0.20000000298023224D * (double) entity.field_70130_N);

            GL11.glTranslatef(dx, dy, dz);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glRotatef(this.random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(this.random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            RenderBlocks.getInstance().func_147800_a(Blocks.field_150432_aD, 0, 1.0F);
            GL11.glPopMatrix();
        }

        GL11.glDisable(3042);
    }
}
