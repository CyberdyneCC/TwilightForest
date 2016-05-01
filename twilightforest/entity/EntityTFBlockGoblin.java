package twilightforest.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.item.TFItems;

public class EntityTFBlockGoblin extends EntityMob implements IEntityMultiPart {

    private static final float CHAIN_SPEED = 16.0F;
    private static final int DATA_CHAINLENGTH = 17;
    private static final int DATA_CHAINPOS = 18;
    int recoilCounter;
    float chainAngle;
    public EntityTFSpikeBlock block;
    public EntityTFGoblinChain chain1;
    public EntityTFGoblinChain chain2;
    public EntityTFGoblinChain chain3;
    public Entity[] partsArray;

    public EntityTFBlockGoblin(World world) {
        super(world);
        this.func_70105_a(0.9F, 1.4F);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAvoidEntity(this, EntityTNTPrimed.class, 2.0F, 0.800000011920929D, 1.5D));
        this.field_70714_bg.func_75776_a(5, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.recoilCounter = 0;
        this.partsArray = new Entity[] { this.block = new EntityTFSpikeBlock(this), this.chain1 = new EntityTFGoblinChain(this), this.chain2 = new EntityTFGoblinChain(this), this.chain3 = new EntityTFGoblinChain(this)};
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(18, Byte.valueOf((byte) 0));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
    }

    protected String func_70639_aQ() {
        return "TwilightForest:mob.redcap.redcap";
    }

    protected String func_70621_aR() {
        return "TwilightForest:mob.redcap.hurt";
    }

    protected String func_70673_aS() {
        return "TwilightForest:mob.redcap.die";
    }

    protected Item getDropItemId() {
        return TFItems.armorShard;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public double getChainYOffset() {
        return 1.5D - (double) this.getChainLength() / 4.0D;
    }

    public Vec3 getChainPosition() {
        return this.getChainPosition(this.getChainAngle(), this.getChainLength());
    }

    public Vec3 getChainPosition(float angle, float distance) {
        double d0 = Math.cos((double) angle * 3.141592653589793D / 180.0D) * (double) distance;
        double d1 = Math.sin((double) angle * 3.141592653589793D / 180.0D) * (double) distance;

        return Vec3.func_72443_a(this.field_70165_t + d0, this.field_70163_u + this.getChainYOffset(), this.field_70161_v + d1);
    }

    public boolean isSwingingChain() {
        return this.field_82175_bq || this.func_70638_az() != null && this.recoilCounter == 0;
    }

    public boolean func_70652_k(Entity par1Entity) {
        this.func_71038_i();
        return false;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.block.func_70071_h_();
        this.chain1.func_70071_h_();
        this.chain2.func_70071_h_();
        this.chain3.func_70071_h_();
        if (this.recoilCounter > 0) {
            --this.recoilCounter;
        }

        this.chainAngle += 16.0F;
        this.chainAngle %= 360.0F;
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(17, Byte.valueOf((byte) ((int) Math.floor((double) (this.getChainLength() * 127.0F)))));
            this.field_70180_af.func_75692_b(18, Byte.valueOf((byte) ((int) Math.floor((double) (this.getChainAngle() / 360.0F * 255.0F)))));
        } else if (Math.abs(this.chainAngle - this.getChainAngle()) > 32.0F) {
            this.chainAngle = this.getChainAngle();
        }

        Vec3 blockPos = this.getChainPosition();

        this.block.func_70107_b(blockPos.field_72450_a, blockPos.field_72448_b, blockPos.field_72449_c);
        this.block.field_70177_z = this.getChainAngle();
        double sx = this.field_70165_t;
        double sy = this.field_70163_u + (double) this.field_70131_O - 0.1D;
        double sz = this.field_70161_v;
        double ox = sx - blockPos.field_72450_a;
        double oy = sy - blockPos.field_72448_b - (double) this.block.field_70131_O / 3.0D;
        double oz = sz - blockPos.field_72449_c;

        this.chain1.func_70107_b(sx - ox * 0.4D, sy - oy * 0.4D, sz - oz * 0.4D);
        this.chain2.func_70107_b(sx - ox * 0.5D, sy - oy * 0.5D, sz - oz * 0.5D);
        this.chain3.func_70107_b(sx - ox * 0.6D, sy - oy * 0.6D, sz - oz * 0.6D);
        if (!this.field_70170_p.field_72995_K && this.isSwingingChain()) {
            this.applyBlockCollisions(this.block);
        }

    }

    protected void applyBlockCollisions(Entity collider) {
        List list = this.field_70170_p.func_72839_b(collider, collider.field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity.func_70104_M()) {
                    this.applyBlockCollision(collider, entity);
                }
            }
        }

    }

    protected void applyBlockCollision(Entity collider, Entity collided) {
        if (collided != this) {
            collided.func_70108_f(collider);
            if (collided instanceof EntityLivingBase) {
                boolean attackSuccess = super.func_70652_k(collided);

                if (attackSuccess) {
                    collided.field_70181_x += 0.4000000059604645D;
                    this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
                    this.recoilCounter = 40;
                }
            }
        }

    }

    public float getChainAngle() {
        return !this.field_70170_p.field_72995_K ? this.chainAngle : (float) (this.field_70180_af.func_75683_a(18) & 255) / 255.0F * 360.0F;
    }

    public float getChainLength() {
        return !this.field_70170_p.field_72995_K ? (this.isSwingingChain() ? 0.9F : 0.3F) : (float) (this.field_70180_af.func_75683_a(17) & 255) / 127.0F;
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    public boolean func_70965_a(EntityDragonPart entitydragonpart, DamageSource damagesource, float i) {
        return false;
    }

    public Entity[] func_70021_al() {
        return this.partsArray;
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 11;

        if (i > 20) {
            i = 20;
        }

        return i;
    }
}
