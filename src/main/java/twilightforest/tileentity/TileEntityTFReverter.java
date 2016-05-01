package twilightforest.tileentity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import twilightforest.block.TFBlocks;

public class TileEntityTFReverter extends TileEntity {

    private static final int REVERT_CHANCE = 10;
    public int radius = 4;
    public int diameter;
    public double requiredPlayerRange;
    public Random rand;
    private int tickCount;
    private boolean slowScan;
    private int ticksSinceChange;
    private Block[] blockData;
    private byte[] metaData;

    public TileEntityTFReverter() {
        this.diameter = 2 * this.radius + 1;
        this.requiredPlayerRange = 16.0D;
        this.rand = new Random();
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.anyPlayerInRange()) {
            ++this.tickCount;
            if (this.field_145850_b.field_72995_K) {
                double d0 = (double) ((float) this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat());
                double d1 = (double) ((float) this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat());
                double d2 = (double) ((float) this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());

                this.field_145850_b.func_72869_a("reddust", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                if (this.rand.nextInt(10) == 0) {
                    this.makeRandomOutline();
                    this.makeRandomOutline();
                    this.makeRandomOutline();
                }
            } else {
                if (this.blockData == null || this.metaData == null) {
                    this.captureBlockData();
                    this.slowScan = true;
                }

                if (!this.slowScan || this.tickCount % 20 == 0) {
                    if (this.scanAndRevertChanges()) {
                        this.slowScan = false;
                        this.ticksSinceChange = 0;
                    } else {
                        ++this.ticksSinceChange;
                        if (this.ticksSinceChange > 20) {
                            this.slowScan = true;
                        }
                    }
                }
            }
        } else {
            this.blockData = null;
            this.metaData = null;
            this.tickCount = 0;
        }

    }

    private void makeRandomOutline() {
        this.makeOutline(this.rand.nextInt(12));
    }

    private void makeOutline(int outline) {
        double sx = (double) this.field_145851_c;
        double sy = (double) this.field_145848_d;
        double sz = (double) this.field_145849_e;
        double dx = (double) this.field_145851_c;
        double dy = (double) this.field_145848_d;
        double dz = (double) this.field_145849_e;

        switch (outline) {
        case 0:
        case 8:
            sx -= (double) this.radius;
            dx += (double) (this.radius + 1);
            sz -= (double) this.radius;
            dz -= (double) this.radius;
            break;

        case 1:
        case 9:
            sx -= (double) this.radius;
            dx -= (double) this.radius;
            sz -= (double) this.radius;
            dz += (double) (this.radius + 1);
            break;

        case 2:
        case 10:
            sx -= (double) this.radius;
            dx += (double) (this.radius + 1);
            sz += (double) (this.radius + 1);
            dz += (double) (this.radius + 1);
            break;

        case 3:
        case 11:
            sx += (double) (this.radius + 1);
            dx += (double) (this.radius + 1);
            sz -= (double) this.radius;
            dz += (double) (this.radius + 1);
            break;

        case 4:
            sx -= (double) this.radius;
            dx -= (double) this.radius;
            sz -= (double) this.radius;
            dz -= (double) this.radius;
            break;

        case 5:
            sx += (double) (this.radius + 1);
            dx += (double) (this.radius + 1);
            sz -= (double) this.radius;
            dz -= (double) this.radius;
            break;

        case 6:
            sx += (double) (this.radius + 1);
            dx += (double) (this.radius + 1);
            sz += (double) (this.radius + 1);
            dz += (double) (this.radius + 1);
            break;

        case 7:
            sx -= (double) this.radius;
            dx -= (double) this.radius;
            sz += (double) (this.radius + 1);
            dz += (double) (this.radius + 1);
        }

        switch (outline) {
        case 0:
        case 1:
        case 2:
        case 3:
            sy += (double) (this.radius + 1);
            dy += (double) (this.radius + 1);
            break;

        case 4:
        case 5:
        case 6:
        case 7:
            sy -= (double) this.radius;
            dy += (double) (this.radius + 1);
            break;

        case 8:
        case 9:
        case 10:
        case 11:
            sy -= (double) this.radius;
            dy -= (double) this.radius;
        }

        if (this.rand.nextBoolean()) {
            this.drawParticleLine((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, dx, dy, dz);
        } else {
            this.drawParticleLine(sx, sy, sz, (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D);
        }

        this.drawParticleLine(sx, sy, sz, dx, dy, dz);
    }

    protected void drawParticleLine(double srcX, double srcY, double srcZ, double destX, double destY, double destZ) {
        byte particles = 16;

        for (int i = 0; i < particles; ++i) {
            double trailFactor = (double) i / ((double) particles - 1.0D);
            double tx = srcX + (destX - srcX) * trailFactor + (double) this.rand.nextFloat() * 0.005D;
            double ty = srcY + (destY - srcY) * trailFactor + (double) this.rand.nextFloat() * 0.005D;
            double tz = srcZ + (destZ - srcZ) * trailFactor + (double) this.rand.nextFloat() * 0.005D;

            this.field_145850_b.func_72869_a("reddust", tx, ty, tz, 0.0D, 0.0D, 0.0D);
        }

    }

    private boolean scanAndRevertChanges() {
        int index = 0;
        boolean reverted = false;

        for (int x = -this.radius; x <= this.radius; ++x) {
            for (int y = -this.radius; y <= this.radius; ++y) {
                for (int z = -this.radius; z <= this.radius; ++z) {
                    Block blockID = this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                    byte meta = (byte) this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);

                    if (this.blockData[index] != blockID) {
                        if (this.revertBlock(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, blockID, meta, this.blockData[index], this.metaData[index])) {
                            reverted = true;
                        } else {
                            this.blockData[index] = blockID;
                            this.metaData[index] = meta;
                        }
                    }

                    ++index;
                }
            }
        }

        return reverted;
    }

    private boolean revertBlock(int x, int y, int z, Block thereBlockID, byte thereMeta, Block replaceBlockID, byte replaceMeta) {
        if (thereBlockID == Blocks.field_150350_a && !replaceBlockID.func_149688_o().func_76230_c()) {
            System.out.println("Not replacing block " + replaceBlockID + " because it doesn\'t block movement");
            return false;
        } else if (this.isUnrevertable(thereBlockID, thereMeta, replaceBlockID, replaceMeta)) {
            return false;
        } else {
            if (this.rand.nextInt(10) == 0) {
                if (replaceBlockID != Blocks.field_150350_a) {
                    replaceBlockID = TFBlocks.towerTranslucent;
                    replaceMeta = 4;
                }

                this.field_145850_b.func_147465_d(x, y, z, replaceBlockID, replaceMeta, 2);
                if (thereBlockID == Blocks.field_150350_a) {
                    this.field_145850_b.func_72926_e(2001, x, y, z, Block.func_149682_b(replaceBlockID) + (replaceMeta << 12));
                } else if (replaceBlockID == Blocks.field_150350_a) {
                    this.field_145850_b.func_72926_e(2001, x, y, z, Block.func_149682_b(thereBlockID) + (thereMeta << 12));
                    thereBlockID.func_149697_b(this.field_145850_b, x, y, z, thereMeta, 0);
                }
            }

            return true;
        }
    }

    private boolean isUnrevertable(Block thereBlockID, byte thereMeta, Block replaceBlockID, byte replaceMeta) {
        return thereBlockID != TFBlocks.towerDevice && replaceBlockID != TFBlocks.towerDevice ? ((thereBlockID != TFBlocks.towerTranslucent || thereMeta == 4) && (replaceBlockID != TFBlocks.towerTranslucent || replaceMeta == 4) ? (thereBlockID == Blocks.field_150379_bu && replaceBlockID == Blocks.field_150374_bv ? true : (thereBlockID == Blocks.field_150374_bv && replaceBlockID == Blocks.field_150379_bu ? true : (thereBlockID != Blocks.field_150355_j && replaceBlockID != Blocks.field_150358_i ? (thereBlockID != Blocks.field_150358_i && replaceBlockID != Blocks.field_150355_j ? replaceBlockID == Blocks.field_150335_W : true) : true))) : true) : true;
    }

    private void captureBlockData() {
        this.blockData = new Block[this.diameter * this.diameter * this.diameter];
        this.metaData = new byte[this.diameter * this.diameter * this.diameter];
        int index = 0;

        for (int x = -this.radius; x <= this.radius; ++x) {
            for (int y = -this.radius; y <= this.radius; ++y) {
                for (int z = -this.radius; z <= this.radius; ++z) {
                    Block blockID = this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                    int meta = this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);

                    this.blockData[index] = blockID;
                    this.metaData[index] = (byte) meta;
                    ++index;
                }
            }
        }

    }

    public boolean anyPlayerInRange() {
        return this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, this.requiredPlayerRange) != null;
    }
}
