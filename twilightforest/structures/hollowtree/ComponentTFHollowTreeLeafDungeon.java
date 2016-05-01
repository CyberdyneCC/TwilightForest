package twilightforest.structures.hollowtree;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.block.TFBlocks;
import twilightforest.entity.TFCreatures;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFHollowTreeLeafDungeon extends StructureTFComponent {

    int radius;

    public ComponentTFHollowTreeLeafDungeon() {}

    protected ComponentTFHollowTreeLeafDungeon(int index, int x, int y, int z, int radius) {
        super(index);
        this.setCoordBaseMode(0);
        this.field_74887_e = new StructureBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        this.radius = radius;
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("leafRadius", this.radius);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.radius = par1NBTTagCompound.func_74762_e("leafRadius");
    }

    public void func_74861_a(StructureComponent structurecomponent, List list, Random rand) {
        this.setCoordBaseMode(rand.nextInt(4));
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        this.drawBlockBlob(world, sbb, this.radius, this.radius, this.radius, 4, TFBlocks.leaves, 0, true);
        this.drawBlockBlob(world, sbb, this.radius, this.radius, this.radius, 3, TFBlocks.log, 12, false);
        this.drawBlockBlob(world, sbb, this.radius, this.radius, this.radius, 2, Blocks.field_150350_a, 0, false);
        this.placeTreasureAtCurrentPosition(world, rand, this.radius + 2, this.radius - 1, this.radius, TFTreasure.tree_cache, sbb);
        this.placeSpawnerAtCurrentPosition(world, rand, this.radius, this.radius, this.radius, TFCreatures.getSpawnerNameFor("Swarm Spider"), sbb);
        return true;
    }

    private void drawBlockBlob(World world, StructureBoundingBox sbb, int sx, int sy, int sz, int blobRadius, Block blockID, int metadata, boolean isLeaves) {
        for (byte dx = 0; dx <= blobRadius; ++dx) {
            for (byte dy = 0; dy <= blobRadius; ++dy) {
                for (byte dz = 0; dz <= blobRadius; ++dz) {
                    boolean dist = false;
                    byte b0;

                    if (dx >= dy && dx >= dz) {
                        b0 = (byte) (dx + (byte) ((int) ((double) Math.max(dy, dz) * 0.5D + (double) Math.min(dy, dz) * 0.25D)));
                    } else if (dy >= dx && dy >= dz) {
                        b0 = (byte) (dy + (byte) ((int) ((double) Math.max(dx, dz) * 0.5D + (double) Math.min(dx, dz) * 0.25D)));
                    } else {
                        b0 = (byte) (dz + (byte) ((int) ((double) Math.max(dx, dy) * 0.5D + (double) Math.min(dx, dy) * 0.25D)));
                    }

                    if (b0 <= blobRadius) {
                        if (isLeaves) {
                            this.placeLeafBlock(world, blockID, metadata, sx + dx, sy + dy, sz + dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx + dx, sy + dy, sz - dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx - dx, sy + dy, sz + dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx - dx, sy + dy, sz - dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx + dx, sy - dy, sz + dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx + dx, sy - dy, sz - dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx - dx, sy - dy, sz + dz, sbb);
                            this.placeLeafBlock(world, blockID, metadata, sx - dx, sy - dy, sz - dz, sbb);
                        } else {
                            this.func_151550_a(world, blockID, metadata, sx + dx, sy + dy, sz + dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx + dx, sy + dy, sz - dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx - dx, sy + dy, sz + dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx - dx, sy + dy, sz - dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx + dx, sy - dy, sz + dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx + dx, sy - dy, sz - dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx - dx, sy - dy, sz + dz, sbb);
                            this.func_151550_a(world, blockID, metadata, sx - dx, sy - dy, sz - dz, sbb);
                        }
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
}
