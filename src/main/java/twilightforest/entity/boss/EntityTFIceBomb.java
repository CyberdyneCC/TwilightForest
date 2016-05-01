package twilightforest.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.entity.EntityTFYeti;

public class EntityTFIceBomb extends EntityThrowable {

    private int zoneTimer = 80;
    private boolean hasHit;

    public EntityTFIceBomb(World par1World) {
        super(par1World);
    }

    public EntityTFIceBomb(World par1World, EntityLivingBase thrower) {
        super(par1World, thrower);
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (this.func_85052_h() != null && this.func_85052_h() instanceof EntityTFYetiAlpha) {
            double dist = this.func_70068_e(this.func_85052_h());

            if (dist <= 100.0D) {
                this.func_70106_y();
            }
        }

        this.field_70181_x = 0.0D;
        this.hasHit = true;
        if (!this.field_70170_p.field_72995_K) {
            this.doTerrainEffects();
        }

    }

    private void doTerrainEffects() {
        byte range = 3;
        int ix = MathHelper.func_76128_c(this.field_70142_S);
        int iy = MathHelper.func_76128_c(this.field_70137_T);
        int iz = MathHelper.func_76128_c(this.field_70136_U);

        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    this.doTerrainEffect(ix + x, iy + y, iz + z);
                }
            }
        }

    }

    private void doTerrainEffect(int x, int y, int z) {
        if (this.field_70170_p.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) {
            this.field_70170_p.func_147449_b(x, y, z, Blocks.field_150432_aD);
        }

        if (this.field_70170_p.func_147439_a(x, y, z).func_149688_o() == Material.field_151587_i) {
            this.field_70170_p.func_147449_b(x, y, z, Blocks.field_150343_Z);
        }

        if (this.field_70170_p.func_147437_c(x, y, z) && Blocks.field_150431_aC.func_149742_c(this.field_70170_p, x, y, z)) {
            this.field_70170_p.func_147449_b(x, y, z, Blocks.field_150431_aC);
        }

    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.hasHit) {
            if (!this.field_70170_p.field_72995_K) {
                this.field_70159_w *= 0.1D;
                this.field_70181_x *= 0.1D;
                this.field_70179_y *= 0.1D;
            }

            --this.zoneTimer;
            this.makeIceZone();
            if (this.zoneTimer <= 0) {
                this.detonate();
            }
        } else {
            this.makeTrail();
        }

    }

    public void makeTrail() {
        for (int i = 0; i < 10; ++i) {
            double dx = this.field_70165_t + (double) (0.75F * (this.field_70146_Z.nextFloat() - 0.5F));
            double dy = this.field_70163_u + (double) (0.75F * (this.field_70146_Z.nextFloat() - 0.5F));
            double dz = this.field_70161_v + (double) (0.75F * (this.field_70146_Z.nextFloat() - 0.5F));

            TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowstuff", dx, dy, dz, 0.0D, 0.0D, 0.0D);
        }

    }

    private void makeIceZone() {
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 20; ++i) {
                double dx = this.field_70165_t + (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 3.0F);
                double dy = this.field_70163_u + (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 3.0F);
                double dz = this.field_70161_v + (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 3.0F);

                TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowstuff", dx, dy, dz, 0.0D, 0.0D, 0.0D);
            }
        } else if (this.zoneTimer % 10 == 0) {
            this.hitNearbyEntities();
        }

    }

    private void hitNearbyEntities() {
        ArrayList nearby = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(3.0D, 2.0D, 3.0D)));
        Iterator iterator = nearby.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLivingBase && entity != this.func_85052_h()) {
                if (entity instanceof EntityTFYeti) {
                    entity.func_70106_y();
                    int chillLevel = MathHelper.func_76128_c(entity.field_70142_S);
                    int iy = MathHelper.func_76128_c(entity.field_70137_T);
                    int iz = MathHelper.func_76128_c(entity.field_70136_U);

                    this.field_70170_p.func_147449_b(chillLevel, iy, iz, Blocks.field_150432_aD);
                    this.field_70170_p.func_147449_b(chillLevel, iy + 1, iz, Blocks.field_150432_aD);
                } else {
                    entity.func_70097_a(DamageSource.field_76376_m, 1.0F);
                    byte chillLevel1 = 2;

                    ((EntityLivingBase) entity).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, chillLevel1, true));
                }
            }
        }

    }

    private void detonate() {
        this.func_70106_y();
    }

    public Block getBlock() {
        return Blocks.field_150403_cj;
    }

    protected float func_70182_d() {
        return 0.75F;
    }

    protected float func_70185_h() {
        return this.hasHit ? 0.0F : 0.025F;
    }

    protected float func_70183_g() {
        return -20.0F;
    }
}
