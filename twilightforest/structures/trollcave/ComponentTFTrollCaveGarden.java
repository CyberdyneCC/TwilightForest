package twilightforest.structures.trollcave;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.world.TFGenBigMushgloom;
import twilightforest.world.TFGenMyceliumBlob;

public class ComponentTFTrollCaveGarden extends ComponentTFTrollCaveMain {

    TFGenMyceliumBlob myceliumBlobGen = new TFGenMyceliumBlob(5);
    TFGenMyceliumBlob dirtGen;
    WorldGenBigMushroom bigMushroomGen;
    TFGenBigMushgloom bigMushgloomGen;

    public ComponentTFTrollCaveGarden() {
        this.dirtGen = new TFGenMyceliumBlob(Blocks.field_150346_d, 5);
        this.bigMushroomGen = new WorldGenBigMushroom();
        this.bigMushgloomGen = new TFGenBigMushgloom();
    }

    public ComponentTFTrollCaveGarden(int index, int x, int y, int z, int caveSize, int caveHeight, int direction) {
        super(index);
        this.dirtGen = new TFGenMyceliumBlob(Blocks.field_150346_d, 5);
        this.bigMushroomGen = new WorldGenBigMushroom();
        this.bigMushgloomGen = new TFGenBigMushgloom();
        this.size = caveSize;
        this.height = caveHeight;
        this.setCoordBaseMode(direction);
        this.field_74887_e = StructureTFComponent.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1, direction);
    }

    public void func_74861_a(StructureComponent parent, List list, Random rand) {}

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox sbb) {
        if (this.isBoundingBoxOutOfHighlands(world, sbb)) {
            return false;
        } else {
            this.hollowCaveMiddle(world, sbb, rand, 0, 0, 0, this.size - 1, this.height - 1, this.size - 1);
            Random decoRNG = new Random(world.func_72905_C() + (long) (this.field_74887_e.field_78897_a * 321534781) ^ (long) (this.field_74887_e.field_78896_c * 756839));

            this.makeTreasureCrate(world, rand, sbb);

            int i;
            ChunkCoordinates dest;

            for (i = 0; i < 24; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generate(world, this.dirtGen, decoRNG, dest.field_71574_a, 1, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 16; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generate(world, this.myceliumBlobGen, decoRNG, dest.field_71574_a, 1, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 16; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generate(world, ComponentTFTrollCaveGarden.uberousGen, decoRNG, dest.field_71574_a, 1, dest.field_71573_c, sbb);
                this.generateAtSurface(world, ComponentTFTrollCaveGarden.uberousGen, decoRNG, dest.field_71574_a, 60, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 32; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generate(world, this.bigMushgloomGen, decoRNG, dest.field_71574_a, 1, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 64; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generate(world, this.bigMushroomGen, decoRNG, dest.field_71574_a, 1, dest.field_71573_c, sbb);
            }

            for (i = 0; i < 128; ++i) {
                dest = this.getCoordsInCave(decoRNG);
                this.generateBlockStalactite(world, decoRNG, Blocks.field_150348_b, 0.7F, true, dest.field_71574_a, 3, dest.field_71573_c, sbb);
            }

            return true;
        }
    }

    protected void generate(World world, WorldGenerator generator, Random rand, int x, int y, int z, StructureBoundingBox sbb) {
        int dx = this.func_74865_a(x, z);
        int dy = this.func_74862_a(y);
        int dz = this.func_74873_b(x, z);

        if (sbb.func_78890_b(dx, dy, dz)) {
            generator.func_76484_a(world, rand, dx, dy, dz);
        }

    }
}
