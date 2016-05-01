package twilightforest.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import twilightforest.entity.passive.EntityTFRaven;

public class TFGenMonolith extends TFGenerator {

    public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
        int ht = rand.nextInt(10) + 10;
        int dir = rand.nextInt(4);

        if (!this.isAreaClear(world, rand, x, y, z, 2, ht, 2)) {
            return false;
        } else {
            int h0;
            int h1;
            int h2;
            int h3;

            switch (dir) {
            case 0:
                h0 = ht;
                h1 = (int) ((double) ht * 0.75D);
                h2 = (int) ((double) ht * 0.75D);
                h3 = (int) ((double) ht * 0.5D);
                break;

            case 1:
                h0 = (int) ((double) ht * 0.5D);
                h1 = ht;
                h2 = (int) ((double) ht * 0.75D);
                h3 = (int) ((double) ht * 0.75D);
                break;

            case 2:
                h0 = (int) ((double) ht * 0.75D);
                h1 = (int) ((double) ht * 0.5D);
                h2 = ht;
                h3 = (int) ((double) ht * 0.75D);
                break;

            case 3:
            default:
                h0 = (int) ((double) ht * 0.75D);
                h1 = (int) ((double) ht * 0.75D);
                h2 = (int) ((double) ht * 0.5D);
                h3 = ht;
            }

            int i;

            for (i = 0; i <= h0; ++i) {
                this.setBlock(world, x + 0, y + i - 1, z + 0, i == ht ? Blocks.field_150368_y : Blocks.field_150343_Z);
            }

            for (i = 0; i <= h1; ++i) {
                this.setBlock(world, x + 1, y + i - 1, z + 0, i == ht ? Blocks.field_150368_y : Blocks.field_150343_Z);
            }

            for (i = 0; i <= h2; ++i) {
                this.setBlock(world, x + 0, y + i - 1, z + 1, i == ht ? Blocks.field_150368_y : Blocks.field_150343_Z);
            }

            for (i = 0; i <= h3; ++i) {
                this.setBlock(world, x + 1, y + i - 1, z + 1, i == ht ? Blocks.field_150368_y : Blocks.field_150343_Z);
            }

            for (i = 0; i < 2; ++i) {
                int dx = x + rand.nextInt(8) - rand.nextInt(8);
                int dz = z + rand.nextInt(8) - rand.nextInt(8);
                int dy = world.func_72825_h(dx, dz);
                EntityTFRaven raven = new EntityTFRaven(world);

                raven.func_70012_b((double) dx, (double) dy, (double) dz, rand.nextFloat() * 360.0F, 0.0F);
                world.func_72838_d(raven);
            }

            return true;
        }
    }
}
