package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdFoundry extends StructureTFStrongholdComponent {

    int entranceLevel;

    public ComponentTFStrongholdFoundry() {}

    public ComponentTFStrongholdFoundry(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("entranceLevel", this.entranceLevel);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.entranceLevel = par1NBTTagCompound.func_74762_e("entranceLevel");
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        if (y > 17) {
            this.entranceLevel = 3;
            return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -20, 0, 18, 25, 18, facing);
        } else if (y < 11) {
            this.entranceLevel = 1;
            return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -6, 0, 18, 25, 18, facing);
        } else {
            this.entranceLevel = 2;
            return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -13, 0, 18, 25, 18, facing);
        }
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        switch (this.entranceLevel) {
        case 1:
            this.addDoor(4, 6, 0);
            this.addNewComponent(parent, list, random, 1, -1, 13, 13);
            this.addNewComponent(parent, list, random, 3, 18, 13, 4);
            this.addNewComponent(parent, list, random, 0, 13, 20, 18);
            break;

        case 2:
            this.addDoor(4, 13, 0);
            this.addNewComponent(parent, list, random, 1, -1, 6, 13);
            this.addNewComponent(parent, list, random, 3, 18, 20, 4);
            this.addNewComponent(parent, list, random, 0, 13, 13, 18);
            break;

        case 3:
            this.addDoor(4, 20, 0);
            this.addNewComponent(parent, list, random, 0, 13, 6, 18);
            this.addNewComponent(parent, list, random, 1, -1, 13, 13);
            this.addNewComponent(parent, list, random, 3, 18, 13, 4);
        }

    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 17, 25, 17, rand, this.deco.randomBlocks);
        this.func_151549_a(world, sbb, 1, 0, 1, 16, 4, 16, Blocks.field_150353_l, Blocks.field_150353_l, false);
        this.func_74882_a(world, sbb, 1, 19, 1, 16, 19, 16, false, rand, this.deco.randomBlocks);
        this.func_74878_a(world, sbb, 2, 19, 2, 15, 19, 15);
        this.func_74882_a(world, sbb, 1, 12, 1, 16, 12, 16, false, rand, this.deco.randomBlocks);
        this.func_74878_a(world, sbb, 2, 12, 2, 15, 12, 15);
        this.func_74882_a(world, sbb, 1, 5, 1, 16, 5, 16, false, rand, this.deco.randomBlocks);
        this.func_74878_a(world, sbb, 2, 5, 2, 15, 5, 15);
        this.func_74882_a(world, sbb, 1, 1, 1, 1, 24, 2, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 2, 1, 1, 2, 24, 1, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 16, 1, 1, 16, 24, 2, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 15, 1, 1, 15, 24, 1, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 1, 1, 15, 1, 24, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 2, 1, 16, 2, 24, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 16, 1, 15, 16, 24, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 15, 1, 16, 15, 24, 16, false, rand, this.deco.randomBlocks);
        Random massRandom = new Random(rand.nextLong());

        int i;
        int blockID;
        int blockMeta;

        for (i = 4; i < 14; ++i) {
            for (blockID = 4; blockID < 14; ++blockID) {
                for (blockMeta = 8; blockMeta < 23; ++blockMeta) {
                    float dy = Math.abs((float) i - 8.5F) + Math.abs((float) blockID - 8.5F) + Math.abs((float) blockMeta - 18.0F);
                    float y = 5.5F + (massRandom.nextFloat() - massRandom.nextFloat()) * 3.5F;

                    if (dy < y) {
                        this.func_151550_a(world, Blocks.field_150348_b, 0, i, blockMeta, blockID, sbb);
                    }
                }
            }
        }

        for (i = 0; i < 400; ++i) {
            blockID = massRandom.nextInt(9) + 5;
            blockMeta = massRandom.nextInt(9) + 5;
            int i = massRandom.nextInt(13) + 10;

            if (this.func_151548_a(world, blockID, i, blockMeta, sbb) != Blocks.field_150350_a) {
                for (int j = 0; j < 3; ++j) {
                    this.func_151550_a(world, Blocks.field_150348_b, 0, blockID, i - j, blockMeta, sbb);
                }
            }
        }

        Block block;
        byte b0;

        for (i = 0; i < 8; ++i) {
            block = Blocks.field_150450_ax;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        for (i = 0; i < 8; ++i) {
            block = Blocks.field_150366_p;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        for (i = 0; i < 6; ++i) {
            block = Blocks.field_150352_o;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        for (i = 0; i < 2; ++i) {
            block = Blocks.field_150426_aN;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        for (i = 0; i < 2; ++i) {
            block = Blocks.field_150412_bA;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        for (i = 0; i < 4; ++i) {
            block = Blocks.field_150482_ag;
            b0 = 0;
            this.addOreToMass(world, sbb, massRandom, block, b0);
        }

        this.placeDoors(world, rand, sbb);
        return true;
    }

    private void addOreToMass(World world, StructureBoundingBox sbb, Random massRandom, Block blockID, int blockMeta) {
        for (int i = 0; i < 10; ++i) {
            int dx = massRandom.nextInt(9) + 5;
            int dz = massRandom.nextInt(9) + 5;
            int dy = massRandom.nextInt(13) + 10;

            if (this.func_151548_a(world, dx, dy, dz, sbb) != Blocks.field_150350_a) {
                this.func_151550_a(world, blockID, blockMeta, dx, dy, dz, sbb);
                break;
            }
        }

    }
}
