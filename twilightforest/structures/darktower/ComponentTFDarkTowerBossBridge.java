package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;

public class ComponentTFDarkTowerBossBridge extends ComponentTFDarkTowerBridge {

    public ComponentTFDarkTowerBossBridge() {}

    protected ComponentTFDarkTowerBossBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, wingSize, direction);
        ComponentTFDarkTowerBossTrap wing = new ComponentTFDarkTowerBossTrap(index, dx[0], dx[1], dx[2], wingSize, wingHeight, direction);

        list.add(wing);
        wing.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }
}
