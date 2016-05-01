package twilightforest;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TFTreasureItem {

    ItemStack itemStack;
    int rarity;
    int randomEnchantmentLevel;

    public TFTreasureItem(Item item) {
        this(item, 1, 10);
    }

    public TFTreasureItem(Item item, int quantity) {
        this(item, quantity, 10);
    }

    public TFTreasureItem(Item item, int quantity, int rarity) {
        this.randomEnchantmentLevel = 0;
        this.itemStack = new ItemStack(item, quantity);
        this.rarity = rarity;
    }

    public TFTreasureItem(Block block, int quantity, int rarity) {
        this.randomEnchantmentLevel = 0;
        this.itemStack = new ItemStack(block, quantity);
        this.rarity = rarity;
    }

    public TFTreasureItem(ItemStack itemStack, int rarity) {
        this.randomEnchantmentLevel = 0;
        this.itemStack = itemStack.func_77946_l();
        this.rarity = rarity;
    }

    public ItemStack getItemStack(Random rand) {
        ItemStack result = this.itemStack.func_77946_l();

        result.field_77994_a = rand.nextInt(result.field_77994_a) + 1;
        if (result.func_77956_u() && this.randomEnchantmentLevel > 0) {
            EnchantmentHelper.func_77504_a(rand, result, this.randomEnchantmentLevel);
        }

        return result;
    }

    public int getRarity() {
        return this.rarity;
    }

    public int getRandomEnchantmentLevel() {
        return this.randomEnchantmentLevel;
    }

    public void setRandomEnchantmentLevel(int randomEnchantmentLevel) {
        this.randomEnchantmentLevel = randomEnchantmentLevel;
    }
}
