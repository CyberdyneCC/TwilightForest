package twilightforest.biomes;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import twilightforest.TwilightForestMod;
import twilightforest.entity.EntityTFKobold;
import twilightforest.entity.passive.EntityTFBighorn;
import twilightforest.entity.passive.EntityTFBoar;
import twilightforest.entity.passive.EntityTFBunny;
import twilightforest.entity.passive.EntityTFDeer;
import twilightforest.entity.passive.EntityTFMobileFirefly;
import twilightforest.entity.passive.EntityTFRaven;
import twilightforest.entity.passive.EntityTFSquirrel;
import twilightforest.entity.passive.EntityTFTinyBird;

public abstract class TFBiomeBase extends BiomeGenBase {

    public static final BiomeGenBase tfLake;
    public static final BiomeGenBase twilightForest;
    public static final BiomeGenBase twilightForest2;
    public static final BiomeGenBase highlands;
    public static final BiomeGenBase mushrooms;
    public static final BiomeGenBase tfSwamp;
    public static final BiomeGenBase stream;
    public static final BiomeGenBase tfSnow;
    public static final BiomeGenBase glacier;
    public static final BiomeGenBase clearing;
    public static final BiomeGenBase oakSavanna;
    public static final BiomeGenBase fireflyForest;
    public static final BiomeGenBase deepMushrooms;
    public static final BiomeGenBase darkForest;
    public static final BiomeGenBase enchantedForest;
    public static final BiomeGenBase fireSwamp;
    public static final BiomeGenBase darkForestCenter;
    public static final BiomeGenBase highlandsCenter;
    public static final BiomeGenBase thornlands;
    protected WorldGenBigMushroom bigMushroomGen = new WorldGenBigMushroom();
    protected WorldGenForest birchGen = new WorldGenForest(false, false);
    protected List undergroundMonsterList;

    public TFBiomeBase(int i) {
        super(i);
        this.field_76758_O = null;
        this.field_76761_J.clear();
        this.field_76755_L.clear();
        this.field_76762_K.clear();
        this.field_76762_K.add(new SpawnListEntry(EntityTFBighorn.class, 12, 4, 4));
        this.field_76762_K.add(new SpawnListEntry(EntityTFBoar.class, 10, 4, 4));
        this.field_76762_K.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
        this.field_76762_K.add(new SpawnListEntry(EntityTFDeer.class, 15, 4, 5));
        this.field_76762_K.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.field_76762_K.add(new SpawnListEntry(EntityTFTinyBird.class, 15, 4, 8));
        this.field_76762_K.add(new SpawnListEntry(EntityTFSquirrel.class, 10, 2, 4));
        this.field_76762_K.add(new SpawnListEntry(EntityTFBunny.class, 10, 4, 5));
        this.field_76762_K.add(new SpawnListEntry(EntityTFRaven.class, 10, 1, 2));
        this.undergroundMonsterList = new ArrayList();
        this.undergroundMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityCreeper.class, 1, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.undergroundMonsterList.add(new SpawnListEntry(EntityTFKobold.class, 10, 4, 8));
        this.field_82914_M.clear();
        this.field_82914_M.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
        this.field_82914_M.add(new SpawnListEntry(EntityTFMobileFirefly.class, 10, 8, 8));
        this.getTFBiomeDecorator().setTreesPerChunk(10);
        this.getTFBiomeDecorator().setGrassPerChunk(2);
    }

    public TFBiomeBase setColor(int par1) {
        return (TFBiomeBase) super.func_76739_b(par1);
    }

    public float func_76741_f() {
        return 0.12F;
    }

    public BiomeDecorator func_76729_a() {
        return new TFBiomeDecorator();
    }

    protected TFBiomeDecorator getTFBiomeDecorator() {
        return (TFBiomeDecorator) this.field_76760_I;
    }

    public WorldGenAbstractTree func_150567_a(Random random) {
        return (WorldGenAbstractTree) (random.nextInt(5) == 0 ? this.birchGen : (random.nextInt(10) == 0 ? new WorldGenBigTree(false) : this.field_76757_N));
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.field_150329_H, 2) : new WorldGenTallGrass(Blocks.field_150329_H, 1);
    }

    public static boolean assignBlankBiomeIds() {
        boolean assigned = false;
        boolean[] usedIDs = new boolean[BiomeGenBase.func_150565_n().length];

        checkUsedIDs(usedIDs);
        TwilightForestMod.idBiomeLake = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeLake);
        TwilightForestMod.idBiomeTwilightForest = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeTwilightForest);
        TwilightForestMod.idBiomeTwilightForestVariant = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeTwilightForestVariant);
        TwilightForestMod.idBiomeHighlands = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeHighlands);
        TwilightForestMod.idBiomeMushrooms = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeMushrooms);
        TwilightForestMod.idBiomeSwamp = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeSwamp);
        TwilightForestMod.idBiomeStream = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeStream);
        TwilightForestMod.idBiomeSnowfield = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeSnowfield);
        TwilightForestMod.idBiomeGlacier = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeGlacier);
        TwilightForestMod.idBiomeClearing = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeClearing);
        TwilightForestMod.idBiomeOakSavanna = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeOakSavanna);
        TwilightForestMod.idBiomeFireflyForest = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeFireflyForest);
        TwilightForestMod.idBiomeDeepMushrooms = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeDeepMushrooms);
        TwilightForestMod.idBiomeDarkForestCenter = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeDarkForestCenter);
        TwilightForestMod.idBiomeHighlandsCenter = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeHighlandsCenter);
        TwilightForestMod.idBiomeDarkForest = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeDarkForest);
        TwilightForestMod.idBiomeEnchantedForest = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeEnchantedForest);
        TwilightForestMod.idBiomeFireSwamp = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeFireSwamp);
        TwilightForestMod.idBiomeThornlands = assignIdIfNeeded(usedIDs, TwilightForestMod.idBiomeThornlands);
        return assigned;
    }

    private static void checkUsedIDs(boolean[] usedIDs) {
        for (int i = 0; i < usedIDs.length; ++i) {
            usedIDs[i] = BiomeGenBase.func_150565_n()[i] != null;
        }

    }

    private static int assignIdIfNeeded(boolean[] usedIDs, int biomeID) {
        if (biomeID == -1) {
            biomeID = findNextOpenBiomeId(usedIDs);
            TwilightForestMod.hasAssignedBiomeID = true;
        }

        return biomeID;
    }

    private static int findNextOpenBiomeId(boolean[] usedIDs) {
        for (int i = 0; i < 256; ++i) {
            if (!usedIDs[i]) {
                usedIDs[i] = true;
                return i;
            }
        }

        FMLLog.warning("[TwilightForest] Could not find open biome ID.  Edit the Twilight Forest config to give all biomes unique IDs.", new Object[0]);
        return -1;
    }

    public static boolean areThereBiomeIdConflicts() {
        boolean conflict = TwilightForestMod.hasBiomeIdConflicts;

        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeLake);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeTwilightForest);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeTwilightForestVariant);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeHighlands);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeMushrooms);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeSwamp);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeStream);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeSnowfield);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeGlacier);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeClearing);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeOakSavanna);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeFireflyForest);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeDeepMushrooms);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeDarkForestCenter);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeHighlandsCenter);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeDarkForest);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeEnchantedForest);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeFireSwamp);
        conflict |= isConflictAtBiomeID(TwilightForestMod.idBiomeThornlands);
        if (conflict) {
            FMLLog.warning("[TwilightForest] Biome ID conflict detected.  Edit the Twilight Forest config to give all biomes unique IDs.", new Object[0]);
        }

        TwilightForestMod.hasBiomeIdConflicts |= conflict;
        return conflict;
    }

    public static boolean isConflictAtBiomeID(int id) {
        BiomeGenBase biomeAt = BiomeGenBase.func_150568_d(id);

        if (biomeAt != null && !(biomeAt instanceof TFBiomeBase)) {
            FMLLog.warning("[TwilightForest] Biome ID conflict.  Biome ID %d contains a biome named %s, but Twilight Forest is set to use that ID.", new Object[] { Integer.valueOf(id), biomeAt.field_76791_y});
            return true;
        } else {
            return false;
        }
    }

    public static void registerWithBiomeDictionary() {
        BiomeDictionary.registerBiomeType(TFBiomeBase.tfLake, new Type[] { Type.OCEAN});
        BiomeDictionary.registerBiomeType(TFBiomeBase.twilightForest, new Type[] { Type.FOREST});
        BiomeDictionary.registerBiomeType(TFBiomeBase.twilightForest2, new Type[] { Type.FOREST, Type.DENSE});
        BiomeDictionary.registerBiomeType(TFBiomeBase.highlands, new Type[] { Type.FOREST, Type.MOUNTAIN, Type.CONIFEROUS});
        BiomeDictionary.registerBiomeType(TFBiomeBase.mushrooms, new Type[] { Type.FOREST, Type.MUSHROOM});
        BiomeDictionary.registerBiomeType(TFBiomeBase.tfSwamp, new Type[] { Type.SWAMP, Type.WET});
        BiomeDictionary.registerBiomeType(TFBiomeBase.stream, new Type[] { Type.RIVER});
        BiomeDictionary.registerBiomeType(TFBiomeBase.tfSnow, new Type[] { Type.FOREST, Type.SNOWY, Type.COLD, Type.CONIFEROUS});
        BiomeDictionary.registerBiomeType(TFBiomeBase.glacier, new Type[] { Type.COLD, Type.SNOWY, Type.WASTELAND});
        BiomeDictionary.registerBiomeType(TFBiomeBase.clearing, new Type[] { Type.PLAINS, Type.SPARSE});
        BiomeDictionary.registerBiomeType(TFBiomeBase.oakSavanna, new Type[] { Type.FOREST, Type.SPARSE});
        BiomeDictionary.registerBiomeType(TFBiomeBase.fireflyForest, new Type[] { Type.FOREST, Type.LUSH});
        BiomeDictionary.registerBiomeType(TFBiomeBase.deepMushrooms, new Type[] { Type.FOREST, Type.MUSHROOM});
        BiomeDictionary.registerBiomeType(TFBiomeBase.darkForest, new Type[] { Type.FOREST, Type.DENSE, Type.SPOOKY});
        BiomeDictionary.registerBiomeType(TFBiomeBase.enchantedForest, new Type[] { Type.FOREST, Type.MAGICAL});
        BiomeDictionary.registerBiomeType(TFBiomeBase.fireSwamp, new Type[] { Type.SWAMP, Type.WASTELAND, Type.HOT});
        BiomeDictionary.registerBiomeType(TFBiomeBase.darkForestCenter, new Type[] { Type.FOREST, Type.DENSE, Type.SPOOKY, Type.MAGICAL});
        BiomeDictionary.registerBiomeType(TFBiomeBase.highlandsCenter, new Type[] { Type.MESA, Type.DEAD, Type.DRY, Type.WASTELAND});
        BiomeDictionary.registerBiomeType(TFBiomeBase.thornlands, new Type[] { Type.HILLS, Type.DEAD, Type.DRY, Type.WASTELAND});
    }

    public void func_150573_a(World world, Random rand, Block[] blockStorage, byte[] metaStorage, int x, int z, double stoneNoise) {
        this.genTwilightBiomeTerrain(world, rand, blockStorage, metaStorage, x, z, stoneNoise);
    }

    public void genTwilightBiomeTerrain(World world, Random rand, Block[] blockStorage, byte[] metaStorage, int x, int z, double stoneNoise) {
        Block topBlock = this.field_76752_A;
        byte topMeta = (byte) (this.field_150604_aj & 255);
        Block fillerBlock = this.field_76753_B;
        byte fillerMeta = (byte) (this.field_76754_C & 255);
        int currentFillerDepth = -1;
        int maxFillerDepth = (int) (stoneNoise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int maskX = x & 15;
        int maskZ = z & 15;
        int worldHeight = blockStorage.length / 256;
        byte seaLevel = 32;

        for (int y = 255; y >= 0; --y) {
            int index = (maskZ * 16 + maskX) * worldHeight + y;

            if (y <= 0 + rand.nextInt(5)) {
                blockStorage[index] = Blocks.field_150357_h;
            } else {
                Block currentBlock = blockStorage[index];

                if (currentBlock != null && currentBlock.func_149688_o() != Material.field_151579_a && currentBlock == Blocks.field_150348_b) {
                    if (this.getStoneReplacementBlock() != null) {
                        blockStorage[index] = this.getStoneReplacementBlock();
                        metaStorage[index] = this.getStoneReplacementMeta();
                    }

                    if (currentFillerDepth == -1) {
                        if (maxFillerDepth <= 0) {
                            topBlock = null;
                            topMeta = 0;
                            fillerBlock = Blocks.field_150348_b;
                            fillerMeta = 0;
                        } else if (y >= seaLevel - 5 && y <= seaLevel) {
                            topBlock = this.field_76752_A;
                            topMeta = (byte) (this.field_150604_aj & 255);
                            fillerBlock = this.field_76753_B;
                            fillerMeta = (byte) (this.field_76754_C & 255);
                        }

                        if (y < seaLevel - 1 && (topBlock == null || topBlock.func_149688_o() == Material.field_151579_a)) {
                            if (this.func_150564_a(x, y, z) < 0.15F) {
                                topBlock = Blocks.field_150432_aD;
                                topMeta = 0;
                            } else {
                                topBlock = Blocks.field_150355_j;
                                topMeta = 0;
                            }
                        }

                        currentFillerDepth = maxFillerDepth;
                        if (y >= seaLevel - 2) {
                            blockStorage[index] = topBlock;
                            metaStorage[index] = topMeta;
                        } else if (y < seaLevel - 8 - maxFillerDepth) {
                            topBlock = null;
                            fillerBlock = Blocks.field_150348_b;
                            fillerMeta = 0;
                            blockStorage[index] = Blocks.field_150351_n;
                        } else {
                            blockStorage[index] = fillerBlock;
                            metaStorage[index] = fillerMeta;
                        }
                    } else if (currentFillerDepth > 0) {
                        --currentFillerDepth;
                        blockStorage[index] = fillerBlock;
                        metaStorage[index] = fillerMeta;
                        if (currentFillerDepth == 0 && fillerBlock == Blocks.field_150354_m) {
                            currentFillerDepth = rand.nextInt(4) + Math.max(0, y - (seaLevel - 1));
                            fillerBlock = Blocks.field_150322_A;
                            fillerMeta = 0;
                        }
                    }
                }
            }
        }

    }

    public Block getStoneReplacementBlock() {
        return null;
    }

    public byte getStoneReplacementMeta() {
        return (byte) 0;
    }

    public boolean doesPlayerHaveRequiredAchievement(EntityPlayer player) {
        if (this.getRequiredAchievement() != null && player instanceof EntityPlayerMP && ((EntityPlayerMP) player).func_147099_x() != null) {
            StatisticsFile stats1 = ((EntityPlayerMP) player).func_147099_x();

            return stats1.func_77443_a(this.getRequiredAchievement());
        } else if (this.getRequiredAchievement() != null && player instanceof EntityClientPlayerMP && ((EntityClientPlayerMP) player).func_146107_m() != null) {
            StatFileWriter stats = ((EntityClientPlayerMP) player).func_146107_m();

            return stats.func_77443_a(this.getRequiredAchievement());
        } else {
            return true;
        }
    }

    protected Achievement getRequiredAchievement() {
        return null;
    }

    public void enforceProgession(EntityPlayer player, World world) {}

    public List getUndergroundSpawnableList() {
        return this.undergroundMonsterList;
    }

    static {
        assignBlankBiomeIds();
        areThereBiomeIdConflicts();
        tfLake = (new TFBiomeTwilightLake(TwilightForestMod.idBiomeLake)).setColor(255).func_76735_a("Twilight Lake").func_150570_a(TFBiomeBase.field_150592_d);
        twilightForest = (new TFBiomeTwilightForest(TwilightForestMod.idBiomeTwilightForest)).setColor(21760).func_76735_a("Twilight Forest");
        twilightForest2 = (new TFBiomeTwilightForestVariant(TwilightForestMod.idBiomeTwilightForestVariant)).setColor(21794).func_76735_a("Dense Twilight Forest").func_150570_a(TFBiomeBase.field_150590_f);
        highlands = (new TFBiomeHighlands(TwilightForestMod.idBiomeHighlands)).setColor(5596740).func_76735_a("Twilight Highlands").func_150570_a(new Height(3.5F, 0.05F));
        mushrooms = (new TFBiomeMushrooms(TwilightForestMod.idBiomeMushrooms)).setColor(2254370).func_76735_a("Mushroom Forest");
        tfSwamp = (new TFBiomeSwamp(TwilightForestMod.idBiomeSwamp)).setColor(3359778).func_76735_a("Twilight Swamp").func_150570_a(new Height(-0.125F, 0.125F));
        stream = (new TFBiomeStream(TwilightForestMod.idBiomeStream)).setColor(3298231).func_76735_a("Twilight Stream").func_150570_a(TFBiomeBase.field_150594_b);
        tfSnow = (new TFBiomeSnow(TwilightForestMod.idBiomeSnowfield)).setColor(13434879).func_76735_a("Snowy Forest").func_150570_a(TFBiomeBase.field_150590_f);
        glacier = (new TFBiomeGlacier(TwilightForestMod.idBiomeGlacier)).setColor(7829435).func_76735_a("Twilight Glacier");
        clearing = (new TFBiomeClearing(TwilightForestMod.idBiomeClearing)).setColor(3447604).func_76735_a("Twilight Clearing").func_150570_a(TFBiomeBase.field_150593_e);
        oakSavanna = (new TFBiomeOakSavanna(TwilightForestMod.idBiomeOakSavanna)).setColor(4482594).func_76735_a("Oak Savanna").func_150570_a(TFBiomeBase.field_150590_f);
        fireflyForest = (new TFBiomeFireflyForest(TwilightForestMod.idBiomeFireflyForest)).setColor(26163).func_76735_a("Firefly Forest").func_150570_a(TFBiomeBase.field_150593_e);
        deepMushrooms = (new TFBiomeDeepMushrooms(TwilightForestMod.idBiomeDeepMushrooms)).setColor(6697762).func_76735_a("Deep Mushroom Forest").func_150570_a(TFBiomeBase.field_150593_e);
        darkForest = (new TFBiomeDarkForest(TwilightForestMod.idBiomeDarkForest)).setColor(13073).func_76735_a("Dark Forest").func_150570_a(TFBiomeBase.field_150593_e);
        enchantedForest = (new TFBiomeEnchantedForest(TwilightForestMod.idBiomeEnchantedForest)).setColor(1135974).func_76735_a("Enchanted Forest");
        fireSwamp = (new TFBiomeFireSwamp(TwilightForestMod.idBiomeFireSwamp)).setColor(4334362).func_76735_a("Fire Swamp").func_150570_a(TFBiomeBase.field_150596_a);
        darkForestCenter = (new TFBiomeDarkForestCenter(TwilightForestMod.idBiomeDarkForestCenter)).setColor(8704).func_76735_a("Dark Forest Center").func_150570_a(TFBiomeBase.field_150593_e);
        highlandsCenter = (new TFBiomeFinalPlateau(TwilightForestMod.idBiomeHighlandsCenter)).setColor(3359778).func_76735_a("Highlands Center").func_150570_a(new Height(10.5F, 0.025F));
        thornlands = (new TFBiomeThornlands(TwilightForestMod.idBiomeThornlands)).setColor(2241314).func_76735_a("Thornlands").func_150570_a(new Height(6.0F, 0.1F));
    }
}
