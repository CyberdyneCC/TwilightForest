package twilightforest.structures.hollowtree;

import java.util.List;
import java.util.Random;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;

public class ComponentTFHollowTreeSmallBranch extends ComponentTFHollowTreeMedBranch {

    public ComponentTFHollowTreeSmallBranch() {}

    protected ComponentTFHollowTreeSmallBranch(int i, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        super(i, sx, sy, sz, length, angle, tilt, leafy);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random rand) {}

    public boolean func_74875_a(World world, Random random, StructureBoundingBox sbb) {
        ChunkCoordinates rSrc = new ChunkCoordinates(this.src.field_71574_a - this.field_74887_e.field_78897_a, this.src.field_71572_b - this.field_74887_e.field_78895_b, this.src.field_71573_c - this.field_74887_e.field_78896_c);
        ChunkCoordinates rDest = new ChunkCoordinates(this.dest.field_71574_a - this.field_74887_e.field_78897_a, this.dest.field_71572_b - this.field_74887_e.field_78895_b, this.dest.field_71573_c - this.field_74887_e.field_78896_c);

        this.drawBresehnam(world, sbb, rSrc.field_71574_a, rSrc.field_71572_b, rSrc.field_71573_c, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, TFBlocks.log, 12);
        if (this.leafy) {
            int leafRad = random.nextInt(2) + 1;

            this.makeLeafBlob(world, sbb, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, leafRad);
        }

        return true;
    }
}
