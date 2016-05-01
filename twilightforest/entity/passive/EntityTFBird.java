package twilightforest.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class EntityTFBird extends EntityAnimal {

    public float flapLength = 0.0F;
    public float flapIntensity = 0.0F;
    public float lastFlapIntensity;
    public float lastFlapLength;
    public float flapSpeed = 1.0F;

    public EntityTFBird(World par1World) {
        super(par1World);
    }

    public boolean func_70650_aV() {
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.lastFlapLength = this.flapLength;
        this.lastFlapIntensity = this.flapIntensity;
        this.flapIntensity = (float) ((double) this.flapIntensity + (double) (this.field_70122_E ? -1 : 4) * 0.3D);
        if (this.flapIntensity < 0.0F) {
            this.flapIntensity = 0.0F;
        }

        if (this.flapIntensity > 1.0F) {
            this.flapIntensity = 1.0F;
        }

        if (!this.field_70122_E && this.flapSpeed < 1.0F) {
            this.flapSpeed = 1.0F;
        }

        this.flapSpeed = (float) ((double) this.flapSpeed * 0.9D);
        if (!this.field_70122_E && this.field_70181_x < 0.0D) {
            this.field_70181_x *= 0.6D;
        }

        this.flapLength += this.flapSpeed * 2.0F;
    }

    protected void func_70069_a(float par1) {}

    protected boolean func_70041_e_() {
        return false;
    }

    protected Item func_146068_u() {
        return Items.field_151008_G;
    }

    public EntityAnimal createChild(EntityAgeable entityanimal) {
        return null;
    }

    public boolean isBirdLanded() {
        return true;
    }
}
