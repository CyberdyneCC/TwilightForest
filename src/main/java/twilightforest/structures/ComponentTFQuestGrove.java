package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.entity.passive.EntityTFQuestRam;

public class ComponentTFQuestGrove extends StructureTFComponent {

    public static final int RADIUS = 13;
    protected boolean beastPlaced = false;
    protected boolean dispenserPlaced = false;

    public ComponentTFQuestGrove() {}

    public ComponentTFQuestGrove(World world, Random rand, int i, int x, int y, int z) {
        super(i);
        this.setCoordBaseMode(0);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -13, 0, -13, 26, 10, 26, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int bx;

        for (bx = 0; bx < 4; ++bx) {
            this.makeWallSide(world, rand, bx, sbb);
        }

        int by;

        for (bx = 10; bx < 17; ++bx) {
            for (by = 10; by < 17; ++by) {
                if (bx != 10 && bx != 16 && by != 10 && by != 16) {
                    if (bx != 11 && bx != 15 && by != 11 && by != 15) {
                        this.func_151550_a(world, Blocks.field_150417_aV, 1, bx, -1, by, sbb);
                    } else if (rand.nextInt(3) > 0) {
                        this.func_151550_a(world, Blocks.field_150417_aV, 1, bx, -1, by, sbb);
                    }
                } else if (rand.nextInt(2) > 0) {
                    this.func_151550_a(world, Blocks.field_150417_aV, 1, bx, -1, by, sbb);
                }
            }
        }

        this.func_151550_a(world, Blocks.field_150430_aB, 4, 13, 5, 19, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 12, 7, 20, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 13, 7, 20, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 14, 7, 20, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 12, 7, 21, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 13, 7, 21, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 1, 14, 7, 21, sbb);
        int bz;

        if (!this.dispenserPlaced) {
            bx = this.func_74865_a(13, 20);
            by = this.func_74862_a(6);
            bz = this.func_74873_b(13, 20);
            if (sbb.func_78890_b(bx, by, bz)) {
                this.dispenserPlaced = true;
                world.func_147465_d(bx, by, bz, Blocks.field_150367_z, 2, 4);
                TileEntityDispenser ram = (TileEntityDispenser) world.func_147438_o(bx, by, bz);

                for (int i = 0; i < 4; ++i) {
                    ram.func_70299_a(i, new ItemStack(Blocks.field_150325_L, 1, rand.nextInt(16)));
                }
            }
        }

        if (!this.beastPlaced) {
            bx = this.func_74865_a(13, 13);
            by = this.func_74862_a(0);
            bz = this.func_74873_b(13, 13);
            if (sbb.func_78890_b(bx, by, bz)) {
                this.beastPlaced = true;
                EntityTFQuestRam entitytfquestram = new EntityTFQuestRam(world);

                entitytfquestram.func_70107_b((double) bx, (double) by, (double) bz);
                entitytfquestram.func_110171_b(bx, by, bz, 13);
                world.func_72838_d(entitytfquestram);
            }
        }

        return true;
    }

    private void makeWallSide(World world, Random rand, int direction, StructureBoundingBox sbb) {
        int temp = this.getCoordBaseMode();

        this.setCoordBaseMode(direction);
        this.placeOuterArch(world, 3, -1, sbb);
        this.placeOuterArch(world, 11, -1, sbb);
        this.placeOuterArch(world, 19, -1, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 0, 0, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 0, 1, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 0, 2, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 0, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 1, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 2, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 8, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 9, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 10, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 16, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 17, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 18, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 24, 3, 0, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 25, 3, 0, sbb);

        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                for (int z = 0; z < 2; ++z) {
                    if (x == 0 || x == 1 || x == 7 || x == 8 || y == 0 || y == 1 || y == 7 || y == 8) {
                        this.func_151550_a(world, Blocks.field_150417_aV, 1, x + 9, y - 2, z + 5, sbb);
                    }
                }
            }
        }

        this.func_151550_a(world, Blocks.field_150417_aV, 3, 6, 0, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 6, 1, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 6, 2, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 6, 3, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 6, 4, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 7, 4, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 8, 4, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 18, 4, 6, sbb);
        this.func_151550_a(world, Blocks.field_150417_aV, 3, 19, 4, 6, sbb);
        this.setCoordBaseMode(temp);
    }

    private void placeOuterArch(World world, int ox, int oy, StructureBoundingBox sbb) {
        for (int x = 0; x < 5; ++x) {
            for (int y = 0; y < 6; ++y) {
                if (x == 0 || x == 4 || y == 0 || y == 5) {
                    this.func_151550_a(world, Blocks.field_150417_aV, 1, x + ox, y + oy, 0, sbb);
                }
            }
        }

    }
}
