package twilightforest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class ItemTFHydraChops extends ItemTFFood {

    public ItemTFHydraChops(int par2, float par3, boolean par4) {
        super(par2, par3, par4);
    }

    public ItemStack func_77654_b(ItemStack itemStack, World world, EntityPlayer player) {
        if (player.func_71024_bL().func_75116_a() <= 0) {
            player.func_71029_a(TFAchievementPage.twilightHydraChop);
        }

        return super.func_77654_b(itemStack, world, player);
    }
}
