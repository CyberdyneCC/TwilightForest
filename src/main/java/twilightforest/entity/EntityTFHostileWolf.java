package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;

public class EntityTFHostileWolf extends EntityWolf implements IMob {

    public EntityTFHostileWolf(World world) {
        super(world);
        this.func_70916_h(true);
        this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    public EntityTFHostileWolf(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
            this.func_70106_y();
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    public boolean func_70601_bi() {
        int chunkX = MathHelper.func_76128_c(this.field_70165_t) >> 4;
        int chunkZ = MathHelper.func_76128_c(this.field_70161_v) >> 4;

        return TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p) == TFFeature.hedgeMaze ? this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).size() == 0 && !this.field_70170_p.func_72953_d(this.field_70121_D) : this.isValidLightLevel() && this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).size() == 0 && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    protected boolean isValidLightLevel() {
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32)) {
            return false;
        } else {
            int l = this.field_70170_p.func_72957_l(i, j, k);

            if (this.field_70170_p.func_72911_I()) {
                int i1 = this.field_70170_p.field_73008_k;

                this.field_70170_p.field_73008_k = 10;
                l = this.field_70170_p.func_72957_l(i, j, k);
                this.field_70170_p.field_73008_k = i1;
            }

            return l <= this.field_70146_Z.nextInt(8);
        }
    }

    public boolean func_70877_b(ItemStack par1ItemStack) {
        return false;
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }
}
