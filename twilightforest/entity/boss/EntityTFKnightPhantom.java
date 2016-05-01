package twilightforest.entity.boss;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.TFTreasure;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFKnightPhantom extends EntityFlying implements IMob {

    private static final float CIRCLE_SMALL_RADIUS = 2.5F;
    private static final float CIRCLE_LARGE_RADIUS = 8.5F;
    private static final int FLAG_CHARGING = 17;
    int number;
    int ticksProgress;
    EntityTFKnightPhantom.Formation currentFormation;
    private ChunkCoordinates homePosition = new ChunkCoordinates(0, 0, 0);
    private float maximumHomeDistance = -1.0F;
    private int chargePosX;
    private int chargePosY;
    private int chargePosZ;

    public EntityTFKnightPhantom(World par1World) {
        super(par1World);
        this.func_70105_a(1.5F, 3.0F);
        this.field_70145_X = true;
        this.field_70178_ae = true;
        this.currentFormation = EntityTFKnightPhantom.Formation.HOVER;
        this.field_70728_aV = 93;
        this.func_70062_b(0, new ItemStack(TFItems.knightlySword));
        this.func_70062_b(3, new ItemStack(TFItems.phantomPlate));
        this.func_70062_b(4, new ItemStack(TFItems.phantomHelm));
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(35.0D);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return this.func_85032_ar() ? false : (par1DamageSource == DamageSource.field_76368_d ? false : super.func_70097_a(par1DamageSource, par2));
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.isChargingAtPlayer()) {
            for (int i = 0; i < 4; ++i) {
                Item particleID = this.field_70146_Z.nextBoolean() ? TFItems.phantomHelm : TFItems.knightlySword;

                this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b(particleID), this.field_70165_t + ((double) (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) - 0.5D) * (double) this.field_70130_N, this.field_70163_u + (double) this.field_70146_Z.nextFloat() * ((double) this.field_70131_O - 0.75D) + 0.5D, this.field_70161_v + ((double) (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) - 0.5D) * (double) this.field_70130_N, 0.0D, -0.1D, 0.0D);
                this.field_70170_p.func_72869_a("smoke", this.field_70165_t + ((double) (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) - 0.5D) * (double) this.field_70130_N, this.field_70163_u + (double) this.field_70146_Z.nextFloat() * ((double) this.field_70131_O - 0.75D) + 0.5D, this.field_70161_v + ((double) (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) - 0.5D) * (double) this.field_70130_N, 0.0D, 0.1D, 0.0D);
            }
        }

    }

    protected void func_70609_aI() {
        super.func_70609_aI();

        for (int i = 0; i < 20; ++i) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02D;

            this.field_70170_p.func_72869_a("explode", this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d0, d1, d2);
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightProgressKnights);
        }

        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
            int nearbyKnights = this.getHomePosition().field_71574_a;
            int dy = this.getHomePosition().field_71572_b;
            int dz = this.getHomePosition().field_71573_c;
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
            TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(nearbyKnights, dz, this.field_70170_p);

            if (nearbyFeature == TFFeature.tfStronghold) {
                chunkProvider.setStructureConquered(nearbyKnights, dy, dz, true);
            }
        }

        if (!this.field_70170_p.field_72995_K) {
            List nearbyKnights1 = this.getNearbyKnights();

            if (nearbyKnights1.size() <= 1) {
                this.makeATreasure();
            }
        }

    }

    private void makeATreasure() {
        if (this.getHomePosition().field_71574_a != 0) {
            TFTreasure.stronghold_boss.generate(this.field_70170_p, (Random) null, this.getHomePosition().field_71574_a, this.getHomePosition().field_71572_b - 1, this.getHomePosition().field_71573_c);
        } else {
            int px = MathHelper.func_76128_c(this.field_70142_S);
            int py = MathHelper.func_76128_c(this.field_70137_T);
            int pz = MathHelper.func_76128_c(this.field_70136_U);

            TFTreasure.stronghold_boss.generate(this.field_70170_p, (Random) null, px, py, pz);
        }

    }

    protected void func_70626_be() {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        }

        this.func_70623_bb();
        this.field_70145_X = this.ticksProgress % 20 != 0;
        ++this.ticksProgress;
        if (this.ticksProgress >= this.getMaxTicksForFormation()) {
            this.switchToNextFormation();
        }

        float seekRange = this.isChargingAtPlayer() ? 24.0F : 9.0F;
        EntityPlayer target = this.field_70170_p.func_72856_b(this, (double) seekRange);

        if (target != null && this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START) {
            int dest = MathHelper.func_76128_c(target.field_70142_S);
            int moveX = MathHelper.func_76128_c(target.field_70137_T);
            int targetZ = MathHelper.func_76128_c(target.field_70136_U);

            if (this.isWithinHomeArea(dest, moveX, targetZ)) {
                this.chargePosX = dest;
                this.chargePosY = moveX;
                this.chargePosZ = targetZ;
            } else {
                this.chargePosX = this.getHomePosition().field_71574_a;
                this.chargePosY = this.getHomePosition().field_71572_b;
                this.chargePosZ = this.getHomePosition().field_71573_c;
            }
        }

        Vec3 dest1 = this.getDestination();
        double moveX1 = dest1.field_72450_a - this.field_70165_t;
        double moveY = dest1.field_72448_b - this.field_70163_u;
        double moveZ = dest1.field_72449_c - this.field_70161_v;
        double factor = moveX1 * moveX1 + moveY * moveY + moveZ * moveZ;

        factor = (double) MathHelper.func_76133_a(factor);
        double speed = 0.1D;

        this.field_70159_w += moveX1 / factor * speed;
        this.field_70181_x += moveY / factor * speed;
        this.field_70179_y += moveZ / factor * speed;
        if (target != null) {
            this.func_70625_a(target, 10.0F, 500.0F);
            if (target.func_70089_S()) {
                float f1 = target.func_70032_d(this);

                if (this.func_70685_l(target)) {
                    this.attackEntity(target, f1);
                }
            }

            if (this.isAxeKnight() && this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK && this.ticksProgress % 4 == 0) {
                this.launchAxeAt(target);
            }

            if (this.isPickKnight() && this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK && this.ticksProgress % 4 == 0) {
                this.launchPicks();
            }
        }

    }

    protected void attackEntity(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < 2.0F && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(par1Entity);
        }

    }

    public boolean func_70652_k(Entity par1Entity) {
        float f = this.getAttackDamage();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a(this, (EntityLivingBase) par1Entity);
            i += EnchantmentHelper.func_77507_b(this, (EntityLivingBase) par1Entity);
        }

        boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a(this), f);

        if (flag) {
            if (i > 0) {
                par1Entity.func_70024_g((double) (-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * (float) i * 0.5F));
                this.field_70159_w *= 0.6D;
                this.field_70179_y *= 0.6D;
            }

            int j = EnchantmentHelper.func_90036_a(this);

            if (j > 0) {
                par1Entity.func_70015_d(j * 4);
            }

            if (par1Entity instanceof EntityLivingBase) {
                ;
            }
        }

        return flag;
    }

    private float getAttackDamage() {
        float damage = (float) this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();

        if (this.isChargingAtPlayer()) {
            damage += 7.0F;
        }

        return damage;
    }

    protected void launchAxeAt(Entity targetedEntity) {
        float bodyFacingAngle = this.field_70761_aq * 3.141593F / 180.0F;
        double sx = this.field_70165_t + (double) (MathHelper.func_76134_b(bodyFacingAngle) * 1.0F);
        double sy = this.field_70163_u + (double) this.field_70131_O * 0.82D;
        double sz = this.field_70161_v + (double) (MathHelper.func_76126_a(bodyFacingAngle) * 1.0F);
        double tx = targetedEntity.field_70165_t - sx;
        double ty = targetedEntity.field_70121_D.field_72338_b + (double) (targetedEntity.field_70131_O / 2.0F) - (this.field_70163_u + (double) (this.field_70131_O / 2.0F));
        double tz = targetedEntity.field_70161_v - sz;

        this.field_70170_p.func_72956_a(this, "random.bow", this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.4F);
        EntityTFThrownAxe projectile = new EntityTFThrownAxe(this.field_70170_p, this);
        float speed = 0.75F;

        projectile.func_70186_c(tx, ty, tz, speed, 1.0F);
        projectile.func_70012_b(sx, sy, sz, this.field_70177_z, this.field_70125_A);
        this.field_70170_p.func_72838_d(projectile);
    }

    protected void launchPicks() {
        this.field_70170_p.func_72956_a(this, "random.bow", this.func_70599_aP(), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.4F);

        for (int i = 0; i < 8; ++i) {
            float throwAngle = (float) i * 3.1415915F / 4.0F;
            double sx = this.field_70165_t + (double) (MathHelper.func_76134_b(throwAngle) * 1.0F);
            double sy = this.field_70163_u + (double) this.field_70131_O * 0.82D;
            double sz = this.field_70161_v + (double) (MathHelper.func_76126_a(throwAngle) * 1.0F);
            double vx = (double) MathHelper.func_76134_b(throwAngle);
            double vy = 0.0D;
            double vz = (double) MathHelper.func_76126_a(throwAngle);
            EntityTFThrownPick projectile = new EntityTFThrownPick(this.field_70170_p, this);

            projectile.func_70012_b(sx, sy, sz, (float) i * 45.0F, this.field_70125_A);
            float speed = 0.5F;

            projectile.func_70186_c(vx, vy, vz, speed, 1.0F);
            this.field_70170_p.func_72838_d(projectile);
        }

    }

    public boolean func_70104_M() {
        return true;
    }

    public void func_70653_a(Entity par1Entity, float damage, double par3, double par5) {
        this.field_70160_al = true;
        float f = MathHelper.func_76133_a(par3 * par3 + par5 * par5);
        float distance = 0.2F;

        this.field_70159_w /= 2.0D;
        this.field_70181_x /= 2.0D;
        this.field_70179_y /= 2.0D;
        this.field_70159_w -= par3 / (double) f * (double) distance;
        this.field_70181_x += (double) distance;
        this.field_70179_y -= par5 / (double) f * (double) distance;
        if (this.field_70181_x > 0.4000000059604645D) {
            this.field_70181_x = 0.4000000059604645D;
        }

    }

    public void switchToNextFormation() {
        List nearbyKnights = this.getNearbyKnights();

        if (this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START) {
            this.switchToFormation(EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK);
        } else if (this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK) {
            if (nearbyKnights.size() > 1) {
                this.switchToFormation(EntityTFKnightPhantom.Formation.WAITING_FOR_LEADER);
            } else {
                switch (this.field_70146_Z.nextInt(3)) {
                case 0:
                    this.func_70062_b(0, new ItemStack(TFItems.knightlySword));
                    break;

                case 1:
                    this.func_70062_b(0, new ItemStack(TFItems.knightlyAxe));
                    break;

                case 2:
                    this.func_70062_b(0, new ItemStack(TFItems.knightlyPick));
                }

                this.switchToFormation(EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START);
            }
        } else if (this.currentFormation == EntityTFKnightPhantom.Formation.WAITING_FOR_LEADER) {
            if (nearbyKnights.size() > 1) {
                this.switchToFormation(((EntityTFKnightPhantom) nearbyKnights.get(1)).currentFormation);
                this.ticksProgress = ((EntityTFKnightPhantom) nearbyKnights.get(1)).ticksProgress;
            } else {
                this.switchToFormation(EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START);
            }
        } else if (this.isThisTheLeader(nearbyKnights)) {
            this.pickRandomFormation();
            this.broadcastMyFormation(nearbyKnights);
            if (this.isNobodyCharging(nearbyKnights)) {
                this.makeARandomKnightCharge(nearbyKnights);
            }
        }

    }

    private List getNearbyKnights() {
        List nearbyKnights = this.field_70170_p.func_72872_a(EntityTFKnightPhantom.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 8.0D, 32.0D));

        return nearbyKnights;
    }

    protected void pickRandomFormation() {
        switch (this.field_70146_Z.nextInt(8)) {
        case 0:
            this.currentFormation = EntityTFKnightPhantom.Formation.SMALL_CLOCKWISE;
            break;

        case 1:
            this.currentFormation = EntityTFKnightPhantom.Formation.SMALL_ANTICLOCKWISE;
            break;

        case 2:
            this.currentFormation = EntityTFKnightPhantom.Formation.SMALL_ANTICLOCKWISE;
            break;

        case 3:
            this.currentFormation = EntityTFKnightPhantom.Formation.CHARGE_PLUSX;
            break;

        case 4:
            this.currentFormation = EntityTFKnightPhantom.Formation.CHARGE_MINUSX;
            break;

        case 5:
            this.currentFormation = EntityTFKnightPhantom.Formation.CHARGE_PLUSZ;
            break;

        case 6:
            this.currentFormation = EntityTFKnightPhantom.Formation.CHARGE_MINUSZ;
            break;

        case 7:
            this.currentFormation = EntityTFKnightPhantom.Formation.SMALL_CLOCKWISE;
        }

        this.switchToFormation(this.currentFormation);
    }

    private boolean isThisTheLeader(List nearbyKnights) {
        boolean iAmTheLowest = true;
        Iterator iterator = nearbyKnights.iterator();

        while (iterator.hasNext()) {
            EntityTFKnightPhantom knight = (EntityTFKnightPhantom) iterator.next();

            if (knight.getNumber() < this.getNumber()) {
                iAmTheLowest = false;
                break;
            }
        }

        return iAmTheLowest;
    }

    private boolean isNobodyCharging(List nearbyKnights) {
        boolean noCharge = true;
        Iterator iterator = nearbyKnights.iterator();

        while (iterator.hasNext()) {
            EntityTFKnightPhantom knight = (EntityTFKnightPhantom) iterator.next();

            if (knight.isChargingAtPlayer()) {
                noCharge = false;
                break;
            }
        }

        return noCharge;
    }

    private void makeARandomKnightCharge(List nearbyKnights) {
        int randomNum = this.field_70146_Z.nextInt(nearbyKnights.size());

        ((EntityTFKnightPhantom) nearbyKnights.get(randomNum)).switchToFormation(EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START);
    }

    private void broadcastMyFormation(List nearbyKnights) {
        Iterator iterator = nearbyKnights.iterator();

        while (iterator.hasNext()) {
            EntityTFKnightPhantom knight = (EntityTFKnightPhantom) iterator.next();

            if (!knight.isChargingAtPlayer()) {
                knight.switchToFormation(this.currentFormation);
            }
        }

    }

    public boolean isChargingAtPlayer() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setChargingAtPlayer(boolean flag) {
        if (flag) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 127));
        } else {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) 0));
        }

    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.wraith.wraith";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.wraith.wraith";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.wraith.wraith";
    }

    private void switchToFormationByNumber(int formationNumber) {
        this.currentFormation = EntityTFKnightPhantom.Formation.values()[formationNumber];
        this.ticksProgress = 0;
    }

    public void switchToFormation(EntityTFKnightPhantom.Formation formation) {
        this.currentFormation = formation;
        this.ticksProgress = 0;
        this.setChargingAtPlayer(this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START || this.currentFormation == EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK);
    }

    public int getFormationAsNumber() {
        return this.currentFormation.ordinal();
    }

    public int getTicksProgress() {
        return this.ticksProgress;
    }

    public void setTicksProgress(int ticksProgress) {
        this.ticksProgress = ticksProgress;
    }

    public int getMaxTicksForFormation() {
        switch (EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[this.currentFormation.ordinal()]) {
        case 1:
        default:
            return 90;

        case 2:
            return 180;

        case 3:
            return 90;

        case 4:
            return 180;

        case 5:
            return 90;

        case 6:
            return 180;

        case 7:
            return 180;

        case 8:
            return 180;

        case 9:
            return 180;

        case 10:
            return 50;

        case 11:
            return 50;

        case 12:
            return 10;
        }
    }

    private Vec3 getDestination() {
        if (!this.hasHome()) {
            ;
        }

        switch (EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[this.currentFormation.ordinal()]) {
        case 1:
        case 10:
            return this.getHoverPosition(8.5F);

        case 2:
            return this.getCirclePosition(8.5F, true);

        case 3:
            return this.getCirclePosition(2.5F, true);

        case 4:
            return this.getCirclePosition(8.5F, false);

        case 5:
            return this.getCirclePosition(2.5F, false);

        case 6:
            return this.getMoveAcrossPosition(true, true);

        case 7:
            return this.getMoveAcrossPosition(false, true);

        case 8:
            return this.getMoveAcrossPosition(true, false);

        case 9:
            return this.getMoveAcrossPosition(false, false);

        case 11:
            return this.getAttackPlayerPosition();

        case 12:
            return this.getLoiterPosition();

        default:
            return this.getLoiterPosition();
        }
    }

    private Vec3 getMoveAcrossPosition(boolean plus, boolean alongX) {
        float offset0 = (float) this.getNumber() * 3.0F - 7.5F;
        float offset1;

        if (this.ticksProgress < 60) {
            offset1 = -7.0F;
        } else {
            offset1 = -7.0F + (float) (this.ticksProgress - 60) / 120.0F * 14.0F;
        }

        if (!plus) {
            offset1 *= -1.0F;
        }

        double dx = (double) ((float) this.getHomePosition().field_71574_a + (alongX ? offset0 : offset1));
        double dy = (double) this.getHomePosition().field_71572_b + Math.cos((double) ((float) this.ticksProgress / 7.0F + (float) this.getNumber()));
        double dz = (double) ((float) this.getHomePosition().field_71573_c + (alongX ? offset1 : offset0));

        return Vec3.func_72443_a(dx, dy, dz);
    }

    protected Vec3 getCirclePosition(float distance, boolean clockwise) {
        float angle = (float) this.ticksProgress * 2.0F;

        if (!clockwise) {
            angle *= -1.0F;
        }

        angle += 60.0F * (float) this.getNumber();
        double dx = (double) this.getHomePosition().field_71574_a + Math.cos((double) angle * 3.141592653589793D / 180.0D) * (double) distance;
        double dy = (double) this.getHomePosition().field_71572_b + Math.cos((double) ((float) this.ticksProgress / 7.0F + (float) this.getNumber()));
        double dz = (double) this.getHomePosition().field_71573_c + Math.sin((double) angle * 3.141592653589793D / 180.0D) * (double) distance;

        return Vec3.func_72443_a(dx, dy, dz);
    }

    private Vec3 getHoverPosition(float distance) {
        double dx = this.field_70142_S;
        double dy = (double) this.getHomePosition().field_71572_b + Math.cos((double) ((float) this.ticksProgress / 7.0F + (float) this.getNumber()));
        double dz = this.field_70136_U;
        double ox = (double) this.getHomePosition().field_71574_a - dx;
        double oz = (double) this.getHomePosition().field_71573_c - dz;
        double dDist = Math.sqrt(ox * ox + oz * oz);

        if (dDist > (double) distance) {
            dx = (double) this.getHomePosition().field_71574_a + ox / dDist * (double) distance;
            dz = (double) this.getHomePosition().field_71573_c + oz / dDist * (double) distance;
        }

        return Vec3.func_72443_a(dx, dy, dz);
    }

    private Vec3 getLoiterPosition() {
        double dx = (double) this.getHomePosition().field_71574_a;
        double dy = (double) this.getHomePosition().field_71572_b + Math.cos((double) ((float) this.ticksProgress / 7.0F + (float) this.getNumber()));
        double dz = (double) this.getHomePosition().field_71573_c;

        return Vec3.func_72443_a(dx, dy, dz);
    }

    private Vec3 getAttackPlayerPosition() {
        return this.isSwordKnight() ? Vec3.func_72443_a((double) this.chargePosX, (double) this.chargePosY, (double) this.chargePosZ) : this.getHoverPosition(8.5F);
    }

    public boolean isSwordKnight() {
        return this.func_71124_b(0) != null && this.func_71124_b(0).func_77973_b() == TFItems.knightlySword;
    }

    public boolean isAxeKnight() {
        return this.func_71124_b(0) != null && this.func_71124_b(0).func_77973_b() == TFItems.knightlyAxe;
    }

    public boolean isPickKnight() {
        return this.func_71124_b(0) != null && this.func_71124_b(0).func_77973_b() == TFItems.knightlyPick;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
        switch (number % 3) {
        case 0:
            this.func_70062_b(0, new ItemStack(TFItems.knightlySword));
            break;

        case 1:
            this.func_70062_b(0, new ItemStack(TFItems.knightlyAxe));
            break;

        case 2:
            this.func_70062_b(0, new ItemStack(TFItems.knightlyPick));
        }

    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        ChunkCoordinates home = this.getHomePosition();

        nbttagcompound.func_74782_a("Home", this.func_70087_a(new double[] { (double) home.field_71574_a, (double) home.field_71572_b, (double) home.field_71573_c}));
        nbttagcompound.func_74757_a("HasHome", this.hasHome());
        nbttagcompound.func_74768_a("MyNumber", this.getNumber());
        nbttagcompound.func_74768_a("Formation", this.getFormationAsNumber());
        nbttagcompound.func_74768_a("TicksProgress", this.getTicksProgress());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        if (nbttagcompound.func_150297_b("Home", 9)) {
            NBTTagList nbttaglist = nbttagcompound.func_150295_c("Home", 6);
            int hx = (int) nbttaglist.func_150309_d(0);
            int hy = (int) nbttaglist.func_150309_d(1);
            int hz = (int) nbttaglist.func_150309_d(2);

            this.setHomeArea(hx, hy, hz, 20);
        }

        if (!nbttagcompound.func_74767_n("HasHome")) {
            this.detachHome();
        }

        this.setNumber(nbttagcompound.func_74762_e("MyNumber"));
        this.switchToFormationByNumber(nbttagcompound.func_74762_e("Formation"));
        this.setTicksProgress(nbttagcompound.func_74762_e("TicksProgress"));
    }

    public boolean isWithinHomeArea(int par1, int par2, int par3) {
        return this.maximumHomeDistance == -1.0F ? true : this.homePosition.func_71569_e(par1, par2, par3) < this.maximumHomeDistance * this.maximumHomeDistance;
    }

    public void setHomeArea(int par1, int par2, int par3, int par4) {
        this.homePosition.func_71571_b(par1, par2, par3);
        this.maximumHomeDistance = (float) par4;
    }

    public ChunkCoordinates getHomePosition() {
        return this.homePosition;
    }

    public float getMaximumHomeDistance() {
        return this.maximumHomeDistance;
    }

    public void detachHome() {
        this.maximumHomeDistance = -1.0F;
    }

    public boolean hasHome() {
        return this.maximumHomeDistance != -1.0F;
    }

    static class SyntheticClass_1 {

        static final int[] $SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation = new int[EntityTFKnightPhantom.Formation.values().length];

        static {
            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.HOVER.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.LARGE_CLOCKWISE.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.SMALL_CLOCKWISE.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.LARGE_ANTICLOCKWISE.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.SMALL_ANTICLOCKWISE.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.CHARGE_PLUSX.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.CHARGE_MINUSX.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.CHARGE_PLUSZ.ordinal()] = 8;
            } catch (NoSuchFieldError nosuchfielderror7) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.CHARGE_MINUSZ.ordinal()] = 9;
            } catch (NoSuchFieldError nosuchfielderror8) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.ATTACK_PLAYER_START.ordinal()] = 10;
            } catch (NoSuchFieldError nosuchfielderror9) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.ATTACK_PLAYER_ATTACK.ordinal()] = 11;
            } catch (NoSuchFieldError nosuchfielderror10) {
                ;
            }

            try {
                EntityTFKnightPhantom.SyntheticClass_1.$SwitchMap$twilightforest$entity$boss$EntityTFKnightPhantom$Formation[EntityTFKnightPhantom.Formation.WAITING_FOR_LEADER.ordinal()] = 12;
            } catch (NoSuchFieldError nosuchfielderror11) {
                ;
            }

        }
    }

    public static enum Formation {

        HOVER, LARGE_CLOCKWISE, SMALL_CLOCKWISE, LARGE_ANTICLOCKWISE, SMALL_ANTICLOCKWISE, CHARGE_PLUSX, CHARGE_MINUSX, CHARGE_PLUSZ, CHARGE_MINUSZ, WAITING_FOR_LEADER, ATTACK_PLAYER_START, ATTACK_PLAYER_ATTACK;
    }
}
