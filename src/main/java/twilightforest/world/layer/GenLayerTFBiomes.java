package twilightforest.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFBiomes extends GenLayer {

    protected BiomeGenBase[] commonBiomes;
    protected BiomeGenBase[] rareBiomes;

    public GenLayerTFBiomes(long l, GenLayer genlayer) {
        super(l);
        this.commonBiomes = new BiomeGenBase[] { TFBiomeBase.twilightForest, TFBiomeBase.twilightForest2, TFBiomeBase.highlands, TFBiomeBase.mushrooms, TFBiomeBase.tfSwamp, TFBiomeBase.clearing, TFBiomeBase.darkForest};
        this.rareBiomes = new BiomeGenBase[] { TFBiomeBase.tfLake, TFBiomeBase.glacier, TFBiomeBase.deepMushrooms, TFBiomeBase.enchantedForest, TFBiomeBase.fireSwamp};
        this.field_75909_a = genlayer;
    }

    public GenLayerTFBiomes(long l) {
        super(l);
        this.commonBiomes = new BiomeGenBase[] { TFBiomeBase.twilightForest, TFBiomeBase.twilightForest2, TFBiomeBase.highlands, TFBiomeBase.mushrooms, TFBiomeBase.tfSwamp, TFBiomeBase.clearing, TFBiomeBase.darkForest};
        this.rareBiomes = new BiomeGenBase[] { TFBiomeBase.tfLake, TFBiomeBase.glacier, TFBiomeBase.deepMushrooms, TFBiomeBase.enchantedForest, TFBiomeBase.fireSwamp};
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int[] dest = IntCache.func_76445_a(width * depth);

        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.func_75903_a((long) (dx + x), (long) (dz + z));
                if (this.func_75902_a(15) == 0) {
                    dest[dx + dz * width] = this.rareBiomes[this.func_75902_a(this.rareBiomes.length)].field_76756_M;
                } else {
                    dest[dx + dz * width] = this.commonBiomes[this.func_75902_a(this.commonBiomes.length)].field_76756_M;
                }
            }
        }

        return dest;
    }
}
