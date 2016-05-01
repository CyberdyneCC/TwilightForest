package twilightforest.entity.boss;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.TFTreasure;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMiniGhast;
import twilightforest.entity.EntityTFTowerGhast;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFUrGhast extends EntityTFTowerGhast implements IBossDisplayData {

    private static final int DATA_TANTRUM = 18;
    private static final int HOVER_ALTITUDE = 20;
    public double courseX;
    public double courseY;
    public double courseZ;
    ArrayList trapLocations;
    ArrayList travelCoords;
    int currentTravelCoordIndex;
    int travelPathRepetitions;
    int desiredRepetitions;
    int nextTantrumCry;
    float damageUntilNextPhase;
    boolean noTrapMode;

    public EntityTFUrGhast(World par1World) {
        super(par1World);
        this.func_70105_a(14.0F, 18.0F);
        this.aggroRange = 128.0F;
        this.wanderFactor = 32.0F;
        this.field_70145_X = true;
        this.trapLocations = new ArrayList();
        this.travelCoords = new ArrayList();
        this.setInTantrum(false);
        this.damageUntilNextPhase = 45.0F;
        this.field_70728_aV = 317;
        this.noTrapMode = false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(250.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, Byte.valueOf((byte) 0));
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70725_aQ > 0) {
            for (int k = 0; k < 5; ++k) {
                double d = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                String explosionType = this.field_70146_Z.nextBoolean() ? "hugeexplosion" : "explode";

                this.field_70170_p.func_72869_a(explosionType, this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d, d1, d2);
            }
        }

    }

    public boolean func_70097_a(DamageSource source, float damage) {
        if (source == DamageSource.field_76368_d) {
            return false;
        } else {
            boolean attackSuccessful = false;

            if (this.isInTantrum()) {
                damage /= 4.0F;
            }

            if ("fireball".equals(source.func_76355_l()) && source.func_76346_g() instanceof EntityPlayer) {
                attackSuccessful = super.func_70097_a(DamageSource.func_76356_a(source.func_76364_f(), source.func_76346_g()), damage);
            } else {
                attackSuccessful = super.func_70097_a(source, damage);
            }

            if (!this.field_70170_p.field_72995_K) {
                if (this.field_70737_aN == this.field_70738_aO) {
                    this.damageUntilNextPhase -= this.getLastDamage();
                    FMLLog.info("[Urghast] Attack successful, %f damage until phase switch.", new Object[] { Float.valueOf(this.damageUntilNextPhase)});
                    if (this.damageUntilNextPhase <= 0.0F) {
                        this.switchPhase();
                    }
                } else {
                    FMLLog.info("[Urghast] Attack fail with %s type attack for %f damage", new Object[] { source.field_76373_n, Float.valueOf(damage)});
                }
            }

            return attackSuccessful;
        }
    }

    private float getLastDamage() {
        return this.field_70735_aL - this.func_110143_aJ();
    }

    private void switchPhase() {
        if (this.isInTantrum()) {
            this.stopTantrum();
        } else {
            this.startTantrum();
        }

        this.damageUntilNextPhase = 48.0F;
    }

    protected void startTantrum() {
        this.setInTantrum(true);
        short rainTime = 6000;
        WorldInfo worldInfo = MinecraftServer.func_71276_C().field_71305_c[0].func_72912_H();

        worldInfo.func_76084_b(true);
        worldInfo.func_76069_a(true);
        worldInfo.func_76080_g(rainTime);
        worldInfo.func_76090_f(rainTime);
        this.spawnGhastsAtTraps();
    }

    protected void spawnGhastsAtTraps() {
        ArrayList ghastSpawns = new ArrayList(this.trapLocations);
        int numSpawns = Math.min(2, ghastSpawns.size());

        for (int i = 0; i < numSpawns; ++i) {
            int index = this.field_70146_Z.nextInt(ghastSpawns.size());
            ChunkCoordinates spawnCoord = (ChunkCoordinates) ghastSpawns.get(index);

            ghastSpawns.remove(index);
            this.spawnMinionGhastsAt(spawnCoord.field_71574_a, spawnCoord.field_71572_b, spawnCoord.field_71573_c);
        }

    }

    public void stopTantrum() {
        this.setInTantrum(false);
    }

    private void spawnMinionGhastsAt(int x, int y, int z) {
        byte tries = 24;
        int spawns = 0;
        byte maxSpawns = 6;
        byte rangeXZ = 4;
        byte rangeY = 8;

        this.field_70170_p.func_72942_c(new EntityLightningBolt(this.field_70170_p, (double) x, (double) (y + 4), (double) z));

        for (int i = 0; i < tries; ++i) {
            EntityTFMiniGhast minion = new EntityTFMiniGhast(this.field_70170_p);
            double sx = (double) x + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * (double) rangeXZ;
            double sy = (double) y + this.field_70146_Z.nextDouble() * (double) rangeY;
            double sz = (double) z + (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * (double) rangeXZ;

            minion.func_70012_b(sx, sy, sz, this.field_70170_p.field_73012_v.nextFloat() * 360.0F, 0.0F);
            minion.makeBossMinion();
            if (minion.func_70601_bi()) {
                this.field_70170_p.func_72838_d(minion);
                minion.func_70656_aK();
            }

            ++spawns;
            if (spawns >= maxSpawns) {
                break;
            }
        }

    }

    protected void func_70626_be() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        }

        this.func_70623_bb();
        List nearbyGhasts = this.field_70170_p.func_72872_a(EntityTFMiniGhast.class, this.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D));
        Iterator offsetX = nearbyGhasts.iterator();

        while (offsetX.hasNext()) {
            EntityTFMiniGhast ghast = (EntityTFMiniGhast) offsetX.next();

            ghast.func_70106_y();
            this.func_70691_i(2.0F);
        }

        if (this.trapLocations.isEmpty() && !this.noTrapMode) {
            this.scanForTrapsTwice();
        }

        if (this.trapLocations.isEmpty() && !this.noTrapMode) {
            FMLLog.info("[TwilightForest] Ur-ghast cannot find traps nearby, entering trap-less mode", new Object[0]);
            this.noTrapMode = true;
        }

        if (this.inTrapCounter > 0) {
            --this.inTrapCounter;
            this.field_70792_g = null;
        } else {
            this.field_70794_e = this.field_70791_f;
            if (this.field_70792_g != null && this.field_70792_g.field_70128_L) {
                this.field_70792_g = null;
            }

            if (this.field_70792_g == null) {
                this.field_70792_g = this.findPlayerInRange();
            } else if (!this.isAggressive && this.field_70792_g instanceof EntityPlayer) {
                this.checkToIncreaseAggro((EntityPlayer) this.field_70792_g);
            }

            if (this.isInTantrum()) {
                this.shedTear();
                this.field_70792_g = null;
                if (--this.nextTantrumCry <= 0) {
                    this.func_85030_a(this.func_70621_aR(), this.func_70599_aP(), this.func_70647_i());
                    this.nextTantrumCry = 20 + this.field_70146_Z.nextInt(30);
                }

                if (this.field_70173_aa % 10 == 0) {
                    this.doTantrumDamageEffects();
                }
            }

            this.checkAndChangeCourse();
            double d0 = this.field_70795_b - this.field_70165_t;
            double offsetY = this.field_70796_c - this.field_70163_u;
            double offsetZ = this.field_70793_d - this.field_70161_v;
            double distanceToWaypoint = d0 * d0 + offsetY * offsetY + offsetZ * offsetZ;

            if (distanceToWaypoint < 1.0D || distanceToWaypoint > 3600.0D) {
                this.makeNewWaypoint();
            }

            double targetRange;

            if (this.field_70797_a-- <= 0) {
                this.field_70797_a += this.field_70146_Z.nextInt(5) + 0;
                distanceToWaypoint = (double) MathHelper.func_76133_a(distanceToWaypoint);
                targetRange = 0.05D;
                this.field_70159_w += d0 / distanceToWaypoint * targetRange;
                this.field_70181_x += offsetY / distanceToWaypoint * targetRange;
                this.field_70179_y += offsetZ / distanceToWaypoint * targetRange;
            }

            targetRange = this.aggroCounter <= 0 && !this.isAggressive ? (double) this.stareRange : (double) this.aggroRange;
            if (this.field_70792_g != null && this.field_70792_g.func_70068_e(this) < targetRange * targetRange && this.func_70685_l(this.field_70792_g)) {
                this.func_70625_a(this.field_70792_g, 10.0F, (float) this.func_70646_bf());
                if (this.isAggressive) {
                    if (this.field_70791_f == 10) {
                        this.func_85030_a("mob.ghast.charge", this.func_70599_aP(), this.func_70647_i());
                    }

                    ++this.field_70791_f;
                    if (this.field_70791_f == 20) {
                        this.spitFireball();
                        this.field_70791_f = -40;
                    }
                }
            } else {
                this.isAggressive = false;
                this.field_70792_g = null;
                this.field_70177_z = -((float) Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F;
                this.field_70125_A = 0.0F;
            }

            if (this.field_70791_f > 0 && !this.isAggressive) {
                --this.field_70791_f;
            }

            byte currentAggroStatus = this.field_70180_af.func_75683_a(16);
            byte newAggroStatus = (byte) (this.field_70791_f > 10 ? 2 : (this.aggroCounter <= 0 && !this.isAggressive ? 0 : 1));

            if (currentAggroStatus != newAggroStatus) {
                this.field_70180_af.func_75692_b(16, Byte.valueOf(newAggroStatus));
            }

        }
    }

    private void doTantrumDamageEffects() {
        AxisAlignedBB below = this.field_70121_D.func_72325_c(0.0D, -16.0D, 0.0D).func_72314_b(0.0D, 16.0D, 0.0D);
        List playersBelow = this.field_70170_p.func_72872_a(EntityPlayer.class, below);
        Iterator ghastsBelow = playersBelow.iterator();

        while (ghastsBelow.hasNext()) {
            EntityPlayer player = (EntityPlayer) ghastsBelow.next();
            int ghast = MathHelper.func_76128_c(player.field_70165_t);
            int dy = MathHelper.func_76128_c(player.field_70163_u);
            int dz = MathHelper.func_76128_c(player.field_70161_v);

            if (this.field_70170_p.func_72937_j(ghast, dy, dz)) {
                player.func_70097_a(DamageSource.field_82728_o, 3.0F);
            }
        }

        List list = this.field_70170_p.func_72872_a(EntityTFMiniGhast.class, below);

        EntityTFMiniGhast entitytfminighast;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); ++entitytfminighast.field_70181_x) {
            entitytfminighast = (EntityTFMiniGhast) iterator.next();
        }

    }

    private void shedTear() {
        TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "bosstear", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double) this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, 0.0D, 0.0D, 0.0D);
    }

    protected void makeNewWaypoint() {
        double closestDistance = this.func_70092_e(this.courseX, this.courseY, this.courseZ);

        for (int i = 0; i < 50; ++i) {
            double potentialX = this.field_70165_t + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.wanderFactor);
            double potentialY = this.courseY + (double) (this.field_70146_Z.nextFloat() * 8.0F) - 4.0D;
            double potentialZ = this.field_70161_v + (double) ((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.wanderFactor);
            double offsetX = this.courseX - potentialX;
            double offsetY = this.courseY - potentialY;
            double offsetZ = this.courseZ - potentialZ;
            double potentialDistanceToCourse = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;

            if (potentialDistanceToCourse < closestDistance) {
                this.field_70795_b = potentialX;
                this.field_70796_c = potentialY;
                this.field_70793_d = potentialZ;
                closestDistance = potentialDistanceToCourse;
            }
        }

    }

    protected void checkAndChangeCourse() {
        if (this.courseX == 0.0D && this.courseY == 0.0D && this.courseZ == 0.0D) {
            this.changeCourse();
        }

        double offsetX = this.courseX - this.field_70165_t;
        double offsetY = this.courseY - this.field_70163_u;
        double offsetZ = this.courseZ - this.field_70161_v;
        double distanceToCourse = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;

        if (distanceToCourse < 100.0D) {
            this.changeCourse();
        }

    }

    private void changeCourse() {
        if (this.travelCoords.isEmpty()) {
            this.makeTravelPath();
        }

        if (!this.travelCoords.isEmpty()) {
            if (this.currentTravelCoordIndex >= this.travelCoords.size()) {
                this.currentTravelCoordIndex = 0;
                ++this.travelPathRepetitions;
                if (!this.checkGhastsAtTraps()) {
                    this.spawnGhastsAtTraps();
                }
            }

            this.courseX = (double) ((ChunkCoordinates) this.travelCoords.get(this.currentTravelCoordIndex)).field_71574_a;
            this.courseY = (double) (((ChunkCoordinates) this.travelCoords.get(this.currentTravelCoordIndex)).field_71572_b + 20);
            this.courseZ = (double) ((ChunkCoordinates) this.travelCoords.get(this.currentTravelCoordIndex)).field_71573_c;
            ++this.currentTravelCoordIndex;
        }

    }

    private boolean checkGhastsAtTraps() {
        int trapsWithEnoughGhasts = 0;
        Iterator iterator = this.trapLocations.iterator();

        while (iterator.hasNext()) {
            ChunkCoordinates trap = (ChunkCoordinates) iterator.next();
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double) trap.field_71574_a, (double) trap.field_71572_b, (double) trap.field_71573_c, (double) (trap.field_71574_a + 1), (double) (trap.field_71572_b + 1), (double) (trap.field_71573_c + 1)).func_72314_b(8.0D, 16.0D, 8.0D);
            List nearbyGhasts = this.field_70170_p.func_72872_a(EntityTFMiniGhast.class, aabb);

            if (nearbyGhasts.size() >= 4) {
                ++trapsWithEnoughGhasts;
            }
        }

        return trapsWithEnoughGhasts >= 1;
    }

    private void makeTravelPath() {
        int px = MathHelper.func_76128_c(this.field_70165_t);
        int py = MathHelper.func_76128_c(this.field_70163_u);
        int pz = MathHelper.func_76128_c(this.field_70161_v);
        ArrayList potentialPoints;

        if (!this.noTrapMode) {
            potentialPoints = new ArrayList(this.trapLocations);
        } else {
            potentialPoints = new ArrayList();
            potentialPoints.add(new ChunkCoordinates(px + 20, py - 20, pz));
            potentialPoints.add(new ChunkCoordinates(px, py - 20, pz - 20));
            potentialPoints.add(new ChunkCoordinates(px - 20, py - 20, pz));
            potentialPoints.add(new ChunkCoordinates(px, py - 20, pz + 20));
        }

        this.travelCoords.clear();

        while (!potentialPoints.isEmpty()) {
            int index = this.field_70146_Z.nextInt(potentialPoints.size());

            this.travelCoords.add(potentialPoints.get(index));
            potentialPoints.remove(index);
        }

        if (this.noTrapMode) {
            this.travelCoords.add(new ChunkCoordinates(px, py - 20, pz));
        }

    }

    protected void spitFireball() {
        double offsetX = this.field_70792_g.field_70165_t - this.field_70165_t;
        double offsetY = this.field_70792_g.field_70121_D.field_72338_b + (double) (this.field_70792_g.field_70131_O / 2.0F) - (this.field_70163_u + (double) (this.field_70131_O / 2.0F));
        double offsetZ = this.field_70792_g.field_70161_v - this.field_70161_v;

        this.field_70170_p.func_72889_a((EntityPlayer) null, 1008, (int) this.field_70165_t, (int) this.field_70163_u, (int) this.field_70161_v, 0);
        EntityTFUrGhastFireball entityFireball = new EntityTFUrGhastFireball(this.field_70170_p, this, offsetX, offsetY, offsetZ);

        entityFireball.field_92057_e = 1;
        double shotSpawnDistance = 8.5D;
        Vec3 lookVec = this.func_70676_i(1.0F);

        entityFireball.field_70165_t = this.field_70165_t + lookVec.field_72450_a * shotSpawnDistance;
        entityFireball.field_70163_u = this.field_70163_u + (double) (this.field_70131_O / 2.0F) + lookVec.field_72448_b * shotSpawnDistance;
        entityFireball.field_70161_v = this.field_70161_v + lookVec.field_72449_c * shotSpawnDistance;
        this.field_70170_p.func_72838_d(entityFireball);

        for (int i = 0; i < 2; ++i) {
            entityFireball = new EntityTFUrGhastFireball(this.field_70170_p, this, offsetX + (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 8.0F), offsetY, offsetZ + (double) ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 8.0F));
            entityFireball.field_92057_e = 1;
            entityFireball.field_70165_t = this.field_70165_t + lookVec.field_72450_a * shotSpawnDistance;
            entityFireball.field_70163_u = this.field_70163_u + (double) (this.field_70131_O / 2.0F) + lookVec.field_72448_b * shotSpawnDistance;
            entityFireball.field_70161_v = this.field_70161_v + lookVec.field_72449_c * shotSpawnDistance;
            this.field_70170_p.func_72838_d(entityFireball);
        }

    }

    private void scanForTrapsTwice() {
        byte scanRangeXZ = 48;
        byte scanRangeY = 32;
        int px = MathHelper.func_76128_c(this.field_70165_t);
        int py = MathHelper.func_76128_c(this.field_70163_u);
        int pz = MathHelper.func_76128_c(this.field_70161_v);

        this.scanForTraps(scanRangeXZ, scanRangeY, px, py, pz);
        if (this.trapLocations.size() > 0) {
            int ax = 0;
            int ay = 0;
            int az = 0;

            ChunkCoordinates trapCoords;

            for (Iterator iterator = this.trapLocations.iterator(); iterator.hasNext(); az += trapCoords.field_71573_c) {
                trapCoords = (ChunkCoordinates) iterator.next();
                ax += trapCoords.field_71574_a;
                ay += trapCoords.field_71572_b;
            }

            ax /= this.trapLocations.size();
            ay /= this.trapLocations.size();
            az /= this.trapLocations.size();
            this.scanForTraps(scanRangeXZ, scanRangeY, ax, ay, az);
        }

    }

    protected void scanForTraps(int scanRangeXZ, int scanRangeY, int px, int py, int pz) {
        for (int sx = -scanRangeXZ; sx <= scanRangeXZ; ++sx) {
            for (int sz = -scanRangeXZ; sz <= scanRangeXZ; ++sz) {
                for (int sy = -scanRangeY; sy <= scanRangeY; ++sy) {
                    if (this.isTrapAt(px + sx, py + sy, pz + sz)) {
                        ChunkCoordinates trapCoords = new ChunkCoordinates(px + sx, py + sy, pz + sz);

                        if (!this.trapLocations.contains(trapCoords)) {
                            this.trapLocations.add(trapCoords);
                        }
                    }
                }
            }
        }

    }

    private boolean isTrapAt(int x, int y, int z) {
        return this.field_70170_p.func_72899_e(x, y, z) && this.field_70170_p.func_147439_a(x, y, z) == TFBlocks.towerDevice && (this.field_70170_p.func_72805_g(x, y, z) == 10 || this.field_70170_p.func_72805_g(x, y, z) == 11);
    }

    public boolean func_70027_ad() {
        return false;
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean isInTantrum() {
        return this.field_70180_af.func_75683_a(18) != 0;
    }

    public void setInTantrum(boolean par1) {
        this.field_70180_af.func_75692_b(18, par1 ? Byte.valueOf((byte) -1) : Byte.valueOf((byte) 0));
        this.damageUntilNextPhase = 48.0F;
    }

    protected float func_70599_aP() {
        return 16.0F;
    }

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.5F;
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("inTantrum", this.isInTantrum());
        super.func_70014_b(nbttagcompound);
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setInTantrum(nbttagcompound.func_74767_n("inTantrum"));
    }

    protected void func_70609_aI() {
        super.func_70609_aI();
        if (this.field_70725_aQ == 20 && !this.field_70170_p.field_72995_K) {
            ChunkCoordinates chestCoords = this.findChestCoords();

            TFTreasure.darktower_boss.generate(this.field_70170_p, (Random) null, chestCoords.field_71574_a, chestCoords.field_71572_b, chestCoords.field_71573_c);
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightProgressUrghast);
        }

        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
            ChunkCoordinates chestCoords = this.findChestCoords();
            int dx = chestCoords.field_71574_a;
            int dy = chestCoords.field_71572_b;
            int dz = chestCoords.field_71573_c;
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
            TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, dz, this.field_70170_p);

            if (nearbyFeature == TFFeature.darkTower) {
                chunkProvider.setStructureConquered(dx, dy, dz, true);
            }
        }

    }

    private ChunkCoordinates findChestCoords() {
        if (this.trapLocations.size() <= 0) {
            return new ChunkCoordinates(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
        } else {
            int ax = 0;
            int ay = 0;
            int az = 0;

            ChunkCoordinates trapCoords;

            for (Iterator iterator = this.trapLocations.iterator(); iterator.hasNext(); az += trapCoords.field_71573_c) {
                trapCoords = (ChunkCoordinates) iterator.next();
                ax += trapCoords.field_71574_a;
                ay += trapCoords.field_71572_b;
            }

            ax /= this.trapLocations.size();
            ay /= this.trapLocations.size();
            az /= this.trapLocations.size();
            return new ChunkCoordinates(ax, ay + 2, az);
        }
    }
}
