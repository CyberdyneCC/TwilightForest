package twilightforest.entity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.item.ItemTFChainBlock;

public class EntityTFChainBlock extends EntityThrowable implements IEntityMultiPart {

    private static final int MAX_SMASH = 12;
    private static final int MAX_CHAIN = 16;
    private boolean isReturning = false;
    private int blocksSmashed = 0;
    private double velX;
    private double velY;
    private double velZ;
    private boolean isAttached;
    private EntityLivingBase attachedTo;
    public EntityTFGoblinChain chain1;
    public EntityTFGoblinChain chain2;
    public EntityTFGoblinChain chain3;
    public EntityTFGoblinChain chain4;
    public EntityTFGoblinChain chain5;
    public Entity[] partsArray;

    public EntityTFChainBlock(World par1World) {
        super(par1World);
        this.func_70105_a(0.6F, 0.6F);
        this.partsArray = new Entity[] { this.chain1 = new EntityTFGoblinChain(this), this.chain2 = new EntityTFGoblinChain(this), this.chain3 = new EntityTFGoblinChain(this), this.chain4 = new EntityTFGoblinChain(this), this.chain5 = new EntityTFGoblinChain(this)};
    }

    public EntityTFChainBlock(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    public EntityTFChainBlock(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.func_70105_a(0.6F, 0.6F);
        this.isReturning = false;
    }

    public void func_70186_c(double x, double y, double z, float speed, float accuracy) {
        super.func_70186_c(x, y, z, speed, accuracy);
        this.velX = this.field_70159_w;
        this.velY = this.field_70181_x;
        this.velZ = this.field_70179_y;
    }

    protected float func_70185_h() {
        return 0.05F;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (mop.field_72308_g != null && mop.field_72308_g instanceof EntityLivingBase && mop.field_72308_g != this.func_85052_h() && mop.field_72308_g.func_70097_a(DamageSource.func_76365_a((EntityPlayer) this.func_85052_h()), 10.0F)) {
            this.field_70173_aa += 60;
        }

        if (!this.field_70170_p.func_147437_c(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
            if (!this.isReturning) {
                this.field_70170_p.func_72956_a(this, "random.anvil_land", 0.125F, this.field_70146_Z.nextFloat());
            }

            if (!this.field_70170_p.field_72995_K && this.blocksSmashed < 12) {
                if (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149712_f(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) > 0.3F) {
                    double bounce = 0.6D;

                    this.velX *= bounce;
                    this.velY *= bounce;
                    this.velZ *= bounce;
                    switch (mop.field_72310_e) {
                    case 0:
                        if (this.velY > 0.0D) {
                            this.velY *= -bounce;
                        }
                        break;

                    case 1:
                        if (this.velY < 0.0D) {
                            this.velY *= -bounce;
                        }
                        break;

                    case 2:
                        if (this.velZ > 0.0D) {
                            this.velZ *= -bounce;
                        }
                        break;

                    case 3:
                        if (this.velZ < 0.0D) {
                            this.velZ *= -bounce;
                        }
                        break;

                    case 4:
                        if (this.velX > 0.0D) {
                            this.velX *= -bounce;
                        }
                        break;

                    case 5:
                        if (this.velX < 0.0D) {
                            this.velX *= -bounce;
                        }
                    }
                }

                this.affectBlocksInAABB(this.field_70121_D, this.func_85052_h());
            }

            if (!this.field_70170_p.field_72995_K) {
                this.isReturning = true;
            }

            if (this.blocksSmashed > 12 && this.field_70173_aa < 60) {
                this.field_70173_aa += 60;
            }
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

                    if (block != Blocks.field_150350_a && block.func_149638_a(this) < 7.0F && block.func_149712_f(this.field_70170_p, dx, dy, dz) >= 0.0F) {
                        if (entity != null && entity instanceof EntityPlayer) {
                            EntityPlayer player = (EntityPlayer) entity;

                            if (block.canHarvestBlock(player, currentMeta)) {
                                block.func_149636_a(this.field_70170_p, player, dx, dy, dz, currentMeta);
                            }
                        }

                        this.field_70170_p.func_147468_f(dx, dy, dz);
                        this.field_70170_p.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(block) + (currentMeta << 12));
                        ++this.blocksSmashed;
                        hitBlock = true;
                    }
                }
            }
        }

        return hitBlock;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.chain1 != null) {
            this.chain1.func_70071_h_();
            this.chain2.func_70071_h_();
            this.chain3.func_70071_h_();
            this.chain4.func_70071_h_();
            this.chain5.func_70071_h_();
        }

        if (this.func_85052_h() == null && !this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }

        if (this.func_85052_h() != null) {
            float handVec = this.func_70032_d(this.func_85052_h());

            if (!this.isReturning && handVec > 16.0F) {
                this.isReturning = true;
            }

            if (this.isReturning && handVec < 1.0F) {
                if (this.func_85052_h() instanceof EntityPlayer) {
                    ItemTFChainBlock.setChainAsReturned((EntityPlayer) this.func_85052_h());
                }

                this.func_70106_y();
            }
        }

        if (this.isReturning && !this.field_70170_p.field_72995_K && this.func_85052_h() != null) {
            EntityLivingBase entitylivingbase = this.func_85052_h();
            Vec3 sx = Vec3.func_72443_a(entitylivingbase.field_70165_t - this.field_70165_t, entitylivingbase.field_70163_u + (double) entitylivingbase.func_70047_e() - 1.200000023841858D - this.field_70163_u, entitylivingbase.field_70161_v - this.field_70161_v).func_72432_b();
            float nearby = Math.min((float) this.field_70173_aa * 0.03F, 1.0F);

            this.field_70159_w = this.velX * (1.0D - (double) nearby) + sx.field_72450_a * 2.0D * (double) nearby;
            this.field_70181_x = this.velY * (1.0D - (double) nearby) + sx.field_72448_b * 2.0D * (double) nearby - (double) this.func_70185_h();
            this.field_70179_y = this.velZ * (1.0D - (double) nearby) + sx.field_72449_c * 2.0D * (double) nearby;
        }

        if (this.field_70170_p.field_72995_K && !this.isAttached) {
            List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(-this.field_70159_w, -this.field_70181_x, -this.field_70179_y).func_72314_b(2.0D, 2.0D, 2.0D));

            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity) list.get(i);

                if (entity instanceof EntityPlayer) {
                    this.attachedTo = (EntityPlayer) entity;
                }
            }

            this.isAttached = true;
        }

        if (this.attachedTo != null) {
            Vec3 vec3 = this.attachedTo.func_70040_Z();

            vec3.func_72442_b(-0.4F);
            double d0 = this.attachedTo.field_70165_t + vec3.field_72450_a;
            double sy = this.attachedTo.field_70163_u + vec3.field_72448_b - 0.6000000238418579D;
            double sz = this.attachedTo.field_70161_v + vec3.field_72449_c;
            double ox = d0 - this.field_70165_t;
            double oy = sy - this.field_70163_u - 0.25D;
            double oz = sz - this.field_70161_v;

            this.chain1.func_70107_b(d0 - ox * 0.05D, sy - oy * 0.05D, sz - oz * 0.05D);
            this.chain2.func_70107_b(d0 - ox * 0.25D, sy - oy * 0.25D, sz - oz * 0.25D);
            this.chain3.func_70107_b(d0 - ox * 0.45D, sy - oy * 0.45D, sz - oz * 0.45D);
            this.chain4.func_70107_b(d0 - ox * 0.65D, sy - oy * 0.65D, sz - oz * 0.65D);
            this.chain5.func_70107_b(d0 - ox * 0.85D, sy - oy * 0.85D, sz - oz * 0.85D);
        }

    }

    protected float func_70182_d() {
        return 1.5F;
    }

    public World func_82194_d() {
        return this.field_70170_p;
    }

    public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
        return false;
    }

    public Entity[] func_70021_al() {
        return this.partsArray;
    }
}
