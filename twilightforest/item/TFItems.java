package twilightforest.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.util.EnumHelper;
import twilightforest.TwilightForestMod;

public class TFItems {

    public static ArmorMaterial ARMOR_NAGA = EnumHelper.addArmorMaterial("NAGA_SCALE", 21, new int[] { 2, 7, 6, 3}, 15);
    public static ArmorMaterial ARMOR_IRONWOOD = EnumHelper.addArmorMaterial("IRONWOOD", 20, new int[] { 2, 7, 5, 2}, 15);
    public static ArmorMaterial ARMOR_FIERY = EnumHelper.addArmorMaterial("FIERY", 25, new int[] { 4, 9, 7, 4}, 10);
    public static ArmorMaterial ARMOR_STEELEAF = EnumHelper.addArmorMaterial("STEELEAF", 10, new int[] { 3, 8, 6, 3}, 9);
    public static ArmorMaterial ARMOR_KNIGHTLY = EnumHelper.addArmorMaterial("KNIGHTMETAL", 20, new int[] { 3, 8, 6, 3}, 8);
    public static ArmorMaterial ARMOR_PHANTOM = EnumHelper.addArmorMaterial("KNIGHTPHANTOM", 30, new int[] { 3, 8, 6, 3}, 8);
    public static ArmorMaterial ARMOR_YETI = EnumHelper.addArmorMaterial("YETI", 20, new int[] { 4, 7, 6, 3}, 15);
    public static ArmorMaterial ARMOR_ARCTIC = EnumHelper.addArmorMaterial("ARCTIC", 10, new int[] { 2, 7, 5, 2}, 8);
    public static ToolMaterial TOOL_IRONWOOD = EnumHelper.addToolMaterial("IRONWOOD", 2, 512, 6.5F, 2.0F, 25);
    public static ToolMaterial TOOL_FIERY = EnumHelper.addToolMaterial("FIERY", 4, 1024, 9.0F, 4.0F, 10);
    public static ToolMaterial TOOL_STEELEAF = EnumHelper.addToolMaterial("STEELEAF", 3, 131, 8.0F, 3.0F, 9);
    public static ToolMaterial TOOL_KNIGHTLY = EnumHelper.addToolMaterial("KNIGHTMETAL", 3, 512, 8.0F, 3.0F, 8);
    public static ToolMaterial TOOL_GIANT = EnumHelper.addToolMaterial("GIANTSTONE", 1, 1024, 4.0F, 1.0F, 5);
    public static ToolMaterial TOOL_ICE = EnumHelper.addToolMaterial("TFICE", 0, 32, 1.0F, 3.5F, 5);
    public static ToolMaterial TOOL_GLASS = EnumHelper.addToolMaterial("TFGLASS", 0, 1, 1.0F, 36.0F, 30);
    public static Item nagaScale;
    public static Item plateNaga;
    public static Item legsNaga;
    public static Item scepterTwilight;
    public static Item scepterLifeDrain;
    public static Item scepterZombie;
    public static Item wandPacification;
    public static Item oreMeter;
    public static Item magicMap;
    public static Item mazeMap;
    public static Item oreMap;
    public static Item feather;
    public static Item magicMapFocus;
    public static Item mazeMapFocus;
    public static Item liveRoot;
    public static Item ironwoodRaw;
    public static Item ironwoodIngot;
    public static Item ironwoodHelm;
    public static Item ironwoodPlate;
    public static Item ironwoodLegs;
    public static Item ironwoodBoots;
    public static Item ironwoodSword;
    public static Item ironwoodShovel;
    public static Item ironwoodPick;
    public static Item ironwoodAxe;
    public static Item ironwoodHoe;
    public static Item torchberries;
    public static Item spawnEgg;
    public static Item venisonRaw;
    public static Item venisonCooked;
    public static Item hydraChop;
    public static Item fieryBlood;
    public static Item trophy;
    public static Item fieryIngot;
    public static Item fieryHelm;
    public static Item fieryPlate;
    public static Item fieryLegs;
    public static Item fieryBoots;
    public static Item fierySword;
    public static Item fieryPick;
    public static Item steeleafIngot;
    public static Item steeleafHelm;
    public static Item steeleafPlate;
    public static Item steeleafLegs;
    public static Item steeleafBoots;
    public static Item steeleafSword;
    public static Item steeleafShovel;
    public static Item steeleafPick;
    public static Item steeleafAxe;
    public static Item steeleafHoe;
    public static Item minotaurAxe;
    public static Item mazebreakerPick;
    public static Item transformPowder;
    public static Item meefRaw;
    public static Item meefSteak;
    public static Item meefStroganoff;
    public static Item mazeWafer;
    public static Item emptyMagicMap;
    public static Item emptyMazeMap;
    public static Item emptyOreMap;
    public static Item oreMagnet;
    public static Item crumbleHorn;
    public static Item peacockFan;
    public static Item moonwormQueen;
    public static Item charmOfLife1;
    public static Item charmOfLife2;
    public static Item charmOfKeeping1;
    public static Item charmOfKeeping2;
    public static Item charmOfKeeping3;
    public static Item towerKey;
    public static Item borerEssence;
    public static Item carminite;
    public static Item experiment115;
    public static Item armorShard;
    public static Item knightMetal;
    public static Item shardCluster;
    public static Item knightlyHelm;
    public static Item knightlyPlate;
    public static Item knightlyLegs;
    public static Item knightlyBoots;
    public static Item knightlySword;
    public static Item knightlyPick;
    public static Item knightlyAxe;
    public static Item phantomHelm;
    public static Item phantomPlate;
    public static Item lampOfCinders;
    public static Item fieryTears;
    public static Item alphaFur;
    public static Item yetiHelm;
    public static Item yetiPlate;
    public static Item yetiLegs;
    public static Item yetiBoots;
    public static Item iceBomb;
    public static Item arcticFur;
    public static Item arcticHelm;
    public static Item arcticPlate;
    public static Item arcticLegs;
    public static Item arcticBoots;
    public static Item magicBeans;
    public static Item giantPick;
    public static Item giantSword;
    public static Item tripleBow;
    public static Item seekerBow;
    public static Item iceBow;
    public static Item enderBow;
    public static Item iceSword;
    public static Item glassSword;
    public static Item knightmetalRing;
    public static Item chainBlock;
    public static Item cubeTalisman;
    public static Item cubeOfAnnihilation;
    public static CreativeTabTwilightForest creativeTab = new CreativeTabTwilightForest("twilightForest");

    public static void registerItems() {
        int nagaScaleRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/nagascale_");
        int ironwoodRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/ironwood_");
        int fieryRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/fiery_");
        int steeleafRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/steeleaf_");
        int knightlyRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/knightly_");
        int phantomRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/phantom_");
        int yetiRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/yeti_");
        int arcticRenderID = TwilightForestMod.proxy.registerArmorRenderID("twilightforest/arctic_");

        TFItems.nagaScale = (new ItemTF()).func_77655_b("nagaScale");
        TFItems.plateNaga = (new ItemTFNagaArmor(TFItems.ARMOR_NAGA, nagaScaleRenderID, 1)).func_77655_b("plateNaga").func_77625_d(1);
        TFItems.legsNaga = (new ItemTFNagaArmor(TFItems.ARMOR_NAGA, nagaScaleRenderID, 2)).func_77655_b("legsNaga").func_77625_d(1);
        TFItems.scepterTwilight = (new ItemTFTwilightWand()).func_77655_b("scepterTwilight").func_77625_d(1).func_77664_n();
        TFItems.scepterLifeDrain = (new ItemTFScepterLifeDrain()).func_77655_b("scepterLifeDrain").func_77625_d(1).func_77664_n();
        TFItems.scepterZombie = (new ItemTFZombieWand()).func_77655_b("scepterZombie").func_77625_d(1).func_77664_n();
        TFItems.oreMeter = (new ItemTFOreMeter()).func_77655_b("oreMeter").func_77625_d(1);
        TFItems.magicMap = (new ItemTFMagicMap()).func_77655_b("magicMap").func_77625_d(1);
        TFItems.mazeMap = (new ItemTFMazeMap(false)).func_77655_b("mazeMap").func_77625_d(1);
        TFItems.oreMap = (new ItemTFMazeMap(true)).func_77655_b("oreMap").func_77625_d(1);
        TFItems.feather = (new ItemTF()).func_77655_b("tfFeather");
        TFItems.magicMapFocus = (new ItemTF()).func_77655_b("magicMapFocus");
        TFItems.mazeMapFocus = (new ItemTF()).func_77655_b("mazeMapFocus");
        TFItems.liveRoot = (new ItemTF()).func_77655_b("liveRoot");
        TFItems.ironwoodRaw = (new ItemTF()).func_77655_b("ironwoodRaw");
        TFItems.ironwoodIngot = (new ItemTF()).func_77655_b("ironwoodIngot");
        TFItems.ironwoodHelm = (new ItemTFIronwoodArmor(TFItems.ARMOR_IRONWOOD, ironwoodRenderID, 0)).func_77655_b("ironwoodHelm").func_77625_d(1);
        TFItems.ironwoodPlate = (new ItemTFIronwoodArmor(TFItems.ARMOR_IRONWOOD, ironwoodRenderID, 1)).func_77655_b("ironwoodPlate").func_77625_d(1);
        TFItems.ironwoodLegs = (new ItemTFIronwoodArmor(TFItems.ARMOR_IRONWOOD, ironwoodRenderID, 2)).func_77655_b("ironwoodLegs").func_77625_d(1);
        TFItems.ironwoodBoots = (new ItemTFIronwoodArmor(TFItems.ARMOR_IRONWOOD, ironwoodRenderID, 3)).func_77655_b("ironwoodBoots").func_77625_d(1);
        TFItems.ironwoodSword = (new ItemTFIronwoodSword(TFItems.TOOL_IRONWOOD)).func_77655_b("ironwoodSword").func_77625_d(1);
        TFItems.ironwoodShovel = (new ItemTFIronwoodShovel(TFItems.TOOL_IRONWOOD)).func_77655_b("ironwoodShovel").func_77625_d(1);
        TFItems.ironwoodPick = (new ItemTFIronwoodPick(TFItems.TOOL_IRONWOOD)).func_77655_b("ironwoodPick").func_77625_d(1);
        TFItems.ironwoodAxe = (new ItemTFIronwoodAxe(TFItems.TOOL_IRONWOOD)).func_77655_b("ironwoodAxe").func_77625_d(1);
        TFItems.ironwoodHoe = (new ItemTFIronwoodHoe(TFItems.TOOL_IRONWOOD)).func_77655_b("ironwoodHoe").func_77625_d(1);
        TFItems.torchberries = (new ItemTF()).func_77655_b("torchberries");
        TFItems.spawnEgg = (new ItemTFSpawnEgg()).func_77655_b("tfspawnegg");
        TFItems.venisonRaw = (new ItemTFFood(3, 0.3F, true)).func_77655_b("venisonRaw");
        TFItems.venisonCooked = (new ItemTFFood(8, 0.8F, true)).func_77655_b("venisonCooked");
        TFItems.hydraChop = (new ItemTFHydraChops(18, 2.0F, true)).func_77844_a(Potion.field_76428_l.field_76415_H, 5, 0, 1.0F).func_77655_b("hydraChop");
        TFItems.fieryBlood = (new ItemTF()).makeRare().func_77655_b("fieryBlood");
        TFItems.trophy = (new ItemTFTrophy()).func_77655_b("trophy");
        TFItems.fieryIngot = (new ItemTF()).makeRare().func_77655_b("fieryIngot");
        TFItems.fieryHelm = (new ItemTFFieryArmor(TFItems.ARMOR_FIERY, fieryRenderID, 0)).func_77655_b("fieryHelm").func_77625_d(1);
        TFItems.fieryPlate = (new ItemTFFieryArmor(TFItems.ARMOR_FIERY, fieryRenderID, 1)).func_77655_b("fieryPlate").func_77625_d(1);
        TFItems.fieryLegs = (new ItemTFFieryArmor(TFItems.ARMOR_FIERY, fieryRenderID, 2)).func_77655_b("fieryLegs").func_77625_d(1);
        TFItems.fieryBoots = (new ItemTFFieryArmor(TFItems.ARMOR_FIERY, fieryRenderID, 3)).func_77655_b("fieryBoots").func_77625_d(1);
        TFItems.fierySword = (new ItemTFFierySword(TFItems.TOOL_FIERY)).func_77655_b("fierySword").func_77625_d(1);
        TFItems.fieryPick = (new ItemTFFieryPick(TFItems.TOOL_FIERY)).func_77655_b("fieryPick").func_77625_d(1);
        TFItems.steeleafIngot = (new ItemTF()).func_77655_b("steeleafIngot");
        TFItems.steeleafHelm = (new ItemTFSteeleafArmor(TFItems.ARMOR_IRONWOOD, steeleafRenderID, 0)).func_77655_b("steeleafHelm").func_77625_d(1);
        TFItems.steeleafPlate = (new ItemTFSteeleafArmor(TFItems.ARMOR_IRONWOOD, steeleafRenderID, 1)).func_77655_b("steeleafPlate").func_77625_d(1);
        TFItems.steeleafLegs = (new ItemTFSteeleafArmor(TFItems.ARMOR_IRONWOOD, steeleafRenderID, 2)).func_77655_b("steeleafLegs").func_77625_d(1);
        TFItems.steeleafBoots = (new ItemTFSteeleafArmor(TFItems.ARMOR_IRONWOOD, steeleafRenderID, 3)).func_77655_b("steeleafBoots").func_77625_d(1);
        TFItems.steeleafSword = (new ItemTFSteeleafSword(TFItems.TOOL_STEELEAF)).func_77655_b("steeleafSword").func_77625_d(1);
        TFItems.steeleafShovel = (new ItemTFSteeleafShovel(TFItems.TOOL_STEELEAF)).func_77655_b("steeleafShovel").func_77625_d(1);
        TFItems.steeleafPick = (new ItemTFSteeleafPick(TFItems.TOOL_STEELEAF)).func_77655_b("steeleafPick").func_77625_d(1);
        TFItems.steeleafAxe = (new ItemTFSteeleafAxe(TFItems.TOOL_STEELEAF)).func_77655_b("steeleafAxe").func_77625_d(1);
        TFItems.steeleafHoe = (new ItemTFSteeleafHoe(TFItems.TOOL_STEELEAF)).func_77655_b("steeleafHoe").func_77625_d(1);
        TFItems.minotaurAxe = (new ItemTFMinotaurAxe(ToolMaterial.EMERALD)).func_77655_b("minotaurAxe").func_77625_d(1);
        TFItems.mazebreakerPick = (new ItemTFMazebreakerPick(ToolMaterial.EMERALD)).func_77655_b("mazebreakerPick").func_77625_d(1);
        TFItems.transformPowder = (new ItemTFTransformPowder()).makeRare().func_77655_b("transformPowder");
        TFItems.meefRaw = (new ItemTFFood(2, 0.3F, true)).func_77655_b("meefRaw");
        TFItems.meefSteak = (new ItemTFFood(6, 0.6F, true)).func_77655_b("meefSteak");
        TFItems.meefStroganoff = (new ItemTFFood(8)).func_77655_b("meefStroganoff");
        TFItems.mazeWafer = (new ItemTFFood(4, 0.6F, false)).func_77655_b("mazeWafer");
        TFItems.emptyMagicMap = (new ItemTFEmptyMagicMap()).func_77655_b("emptyMagicMap");
        TFItems.emptyMazeMap = (new ItemTFEmptyMazeMap(false)).func_77655_b("emptyMazeMap");
        TFItems.emptyOreMap = (new ItemTFEmptyMazeMap(true)).func_77655_b("emptyOreMap");
        TFItems.oreMagnet = (new ItemTFOreMagnet()).func_77655_b("oreMagnet");
        TFItems.crumbleHorn = (new ItemTFCrumbleHorn()).func_77655_b("crumbleHorn");
        TFItems.peacockFan = (new ItemTFPeacockFan()).func_77655_b("peacockFan");
        TFItems.moonwormQueen = (new ItemTFMoonwormQueen()).func_77655_b("moonwormQueen");
        TFItems.charmOfLife1 = (new ItemTFCharm()).func_77655_b("charmOfLife1");
        TFItems.charmOfLife2 = (new ItemTFCharm()).func_77655_b("charmOfLife2");
        TFItems.charmOfKeeping1 = (new ItemTFCharm()).func_77655_b("charmOfKeeping1");
        TFItems.charmOfKeeping2 = (new ItemTFCharm()).func_77655_b("charmOfKeeping2");
        TFItems.charmOfKeeping3 = (new ItemTFCharm()).func_77655_b("charmOfKeeping3");
        TFItems.towerKey = (new ItemTFTowerKey()).func_77655_b("towerKey");
        TFItems.borerEssence = (new ItemTF()).func_77655_b("borerEssence");
        TFItems.carminite = (new ItemTF()).makeRare().func_77655_b("carminite");
        TFItems.experiment115 = (new ItemTFFood(4, 0.3F, false)).func_77655_b("experiment115");
        TFItems.armorShard = (new ItemTF()).func_77655_b("armorShards");
        TFItems.knightMetal = (new ItemTF()).func_77655_b("knightMetal");
        TFItems.shardCluster = (new ItemTF()).func_77655_b("shardCluster");
        TFItems.knightlyHelm = (new ItemTFKnightlyArmor(TFItems.ARMOR_KNIGHTLY, knightlyRenderID, 0)).func_77655_b("knightlyHelm").func_77625_d(1);
        TFItems.knightlyPlate = (new ItemTFKnightlyArmor(TFItems.ARMOR_KNIGHTLY, knightlyRenderID, 1)).func_77655_b("knightlyPlate").func_77625_d(1);
        TFItems.knightlyLegs = (new ItemTFKnightlyArmor(TFItems.ARMOR_KNIGHTLY, knightlyRenderID, 2)).func_77655_b("knightlyLegs").func_77625_d(1);
        TFItems.knightlyBoots = (new ItemTFKnightlyArmor(TFItems.ARMOR_KNIGHTLY, knightlyRenderID, 3)).func_77655_b("knightlyBoots").func_77625_d(1);
        TFItems.knightlySword = (new ItemTFKnightlySword(TFItems.TOOL_KNIGHTLY)).func_77655_b("knightlySword").func_77625_d(1);
        TFItems.knightlyPick = (new ItemTFKnightlyPick(TFItems.TOOL_KNIGHTLY)).func_77655_b("knightlyPick").func_77625_d(1);
        TFItems.knightlyAxe = (new ItemTFKnightlyAxe(TFItems.TOOL_KNIGHTLY)).func_77655_b("knightlyAxe").func_77625_d(1);
        TFItems.phantomHelm = (new ItemTFPhantomArmor(TFItems.ARMOR_PHANTOM, phantomRenderID, 0)).func_77655_b("phantomHelm").func_77625_d(1);
        TFItems.phantomPlate = (new ItemTFPhantomArmor(TFItems.ARMOR_PHANTOM, phantomRenderID, 1)).func_77655_b("phantomPlate").func_77625_d(1);
        TFItems.lampOfCinders = (new ItemTFLampOfCinders()).func_77655_b("lampOfCinders");
        TFItems.fieryTears = (new ItemTF()).makeRare().func_77655_b("fieryTears");
        TFItems.alphaFur = (new ItemTF()).makeRare().func_77655_b("alphaFur");
        TFItems.yetiHelm = (new ItemTFYetiArmor(TFItems.ARMOR_YETI, yetiRenderID, 0)).func_77655_b("yetiHelm").func_77625_d(1);
        TFItems.yetiPlate = (new ItemTFYetiArmor(TFItems.ARMOR_YETI, yetiRenderID, 1)).func_77655_b("yetiPlate").func_77625_d(1);
        TFItems.yetiLegs = (new ItemTFYetiArmor(TFItems.ARMOR_YETI, yetiRenderID, 2)).func_77655_b("yetiLegs").func_77625_d(1);
        TFItems.yetiBoots = (new ItemTFYetiArmor(TFItems.ARMOR_YETI, yetiRenderID, 3)).func_77655_b("yetiBoots").func_77625_d(1);
        TFItems.iceBomb = (new ItemTFIceBomb()).makeRare().func_77655_b("iceBomb").func_77625_d(16);
        TFItems.arcticFur = (new ItemTF()).func_77655_b("arcticFur");
        TFItems.arcticHelm = (new ItemTFArcticArmor(TFItems.ARMOR_ARCTIC, arcticRenderID, 0)).func_77655_b("arcticHelm").func_77625_d(1);
        TFItems.arcticPlate = (new ItemTFArcticArmor(TFItems.ARMOR_ARCTIC, arcticRenderID, 1)).func_77655_b("arcticPlate").func_77625_d(1);
        TFItems.arcticLegs = (new ItemTFArcticArmor(TFItems.ARMOR_ARCTIC, arcticRenderID, 2)).func_77655_b("arcticLegs").func_77625_d(1);
        TFItems.arcticBoots = (new ItemTFArcticArmor(TFItems.ARMOR_ARCTIC, arcticRenderID, 3)).func_77655_b("arcticBoots").func_77625_d(1);
        TFItems.magicBeans = (new ItemTFMagicBeans()).func_77655_b("magicBeans");
        TFItems.giantPick = (new ItemTFGiantPick(TFItems.TOOL_GIANT)).func_77655_b("giantPick").func_77625_d(1);
        TFItems.giantSword = (new ItemTFGiantSword(TFItems.TOOL_GIANT)).func_77655_b("giantSword").func_77625_d(1);
        TFItems.tripleBow = (new ItemTFTripleBow()).func_77655_b("tripleBow").func_77625_d(1);
        TFItems.seekerBow = (new ItemTFSeekerBow()).func_77655_b("seekerBow").func_77625_d(1);
        TFItems.iceBow = (new ItemTFIceBow()).func_77655_b("iceBow").func_77625_d(1);
        TFItems.enderBow = (new ItemTFEnderBow()).func_77655_b("enderBow").func_77625_d(1);
        TFItems.iceSword = (new ItemTFIceSword(TFItems.TOOL_ICE)).func_77655_b("iceSword").func_77625_d(1);
        TFItems.glassSword = (new ItemTFGlassSword(TFItems.TOOL_GLASS)).func_77655_b("glassSword").func_77625_d(1);
        TFItems.knightmetalRing = (new ItemTF()).func_77655_b("knightmetalRing");
        TFItems.chainBlock = (new ItemTFChainBlock()).func_77655_b("chainBlock").func_77625_d(1);
        TFItems.cubeTalisman = (new ItemTF()).func_77655_b("cubeTalisman");
        TFItems.cubeOfAnnihilation = (new ItemTFCubeOfAnnihilation()).func_77655_b("cubeOfAnnihilation").func_77625_d(1);
        registerTFItem(TFItems.nagaScale, "Naga Scale");
        registerTFItem(TFItems.plateNaga, "Naga Scale Tunic");
        registerTFItem(TFItems.legsNaga, "Naga Scale Leggings");
        registerTFItem(TFItems.scepterTwilight, "Scepter of Twilight");
        registerTFItem(TFItems.scepterLifeDrain, "Scepter of Life Draining");
        registerTFItem(TFItems.scepterZombie, "Zombie Scepter");
        registerTFItem(TFItems.oreMeter, "Ore Meter [WIP]");
        registerTFItem(TFItems.magicMap, "Magic Map");
        registerTFItem(TFItems.mazeMap, "Maze Map");
        registerTFItem(TFItems.oreMap, "Maze/Ore Map");
        registerTFItem(TFItems.feather, "Raven\'s Feather");
        registerTFItem(TFItems.magicMapFocus, "Magic Map Focus");
        registerTFItem(TFItems.mazeMapFocus, "Maze Map Focus");
        registerTFItem(TFItems.liveRoot, "Liveroot");
        registerTFItem(TFItems.ironwoodRaw, "Raw Ironwood Materials");
        registerTFItem(TFItems.ironwoodIngot, "Ironwood Ingot");
        registerTFItem(TFItems.ironwoodHelm, "Ironwood Helm");
        registerTFItem(TFItems.ironwoodPlate, "Ironwood Plate");
        registerTFItem(TFItems.ironwoodLegs, "Ironwood Legs");
        registerTFItem(TFItems.ironwoodBoots, "Ironwood Boots");
        registerTFItem(TFItems.ironwoodSword, "Ironwood Sword");
        registerTFItem(TFItems.ironwoodShovel, "Ironwood Shovel");
        registerTFItem(TFItems.ironwoodPick, "Ironwood Pick");
        registerTFItem(TFItems.ironwoodAxe, "Ironwood Axe");
        registerTFItem(TFItems.ironwoodHoe, "Ironwood Hoe");
        registerTFItem(TFItems.torchberries, "Torchberries");
        registerTFItem(TFItems.venisonRaw, "Raw Venison");
        registerTFItem(TFItems.venisonCooked, "Venison Steak");
        registerTFItem(TFItems.hydraChop, "Hydra Chop");
        registerTFItem(TFItems.fieryBlood, "Fiery Blood");
        registerTFItem(TFItems.trophy, "Hydra Trophy");
        registerTFItem(TFItems.fieryIngot, "Fiery Ingot");
        registerTFItem(TFItems.fieryHelm, "Fiery Helm");
        registerTFItem(TFItems.fieryPlate, "Fiery Plate");
        registerTFItem(TFItems.fieryLegs, "Fiery Legs");
        registerTFItem(TFItems.fieryBoots, "Fiery Boots");
        registerTFItem(TFItems.fierySword, "Fiery Sword");
        registerTFItem(TFItems.fieryPick, "Fiery Pick");
        registerTFItem(TFItems.steeleafIngot, "Steeleaf");
        registerTFItem(TFItems.steeleafHelm, "Steeleaf Helm");
        registerTFItem(TFItems.steeleafPlate, "Steeleaf Plate");
        registerTFItem(TFItems.steeleafLegs, "Steeleaf Legs");
        registerTFItem(TFItems.steeleafBoots, "Steeleaf Boots");
        registerTFItem(TFItems.steeleafSword, "Steeleaf Sword");
        registerTFItem(TFItems.steeleafShovel, "Steeleaf Shovel");
        registerTFItem(TFItems.steeleafPick, "Steeleaf Pick");
        registerTFItem(TFItems.steeleafAxe, "Steeleaf Axe");
        registerTFItem(TFItems.steeleafHoe, "Steeleaf Hoe");
        registerTFItem(TFItems.minotaurAxe, "Minotaur Axe");
        registerTFItem(TFItems.mazebreakerPick, "Mazebreaker");
        registerTFItem(TFItems.transformPowder, "Transformation Powder");
        registerTFItem(TFItems.meefRaw, "Raw Meef");
        registerTFItem(TFItems.meefSteak, "Meef Steak");
        registerTFItem(TFItems.meefStroganoff, "Meef Stroganoff");
        registerTFItem(TFItems.mazeWafer, "Maze Wafer");
        registerTFItem(TFItems.emptyMagicMap, "Blank Magic Map");
        registerTFItem(TFItems.emptyMazeMap, "Blank Maze Map");
        registerTFItem(TFItems.emptyOreMap, "Blank Maze/Ore Map");
        registerTFItem(TFItems.oreMagnet, "Ore Magnet");
        registerTFItem(TFItems.crumbleHorn, "Crumble Horn");
        registerTFItem(TFItems.peacockFan, "Peacock Feather Fan");
        registerTFItem(TFItems.moonwormQueen, "Moonworm Queen");
        registerTFItem(TFItems.charmOfLife1, "Charm of Life I");
        registerTFItem(TFItems.charmOfLife2, "Charm of Life II");
        registerTFItem(TFItems.charmOfKeeping1, "Charm of Keeping I");
        registerTFItem(TFItems.charmOfKeeping2, "Charm of Keeping II");
        registerTFItem(TFItems.charmOfKeeping3, "Charm of Keeping III");
        registerTFItem(TFItems.towerKey, "Tower Key");
        registerTFItem(TFItems.borerEssence, "Borer Essence");
        registerTFItem(TFItems.carminite, "Carminite");
        registerTFItem(TFItems.experiment115, "Experiment 115");
        registerTFItem(TFItems.armorShard, "Armor Shards");
        registerTFItem(TFItems.knightMetal, "Knightmetal Ingot");
        registerTFItem(TFItems.shardCluster, "Shard Cluster");
        registerTFItem(TFItems.knightlyHelm, "Knightly Helm");
        registerTFItem(TFItems.knightlyPlate, "Knightly Plate");
        registerTFItem(TFItems.knightlyLegs, "Knightly Legs");
        registerTFItem(TFItems.knightlyBoots, "Knightly Boots");
        registerTFItem(TFItems.knightlySword, "Knightly Sword");
        registerTFItem(TFItems.knightlyPick, "Knightly Pick");
        registerTFItem(TFItems.knightlyAxe, "Knightly Axe");
        registerTFItem(TFItems.phantomHelm, "Phantom Helm");
        registerTFItem(TFItems.phantomPlate, "Phantom Plate");
        registerTFItem(TFItems.lampOfCinders);
        registerTFItem(TFItems.fieryTears);
        registerTFItem(TFItems.alphaFur);
        registerTFItem(TFItems.yetiHelm);
        registerTFItem(TFItems.yetiPlate);
        registerTFItem(TFItems.yetiLegs);
        registerTFItem(TFItems.yetiBoots);
        registerTFItem(TFItems.iceBomb);
        registerTFItem(TFItems.arcticFur);
        registerTFItem(TFItems.arcticHelm);
        registerTFItem(TFItems.arcticPlate);
        registerTFItem(TFItems.arcticLegs);
        registerTFItem(TFItems.arcticBoots);
        registerTFItem(TFItems.magicBeans);
        registerTFItem(TFItems.giantPick);
        registerTFItem(TFItems.giantSword);
        registerTFItem(TFItems.tripleBow);
        registerTFItem(TFItems.seekerBow);
        registerTFItem(TFItems.iceBow);
        registerTFItem(TFItems.enderBow);
        registerTFItem(TFItems.iceSword);
        registerTFItem(TFItems.glassSword);
        registerTFItem(TFItems.knightmetalRing);
        registerTFItem(TFItems.chainBlock);
        registerTFItem(TFItems.cubeTalisman);
        registerTFItem(TFItems.cubeOfAnnihilation);
        registerTFItem(TFItems.spawnEgg, "Spawn");
    }

    private static void registerTFItem(Item item, String englishName) {
        GameRegistry.registerItem(item, item.func_77658_a(), "TwilightForest");
    }

    private static void registerTFItem(Item item) {
        GameRegistry.registerItem(item, item.func_77658_a(), "TwilightForest");
    }
}
