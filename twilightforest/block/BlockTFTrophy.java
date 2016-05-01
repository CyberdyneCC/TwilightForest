package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFTrophy;

public class BlockTFTrophy extends BlockContainer {

    public BlockTFTrophy() {
        super(Material.field_151594_q);
        this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        this.func_149719_a(par1World, par2, par3, par4);
        return super.func_149668_a(par1World, par2, par3, par4);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z) & 7;
        TileEntityTFTrophy trophy = (TileEntityTFTrophy) par1IBlockAccess.func_147438_o(x, y, z);

        if (trophy != null && trophy.func_145904_a() == 0) {
            switch (meta) {
            case 1:
            default:
                this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
                break;

            case 2:
            case 3:
                this.func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;

            case 4:
            case 5:
                this.func_149676_a(0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
            }
        } else if (trophy != null && trophy.func_145904_a() == 3) {
            this.func_149676_a(0.25F, 0.5F, 0.25F, 0.75F, 1.0F, 0.75F);
        } else {
            switch (meta) {
            case 1:
            default:
                this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
                break;

            case 2:
                this.func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
                break;

            case 3:
                this.func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
                break;

            case 4:
                this.func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;

            case 5:
                this.func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
            }
        }

    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack itemStack) {
        int rotation = MathHelper.func_76128_c((double) (par5EntityLiving.field_70177_z * 4.0F / 360.0F) + 2.5D) & 3;

        par1World.func_72921_c(par2, par3, par4, rotation, 2);
    }

    public TileEntity func_149915_a(World world, int i) {
        return new TileEntityTFTrophy();
    }

    @SideOnly(Side.CLIENT)
    public Item idPicked(World par1World, int par2, int par3, int par4) {
        return TFItems.trophy;
    }

    public int func_149643_k(World par1World, int par2, int par3, int par4) {
        TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);

        return tileentity != null && tileentity instanceof TileEntitySkull ? ((TileEntitySkull) tileentity).func_145904_a() : super.func_149643_k(par1World, par2, par3, par4);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
            par5 |= 8;
            par1World.func_72921_c(par2, par3, par4, par5, 2);
        }

        this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList drops = new ArrayList();

        if ((metadata & 8) == 0) {
            ItemStack itemstack = new ItemStack(TFItems.trophy, 1, this.func_149643_k(world, x, y, z));
            TileEntityTFTrophy tileentitytftrophy = (TileEntityTFTrophy) world.func_147438_o(x, y, z);

            if (tileentitytftrophy == null) {
                return drops;
            }

            drops.add(itemstack);
        }

        return drops;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return TFItems.trophy;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150425_aM.func_149691_a(side, meta);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {}
}
