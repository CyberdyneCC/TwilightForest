package twilightforest.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import twilightforest.world.WorldProviderTwilightForest;

public class TFClientTicker {

    @SubscribeEvent
    public void clientTick(ClientTickEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        WorldClient world = mc.field_71441_e;

        if (world != null && world.field_73011_w instanceof WorldProviderTwilightForest && mc.field_71456_v != null) {
            mc.field_71456_v.field_73843_a = 0.0F;
        }

    }
}
