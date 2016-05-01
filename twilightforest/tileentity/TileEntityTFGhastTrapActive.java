package twilightforest.tileentity;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFTowerGhast;
import twilightforest.entity.boss.EntityTFUrGhast;

public class TileEntityTFGhastTrapActive extends TileEntity {

    public int counter = 0;
    public Random rand = new Random();

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        ++this.counter;
        if (this.field_145850_b.field_72995_K) {
            if (this.counter > 100 && this.counter % 4 == 0) {
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "hugesmoke", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.95D, (double) this.field_145849_e + 0.5D, Math.cos((double) this.counter / 10.0D) * 0.05D, 0.25D, Math.sin((double) this.counter / 10.0D) * 0.05D);
            } else if (this.counter < 100) {
                double aabb = Math.cos((double) this.counter / 10.0D) * 2.5D;
                double dy = 20.0D;
                double dz = Math.sin((double) this.counter / 10.0D) * 2.5D;

                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, aabb, dy, dz);
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, -aabb, dy, -dz);
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, -aabb, dy / 2.0D, dz);
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, aabb, dy / 2.0D, -dz);
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, aabb / 2.0D, dy / 4.0D, dz / 2.0D);
                TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, -aabb / 2.0D, dy / 4.0D, -dz / 2.0D);
            }

            if (this.counter < 30) {
                this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "TwilightForest:mob.urghast.trapwarmup", 1.0F, 4.0F, false);
            } else if (this.counter < 80) {
                this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "TwilightForest:mob.urghast.trapon", 1.0F, 4.0F, false);
            } else {
                this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "TwilightForest:mob.urghast.trapspindown", 1.0F, 4.0F, false);
            }
        }

        if (!this.field_145850_b.field_72995_K) {
            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double) this.field_145851_c, (double) this.field_145848_d + 16.0D, (double) this.field_145849_e, (double) (this.field_145851_c + 1), (double) (this.field_145848_d + 16 + 1), (double) (this.field_145849_e + 1)).func_72314_b(6.0D, 16.0D, 6.0D);
            List nearbyGhasts = this.field_145850_b.func_72872_a(EntityGhast.class, axisalignedbb);
            Iterator iterator = nearbyGhasts.iterator();

            while (iterator.hasNext()) {
                EntityGhast ghast = (EntityGhast) iterator.next();

                if (ghast instanceof EntityTFUrGhast) {
                    ((EntityTFUrGhast) ghast).stopTantrum();
                    ghast.field_70159_w = (ghast.field_70165_t - (double) this.field_145851_c - 0.5D) * -0.1D;
                    ghast.field_70181_x = (ghast.field_70163_u - (double) this.field_145848_d - 2.5D) * -0.1D;
                    ghast.field_70179_y = (ghast.field_70161_v - (double) this.field_145849_e - 0.5D) * -0.1D;
                    if (this.rand.nextInt(10) == 0) {
                        ghast.func_70097_a(DamageSource.field_76377_j, 3.0F);
                    }
                } else {
                    ghast.field_70159_w = (ghast.field_70165_t - (double) this.field_145851_c - 0.5D) * -0.1D;
                    ghast.field_70181_x = (ghast.field_70163_u - (double) this.field_145848_d - 1.5D) * -0.1D;
                    ghast.field_70179_y = (ghast.field_70161_v - (double) this.field_145849_e - 0.5D) * -0.1D;
                    if (this.rand.nextInt(10) == 0) {
                        ghast.func_70097_a(DamageSource.field_76377_j, 10.0F);
                    }
                }

                if (ghast instanceof EntityTFTowerGhast) {
                    ((EntityTFTowerGhast) ghast).setInTrap();
                }
            }

            if (this.counter >= 120) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFBlocks.towerDevice, 10, 3);
            }
        }

    }
}
