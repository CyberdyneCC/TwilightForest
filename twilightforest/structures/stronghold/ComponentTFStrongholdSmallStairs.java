package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;

public class ComponentTFStrongholdSmallStairs extends StructureTFStrongholdComponent {

    private boolean enterBottom;
    public boolean hasTreasure;
    public boolean chestTrapped;

    public ComponentTFStrongholdSmallStairs() {}

    public ComponentTFStrongholdSmallStairs(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("enterBottom", this.enterBottom);
        par1NBTTagCompound.func_74757_a("hasTreasure", this.hasTreasure);
        par1NBTTagCompound.func_74757_a("chestTrapped", this.chestTrapped);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.enterBottom = par1NBTTagCompound.func_74767_n("enterBottom");
        this.hasTreasure = par1NBTTagCompound.func_74767_n("hasTreasure");
        this.chestTrapped = par1NBTTagCompound.func_74767_n("chestTrapped");
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        if (y > 17) {
            this.enterBottom = false;
        } else if (y < 11) {
            this.enterBottom = true;
        } else {
            this.enterBottom = (z & 1) == 0;
        }

        return this.enterBottom ? StructureBoundingBox.func_78889_a(x, y, z, -4, -1, 0, 9, 14, 9, facing) : StructureBoundingBox.func_78889_a(x, y, z, -4, -8, 0, 9, 14, 9, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        if (this.enterBottom) {
            this.addDoor(4, 1, 0);
            this.addNewComponent(parent, list, random, 0, 4, 8, 9);
        } else {
            this.addDoor(4, 8, 0);
            this.addNewComponent(parent, list, random, 0, 4, 1, 9);
        }

        this.hasTreasure = random.nextBoolean();
        this.chestTrapped = random.nextInt(3) == 0;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 8, 13, 8, rand, this.deco.randomBlocks);
        this.func_151556_a(world, sbb, 1, 7, 1, 7, 7, 7, this.deco.platformID, this.deco.platformMeta, Blocks.field_150350_a, 0, false);
        this.func_74878_a(world, sbb, 2, 7, 2, 6, 7, 6);
        int rotation = this.enterBottom ? 0 : 2;

        int z;

        for (z = 1; z < 8; ++z) {
            for (int x = 3; x < 6; ++x) {
                this.placeBlockRotated(world, Blocks.field_150350_a, 0, x, z + 1, z, rotation, sbb);
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), x, z, z, rotation, sbb);
                this.placeBlockRotated(world, this.deco.blockID, this.deco.blockMeta, x, z - 1, z, rotation, sbb);
            }
        }

        if (this.hasTreasure) {
            this.placeTreasureRotated(world, 4, 1, 6, rotation, TFTreasure.stronghold_cache, this.chestTrapped, sbb);
            if (this.chestTrapped) {
                this.placeBlockRotated(world, Blocks.field_150335_W, 0, 4, 0, 6, rotation, sbb);
            }

            for (z = 5; z < 8; ++z) {
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(0 + rotation), 3, 1, z, rotation, sbb);
                this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 5, 1, z, rotation, sbb);
            }

            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), 4, 1, 5, rotation, sbb);
            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 4, 1, 7, rotation, sbb);
            this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(1 + rotation), 4, 2, 6, rotation, sbb);
        }

        if (this.enterBottom) {
            this.placeWallStatue(world, 4, 8, 1, 2, sbb);
        } else {
            this.placeWallStatue(world, 4, 8, 7, 0, sbb);
        }

        this.placeDoors(world, rand, sbb);
        return true;
    }
}
