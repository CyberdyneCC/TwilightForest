package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFCompanionBiomes extends GenLayer {

    public GenLayerTFCompanionBiomes(long l, GenLayer genlayer) {
        super(l);
        this.field_75909_a = genlayer;
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
                int right = input[dx + 0 + (dz + 1) * nwidth];
                int left = input[dx + 2 + (dz + 1) * nwidth];
                int up = input[dx + 1 + (dz + 0) * nwidth];
                int down = input[dx + 1 + (dz + 2) * nwidth];
                int center = input[dx + 1 + (dz + 1) * nwidth];

                if (this.isKey(TFBiomeBase.fireSwamp.field_76756_M, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.tfSwamp.field_76756_M;
                } else if (this.isKey(TFBiomeBase.glacier.field_76756_M, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.tfSnow.field_76756_M;
                } else if (this.isKey(TFBiomeBase.darkForestCenter.field_76756_M, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.darkForest.field_76756_M;
                } else if (this.isKey(TFBiomeBase.highlandsCenter.field_76756_M, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.highlands.field_76756_M;
                } else {
                    output[dx + dz * width] = center;
                }
            }
        }

        return output;
    }

    boolean isKey(int biome, int center, int right, int left, int up, int down) {
        return center != biome && (right == biome || left == biome || up == biome || down == biome);
    }
}
