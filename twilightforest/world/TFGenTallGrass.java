package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TFGenTallGrass extends WorldGenerator {

    private Block tallGrassID;
    private int tallGrassMetadata;
    private int amount;

    public TFGenTallGrass(Block blockID, int meta, int amount) {
        this.amount = amount;
        this.tallGrassID = blockID;
        this.tallGrassMetadata = meta;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z) {
        Block block = null;

        do {
            block = par1World.func_147439_a(x, y, z);
            if (block != null && !block.isLeaves(par1World, x, y, z)) {
                break;
            }

            --y;
        } while (y > 0);

        for (int i = 0; i < this.amount; ++i) {
            int dx = x + par2Random.nextInt(8) - par2Random.nextInt(8);
            int dy = y + par2Random.nextInt(4) - par2Random.nextInt(4);
            int dz = z + par2Random.nextInt(8) - par2Random.nextInt(8);

            if (par1World.func_147437_c(dx, dy, dz) && this.tallGrassID.func_149718_j(par1World, dx, dy, dz)) {
                par1World.func_147465_d(dx, dy, dz, this.tallGrassID, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }
}
