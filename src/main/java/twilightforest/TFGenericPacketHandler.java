package twilightforest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import twilightforest.client.renderer.TFWeatherRenderer;
import twilightforest.entity.EntityTFProtectionBox;
import twilightforest.world.WorldProviderTwilightForest;

public class TFGenericPacketHandler {

    public static final byte CHANGE_DIM_ID = 1;
    public static final byte TRANSFORM_BIOME = 2;
    public static final byte THROW_PLAYER = 3;
    public static final byte AREA_PROTECTION = 4;
    public static final byte STRUCTURE_PROTECTION = 5;
    public static final byte STRUCTURE_PROTECTION_CLEAR = 6;
    public static final byte ENFORCED_PROGRESSION_STATUS = 7;
    public static final byte ANNIHILATE_BLOCK = 8;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void incomingPacket(ClientCustomPacketEvent event) {
        ByteBuf buf = event.packet.payload();
        byte discriminatorByte = buf.readByte();

        if (discriminatorByte == 2) {
            this.processTransformBiomeData(buf);
        }

        if (discriminatorByte == 3) {
            this.processThrowPlayerData(buf);
        }

        if (discriminatorByte == 4) {
            this.processAreaProtectionData(buf);
        }

        if (discriminatorByte == 5) {
            this.processStructureProtectionData(buf);
        }

        if (discriminatorByte == 6) {
            this.processStructureProtectionClearData(buf);
        }

        if (discriminatorByte == 7) {
            this.processEnforcedProgressionStatusData(buf);
        }

        if (discriminatorByte == 8) {
            this.processAnnihilateBlock(buf);
        }

    }

    @SideOnly(Side.CLIENT)
    private void processTransformBiomeData(ByteBuf buf) {
        int x = buf.readInt();
        int z = buf.readInt();
        byte biomeID = buf.readByte();
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;
        Chunk chunkAt = worldObj.func_72938_d(x, z);

        chunkAt.func_76605_m()[(z & 15) << 4 | x & 15] = biomeID;
        worldObj.func_147458_c(x, 0, z, x, 255, z);
    }

    @SideOnly(Side.CLIENT)
    private void processThrowPlayerData(ByteBuf buf) {
        float motionX = buf.readFloat();
        float motionY = buf.readFloat();
        float motionZ = buf.readFloat();

        Minecraft.func_71410_x().field_71439_g.func_70024_g((double) motionX, (double) motionY, (double) motionZ);
    }

    @SideOnly(Side.CLIENT)
    private void processAreaProtectionData(ByteBuf buf) {
        int minX = buf.readInt();
        int minY = buf.readInt();
        int minZ = buf.readInt();
        int maxX = buf.readInt();
        int maxY = buf.readInt();
        int maxZ = buf.readInt();
        int blockX = buf.readInt();
        int blockY = buf.readInt();
        int blockZ = buf.readInt();
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;
        EntityTFProtectionBox box = new EntityTFProtectionBox(worldObj, minX, minY, minZ, maxX, maxY, maxZ);

        worldObj.func_72942_c(box);

        for (int i = 0; i < 20; ++i) {
            double d0 = worldObj.field_73012_v.nextGaussian() * 0.02D;
            double d1 = worldObj.field_73012_v.nextGaussian() * 0.02D;
            double d2 = worldObj.field_73012_v.nextGaussian() * 0.02D;
            float dx = (float) blockX + 0.5F + worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat();
            float dy = (float) blockY + 0.5F + worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat();
            float dz = (float) blockZ + 0.5F + worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat();

            TwilightForestMod.proxy.spawnParticle(worldObj, "protection", (double) dx, (double) dy, (double) dz, d0, d1, d2);
        }

    }

    @SideOnly(Side.CLIENT)
    private void processStructureProtectionData(ByteBuf buf) {
        int minX = buf.readInt();
        int minY = buf.readInt();
        int minZ = buf.readInt();
        int maxX = buf.readInt();
        int maxY = buf.readInt();
        int maxZ = buf.readInt();
        StructureBoundingBox sbb = new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;

        if (worldObj.field_73011_w instanceof WorldProviderTwilightForest) {
            TFWeatherRenderer weatherRenderer = (TFWeatherRenderer) worldObj.field_73011_w.getWeatherRenderer();

            weatherRenderer.setProtectedBox(sbb);
        }

    }

    @SideOnly(Side.CLIENT)
    private void processStructureProtectionClearData(ByteBuf buf) {
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;

        if (worldObj.field_73011_w instanceof WorldProviderTwilightForest) {
            TFWeatherRenderer weatherRenderer = (TFWeatherRenderer) worldObj.field_73011_w.getWeatherRenderer();

            weatherRenderer.setProtectedBox((StructureBoundingBox) null);
        }

    }

    @SideOnly(Side.CLIENT)
    private void processEnforcedProgressionStatusData(ByteBuf buf) {
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;
        boolean isEnforced = buf.readBoolean();

        worldObj.func_82736_K().func_82764_b("tfEnforcedProgression", Boolean.valueOf(isEnforced).toString());
    }

    @SideOnly(Side.CLIENT)
    private void processAnnihilateBlock(ByteBuf buf) {
        int blockX = buf.readInt();
        int blockY = buf.readInt();
        int blockZ = buf.readInt();
        WorldClient worldObj = Minecraft.func_71410_x().field_71441_e;

        TwilightForestMod.proxy.doBlockAnnihilateEffect(worldObj, blockX, blockY, blockZ);
    }

    public static FMLProxyPacket makeBiomeChangePacket(int x, int z, byte biomeID) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(2);
        payload.writeInt(x);
        payload.writeInt(z);
        payload.writeByte(biomeID);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeThrowPlayerPacket(float motionX, float motionY, float motionZ) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(3);
        payload.writeFloat(motionX);
        payload.writeFloat(motionY);
        payload.writeFloat(motionZ);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeAreaProtectionPacket(StructureBoundingBox sbb, int blockX, int blockY, int blockZ) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(4);
        payload.writeInt(sbb.field_78897_a);
        payload.writeInt(sbb.field_78895_b);
        payload.writeInt(sbb.field_78896_c);
        payload.writeInt(sbb.field_78893_d);
        payload.writeInt(sbb.field_78894_e);
        payload.writeInt(sbb.field_78892_f);
        payload.writeInt(blockX);
        payload.writeInt(blockY);
        payload.writeInt(blockZ);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeStructureProtectionPacket(StructureBoundingBox sbb) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(5);
        payload.writeInt(sbb.field_78897_a);
        payload.writeInt(sbb.field_78895_b);
        payload.writeInt(sbb.field_78896_c);
        payload.writeInt(sbb.field_78893_d);
        payload.writeInt(sbb.field_78894_e);
        payload.writeInt(sbb.field_78892_f);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeStructureProtectionClearPacket() {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(6);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeEnforcedProgressionStatusPacket(boolean isEnforced) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(7);
        payload.writeBoolean(isEnforced);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }

    public static FMLProxyPacket makeAnnihilateBlockPacket(int blockX, int blockY, int blockZ) {
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        payload.writeByte(8);
        payload.writeInt(blockX);
        payload.writeInt(blockY);
        payload.writeInt(blockZ);
        FMLProxyPacket pkt = new FMLProxyPacket(payload, "TwilightForest");

        return pkt;
    }
}
