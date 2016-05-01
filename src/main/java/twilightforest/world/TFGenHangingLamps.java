package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenHangingLamps extends TFGenerator {

    private static final int MAX_HANG = 8;

    public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z) {
        if (par1World.func_147437_c(x, y, z) && TFGenerator.surroundedByAir(par1World, x, y, z) && this.areLeavesAbove(par1World, x, y, z) && this.isClearBelow(par1World, x, y, z)) {
            par1World.func_147449_b(x, y, z, TFBlocks.fireflyJar);

            for (int cy = 1; cy < 8; ++cy) {
                Material above = par1World.func_147439_a(x, y + cy, z).func_149688_o();

                if (above.func_76220_a() || above == Material.field_151584_j) {
                    break;
                }

                par1World.func_147449_b(x, y + cy, z, Blocks.field_150422_aJ);
            }
        }

        return false;
    }

    private boolean areLeavesAbove(World par1World, int x, int y, int z) {
        boolean areLeavesAbove = false;

        for (int cy = 1; cy < 8; ++cy) {
            Material above = par1World.func_147439_a(x, y + cy, z).func_149688_o();

            if (above.func_76220_a() || above == Material.field_151584_j) {
                areLeavesAbove = true;
            }
        }

        return areLeavesAbove;
    }

    private boolean isClearBelow(World par1World, int x, int y, int z) {
        boolean isClearBelow = true;

        for (int cy = 1; cy < 4; ++cy) {
            if (World.func_147466_a(par1World, x, y - cy, z)) {
                isClearBelow = false;
            }
        }

        return isClearBelow;
    }
}
