package twilightforest.item;

import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;

public class BehaviorTFMobEggDispense extends BehaviorDefaultDispenseItem {

    final MinecraftServer mcServer;

    public BehaviorTFMobEggDispense(MinecraftServer par1) {
        this.mcServer = par1;
    }

    public ItemStack func_82487_b(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        EnumFacing facing = EnumFacing.func_82600_a(par1IBlockSource.func_82620_h());
        double x = par1IBlockSource.func_82615_a() + (double) facing.func_82601_c();
        double y = (double) ((float) par1IBlockSource.func_82622_e() + 0.2F);
        double z = par1IBlockSource.func_82616_c() + (double) facing.func_82599_e();

        ItemTFSpawnEgg.spawnCreature(par1IBlockSource.func_82618_k(), par2ItemStack.func_77960_j(), x, y, z);
        par2ItemStack.func_77979_a(1);
        return par2ItemStack;
    }
}
