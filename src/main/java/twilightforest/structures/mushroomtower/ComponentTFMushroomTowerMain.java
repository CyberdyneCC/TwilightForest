package twilightforest.structures.mushroomtower;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMushroomTowerMain extends ComponentTFMushroomTowerWing {

    public ComponentTFMushroomTowerMain() {}

    public ComponentTFMushroomTowerMain(World world, Random rand, int index, int x, int y, int z) {
        this(world, rand, index, x + 15, y + 4, z + 15, 2);
    }

    public ComponentTFMushroomTowerMain(World world, Random rand, int index, int x, int y, int z, int rotation) {
        super(index, x, y, z, 15, 8 + rand.nextInt(3) * 4, rotation);
        if (this.deco == null) {
            this.deco = new StructureDecoratorMushroomTower();
        }

    }

    protected ComponentTFMushroomTowerMain(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        if (parent != null && parent instanceof StructureTFComponent) {
            this.deco = ((StructureTFComponent) parent).deco;
        }

        this.addOpening(0, 1, this.size / 2, 2);
        this.hasBase = true;
        int mainDir = -1;

        if (this.func_74877_c() < 3) {
            int i;

            for (i = 0; i < 6; ++i) {
                mainDir = this.makeAscenderTower(list, rand);
                if (mainDir != -1) {
                    break;
                }
            }

            for (i = 0; i < 4; ++i) {
                if (i != mainDir) {
                    int[] dest = this.getValidOpening(rand, i);
                    int childHeight = (rand.nextInt(2) + rand.nextInt(2) + 2) * 4 + 1;

                    this.makeBridge(list, rand, this.func_74877_c() + 1, dest[0], dest[1], dest[2], this.size - 4, childHeight, i);
                }
            }
        } else {
            this.makeARoof(parent, list, rand);
        }

    }

    private int makeAscenderTower(List list, Random rand) {
        int mainDir = rand.nextInt(4);
        int[] dest = this.getValidOpening(rand, mainDir);
        int childHeight = this.height - dest[1] + (rand.nextInt(2) + rand.nextInt(2) + 3) * 4 + 1;
        boolean madeIt = this.makeBridge(list, rand, this.func_74877_c() + 1, dest[0], dest[1], dest[2], this.size - 4, childHeight, mainDir, true);

        if (madeIt) {
            System.out.println("Main tower made a bridge to another tower");
            return mainDir;
        } else {
            System.out.println("Main tower failed to branch off at index " + this.field_74886_g);
            return -1;
        }
    }

    public void makeARoof(StructureComponent parent, List list, Random rand) {
        ComponentTFTowerRoofMushroom roof = new ComponentTFTowerRoofMushroom(this.func_74877_c() + 1, this, 1.6F);

        list.add(roof);
        roof.func_74861_a(this, list, rand);
    }

    protected void makeDoorOpening(World world, int dx, int dy, int dz, StructureBoundingBox sbb) {
        super.makeDoorOpening(world, dx, dy, dz, sbb);
        if (dx == 0) {
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx + 1, dy + 0, dz, sbb);
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx + 1, dy + 1, dz, sbb);
        }

        if (dx == this.size - 1) {
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx - 1, dy + 0, dz, sbb);
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx - 1, dy + 1, dz, sbb);
        }

        if (dz == 0) {
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 0, dz + 1, sbb);
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 1, dz + 1, sbb);
        }

        if (dz == this.size - 1) {
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 0, dz - 1, sbb);
            this.func_151550_a(world, Blocks.field_150350_a, 0, dx, dy + 1, dz - 1, sbb);
        }

    }
}
