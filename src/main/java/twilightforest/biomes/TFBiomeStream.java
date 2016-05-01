package twilightforest.biomes;

public class TFBiomeStream extends TFBiomeBase {

    public TFBiomeStream(int i) {
        super(i);
        this.field_76750_F = 0.5F;
        this.field_76751_G = 1.0F;
        this.getTFBiomeDecorator().setWaterlilyPerChunk(2);
        this.field_76762_K.clear();
    }
}
