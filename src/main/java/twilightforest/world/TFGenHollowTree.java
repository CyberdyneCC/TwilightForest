package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;

public class TFGenHollowTree extends TFGenerator {

    private static final int LEAF_DUNGEON_CHANCE = 8;
    protected Block treeBlock;
    protected int treeMeta;
    protected int branchMeta;
    protected Block leafBlock;
    protected int leafMeta;
    protected Block rootBlock;
    protected int rootMeta;

    public TFGenHollowTree() {
        this(false);
    }

    public TFGenHollowTree(boolean par1) {
        super(par1);
        this.treeBlock = TFBlocks.log;
        this.treeMeta = 0;
        this.branchMeta = 12;
        this.leafBlock = TFBlocks.leaves;
        this.leafMeta = 0;
        this.rootBlock = TFBlocks.root;
        this.rootMeta = 0;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        int height = random.nextInt(64) + 32;
        int diameter = random.nextInt(4) + 1;

        if (y >= 1 && y + height + diameter <= TFWorld.MAXHEIGHT) {
            int crownRadius = diameter * 4 + 8;

            int numFireflies;
            int numBranches;

            for (int j1 = -crownRadius; j1 <= crownRadius; ++j1) {
                for (numFireflies = -crownRadius; numFireflies <= crownRadius; ++numFireflies) {
                    for (numBranches = height - crownRadius; numBranches <= height + crownRadius; ++numBranches) {
                        Block i = world.func_147439_a(j1 + x, numBranches + y, numFireflies + z);

                        if (i != Blocks.field_150350_a && i != Blocks.field_150362_t) {
                            return false;
                        }
                    }
                }
            }

            Block block = world.func_147439_a(x, y - 1, z);

            if (block != Blocks.field_150349_c && block != Blocks.field_150346_d) {
                return false;
            } else {
                this.buildTrunk(world, random, x, y, z, diameter, height);
                numFireflies = random.nextInt(3 * diameter) + 5;

                double branchHeight;
                int i;

                for (numBranches = 0; numBranches <= numFireflies; ++numBranches) {
                    i = (int) ((double) height * random.nextDouble() * 0.9D) + height / 10;
                    branchHeight = random.nextDouble();
                    this.addFirefly(world, x, y, z, diameter, i, branchHeight);
                }

                numFireflies = random.nextInt(3 * diameter) + 5;

                for (numBranches = 0; numBranches <= numFireflies; ++numBranches) {
                    i = (int) ((double) height * random.nextDouble() * 0.9D) + height / 10;
                    branchHeight = random.nextDouble();
                    this.addCicada(world, x, y, z, diameter, i, branchHeight);
                }

                this.buildFullCrown(world, random, x, y, z, diameter, height);
                numBranches = random.nextInt(3) + 3;

                for (i = 0; i <= numBranches; ++i) {
                    int j = (int) ((double) height * random.nextDouble() * 0.9D) + height / 10;
                    double branchRotation = random.nextDouble();

                    this.makeSmallBranch(world, random, x, y, z, diameter, j, 4.0D, branchRotation, 0.35D, true);
                }

                this.buildBranchRing(world, random, x, y, z, diameter, 3, 2, 6, 0, 0.75D, 0.0D, 3, 5, 3, false);
                this.buildBranchRing(world, random, x, y, z, diameter, 1, 2, 8, 0, 0.9D, 0.0D, 3, 5, 3, false);
                return true;
            }
        } else {
            return false;
        }
    }

    protected void buildFullCrown(World world, Random random, int x, int y, int z, int diameter, int height) {
        int crownRadius = diameter * 4 + 4;
        int bvar = diameter + 2;

        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius, 0, crownRadius, 0, 0.35D, 0.0D, bvar, bvar + 2, 2, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius / 2, 0, crownRadius, 0, 0.28D, 0.0D, bvar, bvar + 2, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius, 0, 0.15D, 0.0D, 2, 4, 2, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius / 2, 0, 0.05D, 0.0D, bvar, bvar + 2, 1, true);
    }

    protected void buildWeakCrown(World world, Random random, int x, int y, int z, int diameter, int height) {
        byte crownRadius = 8;
        byte bvar = 2;

        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius, 0, crownRadius, 0, 0.35D, 0.0D, bvar, bvar + 2, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height - crownRadius / 2, 0, crownRadius, 0, 0.28D, 0.0D, bvar, bvar + 2, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius, 0, 0.15D, 0.0D, 2, 4, 1, true);
        this.buildBranchRing(world, random, x, y, z, diameter, height, 0, crownRadius / 2, 0, 0.05D, 0.0D, bvar, bvar + 2, 1, true);
    }

    protected void buildBranchRing(World world, Random random, int x, int y, int z, int diameter, int branchHeight, int heightVar, int length, int lengthVar, double tilt, double tiltVar, int minBranches, int maxBranches, int size, boolean leafy) {
        int numBranches = random.nextInt(maxBranches - minBranches) + minBranches;
        double branchRotation = 1.0D / (double) (numBranches + 1);
        double branchOffset = random.nextDouble();

        for (int i = 0; i <= numBranches; ++i) {
            int dHeight;

            if (heightVar > 0) {
                dHeight = branchHeight - heightVar + random.nextInt(2 * heightVar);
            } else {
                dHeight = branchHeight;
            }

            if (size == 2) {
                this.makeLargeBranch(world, random, x, y, z, diameter, dHeight, (double) length, (double) i * branchRotation + branchOffset, tilt, leafy);
            } else if (size == 1) {
                this.makeMedBranch(world, random, x, y, z, diameter, dHeight, (double) length, (double) i * branchRotation + branchOffset, tilt, leafy);
            } else if (size == 3) {
                this.makeRoot(world, random, x, y, z, diameter, dHeight, (double) length, (double) i * branchRotation + branchOffset, tilt);
            } else {
                this.makeSmallBranch(world, random, x, y, z, diameter, dHeight, (double) length, (double) i * branchRotation + branchOffset, tilt, leafy);
            }
        }

    }

    protected void buildTrunk(World world, Random random, int x, int y, int z, int diameter, int height) {
        int hollow = diameter / 2;

        int dx;
        int dz;
        int dy;
        int ax;
        int az;
        int dist;

        for (dx = -diameter; dx <= diameter; ++dx) {
            for (dz = -diameter; dz <= diameter; ++dz) {
                for (dy = -4; dy < 0; ++dy) {
                    ax = Math.abs(dx);
                    az = Math.abs(dz);
                    dist = (int) ((double) Math.max(ax, az) + (double) Math.min(ax, az) * 0.5D);
                    if (dist <= diameter) {
                        if (hasAirAround(world, dx + x, dy + y, dz + z)) {
                            this.setBlockAndMetadata(world, dx + x, dy + y, dz + z, this.treeBlock, dist > hollow ? this.treeMeta : this.branchMeta);
                        } else {
                            this.setBlockAndMetadata(world, dx + x, dy + y, dz + z, this.rootBlock, this.rootMeta);
                        }
                    }
                }
            }
        }

        for (dx = -diameter; dx <= diameter; ++dx) {
            for (dz = -diameter; dz <= diameter; ++dz) {
                for (dy = 0; dy <= height; ++dy) {
                    ax = Math.abs(dx);
                    az = Math.abs(dz);
                    dist = (int) ((double) Math.max(ax, az) + (double) Math.min(ax, az) * 0.5D);
                    if (dist <= diameter && dist > hollow) {
                        this.setBlockAndMetadata(world, dx + x, dy + y, dz + z, this.treeBlock, this.treeMeta);
                    }

                    if (dist <= hollow) {
                        ;
                    }

                    if (dist == hollow && dx == hollow) {
                        this.setBlockAndMetadata(world, dx + x, dy + y, dz + z, Blocks.field_150395_bd, 8);
                    }
                }
            }
        }

    }

    protected void makeMedBranch(World world, Random random, int x, int y, int z, int diameter, int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sy = y + branchHeight;
        int[] src = translate(x, sy, z, (double) diameter, angle, 0.5D);

        this.makeMedBranch(world, random, src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    protected void makeMedBranch(World world, Random random, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[] { sx, sy, sz};
        int[] dest = translate(src[0], src[1], src[2], length, angle, tilt);

        this.drawBresehnam(world, src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, this.branchMeta);
        if (leafy) {
            this.drawLeafBlob(world, dest[0], dest[1], dest[2], 2, this.leafBlock, this.leafMeta);
        }

        int numShoots = random.nextInt(2) + 1;
        double angleInc = 0.8D / (double) numShoots;

        for (int i = 0; i <= numShoots; ++i) {
            double angleVar = angleInc * (double) i - 0.4D;
            double outVar = random.nextDouble() * 0.8D + 0.2D;
            double tiltVar = random.nextDouble() * 0.75D + 0.15D;
            int[] bsrc = translate(src[0], src[1], src[2], length * outVar, angle, tilt);
            double slength = length * 0.4D;

            this.makeSmallBranch(world, random, bsrc[0], bsrc[1], bsrc[2], slength, angle + angleVar, tilt * tiltVar, leafy);
        }

    }

    protected void makeSmallBranch(World world, Random random, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[] { sx, sy, sz};
        int[] dest = translate(src[0], src[1], src[2], length, angle, tilt);

        this.drawBresehnam(world, src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, this.branchMeta);
        if (leafy) {
            byte leafRad = (byte) (random.nextInt(2) + 1);

            this.drawLeafBlob(world, dest[0], dest[1], dest[2], leafRad, this.leafBlock, this.leafMeta);
        }

    }

    protected void makeSmallBranch(World world, Random random, int x, int y, int z, int diameter, int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sy = y + branchHeight;
        int[] src = translate(x, sy, z, (double) diameter, angle, 0.5D);

        this.makeSmallBranch(world, random, src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    protected void makeRoot(World world, Random random, int x, int y, int z, int diameter, int branchHeight, double length, double angle, double tilt) {
        ChunkCoordinates src = translateCoords(x, y + branchHeight, z, (double) diameter, angle, 0.5D);
        ChunkCoordinates dest = translateCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, length, angle, tilt);
        ChunkCoordinates[] lineArray = getBresehnamArrayCoords(src, dest);
        boolean stillAboveGround = true;
        ChunkCoordinates[] achunkcoordinates = lineArray;
        int i = lineArray.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordinates coord = achunkcoordinates[j];

            if (stillAboveGround && hasAirAround(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c)) {
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, this.treeBlock, this.branchMeta);
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b - 1, coord.field_71573_c, this.treeBlock, this.branchMeta);
            } else {
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b, coord.field_71573_c, this.rootBlock, this.rootMeta);
                this.setBlockAndMetadata(world, coord.field_71574_a, coord.field_71572_b - 1, coord.field_71573_c, this.rootBlock, this.rootMeta);
                stillAboveGround = false;
            }
        }

    }

    protected void makeLargeBranch(World world, Random random, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        int[] src = new int[] { sx, sy, sz};
        int[] dest = translate(src[0], src[1], src[2], length, angle, tilt);

        this.drawBresehnam(world, src[0], src[1], src[2], dest[0], dest[1], dest[2], this.treeBlock, this.branchMeta);
        int reinforcements = random.nextInt(3);

        int numMedBranches;
        int numSmallBranches;
        int i;

        for (numMedBranches = 0; numMedBranches <= reinforcements; ++numMedBranches) {
            numSmallBranches = (numMedBranches & 2) == 0 ? 1 : 0;
            i = (numMedBranches & 1) == 0 ? 1 : -1;
            int outVar = (numMedBranches & 2) == 0 ? 0 : 1;

            this.drawBresehnam(world, src[0] + numSmallBranches, src[1] + i, src[2] + outVar, dest[0], dest[1], dest[2], this.treeBlock, this.branchMeta);
        }

        if (leafy) {
            this.drawLeafBlob(world, dest[0], dest[1] + 1, dest[2], 3, this.leafBlock, this.leafMeta);
        }

        numMedBranches = random.nextInt((int) (length / 6.0D)) + random.nextInt(2) + 1;

        for (numSmallBranches = 0; numSmallBranches <= numMedBranches; ++numSmallBranches) {
            double d0 = random.nextDouble() * 0.3D + 0.3D;
            double angleVar = random.nextDouble() * 0.225D * ((numSmallBranches & 1) == 0 ? 1.0D : -1.0D);
            int[] bsrc = translate(src[0], src[1], src[2], length * d0, angle, tilt);

            this.makeMedBranch(world, random, bsrc[0], bsrc[1], bsrc[2], length * 0.6D, angle + angleVar, tilt, leafy);
        }

        numSmallBranches = random.nextInt(2) + 1;

        for (i = 0; i <= numSmallBranches; ++i) {
            double d1 = random.nextDouble() * 0.25D + 0.25D;
            double angleVar1 = random.nextDouble() * 0.25D * ((i & 1) == 0 ? 1.0D : -1.0D);
            int[] bsrc1 = translate(src[0], src[1], src[2], length * d1, angle, tilt);

            this.makeSmallBranch(world, random, bsrc1[0], bsrc1[1], bsrc1[2], Math.max(length * 0.3D, 2.0D), angle + angleVar1, tilt, leafy);
        }

        if (random.nextInt(8) == 0) {
            this.makeLeafDungeon(world, random, dest[0], dest[1] + 1, dest[2]);
        }

    }

    private void makeLeafDungeon(World world, Random random, int x, int y, int z) {
        this.drawLeafBlob(world, x, y, z, 4, this.leafBlock, this.leafMeta);
        this.drawBlob(world, x, y, z, 3, this.treeBlock, this.branchMeta);
        this.drawBlob(world, x, y, z, 2, Blocks.field_150350_a, 0);
        world.func_147465_d(x + 0, y + 1, z + 0, Blocks.field_150474_ac, 0, 2);
        TileEntityMobSpawner ms = (TileEntityMobSpawner) world.func_147438_o(x + 0, y + 1, z + 0);

        if (ms != null) {
            ms.func_145881_a().func_98272_a(TFCreatures.getSpawnerNameFor("Swarm Spider"));
        }

        this.makeLeafDungeonChest(world, random, x, y, z);
    }

    private void makeLeafDungeonChest(World world, Random random, int x, int y, int z) {
        int dir = random.nextInt(4);

        x += Direction.field_71583_a[dir];
        x += Direction.field_71583_a[dir];
        z += Direction.field_71581_b[dir];
        z += Direction.field_71581_b[dir];
        TFTreasure.tree_cache.generate(world, random, x, y - 1, z);
    }

    protected void makeLargeBranch(World world, Random random, int x, int y, int z, int diameter, int branchHeight, double length, double angle, double tilt, boolean leafy) {
        int sy = y + branchHeight;
        int[] src = translate(x, sy, z, (double) diameter, angle, 0.5D);

        this.makeLargeBranch(world, random, src[0], src[1], src[2], length, angle, tilt, leafy);
    }

    protected void addFirefly(World world, int x, int y, int z, int diameter, int fHeight, double fAngle) {
        int[] src = translate(x, y + fHeight, z, (double) (diameter + 1), fAngle, 0.5D);

        fAngle %= 1.0D;
        byte tmeta = 0;

        if (fAngle <= 0.875D && fAngle > 0.125D) {
            if (fAngle > 0.125D && fAngle <= 0.375D) {
                tmeta = 1;
            } else if (fAngle > 0.375D && fAngle <= 0.625D) {
                tmeta = 4;
            } else if (fAngle > 0.625D && fAngle <= 0.875D) {
                tmeta = 2;
            }
        } else {
            tmeta = 3;
        }

        if (TFBlocks.firefly.func_149742_c(world, src[0], src[1], src[2])) {
            this.setBlockAndMetadata(world, src[0], src[1], src[2], TFBlocks.firefly, tmeta);
        }

    }

    protected void addCicada(World world, int x, int y, int z, int diameter, int fHeight, double fAngle) {
        int[] src = translate(x, y + fHeight, z, (double) (diameter + 1), fAngle, 0.5D);

        fAngle %= 1.0D;
        byte tmeta = 1;

        if (fAngle <= 0.875D && fAngle > 0.125D) {
            if (fAngle > 0.125D && fAngle <= 0.375D) {
                tmeta = 1;
            } else if (fAngle > 0.375D && fAngle <= 0.625D) {
                tmeta = 4;
            } else if (fAngle > 0.625D && fAngle <= 0.875D) {
                tmeta = 2;
            }
        } else {
            tmeta = 3;
        }

        if (TFBlocks.cicada.func_149742_c(world, src[0], src[1], src[2])) {
            this.setBlockAndMetadata(world, src[0], src[1], src[2], TFBlocks.cicada, tmeta);
        }

    }
}
