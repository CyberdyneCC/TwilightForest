package twilightforest.biomes;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class TFBiomeTwilightLake extends TFBiomeBase {

    public TFBiomeTwilightLake(int i) {
        super(i);
        this.field_76750_F = 0.66F;
        this.field_76751_G = 1.0F;
        this.field_76755_L.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
    }
}
