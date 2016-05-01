package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.TFTreasure;
import twilightforest.entity.boss.EntityTFMinoshroom;

public class ComponentTFMazeRoomBoss extends ComponentTFMazeRoom {

    private boolean taurPlaced = false;

    public ComponentTFMazeRoomBoss() {}

    public ComponentTFMazeRoomBoss(int i, Random rand, int x, int y, int z) {
        super(i, rand, x, y, z);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.func_151548_a(world, 7, 1, 0, sbb) == Blocks.field_150350_a) {
            this.func_151549_a(world, sbb, 6, 1, 0, 9, 4, 0, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        }

        if (this.func_151548_a(world, 7, 1, 15, sbb) == Blocks.field_150350_a) {
            this.func_151549_a(world, sbb, 6, 1, 15, 9, 4, 15, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        }

        if (this.func_151548_a(world, 0, 1, 7, sbb) == Blocks.field_150350_a) {
            this.func_151549_a(world, sbb, 0, 1, 6, 0, 4, 9, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        }

        if (this.func_151548_a(world, 15, 1, 7, sbb) == Blocks.field_150350_a) {
            this.func_151549_a(world, sbb, 15, 1, 6, 15, 4, 9, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
        }

        int bx;
        int by;
        int bz;

        for (bx = 1; bx < 14; ++bx) {
            for (by = 1; by < 14; ++by) {
                bz = (int) Math.round(7.0D / Math.sqrt((7.5D - (double) bx) * (7.5D - (double) bx) + (7.5D - (double) by) * (7.5D - (double) by)));
                boolean taur = rand.nextInt(bz + 1) > 0;
                boolean mushroom = rand.nextInt(bz) > 0;
                boolean mushRed = rand.nextBoolean();

                if (taur) {
                    this.func_151550_a(world, Blocks.field_150391_bh, 0, bx, 0, by, sbb);
                }

                if (mushroom) {
                    this.func_151550_a(world, mushRed ? Blocks.field_150337_Q : Blocks.field_150338_P, 0, bx, 1, by, sbb);
                }
            }
        }

        this.func_151556_a(world, sbb, 1, 1, 1, 3, 1, 3, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 2, 1, 1, 3, 4, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 2, 2, 1, 4, 3, 1, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 4, 1, 3, 4, 3, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.placeTreasureAtCurrentPosition(world, rand, 3, 2, 3, TFTreasure.labyrinth_room, sbb);
        this.func_151556_a(world, sbb, 12, 1, 12, 14, 1, 14, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 14, 2, 11, 14, 3, 14, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 11, 2, 14, 14, 3, 14, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 12, 4, 12, 14, 4, 14, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        this.placeTreasureAtCurrentPosition(world, rand, 12, 2, 12, TFTreasure.labyrinth_room, sbb);
        this.func_151556_a(world, sbb, 1, 1, 12, 3, 1, 14, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 2, 11, 1, 3, 14, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 2, 2, 14, 4, 3, 14, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 1, 4, 12, 3, 4, 14, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.placeTreasureAtCurrentPosition(world, rand, 3, 2, 12, TFTreasure.labyrinth_room, sbb);
        this.func_151556_a(world, sbb, 12, 1, 1, 14, 1, 3, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 11, 2, 1, 14, 3, 1, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 14, 2, 2, 14, 3, 4, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 12, 4, 1, 14, 4, 3, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.placeTreasureAtCurrentPosition(world, rand, 12, 2, 3, TFTreasure.labyrinth_room, sbb);
        this.func_151556_a(world, sbb, 5, 4, 5, 7, 5, 7, Blocks.field_150420_aW, 14, Blocks.field_150350_a, 0, false);
        this.func_151556_a(world, sbb, 8, 4, 8, 10, 5, 10, Blocks.field_150419_aX, 14, Blocks.field_150350_a, 0, false);
        if (!this.taurPlaced) {
            bx = this.func_74865_a(7, 7);
            by = this.func_74862_a(1);
            bz = this.func_74873_b(7, 7);
            if (sbb.func_78890_b(bx, by, bz)) {
                this.taurPlaced = true;
                EntityTFMinoshroom entitytfminoshroom = new EntityTFMinoshroom(world);

                entitytfminoshroom.func_70107_b((double) bx, (double) by, (double) bz);
                entitytfminoshroom.func_110171_b(bx, by, bz, 7);
                world.func_72838_d(entitytfminoshroom);
            }
        }

        return true;
    }
}
