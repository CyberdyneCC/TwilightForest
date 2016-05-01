package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.TFBlocks;

public class ItemBlockTFPlant extends ItemBlock {

    public ItemBlockTFPlant(Block block) {
        super(block);
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    public IIcon func_77617_a(int par1) {
        return TFBlocks.plant.func_149691_a(2, par1);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return TFBlocks.plant.func_149741_i(par1ItemStack.func_77960_j());
    }

    public String func_77667_c(ItemStack itemstack) {
        int meta = itemstack.func_77960_j();

        return super.func_77658_a() + "." + meta;
    }

    public int func_77647_b(int i) {
        return i;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_150936_a(World par1World, int x, int y, int z, int direction, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack) {
        int meta = par7ItemStack.func_77960_j();

        return (meta == 14 || meta == 13) && direction == 0 && BlockTFPlant.canPlaceRootBelow(par1World, x, y, z) ? true : super.func_150936_a(par1World, x, y, z, direction, par6EntityPlayer, par7ItemStack);
    }

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int direction, float par8, float par9, float par10) {
        int meta = itemStack.func_77960_j();

        return meta != 14 && meta != 13 ? super.func_77648_a(itemStack, player, world, x, y, z, direction, par8, par9, par10) : this.onItemUseRoot(itemStack, player, world, x, y, z, direction);
    }

    public boolean onItemUseRoot(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int direction) {
        Block blockThereId = world.func_147439_a(x, y, z);

        if (blockThereId == Blocks.field_150433_aE) {
            direction = 1;
        } else if (!blockThereId.func_149688_o().func_76222_j()) {
            x += Facing.field_71586_b[direction];
            y += Facing.field_71587_c[direction];
            z += Facing.field_71585_d[direction];
        }

        if (!player.func_82247_a(x, y, z, direction, itemStack)) {
            return false;
        } else if (itemStack.field_77994_a == 0) {
            return false;
        } else {
            if (BlockTFPlant.canPlaceRootBelow(world, x, y + 1, z)) {
                Block plantBlock = TFBlocks.plant;

                if (world.func_147465_d(x, y, z, plantBlock, itemStack.func_77973_b().func_77647_b(itemStack.func_77960_j()), 3)) {
                    if (world.func_147439_a(x, y, z) == plantBlock) {
                        plantBlock.func_149689_a(world, x, y, z, player, itemStack);
                    }

                    world.func_72908_a((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), plantBlock.field_149762_H.func_150495_a(), (plantBlock.field_149762_H.func_150497_c() + 1.0F) / 2.0F, plantBlock.field_149762_H.func_150494_d() * 0.8F);
                    --itemStack.field_77994_a;
                }
            }

            return true;
        }
    }
}
