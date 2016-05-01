package twilightforest.tileentity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;

public class TileEntityTFFlameJet extends TileEntity {

    int counter;
    private int nextMeta;

    public TileEntityTFFlameJet() {
        this(8);
    }

    public TileEntityTFFlameJet(int parNextMeta) {
        this.counter = 0;
        this.nextMeta = parNextMeta;
    }

    public void func_145845_h() {
        if (++this.counter > 60) {
            this.counter = 0;
            if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == TFBlocks.fireJet) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFBlocks.fireJet, this.nextMeta, 3);
            }

            this.func_145843_s();
        } else if (this.counter % 2 == 0) {
            this.field_145850_b.func_72869_a("largesmoke", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, 0.0D, 0.0D, 0.0D);
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "largeflame", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, 0.0D, 0.5D, 0.0D);
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "largeflame", (double) this.field_145851_c - 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, 0.05D, 0.5D, 0.0D);
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "largeflame", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e - 0.5D, 0.0D, 0.5D, 0.05D);
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "largeflame", (double) this.field_145851_c + 1.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 0.5D, -0.05D, 0.5D, 0.0D);
            TwilightForestMod.proxy.spawnParticle(this.field_145850_b, "largeflame", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.0D, (double) this.field_145849_e + 1.5D, 0.0D, 0.5D, -0.05D);
        }

        if (this.counter % 4 == 0) {
            this.field_145850_b.func_72908_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, "mob.ghast.fireball", 1.0F + this.field_145850_b.field_73012_v.nextFloat(), this.field_145850_b.field_73012_v.nextFloat() * 0.7F + 0.3F);
        } else if (this.counter == 1) {
            this.field_145850_b.func_72908_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, "fire.ignite", 1.0F + this.field_145850_b.field_73012_v.nextFloat(), this.field_145850_b.field_73012_v.nextFloat() * 0.7F + 0.3F);
        }

        if (!this.field_145850_b.field_72995_K && this.counter % 5 == 0) {
            List entitiesInRange = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double) (this.field_145851_c - 2), (double) this.field_145848_d, (double) (this.field_145849_e - 2), (double) (this.field_145851_c + 2), (double) (this.field_145848_d + 4), (double) (this.field_145849_e + 2)));
            Iterator iterator = entitiesInRange.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (!entity.func_70045_F()) {
                    entity.func_70097_a(DamageSource.field_76372_a, 2.0F);
                    entity.func_70015_d(15);
                }
            }
        }

    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.nextMeta = par1NBTTagCompound.func_74762_e("NextMeta");
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("NextMeta", this.nextMeta);
    }
}
