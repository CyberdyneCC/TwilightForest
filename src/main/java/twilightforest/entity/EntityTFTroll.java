package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import twilightforest.TFAchievementPage;
import twilightforest.block.TFBlocks;
import twilightforest.entity.ai.EntityAITFCollideAttackFixed;
import twilightforest.entity.boss.EntityTFIceBomb;
import twilightforest.item.TFItems;

public class EntityTFTroll extends EntityMob implements IRangedAttackMob {

    private static final int ROCK_FLAG = 16;
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAITFCollideAttackFixed aiAttackOnCollide = new EntityAITFCollideAttackFixed(this, EntityPlayer.class, 1.2D, false);

    public EntityTFTroll(World par1World) {
        super(par1World);
        this.func_70105_a(1.4F, 2.4F);
        this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIRestrictSun(this));
        this.field_70714_bg.func_75776_a(3, new EntityAIFleeSun(this, 1.0D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        if (par1World != null && !par1World.field_72995_K) {
            this.setCombatTask();
        }

    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.28D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte) 0));
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public boolean hasRock() {
        return (this.field_70180_af.func_75683_a(16) & 2) != 0;
    }

    public void setHasRock(boolean rock) {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (rock) {
            this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte) (b0 & -3)));
        }

        this.setCombatTask();
    }

    public boolean func_70652_k(Entity par1Entity) {
        this.func_71038_i();
        return super.func_70652_k(par1Entity);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("HasRock", this.hasRock());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setHasRock(par1NBTTagCompound.func_74767_n("HasRock"));
    }

    public void setCombatTask() {
        this.field_70714_bg.func_85156_a(this.aiAttackOnCollide);
        this.field_70714_bg.func_85156_a(this.aiArrowAttack);
        if (this.hasRock()) {
            this.field_70714_bg.func_75776_a(4, this.aiArrowAttack);
        } else {
            this.field_70714_bg.func_75776_a(4, this.aiAttackOnCollide);
        }

    }

    protected void func_70609_aI() {
        super.func_70609_aI();
        if (this.field_70725_aQ % 5 == 0) {
            this.ripenTrollBerNearby(this.field_70725_aQ / 5);
        }

        if (this.field_70725_aQ == 1) {
            ;
        }

    }

    private void ripenTrollBerNearby(int offset) {
        int sx = MathHelper.func_76128_c(this.field_70165_t);
        int sy = MathHelper.func_76128_c(this.field_70163_u);
        int sz = MathHelper.func_76128_c(this.field_70161_v);
        byte range = 12;

        for (int dx = -range; dx < range; ++dx) {
            for (int dy = -range; dy < range; ++dy) {
                for (int dz = -range; dz < range; ++dz) {
                    int cx = sx + dx;
                    int cy = sy + dy;
                    int cz = sz + dz;

                    this.ripenBer(offset, cx, cy, cz);
                }
            }
        }

    }

    private void ripenBer(int offset, int cx, int cy, int cz) {
        if (this.field_70170_p.func_147439_a(cx, cy, cz) == TFBlocks.unripeTrollBer && this.field_70146_Z.nextBoolean() && Math.abs(cx + cy + cz) % 5 == offset) {
            this.field_70170_p.func_147449_b(cx, cy, cz, TFBlocks.trollBer);
            this.field_70170_p.func_72926_e(2004, cx, cy, cz, 0);
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    private void makeTrollStoneInAABB(AxisAlignedBB par1AxisAlignedBB) {
        int minX = MathHelper.func_76143_f(par1AxisAlignedBB.field_72340_a);
        int minY = MathHelper.func_76143_f(par1AxisAlignedBB.field_72338_b);
        int minZ = MathHelper.func_76143_f(par1AxisAlignedBB.field_72339_c);
        int maxX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72336_d);
        int maxY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72337_e);
        int maxZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72334_f);

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dy = minY; dy <= maxY; ++dy) {
                for (int dz = minZ; dz <= maxZ; ++dz) {
                    Block currentID = this.field_70170_p.func_147439_a(dx, dy, dz);

                    if (currentID == Blocks.field_150350_a) {
                        this.field_70170_p.func_147449_b(dx, dy, dz, TFBlocks.trollSteinn);
                        this.field_70170_p.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(TFBlocks.trollSteinn) + 0);
                    }
                }
            }
        }

    }

    protected Item func_146068_u() {
        return null;
    }

    protected void func_70600_l(int p_70600_1_) {
        this.func_145779_a(TFItems.magicBeans, 1);
    }

    public void func_82196_d(EntityLivingBase target, float par2) {
        if (this.hasRock()) {
            EntityTFIceBomb ice = new EntityTFIceBomb(this.field_70170_p, this);
            double d0 = target.field_70165_t - this.field_70165_t;
            double d1 = target.field_70163_u + (double) target.func_70047_e() - 1.100000023841858D - target.field_70163_u;
            double d2 = target.field_70161_v - this.field_70161_v;
            float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2) * 0.2F;

            ice.func_70186_c(d0, d1 + (double) f1, d2, 0.75F, 12.0F);
            this.func_85030_a("random.bow", 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
            this.field_70170_p.func_72838_d(ice);
        }

    }

    public void func_71038_i() {
        if (!this.field_82175_bq || this.field_110158_av >= this.getArmSwingAnimationEnd() / 2 || this.field_110158_av < 0) {
            this.field_110158_av = -1;
            this.field_82175_bq = true;
            if (this.field_70170_p instanceof WorldServer) {
                ((WorldServer) this.field_70170_p).func_73039_n().func_151247_a(this, new S0BPacketAnimation(this, 0));
            }
        }

    }

    protected void func_82168_bl() {
        int maxSwing = this.getArmSwingAnimationEnd();

        if (this.field_82175_bq) {
            ++this.field_110158_av;
            if (this.field_110158_av >= maxSwing) {
                this.field_110158_av = 0;
                this.field_82175_bq = false;
            }
        } else {
            this.field_110158_av = 0;
        }

        this.field_70733_aJ = (float) this.field_110158_av / (float) maxSwing;
    }

    private int getArmSwingAnimationEnd() {
        return 6;
    }
}
