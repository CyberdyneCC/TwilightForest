package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.MapData.MapInfo;
import twilightforest.TFFeature;
import twilightforest.TFMagicMapData;
import twilightforest.TFMapPacketHandler;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.world.TFWorldChunkManager;

public class ItemTFMagicMap extends ItemMap {

    public static final String STR_ID = "magicmap";

    @SideOnly(Side.CLIENT)
    public static TFMagicMapData getMPMapData(int par0, World par1World) {
        String mapName = "magicmap_" + par0;
        TFMagicMapData mapData = (TFMagicMapData) par1World.func_72943_a(TFMagicMapData.class, mapName);

        if (mapData == null) {
            mapData = new TFMagicMapData(mapName);
            par1World.func_72823_a(mapName, mapData);
        }

        return mapData;
    }

    public TFMagicMapData getMapData(ItemStack par1ItemStack, World par2World) {
        String mapName = "magicmap_" + par1ItemStack.func_77960_j();
        TFMagicMapData mapData = (TFMagicMapData) par2World.func_72943_a(TFMagicMapData.class, mapName);

        if (mapData == null && !par2World.field_72995_K) {
            par1ItemStack.func_77964_b(par2World.func_72841_b("magicmap"));
            mapName = "magicmap_" + par1ItemStack.func_77960_j();
            mapData = new TFMagicMapData(mapName);
            mapData.field_76201_a = par2World.func_72912_H().func_76079_c();
            mapData.field_76199_b = par2World.func_72912_H().func_76074_e();
            mapData.field_76197_d = 4;
            mapData.field_76200_c = par2World.field_73011_w.field_76574_g;
            mapData.func_76185_a();
            par2World.func_72823_a(mapName, mapData);
        }

        return mapData;
    }

    public void updateMapData(World par1World, Entity par2Entity, TFMagicMapData par3MapData) {
        if (par1World.field_73011_w.field_76574_g == par3MapData.field_76200_c && par2Entity instanceof EntityPlayer) {
            short xSize = 128;
            short zSize = 128;
            int scaleFactor = 1 << par3MapData.field_76197_d;
            int xCenter = par3MapData.field_76201_a;
            int zCenter = par3MapData.field_76199_b;
            int xDraw = MathHelper.func_76128_c(par2Entity.field_70165_t - (double) xCenter) / scaleFactor + xSize / 2;
            int zDraw = MathHelper.func_76128_c(par2Entity.field_70161_v - (double) zCenter) / scaleFactor + zSize / 2;
            int drawSize = 512 / scaleFactor;
            MapInfo mapInfo = par3MapData.func_82568_a((EntityPlayer) par2Entity);

            ++mapInfo.field_82569_d;

            for (int xStep = xDraw - drawSize + 1; xStep < xDraw + drawSize; ++xStep) {
                if ((xStep & 15) == (mapInfo.field_82569_d & 15)) {
                    int highNumber = 255;
                    int lowNumber = 0;

                    for (int zStep = zDraw - drawSize - 1; zStep < zDraw + drawSize; ++zStep) {
                        if (xStep >= 0 && zStep >= -1 && xStep < xSize && zStep < zSize) {
                            int xOffset = xStep - xDraw;
                            int zOffset = zStep - zDraw;
                            boolean flag = xOffset * xOffset + zOffset * zOffset > (drawSize - 2) * (drawSize - 2);
                            int xDraw2 = (xCenter / scaleFactor + xStep - xSize / 2) * scaleFactor;
                            int zDraw2 = (zCenter / scaleFactor + zStep - zSize / 2) * scaleFactor;
                            int[] biomeFrequencies = new int[256];

                            for (int xStep2 = 0; xStep2 < scaleFactor; ++xStep2) {
                                for (int zStep2 = 0; zStep2 < scaleFactor; ++zStep2) {
                                    int biomeID = par1World.func_72807_a(xDraw2 + xStep2, zDraw2 + zStep2).field_76756_M;

                                    ++biomeFrequencies[biomeID];
                                    if (biomeID == BiomeGenBase.field_76781_i.field_76756_M || biomeID == TFBiomeBase.stream.field_76756_M) {
                                        biomeFrequencies[biomeID] += 2;
                                    }

                                    if (par1World.func_72959_q() instanceof TFWorldChunkManager) {
                                        TFWorldChunkManager biomeIDToShow = (TFWorldChunkManager) par1World.func_72959_q();

                                        if (biomeIDToShow.isInFeatureChunk(par1World, xDraw2 + xStep2, zDraw2 + zStep2) && zStep >= 0 && xOffset * xOffset + zOffset * zOffset < drawSize * drawSize) {
                                            par3MapData.addFeatureToMap(TFFeature.getNearestFeature(xDraw2 + xStep2 >> 4, zDraw2 + zStep2 >> 4, par1World), xDraw2, zDraw2);
                                        }
                                    }
                                }
                            }

                            byte b0 = 0;
                            int highestFrequency = 0;

                            for (int existingColor = 0; existingColor < 256; ++existingColor) {
                                if (biomeFrequencies[existingColor] > highestFrequency) {
                                    b0 = (byte) existingColor;
                                    highestFrequency = biomeFrequencies[existingColor];
                                }
                            }

                            ++b0;
                            if (zStep >= 0 && xOffset * xOffset + zOffset * zOffset < drawSize * drawSize && (!flag || (xStep + zStep & 1) != 0)) {
                                byte b1 = par3MapData.field_76198_e[xStep + zStep * xSize];

                                if (b1 != b0) {
                                    if (highNumber > zStep) {
                                        highNumber = zStep;
                                    }

                                    if (lowNumber < zStep) {
                                        lowNumber = zStep;
                                    }

                                    par3MapData.field_76198_e[xStep + zStep * xSize] = b0;
                                }
                            }
                        }
                    }

                    if (highNumber <= lowNumber) {
                        par3MapData.func_76194_a(xStep, highNumber, lowNumber);
                    }
                }
            }
        }

    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (!par2World.field_72995_K) {
            TFMagicMapData mapData = this.getMapData(par1ItemStack, par2World);

            if (par3Entity instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) par3Entity;

                mapData.func_76191_a(entityplayer, par1ItemStack);
            }

            if (par5) {
                this.updateMapData(par2World, par3Entity, mapData);
            }
        }

    }

    public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.uncommon;
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return false;
    }

    public Packet func_150911_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        byte[] mapBytes = this.getMapData(par1ItemStack, par2World).func_76193_a(par1ItemStack, par2World, par3EntityPlayer);

        if (mapBytes == null) {
            return null;
        } else {
            if (mapBytes[0] == 1 && par2World.field_73012_v.nextInt(4) == 0) {
                this.getMapData(par1ItemStack, par2World).checkExistingFeatures(par2World);
                mapBytes = this.getMapData(par1ItemStack, par2World).makeFeatureStorageArray();
            }

            short uniqueID = (short) par1ItemStack.func_77960_j();

            return TFMapPacketHandler.makeMagicMapPacket("magicmap", uniqueID, mapBytes);
        }
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        return ("" + StatCollector.func_74838_a(this.func_77657_g(par1ItemStack) + ".name") + " #" + par1ItemStack.func_77960_j()).trim();
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
