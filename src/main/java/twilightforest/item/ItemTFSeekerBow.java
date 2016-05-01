package twilightforest.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import twilightforest.entity.EntitySeekerArrow;

public class ItemTFSeekerBow extends ItemTFBowBase {

    public ItemTFSeekerBow() {
        this.func_111206_d("TwilightForest:seekerbow");
        this.func_77637_a(TFItems.creativeTab);
    }

    protected EntityArrow getArrow(World world, EntityPlayer entityPlayer, float velocity) {
        return new EntitySeekerArrow(world, entityPlayer, velocity * 0.5F);
    }
}
