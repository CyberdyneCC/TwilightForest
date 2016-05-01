package twilightforest.structures;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;

public class ComponentTFNagaCourtyard extends StructureTFComponent {

    static int RADIUS = 46;
    static int DIAMETER = 2 * ComponentTFNagaCourtyard.RADIUS + 1;

    public ComponentTFNagaCourtyard() {}

    public ComponentTFNagaCourtyard(World world, Random rand, int i, int x, int y, int z) {
        super(i);
        this.setCoordBaseMode(0);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -ComponentTFNagaCourtyard.RADIUS, -1, -ComponentTFNagaCourtyard.RADIUS, ComponentTFNagaCourtyard.RADIUS * 2, 10, ComponentTFNagaCourtyard.RADIUS * 2, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        int pillarRand;
        int i;

        for (pillarRand = 0; pillarRand <= ComponentTFNagaCourtyard.DIAMETER; ++pillarRand) {
            for (i = 0; i <= ComponentTFNagaCourtyard.DIAMETER; ++i) {
                if (rand.nextInt(3) == 0) {
                    this.func_151550_a(world, Blocks.field_150334_T, 0, pillarRand, 0, i, sbb);
                    if (rand.nextInt(20) == 0) {
                        this.func_151550_a(world, Blocks.field_150333_U, 0, pillarRand, 1, i, sbb);
                    } else {
                        this.func_151550_a(world, Blocks.field_150350_a, 0, pillarRand, 1, i, sbb);
                    }
                } else {
                    this.func_151550_a(world, Blocks.field_150349_c, 0, pillarRand, 0, i, sbb);
                }
            }
        }

        for (pillarRand = 0; pillarRand <= ComponentTFNagaCourtyard.DIAMETER; ++pillarRand) {
            this.randomBrick(world, rand, pillarRand, 0, ComponentTFNagaCourtyard.DIAMETER, sbb);
            this.randomBrick(world, rand, pillarRand, 0, 0, sbb);
            this.randomBrick(world, rand, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
            this.randomBrick(world, rand, pillarRand, 1, 0, sbb);
            this.randomBrick(world, rand, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
            this.randomBrick(world, rand, pillarRand, 2, 0, sbb);
            this.randomBrick(world, rand, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
            this.randomBrick(world, rand, pillarRand, 3, 0, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, pillarRand, 4, ComponentTFNagaCourtyard.DIAMETER, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, pillarRand, 4, 0, sbb);
            switch (pillarRand % 23) {
            case 2:
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 11, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 11, pillarRand, 1, 0, sbb);
                break;

            case 3:
                this.func_151550_a(world, TFBlocks.nagastone, 3, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 3, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, 0, sbb);
                break;

            case 4:
            case 8:
            case 16:
            case 20:
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, 0, sbb);
                break;

            case 5:
            case 9:
            case 17:
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 10, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 10, pillarRand, 1, 0, sbb);
                break;

            case 6:
            case 10:
            case 14:
            case 18:
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 3, 0, sbb);
                break;

            case 7:
            case 15:
            case 19:
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 11, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 11, pillarRand, 1, 0, sbb);
                break;

            case 11:
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 1, 0, sbb);

            case 12:
            default:
                break;

            case 13:
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 7, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 1, 0, sbb);
                break;

            case 21:
                this.func_151550_a(world, TFBlocks.nagastone, 2, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 2, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 12, pillarRand, 1, 0, sbb);
                break;

            case 22:
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 6, pillarRand, 3, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, pillarRand, 2, 0, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 10, pillarRand, 1, ComponentTFNagaCourtyard.DIAMETER, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 10, pillarRand, 1, 0, sbb);
            }
        }

        for (pillarRand = 0; pillarRand <= ComponentTFNagaCourtyard.DIAMETER; ++pillarRand) {
            this.randomBrick(world, rand, ComponentTFNagaCourtyard.DIAMETER, 0, pillarRand, sbb);
            this.randomBrick(world, rand, 0, 0, pillarRand, sbb);
            this.randomBrick(world, rand, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
            this.randomBrick(world, rand, 0, 1, pillarRand, sbb);
            this.randomBrick(world, rand, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
            this.randomBrick(world, rand, 0, 2, pillarRand, sbb);
            this.randomBrick(world, rand, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
            this.randomBrick(world, rand, 0, 3, pillarRand, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, ComponentTFNagaCourtyard.DIAMETER, 4, pillarRand, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, 0, 4, pillarRand, sbb);
            switch (pillarRand % 23) {
            case 2:
                this.func_151550_a(world, TFBlocks.nagastone, 5, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 5, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 9, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 9, 0, 1, pillarRand, sbb);
                break;

            case 3:
                this.func_151550_a(world, TFBlocks.nagastone, 1, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 1, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, 0, 1, pillarRand, sbb);
                break;

            case 4:
            case 8:
            case 16:
            case 20:
                this.func_151550_a(world, TFBlocks.nagastone, 13, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, 0, 1, pillarRand, sbb);
                break;

            case 5:
            case 9:
            case 17:
                this.func_151550_a(world, TFBlocks.nagastone, 5, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 5, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 8, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 8, 0, 1, pillarRand, sbb);
                break;

            case 6:
            case 10:
            case 14:
            case 18:
                this.func_151550_a(world, TFBlocks.nagastone, 13, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, 0, 3, pillarRand, sbb);
                break;

            case 7:
            case 15:
            case 19:
                this.func_151550_a(world, TFBlocks.nagastone, 4, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 4, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 9, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 9, 0, 1, pillarRand, sbb);
                break;

            case 11:
                this.func_151550_a(world, TFBlocks.nagastone, 4, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 4, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 1, pillarRand, sbb);

            case 12:
            default:
                break;

            case 13:
                this.func_151550_a(world, TFBlocks.nagastone, 5, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 5, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 1, pillarRand, sbb);
                break;

            case 21:
                this.func_151550_a(world, TFBlocks.nagastone, 0, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 0, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 13, 0, 1, pillarRand, sbb);
                break;

            case 22:
                this.func_151550_a(world, TFBlocks.nagastone, 4, ComponentTFNagaCourtyard.DIAMETER, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 4, 0, 3, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, ComponentTFNagaCourtyard.DIAMETER, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 14, 0, 2, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 8, ComponentTFNagaCourtyard.DIAMETER, 1, pillarRand, sbb);
                this.func_151550_a(world, TFBlocks.nagastone, 8, 0, 1, pillarRand, sbb);
            }
        }

        Random random = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * this.field_74887_e.field_78896_c));

        for (i = 0; i < 20; ++i) {
            int rx = 2 + random.nextInt(ComponentTFNagaCourtyard.DIAMETER - 4);
            int rz = 2 + random.nextInt(ComponentTFNagaCourtyard.DIAMETER - 4);

            this.makePillar(world, random, rx, 1, rz, sbb);
        }

        this.func_151550_a(world, TFBlocks.bossSpawner, 0, ComponentTFNagaCourtyard.RADIUS + 1, 2, ComponentTFNagaCourtyard.RADIUS + 1, sbb);
        return true;
    }

    public boolean makePillar(World world, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        byte height = 8;

        this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 0, z - 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x + 0, y + 0, z - 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 0, z - 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 0, z + 0, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 0, z + 0, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 0, z + 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x + 0, y + 0, z + 1, sbb);
        this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 0, z + 1, sbb);

        for (int i = 0; i < height; ++i) {
            this.randomBrick(world, rand, x, y + i, z, sbb);
            if (i > 0 && rand.nextInt(2) == 0) {
                switch (rand.nextInt(4)) {
                case 0:
                    this.func_151550_a(world, Blocks.field_150395_bd, 8, x - 1, y + i, z + 0, sbb);
                    break;

                case 1:
                    this.func_151550_a(world, Blocks.field_150395_bd, 2, x + 1, y + i, z + 0, sbb);
                    break;

                case 2:
                    this.func_151550_a(world, Blocks.field_150395_bd, 4, x + 0, y + i, z + 1, sbb);
                    break;

                case 3:
                    this.func_151550_a(world, Blocks.field_150395_bd, 1, x + 0, y + i, z - 1, sbb);
                }
            } else if (i > 0 && rand.nextInt(4) == 0) {
                switch (rand.nextInt(4)) {
                case 0:
                    this.func_151550_a(world, TFBlocks.firefly, 0, x - 1, y + i, z + 0, sbb);
                    break;

                case 1:
                    this.func_151550_a(world, TFBlocks.firefly, 0, x + 1, y + i, z + 0, sbb);
                    break;

                case 2:
                    this.func_151550_a(world, TFBlocks.firefly, 0, x + 0, y + i, z + 1, sbb);
                    break;

                case 3:
                    this.func_151550_a(world, TFBlocks.firefly, 0, x + 0, y + i, z - 1, sbb);
                }
            }
        }

        if (height == 8) {
            this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 8, z - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x + 0, y + 8, z - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 8, z - 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 8, z + 0, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 5, x + 0, y + 8, z + 0, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 8, z + 0, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x - 1, y + 8, z + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x + 0, y + 8, z + 1, sbb);
            this.func_151550_a(world, Blocks.field_150333_U, 0, x + 1, y + 8, z + 1, sbb);
        }

        return true;
    }

    public void randomBrick(World world, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        this.func_151550_a(world, Blocks.field_150417_aV, rand.nextInt(3), x, y, z, sbb);
    }
}
