package twilightforest.item;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLilyPad;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class ItemBlockTFHugeLilyPad extends ItemLilyPad {

    Random rand = new Random();

    public ItemBlockTFHugeLilyPad(Block block) {
        super(block);
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = this.func_77621_a(world, player, true);

        if (movingobjectposition == null) {
            return itemStack;
        } else {
            if (movingobjectposition.field_72313_a == MovingObjectType.BLOCK) {
                int x = movingobjectposition.field_72311_b;
                int y = movingobjectposition.field_72312_c;
                int z = movingobjectposition.field_72309_d;
                int bx = x >> 1 << 1;
                int bz = z >> 1 << 1;

                if (this.canPlacePadOn(itemStack, world, player, bx, y, bz) && this.canPlacePadOn(itemStack, world, player, bx + 1, y, bz) && this.canPlacePadOn(itemStack, world, player, bx, y, bz + 1) && this.canPlacePadOn(itemStack, world, player, bx + 1, y, bz + 1)) {
                    this.rand.setSeed(8890919293L);
                    this.rand.setSeed((long) bx * this.rand.nextLong() ^ (long) bz * this.rand.nextLong() ^ 8890919293L);
                    int orient = this.rand.nextInt(4) << 2;

                    world.func_147465_d(bx, y + 1, bz, TFBlocks.hugeLilyPad, 0 | orient, 2);
                    world.func_147465_d(bx + 1, y + 1, bz, TFBlocks.hugeLilyPad, 1 | orient, 2);
                    world.func_147465_d(bx + 1, y + 1, bz + 1, TFBlocks.hugeLilyPad, 2 | orient, 2);
                    world.func_147465_d(bx, y + 1, bz + 1, TFBlocks.hugeLilyPad, 3 | orient, 2);
                    if (!player.field_71075_bZ.field_75098_d) {
                        --itemStack.field_77994_a;
                    }
                }
            }

            return itemStack;
        }
    }

    public boolean canPlacePadOn(ItemStack itemStack, World world, EntityPlayer player, int x, int y, int z) {
        return !world.func_72962_a(player, x, y, z) ? false : (!player.func_82247_a(x, y, z, 1, itemStack) ? false : world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_72805_g(x, y, z) == 0 && world.func_147437_c(x, y + 1, z));
    }
}
