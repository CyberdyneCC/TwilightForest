package twilightforest.world;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.structures.hollowtree.StructureTFHollowTreeStart;

public class MapGenTFHollowTree extends MapGenBase {

    protected Map structureMap = new HashMap();
    public static List oakSpawnBiomes = Arrays.asList(new BiomeGenBase[] { TFBiomeBase.twilightForest, TFBiomeBase.twilightForest2, TFBiomeBase.mushrooms, TFBiomeBase.tfSwamp, TFBiomeBase.clearing, TFBiomeBase.oakSavanna, TFBiomeBase.fireflyForest, TFBiomeBase.deepMushrooms, TFBiomeBase.enchantedForest, TFBiomeBase.fireSwamp});

    protected void func_151538_a(World world, final int chunkX, final int chunkZ, int centerX, int centerZ, Block[] blockData) {
        if (!this.structureMap.containsKey(Long.valueOf(ChunkCoordIntPair.func_77272_a(chunkX, chunkZ)))) {
            this.field_75038_b.nextInt();

            try {
                if (this.canSpawnStructureAtCoords(chunkX, chunkZ)) {
                    StructureStart throwable = this.getStructureStart(chunkX, chunkZ);

                    this.structureMap.put(Long.valueOf(ChunkCoordIntPair.func_77272_a(chunkX, chunkZ)), throwable);
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception preparing hollow tree");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Feature being prepared");

                crashreportcategory.func_71500_a("Is feature chunk", new Callable() {
                    private static final String __OBFID = "CL_00000506";

                    public String call() {
                        return MapGenTFHollowTree.this.canSpawnStructureAtCoords(chunkX, chunkZ) ? "True" : "False";
                    }
                });
                crashreportcategory.func_71507_a("Chunk location", String.format("%d,%d", new Object[] { Integer.valueOf(chunkX), Integer.valueOf(chunkZ)}));
                crashreportcategory.func_71500_a("Chunk pos hash", new Callable() {
                    private static final String __OBFID = "CL_00000507";

                    public String call() {
                        return String.valueOf(ChunkCoordIntPair.func_77272_a(chunkX, chunkZ));
                    }
                });
                crashreportcategory.func_71500_a("Structure type", new Callable() {
                    private static final String __OBFID = "CL_00000508";

                    public String call() {
                        return MapGenTFHollowTree.this.getClass().getCanonicalName();
                    }
                });
                throw new ReportedException(crashreport);
            }
        }

    }

    public boolean generateStructuresInChunk(World world, Random rand, int chunkX, int chunkZ) {
        int mapX = (chunkX << 4) + 8;
        int mapZ = (chunkZ << 4) + 8;
        boolean flag = false;
        Iterator iterator = this.structureMap.values().iterator();

        while (iterator.hasNext()) {
            StructureStart structurestart = (StructureStart) iterator.next();

            if (structurestart.func_75069_d() && structurestart.func_75071_a().func_78885_a(mapX, mapZ, mapX + 15, mapZ + 15)) {
                structurestart.func_75068_a(world, rand, new StructureBoundingBox(mapX, mapZ, mapX + 15, mapZ + 15));
                flag = true;
            }
        }

        return flag;
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return this.field_75038_b.nextInt(TwilightForestMod.twilightOakChance) == 0 && TFFeature.getNearestFeature(chunkX, chunkZ, this.field_75039_c).areChunkDecorationsEnabled && this.field_75039_c.func_72959_q().func_76940_a(chunkX * 16 + 8, chunkZ * 16 + 8, 0, MapGenTFHollowTree.oakSpawnBiomes);
    }

    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new StructureTFHollowTreeStart(this.field_75039_c, this.field_75038_b, chunkX, chunkZ);
    }
}
