package twilightforest.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityTFMiniGhast extends EntityTFTowerGhast {

    public boolean isMinion = false;

    public EntityTFMiniGhast(World par1World) {
        super(par1World);
        this.func_70105_a(1.1F, 1.5F);
        this.aggroRange = 16.0F;
        this.stareRange = 8.0F;
        this.wanderFactor = 4.0F;
    }

    public int func_70641_bl() {
        return 16;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.isMinion ? 6.0D : 10.0D);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    protected boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
        ItemStack playerHeadArmor = par1EntityPlayer.field_71071_by.field_70460_b[3];

        if (playerHeadArmor != null && playerHeadArmor.func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK)) {
            return false;
        } else if (par1EntityPlayer.func_70032_d(this) <= 3.5F && par1EntityPlayer.func_70685_l(this)) {
            return true;
        } else {
            Vec3 vec3 = par1EntityPlayer.func_70676_i(1.0F).func_72432_b();
            Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t - par1EntityPlayer.field_70165_t, this.field_70121_D.field_72338_b + (double) (this.field_70131_O / 2.0F) - (par1EntityPlayer.field_70163_u + (double) par1EntityPlayer.func_70047_e()), this.field_70161_v - par1EntityPlayer.field_70161_v);
            double d0 = vec31.func_72433_c();

            vec31 = vec31.func_72432_b();
            double d1 = vec3.func_72430_b(vec31);

            return d1 > 1.0D - 0.025D / d0 ? par1EntityPlayer.func_70685_l(this) : false;
        }
    }

    protected boolean isValidLightLevel() {
        if (this.isMinion) {
            return true;
        } else {
            int myX = MathHelper.func_76128_c(this.field_70165_t);
            int myY = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
            int myZ = MathHelper.func_76128_c(this.field_70161_v);

            if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, myX, myY, myZ) > this.field_70146_Z.nextInt(32)) {
                return false;
            } else {
                int lightLevel = this.field_70170_p.func_72957_l(myX, myY, myZ);

                if (this.field_70170_p.func_72911_I()) {
                    int i = this.field_70170_p.field_73008_k;

                    this.field_70170_p.field_73008_k = 10;
                    lightLevel = this.field_70170_p.func_72957_l(myX, myY, myZ);
                    this.field_70170_p.field_73008_k = i;
                }

                return lightLevel <= this.field_70146_Z.nextInt(8);
            }
        }
    }

    public void makeBossMinion() {
        this.wanderFactor = 0.005F;
        this.isMinion = true;
        this.func_70606_j(this.func_110138_aP());
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (!this.isMinion) {
            super.func_70628_a(par1, par2);
        }

    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("isMinion", this.isMinion);
        super.func_70014_b(nbttagcompound);
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        if (nbttagcompound.func_74767_n("isMinion")) {
            this.makeBossMinion();
        }

    }
}
