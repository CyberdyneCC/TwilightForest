package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.entity.TFCreatures;

public class ComponentTFStrongholdTreasureRoom extends StructureTFStrongholdComponent {

    private boolean enterBottom;

    public ComponentTFStrongholdTreasureRoom() {}

    public ComponentTFStrongholdTreasureRoom(int i, int facing, int x, int y, int z) {
        super(i, facing, x, y, z);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("enterBottom", this.enterBottom);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.enterBottom = par1NBTTagCompound.func_74767_n("enterBottom");
    }

    public StructureBoundingBox generateBoundingBox(int facing, int x, int y, int z) {
        return StructureBoundingBox.func_78889_a(x, y, z, -4, -1, 0, 9, 7, 18, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        this.addDoor(4, 1, 0);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 8, 6, 17, rand, this.deco.randomBlocks);
        this.placeWallStatue(world, 1, 1, 4, 1, sbb);
        this.placeWallStatue(world, 1, 1, 13, 1, sbb);
        this.placeWallStatue(world, 7, 1, 4, 3, sbb);
        this.placeWallStatue(world, 7, 1, 13, 3, sbb);
        this.placeWallStatue(world, 4, 1, 16, 0, sbb);
        this.func_74882_a(world, sbb, 1, 1, 8, 7, 5, 9, false, rand, this.deco.randomBlocks);
        this.func_151549_a(world, sbb, 3, 1, 8, 5, 4, 9, Blocks.field_150411_aY, Blocks.field_150411_aY, false);
        this.placeSpawnerAtCurrentPosition(world, rand, 4, 1, 4, TFCreatures.getSpawnerNameFor("Helmet Crab"), sbb);
        this.placeSpawnerAtCurrentPosition(world, rand, 4, 4, 15, TFCreatures.getSpawnerNameFor("Helmet Crab"), sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 2, 4, 13, TFTreasure.stronghold_room, sbb);
        this.placeTreasureAtCurrentPosition(world, rand, 6, 4, 13, TFTreasure.stronghold_room, sbb);
        this.placeDoors(world, rand, sbb);
        return true;
    }

    protected void placeDoorwayAt(World world, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        if (x != 0 && x != this.getXSize()) {
            this.func_151556_a(world, sbb, x - 1, y, z, x + 1, y + 3, z, Blocks.field_150411_aY, 0, Blocks.field_150350_a, 0, false);
        } else {
            this.func_151556_a(world, sbb, x, y, z - 1, x, y + 3, z + 1, Blocks.field_150411_aY, 0, Blocks.field_150350_a, 0, false);
        }

    }
}
