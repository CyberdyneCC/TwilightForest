package twilightforest.biomes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import twilightforest.TFAchievementPage;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenThorns;
import twilightforest.world.TFWorld;

public class TFBiomeThornlands extends TFBiomeBase {

    private TFGenThorns tfGenThorns;

    public TFBiomeThornlands(int i) {
        super(i);
        this.field_76752_A = TFBlocks.deadrock;
        this.field_150604_aj = 0;
        this.field_76753_B = TFBlocks.deadrock;
        this.field_76754_C = 1;
        this.field_76750_F = 0.3F;
        this.field_76751_G = 0.2F;
        this.getTFBiomeDecorator().canopyPerChunk = -999.0F;
        this.getTFBiomeDecorator().setTreesPerChunk(-999);
        this.field_76760_I.field_76804_C = 2;
        this.field_76760_I.field_76800_F = -9999;
        this.field_76762_K.clear();
        this.tfGenThorns = new TFGenThorns();
        this.field_76760_I.field_76808_K = false;
    }

    public void func_76728_a(World world, Random rand, int mapX, int mapZ) {
        super.func_76728_a(world, rand, mapX, mapZ);

        for (int i = 0; i < 128; ++i) {
            int rx = mapX + rand.nextInt(16) + 8;
            int rz = mapZ + rand.nextInt(16) + 8;
            int ry = this.getGroundLevel(world, rx, rz);

            this.tfGenThorns.func_76484_a(world, rand, rx, ry, rz);
        }

    }

    public int getGroundLevel(World world, int x, int z) {
        Chunk chunk = world.func_72938_d(x, z);
        int lastDirt = TFWorld.SEALEVEL;

        for (int y = TFWorld.SEALEVEL; y < TFWorld.CHUNKHEIGHT - 1; ++y) {
            Block blockID = chunk.func_150810_a(x & 15, y, z & 15);

            if (blockID == Blocks.field_150349_c) {
                return y + 1;
            }

            if (blockID == Blocks.field_150346_d || blockID == Blocks.field_150348_b || blockID == Blocks.field_150351_n || blockID == Blocks.field_150322_A || blockID == Blocks.field_150354_m || blockID == Blocks.field_150435_aG || blockID == TFBlocks.deadrock) {
                lastDirt = y + 1;
            }
        }

        return lastDirt;
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
