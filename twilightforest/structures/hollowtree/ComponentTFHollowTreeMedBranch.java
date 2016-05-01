package twilightforest.structures.hollowtree;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;
import twilightforest.world.TFGenerator;

public class ComponentTFHollowTreeMedBranch extends StructureTFComponent {

    ChunkCoordinates src;
    ChunkCoordinates dest;
    double length;
    double angle;
    double tilt;
    boolean leafy;

    public ComponentTFHollowTreeMedBranch() {}

    protected ComponentTFHollowTreeMedBranch(int i, int sx, int sy, int sz, double length, double angle, double tilt, boolean leafy) {
        super(i);
        this.src = new ChunkCoordinates(sx, sy, sz);
        this.dest = TFGenerator.translateCoords(this.src.field_71574_a, this.src.field_71572_b, this.src.field_71573_c, length, angle, tilt);
        this.length = length;
        this.angle = angle;
        this.tilt = tilt;
        this.leafy = leafy;
        this.setCoordBaseMode(0);
        this.field_74887_e = new StructureBoundingBox(Math.min(this.src.field_71574_a, this.dest.field_71574_a), Math.min(this.src.field_71572_b, this.dest.field_71572_b), Math.min(this.src.field_71573_c, this.dest.field_71573_c), Math.max(this.src.field_71574_a, this.dest.field_71574_a), Math.max(this.src.field_71572_b, this.dest.field_71572_b), Math.max(this.src.field_71573_c, this.dest.field_71573_c));
        this.field_74887_e.func_78888_b(this.makeExpandedBB(0.5D, length, angle, tilt));
        this.field_74887_e.func_78888_b(this.makeExpandedBB(0.10000000149011612D, length, 0.225D, tilt));
        this.field_74887_e.func_78888_b(this.makeExpandedBB(0.10000000149011612D, length, -0.225D, tilt));
    }

    private StructureBoundingBox makeExpandedBB(double outVar, double branchLength, double branchAngle, double branchTilt) {
        ChunkCoordinates branchSrc = TFGenerator.translateCoords(this.src.field_71574_a, this.src.field_71572_b, this.src.field_71573_c, this.length * outVar, this.angle, this.tilt);
        ChunkCoordinates branchDest = TFGenerator.translateCoords(branchSrc.field_71574_a, branchSrc.field_71572_b, branchSrc.field_71573_c, branchLength, branchAngle, branchTilt);

        return new StructureBoundingBox(Math.min(branchSrc.field_71574_a, branchDest.field_71574_a), Math.min(branchSrc.field_71572_b, branchDest.field_71572_b), Math.min(branchSrc.field_71573_c, branchDest.field_71573_c), Math.max(branchSrc.field_71574_a, branchDest.field_71574_a), Math.max(branchSrc.field_71572_b, branchDest.field_71572_b), Math.max(branchSrc.field_71573_c, branchDest.field_71573_c));
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("srcPosX", this.src.field_71574_a);
        par1NBTTagCompound.func_74768_a("srcPosY", this.src.field_71572_b);
        par1NBTTagCompound.func_74768_a("srcPosZ", this.src.field_71573_c);
        par1NBTTagCompound.func_74768_a("destPosX", this.dest.field_71574_a);
        par1NBTTagCompound.func_74768_a("destPosY", this.dest.field_71572_b);
        par1NBTTagCompound.func_74768_a("destPosZ", this.dest.field_71573_c);
        par1NBTTagCompound.func_74780_a("branchLength", this.length);
        par1NBTTagCompound.func_74780_a("branchAngle", this.angle);
        par1NBTTagCompound.func_74780_a("branchTilt", this.tilt);
        par1NBTTagCompound.func_74757_a("branchLeafy", this.leafy);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.src = new ChunkCoordinates(par1NBTTagCompound.func_74762_e("srcPosX"), par1NBTTagCompound.func_74762_e("srcPosY"), par1NBTTagCompound.func_74762_e("srcPosZ"));
        this.dest = new ChunkCoordinates(par1NBTTagCompound.func_74762_e("destPosX"), par1NBTTagCompound.func_74762_e("destPosY"), par1NBTTagCompound.func_74762_e("destPosZ"));
        this.length = par1NBTTagCompound.func_74769_h("branchLength");
        this.angle = par1NBTTagCompound.func_74769_h("branchAngle");
        this.tilt = par1NBTTagCompound.func_74769_h("branchTilt");
        this.leafy = par1NBTTagCompound.func_74767_n("branchLeafy");
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random rand) {
        int index = this.func_74877_c();
    }

    public void makeSmallBranch(List list, Random rand, int index, int x, int y, int z, double branchLength, double branchRotation, double branchAngle, boolean leafy) {
        ComponentTFHollowTreeSmallBranch branch = new ComponentTFHollowTreeSmallBranch(index, x, y, z, branchLength, branchRotation, branchAngle, leafy);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    public boolean func_74875_a(World world, Random random, StructureBoundingBox sbb) {
        ChunkCoordinates rSrc = new ChunkCoordinates(this.src.field_71574_a - this.field_74887_e.field_78897_a, this.src.field_71572_b - this.field_74887_e.field_78895_b, this.src.field_71573_c - this.field_74887_e.field_78896_c);
        ChunkCoordinates rDest = new ChunkCoordinates(this.dest.field_71574_a - this.field_74887_e.field_78897_a, this.dest.field_71572_b - this.field_74887_e.field_78895_b, this.dest.field_71573_c - this.field_74887_e.field_78896_c);

        this.drawBresehnam(world, sbb, rSrc.field_71574_a, rSrc.field_71572_b, rSrc.field_71573_c, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, TFBlocks.log, 12);
        this.drawBresehnam(world, sbb, rSrc.field_71574_a, rSrc.field_71572_b + 1, rSrc.field_71573_c, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, TFBlocks.log, 12);
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));
        int numShoots = Math.min(decoRNG.nextInt(3) + 1, (int) (this.length / 5.0D));
        double angleInc = 0.8D / (double) numShoots;

        int numLeafBalls;
        double slength;

        for (numLeafBalls = 0; numLeafBalls < numShoots; ++numLeafBalls) {
            double angleVar = angleInc * (double) numLeafBalls - 0.4D;
            double outVar = decoRNG.nextDouble() * 0.8D + 0.2D;
            double tiltVar = decoRNG.nextDouble() * 0.75D + 0.15D;
            ChunkCoordinates i = TFGenerator.translateCoords(rSrc.field_71574_a, rSrc.field_71572_b, rSrc.field_71573_c, this.length * outVar, this.angle, this.tilt);

            slength = this.length * 0.4D;
            this.drawSmallBranch(world, sbb, i.field_71574_a, i.field_71572_b, i.field_71573_c, Math.max(this.length * 0.30000001192092896D, 2.0D), this.angle + angleVar, this.tilt, this.leafy);
        }

        if (this.leafy) {
            numLeafBalls = Math.min(decoRNG.nextInt(3) + 1, (int) (this.length / 5.0D));

            for (int i = 0; i < numLeafBalls; ++i) {
                slength = (double) (decoRNG.nextFloat() * 0.6F + 0.2F) * this.length;
                ChunkCoordinates bdst = TFGenerator.translateCoords(rSrc.field_71574_a, rSrc.field_71572_b, rSrc.field_71573_c, slength, this.angle, this.tilt);

                this.makeLeafBlob(world, sbb, bdst.field_71574_a, bdst.field_71572_b, bdst.field_71573_c, decoRNG.nextBoolean() ? 2 : 3);
            }

            this.makeLeafBlob(world, sbb, rDest.field_71574_a, rDest.field_71572_b, rDest.field_71573_c, 3);
        }

        return true;
    }

    protected void drawBresehnam(World world, StructureBoundingBox sbb, int x1, int y1, int z1, int x2, int y2, int z2, Block blockValue, int metaValue) {
        ChunkCoordinates[] lineCoords = TFGenerator.getBresehnamArrayCoords(x1, y1, z1, x2, y2, z2);
        ChunkCoordinates[] achunkcoordinates = lineCoords;
        int i = lineCoords.length;

        for (int j = 0; j < i; ++j) {
            ChunkCoordinates coords = achunkcoordinates[j];

            this.func_151550_a(world, blockValue, metaValue, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, sbb);
        }

    }

    protected void makeLeafBlob(World world, StructureBoundingBox sbb, int sx, int sy, int sz, int radius) {
        for (int dx = 0; dx <= radius; ++dx) {
            for (int dy = 0; dy <= radius; ++dy) {
                for (int dz = 0; dz <= radius; ++dz) {
                    boolean dist = false;
                    int i;

                    if (dx >= dy && dx >= dz) {
                        i = (int) ((float) dx + (float) Math.max(dy, dz) * 0.5F + (float) Math.min(dy, dz) * 0.25F);
                    } else if (dy >= dx && dy >= dz) {
                        i = (int) ((float) dy + (float) Math.max(dx, dz) * 0.5F + (float) Math.min(dx, dz) * 0.25F);
                    } else {
                        i = (int) ((float) dz + (float) Math.max(dx, dy) * 0.5F + (float) Math.min(dx, dy) * 0.25F);
                    }

                    if (i <= radius) {
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz + dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz - dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz + dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz - dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz + dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz - dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz + dz, sbb);
                        this.placeLeafBlock(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz - dz, sbb);
                    }
                }
            }
        }

    }

    protected void placeLeafBlock(World world, Block blockID, int meta, int x, int y, int z, StructureBoundingBox sbb) {
        int offX = this.func_74865_a(x, z);
        int offY = this.func_74862_a(y);
        int offZ = this.func_74873_b(x, z);

        if (sbb.func_78890_b(offX, offY, offZ)) {
            Block whatsThere = world.func_147439_a(offX, offY, offZ);

            if (whatsThere == null || whatsThere.canBeReplacedByLeaves(world, offX, offY, offZ)) {
                world.func_147465_d(offX, offY, offZ, blockID, meta, 2);
            }
        }

    }

    protected void drawSmallBranch(World world, StructureBoundingBox sbb, int sx, int sy, int sz, double branchLength, double branchAngle, double branchTilt, boolean leafy) {
        ChunkCoordinates branchDest = TFGenerator.translateCoords(sx, sy, sz, branchLength, branchAngle, branchTilt);

        this.drawBresehnam(world, sbb, sx, sy, sz, branchDest.field_71574_a, branchDest.field_71572_b, branchDest.field_71573_c, TFBlocks.log, 12);
        this.makeLeafBlob(world, sbb, branchDest.field_71574_a, branchDest.field_71572_b, branchDest.field_71573_c, 2);
    }
}
