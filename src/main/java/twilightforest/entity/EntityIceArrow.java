package twilightforest.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;

public class EntityIceArrow extends EntityArrow {

    public EntityIceArrow(World par1World) {
        super(par1World);
    }

    public EntityIceArrow(World world, EntityPlayer player, float velocity) {
        super(world, player, velocity);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }
}
