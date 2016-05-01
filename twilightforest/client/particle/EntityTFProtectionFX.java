package twilightforest.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.World;

public class EntityTFProtectionFX extends EntityAuraFX {

    public EntityTFProtectionFX(World world, double x, double y, double z, double velX, double velY, double velZ) {
        super(world, x, y, z, velX, velY, velZ);
        this.func_70536_a(82);
        this.func_70538_b(1.0F, 1.0F, 1.0F);
    }

    public float func_70013_c(float par1) {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float par1) {
        return 15728880;
    }
}
