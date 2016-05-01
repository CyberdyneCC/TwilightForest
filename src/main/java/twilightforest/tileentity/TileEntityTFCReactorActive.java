package twilightforest.tileentity;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFMiniGhast;

public class TileEntityTFCReactorActive extends TileEntity {

    int counter = 0;
    int secX;
    int secY;
    int secZ;
    int terX;
    int terY;
    int terZ;

    public TileEntityTFCReactorActive() {
        Random rand = new Random();

        this.secX = 3 * (rand.nextBoolean() ? 1 : -1);
        this.secY = 3 * (rand.nextBoolean() ? 1 : -1);
        this.secZ = 3 * (rand.nextBoolean() ? 1 : -1);
        this.terX = 3 * (rand.nextBoolean() ? 1 : -1);
        this.terY = 3 * (rand.nextBoolean() ? 1 : -1);
        this.terZ = 3 * (rand.nextBoolean() ? 1 : -1);
        if (this.secX == this.terX && this.secY == this.terY && this.secZ == this.terZ) {
            this.terX = -this.terX;
            this.terY = -this.terY;
            this.terZ = -this.terZ;
        }

    }

    public void func_145845_h() {
        ++this.counter;
        if (!this.field_145850_b.field_72995_K) {
            byte offset = 10;
            int i;

            if (this.counter % 5 == 0) {
                if (this.counter == 5) {
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d + 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d + 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e + 0, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e + 0, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 0, this.field_145849_e + 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 0, this.field_145849_e - 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 0, this.field_145849_e + 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 0, this.field_145849_e - 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d + 0, this.field_145849_e + 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d + 0, this.field_145849_e - 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d + 0, this.field_145849_e + 0, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d + 0, this.field_145849_e + 0, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 7, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d - 1, this.field_145849_e + 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 0, this.field_145848_d - 1, this.field_145849_e - 1, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d - 1, this.field_145849_e + 0, TFBlocks.towerTranslucent, 6, 2);
                    this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d - 1, this.field_145849_e + 0, TFBlocks.towerTranslucent, 6, 2);
                }

                i = this.counter - 80;
                if (i >= offset && i <= 249) {
                    this.drawBlob(this.field_145851_c, this.field_145848_d, this.field_145849_e, (i - offset) / 40, Blocks.field_150350_a, 0, i - offset, false);
                }

                if (i <= 200) {
                    this.drawBlob(this.field_145851_c, this.field_145848_d, this.field_145849_e, i / 40, TFBlocks.towerTranslucent, 5, this.counter, false);
                }

                int secondary = this.counter - 120;

                if (secondary >= offset && secondary <= 129) {
                    this.drawBlob(this.field_145851_c + this.secX, this.field_145848_d + this.secY, this.field_145849_e + this.secZ, (secondary - offset) / 40, Blocks.field_150350_a, 0, secondary - offset, false);
                }

                if (secondary >= 0 && secondary <= 160) {
                    this.drawBlob(this.field_145851_c + this.secX, this.field_145848_d + this.secY, this.field_145849_e + this.secZ, secondary / 40, Blocks.field_150350_a, 0, secondary, true);
                }

                int tertiary = this.counter - 160;

                if (tertiary >= offset && tertiary <= 129) {
                    this.drawBlob(this.field_145851_c + this.terX, this.field_145848_d + this.terY, this.field_145849_e + this.terZ, (tertiary - offset) / 40, Blocks.field_150350_a, 0, tertiary - offset, false);
                }

                if (tertiary >= 0 && tertiary <= 160) {
                    this.drawBlob(this.field_145851_c + this.terX, this.field_145848_d + this.terY, this.field_145849_e + this.terZ, tertiary / 40, Blocks.field_150350_a, 0, tertiary, true);
                }
            }

            if (this.counter >= 350) {
                for (i = 0; i < 3; ++i) {
                    this.spawnGhastNear(this.field_145851_c + this.secX, this.field_145848_d + this.secY, this.field_145849_e + this.secZ);
                    this.spawnGhastNear(this.field_145851_c + this.terX, this.field_145848_d + this.terY, this.field_145849_e + this.terZ);
                }

                this.field_145850_b.func_72876_a((Entity) null, (double) this.field_145851_c, (double) this.field_145848_d, (double) this.field_145849_e, 2.0F, true);
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a, 0, 3);
            }
        } else if (this.counter % 5 == 0 && this.counter <= 250) {
            this.field_145850_b.func_72980_b((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, "portal.portal", (float) this.counter / 100.0F, (float) this.counter / 100.0F, false);
        }

    }

    private void spawnGhastNear(int x, int y, int z) {
        EntityTFMiniGhast ghast = new EntityTFMiniGhast(this.field_145850_b);

        ghast.func_70012_b((double) x - 1.5D + (double) this.field_145850_b.field_73012_v.nextFloat() * 3.0D, (double) y - 1.5D + (double) this.field_145850_b.field_73012_v.nextFloat() * 3.0D, (double) z - 1.5D + (double) this.field_145850_b.field_73012_v.nextFloat() * 3.0D, this.field_145850_b.field_73012_v.nextFloat() * 360.0F, 0.0F);
        this.field_145850_b.func_72838_d(ghast);
    }

    public void drawBlob(int sx, int sy, int sz, int rad, Block blockValue, int metaValue, int fuzz, boolean netherTransform) {
        for (byte dx = 0; dx <= rad; ++dx) {
            int fuzzX = (fuzz + dx) % 8;

            for (byte dy = 0; dy <= rad; ++dy) {
                int fuzzY = (fuzz + dy) % 8;

                for (byte dz = 0; dz <= rad; ++dz) {
                    boolean dist = false;
                    byte b0;

                    if (dx >= dy && dx >= dz) {
                        b0 = (byte) (dx + (byte) ((int) ((double) Math.max(dy, dz) * 0.5D + (double) Math.min(dy, dz) * 0.25D)));
                    } else if (dy >= dx && dy >= dz) {
                        b0 = (byte) (dy + (byte) ((int) ((double) Math.max(dx, dz) * 0.5D + (double) Math.min(dx, dz) * 0.25D)));
                    } else {
                        b0 = (byte) (dz + (byte) ((int) ((double) Math.max(dx, dy) * 0.5D + (double) Math.min(dx, dy) * 0.25D)));
                    }

                    if (b0 == rad && (dx != 0 || dy != 0 || dz != 0)) {
                        switch (fuzzX) {
                        case 0:
                            this.transformBlock(sx + dx, sy + dy, sz + dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 1:
                            this.transformBlock(sx + dx, sy + dy, sz - dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 2:
                            this.transformBlock(sx - dx, sy + dy, sz + dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 3:
                            this.transformBlock(sx - dx, sy + dy, sz - dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 4:
                            this.transformBlock(sx + dx, sy - dy, sz + dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 5:
                            this.transformBlock(sx + dx, sy - dy, sz - dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 6:
                            this.transformBlock(sx - dx, sy - dy, sz + dz, blockValue, metaValue, fuzzY, netherTransform);
                            break;

                        case 7:
                            this.transformBlock(sx - dx, sy - dy, sz - dz, blockValue, metaValue, fuzzY, netherTransform);
                        }
                    }
                }
            }
        }

    }

    protected void transformBlock(int x, int y, int z, Block blockValue, int metaValue, int fuzz, boolean netherTransform) {
        Block whatsThere = this.field_145850_b.func_147439_a(x, y, z);
        int whatsMeta = this.field_145850_b.func_72805_g(x, y, z);

        if (whatsThere == Blocks.field_150350_a || whatsThere.func_149712_f(this.field_145850_b, x, y, z) != -1.0F) {
            if (fuzz == 0 && whatsThere != Blocks.field_150350_a) {
                this.field_145850_b.func_72926_e(2001, x, y, z, Block.func_149682_b(whatsThere) + (whatsMeta << 12));
            }

            if (netherTransform && whatsThere != Blocks.field_150350_a) {
                this.field_145850_b.func_147465_d(x, y, z, Blocks.field_150424_aL, 0, 3);
                if (this.field_145850_b.func_147439_a(x, y + 1, z) == Blocks.field_150350_a && fuzz % 3 == 0) {
                    this.field_145850_b.func_147465_d(x, y + 1, z, Blocks.field_150480_ab, 0, 3);
                }
            } else {
                this.field_145850_b.func_147465_d(x, y, z, blockValue, metaValue, 3);
            }

        }
    }
}
