package twilightforest.structures;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenCanopyTree;

public class TFMaze {

    public int width;
    public int depth;
    public int oddBias = 3;
    public int evenBias = 1;
    public int tall = 3;
    public int head = 0;
    public int roots = 0;
    public int worldX;
    public int worldY;
    public int worldZ;
    public int type;
    public Block wallBlockID;
    public int wallBlockMeta;
    public Block wallVar0ID;
    public int wallVar0Meta;
    public float wallVarRarity;
    public Block headBlockID;
    public int headBlockMeta;
    public Block rootBlockID;
    public int rootBlockMeta;
    public Block pillarBlockID;
    public int pillarBlockMeta;
    public Block doorBlockID;
    public int doorBlockMeta;
    public float doorRarity;
    public Block torchBlockID;
    public int torchBlockMeta;
    public float torchRarity;
    protected int rawWidth;
    protected int rawDepth;
    protected int[] storage;
    public static final int OUT_OF_BOUNDS = Integer.MIN_VALUE;
    public static final int OOB = Integer.MIN_VALUE;
    public static final int ROOM = 5;
    public static final int DOOR = 6;
    public Random rand;

    public TFMaze(int cellsWidth, int cellsDepth) {
        this.wallBlockID = TFBlocks.mazestone;
        this.wallBlockMeta = 2;
        this.rootBlockID = TFBlocks.mazestone;
        this.rootBlockMeta = 0;
        this.torchBlockID = Blocks.field_150478_aa;
        this.pillarBlockID = null;
        this.torchBlockMeta = 0;
        this.torchRarity = 0.75F;
        this.doorRarity = 0.0F;
        this.width = cellsWidth;
        this.depth = cellsDepth;
        this.rawWidth = this.width * 2 + 1;
        this.rawDepth = this.depth * 2 + 1;
        this.storage = new int[this.rawWidth * this.rawDepth];
        this.rand = new Random();
    }

    public int getCell(int x, int z) {
        return this.getRaw(x * 2 + 1, z * 2 + 1);
    }

    public void putCell(int x, int z, int value) {
        this.putRaw(x * 2 + 1, z * 2 + 1, value);
    }

    public boolean cellEquals(int x, int z, int value) {
        return this.getCell(x, z) == value;
    }

    public int getWall(int sx, int sz, int dx, int dz) {
        if (dx == sx + 1 && dz == sz) {
            return this.getRaw(sx * 2 + 2, sz * 2 + 1);
        } else if (dx == sx - 1 && dz == sz) {
            return this.getRaw(sx * 2 + 0, sz * 2 + 1);
        } else if (dx == sx && dz == sz + 1) {
            return this.getRaw(sx * 2 + 1, sz * 2 + 2);
        } else if (dx == sx && dz == sz - 1) {
            return this.getRaw(sx * 2 + 1, sz * 2 + 0);
        } else {
            System.out.println("Wall check out of bounds; s = " + sx + ", " + sz + "; d = " + dx + ", " + dz);
            return Integer.MIN_VALUE;
        }
    }

    public void putWall(int sx, int sz, int dx, int dz, int value) {
        if (dx == sx + 1 && dz == sz) {
            this.putRaw(sx * 2 + 2, sz * 2 + 1, value);
        }

        if (dx == sx - 1 && dz == sz) {
            this.putRaw(sx * 2 + 0, sz * 2 + 1, value);
        }

        if (dx == sx && dz == sz + 1) {
            this.putRaw(sx * 2 + 1, sz * 2 + 2, value);
        }

        if (dx == sx && dz == sz - 1) {
            this.putRaw(sx * 2 + 1, sz * 2 + 0, value);
        }

    }

    public boolean isWall(int sx, int sz, int dx, int dz) {
        return this.getWall(sx, sz, dx, dz) == 0;
    }

    public void putRaw(int rawx, int rawz, int value) {
        if (rawx >= 0 && rawx < this.rawWidth && rawz >= 0 && rawz < this.rawDepth) {
            this.storage[rawz * this.rawWidth + rawx] = value;
        }

    }

    protected int getRaw(int rawx, int rawz) {
        return rawx >= 0 && rawx < this.rawWidth && rawz >= 0 && rawz < this.rawDepth ? this.storage[rawz * this.rawWidth + rawx] : Integer.MIN_VALUE;
    }

    public void setSeed(long newSeed) {
        this.rand.setSeed(newSeed);
    }

    public void copyToWorld(World world, int dx, int dy, int dz) {
        this.worldX = dx;
        this.worldY = dy;
        this.worldZ = dz;

        for (int x = 0; x < this.rawWidth; ++x) {
            for (int z = 0; z < this.rawDepth; ++z) {
                if (this.getRaw(x, z) == 0) {
                    int mdx = dx + x / 2 * (this.evenBias + this.oddBias);
                    int mdz = dz + z / 2 * (this.evenBias + this.oddBias);
                    int even;

                    if (this.isEven(x) && this.isEven(z)) {
                        if (this.type == 4 && this.shouldTree(x, z)) {
                            (new TFGenCanopyTree()).func_76484_a(world, this.rand, mdx, dy, mdz);
                        } else {
                            for (even = 0; even < this.head; ++even) {
                                this.putHeadBlock(world, mdx, dy + this.tall + even, mdz);
                            }

                            for (even = 0; even < this.tall; ++even) {
                                this.putWallBlock(world, mdx, dy + even, mdz);
                            }

                            for (even = 1; even <= this.roots; ++even) {
                                this.putRootBlock(world, mdx, dy - even, mdz);
                            }
                        }
                    }

                    int odd;
                    int y;

                    if (this.isEven(x) && !this.isEven(z)) {
                        for (even = 0; even < this.evenBias; ++even) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                for (y = 0; y < this.head; ++y) {
                                    this.putHeadBlock(world, mdx + even, dy + this.tall + y, mdz + odd);
                                }

                                for (y = 0; y < this.tall; ++y) {
                                    this.putWallBlock(world, mdx + even, dy + y, mdz + odd);
                                }

                                for (y = 1; y <= this.roots; ++y) {
                                    this.putRootBlock(world, mdx + even, dy - y, mdz + odd);
                                }
                            }
                        }
                    }

                    if (!this.isEven(x) && this.isEven(z)) {
                        for (even = 0; even < this.evenBias; ++even) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                for (y = 0; y < this.head; ++y) {
                                    this.putHeadBlock(world, mdx + odd, dy + this.tall + y, mdz + even);
                                }

                                for (y = 0; y < this.tall; ++y) {
                                    this.putWallBlock(world, mdx + odd, dy + y, mdz + even);
                                }

                                for (y = 1; y <= this.roots; ++y) {
                                    this.putRootBlock(world, mdx + odd, dy - y, mdz + even);
                                }
                            }
                        }
                    }
                }
            }
        }

        this.placeTorches(world);
    }

    public void carveToWorld(World world, int dx, int dy, int dz) {
        this.worldX = dx;
        this.worldY = dy;
        this.worldZ = dz;

        for (int x = 0; x < this.rawWidth; ++x) {
            for (int z = 0; z < this.rawDepth; ++z) {
                if (this.getRaw(x, z) != 0) {
                    int mdx = dx + x / 2 * (this.evenBias + this.oddBias);
                    int mdz = dz + z / 2 * (this.evenBias + this.oddBias);
                    int mx;

                    if (this.isEven(x) && this.isEven(z)) {
                        for (mx = 0; mx < this.tall; ++mx) {
                            this.carveBlock(world, mdx, dy + mx, mdz);
                        }
                    } else {
                        int mz;

                        if (this.isEven(x) && !this.isEven(z)) {
                            for (mx = 1; mx <= this.oddBias; ++mx) {
                                for (mz = 0; mz < this.tall; ++mz) {
                                    this.carveBlock(world, mdx, dy + mz, mdz + mx);
                                }
                            }
                        } else if (!this.isEven(x) && this.isEven(z)) {
                            for (mx = 1; mx <= this.oddBias; ++mx) {
                                for (mz = 0; mz < this.tall; ++mz) {
                                    this.carveBlock(world, mdx + mx, dy + mz, mdz);
                                }
                            }
                        } else if (!this.isEven(x) && !this.isEven(z)) {
                            for (mx = 1; mx <= this.oddBias; ++mx) {
                                for (mz = 1; mz <= this.oddBias; ++mz) {
                                    for (int y = 0; y < this.tall; ++y) {
                                        this.carveBlock(world, mdx + mx, dy + y, mdz + mz);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        this.placeTorches(world);
    }

    public void copyToStructure(World world, int dx, int dy, int dz, StructureTFComponent component, StructureBoundingBox sbb) {
        int x;
        int z;
        int mdx;
        int mdy;
        int mdz;

        for (x = 0; x < this.rawWidth; ++x) {
            for (z = 0; z < this.rawDepth; ++z) {
                int odd;
                int y;

                if (this.getRaw(x, z) == 0) {
                    mdx = dx + x / 2 * (this.evenBias + this.oddBias);
                    mdy = dz + z / 2 * (this.evenBias + this.oddBias);
                    if (this.evenBias > 1) {
                        --mdx;
                        --mdy;
                    }

                    if (this.isEven(x) && this.isEven(z)) {
                        if (this.type == 4 && this.shouldTree(x, z)) {
                            this.putCanopyTree(world, mdx, dy, mdy, component, sbb);
                        } else {
                            for (mdz = 0; mdz < this.evenBias; ++mdz) {
                                for (odd = 0; odd < this.evenBias; ++odd) {
                                    for (y = 0; y < this.head; ++y) {
                                        this.putHeadBlock(world, mdx + mdz, dy + this.tall + y, mdy + odd, component, sbb);
                                    }

                                    for (y = 0; y < this.tall; ++y) {
                                        if (this.shouldPillar(x, z)) {
                                            this.putPillarBlock(world, mdx + mdz, dy + y, mdy + odd, component, sbb);
                                        } else {
                                            this.putWallBlock(world, mdx + mdz, dy + y, mdy + odd, component, sbb);
                                        }
                                    }

                                    for (y = 1; y <= this.roots; ++y) {
                                        this.putRootBlock(world, mdx + mdz, dy - y, mdy + odd, component, sbb);
                                    }
                                }
                            }
                        }
                    }

                    if (this.isEven(x) && !this.isEven(z)) {
                        for (mdz = 0; mdz < this.evenBias; ++mdz) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                this.makeWallThing(world, dy, component, sbb, mdx, mdy, mdz, odd);
                            }
                        }
                    }

                    if (!this.isEven(x) && this.isEven(z)) {
                        for (mdz = 0; mdz < this.evenBias; ++mdz) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                this.makeWallThing(world, dy, component, sbb, mdx, mdy, odd, mdz);
                            }
                        }
                    }
                } else if (this.getRaw(x, z) == 6) {
                    mdx = dx + x / 2 * (this.evenBias + this.oddBias);
                    mdy = dz + z / 2 * (this.evenBias + this.oddBias);
                    if (this.evenBias > 1) {
                        --mdx;
                        --mdy;
                    }

                    if (this.isEven(x) && !this.isEven(z)) {
                        for (mdz = 0; mdz < this.evenBias; ++mdz) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                for (y = 0; y < this.head; ++y) {
                                    this.putHeadBlock(world, mdx + mdz, dy + this.tall + y, mdy + odd, component, sbb);
                                }

                                for (y = 0; y < this.tall; ++y) {
                                    this.putDoorBlock(world, mdx + mdz, dy + y, mdy + odd, component, sbb);
                                }

                                for (y = 1; y <= this.roots; ++y) {
                                    this.putRootBlock(world, mdx + mdz, dy - y, mdy + odd, component, sbb);
                                }
                            }
                        }
                    }

                    if (!this.isEven(x) && this.isEven(z)) {
                        for (mdz = 0; mdz < this.evenBias; ++mdz) {
                            for (odd = 1; odd <= this.oddBias; ++odd) {
                                for (y = 0; y < this.head; ++y) {
                                    this.putHeadBlock(world, mdx + odd, dy + this.tall + y, mdy + mdz, component, sbb);
                                }

                                for (y = 0; y < this.tall; ++y) {
                                    this.putDoorBlock(world, mdx + odd, dy + y, mdy + mdz, component, sbb);
                                }

                                for (y = 1; y <= this.roots; ++y) {
                                    this.putRootBlock(world, mdx + odd, dy - y, mdy + mdz, component, sbb);
                                }
                            }
                        }
                    }
                }
            }
        }

        for (x = 0; x < this.rawWidth; ++x) {
            for (z = 0; z < this.rawDepth; ++z) {
                if (this.getRaw(x, z) == 0) {
                    mdx = dx + x / 2 * (this.evenBias + this.oddBias);
                    mdy = dy + 1;
                    mdz = dz + z / 2 * (this.evenBias + this.oddBias);
                    if (this.isEven(x) && this.isEven(z) && this.shouldTorch(x, z) && component.func_151548_a(world, mdx, mdy, mdz, sbb) == this.wallBlockID) {
                        component.func_151550_a(world, this.torchBlockID, this.torchBlockMeta, mdx, mdy, mdz, sbb);
                    }
                }
            }
        }

    }

    protected void makeWallThing(World world, int dy, StructureTFComponent component, StructureBoundingBox sbb, int mdx, int mdz, int even, int odd) {
        int y;

        for (y = 0; y < this.head; ++y) {
            this.putHeadBlock(world, mdx + even, dy + this.tall + y, mdz + odd, component, sbb);
        }

        for (y = 0; y < this.tall; ++y) {
            this.putWallBlock(world, mdx + even, dy + y, mdz + odd, component, sbb);
        }

        for (y = 1; y <= this.roots; ++y) {
            this.putRootBlock(world, mdx + even, dy - y, mdz + odd, component, sbb);
        }

    }

    protected void putPillarBlock(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        component.func_151550_a(world, this.pillarBlockID, this.pillarBlockMeta, x, y, z, sbb);
    }

    protected void putWallBlock(World world, int x, int y, int z) {
        world.func_147465_d(x, y, z, this.wallBlockID, this.wallBlockMeta, 2);
    }

    protected void putWallBlock(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        if (this.wallVarRarity > 0.0F && this.rand.nextFloat() < this.wallVarRarity) {
            component.func_151550_a(world, this.wallVar0ID, this.wallVar0Meta, x, y, z, sbb);
        } else {
            component.func_151550_a(world, this.wallBlockID, this.wallBlockMeta, x, y, z, sbb);
        }

    }

    protected void putDoorBlock(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        component.func_151550_a(world, this.doorBlockID, this.doorBlockMeta, x, y, z, sbb);
    }

    protected void carveBlock(World world, int x, int y, int z) {
        world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
    }

    protected void putHeadBlock(World world, int x, int y, int z) {
        world.func_147465_d(x, y, z, this.headBlockID, this.headBlockMeta, 2);
    }

    protected void putHeadBlock(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        component.func_151550_a(world, this.headBlockID, this.headBlockMeta, x, y, z, sbb);
    }

    protected void putRootBlock(World world, int x, int y, int z) {
        world.func_147465_d(x, y, z, this.rootBlockID, this.rootBlockMeta, 2);
    }

    protected void putRootBlock(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        component.func_151550_a(world, this.rootBlockID, this.rootBlockMeta, x, y, z, sbb);
    }

    protected void putCanopyTree(World world, int x, int y, int z, StructureTFComponent component, StructureBoundingBox sbb) {
        int wx = component.func_74865_a(x, z);
        int wy = component.func_74862_a(y);
        int wz = component.func_74873_b(x, z);

        if (sbb.func_78890_b(wx, wy, wz)) {
            (new TFGenCanopyTree()).func_76484_a(world, this.rand, wx, wy, wz);
        }

    }

    public final boolean isEven(int n) {
        return n % 2 == 0;
    }

    public void placeTorches(World world) {
        byte torchHeight = 1;

        for (int x = 0; x < this.rawWidth; ++x) {
            for (int z = 0; z < this.rawDepth; ++z) {
                if (this.getRaw(x, z) == 0) {
                    int mdx = this.worldX + x / 2 * (this.evenBias + this.oddBias);
                    int mdy = this.worldY + torchHeight;
                    int mdz = this.worldZ + z / 2 * (this.evenBias + this.oddBias);

                    if (this.isEven(x) && this.isEven(z) && this.shouldTorch(x, z) && world.func_147439_a(mdx, mdy, mdz) == this.wallBlockID) {
                        world.func_147465_d(mdx, mdy, mdz, this.torchBlockID, this.torchBlockMeta, 2);
                    }
                }
            }
        }

    }

    public boolean shouldTorch(int rx, int rz) {
        return this.getRaw(rx + 1, rz) != Integer.MIN_VALUE && this.getRaw(rx - 1, rz) != Integer.MIN_VALUE && this.getRaw(rx, rz + 1) != Integer.MIN_VALUE && this.getRaw(rx, rz - 1) != Integer.MIN_VALUE ? ((this.getRaw(rx + 1, rz) != 0 || this.getRaw(rx - 1, rz) != 0) && (this.getRaw(rx, rz + 1) != 0 || this.getRaw(rx, rz - 1) != 0) ? this.rand.nextFloat() <= this.torchRarity : false) : false;
    }

    public boolean shouldPillar(int rx, int rz) {
        return this.pillarBlockID == null ? false : (this.getRaw(rx + 1, rz) != Integer.MIN_VALUE && this.getRaw(rx - 1, rz) != Integer.MIN_VALUE && this.getRaw(rx, rz + 1) != Integer.MIN_VALUE && this.getRaw(rx, rz - 1) != Integer.MIN_VALUE ? (this.getRaw(rx + 1, rz) != 0 || this.getRaw(rx - 1, rz) != 0) && (this.getRaw(rx, rz + 1) != 0 || this.getRaw(rx, rz - 1) != 0) : false);
    }

    public boolean shouldTree(int rx, int rz) {
        return (rx == 0 || rx == this.rawWidth - 1) && (this.getRaw(rx, rz + 1) != 0 || this.getRaw(rx, rz - 1) != 0) ? true : ((rz == 0 || rz == this.rawDepth - 1) && (this.getRaw(rx + 1, rz) != 0 || this.getRaw(rx - 1, rz) != 0) ? true : this.rand.nextInt(50) == 0);
    }

    int getWorldX(int x) {
        return this.worldX + x * (this.evenBias + this.oddBias) + 1;
    }

    int getWorldZ(int z) {
        return this.worldZ + z * (this.evenBias + this.oddBias) + 1;
    }

    public void carveRoom0(int cx, int cz) {
        this.putCell(cx, cz, 5);
        this.putCell(cx + 1, cz, 5);
        this.putWall(cx, cz, cx + 1, cz, 5);
        this.putCell(cx - 1, cz, 5);
        this.putWall(cx, cz, cx - 1, cz, 5);
        this.putCell(cx, cz + 1, 5);
        this.putWall(cx, cz, cx, cz + 1, 5);
        this.putCell(cx, cz - 1, 5);
        this.putWall(cx, cz, cx, cz - 1, 5);
    }

    public void carveRoom1(int cx, int cz) {
        int rx = cx * 2 + 1;
        int rz = cz * 2 + 1;

        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                this.putRaw(rx + i, rz + j, 5);
            }
        }

        this.putCell(rx, rz + 1, 0);
        this.putCell(rx, rz - 1, 0);
        this.putCell(rx + 1, rz, 0);
        this.putCell(rx - 1, rz, 0);
        if (this.getRaw(rx, rz + 4) != Integer.MIN_VALUE) {
            this.putRaw(rx, rz + 3, 5);
        }

        if (this.getRaw(rx, rz - 4) != Integer.MIN_VALUE) {
            this.putRaw(rx, rz - 3, 5);
        }

        if (this.getRaw(rx + 4, rz) != Integer.MIN_VALUE) {
            this.putRaw(rx + 3, rz, 5);
        }

        if (this.getRaw(rx - 4, rz) != Integer.MIN_VALUE) {
            this.putRaw(rx - 3, rz, 5);
        }

    }

    public void add4Exits() {
        int hx = this.rawWidth / 2 + 1;
        int hz = this.rawDepth / 2 + 1;

        this.putRaw(hx, 0, 5);
        this.putRaw(hx, this.rawDepth - 1, 5);
        this.putRaw(0, hz, 5);
        this.putRaw(this.rawWidth - 1, hz, 5);
    }

    public void generateRecursiveBacktracker(int sx, int sz) {
        this.rbGen(sx, sz);
    }

    public void rbGen(int sx, int sz) {
        this.putCell(sx, sz, 1);
        int unvisited = 0;

        if (this.cellEquals(sx + 1, sz, 0)) {
            ++unvisited;
        }

        if (this.cellEquals(sx - 1, sz, 0)) {
            ++unvisited;
        }

        if (this.cellEquals(sx, sz + 1, 0)) {
            ++unvisited;
        }

        if (this.cellEquals(sx, sz - 1, 0)) {
            ++unvisited;
        }

        if (unvisited != 0) {
            int rn = this.rand.nextInt(unvisited);
            int dz = 0;
            int dx = 0;

            if (this.cellEquals(sx + 1, sz, 0)) {
                if (rn == 0) {
                    dx = sx + 1;
                    dz = sz;
                }

                --rn;
            }

            if (this.cellEquals(sx - 1, sz, 0)) {
                if (rn == 0) {
                    dx = sx - 1;
                    dz = sz;
                }

                --rn;
            }

            if (this.cellEquals(sx, sz + 1, 0)) {
                if (rn == 0) {
                    dx = sx;
                    dz = sz + 1;
                }

                --rn;
            }

            if (this.cellEquals(sx, sz - 1, 0) && rn == 0) {
                dx = sx;
                dz = sz - 1;
            }

            if (this.rand.nextFloat() <= this.doorRarity) {
                this.putWall(sx, sz, dx, dz, 6);
            } else {
                this.putWall(sx, sz, dx, dz, 2);
            }

            this.rbGen(dx, dz);
            this.rbGen(sx, sz);
            this.rbGen(sx, sz);
        }
    }
}
