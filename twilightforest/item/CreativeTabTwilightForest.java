package twilightforest.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import twilightforest.block.TFBlocks;

public class CreativeTabTwilightForest extends CreativeTabs {

    public CreativeTabTwilightForest(String label) {
        super(label);
    }

    public Item func_78016_d() {
        return Item.func_150898_a(TFBlocks.fireflyJar);
    }
}
