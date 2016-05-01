package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFFlameJet;
import twilightforest.tileentity.TileEntityTFPoppingJet;
import twilightforest.tileentity.TileEntityTFSmoker;

public class BlockTFFireJet extends Block {

    public static final int META_SMOKER = 0;
    public static final int META_ENCASED_SMOKER_OFF = 1;
    public static final int META_ENCASED_SMOKER_ON = 2;
    public static final int META_JET_IDLE = 8;
    public static final int META_JET_POPPING = 9;
    public static final int META_JET_FLAME = 10;
    public static final int META_ENCASED_JET_IDLE = 11;
    public static final int META_ENCASED_JET_POPPING = 12;
    public static final int META_ENCASED_JET_FLAME = 13;
    private IIcon iconJet;
    private IIcon iconSide;
    private IIcon iconSmokerInactive;
    private IIcon iconSmokerActive;
    private IIcon iconJetInactive;
    private IIcon iconJetActive;

    protected BlockTFFireJet() {
        super(Material.field_151576_e);
        this.func_149711_c(1.5F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
        this.func_149675_a(true);
    }

    public int func_149692_a(int meta) {
        switch (meta) {
        case 2:
            return 1;

        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 11:
        default:
            return meta;

        case 9:
        case 10:
            return 8;

        case 12:
        case 13:
            return 11;
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta == 1 ? (side >= 2 ? this.iconSmokerInactive : (side == 1 ? TFBlocks.towerDevice.func_149691_a(side, 10) : TFBlocks.towerWood.func_149691_a(side, 1))) : (meta == 2 ? (side >= 2 ? this.iconSmokerActive : (side == 1 ? TFBlocks.towerDevice.func_149691_a(side, 11) : TFBlocks.towerWood.func_149691_a(side, 1))) : (meta == 11 ? (side >= 2 ? this.iconJetInactive : (side == 1 ? TFBlocks.towerDevice.func_149691_a(side, 10) : TFBlocks.towerWood.func_149691_a(side, 1))) : (meta != 12 && meta != 13 ? (side == 1 ? this.iconJet : this.iconSide) : (side >= 2 ? this.iconJetActive : (side == 1 ? TFBlocks.towerDevice.func_149691_a(side, 11) : TFBlocks.towerWood.func_149691_a(side, 1))))));
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconSide = par1IconRegister.func_94245_a("TwilightForest:firejet_side");
        this.iconJet = par1IconRegister.func_94245_a("TwilightForest:firejet_top");
        this.iconSmokerInactive = par1IconRegister.func_94245_a("TwilightForest:towerdev_smoker_off");
        this.iconSmokerActive = par1IconRegister.func_94245_a("TwilightForest:towerdev_smoker_on");
        this.iconJetInactive = par1IconRegister.func_94245_a("TwilightForest:towerdev_firejet_off");
        this.iconJetActive = par1IconRegister.func_94245_a("TwilightForest:towerdev_firejet_on");
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z);

        if (meta != 1 && meta != 2 && meta != 11 && meta != 12 && meta != 13) {
            int red = 0;
            int grn = 0;
            int blu = 0;

            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    int biomeColor = par1IBlockAccess.func_72807_a(x + j, z + i).func_150558_b(x + j, y, z + i);

                    red += (biomeColor & 16711680) >> 16;
                    grn += (biomeColor & '\uff00') >> 8;
                    blu += biomeColor & 255;
                }
            }

            return (red / 9 & 255) << 16 | (grn / 9 & 255) << 8 | blu / 9 & 255;
        } else {
            return super.func_149720_d(par1IBlockAccess, x, y, z);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) == this) {
            int meta = world.func_72805_g(x, y, z);

            switch (meta) {
            case 0:
            default:
                return 0;

            case 10:
            case 13:
                return 15;
            }
        } else {
            return 0;
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K && world.func_72805_g(x, y, z) == 8) {
            ChunkCoordinates lavaPos = this.findLavaAround(world, x, y - 1, z);

            if (this.isLava(world, lavaPos.field_71574_a, lavaPos.field_71572_b, lavaPos.field_71573_c)) {
                world.func_147465_d(lavaPos.field_71574_a, lavaPos.field_71572_b, lavaPos.field_71573_c, Blocks.field_150350_a, 0, 2);
                world.func_147465_d(x, y, z, this, 9, 0);
            }
        }

    }

    public void func_149695_a(World par1World, int x, int y, int z, Block myBlockID) {
        int meta = par1World.func_72805_g(x, y, z);

        if (!par1World.field_72995_K) {
            if (meta == 1 && par1World.func_72864_z(x, y, z)) {
                par1World.func_147465_d(x, y, z, this, 2, 3);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
            }

            if (meta == 2 && !par1World.func_72864_z(x, y, z)) {
                par1World.func_147465_d(x, y, z, this, 1, 3);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
            }

            if (meta == 11 && par1World.func_72864_z(x, y, z)) {
                par1World.func_147465_d(x, y, z, this, 12, 3);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
            }
        }

    }

    private ChunkCoordinates findLavaAround(World world, int x, int y, int z) {
        if (this.isLava(world, x, y, z)) {
            return new ChunkCoordinates(x, y, z);
        } else {
            int rx = x + world.field_73012_v.nextInt(3) - 1;
            int rz = z + world.field_73012_v.nextInt(3) - 1;

            if (this.isLava(world, rx, y, rz)) {
                return new ChunkCoordinates(rx, y, rz);
            } else {
                rx = x + world.field_73012_v.nextInt(3) - 1;
                rz = z + world.field_73012_v.nextInt(3) - 1;
                if (this.isLava(world, rx, y, rz)) {
                    return new ChunkCoordinates(rx, y, rz);
                } else {
                    rx = x + world.field_73012_v.nextInt(3) - 1;
                    rz = z + world.field_73012_v.nextInt(3) - 1;
                    return this.isLava(world, rx, y, rz) ? new ChunkCoordinates(rx, y, rz) : new ChunkCoordinates(x, y, z);
                }
            }
        }
    }

    private boolean isLava(World world, int x, int y, int z) {
        return world.func_147439_a(x, y, z).func_149688_o() == Material.field_151587_i && world.func_72805_g(x, y, z) == 0;
    }

    public boolean hasTileEntity(int metadata) {
        return metadata == 0 || metadata == 9 || metadata == 10 || metadata == 2 || metadata == 12 || metadata == 13;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return (TileEntity) (metadata != 0 && metadata != 2 ? (metadata == 9 ? new TileEntityTFPoppingJet(10) : (metadata == 10 ? new TileEntityTFFlameJet(8) : (metadata == 12 ? new TileEntityTFPoppingJet(13) : (metadata == 13 ? new TileEntityTFFlameJet(11) : null)))) : new TileEntityTFSmoker());
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 11));
    }
}
