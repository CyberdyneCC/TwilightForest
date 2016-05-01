package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityTFNatureBolt extends EntityThrowable {

    private EntityPlayer playerTarget;

    public EntityTFNatureBolt(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFNatureBolt(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityTFNatureBolt(World par1World) {
        super(par1World);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    protected float func_70185_h() {
        return 0.003F;
    }

    public void makeTrail() {
        for (int i = 0; i < 5; ++i) {
            double dx = this.field_70165_t + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dy = this.field_70163_u + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());
            double dz = this.field_70161_v + 0.5D * (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble());

            this.field_70170_p.func_72869_a("happyVillager", dx, dy, dz, 0.0D, 0.0D, 0.0D);
        }

    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.field_72308_g != null && par1MovingObjectPosition.field_72308_g instanceof EntityLivingBase && par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76354_b(this, this.func_85052_h()), 2.0F)) {
            byte dx = (byte) (this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL ? 0 : (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL ? 3 : 7));

            if (dx > 0) {
                ((EntityLivingBase) par1MovingObjectPosition.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, dx * 20, 0));
            }
        }

        int i;

        for (i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(Blocks.field_150362_t) + "_0", this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.05D, this.field_70146_Z.nextDouble() * 0.2D, this.field_70146_Z.nextGaussian() * 0.05D);
        }

        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
            if (par1MovingObjectPosition != null) {
                i = MathHelper.func_76128_c((double) par1MovingObjectPosition.field_72311_b);
                int dy = MathHelper.func_76128_c((double) par1MovingObjectPosition.field_72312_c);
                int dz = MathHelper.func_76128_c((double) par1MovingObjectPosition.field_72309_d);
                Material materialHit = this.field_70170_p.func_147439_a(i, dy, dz).func_149688_o();

                if (materialHit == Material.field_151577_b && this.playerTarget != null) {
                    Items.field_151100_aR.func_77648_a(new ItemStack(Items.field_151100_aR, 1, 15), this.playerTarget, this.field_70170_p, i, dy, dz, 0, 0.0F, 0.0F, 0.0F);
                } else if (materialHit.func_76220_a() && this.canReplaceBlock(this.field_70170_p, i, dy, dz)) {
                    this.field_70170_p.func_147465_d(i, dy, dz, Blocks.field_150362_t, 2, 3);
                }
            }
        }

    }

    private boolean canReplaceBlock(World worldObj, int dx, int dy, int dz) {
        Block blockID = worldObj.func_147439_a(dx, dy, dz);
        float hardness = blockID == null ? -1.0F : blockID.func_149712_f(worldObj, dx, dy, dz);

        return hardness >= 0.0F && hardness < 50.0F;
    }

    public void setTarget(EntityLivingBase attackTarget) {
        if (attackTarget instanceof EntityPlayer) {
            this.playerTarget = (EntityPlayer) attackTarget;
        }

    }
}
