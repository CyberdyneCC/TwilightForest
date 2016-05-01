package twilightforest.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;
import twilightforest.world.TFWorld;

public class TFSkyRenderer extends IRenderHandler {

    private int starGLCallList = GLAllocation.func_74526_a(3);
    private int glSkyList;
    private int glSkyList2;

    @SideOnly(Side.CLIENT)
    public TFSkyRenderer() {
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, 4864);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.field_78398_a;

        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, 4864);
        byte b0 = 64;
        int i = 256 / b0 + 2;
        float f = 16.0F;

        int j;
        int k;

        for (j = -b0 * i; j <= b0 * i; j += b0) {
            for (k = -b0 * i; k <= b0 * i; k += b0) {
                tessellator.func_78382_b();
                tessellator.func_78377_a((double) (j + 0), (double) f, (double) (k + 0));
                tessellator.func_78377_a((double) (j + b0), (double) f, (double) (k + 0));
                tessellator.func_78377_a((double) (j + b0), (double) f, (double) (k + b0));
                tessellator.func_78377_a((double) (j + 0), (double) f, (double) (k + b0));
                tessellator.func_78381_a();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, 4864);
        f = -16.0F;
        tessellator.func_78382_b();

        for (j = -b0 * i; j <= b0 * i; j += b0) {
            for (k = -b0 * i; k <= b0 * i; k += b0) {
                tessellator.func_78377_a((double) (j + b0), (double) f, (double) (k + 0));
                tessellator.func_78377_a((double) (j + 0), (double) f, (double) (k + 0));
                tessellator.func_78377_a((double) (j + 0), (double) f, (double) (k + b0));
                tessellator.func_78377_a((double) (j + b0), (double) f, (double) (k + b0));
            }
        }

        tessellator.func_78381_a();
        GL11.glEndList();
    }

    @SideOnly(Side.CLIENT)
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable(3553);
        Vec3 vec3 = this.getTwilightSkyColor(world);
        float f = (float) vec3.field_72450_a;
        float f1 = (float) vec3.field_72448_b;
        float f2 = (float) vec3.field_72449_c;
        float f3;
        float f4;

        if (mc.field_71474_y.field_74337_g) {
            float f5 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;

            f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            f3 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f5;
            f1 = f4;
            f2 = f3;
        }

        GL11.glColor3f(f, f1, f2);
        Tessellator tessellator = Tessellator.field_78398_a;

        GL11.glDepthMask(false);
        GL11.glEnable(2912);
        GL11.glColor3f(f, f1, f2);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(2912);
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        RenderHelper.func_74518_a();
        GL11.glEnable(3553);
        GL11.glBlendFunc(770, 1);
        GL11.glPushMatrix();
        f3 = 1.0F - world.func_72867_j(partialTicks);
        f4 = 0.0F;
        float f6 = 0.0F;
        float f7 = 0.0F;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, f3);
        GL11.glTranslatef(f4, f6, f7);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.getRealCelestialAngle(world, partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        float f8 = 30.0F;

        GL11.glDisable(3553);
        float f9 = 1.0F;

        if (f9 > 0.0F) {
            GL11.glColor4f(f9, f9, f9, f9);
            GL11.glCallList(this.starGLCallList);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glEnable(2912);
        GL11.glPopMatrix();
        GL11.glDisable(3553);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        double d0 = mc.field_71439_g.func_70666_h(partialTicks).field_72448_b - (double) TFWorld.SEALEVEL;

        if (d0 < 0.0D) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            f6 = 1.0F;
            f7 = -((float) (d0 + 65.0D));
            f8 = -f6;
            tessellator.func_78382_b();
            tessellator.func_78384_a(0, 255);
            tessellator.func_78377_a((double) (-f6), (double) f7, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f7, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f8, (double) f6);
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) f6);
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) (-f6));
            tessellator.func_78377_a((double) f6, (double) f8, (double) (-f6));
            tessellator.func_78377_a((double) f6, (double) f7, (double) (-f6));
            tessellator.func_78377_a((double) (-f6), (double) f7, (double) (-f6));
            tessellator.func_78377_a((double) f6, (double) f8, (double) (-f6));
            tessellator.func_78377_a((double) f6, (double) f8, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f7, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f7, (double) (-f6));
            tessellator.func_78377_a((double) (-f6), (double) f7, (double) (-f6));
            tessellator.func_78377_a((double) (-f6), (double) f7, (double) f6);
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) f6);
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) (-f6));
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) (-f6));
            tessellator.func_78377_a((double) (-f6), (double) f8, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f8, (double) f6);
            tessellator.func_78377_a((double) f6, (double) f8, (double) (-f6));
            tessellator.func_78381_a();
        }

        if (world.field_73011_w.func_76561_g()) {
            GL11.glColor3f(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
        } else {
            GL11.glColor3f(f, f1, f2);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float) (d0 - 16.0D)), 0.0F);
        GL11.glCallList(this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDepthMask(true);
    }

    private float getRealCelestialAngle(World world, float partialTicks) {
        int i = (int) (world.func_72820_D() % 24000L);
        float f = ((float) i + partialTicks) / 24000.0F - 0.25F;

        if (f < 0.0F) {
            ++f;
        }

        if (f > 1.0F) {
            --f;
        }

        float f1 = f;

        f = 1.0F - (float) ((Math.cos((double) f * 3.141592653589793D) + 1.0D) / 2.0D);
        f = f1 + (f - f1) / 3.0F;
        return f;
    }

    private Vec3 getTwilightSkyColor(World world) {
        return Vec3.func_72443_a(0.125D, 0.1328125D, 0.2890625D);
    }

    private void renderStars() {
        Random random = new Random(10842L);
        Tessellator tessellator = Tessellator.field_78398_a;

        tessellator.func_78382_b();

        for (int i = 0; i < 3000; ++i) {
            double d0 = (double) (random.nextFloat() * 2.0F - 1.0F);
            double d1 = (double) (random.nextFloat() * 2.0F - 1.0F);
            double d2 = (double) (random.nextFloat() * 2.0F - 1.0F);
            double size = (double) (0.1F + random.nextFloat() * 0.25F);
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d3 < 1.0D && d3 > 0.01D) {
                d3 = 1.0D / Math.sqrt(d3);
                d0 *= d3;
                d1 *= d3;
                d2 *= d3;
                double d4 = d0 * 100.0D;
                double d5 = d1 * 100.0D;
                double d6 = d2 * 100.0D;
                double d7 = Math.atan2(d0, d2);
                double d8 = Math.sin(d7);
                double d9 = Math.cos(d7);
                double d10 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d11 = Math.sin(d10);
                double d12 = Math.cos(d10);
                double d13 = random.nextDouble() * 3.141592653589793D * 2.0D;
                double d14 = Math.sin(d13);
                double d15 = Math.cos(d13);

                for (int j = 0; j < 4; ++j) {
                    double d16 = 0.0D;
                    double d17 = (double) ((j & 2) - 1) * size;
                    double d18 = (double) ((j + 1 & 2) - 1) * size;
                    double d19 = d17 * d15 - d18 * d14;
                    double d20 = d18 * d15 + d17 * d14;
                    double d21 = d19 * d11 + d16 * d12;
                    double d22 = d16 * d11 - d19 * d12;
                    double d23 = d22 * d8 - d20 * d9;
                    double d24 = d20 * d8 + d22 * d9;

                    tessellator.func_78377_a(d4 + d23, d5 + d21, d6 + d24);
                }
            }
        }

        tessellator.func_78381_a();
    }
}
