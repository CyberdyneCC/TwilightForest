package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class ItemTFTrophy extends ItemTF {

    private static final String[] trophyTypes = new String[] { "hydra", "naga", "lich", "ur-ghast", "snowQueen"};
    public static final String[] trophyTextures = new String[] { "hydraTrophy", "nagaTrophy", "lichTrophy", "urGhastTrophy", "snowQueenTrophy"};
    public IIcon[] trophyIcons;

    public ItemTFTrophy() {
        this.func_77637_a(TFItems.creativeTab);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int j = 0; j < ItemTFTrophy.trophyTypes.length; ++j) {
            par3List.add(new ItemStack(par1, 1, j));
        }

    }

    @SideOnly(Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int direction, float par8, float par9, float par10) {
        if (direction == 0) {
            return false;
        } else if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
            return false;
        } else {
            if (direction == 1) {
                ++y;
            }

            if (direction == 2) {
                --z;
            }

            if (direction == 3) {
                ++z;
            }

            if (direction == 4) {
                --x;
            }

            if (direction == 5) {
                ++x;
            }

            if (!player.func_82247_a(x, y, z, direction, itemStack)) {
                return false;
            } else if (!TFBlocks.trophy.func_149742_c(world, x, y, z)) {
                return false;
            } else {
                world.func_147465_d(x, y, z, TFBlocks.trophy, direction, 3);
                int skullRotate = 0;

                if (direction == 1) {
                    skullRotate = MathHelper.func_76128_c((double) (player.field_70177_z * 16.0F / 360.0F) + 0.5D) & 15;
                }

                TileEntity tileEntity = world.func_147438_o(x, y, z);

                if (tileEntity != null && tileEntity instanceof TileEntitySkull) {
                    TileEntitySkull skull = (TileEntitySkull) tileEntity;
                    NBTTagCompound tags = new NBTTagCompound();

                    skull.func_145841_b(tags);
                    tags.func_74774_a("SkullType", (byte) (itemStack.func_77960_j() & 255));
                    skull.func_145839_a(tags);
                    skull.func_145903_a(skullRotate);
                }

                --itemStack.field_77994_a;
                return true;
            }
        }
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        int i = par1ItemStack.func_77960_j();

        if (i < 0 || i >= ItemTFTrophy.trophyTypes.length) {
            i = 0;
        }

        return super.func_77658_a() + "." + ItemTFTrophy.trophyTypes[i];
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.trophyIcons = new IIcon[ItemTFTrophy.trophyTextures.length];

        for (int i = 0; i < ItemTFTrophy.trophyTextures.length; ++i) {
            this.trophyIcons[i] = par1IconRegister.func_94245_a("TwilightForest:" + ItemTFTrophy.trophyTextures[i]);
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        if (par1 < 0 || par1 >= ItemTFTrophy.trophyTypes.length) {
            par1 = 0;
        }

        return this.trophyIcons[par1];
    }
}
