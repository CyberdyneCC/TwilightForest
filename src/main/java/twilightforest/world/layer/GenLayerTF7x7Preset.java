package twilightforest.world.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTF7x7Preset extends GenLayer {

    BiomeGenBase[][] preset = new BiomeGenBase[9][9];

    public GenLayerTF7x7Preset(long par1) {
        super(par1);
        this.initPresets();
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int[] dest = IntCache.func_76445_a(width * depth);

        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                int sx = x + dx + 4;
                int sz = z + dz + 4;

                if (sx >= 0 && sx < 8 && sz >= 0 && sz < 8) {
                    dest[dx + dz * width] = this.preset[sx][sz].field_76756_M;
                } else {
                    dest[dx + dz * width] = BiomeGenBase.field_76771_b.field_76756_M;
                }
            }
        }

        return dest;
    }

    private void initPresets() {
        char[][] map = new char[][] { { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}, { 'P', 'H', 'H', 'H', 'H', 'D', 'D', 'D', 'P'}, { 'P', 'H', 'H', 'H', 'H', 'D', 'D', 'D', 'P'}, { 'P', 'F', 'f', 'F', 'm', 'M', 'D', 'D', 'P'}, { 'P', 'F', 'F', 'F', 'C', 'm', 'D', 'D', 'P'}, { 'P', 'F', 'f', 'f', 'F', 'E', 'O', 'O', 'P'}, { 'P', 'S', 'S', 'S', 'L', 'L', 'O', 'G', 'P'}, { 'P', 'Y', 'S', 'S', 'L', 'L', 'O', 'G', 'P'}, { 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}};

        for (int x = 0; x < map.length; ++x) {
            for (int z = 0; z < map[x].length; ++z) {
                this.preset[x][z] = this.getBiomeFor(map[z][x]);
            }
        }

    }

    protected BiomeGenBase getBiomeFor(char c) {
        switch (c) {
        case 'C':
            return TFBiomeBase.clearing;

        case 'D':
            return TFBiomeBase.darkForest;

        case 'E':
            return TFBiomeBase.enchantedForest;

        case 'F':
            return TFBiomeBase.twilightForest;

        case 'G':
            return TFBiomeBase.glacier;

        case 'H':
            return TFBiomeBase.highlands;

        case 'I':
        case 'J':
        case 'K':
        case 'N':
        case 'P':
        case 'Q':
        case 'R':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Z':
        case '[':
        case '\\':
        case ']':
        case '^':
        case '_':
        case '`':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        default:
            return BiomeGenBase.field_76771_b;

        case 'L':
            return TFBiomeBase.tfLake;

        case 'M':
            return TFBiomeBase.deepMushrooms;

        case 'O':
            return TFBiomeBase.tfSnow;

        case 'S':
            return TFBiomeBase.tfSwamp;

        case 'Y':
            return TFBiomeBase.fireSwamp;

        case 'f':
            return TFBiomeBase.twilightForest2;

        case 'm':
            return TFBiomeBase.mushrooms;
        }
    }
}
