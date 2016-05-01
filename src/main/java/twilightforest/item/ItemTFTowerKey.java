package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.block.BlockTFTowerDevice;
import twilightforest.block.TFBlocks;

public class ItemTFTowerKey extends ItemTF {

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz) {
        if (!world.field_72995_K && world.func_147439_a(x, y, z) == TFBlocks.towerDevice && world.func_72805_g(x, y, z) == 4) {
            BlockTFTowerDevice.unlockBlock(world, x, y, z);
            --itemStack.field_77994_a;
            return true;
        } else {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
