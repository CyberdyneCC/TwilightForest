package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenerator;

public class ItemTFOreMagnet extends ItemTF {

    private static final float WIGGLE = 10.0F;
    private IIcon[] icons;
    private String[] iconNames = new String[] { "oreMagnet", "oreMagnet1", "oreMagnet2"};

    protected ItemTFOreMagnet() {
        this.func_77637_a(TFItems.creativeTab);
        this.field_77777_bU = 1;
        this.func_77656_e(12);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
        player.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public void func_77615_a(ItemStack par1ItemStack, World world, EntityPlayer player, int useRemaining) {
        int useTime = this.func_77626_a(par1ItemStack) - useRemaining;

        if (!world.field_72995_K && useTime > 10) {
            int moved = this.doMagnet(world, player, 0.0F, 0.0F);

            if (moved == 0) {
                moved = this.doMagnet(world, player, 10.0F, 0.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, 10.0F, 10.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, 0.0F, 10.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, -10.0F, 10.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, -10.0F, 0.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, -10.0F, -10.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, 0.0F, -10.0F);
            }

            if (moved == 0) {
                moved = this.doMagnet(world, player, 10.0F, -10.0F);
            }

            if (moved > 0) {
                par1ItemStack.func_77972_a(moved, player);
                if (par1ItemStack.field_77994_a == 0) {
                    player.func_71028_bD();
                }

                world.func_72956_a(player, "mob.endermen.portal", 1.0F, 1.0F);
            }
        }

    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem != null && usingItem.func_77973_b() == this) {
            int useTime = usingItem.func_77988_m() - useRemaining;

            if (useTime >= 20) {
                return (useTime >> 2) % 2 == 0 ? this.icons[2] : this.icons[1];
            }

            if (useTime > 10) {
                return this.icons[1];
            }
        }

        return this.icons[0];
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.icons = new IIcon[this.iconNames.length];

        for (int i = 0; i < this.iconNames.length; ++i) {
            this.icons[i] = par1IconRegister.func_94245_a("TwilightForest:" + this.iconNames[i]);
        }

    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    protected int doMagnet(World world, EntityPlayer player, float yawOffset, float pitchOffset) {
        double range = 32.0D;
        Vec3 srcVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 lookVec = this.getOffsetLook(player, yawOffset, pitchOffset);
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        int useX = MathHelper.func_76128_c(srcVec.field_72450_a);
        int useY = MathHelper.func_76128_c(srcVec.field_72448_b);
        int useZ = MathHelper.func_76128_c(srcVec.field_72449_c);
        int destX = MathHelper.func_76128_c(destVec.field_72450_a);
        int destY = MathHelper.func_76128_c(destVec.field_72448_b);
        int destZ = MathHelper.func_76128_c(destVec.field_72449_c);
        int blocksMoved = doMagnet(world, useX, useY, useZ, destX, destY, destZ);

        return blocksMoved;
    }

    public static int doMagnet(World world, int useX, int useY, int useZ, int destX, int destY, int destZ) {
        int blocksMoved = 0;
        ChunkCoordinates[] lineArray = TFGenerator.getBresehnamArrayCoords(useX, useY, useZ, destX, destY, destZ);
        Block foundID = Blocks.field_150350_a;
        int foundMeta = -1;
        int foundX = -1;
        int foundY = -1;
        int foundZ = -1;
        int baseX = -1;
        int baseY = -1;
        int baseZ = -1;
        boolean isNetherrack = false;
        ChunkCoordinates[] veinBlocks = lineArray;
        int offX = lineArray.length;

        int offY;

        for (offY = 0; offY < offX; ++offY) {
            ChunkCoordinates offZ = veinBlocks[offY];
            Block searchID = world.func_147439_a(offZ.field_71574_a, offZ.field_71572_b, offZ.field_71573_c);
            int coord = world.func_72805_g(offZ.field_71574_a, offZ.field_71572_b, offZ.field_71573_c);

            if (baseY == -1) {
                if (isReplaceable(world, searchID, coord, offZ.field_71574_a, offZ.field_71572_b, offZ.field_71573_c)) {
                    baseX = offZ.field_71574_a;
                    baseY = offZ.field_71572_b;
                    baseZ = offZ.field_71573_c;
                } else if (isNetherReplaceable(world, searchID, coord, offZ.field_71574_a, offZ.field_71572_b, offZ.field_71573_c)) {
                    isNetherrack = true;
                    baseX = offZ.field_71574_a;
                    baseY = offZ.field_71572_b;
                    baseZ = offZ.field_71573_c;
                }
            }

            if (searchID != Blocks.field_150350_a && isOre(searchID, coord)) {
                foundID = searchID;
                foundMeta = coord;
                foundX = offZ.field_71574_a;
                foundY = offZ.field_71572_b;
                foundZ = offZ.field_71573_c;
                break;
            }
        }

        if (baseY != -1 && foundID != Blocks.field_150350_a) {
            ArrayList arraylist = new ArrayList();

            findVein(world, foundX, foundY, foundZ, foundID, foundMeta, arraylist);
            offX = baseX - foundX;
            offY = baseY - foundY;
            int i = baseZ - foundZ;
            Iterator iterator = arraylist.iterator();

            while (iterator.hasNext()) {
                int replaceX;
                int replaceY;
                int replaceZ;
                ChunkCoordinates chunkcoordinates;
                label42: {
                    chunkcoordinates = (ChunkCoordinates) iterator.next();
                    replaceX = chunkcoordinates.field_71574_a + offX;
                    replaceY = chunkcoordinates.field_71572_b + offY;
                    replaceZ = chunkcoordinates.field_71573_c + i;
                    Block replaceID = world.func_147439_a(replaceX, replaceY, replaceZ);
                    int replaceMeta = world.func_72805_g(replaceX, replaceY, replaceZ);

                    if (isNetherrack) {
                        if (isNetherReplaceable(world, replaceID, replaceMeta, replaceX, replaceY, replaceZ)) {
                            break label42;
                        }
                    } else if (isReplaceable(world, replaceID, replaceMeta, replaceX, replaceY, replaceZ)) {
                        break label42;
                    }

                    if (replaceID != Blocks.field_150350_a) {
                        continue;
                    }
                }

                world.func_147465_d(chunkcoordinates.field_71574_a, chunkcoordinates.field_71572_b, chunkcoordinates.field_71573_c, isNetherrack ? Blocks.field_150424_aL : Blocks.field_150348_b, 0, 2);
                world.func_147465_d(replaceX, replaceY, replaceZ, foundID, foundMeta, 2);
                ++blocksMoved;
            }
        }

        return blocksMoved;
    }

    private Vec3 getOffsetLook(EntityPlayer player, float yawOffset, float pitchOffset) {
        float f = MathHelper.func_76134_b(-(player.field_70177_z + yawOffset) * 0.017453292F - 3.1415927F);
        float f1 = MathHelper.func_76126_a(-(player.field_70177_z + yawOffset) * 0.017453292F - 3.1415927F);
        float f2 = -MathHelper.func_76134_b(-(player.field_70125_A + pitchOffset) * 0.017453292F);
        float f3 = MathHelper.func_76126_a(-(player.field_70125_A + pitchOffset) * 0.017453292F);

        return Vec3.func_72443_a((double) (f1 * f2), (double) f3, (double) (f * f2));
    }

    private static boolean isReplaceable(World world, Block replaceID, int replaceMeta, int x, int y, int z) {
        return replaceID == Blocks.field_150346_d ? true : (replaceID == Blocks.field_150349_c ? true : (replaceID == Blocks.field_150351_n ? true : replaceID != Blocks.field_150350_a && replaceID.isReplaceableOreGen(world, x, y, z, Blocks.field_150348_b)));
    }

    private static boolean isNetherReplaceable(World world, Block replaceID, int replaceMeta, int x, int y, int z) {
        return replaceID == Blocks.field_150424_aL ? true : replaceID != Blocks.field_150350_a && replaceID.isReplaceableOreGen(world, x, y, z, Blocks.field_150424_aL);
    }

    private static boolean findVein(World world, int x, int y, int z, Block oreID, int oreMeta, ArrayList veinBlocks) {
        ChunkCoordinates here = new ChunkCoordinates(x, y, z);

        if (veinBlocks.contains(here)) {
            return false;
        } else if (veinBlocks.size() >= 24) {
            return false;
        } else if (world.func_147439_a(x, y, z) == oreID && world.func_72805_g(x, y, z) == oreMeta) {
            veinBlocks.add(here);
            findVein(world, x + 1, y, z, oreID, oreMeta, veinBlocks);
            findVein(world, x - 1, y, z, oreID, oreMeta, veinBlocks);
            findVein(world, x, y + 1, z, oreID, oreMeta, veinBlocks);
            findVein(world, x, y - 1, z, oreID, oreMeta, veinBlocks);
            findVein(world, x, y, z + 1, oreID, oreMeta, veinBlocks);
            findVein(world, x, y, z - 1, oreID, oreMeta, veinBlocks);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOre(Block blockID, int meta) {
        return blockID == Blocks.field_150365_q ? false : (blockID == Blocks.field_150366_p ? true : (blockID == Blocks.field_150482_ag ? true : (blockID == Blocks.field_150412_bA ? true : (blockID == Blocks.field_150352_o ? true : (blockID == Blocks.field_150369_x ? true : (blockID == Blocks.field_150450_ax ? true : (blockID == Blocks.field_150439_ay ? true : (blockID == Blocks.field_150449_bY ? true : (blockID == TFBlocks.root && meta == 1 ? true : blockID.func_149739_a().toLowerCase().contains("ore"))))))))));
    }
}
