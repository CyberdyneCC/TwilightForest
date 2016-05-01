package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import twilightforest.block.TFBlocks;

public class ItemTFOreMeter extends ItemTF {

    protected ItemTFOreMeter() {
        this.func_77637_a(TFItems.creativeTab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
        int useX = MathHelper.func_76128_c(player.field_70165_t);
        int useZ = MathHelper.func_76128_c(player.field_70161_v);

        if (!world.field_72995_K) {
            this.countOreInArea(player, world, useX, useZ, 3);
        }

        return super.func_77659_a(par1ItemStack, world, player);
    }

    private void countOreInChunk(EntityPlayer player, World world, int useX, int useZ) {
        int chunkX = useX >> 4;
        int chunkZ = useZ >> 4;
        int countStone = this.countBlockInChunk(world, Blocks.field_150348_b, chunkX, chunkZ);
        int countDirt = this.countBlockInChunk(world, Blocks.field_150346_d, chunkX, chunkZ);
        int countGravel = this.countBlockInChunk(world, Blocks.field_150351_n, chunkX, chunkZ);
        int countCoal = this.countBlockInChunk(world, Blocks.field_150365_q, chunkX, chunkZ);
        int countIron = this.countBlockInChunk(world, Blocks.field_150366_p, chunkX, chunkZ);
        int countGold = this.countBlockInChunk(world, Blocks.field_150352_o, chunkX, chunkZ);
        int countDiamond = this.countBlockInChunk(world, Blocks.field_150482_ag, chunkX, chunkZ);
        int countLapis = this.countBlockInChunk(world, Blocks.field_150369_x, chunkX, chunkZ);
        int countRedstone = this.countBlockInChunk(world, Blocks.field_150450_ax, chunkX, chunkZ);
        int countRoots = this.countBlockInChunk(world, TFBlocks.root, 0, chunkX, chunkZ);
        int countOreRoots = this.countBlockInChunk(world, TFBlocks.root, 1, chunkX, chunkZ);
        int total = countStone + countDirt + countGravel + countCoal + countIron + countGold + countDiamond + countLapis + countRedstone + countRoots + countOreRoots;
    }

    private void countOreInArea(EntityPlayer player, World world, int useX, int useZ, int radius) {
        int chunkX = useX >> 4;
        int chunkZ = useZ >> 4;
        int countStone = 0;
        int countDirt = 0;
        int countGravel = 0;
        int countCoal = 0;
        int countIron = 0;
        int countGold = 0;
        int countDiamond = 0;
        int countLapis = 0;
        int countRedstone = 0;
        int countExposedDiamond = 0;
        int countRoots = 0;
        int countOreRoots = 0;
        boolean total = false;

        for (int cx = chunkX - radius; cx <= chunkX + radius; ++cx) {
            for (int cz = chunkZ - radius; cz <= chunkZ + radius; ++cz) {
                countStone += this.countBlockInChunk(world, Blocks.field_150348_b, cx, cz);
                countDirt += this.countBlockInChunk(world, Blocks.field_150346_d, cx, cz);
                countGravel += this.countBlockInChunk(world, Blocks.field_150351_n, cx, cz);
                countCoal += this.countBlockInChunk(world, Blocks.field_150365_q, cx, cz);
                countIron += this.countBlockInChunk(world, Blocks.field_150366_p, cx, cz);
                countGold += this.countBlockInChunk(world, Blocks.field_150352_o, cx, cz);
                countDiamond += this.countBlockInChunk(world, Blocks.field_150482_ag, cx, cz);
                countLapis += this.countBlockInChunk(world, Blocks.field_150369_x, cx, cz);
                countRedstone += this.countBlockInChunk(world, Blocks.field_150450_ax, cx, cz);
                countExposedDiamond += this.countExposedBlockInChunk(world, Blocks.field_150482_ag, cx, cz);
                countRoots += this.countBlockInChunk(world, TFBlocks.root, 0, cx, cz);
                countOreRoots += this.countBlockInChunk(world, TFBlocks.root, 1, cx, cz);
            }
        }

        int i = countStone + countDirt + countGravel + countCoal + countIron + countGold + countDiamond + countLapis + countRedstone + countRoots + countOreRoots;
    }

    public float percent(int count, int total) {
        return (float) count / (float) total * 100.0F;
    }

    public int countBlockInChunk(World world, Block stone, int cx, int cz) {
        Chunk chunk = world.func_72964_e(cx, cz);
        int count = 0;

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 256; ++y) {
                    if (chunk.func_150810_a(x, y, z) == stone) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }

    public int countBlockInChunk(World world, Block blockID, int meta, int cx, int cz) {
        Chunk chunk = world.func_72964_e(cx, cz);
        int count = 0;

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                for (int y = 0; y < 256; ++y) {
                    if (chunk.func_150810_a(x, y, z) == blockID && chunk.func_76628_c(x, y, z) == meta) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }

    private int countExposedBlockInChunk(World world, Block blockID, int cx, int cz) {
        int count = 0;

        for (int x = cx << 4; x < (cx << 4) + 16; ++x) {
            for (int z = cz << 4; z < (cz << 4) + 16; ++z) {
                for (int y = 0; y < 256; ++y) {
                    if (world.func_147439_a(x, y, z) == blockID && (world.func_147437_c(x + 1, y, z) || world.func_147437_c(x - 1, y, z) || world.func_147437_c(x, y + 1, z) || world.func_147437_c(x, y - 1, z) || world.func_147437_c(x, y + 1, z) || world.func_147437_c(x, y - 1, z))) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
