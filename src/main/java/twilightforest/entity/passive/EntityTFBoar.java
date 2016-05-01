package twilightforest.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFBoar extends EntityPig {

    public EntityTFBoar(World world) {
        super(world);
        this.func_70105_a(0.9F, 0.9F);
    }

    public EntityTFBoar(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    public EntityPig func_90011_a(EntityAgeable entityanimal) {
        return new EntityTFBoar(this.field_70170_p);
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
