package twilightforest.entity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class EntityTFArmoredGiant extends EntityTFGiantMiner {

    public EntityTFArmoredGiant(World par1World) {
        super(par1World);
        this.func_70062_b(0, new ItemStack(Items.field_151052_q));
        this.func_70062_b(1, new ItemStack(Items.field_151028_Y));
        this.func_70062_b(2, new ItemStack(Items.field_151030_Z));
        this.func_70062_b(3, new ItemStack(Items.field_151165_aa));
        this.func_70062_b(4, new ItemStack(Items.field_151167_ab));
    }

    protected Item func_146068_u() {
        return TFItems.giantSword;
    }
}
