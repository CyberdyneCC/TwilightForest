package twilightforest.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;

public class EntityTFLichMinion extends EntityZombie {

    EntityTFLich master;

    public EntityTFLichMinion(World par1World) {
        super(par1World);
        this.master = null;
    }

    public EntityTFLichMinion(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y, z);
    }

    public EntityTFLichMinion(World par1World, EntityTFLich entityTFLich) {
        super(par1World);
        this.master = entityTFLich;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        EntityLivingBase prevTarget = this.func_70638_az();

        if (super.func_70097_a(par1DamageSource, par2)) {
            if (par1DamageSource.func_76346_g() instanceof EntityTFLich) {
                this.func_70624_b(prevTarget);
                this.func_70604_c(prevTarget);
                this.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 200, 4));
                this.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 200, 1));
            }

            return true;
        } else {
            return false;
        }
    }

    public void func_70636_d() {
        if (this.master == null) {
            this.findNewMaster();
        }

        if (this.master == null || this.master.field_70128_L) {
            this.func_70606_j(0.0F);
        }

        super.func_70636_d();
    }

    private void findNewMaster() {
        List nearbyLiches = this.field_70170_p.func_72872_a(EntityTFLich.class, AxisAlignedBB.func_72330_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).func_72314_b(32.0D, 16.0D, 32.0D));
        Iterator iterator = nearbyLiches.iterator();

        while (iterator.hasNext()) {
            EntityTFLich nearbyLich = (EntityTFLich) iterator.next();

            if (!nearbyLich.isShadowClone() && nearbyLich.wantsNewMinion(this)) {
                this.master = nearbyLich;
                this.master.makeBlackMagicTrail(this.field_70165_t, this.field_70163_u + (double) this.func_70047_e(), this.field_70161_v, this.master.field_70165_t, this.master.field_70163_u + (double) this.master.func_70047_e(), this.master.field_70161_v);
                this.func_70624_b(this.master.func_70638_az());
                break;
            }
        }

    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (par1DamageSource.func_76364_f() instanceof EntityPlayer) {
            ((EntityPlayer) par1DamageSource.func_76364_f()).func_71029_a(TFAchievementPage.twilightHunter);
        }

    }

    protected void func_82164_bB() {
        float[] equipChances = new float[] { 0.0F, 0.25F, 0.75F, 1.0F};

        if (this.field_70146_Z.nextFloat() < equipChances[2]) {
            int i = this.field_70146_Z.nextInt(2);
            float f = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.1F : 0.25F;

            if (this.field_70146_Z.nextFloat() < 0.07F) {
                ++i;
            }

            if (this.field_70146_Z.nextFloat() < 0.07F) {
                ++i;
            }

            if (this.field_70146_Z.nextFloat() < 0.07F) {
                ++i;
            }

            for (int j = 3; j >= 0; --j) {
                ItemStack itemstack = this.func_130225_q(j);

                if (j < 3 && this.field_70146_Z.nextFloat() < f) {
                    break;
                }

                if (itemstack == null) {
                    Item item = func_82161_a(j + 1, i);

                    if (item != null) {
                        this.func_70062_b(j + 1, new ItemStack(item));
                    }
                }
            }
        }

    }
}
