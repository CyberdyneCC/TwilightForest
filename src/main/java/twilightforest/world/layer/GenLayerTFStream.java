package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFStream extends GenLayer {

    public GenLayerTFStream(long l, GenLayer genlayer) {
        super(l);
        super.field_75909_a = genlayer;
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int nx = x - 1;
        int nz = z - 1;
        int nwidth = width + 2;
        int ndepth = depth + 2;
        int[] input = this.field_75909_a.func_75904_a(nx, nz, nwidth, ndepth);
        int[] output = IntCache.func_76445_a(width * depth);

        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                int left = input[dx + 0 + (dz + 1) * nwidth];
                int right = input[dx + 2 + (dz + 1) * nwidth];
                int down = input[dx + 1 + (dz + 0) * nwidth];
                int up = input[dx + 1 + (dz + 2) * nwidth];
                int mid = input[dx + 1 + (dz + 1) * nwidth];

                if (this.shouldStream(mid, left, down, right, up)) {
                    output[dx + dz * width] = TFBiomeBase.stream.field_76756_M;
                } else {
                    output[dx + dz * width] = -1;
                }
            }
        }

        return output;
    }

    boolean shouldStream(int mid, int left, int down, int right, int up) {
        return this.shouldStream(mid, left) ? true : (this.shouldStream(mid, right) ? true : (this.shouldStream(mid, down) ? true : this.shouldStream(mid, up)));
    }

    boolean shouldStream(int biome1, int biome2) {
        return biome1 == biome2 ? false : (biome1 == -biome2 ? false : (biome1 == TFBiomeBase.glacier.field_76756_M && biome2 == TFBiomeBase.tfSnow.field_76756_M ? false : (biome1 == TFBiomeBase.tfSnow.field_76756_M && biome2 == TFBiomeBase.glacier.field_76756_M ? false : (biome1 == TFBiomeBase.deepMushrooms.field_76756_M && biome2 == TFBiomeBase.mushrooms.field_76756_M ? false : (biome1 == TFBiomeBase.mushrooms.field_76756_M && biome2 == TFBiomeBase.deepMushrooms.field_76756_M ? false : (biome1 == TFBiomeBase.tfSwamp.field_76756_M && biome2 == TFBiomeBase.fireSwamp.field_76756_M ? false : (biome1 == TFBiomeBase.fireSwamp.field_76756_M && biome2 == TFBiomeBase.tfSwamp.field_76756_M ? false : (biome1 == TFBiomeBase.highlands.field_76756_M && biome2 == TFBiomeBase.highlandsCenter.field_76756_M ? false : (biome1 == TFBiomeBase.highlandsCenter.field_76756_M && biome2 == TFBiomeBase.highlands.field_76756_M ? false : (biome1 == TFBiomeBase.darkForest.field_76756_M && biome2 == TFBiomeBase.darkForestCenter.field_76756_M ? false : (biome1 == TFBiomeBase.darkForestCenter.field_76756_M && biome2 == TFBiomeBase.darkForest.field_76756_M ? false : (biome1 != TFBiomeBase.tfLake.field_76756_M && biome2 != TFBiomeBase.tfLake.field_76756_M ? (biome1 != TFBiomeBase.clearing.field_76756_M && biome2 != TFBiomeBase.oakSavanna.field_76756_M ? (biome1 != TFBiomeBase.oakSavanna.field_76756_M && biome2 != TFBiomeBase.clearing.field_76756_M ? biome1 != TFBiomeBase.thornlands.field_76756_M && biome2 != TFBiomeBase.thornlands.field_76756_M : false) : false) : false))))))))))));
    }
}
