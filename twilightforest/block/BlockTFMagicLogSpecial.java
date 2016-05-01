package twilightforest.block;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import twilightforest.TFGenericPacketHandler;
import twilightforest.TwilightForestMod;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.item.ItemTFOreMagnet;
import twilightforest.item.TFItems;

public class BlockTFMagicLogSpecial extends BlockTFMagicLog {

    protected BlockTFMagicLogSpecial() {
        this.func_149647_a(TFItems.creativeTab);
    }

    public int func_149738_a(World par1World) {
        return 20;
    }

    public void func_149726_b(World par1World, int par2, int par3, int par4) {
        par1World.func_147464_a(par2, par3, par4, this, this.func_149738_a(par1World));
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a(TFBlocks.magicLog);
    }

    public IIcon func_149691_a(int side, int meta) {
        int orient = meta & 12;
        int woodType = meta & 3;

        if (orient == 12) {
            switch (woodType) {
            case 0:
            default:
                return side != 1 && side != 0 ? BlockTFMagicLogSpecial.SPR_TIMECLOCKOFF : BlockTFMagicLogSpecial.SPR_TIMETOP;

            case 1:
                return side != 1 && side != 0 ? BlockTFMagicLogSpecial.SPR_TRANSHEARTOFF : BlockTFMagicLogSpecial.SPR_TRANSTOP;

            case 2:
                return side != 1 && side != 0 ? BlockTFMagicLogSpecial.SPR_MINEGEMOFF : BlockTFMagicLogSpecial.SPR_MINETOP;

            case 3:
                return side != 1 && side != 0 ? BlockTFMagicLogSpecial.SPR_SORTEYEOFF : BlockTFMagicLogSpecial.SPR_SORTTOP;
            }
        } else {
            switch (woodType) {
            case 0:
            default:
                return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLogSpecial.SPR_TIMETOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLogSpecial.SPR_TIMETOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLogSpecial.SPR_TIMETOP : BlockTFMagicLogSpecial.SPR_TIMECLOCK));

            case 1:
                return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLogSpecial.SPR_TRANSTOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLogSpecial.SPR_TRANSTOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLogSpecial.SPR_TRANSTOP : BlockTFMagicLogSpecial.SPR_TRANSHEART));

            case 2:
                return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLogSpecial.SPR_MINETOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLogSpecial.SPR_MINETOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLogSpecial.SPR_MINETOP : BlockTFMagicLogSpecial.SPR_MINEGEM));

            case 3:
                return orient == 0 && (side == 1 || side == 0) ? BlockTFMagicLogSpecial.SPR_SORTTOP : (orient == 4 && (side == 5 || side == 4) ? BlockTFMagicLogSpecial.SPR_SORTTOP : (orient == 8 && (side == 2 || side == 3) ? BlockTFMagicLogSpecial.SPR_SORTTOP : BlockTFMagicLogSpecial.SPR_SORTEYE));
            }
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        int meta = world.func_72805_g(x, y, z);

        if ((meta & 12) != 12) {
            if ((meta & 3) == 0 && !world.field_72995_K) {
                world.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.1F, 0.5F);
                this.doTreeOfTimeEffect(world, x, y, z, rand);
            } else if ((meta & 3) == 1 && !world.field_72995_K) {
                this.doTreeOfTransformationEffect(world, x, y, z, rand);
            } else if ((meta & 3) == 2 && !world.field_72995_K) {
                this.doMinersTreeEffect(world, x, y, z, rand);
            } else if ((meta & 3) == 3 && !world.field_72995_K) {
                this.doSortingTreeEffect(world, x, y, z, rand);
            }

            world.func_147464_a(x, y, z, this, this.func_149738_a(world));
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int meta = world.func_72805_g(x, y, z);
        int orient = meta & 12;
        int woodType = meta & 3;

        if (orient == 0) {
            world.func_72921_c(x, y, z, woodType | 12, 3);
            return true;
        } else if (orient == 12) {
            world.func_72921_c(x, y, z, woodType | 0, 3);
            world.func_147464_a(x, y, z, this, this.func_149738_a(world));
            return true;
        } else {
            return false;
        }
    }

    private void doTreeOfTimeEffect(World world, int x, int y, int z, Random rand) {
        int numticks = 24 * this.func_149738_a(world);
        int successes = 0;

        for (int i = 0; i < numticks; ++i) {
            int dx = rand.nextInt(32) - 16;
            int dy = rand.nextInt(32) - 16;
            int dz = rand.nextInt(32) - 16;
            Block thereID = world.func_147439_a(x + dx, y + dy, z + dz);

            if (thereID != Blocks.field_150350_a && thereID.func_149653_t()) {
                thereID.func_149674_a(world, x + dx, y + dy, z + dz, rand);
                ++successes;
            }
        }

    }

    private void doTreeOfTransformationEffect(World world, int x, int y, int z, Random rand) {
        for (int i = 0; i < 1; ++i) {
            int dx = rand.nextInt(32) - 16;
            int dz = rand.nextInt(32) - 16;

            world.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "note.harp", 0.1F, rand.nextFloat() * 2.0F);
            if (Math.sqrt((double) (dx * dx + dz * dz)) < 16.0D) {
                BiomeGenBase biomeAt = world.func_72807_a(x + dx, z + dz);

                if (biomeAt != TFBiomeBase.enchantedForest) {
                    Chunk chunkAt = world.func_72938_d(x + dx, z + dz);

                    chunkAt.func_76605_m()[(z + dz & 15) << 4 | x + dx & 15] = (byte) TFBiomeBase.enchantedForest.field_76756_M;
                    world.func_147471_g(x + dx, y, z + dz);
                    if (world instanceof WorldServer) {
                        this.sendChangedBiome(world, x + dx, z + dz, chunkAt);
                    }
                }
            }
        }

    }

    private void sendChangedBiome(World world, int x, int z, Chunk chunkAt) {
        FMLProxyPacket message = TFGenericPacketHandler.makeBiomeChangePacket(x, z, (byte) TFBiomeBase.enchantedForest.field_76756_M);
        TargetPoint targetPoint = new TargetPoint(world.field_73011_w.field_76574_g, (double) x, 128.0D, (double) z, 128.0D);

        TwilightForestMod.genericChannel.sendToAllAround(message, targetPoint);
    }

    private void doMinersTreeEffect(World world, int x, int y, int z, Random rand) {
        int dx = rand.nextInt(64) - 32;
        int dy = rand.nextInt(64) - 32;
        int dz = rand.nextInt(64) - 32;
        int moved = ItemTFOreMagnet.doMagnet(world, x, y, z, x + dx, y + dy, z + dz);

        if (moved > 0) {
            world.func_72908_a((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "mob.endermen.portal", 0.1F, 1.0F);
        }

    }

    private void doSortingTreeEffect(World world, int x, int y, int z, Random rand) {
        byte XSEARCH = 16;
        byte YSEARCH = 16;
        byte ZSEARCH = 16;
        ArrayList chests = new ArrayList();
        int itemCount = 0;

        int sortedChestNum;
        int sortedSlotNum;
        int matchCount;
        int moveChest;

        for (int beingSorted = x - XSEARCH; beingSorted < x + XSEARCH; ++beingSorted) {
            for (sortedChestNum = y - YSEARCH; sortedChestNum < y + YSEARCH; ++sortedChestNum) {
                for (sortedSlotNum = z - ZSEARCH; sortedSlotNum < z + ZSEARCH; ++sortedSlotNum) {
                    if (world.func_147439_a(beingSorted, sortedChestNum, sortedSlotNum) == Blocks.field_150486_ae) {
                        IInventory matchChestNum = Blocks.field_150486_ae.func_149951_m(world, beingSorted, sortedChestNum, sortedSlotNum);

                        if (matchChestNum != null && !this.checkIfChestsContains(chests, (IInventory) world.func_147438_o(beingSorted, sortedChestNum, sortedSlotNum))) {
                            matchCount = 0;

                            for (moveChest = 0; moveChest < matchChestNum.func_70302_i_(); ++moveChest) {
                                if (matchChestNum.func_70301_a(moveChest) != null) {
                                    ++matchCount;
                                    ++itemCount;
                                }
                            }

                            if (matchCount > 0) {
                                chests.add(matchChestNum);
                            }
                        }
                    }
                }
            }
        }

        ItemStack itemstack = null;

        sortedChestNum = -1;
        sortedSlotNum = -1;
        IInventory chest;
        int slotNum;
        ItemStack currentItem;
        int i;

        if (itemCount > 0) {
            i = rand.nextInt(itemCount);
            matchCount = 0;

            for (moveChest = 0; moveChest < chests.size(); ++moveChest) {
                chest = (IInventory) chests.get(moveChest);

                for (slotNum = 0; slotNum < chest.func_70302_i_(); ++slotNum) {
                    currentItem = chest.func_70301_a(slotNum);
                    if (currentItem != null && matchCount++ == i) {
                        itemstack = currentItem;
                        sortedChestNum = moveChest;
                        sortedSlotNum = slotNum;
                    }
                }
            }
        }

        if (itemstack != null) {
            i = -1;
            matchCount = 0;

            for (moveChest = 0; moveChest < chests.size(); ++moveChest) {
                chest = (IInventory) chests.get(moveChest);
                slotNum = 0;

                for (int j = 0; j < chest.func_70302_i_(); ++j) {
                    ItemStack currentItem1 = chest.func_70301_a(j);

                    if (currentItem1 != null && this.isSortingMatch(itemstack, currentItem1)) {
                        slotNum += currentItem1.field_77994_a;
                    }
                }

                if (slotNum > matchCount) {
                    matchCount = slotNum;
                    i = moveChest;
                }
            }

            if (i >= 0 && i != sortedChestNum) {
                IInventory iinventory = (IInventory) chests.get(i);

                chest = (IInventory) chests.get(sortedChestNum);
                slotNum = this.getEmptySlotIn(iinventory);
                if (slotNum >= 0) {
                    chest.func_70299_a(sortedSlotNum, (ItemStack) null);
                    iinventory.func_70299_a(slotNum, itemstack);
                }
            }

            if (itemstack.field_77994_a < itemstack.func_77976_d()) {
                Iterator iterator = chests.iterator();

                while (iterator.hasNext()) {
                    chest = (IInventory) iterator.next();

                    for (slotNum = 0; slotNum < chest.func_70302_i_(); ++slotNum) {
                        currentItem = chest.func_70301_a(slotNum);
                        if (currentItem != null && currentItem != itemstack && itemstack.func_77969_a(currentItem) && currentItem.field_77994_a <= itemstack.func_77976_d() - itemstack.field_77994_a) {
                            chest.func_70299_a(slotNum, (ItemStack) null);
                            itemstack.field_77994_a += currentItem.field_77994_a;
                            currentItem.field_77994_a = 0;
                        }
                    }
                }
            }
        }

    }

    private boolean isSortingMatch(ItemStack beingSorted, ItemStack currentItem) {
        return this.getCreativeTab(currentItem.func_77973_b()) == this.getCreativeTab(beingSorted.func_77973_b());
    }

    private Object getCreativeTab(Item item) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(Item.class, item, 0);
        } catch (IllegalArgumentException illegalargumentexception) {
            illegalargumentexception.printStackTrace();
        } catch (SecurityException securityexception) {
            securityexception.printStackTrace();
        }

        return null;
    }

    private boolean checkIfChestsContains(ArrayList chests, IInventory testChest) {
        Iterator iterator = chests.iterator();

        IInventory chest;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            chest = (IInventory) iterator.next();
            if (chest == testChest) {
                return true;
            }
        } while (!(chest instanceof InventoryLargeChest) || !((InventoryLargeChest) chest).func_90010_a(testChest));

        return true;
    }

    private int getEmptySlotIn(IInventory chest) {
        for (int i = 0; i < chest.func_70302_i_(); ++i) {
            if (chest.func_70301_a(i) == null) {
                return i;
            }
        }

        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {}

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
}
