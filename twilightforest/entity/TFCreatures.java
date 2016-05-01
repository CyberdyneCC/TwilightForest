package twilightforest.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;

public class TFCreatures {

    public static HashMap entityEggs = new LinkedHashMap();

    public static void registerTFCreature(Class entityClass, String entityName, int id, int backgroundEggColour, int foregroundEggColour) {
        registerTFCreature(entityClass, entityName, id, backgroundEggColour, foregroundEggColour, 80, 3, true);
    }

    public static void registerTFCreature(Class entityClass, String entityName, int id, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        if (TwilightForestMod.creatureCompatibility) {
            EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        }

        EntityRegistry.registerModEntity(entityClass, entityName, id, TwilightForestMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        TFCreatures.entityEggs.put(Integer.valueOf(id), new TFEntityEggInfo(id, backgroundEggColour, foregroundEggColour));
    }

    public static void registerTFCreature(Class entityClass, String entityName, int id) {
        if (TwilightForestMod.creatureCompatibility) {
            EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        }

        EntityRegistry.registerModEntity(entityClass, entityName, id, TwilightForestMod.instance, 80, 3, true);
    }

    public static Entity createEntityByID(int entityID, World par1World) {
        Entity entity = null;

        try {
            ModContainer modcontainer = FMLCommonHandler.instance().findContainerFor(TwilightForestMod.instance);
            EntityRegistration er = EntityRegistry.instance().lookupModSpawn(modcontainer, entityID);
            Class clazz = er.getEntityClass();

            if (clazz != null) {
                entity = (Entity) clazz.getConstructor(new Class[] { World.class}).newInstance(new Object[] { par1World});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (entity == null) {
            System.out.println("Skipping Entity with id " + entityID);
        }

        return entity;
    }

    public static String getStringFromID(int entityID) {
        ModContainer mc = FMLCommonHandler.instance().findContainerFor(TwilightForestMod.instance);
        EntityRegistration er = EntityRegistry.instance().lookupModSpawn(mc, entityID);

        return er != null ? er.getEntityName() : null;
    }

    public static String getSpawnerNameFor(String baseName) {
        return TwilightForestMod.creatureCompatibility ? baseName : "TwilightForest." + baseName;
    }
}
