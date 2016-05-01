package twilightforest;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.BlockTFPortal;
import twilightforest.block.TFBlocks;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.WorldProviderTwilightForest;

public class TFTickHandler {

    public Item portalItem = null;

    @SubscribeEvent
    public void playerTick(PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.field_70170_p;

        if (!TwilightForestMod.disablePortalCreation && event.phase == Phase.END && !world.field_72995_K && world.func_72820_D() % 20L == 0L) {
            if (TwilightForestMod.adminOnlyPortals) {
                try {
                    if (MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(player.func_146103_bH())) {
                        this.checkForPortalCreation(player, world, 4.0F);
                    }
                } catch (NoSuchMethodError nosuchmethoderror) {
                    FMLLog.warning("[TwilightForest] Could not determine op status for adminOnlyPortals option, ignoring option.", new Object[0]);
                    TwilightForestMod.adminOnlyPortals = false;
                }
            } else {
                this.checkForPortalCreation(player, world, 32.0F);
            }
        }

        if (!world.field_72995_K && event.phase == Phase.END && world.func_72820_D() % 20L == 0L && world.func_82736_K().func_82766_b("tfEnforcedProgression") && world.field_73011_w instanceof WorldProviderTwilightForest && !player.field_71075_bZ.field_75098_d) {
            this.checkBiomeForProgression(player, world);
        }

        if (!world.field_72995_K && event.phase == Phase.END && world.func_72820_D() % 100L == 0L && world.func_82736_K().func_82766_b("tfEnforcedProgression") && world.field_73011_w instanceof WorldProviderTwilightForest) {
            if (!player.field_71075_bZ.field_75098_d) {
                this.checkForLockedStructuresSendPacket(player, world);
            } else {
                this.sendAllClearPacket(world, player);
            }
        }

    }

    private void sendStructureProtectionPacket(World world, EntityPlayer player, StructureBoundingBox sbb) {
        FMLProxyPacket message = TFGenericPacketHandler.makeStructureProtectionPacket(sbb);

        if (player instanceof EntityPlayerMP) {
            TwilightForestMod.genericChannel.sendTo(message, (EntityPlayerMP) player);
        }

    }

    private void sendAllClearPacket(World world, EntityPlayer player) {
        FMLProxyPacket message = TFGenericPacketHandler.makeStructureProtectionClearPacket();

        if (player instanceof EntityPlayerMP) {
            TwilightForestMod.genericChannel.sendTo(message, (EntityPlayerMP) player);
        }

    }

    private boolean checkForLockedStructuresSendPacket(EntityPlayer player, World world) {
        ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) world.field_73011_w).getChunkProvider();
        int px = MathHelper.func_76128_c(player.field_70165_t);
        int py = MathHelper.func_76128_c(player.field_70163_u);
        int pz = MathHelper.func_76128_c(player.field_70161_v);

        if (chunkProvider != null && chunkProvider.isBlockNearFullStructure(px, pz, 100)) {
            StructureBoundingBox fullSBB = chunkProvider.getFullSBBNear(px, pz, 100);
            TFFeature nearFeature = TFFeature.getFeatureForRegion(fullSBB.func_78881_e() >> 4, fullSBB.func_78891_g() >> 4, world);

            if (nearFeature.hasProtectionAura && !nearFeature.doesPlayerHaveRequiredAchievement(player)) {
                this.sendStructureProtectionPacket(world, player, fullSBB);
                return true;
            } else {
                this.sendAllClearPacket(world, player);
                return false;
            }
        } else {
            return false;
        }
    }

    @SubscribeEvent
    public void tickStart(ItemTossEvent event) {
        System.out.println("ItemTossEvent Tick");
    }

    private void checkForPortalCreation(EntityPlayer player, World world, float rangeToCheck) {
        if (world != null && player != null && (world.field_73011_w.field_76574_g == 0 || world.field_73011_w.field_76574_g == TwilightForestMod.dimensionID || TwilightForestMod.allowPortalsInOtherDimensions)) {
            List itemList = world.func_72872_a(EntityItem.class, player.field_70121_D.func_72314_b((double) rangeToCheck, (double) rangeToCheck, (double) rangeToCheck));

            if (this.portalItem == null) {
                ;
            }

            Iterator iterator = itemList.iterator();

            while (iterator.hasNext()) {
                EntityItem entityItem = (EntityItem) iterator.next();

                if (entityItem.func_92059_d().func_77973_b() == this.portalItem && world.func_72875_a(entityItem.field_70121_D, Material.field_151586_h)) {
                    Random rand = new Random();

                    int dx;

                    for (dx = 0; dx < 2; ++dx) {
                        double dy = rand.nextGaussian() * 0.02D;
                        double d1 = rand.nextGaussian() * 0.02D;
                        double d2 = rand.nextGaussian() * 0.02D;

                        world.func_72869_a("spell", entityItem.field_70165_t, entityItem.field_70163_u + 0.2D, entityItem.field_70161_v, dy, d1, d2);
                    }

                    dx = MathHelper.func_76128_c(entityItem.field_70165_t);
                    int i = MathHelper.func_76128_c(entityItem.field_70163_u);
                    int dz = MathHelper.func_76128_c(entityItem.field_70161_v);

                    if (((BlockTFPortal) TFBlocks.portal).tryToCreatePortal(world, dx, i, dz)) {
                        player.func_71029_a(TFAchievementPage.twilightPortal);
                    }
                }
            }
        }

    }

    private void checkBiomeForProgression(EntityPlayer player, World world) {
        BiomeGenBase currentBiome = world.func_72807_a(MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70161_v));

        if (currentBiome instanceof TFBiomeBase) {
            TFBiomeBase tfBiome = (TFBiomeBase) currentBiome;
            boolean dangerousBiome = !tfBiome.doesPlayerHaveRequiredAchievement(player);

            if (dangerousBiome) {
                tfBiome.enforceProgession(player, world);
            }
        }

    }
}
