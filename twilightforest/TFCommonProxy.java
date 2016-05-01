package twilightforest;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import twilightforest.client.GuiTFGoblinCrafting;
import twilightforest.uncrafting.ContainerTFUncrafting;

public class TFCommonProxy implements IGuiHandler {

    public void doPreLoadRegistration() {}

    public void doOnLoadRegistration() {}

    public int getCritterBlockRenderID() {
        return 0;
    }

    public int getPlantBlockRenderID() {
        return 0;
    }

    public int getComplexBlockRenderID() {
        return 0;
    }

    public int getNagastoneBlockRenderID() {
        return 0;
    }

    public int getMagicLeavesBlockRenderID() {
        return 0;
    }

    public int getPedestalBlockRenderID() {
        return 0;
    }

    public int getThornsBlockRenderID() {
        return 0;
    }

    public int getKnightmetalBlockRenderID() {
        return 0;
    }

    public int getHugeLilyPadBlockRenderID() {
        return 0;
    }

    public int getCastleMagicBlockRenderID() {
        return 0;
    }

    public int registerArmorRenderID(String prefix) {
        return 0;
    }

    public World getClientWorld() {
        return null;
    }

    public void spawnParticle(World world, String particleType, double x, double y, double z, double velX, double velY, double velZ) {}

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new ContainerTFUncrafting(player.field_71071_by, world, x, y, z);
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return new GuiTFGoblinCrafting(player.field_71071_by, world, x, y, z);
    }

    public ModelBiped getKnightlyArmorModel(int armorSlot) {
        return null;
    }

    public ModelBiped getPhantomArmorModel(int armorSlot) {
        return null;
    }

    public ModelBiped getYetiArmorModel(int armorSlot) {
        return null;
    }

    public ModelBiped getArcticArmorModel(int armorSlot) {
        return null;
    }

    public ModelBiped getFieryArmorModel(int armorSlot) {
        return null;
    }

    public void doBlockAnnihilateEffect(World worldObj, int blockX, int blockY, int blockZ) {}
}
