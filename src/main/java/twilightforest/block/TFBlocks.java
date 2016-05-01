package twilightforest.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import twilightforest.item.ItemBlockTFAuroraSlab;
import twilightforest.item.ItemBlockTFDeadrock;
import twilightforest.item.ItemBlockTFHugeLilyPad;
import twilightforest.item.ItemBlockTFHugeWaterLily;
import twilightforest.item.ItemBlockTFMeta;
import twilightforest.item.ItemBlockTFPlant;
import twilightforest.item.ItemBlockTFThorns;

public class TFBlocks {

    public static Block log;
    public static Block leaves;
    public static Block firefly;
    public static Block portal;
    public static Block mazestone;
    public static Block hedge;
    public static Block bossSpawner;
    public static Block fireflyJar;
    public static Block plant;
    public static Block cicada;
    public static Block root;
    public static Block uncraftingTable;
    public static Block fireJet;
    public static Block nagastone;
    public static Block sapling;
    public static Block magicLog;
    public static Block magicLogSpecial;
    public static Block magicLeaves;
    public static Block moonworm;
    public static Block towerWood;
    public static Block towerDevice;
    public static Block towerTranslucent;
    public static Block trophy;
    public static Block shield;
    public static Block trophyPedestal;
    public static Block auroraBlock;
    public static Block underBrick;
    public static Block thorns;
    public static Block burntThorns;
    public static Block thornRose;
    public static Block leaves3;
    public static Block deadrock;
    public static Block darkleaves;
    public static Block auroraPillar;
    public static BlockSlab auroraSlab;
    public static BlockSlab auroraDoubleSlab;
    public static Block trollSteinn;
    public static Block wispyCloud;
    public static Block fluffyCloud;
    public static Block giantCobble;
    public static Block giantLog;
    public static Block giantLeaves;
    public static Block giantObsidian;
    public static Block uberousSoil;
    public static Block hugeStalk;
    public static Block hugeGloomBlock;
    public static Block trollVidr;
    public static Block unripeTrollBer;
    public static Block trollBer;
    public static Block knightmetalStorage;
    public static Block hugeLilyPad;
    public static Block hugeWaterLily;
    public static Block slider;
    public static Block castleBlock;
    public static Block castleMagic;
    public static Block forceField;

    public static void registerBlocks() {
        TFBlocks.log = (new BlockTFLog()).func_149663_c("TFLog");
        TFBlocks.leaves = (new BlockTFLeaves()).func_149663_c("TFLeaves").func_149658_d("leaves_oak");
        TFBlocks.firefly = (new BlockTFFirefly()).func_149663_c("TFFirefly");
        TFBlocks.cicada = (new BlockTFCicada()).func_149663_c("TFCicada");
        TFBlocks.portal = (new BlockTFPortal()).func_149663_c("TFPortal");
        TFBlocks.mazestone = (new BlockTFMazestone()).func_149663_c("TFMazestone");
        TFBlocks.hedge = (new BlockTFHedge()).func_149663_c("TFHedge");
        TFBlocks.bossSpawner = (new BlockTFBossSpawner()).func_149663_c("TFBossSpawner");
        TFBlocks.fireflyJar = (new BlockTFFireflyJar()).func_149663_c("TFFireflyJar");
        TFBlocks.plant = (new BlockTFPlant()).func_149663_c("TFPlant");
        TFBlocks.root = (new BlockTFRoots()).func_149663_c("TFRoots");
        TFBlocks.uncraftingTable = (new BlockTFUncraftingTable()).func_149663_c("TFUncraftingTable");
        TFBlocks.fireJet = (new BlockTFFireJet()).func_149663_c("TFFireJet");
        TFBlocks.nagastone = (new BlockTFNagastone()).func_149663_c("TFNagastone");
        TFBlocks.sapling = (new BlockTFSapling()).func_149663_c("TFSapling");
        TFBlocks.magicLog = (new BlockTFMagicLog()).func_149663_c("TFMagicLog");
        TFBlocks.magicLogSpecial = (new BlockTFMagicLogSpecial()).func_149663_c("TFMagicLogSpecial");
        TFBlocks.magicLeaves = (new BlockTFMagicLeaves()).func_149663_c("TFMagicLeaves");
        TFBlocks.moonworm = (new BlockTFMoonworm()).func_149663_c("TFMoonworm");
        TFBlocks.towerWood = (new BlockTFTowerWood()).func_149663_c("TFTowerStone");
        TFBlocks.towerDevice = (new BlockTFTowerDevice()).func_149663_c("TFTowerDevice");
        TFBlocks.towerTranslucent = (new BlockTFTowerTranslucent()).func_149663_c("TFTowerTranslucent");
        TFBlocks.trophy = (new BlockTFTrophy()).func_149663_c("TFTrophy");
        TFBlocks.shield = (new BlockTFShield()).func_149663_c("TFShield");
        TFBlocks.trophyPedestal = (new BlockTFTrophyPedestal()).func_149663_c("TFTrophyPedestal");
        TFBlocks.auroraBlock = (new BlockTFAuroraBrick()).func_149663_c("TFAuroraBrick");
        TFBlocks.underBrick = (new BlockTFUnderBrick()).func_149663_c("TFUnderBrick");
        TFBlocks.thorns = (new BlockTFThorns()).func_149663_c("TFThorns");
        TFBlocks.burntThorns = (new BlockTFBurntThorns()).func_149663_c("TFBurntThorns");
        TFBlocks.thornRose = (new BlockTFThornRose()).func_149663_c("TFThornRose").func_149658_d("TwilightForest:thornRose");
        TFBlocks.leaves3 = (new BlockTFLeaves3()).func_149663_c("TFLeaves3").func_149658_d("leaves_oak");
        TFBlocks.deadrock = (new BlockTFDeadrock()).func_149663_c("TFDeadrock");
        TFBlocks.darkleaves = (new BlockTFDarkLeaves()).func_149663_c("DarkLeaves");
        TFBlocks.auroraPillar = (new BlockTFAuroraPillar()).func_149663_c("AuroraPillar");
        TFBlocks.auroraSlab = (BlockSlab) (new BlockTFAuroraSlab(false)).func_149663_c("AuroraSlab");
        TFBlocks.auroraDoubleSlab = (BlockSlab) (new BlockTFAuroraSlab(true)).func_149663_c("AuroraDoubleSlab");
        TFBlocks.trollSteinn = (new BlockTFTrollSteinn()).func_149663_c("TrollSteinn");
        TFBlocks.wispyCloud = (new BlockTFWispyCloud()).func_149663_c("WispyCloud");
        TFBlocks.fluffyCloud = (new BlockTFFluffyCloud()).func_149663_c("FluffyCloud");
        TFBlocks.giantCobble = (new BlockTFGiantCobble()).func_149663_c("GiantCobble");
        TFBlocks.giantLog = (new BlockTFGiantLog()).func_149663_c("GiantLog");
        TFBlocks.giantLeaves = (new BlockTFGiantLeaves()).func_149663_c("GiantLeaves");
        TFBlocks.giantObsidian = (new BlockTFGiantObsidian()).func_149663_c("GiantObsidian");
        TFBlocks.uberousSoil = (new BlockTFUberousSoil()).func_149663_c("UberousSoil");
        TFBlocks.hugeStalk = (new BlockTFHugeStalk()).func_149663_c("HugeStalk");
        TFBlocks.hugeGloomBlock = (new BlockTFHugeGloomBlock()).func_149663_c("HugeGloomBlock");
        TFBlocks.trollVidr = (new BlockTFTrollRoot()).func_149663_c("TrollVidr");
        TFBlocks.unripeTrollBer = (new BlockTFUnripeTorchCluster()).func_149663_c("UnripeTrollBer");
        TFBlocks.trollBer = (new BlockTFRipeTorchCluster()).func_149663_c("TrollBer");
        TFBlocks.knightmetalStorage = (new BlockTFKnightmetalBlock()).func_149663_c("KnightmetalBlock");
        TFBlocks.hugeLilyPad = (new BlockTFHugeLilyPad()).func_149663_c("HugeLilyPad");
        TFBlocks.hugeWaterLily = (new BlockTFHugeWaterLily()).func_149663_c("HugeWaterLily").func_149658_d("TwilightForest:huge_waterlily");
        TFBlocks.slider = (new BlockTFSlider()).func_149663_c("Slider");
        TFBlocks.castleBlock = (new BlockTFCastleBlock()).func_149663_c("CastleBrick");
        TFBlocks.castleMagic = (new BlockTFCastleMagic()).func_149663_c("CastleMagic");
        TFBlocks.forceField = (new BlockTFForceField()).func_149663_c("ForceField");
        registerMyBlock(TFBlocks.log);
        registerMyBlock(TFBlocks.root);
        registerMyBlock(TFBlocks.leaves);
        registerMyBlock(TFBlocks.firefly, ItemBlock.class);
        registerMyBlock(TFBlocks.cicada, ItemBlock.class);
        registerMyBlock(TFBlocks.portal, ItemBlock.class);
        registerMyBlock(TFBlocks.mazestone);
        registerMyBlock(TFBlocks.hedge);
        registerMyBlock(TFBlocks.bossSpawner);
        registerMyBlock(TFBlocks.fireflyJar, ItemBlock.class);
        registerMyBlock(TFBlocks.plant, ItemBlockTFPlant.class);
        registerMyBlock(TFBlocks.uncraftingTable, ItemBlock.class);
        registerMyBlock(TFBlocks.fireJet);
        registerMyBlock(TFBlocks.nagastone);
        registerMyBlock(TFBlocks.sapling);
        registerMyBlock(TFBlocks.moonworm, ItemBlock.class);
        registerMyBlock(TFBlocks.magicLog);
        registerMyBlock(TFBlocks.magicLeaves);
        registerMyBlock(TFBlocks.magicLogSpecial);
        registerMyBlock(TFBlocks.towerWood);
        registerMyBlock(TFBlocks.towerDevice);
        registerMyBlock(TFBlocks.towerTranslucent);
        registerMyBlock(TFBlocks.trophy);
        registerMyBlock(TFBlocks.shield);
        registerMyBlock(TFBlocks.trophyPedestal);
        registerMyBlock(TFBlocks.auroraBlock, ItemBlock.class);
        registerMyBlock(TFBlocks.underBrick);
        registerMyBlock(TFBlocks.thorns, ItemBlockTFThorns.class, TFBlocks.thorns, ((BlockTFThorns) TFBlocks.thorns).getNames());
        registerMyBlock(TFBlocks.burntThorns, ItemBlock.class);
        registerMyBlock(TFBlocks.thornRose, ItemBlock.class);
        registerMyBlock(TFBlocks.leaves3);
        registerMyBlock(TFBlocks.deadrock, ItemBlockTFDeadrock.class, TFBlocks.deadrock, BlockTFDeadrock.names);
        registerMyBlock(TFBlocks.darkleaves, ItemBlock.class);
        registerMyBlock(TFBlocks.auroraPillar, ItemBlock.class);
        registerMyBlock(TFBlocks.auroraSlab, ItemBlockTFAuroraSlab.class, TFBlocks.auroraSlab, TFBlocks.auroraDoubleSlab, false);
        registerMyBlock(TFBlocks.auroraDoubleSlab, ItemBlockTFAuroraSlab.class, TFBlocks.auroraSlab, TFBlocks.auroraDoubleSlab, true);
        registerMyBlock(TFBlocks.trollSteinn, ItemBlock.class);
        registerMyBlock(TFBlocks.wispyCloud, ItemBlock.class);
        registerMyBlock(TFBlocks.fluffyCloud, ItemBlock.class);
        registerMyBlock(TFBlocks.giantCobble, ItemBlock.class);
        registerMyBlock(TFBlocks.giantLog, ItemBlock.class);
        registerMyBlock(TFBlocks.giantLeaves, ItemBlock.class);
        registerMyBlock(TFBlocks.giantObsidian, ItemBlock.class);
        registerMyBlock(TFBlocks.uberousSoil, ItemBlock.class);
        registerMyBlock(TFBlocks.hugeStalk, ItemBlock.class);
        registerMyBlock(TFBlocks.hugeGloomBlock, ItemBlock.class);
        registerMyBlock(TFBlocks.trollVidr, ItemBlock.class);
        registerMyBlock(TFBlocks.unripeTrollBer, ItemBlock.class);
        registerMyBlock(TFBlocks.trollBer, ItemBlock.class);
        registerMyBlock(TFBlocks.knightmetalStorage, ItemBlock.class);
        registerMyBlock(TFBlocks.hugeLilyPad, ItemBlockTFHugeLilyPad.class);
        registerMyBlock(TFBlocks.hugeWaterLily, ItemBlockTFHugeWaterLily.class);
        registerMyBlock(TFBlocks.slider);
        registerMyBlock(TFBlocks.castleBlock);
        registerMyBlock(TFBlocks.castleMagic);
        registerMyBlock(TFBlocks.forceField);
        Blocks.field_150480_ab.setFireInfo(TFBlocks.log, 5, 5);
        Blocks.field_150480_ab.setFireInfo(TFBlocks.leaves, 30, 60);
        Blocks.field_150480_ab.setFireInfo(TFBlocks.leaves3, 30, 60);
    }

    private static void registerMyBlock(Block block, Class pickup, BlockSlab singleSlab, BlockSlab doubleSlab, boolean isDouble) {
        GameRegistry.registerBlock(block, pickup, block.func_149739_a(), new Object[] { singleSlab, doubleSlab, Boolean.valueOf(isDouble)});
    }

    private static void registerMyBlock(Block block, Class pickup, Block blockAgain, String[] names) {
        GameRegistry.registerBlock(block, pickup, block.func_149739_a(), new Object[] { blockAgain, names});
    }

    private static void registerMyBlock(Block block, Class pickup) {
        GameRegistry.registerBlock(block, pickup, block.func_149739_a());
    }

    private static void registerMyBlock(Block block) {
        GameRegistry.registerBlock(block, ItemBlockTFMeta.class, block.func_149739_a());
    }
}
