package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class ItemTFCrumbleHorn extends ItemTF {

    private static final int CHANCE_HARVEST = 20;
    private static final int CHANCE_CRUMBLE = 5;

    protected ItemTFCrumbleHorn() {
        this.func_77637_a(TFItems.creativeTab);
        this.field_77777_bU = 1;
        this.func_77656_e(1024);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
        player.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        world.func_72956_a(player, "mob.sheep.say", 1.0F, 0.8F);
        return par1ItemStack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count > 10 && count % 5 == 0 && !player.field_70170_p.field_72995_K) {
            int crumbled = this.doCrumble(player.field_70170_p, player);

            if (crumbled > 0) {
                stack.func_77972_a(crumbled, player);
            }

            player.field_70170_p.func_72956_a(player, "mob.sheep.say", 1.0F, 0.8F);
        }

    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    private int doCrumble(World world, EntityPlayer player) {
        double range = 3.0D;
        double radius = 2.0D;
        Vec3 srcVec = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 lookVec = player.func_70040_Z();
        Vec3 destVec = srcVec.func_72441_c(lookVec.field_72450_a * range, lookVec.field_72448_b * range, lookVec.field_72449_c * range);
        AxisAlignedBB crumbleBox = AxisAlignedBB.func_72330_a(destVec.field_72450_a - radius, destVec.field_72448_b - radius, destVec.field_72449_c - radius, destVec.field_72450_a + radius, destVec.field_72448_b + radius, destVec.field_72449_c + radius);

        return this.crumbleBlocksInAABB(world, player, crumbleBox);
    }

    private int crumbleBlocksInAABB(World world, EntityPlayer player, AxisAlignedBB par1AxisAlignedBB) {
        int minX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72340_a);
        int minY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72338_b);
        int minZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72339_c);
        int maxX = MathHelper.func_76128_c(par1AxisAlignedBB.field_72336_d);
        int maxY = MathHelper.func_76128_c(par1AxisAlignedBB.field_72337_e);
        int maxZ = MathHelper.func_76128_c(par1AxisAlignedBB.field_72334_f);
        int crumbled = 0;

        for (int dx = minX; dx <= maxX; ++dx) {
            for (int dy = minY; dy <= maxY; ++dy) {
                for (int dz = minZ; dz <= maxZ; ++dz) {
                    crumbled += this.crumbleBlock(world, player, dx, dy, dz);
                }
            }
        }

        return crumbled;
    }

    private int crumbleBlock(World world, EntityPlayer player, int dx, int dy, int dz) {
        int cost = 0;
        Block currentID = world.func_147439_a(dx, dy, dz);

        if (currentID != Blocks.field_150350_a) {
            int currentMeta = world.func_72805_g(dx, dy, dz);

            if (currentID == Blocks.field_150348_b && world.field_73012_v.nextInt(5) == 0) {
                world.func_147465_d(dx, dy, dz, Blocks.field_150347_e, 0, 3);
                world.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                ++cost;
            }

            if (currentID == Blocks.field_150417_aV && currentMeta == 0 && world.field_73012_v.nextInt(5) == 0) {
                world.func_147465_d(dx, dy, dz, Blocks.field_150417_aV, 2, 3);
                world.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                ++cost;
            }

            if (currentID == TFBlocks.mazestone && currentMeta == 1 && world.field_73012_v.nextInt(5) == 0) {
                world.func_147465_d(dx, dy, dz, TFBlocks.mazestone, 4, 3);
                world.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                ++cost;
            }

            if (currentID == Blocks.field_150347_e && world.field_73012_v.nextInt(5) == 0) {
                world.func_147465_d(dx, dy, dz, Blocks.field_150351_n, 0, 3);
                world.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                ++cost;
            }

            if ((currentID == Blocks.field_150351_n || currentID == Blocks.field_150346_d) && currentID.canHarvestBlock(player, currentMeta) && world.field_73012_v.nextInt(20) == 0) {
                world.func_147465_d(dx, dy, dz, Blocks.field_150350_a, 0, 3);
                currentID.func_149636_a(world, player, dx, dy, dz, currentMeta);
                world.func_72926_e(2001, dx, dy, dz, Block.func_149682_b(currentID) + (currentMeta << 12));
                ++cost;
            }
        }

        return cost;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
