package twilightforest.biomes;

public class TFBiomeDarkForestCenter extends TFBiomeDarkForest {

    public TFBiomeDarkForestCenter(int i) {
        super(i);
    }

    public int func_150558_b(int x, int y, int z) {
        double d0 = TFBiomeDarkForestCenter.field_150606_ad.func_151601_a((double) x * 0.0225D, (double) z * 0.0225D);

        return d0 < -0.2D ? 6714688 : 5587220;
    }

    public int func_150571_c(int x, int y, int z) {
        double d0 = TFBiomeDarkForestCenter.field_150606_ad.func_151601_a((double) x * 0.0225D, (double) z * 0.0225D);

        return d0 < -0.1D ? 16351774 : 15289876;
    }
}
