package twilightforest;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandGameRule;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;
import twilightforest.enchantment.TFEnchantment;
import twilightforest.entity.EntityTFCharmEffect;
import twilightforest.entity.EntityTFPinchBeetle;
import twilightforest.entity.EntityTFYeti;
import twilightforest.item.TFItems;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class TFEventListener {

    protected HashMap playerKeepsMap = new HashMap();
    private boolean isBreakingWithGiantPick = false;
    private boolean shouldMakeGiantCobble = false;
    private int amountOfCobbleToReplace = 0;
    private long lastSpawnedHintMonsterTime;

    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        Item item = event.item.func_92059_d().func_77973_b();

        if (item == TFItems.scepterTwilight || item == TFItems.scepterLifeDrain || item == TFItems.scepterZombie) {
            this.checkPlayerForScepterMastery(event.entityPlayer);
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressLich);
        }

        if (item == TFItems.nagaScale) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressNaga);
        }

        if (item == TFItems.trophy && event.item.func_92059_d().func_77960_j() == 0) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightKillHydra);
        }

        if (item == TFItems.trophy && event.item.func_92059_d().func_77960_j() == 1) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightKillNaga);
        }

        if (item == TFItems.trophy && event.item.func_92059_d().func_77960_j() == 2) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightKillLich);
        }

        if (item == TFItems.trophy && event.item.func_92059_d().func_77960_j() == 3) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressUrghast);
        }

        if (item == TFItems.trophy && event.item.func_92059_d().func_77960_j() == 4) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressGlacier);
        }

        if (item == TFItems.mazebreakerPick) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightMazebreaker);
        }

        if (item == TFItems.meefStroganoff || item == TFItems.minotaurAxe) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressLabyrinth);
        }

        if (item == TFItems.fieryBlood) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressHydra);
        }

        if (item == TFItems.phantomHelm || item == TFItems.phantomPlate) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressKnights);
        }

        if (item == TFItems.fieryTears) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressUrghast);
        }

        if (item == TFItems.alphaFur || item == TFItems.yetiBoots || item == TFItems.yetiHelm || item == TFItems.yetiPlate || item == TFItems.yetiLegs) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressYeti);
        }

        if (item == TFItems.lampOfCinders) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightProgressTroll);
        }

    }

    private void checkPlayerForScepterMastery(EntityPlayer player) {
        boolean scepterTwilight = false;
        boolean scepterLifeDrain = false;
        boolean scepterZombie = false;
        InventoryPlayer inv = player.field_71071_by;

        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);

            if (stack != null && stack.func_77973_b() == TFItems.scepterTwilight) {
                scepterTwilight = true;
            }

            if (stack != null && stack.func_77973_b() == TFItems.scepterLifeDrain) {
                scepterLifeDrain = true;
            }

            if (stack != null && stack.func_77973_b() == TFItems.scepterZombie) {
                scepterZombie = true;
            }
        }

        if (scepterTwilight && scepterLifeDrain && scepterZombie) {
            player.func_71029_a(TFAchievementPage.twilightLichScepters);
        }

    }

    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {
        ItemStack itemStack = event.crafting;
        EntityPlayer player = event.player;

        if (itemStack.func_77973_b() == TFItems.plateNaga || itemStack.func_77973_b() == TFItems.legsNaga) {
            this.checkPlayerForNagaArmorer(player);
        }

        if (itemStack.func_77973_b() == TFItems.magicMapFocus) {
            player.func_71029_a(TFAchievementPage.twilightMagicMapFocus);
        }

        if (itemStack.func_77973_b() == TFItems.emptyMagicMap) {
            player.func_71029_a(TFAchievementPage.twilightMagicMap);
        }

        if (itemStack.func_77973_b() == TFItems.emptyMazeMap) {
            player.func_71029_a(TFAchievementPage.twilightMazeMap);
        }

        if (itemStack.func_77973_b() == TFItems.emptyOreMap) {
            player.func_71029_a(TFAchievementPage.twilightOreMap);
        }

        if (itemStack.func_77973_b() == Item.func_150898_a(Blocks.field_150344_f) && itemStack.field_77994_a == 64 && this.doesCraftMatrixHaveGiantLog(event.craftMatrix)) {
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.field_150344_f, 64));
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.field_150344_f, 64));
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.field_150344_f, 64));
        }

    }

    private void addToPlayerInventoryOrDrop(EntityPlayer player, ItemStack planks) {
        if (!player.field_71071_by.func_70441_a(planks)) {
            player.func_71019_a(planks, false);
        }

    }

    private boolean doesCraftMatrixHaveGiantLog(IInventory inv) {
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);

            if (stack != null && stack.func_77973_b() == Item.func_150898_a(TFBlocks.giantLog)) {
                return true;
            }
        }

        return false;
    }

    private void checkPlayerForNagaArmorer(EntityPlayer player) {
        boolean nagaScale = false;
        boolean legsNaga = false;
        InventoryPlayer inv = player.field_71071_by;

        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);

            if (stack != null && stack.func_77973_b() == TFItems.nagaScale) {
                nagaScale = true;
            }

            if (stack != null && stack.func_77973_b() == TFItems.legsNaga) {
                legsNaga = true;
            }
        }

        if (nagaScale && legsNaga) {
            player.func_71029_a(TFAchievementPage.twilightNagaArmors);
        }

    }

    @SubscribeEvent
    public void harvestDrops(HarvestDropsEvent event) {
        if (event.harvester != null && event.harvester.field_71071_by.func_70448_g() != null && event.harvester.field_71071_by.func_70448_g().func_77973_b().func_150897_b(event.block) && event.harvester.field_71071_by.func_70448_g().func_77973_b() == TFItems.fieryPick) {
            ArrayList removeThese = new ArrayList(1);
            ArrayList addThese = new ArrayList(1);
            Iterator iterator = event.drops.iterator();

            while (iterator.hasNext()) {
                ItemStack input = (ItemStack) iterator.next();
                ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(input);

                if (result != null) {
                    addThese.add(new ItemStack(result.func_77973_b(), input.field_77994_a));
                    removeThese.add(input);
                    this.spawnSpeltXP(result, event.world, event.x, event.y, event.z);
                }
            }

            event.drops.removeAll(removeThese);
            event.drops.addAll(addThese);
        }

        if (this.shouldMakeGiantCobble && event.drops.size() > 0 && ((ItemStack) event.drops.get(0)).func_77973_b() == Item.func_150898_a(Blocks.field_150347_e)) {
            event.drops.remove(0);
            if (this.amountOfCobbleToReplace == 64) {
                event.drops.add(new ItemStack(TFBlocks.giantCobble));
            }

            --this.amountOfCobbleToReplace;
            if (this.amountOfCobbleToReplace <= 0) {
                this.shouldMakeGiantCobble = false;
            }
        }

    }

    private void spawnSpeltXP(ItemStack smelted, World world, int x, int y, int z) {
        float floatXP = FurnaceRecipes.func_77602_a().func_151398_b(smelted);
        int smeltXP = (int) floatXP;

        if (floatXP > (float) smeltXP && world.field_73012_v.nextFloat() < floatXP - (float) smeltXP) {
            ++smeltXP;
        }

        while (smeltXP > 0) {
            int splitXP = EntityXPOrb.func_70527_a(smeltXP);

            smeltXP -= splitXP;
            world.func_72838_d(new EntityXPOrb(world, (double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, splitXP));
        }

    }

    @SubscribeEvent
    public void entityHurts(LivingHurtEvent event) {
        EntityPlayer player;
        int charm1;

        if (event.entityLiving instanceof EntityPlayer && event.source.field_76373_n.equals("mob") && event.source.func_76346_g() != null) {
            player = (EntityPlayer) event.entityLiving;
            charm1 = TFEnchantment.getFieryAuraLevel(player.field_71071_by, event.source);
            if (charm1 > 0 && player.func_70681_au().nextInt(25) < charm1) {
                event.source.func_76346_g().func_70015_d(charm1 / 2);
            }
        }

        if (event.entityLiving instanceof EntityPlayer && event.source.field_76373_n.equals("mob") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase) {
            player = (EntityPlayer) event.entityLiving;
            charm1 = TFEnchantment.getChillAuraLevel(player.field_71071_by, event.source);
            if (charm1 > 0) {
                ((EntityLivingBase) event.source.func_76346_g()).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, charm1 * 5 + 5, charm1));
            }
        }

        if (event.source.field_76373_n.equals("arrow") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.func_76346_g();
            if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == TFItems.tripleBow) {
                event.entityLiving.field_70172_ad = 0;
            }
        }

        if (event.source.field_76373_n.equals("arrow") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.func_76346_g();
            if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == TFItems.iceBow) {
                byte charm11 = 2;

                event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 200, charm11, true));
            }
        }

        if (event.source.field_76373_n.equals("arrow") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.func_76346_g();
            if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == TFItems.enderBow) {
                double charm12 = player.field_70165_t;
                double effect = player.field_70163_u;
                double sourceZ = player.field_70161_v;
                float sourceYaw = player.field_70177_z;
                float sourcePitch = player.field_70125_A;

                player.field_70177_z = event.entityLiving.field_70177_z;
                player.field_70125_A = event.entityLiving.field_70125_A;
                player.func_70634_a(event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v);
                player.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
                event.entityLiving.func_70080_a(charm12, effect, sourceZ, sourceYaw, sourcePitch);
                event.entityLiving.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
            }
        }

        if (event.entityLiving instanceof EntityPlayer && this.willEntityDie(event)) {
            player = (EntityPlayer) event.entityLiving;
            boolean charm13 = false;
            boolean charm2 = player.field_71071_by.func_146026_a(TFItems.charmOfLife2);

            if (!charm2) {
                charm13 = player.field_71071_by.func_146026_a(TFItems.charmOfLife1);
            }

            if (charm2 || charm13) {
                event.setResult(Result.DENY);
                event.setCanceled(true);
                event.ammount = 0.0F;
                if (charm13) {
                    player.func_70606_j(8.0F);
                    player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 100, 0));
                }

                if (charm2) {
                    player.func_70606_j((float) player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
                    player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 600, 3));
                    player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 600, 0));
                    player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 600, 0));
                }

                EntityTFCharmEffect effect1 = new EntityTFCharmEffect(player.field_70170_p, player, charm13 ? TFItems.charmOfLife1 : TFItems.charmOfLife2);

                player.field_70170_p.func_72838_d(effect1);
                EntityTFCharmEffect effect2 = new EntityTFCharmEffect(player.field_70170_p, player, charm13 ? TFItems.charmOfLife1 : TFItems.charmOfLife2);

                effect2.offset = 3.1415927F;
                player.field_70170_p.func_72838_d(effect2);
                player.field_70170_p.func_72908_a(player.field_70165_t + 0.5D, player.field_70163_u + 0.5D, player.field_70161_v + 0.5D, "mob.zombie.unfect", 1.5F, 1.0F);
            }
        }

    }

    public boolean willEntityDie(LivingHurtEvent event) {
        float amount = event.ammount;
        DamageSource source = event.source;
        EntityLivingBase living = event.entityLiving;
        int resistance;

        if (!source.func_76363_c()) {
            resistance = 25 - living.func_70658_aO();
            amount = amount * (float) resistance / 25.0F;
        }

        if (living.func_70644_a(Potion.field_76429_m)) {
            resistance = 25 - (living.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
            amount = amount * (float) resistance / 25.0F;
        }

        return Math.ceil((double) amount) >= Math.floor((double) living.func_110143_aJ());
    }

    @SubscribeEvent
    public void bonemealUsed(BonemealEvent event) {
        if (event.block == TFBlocks.sapling && !event.world.field_72995_K) {
            ((BlockSapling) TFBlocks.sapling).func_149878_d(event.world, event.x, event.y, event.z, event.world.field_73012_v);
            event.setResult(Result.ALLOW);
        }

    }

    @SubscribeEvent
    public void livingDies(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !event.entityLiving.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            InventoryPlayer keepInventory;
            int i;

            if (player.field_71071_by.func_146026_a(TFItems.charmOfKeeping3)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping III!  Keep it all!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);

                for (i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                    keepInventory.field_70462_a[i] = ItemStack.func_77944_b(player.field_71071_by.field_70462_a[i]);
                    player.field_71071_by.field_70462_a[i] = null;
                }

                keepInventory.func_70437_b(new ItemStack(TFItems.charmOfKeeping3));
                this.playerKeepsMap.put(player.func_70005_c_(), keepInventory);
            } else if (player.field_71071_by.func_146026_a(TFItems.charmOfKeeping2)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping II!  Keep armor and hotbar!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);

                for (i = 0; i < 9; ++i) {
                    keepInventory.field_70462_a[i] = ItemStack.func_77944_b(player.field_71071_by.field_70462_a[i]);
                    player.field_71071_by.field_70462_a[i] = null;
                }

                keepInventory.func_70437_b(new ItemStack(TFItems.charmOfKeeping2));
                this.playerKeepsMap.put(player.func_70005_c_(), keepInventory);
            } else if (player.field_71071_by.func_146026_a(TFItems.charmOfKeeping1)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping I!  Keep armor and current item!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);
                if (player.field_71071_by.func_70448_g() != null) {
                    keepInventory.field_70462_a[player.field_71071_by.field_70461_c] = ItemStack.func_77944_b(player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c]);
                    player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
                }

                keepInventory.func_70437_b(new ItemStack(TFItems.charmOfKeeping1));
                this.playerKeepsMap.put(player.func_70005_c_(), keepInventory);
            }

            if (player.field_71071_by.func_146028_b(TFItems.towerKey)) {
                keepInventory = this.retrieveOrMakeKeepInventory(player);

                for (i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                    if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() == TFItems.towerKey) {
                        keepInventory.field_70462_a[i] = ItemStack.func_77944_b(player.field_71071_by.field_70462_a[i]);
                        player.field_71071_by.field_70462_a[i] = null;
                    }
                }

                this.playerKeepsMap.put(player.func_70005_c_(), keepInventory);
            }
        }

        if (this.playerKeepsMap.size() > 1) {
            FMLLog.warning("[TwilightForest] Twilight Forest mod is keeping track of a lot of dead player inventories.  Has there been an apocalypse?", new Object[0]);
        }

    }

    private InventoryPlayer retrieveOrMakeKeepInventory(EntityPlayer player) {
        return this.playerKeepsMap.containsKey(player.func_70005_c_()) ? (InventoryPlayer) this.playerKeepsMap.get(player.func_70005_c_()) : new InventoryPlayer((EntityPlayer) null);
    }

    private void keepAllArmor(EntityPlayer player, InventoryPlayer keepInventory) {
        for (int i = 0; i < player.field_71071_by.field_70460_b.length; ++i) {
            keepInventory.field_70460_b[i] = ItemStack.func_77944_b(player.field_71071_by.field_70460_b[i]);
            player.field_71071_by.field_70460_b[i] = null;
        }

    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        EntityPlayer player = event.player;

        if (this.playerKeepsMap.containsKey(player.func_70005_c_())) {
            FMLLog.info("[TwilightForest] Player %s respawned and recieved items held in storage", new Object[] { player.func_70005_c_()});
            InventoryPlayer keepInventory = (InventoryPlayer) this.playerKeepsMap.get(player.func_70005_c_());

            int effect;

            for (effect = 0; effect < player.field_71071_by.field_70460_b.length; ++effect) {
                if (keepInventory.field_70460_b[effect] != null) {
                    player.field_71071_by.field_70460_b[effect] = keepInventory.field_70460_b[effect];
                }
            }

            for (effect = 0; effect < player.field_71071_by.field_70462_a.length; ++effect) {
                if (keepInventory.field_70462_a[effect] != null) {
                    player.field_71071_by.field_70462_a[effect] = keepInventory.field_70462_a[effect];
                }
            }

            if (keepInventory.func_70445_o() != null) {
                EntityTFCharmEffect entitytfcharmeffect = new EntityTFCharmEffect(player.field_70170_p, player, keepInventory.func_70445_o().func_77973_b());

                player.field_70170_p.func_72838_d(entitytfcharmeffect);
                EntityTFCharmEffect effect2 = new EntityTFCharmEffect(player.field_70170_p, player, keepInventory.func_70445_o().func_77973_b());

                effect2.offset = 3.1415927F;
                player.field_70170_p.func_72838_d(effect2);
                player.field_70170_p.func_72908_a(player.field_70165_t + 0.5D, player.field_70163_u + 0.5D, player.field_70161_v + 0.5D, "mob.zombie.unfect", 1.5F, 1.0F);
            }

            this.playerKeepsMap.remove(player.func_70005_c_());
        }

    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;

        if (this.playerKeepsMap.containsKey(player.func_70005_c_())) {
            FMLLog.warning("[TwilightForest] Mod was keeping inventory items in reserve for player %s but they logged out!  Items are being dropped.", new Object[] { player.func_70005_c_()});
            InventoryPlayer keepInventory = (InventoryPlayer) this.playerKeepsMap.get(player.func_70005_c_());

            keepInventory.field_70458_d = player;
            keepInventory.func_70436_m();
            this.playerKeepsMap.remove(player.func_70005_c_());
        }

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public boolean preOverlay(Pre event) {
        if (event.type == ElementType.HEALTHMOUNT && this.isRidingUnfriendly(Minecraft.func_71410_x().field_71439_g)) {
            event.setCanceled(true);
            return false;
        } else {
            return true;
        }
    }

    @SubscribeEvent
    public boolean livingUpdate(LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer && event.entity.func_70093_af() && this.isRidingUnfriendly(event.entityLiving)) {
            event.entity.func_70095_a(false);
        }

        return true;
    }

    private boolean isRidingUnfriendly(EntityLivingBase entity) {
        return entity.func_70115_ae() && (entity.field_70154_o instanceof EntityTFPinchBeetle || entity.field_70154_o instanceof EntityTFYeti);
    }

    @SubscribeEvent
    public void breakBlock(BreakEvent event) {
        if (!event.getPlayer().field_71075_bZ.field_75098_d && this.isAreaProtected(event.world, event.getPlayer(), event.x, event.y, event.z) && this.isBlockProtectedFromBreaking(event.world, event.x, event.y, event.z)) {
            event.setCanceled(true);
        } else if (!this.isBreakingWithGiantPick && event.getPlayer().func_71045_bC() != null && event.getPlayer().func_71045_bC().func_77973_b() == TFItems.giantPick && event.getPlayer().func_71045_bC().func_77973_b().func_150897_b(event.block)) {
            this.isBreakingWithGiantPick = true;
            int bx = event.x >> 2 << 2;
            int by = event.y >> 2 << 2;
            int bz = event.z >> 2 << 2;
            boolean allCobble = event.block.func_149650_a(event.blockMetadata, event.world.field_73012_v, 0) == Item.func_150898_a(Blocks.field_150347_e);

            int dx;
            int dy;
            int dz;
            Block blockThere;
            int metaThere;

            for (dx = 0; dx < 4; ++dx) {
                for (dy = 0; dy < 4; ++dy) {
                    for (dz = 0; dz < 4; ++dz) {
                        blockThere = event.world.func_147439_a(bx + dx, by + dy, bz + dz);
                        metaThere = event.world.func_72805_g(bx + dx, by + dy, bz + dz);
                        allCobble &= blockThere.func_149650_a(metaThere, event.world.field_73012_v, 0) == Item.func_150898_a(Blocks.field_150347_e);
                    }
                }
            }

            if (allCobble && !event.getPlayer().field_71075_bZ.field_75098_d) {
                this.shouldMakeGiantCobble = true;
                this.amountOfCobbleToReplace = 64;
            } else {
                this.shouldMakeGiantCobble = false;
                this.amountOfCobbleToReplace = 0;
            }

            for (dx = 0; dx < 4; ++dx) {
                for (dy = 0; dy < 4; ++dy) {
                    for (dz = 0; dz < 4; ++dz) {
                        blockThere = event.world.func_147439_a(bx + dx, by + dy, bz + dz);
                        metaThere = event.world.func_72805_g(bx + dx, by + dy, bz + dz);
                        if ((event.x != bx + dx || event.y != by + dy || event.z != bz + dz) && blockThere == event.block && metaThere == event.blockMetadata && event.getPlayer() instanceof EntityPlayerMP) {
                            EntityPlayerMP playerMP = (EntityPlayerMP) event.getPlayer();

                            playerMP.field_71134_c.func_73084_b(bx + dx, by + dy, bz + dz);
                        }
                    }
                }
            }

            this.isBreakingWithGiantPick = false;
        }

    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent event) {
        if (event.action == Action.RIGHT_CLICK_BLOCK && event.entityPlayer.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest && !event.entityPlayer.field_71075_bZ.field_75098_d) {
            World currentItem = event.entityPlayer.field_70170_p;
            EntityPlayer player = event.entityPlayer;
            int x = event.x;
            int y = event.y;
            int z = event.z;

            if (!currentItem.field_72995_K && this.isBlockProtectedFromInteraction(currentItem, x, y, z) && this.isAreaProtected(currentItem, player, x, y, z)) {
                event.useBlock = Result.DENY;
            }
        }

        ItemStack currentItem1 = event.entityPlayer.field_71071_by.func_70448_g();

        if (currentItem1 != null && (currentItem1.func_77973_b() == TFItems.fierySword || currentItem1.func_77973_b() == TFItems.fieryPick) && this.checkPlayerForFieryArmor(event.entityPlayer)) {
            event.entityPlayer.func_71029_a(TFAchievementPage.twilightFierySet);
        }

    }

    private boolean isBlockProtectedFromInteraction(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);

        return block == TFBlocks.towerDevice || block == Blocks.field_150486_ae || block == Blocks.field_150447_bR || block == Blocks.field_150430_aB || block == Blocks.field_150471_bO || block == Blocks.field_150442_at;
    }

    private boolean isBlockProtectedFromBreaking(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);

        return !block.func_149739_a().equals("tile.openblocks.grave");
    }

    private boolean checkPlayerForFieryArmor(EntityPlayer entityPlayer) {
        ItemStack[] armor = entityPlayer.field_71071_by.field_70460_b;

        return armor[0] != null && armor[0].func_77973_b() == TFItems.fieryBoots ? true : (armor[1] != null && armor[1].func_77973_b() == TFItems.fieryLegs ? true : (armor[2] != null && armor[2].func_77973_b() == TFItems.fieryPlate ? true : armor[3] != null && armor[3].func_77973_b() == TFItems.fieryHelm));
    }

    private boolean isAreaProtected(World world, EntityPlayer player, int x, int y, int z) {
        if (world.func_82736_K().func_82766_b("tfEnforcedProgression") && world.field_73011_w instanceof WorldProviderTwilightForest) {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) world.field_73011_w).getChunkProvider();

            if (chunkProvider != null && chunkProvider.isBlockInStructureBB(x, y, z)) {
                TFFeature nearbyFeature = ((TFWorldChunkManager) world.field_73011_w.field_76578_c).getFeatureAt(x, z, world);

                if (!nearbyFeature.doesPlayerHaveRequiredAchievement(player) && chunkProvider.isBlockProtected(x, y, z)) {
                    StructureBoundingBox sbb = chunkProvider.getSBBAt(x, y, z);

                    this.sendAreaProtectionPacket(world, x, y, z, sbb);
                    nearbyFeature.trySpawnHintMonster(world, player, x, y, z);
                    return true;
                }
            }
        }

        return false;
    }

    private void sendAreaProtectionPacket(World world, int x, int y, int z, StructureBoundingBox sbb) {
        FMLProxyPacket message = TFGenericPacketHandler.makeAreaProtectionPacket(sbb, x, y, z);
        TargetPoint targetPoint = new TargetPoint(world.field_73011_w.field_76574_g, (double) x, (double) y, (double) z, 64.0D);

        TwilightForestMod.genericChannel.sendToAllAround(message, targetPoint);
    }

    @SubscribeEvent
    public void livingAttack(LivingAttackEvent event) {
        if (event.entityLiving instanceof IMob && event.source.func_76346_g() instanceof EntityPlayer && !((EntityPlayer) event.source.func_76346_g()).field_71075_bZ.field_75098_d && event.entityLiving.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest && event.entityLiving.field_70170_p.func_82736_K().func_82766_b("tfEnforcedProgression")) {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) event.entityLiving.field_70170_p.field_73011_w).getChunkProvider();
            int mx = MathHelper.func_76128_c(event.entityLiving.field_70165_t);
            int my = MathHelper.func_76128_c(event.entityLiving.field_70163_u);
            int mz = MathHelper.func_76128_c(event.entityLiving.field_70161_v);

            if (chunkProvider != null && chunkProvider.isBlockInStructureBB(mx, my, mz) && chunkProvider.isBlockProtected(mx, my, mz)) {
                TFFeature nearbyFeature = ((TFWorldChunkManager) event.entityLiving.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(mx, mz, event.entityLiving.field_70170_p);

                if (!nearbyFeature.doesPlayerHaveRequiredAchievement((EntityPlayer) event.source.func_76346_g())) {
                    event.setResult(Result.DENY);
                    event.setCanceled(true);

                    for (int i = 0; i < 20; ++i) {
                        TwilightForestMod.proxy.spawnParticle(event.entityLiving.field_70170_p, "protection", event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public void playerLogsIn(PlayerLoggedInEvent event) {
        TwilightForestMod.hasBiomeIdConflicts = TFBiomeBase.areThereBiomeIdConflicts();
        if (TwilightForestMod.hasBiomeIdConflicts) {
            event.player.func_145747_a(new ChatComponentText("[TwilightForest] Biome ID conflict detected.  Fix by editing the config file."));
        }

        if (!event.player.field_70170_p.field_72995_K && event.player instanceof EntityPlayerMP) {
            this.sendEnforcedProgressionStatus((EntityPlayerMP) event.player, event.player.field_70170_p.func_82736_K().func_82766_b("tfEnforcedProgression"));
        }

    }

    @SubscribeEvent
    public void playerPortals(PlayerChangedDimensionEvent event) {
        if (!event.player.field_70170_p.field_72995_K && event.player instanceof EntityPlayerMP && event.toDim == TwilightForestMod.dimensionID) {
            this.sendEnforcedProgressionStatus((EntityPlayerMP) event.player, event.player.field_70170_p.func_82736_K().func_82766_b("tfEnforcedProgression"));
        }

    }

    private void sendEnforcedProgressionStatus(EntityPlayerMP player, boolean isEnforced) {
        TwilightForestMod.genericChannel.sendTo(TFGenericPacketHandler.makeEnforcedProgressionStatusPacket(isEnforced), player);
    }

    @SubscribeEvent
    public void worldLoaded(Load event) {
        if (!event.world.field_72995_K && !event.world.func_82736_K().func_82765_e("tfEnforcedProgression")) {
            FMLLog.info("[TwilightForest] Loaded a world with the tfEnforcedProgression game rule not defined.  Defining it.", new Object[0]);
            event.world.func_82736_K().func_82769_a("tfEnforcedProgression", "true");
        }

    }

    @SubscribeEvent
    public void commandSent(CommandEvent event) {
        if (event.command instanceof CommandGameRule && event.parameters.length > 1 && "tfEnforcedProgression".equals(event.parameters[0])) {
            boolean isEnforced = Boolean.valueOf(event.parameters[1]).booleanValue();

            TwilightForestMod.genericChannel.sendToAll(TFGenericPacketHandler.makeEnforcedProgressionStatusPacket(isEnforced));
        }

    }
}
