package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFThorns extends BlockRotatedPillar {

    private static final float THORN_DAMAGE = 4.0F;
    private String[] names;
    private IIcon[] sideIcons;
    private IIcon[] topIcons;

    protected BlockTFThorns() {
        super(Material.field_151575_d);
        this.setNames(new String[] { "brown", "green"});
        this.func_149711_c(50.0F);
        this.func_149752_b(2000.0F);
        this.func_149672_a(BlockTFThorns.field_149766_f);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getThornsBlockRenderID();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        int rotation = meta & 12;
        float pixel = 0.0625F;

        switch (rotation) {
        case 0:
        default:
            return AxisAlignedBB.func_72330_a((double) ((float) x + pixel * 3.0F), (double) y, (double) ((float) z + pixel * 3.0F), (double) ((float) x + 1.0F - pixel * 3.0F), (double) ((float) y + 1.0F), (double) ((float) z + 1.0F - pixel * 3.0F));

        case 4:
            return AxisAlignedBB.func_72330_a((double) x, (double) ((float) y + pixel * 3.0F), (double) ((float) z + pixel * 3.0F), (double) ((float) x + 1.0F), (double) ((float) y + 1.0F - pixel * 3.0F), (double) ((float) z + 1.0F - pixel * 3.0F));

        case 8:
            return AxisAlignedBB.func_72330_a((double) ((float) x + pixel * 3.0F), (double) ((float) y + pixel * 3.0F), (double) z, (double) ((float) x + 1.0F - pixel * 3.0F), (double) ((float) y + 1.0F - pixel * 3.0F), (double) ((float) z + 1.0F));
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        return this.func_149668_a(world, x, y, z);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        entity.func_70097_a(DamageSource.field_76367_g, 4.0F);
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        if (!player.field_71075_bZ.field_75098_d) {
            if (!world.field_72995_K) {
                world.func_147465_d(x, y, z, this, meta & 12 | 1, 2);
                this.doThornBurst(world, x, y, z, meta);
            }
        } else {
            world.func_147468_f(x, y, z);
        }

        return true;
    }

    public int func_149656_h() {
        return 2;
    }

    private void doThornBurst(World world, int x, int y, int z, int meta) {
        int rotation = meta & 12;

        switch (rotation) {
        case 0:
            this.growThorns(world, x, y, z, ForgeDirection.UP);
            this.growThorns(world, x, y, z, ForgeDirection.DOWN);
            break;

        case 4:
            this.growThorns(world, x, y, z, ForgeDirection.EAST);
            this.growThorns(world, x, y, z, ForgeDirection.WEST);
            break;

        case 8:
            this.growThorns(world, x, y, z, ForgeDirection.NORTH);
            this.growThorns(world, x, y, z, ForgeDirection.SOUTH);
        }

        this.growThorns(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[world.field_73012_v.nextInt(ForgeDirection.VALID_DIRECTIONS.length)]);
        this.growThorns(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[world.field_73012_v.nextInt(ForgeDirection.VALID_DIRECTIONS.length)]);
        this.growThorns(world, x, y, z, ForgeDirection.VALID_DIRECTIONS[world.field_73012_v.nextInt(ForgeDirection.VALID_DIRECTIONS.length)]);
    }

    private void growThorns(World world, int x, int y, int z, ForgeDirection dir) {
        int length = 1 + world.field_73012_v.nextInt(3);

        for (int i = 1; i < length; ++i) {
            int dx = x + dir.offsetX * i;
            int dy = y + dir.offsetY * i;
            int dz = z + dir.offsetZ * i;

            if (!world.func_147437_c(dx, dy, dz)) {
                break;
            }

            world.func_147465_d(dx, dy, dz, this, getMetaFor(dir) | 1, 2);
        }

    }

    public static int getMetaFor(ForgeDirection dir) {
        switch (BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[dir.ordinal()]) {
        case 1:
        case 2:
        case 3:
        default:
            return 0;

        case 4:
        case 5:
            return 4;

        case 6:
        case 7:
            return 8;
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block logBlock, int metadata) {
        byte range = 4;
        int exRange = range + 1;

        if (world.func_72904_c(x - exRange, y - exRange, z - exRange, x + exRange, y + exRange, z + exRange)) {
            for (int dx = -range; dx <= range; ++dx) {
                for (int dy = -range; dy <= range; ++dy) {
                    for (int dz = -range; dz <= range; ++dz) {
                        Block block = world.func_147439_a(x + dx, y + dy, z + dz);

                        if (block.isLeaves(world, x + dx, y + dy, z + dz)) {
                            block.beginLeavesDecay(world, x + dx, y + dy, z + dz);
                        }
                    }
                }
            }
        }

    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150163_b(int meta) {
        return this.sideIcons[meta & 3];
    }

    @SideOnly(Side.CLIENT)
    protected IIcon func_150161_d(int meta) {
        return this.topIcons[meta & 3];
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.sideIcons = new IIcon[this.getNames().length];
        this.topIcons = new IIcon[this.getNames().length];

        for (int i = 0; i < this.getNames().length; ++i) {
            this.sideIcons[i] = iconRegister.func_94245_a("TwilightForest:" + this.getNames()[i] + "_thorns_side");
            this.topIcons[i] = iconRegister.func_94245_a("TwilightForest:" + this.getNames()[i] + "_thorns_top");
        }

    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < this.getNames().length; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    public String[] getNames() {
        return this.names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    static class SyntheticClass_1 {

        static final int[] $SwitchMap$net$minecraftforge$common$util$ForgeDirection = new int[ForgeDirection.values().length];

        static {
            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.UP.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.EAST.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.NORTH.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                BlockTFThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.SOUTH.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

        }
    }
}
