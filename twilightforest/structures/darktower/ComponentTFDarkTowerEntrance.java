package twilightforest.structures.darktower;

import java.util.List;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFDarkTowerEntrance extends ComponentTFDarkTowerWing {

    public ComponentTFDarkTowerEntrance() {}

    protected ComponentTFDarkTowerEntrance(int i, int x, int y, int z, int pSize, int pHeight, int direction) {
        super(i, x, y, z, pSize, pHeight, direction);
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        super.func_74861_a(parent, list, rand);
        this.addOpening(this.size / 2, 1, 0, 1, EnumDarkTowerDoor.REAPPEARING);
        this.addOpening(this.size / 2, 1, this.size - 1, 3, EnumDarkTowerDoor.REAPPEARING);
    }

    public void makeABeard(StructureComponent parent, List list, Random rand) {}

    public void makeARoof(StructureComponent parent, List list, Random rand) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.makeEncasedWalls(world, rand, sbb, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);

        for (int x = 0; x < this.size; ++x) {
            for (int z = 0; z < this.size; ++z) {
                this.func_151554_b(world, this.deco.accentID, this.deco.accentMeta, x, -1, z, sbb);
            }
        }

        this.func_74878_a(world, sbb, 1, 1, 1, this.size - 2, this.height - 2, this.size - 2);
        this.nullifySkyLightForBoundingBox(world);
        this.makeOpenings(world, sbb);
        return true;
    }
}
