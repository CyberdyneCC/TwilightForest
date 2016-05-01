package twilightforest.world;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import twilightforest.block.TFBlocks;

public class TFGenThorns extends TFGenerator {

    private static final int MAX_SPREAD = 7;
    private static final int CHANCE_OF_BRANCH = 3;
    private static final int CHANCE_OF_LEAF = 3;
    private static final int CHANCE_LEAF_IS_ROSE = 50;

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int nextLength = 2 + rand.nextInt(4);
        int maxLength = 2 + rand.nextInt(4) + rand.nextInt(4) + rand.nextInt(4);

        this.placeThorns(world, rand, x, y, z, nextLength, ForgeDirection.UP, maxLength, x, y, z);
        return true;
    }

    private void placeThorns(World world, Random rand, int x, int y, int z, int length, ForgeDirection dir, int maxLength, int ox, int oy, int oz) {
        boolean complete = false;

        int middle;
        int nextDir;
        int nextX;
        int nextY;

        for (middle = 0; middle < length; ++middle) {
            nextDir = x + dir.offsetX * middle;
            nextX = y + dir.offsetY * middle;
            nextY = z + dir.offsetZ * middle;
            if (Math.abs(nextDir - ox) >= 7 || Math.abs(nextY - oz) >= 7 || !this.canPlaceThorns(world, nextDir, nextX, nextY)) {
                break;
            }

            this.setBlockAndMetadata(world, nextDir, nextX, nextY, TFBlocks.thorns, this.getMetaFor(dir));
            if (middle == length - 1) {
                complete = true;
                if (rand.nextInt(3) == 0 && world.func_147437_c(nextDir + dir.offsetX, nextX + dir.offsetY, nextY + dir.offsetZ)) {
                    if (rand.nextInt(50) > 0) {
                        this.setBlockAndMetadata(world, nextDir + dir.offsetX, nextX + dir.offsetY, nextY + dir.offsetZ, TFBlocks.leaves3, 0);
                    } else {
                        this.setBlock(world, nextDir + dir.offsetX, nextX + dir.offsetY, nextY + dir.offsetZ, TFBlocks.thornRose);
                    }
                }
            }
        }

        int nextZ;

        if (complete && maxLength > 1) {
            ForgeDirection forgedirection = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];

            nextDir = x + dir.offsetX * (length - 1) + forgedirection.offsetX;
            nextX = y + dir.offsetY * (length - 1) + forgedirection.offsetY;
            nextY = z + dir.offsetZ * (length - 1) + forgedirection.offsetZ;
            nextZ = 1 + rand.nextInt(maxLength);
            this.placeThorns(world, rand, nextDir, nextX, nextY, nextZ, forgedirection, maxLength - 1, ox, oy, oz);
        }

        ForgeDirection forgedirection1;

        if (complete && length > 3 && rand.nextInt(3) == 0) {
            middle = rand.nextInt(length);
            forgedirection1 = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
            nextX = x + dir.offsetX * middle + forgedirection1.offsetX;
            nextY = y + dir.offsetY * middle + forgedirection1.offsetY;
            nextZ = z + dir.offsetZ * middle + forgedirection1.offsetZ;
            int nextLength = 1 + rand.nextInt(maxLength);

            this.placeThorns(world, rand, nextX, nextY, nextZ, nextLength, forgedirection1, maxLength - 1, ox, oy, oz);
        }

        if (complete && length > 3 && rand.nextInt(3) == 0) {
            middle = rand.nextInt(length);
            forgedirection1 = ForgeDirection.VALID_DIRECTIONS[rand.nextInt(ForgeDirection.VALID_DIRECTIONS.length)];
            nextX = x + dir.offsetX * middle + forgedirection1.offsetX;
            nextY = y + dir.offsetY * middle + forgedirection1.offsetY;
            nextZ = z + dir.offsetZ * middle + forgedirection1.offsetZ;
            if (world.func_147437_c(nextX, nextY, nextZ)) {
                this.setBlockAndMetadata(world, nextX, nextY, nextZ, TFBlocks.leaves3, 0);
            }
        }

    }

    private boolean canPlaceThorns(World world, int dx, int dy, int dz) {
        return world.func_147437_c(dx, dy, dz) || world.func_147439_a(dx, dy, dz).isLeaves(world, dx, dy, dz);
    }

    private int getMetaFor(ForgeDirection dir) {
        switch (TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[dir.ordinal()]) {
        case 1:
        case 2:
        case 3:
        default:
            return 0;

        case 4:
        case 5:
            return 4;

        case 6:
        case 7:
            return 8;
        }
    }

    static class SyntheticClass_1 {

        static final int[] $SwitchMap$net$minecraftforge$common$util$ForgeDirection = new int[ForgeDirection.values().length];

        static {
            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.UP.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.EAST.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.NORTH.ordinal()] = 6;
            } catch (NoSuchFieldError nosuchfielderror5) {
                ;
            }

            try {
                TFGenThorns.SyntheticClass_1.$SwitchMap$net$minecraftforge$common$util$ForgeDirection[ForgeDirection.SOUTH.ordinal()] = 7;
            } catch (NoSuchFieldError nosuchfielderror6) {
                ;
            }

        }
    }
}
