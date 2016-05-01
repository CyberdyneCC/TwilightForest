package twilightforest;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class TFTreasure {

    int type;
    Random treasureRNG;
    protected TFTreasureTable useless;
    protected TFTreasureTable common;
    protected TFTreasureTable uncommon;
    protected TFTreasureTable rare;
    protected TFTreasureTable ultrarare;
    public static TFTreasure hill1 = new TFTreasure(1);
    public static TFTreasure hill2 = new TFTreasure(2);
    public static TFTreasure hill3 = new TFTreasure(3);
    public static TFTreasure hedgemaze = new TFTreasure(4);
    public static TFTreasure labyrinth_room = new TFTreasure(5);
    public static TFTreasure labyrinth_deadend = new TFTreasure(6);
    public static TFTreasure tower_room = new TFTreasure(7);
    public static TFTreasure tower_library = new TFTreasure(8);
    public static TFTreasure basement = new TFTreasure(9);
    public static TFTreasure labyrinth_vault = new TFTreasure(10);
    public static TFTreasure darktower_cache = new TFTreasure(11);
    public static TFTreasure darktower_key = new TFTreasure(12);
    public static TFTreasure darktower_boss = new TFTreasure(13);
    public static TFTreasure tree_cache = new TFTreasure(14);
    public static TFTreasure stronghold_cache = new TFTreasure(15);
    public static TFTreasure stronghold_room = new TFTreasure(16);
    public static TFTreasure stronghold_boss = new TFTreasure(17);
    public static TFTreasure aurora_cache = new TFTreasure(18);
    public static TFTreasure aurora_room = new TFTreasure(19);
    public static TFTreasure aurora_boss = new TFTreasure(20);
    public static TFTreasure troll_garden = new TFTreasure(21);
    public static TFTreasure troll_vault = new TFTreasure(22);

    public TFTreasure(int i) {
        this.type = i;
        this.useless = new TFTreasureTable();
        this.common = new TFTreasureTable();
        this.uncommon = new TFTreasureTable();
        this.rare = new TFTreasureTable();
        this.ultrarare = new TFTreasureTable();
        this.treasureRNG = new Random();
        this.fill(i);
    }

    public boolean generate(World world, Random rand, int cx, int cy, int cz) {
        return this.generate(world, rand, cx, cy, cz, Blocks.field_150486_ae);
    }

    public boolean generate(World world, Random rand, int cx, int cy, int cz, Block chestBlock) {
        boolean flag = true;

        this.treasureRNG.setSeed(world.func_72905_C() * (long) cx + (long) cy ^ (long) cz);
        world.func_147465_d(cx, cy, cz, chestBlock, 0, 2);

        int i;

        for (i = 0; i < 4; ++i) {
            flag &= this.addItemToChest(world, this.treasureRNG, cx, cy, cz, this.getCommonItem(this.treasureRNG));
        }

        for (i = 0; i < 2; ++i) {
            flag &= this.addItemToChest(world, this.treasureRNG, cx, cy, cz, this.getUncommonItem(this.treasureRNG));
        }

        for (i = 0; i < 1; ++i) {
            flag &= this.addItemToChest(world, this.treasureRNG, cx, cy, cz, this.getRareItem(this.treasureRNG));
        }

        return flag;
    }

    public ItemStack getCommonItem(Random rand) {
        return !this.useless.isEmpty() && rand.nextInt(4) == 0 ? this.useless.getRandomItem(rand) : this.common.getRandomItem(rand);
    }

    public ItemStack getUncommonItem(Random rand) {
        return this.uncommon.getRandomItem(rand);
    }

    public ItemStack getRareItem(Random rand) {
        return !this.ultrarare.isEmpty() && rand.nextInt(4) == 0 ? this.ultrarare.getRandomItem(rand) : this.rare.getRandomItem(rand);
    }

    protected boolean addItemToChest(World world, Random rand, int cx, int cy, int cz, ItemStack itemStack) {
        TileEntityChest chest = (TileEntityChest) world.func_147438_o(cx, cy, cz);

        if (chest != null) {
            int slot = this.findRandomInventorySlot(chest, rand);

            if (slot != -1) {
                chest.func_70299_a(slot, itemStack);
                return true;
            }
        }

        return false;
    }

    protected int findRandomInventorySlot(TileEntityChest chest, Random rand) {
        for (int i = 0; i < 100; ++i) {
            int slot = rand.nextInt(chest.func_70302_i_());

            if (chest.func_70301_a(slot) == null) {
                return slot;
            }
        }

        return -1;
    }

    protected void fill(int i) {
        this.useless.add((Block) Blocks.field_150328_O, 4);
        this.useless.add((Block) Blocks.field_150327_N, 4);
        this.useless.add(Items.field_151008_G, 3);
        this.useless.add(Items.field_151014_N, 2);
        this.useless.add(Items.field_151145_ak, 2);
        this.useless.add(Blocks.field_150434_aF, 2);
        this.useless.add(Items.field_151120_aE, 4);
        this.useless.add((Block) Blocks.field_150354_m, 4);
        this.useless.add(Items.field_151162_bE, 1);
        this.useless.add(new ItemStack(Items.field_151100_aR, 1, 0));
        if (i == 1) {
            this.common.add(Items.field_151042_j, 4);
            this.common.add(Items.field_151015_O, 4);
            this.common.add(Items.field_151007_F, 4);
            this.common.add(Items.field_151133_ar, 1);
            this.uncommon.add(Items.field_151025_P, 1);
            this.uncommon.add(TFItems.oreMagnet, 1);
            this.uncommon.add(Items.field_151016_H, 4);
            this.uncommon.add(Items.field_151032_g, 12);
            this.uncommon.add(Blocks.field_150478_aa, 12);
            this.rare.add(Items.field_151043_k, 3);
            this.rare.add(Items.field_151035_b, 1);
            this.rare.add(TFItems.liveRoot, 3);
            this.ultrarare.add(TFItems.transformPowder, 12);
            this.ultrarare.add(Items.field_151045_i, 1);
            this.ultrarare.add(TFItems.steeleafIngot, 3);
        }

        if (i == 2) {
            this.common.add(Items.field_151042_j, 4);
            this.common.add(Items.field_151172_bF, 4);
            this.common.add(Blocks.field_150468_ap, 6);
            this.common.add(Items.field_151133_ar, 1);
            this.uncommon.add(Items.field_151168_bH, 2);
            this.uncommon.add(TFItems.oreMagnet, 1);
            this.uncommon.add(TFItems.ironwoodIngot, 4);
            this.uncommon.add(Items.field_151032_g, 12);
            this.uncommon.add(Blocks.field_150478_aa, 12);
            this.rare.add(TFItems.nagaScale, 1);
            this.rare.add(TFBlocks.uncraftingTable, 1);
            this.rare.add(TFItems.transformPowder, 12);
            this.ultrarare.add(TFItems.peacockFan, 1);
            this.ultrarare.add(Items.field_151166_bC, 6);
            this.ultrarare.add(Items.field_151045_i, 1);
            this.ultrarare.add(TFItems.charmOfLife1, 1);
        }

        if (i == 3) {
            this.common.add(Items.field_151074_bl, 9);
            this.common.add(Items.field_151174_bG, 4);
            this.common.add(Items.field_151115_aP, 4);
            this.common.add(TFItems.torchberries, 5);
            this.uncommon.add(Items.field_151158_bO, 1);
            this.uncommon.add(TFItems.oreMagnet, 1);
            this.uncommon.add(Items.field_151016_H, 4);
            this.uncommon.add(Items.field_151032_g, 12);
            this.uncommon.add(Blocks.field_150478_aa, 12);
            this.uncommon.add(TFItems.steeleafIngot, 4);
            this.rare.add(TFItems.nagaScale, 1);
            this.rare.addEnchanted(new ItemStack(TFItems.ironwoodPick, 1), Enchantment.field_77349_p, 1, Enchantment.field_77346_s, 1);
            this.rare.add(TFItems.transformPowder, 12);
            this.ultrarare.add(TFItems.moonwormQueen, 1);
            this.ultrarare.add(TFBlocks.sapling, 1, 4);
            this.ultrarare.add(Items.field_151045_i, 2);
            this.ultrarare.add(TFItems.charmOfLife1, 1);
            this.ultrarare.add(TFItems.charmOfKeeping1, 1);
        }

        if (i == 4) {
            this.common.add(Blocks.field_150344_f, 4);
            this.common.add((Block) Blocks.field_150338_P, 4);
            this.common.add((Block) Blocks.field_150337_Q, 4);
            this.common.add(Items.field_151015_O, 4);
            this.common.add(Items.field_151007_F, 4);
            this.common.add(Items.field_151055_y, 6);
            this.uncommon.add(Items.field_151127_ba, 4);
            this.uncommon.add(Items.field_151081_bc, 4);
            this.uncommon.add(Items.field_151080_bb, 4);
            this.uncommon.add(Items.field_151032_g, 12);
            this.uncommon.add(TFBlocks.firefly, 4);
            this.rare.add(Blocks.field_150321_G, 3);
            this.rare.add((Item) Items.field_151097_aZ, 1);
            this.rare.add(Items.field_151141_av, 1);
            this.rare.add((Item) Items.field_151031_f, 1);
            this.rare.add(Items.field_151034_e, 2);
            this.ultrarare.add(Items.field_151012_L, 1);
            this.ultrarare.add(Items.field_151045_i, 1);
            this.ultrarare.add(Items.field_151009_A, 1);
            this.ultrarare.add(Items.field_151153_ao, 1);
        }

        if (i == 5) {
            this.useless.clear();
            this.common.add(Items.field_151042_j, 4);
            this.common.add(TFItems.mazeWafer, 12);
            this.common.add(Items.field_151016_H, 4);
            this.common.add(TFItems.ironwoodIngot, 4);
            this.common.add(TFBlocks.firefly, 5);
            this.common.add(Items.field_151117_aB, 1);
            this.uncommon.add(TFItems.steeleafIngot, 6);
            this.uncommon.add(TFItems.steeleafLegs, 1);
            this.uncommon.add(TFItems.steeleafPlate, 1);
            this.uncommon.add(TFItems.steeleafHelm, 1);
            this.uncommon.add(TFItems.steeleafBoots, 1);
            this.uncommon.add(TFItems.steeleafPick, 1);
            this.uncommon.add(TFItems.ironwoodPlate, 1);
            this.uncommon.add(TFItems.ironwoodSword, 1);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.rare.add(TFItems.mazeMapFocus, 1);
            this.rare.add(Blocks.field_150335_W, 3);
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16373));
        }

        if (i == 6) {
            this.common.add(Items.field_151055_y, 12);
            this.common.add(Items.field_151044_h, 12);
            this.common.add(Items.field_151032_g, 12);
            this.common.add(TFItems.mazeWafer, 9);
            this.common.add(Items.field_151121_aF, 12);
            this.common.add(Items.field_151116_aA, 4);
            this.common.add(Items.field_151009_A, 1);
            this.uncommon.add(Items.field_151117_aB, 1);
            this.uncommon.add(Items.field_151121_aF, 5);
            this.uncommon.add(Items.field_151042_j, 6);
            this.uncommon.add(TFItems.ironwoodIngot, 8);
            this.uncommon.add(TFBlocks.firefly, 5);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.rare.add(TFItems.steeleafIngot, 8);
            this.rare.add(Items.field_151153_ao, 1);
            this.rare.add(Items.field_151072_bj, 2);
        }

        if (i == 10) {
            this.useless.clear();
            this.common.add(Items.field_151042_j, 9);
            this.common.add(Items.field_151166_bC, 5);
            this.common.add(TFItems.mazeWafer, 12);
            this.common.add(TFItems.ironwoodIngot, 9);
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 16369));
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 16373));
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 16370));
            this.uncommon.addEnchanted(new ItemStack(Items.field_151031_f), Enchantment.field_77342_w, 1, Enchantment.field_77344_u, 2);
            this.uncommon.addEnchanted(new ItemStack(Items.field_151031_f), Enchantment.field_77345_t, 3, Enchantment.field_77343_v, 1);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafShovel), Enchantment.field_77349_p, 4, Enchantment.field_77347_r, 2);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafAxe), Enchantment.field_77349_p, 5);
            this.uncommon.add(TFItems.steeleafIngot, 12);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafPlate), Enchantment.field_77332_c, 3);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafLegs), Enchantment.field_77329_d, 4);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafBoots), Enchantment.field_77332_c, 2);
            this.uncommon.addEnchanted(new ItemStack(TFItems.steeleafHelm), Enchantment.field_77340_h, 3);
            this.rare.add(Blocks.field_150475_bE, 1);
            this.rare.add(Blocks.field_150477_bB, 1);
            this.rare.addEnchanted(new ItemStack(TFItems.steeleafPick), Enchantment.field_77349_p, 4, Enchantment.field_77348_q, 1);
            this.rare.addEnchanted(new ItemStack(TFItems.steeleafSword), Enchantment.field_77338_j, 4, Enchantment.field_77337_m, 2);
            this.rare.addEnchanted(new ItemStack(TFItems.steeleafSword), Enchantment.field_77336_l, 5, Enchantment.field_77334_n, 2);
            this.rare.addEnchanted(new ItemStack(TFItems.mazebreakerPick), Enchantment.field_77349_p, 4, Enchantment.field_77347_r, 3, Enchantment.field_77346_s, 2);
        }

        if (i == 7) {
            this.common.add(Items.field_151069_bo, 6);
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 0));
            this.common.add(Items.field_151102_aT, 5);
            this.common.add(Items.field_151070_bp, 3);
            this.common.add(Items.field_151073_bk, 1);
            this.common.add(Items.field_151064_bs, 2);
            this.common.add(Items.field_151071_bq, 1);
            this.common.add(Items.field_151060_bw, 2);
            this.common.add(Items.field_151065_br, 3);
            this.common.add(Items.field_151121_aF, 6);
            this.uncommon.addRandomEnchanted(Items.field_151010_B, 10);
            this.uncommon.addRandomEnchanted((Item) Items.field_151151_aj, 7);
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16274));
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16341));
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16307));
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16348));
            this.rare.addRandomEnchanted((Item) Items.field_151169_ag, 18);
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16306));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16305));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 32725));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 32764));
            this.rare.add(TFItems.transformPowder, 12);
            this.rare.add(TFItems.charmOfLife1, 1);
            this.rare.add(TFItems.charmOfKeeping1, 1);
            this.ultrarare.addRandomEnchanted(Items.field_151006_E, 20);
            this.ultrarare.add(Items.field_151079_bi, 1);
            this.ultrarare.add(Blocks.field_150343_Z, 4);
            this.ultrarare.add(Items.field_151045_i, 1);
            this.ultrarare.add(TFItems.moonwormQueen, 1);
            this.ultrarare.add(TFItems.peacockFan, 1);
        }

        if (i == 8) {
            this.common.add(Items.field_151069_bo, 6);
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 0));
            this.common.add(Blocks.field_150468_ap, 6);
            this.common.add(Items.field_151121_aF, 6);
            this.common.add(Items.field_151103_aS, 6);
            this.common.add(Items.field_151074_bl, 6);
            this.common.add(Items.field_151119_aD, 12);
            this.uncommon.addRandomEnchanted((Item) Items.field_151165_aa, 5);
            this.uncommon.add(Items.field_151059_bz, 3);
            this.uncommon.add(Items.field_151122_aG, 5);
            this.uncommon.add((Item) Items.field_151148_bJ, 1);
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16));
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16276));
            this.uncommon.add(new ItemStack(Items.field_151068_bn, 1, 16312));
            this.rare.addRandomEnchanted((Item) Items.field_151031_f, 5);
            this.rare.addRandomEnchanted(Items.field_151052_q, 10);
            this.rare.addRandomEnchanted(Items.field_151041_m, 15);
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 32696));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16369));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16373));
            this.rare.add(new ItemStack(Items.field_151068_bn, 1, 16370));
            this.rare.add(TFItems.transformPowder, 12);
            this.rare.add(TFItems.charmOfKeeping1, 1);
            this.ultrarare.addRandomEnchanted(Items.field_151005_D, 10);
            this.ultrarare.addRandomEnchanted(Items.field_151040_l, 20);
            this.ultrarare.addRandomEnchanted((Item) Items.field_151031_f, 30);
            this.ultrarare.add(Blocks.field_150342_X, 5);
            this.ultrarare.add(Items.field_151079_bi, 2);
            this.ultrarare.add(Items.field_151062_by, 6);
        }

        if (i == 9) {
            this.common.add(new ItemStack(Items.field_151068_bn, 1, 0));
            this.common.add(Items.field_151078_bh, 6);
            this.common.add(Items.field_151170_bI, 2);
            this.common.add(Items.field_151015_O, 6);
            this.common.add(Items.field_151174_bG, 6);
            this.common.add(Items.field_151172_bF, 6);
            this.common.add(Items.field_151127_ba, 6);
            this.common.add(Items.field_151131_as, 1);
            this.common.add(Blocks.field_150478_aa, 12);
            this.common.add(Items.field_151009_A, 1);
            this.common.add(Items.field_151117_aB, 1);
            this.common.add(Items.field_151081_bc, 5);
            this.uncommon.add(Items.field_151025_P, 8);
            this.uncommon.add(Items.field_151083_be, 6);
            this.uncommon.add(Items.field_151157_am, 8);
            this.uncommon.add(Items.field_151168_bH, 8);
            this.uncommon.add(Items.field_151077_bg, 10);
            this.uncommon.add(Items.field_151101_aQ, 8);
            this.rare.add(Items.field_151060_bw, 12);
            this.rare.add(Items.field_151034_e, 12);
            this.rare.add((Item) Items.field_151148_bJ, 1);
            this.rare.add(TFItems.charmOfKeeping1, 1);
            this.ultrarare.add(Items.field_151153_ao, 2);
            this.ultrarare.add(Items.field_151150_bK, 2);
            this.ultrarare.add(Items.field_151105_aU, 1);
            this.ultrarare.add(Items.field_151124_az, 1);
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 4));
        }

        if (i == 11) {
            this.common.add(Items.field_151055_y, 12);
            this.common.add(new ItemStack(Items.field_151044_h, 12, 1));
            this.common.add(Items.field_151032_g, 12);
            this.common.add(TFItems.experiment115, 9);
            this.common.add(new ItemStack(Blocks.field_150325_L, 1, 14));
            this.common.add(Items.field_151137_ax, 6);
            this.uncommon.add(Blocks.field_150379_bu, 3);
            this.uncommon.add(Items.field_151042_j, 6);
            this.uncommon.add(TFItems.ironwoodIngot, 8);
            this.uncommon.add(TFBlocks.firefly, 5);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.rare.add(TFItems.steeleafIngot, 8);
            this.rare.add(Items.field_151045_i, 2);
        }

        if (i == 12) {
            this.useless.clear();
            this.common.add(Items.field_151042_j, 4);
            this.common.add(TFItems.experiment115, 12);
            this.common.add(Items.field_151016_H, 4);
            this.common.add(TFItems.ironwoodIngot, 4);
            this.common.add(TFBlocks.firefly, 5);
            this.common.add(Items.field_151137_ax, 12);
            this.common.add(Items.field_151114_aO, 12);
            this.uncommon.add(TFItems.steeleafIngot, 6);
            this.uncommon.add(TFItems.steeleafLegs, 1);
            this.uncommon.add(TFItems.steeleafPlate, 1);
            this.uncommon.add(TFItems.steeleafHelm, 1);
            this.uncommon.add(TFItems.steeleafBoots, 1);
            this.uncommon.add(TFItems.steeleafPick, 1);
            this.uncommon.add(TFItems.ironwoodPlate, 1);
            this.uncommon.add(TFItems.ironwoodSword, 1);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.rare.add(TFItems.charmOfLife1, 1);
            this.rare.addEnchantedBook(Enchantment.field_77330_e, 3);
            this.rare.addEnchantedBook(Enchantment.field_77337_m, 2);
            this.rare.addEnchantedBook(Enchantment.field_77349_p, 3);
        }

        if (i == 13) {
            this.useless.clear();
            this.common.add(TFItems.carminite, 3);
            this.uncommon.add(TFItems.fieryTears, 5);
            this.rare.add(new ItemStack(TFItems.trophy, 1, 3));
        }

        if (i == 14) {
            this.common.add(Items.field_151170_bI, 2);
            this.common.add(Items.field_151015_O, 6);
            this.common.add(Items.field_151174_bG, 6);
            this.common.add(Items.field_151172_bF, 6);
            this.common.add(Items.field_151127_ba, 6);
            this.common.add(Items.field_151131_as, 1);
            this.common.add(Items.field_151117_aB, 1);
            this.common.add(Items.field_151081_bc, 5);
            this.uncommon.add(new ItemStack(TFBlocks.firefly, 12));
            this.uncommon.add(new ItemStack(TFBlocks.sapling, 4, 0));
            this.uncommon.add(new ItemStack(TFBlocks.sapling, 4, 1));
            this.uncommon.add(new ItemStack(TFBlocks.sapling, 4, 2));
            this.uncommon.add(new ItemStack(TFBlocks.sapling, 4, 3));
            this.rare.add(Items.field_151158_bO, 12);
            this.rare.add(Items.field_151034_e, 12);
            this.rare.add(TFItems.charmOfLife1, 1);
            this.rare.add(TFItems.charmOfKeeping1, 1);
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 4));
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 5));
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 6));
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 7));
            this.ultrarare.add(new ItemStack(TFBlocks.sapling, 1, 8));
        }

        if (i == 15) {
            this.common.add(Items.field_151055_y, 12);
            this.common.add(new ItemStack(Items.field_151044_h, 12));
            this.common.add(Items.field_151032_g, 12);
            this.common.add(TFItems.mazeWafer, 9);
            this.common.add(new ItemStack(Blocks.field_150325_L, 1, 11));
            this.common.add(Items.field_151042_j, 2);
            this.uncommon.add(Items.field_151133_ar, 1);
            this.uncommon.add(Items.field_151042_j, 6);
            this.uncommon.add(TFItems.ironwoodIngot, 6);
            this.uncommon.add(TFBlocks.firefly, 5);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.uncommon.add(TFItems.armorShard, 3);
            this.rare.add(TFItems.knightMetal, 8);
            this.rare.addRandomEnchanted((Item) Items.field_151031_f, 20);
            this.rare.addRandomEnchanted(Items.field_151040_l, 20);
            this.rare.addRandomEnchanted(TFItems.ironwoodSword, 15);
            this.rare.addRandomEnchanted(TFItems.steeleafSword, 10);
            this.ultrarare.addEnchantedBook(Enchantment.field_77336_l, 4);
            this.ultrarare.addEnchantedBook(Enchantment.field_77338_j, 4);
            this.ultrarare.addEnchantedBook(Enchantment.field_77339_k, 4);
            this.ultrarare.addEnchantedBook(Enchantment.field_77347_r, 2);
            this.ultrarare.addEnchantedBook(Enchantment.field_77347_r, 2);
            this.ultrarare.addEnchantedBook(Enchantment.field_77332_c, 3);
            this.ultrarare.addEnchantedBook(Enchantment.field_77328_g, 3);
            this.ultrarare.addEnchantedBook(Enchantment.field_77330_e, 3);
        }

        if (i == 16) {
            this.useless.clear();
            this.common.add(Items.field_151042_j, 4);
            this.common.add(TFItems.mazeWafer, 12);
            this.common.add(Items.field_151016_H, 4);
            this.common.add(TFItems.ironwoodIngot, 4);
            this.common.add(TFBlocks.firefly, 5);
            this.common.add(Items.field_151117_aB, 1);
            this.uncommon.add(TFItems.steeleafIngot, 6);
            this.uncommon.add(TFItems.steeleafLegs, 1);
            this.uncommon.add(TFItems.steeleafPlate, 1);
            this.uncommon.add(TFItems.steeleafHelm, 1);
            this.uncommon.add(TFItems.steeleafBoots, 1);
            this.uncommon.add(TFItems.steeleafPick, 1);
            this.uncommon.add(TFItems.ironwoodPlate, 1);
            this.uncommon.add(TFItems.ironwoodSword, 1);
            this.uncommon.add(TFItems.charmOfLife1, 1);
            this.rare.add(TFItems.mazeMapFocus, 1);
            this.rare.addRandomEnchanted((Item) Items.field_151031_f, 30);
            this.rare.addRandomEnchanted(Items.field_151040_l, 30);
            this.rare.addRandomEnchanted(TFItems.ironwoodSword, 25);
            this.rare.addRandomEnchanted(TFItems.steeleafSword, 20);
            this.rare.addRandomEnchanted(Items.field_151048_u, 15);
        }

        if (i == 17) {
            this.useless.clear();
            this.common.addRandomEnchanted(TFItems.knightlySword, 20);
            this.common.addRandomEnchanted(TFItems.knightlyPick, 20);
            this.common.addRandomEnchanted(TFItems.knightlyAxe, 20);
            this.uncommon.addRandomEnchanted(TFItems.phantomHelm, 20);
            this.uncommon.addRandomEnchanted(TFItems.phantomPlate, 20);
            this.rare.addRandomEnchanted(TFItems.phantomHelm, 30);
            this.rare.addRandomEnchanted(TFItems.phantomPlate, 30);
        }

        if (i == 18) {
            this.common.add(Items.field_151055_y, 12);
            this.common.add(new ItemStack(Items.field_151044_h, 12));
            this.common.add(Items.field_151032_g, 12);
            this.common.add(TFItems.mazeWafer, 9);
            this.common.add(Blocks.field_150432_aD, 4);
            this.common.add(Blocks.field_150403_cj, 4);
            this.common.add(TFItems.ironwoodIngot, 2);
            this.uncommon.add(TFBlocks.auroraBlock, 12);
            this.uncommon.add(TFItems.ironwoodIngot, 6);
            this.uncommon.add(TFBlocks.firefly, 5);
            this.uncommon.add(TFItems.charmOfKeeping1, 1);
            this.uncommon.add(TFItems.arcticFur, 3);
            this.rare.add(TFItems.arcticFur, 8);
            this.rare.add(TFItems.iceBow, 1);
            this.rare.add(TFItems.enderBow, 1);
            this.rare.add(TFItems.iceSword, 1);
            this.ultrarare.addEnchantedBook(Enchantment.field_77338_j, 4);
            this.ultrarare.addEnchantedBook(Enchantment.field_77345_t, 4);
            this.ultrarare.addEnchantedBook(Enchantment.field_77344_u, 2);
            this.ultrarare.addEnchantedBook(Enchantment.field_77347_r, 2);
            this.ultrarare.addEnchantedBook(Enchantment.field_77347_r, 2);
            this.ultrarare.addEnchantedBook(Enchantment.field_77342_w, 1);
        }

        if (i == 19) {
            this.useless.clear();
            this.common.add(Blocks.field_150432_aD, 4);
            this.common.add(Blocks.field_150403_cj, 4);
            this.common.add(TFItems.mazeWafer, 12);
            this.common.add(TFItems.iceBomb, 4);
            this.common.add(TFBlocks.firefly, 5);
            this.uncommon.add(TFItems.arcticFur, 6);
            this.uncommon.add(TFItems.arcticLegs, 1);
            this.uncommon.add(TFItems.arcticPlate, 1);
            this.uncommon.add(TFItems.arcticHelm, 1);
            this.uncommon.add(TFItems.arcticBoots, 1);
            this.uncommon.add(TFItems.knightlyPlate, 1);
            this.uncommon.add(TFItems.knightlySword, 1);
            this.uncommon.add(TFItems.charmOfLife1, 1);
            this.rare.addRandomEnchanted(TFItems.iceBow, 30);
            this.rare.addRandomEnchanted(TFItems.enderBow, 5);
            this.rare.addRandomEnchanted(TFItems.iceSword, 25);
            this.rare.addRandomEnchanted(TFItems.glassSword, 20);
        }

        if (i == 21) {
            this.useless.clear();
            this.common.add((Block) Blocks.field_150337_Q, 4);
            this.common.add((Block) Blocks.field_150338_P, 4);
            this.common.add(Items.field_151014_N, 6);
            this.common.add(Items.field_151172_bF, 6);
            this.common.add(Items.field_151174_bG, 6);
            this.common.add(Items.field_151081_bc, 6);
            this.common.add(new ItemStack(Items.field_151100_aR, 12, 15));
            this.uncommon.add(TFBlocks.uberousSoil, 6);
            this.rare.add(TFItems.magicBeans, 1);
        }

        if (i == 22) {
            this.useless.clear();
            this.useless.clear();
            this.common.add(Items.field_151044_h, 32);
            this.common.add(TFItems.torchberries, 16);
            this.common.add(Items.field_151166_bC, 6);
            this.uncommon.add(TFBlocks.trollSteinn, 6);
            this.uncommon.add(Blocks.field_150343_Z, 6);
            this.rare.add(TFItems.lampOfCinders, 1);
        }

    }
}
