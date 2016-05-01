package twilightforest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapData.MapCoord;
import twilightforest.world.TFWorldChunkManager;

public class TFMagicMapData extends MapData {

    private static final int FEATURE_DATA_BYTE = 18;
    public List featuresVisibleOnMap = new ArrayList();

    public TFMagicMapData(String par1Str) {
        super(par1Str);
    }

    public void func_76184_a(NBTTagCompound par1NBTTagCompound) {
        super.func_76184_a(par1NBTTagCompound);
        byte[] featureStorage = par1NBTTagCompound.func_74770_j("features");

        if (featureStorage.length > 0) {
            this.func_76192_a(featureStorage);
        }

    }

    public void func_76187_b(NBTTagCompound par1NBTTagCompound) {
        super.func_76187_b(par1NBTTagCompound);
        if (this.featuresVisibleOnMap.size() > 0) {
            byte[] featureStorage = this.makeFeatureStorageArray();

            par1NBTTagCompound.func_74773_a("features", featureStorage);
        }

    }

    public void addFeatureToMap(TFFeature feature, int x, int z) {
        byte relativeX = (byte) (x - this.field_76201_a >> this.field_76197_d);
        byte relativeZ = (byte) (z - this.field_76199_b >> this.field_76197_d);
        byte rangeX = 64;
        byte rangeY = 64;

        if (relativeX >= -rangeX && relativeZ >= -rangeY && relativeX <= rangeX && relativeZ <= rangeY) {
            byte markerIcon = (byte) feature.featureID;
            byte mapX = (byte) (relativeX << 1);
            byte mapZ = (byte) (relativeZ << 1);
            byte mapRotation = 8;
            boolean featureFound = false;
            Iterator iterator = this.featuresVisibleOnMap.iterator();

            while (iterator.hasNext()) {
                MapCoord existingCoord = (MapCoord) iterator.next();

                if (existingCoord.field_76214_b == mapX && existingCoord.field_76215_c == mapZ) {
                    featureFound = true;
                }
            }

            if (!featureFound) {
                this.featuresVisibleOnMap.add(new MapCoord(this, markerIcon, mapX, mapZ, mapRotation));
            }
        }

    }

    public void checkExistingFeatures(World world) {
        ArrayList toRemove = null;
        Iterator iterator = this.featuresVisibleOnMap.iterator();

        while (iterator.hasNext()) {
            MapCoord coord = (MapCoord) iterator.next();
            int worldX = (coord.field_76214_b << this.field_76197_d - 1) + this.field_76201_a;
            int worldZ = (coord.field_76215_c << this.field_76197_d - 1) + this.field_76199_b;

            if (world != null && world.func_72959_q() instanceof TFWorldChunkManager) {
                TFWorldChunkManager tfManager = (TFWorldChunkManager) world.func_72959_q();

                coord.field_76216_a = (byte) tfManager.getFeatureID(worldX, worldZ, world);
                if (coord.field_76216_a == 0) {
                    if (toRemove == null) {
                        toRemove = new ArrayList();
                    }

                    toRemove.add(coord);
                }
            }
        }

        if (toRemove != null) {
            this.featuresVisibleOnMap.removeAll(toRemove);
        }

    }

    public void func_76192_a(byte[] par1ArrayOfByte) {
        if (par1ArrayOfByte[0] == 18) {
            this.featuresVisibleOnMap.clear();

            for (int i = 0; i < (par1ArrayOfByte.length - 1) / 3; ++i) {
                byte markerIcon = par1ArrayOfByte[i * 3 + 1];
                byte mapX = par1ArrayOfByte[i * 3 + 2];
                byte mapZ = par1ArrayOfByte[i * 3 + 3];
                byte mapRotation = 8;

                this.featuresVisibleOnMap.add(new MapCoord(this, markerIcon, mapX, mapZ, mapRotation));
            }
        } else {
            super.func_76192_a(par1ArrayOfByte);
        }

    }

    public byte[] makeFeatureStorageArray() {
        byte[] storage = new byte[this.featuresVisibleOnMap.size() * 3 + 1];

        storage[0] = 18;

        for (int i = 0; i < this.featuresVisibleOnMap.size(); ++i) {
            MapCoord featureCoord = (MapCoord) this.featuresVisibleOnMap.get(i);

            storage[i * 3 + 1] = featureCoord.field_76216_a;
            storage[i * 3 + 2] = featureCoord.field_76214_b;
            storage[i * 3 + 3] = featureCoord.field_76215_c;
        }

        return storage;
    }
}
