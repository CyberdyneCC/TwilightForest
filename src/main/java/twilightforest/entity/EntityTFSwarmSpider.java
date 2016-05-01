package twilightforest.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;

public class EntityTFSwarmSpider extends EntitySpider {

    protected boolean shouldSpawn;

    public EntityTFSwarmSpider(World world) {
        this(world, true);
    }

    public EntityTFSwarmSpider(World world, boolean spawnMore) {
        super(world);
        this.shouldSpawn = false;
        this.func_70105_a(0.8F, 0.4F);
        this.setSpawnMore(spawnMore);
        this.field_70728_aV = 2;
    }

    public EntityTFSwarmSpider(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
    }

    public float spiderScaleAmount() {
        return 0.5F;
    }

    public float func_70603_bj() {
        return 0.5F;
    }

    public void func_70071_h_() {
        if (this.shouldSpawnMore()) {
            if (!this.field_70170_p.field_72995_K) {
                int more = 1 + this.field_70146_Z.nextInt(2);

                for (int i = 0; i < more; ++i) {
                    if (!this.spawnAnother()) {
                        this.spawnAnother();
                    }
                }
            }

            this.setSpawnMore(false);
        }

        super.func_70071_h_();
    }

    protected void func_70785_a(Entity entity, float f) {
        if (this.field_70724_aR <= 0 && (!this.field_70160_al || this.field_70146_Z.nextInt(4) != 0)) {
            this.field_70724_aR = 20;
        } else {
            super.func_70785_a(entity, f);
        }

    }

    protected Entity func_70782_k() {
        double d0 = 16.0D;

        return this.field_70170_p.func_72856_b(this, d0);
    }

    protected boolean spawnAnother() {
        EntityTFSwarmSpider another = new EntityTFSwarmSpider(this.field_70170_p, false);
        double sx = this.field_70165_t + (this.field_70146_Z.nextBoolean() ? 0.9D : -0.9D);
        double sy = this.field_70163_u;
        double sz = this.field_70161_v + (this.field_70146_Z.nextBoolean() ? 0.9D : -0.9D);

        another.func_70012_b(sx, sy, sz, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
        if (!another.func_70601_bi()) {
            another.func_70106_y();
            return false;
        } else {
            this.field_70170_p.func_72838_d(another);
            return true;
        }
    }

    protected boolean func_70814_o() {
        int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
        int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

        return TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hedgeMaze ? true : super.func_70814_o();
    }

    public boolean shouldSpawnMore() {
        return this.shouldSpawn;
    }

    public void setSpawnMore(boolean flag) {
        this.shouldSpawn = flag;
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74757_a("SpawnMore", this.shouldSpawnMore());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setSpawnMore(nbttagcompound.func_74767_n("SpawnMore"));
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

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.5F;
    }

    public int func_70641_bl() {
        return 16;
    }
}
