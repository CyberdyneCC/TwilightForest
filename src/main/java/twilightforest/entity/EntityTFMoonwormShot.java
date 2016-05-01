package twilightforest.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class EntityTFMoonwormShot extends EntityThrowable {

    public EntityTFMoonwormShot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFMoonwormShot(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    public EntityTFMoonwormShot(World par1World) {
        super(par1World);
    }

    protected float func_40077_c() {
        return 0.5F;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.makeTrail();
    }

    public float func_70013_c(float par1) {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float par1) {
        return 15728880;
    }

    public void makeTrail() {}

    public boolean func_70067_L() {
        return true;
    }

    public float func_70111_Y() {
        return 1.0F;
    }

    protected float func_70185_h() {
        return 0.03F;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (mop.field_72313_a == MovingObjectType.BLOCK && !this.field_70170_p.field_72995_K) {
            TFItems.moonwormQueen.func_77648_a((ItemStack) null, (EntityPlayer) this.func_85052_h(), this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, 0.0F, 0.0F, 0.0F);
        }

        if (mop.field_72308_g != null) {
            mop.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.func_85052_h()), 0.0F);
        }

        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("blockcrack_" + Block.func_149682_b(TFBlocks.moonworm) + "_0", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0D, 0.0D, 0.0D);
        }

        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

    }
}
