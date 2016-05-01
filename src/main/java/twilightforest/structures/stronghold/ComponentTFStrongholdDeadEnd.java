package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;

public class ComponentTFStrongholdDeadEnd extends StructureTFStrongholdComponent {

    private boolean chestTrapped;

    public ComponentTFStrongholdDeadEnd() {}

    public ComponentTFStrongholdDeadEnd(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("chestTrapped", this.chestTrapped);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.chestTrapped = par1NBTTagCompound.func_74767_n("chestTrapped");
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 9, 6, 9, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addDoor(4, 1, 0);
        this.chestTrapped = random.nextInt(3) == 0;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 8, 6, 8, rand, this.deco.randomBlocks);
        this.placeWallStatue(world, 1, 1, 4, 1, sbb);
        this.placeWallStatue(world, 7, 1, 4, 3, sbb);
        this.placeWallStatue(world, 4, 1, 7, 0, sbb);
        this.placeDoors(world, rand, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 4, 1, 3, TFTreasure.stronghold_cache, this.chestTrapped, sbb);
        if (this.chestTrapped) {
            this.func_151550_a(world, Blocks.field_150335_W, 0, 4, 0, 3, sbb);
        }

        for (int z = 2; z < 5; ++z) {
            this.func_151550_a(world, this.deco.stairID, this.getStairMeta(0), 3, 1, z, sbb);
            this.func_151550_a(world, this.deco.stairID, this.getStairMeta(2), 5, 1, z, sbb);
        }

        this.func_151550_a(world, this.deco.stairID, this.getStairMeta(1), 4, 1, 2, sbb);
        this.func_151550_a(world, this.deco.stairID, this.getStairMeta(3), 4, 1, 4, sbb);
        this.func_151550_a(world, this.deco.stairID, this.getStairMeta(1), 4, 2, 3, sbb);
        return true;
    }
}
