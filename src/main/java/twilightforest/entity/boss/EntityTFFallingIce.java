package twilightforest.entity.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;

public class EntityTFFallingIce extends Entity {

    private static final int HANG_TIME = 100;
    private int fallTime;
    private float hurtAmount;
    private int hurtMax;

    public EntityTFFallingIce(World par1World) {
        super(par1World);
        this.func_70105_a(2.98F, 2.98F);
        this.hurtAmount = 10.0F;
        this.hurtMax = 30;
    }

    public EntityTFFallingIce(World par1World, int x, int y, int z) {
        this(par1World);
        this.field_70156_m = true;
        this.func_70107_b((double) x, (double) y, (double) z);
        this.field_70159_w = 0.0D;
        this.field_70181_x = 0.0D;
        this.field_70179_y = 0.0D;
        this.field_70169_q = (double) x;
        this.field_70167_r = (double) y;
        this.field_70166_s = (double) z;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70088_a() {}

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        ++this.fallTime;
        if (this.fallTime > 100) {
            this.field_70181_x -= 0.03999999910593033D;
        }

        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.9800000190734863D;
        this.field_70181_x *= 0.9800000190734863D;
        this.field_70179_y *= 0.9800000190734863D;
        if (!this.field_70170_p.field_72995_K && this.field_70122_E) {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
            this.field_70181_x *= -0.5D;
            this.func_70106_y();
        }

        if (!this.field_70170_p.field_72995_K) {
            ArrayList nearby = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D));
            Iterator iterator = nearby.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (entity instanceof EntityTFFallingIce) {
                    EntityTFFallingIce otherIce = (EntityTFFallingIce) entity;

                    if (otherIce.getFallTime() < this.fallTime) {
                        otherIce.func_70106_y();
                    }
                }
            }

            this.destroyIceInAABB(this.field_70121_D.func_72314_b(0.5D, 0.0D, 0.5D));
        }

        this.makeTrail();
    }

    public void makeTrail() {
        for (int i = 0; i < 2; ++i) {
            double dx = this.field_70165_t + (double) (2.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));
            double dy = this.field_70163_u - 3.0D + (double) (3.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));
            double dz = this.field_70161_v + (double) (2.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));

            TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowwarning", dx, dy, dz, 0.0D, -1.0D, 0.0D);
        }

    }

    protected void func_70069_a(float par1) {
        int distance = MathHelper.func_76123_f(par1 - 1.0F);

        if (distance > 0) {
            ArrayList i = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(2.0D, 0.0D, 2.0D)));
            DamageSource dx = DamageSource.field_82729_p;
            Iterator iterator = i.iterator();

            while (iterator.hasNext()) {
                Entity dy = (Entity) iterator.next();

                if (!(dy instanceof EntityTFYetiAlpha)) {
                    dy.func_70097_a(dx, (float) Math.min(MathHelper.func_76141_d((float) distance * this.hurtAmount), this.hurtMax));
                }
            }
        }

        for (int i = 0; i < 200; ++i) {
            double d0 = this.field_70165_t + (double) (3.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));
            double d1 = this.field_70163_u + 2.0D + (double) (3.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));
            double dz = this.field_70161_v + (double) (3.0F * (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()));

            this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(Blocks.field_150403_cj) + "_0", d0, d1, dz, 0.0D, 0.0D, 0.0D);
        }

        this.func_85030_a(Blocks.field_150467_bQ.field_149762_H.func_150495_a(), 3.0F, 0.5F);
        this.func_85030_a(Blocks.field_150403_cj.field_149762_H.func_150495_a(), 3.0F, 0.5F);
    }

    public void destroyIceInAABB(AxisAlignedBB par1AxisAlignedBB) {
        int minX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72340_a);
        int minY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72338_b);
        int minZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72339_c);
        int maxX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72336_d);
        int maxY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72337_e);
        int maxZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72334_f);

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dy = minY; dy <= maxY; ++dy) {
                for (int dz = minZ; dz <= maxZ; ++dz) {
                    Block block = this.field_70170_p.func_147439_a(dx, dy, dz);

                    if (block == Blocks.field_150432_aD || block == Blocks.field_150403_cj || block == Blocks.field_150348_b) {
                        this.field_70170_p.func_147465_d(dx, dy, dz, Blocks.field_150350_a, 0, 3);
                    }
                }
            }
        }

    }

    protected void func_70037_a(NBTTagCompound nbttagcompound) {}

    protected void func_70014_b(NBTTagCompound nbttagcompound) {}

    @SideOnly(Side.CLIENT)
    public boolean func_90999_ad() {
        return false;
    }

    public Block getBlock() {
        return Blocks.field_150403_cj;
    }

    public int getFallTime() {
        return this.fallTime;
    }
}
