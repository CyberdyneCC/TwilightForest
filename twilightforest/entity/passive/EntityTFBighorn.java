package twilightforest.entity.passive;

import java.util.Random;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFBighorn extends EntitySheep {

    public EntityTFBighorn(World world) {
        super(world);
        this.func_70105_a(0.9F, 1.3F);
    }

    public EntityTFBighorn(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    public static int getRandomFleeceColor(Random random) {
        return random.nextInt(2) == 0 ? 12 : random.nextInt(15);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
        this.func_70891_b(getRandomFleeceColor(this.field_70170_p.field_73012_v));
        return par1EntityLivingData;
    }

    public EntitySheep func_90011_a(EntityAgeable entityanimal) {
        EntityTFBighorn otherParent = (EntityTFBighorn) entityanimal;
        EntityTFBighorn babySheep = new EntityTFBighorn(this.field_70170_p);

        if (this.field_70146_Z.nextBoolean()) {
            babySheep.func_70891_b(this.func_70896_n());
        } else {
            babySheep.func_70891_b(otherParent.func_70896_n());
        }

        return babySheep;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }
}
