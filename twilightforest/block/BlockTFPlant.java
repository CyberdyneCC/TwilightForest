package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFPlant extends BlockBush implements IShearable {

    boolean[] isGrassColor = new boolean[] { false, false, false, false, true, true, false, false, true, false, true, false, false, false, false, false};
    int[] field_149784_t = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 8, 0, 0};
    private IIcon[] icons;
    private String[] iconNames = new String[] { null, null, null, "mosspatch", "mayapple", "cloverpatch", null, null, "fiddlehead", "mushgloom", null, null, null, "torchberry", "rootstrand", null};
    public static IIcon mayappleSide;
    public static final int META_MOSSPATCH = 3;
    public static final int META_MAYAPPLE = 4;
    public static final int META_CLOVERPATCH = 5;
    public static final int META_FIDDLEHEAD = 8;
    public static final int META_MUSHGLOOM = 9;
    public static final int META_FORESTGRASS = 10;
    public static final int META_DEADBUSH = 11;
    public static final int META_TORCHBERRY = 13;
    public static final int META_ROOT_STRAND = 14;

    protected BlockTFPlant() {
        super(Material.field_151585_k);
        this.func_149675_a(true);
        float f = 0.4F;

        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
        this.func_149711_c(0.0F);
        this.func_149672_a(Block.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int metadata) {
        return this.icons[metadata];
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.iconNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            if (this.iconNames[i] != null) {
                this.icons[i] = par1IconRegister.func_94245_a("TwilightForest:" + this.iconNames[i]);
            }
        }

        this.icons[10] = Blocks.field_150329_H.func_149691_a(2, 1);
        this.icons[11] = Blocks.field_150330_I.func_149733_h(2);
        BlockTFPlant.mayappleSide = par1IconRegister.func_94245_a("TwilightForest:mayapple_side");
    }

    public int func_149635_D() {
        double d0 = 0.5D;
        double d1 = 1.0D;

        return ColorizerGrass.func_77480_a(d0, d1);
    }

    public void func_149726_b(World world, int i, int j, int k) {
        world.func_147464_a(i, j, k, this, world.field_73012_v.nextInt(50) + 20);
    }

    public boolean func_149705_a(World par1World, int x, int y, int z, int par5, ItemStack par6ItemStack) {
        Block blockAt = par1World.func_147439_a(x, y, z);

        return (blockAt == Blocks.field_150350_a || blockAt.func_149688_o().func_76222_j()) && this.canBlockStay(par1World, x, y, z, par6ItemStack.func_77960_j());
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return this.canBlockStay(world, x, y, z, meta);
    }

    public boolean canBlockStay(World world, int x, int y, int z, int meta) {
        Block soil = world.func_147439_a(x, y - 1, z);

        switch (meta) {
        case 0:
        case 10:
        case 11:
            return soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);

        case 1:
        case 2:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 12:
        default:
            return (world.func_72883_k(x, y, z) >= 3 || world.func_72937_j(x, y, z)) && soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);

        case 3:
        case 9:
            return soil != null && soil.isSideSolid(world, x, y, z, ForgeDirection.UP);

        case 13:
        case 14:
            return canPlaceRootBelow(world, x, y + 1, z);
        }
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z);
        long seed;
        int xOff0;
        int xOff1;
        int zOff0;
        int zOff1;
        boolean xConnect0;
        boolean xConnect1;

        if (meta == 3) {
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 11L;
            xOff0 = (int) (seed >> 12 & 3L);
            xOff1 = (int) (seed >> 15 & 3L);
            zOff0 = (int) (seed >> 18 & 3L);
            zOff1 = (int) (seed >> 21 & 3L);
            boolean yOff0 = par1IBlockAccess.func_147439_a(x + 1, y, z) == this && par1IBlockAccess.func_72805_g(x + 1, y, z) == 3;
            boolean yOff1 = par1IBlockAccess.func_147439_a(x - 1, y, z) == this && par1IBlockAccess.func_72805_g(x - 1, y, z) == 3;

            xConnect0 = par1IBlockAccess.func_147439_a(x, y, z + 1) == this && par1IBlockAccess.func_72805_g(x, y, z + 1) == 3;
            xConnect1 = par1IBlockAccess.func_147439_a(x, y, z - 1) == this && par1IBlockAccess.func_72805_g(x, y, z - 1) == 3;
            this.func_149676_a(yOff1 ? 0.0F : (1.0F + (float) xOff1) / 16.0F, 0.0F, xConnect1 ? 0.0F : (1.0F + (float) zOff1) / 16.0F, yOff0 ? 1.0F : (15.0F - (float) xOff0) / 16.0F, 0.0625F, xConnect0 ? 1.0F : (15.0F - (float) zOff0) / 16.0F);
        } else if (meta == 5) {
            seed = (long) (x * 3129871) ^ (long) y * 116129781L ^ (long) z;
            seed = seed * seed * 42317861L + seed * 11L;
            xOff0 = (int) (seed >> 12 & 3L);
            xOff1 = (int) (seed >> 15 & 3L);
            zOff0 = (int) (seed >> 18 & 3L);
            zOff1 = (int) (seed >> 21 & 3L);
            int yOff01 = (int) (seed >> 24 & 1L);
            int yOff11 = (int) (seed >> 27 & 1L);

            xConnect0 = par1IBlockAccess.func_147439_a(x + 1, y, z) == this && par1IBlockAccess.func_72805_g(x + 1, y, z) == 5;
            xConnect1 = par1IBlockAccess.func_147439_a(x - 1, y, z) == this && par1IBlockAccess.func_72805_g(x - 1, y, z) == 5;
            boolean zConnect0 = par1IBlockAccess.func_147439_a(x, y, z + 1) == this && par1IBlockAccess.func_72805_g(x, y, z + 1) == 5;
            boolean zConnect1 = par1IBlockAccess.func_147439_a(x, y, z - 1) == this && par1IBlockAccess.func_72805_g(x, y, z - 1) == 5;

            this.func_149676_a(xConnect1 ? 0.0F : (1.0F + (float) xOff1) / 16.0F, 0.0F, zConnect1 ? 0.0F : (1.0F + (float) zOff1) / 16.0F, xConnect0 ? 1.0F : (15.0F - (float) xOff0) / 16.0F, (1.0F + (float) yOff01 + (float) yOff11) / 16.0F, zConnect0 ? 1.0F : (15.0F - (float) zOff0) / 16.0F);
        } else if (meta == 4) {
            this.func_149676_a(0.25F, 0.0F, 0.25F, 0.8125F, 0.375F, 0.8125F);
        } else {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    public int func_149741_i(int par1) {
        return this.isGrassColor[par1] ? ColorizerFoliage.func_77468_c() : 16777215;
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int x, int y, int z) {
        int meta = par1IBlockAccess.func_72805_g(x, y, z);

        return this.isGrassColor[meta] ? par1IBlockAccess.func_72807_a(x, z).func_150558_b(x, y, z) : 16777215;
    }

    public AxisAlignedBB func_149668_a(World par1World, int x, int y, int z) {
        par1World.func_72805_g(x, y, z);
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getPlantBlockRenderID();
    }

    public void func_149674_a(World par1World, int x, int y, int z, Random par5Random) {
        int meta = par1World.func_72805_g(x, y, z);

        if (par1World.func_72957_l(x, y, z) < this.field_149784_t[meta]) {
            ;
        }

    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return this.field_149784_t[meta];
    }

    public static boolean canPlaceRootBelow(World world, int x, int y, int z) {
        Block blockID = world.func_147439_a(x, y, z);

        if (blockID != null && (blockID.func_149688_o() == Material.field_151578_c || blockID.func_149688_o() == Material.field_151577_b)) {
            return true;
        } else {
            int blockMeta = world.func_72805_g(x, y, z);

            return blockID == TFBlocks.plant && blockMeta == 14 || blockID == TFBlocks.root && blockMeta == 0;
        }
    }

    public ArrayList getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList ret = new ArrayList();

        switch (meta) {
        case 3:
        case 4:
        case 5:
        case 10:
        case 11:
        case 14:
            break;

        case 6:
        case 7:
        case 8:
        case 9:
        case 12:
        default:
            ret.add(new ItemStack(this, 1, meta));
            break;

        case 13:
            ret.add(new ItemStack(TFItems.torchberries));
        }

        return ret;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList ret = new ArrayList();

        ret.add(new ItemStack(this, 1, world.func_72805_g(x, y, z)));
        return ret;
    }

    public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
        if (world.field_72995_K || player.func_71045_bC() == null || player.func_71045_bC().func_77973_b() != Items.field_151097_aZ) {
            super.func_149636_a(world, player, x, y, z, meta);
        }

    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(this, 1, 3));
        par3List.add(new ItemStack(this, 1, 4));
        par3List.add(new ItemStack(this, 1, 8));
        par3List.add(new ItemStack(this, 1, 9));
        par3List.add(new ItemStack(this, 1, 10));
        par3List.add(new ItemStack(this, 1, 11));
        par3List.add(new ItemStack(this, 1, 13));
        par3List.add(new ItemStack(this, 1, 14));
    }

    public void func_149683_g() {
        this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        switch (meta) {
        case 3:
        case 9:
            return EnumPlantType.Cave;

        default:
            return EnumPlantType.Plains;
        }
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World par1World, int x, int y, int z, Random par5Random) {
        super.func_149734_b(par1World, x, y, z, par5Random);
        int meta = par1World.func_72805_g(x, y, z);

        if (meta == 3 && par5Random.nextInt(10) == 0) {
            par1World.func_72869_a("townaura", (double) ((float) x + par5Random.nextFloat()), (double) ((float) y + 0.1F), (double) ((float) z + par5Random.nextFloat()), 0.0D, 0.0D, 0.0D);
        }

    }
}
