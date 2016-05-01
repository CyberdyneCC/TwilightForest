package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.block.TFBlocks;

public class ItemTFLampOfCinders extends ItemTF {

    private static final int FIRING_TIME = 12;

    public ItemTFLampOfCinders() {
        this.func_77637_a(TFItems.creativeTab);
        this.field_77777_bU = 1;
        this.func_77656_e(1024);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
        if (par1ItemStack.func_77960_j() < this.func_77612_l()) {
            player.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        } else {
            player.func_71034_by();
        }

        return par1ItemStack;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!this.burnBlock(player, world, x, y, z)) {
            return false;
        } else {
            world.func_72956_a(player, this.getSound(), 0.5F, 1.5F);

            for (int i = 0; i < 10; ++i) {
                float dx = (float) x + 0.5F + (ItemTFLampOfCinders.field_77697_d.nextFloat() - ItemTFLampOfCinders.field_77697_d.nextFloat()) * 0.75F;
                float dy = (float) y + 0.5F + (ItemTFLampOfCinders.field_77697_d.nextFloat() - ItemTFLampOfCinders.field_77697_d.nextFloat()) * 0.75F;
                float dz = (float) z + 0.5F + (ItemTFLampOfCinders.field_77697_d.nextFloat() - ItemTFLampOfCinders.field_77697_d.nextFloat()) * 0.75F;

                world.func_72869_a("smoke", (double) dx, (double) dy, (double) dz, 0.0D, 0.0D, 0.0D);
                world.func_72869_a("flame", (double) dx, (double) dy, (double) dz, 0.0D, 0.0D, 0.0D);
            }

            return true;
        }
    }

    private boolean burnBlock(EntityPlayer player, World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);

        if (block == TFBlocks.thorns) {
            world.func_147465_d(x, y, z, TFBlocks.burntThorns, world.func_72805_g(x, y, z) & 12, 2);
            return true;
        } else {
            return false;
        }
    }

    public void func_77615_a(ItemStack par1ItemStack, World world, EntityPlayer player, int useRemaining) {
        int useTime = this.func_77626_a(par1ItemStack) - useRemaining;

        if (useTime > 12 && par1ItemStack.func_77960_j() + 1 < this.func_77612_l()) {
            this.doBurnEffect(world, player);
            player.func_71029_a(TFAchievementPage.twilightProgressTroll);
        }

    }

    private void doBurnEffect(World world, EntityPlayer player) {
        int px = MathHelper.func_76128_c(player.field_70142_S);
        int py = MathHelper.func_76128_c(player.field_70137_T + (double) player.func_70047_e());
        int pz = MathHelper.func_76128_c(player.field_70136_U);
        byte range = 4;
        int i;
        int rx;
        int ry;

        if (!world.field_72995_K) {
            world.func_72956_a(player, this.getSound(), 1.5F, 0.8F);

            for (i = -range; i <= range; ++i) {
                for (rx = -range; rx <= range; ++rx) {
                    for (ry = -range; ry <= range; ++ry) {
                        this.burnBlock(player, world, px + i, py + rx, pz + ry);
                    }
                }
            }
        }

        for (i = 0; i < 6; ++i) {
            rx = px + ItemTFLampOfCinders.field_77697_d.nextInt(range) - ItemTFLampOfCinders.field_77697_d.nextInt(range);
            ry = py + ItemTFLampOfCinders.field_77697_d.nextInt(2);
            int rz = pz + ItemTFLampOfCinders.field_77697_d.nextInt(range) - ItemTFLampOfCinders.field_77697_d.nextInt(range);

            world.func_72889_a(player, 2004, rx, ry, rz, 0);
        }

    }

    public String getSound() {
        return "mob.ghast.fireball";
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }
}
