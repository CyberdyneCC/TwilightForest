package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFCheckBad extends GenLayer {

    private String stage;

    public GenLayerTFCheckBad(long par1, GenLayer par3GenLayer, String stage) {
        super(par1);
        super.field_75909_a = par3GenLayer;
        this.stage = stage;
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int[] input = this.field_75909_a.func_75904_a(x, z, width, depth);

        for (int i = 0; i < width * depth; ++i) {
            if (input[i] < 0 || input[i] > TFBiomeBase.fireSwamp.field_76756_M) {
                System.err.printf("Got a bad ID, %d at %d, %d while checking during stage %s\n", new Object[] { Integer.valueOf(input[i]), Integer.valueOf(x), Integer.valueOf(z), this.stage});
            }
        }

        return input;
    }
}
