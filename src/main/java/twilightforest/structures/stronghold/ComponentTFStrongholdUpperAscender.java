package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdUpperAscender extends StructureTFStrongholdComponent {

    boolean exitTop;

    public ComponentTFStrongholdUpperAscender() {}

    public ComponentTFStrongholdUpperAscender(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("exitTop", this.exitTop);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.exitTop = par1NBTTagCompound.func_74767_n("exitTop");
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        if (y < 36) {
            this.exitTop = true;
            return StructureBoundingBox.func_78889_a(x, y, z, -2, -1, 0, 5, 10, 10, facing);
        } else {
            this.exitTop = false;
            return StructureBoundingBox.func_78889_a(x, y, z, -2, -6, 0, 5, 10, 10, facing);
        }
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addNewUpperComponent(parent, list, random, 0, 2, this.exitTop ? 6 : 1, 10);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.func_74860_a(world, sbb)) {
            return false;
        } else {
            this.placeUpperStrongholdWalls(world, sbb, 0, 0, 0, 4, 9, 9, rand, this.deco.randomBlocks);
            this.placeSmallDoorwayAt(world, rand, 2, 2, this.exitTop ? 1 : 6, 0, sbb);
            this.placeSmallDoorwayAt(world, rand, 0, 2, this.exitTop ? 6 : 1, 9, sbb);
            if (this.exitTop) {
                this.makeStairsAt(world, 1, 3, 1, sbb);
                this.makeStairsAt(world, 2, 4, 1, sbb);
                this.makeStairsAt(world, 3, 5, 1, sbb);
                this.makeStairsAt(world, 4, 6, 1, sbb);
                this.makeStairsAt(world, 5, 7, 1, sbb);
                this.makePlatformAt(world, 5, 8, sbb);
            } else {
                this.makeStairsAt(world, 1, 6, 3, sbb);
                this.makeStairsAt(world, 2, 5, 3, sbb);
                this.makeStairsAt(world, 3, 4, 3, sbb);
                this.makeStairsAt(world, 4, 3, 3, sbb);
                this.makeStairsAt(world, 5, 2, 3, sbb);
                this.makePlatformAt(world, 5, 1, sbb);
            }

            return true;
        }
    }

    private void makeStairsAt(World world, int y, int z, int facing, StructureBoundingBox sbb) {
        if (this.func_151548_a(world, 0, y, z, sbb) != Blocks.field_150350_a || this.func_151548_a(world, 4, y, z, sbb) != Blocks.field_150350_a) {
            for (int x = 1; x < 4; ++x) {
                this.func_151550_a(world, Blocks.field_150390_bg, this.getStairMeta(facing), x, y, z, sbb);
            }
        }

    }

    private void makePlatformAt(World world, int y, int z, StructureBoundingBox sbb) {
        if (this.func_151548_a(world, 0, y, z, sbb) != Blocks.field_150350_a || this.func_151548_a(world, 4, y, z, sbb) != Blocks.field_150350_a) {
            for (int x = 1; x < 4; ++x) {
                this.func_151550_a(world, Blocks.field_150417_aV, 0, x, y, z, sbb);
            }
        }

    }

    public boolean isComponentProtected() {
        return false;
    }
}
