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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFTowerTranslucent extends Block {

    public static final int META_REAPPEARING_INACTIVE = 0;
    public static final int META_REAPPEARING_ACTIVE = 1;
    public static final int META_BUILT_INACTIVE = 2;
    public static final int META_BUILT_ACTIVE = 3;
    public static final int META_REVERTER_REPLACEMENT = 4;
    public static final int META_REACTOR_DEBRIS = 5;
    public static final int META_FAKE_GOLD = 6;
    public static final int META_FAKE_DIAMOND = 7;
    public static IIcon TEX_REAPPEARING_INACTIVE;
    public static IIcon TEX_REAPPEARING_ACTIVE;
    public static IIcon TEX_BUILT_INACTIVE;
    public static IIcon TEX_BUILT_ACTIVE;
    public static IIcon TEX_REVERTER_REPLACEMENT;
    private static Random sideRNG = new Random();

    public BlockTFTowerTranslucent() {
        super(Material.field_151592_s);
        this.func_149711_c(50.0F);
        this.func_149752_b(2000.0F);
        this.func_149672_a(Block.field_149777_j);
        this.func_149647_a(TFItems.creativeTab);
    }

    public boolean func_149662_c() {
        return false;
    }

    public int tickRate() {
        return 15;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return null;
    }

    protected boolean func_149700_E() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        int meta = par1World.func_72805_g(par2, par3, par4) & 7;

        if (meta != 0 && meta != 1) {
            this.func_149719_a(par1World, par2, par3, par4);
            return super.func_149668_a(par1World, par2, par3, par4);
        } else {
            return null;
        }
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int meta = par1IBlockAccess.func_72805_g(par2, par3, par4);

        if (meta == 0 || meta == 1) {
            this.func_149676_a(0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
        }

        if (meta == 5) {
            this.func_149676_a(BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F, BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F, BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F, 1.0F - BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F, 1.0F - BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F, 1.0F - BlockTFTowerTranslucent.sideRNG.nextFloat() * 0.4F);
        } else {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    public float func_149712_f(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return meta != 4 && meta != 5 ? super.func_149712_f(world, x, y, z) : 0.3F;
    }

    public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int meta = par1IBlockAccess.func_72805_g(par2, par3, par4);

        switch (meta) {
        case 0:
        case 1:
        default:
            return false;

        case 2:
        case 3:
        case 4:
        case 5:
            return true;
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFTowerTranslucent.TEX_REAPPEARING_INACTIVE;

        case 1:
            return BlockTFTowerTranslucent.TEX_REAPPEARING_ACTIVE;

        case 2:
            return BlockTFTowerTranslucent.TEX_BUILT_INACTIVE;

        case 3:
            return BlockTFTowerTranslucent.TEX_BUILT_ACTIVE;

        case 4:
            return BlockTFTowerTranslucent.TEX_REVERTER_REPLACEMENT;

        case 5:
            Object toMimic = BlockTFTowerTranslucent.sideRNG.nextBoolean() ? (BlockTFTowerTranslucent.sideRNG.nextBoolean() ? Blocks.field_150427_aO : Blocks.field_150424_aL) : (BlockTFTowerTranslucent.sideRNG.nextBoolean() ? Blocks.field_150357_h : Blocks.field_150343_Z);

            return ((Block) toMimic).func_149691_a(side, meta);

        case 6:
            return Blocks.field_150340_R.func_149691_a(side, meta);

        case 7:
            return Blocks.field_150484_ah.func_149691_a(side, meta);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFTowerTranslucent.TEX_REAPPEARING_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reappearing_trace_off");
        BlockTFTowerTranslucent.TEX_REAPPEARING_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reappearing_trace_on");
        BlockTFTowerTranslucent.TEX_BUILT_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_built_off");
        BlockTFTowerTranslucent.TEX_BUILT_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_built_on");
        BlockTFTowerTranslucent.TEX_REVERTER_REPLACEMENT = par1IconRegister.func_94245_a("TwilightForest:towerdev_antibuilt");
    }

    public void func_149674_a(World par1World, int x, int y, int z, Random par5Random) {
        if (!par1World.field_72995_K) {
            int meta = par1World.func_72805_g(x, y, z);

            if (meta == 3) {
                par1World.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
                par1World.func_147459_d(x, y, z, this);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.pop", 0.3F, 0.5F);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x - 1, y, z);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x + 1, y, z);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x, y + 1, z);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x, y - 1, z);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x, y, z + 1);
                BlockTFTowerDevice.checkAndActivateVanishBlock(par1World, x, y, z - 1);
            }

            if (meta == 1) {
                par1World.func_147465_d(x, y, z, TFBlocks.towerDevice, 0, 3);
                par1World.func_147459_d(x, y, z, this);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.5F);
            } else if (meta == 0) {
                BlockTFTowerDevice.changeToActiveVanishBlock(par1World, x, y, z, 1);
            }
        }

    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {}
}
