package twilightforest.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.TwilightForestMod;
import twilightforest.entity.ai.EntityAIStayNearHome;
import twilightforest.entity.ai.EntityAITFThrowRider;
import twilightforest.entity.ai.EntityAITFYetiRampage;
import twilightforest.entity.ai.EntityAITFYetiTired;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class EntityTFYetiAlpha extends EntityMob implements IRangedAttackMob {

    private static final int RAMPAGE_FLAG = 16;
    private static final int TIRED_FLAG = 17;
    private int collisionCounter;
    private boolean canRampage;

    public EntityTFYetiAlpha(World par1World) {
        super(par1World);
        this.func_70105_a(3.8F, 5.0F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, new EntityAITFYetiTired(this, 100));
        this.field_70714_bg.func_75776_a(2, new EntityAITFThrowRider(this, 1.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAIStayNearHome(this, 2.0F));
        this.field_70714_bg.func_75776_a(4, new EntityAITFYetiRampage(this, 10, 180));
        this.field_70714_bg.func_75776_a(5, new EntityAIArrowAttack(this, 1.0D, 40, 40, 40.0F));
        this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 2.0D));
        this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
        this.field_70728_aV = 317;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte) 0));
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.38D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    }

    public void func_70636_d() {
        if (this.field_70153_n != null && this.field_70153_n.func_70093_af()) {
            this.field_70153_n.func_70095_a(false);
        }

        super.func_70636_d();
        if (this.field_70153_n != null) {
            this.func_70671_ap().func_75651_a(this.field_70153_n, 100.0F, 100.0F);
        }

        if (this.field_70132_H) {
            ++this.collisionCounter;
        }

        if (this.collisionCounter >= 15) {
            if (!this.field_70170_p.field_72995_K) {
                this.destroyBlocksInAABB(this.field_70121_D);
            }

            this.collisionCounter = 0;
        }

        if (this.isRampaging()) {
            float i = (float) this.field_70173_aa / 10.0F;

            for (int i1 = 0; i1 < 20; ++i1) {
                this.addSnowEffect(i + (float) (i1 * 50), (float) i1 + i);
            }

            this.field_70721_aZ = (float) ((double) this.field_70721_aZ + 0.6D);
        }

        if (this.isTired()) {
            for (int i = 0; i < 20; ++i) {
                this.field_70170_p.func_72869_a("splash", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 0.5D, this.field_70163_u + (double) this.func_70047_e(), this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 0.5D, (double) ((this.field_70146_Z.nextFloat() - 0.5F) * 0.75F), 0.0D, (double) ((this.field_70146_Z.nextFloat() - 0.5F) * 0.75F));
            }
        }

    }

    private void addSnowEffect(float rotation, float hgt) {
        double px = 3.0D * Math.cos((double) rotation);
        double py = (double) (hgt % 5.0F);
        double pz = 3.0D * Math.sin((double) rotation);

        TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowstuff", this.field_70142_S + px, this.field_70137_T + py, this.field_70136_U + pz, 0.0D, 0.0D, 0.0D);
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        if (super.func_70085_c(par1EntityPlayer)) {
            return true;
        } else if (!this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == par1EntityPlayer)) {
            par1EntityPlayer.func_70078_a(this);
            return true;
        } else {
            return false;
        }
    }

    public boolean func_70652_k(Entity par1Entity) {
        if (this.field_70153_n == null && par1Entity.field_70154_o == null) {
            par1Entity.func_70078_a(this);
        }

        return super.func_70652_k(par1Entity);
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (!this.canRampage && !this.isTired() && par1DamageSource.func_76352_a()) {
            return false;
        } else {
            boolean success = super.func_70097_a(par1DamageSource, par2);

            this.canRampage = true;
            return success;
        }
    }

    protected void func_70628_a(boolean flag, int looting) {
        Item fur = this.func_146068_u();
        int drops;

        if (fur != null) {
            int bombs = 6 + this.field_70146_Z.nextInt(6 + looting);

            for (drops = 0; drops < bombs; ++drops) {
                this.func_145779_a(fur, 1);
            }
        }

        Item item = TFItems.iceBomb;

        drops = 6 + this.field_70146_Z.nextInt(6 + looting);

        for (int d = 0; d < drops; ++d) {
            this.func_145779_a(item, 1);
        }

    }

    protected Item func_146068_u() {
        return TFItems.alphaFur;
    }

    public void func_70043_V() {
        if (this.field_70153_n != null) {
            Vec3 riderPos = this.getRiderPosition();

            this.field_70153_n.func_70107_b(riderPos.field_72450_a, riderPos.field_72448_b, riderPos.field_72449_c);
        }

    }

    public double func_70042_X() {
        return 5.75D;
    }

    public Vec3 getRiderPosition() {
        if (this.field_70153_n != null) {
            float distance = 0.4F;
            double d0 = Math.cos((double) (this.field_70177_z + 90.0F) * 3.141592653589793D / 180.0D) * (double) distance;
            double d1 = Math.sin((double) (this.field_70177_z + 90.0F) * 3.141592653589793D / 180.0D) * (double) distance;

            return Vec3.func_72443_a(this.field_70165_t + d0, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
        } else {
            return Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }

    public boolean canRiderInteract() {
        return true;
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

    public void makeRandomBlockFall() {
        this.makeRandomBlockFall(30);
    }

    private void makeRandomBlockFall(int range) {
        int bx = MathHelper.func_76128_c(this.field_70165_t) + this.func_70681_au().nextInt(range) - this.func_70681_au().nextInt(range);
        int bz = MathHelper.func_76128_c(this.field_70161_v) + this.func_70681_au().nextInt(range) - this.func_70681_au().nextInt(range);
        int by = MathHelper.func_76128_c(this.field_70163_u + (double) this.func_70047_e());

        this.makeBlockFallAbove(bx, bz, by);
    }

    private void makeBlockFallAbove(int bx, int bz, int by) {
        if (this.field_70170_p.func_147437_c(bx, by, bz)) {
            for (int i = 1; i < 30; ++i) {
                if (!this.field_70170_p.func_147437_c(bx, by + i, bz)) {
                    this.makeBlockFall(bx, by + i, bz);
                    break;
                }
            }
        }

    }

    public void makeNearbyBlockFall() {
        this.makeRandomBlockFall(15);
    }

    public void makeBlockAboveTargetFall() {
        if (this.func_70638_az() != null) {
            int bx = MathHelper.func_76128_c(this.func_70638_az().field_70165_t);
            int bz = MathHelper.func_76128_c(this.func_70638_az().field_70161_v);
            int by = MathHelper.func_76128_c(this.func_70638_az().field_70163_u + (double) this.func_70638_az().func_70047_e());

            this.makeBlockFallAbove(bx, bz, by);
        }

    }

    private void makeBlockFall(int bx, int by, int bz) {
        Block currentID = this.field_70170_p.func_147439_a(bx, by, bz);
        int currentMeta = this.field_70170_p.func_72805_g(bx, by, bz);

        this.field_70170_p.func_147449_b(bx, by, bz, Blocks.field_150403_cj);
        this.field_70170_p.func_72926_e(2001, bx, by, bz, Block.func_149682_b(currentID) + (currentMeta << 12));
        EntityTFFallingIce ice = new EntityTFFallingIce(this.field_70170_p, bx, by - 3, bz);

        this.field_70170_p.func_72838_d(ice);
    }

    public void func_82196_d(EntityLivingBase target, float par2) {
        if (!this.canRampage) {
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

    public boolean func_70692_ba() {
        return false;
    }

    public boolean canRampage() {
        return this.canRampage;
    }

    public void setRampaging(boolean par1) {
        this.func_70096_w().func_75692_b(16, Byte.valueOf((byte) (par1 ? 1 : 0)));
    }

    public boolean isRampaging() {
        return this.func_70096_w().func_75683_a(16) == 1;
    }

    public void setTired(boolean par1) {
        this.func_70096_w().func_75692_b(17, Byte.valueOf((byte) (par1 ? 1 : 0)));
        this.canRampage = false;
    }

    public boolean isTired() {
        return this.func_70096_w().func_75683_a(17) == 1;
    }

    protected void func_70069_a(float par1) {
        super.func_70069_a(par1);
        if (this.isRampaging()) {
            this.func_85030_a("random.bow", 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
            int i = MathHelper.func_76128_c(this.field_70165_t);
            int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - (double) this.field_70129_M);
            int k = MathHelper.func_76128_c(this.field_70161_v);

            this.field_70170_p.func_72926_e(2006, i, j, k, 20);
            this.field_70170_p.func_72926_e(2006, i, j, k, 30);
            if (!this.field_70170_p.field_72995_K) {
                this.hitNearbyEntities();
            }
        }

    }

    private void hitNearbyEntities() {
        ArrayList nearby = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(5.0D, 0.0D, 5.0D)));
        Iterator iterator = nearby.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLivingBase) {
                boolean hit = entity.func_70097_a(DamageSource.func_76358_a(this), 5.0F);

                if (hit) {
                    entity.field_70181_x += 0.4000000059604645D;
                }
            }
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightProgressYeti);
        }

        if (!this.field_70170_p.field_72995_K) {
            int dx = MathHelper.func_76128_c(this.field_70165_t);
            int dy = MathHelper.func_76128_c(this.field_70163_u);
            int dz = MathHelper.func_76128_c(this.field_70161_v);

            if (this.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest) {
                ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) this.field_70170_p.field_73011_w).getChunkProvider();
                TFFeature nearbyFeature = ((TFWorldChunkManager) this.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, dz, this.field_70170_p);

                if (nearbyFeature == TFFeature.yetiCave) {
                    chunkProvider.setStructureConquered(dx, dy, dz, true);
                }
            }
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

            this.func_110171_b(hx, hy, hz, 30);
        }

        if (!nbttagcompound.func_74767_n("HasHome")) {
            this.func_110177_bN();
        }

    }
}
