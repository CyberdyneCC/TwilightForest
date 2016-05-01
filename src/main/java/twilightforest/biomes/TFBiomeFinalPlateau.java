package twilightforest.biomes;

import net.minecraft.block.Block;
import net.minecraft.stats.Achievement;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import twilightforest.TFAchievementPage;
import twilightforest.block.TFBlocks;
import twilightforest.entity.passive.EntityTFRaven;

public class TFBiomeFinalPlateau extends TFBiomeBase {

    public TFBiomeFinalPlateau(int i) {
        super(i);
        this.field_76752_A = TFBlocks.deadrock;
        this.field_150604_aj = 0;
        this.field_76753_B = TFBlocks.deadrock;
        this.field_76754_C = 1;
        this.field_76750_F = 0.3F;
        this.field_76751_G = 0.2F;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().setTreesPerChunk(-999);
        this.field_76760_I.field_76808_K = false;
        this.field_76762_K.clear();
        this.field_76762_K.add(new SpawnListEntry(EntityTFRaven.class, 10, 4, 4));
    }

    public Block getStoneReplacementBlock() {
        return TFBlocks.deadrock;
    }

    public byte getStoneReplacementMeta() {
        return (byte) 2;
    }

    protected Achievement getRequiredAchievement() {
        return TFAchievementPage.twilightProgressGlacier;
    }
}
