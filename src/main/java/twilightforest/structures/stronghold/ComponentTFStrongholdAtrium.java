package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.world.TFGenSmallRainboak;
import twilightforest.world.TFGenSmallTwilightOak;

public class ComponentTFStrongholdAtrium extends StructureTFStrongholdComponent {

    private boolean enterBottom;

    public ComponentTFStrongholdAtrium() {}

    public ComponentTFStrongholdAtrium(int i, int facing, int x, int y, int z) {
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
        if (y > 17) {
            this.enterBottom = false;
        } else if (y < 11) {
            this.enterBottom = true;
        } else {
            this.enterBottom = (z & 1) == 0;
        }

        return this.enterBottom ? StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -4, -1, 0, 18, 14, 18, facing) : StructureTFStrongholdComponent.getComponentToAddBoundingBox(x, y, z, -13, -8, 0, 18, 14, 18, facing);
    }

    public void func_74861_a(StructureComponent parent, List list, Random random) {
        super.func_74861_a(parent, list, random);
        if (this.enterBottom) {
            this.addDoor(4, 1, 0);
            this.addNewComponent(parent, list, random, 2, 13, 8, -1);
        } else {
            this.addDoor(13, 8, 0);
            this.addNewComponent(parent, list, random, 2, 4, 1, -1);
        }

        this.addNewComponent(parent, list, random, 0, 13, 1, 18);
        this.addNewComponent(parent, list, random, 0, 4, 8, 18);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.placeStrongholdWalls(world, sbb, 0, 0, 0, 17, 13, 17, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 1, 6, 1, 16, 7, 16, false, rand, this.deco.randomBlocks);
        this.func_151549_a(world, sbb, 5, 8, 5, 12, 8, 12, this.deco.fenceID, Blocks.field_150350_a, false);
        this.func_74878_a(world, sbb, 6, 6, 6, 11, 8, 11);
        this.placeBalconyPillar(world, sbb, 0);
        this.placeBalconyPillar(world, sbb, 1);
        this.placeBalconyPillar(world, sbb, 2);
        this.placeBalconyPillar(world, sbb, 3);
        this.func_74882_a(world, sbb, 1, 1, 1, 1, 12, 2, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 2, 1, 1, 2, 12, 1, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 16, 1, 1, 16, 12, 2, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 15, 1, 1, 15, 12, 1, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 1, 1, 15, 1, 12, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 2, 1, 16, 2, 12, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 16, 1, 15, 16, 12, 16, false, rand, this.deco.randomBlocks);
        this.func_74882_a(world, sbb, 15, 1, 16, 15, 12, 16, false, rand, this.deco.randomBlocks);
        this.func_151551_a(world, sbb, rand, 0.5F, 6, 0, 6, 11, 0, 11, Blocks.field_150349_c, Blocks.field_150349_c, false);
        this.func_151549_a(world, sbb, 7, 0, 7, 10, 0, 10, Blocks.field_150349_c, Blocks.field_150350_a, false);
        this.spawnATree(world, rand.nextInt(5), 8, 1, 8, sbb);
        this.placeCornerStatue(world, 2, 8, 2, 0, sbb);
        this.placeCornerStatue(world, 2, 1, 15, 1, sbb);
        this.placeCornerStatue(world, 15, 1, 2, 2, sbb);
        this.placeCornerStatue(world, 15, 8, 15, 3, sbb);
        this.placeDoors(world, rand, sbb);
        return true;
    }

    private void spawnATree(World world, int treeNum, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz)) {
            byte minHeight = 8;
            Object treeGen;

            switch (treeNum) {
            case 0:
            default:
                treeGen = new WorldGenTrees(true, minHeight, 0, 0, false);
                break;

            case 1:
                treeGen = new WorldGenTrees(true, minHeight, 3, 3, false);
                break;

            case 2:
                treeGen = new WorldGenTrees(true, minHeight, 2, 2, false);
                break;

            case 3:
                treeGen = new TFGenSmallTwilightOak(false, minHeight);
                break;

            case 4:
                treeGen = new TFGenSmallRainboak(false);
            }

            for (int i = 0; i < 100 && !((WorldGenerator) treeGen).func_76484_a(world, world.field_73012_v, dx, dy, dz); ++i) {
                if (i == 99) {
                    ;
                }
            }
        }

    }

    private void placeBalconyPillar(World world, StructureBoundingBox sbb, int rotation) {
        this.fillBlocksRotated(world, sbb, 5, 1, 5, 5, 12, 5, this.deco.pillarID, this.deco.pillarMeta, rotation);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation), 5, 1, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation), 6, 1, 5, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, 5, 5, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 6, 5, 5, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(3 + rotation) + 4, 5, 12, 6, rotation, sbb);
        this.placeBlockRotated(world, this.deco.stairID, this.getStairMeta(2 + rotation) + 4, 6, 12, 5, rotation, sbb);
    }
}
