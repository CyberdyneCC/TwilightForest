package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerTFFeatureZoom extends GenLayer {

    public GenLayerTFFeatureZoom(long l, GenLayer genlayer) {
        super(l);
        this.field_75909_a = genlayer;
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int sx = x >> 1;
        int sz = z >> 1;
        int swidth = (width >> 1) + 3;
        int sdepth = (depth >> 1) + 3;
        int[] src = this.field_75909_a.func_75904_a(sx, sz, swidth, sdepth);
        int[] dest = IntCache.func_76445_a(swidth * 2 * sdepth * 2);
        int doubleWidth = swidth << 1;

        int copyZ;

        for (int output = 0; output < sdepth - 1; ++output) {
            for (copyZ = 0; copyZ < swidth - 1; ++copyZ) {
                dest[copyZ * 2 + 0 + (output * 2 + 0) * doubleWidth] = src[copyZ + output * swidth];
                dest[copyZ * 2 + 1 + (output * 2 + 0) * doubleWidth] = 0;
                dest[copyZ * 2 + 0 + (output * 2 + 1) * doubleWidth] = 0;
                dest[copyZ * 2 + 1 + (output * 2 + 1) * doubleWidth] = 0;
            }
        }

        int[] aint = IntCache.func_76445_a(width * depth);

        for (copyZ = 0; copyZ < depth; ++copyZ) {
            System.arraycopy(dest, (copyZ + (z & 1)) * (swidth << 1) + (x & 1), aint, copyZ * width, width);
        }

        return aint;
    }

    public static GenLayer multipleZoom(long seed, GenLayer genlayer, int loops) {
        Object layer = genlayer;

        for (int i = 0; i < loops; ++i) {
            layer = new GenLayerTFFeatureZoom(seed + (long) i, (GenLayer) layer);
        }

        return (GenLayer) layer;
    }
}
