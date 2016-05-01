package twilightforest.entity.boss;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFNaga extends EntityMob implements IMob, IBossDisplayData, IEntityMultiPart {

    private static int TICKS_BEFORE_HEALING = 600;
    private static int MAX_SEGMENTS = 12;
    int currentSegments = 0;
    float segmentHealth;
    int LEASH_X = 46;
    int LEASH_Y = 7;
    int LEASH_Z = 46;
    EntityTFNagaSegment[] body;
    protected PathEntity field_70786_d;
    protected Entity targetEntity;
    int circleCount;
    int intimidateTimer;
    int crumblePlayerTimer;
    int chargeCount;
    boolean clockwise;
    public int ticksSinceDamaged = 0;

    public EntityTFNaga(World world) {
        super(world);
        this.func_70105_a(1.75F, 3.0F);
        this.field_70138_W = 2.0F;
        this.func_70606_j(this.func_110138_aP());
        this.segmentHealth = this.func_110138_aP() / 10.0F;
        this.setSegmentsPerHealth();
        this.field_70728_aV = 217;
        this.field_70158_ak = true;
        this.circleCount = 15;
        this.body = new EntityTFNagaSegment[EntityTFNaga.MAX_SEGMENTS];

        for (int i = 0; i < this.body.length; ++i) {
            this.body[i] = new EntityTFNagaSegment(this, i);
            world.func_72838_d(this.body[i]);
        }

        this.goNormal();
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public float getMaxHealthPerDifficulty() {
        return this.field_70170_p != null ? (this.field_70170_p.field_73013_u == EnumDifficulty.EASY ? 120.0F : (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL ? 200.0F : (this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 250.0F : 200.0F))) : 200.0F;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double) this.getMaxHealthPerDifficulty());
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(2.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
    }

    protected int setSegmentsPerHealth() {
        int oldSegments = this.currentSegments;
        int newSegments = (int) (this.func_110143_aJ() / this.segmentHealth + (float) (this.func_110143_aJ() > 0.0F ? 2 : 0));

        if (newSegments < 0) {
            newSegments = 0;
        }

        if (newSegments > EntityTFNaga.MAX_SEGMENTS) {
            newSegments = EntityTFNaga.MAX_SEGMENTS;
        }

        if (newSegments != oldSegments) {
            if (newSegments < oldSegments) {
                for (int i = newSegments; i < oldSegments; ++i) {
                    if (this.body != null && this.body[i] != null) {
                        this.body[i].selfDestruct();
                    }
                }
            } else {
                this.spawnBodySegments();
            }
        }

        this.currentSegments = newSegments;
        this.setMovementFactorPerSegments();
        return this.currentSegments;
    }

    protected void setMovementFactorPerSegments() {
        float movementFactor = 0.6F - (float) this.currentSegments / 12.0F * 0.2F;

        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double) movementFactor);
    }

    public boolean func_70041_e_() {
        return false;
    }

    public boolean func_70058_J() {
        return false;
    }

    public void func_70071_h_() {
        this.despawnIfInvalid();
        int i;

        if (this.field_70725_aQ > 0) {
            for (i = 0; i < 5; ++i) {
                double d = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                String explosionType = this.field_70146_Z.nextBoolean() ? "hugeexplosion" : "explode";

                this.field_70170_p.func_72869_a(explosionType, this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d, d1, d2);
            }
        }

        ++this.ticksSinceDamaged;
        if (!this.field_70170_p.field_72995_K && this.ticksSinceDamaged > EntityTFNaga.TICKS_BEFORE_HEALING && this.ticksSinceDamaged % 20 == 0) {
            this.func_70691_i(1.0F);
        }

        this.setSegmentsPerHealth();
        super.func_70071_h_();
        this.moveSegments();

        for (i = 0; i < this.body.length; ++i) {
            if (!this.body[i].field_70175_ag && !this.field_70170_p.field_72995_K) {
                this.field_70170_p.func_72838_d(this.body[i]);
            }
        }

    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.field_70123_F && this.hasTarget()) {
            this.breakNearbyBlocks();
        }

        if (this.targetEntity != null && !this.isEntityWithinHomeArea(this.targetEntity)) {
            this.targetEntity = null;
        }

        if (this.targetEntity == null) {
            this.targetEntity = this.findTarget();
            if (this.targetEntity != null) {
                this.acquireNewPath();
            }
        } else if (!this.targetEntity.func_70089_S()) {
            this.targetEntity = null;
        } else {
            float inWater = this.targetEntity.func_70032_d(this);

            if (inWater > 80.0F) {
                this.targetEntity = null;
            } else if (this.func_70685_l(this.targetEntity)) {
                this.func_70785_a(this.targetEntity, inWater);
            }
        }

        if (!this.func_70781_l()) {
            this.acquireNewPath();
        }

        boolean inWater1 = this.func_70090_H();
        boolean inLava = this.func_70058_J();
        Vec3 vec3d = this.func_70781_l() ? this.field_70786_d.func_75878_a(this) : null;
        double d1 = (double) (this.field_70130_N * 4.0F);

        while (vec3d != null && vec3d.func_72445_d(this.field_70165_t, vec3d.field_72448_b, this.field_70161_v) < d1 * d1) {
            this.field_70786_d.func_75875_a();
            if (this.field_70786_d.func_75879_b()) {
                vec3d = null;
                this.field_70786_d = null;
            } else {
                vec3d = this.field_70786_d.func_75878_a(this);
            }
        }

        this.field_70703_bu = false;
        if (vec3d != null) {
            d1 = vec3d.field_72450_a - this.field_70165_t;
            double d2 = vec3d.field_72449_c - this.field_70161_v;
            double dist = (double) MathHelper.func_76133_a(d1 * d1 + d2 * d2);
            int i = MathHelper.func_76128_c(this.field_70121_D.field_72338_b + 0.5D);
            double d3 = vec3d.field_72448_b - (double) i;
            float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f3 = f2 - this.field_70177_z;

            this.field_70701_bs = this.getMoveSpeed();
            this.func_70659_e(0.5F);
            if (dist > 4.0D && this.chargeCount == 0) {
                this.field_70702_br = MathHelper.func_76134_b((float) this.field_70173_aa * 0.3F) * this.getMoveSpeed() * 0.6F;
            }

            while (f3 < -180.0F) {
                f3 += 360.0F;
            }

            while (f3 >= 180.0F) {
                f3 -= 360.0F;
            }

            if (f3 > 30.0F) {
                f3 = 30.0F;
            }

            if (f3 < -30.0F) {
                f3 = -30.0F;
            }

            this.field_70177_z += f3;
            if (d3 > 0.6D) {
                this.field_70703_bu = true;
            }
        }

        if (this.intimidateTimer > 0 && this.hasTarget()) {
            this.func_70625_a(this.targetEntity, 30.0F, 30.0F);
            this.field_70701_bs = 0.1F;
        }

        if (this.intimidateTimer > 0 && this.hasTarget()) {
            this.func_70625_a(this.targetEntity, 30.0F, 30.0F);
            this.field_70701_bs = 0.1F;
        }

        if (this.field_70146_Z.nextFloat() < 0.8F && (inWater1 || inLava)) {
            this.field_70703_bu = true;
        }

    }

    private float getMoveSpeed() {
        return 0.5F;
    }

    private void setMoveSpeed(float f) {
        this.func_70659_e(f);
    }

    protected void breakNearbyBlocks() {
        int minx = MathHelper.func_76128_c(this.field_70121_D.field_72340_a - 0.5D);
        int miny = MathHelper.func_76128_c(this.field_70121_D.field_72338_b + 1.01D);
        int minz = MathHelper.func_76128_c(this.field_70121_D.field_72339_c - 0.5D);
        int maxx = MathHelper.func_76128_c(this.field_70121_D.field_72336_d + 0.5D);
        int maxy = MathHelper.func_76128_c(this.field_70121_D.field_72337_e + 0.001D);
        int maxz = MathHelper.func_76128_c(this.field_70121_D.field_72334_f + 0.5D);

        if (this.field_70170_p.func_72904_c(minx, miny, minz, maxx, maxy, maxz)) {
            for (int dx = minx; dx <= maxx; ++dx) {
                for (int dy = miny; dy <= maxy; ++dy) {
                    for (int dz = minz; dz <= maxz; ++dz) {
                        Block i5 = this.field_70170_p.func_147439_a(dx, dy, dz);

                        if (i5 != Blocks.field_150350_a) {
                            this.breakBlock(dx, dy, dz);
                        }
                    }
                }
            }
        }

    }

    protected String func_70639_aQ() {
        return this.field_70146_Z.nextInt(3) != 0 ? "TwilightForest:mob.naga.hiss" : "TwilightForest:mob.naga.rattle";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.naga.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.naga.hurt";
    }

    protected void acquireNewPath() {
        if (!this.hasTarget()) {
            this.wanderRandomly();
        } else if (this.intimidateTimer > 0) {
            this.field_70786_d = null;
            --this.intimidateTimer;
            if (this.intimidateTimer == 0) {
                this.clockwise = !this.clockwise;
                if (this.targetEntity.field_70121_D.field_72338_b > this.field_70121_D.field_72337_e) {
                    this.doCrumblePlayer();
                } else {
                    this.doCharge();
                }
            }

        } else {
            if (this.crumblePlayerTimer > 0) {
                this.field_70786_d = null;
                --this.crumblePlayerTimer;
                this.crumbleBelowTarget(2);
                this.crumbleBelowTarget(3);
                if (this.crumblePlayerTimer == 0) {
                    this.doCharge();
                }
            }

            if (this.chargeCount > 0) {
                --this.chargeCount;
                Vec3 radius = this.findCirclePoint(this.targetEntity, 14.0D, 3.141592653589793D);

                this.field_70786_d = this.field_70170_p.func_72844_a(this, MathHelper.func_76128_c(radius.field_72450_a), MathHelper.func_76128_c(radius.field_72448_b), MathHelper.func_76128_c(radius.field_72449_c), 40.0F, true, true, true, true);
                if (this.chargeCount == 0) {
                    this.doCircle();
                }
            }

            if (this.circleCount > 0) {
                --this.circleCount;
                double d0 = this.circleCount % 2 == 0 ? 12.0D : 14.0D;
                double rotation = 1.0D;

                if (this.circleCount > 1 && this.circleCount < 3) {
                    d0 = 16.0D;
                }

                if (this.circleCount == 1) {
                    rotation = 0.1D;
                }

                Vec3 tpoint = this.findCirclePoint(this.targetEntity, d0, rotation);

                this.field_70786_d = this.field_70170_p.func_72844_a(this, (int) tpoint.field_72450_a, (int) tpoint.field_72448_b, (int) tpoint.field_72449_c, 40.0F, true, true, true, true);
                if (this.circleCount == 0) {
                    this.doIntimidate();
                }
            }

        }
    }

    protected void crumbleBelowTarget(int range) {
        int floor = (int) this.field_70121_D.field_72338_b;
        int targetY = (int) this.targetEntity.field_70121_D.field_72338_b;

        if (targetY > floor) {
            int dx = (int) this.targetEntity.field_70165_t + this.field_70146_Z.nextInt(range) - this.field_70146_Z.nextInt(range);
            int dz = (int) this.targetEntity.field_70161_v + this.field_70146_Z.nextInt(range) - this.field_70146_Z.nextInt(range);
            int dy = targetY - this.field_70146_Z.nextInt(range) + this.field_70146_Z.nextInt(range > 1 ? range - 1 : range);

            if (dy <= floor) {
                dy = targetY;
            }

            if (this.field_70170_p.func_147439_a(dx, dy, dz) != Blocks.field_150350_a) {
                this.breakBlock(dx, dy, dz);

                for (int k = 0; k < 20; ++k) {
                    double d = this.field_70146_Z.nextGaussian() * 0.02D;
                    double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                    double d2 = this.field_70146_Z.nextGaussian() * 0.02D;

                    this.field_70170_p.func_72869_a("crit", this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d, d1, d2);
                }
            }
        }

    }

    protected void breakBlock(int dx, int dy, int dz) {
        Block whatsThere = this.field_70170_p.func_147439_a(dx, dy, dz);
        int whatsMeta = this.field_70170_p.func_72805_g(dx, dy, dz);

        if (whatsThere != Blocks.field_150350_a) {
            whatsThere.func_149697_b(this.field_70170_p, dx, dy, dz, whatsMeta, 0);
            this.field_70170_p.func_147465_d(dx, dy, dz, Blocks.field_150350_a, 0, 2);
            this.field_70170_p.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(whatsThere) + (whatsMeta << 12));
        }

    }

    protected void doCircle() {
        this.circleCount += 10 + this.field_70146_Z.nextInt(10);
        this.goNormal();
    }

    protected void doCrumblePlayer() {
        this.crumblePlayerTimer = 20 + this.field_70146_Z.nextInt(20);
        this.goSlow();
    }

    protected void doCharge() {
        this.chargeCount = 4;
        this.goFast();
    }

    protected void doIntimidate() {
        this.intimidateTimer += 15 + this.field_70146_Z.nextInt(10);
        this.goSlow();
    }

    protected void goSlow() {
        this.field_70702_br = 0.0F;
        this.setMoveSpeed(0.1F);
        this.field_70786_d = null;
    }

    protected void goNormal() {
        this.setMoveSpeed(0.6F);
    }

    protected void goFast() {
        this.setMoveSpeed(1.0F);
    }

    public boolean func_70104_M() {
        return false;
    }

    protected Vec3 findCirclePoint(Entity toCircle, double radius, double rotation) {
        double vecx = this.field_70165_t - toCircle.field_70165_t;
        double vecz = this.field_70161_v - toCircle.field_70161_v;
        float rangle = (float) Math.atan2(vecz, vecx);

        rangle = (float) ((double) rangle + (this.clockwise ? rotation : -rotation));
        double dx = (double) MathHelper.func_76134_b(rangle) * radius;
        double dz = (double) MathHelper.func_76126_a(rangle) * radius;
        double dy = Math.min(this.field_70121_D.field_72338_b, toCircle.field_70163_u);

        return Vec3.func_72443_a(toCircle.field_70165_t + dx, dy, toCircle.field_70161_v + dz);
    }

    public boolean hasTarget() {
        return this.targetEntity != null;
    }

    protected Entity findTarget() {
        EntityPlayer entityplayer = this.field_70170_p.func_72856_b(this, 32.0D);

        return entityplayer != null && this.func_70685_l(entityplayer) && this.isEntityWithinHomeArea(entityplayer) ? entityplayer : null;
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        if (damagesource.func_76364_f() != null && !this.isEntityWithinHomeArea(damagesource.func_76364_f())) {
            return false;
        } else if (damagesource.func_76346_g() != null && !this.isEntityWithinHomeArea(damagesource.func_76346_g())) {
            return false;
        } else if (super.func_70097_a(damagesource, i)) {
            this.setSegmentsPerHealth();
            Entity entity = damagesource.func_76346_g();

            if (entity != this) {
                this.targetEntity = entity;
            }

            this.ticksSinceDamaged = 0;
            return true;
        } else {
            return false;
        }
    }

    protected void func_70785_a(Entity toAttack, float f) {
        if (this.field_70724_aR <= 0 && f < 4.0F && toAttack.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b - 2.5D && toAttack.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e + 2.5D) {
            this.field_70724_aR = 20;
            this.func_70652_k(toAttack);
            if ((double) this.getMoveSpeed() > 0.8D) {
                toAttack.func_70024_g((double) (-MathHelper.func_76126_a(this.field_70177_z * 3.141593F / 180.0F) * 1.0F), 0.1D, (double) (MathHelper.func_76134_b(this.field_70177_z * 3.141593F / 180.0F) * 1.0F));
            }
        }

    }

    protected void wanderRandomly() {
        this.goNormal();
        boolean flag = false;
        int tx = -1;
        int ty = -1;
        int tz = -1;
        float worstweight = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int dx = MathHelper.func_76128_c(this.field_70165_t + (double) this.field_70146_Z.nextInt(21) - 6.0D);
            int dy = MathHelper.func_76128_c(this.field_70163_u + (double) this.field_70146_Z.nextInt(7) - 3.0D);
            int dz = MathHelper.func_76128_c(this.field_70161_v + (double) this.field_70146_Z.nextInt(21) - 6.0D);

            if (!this.func_110176_b(dx, dy, dz)) {
                dx = this.func_110172_bL().field_71574_a + this.field_70146_Z.nextInt(21) - this.field_70146_Z.nextInt(21);
                dy = this.func_110172_bL().field_71572_b + this.field_70146_Z.nextInt(7) - this.field_70146_Z.nextInt(7);
                dz = this.func_110172_bL().field_71573_c + this.field_70146_Z.nextInt(21) - this.field_70146_Z.nextInt(21);
            }

            float weight = this.func_70783_a(dx, dy, dz);

            if (weight > worstweight) {
                worstweight = weight;
                tx = dx;
                ty = dy;
                tz = dz;
                flag = true;
            }
        }

        if (flag) {
            this.field_70786_d = this.field_70170_p.func_72844_a(this, tx, ty, tz, 80.0F, true, true, true, true);
        }

    }

    public float func_70783_a(int i, int j, int k) {
        return !this.func_110176_b(i, j, k) ? Float.MIN_VALUE : 0.0F;
    }

    public boolean func_70781_l() {
        return this.field_70786_d != null;
    }

    protected Item func_146068_u() {
        return TFItems.nagaScale;
    }

    protected void func_70628_a(boolean flag, int z) {
        Item i = this.func_146068_u();

        if (i != null) {
            int j = 6 + this.field_70146_Z.nextInt(6);

            for (int k = 0; k < j; ++k) {
                this.func_145779_a(i, 1);
            }
        }

        this.func_70099_a(new ItemStack(TFItems.trophy, 1, 1), 0.0F);
    }

    protected void despawnIfInvalid() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.despawnMe();
        }

    }

    protected void despawnMe() {
        if (this.isLeashed()) {
            ChunkCoordinates home = this.func_110172_bL();

            this.field_70170_p.func_147465_d(home.field_71574_a, home.field_71572_b, home.field_71573_c, TFBlocks.bossSpawner, 0, 2);
        }

        this.func_70106_y();
    }

    public boolean isLeashed() {
        return this.getMaximumHomeDistance() > -1.0F;
    }

    public boolean func_110176_b(int x, int y, int z) {
        if (this.getMaximumHomeDistance() == -1.0F) {
            return true;
        } else {
            int distX = Math.abs(this.func_110172_bL().field_71574_a - x);
            int distY = Math.abs(this.func_110172_bL().field_71572_b - y);
            int distZ = Math.abs(this.func_110172_bL().field_71573_c - z);

            return distX <= this.LEASH_X && distY <= this.LEASH_Y && distZ <= this.LEASH_Z;
        }
    }

    public boolean isEntityWithinHomeArea(Entity entity) {
        return this.func_110176_b(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70163_u), MathHelper.func_76128_c(entity.field_70161_v));
    }

    protected void spawnBodySegments() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.body == null) {
                this.body = new EntityTFNagaSegment[EntityTFNaga.MAX_SEGMENTS];
            }

            for (int i = 0; i < this.currentSegments; ++i) {
                if (this.body[i] == null || this.body[i].field_70128_L) {
                    this.body[i] = new EntityTFNagaSegment(this, i);
                    this.body[i].func_70012_b(this.field_70165_t + 0.1D * (double) i, this.field_70163_u + 0.5D, this.field_70161_v + 0.1D * (double) i, this.field_70146_Z.nextFloat() * 360.0F, 0.0F);
                    this.field_70170_p.func_72838_d(this.body[i]);
                }
            }
        }

    }

    protected void moveSegments() {
        for (int i = 0; i < this.currentSegments; ++i) {
            Object leader;

            if (i == 0) {
                leader = this;
            } else {
                leader = this.body[i - 1];
            }

            double followX = ((Entity) leader).field_70165_t;
            double followY = ((Entity) leader).field_70163_u;
            double followZ = ((Entity) leader).field_70161_v;
            float angle = (((Entity) leader).field_70177_z + 180.0F) * 3.141593F / 180.0F;
            double straightenForce = 0.05D + 1.0D / (double) ((float) (i + 1)) * 0.5D;
            double idealX = (double) (-MathHelper.func_76126_a(angle)) * straightenForce;
            double idealZ = (double) MathHelper.func_76134_b(angle) * straightenForce;
            Vec3 diff = Vec3.func_72443_a(this.body[i].field_70165_t - followX, this.body[i].field_70163_u - followY, this.body[i].field_70161_v - followZ);

            diff = diff.func_72432_b();
            diff = diff.func_72441_c(idealX, 0.0D, idealZ);
            diff = diff.func_72432_b();
            double f = 2.0D;
            double destX = followX + f * diff.field_72450_a;
            double destY = followY + f * diff.field_72448_b;
            double destZ = followZ + f * diff.field_72449_c;

            this.body[i].func_70107_b(destX, destY, destZ);
            this.body[i].field_70159_w = f * diff.field_72450_a;
            this.body[i].field_70181_x = f * diff.field_72448_b;
            this.body[i].field_70179_y = f * diff.field_72449_c;
            double distance = (double) MathHelper.func_76133_a(diff.field_72450_a * diff.field_72450_a + diff.field_72449_c * diff.field_72449_c);

            if (i == 0) {
                diff.field_72448_b -= 0.15D;
            }

            this.body[i].func_70101_b((float) (Math.atan2(diff.field_72449_c, diff.field_72450_a) * 180.0D / 3.141592653589793D) + 90.0F, -((float) (Math.atan2(diff.field_72448_b, distance) * 180.0D / 3.141592653589793D)));
        }

    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        ChunkCoordinates home = this.func_110172_bL();

        nbttagcompound.func_74782_a("Home", this.func_70087_a(new double[] { (double) home.field_71574_a, (double) home.field_71572_b, (double) home.field_71573_c}));
        nbttagcompound.func_74757_a("HasHome", this.func_110175_bO());
        super.func_70014_b(nbttagcompound);
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        if (nbttagcompound.func_150297_b("Home", 9)) {
            NBTTagList nbttaglist = nbttagcompound.func_150295_c("Home", 6);
            int hx = (int) nbttaglist.func_150309_d(0);
            int hy = (int) nbttaglist.func_150309_d(1);
            int hz = (int) nbttaglist.func_150309_d(2);

            this.func_110171_b(hx, hy, hz, 20);
        }

        if (!nbttagcompound.func_74767_n("HasHome")) {
            this.func_110177_bN();
        }

        this.setSegmentsPerHealth();
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightKillNaga);
        }

        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
            int dx = MathHelper.func_76128_c(this.field_70165_t);
            int dy = MathHelper.func_76128_c(this.field_70163_u);
            int dz = MathHelper.func_76128_c(this.field_70161_v);
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
            TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, dz, this.field_70170_p);

            if (nearbyFeature == TFFeature.nagaCourtyard) {
                chunkProvider.setStructureConquered(dx, dy, dz, true);
            }
        }

    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    public boolean func_70965_a(EntityDragonPart entitydragonpart, DamageSource damagesource, float i) {
        return false;
    }

    public Entity[] func_70021_al() {
        return this.body;
    }

    public float getMaximumHomeDistance() {
        return this.func_110174_bM();
    }
}
