package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class EntityTFTowerTermite extends EntityMob {

    private int allySummonCooldown;

    public EntityTFTowerTermite(World par1World) {
        super(par1World);
        this.func_70105_a(0.3F, 0.7F);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.field_70714_bg.func_75776_a(2, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(4, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27D);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected Entity func_70782_k() {
        double d0 = 8.0D;

        return this.field_70170_p.func_72856_b(this, d0);
    }

    protected String func_70639_aQ() {
        return "mob.silverfish.say";
    }

    protected String func_70621_aR() {
        return "mob.silverfish.hit";
    }

    protected String func_70673_aS() {
        return "mob.silverfish.kill";
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
        if (this.func_85032_ar()) {
            return false;
        } else {
            if (this.allySummonCooldown <= 0 && (par1DamageSource instanceof EntityDamageSource || par1DamageSource == DamageSource.field_76376_m)) {
                this.allySummonCooldown = 20;
            }

            return super.func_70097_a(par1DamageSource, (float) par2);
        }
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.allySummonCooldown > 0) {
            --this.allySummonCooldown;
            if (this.allySummonCooldown == 0) {
                this.tryToSummonAllies();
            }
        }

        if (this.func_70638_az() == null && this.func_70661_as().func_75500_f()) {
            this.tryToBurrow();
        }

    }

    protected void tryToSummonAllies() {
        int sx = MathHelper.func_76128_c(this.field_70165_t);
        int sy = MathHelper.func_76128_c(this.field_70163_u);
        int sz = MathHelper.func_76128_c(this.field_70161_v);
        boolean stopSummoning = false;

        for (int dy = 0; !stopSummoning && dy <= 5 && dy >= -5; dy = dy <= 0 ? 1 - dy : 0 - dy) {
            for (int dx = 0; !stopSummoning && dx <= 10 && dx >= -10; dx = dx <= 0 ? 1 - dx : 0 - dx) {
                for (int dz = 0; !stopSummoning && dz <= 10 && dz >= -10; dz = dz <= 0 ? 1 - dz : 0 - dz) {
                    Block blockID = this.field_70170_p.func_147439_a(sx + dx, sy + dy, sz + dz);
                    int blockMeta = this.field_70170_p.func_72805_g(sx + dx, sy + dy, sz + dz);

                    if (blockID == TFBlocks.towerWood && blockMeta == 4) {
                        this.field_70170_p.func_72926_e(2001, sx + dx, sy + dy, sz + dz, Block.func_149682_b(blockID) + (blockMeta << 12));
                        this.field_70170_p.func_147465_d(sx + dx, sy + dy, sz + dz, Blocks.field_150350_a, 0, 3);
                        TFBlocks.towerWood.func_149664_b(this.field_70170_p, sx + dx, sy + dy, sz + dz, 4);
                        if (this.field_70146_Z.nextBoolean()) {
                            stopSummoning = true;
                            break;
                        }
                    }
                }
            }
        }

    }

    protected void tryToBurrow() {
        int x = MathHelper.func_76128_c(this.field_70165_t);
        int y = MathHelper.func_76128_c(this.field_70163_u + 0.5D);
        int z = MathHelper.func_76128_c(this.field_70161_v);
        int randomFacing = this.field_70146_Z.nextInt(6);

        x += Facing.field_71586_b[randomFacing];
        y += Facing.field_71587_c[randomFacing];
        z += Facing.field_71585_d[randomFacing];
        Block blockIDNearby = this.field_70170_p.func_147439_a(x, y, z);
        int blockMetaNearby = this.field_70170_p.func_72805_g(x, y, z);

        if (this.canBurrowIn(blockIDNearby, blockMetaNearby)) {
            this.field_70170_p.func_147465_d(x, y, z, TFBlocks.towerWood, 4, 3);
            this.func_70656_aK();
            this.func_70106_y();
        }

    }

    protected boolean canBurrowIn(Block blockIDNearby, int blockMetaNearby) {
        return blockIDNearby == TFBlocks.towerWood && blockMetaNearby == 0;
    }

    protected boolean isInfestedBlock(Block blockIDNearby, int blockMetaNearby) {
        return blockIDNearby == TFBlocks.towerWood && blockMetaNearby == 4;
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected Item func_146068_u() {
        return TFItems.borerEssence;
    }

    public void func_70071_h_() {
        this.field_70761_aq = this.field_70177_z;
        super.func_70071_h_();
    }

    public EnumCreatureAttribute func_70668_bt() {
        return EnumCreatureAttribute.ARTHROPOD;
    }
}
