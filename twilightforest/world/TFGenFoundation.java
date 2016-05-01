package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.TFTreasure;

public class TFGenFoundation extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int sx = 5 + rand.nextInt(5);
        int sz = 5 + rand.nextInt(5);

        if (!this.isAreaClear(world, rand, x, y, z, sx, 4, sz)) {
            return false;
        } else {
            int cx;
            int cz;

            for (cx = 0; cx <= sx; ++cx) {
                for (cz = 0; cz <= sz; ++cz) {
                    if (cx != 0 && cx != sx && cz != 0 && cz != sz) {
                        if (rand.nextInt(3) != 0) {
                            this.setBlock(world, x + cx, y - 1, z + cz, Blocks.field_150344_f);
                        }
                    } else {
                        int ht = rand.nextInt(4) + 1;

                        for (int cy = 0; cy <= ht; ++cy) {
                            this.setBlock(world, x + cx, y + cy - 1, z + cz, this.randStone(rand, cy + 1));
                        }
                    }
                }
            }

            if (rand.nextInt(2) == 0) {
                for (cx = 1; cx < sx; ++cx) {
                    for (cz = 1; cz < sz; ++cz) {
                        this.setBlock(world, x + cx, y - 3, z + cz, Blocks.field_150350_a);
                        this.setBlock(world, x + cx, y - 4, z + cz, Blocks.field_150350_a);
                    }
                }

                cx = rand.nextInt(sx - 1) + 1;
                cz = rand.nextInt(sz - 1) + 1;
                TFTreasure.basement.generate(world, rand, x + cx, y - 4, z + cz);
            }

            return true;
        }
    }
}
