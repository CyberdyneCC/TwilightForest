package twilightforest.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import twilightforest.TFCommonProxy;
import twilightforest.TFGenericPacketHandler;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import twilightforest.client.model.ModelTFArcticArmor;
import twilightforest.client.model.ModelTFBighorn;
import twilightforest.client.model.ModelTFBighornFur;
import twilightforest.client.model.ModelTFBlockGoblin;
import twilightforest.client.model.ModelTFBoar;
import twilightforest.client.model.ModelTFBunny;
import twilightforest.client.model.ModelTFDeathTome;
import twilightforest.client.model.ModelTFDeer;
import twilightforest.client.model.ModelTFFieryArmor;
import twilightforest.client.model.ModelTFFireBeetle;
import twilightforest.client.model.ModelTFGhast;
import twilightforest.client.model.ModelTFGoblinChain;
import twilightforest.client.model.ModelTFGoblinKnightLower;
import twilightforest.client.model.ModelTFGoblinKnightUpper;
import twilightforest.client.model.ModelTFHelmetCrab;
import twilightforest.client.model.ModelTFHydra;
import twilightforest.client.model.ModelTFHydraHead;
import twilightforest.client.model.ModelTFHydraNeck;
import twilightforest.client.model.ModelTFKnightPhantom2;
import twilightforest.client.model.ModelTFKnightlyArmor;
import twilightforest.client.model.ModelTFKobold;
import twilightforest.client.model.ModelTFLich;
import twilightforest.client.model.ModelTFLichMinion;
import twilightforest.client.model.ModelTFLoyalZombie;
import twilightforest.client.model.ModelTFMinoshroom;
import twilightforest.client.model.ModelTFMinotaur;
import twilightforest.client.model.ModelTFMosquitoSwarm;
import twilightforest.client.model.ModelTFNaga;
import twilightforest.client.model.ModelTFPenguin;
import twilightforest.client.model.ModelTFPhantomArmor;
import twilightforest.client.model.ModelTFPinchBeetle;
import twilightforest.client.model.ModelTFRaven;
import twilightforest.client.model.ModelTFRedcap;
import twilightforest.client.model.ModelTFSkeletonDruid;
import twilightforest.client.model.ModelTFSlimeBeetle;
import twilightforest.client.model.ModelTFSpikeBlock;
import twilightforest.client.model.ModelTFSquirrel;
import twilightforest.client.model.ModelTFTinyBird;
import twilightforest.client.model.ModelTFTowerBoss;
import twilightforest.client.model.ModelTFTowerGolem;
import twilightforest.client.model.ModelTFTroll;
import twilightforest.client.model.ModelTFWraith;
import twilightforest.client.model.ModelTFYeti;
import twilightforest.client.model.ModelTFYetiAlpha;
import twilightforest.client.model.ModelTFYetiArmor;
import twilightforest.client.particle.EntityTFAnnihilateFX;
import twilightforest.client.particle.EntityTFBossTearFX;
import twilightforest.client.particle.EntityTFGhastTrapFX;
import twilightforest.client.particle.EntityTFIceBeamFX;
import twilightforest.client.particle.EntityTFLargeFlameFX;
import twilightforest.client.particle.EntityTFLeafRuneFX;
import twilightforest.client.particle.EntityTFProtectionFX;
import twilightforest.client.particle.EntityTFSnowFX;
import twilightforest.client.particle.EntityTFSnowGuardianFX;
import twilightforest.client.particle.EntityTFSnowWarningFX;
import twilightforest.client.renderer.TFFieryItemRenderer;
import twilightforest.client.renderer.TFGiantBlockRenderer;
import twilightforest.client.renderer.TFGiantItemRenderer;
import twilightforest.client.renderer.TFIceItemRenderer;
import twilightforest.client.renderer.TFMagicMapRenderer;
import twilightforest.client.renderer.TFMazeMapRenderer;
import twilightforest.client.renderer.TileEntityTFCicadaRenderer;
import twilightforest.client.renderer.TileEntityTFFireflyRenderer;
import twilightforest.client.renderer.TileEntityTFMoonwormRenderer;
import twilightforest.client.renderer.TileEntityTFTrophyRenderer;
import twilightforest.client.renderer.blocks.RenderBlockTFCastleMagic;
import twilightforest.client.renderer.blocks.RenderBlockTFCritters;
import twilightforest.client.renderer.blocks.RenderBlockTFFireflyJar;
import twilightforest.client.renderer.blocks.RenderBlockTFHugeLilyPad;
import twilightforest.client.renderer.blocks.RenderBlockTFKnightMetal;
import twilightforest.client.renderer.blocks.RenderBlockTFMagicLeaves;
import twilightforest.client.renderer.blocks.RenderBlockTFNagastone;
import twilightforest.client.renderer.blocks.RenderBlockTFPedestal;
import twilightforest.client.renderer.blocks.RenderBlockTFPlants;
import twilightforest.client.renderer.blocks.RenderBlockTFThorns;
import twilightforest.client.renderer.entity.RenderTFApocalypseCube;
import twilightforest.client.renderer.entity.RenderTFBighorn;
import twilightforest.client.renderer.entity.RenderTFBiped;
import twilightforest.client.renderer.entity.RenderTFBird;
import twilightforest.client.renderer.entity.RenderTFBlockGoblin;
import twilightforest.client.renderer.entity.RenderTFBoar;
import twilightforest.client.renderer.entity.RenderTFBunny;
import twilightforest.client.renderer.entity.RenderTFChainBlock;
import twilightforest.client.renderer.entity.RenderTFCharm;
import twilightforest.client.renderer.entity.RenderTFCubeOfAnnihilation;
import twilightforest.client.renderer.entity.RenderTFDeer;
import twilightforest.client.renderer.entity.RenderTFFallingIce;
import twilightforest.client.renderer.entity.RenderTFGenericLiving;
import twilightforest.client.renderer.entity.RenderTFGiant;
import twilightforest.client.renderer.entity.RenderTFGoblinKnightUpper;
import twilightforest.client.renderer.entity.RenderTFHedgeSpider;
import twilightforest.client.renderer.entity.RenderTFHydra;
import twilightforest.client.renderer.entity.RenderTFHydraHead;
import twilightforest.client.renderer.entity.RenderTFHydraMortar;
import twilightforest.client.renderer.entity.RenderTFIceCrystal;
import twilightforest.client.renderer.entity.RenderTFIceExploder;
import twilightforest.client.renderer.entity.RenderTFIceShooter;
import twilightforest.client.renderer.entity.RenderTFKingSpider;
import twilightforest.client.renderer.entity.RenderTFKnightPhantom;
import twilightforest.client.renderer.entity.RenderTFKobold;
import twilightforest.client.renderer.entity.RenderTFLich;
import twilightforest.client.renderer.entity.RenderTFMazeSlime;
import twilightforest.client.renderer.entity.RenderTFMiniGhast;
import twilightforest.client.renderer.entity.RenderTFMinoshroom;
import twilightforest.client.renderer.entity.RenderTFMistWolf;
import twilightforest.client.renderer.entity.RenderTFMoonwormShot;
import twilightforest.client.renderer.entity.RenderTFNaga;
import twilightforest.client.renderer.entity.RenderTFNagaSegment;
import twilightforest.client.renderer.entity.RenderTFProtectionBox;
import twilightforest.client.renderer.entity.RenderTFQuestRam;
import twilightforest.client.renderer.entity.RenderTFSlideBlock;
import twilightforest.client.renderer.entity.RenderTFSlimeBeetle;
import twilightforest.client.renderer.entity.RenderTFSnowGuardian;
import twilightforest.client.renderer.entity.RenderTFSnowQueen;
import twilightforest.client.renderer.entity.RenderTFSnowQueenIceShield;
import twilightforest.client.renderer.entity.RenderTFSpikeBlock;
import twilightforest.client.renderer.entity.RenderTFSwarmSpider;
import twilightforest.client.renderer.entity.RenderTFThrownAxe;
import twilightforest.client.renderer.entity.RenderTFThrownIce;
import twilightforest.client.renderer.entity.RenderTFTinyBird;
import twilightforest.client.renderer.entity.RenderTFTinyFirefly;
import twilightforest.client.renderer.entity.RenderTFTowerBroodling;
import twilightforest.client.renderer.entity.RenderTFTowerGhast;
import twilightforest.client.renderer.entity.RenderTFTowerGolem;
import twilightforest.client.renderer.entity.RenderTFUrGhast;
import twilightforest.client.renderer.entity.RenderTFWinterWolf;
import twilightforest.client.renderer.entity.RenderTFWraith;
import twilightforest.client.renderer.entity.RenderTFYeti;
import twilightforest.entity.EntityTFAdherent;
import twilightforest.entity.EntityTFApocalypseCube;
import twilightforest.entity.EntityTFBlockGoblin;
import twilightforest.entity.EntityTFBoggard;
import twilightforest.entity.EntityTFChainBlock;
import twilightforest.entity.EntityTFCharmEffect;
import twilightforest.entity.EntityTFCubeOfAnnihilation;
import twilightforest.entity.EntityTFDeathTome;
import twilightforest.entity.EntityTFFireBeetle;
import twilightforest.entity.EntityTFGiantMiner;
import twilightforest.entity.EntityTFGoblinChain;
import twilightforest.entity.EntityTFGoblinKnightLower;
import twilightforest.entity.EntityTFGoblinKnightUpper;
import twilightforest.entity.EntityTFHedgeSpider;
import twilightforest.entity.EntityTFHelmetCrab;
import twilightforest.entity.EntityTFIceExploder;
import twilightforest.entity.EntityTFIceShooter;
import twilightforest.entity.EntityTFIceSnowball;
import twilightforest.entity.EntityTFKingSpider;
import twilightforest.entity.EntityTFKobold;
import twilightforest.entity.EntityTFLoyalZombie;
import twilightforest.entity.EntityTFMazeSlime;
import twilightforest.entity.EntityTFMiniGhast;
import twilightforest.entity.EntityTFMinotaur;
import twilightforest.entity.EntityTFMistWolf;
import twilightforest.entity.EntityTFMoonwormShot;
import twilightforest.entity.EntityTFMosquitoSwarm;
import twilightforest.entity.EntityTFNatureBolt;
import twilightforest.entity.EntityTFPinchBeetle;
import twilightforest.entity.EntityTFProtectionBox;
import twilightforest.entity.EntityTFRedcap;
import twilightforest.entity.EntityTFRedcapSapper;
import twilightforest.entity.EntityTFSkeletonDruid;
import twilightforest.entity.EntityTFSlideBlock;
import twilightforest.entity.EntityTFSlimeBeetle;
import twilightforest.entity.EntityTFSlimeProjectile;
import twilightforest.entity.EntityTFSnowGuardian;
import twilightforest.entity.EntityTFSpikeBlock;
import twilightforest.entity.EntityTFSwarmSpider;
import twilightforest.entity.EntityTFTomeBolt;
import twilightforest.entity.EntityTFTowerBroodling;
import twilightforest.entity.EntityTFTowerGhast;
import twilightforest.entity.EntityTFTowerGolem;
import twilightforest.entity.EntityTFTowerTermite;
import twilightforest.entity.EntityTFTroll;
import twilightforest.entity.EntityTFTwilightWandBolt;
import twilightforest.entity.EntityTFWinterWolf;
import twilightforest.entity.EntityTFWraith;
import twilightforest.entity.EntityTFYeti;
import twilightforest.entity.boss.EntityTFFallingIce;
import twilightforest.entity.boss.EntityTFHydra;
import twilightforest.entity.boss.EntityTFHydraHead;
import twilightforest.entity.boss.EntityTFHydraMortar;
import twilightforest.entity.boss.EntityTFHydraNeck;
import twilightforest.entity.boss.EntityTFIceBomb;
import twilightforest.entity.boss.EntityTFIceCrystal;
import twilightforest.entity.boss.EntityTFKnightPhantom;
import twilightforest.entity.boss.EntityTFLich;
import twilightforest.entity.boss.EntityTFLichBolt;
import twilightforest.entity.boss.EntityTFLichBomb;
import twilightforest.entity.boss.EntityTFLichMinion;
import twilightforest.entity.boss.EntityTFMinoshroom;
import twilightforest.entity.boss.EntityTFNaga;
import twilightforest.entity.boss.EntityTFNagaSegment;
import twilightforest.entity.boss.EntityTFSnowQueen;
import twilightforest.entity.boss.EntityTFSnowQueenIceShield;
import twilightforest.entity.boss.EntityTFThrownAxe;
import twilightforest.entity.boss.EntityTFThrownPick;
import twilightforest.entity.boss.EntityTFUrGhast;
import twilightforest.entity.boss.EntityTFYetiAlpha;
import twilightforest.entity.passive.EntityTFBighorn;
import twilightforest.entity.passive.EntityTFBoar;
import twilightforest.entity.passive.EntityTFBunny;
import twilightforest.entity.passive.EntityTFDeer;
import twilightforest.entity.passive.EntityTFMobileFirefly;
import twilightforest.entity.passive.EntityTFPenguin;
import twilightforest.entity.passive.EntityTFQuestRam;
import twilightforest.entity.passive.EntityTFRaven;
import twilightforest.entity.passive.EntityTFSquirrel;
import twilightforest.entity.passive.EntityTFTinyBird;
import twilightforest.entity.passive.EntityTFTinyFirefly;
import twilightforest.item.TFItems;
import twilightforest.tileentity.TileEntityTFCicada;
import twilightforest.tileentity.TileEntityTFFirefly;
import twilightforest.tileentity.TileEntityTFMoonworm;
import twilightforest.tileentity.TileEntityTFTrophy;

public class TFClientProxy extends TFCommonProxy {

    int critterRenderID;
    int plantRenderID;
    int blockComplexRenderID;
    int nagastoneRenderID;
    int magicLeavesRenderID;
    int pedestalRenderID;
    int thornsRenderID;
    int knightmetalBlockRenderID;
    int hugeLilyPadBlockRenderID;
    int castleMagicBlockRenderID;
    ModelBiped[] knightlyArmorModel;
    ModelBiped[] phantomArmorModel;
    ModelBiped[] yetiArmorModel;
    ModelBiped[] arcticArmorModel;
    ModelBiped[] fieryArmorModel;
    TFClientTicker clientTicker;
    TFClientEvents clientEvents;
    boolean isDangerOverlayShown;

    public void doPreLoadRegistration() {}

    public void doOnLoadRegistration() {
        Minecraft mc = FMLClientHandler.instance().getClient();

        this.clientTicker = new TFClientTicker();
        FMLCommonHandler.instance().bus().register(this.clientTicker);
        this.clientEvents = new TFClientEvents();
        MinecraftForge.EVENT_BUS.register(this.clientEvents);
        TFGenericPacketHandler genericPacketHandler = new TFGenericPacketHandler();

        TwilightForestMod.genericChannel.register(genericPacketHandler);
        RenderingRegistry.registerEntityRenderingHandler(EntityTFBoar.class, new RenderTFBoar(new ModelTFBoar(), new ModelPig(0.5F), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFBighorn.class, new RenderTFBighorn(new ModelTFBighorn(), new ModelTFBighornFur(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFDeer.class, new RenderTFDeer(new ModelTFDeer(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFRedcap.class, new RenderTFBiped(new ModelTFRedcap(), 0.625F, "redcap.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTinyFirefly.class, new RenderTFTinyFirefly());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSkeletonDruid.class, new RenderTFBiped(new ModelTFSkeletonDruid(), 0.5F, "skeletondruid.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFWraith.class, new RenderTFWraith(new ModelTFWraith(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHydra.class, new RenderTFHydra(new ModelTFHydra(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFLich.class, new RenderTFLich(new ModelTFLich(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFPenguin.class, new RenderTFBird(new ModelTFPenguin(), 1.0F, "penguin.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFLichMinion.class, new RenderTFBiped(new ModelTFLichMinion(), 1.0F, "textures/entity/zombie/zombie.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFLoyalZombie.class, new RenderTFBiped(new ModelTFLoyalZombie(), 1.0F, "textures/entity/zombie/zombie.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTinyBird.class, new RenderTFTinyBird(new ModelTFTinyBird(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSquirrel.class, new RenderTFGenericLiving(new ModelTFSquirrel(), 1.0F, "squirrel2.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFBunny.class, new RenderTFBunny(new ModelTFBunny(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFRaven.class, new RenderTFBird(new ModelTFRaven(), 1.0F, "raven.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFQuestRam.class, new RenderTFQuestRam());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFKobold.class, new RenderTFKobold(new ModelTFKobold(), 0.625F, "kobold.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFBoggard.class, new RenderTFBiped(new ModelTFLoyalZombie(), 0.625F, "kobold.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMosquitoSwarm.class, new RenderTFGenericLiving(new ModelTFMosquitoSwarm(), 0.625F, "mosquitoswarm.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFDeathTome.class, new RenderTFGenericLiving(new ModelTFDeathTome(), 0.625F, "textures/entity/enchanting_table_book.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMinotaur.class, new RenderTFBiped(new ModelTFMinotaur(), 0.625F, "minotaur.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMinoshroom.class, new RenderTFMinoshroom(new ModelTFMinoshroom(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFFireBeetle.class, new RenderTFGenericLiving(new ModelTFFireBeetle(), 0.625F, "firebeetle.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSlimeBeetle.class, new RenderTFSlimeBeetle(new ModelTFSlimeBeetle(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFPinchBeetle.class, new RenderTFGenericLiving(new ModelTFPinchBeetle(), 0.625F, "pinchbeetle.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMistWolf.class, new RenderTFMistWolf(new ModelWolf(), new ModelWolf(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMobileFirefly.class, new RenderTFTinyFirefly());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMiniGhast.class, new RenderTFMiniGhast(new ModelTFGhast(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTowerGolem.class, new RenderTFTowerGolem(new ModelTFTowerGolem(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTowerTermite.class, new RenderTFGenericLiving(new ModelSilverfish(), 0.3F, "towertermite.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTowerGhast.class, new RenderTFTowerGhast(new ModelTFGhast(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFUrGhast.class, new RenderTFUrGhast(new ModelTFTowerBoss(), 0.625F, 24.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFBlockGoblin.class, new RenderTFBlockGoblin(new ModelTFBlockGoblin(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFGoblinChain.class, new RenderTFSpikeBlock(new ModelTFGoblinChain(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSpikeBlock.class, new RenderTFSpikeBlock(new ModelTFSpikeBlock(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFGoblinKnightUpper.class, new RenderTFGoblinKnightUpper(new ModelTFGoblinKnightUpper(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFGoblinKnightLower.class, new RenderTFBiped(new ModelTFGoblinKnightLower(), 0.625F, "doublegoblin.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHelmetCrab.class, new RenderTFGenericLiving(new ModelTFHelmetCrab(), 0.625F, "helmetcrab.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFKnightPhantom.class, new RenderTFKnightPhantom(new ModelTFKnightPhantom2(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFNaga.class, new RenderTFNaga(new ModelTFNaga(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFNagaSegment.class, new RenderTFNagaSegment(new ModelTFNaga(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSwarmSpider.class, new RenderTFSwarmSpider());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFKingSpider.class, new RenderTFKingSpider());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTowerBroodling.class, new RenderTFTowerBroodling());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHedgeSpider.class, new RenderTFHedgeSpider());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFRedcapSapper.class, new RenderTFBiped(new ModelTFRedcap(), 0.625F, "redcapsapper.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMazeSlime.class, new RenderTFMazeSlime(new ModelSlime(16), new ModelSlime(0), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFYeti.class, new RenderTFYeti(new ModelTFYeti(), 0.625F, "yeti2.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFProtectionBox.class, new RenderTFProtectionBox());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFYetiAlpha.class, new RenderTFYeti(new ModelTFYetiAlpha(), 0.625F, "yetialpha.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFWinterWolf.class, new RenderTFWinterWolf(new ModelWolf(), new ModelWolf(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSnowGuardian.class, new RenderTFSnowGuardian());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFIceShooter.class, new RenderTFIceShooter());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFIceExploder.class, new RenderTFIceExploder());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSnowQueen.class, new RenderTFSnowQueen());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSnowQueenIceShield.class, new RenderTFSnowQueenIceShield());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTroll.class, new RenderTFBiped(new ModelTFTroll(), 0.625F, "troll.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFGiantMiner.class, new RenderTFGiant());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFIceCrystal.class, new RenderTFIceCrystal());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFChainBlock.class, new RenderTFChainBlock(new ModelTFSpikeBlock(), 0.625F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFCubeOfAnnihilation.class, new RenderTFCubeOfAnnihilation());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFApocalypseCube.class, new RenderTFApocalypseCube());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFAdherent.class, new RenderTFBiped(new ModelTFAdherent(), 0.625F, "adherent.png"));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFNatureBolt.class, new RenderSnowball(Items.field_151014_N));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFLichBolt.class, new RenderSnowball(Items.field_151079_bi));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTwilightWandBolt.class, new RenderSnowball(Items.field_151079_bi));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFTomeBolt.class, new RenderSnowball(Items.field_151121_aF));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHydraMortar.class, new RenderTFHydraMortar());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSlimeProjectile.class, new RenderSnowball(Items.field_151123_aH));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFMoonwormShot.class, new RenderTFMoonwormShot());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFCharmEffect.class, new RenderTFCharm(TFItems.charmOfLife1.func_77617_a(0)));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFLichBomb.class, new RenderSnowball(Items.field_151064_bs));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFThrownAxe.class, new RenderTFThrownAxe(TFItems.knightlyAxe));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFThrownPick.class, new RenderTFThrownAxe(TFItems.knightlyPick));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFFallingIce.class, new RenderTFFallingIce());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFIceBomb.class, new RenderTFThrownIce());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFIceSnowball.class, new RenderSnowball(Items.field_151126_ay));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFSlideBlock.class, new RenderTFSlideBlock());
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHydraHead.class, new RenderTFHydraHead(new ModelTFHydraHead(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTFHydraNeck.class, new RenderTFGenericLiving(new ModelTFHydraNeck(), 1.0F, "hydra4.png"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTFFirefly.class, new TileEntityTFFireflyRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTFCicada.class, new TileEntityTFCicadaRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTFMoonworm.class, new TileEntityTFMoonwormRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTFTrophy.class, new TileEntityTFTrophyRenderer());
        MinecraftForgeClient.registerItemRenderer(TFItems.magicMap, new TFMagicMapRenderer(mc.field_71474_y, mc.func_110434_K()));
        TFMazeMapRenderer mazeRenderer = new TFMazeMapRenderer(mc.field_71474_y, mc.func_110434_K());

        MinecraftForgeClient.registerItemRenderer(TFItems.mazeMap, mazeRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.oreMap, mazeRenderer);
        TFGiantItemRenderer giantRenderer = new TFGiantItemRenderer(mc.field_71474_y, mc.func_110434_K());

        MinecraftForgeClient.registerItemRenderer(TFItems.giantPick, giantRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.giantSword, giantRenderer);
        TFGiantBlockRenderer giantBlockRenderer = new TFGiantBlockRenderer(mc.field_71474_y, mc.func_110434_K());

        MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(TFBlocks.giantLeaves), giantBlockRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(TFBlocks.giantCobble), giantBlockRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(TFBlocks.giantLog), giantBlockRenderer);
        MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(TFBlocks.giantObsidian), giantBlockRenderer);
        TFFieryItemRenderer fieryRenderer = new TFFieryItemRenderer(mc.field_71474_y, mc.func_110434_K());

        MinecraftForgeClient.registerItemRenderer(TFItems.fieryPick, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fierySword, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fieryIngot, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fieryHelm, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fieryPlate, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fieryLegs, fieryRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.fieryBoots, fieryRenderer);
        TFIceItemRenderer iceRenderer = new TFIceItemRenderer(mc.field_71474_y, mc.func_110434_K());

        MinecraftForgeClient.registerItemRenderer(TFItems.iceSword, iceRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.glassSword, iceRenderer);
        MinecraftForgeClient.registerItemRenderer(TFItems.iceBow, iceRenderer);
        this.blockComplexRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFFireflyJar(this.blockComplexRenderID));
        this.plantRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFPlants(this.plantRenderID));
        this.critterRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFCritters(this.critterRenderID));
        this.nagastoneRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFNagastone(this.nagastoneRenderID));
        this.magicLeavesRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFMagicLeaves(this.magicLeavesRenderID));
        this.pedestalRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFPedestal(this.pedestalRenderID));
        this.thornsRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFThorns(this.thornsRenderID));
        this.knightmetalBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFKnightMetal(this.knightmetalBlockRenderID));
        this.hugeLilyPadBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFHugeLilyPad(this.hugeLilyPadBlockRenderID));
        this.castleMagicBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new RenderBlockTFCastleMagic(this.castleMagicBlockRenderID));
        this.knightlyArmorModel = new ModelBiped[4];
        this.knightlyArmorModel[0] = new ModelTFKnightlyArmor(0, 0.5F);
        this.knightlyArmorModel[1] = new ModelTFKnightlyArmor(1, 1.0F);
        this.knightlyArmorModel[2] = new ModelTFKnightlyArmor(2, 0.5F);
        this.knightlyArmorModel[3] = new ModelTFKnightlyArmor(3, 0.5F);
        this.phantomArmorModel = new ModelBiped[2];
        this.phantomArmorModel[0] = new ModelTFPhantomArmor(0, 0.5F);
        this.phantomArmorModel[1] = new ModelTFPhantomArmor(1, 1.0F);
        this.yetiArmorModel = new ModelBiped[4];
        this.yetiArmorModel[0] = new ModelTFYetiArmor(0, 0.6F);
        this.yetiArmorModel[1] = new ModelTFYetiArmor(1, 1.0F);
        this.yetiArmorModel[2] = new ModelTFYetiArmor(2, 0.4F);
        this.yetiArmorModel[3] = new ModelTFYetiArmor(3, 0.55F);
        this.arcticArmorModel = new ModelBiped[4];
        this.arcticArmorModel[0] = new ModelTFArcticArmor(0, 0.6F);
        this.arcticArmorModel[1] = new ModelTFArcticArmor(1, 1.0F);
        this.arcticArmorModel[2] = new ModelTFArcticArmor(2, 0.4F);
        this.arcticArmorModel[3] = new ModelTFArcticArmor(3, 0.55F);
        this.fieryArmorModel = new ModelBiped[4];
        this.fieryArmorModel[0] = new ModelTFFieryArmor(0, 0.5F);
        this.fieryArmorModel[1] = new ModelTFFieryArmor(1, 1.0F);
        this.fieryArmorModel[2] = new ModelTFFieryArmor(2, 0.5F);
        this.fieryArmorModel[3] = new ModelTFFieryArmor(3, 0.5F);
    }

    public int getCritterBlockRenderID() {
        return this.critterRenderID;
    }

    public int getPlantBlockRenderID() {
        return this.plantRenderID;
    }

    public int getComplexBlockRenderID() {
        return this.blockComplexRenderID;
    }

    public int getNagastoneBlockRenderID() {
        return this.nagastoneRenderID;
    }

    public int getMagicLeavesBlockRenderID() {
        return this.magicLeavesRenderID;
    }

    public int getPedestalBlockRenderID() {
        return this.pedestalRenderID;
    }

    public int getThornsBlockRenderID() {
        return this.thornsRenderID;
    }

    public int getKnightmetalBlockRenderID() {
        return this.knightmetalBlockRenderID;
    }

    public int getHugeLilyPadBlockRenderID() {
        return this.hugeLilyPadBlockRenderID;
    }

    public int getCastleMagicBlockRenderID() {
        return this.castleMagicBlockRenderID;
    }

    public int registerArmorRenderID(String prefix) {
        return RenderingRegistry.addNewArmourRendererPrefix(prefix);
    }

    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().field_71441_e;
    }

    public void spawnParticle(World world, String particleType, double x, double y, double z, double velX, double velY, double velZ) {
        Minecraft mc = FMLClientHandler.instance().getClient();

        if (mc != null && mc.field_71451_h != null && mc.field_71452_i != null && mc.field_71441_e == world) {
            double distX = mc.field_71451_h.field_70165_t - x;
            double distY = mc.field_71451_h.field_70163_u - y;
            double distZ = mc.field_71451_h.field_70161_v - z;
            Object particle = null;
            double maxDist = 64.0D;

            if (distX * distX + distY * distY + distZ * distZ < maxDist * maxDist) {
                if (particleType.equals("largeflame")) {
                    particle = new EntityTFLargeFlameFX(world, x, y, z, velX, velY, velZ);
                } else if (particleType.equals("hugesmoke")) {
                    particle = new EntitySmokeFX(world, x, y, z, velX, velY, velZ, 8.0F);
                } else if (particleType.equals("leafrune")) {
                    particle = new EntityTFLeafRuneFX(world, x, y, z, velX, velY, velZ);
                } else if (particleType.equals("bosstear")) {
                    particle = new EntityTFBossTearFX(world, x, y, z, velX, velY, velZ, Items.field_151073_bk);
                } else if (particleType.equals("ghasttrap")) {
                    particle = new EntityTFGhastTrapFX(world, x, y, z, velX, velY, velZ);
                } else if (particleType.equals("protection")) {
                    particle = new EntityTFProtectionFX(world, x, y, z, velX, velY, velZ);
                } else if (particleType.equals("snowstuff")) {
                    particle = new EntityTFSnowFX(world, x, y, z, velX, velY, velZ);
                } else if (particleType.equals("snowwarning")) {
                    particle = new EntityTFSnowWarningFX(world, x, y, z, velX, velY, velZ, 1.0F);
                } else if (particleType.equals("snowguardian")) {
                    particle = new EntityTFSnowGuardianFX(world, x, y, z, velX, velY, velZ, 0.75F);
                } else if (particleType.equals("icebeam")) {
                    particle = new EntityTFIceBeamFX(world, x, y, z, velX, velY, velZ, 0.75F);
                } else if (particleType.equals("annihilate")) {
                    particle = new EntityTFAnnihilateFX(world, x, y, z, velX, velY, velZ, 0.75F);
                }

                if (particle != null) {
                    ((EntityFX) particle).field_70169_q = ((EntityFX) particle).field_70165_t;
                    ((EntityFX) particle).field_70167_r = ((EntityFX) particle).field_70163_u;
                    ((EntityFX) particle).field_70166_s = ((EntityFX) particle).field_70161_v;
                    mc.field_71452_i.func_78873_a((EntityFX) particle);
                }
            }
        }

    }

    public ModelBiped getKnightlyArmorModel(int armorSlot) {
        return this.knightlyArmorModel[armorSlot];
    }

    public ModelBiped getPhantomArmorModel(int armorSlot) {
        return this.phantomArmorModel[armorSlot];
    }

    public ModelBiped getYetiArmorModel(int armorSlot) {
        return this.yetiArmorModel[armorSlot];
    }

    public ModelBiped getArcticArmorModel(int armorSlot) {
        return this.arcticArmorModel[armorSlot];
    }

    public ModelBiped getFieryArmorModel(int armorSlot) {
        return this.fieryArmorModel[armorSlot];
    }

    public boolean isDangerOverlayShown() {
        return this.isDangerOverlayShown;
    }

    public void setDangerOverlayShown(boolean isDangerOverlayShown) {
        this.isDangerOverlayShown = isDangerOverlayShown;
    }

    public void doBlockAnnihilateEffect(World worldObj, int blockX, int blockY, int blockZ) {
        byte four = 4;

        for (int dx = 0; dx < four; ++dx) {
            for (int dy = 0; dy < four; ++dy) {
                for (int dz = 0; dz < four; ++dz) {
                    double d0 = (double) blockX + ((double) dx + 0.5D) / (double) four;
                    double d1 = (double) blockY + ((double) dy + 0.5D) / (double) four;
                    double d2 = (double) blockZ + ((double) dz + 0.5D) / (double) four;
                    double gx = worldObj.field_73012_v.nextGaussian() * 0.2D;
                    double gy = worldObj.field_73012_v.nextGaussian() * 0.2D;
                    double gz = worldObj.field_73012_v.nextGaussian() * 0.2D;

                    TwilightForestMod.proxy.spawnParticle(worldObj, "annihilate", d0, d1, d2, gx, gy, gz);
                }
            }
        }

    }
}
