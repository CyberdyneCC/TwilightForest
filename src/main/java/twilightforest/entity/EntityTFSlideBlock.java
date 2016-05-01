package twilightforest.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTFSlideBlock extends Entity implements IEntityAdditionalSpawnData {

    private static final int WARMUP_TIME = 20;
    private Block myBlock;
    private int myMeta;
    private short slideTime;
    private boolean canDropItem = true;
    float moveX;
    float moveY;
    float moveZ;

    public EntityTFSlideBlock(World world) {
        super(world);
        this.field_70156_m = true;
        this.field_70144_Y = 1.0F;
        this.func_70105_a(0.98F, 0.98F);
    }

    public EntityTFSlideBlock(World world, double x, double y, double z, Block block, int meta) {
        super(world);
        this.myBlock = block;
        this.myMeta = meta;
        this.field_70156_m = true;
        this.field_70144_Y = 1.0F;
        this.func_70105_a(0.98F, 0.98F);
        this.func_70107_b(x, y, z);
        this.field_70159_w = 0.0D;
        this.field_70181_x = 0.0D;
        this.field_70179_y = 0.0D;
        this.field_70169_q = x;
        this.field_70167_r = y;
        this.field_70166_s = z;
        this.determineMoveDirection();
    }

    private void determineMoveDirection() {
        this.moveX = 0.0F;
        this.moveY = 0.0F;
        this.moveZ = 0.0F;
        int bx = MathHelper.func_76128_c(this.field_70165_t);
        int by = MathHelper.func_76128_c(this.field_70163_u);
        int bz = MathHelper.func_76128_c(this.field_70161_v);

        if ((this.myMeta & 12) == 4) {
            if (!this.field_70170_p.func_147437_c(bx, by + 1, bz) && this.field_70170_p.func_147437_c(bx, by - 1, bz)) {
                this.moveY = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by - 1, bz) && this.field_70170_p.func_147437_c(bx, by + 1, bz)) {
                this.moveY = 1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by, bz + 1) && this.field_70170_p.func_147437_c(bx, by, bz - 1)) {
                this.moveZ = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by, bz - 1) && this.field_70170_p.func_147437_c(bx, by, bz + 1)) {
                this.moveZ = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by - 1, bz)) {
                this.moveY = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by + 1, bz)) {
                this.moveY = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by, bz - 1)) {
                this.moveZ = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by, bz + 1)) {
                this.moveZ = 1.0F;
            }
        } else if ((this.myMeta & 12) == 8) {
            if (!this.field_70170_p.func_147437_c(bx, by + 1, bz) && this.field_70170_p.func_147437_c(bx, by - 1, bz)) {
                this.moveY = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by - 1, bz) && this.field_70170_p.func_147437_c(bx, by + 1, bz)) {
                this.moveY = 1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx + 1, by, bz) && this.field_70170_p.func_147437_c(bx - 1, by, bz)) {
                this.moveX = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx - 1, by, bz) && this.field_70170_p.func_147437_c(bx + 1, by, bz)) {
                this.moveX = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by - 1, bz)) {
                this.moveY = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by + 1, bz)) {
                this.moveY = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx - 1, by, bz)) {
                this.moveX = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx + 1, by, bz)) {
                this.moveX = 1.0F;
            }
        } else if ((this.myMeta & 12) == 0) {
            if (!this.field_70170_p.func_147437_c(bx + 1, by, bz) && this.field_70170_p.func_147437_c(bx - 1, by, bz)) {
                this.moveX = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx - 1, by, bz) && this.field_70170_p.func_147437_c(bx + 1, by, bz)) {
                this.moveX = 1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by, bz + 1) && this.field_70170_p.func_147437_c(bx, by, bz - 1)) {
                this.moveZ = -1.0F;
            } else if (!this.field_70170_p.func_147437_c(bx, by, bz - 1) && this.field_70170_p.func_147437_c(bx, by, bz + 1)) {
                this.moveZ = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx - 1, by, bz)) {
                this.moveX = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx + 1, by, bz)) {
                this.moveX = 1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by, bz - 1)) {
                this.moveZ = -1.0F;
            } else if (this.field_70170_p.func_147437_c(bx, by, bz + 1)) {
                this.moveZ = 1.0F;
            }
        }

    }

    protected void func_70088_a() {}

    protected boolean func_70041_e_() {
        return false;
    }

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        return 0.0F;
    }

    public void func_70071_h_() {
        if (this.myBlock != null && this.myBlock.func_149688_o() != Material.field_151579_a) {
            this.field_70169_q = this.field_70165_t;
            this.field_70167_r = this.field_70163_u;
            this.field_70166_s = this.field_70161_v;
            ++this.slideTime;
            if (this.slideTime > 20) {
                this.field_70159_w += (double) this.moveX * 0.03999999910593033D;
                this.field_70181_x += (double) this.moveY * 0.03999999910593033D;
                this.field_70179_y += (double) this.moveZ * 0.03999999910593033D;
                this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }

            this.field_70159_w *= 0.9800000190734863D;
            this.field_70181_x *= 0.9800000190734863D;
            this.field_70179_y *= 0.9800000190734863D;
            if (this.slideTime % 5 == 0) {
                this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "TwilightForest:random.slider", 1.0F, 0.9F + this.field_70146_Z.nextFloat() * 0.4F);
            }

            if (!this.field_70170_p.field_72995_K) {
                int bx = MathHelper.func_76128_c(this.field_70165_t);
                int by = MathHelper.func_76128_c(this.field_70163_u);
                int bz = MathHelper.func_76128_c(this.field_70161_v);

                if (this.slideTime == 1) {
                    if (this.field_70170_p.func_147439_a(bx, by, bz) != this.myBlock) {
                        this.func_70106_y();
                        return;
                    }

                    this.field_70170_p.func_147468_f(bx, by, bz);
                }

                if (this.slideTime == 60) {
                    this.field_70159_w = 0.0D;
                    this.field_70181_x = 0.0D;
                    this.field_70179_y = 0.0D;
                    this.moveX *= -1.0F;
                    this.moveY *= -1.0F;
                    this.moveZ *= -1.0F;
                }

                if (!this.field_70132_H && !this.isStopped()) {
                    if (this.slideTime > 100 && !this.field_70170_p.field_72995_K && (by < 1 || by > 256) || this.slideTime > 600) {
                        if (this.canDropItem) {
                            this.func_70099_a(new ItemStack(this.myBlock, 1, this.myBlock.func_149692_a(this.myMeta)), 0.0F);
                        }

                        this.func_70106_y();
                    }
                } else {
                    this.field_70159_w *= 0.699999988079071D;
                    this.field_70179_y *= 0.699999988079071D;
                    this.field_70181_x *= 0.699999988079071D;
                    this.func_70106_y();
                    if ((!this.field_70170_p.func_147472_a(this.myBlock, bx, by, bz, true, 1, (Entity) null, (ItemStack) null) || !this.field_70170_p.func_147465_d(bx, by, bz, this.myBlock, this.myMeta, 3)) && this.canDropItem) {
                        this.func_70099_a(new ItemStack(this.myBlock, 1, this.myBlock.func_149692_a(this.myMeta)), 0.0F);
                    }
                }

                this.damageKnockbackEntities(this.field_70170_p.func_72839_b(this, this.field_70121_D), this);
            }
        } else {
            this.func_70106_y();
        }

    }

    private void damageKnockbackEntities(List par1List, Entity me) {
        Iterator iterator = par1List.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (entity instanceof EntityLivingBase) {
                entity.func_70097_a(DamageSource.field_76377_j, 5.0F);
                double kx = (this.field_70165_t - entity.field_70165_t) * 2.0D;
                double kz = (this.field_70161_v - entity.field_70161_v) * 2.0D;

                ((EntityLivingBase) entity).func_70653_a(this, 5.0F, kx, kz);
            }
        }

    }

    public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
        return null;
    }

    private boolean isStopped() {
        return this.moveX == 0.0F && this.moveY == 0.0F && this.moveZ == 0.0F;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_90999_ad() {
        return false;
    }

    protected void func_70037_a(NBTTagCompound nbtTagCompound) {
        this.myBlock = Block.func_149729_e(nbtTagCompound.func_74762_e("TileID"));
        this.myMeta = nbtTagCompound.func_74771_c("Meta");
        this.slideTime = nbtTagCompound.func_74765_d("Time");
        this.moveX = nbtTagCompound.func_74760_g("MoveX");
        this.moveY = nbtTagCompound.func_74760_g("MoveY");
        this.moveZ = nbtTagCompound.func_74760_g("MoveZ");
    }

    protected void func_70014_b(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.func_74768_a("TileID", Block.func_149682_b(this.myBlock));
        nbtTagCompound.func_74774_a("Meta", (byte) this.myMeta);
        nbtTagCompound.func_74777_a("Time", this.slideTime);
        nbtTagCompound.func_74776_a("MoveX", this.moveX);
        nbtTagCompound.func_74776_a("MoveY", this.moveY);
        nbtTagCompound.func_74776_a("MoveZ", this.moveZ);
    }

    public Block getBlock() {
        return this.myBlock;
    }

    public int getMeta() {
        return this.myMeta;
    }

    public void writeSpawnData(ByteBuf buffer) {
        int blockData = Block.func_149682_b(this.myBlock) + (this.myMeta << 16);

        buffer.writeInt(blockData);
    }

    public void readSpawnData(ByteBuf additionalData) {
        int blockData = additionalData.readInt();

        this.myBlock = Block.func_149729_e(blockData & '\uffff');
        this.myMeta = blockData >> 16;
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_96092_aw() {
        return false;
    }
}
