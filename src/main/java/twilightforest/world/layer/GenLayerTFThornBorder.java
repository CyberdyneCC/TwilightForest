package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFThornBorder extends GenLayer {

    public GenLayerTFThornBorder(long l, GenLayer genlayer) {
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
                int ur = input[dx + 0 + (dz + 0) * nwidth];
                int ul = input[dx + 2 + (dz + 0) * nwidth];
                int dr = input[dx + 0 + (dz + 2) * nwidth];
                int dl = input[dx + 2 + (dz + 2) * nwidth];

                if (this.onBorder(TFBiomeBase.highlandsCenter.field_76756_M, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.thornlands.field_76756_M;
                } else if (this.onBorder(TFBiomeBase.highlandsCenter.field_76756_M, center, ur, ul, dr, dl)) {
                    output[dx + dz * width] = TFBiomeBase.thornlands.field_76756_M;
                } else {
                    output[dx + dz * width] = center;
                }
            }
        }

        return output;
    }

    private boolean onBorder(int biomeID, int biomeID2, int center, int right, int left, int up, int down) {
        return center != biomeID ? false : (right == biomeID2 ? true : (left == biomeID2 ? true : (up == biomeID2 ? true : down == biomeID2)));
    }

    private boolean onBorder(int biomeID, int center, int right, int left, int up, int down) {
        return center == biomeID ? false : (right == biomeID ? true : (left == biomeID ? true : (up == biomeID ? true : down == biomeID)));
    }
}
