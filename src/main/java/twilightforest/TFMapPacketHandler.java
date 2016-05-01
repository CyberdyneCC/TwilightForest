package twilightforest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S34PacketMaps;
import twilightforest.item.ItemTFMagicMap;
import twilightforest.item.ItemTFMazeMap;

@Sharable
public class TFMapPacketHandler {

    public S34PacketMaps readMapPacket(ByteBuf byteBuf) {
        S34PacketMaps mapPacket = new S34PacketMaps();

        try {
            mapPacket.func_148837_a(new PacketBuffer(byteBuf));
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

        return mapPacket;
    }

    public static Packet makeMagicMapPacket(String mapChannel, short mapID, byte[] datas) {
        S34PacketMaps mapPacket = new S34PacketMaps(mapID, datas);
        PacketBuffer payload = new PacketBuffer(Unpooled.buffer());

        try {
            mapPacket.func_148840_b(payload);
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }

        FMLProxyPacket pkt = new FMLProxyPacket(payload, mapChannel);

        return pkt;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void incomingPacket(ClientCustomPacketEvent event) {
        S34PacketMaps mapPacket;

        if (event.packet.channel().equals("magicmap")) {
            mapPacket = this.readMapPacket(event.packet.payload());
            ItemTFMagicMap.getMPMapData(mapPacket.func_149188_c(), TwilightForestMod.proxy.getClientWorld()).func_76192_a(mapPacket.func_149187_d());
        } else if (event.packet.channel().equals("mazemap")) {
            mapPacket = this.readMapPacket(event.packet.payload());
            TFMazeMapData data = ItemTFMazeMap.getMPMapData(mapPacket.func_149188_c(), TwilightForestMod.proxy.getClientWorld());

            data.func_76192_a(mapPacket.func_149187_d());
            Minecraft.func_71410_x().field_71460_t.func_147701_i().func_148246_a(data);
        }

    }
}
