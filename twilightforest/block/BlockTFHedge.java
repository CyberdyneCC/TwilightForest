package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.item.TFItems;

public class BlockTFHedge extends BlockLeavesBase {

    public int damageDone = 3;
    public static IIcon sprHedge;
    public static IIcon sprDarkwoodLeaves;

    protected BlockTFHedge() {
        super(Material.field_151570_A, false);
        this.func_149711_c(2.0F);
        this.func_149752_b(10.0F);
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        switch (meta) {
        case 0:
            float f = 0.0625F;

            return AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) ((float) (y + 1) - f), (double) (z + 1));

        case 1:
        default:
            return AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1));
        }
    }

    public boolean func_149662_c() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        Block i1 = par1IBlockAccess.func_147439_a(par2, par3, par4);

        return !this.field_150121_P && i1 == this ? false : super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5);
    }

    public int func_149692_a(int meta) {
        if (meta == 2) {
            meta = 0;
        }

        return meta == 1 ? 3 : meta;
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFHedge.sprHedge;

        case 1:
            return BlockTFHedge.sprDarkwoodLeaves;
        }
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int meta = world.func_72805_g(x, y, z);

        if (meta == 2) {
            meta = 0;
        }

        if (meta == 0 && this.shouldDamage(entity)) {
            entity.func_70097_a(DamageSource.field_76367_g, (float) this.damageDone);
        }

    }

    public void func_149724_b(World world, int x, int y, int z, Entity entity) {
        int meta = world.func_72805_g(x, y, z);

        if (meta == 2) {
            meta = 0;
        }

        if (meta == 0 && this.shouldDamage(entity)) {
            entity.func_70097_a(DamageSource.field_76367_g, (float) this.damageDone);
        }

    }

    public void func_149699_a(World world, int x, int y, int z, EntityPlayer entityplayer) {
        int meta = world.func_72805_g(x, y, z);

        if (meta == 2) {
            meta = 0;
        }

        if (meta == 0 && !world.field_72995_K) {
            world.func_147464_a(x, y, z, this, 10);
        }

    }

    public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
        super.func_149636_a(world, entityplayer, i, j, k, meta);
        if (meta == 2) {
            meta = 0;
        }

        if (meta == 0) {
            entityplayer.func_70097_a(DamageSource.field_76367_g, (float) this.damageDone);
        }

    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        double range = 4.0D;
        List nearbyPlayers = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1)).func_72314_b(range, range, range));
        Iterator iterator = nearbyPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer player = (EntityPlayer) iterator.next();

            if (player.field_82175_bq) {
                MovingObjectPosition mop = this.getPlayerPointVec(world, player, range);

                if (mop != null && world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == this) {
                    player.func_70097_a(DamageSource.field_76367_g, (float) this.damageDone);
                    world.func_147464_a(x, y, z, this, 10);
                }
            }
        }

    }

    private MovingObjectPosition getPlayerPointVec(World worldObj, EntityPlayer player, double range) {
        Vec3 position = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 look = player.func_70676_i(1.0F);
        Vec3 dest = position.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range);

        return worldObj.func_72933_a(position, dest);
    }

    private boolean shouldDamage(Entity entity) {
        return !(entity instanceof EntitySpider) && !(entity instanceof EntityItem) && !entity.func_145773_az();
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        int metadata = world.func_72805_g(x, y, z);

        return metadata == 1 ? 1 : 0;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 0;
    }

    public int func_149745_a(Random par1Random) {
        return par1Random.nextInt(40) == 0 ? 1 : 0;
    }

    public Item func_149650_a(int meta, Random par2Random, int par3) {
        return meta == 1 ? Item.func_150898_a(TFBlocks.sapling) : null;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(this, 1, world.func_72805_g(x, y, z));
    }

    public void func_149690_a(World par1World, int par2, int par3, int par4, int meta, float par6, int fortune) {
        if (!par1World.field_72995_K && meta == 1 && par1World.field_73012_v.nextInt(40) == 0) {
            Item item = this.func_149650_a(meta, par1World.field_73012_v, fortune);

            this.func_149642_a(par1World, par2, par3, par4, new ItemStack(item, 1, this.func_149692_a(meta)));
        }

    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFHedge.sprHedge = par1IconRegister.func_94245_a("TwilightForest:hedge");
        BlockTFHedge.sprDarkwoodLeaves = par1IconRegister.func_94245_a("TwilightForest:darkwood_leaves");
    }
}
