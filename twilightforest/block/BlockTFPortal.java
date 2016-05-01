package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import twilightforest.TFAchievementPage;
import twilightforest.TFTeleporter;
import twilightforest.TwilightForestMod;

public class BlockTFPortal extends BlockBreakable {

    public BlockTFPortal() {
        super("TFPortal", Material.field_151567_E, false);
        this.func_149711_c(-1.0F);
        this.func_149672_a(Block.field_149778_k);
        this.func_149715_a(0.75F);
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public void func_149719_a(IBlockAccess iblockaccess, int i, int j, int k) {
        this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150427_aO.func_149691_a(side, meta);
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {}

    public boolean tryToCreatePortal(World world, int dx, int dy, int dz) {
        if (this.isGoodPortalPool(world, dx, dy, dz)) {
            world.func_72942_c(new EntityLightningBolt(world, (double) dx, (double) dy, (double) dz));
            this.transmuteWaterToPortal(world, dx, dy, dz);
            return true;
        } else {
            return false;
        }
    }

    public void transmuteWaterToPortal(World world, int dx, int dy, int dz) {
        int px = dx;
        int pz = dz;

        if (world.func_147439_a(dx - 1, dy, dz).func_149688_o() == Material.field_151586_h) {
            px = dx - 1;
        }

        if (world.func_147439_a(px, dy, dz - 1).func_149688_o() == Material.field_151586_h) {
            pz = dz - 1;
        }

        world.func_147465_d(px + 0, dy, pz + 0, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 1, dy, pz + 0, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 1, dy, pz + 1, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 0, dy, pz + 1, TFBlocks.portal, 0, 2);
    }

    public boolean isGoodPortalPool(World world, int dx, int dy, int dz) {
        boolean flag = false;

        flag |= this.isGoodPortalPoolStrict(world, dx + 0, dy, dz + 0);
        flag |= this.isGoodPortalPoolStrict(world, dx - 1, dy, dz - 1);
        flag |= this.isGoodPortalPoolStrict(world, dx + 0, dy, dz - 1);
        flag |= this.isGoodPortalPoolStrict(world, dx + 1, dy, dz - 1);
        flag |= this.isGoodPortalPoolStrict(world, dx - 1, dy, dz + 0);
        flag |= this.isGoodPortalPoolStrict(world, dx + 1, dy, dz + 0);
        flag |= this.isGoodPortalPoolStrict(world, dx - 1, dy, dz + 1);
        flag |= this.isGoodPortalPoolStrict(world, dx + 0, dy, dz + 1);
        flag |= this.isGoodPortalPoolStrict(world, dx + 1, dy, dz + 1);
        return flag;
    }

    public boolean isGoodPortalPoolStrict(World world, int dx, int dy, int dz) {
        boolean flag = true;

        flag &= world.func_147439_a(dx + 0, dy, dz + 0).func_149688_o() == Material.field_151586_h;
        flag &= world.func_147439_a(dx + 1, dy, dz + 0).func_149688_o() == Material.field_151586_h;
        flag &= world.func_147439_a(dx + 1, dy, dz + 1).func_149688_o() == Material.field_151586_h;
        flag &= world.func_147439_a(dx + 0, dy, dz + 1).func_149688_o() == Material.field_151586_h;
        flag &= this.isGrassOrDirt(world, dx - 1, dy, dz - 1);
        flag &= this.isGrassOrDirt(world, dx - 1, dy, dz + 0);
        flag &= this.isGrassOrDirt(world, dx - 1, dy, dz + 1);
        flag &= this.isGrassOrDirt(world, dx - 1, dy, dz + 2);
        flag &= this.isGrassOrDirt(world, dx + 0, dy, dz - 1);
        flag &= this.isGrassOrDirt(world, dx + 1, dy, dz - 1);
        flag &= this.isGrassOrDirt(world, dx + 0, dy, dz + 2);
        flag &= this.isGrassOrDirt(world, dx + 1, dy, dz + 2);
        flag &= this.isGrassOrDirt(world, dx + 2, dy, dz - 1);
        flag &= this.isGrassOrDirt(world, dx + 2, dy, dz + 0);
        flag &= this.isGrassOrDirt(world, dx + 2, dy, dz + 1);
        flag &= this.isGrassOrDirt(world, dx + 2, dy, dz + 2);
        flag &= world.func_147439_a(dx + 0, dy - 1, dz + 0).func_149688_o().func_76220_a();
        flag &= world.func_147439_a(dx + 1, dy - 1, dz + 0).func_149688_o().func_76220_a();
        flag &= world.func_147439_a(dx + 1, dy - 1, dz + 1).func_149688_o().func_76220_a();
        flag &= world.func_147439_a(dx + 0, dy - 1, dz + 1).func_149688_o().func_76220_a();
        flag &= this.isNatureBlock(world, dx - 1, dy + 1, dz - 1);
        flag &= this.isNatureBlock(world, dx - 1, dy + 1, dz + 0);
        flag &= this.isNatureBlock(world, dx - 1, dy + 1, dz + 1);
        flag &= this.isNatureBlock(world, dx - 1, dy + 1, dz + 2);
        flag &= this.isNatureBlock(world, dx + 0, dy + 1, dz - 1);
        flag &= this.isNatureBlock(world, dx + 1, dy + 1, dz - 1);
        flag &= this.isNatureBlock(world, dx + 0, dy + 1, dz + 2);
        flag &= this.isNatureBlock(world, dx + 1, dy + 1, dz + 2);
        flag &= this.isNatureBlock(world, dx + 2, dy + 1, dz - 1);
        flag &= this.isNatureBlock(world, dx + 2, dy + 1, dz + 0);
        flag &= this.isNatureBlock(world, dx + 2, dy + 1, dz + 1);
        flag &= this.isNatureBlock(world, dx + 2, dy + 1, dz + 2);
        return flag;
    }

    public boolean isNatureBlock(World world, int dx, int dy, int dz) {
        Material mat = world.func_147439_a(dx, dy, dz).func_149688_o();

        return mat == Material.field_151585_k || mat == Material.field_151582_l || mat == Material.field_151584_j;
    }

    public void func_149695_a(World world, int x, int y, int z, Block notUsed) {
        boolean good = true;

        if (world.func_147439_a(x - 1, y, z) == this) {
            good &= this.isGrassOrDirt(world, x + 1, y, z);
        } else if (world.func_147439_a(x + 1, y, z) == this) {
            good &= this.isGrassOrDirt(world, x - 1, y, z);
        } else {
            good = false;
        }

        if (world.func_147439_a(x, y, z - 1) == this) {
            good &= this.isGrassOrDirt(world, x, y, z + 1);
        } else if (world.func_147439_a(x, y, z + 1) == this) {
            good &= this.isGrassOrDirt(world, x, y, z - 1);
        } else {
            good = false;
        }

        if (!good) {
            world.func_147465_d(x, y, z, Blocks.field_150355_j, 0, 3);
        }

    }

    protected boolean isGrassOrDirt(World world, int dx, int dy, int dz) {
        return world.func_147439_a(dx, dy, dz).func_149688_o() == Material.field_151577_b || world.func_147439_a(dx, dy, dz).func_149688_o() == Material.field_151578_c;
    }

    public int func_149745_a(Random random) {
        return 0;
    }

    public int func_149701_w() {
        return 1;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        if (entity.field_70154_o == null && entity.field_70153_n == null && entity.field_71088_bW <= 0) {
            if (entity instanceof EntityPlayerMP) {
                EntityPlayerMP playerMP = (EntityPlayerMP) entity;

                if (playerMP.field_71088_bW > 0) {
                    playerMP.field_71088_bW = 10;
                } else if (playerMP.field_71093_bK != TwilightForestMod.dimensionID) {
                    playerMP.func_71029_a(TFAchievementPage.twilightPortal);
                    playerMP.func_71029_a(TFAchievementPage.twilightArrival);
                    System.out.println("Player touched the portal block.  Sending the player to dimension 7");
                    playerMP.field_71133_b.func_71203_ab().transferPlayerToDimension(playerMP, TwilightForestMod.dimensionID, new TFTeleporter(playerMP.field_71133_b.func_71218_a(TwilightForestMod.dimensionID)));
                    playerMP.func_82242_a(0);
                    playerMP.func_71029_a(TFAchievementPage.twilightPortal);
                    playerMP.func_71029_a(TFAchievementPage.twilightArrival);
                    int spawnX = MathHelper.func_76128_c(playerMP.field_70165_t);
                    int spawnY = MathHelper.func_76128_c(playerMP.field_70163_u);
                    int spawnZ = MathHelper.func_76128_c(playerMP.field_70161_v);

                    playerMP.setSpawnChunk(new ChunkCoordinates(spawnX, spawnY, spawnZ), true, TwilightForestMod.dimensionID);
                } else {
                    playerMP.field_71133_b.func_71203_ab().transferPlayerToDimension(playerMP, 0, new TFTeleporter(playerMP.field_71133_b.func_71218_a(0)));
                    playerMP.func_82242_a(0);
                }
            } else if (entity.field_71093_bK == TwilightForestMod.dimensionID) {
                this.sendEntityToDimension(entity, 0);
            }
        }

    }

    public void sendEntityToDimension(Entity entity, int par1) {
        if (!entity.field_70170_p.field_72995_K && !entity.field_70128_L) {
            entity.field_70170_p.field_72984_F.func_76320_a("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            int dim = entity.field_71093_bK;
            WorldServer worldserver = minecraftserver.func_71218_a(dim);
            WorldServer worldserver1 = minecraftserver.func_71218_a(par1);

            entity.field_71093_bK = par1;
            entity.field_70170_p.func_72900_e(entity);
            entity.field_70128_L = false;
            entity.field_70170_p.field_72984_F.func_76320_a("reposition");
            minecraftserver.func_71203_ab().transferEntityToWorld(entity, dim, worldserver, worldserver1, new TFTeleporter(worldserver1));
            entity.field_70170_p.field_72984_F.func_76318_c("reloading");
            Entity transferEntity = EntityList.func_75620_a(EntityList.func_75621_b(entity), worldserver1);

            if (transferEntity != null) {
                transferEntity.func_82141_a(entity, true);
                worldserver1.func_72838_d(transferEntity);
            }

            entity.field_70128_L = true;
            entity.field_70170_p.field_72984_F.func_76319_b();
            worldserver.func_82742_i();
            worldserver1.func_82742_i();
            entity.field_70170_p.field_72984_F.func_76319_b();
        }

    }

    public void func_149734_b(World world, int i, int j, int k, Random random) {
        if (random.nextInt(100) == 0) {
            world.func_72908_a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "portal.portal", 1.0F, random.nextFloat() * 0.4F + 0.8F);
        }

        for (int l = 0; l < 4; ++l) {
            double d = (double) ((float) i + random.nextFloat());
            double d1 = (double) ((float) j + random.nextFloat());
            double d2 = (double) ((float) k + random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = random.nextInt(2) * 2 - 1;

            d3 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            if (world.func_147439_a(i - 1, j, k) != this && world.func_147439_a(i + 1, j, k) != this) {
                d = (double) i + 0.5D + 0.25D * (double) i1;
                d3 = (double) (random.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) k + 0.5D + 0.25D * (double) i1;
                d5 = (double) (random.nextFloat() * 2.0F * (float) i1);
            }

            world.func_72869_a("portal", d, d1, d2, d3, d4, d5);
        }

    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }
}
