package twilightforest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TFTreasureTable {

    private static final int DEFAULT_RARITY = 10;
    protected ArrayList list = new ArrayList();

    public void add(Item item, int quantity) {
        this.add(item, quantity, 10);
    }

    public void add(Item item, int quantity, int rarity) {
        this.list.add(new TFTreasureItem(item, quantity, rarity));
    }

    public void add(ItemStack itemstack) {
        this.list.add(new TFTreasureItem(itemstack, 10));
    }

    public void addEnchanted(ItemStack itemstack, Enchantment ench1, int enchLevel1) {
        itemstack.func_77966_a(ench1, enchLevel1);
        this.list.add(new TFTreasureItem(itemstack, 10));
    }

    public void addEnchantedBook(Enchantment ench1, int enchLevel1) {
        ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(ench1, enchLevel1));

        this.list.add(new TFTreasureItem(itemstack, 10));
    }

    public void addEnchanted(ItemStack itemstack, Enchantment ench1, int enchLevel1, Enchantment ench2, int enchLevel2) {
        itemstack.func_77966_a(ench1, enchLevel1);
        itemstack.func_77966_a(ench2, enchLevel2);
        this.list.add(new TFTreasureItem(itemstack, 10));
    }

    public void addEnchanted(ItemStack itemstack, Enchantment ench1, int enchLevel1, Enchantment ench2, int enchLevel2, Enchantment ench3, int enchLevel3) {
        itemstack.func_77966_a(ench1, enchLevel1);
        itemstack.func_77966_a(ench2, enchLevel2);
        itemstack.func_77966_a(ench3, enchLevel3);
        this.list.add(new TFTreasureItem(itemstack, 10));
    }

    public void addRandomEnchanted(ItemStack itemstack, int randomLevel) {
        TFTreasureItem treasure = new TFTreasureItem(itemstack, 10);

        treasure.setRandomEnchantmentLevel(randomLevel);
        this.list.add(treasure);
    }

    public void addRandomEnchanted(Item item, int randomLevel) {
        TFTreasureItem treasure = new TFTreasureItem(item, 1, 10);

        treasure.setRandomEnchantmentLevel(randomLevel);
        this.list.add(treasure);
    }

    public void add(Block block, int quantity) {
        this.add(block, quantity, 10);
    }

    public void add(Block block, int quantity, int rarity) {
        this.list.add(new TFTreasureItem(block, quantity, rarity));
    }

    protected int total() {
        int value = 0;

        TFTreasureItem item;

        for (Iterator iterator = this.list.iterator(); iterator.hasNext(); value += item.getRarity()) {
            item = (TFTreasureItem) iterator.next();
        }

        return value;
    }

    public ItemStack getRandomItem(Random rand) {
        int value = rand.nextInt(this.total());

        TFTreasureItem item;

        for (Iterator iterator = this.list.iterator(); iterator.hasNext(); value -= item.getRarity()) {
            item = (TFTreasureItem) iterator.next();
            if (item.getRarity() > value) {
                return item.getItemStack(rand);
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void clear() {
        this.list.clear();
    }
}
