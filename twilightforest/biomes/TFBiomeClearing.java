package twilightforest.biomes;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TFBiomeClearing extends TFBiomeBase {

    public TFBiomeClearing(int i) {
        super(i);
        this.field_76750_F = 0.8F;
        this.field_76751_G = 0.4F;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().setTreesPerChunk(-999);
        this.getTFBiomeDecorator().setFlowersPerChunk(4);
        this.getTFBiomeDecorator().setGrassPerChunk(10);
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return new WorldGenTallGrass(Blocks.field_150329_H, 1);
    }
}
