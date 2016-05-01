package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFHugeLilyPad extends BlockBush {

    private IIcon pad1;
    private IIcon pad2;
    private IIcon pad3;
    private IIcon blank;
    private boolean isSelfDestructing = false;

    protected BlockTFHugeLilyPad() {
        super(Material.field_151585_k);
        float f = 0.5F;
        float f1 = 0.015625F;

        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
        this.func_149672_a(BlockTFHugeLilyPad.field_149779_h);
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getHugeLilyPadBlockRenderID();
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return AxisAlignedBB.func_72330_a((double) p_149668_2_ + this.field_149759_B, (double) p_149668_3_ + this.field_149760_C, (double) p_149668_4_ + this.field_149754_D, (double) p_149668_2_ + this.field_149755_E, (double) p_149668_3_ + this.field_149756_F, (double) p_149668_4_ + this.field_149757_G);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public IIcon func_149691_a(int side, int meta) {
        if (side > 1) {
            return this.blank;
        } else {
            int orient = meta >> 2;
            int piece = meta & 3;

            if (orient == 1) {
                orient = 3;
            } else if (orient == 3) {
                orient = 1;
            }

            int display = (piece + orient) % 4;

            switch (display) {
            case 0:
            default:
                return this.field_149761_L;

            case 1:
                return this.pad1;

            case 2:
                return this.pad2;

            case 3:
                return this.pad3;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("TwilightForest:huge_lilypad_0");
        this.pad1 = par1IconRegister.func_94245_a("TwilightForest:huge_lilypad_1");
        this.pad2 = par1IconRegister.func_94245_a("TwilightForest:huge_lilypad_2");
        this.pad3 = par1IconRegister.func_94245_a("TwilightForest:huge_lilypad_3");
        this.blank = par1IconRegister.func_94245_a("TwilightForest:blank");
    }

    protected boolean func_149854_a(Block block) {
        return block == Blocks.field_150355_j;
    }

    public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
        this.setGiantBlockToAir(world, x, y, z);
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        world.func_147468_f(x, y, z);
        this.setGiantBlockToAir(world, x, y, z);
    }

    public void func_149725_f(World world, int x, int y, int z, int meta) {
        int orient = meta >> 2;
        int piece = meta & 3;
        int display = (piece + orient) % 4;

        if (!this.isSelfDestructing && !this.func_149718_j(world, x, y, z)) {
            this.setGiantBlockToAir(world, x, y, z);
        }

    }

    private void setGiantBlockToAir(World world, int x, int y, int z) {
        this.isSelfDestructing = true;
        int bx = x >> 1 << 1;
        int bz = z >> 1 << 1;

        for (int dx = 0; dx < 2; ++dx) {
            for (int dz = 0; dz < 2; ++dz) {
                if ((x != bx + dx || z != bz + dz) && world.func_147439_a(bx + dx, y, bz + dz) == this) {
                    world.func_147465_d(bx + dx, y, bz + dz, Blocks.field_150350_a, 0, 2);
                }
            }
        }

        this.isSelfDestructing = false;
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        boolean allThisBlock = true;
        boolean allWater = true;
        int bx = x >> 1 << 1;
        int bz = z >> 1 << 1;

        for (int dx = 0; dx < 2; ++dx) {
            for (int dz = 0; dz < 2; ++dz) {
                allThisBlock &= world.func_147439_a(bx + dx, y, bz + dz) == this;
                allWater &= world.func_147439_a(bx + dx, y - 1, bz + dz).func_149688_o() == Material.field_151586_h && world.func_72805_g(bx + dx, y - 1, bz + dz) == 0;
            }
        }

        return allThisBlock && allWater;
    }

    protected void func_149855_e(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_) {
        if (!this.func_149718_j(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_)) {
            p_149855_1_.func_147465_d(p_149855_2_, p_149855_3_, p_149855_4_, func_149729_e(0), 0, 2);
        }

    }

    public int func_149656_h() {
        return 2;
    }

    @SideOnly(Side.CLIENT)
    public int func_149635_D() {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int func_149741_i(int p_149741_1_) {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return 2129968;
    }
}
