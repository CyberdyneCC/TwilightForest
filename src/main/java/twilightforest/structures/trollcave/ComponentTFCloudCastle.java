package twilightforest.structures.trollcave;

import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.block.TFBlocks;
import twilightforest.entity.EntityTFArmoredGiant;
import twilightforest.entity.EntityTFGiantMiner;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFCloudCastle extends StructureTFComponent {

    private boolean minerPlaced = false;
    private boolean warriorPlaced = false;

    public ComponentTFCloudCastle() {}

    public ComponentTFCloudCastle(int index, int x, int y, int z) {
        super(index);
        this.setCoordBaseMode(0);
        x = x >> 2 << 2;
        y = y >> 2 << 2;
        z = z >> 2 << 2;
        this.spawnListIndex = 1;
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -8, 0, -8, 16, 16, 16, 0);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("minerPlaced", this.minerPlaced);
        par1NBTTagCompound.func_74757_a("warriorPlaced", this.warriorPlaced);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.minerPlaced = par1NBTTagCompound.func_74767_n("minerPlaced");
        this.warriorPlaced = par1NBTTagCompound.func_74767_n("warriorPlaced");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        boolean plus = rand.nextBoolean();
        int offset = rand.nextInt(5) - rand.nextInt(5);
        ComponentTFCloudTree treeX = new ComponentTFCloudTree(this.func_74877_c() + 1, this.field_74887_e.field_78897_a + (plus ? 16 : -16), 168, this.field_74887_e.field_78896_c - 8 + offset * 4);

        list.add(treeX);
        treeX.func_74861_a(this, list, rand);
        plus = rand.nextBoolean();
        offset = rand.nextInt(5) - rand.nextInt(5);
        ComponentTFCloudTree treeZ = new ComponentTFCloudTree(this.func_74877_c() + 1, this.field_74887_e.field_78897_a - 8 + offset * 4, 168, this.field_74887_e.field_78896_c + (plus ? 16 : -16));

        list.add(treeZ);
        treeZ.func_74861_a(this, list, rand);
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.func_151556_a(world, sbb, 0, -4, 0, 15, -1, 15, TFBlocks.fluffyCloud, 0, TFBlocks.fluffyCloud, 0, false);
        this.func_151556_a(world, sbb, 0, 0, 0, 15, 11, 15, TFBlocks.giantCobble, 0, TFBlocks.giantCobble, 0, false);
        this.func_151556_a(world, sbb, 0, 12, 0, 15, 15, 15, TFBlocks.giantLog, 0, TFBlocks.giantLog, 0, false);
        this.func_74878_a(world, sbb, 4, 0, 4, 11, 11, 11);
        this.func_74878_a(world, sbb, 0, 0, 4, 4, 7, 7);
        int bx;
        int by;
        int bz;

        if (!this.minerPlaced) {
            bx = this.func_74865_a(6, 6);
            by = this.func_74862_a(0);
            bz = this.func_74873_b(6, 6);
            if (sbb.func_78890_b(bx, by, bz)) {
                this.minerPlaced = true;
                EntityTFGiantMiner warrior = new EntityTFGiantMiner(world);

                warrior.func_70107_b((double) bx, (double) by, (double) bz);
                warrior.makeNonDespawning();
                world.func_72838_d(warrior);
            }
        }

        if (!this.warriorPlaced) {
            bx = this.func_74865_a(9, 9);
            by = this.func_74862_a(0);
            bz = this.func_74873_b(9, 9);
            if (sbb.func_78890_b(bx, by, bz)) {
                this.warriorPlaced = true;
                EntityTFArmoredGiant warrior1 = new EntityTFArmoredGiant(world);

                warrior1.func_70107_b((double) bx, (double) by, (double) bz);
                warrior1.makeNonDespawning();
                world.func_72838_d(warrior1);
            }
        }

        return true;
    }
}
