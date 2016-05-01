package twilightforest.structures.hollowtree;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;
import twilightforest.world.TFGenerator;

public class ComponentTFHollowTreeTrunk extends StructureTFComponent {

    int radius;
    int height;
    int groundLevel = -1;

    public ComponentTFHollowTreeTrunk() {}

    public ComponentTFHollowTreeTrunk(World world, Random rand, int index, int x, int y, int z) {
        super(index);
        this.height = rand.nextInt(64) + 32;
        this.radius = rand.nextInt(4) + 1;
        this.setCoordBaseMode(0);
        this.field_74887_e = new StructureBoundingBox(x, y, z, x + this.radius * 2 + 2, y + this.height, z + this.radius * 2 + 2);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("trunkRadius", this.radius);
        par1NBTTagCompound.func_74768_a("trunkHeight", this.height);
        par1NBTTagCompound.func_74768_a("trunkGroundLevel", this.groundLevel);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.radius = par1NBTTagCompound.func_74762_e("trunkRadius");
        this.height = par1NBTTagCompound.func_74762_e("trunkHeight");
        this.groundLevel = par1NBTTagCompound.func_74762_e("trunkGroundLevel");
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random rand) {
        int index = this.func_74877_c();
        int numBranches = rand.nextInt(3) + 3;

        for (int i = 0; i <= numBranches; ++i) {
            int branchHeight = (int) ((double) this.height * rand.nextDouble() * 0.9D) + this.height / 10;
            double branchRotation = rand.nextDouble();

            this.makeSmallBranch(list, rand, index + i + 1, branchHeight, 4, branchRotation, 0.35D, true);
        }

        this.buildFullCrown(list, rand, index + numBranches + 1);
        this.buildBranchRing(list, rand, index, 3, 2, 6, 0, 0.75D, 0.0D, 3, 5, 3, false);
        this.buildBranchRing(list, rand, index, 1, 2, 8, 0, 0.9D, 0.0D, 3, 5, 3, false);
    }

    protected void buildFullCrown(List list, Random rand, int index) {
        int crownRadius = this.radius * 4 + 4;
        int bvar = this.radius + 2;

        index += this.buildBranchRing(list, rand, index, this.height - crownRadius, 0, crownRadius, 0, 0.35D, 0.0D, bvar, bvar + 2, 2, true);
        index += this.buildBranchRing(list, rand, index, this.height - crownRadius / 2, 0, crownRadius, 0, 0.28D, 0.0D, bvar, bvar + 2, 1, true);
        index += this.buildBranchRing(list, rand, index, this.height, 0, crownRadius, 0, 0.15D, 0.0D, 2, 4, 2, true);
        int i = index + this.buildBranchRing(list, rand, index, this.height, 0, crownRadius / 2, 0, 0.05D, 0.0D, bvar, bvar + 2, 1, true);
    }

    protected int buildBranchRing(List list, Random rand, int index, int branchHeight, int heightVar, int length, int lengthVar, double tilt, double tiltVar, int minBranches, int maxBranches, int size, boolean leafy) {
        int numBranches = rand.nextInt(maxBranches - minBranches + 1) + minBranches;
        double branchRotation = 1.0D / (double) numBranches;
        double branchOffset = rand.nextDouble();

        for (int i = 0; i <= numBranches; ++i) {
            int dHeight;

            if (heightVar > 0) {
                dHeight = branchHeight - heightVar + rand.nextInt(2 * heightVar);
            } else {
                dHeight = branchHeight;
            }

            if (size == 2) {
                this.makeLargeBranch(list, rand, index, dHeight, length, (double) i * branchRotation + branchOffset, tilt, leafy);
            } else if (size == 1) {
                this.makeMedBranch(list, rand, index, dHeight, length, (double) i * branchRotation + branchOffset, tilt, leafy);
            } else if (size == 3) {
                this.makeRoot(list, rand, index, dHeight, length, (double) i * branchRotation + branchOffset, tilt);
            } else {
                this.makeSmallBranch(list, rand, index, dHeight, length, (double) i * branchRotation + branchOffset, tilt, leafy);
            }
        }

        return numBranches;
    }

    public void makeSmallBranch(List list, Random rand, int index, int branchHeight, int branchLength, double branchRotation, double branchAngle, boolean leafy) {
        ChunkCoordinates bSrc = this.getBranchSrc(branchHeight, branchRotation);
        ComponentTFHollowTreeSmallBranch branch = new ComponentTFHollowTreeSmallBranch(index, bSrc.field_71574_a, bSrc.field_71572_b, bSrc.field_71573_c, (double) branchLength, branchRotation, branchAngle, leafy);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    public void makeMedBranch(List list, Random rand, int index, int branchHeight, int branchLength, double branchRotation, double branchAngle, boolean leafy) {
        ChunkCoordinates bSrc = this.getBranchSrc(branchHeight, branchRotation);
        ComponentTFHollowTreeMedBranch branch = new ComponentTFHollowTreeMedBranch(index, bSrc.field_71574_a, bSrc.field_71572_b, bSrc.field_71573_c, (double) branchLength, branchRotation, branchAngle, leafy);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    public void makeLargeBranch(List list, Random rand, int index, int branchHeight, int branchLength, double branchRotation, double branchAngle, boolean leafy) {
        ChunkCoordinates bSrc = this.getBranchSrc(branchHeight, branchRotation);
        ComponentTFHollowTreeLargeBranch branch = new ComponentTFHollowTreeLargeBranch(index, bSrc.field_71574_a, bSrc.field_71572_b, bSrc.field_71573_c, (double) branchLength, branchRotation, branchAngle, leafy);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    public void makeRoot(List list, Random rand, int index, int branchHeight, int branchLength, double branchRotation, double branchAngle) {
        ChunkCoordinates bSrc = this.getBranchSrc(branchHeight, branchRotation);
        ComponentTFHollowTreeRoot branch = new ComponentTFHollowTreeRoot(index, bSrc.field_71574_a, bSrc.field_71572_b, bSrc.field_71573_c, (double) branchLength, branchRotation, branchAngle, false);

        list.add(branch);
        branch.func_74861_a(this, list, rand);
    }

    private ChunkCoordinates getBranchSrc(int branchHeight, double branchRotation) {
        return TFGenerator.translateCoords(this.field_74887_e.field_78897_a + this.radius + 1, this.field_74887_e.field_78895_b + branchHeight, this.field_74887_e.field_78896_c + this.radius + 1, (double) this.radius, branchRotation, 0.5D);
    }

    public boolean func_74875_a(World world, Random random, StructureBoundingBox sbb) {
        if (this.groundLevel < 0) {
            this.groundLevel = this.getAverageGroundLevel(world, sbb);
            if (this.groundLevel < 0) {
                return true;
            }

            this.height = this.field_74887_e.field_78894_e - this.groundLevel;
            this.field_74887_e.field_78895_b = this.groundLevel;
        }

        int hollow = this.radius / 2;

        int numInsects;
        int i;
        int fHeight;

        for (numInsects = 0; numInsects <= 2 * this.radius; ++numInsects) {
            for (i = 0; i <= 2 * this.radius; ++i) {
                fHeight = Math.abs(numInsects - this.radius);
                int fAngle = Math.abs(i - this.radius);
                int dist = (int) ((double) Math.max(fHeight, fAngle) + (double) Math.min(fHeight, fAngle) * 0.5D);

                for (int dy = 0; dy <= this.height; ++dy) {
                    if (dist <= this.radius && dist > hollow) {
                        this.func_151550_a(world, TFBlocks.log, 0, numInsects + 1, dy, i + 1, sbb);
                    }
                }

                if (dist <= this.radius) {
                    this.func_151554_b(world, TFBlocks.log, 0, numInsects + 1, -1, i + 1, sbb);
                }

                if (dist == hollow && numInsects == hollow + this.radius) {
                    this.func_151554_b(world, Blocks.field_150395_bd, 8, numInsects + 1, this.height, i + 1, sbb);
                }
            }
        }

        numInsects = random.nextInt(3 * this.radius) + random.nextInt(3 * this.radius) + 10;

        for (i = 0; i <= numInsects; ++i) {
            fHeight = (int) ((double) this.height * random.nextDouble() * 0.9D) + this.height / 10;
            double d0 = random.nextDouble();

            this.addInsect(world, fHeight, d0, random, sbb);
        }

        return true;
    }

    protected void addInsect(World world, int fHeight, double fAngle, Random random, StructureBoundingBox sbb) {
        ChunkCoordinates bugSpot = TFGenerator.translateCoords(this.radius + 1, fHeight, this.radius + 1, (double) (this.radius + 1), fAngle, 0.5D);

        fAngle %= 1.0D;
        byte insectMeta = 0;

        if (fAngle <= 0.875D && fAngle > 0.125D) {
            if (fAngle > 0.125D && fAngle <= 0.375D) {
                insectMeta = 1;
            } else if (fAngle > 0.375D && fAngle <= 0.625D) {
                insectMeta = 4;
            } else if (fAngle > 0.625D && fAngle <= 0.875D) {
                insectMeta = 2;
            }
        } else {
            insectMeta = 3;
        }

        this.addInsect(world, random.nextBoolean() ? TFBlocks.firefly : TFBlocks.cicada, insectMeta, bugSpot.field_71574_a, bugSpot.field_71572_b, bugSpot.field_71573_c, sbb);
    }

    private void addInsect(World world, Block blockID, int insectMeta, int posX, int posY, int posZ, StructureBoundingBox sbb) {
        int ox = this.func_74865_a(posX, posZ);
        int oy = this.func_74862_a(posY);
        int oz = this.func_74873_b(posX, posZ);

        if (sbb.func_78890_b(ox, oy, oz) && blockID != null && blockID.func_149742_c(world, ox, oy, oz)) {
            world.func_147465_d(ox, oy, oz, blockID, insectMeta, 2);
        }

    }
}
