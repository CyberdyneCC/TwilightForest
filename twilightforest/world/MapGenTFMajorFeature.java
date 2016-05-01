package twilightforest.world;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.util.Iterator;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureData;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import twilightforest.TFFeature;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.StructureTFMajorFeatureStart;

public class MapGenTFMajorFeature extends MapGenStructure {

    protected boolean func_75047_a(int chunkX, int chunkZ) {
        return TFFeature.getFeatureDirectlyAt(chunkX, chunkZ, this.field_75039_c).isStructureEnabled;
    }

    protected StructureStart func_75049_b(int chunkX, int chunkZ) {
        this.field_75038_b.setSeed(this.field_75039_c.func_72905_C());
        long rand1 = this.field_75038_b.nextLong();
        long rand2 = this.field_75038_b.nextLong();
        long chunkXr1 = (long) chunkX * rand1;
        long chunkZr2 = (long) chunkZ * rand2;

        this.field_75038_b.setSeed(chunkXr1 ^ chunkZr2 ^ this.field_75039_c.func_72905_C());
        this.field_75038_b.nextInt();
        return new StructureTFMajorFeatureStart(this.field_75039_c, this.field_75038_b, chunkX, chunkZ);
    }

    public String func_143025_a() {
        return "TFFeature";
    }

    public int getSpawnListIndexAt(int par1, int par2, int par3) {
        int highestFoundIndex = -1;
        Iterator startIterator = this.field_75053_d.values().iterator();

        while (startIterator.hasNext()) {
            StructureStart start = (StructureStart) startIterator.next();

            if (start.func_75069_d() && start.func_75071_a().func_78885_a(par1, par3, par1, par3)) {
                Iterator componentIterator = start.func_75073_b().iterator();

                while (componentIterator.hasNext()) {
                    StructureComponent component = (StructureComponent) componentIterator.next();

                    if (component != null && component.func_74874_b() != null && component.func_74874_b().func_78890_b(par1, par2, par3)) {
                        if (!(component instanceof StructureTFComponent)) {
                            return 0;
                        }

                        StructureTFComponent tfComponent = (StructureTFComponent) component;

                        if (tfComponent.spawnListIndex > highestFoundIndex) {
                            highestFoundIndex = tfComponent.spawnListIndex;
                        }
                    }
                }
            }
        }

        return highestFoundIndex;
    }

    public StructureBoundingBox getSBBAt(int mapX, int mapY, int mapZ) {
        StructureBoundingBox boxFound = null;
        Iterator startIterator = this.field_75053_d.values().iterator();

        while (startIterator.hasNext()) {
            StructureStart start = (StructureStart) startIterator.next();

            if (start.func_75069_d() && start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ)) {
                Iterator componentIterator = start.func_75073_b().iterator();

                while (componentIterator.hasNext()) {
                    StructureComponent component = (StructureComponent) componentIterator.next();

                    if (component.func_74874_b().func_78890_b(mapX, mapY, mapZ)) {
                        boxFound = component.func_74874_b();
                    }
                }
            }
        }

        return boxFound;
    }

    public boolean isBlockProtectedAt(int mapX, int mapY, int mapZ) {
        boolean blockProtected = false;
        Iterator startIterator = this.field_75053_d.values().iterator();

        while (startIterator.hasNext()) {
            StructureStart start = (StructureStart) startIterator.next();

            if (start.func_75069_d() && start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ)) {
                Iterator componentIterator = start.func_75073_b().iterator();

                while (componentIterator.hasNext()) {
                    StructureComponent component = (StructureComponent) componentIterator.next();

                    if (component.func_74874_b().func_78890_b(mapX, mapY, mapZ)) {
                        if (component instanceof StructureTFComponent) {
                            StructureTFComponent tfComp = (StructureTFComponent) component;

                            blockProtected = tfComp.isComponentProtected();
                        } else {
                            blockProtected = true;
                        }
                    }
                }
            }
        }

        return blockProtected;
    }

    public void setStructureConquered(int mapX, int mapY, int mapZ, boolean flag) {
        Iterator iterator = this.field_75053_d.values().iterator();

        while (iterator.hasNext()) {
            StructureStart start = (StructureStart) iterator.next();

            if (start.func_75069_d() && start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ) && start instanceof StructureTFMajorFeatureStart) {
                StructureTFMajorFeatureStart featureStart = (StructureTFMajorFeatureStart) start;

                featureStart.isConquered = flag;
                MapGenStructureData data = (MapGenStructureData) ObfuscationReflectionHelper.getPrivateValue(MapGenStructure.class, this, new String[] { "field_143029_e"});

                data.func_143043_a(featureStart.func_143021_a(start.func_143019_e(), start.func_143018_f()), start.func_143019_e(), start.func_143018_f());
                data.func_76186_a(true);
            }
        }

    }

    public boolean isStructureConquered(int mapX, int mapY, int mapZ) {
        boolean conquered = false;
        Iterator iterator = this.field_75053_d.values().iterator();

        while (iterator.hasNext()) {
            StructureStart start = (StructureStart) iterator.next();

            if (start.func_75069_d() && start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ) && start instanceof StructureTFMajorFeatureStart) {
                conquered = ((StructureTFMajorFeatureStart) start).isConquered;
            }
        }

        return conquered;
    }

    public boolean isBlockInFullStructure(int mapX, int mapZ) {
        Iterator iterator = this.field_75053_d.values().iterator();

        StructureStart start;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            start = (StructureStart) iterator.next();
        } while (!start.func_75069_d() || !start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ));

        return true;
    }

    public boolean isBlockNearFullStructure(int mapX, int mapZ, int range) {
        StructureBoundingBox rangeBB = new StructureBoundingBox(mapX - range, mapZ - range, mapX + range, mapZ + range);
        Iterator iterator = this.field_75053_d.values().iterator();

        StructureStart start;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            start = (StructureStart) iterator.next();
        } while (!start.func_75069_d() || !start.func_75071_a().func_78884_a(rangeBB));

        return true;
    }

    public StructureBoundingBox getFullSBBAt(int mapX, int mapZ) {
        Iterator iterator = this.field_75053_d.values().iterator();

        StructureStart start;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            start = (StructureStart) iterator.next();
        } while (!start.func_75069_d() || !start.func_75071_a().func_78885_a(mapX, mapZ, mapX, mapZ));

        return start.func_75071_a();
    }

    public StructureBoundingBox getFullSBBNear(int mapX, int mapZ, int range) {
        StructureBoundingBox rangeBB = new StructureBoundingBox(mapX - range, mapZ - range, mapX + range, mapZ + range);
        Iterator iterator = this.field_75053_d.values().iterator();

        StructureStart start;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            start = (StructureStart) iterator.next();
        } while (!start.func_75069_d() || !start.func_75071_a().func_78884_a(rangeBB));

        return start.func_75071_a();
    }
}
