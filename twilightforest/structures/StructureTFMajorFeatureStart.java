package twilightforest.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.minecraft.world.gen.structure.StructureStrongholdPieces.Stairs2;
import twilightforest.TFFeature;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;
import twilightforest.structures.darktower.ComponentTFDarkTowerMain;
import twilightforest.structures.darktower.TFDarkTowerPieces;
import twilightforest.structures.hollowtree.StructureTFHollowTreeStart;
import twilightforest.structures.hollowtree.TFHollowTreePieces;
import twilightforest.structures.icetower.ComponentTFIceTowerMain;
import twilightforest.structures.icetower.TFIceTowerPieces;
import twilightforest.structures.lichtower.ComponentTFTowerMain;
import twilightforest.structures.lichtower.TFLichTowerPieces;
import twilightforest.structures.minotaurmaze.ComponentTFMazeRuins;
import twilightforest.structures.minotaurmaze.TFMinotaurMazePieces;
import twilightforest.structures.mushroomtower.ComponentTFMushroomTowerMain;
import twilightforest.structures.mushroomtower.TFMushroomTowerPieces;
import twilightforest.structures.stronghold.ComponentTFStrongholdEntrance;
import twilightforest.structures.stronghold.TFStrongholdPieces;
import twilightforest.structures.trollcave.ComponentTFTrollCaveMain;
import twilightforest.structures.trollcave.TFTrollCavePieces;
import twilightforest.world.TFWorld;
import twilightforest.world.TFWorldChunkManager;

public class StructureTFMajorFeatureStart extends StructureStart {

    public TFFeature feature;
    public boolean isConquered;

    public StructureTFMajorFeatureStart() {}

    public StructureTFMajorFeatureStart(World world, Random rand, int chunkX, int chunkZ) {
        StructureStrongholdPieces.func_75198_a();
        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y = TFWorld.SEALEVEL + 1;

        this.feature = TFFeature.getFeatureDirectlyAt(chunkX, chunkZ, world);
        this.isConquered = false;
        StructureComponent firstComponent = this.makeFirstComponent(world, rand, this.feature, x, y, z);

        if (firstComponent != null) {
            this.field_75075_a.add(firstComponent);
            firstComponent.func_74861_a(firstComponent, this.field_75075_a, rand);
        }

        this.func_75072_c();
        if (firstComponent instanceof Stairs2) {
            List list = ((Stairs2) firstComponent).field_75026_c;

            while (!list.isEmpty()) {
                int offY = rand.nextInt(list.size());
                StructureComponent structurecomponent = (StructureComponent) list.remove(offY);

                structurecomponent.func_74861_a(firstComponent, this.field_75075_a, rand);
            }

            this.func_75072_c();
            byte offY1 = -33;

            this.field_75074_b.func_78886_a(0, offY1, 0);
            Iterator iterator = this.func_75073_b().iterator();

            while (iterator.hasNext()) {
                StructureComponent com = (StructureComponent) iterator.next();

                com.func_74874_b().func_78886_a(0, offY1, 0);
            }
        }

        if (firstComponent instanceof ComponentTFTowerMain || firstComponent instanceof ComponentTFDarkTowerMain) {
            this.moveToAvgGroundLevel(world, x, z);
        }

    }

    public StructureComponent makeFirstComponent(World world, Random rand, TFFeature feature, int x, int y, int z) {
        return (StructureComponent) (feature == TFFeature.nagaCourtyard ? new ComponentTFNagaCourtyard(world, rand, 0, x, y, z) : (feature == TFFeature.hedgeMaze ? new ComponentTFHedgeMaze(world, rand, 0, x, y, z) : (feature == TFFeature.hill1 ? new ComponentTFHollowHill(world, rand, 0, 1, x, y, z) : (feature == TFFeature.hill2 ? new ComponentTFHollowHill(world, rand, 0, 2, x, y, z) : (feature == TFFeature.hill3 ? new ComponentTFHollowHill(world, rand, 0, 3, x, y, z) : (feature == TFFeature.lichTower ? new ComponentTFTowerMain(world, rand, 0, x, y, z) : (feature == TFFeature.questGrove ? new ComponentTFQuestGrove(world, rand, 0, x, y, z) : (feature == TFFeature.hydraLair ? new ComponentTFHydraLair(world, rand, 0, x, y, z) : (feature == TFFeature.labyrinth ? new ComponentTFMazeRuins(world, rand, 0, x, y, z) : (feature == TFFeature.darkTower ? new ComponentTFDarkTowerMain(world, rand, 0, x, y - 1, z) : (feature == TFFeature.tfStronghold ? new ComponentTFStrongholdEntrance(world, rand, 0, x, y, z) : (feature == TFFeature.iceTower ? new ComponentTFIceTowerMain(world, rand, 0, x, y, z) : (feature == TFFeature.mushroomTower ? new ComponentTFMushroomTowerMain(world, rand, 0, x, y, z) : (feature == TFFeature.yetiCave ? new ComponentTFYetiCave(world, rand, 0, x, y, z) : (feature == TFFeature.trollCave ? new ComponentTFTrollCaveMain(world, rand, 0, x, y, z) : (feature == TFFeature.finalCastle ? new TFFinalCastlePieces.Main(world, rand, 0, x, y, z) : null))))))))))))))));
    }

    public boolean func_75069_d() {
        return this.feature.isStructureEnabled;
    }

    protected void moveToAvgGroundLevel(World world, int x, int z) {
        if (world.func_72959_q() instanceof TFWorldChunkManager) {
            BiomeGenBase biomeAt = world.func_72807_a(x, z);
            int offY = (int) ((biomeAt.field_76748_D + biomeAt.field_76749_E) * 8.0F);

            if (biomeAt == TFBiomeBase.darkForest) {
                offY += 4;
            }

            if (offY > 0) {
                this.field_75074_b.func_78886_a(0, offY, 0);
                Iterator iterator = this.func_75073_b().iterator();

                while (iterator.hasNext()) {
                    StructureComponent com = (StructureComponent) iterator.next();

                    com.func_74874_b().func_78886_a(0, offY, 0);
                }
            }
        }

    }

    private boolean isIntersectingLarger(StructureBoundingBox chunkBB, StructureComponent component) {
        StructureBoundingBox compBB = component.func_74874_b();

        return compBB.field_78893_d + 1 >= chunkBB.field_78897_a && compBB.field_78897_a - 1 <= chunkBB.field_78893_d && compBB.field_78892_f + 1 >= chunkBB.field_78896_c && compBB.field_78896_c - 1 <= chunkBB.field_78892_f;
    }

    private boolean isShieldable(StructureComponent component) {
        return component.func_74874_b().field_78894_e <= 32;
    }

    private void addShieldFor(World world, StructureComponent component, List otherComponents, StructureBoundingBox chunkBox) {
        StructureBoundingBox shieldBox = new StructureBoundingBox(component.func_74874_b());

        --shieldBox.field_78897_a;
        --shieldBox.field_78895_b;
        --shieldBox.field_78896_c;
        ++shieldBox.field_78893_d;
        ++shieldBox.field_78894_e;
        ++shieldBox.field_78892_f;
        ArrayList intersecting = new ArrayList();
        Iterator x = otherComponents.iterator();

        while (x.hasNext()) {
            StructureComponent y = (StructureComponent) x.next();

            if (y != component && shieldBox.func_78884_a(y.func_74874_b())) {
                intersecting.add(y);
            }
        }

        for (int i = shieldBox.field_78897_a; i <= shieldBox.field_78893_d; ++i) {
            for (int j = shieldBox.field_78895_b; j <= shieldBox.field_78894_e; ++j) {
                for (int z = shieldBox.field_78896_c; z <= shieldBox.field_78892_f; ++z) {
                    if ((i == shieldBox.field_78897_a || i == shieldBox.field_78893_d || j == shieldBox.field_78895_b || j == shieldBox.field_78894_e || z == shieldBox.field_78896_c || z == shieldBox.field_78892_f) && chunkBox.func_78890_b(i, j, z)) {
                        boolean notIntersecting = true;
                        Iterator iterator = intersecting.iterator();

                        while (iterator.hasNext()) {
                            StructureComponent other = (StructureComponent) iterator.next();

                            if (other.func_74874_b().func_78890_b(i, j, z)) {
                                notIntersecting = false;
                            }
                        }

                        if (notIntersecting) {
                            world.func_147465_d(i, j, z, TFBlocks.shield, this.calculateShieldMeta(shieldBox, i, j, z), 2);
                        }
                    }
                }
            }
        }

    }

    private int calculateShieldMeta(StructureBoundingBox shieldBox, int x, int y, int z) {
        byte shieldMeta = 0;

        if (x == shieldBox.field_78897_a) {
            shieldMeta = 5;
        }

        if (x == shieldBox.field_78893_d) {
            shieldMeta = 4;
        }

        if (z == shieldBox.field_78896_c) {
            shieldMeta = 3;
        }

        if (z == shieldBox.field_78892_f) {
            shieldMeta = 2;
        }

        if (y == shieldBox.field_78895_b) {
            shieldMeta = 1;
        }

        if (y == shieldBox.field_78894_e) {
            shieldMeta = 0;
        }

        return shieldMeta;
    }

    public void func_143022_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143022_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("Conquered", this.isConquered);
        par1NBTTagCompound.func_74768_a("FeatureID", this.feature.featureID);
    }

    public void func_143017_b(NBTTagCompound nbttagcompound) {
        super.func_143017_b(nbttagcompound);
        this.isConquered = nbttagcompound.func_74767_n("Conquered");
        this.feature = TFFeature.featureList[nbttagcompound.func_74762_e("FeatureID")];
    }

    static {
        MapGenStructureIO.func_143034_b(StructureTFMajorFeatureStart.class, "TFFeature");
        MapGenStructureIO.func_143034_b(StructureTFHollowTreeStart.class, "TFHollowTree");
        TFStrongholdPieces.registerPieces();
        TFMinotaurMazePieces.registerPieces();
        TFDarkTowerPieces.registerPieces();
        TFLichTowerPieces.registerPieces();
        TFIceTowerPieces.registerPieces();
        TFMushroomTowerPieces.registerPieces();
        TFHollowTreePieces.registerPieces();
        TFTrollCavePieces.registerPieces();
        TFFinalCastlePieces.registerFinalCastlePieces();
        MapGenStructureIO.func_143031_a(ComponentTFHedgeMaze.class, "TFHedge");
        MapGenStructureIO.func_143031_a(ComponentTFHillMaze.class, "TFHillMaze");
        MapGenStructureIO.func_143031_a(ComponentTFHollowHill.class, "TFHill");
        MapGenStructureIO.func_143031_a(ComponentTFHydraLair.class, "TFHydra");
        MapGenStructureIO.func_143031_a(ComponentTFNagaCourtyard.class, "TFNaga");
        MapGenStructureIO.func_143031_a(ComponentTFQuestGrove.class, "TFQuest1");
        MapGenStructureIO.func_143031_a(ComponentTFYetiCave.class, "TFYeti");
    }
}
