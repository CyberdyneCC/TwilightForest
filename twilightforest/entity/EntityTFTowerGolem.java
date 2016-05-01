package twilightforest.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class EntityTFTowerGolem extends EntityMob {

    private int attackTimer;

    public EntityTFTowerGolem(World par1World) {
        super(par1World);
        this.func_70105_a(1.4F, 2.9F);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(3, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9.0D);
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 2;

        if (i > 20) {
            i = 20;
        }

        return i;
    }

    public boolean func_70652_k(Entity par1Entity) {
        this.attackTimer = 10;
        this.field_70170_p.func_72960_a(this, (byte) 4);
        boolean attackSuccess = super.func_70652_k(par1Entity);

        if (attackSuccess) {
            par1Entity.field_70181_x += 0.4000000059604645D;
        }

        this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
        return attackSuccess;
    }

    protected String func_70639_aQ() {
        return "none";
    }

    protected String func_70621_aR() {
        return "mob.irongolem.hit";
    }

    protected String func_70673_aS() {
        return "mob.irongolem.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void func_82167_n(Entity par1Entity) {
        if (par1Entity instanceof IMob && this.func_70681_au().nextInt(10) == 0) {
            this.func_70624_b((EntityLivingBase) par1Entity);
        }

        super.func_82167_n(par1Entity);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D && this.field_70146_Z.nextInt(5) == 0) {
            int i = MathHelper.func_76128_c(this.field_70165_t);
            int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D - (double) this.field_70129_M);
            int k = MathHelper.func_76128_c(this.field_70161_v);
            Block block = this.field_70170_p.func_147439_a(i, j, k);

            if (block.func_149688_o() != Material.field_151579_a) {
                this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(block) + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + ((double) this.field_70146_Z.nextFloat() - 0.5D) * (double) this.field_70130_N, this.field_70121_D.field_72338_b + 0.1D, this.field_70161_v + ((double) this.field_70146_Z.nextFloat() - 0.5D) * (double) this.field_70130_N, 4.0D * ((double) this.field_70146_Z.nextFloat() - 0.5D), 0.5D, ((double) this.field_70146_Z.nextFloat() - 0.5D) * 4.0D);
            }
        }

        if (this.field_70146_Z.nextBoolean()) {
            this.field_70170_p.func_72869_a("reddust", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double) this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N, 0.0D, 0.0D, 0.0D);
        }

    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
        } else {
            super.func_70103_a(par1);
        }

    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    protected void func_70628_a(boolean par1, int par2) {
        int i = this.field_70146_Z.nextInt(3);

        int i;

        for (i = 0; i < i; ++i) {
            this.func_145779_a(Items.field_151042_j, 1);
        }

        i = this.field_70146_Z.nextInt(3);

        for (i = 0; i < i; ++i) {
            this.func_145779_a(Item.func_150898_a(TFBlocks.towerWood), 1);
        }

    }

    public int func_70641_bl() {
        return 16;
    }
}
