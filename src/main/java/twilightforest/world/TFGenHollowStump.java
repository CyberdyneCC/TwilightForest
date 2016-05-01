package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;

public class TFGenHollowStump extends TFGenHollowTree {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int radius = rand.nextInt(2) + 2;

        if (!this.isAreaClear(world, rand, x - radius, y, z - radius, 2 * radius, 6, 2 * radius)) {
            return false;
        } else {
            this.buildTrunk(world, rand, x, y, z, radius, 6);
            this.buildBranchRing(world, rand, x, y, z, radius, 3, 2, 6, 0, 0.75D, 0.0D, 3, 5, 3, false);
            this.buildBranchRing(world, rand, x, y, z, radius, 1, 2, 8, 0, 0.9D, 0.0D, 3, 5, 3, false);
            return true;
        }
    }

    protected void buildTrunk(World world, Random random, int x, int y, int z, int diameter, int maxHeight) {
        int hollow = diameter / 2;

        int dx;
        int dz;
        int height;
        int dy;
        int ax;
        int az;

        for (dx = -diameter; dx <= diameter; ++dx) {
            for (dz = -diameter; dz <= diameter; ++dz) {
                for (height = -4; height < 0; ++height) {
                    dy = Math.abs(dx);
                    ax = Math.abs(dz);
                    az = (int) ((double) Math.max(dy, ax) + (double) Math.min(dy, ax) * 0.5D);
                    if (az <= diameter) {
                        if (hasAirAround(world, dx + x, height + y, dz + z)) {
                            this.setBlockAndMetadata(world, dx + x, height + y, dz + z, this.treeBlock, az > hollow ? this.treeMeta : this.branchMeta);
                        } else {
                            this.setBlockAndMetadata(world, dx + x, height + y, dz + z, this.rootBlock, this.rootMeta);
                        }
                    }
                }
            }
        }

        for (dx = -diameter; dx <= diameter; ++dx) {
            for (dz = -diameter; dz <= diameter; ++dz) {
                height = 2 + random.nextInt(3) + random.nextInt(2);

                for (dy = 0; dy <= height; ++dy) {
                    ax = Math.abs(dx);
                    az = Math.abs(dz);
                    int dist = (int) ((double) Math.max(ax, az) + (double) Math.min(ax, az) * 0.5D);

                    if (dist <= diameter && dist > hollow) {
                        this.setBlockAndMetadata(world, dx + x, dy + y, dz + z, this.treeBlock, this.treeMeta);
                    }
                }
            }
        }

    }
}
