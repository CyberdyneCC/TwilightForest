package twilightforest.client.particle;

import net.minecraft.world.World;

public class EntityTFSnowGuardianFX extends EntityTFSnowFX {

    public EntityTFSnowGuardianFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
        super(par1World, par2, par4, par6, par8, par10, par12, par14);
        this.field_70547_e = 10 + this.field_70146_Z.nextInt(15);
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 0.75F + this.field_70146_Z.nextFloat() * 0.25F;
    }
}
