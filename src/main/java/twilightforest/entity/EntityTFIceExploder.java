package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;

public class EntityTFIceExploder extends EntityMob {

    private static final float EXPLOSION_RADIUS = 1.0F;

    public EntityTFIceExploder(World par1World) {
        super(par1World);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.func_70105_a(0.8F, 1.8F);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected Item func_146068_u() {
        return Items.field_151126_ay;
    }

    public void func_70636_d() {
        super.func_70636_d();

        for (int i = 0; i < 3; ++i) {
            float px = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;
            float py = this.func_70047_e() + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5F;
            float pz = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3F;

            TwilightForestMod.proxy.spawnParticle(this.field_70170_p, "snowguardian", this.field_70142_S + (double) px, this.field_70137_T + (double) py, this.field_70136_U + (double) pz, 0.0D, 0.0D, 0.0D);
        }

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

    public float func_70047_e() {
        return this.field_70131_O * 0.6F;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    protected void func_70609_aI() {
        ++this.field_70725_aQ;
        if (this.field_70725_aQ == 60) {
            boolean flag = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");

            this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, flag);
            if (flag) {
                this.detonate();
            }

            int i;

            if (!this.field_70170_p.field_72995_K && (this.field_70718_bc > 0 || this.func_70684_aJ()) && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
                i = this.func_70693_a(this.field_70717_bb);

                while (i > 0) {
                    int d2 = EntityXPOrb.func_70527_a(i);

                    i -= d2;
                    this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, d2));
                }
            }

            this.func_70106_y();

            for (i = 0; i < 20; ++i) {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02D;

                this.field_70170_p.func_72869_a("explode", this.field_70165_t + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, this.field_70163_u + (double) (this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double) (this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double) this.field_70130_N, d0, d0, d1);
            }
        }

    }

    private void detonate() {
        byte range = 4;
        int sx = MathHelper.func_76128_c(this.field_70165_t);
        int sy = MathHelper.func_76128_c(this.field_70163_u);
        int sz = MathHelper.func_76128_c(this.field_70161_v);

        for (int dx = -range; dx <= range; ++dx) {
            for (int dy = -range; dy <= range; ++dy) {
                for (int dz = -range; dz <= range; ++dz) {
                    double distance = Math.sqrt((double) (dx * dx + dy * dy + dz * dz));
                    float randRange = (float) range + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 2.0F;

                    if (distance < (double) randRange) {
                        this.transformBlock(sx + dx, sy + dy, sz + dz);
                    }
                }
            }
        }

    }

    private void transformBlock(int x, int y, int z) {
        Block block = this.field_70170_p.func_147439_a(x, y, z);
        int meta = this.field_70170_p.func_72805_g(x, y, z);

        if (block.func_149638_a(this) < 8.0F && block.func_149712_f(this.field_70170_p, x, y, z) >= 0.0F) {
            int blockColor = 16777215;

            try {
                blockColor = block.func_149720_d(this.field_70170_p, x, y, z);
            } catch (NoSuchMethodError nosuchmethoderror) {
                ;
            }

            if (blockColor == 16777215) {
                blockColor = block.func_149728_f(meta).field_76291_p;
            }

            if (this.shouldTransformGlass(block, x, y, z)) {
                this.field_70170_p.func_147465_d(x, y, z, Blocks.field_150399_cn, this.getMetaForColor(blockColor), 3);
            } else if (this.shouldTransformClay(block, x, y, z)) {
                this.field_70170_p.func_147465_d(x, y, z, Blocks.field_150406_ce, this.getMetaForColor(blockColor), 3);
            }
        }

    }

    private boolean shouldTransformClay(Block block, int x, int y, int z) {
        return block.isNormalCube(this.field_70170_p, x, y, z);
    }

    private boolean shouldTransformGlass(Block block, int x, int y, int z) {
        return block != Blocks.field_150350_a && this.isBlockNormalBounds(block, x, y, z) && (!block.func_149688_o().func_76218_k() || block.isLeaves(this.field_70170_p, x, y, z) || block == Blocks.field_150432_aD || block == TFBlocks.auroraBlock);
    }

    private boolean isBlockNormalBounds(Block block, int x, int y, int z) {
        return block.func_149753_y() == 1.0D && block.func_149669_A() == 1.0D && block.func_149693_C() == 1.0D && block.func_149704_x() == 0.0D && block.func_149665_z() == 0.0D && block.func_149706_B() == 0.0D;
    }

    private int getMetaForColor(int blockColor) {
        int red = blockColor >> 16 & 255;
        int green = blockColor >> 8 & 255;
        int blue = blockColor & 255;
        int bestColor = 0;
        int bestDifference = 1024;

        for (int i = 0; i < 15; ++i) {
            int iColor = Blocks.field_150325_L.func_149728_f(i).field_76291_p;
            int iRed = iColor >> 16 & 255;
            int iGreen = iColor >> 8 & 255;
            int iBlue = iColor & 255;
            int difference = Math.abs(red - iRed) + Math.abs(green - iGreen) + Math.abs(blue - iBlue);

            if (difference < bestDifference) {
                bestColor = i;
                bestDifference = difference;
            }
        }

        return bestColor;
    }

    public int func_70641_bl() {
        return 8;
    }
}
