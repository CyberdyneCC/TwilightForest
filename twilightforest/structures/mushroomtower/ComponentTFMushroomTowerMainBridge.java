package twilightforest.structures.mushroomtower;

import java.util.List;
import java.util.Random;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFMushroomTowerMainBridge extends ComponentTFMushroomTowerBridge {

    public ComponentTFMushroomTowerMainBridge() {}

    protected ComponentTFMushroomTowerMainBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, 11, pHeight, direction);
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 15, direction);

        dx = this.adjustCoordinates(dx[0], dx[1], dx[2], 15, direction, list);
        ComponentTFMushroomTowerMain wing = new ComponentTFMushroomTowerMain(index, dx[0], dx[1], dx[2], 15, wingHeight, direction);

        list.add(wing);
        wing.func_74861_a((StructureComponent) list.get(0), list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }
}
