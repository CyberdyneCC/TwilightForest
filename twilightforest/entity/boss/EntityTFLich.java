package twilightforest.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.EntityTFSwarmSpider;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFLich extends EntityMob implements IBossDisplayData {

    private static final int DATA_ISCLONE = 21;
    private static final int DATA_SHIELDSTRENGTH = 17;
    private static final int DATA_MINIONSLEFT = 18;
    private static final int DATA_BOSSHEALTH = 19;
    private static final int DATA_ATTACKTYPE = 20;
    EntityTFLich masterLich;
    private static final ItemStack[] heldItems = new ItemStack[] { new ItemStack(TFItems.scepterTwilight, 1), new ItemStack(TFItems.scepterZombie, 1), new ItemStack(Items.field_151010_B, 1)};
    public static final int MAX_SHADOW_CLONES = 2;
    public static final int INITIAL_SHIELD_STRENGTH = 5;
    public static final int MAX_ACTIVE_MINIONS = 3;
    public static final int INITIAL_MINIONS_TO_SUMMON = 9;
    public static final int MAX_HEALTH = 100;

    public EntityTFLich(World world) {
        super(world);
        this.func_70105_a(1.1F, 2.5F);
        this.setShadowClone(false);
        this.masterLich = null;
        this.field_70178_ae = true;
        this.setShieldStrength(5);
        this.setMinionsToSummon(9);
        this.field_70728_aV = 217;
    }

    public EntityTFLich(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    public EntityTFLich(World world, EntityTFLich otherLich) {
        this(world);
        this.setShadowClone(true);
        this.masterLich = otherLich;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(18, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(19, new Integer(100));
        this.field_70180_af.func_75682_a(20, Byte.valueOf((byte) 0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.800000011920929D);
    }

    public ItemStack func_70694_bm() {
        return EntityTFLich.heldItems[this.getPhase() - 1];
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.dropScepter();
        int totalDrops = this.field_70146_Z.nextInt(3 + par2) + 2;

        int i;

        for (i = 0; i < totalDrops; ++i) {
            this.dropGoldThing();
        }

        totalDrops = this.field_70146_Z.nextInt(4 + par2) + 1;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(Items.field_151079_bi, 1);
        }

        totalDrops = this.field_70146_Z.nextInt(5 + par2) + 5;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(Items.field_151103_aS, 1);
        }

        this.func_70099_a(new ItemStack(TFItems.trophy, 1, 2), 0.0F);
    }

    private void dropScepter() {
        int scepterType = this.field_70146_Z.nextInt(3);

        if (scepterType == 0) {
            this.func_70099_a(new ItemStack(TFItems.scepterZombie), 0.0F);
        } else if (scepterType == 1) {
            this.func_70099_a(new ItemStack(TFItems.scepterLifeDrain), 0.0F);
        } else {
            this.func_70099_a(new ItemStack(TFItems.scepterTwilight), 0.0F);
        }

    }

    private void dropGoldThing() {
        int thingType = this.field_70146_Z.nextInt(5);
        ItemStack goldThing;

        if (thingType == 0) {
            goldThing = new ItemStack(Items.field_151010_B);
        } else if (thingType == 1) {
            goldThing = new ItemStack(Items.field_151169_ag);
        } else if (thingType == 2) {
            goldThing = new ItemStack(Items.field_151171_ah);
        } else if (thingType == 3) {
            goldThing = new ItemStack(Items.field_151149_ai);
        } else {
            goldThing = new ItemStack(Items.field_151151_aj);
        }

        EnchantmentHelper.func_77504_a(this.field_70146_Z, goldThing, 10 + this.field_70146_Z.nextInt(30));
        this.func_70099_a(goldThing, 0.0F);
    }

    public void func_70110_aj() {}

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70058_J() {
        return false;
    }

    public boolean func_70090_H() {
        return false;
    }

    public int getPhase() {
        return !this.isShadowClone() && this.getShieldStrength() <= 0 ? (this.getMinionsToSummon() > 0 ? 2 : 3) : 1;
    }

    public void func_70636_d() {
        float angle = this.field_70761_aq * 3.141593F / 180.0F;
        double dx = this.field_70165_t + (double) MathHelper.func_76134_b(angle) * 0.65D;
        double dy = this.field_70163_u + (double) this.field_70131_O * 0.94D;
        double dz = this.field_70161_v + (double) MathHelper.func_76126_a(angle) * 0.65D;
        int factor = (80 - this.field_70724_aR) / 10;
        int particles = factor > 0 ? this.field_70146_Z.nextInt(factor) : 1;

        for (int j1 = 0; j1 < particles; ++j1) {
            float sparkle = 1.0F - ((float) this.field_70724_aR + 1.0F) / 60.0F;

            sparkle *= sparkle;
            float red = 0.37F * sparkle;
            float grn = 0.99F * sparkle;
            float blu = 0.89F * sparkle;

            if (this.getNextAttackType() != 0) {
                red = 0.99F * sparkle;
                grn = 0.47F * sparkle;
                blu = 0.0F * sparkle;
            }

            this.field_70170_p.func_72869_a("mobSpell", dx + this.field_70146_Z.nextGaussian() * 0.025D, dy + this.field_70146_Z.nextGaussian() * 0.025D, dz + this.field_70146_Z.nextGaussian() * 0.025D, (double) red, (double) grn, (double) blu);
        }

        if (this.isShadowClone()) {
            this.checkForMaster();
        }

        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(19, Integer.valueOf((int) this.func_110143_aJ()));
        }

        super.func_70636_d();
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float damage) {
        if (par1DamageSource.func_76355_l() == "inWall" && this.field_70789_a != null) {
            this.teleportToSightOfEntity(this.field_70789_a);
        }

        if (this.isShadowClone()) {
            this.field_70170_p.func_72956_a(this, "random.fizz", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            return false;
        } else {
            Entity prevTarget = this.field_70789_a;

            if (par1DamageSource.func_76346_g() instanceof EntityTFLich) {
                return false;
            } else if (this.getShieldStrength() > 0) {
                if (par1DamageSource.func_76363_c() && damage > 2.0F) {
                    if (this.getShieldStrength() > 0) {
                        this.setShieldStrength(this.getShieldStrength() - 1);
                        this.field_70170_p.func_72956_a(this, "random.break", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    }
                } else {
                    this.field_70170_p.func_72956_a(this, "random.break", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    if (par1DamageSource.func_76346_g() instanceof EntityPlayer) {
                        this.field_70789_a = par1DamageSource.func_76346_g();
                    }
                }

                return false;
            } else if (super.func_70097_a(par1DamageSource, damage)) {
                if (this.field_70789_a instanceof EntityTFLich) {
                    this.field_70789_a = prevTarget;
                }

                if (this.getPhase() < 3 || this.field_70146_Z.nextInt(4) == 0) {
                    this.teleportToSightOfEntity(this.field_70789_a);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    protected void func_70785_a(Entity targetedEntity, float f) {
        if (!this.isShadowClone() && this.field_70724_aR % 15 == 0) {
            this.popNearbyMob();
        }

        if (this.getPhase() == 1) {
            if (this.field_70724_aR == 60 && !this.field_70170_p.field_72995_K) {
                this.teleportToSightOfEntity(targetedEntity);
                if (!this.isShadowClone()) {
                    this.checkAndSpawnClones(targetedEntity);
                }
            }

            if (this.func_70685_l(targetedEntity) && this.field_70724_aR == 0 && f < 20.0F) {
                if (this.getNextAttackType() == 0) {
                    this.launchBoltAt(targetedEntity);
                } else {
                    this.launchBombAt(targetedEntity);
                }

                if (this.field_70146_Z.nextInt(3) > 0) {
                    this.setNextAttackType(0);
                } else {
                    this.setNextAttackType(1);
                }

                this.field_70724_aR = 100;
            }

            this.field_70787_b = true;
        }

        if (this.getPhase() == 2 && !this.isShadowClone()) {
            this.despawnClones();
            if (this.field_70724_aR % 15 == 0) {
                this.checkAndSpawnMinions(targetedEntity);
            }

            if (this.field_70724_aR == 0) {
                if (f < 2.0F) {
                    this.func_70652_k(targetedEntity);
                    this.field_70724_aR = 20;
                } else if (f < 20.0F && this.func_70685_l(targetedEntity)) {
                    if (this.getNextAttackType() == 0) {
                        this.launchBoltAt(targetedEntity);
                    } else {
                        this.launchBombAt(targetedEntity);
                    }

                    if (this.field_70146_Z.nextInt(2) > 0) {
                        this.setNextAttackType(0);
                    } else {
                        this.setNextAttackType(1);
                    }

                    this.field_70724_aR = 60;
                } else {
                    this.teleportToSightOfEntity(targetedEntity);
                    this.field_70724_aR = 20;
                }
            }

            this.field_70787_b = true;
        }

        if (this.getPhase() == 3 && this.field_70724_aR <= 0 && f < 2.0F && targetedEntity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && targetedEntity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(targetedEntity);
            this.field_70787_b = true;
        }

    }

    protected void launchBoltAt(Entity targetedEntity) {
        float bodyFacingAngle = this.field_70761_aq * 3.141593F / 180.0F;
        double sx = this.field_70165_t + (double) MathHelper.func_76134_b(bodyFacingAngle) * 0.65D;
        double sy = this.field_70163_u + (double) this.field_70131_O * 0.82D;
        double sz = this.field_70161_v + (double) MathHelper.func_76126_a(bodyFacingAngle) * 0.65D;
        double tx = targetedEntity.field_70165_t - sx;
        double ty = targetedEntity.field_70121_D.field_72338_b + (double) (targetedEntity.field_70131_O / 2.0F) - (this.field_70163_u + (double) (this.field_70131_O / 2.0F));
        double tz = targetedEntity.field_70161_v - sz;

        this.field_70170_p.func_72956_a(this, "mob.ghast.fireball", this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
        EntityTFLichBolt projectile = new EntityTFLichBolt(this.field_70170_p, this);

        projectile.func_70186_c(tx, ty, tz, projectile.func_70182_d(), 1.0F);
        projectile.func_70012_b(sx, sy, sz, this.field_70177_z, this.field_70125_A);
        this.field_70170_p.func_72838_d(projectile);
    }

    protected void launchBombAt(Entity targetedEntity) {
        float bodyFacingAngle = this.field_70761_aq * 3.141593F / 180.0F;
        double sx = this.field_70165_t + (double) MathHelper.func_76134_b(bodyFacingAngle) * 0.65D;
        double sy = this.field_70163_u + (double) this.field_70131_O * 0.82D;
        double sz = this.field_70161_v + (double) MathHelper.func_76126_a(bodyFacingAngle) * 0.65D;
        double tx = targetedEntity.field_70165_t - sx;
        double ty = targetedEntity.field_70121_D.field_72338_b + (double) (targetedEntity.field_70131_O / 2.0F) - (this.field_70163_u + (double) (this.field_70131_O / 2.0F));
        double tz = targetedEntity.field_70161_v - sz;

        this.field_70170_p.func_72956_a(this, "mob.ghast.fireball", this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
        EntityTFLichBomb projectile = new EntityTFLichBomb(this.field_70170_p, this);

        projectile.func_70186_c(tx, ty, tz, projectile.func_40077_c(), 1.0F);
        projectile.func_70012_b(sx, sy, sz, this.field_70177_z, this.field_70125_A);
        this.field_70170_p.func_72838_d(projectile);
    }

    protected void popNearbyMob() {
        List nearbyMobs = this.field_70170_p.func_72839_b(this, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        Iterator iterator = nearbyMobs.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLiving && this.canPop(entity) && this.func_70685_l(entity)) {
                EntityLiving mob = (EntityLiving) entity;

                if (!this.field_70170_p.field_72995_K) {
                    mob.func_70106_y();
                    mob.func_70656_aK();
                }

                this.makeRedMagicTrail(mob.field_70165_t, mob.field_70163_u + (double) mob.field_70131_O / 2.0D, mob.field_70161_v, this.field_70165_t, this.field_70163_u + (double) this.field_70131_O / 2.0D, this.field_70161_v);
                break;
            }
        }

    }

    protected boolean canPop(Entity nearby) {
        Class[] poppable = new Class[] { EntitySkeleton.class, EntityZombie.class, EntityEnderman.class, EntitySpider.class, EntityCreeper.class, EntityTFSwarmSpider.class};
        Class[] aclass = poppable;
        int i = poppable.length;

        for (int j = 0; j < i; ++j) {
            Class pop = aclass[j];

            if (nearby.getClass() == pop) {
                return true;
            }
        }

        return false;
    }

    protected void checkAndSpawnClones(Entity targetedEntity) {
        if (this.countMyClones() < 2) {
            this.spawnShadowClone(targetedEntity);
        }

    }

    protected int countMyClones() {
        List nearbyLiches = this.field_70170_p.func_72872_a(EntityTFLich.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        int count = 0;
        Iterator iterator = nearbyLiches.iterator();

        while (iterator.hasNext()) {
            EntityTFLich nearbyLich = (EntityTFLich) iterator.next();

            if (nearbyLich.isShadowClone() && nearbyLich.masterLich == this) {
                ++count;
            }
        }

        return count;
    }

    public boolean wantsNewClone(EntityTFLich clone) {
        return clone.isShadowClone() && this.countMyClones() < 2;
    }

    protected void spawnShadowClone(Entity targetedEntity) {
        Vec3 cloneSpot = this.findVecInLOSOf(targetedEntity);
        EntityTFLich newClone = new EntityTFLich(this.field_70170_p, this);

        newClone.func_70107_b(cloneSpot.field_72450_a, cloneSpot.field_72448_b, cloneSpot.field_72449_c);
        this.field_70170_p.func_72838_d(newClone);
        newClone.field_70789_a = targetedEntity;
        newClone.field_70724_aR = 60 + this.field_70146_Z.nextInt(3) - this.field_70146_Z.nextInt(3);
        this.makeTeleportTrail(this.field_70165_t, this.field_70163_u, this.field_70161_v, cloneSpot.field_72450_a, cloneSpot.field_72448_b, cloneSpot.field_72449_c);
    }

    protected void despawnClones() {
        List nearbyLiches = this.field_70170_p.func_72872_a(this.getClass(), AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        Iterator iterator = nearbyLiches.iterator();

        while (iterator.hasNext()) {
            EntityTFLich nearbyLich = (EntityTFLich) iterator.next();

            if (nearbyLich.isShadowClone()) {
                nearbyLich.field_70128_L = true;
            }
        }

    }

    protected void checkAndSpawnMinions(Entity targetedEntity) {
        if (!this.field_70170_p.field_72995_K && this.getMinionsToSummon() > 0) {
            int minions = this.countMyMinions();

            if (minions < 3) {
                this.spawnMinionAt((EntityLivingBase) targetedEntity);
                this.setMinionsToSummon(this.getMinionsToSummon() - 1);
            }
        }

    }

    protected int countMyMinions() {
        List nearbyMinons = this.field_70170_p.func_72872_a(EntityTFLichMinion.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        int count = 0;
        Iterator iterator = nearbyMinons.iterator();

        while (iterator.hasNext()) {
            EntityTFLichMinion nearbyMinon = (EntityTFLichMinion) iterator.next();

            if (nearbyMinon.master == this) {
                ++count;
            }
        }

        return count;
    }

    protected void spawnMinionAt(EntityLivingBase targetedEntity) {
        Vec3 minionSpot = this.findVecInLOSOf(targetedEntity);
        EntityTFLichMinion minion = new EntityTFLichMinion(this.field_70170_p, this);

        minion.func_70107_b(minionSpot.field_72450_a, minionSpot.field_72448_b, minionSpot.field_72449_c);
        this.field_70170_p.func_72838_d(minion);
        minion.func_70624_b(targetedEntity);
        minion.func_70656_aK();
        this.field_70170_p.func_72956_a(minion, "random.pop", 1.0F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        this.makeBlackMagicTrail(this.field_70165_t, this.field_70163_u + (double) this.func_70047_e(), this.field_70161_v, minionSpot.field_72450_a, minionSpot.field_72448_b + (double) minion.field_70131_O / 2.0D, minionSpot.field_72449_c);
    }

    public boolean wantsNewMinion(EntityTFLichMinion minion) {
        return this.countMyMinions() < 3;
    }

    protected void checkForMaster() {
        if (this.masterLich == null) {
            this.findNewMaster();
        }

        if (!this.field_70170_p.field_72995_K && (this.masterLich == null || this.masterLich.field_70128_L)) {
            this.field_70128_L = true;
        }

    }

    private void findNewMaster() {
        List nearbyLiches = this.field_70170_p.func_72872_a(EntityTFLich.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        Iterator iterator = nearbyLiches.iterator();

        while (iterator.hasNext()) {
            EntityTFLich nearbyLich = (EntityTFLich) iterator.next();

            if (!nearbyLich.isShadowClone() && nearbyLich.wantsNewClone(this)) {
                this.masterLich = nearbyLich;
                this.makeTeleportTrail(this.field_70165_t, this.field_70163_u, this.field_70161_v, nearbyLich.field_70165_t, nearbyLich.field_70163_u, nearbyLich.field_70161_v);
                this.func_70624_b(this.masterLich.func_70638_az());
                break;
            }
        }

    }

    protected void teleportToSightOfEntity(Entity entity) {
        Vec3 dest = this.findVecInLOSOf(entity);
        double srcX = this.field_70165_t;
        double srcY = this.field_70163_u;
        double srcZ = this.field_70161_v;

        if (dest != null) {
            this.teleportToNoChecks(dest.field_72450_a, dest.field_72448_b, dest.field_72449_c);
            this.func_70625_a(entity, 100.0F, 100.0F);
            this.field_70761_aq = this.field_70177_z;
            if (!this.func_70685_l(entity)) {
                this.teleportToNoChecks(srcX, srcY, srcZ);
            }
        }

    }

    protected Vec3 findVecInLOSOf(Entity targetEntity) {
        if (targetEntity == null) {
            return null;
        } else {
            double tx = 0.0D;
            double ty = 0.0D;
            double tz = 0.0D;
            byte tries = 100;

            for (int i = 0; i < tries; ++i) {
                tx = targetEntity.field_70165_t + this.field_70146_Z.nextGaussian() * 16.0D;
                ty = targetEntity.field_70163_u + this.field_70146_Z.nextGaussian() * 8.0D;
                tz = targetEntity.field_70161_v + this.field_70146_Z.nextGaussian() * 16.0D;
                boolean groundFlag = false;
                int bx = MathHelper.func_76128_c(tx);
                int by = MathHelper.func_76128_c(ty);
                int bz = MathHelper.func_76128_c(tz);

                while (!groundFlag && ty > 0.0D) {
                    Block halfWidth = this.field_70170_p.func_147439_a(bx, by - 1, bz);

                    if (halfWidth != Blocks.field_150350_a && halfWidth.func_149688_o().func_76220_a()) {
                        groundFlag = true;
                    } else {
                        --ty;
                        --by;
                    }
                }

                if (by != 0 && this.canEntitySee(targetEntity, tx, ty, tz)) {
                    float f = this.field_70130_N / 2.0F;
                    AxisAlignedBB destBox = AxisAlignedBB.func_72330_a(tx - (double) f, ty - (double) this.field_70129_M + (double) this.field_70139_V, tz - (double) f, tx + (double) f, ty - (double) this.field_70129_M + (double) this.field_70139_V + (double) this.field_70131_O, tz + (double) f);

                    if (this.field_70170_p.func_72945_a(this, destBox).size() <= 0 && !this.field_70170_p.func_72953_d(destBox)) {
                        break;
                    }
                }
            }

            return tries == 99 ? null : Vec3.func_72443_a(tx, ty, tz);
        }
    }

    protected boolean canEntitySee(Entity entity, double dx, double dy, double dz) {
        return this.field_70170_p.func_72933_a(Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u + (double) entity.func_70047_e(), entity.field_70161_v), Vec3.func_72443_a(dx, dy, dz)) == null;
    }

    protected boolean teleportToNoChecks(double destX, double destY, double destZ) {
        double srcX = this.field_70165_t;
        double srcY = this.field_70163_u;
        double srcZ = this.field_70161_v;

        this.func_70107_b(destX, destY, destZ);
        this.makeTeleportTrail(srcX, srcY, srcZ, destX, destY, destZ);
        this.field_70170_p.func_72908_a(srcX, srcY, srcZ, "mob.endermen.portal", 1.0F, 1.0F);
        this.field_70170_p.func_72956_a(this, "mob.endermen.portal", 1.0F, 1.0F);
        this.field_70703_bu = false;
        return true;
    }

    protected void makeTeleportTrail(double srcX, double srcY, double srcZ, double destX, double destY, double destZ) {
        short particles = 128;

        for (int i = 0; i < particles; ++i) {
            double trailFactor = (double) i / ((double) particles - 1.0D);
            float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            double tx = srcX + (destX - srcX) * trailFactor + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 2.0D;
            double ty = srcY + (destY - srcY) * trailFactor + this.field_70146_Z.nextDouble() * (double) this.field_70131_O;
            double tz = srcZ + (destZ - srcZ) * trailFactor + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 2.0D;

            this.field_70170_p.func_72869_a("spell", tx, ty, tz, (double) f, (double) f1, (double) f2);
        }

    }

    protected void makeRedMagicTrail(double srcX, double srcY, double srcZ, double destX, double destY, double destZ) {
        byte particles = 32;

        for (int i = 0; i < particles; ++i) {
            double trailFactor = (double) i / ((double) particles - 1.0D);
            float f = 1.0F;
            float f1 = 0.5F;
            float f2 = 0.5F;
            double tx = srcX + (destX - srcX) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;
            double ty = srcY + (destY - srcY) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;
            double tz = srcZ + (destZ - srcZ) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;

            this.field_70170_p.func_72869_a("mobSpell", tx, ty, tz, (double) f, (double) f1, (double) f2);
        }

    }

    protected void makeBlackMagicTrail(double srcX, double srcY, double srcZ, double destX, double destY, double destZ) {
        byte particles = 32;

        for (int i = 0; i < particles; ++i) {
            double trailFactor = (double) i / ((double) particles - 1.0D);
            float f = 0.2F;
            float f1 = 0.2F;
            float f2 = 0.2F;
            double tx = srcX + (destX - srcX) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;
            double ty = srcY + (destY - srcY) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;
            double tz = srcZ + (destZ - srcZ) * trailFactor + this.field_70146_Z.nextGaussian() * 0.005D;

            this.field_70170_p.func_72869_a("mobSpell", tx, ty, tz, (double) f, (double) f1, (double) f2);
        }

    }

    public boolean isShadowClone() {
        return (this.field_70180_af.func_75683_a(21) & 2) != 0;
    }

    public void setShadowClone(boolean par1) {
        byte b0 = this.field_70180_af.func_75683_a(21);

        if (par1) {
            this.field_70180_af.func_75692_b(21, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.field_70180_af.func_75692_b(21, Byte.valueOf((byte) (b0 & -3)));
        }

    }

    public byte getShieldStrength() {
        return this.field_70180_af.func_75683_a(17);
    }

    public void setShieldStrength(int shieldStrength) {
        this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) shieldStrength));
    }

    public byte getMinionsToSummon() {
        return this.field_70180_af.func_75683_a(18);
    }

    public void setMinionsToSummon(int minionsToSummon) {
        this.field_70180_af.func_75692_b(18, Byte.valueOf((byte) minionsToSummon));
    }

    public byte getNextAttackType() {
        return this.field_70180_af.func_75683_a(20);
    }

    public void setNextAttackType(int attackType) {
        this.field_70180_af.func_75692_b(20, Byte.valueOf((byte) attackType));
    }

    protected String func_70639_aQ() {
        return "mob.blaze.breathe";
    }

    protected String func_70621_aR() {
        return "mob.blaze.hit";
    }

    protected String func_70673_aS() {
        return "mob.blaze.death";
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74757_a("ShadowClone", this.isShadowClone());
        nbttagcompound.func_74774_a("ShieldStrength", this.getShieldStrength());
        nbttagcompound.func_74774_a("MinionsToSummon", this.getMinionsToSummon());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setShadowClone(nbttagcompound.func_74767_n("ShadowClone"));
        this.setShieldStrength(nbttagcompound.func_74771_c("ShieldStrength"));
        this.setMinionsToSummon(nbttagcompound.func_74771_c("MinionsToSummon"));
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightKillLich);
        }

        if (!this.field_70170_p.field_72995_K && !this.isShadowClone()) {
            int dx = MathHelper.func_76128_c(this.field_70165_t);
            int dy = MathHelper.func_76128_c(this.field_70163_u);
            int dz = MathHelper.func_76128_c(this.field_70161_v);

            if (this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
                ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
                TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, dz, this.field_70170_p);

                if (nearbyFeature == TFFeature.lichTower) {
                    chunkProvider.setStructureConquered(dx, dy, dz, true);
                }
            }
        }

    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.UNDEAD;
    }
}
