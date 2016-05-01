package twilightforest.structures.hollowtree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenerator;

public class ComponentTFHollowTreeRoot extends ComponentTFHollowTreeMedBranch {

    private int groundLevel = -1;

    public ComponentTFHollowTreeRoot() {}

    public ComponentTFHollowTreeRoot(int i, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        super(i, sx, sy, sz, length, angle, tilt, leafy);
        this.field_74887_e = new StructureBoundingBox(Math.min(this.src.field_71574_a, this.dest.field_71574_a), Math.min(this.src.field_71572_b, this.dest.field_71572_b), Math.min(this.src.field_71573_c, this.dest.field_71573_c), Math.max(this.src.field_71574_a, this.dest.field_71574_a), Math.max(this.src.field_71572_b, this.dest.field_71572_b), Math.max(this.src.field_71573_c, this.dest.field_71573_c));
    }

    public boolean func_74875_a(World world, Random random, StructureBoundingBox sbb) {
        if (this.groundLevel < 0) {
            int i = this.field_74887_e.field_78894_e - this.field_74887_e.field_78895_b;

            this.groundLevel = this.getSampledDirtLevel(world, sbb);
            if (this.groundLevel < 0) {
                return true;
            }

            this.src.field_71572_b = this.groundLevel + 5;
        }

        ChunkCoordinates rSrc = new ChunkCoordinates(this.src.field_71574_a - this.field_74887_e.field_78897_a, this.src.field_71572_b - this.field_74887_e.field_78895_b, this.src.field_71573_c - this.field_74887_e.field_78896_c);
        ChunkCoordinates rDest = new ChunkCoordinates(this.dest.field_71574_a - this.field_74887_e.field_78897_a, this.dest.field_71572_b - this.field_74887_e.field_78895_b, this.dest.field_71573_c - this.field_74887_e.field_78896_c);

        this.drawRootLine(world, sbb, rSrc.field_71574_a, rSrc.field_71572_b, rSrc.field_71573_c, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, TFBlocks.root, 0);
        this.drawRootLine(world, sbb, rSrc.field_71574_a, rSrc.field_71572_b - 1, rSrc.field_71573_c, rDest.field_71574_a, rDest.field_71572_b - 1, rDest.field_71573_c, TFBlocks.root, 0);
        return true;
    }

    protected void drawRootLine(World world, StructureBoundingBox sbb, int x1, int y1, int z1, int x2, int y2, int z2, Block blockValue, int metaValue) {
        ChunkCoordinates[] lineCoords = TFGenerator.getBresehnamArrayCoords(x1, y1, z1, x2, y2, z2);
        ChunkCoordinates[] achunkcoordinates = lineCoords;
        int i = lineCoords.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordinates coords = achunkcoordinates[j];
            Block block = this.func_151548_a(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, sbb);

            if (block.isNormalCube(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) && (block == null || block.func_149688_o() != Material.field_151577_b)) {
                if (block == null || block.func_149688_o() != Material.field_151575_d) {
                    this.func_151550_a(world, blockValue, metaValue, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, sbb);
                }
            } else {
                this.func_151550_a(world, TFBlocks.log, 12, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, sbb);
            }
        }

    }
}
