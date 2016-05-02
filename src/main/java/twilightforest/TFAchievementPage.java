package twilightforest;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class TFAchievementPage extends AchievementPage {

    public static Achievement twilightPortal = (new Achievement("TwilightForest1", "twilightPortal", -2, 1, TFBlocks.portal, (Achievement) null)).setSpecial().registerStat();
    public static Achievement twilightArrival = (new Achievement("TwilightForest2", "twilightArrival", 0, 0, new ItemStack(TFBlocks.log, 1, 9), TFAchievementPage.twilightPortal)).registerStat();
    public static Achievement twilightHunter = (new Achievement("TwilightForest3", "twilightHunter", 2, 2, TFItems.feather, TFAchievementPage.twilightArrival)).registerStat();
    public static Achievement twilightMagicMapFocus = (new Achievement("TwilightForest5", "twilightMagicMapFocus", 2, 0, TFItems.magicMapFocus, TFAchievementPage.twilightHunter)).registerStat();
    public static Achievement twilightHill1 = (new Achievement("TwilightForest10", "twilightHill1", -2, -1, Blocks.iron_ore, TFAchievementPage.twilightArrival)).registerStat();
    public static Achievement twilightHill2 = (new Achievement("TwilightForest11", "twilightHill2", -3, -2, Blocks.gold_ore, TFAchievementPage.twilightArrival)).registerStat();
    public static Achievement twilightHill3 = (new Achievement("TwilightForest12", "twilightHill3", -1, -3, Blocks.diamond_ore, TFAchievementPage.twilightArrival)).registerStat();
    public static Achievement twilightHedge = (new Achievement("TwilightForest13", "twilightHedge", 2, -3, TFBlocks.hedge, TFAchievementPage.twilightArrival)).registerStat();
    public static Achievement twilightMagicMap = (new Achievement("TwilightForest14", "twilightMagicMap", 4, -1, TFItems.magicMap, TFAchievementPage.twilightMagicMapFocus)).registerStat();
    public static Achievement twilightKillNaga = (new Achievement("TwilightForest6", "twilightKillNaga", 4, 3, new ItemStack(TFItems.trophy, 1, 1), TFAchievementPage.twilightHunter)).registerStat();
    public static Achievement twilightProgressNaga = (new Achievement("TwilightForest17", "twilightProgressNaga", 4, 5, TFItems.nagaScale, TFAchievementPage.twilightKillNaga)).registerStat();
    public static Achievement twilightKillLich = (new Achievement("TwilightForest8", "twilightKillLich", 2, 5, new ItemStack(TFItems.trophy, 1, 2), TFAchievementPage.twilightProgressNaga)).registerStat();
    public static Achievement twilightProgressLich = (new Achievement("TwilightForest18", "twilightProgressLich", -1, 4, TFItems.scepterLifeDrain, TFAchievementPage.twilightKillLich)).registerStat();
    public static Achievement twilightProgressLabyrinth = (new Achievement("TwilightForest28", "twilightProgressLabyrinth", -4, 6, TFItems.meefStroganoff, TFAchievementPage.twilightProgressLich)).registerStat();
    public static Achievement twilightKillHydra = (new Achievement("TwilightForest30", "twilightKillHydra", -6, 4, new ItemStack(TFItems.trophy, 1, 0), TFAchievementPage.twilightProgressLabyrinth)).registerStat();
    public static Achievement twilightProgressHydra = (new Achievement("TwilightForest20", "twilightProgressHydra", -8, 4, TFItems.fieryBlood, TFAchievementPage.twilightKillHydra)).registerStat();
    public static Achievement twilightProgressTrophyPedestal = (new Achievement("TwilightForest29", "twilightProgressTrophyPedestal", -5, 2, TFBlocks.trophyPedestal, TFAchievementPage.twilightProgressHydra)).registerStat();
    public static Achievement twilightProgressKnights = (new Achievement("TwilightForest21", "twilightProgressKnights", -5, -1, TFItems.phantomHelm, TFAchievementPage.twilightProgressTrophyPedestal)).registerStat();
    public static Achievement twilightProgressUrghast = (new Achievement("TwilightForest22", "twilightProgressUrghast", -7, -1, new ItemStack(TFItems.trophy, 1, 3), TFAchievementPage.twilightProgressKnights)).registerStat();
    public static Achievement twilightProgressYeti = (new Achievement("TwilightForest23", "twilightProgressYeti", -7, -3, TFItems.alphaFur, TFAchievementPage.twilightProgressUrghast)).registerStat();
    public static Achievement twilightProgressGlacier = (new Achievement("TwilightForest24", "twilightProgressGlacier", -5, -5, new ItemStack(TFItems.trophy, 1, 4), TFAchievementPage.twilightProgressYeti)).registerStat();
    public static Achievement twilightProgressTroll = (new Achievement("TwilightForest25", "twilightProgressTroll", -5, -7, TFItems.lampOfCinders, TFAchievementPage.twilightProgressGlacier)).registerStat();
    public static Achievement twilightProgressThorns = (new Achievement("TwilightForest26", "twilightProgressThorns", -3, -7, TFBlocks.thorns, TFAchievementPage.twilightProgressTroll)).registerStat();
    public static Achievement twilightProgressCastle = (new Achievement("TwilightForest27", "twilightProgressCastle", -1, -7, Blocks.stonebrick, TFAchievementPage.twilightProgressThorns)).registerStat();
    public static Achievement twilightNagaArmors = (new Achievement("TwilightForest7", "twilightNagaArmors", 5, 1, TFItems.plateNaga, TFAchievementPage.twilightKillNaga)).setSpecial().registerStat();
    public static Achievement twilightLichScepters = (new Achievement("TwilightForest9", "twilightLichScepters", 3, 7, TFItems.scepterZombie, TFAchievementPage.twilightKillLich)).setSpecial().registerStat();
    public static Achievement twilightMazeMap = (new Achievement("TwilightForest15", "twilightMazeMap", 1, 7, TFItems.mazeMap, TFAchievementPage.twilightProgressLich)).registerStat();
    public static Achievement twilightOreMap = (new Achievement("TwilightForest16", "twilightOreMap", 1, 9, TFItems.oreMap, TFAchievementPage.twilightMazeMap)).setSpecial().registerStat();
    public static Achievement twilightHydraChop = (new Achievement("TwilightForest31", "twilightHydraChop", -6, 6, TFItems.hydraChop, TFAchievementPage.twilightKillHydra)).registerStat();
    public static Achievement twilightMazebreaker = (new Achievement("TwilightForest32", "twilightMazebreaker", -3, 4, TFItems.mazebreakerPick, TFAchievementPage.twilightProgressLich)).setSpecial().registerStat();
    public static Achievement twilightFierySet = (new Achievement("TwilightForest33", "twilightFierySet", -8, 7, TFItems.fierySword, TFAchievementPage.twilightProgressHydra)).setSpecial().registerStat();
    public static Achievement twilightQuestRam = (new Achievement("TwilightForest34", "twilightQuestRam", 1, -5, TFItems.crumbleHorn, TFAchievementPage.twilightArrival)).setSpecial().registerStat();

    public TFAchievementPage() {
        super("Twilight Forest", new Achievement[] { TFAchievementPage.twilightPortal, TFAchievementPage.twilightArrival, TFAchievementPage.twilightHunter, TFAchievementPage.twilightMagicMapFocus, TFAchievementPage.twilightKillNaga, TFAchievementPage.twilightNagaArmors, TFAchievementPage.twilightKillLich, TFAchievementPage.twilightLichScepters, TFAchievementPage.twilightHill1, TFAchievementPage.twilightHill2, TFAchievementPage.twilightHill3, TFAchievementPage.twilightHedge, TFAchievementPage.twilightMagicMap, TFAchievementPage.twilightMazeMap, TFAchievementPage.twilightOreMap, TFAchievementPage.twilightProgressNaga, TFAchievementPage.twilightProgressLich, TFAchievementPage.twilightProgressLabyrinth, TFAchievementPage.twilightHydraChop, TFAchievementPage.twilightProgressKnights, TFAchievementPage.twilightProgressUrghast, TFAchievementPage.twilightProgressYeti, TFAchievementPage.twilightProgressGlacier, TFAchievementPage.twilightProgressTroll, TFAchievementPage.twilightProgressThorns, TFAchievementPage.twilightProgressCastle, TFAchievementPage.twilightKillHydra, TFAchievementPage.twilightHydraChop, TFAchievementPage.twilightProgressTrophyPedestal, TFAchievementPage.twilightProgressHydra, TFAchievementPage.twilightMazebreaker, TFAchievementPage.twilightFierySet, TFAchievementPage.twilightQuestRam});
    }
}
