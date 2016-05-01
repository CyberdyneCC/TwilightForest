package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMoonwormShot;

public class ItemTFMoonwormQueen extends ItemTF {

    private static final int FIRING_TIME = 12;
    private IIcon[] icons;
    private String[] iconNames = new String[] { "moonwormQueen", "moonwormQueenAlt"};

    protected ItemTFMoonwormQueen() {
        this.func_77637_a(TFItems.creativeTab);
        this.field_77777_bU = 1;
        this.func_77656_e(256);
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
        Block currentBlockID = world.func_147439_a(x, y, z);

        if (currentBlockID == TFBlocks.moonworm) {
            return false;
        } else if (par1ItemStack != null && par1ItemStack.func_77960_j() == this.func_77612_l()) {
            return false;
        } else {
            if (currentBlockID == Blocks.field_150433_aE) {
                side = 1;
            } else if (currentBlockID != Blocks.field_150395_bd && currentBlockID != Blocks.field_150329_H && currentBlockID != Blocks.field_150330_I && (currentBlockID == Blocks.field_150350_a || !currentBlockID.isReplaceable(world, x, y, z))) {
                if (side == 0) {
                    --y;
                }

                if (side == 1) {
                    ++y;
                }

                if (side == 2) {
                    --z;
                }

                if (side == 3) {
                    ++z;
                }

                if (side == 4) {
                    --x;
                }

                if (side == 5) {
                    ++x;
                }
            }

            if (world.func_147472_a(TFBlocks.moonworm, x, y, z, false, side, player, par1ItemStack)) {
                int placementMeta = TFBlocks.moonworm.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, 0);

                if (world.func_147465_d(x, y, z, TFBlocks.moonworm, placementMeta, 3)) {
                    if (world.func_147439_a(x, y, z) == TFBlocks.moonworm) {
                        TFBlocks.moonworm.func_149689_a(world, x, y, z, player, par1ItemStack);
                    }

                    world.func_72908_a((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), this.getSound(), TFBlocks.moonworm.field_149762_H.func_150497_c() / 2.0F, TFBlocks.moonworm.field_149762_H.func_150494_d() * 0.8F);
                    if (par1ItemStack != null) {
                        par1ItemStack.func_77972_a(1, player);
                        player.func_71034_by();
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public String getSound() {
        return "mob.slime.big";
    }

    public void func_77615_a(ItemStack par1ItemStack, World world, EntityPlayer player, int useRemaining) {
        int useTime = this.func_77626_a(par1ItemStack) - useRemaining;

        if (!world.field_72995_K && useTime > 12 && par1ItemStack.func_77960_j() + 1 < this.func_77612_l()) {
            boolean fired = world.func_72838_d(new EntityTFMoonwormShot(world, player));

            if (fired) {
                par1ItemStack.func_77972_a(2, player);
                world.func_72956_a(player, this.getSound(), 1.0F, 1.0F);
            }
        }

    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem != null && usingItem.func_77973_b() == this) {
            int useTime = usingItem.func_77988_m() - useRemaining;

            if (useTime >= 12) {
                return (useTime >> 1) % 2 == 0 ? this.icons[0] : this.icons[1];
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
}
