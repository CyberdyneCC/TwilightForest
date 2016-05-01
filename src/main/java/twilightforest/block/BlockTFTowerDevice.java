package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFCReactorActive;
import twilightforest.tileentity.TileEntityTFGhastTrapActive;
import twilightforest.tileentity.TileEntityTFGhastTrapInactive;
import twilightforest.tileentity.TileEntityTFReverter;
import twilightforest.tileentity.TileEntityTFTowerBuilder;

public class BlockTFTowerDevice extends Block {

    private static IIcon TEX_REAPPEARING_INACTIVE;
    private static IIcon TEX_REAPPEARING_ACTIVE;
    private static IIcon TEX_VANISH_INACTIVE;
    private static IIcon TEX_VANISH_ACTIVE;
    private static IIcon TEX_VANISH_LOCKED;
    private static IIcon TEX_VANISH_UNLOCKED;
    private static IIcon TEX_BUILDER_INACTIVE;
    private static IIcon TEX_BUILDER_ACTIVE;
    private static IIcon TEX_ANTIBUILDER;
    private static IIcon TEX_BUILDER_TIMEOUT;
    private static IIcon TEX_GHASTTRAP_INACTIVE;
    private static IIcon TEX_GHASTTRAP_ACTIVE;
    private static IIcon TEX_REACTOR_INACTIVE;
    private static IIcon TEX_REACTOR_ACTIVE;
    private static IIcon TEX_GHASTTRAP_LID_INACTIVE;
    private static IIcon TEX_GHASTTRAP_LID_ACTIVE;
    private static IIcon TEX_SMOKER_ACTIVE;
    private static IIcon TEX_SMOKER_INACTIVE;
    private static IIcon TEX_FIREJET_ACTIVE;
    private static IIcon TEX_FIREJET_INACTIVE;
    public static final int META_REAPPEARING_INACTIVE = 0;
    public static final int META_REAPPEARING_ACTIVE = 1;
    public static final int META_VANISH_INACTIVE = 2;
    public static final int META_VANISH_ACTIVE = 3;
    public static final int META_VANISH_LOCKED = 4;
    public static final int META_VANISH_UNLOCKED = 5;
    public static final int META_BUILDER_INACTIVE = 6;
    public static final int META_BUILDER_ACTIVE = 7;
    public static final int META_BUILDER_TIMEOUT = 8;
    public static final int META_ANTIBUILDER = 9;
    public static final int META_GHASTTRAP_INACTIVE = 10;
    public static final int META_GHASTTRAP_ACTIVE = 11;
    public static final int META_REACTOR_INACTIVE = 12;
    public static final int META_REACTOR_ACTIVE = 13;

    public BlockTFTowerDevice() {
        super(Material.field_151575_d);
        this.func_149711_c(10.0F);
        this.func_149752_b(35.0F);
        this.func_149672_a(Block.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int tickRate() {
        return 15;
    }

    public IIcon func_149691_a(int side, int meta) {
        switch (meta) {
        case 0:
        default:
            return BlockTFTowerDevice.TEX_REAPPEARING_INACTIVE;

        case 1:
            return BlockTFTowerDevice.TEX_REAPPEARING_ACTIVE;

        case 2:
            return BlockTFTowerDevice.TEX_VANISH_INACTIVE;

        case 3:
            return BlockTFTowerDevice.TEX_VANISH_ACTIVE;

        case 4:
            return BlockTFTowerDevice.TEX_VANISH_LOCKED;

        case 5:
            return BlockTFTowerDevice.TEX_VANISH_UNLOCKED;

        case 6:
            return BlockTFTowerDevice.TEX_BUILDER_INACTIVE;

        case 7:
            return BlockTFTowerDevice.TEX_BUILDER_ACTIVE;

        case 8:
            return BlockTFTowerDevice.TEX_BUILDER_TIMEOUT;

        case 9:
            return BlockTFTowerDevice.TEX_ANTIBUILDER;

        case 10:
            if (side >= 2) {
                return BlockTFTowerDevice.TEX_GHASTTRAP_INACTIVE;
            } else {
                if (side == 1) {
                    return BlockTFTowerDevice.TEX_GHASTTRAP_LID_INACTIVE;
                }

                return TFBlocks.towerWood.func_149691_a(side, 1);
            }

        case 11:
            if (side >= 2) {
                return BlockTFTowerDevice.TEX_GHASTTRAP_ACTIVE;
            } else {
                if (side == 1) {
                    return BlockTFTowerDevice.TEX_GHASTTRAP_LID_ACTIVE;
                }

                return TFBlocks.towerWood.func_149691_a(side, 1);
            }

        case 12:
            return BlockTFTowerDevice.TEX_REACTOR_INACTIVE;

        case 13:
            return BlockTFTowerDevice.TEX_REACTOR_ACTIVE;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        BlockTFTowerDevice.TEX_REAPPEARING_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reappearing_off");
        BlockTFTowerDevice.TEX_REAPPEARING_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reappearing_on");
        BlockTFTowerDevice.TEX_VANISH_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_vanish_off");
        BlockTFTowerDevice.TEX_VANISH_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_vanish_on");
        BlockTFTowerDevice.TEX_VANISH_LOCKED = par1IconRegister.func_94245_a("TwilightForest:towerdev_lock_on");
        BlockTFTowerDevice.TEX_VANISH_UNLOCKED = par1IconRegister.func_94245_a("TwilightForest:towerdev_lock_off");
        BlockTFTowerDevice.TEX_BUILDER_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_builder_off");
        BlockTFTowerDevice.TEX_BUILDER_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_builder_on");
        BlockTFTowerDevice.TEX_ANTIBUILDER = par1IconRegister.func_94245_a("TwilightForest:towerdev_antibuilder");
        BlockTFTowerDevice.TEX_BUILDER_TIMEOUT = par1IconRegister.func_94245_a("TwilightForest:towerdev_builder_timeout");
        BlockTFTowerDevice.TEX_GHASTTRAP_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_ghasttrap_off");
        BlockTFTowerDevice.TEX_GHASTTRAP_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_ghasttrap_on");
        BlockTFTowerDevice.TEX_REACTOR_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reactor_off");
        BlockTFTowerDevice.TEX_REACTOR_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_reactor_on");
        BlockTFTowerDevice.TEX_GHASTTRAP_LID_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_ghasttraplid_off");
        BlockTFTowerDevice.TEX_GHASTTRAP_LID_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_ghasttraplid_on");
        BlockTFTowerDevice.TEX_SMOKER_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_smoker_off");
        BlockTFTowerDevice.TEX_SMOKER_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_smoker_on");
        BlockTFTowerDevice.TEX_FIREJET_INACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_firejet_off");
        BlockTFTowerDevice.TEX_FIREJET_ACTIVE = par1IconRegister.func_94245_a("TwilightForest:towerdev_firejet_on");
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 9));
        par3List.add(new ItemStack(par1, 1, 10));
        par3List.add(new ItemStack(par1, 1, 12));
    }

    public boolean func_149727_a(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int meta = par1World.func_72805_g(x, y, z);

        if (meta == 2) {
            if (areNearbyLockBlocks(par1World, x, y, z)) {
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 1.0F, 0.3F);
            } else {
                changeToActiveVanishBlock(par1World, x, y, z, 3);
            }

            return true;
        } else if (meta == 0) {
            if (areNearbyLockBlocks(par1World, x, y, z)) {
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 1.0F, 0.3F);
            } else {
                changeToActiveVanishBlock(par1World, x, y, z, 1);
            }

            return true;
        } else {
            return false;
        }
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int meta = world.func_72805_g(x, y, z);

        return meta == 2 ? 6000.0F : (meta == 4 ? 6000000.0F : super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ));
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        switch (meta) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            return -1.0F;

        default:
            return super.func_149712_f(world, x, y, z);
        }
    }

    public static boolean areNearbyLockBlocks(World world, int x, int y, int z) {
        boolean locked = false;

        for (int dx = x - 2; dx <= x + 2; ++dx) {
            for (int dy = y - 2; dy <= y + 2; ++dy) {
                for (int dz = z - 2; dz <= z + 2; ++dz) {
                    if (world.func_147439_a(dx, dy, dz) == TFBlocks.towerDevice && world.func_72805_g(dx, dy, dz) == 4) {
                        locked = true;
                    }
                }
            }
        }

        return locked;
    }

    public static void unlockBlock(World par1World, int x, int y, int z) {
        Block thereBlockID = par1World.func_147439_a(x, y, z);
        int thereBlockMeta = par1World.func_72805_g(x, y, z);

        if (thereBlockID == TFBlocks.towerDevice || thereBlockMeta == 4) {
            changeToBlockMeta(par1World, x, y, z, 5);
            par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
        }

    }

    private static void changeToBlockMeta(World par1World, int x, int y, int z, int meta) {
        Block thereBlockID = par1World.func_147439_a(x, y, z);

        if (thereBlockID == TFBlocks.towerDevice || thereBlockID == TFBlocks.towerTranslucent) {
            par1World.func_147465_d(x, y, z, thereBlockID, meta, 3);
            par1World.func_147458_c(x, y, z, x, y, z);
            par1World.func_147459_d(x, y, z, thereBlockID);
        }

    }

    public void func_149726_b(World par1World, int x, int y, int z) {
        int meta = par1World.func_72805_g(x, y, z);

        if (!par1World.field_72995_K && meta == 6 && par1World.func_72864_z(x, y, z)) {
            changeToBlockMeta(par1World, x, y, z, 7);
            par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
        }

    }

    public void func_149695_a(World par1World, int x, int y, int z, Block myBlockID) {
        int meta = par1World.func_72805_g(x, y, z);

        if (!par1World.field_72995_K) {
            if (meta == 2 && par1World.func_72864_z(x, y, z) && !areNearbyLockBlocks(par1World, x, y, z)) {
                changeToActiveVanishBlock(par1World, x, y, z, 3);
            }

            if (meta == 0 && par1World.func_72864_z(x, y, z) && !areNearbyLockBlocks(par1World, x, y, z)) {
                changeToActiveVanishBlock(par1World, x, y, z, 1);
            }

            if (meta == 6 && par1World.func_72864_z(x, y, z)) {
                changeToBlockMeta(par1World, x, y, z, 7);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
                par1World.func_147464_a(x, y, z, this, 4);
            }

            if (meta == 7 && !par1World.func_72864_z(x, y, z)) {
                changeToBlockMeta(par1World, x, y, z, 6);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
                par1World.func_147464_a(x, y, z, this, 4);
            }

            if (meta == 8 && !par1World.func_72864_z(x, y, z)) {
                changeToBlockMeta(par1World, x, y, z, 6);
            }

            if (meta == 10 && this.isInactiveTrapCharged(par1World, x, y, z) && par1World.func_72864_z(x, y, z)) {
                changeToBlockMeta(par1World, x, y, z, 11);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
                par1World.func_147464_a(x, y, z, this, 4);
            }

            if (meta == 12 && this.isReactorReady(par1World, x, y, z)) {
                changeToBlockMeta(par1World, x, y, z, 13);
            }
        }

    }

    public void func_149674_a(World par1World, int x, int y, int z, Random par5Random) {
        if (!par1World.field_72995_K) {
            int meta = par1World.func_72805_g(x, y, z);

            if (meta == 3 || meta == 1) {
                if (meta == 3) {
                    par1World.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
                } else {
                    par1World.func_147465_d(x, y, z, TFBlocks.towerTranslucent, 0, 3);
                    par1World.func_147464_a(x, y, z, TFBlocks.towerTranslucent, 80);
                }

                par1World.func_147459_d(x, y, z, this);
                par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.pop", 0.3F, 0.5F);
                checkAndActivateVanishBlock(par1World, x - 1, y, z);
                checkAndActivateVanishBlock(par1World, x + 1, y, z);
                checkAndActivateVanishBlock(par1World, x, y + 1, z);
                checkAndActivateVanishBlock(par1World, x, y - 1, z);
                checkAndActivateVanishBlock(par1World, x, y, z + 1);
                checkAndActivateVanishBlock(par1World, x, y, z - 1);
            }

            if (meta == 7 && par1World.func_72864_z(x, y, z)) {
                this.letsBuild(par1World, x, y, z);
            }

            if (meta == 6 || meta == 8) {
                checkAndActivateVanishBlock(par1World, x - 1, y, z);
                checkAndActivateVanishBlock(par1World, x + 1, y, z);
                checkAndActivateVanishBlock(par1World, x, y + 1, z);
                checkAndActivateVanishBlock(par1World, x, y - 1, z);
                checkAndActivateVanishBlock(par1World, x, y, z + 1);
                checkAndActivateVanishBlock(par1World, x, y, z - 1);
            }
        }

    }

    private void letsBuild(World par1World, int x, int y, int z) {
        BlockSourceImpl blockSource = new BlockSourceImpl(par1World, x, y, z);
        TileEntityTFTowerBuilder tileEntity = (TileEntityTFTowerBuilder) blockSource.func_150835_j();

        if (tileEntity != null && !tileEntity.makingBlocks) {
            tileEntity.startBuilding();
        }

    }

    private boolean isInactiveTrapCharged(World par1World, int x, int y, int z) {
        BlockSourceImpl blockSource = new BlockSourceImpl(par1World, x, y, z);
        TileEntityTFGhastTrapInactive tileEntity = (TileEntityTFGhastTrapInactive) blockSource.func_150835_j();

        return tileEntity != null && tileEntity.isCharged();
    }

    private boolean isReactorReady(World world, int x, int y, int z) {
        return world.func_147439_a(x, y + 1, z) == Blocks.field_150451_bX && world.func_147439_a(x, y - 1, z) == Blocks.field_150451_bX && world.func_147439_a(x + 1, y, z) == Blocks.field_150451_bX && world.func_147439_a(x - 1, y, z) == Blocks.field_150451_bX && world.func_147439_a(x, y, z + 1) == Blocks.field_150451_bX && world.func_147439_a(x, y, z - 1) == Blocks.field_150451_bX;
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World par1World, int x, int y, int z, Random par5Random) {
        int meta = par1World.func_72805_g(x, y, z);

        if (meta == 3 || meta == 1 || meta == 7) {
            for (int i = 0; i < 1; ++i) {
                this.sparkle(par1World, x, y, z, par5Random);
            }
        }

    }

    public void sparkle(World world, int x, int y, int z, Random rand) {
        double offset = 0.0625D;

        for (int side = 0; side < 6; ++side) {
            double rx = (double) ((float) x + rand.nextFloat());
            double ry = (double) ((float) y + rand.nextFloat());
            double rz = (double) ((float) z + rand.nextFloat());

            if (side == 0 && !world.func_147439_a(x, y + 1, z).func_149662_c()) {
                ry = (double) (y + 1) + offset;
            }

            if (side == 1 && !world.func_147439_a(x, y - 1, z).func_149662_c()) {
                ry = (double) (y + 0) - offset;
            }

            if (side == 2 && !world.func_147439_a(x, y, z + 1).func_149662_c()) {
                rz = (double) (z + 1) + offset;
            }

            if (side == 3 && !world.func_147439_a(x, y, z - 1).func_149662_c()) {
                rz = (double) (z + 0) - offset;
            }

            if (side == 4 && !world.func_147439_a(x + 1, y, z).func_149662_c()) {
                rx = (double) (x + 1) + offset;
            }

            if (side == 5 && !world.func_147439_a(x - 1, y, z).func_149662_c()) {
                rx = (double) (x + 0) - offset;
            }

            if (rx < (double) x || rx > (double) (x + 1) || ry < 0.0D || ry > (double) (y + 1) || rz < (double) z || rz > (double) (z + 1)) {
                world.func_72869_a("reddust", rx, ry, rz, 0.0D, 0.0D, 0.0D);
            }
        }

    }

    public static void checkAndActivateVanishBlock(World world, int x, int y, int z) {
        Block thereID = world.func_147439_a(x, y, z);
        int thereMeta = world.func_72805_g(x, y, z);

        if (thereID == TFBlocks.towerDevice && (thereMeta == 2 || thereMeta == 5) && !areNearbyLockBlocks(world, x, y, z)) {
            changeToActiveVanishBlock(world, x, y, z, 3);
        } else if (thereID == TFBlocks.towerDevice && thereMeta == 0 && !areNearbyLockBlocks(world, x, y, z)) {
            changeToActiveVanishBlock(world, x, y, z, 1);
        } else if (thereID == TFBlocks.towerTranslucent && thereMeta == 2) {
            changeToActiveVanishBlock(world, x, y, z, 3);
        }

    }

    public static void changeToActiveVanishBlock(World par1World, int x, int y, int z, int meta) {
        changeToBlockMeta(par1World, x, y, z, meta);
        par1World.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.pop", 0.3F, 0.6F);
        Block thereBlockID = par1World.func_147439_a(x, y, z);

        par1World.func_147464_a(x, y, z, thereBlockID, getTickRateFor(thereBlockID, meta, par1World.field_73012_v));
    }

    private static int getTickRateFor(Block thereBlockID, int meta, Random rand) {
        return thereBlockID == TFBlocks.towerDevice && (meta == 3 || meta == 1) ? 2 + rand.nextInt(5) : (thereBlockID == TFBlocks.towerTranslucent && meta == 3 ? 10 : 15);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        Block blockID = world.func_147439_a(x, y, z);
        int meta = world.func_72805_g(x, y, z);

        if (blockID != this) {
            return 0;
        } else {
            switch (meta) {
            case 1:
            case 3:
            case 7:
                return 4;

            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
            case 10:
            case 12:
            default:
                return 0;

            case 9:
                return 10;

            case 11:
            case 13:
                return 15;
            }
        }
    }

    public boolean hasTileEntity(int metadata) {
        return metadata == 7 || metadata == 9 || metadata == 13 || metadata == 10 || metadata == 11;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return (TileEntity) (metadata == 7 ? new TileEntityTFTowerBuilder() : (metadata == 9 ? new TileEntityTFReverter() : (metadata == 10 ? new TileEntityTFGhastTrapInactive() : (metadata == 11 ? new TileEntityTFGhastTrapActive() : (metadata == 13 ? new TileEntityTFCReactorActive() : null)))));
    }

    public Item func_149650_a(int meta, Random par2Random, int par3) {
        switch (meta) {
        case 9:
            return null;

        default:
            return Item.func_150898_a(this);
        }
    }

    public int func_149692_a(int meta) {
        switch (meta) {
        case 1:
            return 0;

        case 2:
        case 4:
        case 5:
        case 6:
        case 9:
        case 10:
        case 12:
        default:
            return meta;

        case 3:
            return 2;

        case 7:
        case 8:
            return 6;

        case 11:
            return 10;

        case 13:
            return 12;
        }
    }
}
