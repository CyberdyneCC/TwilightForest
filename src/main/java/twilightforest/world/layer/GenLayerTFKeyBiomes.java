package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFKeyBiomes extends GenLayer {

    public GenLayerTFKeyBiomes(long l, GenLayer genlayer) {
        super(l);
        this.field_75909_a = genlayer;
    }

    public GenLayerTFKeyBiomes(long l) {
        super(l);
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int[] src = this.field_75909_a.func_75904_a(x, z, width, depth);
        int[] dest = IntCache.func_76445_a(width * depth);

        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.func_75903_a((long) (dx + x | 3), (long) (dz + z | 3));
                int ox = this.func_75902_a(3) + 1;
                int oz = this.func_75902_a(3) + 1;

                if ((dx + x & 3) == ox && (dz + z & 3) == oz) {
                    if ((dx + x & 4) == 0) {
                        if ((dz + z & 4) == 0) {
                            dest[dx + dz * width] = this.getKeyBiomeFor(dx + x, dz + z, 0);
                        } else {
                            dest[dx + dz * width] = this.getKeyBiomeFor(dx + x, dz + z, 1);
                        }
                    } else if ((dz + z & 4) == 0) {
                        dest[dx + dz * width] = this.getKeyBiomeFor(dx + x, dz + z, 2);
                    } else {
                        dest[dx + dz * width] = this.getKeyBiomeFor(dx + x, dz + z, 3);
                    }
                } else {
                    dest[dx + dz * width] = src[dx + dz * width];
                }
            }
        }

        return dest;
    }

    private int getKeyBiomeFor(int mapX, int mapZ, int index) {
        int regionX = mapX + 4 >> 3;
        int regionZ = mapZ + 4 >> 3;

        this.func_75903_a((long) regionX, (long) regionZ);
        int offset = this.func_75902_a(4);

        switch ((index + offset) % 4) {
        case 0:
        default:
            return TFBiomeBase.glacier.field_76756_M;

        case 1:
            return TFBiomeBase.fireSwamp.field_76756_M;

        case 2:
            return TFBiomeBase.darkForestCenter.field_76756_M;

        case 3:
            return TFBiomeBase.highlandsCenter.field_76756_M;
        }
    }
}
