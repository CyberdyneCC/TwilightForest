package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFRiverMix extends GenLayer {

    private GenLayer biomeLayer;
    private GenLayer riverLayer;

    public GenLayerTFRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
        super(par1);
        this.biomeLayer = par3GenLayer;
        this.riverLayer = par4GenLayer;
    }

    public void func_75905_a(long par1) {
        this.biomeLayer.func_75905_a(par1);
        this.riverLayer.func_75905_a(par1);
        super.func_75905_a(par1);
    }

    public int[] func_75904_a(int par1, int par2, int par3, int par4) {
        int[] biomeInputs = this.biomeLayer.func_75904_a(par1, par2, par3, par4);
        int[] riverInputs = this.riverLayer.func_75904_a(par1, par2, par3, par4);
        int[] outputs = IntCache.func_76445_a(par3 * par4);

        for (int i = 0; i < par3 * par4; ++i) {
            if (riverInputs[i] == TFBiomeBase.stream.field_76756_M) {
                outputs[i] = riverInputs[i] & 255;
            } else {
                outputs[i] = biomeInputs[i];
            }
        }

        return outputs;
    }
}
