package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.entity.EntityTFSlideBlock;
import twilightforest.item.TFItems;

public class BlockTFSlider extends BlockRotatedPillar {

    private static final int TICK_TIME = 80;
    private static final int OFFSET_TIME = 20;
    private static final int PLAYER_RANGE = 32;
    private static final float BLOCK_DAMAGE = 5.0F;
    private IIcon horiIcon;
    private IIcon vertIcon;
    private IIcon topIcon;

    protected BlockTFSlider() {
        super(Material.field_151573_f);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        int rotation = meta & 12;
        float pixel = 0.0625F;
        float inset = 5.0F;

        switch (rotation) {
        case 0:
        default:
            return AxisAlignedBB.func_72330_a((double) ((float) x + pixel * inset), (double) y, (double) ((float) z + pixel * inset), (double) ((float) x + 1.0F - pixel * inset), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F - pixel * inset));

        case 4:
            return AxisAlignedBB.func_72330_a((double) x, (double) ((float) y + pixel * inset), (double) ((float) z + pixel * inset), (double) ((float) x + 1.0F), (double) ((float) y + 1.0F - pixel * inset), (double) ((float) z + 1.0F - pixel * inset));

        case 8:
            return AxisAlignedBB.func_72330_a((double) ((float) x + pixel * inset), (double) ((float) y + pixel * inset), (double) z, (double) ((float) x + 1.0F - pixel * inset), (double) ((float) y + 1.0F - pixel * inset), (double) ((float) z + 1.0F));
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        return this.func_149668_a(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        this.setBlockBoundsBasedOnMeta(meta);
    }

    public void setBlockBoundsBasedOnMeta(int meta) {
        int rotation = meta & 12;
        float pixel = 0.0625F;
        float inset = 5.0F;

        switch (rotation) {
        case 0:
        default:
            this.func_149676_a(pixel * inset, 0.0F, pixel * inset, 1.0F - pixel * inset, 1.0F, 1.0F - pixel * inset);
            break;

        case 4:
            this.func_149676_a(0.0F, pixel * inset, pixel * inset, 1.0F, 1.0F - pixel * inset, 1.0F - pixel * inset);
            break;

        case 8:
            this.func_149676_a(pixel * inset, pixel * inset, 0.0F, 1.0F - pixel * inset, 1.0F - pixel * inset, 1.0F);
        }

    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        int rotation = meta & 12;

        if (rotation == 0) {
            switch (side) {
            case 0:
            case 1:
                return this.topIcon;

            default:
                return this.vertIcon;
            }
        } else if (rotation == 4) {
            switch (side) {
            case 4:
            case 5:
                return this.topIcon;

            default:
                return this.horiIcon;
            }
        } else {
            switch (side) {
            case 0:
            case 1:
                return this.vertIcon;

            case 2:
            case 3:
                return this.topIcon;

            default:
                return this.horiIcon;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150163_b(int meta) {
        return (meta & 12) == 0 ? this.horiIcon : ((meta & 12) == 8 ? this.horiIcon : this.vertIcon);
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150161_d(int p_150161_1_) {
        return this.topIcon;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.horiIcon = par1IconRegister.func_94245_a("TwilightForest:slider_h");
        this.vertIcon = par1IconRegister.func_94245_a("TwilightForest:slider_v");
        this.topIcon = par1IconRegister.func_94245_a("TwilightForest:slider_top");
    }

    public void func_149674_a(World world, int x, int y, int z, Random par5Random) {
        if (!world.field_72995_K && this.isConnectedInRange(world, x, y, z)) {
            EntityTFSlideBlock slideBlock = new EntityTFSlideBlock(world, (double) x + 0.5D, (double) y, (double) z + 0.5D, this, world.func_72805_g(x, y, z));

            world.func_72838_d(slideBlock);
        }

        this.scheduleBlockUpdate(world, x, y, z);
    }

    public boolean isConnectedInRange(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return (meta & 12) == 0 ? this.anyPlayerInRange(world, x, y, z) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.UP) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.DOWN) : ((meta & 12) == 4 ? this.anyPlayerInRange(world, x, y, z) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.WEST) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.EAST) : ((meta & 12) != 8 ? this.anyPlayerInRange(world, x, y, z) : this.anyPlayerInRange(world, x, y, z) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.NORTH) || this.isConnectedInRangeRecursive(world, x, y, z, ForgeDirection.SOUTH)));
    }

    private boolean isConnectedInRangeRecursive(World world, int x, int y, int z, ForgeDirection dir) {
        int dx = x + dir.offsetX;
        int dy = y + dir.offsetY;
        int dz = z + dir.offsetZ;

        return world.func_147439_a(x, y, z) == world.func_147439_a(dx, dy, dz) && world.func_72805_g(x, y, z) == world.func_72805_g(dx, dy, dz) ? this.anyPlayerInRange(world, dx, dy, dz) || this.isConnectedInRangeRecursive(world, dx, dy, dz, dir) : false;
    }

    public boolean anyPlayerInRange(World world, int x, int y, int z) {
        return world.func_72977_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, 32.0D) != null;
    }

    public void scheduleBlockUpdate(World world, int x, int y, int z) {
        int offset = world.func_72805_g(x, y, z) & 3;
        int update = 80 - (int) (world.func_72820_D() - (long) (offset * 20)) % 80;

        world.func_147464_a(x, y, z, this, update);
    }

    public void func_149726_b(World world, int x, int y, int z) {
        this.scheduleBlockUpdate(world, x, y, z);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public void func_149683_g() {
        this.setBlockBoundsBasedOnMeta(0);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        entity.func_70097_a(DamageSource.field_76377_j, 5.0F);
        if (entity instanceof EntityLivingBase) {
            double kx = ((double) x + 0.5D - entity.field_70165_t) * 2.0D;
            double kz = ((double) z + 0.5D - entity.field_70161_v) * 2.0D;

            ((EntityLivingBase) entity).func_70653_a((Entity) null, 5.0F, kx, kz);
        }

    }
}
