package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;

public class EntityTFHedgeSpider extends EntitySpider {

    public EntityTFHedgeSpider(World world) {
        super(world);
    }

    public EntityTFHedgeSpider(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected Entity func_70782_k() {
        double d0 = 16.0D;

        return this.field_70170_p.func_72856_b(this, d0);
    }

    protected boolean func_70814_o() {
        int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
        int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

        return TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hedgeMaze ? true : super.func_70814_o();
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

            if (TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hedgeMaze) {
                ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHedge);
            }
        }

    }
}
