package twilightforest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.entity.EntityIceArrow;

public class ItemTFIceBow extends ItemTFBowBase {

    public ItemTFIceBow() {
        this.func_111206_d("TwilightForest:icebow");
        this.func_77637_a(TFItems.creativeTab);
    }

    protected EntityArrow getArrow(World world, EntityPlayer entityPlayer, float velocity) {
        return new EntityIceArrow(world, entityPlayer, velocity);
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == Item.func_150898_a(Blocks.field_150432_aD) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }
}
