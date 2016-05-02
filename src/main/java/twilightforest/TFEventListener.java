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
        Item item = event.item.getEntityItem().getItem();

        if (item == TFItems.scepterTwilight || item == TFItems.scepterLifeDrain || item == TFItems.scepterZombie) {
            this.checkPlayerForScepterMastery(event.entityPlayer);
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressLich);
        }

        if (item == TFItems.nagaScale) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressNaga);
        }

        if (item == TFItems.trophy && event.item.getEntityItem().getItemDamage() == 0) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightKillHydra);
        }

        if (item == TFItems.trophy && event.item.getEntityItem().getItemDamage() == 1) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightKillNaga);
        }

        if (item == TFItems.trophy && event.item.getEntityItem().getItemDamage() == 2) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightKillLich);
        }

        if (item == TFItems.trophy && event.item.getEntityItem().getItemDamage() == 3) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressUrghast);
        }

        if (item == TFItems.trophy && event.item.getEntityItem().getItemDamage() == 4) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressGlacier);
        }

        if (item == TFItems.mazebreakerPick) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightMazebreaker);
        }

        if (item == TFItems.meefStroganoff || item == TFItems.minotaurAxe) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressLabyrinth);
        }

        if (item == TFItems.fieryBlood) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressHydra);
        }

        if (item == TFItems.phantomHelm || item == TFItems.phantomPlate) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressKnights);
        }

        if (item == TFItems.fieryTears) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressUrghast);
        }

        if (item == TFItems.alphaFur || item == TFItems.yetiBoots || item == TFItems.yetiHelm || item == TFItems.yetiPlate || item == TFItems.yetiLegs) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressYeti);
        }

        if (item == TFItems.lampOfCinders) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightProgressTroll);
        }

    }

    private void checkPlayerForScepterMastery(EntityPlayer player) {
        boolean scepterTwilight = false;
        boolean scepterLifeDrain = false;
        boolean scepterZombie = false;
        InventoryPlayer inv = player. inventory;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (stack != null && stack.getItem() == TFItems.scepterTwilight) {
                scepterTwilight = true;
            }

            if (stack != null && stack.getItem() == TFItems.scepterLifeDrain) {
                scepterLifeDrain = true;
            }

            if (stack != null && stack.getItem() == TFItems.scepterZombie) {
                scepterZombie = true;
            }
        }

        if (scepterTwilight && scepterLifeDrain && scepterZombie) {
            player.triggerAchievement(TFAchievementPage.twilightLichScepters);
        }

    }

    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {
        ItemStack itemStack = event.crafting;
        EntityPlayer player = event.player;

        if (itemStack.getItem() == TFItems.plateNaga || itemStack.getItem() == TFItems.legsNaga) {
            this.checkPlayerForNagaArmorer(player);
        }

        if (itemStack.getItem() == TFItems.magicMapFocus) {
            player.triggerAchievement(TFAchievementPage.twilightMagicMapFocus);
        }

        if (itemStack.getItem() == TFItems.emptyMagicMap) {
            player.triggerAchievement(TFAchievementPage.twilightMagicMap);
        }

        if (itemStack.getItem() == TFItems.emptyMazeMap) {
            player.triggerAchievement(TFAchievementPage.twilightMazeMap);
        }

        if (itemStack.getItem() == TFItems.emptyOreMap) {
            player.triggerAchievement(TFAchievementPage.twilightOreMap);
        }

        if (itemStack.getItem() == Item.getItemFromBlock(Blocks.planks) && itemStack.stackSize == 64 && this.doesCraftMatrixHaveGiantLog(event.craftMatrix)) {
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.planks, 64));
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.planks, 64));
            this.addToPlayerInventoryOrDrop(player, new ItemStack(Blocks.planks, 64));
        }

    }

    private void addToPlayerInventoryOrDrop(EntityPlayer player, ItemStack planks) {
        if (!player. inventory.addItemStackToInventory(planks)) {
            player.dropPlayerItemWithRandomChoice(planks, false);
        }

    }

    private boolean doesCraftMatrixHaveGiantLog(IInventory inv) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (stack != null && stack.getItem() == Item.getItemFromBlock(TFBlocks.giantLog)) {
                return true;
            }
        }

        return false;
    }

    private void checkPlayerForNagaArmorer(EntityPlayer player) {
        boolean nagaScale = false;
        boolean legsNaga = false;
        InventoryPlayer inv = player. inventory;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);

            if (stack != null && stack.getItem() == TFItems.nagaScale) {
                nagaScale = true;
            }

            if (stack != null && stack.getItem() == TFItems.legsNaga) {
                legsNaga = true;
            }
        }

        if (nagaScale && legsNaga) {
            player.triggerAchievement(TFAchievementPage.twilightNagaArmors);
        }

    }

    @SubscribeEvent
    public void harvestDrops(HarvestDropsEvent event) {
        if (event.harvester != null && event.harvester. inventory.getCurrentItem() != null && event.harvester. inventory.getCurrentItem().getItem().func_150897_b(event.block) && event.harvester. inventory.getCurrentItem().getItem() == TFItems.fieryPick) {
            ArrayList removeThese = new ArrayList(1);
            ArrayList addThese = new ArrayList(1);
            Iterator iterator = event.drops.iterator();

            while (iterator.hasNext()) {
                ItemStack input = (ItemStack) iterator.next();
                ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input);

                if (result != null) {
                    addThese.add(new ItemStack(result.getItem(), input.stackSize));
                    removeThese.add(input);
                    this.spawnSpeltXP(result, event.world, event.x, event.y, event.z);
                }
            }

            event.drops.removeAll(removeThese);
            event.drops.addAll(addThese);
        }

        if (this.shouldMakeGiantCobble && event.drops.size() > 0 && ((ItemStack) event.drops.get(0)).getItem() == Item.getItemFromBlock(Blocks.cobblestone)) {
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
        float floatXP = FurnaceRecipes.smelting().func_151398_b(smelted);
        int smeltXP = (int) floatXP;

        if (floatXP > (float) smeltXP && world.rand.nextFloat() < floatXP - (float) smeltXP) {
            ++smeltXP;
        }

        while (smeltXP > 0) {
            int splitXP = EntityXPOrb.getXPSplit(smeltXP);

            smeltXP -= splitXP;
            world.spawnEntityInWorld(new EntityXPOrb(world, (double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, splitXP));
        }

    }

    @SubscribeEvent
    public void entityHurts(LivingHurtEvent event) {
        EntityPlayer player;
        int charm1;

        if (event.entityLiving instanceof EntityPlayer && event.source.damageType.equals("mob") && event.source.getEntity() != null) {
            player = (EntityPlayer) event.entityLiving;
            charm1 = TFEnchantment.getFieryAuraLevel(player. inventory, event.source);
            if (charm1 > 0 && player.getRNG().nextInt(25) < charm1) {
                event.source.getEntity().setFire(charm1 / 2);
            }
        }

        if (event.entityLiving instanceof EntityPlayer && event.source.damageType.equals("mob") && event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase) {
            player = (EntityPlayer) event.entityLiving;
            charm1 = TFEnchantment.getChillAuraLevel(player. inventory, event.source);
            if (charm1 > 0) {
                ((EntityLivingBase) event.source.getEntity()).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), charm1 * 5 + 5, charm1));
            }
        }

        if (event.source.damageType.equals("arrow") && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TFItems.tripleBow) {
                event.entityLiving.hurtResistantTime = 0;
            }
        }

        if (event.source.damageType.equals("arrow") && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TFItems.iceBow) {
                byte charm11 = 2;

                event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 200, charm11, true));
            }
        }

        if (event.source.damageType.equals("arrow") && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
            player = (EntityPlayer) event.source.getEntity();
            if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == TFItems.enderBow) {
                double charm12 = player.posX;
                double effect = player.posY;
                double sourceZ = player.posZ;
                float sourceYaw = player.rotationYaw;
                float sourcePitch = player.rotationPitch;

                player.rotationYaw = event.entityLiving.rotationYaw;
                player.rotationPitch = event.entityLiving.rotationPitch;
                player.setPositionAndUpdate(event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ);
                player.playSound("mob.endermen.portal", 1.0F, 1.0F);
                event.entityLiving.setPositionAndRotation(charm12, effect, sourceZ, sourceYaw, sourcePitch);
                event.entityLiving.playSound("mob.endermen.portal", 1.0F, 1.0F);
            }
        }

        if (event.entityLiving instanceof EntityPlayer && this.willEntityDie(event)) {
            player = (EntityPlayer) event.entityLiving;
            boolean charm13 = false;
            boolean charm2 = player. inventory.consumeInventoryItem(TFItems.charmOfLife2);

            if (!charm2) {
                charm13 = player. inventory.consumeInventoryItem(TFItems.charmOfLife1);
            }

            if (charm2 || charm13) {
                event.setResult(Result.DENY);
                event.setCanceled(true);
                event.ammount = 0.0F;
                if (charm13) {
                    player.setHealth(8.0F);
                    player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 100, 0));
                }

                if (charm2) {
                    player.setHealth((float) player.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue());
                    player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 600, 3));
                    player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 600, 0));
                    player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 600, 0));
                }

                EntityTFCharmEffect effect1 = new EntityTFCharmEffect(player.worldObj, player, charm13 ? TFItems.charmOfLife1 : TFItems.charmOfLife2);

                player.worldObj.spawnEntityInWorld(effect1);
                EntityTFCharmEffect effect2 = new EntityTFCharmEffect(player.worldObj, player, charm13 ? TFItems.charmOfLife1 : TFItems.charmOfLife2);

                effect2.offset = 3.1415927F;
                player.worldObj.spawnEntityInWorld(effect2);
                player.worldObj.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "mob.zombie.unfect", 1.5F, 1.0F);
            }
        }

    }

    public boolean willEntityDie(LivingHurtEvent event) {
        float amount = event.ammount;
        DamageSource source = event.source;
        EntityLivingBase living = event.entityLiving;
        int resistance;

        if (!source.isUnblockable()) {
            resistance = 25 - living.getTotalArmorValue();
            amount = amount * (float) resistance / 25.0F;
        }

        if (living.isPotionActive(Potion.resistance)) {
            resistance = 25 - (living.getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
            amount = amount * (float) resistance / 25.0F;
        }

        return Math.ceil((double) amount) >= Math.floor((double) living.getHealth());
    }

    @SubscribeEvent
    public void bonemealUsed(BonemealEvent event) {
        if (event.block == TFBlocks.sapling && !event.world.isRemote) {
            ((BlockSapling) TFBlocks.sapling).func_149878_d(event.world, event.x, event.y, event.z, event.world.rand);
            event.setResult(Result.ALLOW);
        }

    }

    @SubscribeEvent
    public void livingDies(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            InventoryPlayer keepInventory;
            int i;

            if (player. inventory.consumeInventoryItem(TFItems.charmOfKeeping3)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping III!  Keep it all!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);

                for (i = 0; i < player. inventory.mainInventory.length; ++i) {
                    keepInventory.mainInventory[i] = ItemStack.copyItemStack(player. inventory.mainInventory[i]);
                    player. inventory.mainInventory[i] = null;
                }

                keepInventory.setItemStack(new ItemStack(TFItems.charmOfKeeping3));
                this.playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
            } else if (player. inventory.consumeInventoryItem(TFItems.charmOfKeeping2)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping II!  Keep armor and hotbar!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);

                for (i = 0; i < 9; ++i) {
                    keepInventory.mainInventory[i] = ItemStack.copyItemStack(player. inventory.mainInventory[i]);
                    player. inventory.mainInventory[i] = null;
                }

                keepInventory.setItemStack(new ItemStack(TFItems.charmOfKeeping2));
                this.playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
            } else if (player. inventory.consumeInventoryItem(TFItems.charmOfKeeping1)) {
                FMLLog.info("[TwilightForest] Player died with charm of keeping I!  Keep armor and current item!", new Object[0]);
                keepInventory = new InventoryPlayer((EntityPlayer) null);
                this.keepAllArmor(player, keepInventory);
                if (player. inventory.getCurrentItem() != null) {
                    keepInventory.mainInventory[player. inventory.currentItem] = ItemStack.copyItemStack(player. inventory.mainInventory[player. inventory.currentItem]);
                    player. inventory.mainInventory[player. inventory.currentItem] = null;
                }

                keepInventory.setItemStack(new ItemStack(TFItems.charmOfKeeping1));
                this.playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
            }

            if (player. inventory.hasItem(TFItems.towerKey)) {
                keepInventory = this.retrieveOrMakeKeepInventory(player);

                for (i = 0; i < player. inventory.mainInventory.length; ++i) {
                    if (player. inventory.mainInventory[i] != null && player. inventory.mainInventory[i].getItem() == TFItems.towerKey) {
                        keepInventory.mainInventory[i] = ItemStack.copyItemStack(player. inventory.mainInventory[i]);
                        player. inventory.mainInventory[i] = null;
                    }
                }

                this.playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
            }
        }

        if (this.playerKeepsMap.size() > 1) {
            FMLLog.warning("[TwilightForest] Twilight Forest mod is keeping track of a lot of dead player inventories.  Has there been an apocalypse?", new Object[0]);
        }

    }

    private InventoryPlayer retrieveOrMakeKeepInventory(EntityPlayer player) {
        return this.playerKeepsMap.containsKey(player.getCommandSenderName()) ? (InventoryPlayer) this.playerKeepsMap.get(player.getCommandSenderName()) : new InventoryPlayer((EntityPlayer) null);
    }

    private void keepAllArmor(EntityPlayer player, InventoryPlayer keepInventory) {
        for (int i = 0; i < player. inventory.armorInventory.length; ++i) {
            keepInventory.armorInventory[i] = ItemStack.copyItemStack(player. inventory.armorInventory[i]);
            player. inventory.armorInventory[i] = null;
        }

    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        EntityPlayer player = event.player;

        if (this.playerKeepsMap.containsKey(player.getCommandSenderName())) {
            FMLLog.info("[TwilightForest] Player %s respawned and recieved items held in storage", new Object[] { player.getCommandSenderName()});
            InventoryPlayer keepInventory = (InventoryPlayer) this.playerKeepsMap.get(player.getCommandSenderName());

            int effect;

            for (effect = 0; effect < player. inventory.armorInventory.length; ++effect) {
                if (keepInventory.armorInventory[effect] != null) {
                    player. inventory.armorInventory[effect] = keepInventory.armorInventory[effect];
                }
            }

            for (effect = 0; effect < player. inventory.mainInventory.length; ++effect) {
                if (keepInventory.mainInventory[effect] != null) {
                    player. inventory.mainInventory[effect] = keepInventory.mainInventory[effect];
                }
            }

            if (keepInventory.getItemStack() != null) {
                EntityTFCharmEffect entitytfcharmeffect = new EntityTFCharmEffect(player.worldObj, player, keepInventory.getItemStack().getItem());

                player.worldObj.spawnEntityInWorld(entitytfcharmeffect);
                EntityTFCharmEffect effect2 = new EntityTFCharmEffect(player.worldObj, player, keepInventory.getItemStack().getItem());

                effect2.offset = 3.1415927F;
                player.worldObj.spawnEntityInWorld(effect2);
                player.worldObj.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "mob.zombie.unfect", 1.5F, 1.0F);
            }

            this.playerKeepsMap.remove(player.getCommandSenderName());
        }

    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;

        if (this.playerKeepsMap.containsKey(player.getCommandSenderName())) {
            FMLLog.warning("[TwilightForest] Mod was keeping inventory items in reserve for player %s but they logged out!  Items are being dropped.", new Object[] { player.getCommandSenderName()});
            InventoryPlayer keepInventory = (InventoryPlayer) this.playerKeepsMap.get(player.getCommandSenderName());

            keepInventory.player = player;
            keepInventory.dropAllItems();
            this.playerKeepsMap.remove(player.getCommandSenderName());
        }

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public boolean preOverlay(Pre event) {
        if (event.type == ElementType.HEALTHMOUNT && this.isRidingUnfriendly(Minecraft.getMinecraft().thePlayer)) {
            event.setCanceled(true);
            return false;
        } else {
            return true;
        }
    }

    @SubscribeEvent
    public boolean livingUpdate(LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer && event.entity.isSneaking() && this.isRidingUnfriendly(event.entityLiving)) {
            event.entity.setSneaking(false);
        }

        return true;
    }

    private boolean isRidingUnfriendly(EntityLivingBase entity) {
        return entity.isRiding() && (entity instanceof EntityTFPinchBeetle || entity instanceof EntityTFYeti);
    }

    @SubscribeEvent
    public void breakBlock(BreakEvent event) {
        if (!event.getPlayer().capabilities.isCreativeMode && this.isAreaProtected(event.world, event.getPlayer(), event.x, event.y, event.z) && this.isBlockProtectedFromBreaking(event.world, event.x, event.y, event.z)) {
            event.setCanceled(true);
        } else if (!this.isBreakingWithGiantPick && event.getPlayer().getCurrentEquippedItem() != null && event.getPlayer().getCurrentEquippedItem().getItem() == TFItems.giantPick && event.getPlayer().getCurrentEquippedItem().getItem().func_150897_b(event.block)) {
            this.isBreakingWithGiantPick = true;
            int bx = event.x >> 2 << 2;
            int by = event.y >> 2 << 2;
            int bz = event.z >> 2 << 2;
            boolean allCobble = event.block.getItemDropped(event.blockMetadata, event.world.rand, 0) == Item.getItemFromBlock(Blocks.cobblestone);

            int dx;
            int dy;
            int dz;
            Block blockThere;
            int metaThere;

            for (dx = 0; dx < 4; ++dx) {
                for (dy = 0; dy < 4; ++dy) {
                    for (dz = 0; dz < 4; ++dz) {
                        blockThere = event.world.getBlock(bx + dx, by + dy, bz + dz);
                        metaThere = event.world.getBlockMetadata(bx + dx, by + dy, bz + dz);
                        allCobble &= blockThere.getItemDropped(metaThere, event.world.rand, 0) == Item.getItemFromBlock(Blocks.cobblestone);
                    }
                }
            }

            if (allCobble && !event.getPlayer().capabilities.isCreativeMode) {
                this.shouldMakeGiantCobble = true;
                this.amountOfCobbleToReplace = 64;
            } else {
                this.shouldMakeGiantCobble = false;
                this.amountOfCobbleToReplace = 0;
            }

            for (dx = 0; dx < 4; ++dx) {
                for (dy = 0; dy < 4; ++dy) {
                    for (dz = 0; dz < 4; ++dz) {
                        blockThere = event.world.getBlock(bx + dx, by + dy, bz + dz);
                        metaThere = event.world.getBlockMetadata(bx + dx, by + dy, bz + dz);
                        if ((event.x != bx + dx || event.y != by + dy || event.z != bz + dz) && blockThere == event.block && metaThere == event.blockMetadata && event.getPlayer() instanceof EntityPlayerMP) {
                            EntityPlayerMP playerMP = (EntityPlayerMP) event.getPlayer();

                            playerMP.theItemInWorldManager.tryHarvestBlock(bx + dx, by + dy, bz + dz);
                        }
                    }
                }
            }

            this.isBreakingWithGiantPick = false;
        }

    }

    @SubscribeEvent
    public void rightClickBlock(PlayerInteractEvent event) {
        if (event.action == Action.RIGHT_CLICK_BLOCK && event.entityPlayer.worldObj.provider instanceof WorldProviderTwilightForest && !event.entityPlayer.capabilities.isCreativeMode) {
            World currentItem = event.entityPlayer.worldObj;
            EntityPlayer player = event.entityPlayer;
            int x = event.x;
            int y = event.y;
            int z = event.z;

            if (!currentItem.isRemote && this.isBlockProtectedFromInteraction(currentItem, x, y, z) && this.isAreaProtected(currentItem, player, x, y, z)) {
                event.useBlock = Result.DENY;
            }
        }

        ItemStack currentItem1 = event.entityPlayer. inventory.getCurrentItem();

        if (currentItem1 != null && (currentItem1.getItem() == TFItems.fierySword || currentItem1.getItem() == TFItems.fieryPick) && this.checkPlayerForFieryArmor(event.entityPlayer)) {
            event.entityPlayer.triggerAchievement(TFAchievementPage.twilightFierySet);
        }

    }

    private boolean isBlockProtectedFromInteraction(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        return block == TFBlocks.towerDevice || block == Blocks.chest || block == Blocks.trapped_chest || block == Blocks.stone_button || block == Blocks.wooden_button || block == Blocks.lever;
    }

    private boolean isBlockProtectedFromBreaking(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        return !block.getUnlocalizedName().equals("tile.openblocks.grave");
    }

    private boolean checkPlayerForFieryArmor(EntityPlayer entityPlayer) {
        ItemStack[] armor = entityPlayer. inventory.armorInventory;

        return armor[0] != null && armor[0].getItem() == TFItems.fieryBoots ? true : (armor[1] != null && armor[1].getItem() == TFItems.fieryLegs ? true : (armor[2] != null && armor[2].getItem() == TFItems.fieryPlate ? true : armor[3] != null && armor[3].getItem() == TFItems.fieryHelm));
    }

    private boolean isAreaProtected(World world, EntityPlayer player, int x, int y, int z) {
        if (world.getGameRules().getGameRuleBooleanValue("tfEnforcedProgression") && world.provider instanceof WorldProviderTwilightForest) {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) world.provider).getChunkProvider();

            if (chunkProvider != null && chunkProvider.isBlockInStructureBB(x, y, z)) {
                TFFeature nearbyFeature = ((TFWorldChunkManager) world.provider.worldChunkMgr).getFeatureAt(x, z, world);

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
        TargetPoint targetPoint = new TargetPoint(world.provider.dimensionId, (double) x, (double) y, (double) z, 64.0D);

        TwilightForestMod.genericChannel.sendToAllAround(message, targetPoint);
    }

    @SubscribeEvent
    public void livingAttack(LivingAttackEvent event) {
        if (event.entityLiving instanceof IMob && event.source.getEntity() instanceof EntityPlayer && !((EntityPlayer) event.source.getEntity()).capabilities.isCreativeMode && event.entityLiving.worldObj.provider instanceof WorldProviderTwilightForest && event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("tfEnforcedProgression")) {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) event.entityLiving.worldObj.provider).getChunkProvider();
            int mx = MathHelper.floor_double(event.entityLiving.posX);
            int my = MathHelper.floor_double(event.entityLiving.posY);
            int mz = MathHelper.floor_double(event.entityLiving.posZ);

            if (chunkProvider != null && chunkProvider.isBlockInStructureBB(mx, my, mz) && chunkProvider.isBlockProtected(mx, my, mz)) {
                TFFeature nearbyFeature = ((TFWorldChunkManager) event.entityLiving.worldObj.provider.worldChunkMgr).getFeatureAt(mx, mz, event.entityLiving.worldObj);

                if (!nearbyFeature.doesPlayerHaveRequiredAchievement((EntityPlayer) event.source.getEntity())) {
                    event.setResult(Result.DENY);
                    event.setCanceled(true);

                    for (int i = 0; i < 20; ++i) {
                        TwilightForestMod.proxy.spawnParticle(event.entityLiving.worldObj, "protection", event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public void playerLogsIn(PlayerLoggedInEvent event) {
        TwilightForestMod.hasBiomeIdConflicts = TFBiomeBase.areThereBiomeIdConflicts();
        if (TwilightForestMod.hasBiomeIdConflicts) {
            event.player.addChatMessage(new ChatComponentText("[TwilightForest] Biome ID conflict detected.  Fix by editing the config file."));
        }

        if (!event.player.worldObj.isRemote && event.player instanceof EntityPlayerMP) {
            this.sendEnforcedProgressionStatus((EntityPlayerMP) event.player, event.player.worldObj.getGameRules().getGameRuleBooleanValue("tfEnforcedProgression"));
        }

    }

    @SubscribeEvent
    public void playerPortals(PlayerChangedDimensionEvent event) {
        if (!event.player.worldObj.isRemote && event.player instanceof EntityPlayerMP && event.toDim == TwilightForestMod.dimensionID) {
            this.sendEnforcedProgressionStatus((EntityPlayerMP) event.player, event.player.worldObj.getGameRules().getGameRuleBooleanValue("tfEnforcedProgression"));
        }

    }

    private void sendEnforcedProgressionStatus(EntityPlayerMP player, boolean isEnforced) {
        TwilightForestMod.genericChannel.sendTo(TFGenericPacketHandler.makeEnforcedProgressionStatusPacket(isEnforced), player);
    }

    @SubscribeEvent
    public void worldLoaded(Load event) {
        if (!event.world.isRemote && !event.world.getGameRules().hasRule("tfEnforcedProgression")) {
            FMLLog.info("[TwilightForest] Loaded a world with the tfEnforcedProgression game rule not defined.  Defining it.", new Object[0]);
            event.world.getGameRules().addGameRule("tfEnforcedProgression", "true");
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
