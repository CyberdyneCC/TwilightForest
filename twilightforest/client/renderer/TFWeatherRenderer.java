package twilightforest.client.renderer;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.biomes.TFBiomeDarkForest;
import twilightforest.biomes.TFBiomeFinalPlateau;
import twilightforest.biomes.TFBiomeFireSwamp;
import twilightforest.biomes.TFBiomeGlacier;
import twilightforest.biomes.TFBiomeHighlands;
import twilightforest.biomes.TFBiomeSnow;
import twilightforest.biomes.TFBiomeSwamp;
import twilightforest.biomes.TFBiomeThornlands;

public class TFWeatherRenderer extends IRenderHandler {

    private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
    private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");
    private static final ResourceLocation locationBlizzardPng = new ResourceLocation("twilightforest:textures/environment/blizzard.png");
    private static final ResourceLocation locationMosquitoPng = new ResourceLocation("twilightforest:textures/environment/mosquitoes.png");
    private static final ResourceLocation locationAshesPng = new ResourceLocation("twilightforest:textures/environment/ashes.png");
    private static final ResourceLocation locationDarkstreamPng = new ResourceLocation("twilightforest:textures/environment/darkstream.png");
    private static final ResourceLocation locationBigrainPng = new ResourceLocation("twilightforest:textures/environment/bigrain.png");
    private static final ResourceLocation locationSparklesPng = new ResourceLocation("twilightforest:textures/environment/sparkles.png");
    float[] rainXCoords;
    float[] rainYCoords;
    private int rendererUpdateCount;
    private Random random = new Random();
    private StructureBoundingBox protectedBox;

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        ++this.rendererUpdateCount;
        this.renderNormalWeather(partialTicks, mc);
        if (world.func_82736_K().func_82766_b("tfEnforcedProgression") && !mc.field_71439_g.field_71075_bZ.field_75098_d) {
            this.renderLockedBiome(partialTicks, world, mc);
            this.renderLockedStructure(partialTicks, world, mc);
        }

    }

    private void renderNormalWeather(float partialTicks, Minecraft mc) {
        float rainStrength = mc.field_71441_e.func_72867_j(partialTicks);

        if (rainStrength > 0.0F) {
            mc.field_71460_t.func_78463_b((double) partialTicks);
            this.initializeRainCoords();
            EntityLivingBase entitylivingbase = mc.field_71451_h;
            WorldClient worldclient = mc.field_71441_e;
            int k2 = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
            int l2 = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
            int i3 = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
            Tessellator tessellator = Tessellator.field_78398_a;

            GL11.glDisable(2884);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(3042);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glAlphaFunc(516, 0.1F);
            double d0 = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * (double) partialTicks;
            double d1 = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double) partialTicks;
            double d2 = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * (double) partialTicks;
            int k = MathHelper.func_76128_c(d1);
            byte range = 5;

            if (mc.field_71474_y.field_74347_j) {
                range = 10;
            }

            byte b1 = -1;
            float f5 = (float) this.rendererUpdateCount + partialTicks;

            if (mc.field_71474_y.field_74347_j) {
                range = 10;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            for (int l = i3 - range; l <= i3 + range; ++l) {
                for (int i1 = k2 - range; i1 <= k2 + range; ++i1) {
                    int j1 = (l - i3 + 16) * 32 + i1 - k2 + 16;
                    float f6 = this.rainXCoords[j1] * 0.5F;
                    float f7 = this.rainYCoords[j1] * 0.5F;
                    BiomeGenBase biomegenbase = worldclient.func_72807_a(i1, l);

                    if (biomegenbase.func_76738_d() || biomegenbase.func_76746_c()) {
                        int k1 = worldclient.func_72874_g(i1, l);
                        int l1 = l2 - range;
                        int i2 = l2 + range;

                        if (l1 < k1) {
                            l1 = k1;
                        }

                        if (i2 < k1) {
                            i2 = k1;
                        }

                        float f8 = 1.0F;
                        int j2 = k1;

                        if (k1 < k) {
                            j2 = k;
                        }

                        if (l1 != i2) {
                            this.random.setSeed((long) (i1 * i1 * 3121 + i1 * 45238971 ^ l * l * 418711 + l * 13761));
                            float f9 = biomegenbase.func_150564_a(i1, l1, l);
                            float downwardsMotion;
                            double xDist;

                            if (worldclient.func_72959_q().func_76939_a(f9, k1) >= 0.15F) {
                                if (b1 != 0) {
                                    if (b1 >= 0) {
                                        tessellator.func_78381_a();
                                    }

                                    b1 = 0;
                                    mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationRainPng);
                                    tessellator.func_78382_b();
                                }

                                downwardsMotion = ((float) (this.rendererUpdateCount + i1 * i1 * 3121 + i1 * 45238971 + l * l * 418711 + l * 13761 & 31) + partialTicks) / 32.0F * (3.0F + this.random.nextFloat());
                                double f16 = (double) ((float) i1 + 0.5F) - entitylivingbase.field_70165_t;

                                xDist = (double) ((float) l + 0.5F) - entitylivingbase.field_70161_v;
                                float zDist = MathHelper.func_76133_a(f16 * f16 + xDist * xDist) / (float) range;
                                float f13 = 1.0F;

                                tessellator.func_78380_c(worldclient.func_72802_i(i1, j2, l, 0));
                                tessellator.func_78369_a(f13, f13, f13, ((1.0F - zDist * zDist) * 0.5F + 0.5F) * rainStrength);
                                tessellator.func_78373_b(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.func_78374_a((double) ((float) i1 - f6) + 0.5D, (double) l1, (double) ((float) l - f7) + 0.5D, (double) (0.0F * f8), (double) ((float) l1 * f8 / 4.0F + downwardsMotion * f8));
                                tessellator.func_78374_a((double) ((float) i1 + f6) + 0.5D, (double) l1, (double) ((float) l + f7) + 0.5D, (double) (1.0F * f8), (double) ((float) l1 * f8 / 4.0F + downwardsMotion * f8));
                                tessellator.func_78374_a((double) ((float) i1 + f6) + 0.5D, (double) i2, (double) ((float) l + f7) + 0.5D, (double) (1.0F * f8), (double) ((float) i2 * f8 / 4.0F + downwardsMotion * f8));
                                tessellator.func_78374_a((double) ((float) i1 - f6) + 0.5D, (double) i2, (double) ((float) l - f7) + 0.5D, (double) (0.0F * f8), (double) ((float) i2 * f8 / 4.0F + downwardsMotion * f8));
                                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                            } else {
                                if (b1 != 1) {
                                    if (b1 >= 0) {
                                        tessellator.func_78381_a();
                                    }

                                    b1 = 1;
                                    mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationSnowPng);
                                    tessellator.func_78382_b();
                                }

                                downwardsMotion = ((float) (this.rendererUpdateCount & 511) + partialTicks) / 512.0F;
                                float f = this.random.nextFloat() + f5 * 0.01F * (float) this.random.nextGaussian();
                                float f11 = this.random.nextFloat() + f5 * (float) this.random.nextGaussian() * 0.001F;

                                xDist = (double) ((float) i1 + 0.5F) - entitylivingbase.field_70165_t;
                                double d0 = (double) ((float) l + 0.5F) - entitylivingbase.field_70161_v;
                                float f14 = MathHelper.func_76133_a(xDist * xDist + d0 * d0) / (float) range;
                                float f15 = 1.0F;

                                tessellator.func_78380_c((worldclient.func_72802_i(i1, j2, l, 0) * 3 + 15728880) / 4);
                                tessellator.func_78369_a(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * rainStrength);
                                tessellator.func_78373_b(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.func_78374_a((double) ((float) i1 - f6) + 0.5D, (double) l1, (double) ((float) l - f7) + 0.5D, (double) (0.0F * f8 + f), (double) ((float) l1 * f8 / 4.0F + downwardsMotion * f8 + f11));
                                tessellator.func_78374_a((double) ((float) i1 + f6) + 0.5D, (double) l1, (double) ((float) l + f7) + 0.5D, (double) (1.0F * f8 + f), (double) ((float) l1 * f8 / 4.0F + downwardsMotion * f8 + f11));
                                tessellator.func_78374_a((double) ((float) i1 + f6) + 0.5D, (double) i2, (double) ((float) l + f7) + 0.5D, (double) (1.0F * f8 + f), (double) ((float) i2 * f8 / 4.0F + downwardsMotion * f8 + f11));
                                tessellator.func_78374_a((double) ((float) i1 - f6) + 0.5D, (double) i2, (double) ((float) l - f7) + 0.5D, (double) (0.0F * f8 + f), (double) ((float) i2 * f8 / 4.0F + downwardsMotion * f8 + f11));
                                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }

            if (b1 >= 0) {
                tessellator.func_78381_a();
            }

            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            mc.field_71460_t.func_78483_a((double) partialTicks);
        }

    }

    private void renderLockedBiome(float partialTicks, WorldClient world, Minecraft mc) {
        if (this.isNearLockedBiome(world, mc.field_71451_h)) {
            this.initializeRainCoords();
            EntityLivingBase entitylivingbase = mc.field_71451_h;
            WorldClient worldclient = mc.field_71441_e;
            int px = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
            int py = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
            int pz = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
            Tessellator tessellator = Tessellator.field_78398_a;

            GL11.glDisable(2884);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(3042);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glAlphaFunc(516, 0.1F);
            double offX = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * (double) partialTicks;
            double offY = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double) partialTicks;
            double offZ = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * (double) partialTicks;
            int floorY = MathHelper.func_76128_c(offY);
            byte range = 5;

            if (mc.field_71474_y.field_74347_j) {
                range = 10;
            }

            byte drawFlag = -1;
            float preciseCount = (float) this.rendererUpdateCount + partialTicks;

            if (mc.field_71474_y.field_74347_j) {
                range = 15;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            for (int dz = pz - range; dz <= pz + range; ++dz) {
                for (int dx = px - range; dx <= px + range; ++dx) {
                    int rainIndex = (dz - pz + 16) * 32 + dx - px + 16;
                    float rainX = this.rainXCoords[rainIndex] * 0.5F;
                    float rainZ = this.rainYCoords[rainIndex] * 0.5F;
                    BiomeGenBase biomegenbase = worldclient.func_72807_a(dx, dz);

                    if (biomegenbase instanceof TFBiomeBase && entitylivingbase instanceof EntityPlayer && !((TFBiomeBase) biomegenbase).doesPlayerHaveRequiredAchievement((EntityPlayer) entitylivingbase)) {
                        byte rainHeight = 0;
                        int rainMin = py - range;
                        int rainMax = py + range * 2;

                        if (rainMin < rainHeight) {
                            rainMin = rainHeight;
                        }

                        if (rainMax < rainHeight) {
                            rainMax = rainHeight;
                        }

                        float one = 1.0F;
                        int rainFloor = rainHeight;

                        if (rainHeight < floorY) {
                            rainFloor = floorY;
                        }

                        if (rainMin != rainMax) {
                            this.random.setSeed((long) (dx * dx * 3121 + dx * 45238971 ^ dz * dz * 418711 + dz * 13761));
                            float countFactor;
                            float uFactor;
                            float vFactor;
                            double xRange;
                            double zRange;
                            float distanceFromPlayer;
                            float bright;

                            if (!(biomegenbase instanceof TFBiomeSnow) && !(biomegenbase instanceof TFBiomeGlacier)) {
                                float vFactor1;
                                float f;

                                if (biomegenbase instanceof TFBiomeSwamp) {
                                    if (drawFlag != 1) {
                                        if (drawFlag >= 0) {
                                            tessellator.func_78381_a();
                                        }

                                        drawFlag = 1;
                                        mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationMosquitoPng);
                                        tessellator.func_78382_b();
                                    }

                                    countFactor = 0.0F;
                                    uFactor = this.random.nextFloat() + preciseCount * 0.03F * (float) this.random.nextGaussian();
                                    vFactor = this.random.nextFloat() + preciseCount * 0.003F * (float) this.random.nextGaussian();
                                    tessellator.func_78380_c(983055);
                                    f = this.random.nextFloat() * 0.3F;
                                    vFactor1 = this.random.nextFloat() * 0.3F;
                                    float f1 = this.random.nextFloat() * 0.3F;

                                    tessellator.func_78369_a(f, vFactor1, f1, 1.0F);
                                    tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                                    tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMin, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMin, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMax, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMax, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                                } else if (biomegenbase instanceof TFBiomeFireSwamp) {
                                    if (drawFlag != 2) {
                                        if (drawFlag >= 0) {
                                            tessellator.func_78381_a();
                                        }

                                        drawFlag = 2;
                                        mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationAshesPng);
                                        tessellator.func_78382_b();
                                    }

                                    countFactor = -((float) (this.rendererUpdateCount & 1023) + partialTicks) / 1024.0F;
                                    uFactor = this.random.nextFloat() + preciseCount * 0.001F * (float) this.random.nextGaussian();
                                    vFactor = this.random.nextFloat() + preciseCount * 0.001F * (float) this.random.nextGaussian();
                                    xRange = (double) ((float) dx + 0.5F) - entitylivingbase.field_70165_t;
                                    zRange = (double) ((float) dz + 0.5F) - entitylivingbase.field_70161_v;
                                    distanceFromPlayer = MathHelper.func_76133_a(xRange * xRange + zRange * zRange) / (float) range;
                                    tessellator.func_78380_c(983055);
                                    bright = this.random.nextFloat() * 0.2F + 0.8F;
                                    tessellator.func_78369_a(bright, bright, bright, ((1.0F - distanceFromPlayer * distanceFromPlayer) * 0.3F + 0.5F) * 1.0F);
                                    tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                                    tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMin, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMin, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMax, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMax, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                    tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                                } else {
                                    float alpha;

                                    if (biomegenbase instanceof TFBiomeDarkForest && this.random.nextInt(2) == 0) {
                                        if (drawFlag != 3) {
                                            if (drawFlag >= 0) {
                                                tessellator.func_78381_a();
                                            }

                                            drawFlag = 3;
                                            mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationDarkstreamPng);
                                            tessellator.func_78382_b();
                                        }

                                        int i = Math.min(rainMax, worldclient.func_72874_g(dx, dz));
                                        int j = Math.min(rainMin, i);

                                        vFactor = -((float) (this.rendererUpdateCount & 511) + partialTicks) / 512.0F;
                                        f = 0.0F;
                                        vFactor1 = this.random.nextFloat() + preciseCount * 0.01F * (float) this.random.nextGaussian();
                                        zRange = (double) ((float) dx + 0.5F) - entitylivingbase.field_70165_t;
                                        double d0 = (double) ((float) dz + 0.5F) - entitylivingbase.field_70161_v;

                                        alpha = MathHelper.func_76133_a(zRange * zRange + d0 * d0) / (float) range;
                                        tessellator.func_78380_c(983055);
                                        float bright1 = 1.0F;
                                        float alpha1 = this.random.nextFloat();

                                        tessellator.func_78369_a(bright1, bright1, bright1, ((1.0F - alpha * alpha) * 0.3F + 0.5F) * alpha1);
                                        tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                                        tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) j, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + f), (double) ((float) j * one / 4.0F + vFactor * one + vFactor1));
                                        tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) j, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + f), (double) ((float) j * one / 4.0F + vFactor * one + vFactor1));
                                        tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) i, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + f), (double) ((float) i * one / 4.0F + vFactor * one + vFactor1));
                                        tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) i, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + f), (double) ((float) i * one / 4.0F + vFactor * one + vFactor1));
                                        tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                                    } else if (biomegenbase instanceof TFBiomeHighlands || biomegenbase instanceof TFBiomeThornlands || biomegenbase instanceof TFBiomeFinalPlateau) {
                                        if (drawFlag != 4) {
                                            if (drawFlag >= 0) {
                                                tessellator.func_78381_a();
                                            }

                                            drawFlag = 4;
                                            mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationBigrainPng);
                                            tessellator.func_78382_b();
                                        }

                                        countFactor = ((float) (this.rendererUpdateCount + dx * dx * 3121 + dx * 45238971 + dz * dz * 418711 + dz * 13761 & 31) + partialTicks) / 32.0F * (3.0F + this.random.nextFloat());
                                        uFactor = this.random.nextFloat();
                                        vFactor = this.random.nextFloat();
                                        xRange = (double) ((float) dx + 0.5F) - entitylivingbase.field_70165_t;
                                        zRange = (double) ((float) dz + 0.5F) - entitylivingbase.field_70161_v;
                                        distanceFromPlayer = MathHelper.func_76133_a(xRange * xRange + zRange * zRange) / (float) range;
                                        tessellator.func_78380_c(worldclient.func_72802_i(dx, rainFloor, dz, 0));
                                        bright = 1.0F;
                                        alpha = 1.0F;
                                        tessellator.func_78369_a(bright, bright, bright, ((1.0F - distanceFromPlayer * distanceFromPlayer) * 0.3F + 0.5F) * alpha);
                                        tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                                        tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMin, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                        tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMin, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                        tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMax, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                        tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMax, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                        tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                                    }
                                }
                            } else {
                                if (drawFlag != 0) {
                                    if (drawFlag >= 0) {
                                        tessellator.func_78381_a();
                                    }

                                    drawFlag = 0;
                                    mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationBlizzardPng);
                                    tessellator.func_78382_b();
                                }

                                countFactor = ((float) (this.rendererUpdateCount & 511) + partialTicks) / 512.0F;
                                uFactor = this.random.nextFloat() + preciseCount * 0.03F * (float) this.random.nextGaussian();
                                vFactor = this.random.nextFloat() + preciseCount * 0.001F * (float) this.random.nextGaussian();
                                xRange = (double) ((float) dx + 0.5F) - entitylivingbase.field_70165_t;
                                zRange = (double) ((float) dz + 0.5F) - entitylivingbase.field_70161_v;
                                distanceFromPlayer = MathHelper.func_76133_a(xRange * xRange + zRange * zRange) / (float) range;
                                bright = 1.0F;
                                tessellator.func_78380_c((worldclient.func_72802_i(dx, rainFloor, dz, 0) * 3 + 15728880) / 4);
                                tessellator.func_78369_a(bright, bright, bright, ((1.0F - distanceFromPlayer * distanceFromPlayer) * 0.3F + 0.5F) * 1.0F);
                                tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                                tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMin, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMin, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                                tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMax, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMax, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }

            if (drawFlag >= 0) {
                tessellator.func_78381_a();
            }

            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            mc.field_71460_t.func_78483_a((double) partialTicks);
        }

    }

    private void renderLockedStructure(float partialTicks, WorldClient world, Minecraft mc) {
        if (this.isNearLockedStructure(world, mc.field_71451_h)) {
            this.initializeRainCoords();
            EntityLivingBase entitylivingbase = mc.field_71451_h;
            int px = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
            int py = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
            int pz = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
            Tessellator tessellator = Tessellator.field_78398_a;

            GL11.glDisable(2884);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(3042);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glAlphaFunc(516, 0.1F);
            double offX = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * (double) partialTicks;
            double offY = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double) partialTicks;
            double offZ = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * (double) partialTicks;
            byte range = 5;

            if (mc.field_71474_y.field_74347_j) {
                range = 10;
            }

            byte drawFlag = -1;
            float preciseCount = (float) this.rendererUpdateCount + partialTicks;

            if (mc.field_71474_y.field_74347_j) {
                range = 15;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            for (int dz = pz - range; dz <= pz + range; ++dz) {
                for (int dx = px - range; dx <= px + range; ++dx) {
                    int rainIndex = (dz - pz + 16) * 32 + dx - px + 16;
                    float rainX = this.rainXCoords[rainIndex] * 0.5F;
                    float rainZ = this.rainYCoords[rainIndex] * 0.5F;

                    if (this.protectedBox != null && this.protectedBox.func_78885_a(dx, dz, dx, dz)) {
                        int structureMin = this.protectedBox.field_78895_b - 4;
                        int structureMax = this.protectedBox.field_78894_e + 4;
                        int rainMin = py - range;
                        int rainMax = py + range * 2;

                        if (rainMin < structureMin) {
                            rainMin = structureMin;
                        }

                        if (rainMax < structureMin) {
                            rainMax = structureMin;
                        }

                        if (rainMin > structureMax) {
                            rainMin = structureMax;
                        }

                        if (rainMax > structureMax) {
                            rainMax = structureMax;
                        }

                        float one = 1.0F;

                        if (rainMin != rainMax) {
                            this.random.setSeed((long) (dx * dx * 3121 + dx * 45238971 ^ dz * dz * 418711 + dz * 13761));
                            if (drawFlag != 0) {
                                if (drawFlag >= 0) {
                                    tessellator.func_78381_a();
                                }

                                drawFlag = 0;
                                mc.func_110434_K().func_110577_a(TFWeatherRenderer.locationSparklesPng);
                                tessellator.func_78382_b();
                            }

                            float countFactor = ((float) (this.rendererUpdateCount & 511) + partialTicks) / 512.0F;
                            float uFactor = this.random.nextFloat() + preciseCount * 0.01F * (float) this.random.nextGaussian();
                            float vFactor = this.random.nextFloat() + preciseCount * 0.01F * (float) this.random.nextGaussian();
                            double xRange = (double) ((float) dx + 0.5F) - entitylivingbase.field_70165_t;
                            double zRange = (double) ((float) dz + 0.5F) - entitylivingbase.field_70161_v;
                            float distanceFromPlayer = MathHelper.func_76133_a(xRange * xRange + zRange * zRange) / (float) range;

                            tessellator.func_78380_c(983055);
                            float bright = 1.0F;
                            float alpha = this.random.nextFloat();

                            tessellator.func_78369_a(bright, bright, bright, ((1.0F - distanceFromPlayer * distanceFromPlayer) * 0.3F + 0.5F) * alpha);
                            tessellator.func_78373_b(-offX * 1.0D, -offY * 1.0D, -offZ * 1.0D);
                            tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMin, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                            tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMin, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMin * one / 4.0F + countFactor * one + vFactor));
                            tessellator.func_78374_a((double) ((float) dx + rainX) + 0.5D, (double) rainMax, (double) ((float) dz + rainZ) + 0.5D, (double) (1.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                            tessellator.func_78374_a((double) ((float) dx - rainX) + 0.5D, (double) rainMax, (double) ((float) dz - rainZ) + 0.5D, (double) (0.0F * one + uFactor), (double) ((float) rainMax * one / 4.0F + countFactor * one + vFactor));
                            tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }

            if (drawFlag >= 0) {
                tessellator.func_78381_a();
            }

            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            mc.field_71460_t.func_78483_a((double) partialTicks);
        }

    }

    private void initializeRainCoords() {
        if (this.rainXCoords == null) {
            this.rainXCoords = new float[1024];
            this.rainYCoords = new float[1024];

            for (int i = 0; i < 32; ++i) {
                for (int j = 0; j < 32; ++j) {
                    float f2 = (float) (j - 16);
                    float f3 = (float) (i - 16);
                    float f4 = MathHelper.func_76129_c(f2 * f2 + f3 * f3);

                    this.rainXCoords[i << 5 | j] = -f3 / f4;
                    this.rainYCoords[i << 5 | j] = f2 / f4;
                }
            }
        }

    }

    private boolean isNearLockedBiome(World world, EntityLivingBase viewEntity) {
        byte range = 15;
        int px = MathHelper.func_76128_c(viewEntity.field_70165_t);
        int pz = MathHelper.func_76128_c(viewEntity.field_70161_v);

        for (int z = pz - range; z <= pz + range; ++z) {
            for (int x = px - range; x <= px + range; ++x) {
                BiomeGenBase biomegenbase = world.func_72807_a(x, z);

                if (biomegenbase instanceof TFBiomeBase && viewEntity instanceof EntityPlayer) {
                    TFBiomeBase tfBiome = (TFBiomeBase) biomegenbase;
                    EntityPlayer player = (EntityPlayer) viewEntity;

                    if (!tfBiome.doesPlayerHaveRequiredAchievement(player)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isNearLockedStructure(World world, EntityLivingBase viewEntity) {
        byte range = 15;
        int px = MathHelper.func_76128_c(viewEntity.field_70165_t);
        int pz = MathHelper.func_76128_c(viewEntity.field_70161_v);

        return this.protectedBox != null && this.protectedBox.func_78885_a(px - range, pz - range, px + range, pz + range);
    }

    public StructureBoundingBox getProtectedBox() {
        return this.protectedBox;
    }

    public void setProtectedBox(StructureBoundingBox protectedBox) {
        this.protectedBox = protectedBox;
    }
}
