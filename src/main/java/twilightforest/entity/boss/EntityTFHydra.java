package twilightforest.entity.boss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFHydra extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {

    private static int TICKS_BEFORE_HEALING = 1000;
    private static int HEAD_RESPAWN_TICKS = 100;
    private static int HEAD_MAX_DAMAGE = 120;
    private static float ARMOR_MULTIPLIER = 8.0F;
    private static int MAX_HEALTH = 360;
    private static float HEADS_ACTIVITY_FACTOR = 0.3F;
    private static int SECONDARY_FLAME_CHANCE = 10;
    private static int SECONDARY_MORTAR_CHANCE = 16;
    private static final int DATA_SPAWNHEADS = 17;
    private static final int DATA_BOSSHEALTH = 18;
    public Entity[] partArray;
    public EntityDragonPart body;
    public HydraHeadContainer[] hc;
    public int numHeads;
    public EntityDragonPart leftLeg;
    public EntityDragonPart rightLeg;
    public EntityDragonPart tail;
    Entity field_70776_bF;
    public int ticksSinceDamaged;

    public EntityTFHydra(World world) {
        super(world);
        this.numHeads = 7;
        this.field_70776_bF = null;
        this.ticksSinceDamaged = 0;
        this.partArray = new Entity[] { this.body = new EntityDragonPart(this, "body", 4.0F, 4.0F), this.leftLeg = new EntityDragonPart(this, "leg", 2.0F, 3.0F), this.rightLeg = new EntityDragonPart(this, "leg", 2.0F, 3.0F), this.tail = new EntityDragonPart(this, "tail", 4.0F, 4.0F)};
        this.hc = new HydraHeadContainer[this.numHeads];

        for (int partList = 0; partList < this.numHeads; ++partList) {
            this.hc[partList] = new HydraHeadContainer(this, partList, partList < 3);
        }

        ArrayList arraylist = new ArrayList();

        Collections.addAll(arraylist, this.partArray);

        for (int i = 0; i < this.numHeads; ++i) {
            Collections.addAll(arraylist, this.hc[i].getNeckArray());
        }

        this.partArray = (Entity[]) arraylist.toArray(this.partArray);
        this.func_70105_a(16.0F, 12.0F);
        this.field_70158_ak = true;
        this.field_70178_ae = true;
        this.field_70728_aV = 511;
        this.setSpawnHeads(true);
    }

    public EntityTFHydra(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double) EntityTFHydra.MAX_HEALTH);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
    }

    public void func_70636_d() {
        int angle;

        if ((this.hc[0].headEntity == null || this.hc[1].headEntity == null || this.hc[2].headEntity == null) && this.shouldSpawnHeads() && !this.field_70170_p.field_72995_K) {
            for (angle = 0; angle < this.numHeads; ++angle) {
                this.hc[angle].headEntity = new EntityTFHydraHead(this, "head" + angle, 3.0F, 3.0F);
                this.hc[angle].headEntity.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.field_70170_p.func_72838_d(this.hc[angle].headEntity);
            }

            this.setSpawnHeads(false);
        }

        this.body.func_70071_h_();

        for (angle = 0; angle < this.numHeads; ++angle) {
            this.hc[angle].onUpdate();
        }

        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(18, Integer.valueOf((int) this.func_110143_aJ()));
        } else if (this.func_110143_aJ() > 0.0F) {
            this.field_70725_aQ = 0;
        }

        if (this.field_70737_aN > 0) {
            for (angle = 0; angle < this.numHeads; ++angle) {
                this.hc[angle].setHurtTime(this.field_70737_aN);
            }
        }

        ++this.ticksSinceDamaged;
        if (!this.field_70170_p.field_72995_K && this.ticksSinceDamaged > EntityTFHydra.TICKS_BEFORE_HEALING && this.ticksSinceDamaged % 5 == 0) {
            this.func_70691_i(1.0F);
        }

        this.setDifficultyVariables();
        if (this.field_70716_bi > 0) {
            double d0 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double) this.field_70716_bi;
            double d1 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double) this.field_70716_bi;
            double d2 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double) this.field_70716_bi;
            double d3 = MathHelper.func_76138_g(this.field_70712_bm - (double) this.field_70177_z);

            this.field_70177_z = (float) ((double) this.field_70177_z + d3 / (double) this.field_70716_bi);
            this.field_70125_A = (float) ((double) this.field_70125_A + (this.field_70705_bn - (double) this.field_70125_A) / (double) this.field_70716_bi);
            --this.field_70716_bi;
            this.func_70107_b(d0, d1, d2);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
        }

        if (Math.abs(this.field_70159_w) < 0.005D) {
            this.field_70159_w = 0.0D;
        }

        if (Math.abs(this.field_70181_x) < 0.005D) {
            this.field_70181_x = 0.0D;
        }

        if (Math.abs(this.field_70179_y) < 0.005D) {
            this.field_70179_y = 0.0D;
        }

        this.field_70170_p.field_72984_F.func_76320_a("ai");
        if (this.func_70610_aX()) {
            this.field_70703_bu = false;
            this.field_70702_br = 0.0F;
            this.field_70701_bs = 0.0F;
            this.field_70704_bt = 0.0F;
        } else if (this.func_70613_aW()) {
            this.field_70170_p.field_72984_F.func_76320_a("oldAi");
            this.func_70626_be();
            this.field_70170_p.field_72984_F.func_76319_b();
            this.field_70759_as = this.field_70177_z;
        }

        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("jump");
        if (this.field_70703_bu) {
            if (!this.func_70090_H() && !this.func_70058_J()) {
                if (this.field_70122_E) {
                    this.func_70664_aZ();
                }
            } else {
                this.field_70181_x += 0.03999999910593033D;
            }
        }

        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("travel");
        this.field_70702_br *= 0.98F;
        this.field_70701_bs *= 0.98F;
        this.field_70704_bt *= 0.9F;
        this.func_70612_e(this.field_70702_br, this.field_70701_bs);
        this.field_70170_p.field_72984_F.func_76319_b();
        this.body.field_70130_N = this.body.field_70131_O = 6.0F;
        this.tail.field_70130_N = 6.0F;
        this.tail.field_70131_O = 2.0F;
        float f = (this.field_70761_aq + 180.0F) * 3.141593F / 180.0F;
        double dx = this.field_70165_t - (double) MathHelper.func_76126_a(f) * 3.0D;
        double dy = this.field_70163_u + 0.1D;
        double dz = this.field_70161_v + (double) MathHelper.func_76134_b(f) * 3.0D;

        this.body.func_70107_b(dx, dy, dz);
        dx = this.field_70165_t - (double) MathHelper.func_76126_a(f) * 10.5D;
        dy = this.field_70163_u + 0.1D;
        dz = this.field_70161_v + (double) MathHelper.func_76134_b(f) * 10.5D;
        this.tail.func_70107_b(dx, dy, dz);
        this.field_70170_p.field_72984_F.func_76320_a("push");
        if (!this.field_70170_p.field_72995_K && this.field_70737_aN == 0) {
            this.collideWithEntities(this.field_70170_p.func_72839_b(this, this.body.field_70121_D), this.body);
            this.collideWithEntities(this.field_70170_p.func_72839_b(this, this.tail.field_70121_D), this.tail);
        }

        this.field_70170_p.field_72984_F.func_76319_b();
        if (!this.field_70170_p.field_72995_K) {
            this.destroyBlocksInAABB(this.body.field_70121_D);
            this.destroyBlocksInAABB(this.tail.field_70121_D);

            for (int i = 0; i < this.numHeads; ++i) {
                if (this.hc[i].headEntity != null && this.hc[i].isActive()) {
                    this.destroyBlocksInAABB(this.hc[i].headEntity.field_70121_D);
                }
            }

            if (this.field_70173_aa % 20 == 0 && this.isUnsteadySurfaceBeneath()) {
                this.destroyBlocksInAABB(this.field_70121_D.func_72317_d(0.0D, -1.0D, 0.0D));
            }
        }

    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(18, new Integer(EntityTFHydra.MAX_HEALTH));
    }

    public boolean shouldSpawnHeads() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setSpawnHeads(boolean flag) {
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 127));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 0));
        }

    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74757_a("SpawnHeads", this.shouldSpawnHeads());
        nbttagcompound.func_74774_a("NumHeads", (byte) this.countActiveHeads());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setSpawnHeads(nbttagcompound.func_74767_n("SpawnHeads"));
        this.activateNumberOfHeads(nbttagcompound.func_74771_c("NumHeads"));
    }

    protected void func_70626_be() {
        ++this.field_70708_bq;
        this.func_70623_bb();
        this.field_70702_br = 0.0F;
        this.field_70701_bs = 0.0F;
        float f = 48.0F;

        int flag;

        for (flag = 0; flag < this.numHeads; ++flag) {
            if (this.hc[flag].isActive() && this.hc[flag].getDamageTaken() > EntityTFHydra.HEAD_MAX_DAMAGE) {
                this.hc[flag].setNextState(11);
                this.hc[flag].endCurrentAction();
                this.hc[flag].setRespawnCounter(EntityTFHydra.HEAD_RESPAWN_TICKS);
                int flag1 = this.getRandomDeadHead();

                if (flag1 != -1) {
                    this.hc[flag1].setRespawnCounter(EntityTFHydra.HEAD_RESPAWN_TICKS);
                }
            }
        }

        if (this.field_70146_Z.nextFloat() < 0.7F) {
            EntityPlayer entityplayer = this.field_70170_p.func_72856_b(this, (double) f);

            if (entityplayer != null) {
                this.field_70776_bF = entityplayer;
                this.field_70700_bx = 100 + this.field_70146_Z.nextInt(20);
            } else {
                this.field_70704_bt = (this.field_70146_Z.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.field_70776_bF != null) {
            this.func_70625_a(this.field_70776_bF, 10.0F, (float) this.func_70646_bf());

            for (flag = 0; flag < this.numHeads; ++flag) {
                if (!this.isHeadAttacking(this.hc[flag]) && !this.hc[flag].isSecondaryAttacking) {
                    this.hc[flag].setTargetEntity(this.field_70776_bF);
                }
            }

            if (this.field_70776_bF.func_70089_S()) {
                float f = this.field_70776_bF.func_70032_d(this);

                if (this.func_70685_l(this.field_70776_bF)) {
                    this.attackEntity(this.field_70776_bF, f);
                }
            }

            if (this.field_70700_bx-- <= 0 || this.field_70776_bF.field_70128_L || this.field_70776_bF.func_70068_e(this) > (double) (f * f)) {
                this.field_70776_bF = null;
            }
        } else {
            if (this.field_70146_Z.nextFloat() < 0.05F) {
                this.field_70704_bt = (this.field_70146_Z.nextFloat() - 0.5F) * 20.0F;
            }

            this.field_70177_z += this.field_70704_bt;
            this.field_70125_A = this.field_70698_bv;

            for (flag = 0; flag < this.numHeads; ++flag) {
                if (this.hc[flag].currentState == 0) {
                    this.hc[flag].setTargetEntity((Entity) null);
                }
            }
        }

        this.secondaryAttacks();
        boolean flag = this.func_70090_H();
        boolean flag1 = this.func_70058_J();

        if (flag || flag1) {
            this.field_70703_bu = this.field_70146_Z.nextFloat() < 0.8F;
        }

    }

    private void setDifficultyVariables() {
        if (this.field_70170_p.field_73013_u != EnumDifficulty.HARD) {
            EntityTFHydra.HEADS_ACTIVITY_FACTOR = 0.3F;
        } else {
            EntityTFHydra.HEADS_ACTIVITY_FACTOR = 0.5F;
        }

    }

    private int getRandomDeadHead() {
        for (int i = 0; i < this.numHeads; ++i) {
            if (this.hc[i].currentState == 12 && this.hc[i].respawnCounter == -1) {
                return i;
            }
        }

        return -1;
    }

    private void activateNumberOfHeads(int howMany) {
        int moreHeads = howMany - this.countActiveHeads();

        for (int i = 0; i < moreHeads; ++i) {
            int otherHead = this.getRandomDeadHead();

            if (otherHead != -1) {
                this.hc[otherHead].currentState = 0;
                this.hc[otherHead].setNextState(0);
                this.hc[otherHead].endCurrentAction();
            }
        }

    }

    private void attackEntity(Entity target, float distance) {
        byte BITE_CHANCE = 10;
        byte FLAME_CHANCE = 100;
        short MORTAR_CHANCE = 160;
        boolean targetAbove = target.field_70121_D.field_72338_b > this.field_70121_D.field_72337_e;

        int i;

        for (i = 0; i < 3; ++i) {
            if (this.hc[i].currentState == 0 && !this.areTooManyHeadsAttacking(target, i)) {
                if (distance > 4.0F && distance < 10.0F && this.field_70146_Z.nextInt(BITE_CHANCE) == 0 && this.countActiveHeads() > 2 && !this.areOtherHeadsBiting(target, i)) {
                    this.hc[i].setNextState(1);
                } else if (distance > 0.0F && distance < 20.0F && this.field_70146_Z.nextInt(FLAME_CHANCE) == 0) {
                    this.hc[i].setNextState(5);
                } else if (distance > 8.0F && distance < 32.0F && !targetAbove && this.field_70146_Z.nextInt(MORTAR_CHANCE) == 0) {
                    this.hc[i].setNextState(8);
                }
            }
        }

        for (i = 3; i < this.numHeads; ++i) {
            if (this.hc[i].currentState == 0 && !this.areTooManyHeadsAttacking(target, i)) {
                if (distance > 0.0F && distance < 20.0F && this.field_70146_Z.nextInt(FLAME_CHANCE) == 0) {
                    this.hc[i].setNextState(5);
                } else if (distance > 8.0F && distance < 32.0F && !targetAbove && this.field_70146_Z.nextInt(MORTAR_CHANCE) == 0) {
                    this.hc[i].setNextState(8);
                }
            }
        }

    }

    protected boolean areTooManyHeadsAttacking(Entity target, int testHead) {
        int otherAttacks = 0;

        for (int i = 0; i < this.numHeads; ++i) {
            if (i != testHead && this.isHeadAttacking(this.hc[i])) {
                ++otherAttacks;
                if (this.isHeadBiting(this.hc[i])) {
                    otherAttacks += 2;
                }
            }
        }

        return (float) otherAttacks >= 1.0F + (float) this.countActiveHeads() * EntityTFHydra.HEADS_ACTIVITY_FACTOR;
    }

    public int countActiveHeads() {
        int count = 0;

        for (int i = 0; i < this.numHeads; ++i) {
            if (this.hc[i].isActive()) {
                ++count;
            }
        }

        return count;
    }

    private boolean isHeadAttacking(HydraHeadContainer head) {
        return head.currentState == 1 || head.currentState == 2 || head.currentState == 3 || head.currentState == 5 || head.currentState == 6 || head.currentState == 8 || head.currentState == 9;
    }

    protected boolean areOtherHeadsBiting(Entity target, int testHead) {
        for (int i = 0; i < this.numHeads; ++i) {
            if (i != testHead && this.isHeadBiting(this.hc[i])) {
                return true;
            }
        }

        return false;
    }

    protected boolean isHeadBiting(HydraHeadContainer head) {
        return head.currentState == 1 || head.currentState == 2 || head.currentState == 3 || head.nextState == 1;
    }

    private void secondaryAttacks() {
        for (int secondaryTarget = 0; secondaryTarget < this.numHeads; ++secondaryTarget) {
            if (this.hc[secondaryTarget].headEntity == null) {
                return;
            }
        }

        EntityLivingBase entitylivingbase = this.findSecondaryTarget(20.0D);

        if (entitylivingbase != null) {
            float distance = entitylivingbase.func_70032_d(this);

            for (int i = 1; i < this.numHeads; ++i) {
                if (this.hc[i].isActive() && this.hc[i].currentState == 0 && this.isTargetOnThisSide(i, entitylivingbase)) {
                    if (distance > 0.0F && distance < 20.0F && this.field_70146_Z.nextInt(EntityTFHydra.SECONDARY_FLAME_CHANCE) == 0) {
                        this.hc[i].setTargetEntity(entitylivingbase);
                        this.hc[i].isSecondaryAttacking = true;
                        this.hc[i].setNextState(5);
                    } else if (distance > 8.0F && distance < 32.0F && this.field_70146_Z.nextInt(EntityTFHydra.SECONDARY_MORTAR_CHANCE) == 0) {
                        this.hc[i].setTargetEntity(entitylivingbase);
                        this.hc[i].isSecondaryAttacking = true;
                        this.hc[i].setNextState(8);
                    }
                }
            }
        }

    }

    public boolean isTargetOnThisSide(int headNum, Entity target) {
        double headDist = this.distanceSqXZ(this.hc[headNum].headEntity, target);
        double middleDist = this.distanceSqXZ(this, target);

        return headDist < middleDist;
    }

    private double distanceSqXZ(Entity headEntity, Entity target) {
        double distX = headEntity.field_70165_t - target.field_70165_t;
        double distZ = headEntity.field_70161_v - target.field_70161_v;

        return distX * distX + distZ * distZ;
    }

    public EntityLivingBase findSecondaryTarget(double range) {
        double closestRange = -1.0D;
        EntityLivingBase closestEntity = null;
        List nearbyEntities = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(range, range, range));
        Iterator iterator = nearbyEntities.iterator();

        while (iterator.hasNext()) {
            EntityLivingBase nearbyLiving = (EntityLivingBase) iterator.next();

            if (!(nearbyLiving instanceof EntityTFHydra) && !(nearbyLiving instanceof EntityTFHydraPart) && nearbyLiving != this.field_70776_bF && !this.isAnyHeadTargeting(nearbyLiving) && this.func_70685_l(nearbyLiving)) {
                double curDist = nearbyLiving.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);

                if ((range < 0.0D || curDist < range * range) && (closestRange == -1.0D || curDist < closestRange)) {
                    closestRange = curDist;
                    closestEntity = nearbyLiving;
                }
            }
        }

        return closestEntity;
    }

    boolean isAnyHeadTargeting(Entity targetEntity) {
        for (int i = 0; i < this.numHeads; ++i) {
            if (this.hc[i].targetEntity != null && this.hc[i].targetEntity.equals(targetEntity)) {
                return true;
            }
        }

        return false;
    }

    private void collideWithEntities(List par1List, Entity part) {
        double pushPower = 4.0D;
        double centerX = (part.field_70121_D.field_72340_a + part.field_70121_D.field_72336_d) / 2.0D;
        double centerY = (part.field_70121_D.field_72339_c + part.field_70121_D.field_72334_f) / 2.0D;
        Iterator iterator = par1List.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLivingBase) {
                double distX = entity.field_70165_t - centerX;
                double distZ = entity.field_70161_v - centerY;
                double sqDist = distX * distX + distZ * distZ;

                entity.func_70024_g(distX / sqDist * pushPower, 0.20000000298023224D, distZ / sqDist * pushPower);
            }
        }

    }

    private boolean isUnsteadySurfaceBeneath() {
        int minX = MathHelper.func_76128_c(this.field_70121_D.field_72340_a);
        int minZ = MathHelper.func_76128_c(this.field_70121_D.field_72339_c);
        int maxX = MathHelper.func_76128_c(this.field_70121_D.field_72336_d);
        int maxZ = MathHelper.func_76128_c(this.field_70121_D.field_72334_f);
        int minY = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int solid = 0;
        int total = 0;
        int dy = minY - 1;

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dz = minZ; dz <= maxZ; ++dz) {
                ++total;
                if (this.field_70170_p.func_147439_a(dx, dy, dz).func_149688_o().func_76220_a()) {
                    ++solid;
                }
            }
        }

        return (float) solid / (float) total < 0.6F;
    }

    private boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB) {
        int minX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72340_a);
        int minY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72338_b);
        int minZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72339_c);
        int maxX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72336_d);
        int maxY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72337_e);
        int maxZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72334_f);
        boolean wasBlocked = false;

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dy = minY; dy <= maxY; ++dy) {
                for (int dz = minZ; dz <= maxZ; ++dz) {
                    Block currentID = this.field_70170_p.func_147439_a(dx, dy, dz);

                    if (currentID != Blocks.field_150350_a) {
                        int currentMeta = this.field_70170_p.func_72805_g(dx, dy, dz);

                        if (currentID != Blocks.field_150343_Z && currentID != Blocks.field_150377_bs && currentID != Blocks.field_150357_h) {
                            this.field_70170_p.func_147465_d(dx, dy, dz, Blocks.field_150350_a, 0, 2);
                            this.field_70170_p.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                        } else {
                            wasBlocked = true;
                        }
                    }
                }
            }
        }

        return wasBlocked;
    }

    public int func_70646_bf() {
        return 500;
    }

    public boolean func_70965_a(EntityDragonPart dragonpart, DamageSource damagesource, float i) {
        double range = this.calculateRange(damagesource);

        return range > 400.0D ? false : this.superAttackFrom(damagesource, (float) Math.round(i / 8.0F));
    }

    protected boolean superAttackFrom(DamageSource par1DamageSource, float par2) {
        return super.func_70097_a(par1DamageSource, par2);
    }

    public boolean attackEntityFromPart(EntityTFHydraPart part, DamageSource damagesource, float damageAmount) {
        if (!this.field_70170_p.field_72995_K && damagesource == DamageSource.field_76368_d && part.func_70046_E() != null) {
            this.destroyBlocksInAABB(part.func_70046_E());
        }

        HydraHeadContainer headCon = null;

        for (int range = 0; range < this.numHeads; ++range) {
            if (this.hc[range].headEntity == part) {
                headCon = this.hc[range];
            }
        }

        double d0 = this.calculateRange(damagesource);

        if (d0 > 400.0D) {
            return false;
        } else if (headCon != null && !headCon.isActive()) {
            return false;
        } else {
            boolean tookDamage;

            if (headCon != null && (double) headCon.getCurrentMouthOpen() > 0.5D) {
                tookDamage = this.superAttackFrom(damagesource, damageAmount);
                headCon.addDamage(damageAmount);
            } else {
                int armoredDamage = Math.round(damageAmount / EntityTFHydra.ARMOR_MULTIPLIER);

                tookDamage = this.superAttackFrom(damagesource, (float) armoredDamage);
                if (headCon != null) {
                    headCon.addDamage((float) armoredDamage);
                }
            }

            if (tookDamage) {
                this.ticksSinceDamaged = 0;
            }

            return tookDamage;
        }
    }

    protected double calculateRange(DamageSource damagesource) {
        double range = -1.0D;

        if (damagesource.func_76364_f() != null) {
            range = this.func_70068_e(damagesource.func_76364_f());
        }

        if (damagesource.func_76346_g() != null) {
            range = this.func_70068_e(damagesource.func_76346_g());
        }

        return range;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return false;
    }

    public Entity[] func_70021_al() {
        return this.partArray;
    }

    public boolean func_70067_L() {
        return false;
    }

    public boolean func_70104_M() {
        return false;
    }

    public void func_70653_a(Entity entity, float i, double d, double d1) {}

    protected String func_70639_aQ() {
        return "TwilightForest:mob.hydra.growl";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.hydra.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.hydra.death";
    }

    protected float func_70599_aP() {
        return 2.0F;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightKillHydra);
        }

        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
            int dx = MathHelper.func_76128_c(this.field_70165_t);
            int dy = MathHelper.func_76128_c(this.field_70163_u);
            int dz = MathHelper.func_76128_c(this.field_70161_v);
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
            TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, dz, this.field_70170_p);

            if (nearbyFeature == TFFeature.hydraLair) {
                chunkProvider.setStructureConquered(dx, dy, dz, true);
            }
        }

    }

    protected void func_70628_a(boolean par1, int par2) {
        int totalDrops = this.field_70146_Z.nextInt(3 + par2) + 5;

        int i;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(TFItems.hydraChop, 5);
        }

        totalDrops = this.field_70146_Z.nextInt(4 + par2) + 7;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(TFItems.fieryBlood, 1);
        }

        this.func_145779_a(TFItems.trophy, 1);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70027_ad() {
        return false;
    }

    protected void func_70609_aI() {
        ++this.field_70725_aQ;
        int i;

        if (this.field_70725_aQ == 1) {
            for (i = 0; i < this.numHeads; ++i) {
                this.hc[i].setRespawnCounter(-1);
                if (this.hc[i].isActive()) {
                    this.hc[i].setNextState(0);
                    this.hc[i].endCurrentAction();
                    this.hc[i].setHurtTime(200);
                }
            }
        }

        if (this.field_70725_aQ <= 140 && this.field_70725_aQ % 20 == 0) {
            i = this.field_70725_aQ / 20 - 1;
            if (this.hc[i].isActive()) {
                this.hc[i].setNextState(11);
                this.hc[i].endCurrentAction();
            }
        }

        if (this.field_70725_aQ == 200) {
            if (!this.field_70170_p.field_72995_K && (this.field_70718_bc > 0 || this.func_70684_aJ()) && !this.func_70631_g_()) {
                i = this.func_70693_a(this.field_70717_bb);

                while (i > 0) {
                    int j = EntityXPOrb.func_70527_a(i);

                    i -= j;
                    this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                }
            }

            this.func_70106_y();
        }

        for (i = 0; i < 20; ++i) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
            String particle = this.field_70146_Z.nextInt(2) == 0 ? "largeexplode" : "explode";

            this.field_70170_p.func_72869_a(particle, this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.body.field_70130_N * 2.0F) - (double) this.body.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.body.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.body.field_70130_N * 2.0F) - (double) this.body.field_70130_N, d0, d1, d2);
        }

    }

    public World func_82194_d() {
        return this.field_70170_p;
    }
}
