package twilightforest.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import twilightforest.TwilightForestMod;
import twilightforest.block.BlockTFTowerDevice;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMiniGhast;

public class TileEntityTFGhastTrapInactive extends TileEntity {

    int counter;
    Random rand = new Random();
    ArrayList dyingGhasts = new ArrayList();

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double) this.field_145851_c, (double) this.field_145848_d, (double) this.field_145849_e, (double) (this.field_145851_c + 1), (double) (this.field_145848_d + 1), (double) (this.field_145849_e + 1)).func_72314_b(10.0D, 16.0D, 10.0D);
        List nearbyGhasts = this.field_145850_b.func_72872_a(EntityTFMiniGhast.class, aabb);
        Iterator chargeLevel = nearbyGhasts.iterator();

        EntityTFMiniGhast highlight;

        while (chargeLevel.hasNext()) {
            highlight = (EntityTFMiniGhast) chargeLevel.next();
            if (highlight.field_70725_aQ > 0) {
                this.makeParticlesTo(highlight);
                if (!this.dyingGhasts.contains(highlight)) {
                    this.dyingGhasts.add(highlight);
                }
            }
        }

        int i = Math.min(3, this.dyingGhasts.size());

        ++this.counter;
        if (this.field_145850_b.field_72995_K) {
            if (this.counter % 20 == 0 && nearbyGhasts.size() > 0) {
                highlight = (EntityTFMiniGhast) nearbyGhasts.get(this.rand.nextInt(nearbyGhasts.size()));
                this.makeParticlesTo(highlight);
            }

            if (i >= 1 && this.counter % 10 == 0) {
                ((BlockTFTowerDevice) TFBlocks.towerDevice).sparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73012_v);
                this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "note.harp", 1.0F, 1.0F, false);
            }

            if (i >= 2) {
                this.field_145850_b.func_72869_a("smoke", (double) this.field_145851_c + 0.1D + (double) this.rand.nextFloat() * 0.8D, (double) this.field_145848_d + 1.05D, (double) this.field_145849_e + 0.1D + (double) this.rand.nextFloat() * 0.8D, (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05D, 0.0D, (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05D);
                if (this.counter % 10 == 0) {
                    this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "note.harp", 1.2F, 0.8F, false);
                }
            }

            if (i >= 3) {
                this.field_145850_b.func_72869_a("largesmoke", (double) this.field_145851_c + 0.1D + (double) this.rand.nextFloat() * 0.8D, (double) this.field_145848_d + 1.05D, (double) this.field_145849_e + 0.1D + (double) this.rand.nextFloat() * 0.8D, (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05D, 0.05D, (double) (this.rand.nextFloat() - this.rand.nextFloat()) * 0.05D);
                ((BlockTFTowerDevice) TFBlocks.towerDevice).sparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.field_73012_v);
                if (this.counter % 5 == 0) {
                    this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, "note.harp", 1.5F, 2.0F, false);
                }
            }
        }

    }

    private void makeParticlesTo(Entity highlight) {
        double sx = (double) this.field_145851_c + 0.5D;
        double sy = (double) this.field_145848_d + 1.0D;
        double sz = (double) this.field_145849_e + 0.5D;
        double dx = sx - highlight.field_70165_t;
        double dy = sy - highlight.field_70163_u - (double) highlight.func_70047_e();
        double dz = sz - highlight.field_70161_v;

        for (int i = 0; i < 5; ++i) {
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "ghasttrap", sx, sy, sz, -dx, -dy, -dz);
        }

    }

    public boolean isCharged() {
        return this.dyingGhasts.size() >= 3;
    }
}
