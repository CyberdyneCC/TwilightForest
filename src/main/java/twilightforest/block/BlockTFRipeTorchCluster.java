package twilightforest.block;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFRipeTorchCluster extends BlockTFTrollRoot {

    protected BlockTFRipeTorchCluster() {
        this.func_149658_d("TwilightForest:ripe_torch_cluster");
        this.func_149715_a(1.0F);
    }

    public Item func_149650_a(int meta, Random rand, int fortune) {
        return TFItems.torchberries;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return this.func_149679_a(fortune, random);
    }

    public int func_149745_a(Random rand) {
        return 4 + rand.nextInt(5);
    }

    public int func_149679_a(int bonus, Random rand) {
        if (bonus > 0 && Item.func_150898_a(this) != this.func_149650_a(0, rand, bonus)) {
            int j = rand.nextInt(bonus + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return this.func_149745_a(rand) * (j + 1);
        } else {
            return this.func_149745_a(rand);
        }
    }

    public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (world.field_72995_K || player.func_71045_bC() == null || player.func_71045_bC().func_77973_b() != Items.field_151097_aZ) {
            super.func_149636_a(world, player, x, y, z, meta);
        }

    }
}
