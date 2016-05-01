package twilightforest.structures.hollowtree;

import java.util.List;
import java.util.Random;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenerator;

public class ComponentTFHollowTreeLargeBranch extends ComponentTFHollowTreeMedBranch {

    private static final int LEAF_DUNGEON_CHANCE = 8;
    public boolean hasLeafDungeon = false;

    public ComponentTFHollowTreeLargeBranch() {}

    protected ComponentTFHollowTreeLargeBranch(int i, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        super(i, sx, sy, sz, length, angle, tilt, leafy);
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random rand) {
        int index = this.func_74877_c();
        int numMedBranches = rand.nextInt((int) (this.length / 6.0D)) + (int) (this.length / 8.0D);

        for (int i = 0; i <= numMedBranches; ++i) {
            double outVar = rand.nextDouble() * 0.3D + 0.3D;
            double angleVar = rand.nextDouble() * 0.225D * ((i & 1) == 0 ? 1.0D : -1.0D);
            ChunkCoordinates bsrc = TFGenerator.translateCoords(this.src.field_71574_a, this.src.field_71572_b, this.src.field_71573_c, this.length * outVar, this.angle, this.tilt);

            this.makeMedBranch(list, rand, index + 2 + i, bsrc.field_71574_a, bsrc.field_71572_b, bsrc.field_71573_c, this.length * 0.6D, this.angle + angleVar, this.tilt, this.leafy);
        }

        this.hasLeafDungeon = rand.nextInt(8) == 0;
        if (this.hasLeafDungeon) {
            this.makeLeafDungeon(list, rand, index + 1, this.dest.field_71574_a, this.dest.field_71572_b, this.dest.field_71573_c);
        }

    }

    public void makeLeafDungeon(List list, Random rand, int index, int x, int y, int z) {
        ComponentTFHollowTreeLeafDungeon dungeon = new ComponentTFHollowTreeLeafDungeon(index, x, y, z, 4);

        list.add(dungeon);
        dungeon.func_74861_a(this, list, rand);
    }

    public void makeMedBranch(List list, Random rand, int index, int x, int y, int z, double branchLength, double branchRotation, double branchAngle, boolean leafy) {
        ComponentTFHollowTreeMedBranch branch = new ComponentTFHollowTreeMedBranch(index, x, y, z, branchLength, branchRotation, branchAngle, leafy);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        ChunkCoordinates rsrc = new ChunkCoordinates(this.src.field_71574_a - this.field_74887_e.field_78897_a, this.src.field_71572_b - this.field_74887_e.field_78895_b, this.src.field_71573_c - this.field_74887_e.field_78896_c);
        ChunkCoordinates rdest = new ChunkCoordinates(this.dest.field_71574_a - this.field_74887_e.field_78897_a, this.dest.field_71572_b - this.field_74887_e.field_78895_b, this.dest.field_71573_c - this.field_74887_e.field_78896_c);

        this.drawBresehnam(world, sbb, rsrc.field_71574_a, rsrc.field_71572_b, rsrc.field_71573_c, rdest.field_71574_a, rdest.field_71572_b, rdest.field_71573_c, TFBlocks.log, 12);
        byte reinforcements = 4;

        int numSmallBranches;
        int i;

        for (int decoRNG = 0; decoRNG <= reinforcements; ++decoRNG) {
            numSmallBranches = (decoRNG & 2) == 0 ? 1 : 0;
            i = (decoRNG & 1) == 0 ? 1 : -1;
            int outVar = (decoRNG & 2) == 0 ? 0 : 1;

            this.drawBresehnam(world, sbb, rsrc.field_71574_a + numSmallBranches, rsrc.field_71572_b + i, rsrc.field_71573_c + outVar, rdest.field_71574_a, rdest.field_71572_b, rdest.field_71573_c, TFBlocks.log, 12);
        }

        Random random = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        numSmallBranches = random.nextInt(2) + 1;

        for (i = 0; i <= numSmallBranches; ++i) {
            double d0 = (double) (random.nextFloat() * 0.25F + 0.25F);
            double angleVar = (double) (random.nextFloat() * 0.25F * ((i & 1) == 0 ? 1.0F : -1.0F));
            ChunkCoordinates bsrc = TFGenerator.translateCoords(rsrc.field_71574_a, rsrc.field_71572_b, rsrc.field_71573_c, this.length * d0, this.angle, this.tilt);

            this.drawSmallBranch(world, sbb, bsrc.field_71574_a, bsrc.field_71572_b, bsrc.field_71573_c, Math.max(this.length * 0.30000001192092896D, 2.0D), this.angle + angleVar, this.tilt, this.leafy);
        }

        if (this.leafy && !this.hasLeafDungeon) {
            this.makeLeafBlob(world, sbb, rdest.field_71574_a, rdest.field_71572_b, rdest.field_71573_c, 3);
        }

        return true;
    }
}
