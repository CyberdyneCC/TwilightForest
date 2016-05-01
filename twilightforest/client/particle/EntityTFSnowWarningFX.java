package twilightforest.client.particle;

import net.minecraft.world.World;

public class EntityTFSnowWarningFX extends EntityTFSnowFX {

    public EntityTFSnowWarningFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14) {
        super(par1World, par2, par4, par6, par8, par10, par12, par14);
        this.field_70547_e = 50;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.field_70181_x -= 0.019999999552965164D;
    }
}
