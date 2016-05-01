package twilightforest.structures.trollcave;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.TFTreasure;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;
import twilightforest.world.TFGenCaveStalactite;
import twilightforest.world.TFGenMyceliumBlob;

public class ComponentTFTrollCaveMain extends StructureTFComponent {

    protected int size;
    protected int height;
    public static final TFGenMyceliumBlob uberousGen = new TFGenMyceliumBlob(TFBlocks.uberousSoil, 4);

    public ComponentTFTrollCaveMain() {}

    public ComponentTFTrollCaveMain(int index) {
        super(index);
    }

    public ComponentTFTrollCaveMain(World world, Random rand, int i, int x, int y, int z) {
        this.setCoordBaseMode(0);
        y += 10;
        this.size = 30;
        this.height = 20;
        int radius = this.size / 2;

        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, -radius, -this.height, -radius, this.size, this.height, this.size, 0);
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("size", this.size);
        par1NBTTagCompound.func_74768_a("height", this.height);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.size = par1NBTTagCompound.func_74762_e("size");
        this.height = par1NBTTagCompound.func_74762_e("height");
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {
        for (int castle = 0; castle < 4; ++castle) {
            ChunkCoordinates vault = this.getValidOpening(rand, 5, castle);

            this.makeSmallerCave(list, rand, this.func_74877_c() + 1, vault.field_71574_a, vault.field_71572_b, vault.field_71573_c, 18, 15, castle);
        }

        ComponentTFCloudCastle componenttfcloudcastle = new ComponentTFCloudCastle(this.func_74877_c() + 1, this.field_74887_e.field_78897_a + (this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a) / 2, 168, this.field_74887_e.field_78896_c + (this.field_74887_e.field_78892_f - this.field_74887_e.field_78896_c) / 2);

        list.add(componenttfcloudcastle);
        componenttfcloudcastle.func_74861_a(this, list, rand);
        ComponentTFTrollVault componenttftrollvault = new ComponentTFTrollVault(this.func_74877_c() + 1, this.field_74887_e.field_78897_a + (this.field_74887_e.field_78893_d - this.field_74887_e.field_78897_a) / 2, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + (this.field_74887_e.field_78892_f - this.field_74887_e.field_78896_c) / 2);

        list.add(componenttftrollvault);
        componenttftrollvault.func_74861_a(this, list, rand);
    }

    protected boolean makeSmallerCave(List list, Random rand, int index, int x, int y, int z, int caveSize, int caveHeight, int rotation) {
        int direction = (this.getCoordBaseMode() + rotation) % 4;
        ChunkCoordinates dest = this.offsetTowerCCoords(x, y, z, caveSize, direction);
        ComponentTFTrollCaveConnect cave = new ComponentTFTrollCaveConnect(index, dest.field_71574_a, dest.field_71572_b, dest.field_71573_c, caveSize, caveHeight, direction);
        StructureComponent intersect = StructureComponent.func_74883_a(list, cave.func_74874_b());

        if (intersect != null && intersect != this) {
            return false;
        } else {
            list.add(cave);
            cave.func_74861_a((StructureComponent) list.get(0), list, rand);
            return true;
        }
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

        this.hollowCaveMiddle(world, sbb, rand, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);

        int i;
        ChunkCoordinates dest;

        for (i = 0; i < 128; ++i) {
            dest = this.getCoordsInCave(decoRNG);
            this.generateBlockStalactite(world, decoRNG, Blocks.field_150348_b, 0.7F, true, dest.field_71574_a, 3, dest.field_71573_c, sbb);
        }

        for (i = 0; i < 32; ++i) {
            dest = this.getCoordsInCave(decoRNG);
            this.generateBlockStalactite(world, decoRNG, Blocks.field_150348_b, 0.5F, false, dest.field_71574_a, 3, dest.field_71573_c, sbb);
        }

        for (i = 0; i < 32; ++i) {
            dest = this.getCoordsInCave(decoRNG);
            this.generateAtSurface(world, ComponentTFTrollCaveMain.uberousGen, decoRNG, dest.field_71574_a, 60, dest.field_71573_c, sbb);
        }

        return true;
    }

    protected ChunkCoordinates getCoordsInCave(Random rand) {
        return new ChunkCoordinates(rand.nextInt(this.size - 1), rand.nextInt(this.height - 1), rand.nextInt(this.size - 1));
    }

    protected void hollowCaveMiddle(World par1World, StructureBoundingBox par2StructureBoundingBox, Random rand, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        int threshold = this.size / 5;

        for (int y = minY; y <= maxY; ++y) {
            for (int x = minX; x <= maxX; ++x) {
                for (int z = minZ; z <= maxZ; ++z) {
                    int ex = Math.min(x - minX, maxX - x);
                    int ey = Math.min((y - minY) * 2, maxY - y);
                    int ez = Math.min(z - minZ, maxZ - z);
                    double dist = Math.sqrt((double) (ex * ey * ez));

                    if (dist > (double) threshold) {
                        this.func_151550_a(par1World, Blocks.field_150350_a, 0, x, y, z, par2StructureBoundingBox);
                    } else if (dist == (double) threshold && rand.nextInt(4) == 0 && this.func_151548_a(par1World, x, y, z, par2StructureBoundingBox) == Blocks.field_150348_b) {
                        this.func_151550_a(par1World, TFBlocks.trollSteinn, 0, x, y, z, par2StructureBoundingBox);
                    }
                }
            }
        }

    }

    public ChunkCoordinates getValidOpening(Random rand, int caveHeight, int direction) {
        int offset = this.size / 4;
        int wLength = this.size - offset * 2;
        int rx;
        int rz;
        int ry;

        if (direction != 0 && direction != 2) {
            if (direction != 1 && direction != 3) {
                return null;
            } else {
                rx = offset + rand.nextInt(wLength);
                rz = direction == 1 ? this.size - 1 : 0;
                ry = rand.nextInt(offset) - rand.nextInt(offset);
                return new ChunkCoordinates(rx, ry, rz);
            }
        } else {
            rx = direction == 0 ? this.size - 1 : 0;
            rz = offset + rand.nextInt(wLength);
            ry = rand.nextInt(offset) - rand.nextInt(offset);
            return new ChunkCoordinates(rx, ry, rz);
        }
    }

    protected ChunkCoordinates offsetTowerCCoords(int x, int y, int z, int towerSize, int direction) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        return direction == 0 ? new ChunkCoordinates(dx - 1, dy - 1, dz - towerSize / 2) : (direction == 1 ? new ChunkCoordinates(dx + towerSize / 2, dy - 1, dz - 1) : (direction == 2 ? new ChunkCoordinates(dx + 1, dy - 1, dz + towerSize / 2) : (direction == 3 ? new ChunkCoordinates(dx - towerSize / 2, dy - 1, dz + 1) : new ChunkCoordinates(x, y, z))));
    }

    public boolean isBoundingBoxOutOfHighlands(World world, StructureBoundingBox sbb) {
        int minX = this.field_74887_e.field_78897_a - 1;
        int minZ = this.field_74887_e.field_78896_c - 1;
        int maxX = this.field_74887_e.field_78893_d + 1;
        int maxZ = this.field_74887_e.field_78892_f + 1;

        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                if (world.func_72807_a(x, z) != TFBiomeBase.highlands) {
                    return true;
                }
            }
        }

        return false;
    }

    protected void generateBlockStalactite(World world, Random rand, Block blockToGenerate, float length, boolean up, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz)) {
            (new TFGenCaveStalactite(blockToGenerate, length, up)).func_76484_a(world, rand, dx, dy, dz);
        }

    }

    protected void generateAtSurface(World world, WorldGenerator generator, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, y, dz)) {
            int dy;

            for (dy = y; dy < y + 32 && !world.func_147437_c(dx, dy, dz); ++dy) {
                ;
            }

            generator.func_76484_a(world, rand, dx, dy, dz);
        }

    }

    protected void makeTreasureCrate(World world, Random rand, StructureBoundingBox sbb) {
        int mid = this.size / 2;

        this.func_151549_a(world, sbb, mid - 2, 0, mid - 2, mid + 1, 3, mid + 1, Blocks.field_150343_Z, Blocks.field_150343_Z, false);
        this.func_74878_a(world, sbb, mid - 1, 1, mid - 1, mid + 0, 2, mid + 0);
        this.placeTreasureAtCurrentPosition(world, rand, mid, 1, mid, TFTreasure.troll_garden, false, sbb);
    }
}
