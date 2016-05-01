package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TwilightForestMod;
import twilightforest.item.TFItems;

public class BlockTFTrophyPedestal extends Block {

    private IIcon sprTopActive;
    private IIcon sprTop;
    private IIcon sprBottom;
    private IIcon sprNagaActive;
    private IIcon sprNaga;
    private IIcon sprLichActive;
    private IIcon sprLich;
    private IIcon sprHydraActive;
    private IIcon sprHydra;
    private IIcon sprUrghastActive;
    private IIcon sprUrghast;

    public BlockTFTrophyPedestal() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0F);
        this.func_149752_b(2000.0F);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        if (side == 1) {
            return meta > 7 ? this.sprTopActive : this.sprTop;
        } else {
            if (side >= 2 && side <= 5) {
                int rotate = meta & 3;
                int rotatedSide = (side - 2 + rotate) % 4;

                switch (rotatedSide) {
                case 0:
                    return meta > 7 ? this.sprNagaActive : this.sprNaga;

                case 1:
                    return meta > 7 ? this.sprLichActive : this.sprLich;

                case 2:
                    return meta > 7 ? this.sprHydraActive : this.sprHydra;

                case 3:
                    return meta > 7 ? this.sprUrghastActive : this.sprUrghast;
                }
            }

            return this.sprTop;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.sprTopActive = par1IconRegister.func_94245_a("TwilightForest:pedestal_top_active");
        this.sprTop = par1IconRegister.func_94245_a("TwilightForest:pedestal_top");
        this.sprBottom = par1IconRegister.func_94245_a("TwilightForest:pedestal_top");
        this.sprNagaActive = par1IconRegister.func_94245_a("TwilightForest:pedestal_naga_active");
        this.sprNaga = par1IconRegister.func_94245_a("TwilightForest:pedestal_naga");
        this.sprLichActive = par1IconRegister.func_94245_a("TwilightForest:pedestal_lich_active");
        this.sprLich = par1IconRegister.func_94245_a("TwilightForest:pedestal_lich");
        this.sprHydraActive = par1IconRegister.func_94245_a("TwilightForest:pedestal_hydra_active");
        this.sprHydra = par1IconRegister.func_94245_a("TwilightForest:pedestal_hydra");
        this.sprUrghastActive = par1IconRegister.func_94245_a("TwilightForest:pedestal_urghast_active");
        this.sprUrghast = par1IconRegister.func_94245_a("TwilightForest:pedestal_urghast");
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 15));
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return TwilightForestMod.proxy.getPedestalBlockRenderID();
    }

    @SideOnly(Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return true;
    }

    public void func_149695_a(World par1World, int x, int y, int z, Block myBlockID) {
        int meta = par1World.func_72805_g(x, y, z);

        if (!par1World.field_72995_K && meta > 0 && this.isTrophyOnTop(par1World, x, y, z)) {
            par1World.func_147464_a(x, y, z, this, 1);
        }

    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int facing = MathHelper.func_76128_c((double) (par5EntityLivingBase.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
        int latent = par6ItemStack.func_77960_j() & 8;

        if (facing == 0) {
            par1World.func_72921_c(par2, par3, par4, 0 | latent, 2);
        }

        if (facing == 1) {
            par1World.func_72921_c(par2, par3, par4, 1 | latent, 2);
        }

        if (facing == 2) {
            par1World.func_72921_c(par2, par3, par4, 3 | latent, 2);
        }

        if (facing == 3) {
            par1World.func_72921_c(par2, par3, par4, 2 | latent, 2);
        }

    }

    private boolean isTrophyOnTop(World world, int x, int y, int z) {
        return world.func_147439_a(x, y + 1, z) == TFBlocks.trophy;
    }

    public void func_149674_a(World world, int x, int y, int z, Random par5Random) {
        if (!world.field_72995_K) {
            int meta = world.func_72805_g(x, y, z);

            if (this.isTrophyOnTop(world, x, y, z)) {
                if (meta > 7) {
                    if (world.func_82736_K().func_82766_b("tfEnforcedProgression")) {
                        if (this.areNearbyPlayersEligible(world, x, y, z)) {
                            this.doPedestalEffect(world, x, y, z, meta);
                        }

                        this.warnIneligiblePlayers(world, x, y, z);
                    } else {
                        this.doPedestalEffect(world, x, y, z, meta);
                    }
                }

                this.rewardNearbyPlayers(world, x, y, z);
            }
        }

    }

    private void warnIneligiblePlayers(World world, int x, int y, int z) {
        List nearbyPlayers = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1)).func_72314_b(16.0D, 16.0D, 16.0D));
        Iterator iterator = nearbyPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer player = (EntityPlayer) iterator.next();

            if (!this.isPlayerEligible(player)) {
                player.func_145747_a(new ChatComponentText("You are unworthy."));
            }
        }

    }

    private boolean areNearbyPlayersEligible(World world, int x, int y, int z) {
        boolean isEligible = false;
        List nearbyPlayers = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1)).func_72314_b(16.0D, 16.0D, 16.0D));

        EntityPlayer player;

        for (Iterator iterator = nearbyPlayers.iterator(); iterator.hasNext(); isEligible |= this.isPlayerEligible(player)) {
            player = (EntityPlayer) iterator.next();
        }

        return isEligible;
    }

    private boolean isPlayerEligible(EntityPlayer player) {
        if (player instanceof EntityPlayerMP && ((EntityPlayerMP) player).func_147099_x() != null) {
            StatisticsFile stats1 = ((EntityPlayerMP) player).func_147099_x();

            return stats1.func_77443_a(TFAchievementPage.twilightProgressTrophyPedestal.field_75992_c);
        } else if (player instanceof EntityClientPlayerMP && ((EntityClientPlayerMP) player).func_146107_m() != null) {
            StatFileWriter stats = ((EntityClientPlayerMP) player).func_146107_m();

            return stats.func_77443_a(TFAchievementPage.twilightProgressTrophyPedestal.field_75992_c);
        } else {
            return false;
        }
    }

    private void doPedestalEffect(World world, int x, int y, int z, int meta) {
        this.removeNearbyShields(world, x, y, z);
        world.func_72921_c(x, y, z, meta & 7, 2);
        world.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "mob.zombie.infect", 4.0F, 0.1F);
    }

    private void rewardNearbyPlayers(World world, int x, int y, int z) {
        List nearbyPlayers = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double) x, (double) y, (double) z, (double) (x + 1), (double) (y + 1), (double) (z + 1)).func_72314_b(16.0D, 16.0D, 16.0D));
        Iterator iterator = nearbyPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer player = (EntityPlayer) iterator.next();

            player.func_71029_a(TFAchievementPage.twilightProgressTrophyPedestal);
        }

    }

    protected void removeNearbyShields(World world, int x, int y, int z) {
        for (int sx = -5; sx <= 5; ++sx) {
            for (int sy = -5; sy <= 5; ++sy) {
                for (int sz = -5; sz <= 5; ++sz) {
                    Block blockAt = world.func_147439_a(x + sx, y + sy, z + sz);
                    int metaAt = world.func_72805_g(x + sx, y + sy, z + sz);

                    if (blockAt == TFBlocks.shield && metaAt == 15) {
                        world.func_147465_d(x + sx, y + sy, z + sz, Blocks.field_150350_a, 0, 2);
                        world.func_72926_e(2001, x + sx, y + sy, z + sz, Block.func_149682_b(blockAt) + (metaAt << 12));
                    }
                }
            }
        }

    }

    public int func_149738_a(World world) {
        return 10;
    }

    public float func_149737_a(EntityPlayer par1EntityPlayer, World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);

        return meta > 0 ? -1.0F : super.func_149737_a(par1EntityPlayer, world, x, y, z);
    }
}
