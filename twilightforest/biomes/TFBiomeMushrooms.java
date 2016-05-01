package twilightforest.biomes;

public class TFBiomeMushrooms extends TFBiomeBase {

    public TFBiomeMushrooms(int i) {
        super(i);
        this.field_76751_G = 0.8F;
        this.field_76750_F = 0.8F;
        this.getTFBiomeDecorator().setTreesPerChunk(8);
        this.getTFBiomeDecorator().setMushroomsPerChunk(8);
        this.getTFBiomeDecorator().setBigMushroomsPerChunk(2);
        this.getTFBiomeDecorator().alternateCanopyChance = 0.2F;
    }
}
