package twilightforest.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import twilightforest.TwilightForestMod;

public class HydraHeadContainer {

    private static int FLAME_BURN_FACTOR = 3;
    private static int FLAME_DAMAGE = 19;
    private static int BITE_DAMAGE = 48;
    private static double FLAME_BREATH_TRACKING_SPEED = 0.04D;
    public static final int NEXT_AUTOMATIC = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_BITE_BEGINNING = 1;
    public static final int STATE_BITE_READY = 2;
    public static final int STATE_BITE_BITING = 3;
    public static final int STATE_BITE_ENDING = 4;
    public static final int STATE_FLAME_BEGINNING = 5;
    public static final int STATE_FLAME_BREATHING = 6;
    public static final int STATE_FLAME_ENDING = 7;
    public static final int STATE_MORTAR_BEGINNING = 8;
    public static final int STATE_MORTAR_LAUNCH = 9;
    public static final int STATE_MORTAR_ENDING = 10;
    public static final int STATE_DYING = 11;
    public static final int STATE_DEAD = 12;
    public static final int STATE_ATTACK_COOLDOWN = 13;
    public static final int STATE_BORN = 14;
    public static final int STATE_ROAR_START = 15;
    public static final int STATE_ROAR_RAWR = 16;
    public static final int NUM_STATES = 17;
    public EntityTFHydraHead headEntity;
    public EntityTFHydraNeck necka;
    public EntityTFHydraNeck neckb;
    public EntityTFHydraNeck neckc;
    public EntityTFHydraNeck neckd;
    public EntityTFHydraNeck necke;
    public Entity targetEntity;
    public double targetX;
    public double targetY;
    public double targetZ;
    public int prevState;
    public int currentState;
    public int nextState = -1;
    public boolean didRoar;
    public boolean isSecondaryAttacking;
    public int ticksNeeded;
    public int ticksProgress;
    public final int headNum;
    public int damageTaken;
    public int respawnCounter;
    public final EntityTFHydra hydraObj;
    public int[] nextStates;
    public int[] stateDurations;
    public float[][] stateNeckLength;
    public float[][] stateXRotations;
    public float[][] stateYRotations;
    public float[][] stateMouthOpen;

    public HydraHeadContainer(EntityTFHydra hydra, int number, boolean startActive) {
        this.headNum = number;
        this.hydraObj = hydra;
        this.damageTaken = 0;
        this.respawnCounter = -1;
        this.didRoar = false;
        this.necka = new EntityTFHydraNeck(this.hydraObj, "neck" + this.headNum + "a", 2.0F, 2.0F);
        this.neckb = new EntityTFHydraNeck(this.hydraObj, "neck" + this.headNum + "b", 2.0F, 2.0F);
        this.neckc = new EntityTFHydraNeck(this.hydraObj, "neck" + this.headNum + "c", 2.0F, 2.0F);
        this.neckd = new EntityTFHydraNeck(this.hydraObj, "neck" + this.headNum + "d", 2.0F, 2.0F);
        this.necke = new EntityTFHydraNeck(this.hydraObj, "neck" + this.headNum + "e", 2.0F, 2.0F);
        this.nextStates = new int[17];
        this.nextStates[0] = 0;
        this.nextStates[1] = 2;
        this.nextStates[2] = 3;
        this.nextStates[3] = 4;
        this.nextStates[4] = 13;
        this.nextStates[5] = 6;
        this.nextStates[6] = 7;
        this.nextStates[7] = 13;
        this.nextStates[8] = 9;
        this.nextStates[9] = 10;
        this.nextStates[10] = 13;
        this.nextStates[13] = 0;
        this.nextStates[11] = 12;
        this.nextStates[12] = 12;
        this.nextStates[14] = 15;
        this.nextStates[15] = 16;
        this.nextStates[16] = 0;
        this.stateDurations = new int[17];
        this.setupStateDurations();
        this.stateNeckLength = new float[this.hydraObj.numHeads][17];
        this.stateXRotations = new float[this.hydraObj.numHeads][17];
        this.stateYRotations = new float[this.hydraObj.numHeads][17];
        this.stateMouthOpen = new float[this.hydraObj.numHeads][17];
        this.setupStateRotations();
        if (startActive) {
            this.prevState = 0;
            this.currentState = 0;
            this.nextState = -1;
            this.ticksNeeded = 60;
            this.ticksProgress = 60;
        } else {
            this.prevState = 12;
            this.currentState = 12;
            this.nextState = -1;
            this.ticksNeeded = 20;
            this.ticksProgress = 20;
        }

    }

    protected void setupStateDurations() {
        this.stateDurations[0] = 10;
        this.stateDurations[1] = 40;
        this.stateDurations[2] = 80;
        this.stateDurations[3] = 7;
        this.stateDurations[4] = 40;
        this.stateDurations[5] = 40;
        this.stateDurations[6] = 100;
        this.stateDurations[7] = 30;
        this.stateDurations[8] = 40;
        this.stateDurations[9] = 25;
        this.stateDurations[10] = 30;
        this.stateDurations[13] = 80;
        this.stateDurations[11] = 70;
        this.stateDurations[12] = 20;
        this.stateDurations[14] = 20;
        this.stateDurations[15] = 10;
        this.stateDurations[16] = 50;
    }

    protected void setupStateRotations() {
        this.setAnimation(0, 0, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 0, 10.0F, 60.0F, 9.0F, 0.0F);
        this.setAnimation(2, 0, 10.0F, -60.0F, 9.0F, 0.0F);
        this.setAnimation(3, 0, 50.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 0, 50.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 0, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 0, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 13, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 13, 10.0F, 60.0F, 9.0F, 0.0F);
        this.setAnimation(2, 13, 10.0F, -60.0F, 9.0F, 0.0F);
        this.setAnimation(3, 13, 50.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 13, 50.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 13, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 13, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 5, 50.0F, 0.0F, 8.0F, 0.75F);
        this.setAnimation(1, 5, 30.0F, 45.0F, 9.0F, 0.75F);
        this.setAnimation(2, 5, 30.0F, -45.0F, 9.0F, 0.75F);
        this.setAnimation(3, 5, 50.0F, 90.0F, 8.0F, 0.75F);
        this.setAnimation(4, 5, 50.0F, -90.0F, 8.0F, 0.75F);
        this.setAnimation(5, 5, -10.0F, 90.0F, 9.0F, 0.75F);
        this.setAnimation(6, 5, -10.0F, -90.0F, 9.0F, 0.75F);
        this.setAnimation(0, 6, 45.0F, 0.0F, 8.0F, 1.0F);
        this.setAnimation(1, 6, 30.0F, 60.0F, 9.0F, 1.0F);
        this.setAnimation(2, 6, 30.0F, -60.0F, 9.0F, 1.0F);
        this.setAnimation(3, 6, 50.0F, 90.0F, 8.0F, 1.0F);
        this.setAnimation(4, 6, 50.0F, -90.0F, 8.0F, 1.0F);
        this.setAnimation(5, 6, -10.0F, 90.0F, 9.0F, 1.0F);
        this.setAnimation(6, 6, -10.0F, -90.0F, 9.0F, 1.0F);
        this.setAnimation(0, 7, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 7, 10.0F, 45.0F, 9.0F, 0.0F);
        this.setAnimation(2, 7, 10.0F, -45.0F, 9.0F, 0.0F);
        this.setAnimation(3, 7, 50.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 7, 50.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 7, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 7, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 1, -5.0F, 60.0F, 5.0F, 0.25F);
        this.setAnimation(1, 1, -10.0F, 60.0F, 9.0F, 0.25F);
        this.setAnimation(2, 1, -10.0F, -60.0F, 9.0F, 0.25F);
        this.setAnimation(0, 2, -5.0F, 60.0F, 5.0F, 1.0F);
        this.setAnimation(1, 2, -10.0F, 60.0F, 9.0F, 1.0F);
        this.setAnimation(2, 2, -10.0F, -60.0F, 9.0F, 1.0F);
        this.setAnimation(0, 3, -5.0F, -30.0F, 5.0F, 0.2F);
        this.setAnimation(1, 3, -10.0F, -30.0F, 5.0F, 0.2F);
        this.setAnimation(2, 3, -10.0F, 30.0F, 5.0F, 0.2F);
        this.setAnimation(0, 4, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 4, -10.0F, 60.0F, 9.0F, 0.0F);
        this.setAnimation(2, 4, -10.0F, -60.0F, 9.0F, 0.0F);
        this.setAnimation(0, 8, 50.0F, 0.0F, 8.0F, 0.75F);
        this.setAnimation(1, 8, 30.0F, 45.0F, 9.0F, 0.75F);
        this.setAnimation(2, 8, 30.0F, -45.0F, 9.0F, 0.75F);
        this.setAnimation(3, 8, 50.0F, 90.0F, 8.0F, 0.75F);
        this.setAnimation(4, 8, 50.0F, -90.0F, 8.0F, 0.75F);
        this.setAnimation(5, 8, -10.0F, 90.0F, 9.0F, 0.75F);
        this.setAnimation(6, 8, -10.0F, -90.0F, 9.0F, 0.75F);
        this.setAnimation(0, 9, 45.0F, 0.0F, 8.0F, 1.0F);
        this.setAnimation(1, 9, 30.0F, 60.0F, 9.0F, 1.0F);
        this.setAnimation(2, 9, 30.0F, -60.0F, 9.0F, 1.0F);
        this.setAnimation(3, 9, 50.0F, 90.0F, 8.0F, 1.0F);
        this.setAnimation(4, 9, 50.0F, -90.0F, 8.0F, 1.0F);
        this.setAnimation(5, 9, -10.0F, 90.0F, 9.0F, 1.0F);
        this.setAnimation(6, 9, -10.0F, -90.0F, 9.0F, 1.0F);
        this.setAnimation(0, 10, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 10, 10.0F, 45.0F, 9.0F, 0.0F);
        this.setAnimation(2, 10, 10.0F, -45.0F, 9.0F, 0.0F);
        this.setAnimation(3, 10, 50.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 10, 50.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 10, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 10, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 11, -20.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 11, -20.0F, 60.0F, 9.0F, 0.0F);
        this.setAnimation(2, 11, -20.0F, -60.0F, 9.0F, 0.0F);
        this.setAnimation(3, 11, -20.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 11, -20.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 11, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 11, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 12, 0.0F, 179.0F, 4.0F, 0.0F);
        this.setAnimation(1, 12, 0.0F, 179.0F, 4.0F, 0.0F);
        this.setAnimation(2, 12, 0.0F, -180.0F, 4.0F, 0.0F);
        this.setAnimation(3, 12, 0.0F, 179.0F, 4.0F, 0.0F);
        this.setAnimation(4, 12, 0.0F, -180.0F, 4.0F, 0.0F);
        this.setAnimation(5, 12, 0.0F, 179.0F, 4.0F, 0.0F);
        this.setAnimation(6, 12, 0.0F, -180.0F, 4.0F, 0.0F);
        this.setAnimation(0, 14, 60.0F, 0.0F, 7.0F, 0.0F);
        this.setAnimation(1, 14, 10.0F, 60.0F, 9.0F, 0.0F);
        this.setAnimation(2, 14, 10.0F, -60.0F, 9.0F, 0.0F);
        this.setAnimation(3, 14, 50.0F, 90.0F, 8.0F, 0.0F);
        this.setAnimation(4, 14, 50.0F, -90.0F, 8.0F, 0.0F);
        this.setAnimation(5, 14, -10.0F, 90.0F, 9.0F, 0.0F);
        this.setAnimation(6, 14, -10.0F, -90.0F, 9.0F, 0.0F);
        this.setAnimation(0, 15, 60.0F, 0.0F, 7.0F, 0.25F);
        this.setAnimation(1, 15, 10.0F, 60.0F, 9.0F, 0.25F);
        this.setAnimation(2, 15, 10.0F, -60.0F, 9.0F, 0.25F);
        this.setAnimation(3, 15, 50.0F, 90.0F, 8.0F, 0.25F);
        this.setAnimation(4, 15, 50.0F, -90.0F, 8.0F, 0.25F);
        this.setAnimation(5, 15, -10.0F, 90.0F, 9.0F, 0.25F);
        this.setAnimation(6, 15, -10.0F, -90.0F, 9.0F, 0.25F);
        this.setAnimation(0, 16, 60.0F, 0.0F, 9.0F, 1.0F);
        this.setAnimation(1, 16, 10.0F, 60.0F, 11.0F, 1.0F);
        this.setAnimation(2, 16, 10.0F, -60.0F, 11.0F, 1.0F);
        this.setAnimation(3, 16, 50.0F, 90.0F, 10.0F, 1.0F);
        this.setAnimation(4, 16, 50.0F, -90.0F, 10.0F, 1.0F);
        this.setAnimation(5, 16, -10.0F, 90.0F, 11.0F, 1.0F);
        this.setAnimation(6, 16, -10.0F, -90.0F, 11.0F, 1.0F);
    }

    protected void setAnimation(int head, int state, float xRotation, float yRotation, float neckLength, float mouthOpen) {
        this.stateXRotations[head][state] = xRotation;
        this.stateYRotations[head][state] = yRotation;
        this.stateNeckLength[head][state] = neckLength;
        this.stateMouthOpen[head][state] = mouthOpen;
    }

    public EntityTFHydraNeck[] getNeckArray() {
        return new EntityTFHydraNeck[] { this.necka, this.neckb, this.neckc, this.neckd, this.necke};
    }

    public void onUpdate() {
        this.necka.func_70071_h_();
        this.neckb.func_70071_h_();
        this.neckc.func_70071_h_();
        this.neckd.func_70071_h_();
        this.necke.func_70071_h_();
        if (this.headEntity == null) {
            this.headEntity = this.findNearbyHead("head" + this.headNum);
        }

        this.setDifficultyVariables();
        if (this.headEntity != null) {
            this.headEntity.field_70130_N = this.headEntity.field_70131_O = this.isActive() ? 4.0F : 1.0F;
            if (!this.hydraObj.field_70170_p.field_72995_K) {
                this.advanceRespawnCounter();
                this.advanceHeadState();
                this.setHeadPosition();
                this.setHeadFacing();
                this.executeAttacks();
            } else {
                this.clientAnimateHeadDeath();
            }

            this.setNeckPosition();
            this.addMouthParticles();
            this.playSounds();
        }

    }

    protected void advanceRespawnCounter() {
        if (this.currentState == 12 && this.respawnCounter > -1 && --this.respawnCounter <= 0) {
            this.setNextState(14);
            this.damageTaken = 0;
            this.endCurrentAction();
            this.respawnCounter = -1;
        }

    }

    protected void clientAnimateHeadDeath() {
        if (this.headEntity.getState() == 11) {
            ++this.headEntity.field_70725_aQ;
            if (this.headEntity.field_70725_aQ > 0) {
                if (this.headEntity.field_70725_aQ < 20) {
                    this.doExplosionOn(this.headEntity, true);
                } else if (this.headEntity.field_70725_aQ < 30) {
                    this.doExplosionOn(this.necka, false);
                } else if (this.headEntity.field_70725_aQ < 40) {
                    this.doExplosionOn(this.neckb, false);
                } else if (this.headEntity.field_70725_aQ < 50) {
                    this.doExplosionOn(this.neckc, false);
                } else if (this.headEntity.field_70725_aQ < 60) {
                    this.doExplosionOn(this.neckd, false);
                } else if (this.headEntity.field_70725_aQ < 70) {
                    this.doExplosionOn(this.necke, false);
                }
            }

            this.necka.field_70737_aN = 20;
            this.neckb.field_70737_aN = 20;
            this.neckc.field_70737_aN = 20;
            this.neckd.field_70737_aN = 20;
            this.necke.field_70737_aN = 20;
        } else {
            this.headEntity.field_70725_aQ = 0;
            this.headEntity.func_70606_j((float) this.headEntity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
        }

    }

    private void doExplosionOn(EntityTFHydraPart part, boolean large) {
        for (int i = 0; i < 10; ++i) {
            double d0 = part.func_70681_au().nextGaussian() * 0.02D;
            double d1 = part.func_70681_au().nextGaussian() * 0.02D;
            double d2 = part.func_70681_au().nextGaussian() * 0.02D;
            String particle = large && part.func_70681_au().nextInt(5) == 0 ? "largeexplode" : "explode";

            part.field_70170_p.func_72869_a(particle, part.field_70165_t + (double) (part.func_70681_au().nextFloat() * part.field_70130_N * 2.0F) - (double) part.field_70130_N, part.field_70163_u + (double) (part.func_70681_au().nextFloat() * part.field_70131_O), part.field_70161_v + (double) (part.func_70681_au().nextFloat() * part.field_70130_N * 2.0F) - (double) part.field_70130_N, d0, d1, d2);
        }

    }

    protected void advanceHeadState() {
        if (++this.ticksProgress >= this.ticksNeeded) {
            int myNext;

            if (this.nextState == -1) {
                myNext = this.nextStates[this.currentState];
                if (myNext != this.currentState && this.isSecondaryAttacking && myNext == 13) {
                    this.isSecondaryAttacking = false;
                    myNext = 0;
                }
            } else {
                myNext = this.nextState;
                this.nextState = -1;
            }

            this.ticksNeeded = this.ticksProgress = this.stateDurations[myNext];
            this.ticksProgress = 0;
            this.prevState = this.currentState;
            this.currentState = myNext;
        }

        if (this.headEntity.getState() != this.currentState) {
            this.headEntity.setState(this.currentState);
        }

    }

    protected void setHeadFacing() {
        if (this.currentState == 2) {
            this.faceEntity(this.targetEntity, 5.0F, (float) this.hydraObj.func_70646_bf());
            float biteMaxYaw = -60.0F;
            float biteMinYaw = -90.0F;

            if (this.headNum == 2) {
                biteMaxYaw = 60.0F;
                biteMinYaw = 90.0F;
            }

            float yawOffOffset = MathHelper.func_76142_g(this.headEntity.field_70177_z - this.hydraObj.field_70761_aq);

            if (yawOffOffset > biteMaxYaw) {
                this.headEntity.field_70177_z = this.hydraObj.field_70761_aq + biteMaxYaw;
            }

            if (yawOffOffset < biteMinYaw) {
                this.headEntity.field_70177_z = this.hydraObj.field_70761_aq + biteMinYaw;
            }

            Vec3 look = this.headEntity.func_70040_Z();
            double distance = 16.0D;

            this.targetX = this.headEntity.field_70165_t + look.field_72450_a * distance;
            this.targetY = this.headEntity.field_70163_u + 1.5D + look.field_72448_b * distance;
            this.targetZ = this.headEntity.field_70161_v + look.field_72449_c * distance;
        } else if (this.currentState != 3 && this.currentState != 4) {
            if (this.currentState == 16) {
                this.faceVec(this.targetX, this.targetY, this.targetZ, 10.0F, (float) this.hydraObj.func_70646_bf());
            } else if (this.currentState != 6 && this.currentState != 5) {
                if (this.isActive()) {
                    if (this.targetEntity != null) {
                        this.faceEntity(this.targetEntity, 5.0F, (float) this.hydraObj.func_70646_bf());
                    } else {
                        this.faceIdle(1.5F, (float) this.hydraObj.func_70646_bf());
                    }
                }
            } else {
                this.moveTargetCoordsTowardsTargetEntity(HydraHeadContainer.FLAME_BREATH_TRACKING_SPEED);
                this.faceVec(this.targetX, this.targetY, this.targetZ, 5.0F, (float) this.hydraObj.func_70646_bf());
            }
        } else {
            this.faceEntity(this.targetEntity, 5.0F, (float) this.hydraObj.func_70646_bf());
            this.headEntity.field_70125_A = (float) ((double) this.headEntity.field_70125_A + 0.7853981633974483D);
        }

    }

    protected void moveTargetCoordsTowardsTargetEntity(double distance) {
        if (this.targetEntity != null) {
            Vec3 vect = Vec3.func_72443_a(this.targetEntity.field_70165_t - this.targetX, this.targetEntity.field_70163_u - this.targetY, this.targetEntity.field_70161_v - this.targetZ);

            vect = vect.func_72432_b();
            this.targetX += vect.field_72450_a * distance;
            this.targetY += vect.field_72448_b * distance;
            this.targetZ += vect.field_72449_c * distance;
        }

    }

    protected void addMouthParticles() {
        Vec3 vector = this.headEntity.func_70040_Z();
        double dist = 3.5D;
        double px = this.headEntity.field_70165_t + vector.field_72450_a * dist;
        double py = this.headEntity.field_70163_u + 1.0D + vector.field_72448_b * dist;
        double pz = this.headEntity.field_70161_v + vector.field_72449_c * dist;

        if (this.headEntity.getState() == 5) {
            this.headEntity.field_70170_p.func_72869_a("flame", px + this.headEntity.func_70681_au().nextDouble() - 0.5D, py + this.headEntity.func_70681_au().nextDouble() - 0.5D, pz + this.headEntity.func_70681_au().nextDouble() - 0.5D, 0.0D, 0.0D, 0.0D);
            this.headEntity.field_70170_p.func_72869_a("smoke", px + this.headEntity.func_70681_au().nextDouble() - 0.5D, py + this.headEntity.func_70681_au().nextDouble() - 0.5D, pz + this.headEntity.func_70681_au().nextDouble() - 0.5D, 0.0D, 0.0D, 0.0D);
        }

        if (this.headEntity.getState() == 6) {
            Vec3 look = this.headEntity.func_70040_Z();

            for (int i = 0; i < 5; ++i) {
                double dx = look.field_72450_a;
                double dy = look.field_72448_b;
                double dz = look.field_72449_c;
                double spread = 5.0D + this.headEntity.func_70681_au().nextDouble() * 2.5D;
                double velocity = 1.0D + this.headEntity.func_70681_au().nextDouble();

                dx += this.headEntity.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dy += this.headEntity.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dz += this.headEntity.func_70681_au().nextGaussian() * 0.007499999832361937D * spread;
                dx *= velocity;
                dy *= velocity;
                dz *= velocity;
                TwilightForestMod.proxy.spawnParticle(this.headEntity.field_70170_p, "largeflame", px, py, pz, dx, dy, dz);
            }
        }

        if (this.headEntity.getState() == 1 || this.headEntity.getState() == 2) {
            this.headEntity.field_70170_p.func_72869_a("splash", px + this.headEntity.func_70681_au().nextDouble() - 0.5D, py + this.headEntity.func_70681_au().nextDouble() - 0.5D, pz + this.headEntity.func_70681_au().nextDouble() - 0.5D, 0.0D, 0.0D, 0.0D);
        }

        if (this.headEntity.getState() == 8) {
            this.headEntity.field_70170_p.func_72869_a("largesmoke", px + this.headEntity.func_70681_au().nextDouble() - 0.5D, py + this.headEntity.func_70681_au().nextDouble() - 0.5D, pz + this.headEntity.func_70681_au().nextDouble() - 0.5D, 0.0D, 0.0D, 0.0D);
        }

    }

    protected void playSounds() {
        if (this.headEntity.getState() == 6 && this.headEntity.field_70173_aa % 5 == 0) {
            this.headEntity.field_70170_p.func_72908_a(this.headEntity.field_70165_t + 0.5D, this.headEntity.field_70163_u + 0.5D, this.headEntity.field_70161_v + 0.5D, "mob.ghast.fireball", 0.5F + this.headEntity.func_70681_au().nextFloat(), this.headEntity.func_70681_au().nextFloat() * 0.7F + 0.3F);
        }

        if (this.headEntity.getState() == 16) {
            this.headEntity.field_70170_p.func_72908_a(this.headEntity.field_70165_t + 0.5D, this.headEntity.field_70163_u + 0.5D, this.headEntity.field_70161_v + 0.5D, "TwilightForest:mob.hydra.roar", 1.25F, this.headEntity.func_70681_au().nextFloat() * 0.3F + 0.7F);
        }

        if (this.headEntity.getState() == 2 && this.ticksProgress == 60) {
            this.headEntity.field_70170_p.func_72908_a(this.headEntity.field_70165_t + 0.5D, this.headEntity.field_70163_u + 0.5D, this.headEntity.field_70161_v + 0.5D, "TwilightForest:mob.hydra.warn", 2.0F, this.headEntity.func_70681_au().nextFloat() * 0.3F + 0.7F);
        }

        if (this.headEntity.getState() == 0) {
            this.didRoar = false;
        }

    }

    protected void setNeckPosition() {
        Vec3 vector = null;
        float neckRotation = 0.0F;

        if (this.headNum == 0) {
            vector = Vec3.func_72443_a(0.0D, 3.0D, -1.0D);
            neckRotation = 0.0F;
        }

        if (this.headNum == 1) {
            vector = Vec3.func_72443_a(-1.0D, 3.0D, 3.0D);
            neckRotation = 90.0F;
        }

        if (this.headNum == 2) {
            vector = Vec3.func_72443_a(1.0D, 3.0D, 3.0D);
            neckRotation = -90.0F;
        }

        if (this.headNum == 3) {
            vector = Vec3.func_72443_a(-1.0D, 3.0D, 3.0D);
            neckRotation = 135.0F;
        }

        if (this.headNum == 4) {
            vector = Vec3.func_72443_a(1.0D, 3.0D, 3.0D);
            neckRotation = -135.0F;
        }

        if (this.headNum == 5) {
            vector = Vec3.func_72443_a(-1.0D, 3.0D, 5.0D);
            neckRotation = 135.0F;
        }

        if (this.headNum == 6) {
            vector = Vec3.func_72443_a(1.0D, 3.0D, 5.0D);
            neckRotation = -135.0F;
        }

        vector.func_72442_b(-(this.hydraObj.field_70761_aq + neckRotation) * 3.141593F / 180.0F);
        this.setNeckPositon(this.hydraObj.field_70165_t + vector.field_72450_a, this.hydraObj.field_70163_u + vector.field_72448_b, this.hydraObj.field_70161_v + vector.field_72449_c, this.hydraObj.field_70761_aq, 0.0F);
    }

    protected void setHeadPosition() {
        this.setupStateDurations();
        this.setupStateRotations();
        float neckLength = this.getCurrentNeckLength();
        float xRotation = this.getCurrentHeadXRotation();
        float yRotation = this.getCurrentHeadYRotation();
        float periodX = this.headNum != 0 && this.headNum != 3 ? (this.headNum != 1 && this.headNum != 4 ? 7.0F : 5.0F) : 20.0F;
        float periodY = this.headNum != 0 && this.headNum != 4 ? (this.headNum != 1 && this.headNum != 6 ? 5.0F : 6.0F) : 10.0F;
        float xSwing = MathHelper.func_76126_a((float) this.hydraObj.field_70173_aa / periodX) * 3.0F;
        float ySwing = MathHelper.func_76126_a((float) this.hydraObj.field_70173_aa / periodY) * 5.0F;

        if (!this.isActive()) {
            ySwing = 0.0F;
            xSwing = 0.0F;
        }

        Vec3 vector = Vec3.func_72443_a(0.0D, 0.0D, (double) neckLength);

        vector.func_72440_a((xRotation * 3.141593F + xSwing) / 180.0F);
        vector.func_72442_b(-(this.hydraObj.field_70761_aq + yRotation + ySwing) * 3.141593F / 180.0F);
        double dx = this.hydraObj.field_70165_t + vector.field_72450_a;
        double dy = this.hydraObj.field_70163_u + vector.field_72448_b + 3.0D;
        double dz = this.hydraObj.field_70161_v + vector.field_72449_c;

        this.headEntity.func_70107_b(dx, dy, dz);
        this.headEntity.setMouthOpen(this.getCurrentMouthOpen());
    }

    protected void executeAttacks() {
        Entity target;

        if (this.currentState == 9 && this.ticksProgress % 10 == 0) {
            target = this.getHeadLookTarget();
            if (target != null && (target instanceof EntityTFHydraPart || target instanceof EntityDragonPart)) {
                this.endCurrentAction();
            } else {
                EntityTFHydraMortar mortar = new EntityTFHydraMortar(this.headEntity.field_70170_p, this.headEntity);
                Vec3 nearby = this.headEntity.func_70040_Z();
                double dist = 3.5D;
                double px = this.headEntity.field_70165_t + nearby.field_72450_a * dist;
                double py = this.headEntity.field_70163_u + 1.0D + nearby.field_72448_b * dist;
                double pz = this.headEntity.field_70161_v + nearby.field_72449_c * dist;

                mortar.func_70012_b(px, py, pz, 0.0F, 0.0F);
                if (this.targetEntity != null && !this.headEntity.func_70685_l(this.targetEntity)) {
                    mortar.setToBlasting();
                }

                this.headEntity.field_70170_p.func_72889_a((EntityPlayer) null, 1008, (int) this.headEntity.field_70165_t, (int) this.headEntity.field_70163_u, (int) this.headEntity.field_70161_v, 0);
                this.headEntity.field_70170_p.func_72838_d(mortar);
            }
        }

        if (this.headEntity.getState() == 3) {
            List target1 = this.headEntity.field_70170_p.func_72839_b(this.headEntity, this.headEntity.field_70121_D.func_72314_b(0.0D, 1.0D, 0.0D));
            Iterator mortar1 = target1.iterator();

            while (mortar1.hasNext()) {
                Entity nearby1 = (Entity) mortar1.next();

                if (nearby1 instanceof EntityLivingBase && !(nearby1 instanceof EntityTFHydraPart) && !(nearby1 instanceof EntityTFHydra) && !(nearby1 instanceof EntityDragonPart)) {
                    nearby1.func_70097_a(DamageSource.func_76358_a(this.hydraObj), (float) HydraHeadContainer.BITE_DAMAGE);
                }
            }
        }

        if (this.headEntity.getState() == 6) {
            target = this.getHeadLookTarget();
            if (target != null) {
                if (!(target instanceof EntityTFHydraPart) && !(target instanceof EntityDragonPart)) {
                    if (!target.func_70045_F() && target.func_70097_a(DamageSource.field_76372_a, (float) HydraHeadContainer.FLAME_DAMAGE)) {
                        target.func_70015_d(HydraHeadContainer.FLAME_BURN_FACTOR);
                    }
                } else {
                    this.endCurrentAction();
                }
            }
        }

    }

    protected void setDifficultyVariables() {
        if (this.hydraObj.field_70170_p.field_73013_u != EnumDifficulty.HARD) {
            HydraHeadContainer.FLAME_BREATH_TRACKING_SPEED = 0.04D;
        } else {
            HydraHeadContainer.FLAME_BREATH_TRACKING_SPEED = 0.1D;
        }

    }

    private Entity getHeadLookTarget() {
        Entity pointedEntity = null;
        double range = 30.0D;
        Vec3 srcVec = Vec3.func_72443_a(this.headEntity.field_70165_t, this.headEntity.field_70163_u + 1.0D, this.headEntity.field_70161_v);
        Vec3 lookVec = this.headEntity.func_70676_i(1.0F);
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        float f = 3.0F;
        List possibleList = this.headEntity.field_70170_p.func_72839_b(this.headEntity, this.headEntity.field_70121_D.func_72321_a(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range).func_72314_b((double) f, (double) f, (double) f));
        double hitDist = 0.0D;
        Iterator iterator = possibleList.iterator();

        while (iterator.hasNext()) {
            Entity possibleEntity = (Entity) iterator.next();

            if (possibleEntity.func_70067_L() && possibleEntity != this.headEntity && possibleEntity != this.necka && possibleEntity != this.neckb && possibleEntity != this.neckc) {
                float borderSize = possibleEntity.func_70111_Y();
                AxisAlignedBB collisionBB = possibleEntity.field_70121_D.func_72314_b((double) borderSize, (double) borderSize, (double) borderSize);
                MovingObjectPosition interceptPos = collisionBB.func_72327_a(srcVec, destVec);

                if (collisionBB.func_72318_a(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos != null) {
                    double possibleDist = srcVec.func_72438_d(interceptPos.field_72307_f);

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }

        return pointedEntity;
    }

    public void setNextState(int next) {
        this.nextState = next;
    }

    public void endCurrentAction() {
        this.ticksProgress = this.ticksNeeded;
    }

    private EntityTFHydraHead findNearbyHead(String string) {
        List nearbyHeads = this.hydraObj.field_70170_p.func_72872_a(EntityTFHydraHead.class, AxisAlignedBB.func_72330_a(this.hydraObj.field_70165_t, this.hydraObj.field_70163_u, this.hydraObj.field_70161_v, this.hydraObj.field_70165_t + 1.0D, this.hydraObj.field_70163_u + 1.0D, this.hydraObj.field_70161_v + 1.0D).func_72314_b(16.0D, 16.0D, 16.0D));
        Iterator iterator = nearbyHeads.iterator();

        EntityTFHydraHead nearbyHead;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            nearbyHead = (EntityTFHydraHead) iterator.next();
        } while (!nearbyHead.getPartName().equals(string));

        nearbyHead.hydraObj = this.hydraObj;
        return nearbyHead;
    }

    protected float getCurrentNeckLength() {
        float prevLength = this.stateNeckLength[this.headNum][this.prevState];
        float curLength = this.stateNeckLength[this.headNum][this.currentState];
        float progress = (float) this.ticksProgress / (float) this.ticksNeeded;

        return prevLength + (curLength - prevLength) * progress;
    }

    protected float getCurrentHeadXRotation() {
        float prevRotation = this.stateXRotations[this.headNum][this.prevState];
        float currentRotation = this.stateXRotations[this.headNum][this.currentState];
        float progress = (float) this.ticksProgress / (float) this.ticksNeeded;

        return prevRotation + (currentRotation - prevRotation) * progress;
    }

    protected float getCurrentHeadYRotation() {
        float prevRotation = this.stateYRotations[this.headNum][this.prevState];
        float currentRotation = this.stateYRotations[this.headNum][this.currentState];
        float progress = (float) this.ticksProgress / (float) this.ticksNeeded;

        return prevRotation + (currentRotation - prevRotation) * progress;
    }

    protected float getCurrentMouthOpen() {
        float prevOpen = this.stateMouthOpen[this.headNum][this.prevState];
        float curOpen = this.stateMouthOpen[this.headNum][this.currentState];
        float progress = (float) this.ticksProgress / (float) this.ticksNeeded;

        return prevOpen + (curOpen - prevOpen) * progress;
    }

    protected void setNeckPositon(double startX, double startY, double startZ, float startYaw, float startPitch) {
        double endX = this.headEntity.field_70165_t;
        double endY = this.headEntity.field_70163_u;
        double endZ = this.headEntity.field_70161_v;
        float endYaw = this.headEntity.field_70177_z;

        float endPitch;

        for (endPitch = this.headEntity.field_70125_A; startYaw - endYaw < -180.0F; endYaw -= 360.0F) {
            ;
        }

        while (startYaw - endYaw >= 180.0F) {
            endYaw += 360.0F;
        }

        while (startPitch - endPitch < -180.0F) {
            endPitch -= 360.0F;
        }

        while (startPitch - endPitch >= 180.0F) {
            endPitch += 360.0F;
        }

        Vec3 factor;

        if (endPitch > 0.0F) {
            factor = Vec3.func_72443_a(0.0D, 0.0D, -1.0D);
            factor.func_72442_b(-endYaw * 3.141593F / 180.0F);
            endX += factor.field_72450_a;
            endY += factor.field_72448_b;
            endZ += factor.field_72449_c;
        } else {
            factor = this.headEntity.func_70040_Z();
            float dist = 1.0F;

            endX -= factor.field_72450_a * (double) dist;
            endY -= factor.field_72448_b * (double) dist;
            endZ -= factor.field_72449_c * (double) dist;
        }

        float factor1 = 0.0F;

        factor1 = 0.0F;
        this.necka.func_70107_b(endX + (startX - endX) * (double) factor1, endY + (startY - endY) * (double) factor1, endZ + (startZ - endZ) * (double) factor1);
        this.necka.field_70177_z = endYaw + (startYaw - endYaw) * factor1;
        this.necka.field_70125_A = endPitch + (startPitch - endPitch) * factor1;
        factor1 = 0.25F;
        this.neckb.func_70107_b(endX + (startX - endX) * (double) factor1, endY + (startY - endY) * (double) factor1, endZ + (startZ - endZ) * (double) factor1);
        this.neckb.field_70177_z = endYaw + (startYaw - endYaw) * factor1;
        this.neckb.field_70125_A = endPitch + (startPitch - endPitch) * factor1;
        factor1 = 0.5F;
        this.neckc.func_70107_b(endX + (startX - endX) * (double) factor1, endY + (startY - endY) * (double) factor1, endZ + (startZ - endZ) * (double) factor1);
        this.neckc.field_70177_z = endYaw + (startYaw - endYaw) * factor1;
        this.neckc.field_70125_A = endPitch + (startPitch - endPitch) * factor1;
        factor1 = 0.75F;
        this.neckd.func_70107_b(endX + (startX - endX) * (double) factor1, endY + (startY - endY) * (double) factor1, endZ + (startZ - endZ) * (double) factor1);
        this.neckd.field_70177_z = endYaw + (startYaw - endYaw) * factor1;
        this.neckd.field_70125_A = endPitch + (startPitch - endPitch) * factor1;
        factor1 = 1.0F;
        this.necke.func_70107_b(endX + (startX - endX) * (double) factor1, endY + (startY - endY) * (double) factor1, endZ + (startZ - endZ) * (double) factor1);
        this.necke.field_70177_z = endYaw + (startYaw - endYaw) * factor1;
        this.necke.field_70125_A = endPitch + (startPitch - endPitch) * factor1;
    }

    protected void faceIdle(float yawConstraint, float pitchConstraint) {
        float angle = this.hydraObj.field_70177_z * 3.141593F / 180.0F;
        float distance = 30.0F;
        double dx = this.hydraObj.field_70165_t - (double) (MathHelper.func_76126_a(angle) * distance);
        double dy = this.hydraObj.field_70163_u + 3.0D;
        double dz = this.hydraObj.field_70161_v + (double) (MathHelper.func_76134_b(angle) * distance);

        this.faceVec(dx, dy, dz, yawConstraint, pitchConstraint);
    }

    public void faceEntity(Entity entity, float yawConstraint, float pitchConstraint) {
        double yTarget;

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityliving = (EntityLivingBase) entity;

            yTarget = entityliving.field_70163_u + (double) entityliving.func_70047_e();
        } else {
            yTarget = (entity.field_70121_D.field_72338_b + entity.field_70121_D.field_72337_e) / 2.0D;
        }

        this.faceVec(entity.field_70165_t, yTarget, entity.field_70161_v, yawConstraint, pitchConstraint);
        this.targetX = entity.field_70165_t;
        this.targetY = entity.field_70163_u;
        this.targetZ = entity.field_70161_v;
    }

    public void faceVec(double xCoord, double yCoord, double zCoord, float yawConstraint, float pitchConstraint) {
        double xOffset = xCoord - this.headEntity.field_70165_t;
        double zOffset = zCoord - this.headEntity.field_70161_v;
        double yOffset = this.headEntity.field_70163_u + 1.0D - yCoord;
        double distance = (double) MathHelper.func_76133_a(xOffset * xOffset + zOffset * zOffset);
        float xyAngle = (float) (Math.atan2(zOffset, xOffset) * 180.0D / 3.141592653589793D) - 90.0F;
        float zdAngle = (float) (-(Math.atan2(yOffset, distance) * 180.0D / 3.141592653589793D));

        this.headEntity.field_70125_A = -this.updateRotation(this.headEntity.field_70125_A, zdAngle, pitchConstraint);
        this.headEntity.field_70177_z = this.updateRotation(this.headEntity.field_70177_z, xyAngle, yawConstraint);
    }

    private float updateRotation(float current, float intended, float increment) {
        float delta = MathHelper.func_76142_g(intended - current);

        if (delta > increment) {
            delta = increment;
        }

        if (delta < -increment) {
            delta = -increment;
        }

        return MathHelper.func_76142_g(current + delta);
    }

    public Entity getTargetEntity() {
        return this.targetEntity;
    }

    public void setTargetEntity(Entity targetEntity) {
        this.targetEntity = targetEntity;
    }

    public void setHurtTime(int hurtTime) {
        if (this.headEntity != null) {
            this.headEntity.field_70737_aN = hurtTime;
        }

        this.necka.field_70737_aN = hurtTime;
        this.neckb.field_70737_aN = hurtTime;
        this.neckc.field_70737_aN = hurtTime;
        this.neckd.field_70737_aN = hurtTime;
        this.necke.field_70737_aN = hurtTime;
    }

    public boolean shouldRenderHead() {
        return this.headEntity.getState() != 12 && this.headEntity.field_70725_aQ < 20;
    }

    public boolean shouldRenderNeck(int neckNum) {
        int time = 30 + 10 * neckNum;

        return this.headEntity.getState() != 12 && this.headEntity.field_70725_aQ < time;
    }

    public boolean isActive() {
        return this.currentState != 11 && this.currentState != 12;
    }

    public void addDamage(float damageAmount) {
        this.damageTaken = (int) ((float) this.damageTaken + damageAmount);
    }

    public int getDamageTaken() {
        return this.damageTaken;
    }

    public void setRespawnCounter(int count) {
        this.respawnCounter = count;
    }
}
