package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerTF extends GenLayer {

    public GenLayerTF(long l) {
        super(l);
    }

    public static GenLayer[] makeTheWorld(long l) {
        GenLayerTFBiomes1Point7 biomes = new GenLayerTFBiomes1Point7(1L);
        GenLayerTFKeyBiomes biomes1 = new GenLayerTFKeyBiomes(1000L, biomes);
        GenLayerTFCompanionBiomes biomes2 = new GenLayerTFCompanionBiomes(1000L, biomes1);
        GenLayerZoom biomes3 = new GenLayerZoom(1000L, biomes2);

        biomes3 = new GenLayerZoom(1001L, biomes3);
        GenLayerTFBiomeStabilize biomes4 = new GenLayerTFBiomeStabilize(700L, biomes3);
        GenLayerTFThornBorder biomes5 = new GenLayerTFThornBorder(500L, biomes4);

        biomes3 = new GenLayerZoom(1002L, biomes5);
        biomes3 = new GenLayerZoom(1003L, biomes3);
        biomes3 = new GenLayerZoom(1004L, biomes3);
        biomes3 = new GenLayerZoom(1005L, biomes3);
        GenLayerTFStream riverLayer = new GenLayerTFStream(1L, biomes3);
        GenLayerSmooth riverLayer1 = new GenLayerSmooth(7000L, riverLayer);
        GenLayerTFRiverMix biomes6 = new GenLayerTFRiverMix(100L, biomes3, riverLayer1);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes6);

        biomes6.func_75905_a(l);
        genlayervoronoizoom.func_75905_a(l);
        return new GenLayer[] { biomes6, genlayervoronoizoom};
    }

    public static GenLayer[] makeTheWorldOldMapGen(long l) {
        GenLayerTFBiomes biomes = new GenLayerTFBiomes(1L);
        GenLayerZoom biomes1 = new GenLayerZoom(1000L, biomes);

        biomes1 = new GenLayerZoom(1001L, biomes1);
        GenLayerTFBiomeStabilize biomes2 = new GenLayerTFBiomeStabilize(700L, biomes1);

        biomes1 = new GenLayerZoom(1002L, biomes2);
        GenLayerTFBiomeBorders biomes3 = new GenLayerTFBiomeBorders(500L, biomes1);

        biomes1 = new GenLayerZoom(1003L, biomes3);
        biomes1 = new GenLayerZoom(1004L, biomes1);
        biomes1 = new GenLayerZoom(1005L, biomes1);
        GenLayerTFStream riverLayer = new GenLayerTFStream(1L, biomes1);
        GenLayerSmooth riverLayer1 = new GenLayerSmooth(7000L, riverLayer);
        GenLayerTFRiverMix biomes4 = new GenLayerTFRiverMix(100L, biomes1, riverLayer1);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes4);

        biomes4.func_75905_a(l);
        genlayervoronoizoom.func_75905_a(l);
        return new GenLayer[] { biomes4, genlayervoronoizoom};
    }
}
