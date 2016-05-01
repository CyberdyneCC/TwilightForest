package twilightforest.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import twilightforest.block.TFBlocks;

public class TFRecipes {

    public static void registerRecipes() {
        OreDictionary.registerOre("logWood", new ItemStack(TFBlocks.log, 1, 32767));
        OreDictionary.registerOre("logWood", new ItemStack(TFBlocks.magicLog, 1, 32767));
        OreDictionary.registerOre("treeSapling", new ItemStack(TFBlocks.sapling, 1, 32767));
        OreDictionary.registerOre("treeLeaves", new ItemStack(TFBlocks.leaves, 1, 32767));
        OreDictionary.registerOre("treeLeaves", new ItemStack(TFBlocks.magicLeaves, 1, 32767));
        OreDictionary.registerOre("plankWood", new ItemStack(TFBlocks.towerWood, 1, 32767));
        OreDictionary.registerOre("fieryIngot", new ItemStack(TFItems.fieryIngot));
        OreDictionary.registerOre("ironwood", new ItemStack(TFItems.ironwoodIngot));
        OreDictionary.registerOre("steeleaf", new ItemStack(TFItems.steeleafIngot));
        OreDictionary.registerOre("knightmetal", new ItemStack(TFItems.knightMetal));
        RecipeSorter.register("TwilightForest:mapcloning", TFMapCloningRecipe.class, Category.SHAPELESS, "after:minecraft:shapeless");
        GameRegistry.addRecipe(new ItemStack(Blocks.field_150344_f, 4, 0), new Object[] { "w", Character.valueOf('w'), new ItemStack(TFBlocks.log, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(Blocks.field_150344_f, 4, 1), new Object[] { "w", Character.valueOf('w'), new ItemStack(TFBlocks.log, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(Blocks.field_150344_f, 4, 2), new Object[] { "w", Character.valueOf('w'), new ItemStack(TFBlocks.log, 1, 2)});
        GameRegistry.addRecipe(new ItemStack(Blocks.field_150344_f, 4, 1), new Object[] { "w", Character.valueOf('w'), new ItemStack(TFBlocks.log, 1, 3)});
        GameRegistry.addSmelting(TFBlocks.log, new ItemStack(Items.field_151044_h, 1, 1), 0.1F);
        addEnchantedRecipe(TFItems.plateNaga, Enchantment.field_77329_d, 3, new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.nagaScale});
        addEnchantedRecipe(TFItems.legsNaga, Enchantment.field_77332_c, 3, new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.nagaScale});
        GameRegistry.addShapelessRecipe(new ItemStack(TFBlocks.fireflyJar, 1, 0), new Object[] { TFBlocks.firefly, Items.field_151069_bo});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterTwilight), new Object[] { new ItemStack(TFItems.scepterTwilight, 1, TFItems.scepterTwilight.func_77612_l()), Items.field_151079_bi});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterLifeDrain), new Object[] { new ItemStack(TFItems.scepterLifeDrain, 1, TFItems.scepterLifeDrain.func_77612_l()), Items.field_151071_bq});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 16281)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 16313)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 16345)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 16377)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 8201)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 8265)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.scepterZombie), new Object[] { new ItemStack(TFItems.scepterZombie, 1, TFItems.scepterZombie.func_77612_l()), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151068_bn, 1, 8233)});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.magicMapFocus), new Object[] { TFItems.feather, TFItems.torchberries, Items.field_151114_aO});
        GameRegistry.addRecipe(new ItemStack(TFItems.emptyMagicMap), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.field_151121_aF, Character.valueOf('X'), TFItems.magicMapFocus});
        GameRegistry.addRecipe(new ItemStack(TFItems.emptyMazeMap), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.field_151121_aF, Character.valueOf('X'), TFItems.mazeMapFocus});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.emptyOreMap), new Object[] { new ItemStack(TFItems.mazeMap, 1, 32767), Blocks.field_150340_R, Blocks.field_150484_ah, Blocks.field_150339_S});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.emptyOreMap), new Object[] { new ItemStack(TFItems.emptyMazeMap, 1, 32767), Blocks.field_150340_R, Blocks.field_150484_ah, Blocks.field_150339_S});
        GameRegistry.addRecipe(new ItemStack(Items.field_151032_g, 4), new Object[] { "X", "#", "Y", Character.valueOf('Y'), TFItems.feather, Character.valueOf('X'), Items.field_151145_ak, Character.valueOf('#'), Items.field_151055_y});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.field_151055_y), new Object[] { new ItemStack(TFBlocks.plant, 1, 14)});
        GameRegistry.addRecipe(new ItemStack(Blocks.field_150478_aa, 5), new Object[] { "B", "S", Character.valueOf('B'), TFItems.torchberries, Character.valueOf('S'), Items.field_151055_y});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.ironwoodRaw), new Object[] { TFItems.liveRoot, Items.field_151042_j, Items.field_151074_bl});
        GameRegistry.addSmelting(TFItems.ironwoodRaw, new ItemStack(TFItems.ironwoodIngot, 2), 1.0F);
        addEnchantedRecipe(TFItems.ironwoodHelm, Enchantment.field_77341_i, 1, new Object[] { "###", "# #", Character.valueOf('#'), TFItems.ironwoodIngot});
        addEnchantedRecipe(TFItems.ironwoodPlate, Enchantment.field_77332_c, 1, new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.ironwoodIngot});
        addEnchantedRecipe(TFItems.ironwoodLegs, Enchantment.field_77332_c, 1, new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.ironwoodIngot});
        addEnchantedRecipe(TFItems.ironwoodBoots, Enchantment.field_77330_e, 1, new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.ironwoodIngot});
        addEnchantedRecipe(TFItems.ironwoodSword, Enchantment.field_77337_m, 1, new Object[] { "#", "#", "X", Character.valueOf('#'), TFItems.ironwoodIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.ironwoodShovel, Enchantment.field_77347_r, 1, new Object[] { "#", "X", "X", Character.valueOf('#'), TFItems.ironwoodIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.ironwoodPick, Enchantment.field_77349_p, 1, new Object[] { "###", " X ", " X ", Character.valueOf('#'), TFItems.ironwoodIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.ironwoodAxe, Enchantment.field_77346_s, 1, new Object[] { "##", "#X", " X", Character.valueOf('#'), TFItems.ironwoodIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.ironwoodHoe, (Enchantment) null, 0, new Object[] { "##", " X", " X", Character.valueOf('#'), TFItems.ironwoodIngot, Character.valueOf('X'), Items.field_151055_y});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.uncraftingTable), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Blocks.field_150462_ai, Character.valueOf('X'), TFItems.mazeMapFocus});
        GameRegistry.addSmelting(TFItems.venisonRaw, new ItemStack(TFItems.venisonCooked), 0.3F);
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.fieryIngot), new Object[] { TFItems.fieryBlood, Items.field_151042_j});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.fieryIngot), new Object[] { TFItems.fieryTears, Items.field_151042_j});
        GameRegistry.addRecipe(new ItemStack(TFItems.fieryHelm), new Object[] { "###", "# #", Character.valueOf('#'), TFItems.fieryIngot});
        GameRegistry.addRecipe(new ItemStack(TFItems.fieryPlate), new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.fieryIngot});
        GameRegistry.addRecipe(new ItemStack(TFItems.fieryLegs), new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.fieryIngot});
        GameRegistry.addRecipe(new ItemStack(TFItems.fieryBoots), new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.fieryIngot});
        addEnchantedRecipe(TFItems.fierySword, Enchantment.field_77334_n, 2, new Object[] { "#", "#", "X", Character.valueOf('#'), TFItems.fieryIngot, Character.valueOf('X'), Items.field_151072_bj});
        GameRegistry.addRecipe(new ItemStack(TFItems.fieryPick), new Object[] { "###", " X ", " X ", Character.valueOf('#'), TFItems.fieryIngot, Character.valueOf('X'), Items.field_151072_bj});
        addEnchantedRecipe(TFItems.steeleafHelm, Enchantment.field_77328_g, 2, new Object[] { "###", "# #", Character.valueOf('#'), TFItems.steeleafIngot});
        addEnchantedRecipe(TFItems.steeleafPlate, Enchantment.field_77327_f, 2, new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.steeleafIngot});
        addEnchantedRecipe(TFItems.steeleafLegs, Enchantment.field_77329_d, 2, new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.steeleafIngot});
        addEnchantedRecipe(TFItems.steeleafBoots, Enchantment.field_77330_e, 2, new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.steeleafIngot});
        addEnchantedRecipe(TFItems.steeleafSword, Enchantment.field_77335_o, 2, new Object[] { "#", "#", "X", Character.valueOf('#'), TFItems.steeleafIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.steeleafShovel, Enchantment.field_77349_p, 2, new Object[] { "#", "X", "X", Character.valueOf('#'), TFItems.steeleafIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.steeleafPick, Enchantment.field_77346_s, 2, new Object[] { "###", " X ", " X ", Character.valueOf('#'), TFItems.steeleafIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.steeleafAxe, Enchantment.field_77349_p, 2, new Object[] { "##", "#X", " X", Character.valueOf('#'), TFItems.steeleafIngot, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.steeleafHoe, (Enchantment) null, 0, new Object[] { "##", " X", " X", Character.valueOf('#'), TFItems.steeleafIngot, Character.valueOf('X'), Items.field_151055_y});
        GameRegistry.addSmelting(TFItems.meefRaw, new ItemStack(TFItems.meefSteak), 0.3F);
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.moonwormQueen), new Object[] { new ItemStack(TFItems.moonwormQueen, 1, 32767), TFItems.torchberries, TFItems.torchberries, TFItems.torchberries});
        GameRegistry.addRecipe(new ItemStack(TFItems.emptyMagicMap), new Object[] { "###", "#X#", "###", Character.valueOf('#'), Items.field_151121_aF, Character.valueOf('X'), TFItems.magicMapFocus});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.charmOfKeeping2), new Object[] { TFItems.charmOfKeeping1, TFItems.charmOfKeeping1, TFItems.charmOfKeeping1, TFItems.charmOfKeeping1});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.charmOfKeeping3), new Object[] { TFItems.charmOfKeeping2, TFItems.charmOfKeeping2, TFItems.charmOfKeeping2, TFItems.charmOfKeeping2});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.charmOfLife2), new Object[] { TFItems.charmOfLife1, TFItems.charmOfLife1, TFItems.charmOfLife1, TFItems.charmOfLife1});
        GameRegistry.addRecipe(new TFMapCloningRecipe(TFItems.magicMap, TFItems.emptyMagicMap));
        GameRegistry.addRecipe(new TFMapCloningRecipe(TFItems.mazeMap, TFItems.emptyMazeMap));
        GameRegistry.addRecipe(new TFMapCloningRecipe(TFItems.oreMap, TFItems.emptyOreMap));
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerWood, 4, 0), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(TFBlocks.log, 1, 3)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerWood, 3, 1), new Object[] { "#", "#", "#", Character.valueOf('#'), new ItemStack(TFBlocks.towerWood, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(TFItems.carminite), new Object[] { "brb", "rgr", "brb", Character.valueOf('b'), new ItemStack(TFItems.borerEssence), Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('g'), new ItemStack(Items.field_151073_bk)});
        GameRegistry.addRecipe(new ItemStack(TFItems.carminite), new Object[] { "rbr", "bgb", "rbr", Character.valueOf('b'), new ItemStack(TFItems.borerEssence), Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('g'), new ItemStack(Items.field_151073_bk)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerDevice, 8, 2), new Object[] { "ewe", "wcw", "ewe", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('w'), new ItemStack(TFBlocks.towerWood, 1, 0), Character.valueOf('c'), new ItemStack(TFItems.carminite)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerDevice, 2, 0), new Object[] { "ere", "rcr", "ere", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('c'), new ItemStack(TFItems.carminite)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerDevice, 1, 6), new Object[] { "ece", "cdc", "ece", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('d'), new ItemStack(Blocks.field_150367_z), Character.valueOf('c'), new ItemStack(TFItems.carminite)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.towerDevice, 1, 12), new Object[] { "ece", "coc", "ece", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('o'), new ItemStack(Blocks.field_150450_ax), Character.valueOf('c'), new ItemStack(TFItems.carminite)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.fireJet, 1, 1), new Object[] { "ere", "rsr", "ere", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('s'), new ItemStack(TFBlocks.fireJet, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.fireJet, 1, 11), new Object[] { "ere", "rjr", "lll", Character.valueOf('e'), new ItemStack(TFBlocks.towerWood, 1, 1), Character.valueOf('r'), new ItemStack(Items.field_151137_ax), Character.valueOf('l'), new ItemStack(Items.field_151129_at), Character.valueOf('j'), new ItemStack(TFBlocks.fireJet, 1, 8)});
        GameRegistry.addRecipe(new ItemStack(TFItems.shardCluster), new Object[] { "###", "###", "###", Character.valueOf('#'), TFItems.armorShard});
        GameRegistry.addSmelting(TFItems.shardCluster, new ItemStack(TFItems.knightMetal), 1.0F);
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyHelm), new Object[] { "###", "# #", Character.valueOf('#'), TFItems.knightMetal});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyPlate), new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.knightMetal});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyLegs), new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.knightMetal});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyBoots), new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.knightMetal});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlySword), new Object[] { "#", "#", "X", Character.valueOf('#'), TFItems.knightMetal, Character.valueOf('X'), Items.field_151055_y});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyPick), new Object[] { "###", " X ", " X ", Character.valueOf('#'), TFItems.knightMetal, Character.valueOf('X'), Items.field_151055_y});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyAxe), new Object[] { "##", "#X", " X", Character.valueOf('#'), TFItems.knightMetal, Character.valueOf('X'), Items.field_151055_y});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightlyAxe), new Object[] { "##", "X#", "X ", Character.valueOf('#'), TFItems.knightMetal, Character.valueOf('X'), Items.field_151055_y});
        addEnchantedRecipe(TFItems.yetiHelm, Enchantment.field_77332_c, 2, new Object[] { "###", "# #", Character.valueOf('#'), TFItems.alphaFur});
        addEnchantedRecipe(TFItems.yetiPlate, Enchantment.field_77332_c, 2, new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.alphaFur});
        addEnchantedRecipe(TFItems.yetiLegs, Enchantment.field_77332_c, 2, new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.alphaFur});
        addEnchantedRecipe(TFItems.yetiBoots, Enchantment.field_77332_c, 2, Enchantment.field_77330_e, 4, new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.alphaFur});
        GameRegistry.addRecipe(new ItemStack(TFItems.arcticHelm), new Object[] { "###", "# #", Character.valueOf('#'), TFItems.arcticFur});
        GameRegistry.addRecipe(new ItemStack(TFItems.arcticPlate), new Object[] { "# #", "###", "###", Character.valueOf('#'), TFItems.arcticFur});
        GameRegistry.addRecipe(new ItemStack(TFItems.arcticLegs), new Object[] { "###", "# #", "# #", Character.valueOf('#'), TFItems.arcticFur});
        GameRegistry.addRecipe(new ItemStack(TFItems.arcticBoots), new Object[] { "# #", "# #", Character.valueOf('#'), TFItems.arcticFur});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.auroraSlab, 6, 0), new Object[] { "###", Character.valueOf('#'), TFBlocks.auroraBlock});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.auroraPillar, 2, 0), new Object[] { "#", "#", Character.valueOf('#'), TFBlocks.auroraBlock});
        GameRegistry.addRecipe(new ItemStack(TFItems.giantPick), new Object[] { "###", " X ", " X ", Character.valueOf('#'), TFBlocks.giantCobble, Character.valueOf('X'), TFBlocks.giantLog});
        GameRegistry.addRecipe(new ItemStack(TFItems.giantSword), new Object[] { "#", "#", "X", Character.valueOf('#'), TFBlocks.giantCobble, Character.valueOf('X'), TFBlocks.giantLog});
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150347_e, 64), new Object[] { new ItemStack(TFBlocks.giantCobble)});
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150344_f, 64), new Object[] { new ItemStack(TFBlocks.giantLog)});
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.field_150362_t, 64), new Object[] { new ItemStack(TFBlocks.giantLeaves)});
        GameRegistry.addRecipe(new ItemStack(TFItems.knightmetalRing), new Object[] { " # ", "# #", " # ", Character.valueOf('#'), TFItems.knightMetal});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.chainBlock), new Object[] { TFItems.knightmetalRing, TFItems.knightMetal, TFItems.knightMetal, TFItems.knightMetal, TFBlocks.knightmetalStorage});
        GameRegistry.addShapelessRecipe(new ItemStack(TFItems.knightMetal, 9), new Object[] { new ItemStack(TFBlocks.knightmetalStorage)});
        GameRegistry.addRecipe(new ItemStack(TFBlocks.knightmetalStorage), new Object[] { "###", "###", "###", Character.valueOf('#'), TFItems.knightMetal});
    }

    public static void addEnchantedRecipe(Item item, Enchantment enchantment, int enchantmentLevel, Object... ingredientArray) {
        ItemStack result = new ItemStack(item);

        if (enchantment != null) {
            result.func_77966_a(enchantment, enchantmentLevel);
        }

        GameRegistry.addRecipe(result, ingredientArray);
    }

    public static void addEnchantedRecipe(Item item, Enchantment enchantment, int enchantmentLevel, Enchantment enchantment2, int enchantmentLevel2, Object... ingredientArray) {
        ItemStack result = new ItemStack(item);

        if (enchantment != null) {
            result.func_77966_a(enchantment, enchantmentLevel);
        }

        if (enchantment2 != null) {
            result.func_77966_a(enchantment2, enchantmentLevel2);
        }

        GameRegistry.addRecipe(result, ingredientArray);
    }
}
