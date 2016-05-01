package twilightforest.world;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenDarkCanopyTree extends TFTreeGenerator {

    public TFGenDarkCanopyTree() {
        this(false);
    }

    public TFGenDarkCanopyTree(boolean par1) {
        super(par1);
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 3;
        this.branchMeta = 15;
        this.leafBlock = TFBlocks.darkleaves;
        this.leafMeta = 0;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        boolean foundDirt = false;

        int treeHeight;

        for (treeHeight = y; treeHeight >= TFWorld.SEALEVEL; --treeHeight) {
            Material materialUnder = world.func_147439_a(x, treeHeight - 1, z).func_149688_o();

            if (materialUnder == Material.field_151577_b || materialUnder == Material.field_151578_c) {
                foundDirt = true;
                y = treeHeight;
                break;
            }

            if (materialUnder == Material.field_151576_e || materialUnder == Material.field_151595_p) {
                break;
            }
        }

        if (!foundDirt) {
            return false;
        } else if (world.func_147439_a(x + 1, y, z + 0).func_149688_o() != Material.field_151575_d && world.func_147439_a(x - 1, y, z + 0).func_149688_o() != Material.field_151575_d && world.func_147439_a(x + 0, y, z + 1).func_149688_o() != Material.field_151575_d && world.func_147439_a(x + 0, y, z - 1).func_149688_o() != Material.field_151575_d) {
            treeHeight = 6 + random.nextInt(5);
            this.drawBresehnam(world, x, y, z, x, y + treeHeight, z, this.treeBlock, this.treeMeta);
            this.leafAround(world, x, y + treeHeight, z);
            byte numBranches = 4;
            double offset = (double) random.nextFloat();

            int numRoots;

            for (numRoots = 0; numRoots < numBranches; ++numRoots) {
                this.buildBranch(world, x, y, z, treeHeight - 3 - numBranches + numRoots / 2, (double) (10 + random.nextInt(4)), 0.23D * (double) numRoots + offset, 0.23D, random);
            }

            if (hasAirAround(world, x, y - 1, z)) {
                this.setBlockAndMetadata(world, x, y - 1, z, this.treeBlock, this.treeMeta);
            } else {
                this.setBlockAndMetadata(world, x, y - 1, z, this.rootBlock, this.rootMeta);
            }

            numRoots = 3 + random.nextInt(2);
            offset = random.nextDouble();

            for (int b = 0; b < numRoots; ++b) {
                this.buildRoot(world, x, y, z, offset, b);
            }

            return true;
        } else {
            return false;
        }
    }

    void buildBranch(World world, int x, int y, int z, int height, double length, double angle, double tilt, Random random) {
        ChunkCoordinates src = new ChunkCoordinates(x, y + height, z);
        ChunkCoordinates dest = translateCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, length, angle, tilt);

        if (dest.field_71574_a - x < -4) {
            dest.field_71574_a = x - 4;
        }

        if (dest.field_71574_a - x > 4) {
            dest.field_71574_a = x + 4;
        }

        if (dest.field_71573_c - z < -4) {
            dest.field_71573_c = z - 4;
        }

        if (dest.field_71573_c - z > 4) {
            dest.field_71573_c = z + 4;
        }

        this.drawBresehnam(world, src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, this.treeBlock, this.branchMeta);
        if (Math.abs(x - dest.field_71574_a) + 2 > 7 || Math.abs(z - dest.field_71573_c) + 2 > 7) {
            System.out.println("getting branch too far.  x = " + (x - dest.field_71574_a + 2) + ", z = " + (z - dest.field_71573_c + 2));
        }

        this.leafAround(world, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c);
    }

    private void leafAround(World world, int dx, int dy, int dz) {
        byte leafSize = 4;

        if (hasAirAround(world, dx, dy, dz)) {
            this.makeLeafCircle(world, dx, dy - 1, dz, leafSize, this.leafBlock, this.leafMeta);
            this.makeLeafCircle(world, dx, dy, dz, leafSize + 1, this.leafBlock, this.leafMeta);
            this.makeLeafCircle(world, dx, dy + 1, dz, leafSize, this.leafBlock, this.leafMeta);
            this.makeLeafCircle(world, dx, dy + 2, dz, leafSize - 2, this.leafBlock, this.leafMeta);
        }

    }
}
