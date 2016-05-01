package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.entity.boss.EntityTFYetiAlpha;
import twilightforest.world.TFWorld;

public class ComponentTFYetiCave extends ComponentTFHollowHill {

    private boolean yetiPlaced;

    public ComponentTFYetiCave() {}

    public ComponentTFYetiCave(World world, Random rand, int i, int x, int y, int z) {
        super(world, rand, i, 2, x, y + 2, z);
    }

    boolean isInHill(int cx, int cz) {
        return cx < this.radius * 2 && cx > 0 && cz < this.radius * 2 && cz > 0;
    }

    boolean isInHill(int mapX, int mapY, int mapZ) {
        return mapX < this.radius * 2 && mapX > 0 && mapZ < this.radius * 2 && mapZ > 0 && mapY > TFWorld.SEALEVEL && mapY < TFWorld.SEALEVEL + 20;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        short sn = 128;

        int bx;
        int[] by;

        for (bx = 0; bx < sn; ++bx) {
            by = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150348_b, 1.0F, true, by[0], 1, by[1], sbb);
        }

        for (bx = 0; bx < sn; ++bx) {
            by = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150432_aD, 1.0F, true, by[0], 1, by[1], sbb);
        }

        for (bx = 0; bx < sn; ++bx) {
            by = this.getCoordsInHill2D(rand);
            this.generateBlockStalactite(world, Blocks.field_150403_cj, 0.9F, true, by[0], 1, by[1], sbb);
        }

        if (!this.yetiPlaced) {
            bx = this.func_74865_a(this.radius, this.radius);
            int i = this.func_74862_a(0);
            int bz = this.func_74873_b(this.radius, this.radius);

            if (sbb.func_78890_b(bx, i, bz)) {
                this.yetiPlaced = true;
                EntityTFYetiAlpha yeti = new EntityTFYetiAlpha(world);

                yeti.func_70107_b((double) bx, (double) i, (double) bz);
                yeti.func_110171_b(bx, i, bz, 30);
                world.func_72838_d(yeti);
            }
        }

        return true;
    }
}
