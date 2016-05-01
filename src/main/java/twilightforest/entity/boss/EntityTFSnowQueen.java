package twilightforest.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.entity.IBreathAttacker;
import twilightforest.entity.ai.EntityAITFHoverBeam;
import twilightforest.entity.ai.EntityAITFHoverSummon;
import twilightforest.entity.ai.EntityAITFHoverThenDrop;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFSnowQueen extends EntityMob implements IBossDisplayData, IEntityMultiPart, IBreathAttacker {

    private static final int MAX_SUMMONS = 6;
    private static final int BEAM_FLAG = 21;
    private static final int PHASE_FLAG = 22;
    private static final int MAX_DAMAGE_WHILE_BEAMING = 25;
    private static final float BREATH_DAMAGE = 4.0F;
    public Entity[] iceArray;
    private int summonsRemaining = 0;
    private int successfulDrops;
    private int maxDrops;
    private int damageWhileBeaming;

    public EntityTFSnowQueen(World par1World) {
        super(par1World);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAITFHoverSummon(this, EntityPlayer.class, 1.0D));
        this.field_70714_bg.func_75776_a(2, new EntityAITFHoverThenDrop(this, EntityPlayer.class, 80, 20));
        this.field_70714_bg.func_75776_a(3, new EntityAITFHoverBeam(this, EntityPlayer.class, 80, 100));
        this.field_70714_bg.func_75776_a(6, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70105_a(0.7F, 2.2F);
        this.iceArray = new Entity[7];

        for (int i = 0; i < this.iceArray.length; ++i) {
            this.iceArray[i] = new EntityTFSnowQueenIceShield(this);
        }

        this.setCurrentPhase(EntityTFSnowQueen.Phase.SUMMON);
        this.field_70178_ae = true;
        this.field_70728_aV = 317;
    }

    public boolean func_70104_M() {
        return false;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(22, Byte.valueOf((byte) 0));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.ice.noise";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.ice.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.ice.death";
    }

    protected Item func_146068_u() {
        return Items.field_151126_ay;
    }

    protected void func_82162_bC() {
        super.func_82162_bC();
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData data = super.func_110161_a(par1EntityLivingData);

        return data;
    }

    public void func_70636_d() {
        super.func_70636_d();

        int look;
        float dist;
        float py;
        float px;

        for (look = 0; look < 3; ++look) {
            dist = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;
            py = this.func_70047_e() + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
            px = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;
            TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowguardian", this.field_70142_S + (double) dist, this.field_70137_T + (double) py, this.field_70136_U + (double) px, 0.0D, 0.0D, 0.0D);
        }

        if (this.getCurrentPhase() == EntityTFSnowQueen.Phase.DROP) {
            for (look = 0; look < this.iceArray.length; ++look) {
                dist = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
                py = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
                px = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
                TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowwarning", this.iceArray[look].field_70142_S + (double) dist, this.iceArray[look].field_70137_T + (double) py, this.iceArray[look].field_70136_U + (double) px, 0.0D, 0.0D, 0.0D);
            }
        }

        if (this.isBreathing() && this.func_70089_S()) {
            Vec3 vec3 = this.func_70040_Z();
            double d0 = 0.5D;
            double d1 = this.field_70165_t + vec3.field_72450_a * d0;
            double py1 = this.field_70163_u + 1.7000000476837158D + vec3.field_72448_b * d0;
            double pz = this.field_70161_v + vec3.field_72449_c * d0;

            for (int i = 0; i < 10; ++i) {
                double dx = vec3.field_72450_a;
                double dy = 0.0D;
                double dz = vec3.field_72449_c;
                double spread = 2.0D + this.func_70681_au().nextDouble() * 2.5D;
                double velocity = 2.0D + this.func_70681_au().nextDouble() * 0.15D;

                dx += this.func_70681_au().nextGaussian() * 0.0075D * spread;
                dy += this.func_70681_au().nextGaussian() * 0.0075D * spread;
                dz += this.func_70681_au().nextGaussian() * 0.0075D * spread;
                dx *= velocity;
                dy *= velocity;
                dz *= velocity;
                TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "icebeam", d1, py1, pz, dx, dy, dz);
            }
        }

    }

    public void func_70071_h_() {
        super.func_70071_h_();

        int k;

        for (k = 0; k < this.iceArray.length; ++k) {
            this.iceArray[k].func_70071_h_();
            if (k < this.iceArray.length - 1) {
                Vec3 d = this.getIceShieldPosition(k);

                this.iceArray[k].func_70107_b(d.field_72450_a, d.field_72448_b, d.field_72449_c);
                this.iceArray[k].field_70177_z = this.getIceShieldAngle(k);
            } else {
                this.iceArray[k].func_70107_b(this.field_70165_t, this.field_70163_u - 1.0D, this.field_70161_v);
                this.iceArray[k].field_70177_z = this.getIceShieldAngle(k);
            }

            if (!this.field_70170_p.field_72995_K) {
                this.applyShieldCollisions(this.iceArray[k]);
            }
        }

        if (this.field_70725_aQ > 0) {
            for (k = 0; k < 5; ++k) {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
                String explosionType = this.field_70146_Z.nextBoolean() ? "hugeexplosion" : "explode";

                this.field_70170_p.func_72869_a(explosionType, this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d0, d1, d2);
            }
        }

    }

    protected void func_70628_a(boolean par1, int par2) {
        this.dropBow();
        int totalDrops = this.field_70146_Z.nextInt(4 + par2) + 1;

        int i;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(Item.func_150898_a(Blocks.field_150403_cj), 7);
        }

        totalDrops = this.field_70146_Z.nextInt(5 + par2) + 5;

        for (i = 0; i < totalDrops; ++i) {
            this.func_145779_a(Items.field_151126_ay, 16);
        }

        this.func_70099_a(new ItemStack(TFItems.trophy, 1, 4), 0.0F);
    }

    private void dropBow() {
        int bowType = this.field_70146_Z.nextInt(2);

        if (bowType == 0) {
            this.func_70099_a(new ItemStack(TFItems.tripleBow), 0.0F);
        } else {
            this.func_70099_a(new ItemStack(TFItems.seekerBow), 0.0F);
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightProgressGlacier);
        }

        if (!this.field_70170_p.field_72995_K) {
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

    private void applyShieldCollisions(Entity collider) {
        List list = this.field_70170_p.func_72839_b(collider, collider.field_70121_D.func_72314_b(-0.20000000298023224D, -0.20000000298023224D, -0.20000000298023224D));
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            Entity collided = (Entity) iterator.next();

            if (collided.func_70104_M()) {
                this.applyShieldCollision(collider, collided);
            }
        }

    }

    protected void applyShieldCollision(Entity collider, Entity collided) {
        if (collided != this) {
            collided.func_70108_f(collider);
            if (collided instanceof EntityLivingBase) {
                boolean attackSuccess = super.func_70652_k(collided);

                if (attackSuccess) {
                    collided.field_70181_x += 0.4000000059604645D;
                    this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
                }
            }
        }

    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.getCurrentPhase() == EntityTFSnowQueen.Phase.SUMMON && this.getSummonsRemaining() == 0 && this.countMyMinions() <= 0) {
            this.setCurrentPhase(EntityTFSnowQueen.Phase.DROP);
        }

        if (this.getCurrentPhase() == EntityTFSnowQueen.Phase.DROP && this.successfulDrops >= this.maxDrops) {
            this.setCurrentPhase(EntityTFSnowQueen.Phase.BEAM);
        }

        if (this.getCurrentPhase() == EntityTFSnowQueen.Phase.BEAM && this.damageWhileBeaming >= 25) {
            this.setCurrentPhase(EntityTFSnowQueen.Phase.SUMMON);
        }

    }

    public boolean func_70097_a(DamageSource par1DamageSource, float damage) {
        boolean result = super.func_70097_a(par1DamageSource, damage);

        if (result && this.getCurrentPhase() == EntityTFSnowQueen.Phase.BEAM) {
            this.damageWhileBeaming = (int) ((float) this.damageWhileBeaming + damage);
        }

        return result;
    }

    private Vec3 getIceShieldPosition(int i) {
        return this.getIceShieldPosition(this.getIceShieldAngle(i), 1.0F);
    }

    private float getIceShieldAngle(int i) {
        return 60.0F * (float) i + (float) this.field_70173_aa * 5.0F;
    }

    public Vec3 getIceShieldPosition(float angle, float distance) {
        double d0 = Math.cos((double) angle * 3.141592653589793D / 180.0D) * (double) distance;
        double d1 = Math.sin((double) angle * 3.141592653589793D / 180.0D) * (double) distance;

        return Vec3.func_72443_a(this.field_70165_t + d0, this.field_70163_u + this.getShieldYOffset(), this.field_70161_v + d1);
    }

    public double getShieldYOffset() {
        return 0.10000000149011612D;
    }

    protected void func_70069_a(float par1) {}

    public World func_82194_d() {
        return this.field_70170_p;
    }

    public boolean func_70965_a(EntityDragonPart entitydragonpart, DamageSource damagesource, float i) {
        return false;
    }

    public Entity[] func_70021_al() {
        return this.iceArray;
    }

    public boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB) {
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
                    Block block = this.field_70170_p.func_147439_a(dx, dy, dz);

                    if (block != Blocks.field_150350_a) {
                        int currentMeta = this.field_70170_p.func_72805_g(dx, dy, dz);

                        if (block != Blocks.field_150432_aD && block != Blocks.field_150403_cj) {
                            wasBlocked = true;
                        } else {
                            this.field_70170_p.func_147465_d(dx, dy, dz, Blocks.field_150350_a, 0, 2);
                            this.field_70170_p.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(block) + (currentMeta << 12));
                        }
                    }
                }
            }
        }

        return wasBlocked;
    }

    public boolean isBreathing() {
        return this.func_70096_w().func_75683_a(21) == 1;
    }

    public void setBreathing(boolean flag) {
        this.func_70096_w().func_75692_b(21, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public EntityTFSnowQueen.Phase getCurrentPhase() {
        return EntityTFSnowQueen.Phase.values()[this.func_70096_w().func_75683_a(22)];
    }

    public void setCurrentPhase(EntityTFSnowQueen.Phase currentPhase) {
        this.func_70096_w().func_75692_b(22, Byte.valueOf((byte) currentPhase.ordinal()));
        if (currentPhase == EntityTFSnowQueen.Phase.SUMMON) {
            this.setSummonsRemaining(6);
        }

        if (currentPhase == EntityTFSnowQueen.Phase.DROP) {
            this.successfulDrops = 0;
            this.maxDrops = 2 + this.field_70146_Z.nextInt(3);
        }

        if (currentPhase == EntityTFSnowQueen.Phase.BEAM) {
            this.damageWhileBeaming = 0;
        }

    }

    public int getSummonsRemaining() {
        return this.summonsRemaining;
    }

    public void setSummonsRemaining(int summonsRemaining) {
        this.summonsRemaining = summonsRemaining;
    }

    public void summonMinionAt(EntityLivingBase targetedEntity) {
        Vec3 minionSpot = this.findVecInLOSOf(targetedEntity);
        EntityTFIceCrystal minion = new EntityTFIceCrystal(this.field_70170_p);

        minion.func_70107_b(minionSpot.field_72450_a, minionSpot.field_72448_b, minionSpot.field_72449_c);
        this.field_70170_p.func_72838_d(minion);
        minion.func_70624_b(targetedEntity);
        minion.setToDieIn30Seconds();
        --this.summonsRemaining;
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

    public int countMyMinions() {
        List nearbyMinons = this.field_70170_p.func_72872_a(EntityTFIceCrystal.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));

        return nearbyMinons.size();
    }

    public void incrementSuccessfulDrops() {
        ++this.successfulDrops;
    }

    public void doBreathAttack(Entity target) {
        if (target.func_70097_a(DamageSource.func_76358_a(this), 4.0F)) {
            ;
        }

    }

    public static enum Phase {

        SUMMON, DROP, BEAM;
    }
}
