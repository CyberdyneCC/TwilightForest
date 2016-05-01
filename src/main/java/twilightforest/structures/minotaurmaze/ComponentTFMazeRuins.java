package twilightforest.structures.minotaurmaze;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFMazeRuins extends StructureTFComponent {

    public ComponentTFMazeRuins() {}

    public ComponentTFMazeRuins(World world, Random rand, int i, int x, int y, int z) {
        super(i);
        this.setCoordBaseMode(0);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 0, 0, 0, 0);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random random) {
        super.func_74861_a(structurecomponent, list, random);
        ComponentTFMinotaurMaze maze = new ComponentTFMinotaurMaze(1, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 14, this.field_74887_e.field_78896_c, 1);

        list.add(maze);
        maze.func_74861_a(this, list, random);
        ComponentTFMazeEntranceShaft mazeEnter = new ComponentTFMazeEntranceShaft(2, random, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1);

        list.add(mazeEnter);
        mazeEnter.func_74861_a(this, list, random);
        ComponentTFMazeMound mazeAbove = new ComponentTFMazeMound(2, random, this.field_74887_e.field_78897_a - 14, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 14);

        list.add(mazeAbove);
        mazeAbove.func_74861_a(this, list, random);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        return true;
    }
}
