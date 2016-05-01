package twilightforest.structures.minotaurmaze;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFMazeDeadEndTrappedChest extends ComponentTFMazeDeadEndChest {

    public ComponentTFMazeDeadEndTrappedChest() {}

    public ComponentTFMazeDeadEndTrappedChest(int i, int x, int y, int z, int rotation) {
        super(i, x, y, z, rotation);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        super.func_74875_a(world, rand, sbb);
        this.func_151550_a(world, Blocks.field_150479_bC, this.getHookMeta(3), 1, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150479_bC, this.getHookMeta(1), 4, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150473_bD, 0, 2, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150473_bD, 0, 3, 1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150335_W, 0, 0, 0, 2, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 0, -1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 1, -1, 2, sbb);
        this.func_151550_a(world, Blocks.field_150335_W, 0, 2, 0, 4, sbb);
        this.func_151550_a(world, Blocks.field_150335_W, 0, 3, 0, 4, sbb);
        this.func_151550_a(world, Blocks.field_150335_W, 0, 2, 0, 3, sbb);
        this.func_151550_a(world, Blocks.field_150335_W, 0, 3, 0, 3, sbb);
        return true;
    }

    protected int getHookMeta(int dir) {
        return (this.getCoordBaseMode() + dir) % 4;
    }
}
