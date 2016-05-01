package twilightforest.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.client.renderer.TFSkyRenderer;
import twilightforest.client.renderer.TFWeatherRenderer;

public class WorldProviderTwilightForest extends WorldProviderSurface {

    public final String saveFolder;
    public ChunkProviderTwilightForest chunkProvider;

    public WorldProviderTwilightForest() {
        this.setDimension(TwilightForestMod.dimensionID);
        this.saveFolder = "DIM" + TwilightForestMod.dimensionID;
    }

    public float[] func_76560_a(float celestialAngle, float f1) {
        return null;
    }

    public Vec3 func_76562_b(float f, float f1) {
        float bright = MathHelper.func_76134_b(1.5707965F) * 2.0F + 0.5F;

        if (bright < 0.0F) {
            bright = 0.0F;
        }

        if (bright > 1.0F) {
            bright = 1.0F;
        }

        float red = 0.7529412F;
        float green = 1.0F;
        float blue = 0.8470588F;

        red *= bright * 0.94F + 0.06F;
        green *= bright * 0.94F + 0.06F;
        blue *= bright * 0.91F + 0.09F;
        return Vec3.func_72443_a((double) red, (double) green, (double) blue);
    }

    public float func_76563_a(long par1, float par3) {
        return 0.225F;
    }

    public void func_76572_b() {
        this.field_76578_c = new TFWorldChunkManager(this.field_76579_a);
        this.field_76574_g = TwilightForestMod.dimensionID;
    }

    public IChunkProvider func_76555_c() {
        if (this.chunkProvider == null) {
            this.chunkProvider = new ChunkProviderTwilightForest(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r());
            return this.chunkProvider;
        } else {
            return new ChunkProviderTwilightForest(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r());
        }
    }

    public ChunkProviderTwilightForest getChunkProvider() {
        return this.chunkProvider;
    }

    public boolean func_76561_g() {
        return false;
    }

    public int func_76557_i() {
        return 30;
    }

    public boolean func_76567_e() {
        return this.field_76579_a.func_72912_H().func_76070_v();
    }

    public String getSaveFolder() {
        return this.saveFolder;
    }

    public String getWelcomeMessage() {
        return "Entering the Twilight Forest";
    }

    public String getDepartMessage() {
        return "Leaving the Twilight Forest";
    }

    public String func_80007_l() {
        return "Twilight Forest";
    }

    public boolean isDaytime() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return Vec3.func_72443_a(0.16796875D, 0.1796875D, 0.38671875D);
    }

    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        return 1.0F;
    }

    public double getHorizon() {
        return 32.0D;
    }

    public BiomeGenBase getBiomeGenForCoords(int x, int z) {
        BiomeGenBase biome = super.getBiomeGenForCoords(x, z);

        if (biome == null) {
            biome = TFBiomeBase.twilightForest;
        }

        return biome;
    }

    public long getSeed() {
        return TwilightForestMod.twilightForestSeed != null && TwilightForestMod.twilightForestSeed.length() != 0 ? (long) TwilightForestMod.twilightForestSeed.hashCode() : super.getSeed();
    }

    public void updateWeather() {
        super.updateWeather();
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (super.getSkyRenderer() == null) {
            this.setSkyRenderer(new TFSkyRenderer());
        }

        return super.getSkyRenderer();
    }

    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer() {
        if (super.getWeatherRenderer() == null) {
            this.setWeatherRenderer(new TFWeatherRenderer());
        }

        return super.getWeatherRenderer();
    }

    public float func_76571_f() {
        return 161.0F;
    }
}
