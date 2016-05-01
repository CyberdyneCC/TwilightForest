package twilightforest.entity;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.TFGenericPacketHandler;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.item.ItemTFCubeOfAnnihilation;

public class EntityTFCubeOfAnnihilation extends EntityThrowable {

    boolean hasHitObstacle = false;

    public EntityTFCubeOfAnnihilation(World par1World) {
        super(par1World);
        this.func_70105_a(1.1F, 1.0F);
        this.field_70178_ae = true;
    }

    public EntityTFCubeOfAnnihilation(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
        this.func_70105_a(1.0F, 1.0F);
        this.field_70178_ae = true;
    }

    public EntityTFCubeOfAnnihilation(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.func_70105_a(1.0F, 1.0F);
        this.field_70178_ae = true;
    }

    protected float func_70185_h() {
        return 0.0F;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (mop.field_72308_g != null && mop.field_72308_g instanceof EntityLivingBase && mop.field_72308_g.func_70097_a(DamageSource.func_76365_a((EntityPlayer) this.func_85052_h()), 10.0F)) {
            this.field_70173_aa += 60;
        }

        if (!this.field_70170_p.func_147437_c(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) && !this.field_70170_p.field_72995_K) {
            this.affectBlocksInAABB(this.field_70121_D.func_72314_b(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D), this.func_85052_h());
        }

    }

    private boolean affectBlocksInAABB(AxisAlignedBB par1AxisAlignedBB, EntityLivingBase entity) {
        int minX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72340_a);
        int minY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72338_b);
        int minZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72339_c);
        int maxX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72336_d);
        int maxY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72337_e);
        int maxZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72334_f);
        boolean hitBlock = false;

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dy = minY; dy <= maxY; ++dy) {
                for (int dz = minZ; dz <= maxZ; ++dz) {
                    Block block = this.field_70170_p.func_147439_a(dx, dy, dz);
                    int currentMeta = this.field_70170_p.func_72805_g(dx, dy, dz);

                    if (block != Blocks.field_150350_a) {
                        if (this.canAnnihilate(dx, dy, dz, block, currentMeta)) {
                            this.field_70170_p.func_147468_f(dx, dy, dz);
                            this.field_70170_p.func_72956_a(this, "random.fizz", 0.125F, this.field_70146_Z.nextFloat() * 0.25F + 0.75F);
                            this.sendAnnihilateBlockPacket(this.field_70170_p, dx, dy, dz);
                        } else {
                            this.hasHitObstacle = true;
                        }

                        hitBlock = true;
                    }
                }
            }
        }

        return hitBlock;
    }

    private boolean canAnnihilate(int dx, int dy, int dz, Block block, int meta) {
        return block != TFBlocks.deadrock && block != TFBlocks.castleBlock && (block != TFBlocks.castleMagic || meta == 3) && block != TFBlocks.forceField && block != TFBlocks.thorns ? block.func_149638_a(this) < 8.0F && block.func_149712_f(this.field_70170_p, dx, dy, dz) >= 0.0F : true;
    }

    private void sendAnnihilateBlockPacket(World world, int x, int y, int z) {
        FMLProxyPacket message = TFGenericPacketHandler.makeAnnihilateBlockPacket(x, y, z);
        TargetPoint targetPoint = new TargetPoint(world.field_73011_w.field_76574_g, (double) x, (double) y, (double) z, 64.0D);

        TwilightForestMod.genericChannel.sendToAllAround(message, targetPoint);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.func_85052_h() == null) {
                this.func_70106_y();
                return;
            }

            if (this.isReturning()) {
                List destPoint = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));

                if (destPoint.contains(this.func_85052_h()) && !this.field_70170_p.field_72995_K) {
                    if (this.func_85052_h() instanceof EntityPlayer) {
                        ItemTFCubeOfAnnihilation.setCubeAsReturned((EntityPlayer) this.func_85052_h());
                    }

                    this.func_70106_y();
                }
            }

            Vec3 destPoint1 = Vec3.func_72443_a(this.func_85052_h().field_70165_t, this.func_85052_h().field_70163_u + (double) this.func_85052_h().func_70047_e(), this.func_85052_h().field_70161_v);
            Vec3 velocity;
            float currentSpeed;

            if (!this.isReturning()) {
                velocity = this.func_85052_h().func_70040_Z();
                currentSpeed = 16.0F;
                velocity.field_72450_a *= (double) currentSpeed;
                velocity.field_72448_b *= (double) currentSpeed;
                velocity.field_72449_c *= (double) currentSpeed;
                destPoint1.field_72450_a += velocity.field_72450_a;
                destPoint1.field_72448_b += velocity.field_72448_b;
                destPoint1.field_72449_c += velocity.field_72449_c;
            }

            velocity = Vec3.func_72443_a(this.field_70165_t - destPoint1.field_72450_a, this.field_70163_u + (double) (this.field_70131_O / 2.0F) - destPoint1.field_72448_b, this.field_70161_v - destPoint1.field_72449_c);
            this.field_70159_w -= velocity.field_72450_a;
            this.field_70181_x -= velocity.field_72448_b;
            this.field_70179_y -= velocity.field_72449_c;
            currentSpeed = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
            float maxSpeed = 0.5F;

            if (currentSpeed > maxSpeed) {
                this.field_70159_w /= (double) (currentSpeed / maxSpeed);
                this.field_70181_x /= (double) (currentSpeed / maxSpeed);
                this.field_70179_y /= (double) (currentSpeed / maxSpeed);
            } else {
                float slow = 0.5F;

                this.field_70159_w *= (double) slow;
                this.field_70181_x *= (double) slow;
                this.field_70179_y *= (double) slow;
            }

            this.affectBlocksInAABB(this.field_70121_D.func_72314_b(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D), this.func_85052_h());
        }

    }

    public boolean isReturning() {
        if (!this.hasHitObstacle && this.func_85052_h() != null && this.func_85052_h() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) this.func_85052_h();

            return !player.func_71039_bw();
        } else {
            return true;
        }
    }

    protected float func_70182_d() {
        return 1.5F;
    }
}
