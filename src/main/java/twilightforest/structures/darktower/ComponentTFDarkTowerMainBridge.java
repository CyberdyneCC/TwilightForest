package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;

public class ComponentTFDarkTowerMainBridge extends ComponentTFDarkTowerBridge {

    public ComponentTFDarkTowerMainBridge() {}

    protected ComponentTFDarkTowerMainBridge(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, 15, pHeight, direction);
    }

    public boolean makeTowerWing(List list, Random rand, int index, int x, int y, int z, int wingSize, int wingHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        int[] dx = this.offsetTowerCoords(x, y, z, 19, direction);
        ComponentTFDarkTowerMain wing = new ComponentTFDarkTowerMain((World) null, rand, index, dx[0], dx[1], dx[2], direction);

        list.add(wing);
        wing.func_74861_a(this, list, rand);
        this.addOpening(x, y, z, rotation);
        return true;
    }
}
