package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class TFGenerator extends WorldGenerator {

    public TFGenerator() {
        this(false);
    }

    public TFGenerator(boolean par1) {
        super(par1);
    }

    public static int[] translate(int sx, int sy, int sz, double distance, double angle, double tilt) {
        int[] dest = new int[] { sx, sy, sz};
        double rangle = angle * 2.0D * 3.141592653589793D;
        double rtilt = tilt * 3.141592653589793D;

        dest[0] = (int) ((long) dest[0] + Math.round(Math.sin(rangle) * Math.sin(rtilt) * distance));
        dest[1] = (int) ((long) dest[1] + Math.round(Math.cos(rtilt) * distance));
        dest[2] = (int) ((long) dest[2] + Math.round(Math.cos(rangle) * Math.sin(rtilt) * distance));
        return dest;
    }

    public static ChunkCoordinates translateCoords(int sx, int sy, int sz, double distance, double angle, double tilt) {
        ChunkCoordinates dest = new ChunkCoordinates(sx, sy, sz);
        double rangle = angle * 2.0D * 3.141592653589793D;
        double rtilt = tilt * 3.141592653589793D;

        dest.field_71574_a = (int) ((long) dest.field_71574_a + Math.round(Math.sin(rangle) * Math.sin(rtilt) * distance));
        dest.field_71572_b = (int) ((long) dest.field_71572_b + Math.round(Math.cos(rtilt) * distance));
        dest.field_71573_c = (int) ((long) dest.field_71573_c + Math.round(Math.cos(rangle) * Math.sin(rtilt) * distance));
        return dest;
    }

    protected void drawBresehnam(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block blockValue, int metaValue) {
        ChunkCoordinates[] lineArray = getBresehnamArrayCoords(x1, y1, z1, x2, y2, z2);
        ChunkCoordinates[] achunkcoordinates = lineArray;
        int i = lineArray.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordinates pixel = achunkcoordinates[j];

            this.setBlockAndMetadata(world, pixel.field_71574_a, pixel.field_71572_b, pixel.field_71573_c, blockValue, metaValue);
        }

    }

    public static ChunkCoordinates[] getBresehnamArrayCoords(ChunkCoordinates src, ChunkCoordinates dest) {
        return getBresehnamArrayCoords(src.field_71574_a, src.field_71572_b, src.field_71573_c, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c);
    }

    public static ChunkCoordinates[] getBresehnamArrayCoords(int x1, int y1, int z1, int x2, int y2, int z2) {
        ChunkCoordinates pixel = new ChunkCoordinates(x1, y1, z1);
        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;
        int x_inc = dx < 0 ? -1 : 1;
        int l = Math.abs(dx);
        int y_inc = dy < 0 ? -1 : 1;
        int m = Math.abs(dy);
        int z_inc = dz < 0 ? -1 : 1;
        int n = Math.abs(dz);
        int dx2 = l << 1;
        int dy2 = m << 1;
        int dz2 = n << 1;
        int i;
        int err_1;
        int err_2;
        ChunkCoordinates[] lineArray;

        if (l >= m && l >= n) {
            err_1 = dy2 - l;
            err_2 = dz2 - l;
            lineArray = new ChunkCoordinates[l + 1];

            for (i = 0; i < l; ++i) {
                lineArray[i] = new ChunkCoordinates(pixel);
                if (err_1 > 0) {
                    pixel.field_71572_b += y_inc;
                    err_1 -= dx2;
                }

                if (err_2 > 0) {
                    pixel.field_71573_c += z_inc;
                    err_2 -= dx2;
                }

                err_1 += dy2;
                err_2 += dz2;
                pixel.field_71574_a += x_inc;
            }
        } else if (m >= l && m >= n) {
            err_1 = dx2 - m;
            err_2 = dz2 - m;
            lineArray = new ChunkCoordinates[m + 1];

            for (i = 0; i < m; ++i) {
                lineArray[i] = new ChunkCoordinates(pixel);
                if (err_1 > 0) {
                    pixel.field_71574_a += x_inc;
                    err_1 -= dy2;
                }

                if (err_2 > 0) {
                    pixel.field_71573_c += z_inc;
                    err_2 -= dy2;
                }

                err_1 += dx2;
                err_2 += dz2;
                pixel.field_71572_b += y_inc;
            }
        } else {
            err_1 = dy2 - n;
            err_2 = dx2 - n;
            lineArray = new ChunkCoordinates[n + 1];

            for (i = 0; i < n; ++i) {
                lineArray[i] = new ChunkCoordinates(pixel);
                if (err_1 > 0) {
                    pixel.field_71572_b += y_inc;
                    err_1 -= dz2;
                }

                if (err_2 > 0) {
                    pixel.field_71574_a += x_inc;
                    err_2 -= dz2;
                }

                err_1 += dy2;
                err_2 += dx2;
                pixel.field_71573_c += z_inc;
            }
        }

        lineArray[lineArray.length - 1] = new ChunkCoordinates(pixel);
        return lineArray;
    }

    public void makeLeafCircle(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue) {
        this.makeLeafCircle(world, sx, sy, sz, rad, blockValue, metaValue, false);
    }

    public void makeLeafCircle(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue, boolean useHack) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dz = 0; dz <= rad; ++dz) {
                int dist = Math.max(dx, dz) + (Math.min(dx, dz) >> 1);

                if (useHack && dx == 3 && dz == 3) {
                    dist = 6;
                }

                if (dist <= rad) {
                    this.putLeafBlock(world, sx + dx, sy, sz + dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx + dx, sy, sz - dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx - dx, sy, sz + dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx - dx, sy, sz - dz, blockValue, metaValue);
                }
            }
        }

    }

    public void makeLeafCircle2(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue, boolean useHack) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dz = 0; dz <= rad; ++dz) {
                if (dx * dx + dz * dz <= rad * rad) {
                    this.putLeafBlock(world, sx + 1 + dx, sy, sz + 1 + dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx + 1 + dx, sy, sz - dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx - dx, sy, sz + 1 + dz, blockValue, metaValue);
                    this.putLeafBlock(world, sx - dx, sy, sz - dz, blockValue, metaValue);
                }
            }
        }

    }

    public void putLeafBlock(World world, int x, int y, int z, Block blockValue, int metaValue) {
        Block whatsThere = world.func_147439_a(x, y, z);

        if (whatsThere == null || whatsThere.canBeReplacedByLeaves(world, x, y, z)) {
            this.setBlockAndMetadata(world, x, y, z, blockValue, metaValue);
        }

    }

    protected Block randStone(Random rand, int howMuch) {
        return rand.nextInt(howMuch) >= 1 ? Blocks.field_150347_e : Blocks.field_150341_Y;
    }

    protected boolean isAreaClear(World world, Random rand, int x, int y, int z, int width, int height, int depth) {
        boolean flag = true;

        for (int cx = 0; cx < width; ++cx) {
            for (int cz = 0; cz < depth; ++cz) {
                Material m = world.func_147439_a(x + cx, y - 1, z + cz).func_149688_o();

                if (m != Material.field_151578_c && m != Material.field_151577_b && m != Material.field_151576_e) {
                    flag = false;
                }

                for (int cy = 0; cy < height; ++cy) {
                    if (!world.func_147437_c(x + cx, y + cy, z + cz)) {
                        flag = false;
                    }
                }
            }
        }

        return flag;
    }

    public void drawBlob(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dy = 0; dy <= rad; ++dy) {
                for (byte dz = 0; dz <= rad; ++dz) {
                    boolean dist = false;
                    int i;

                    if (dx >= dy && dx >= dz) {
                        i = dx + (Math.max(dy, dz) >> 1) + (Math.min(dy, dz) >> 2);
                    } else if (dy >= dx && dy >= dz) {
                        i = dy + (Math.max(dx, dz) >> 1) + (Math.min(dx, dz) >> 2);
                    } else {
                        i = dz + (Math.max(dx, dy) >> 1) + (Math.min(dx, dy) >> 2);
                    }

                    if (i <= rad) {
                        this.setBlockAndMetadata(world, sx + dx, sy + dy, sz + dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx + dx, sy + dy, sz - dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx - dx, sy + dy, sz + dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx - dx, sy + dy, sz - dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx + dx, sy - dy, sz + dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx + dx, sy - dy, sz - dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx - dx, sy - dy, sz + dz, blockValue, metaValue);
                        this.setBlockAndMetadata(world, sx - dx, sy - dy, sz - dz, blockValue, metaValue);
                    }
                }
            }
        }

    }

    public void drawLeafBlob(World world, int sx, int sy, int sz, int rad, Block blockValue, int metaValue) {
        for (byte dx = 0; dx <= rad; ++dx) {
            for (byte dy = 0; dy <= rad; ++dy) {
                for (byte dz = 0; dz <= rad; ++dz) {
                    boolean dist = false;
                    int i;

                    if (dx >= dy && dx >= dz) {
                        i = dx + (Math.max(dy, dz) >> 1) + (Math.min(dy, dz) >> 2);
                    } else if (dy >= dx && dy >= dz) {
                        i = dy + (Math.max(dx, dz) >> 1) + (Math.min(dx, dz) >> 2);
                    } else {
                        i = dz + (Math.max(dx, dy) >> 1) + (Math.min(dx, dy) >> 2);
                    }

                    if (i <= rad) {
                        this.putLeafBlock(world, sx + dx, sy + dy, sz + dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx + dx, sy + dy, sz - dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx - dx, sy + dy, sz + dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx - dx, sy + dy, sz - dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx + dx, sy - dy, sz + dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx + dx, sy - dy, sz - dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx - dx, sy - dy, sz + dz, blockValue, metaValue);
                        this.putLeafBlock(world, sx - dx, sy - dy, sz - dz, blockValue, metaValue);
                    }
                }
            }
        }

    }

    protected static boolean surroundedByAir(IBlockAccess world, int bx, int by, int bz) {
        boolean airAround = true;

        if (!world.func_147437_c(bx + 1, by, bz)) {
            airAround = false;
        }

        if (!world.func_147437_c(bx - 1, by, bz)) {
            airAround = false;
        }

        if (!world.func_147437_c(bx, by, bz + 1)) {
            airAround = false;
        }

        if (!world.func_147437_c(bx, by, bz - 1)) {
            airAround = false;
        }

        if (!world.func_147437_c(bx, by + 1, bz)) {
            airAround = false;
        }

        if (!world.func_147437_c(bx, by - 1, bz)) {
            airAround = false;
        }

        return airAround;
    }

    protected static boolean hasAirAround(IBlockAccess world, int bx, int by, int bz) {
        boolean airAround = false;

        if (world.func_147439_a(bx + 1, by, bz) == Blocks.field_150350_a) {
            airAround = true;
        }

        if (world.func_147439_a(bx - 1, by, bz) == Blocks.field_150350_a) {
            airAround = true;
        }

        if (world.func_147439_a(bx, by, bz + 1) == Blocks.field_150350_a) {
            airAround = true;
        }

        if (world.func_147439_a(bx, by, bz - 1) == Blocks.field_150350_a) {
            airAround = true;
        }

        if (world.func_147439_a(bx, by + 1, bz) == Blocks.field_150350_a) {
            airAround = true;
        }

        return airAround;
    }

    protected static boolean isNearSolid(IBlockAccess world, int bx, int by, int bz) {
        boolean nearSolid = false;

        if (world.func_147439_a(bx + 1, by, bz).func_149688_o().func_76220_a()) {
            nearSolid = true;
        }

        if (world.func_147439_a(bx - 1, by, bz).func_149688_o().func_76220_a()) {
            nearSolid = true;
        }

        if (world.func_147439_a(bx, by, bz + 1).func_149688_o().func_76220_a()) {
            nearSolid = true;
        }

        if (world.func_147439_a(bx, by, bz - 1).func_149688_o().func_76220_a()) {
            nearSolid = true;
        }

        return nearSolid;
    }

    protected void setBlock(World world, int x, int y, int z, Block block) {
        this.func_150515_a(world, x, y, z, block);
    }

    protected void setBlockAndMetadata(World world, int x, int y, int z, Block block, int meta) {
        this.func_150516_a(world, x, y, z, block, meta);
    }
}
