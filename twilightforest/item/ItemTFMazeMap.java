package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData.MapCoord;
import net.minecraft.world.storage.MapData.MapInfo;
import twilightforest.TFMapPacketHandler;
import twilightforest.TFMazeMapData;

public class ItemTFMazeMap extends ItemMap {

    public static final String STR_ID = "mazemap";
    private static final int YSEARCH = 3;
    protected boolean mapOres;

    protected ItemTFMazeMap(boolean par2MapOres) {
        this.mapOres = par2MapOres;
    }

    @SideOnly(Side.CLIENT)
    public static TFMazeMapData getMPMapData(int par0, World par1World) {
        String mapName = "mazemap_" + par0;
        TFMazeMapData mapData = (TFMazeMapData) par1World.func_72943_a(TFMazeMapData.class, mapName);

        if (mapData == null) {
            mapData = new TFMazeMapData(mapName);
            par1World.func_72823_a(mapName, mapData);
        }

        return mapData;
    }

    public TFMazeMapData getMapData(ItemStack par1ItemStack, World par2World) {
        TFMazeMapData mapData = (TFMazeMapData) par2World.func_72943_a(TFMazeMapData.class, "mazemap_" + par1ItemStack.func_77960_j());

        if (mapData == null && !par2World.field_72995_K) {
            par1ItemStack.func_77964_b(par2World.func_72841_b("mazemap"));
            String mapName = "mazemap_" + par1ItemStack.func_77960_j();

            mapData = new TFMazeMapData(mapName);
            mapData.field_76201_a = par2World.func_72912_H().func_76079_c();
            mapData.field_76199_b = par2World.func_72912_H().func_76074_e();
            mapData.field_76197_d = 0;
            mapData.field_76200_c = par2World.field_73011_w.field_76574_g;
            mapData.func_76185_a();
            par2World.func_72823_a(mapName, mapData);
        }

        return mapData;
    }

    public void updateMapData(World par1World, Entity par2Entity, TFMazeMapData par3MapData) {
        int yDraw = MathHelper.func_76128_c(par2Entity.field_70163_u - (double) par3MapData.yCenter);

        if (par1World.field_73011_w.field_76574_g == par3MapData.field_76200_c && yDraw > -3 && yDraw < 3) {
            short xSize = 128;
            short zSize = 128;
            int xCenter = par3MapData.field_76201_a;
            int zCenter = par3MapData.field_76199_b;
            int xDraw = MathHelper.func_76128_c(par2Entity.field_70165_t - (double) xCenter) + xSize / 2;
            int zDraw = MathHelper.func_76128_c(par2Entity.field_70161_v - (double) zCenter) + zSize / 2;
            byte drawSize = 16;
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
                            int xDraw2 = xCenter + xStep - xSize / 2;
                            int zDraw2 = zCenter + zStep - zSize / 2;
                            Chunk chunk = par1World.func_72938_d(xDraw2, zDraw2);
                            int x15 = xDraw2 & 15;
                            int z15 = zDraw2 & 15;
                            int heightValue = par3MapData.yCenter;
                            Block blockID = chunk.func_150810_a(x15, heightValue, z15);
                            byte tint = 1;
                            int colorIndex = 0;

                            if (blockID == Blocks.field_150348_b && this.mapOres) {
                                for (int existingColor = -3; existingColor <= 3; ++existingColor) {
                                    Block tintedColor = chunk.func_150810_a(x15, heightValue + existingColor, z15);

                                    if (tintedColor != Blocks.field_150348_b) {
                                        blockID = tintedColor;
                                        if (existingColor > 0) {
                                            tint = 2;
                                        }

                                        if (existingColor < 0) {
                                            tint = 0;
                                        }
                                        break;
                                    }
                                }
                            }

                            if (blockID != Blocks.field_150350_a) {
                                MapColor mapcolor = blockID.func_149688_o().func_151565_r();

                                colorIndex = mapcolor.field_76290_q;
                            }

                            if (this.mapOres) {
                                if (blockID == Blocks.field_150365_q) {
                                    colorIndex = MapColor.field_151654_J.field_76290_q;
                                } else if (blockID == Blocks.field_150352_o) {
                                    colorIndex = MapColor.field_151647_F.field_76290_q;
                                } else if (blockID == Blocks.field_150366_p) {
                                    colorIndex = MapColor.field_151668_h.field_76290_q;
                                } else if (blockID == Blocks.field_150369_x) {
                                    colorIndex = MapColor.field_151652_H.field_76290_q;
                                } else if (blockID != Blocks.field_150450_ax && blockID != Blocks.field_150439_ay) {
                                    if (blockID == Blocks.field_150482_ag) {
                                        colorIndex = MapColor.field_151648_G.field_76290_q;
                                    } else if (blockID == Blocks.field_150412_bA) {
                                        colorIndex = MapColor.field_151653_I.field_76290_q;
                                    } else if (blockID != Blocks.field_150350_a && blockID.func_149739_a().toLowerCase().contains("ore")) {
                                        colorIndex = MapColor.field_151671_v.field_76290_q;
                                    }
                                } else {
                                    colorIndex = MapColor.field_151645_D.field_76290_q;
                                }
                            }

                            if (zStep >= 0 && xOffset * xOffset + zOffset * zOffset < drawSize * drawSize && (!flag || (xStep + zStep & 1) != 0)) {
                                byte b0 = par3MapData.field_76198_e[xStep + zStep * xSize];
                                byte b1 = (byte) (colorIndex * 4 + tint);

                                if (b0 != b1) {
                                    if (highNumber > zStep) {
                                        highNumber = zStep;
                                    }

                                    if (lowNumber < zStep) {
                                        lowNumber = zStep;
                                    }

                                    par3MapData.field_76198_e[xStep + zStep * xSize] = b1;
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

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean isActiveItem) {
        if (!par2World.field_72995_K) {
            TFMazeMapData mapData = this.getMapData(par1ItemStack, par2World);

            if (par3Entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) par3Entity;

                mapData.func_76191_a(player, par1ItemStack);
                int yProximity = MathHelper.func_76128_c(player.field_70163_u - (double) mapData.yCenter);

                if (yProximity < -3 || yProximity > 3) {
                    MapCoord mapCoord = (MapCoord) mapData.field_76203_h.get(player.func_70005_c_());

                    if (mapCoord != null) {
                        mapCoord.field_76216_a = 6;
                    }
                }
            }

            if (isActiveItem) {
                this.updateMapData(par2World, par3Entity, mapData);
            }
        }

    }

    public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par1ItemStack.func_77964_b(par2World.func_72841_b("mazemap"));
        String mapName = "mazemap_" + par1ItemStack.func_77960_j();
        TFMazeMapData mapData = new TFMazeMapData(mapName);

        par2World.func_72823_a(mapName, mapData);
        mapData.field_76201_a = MathHelper.func_76128_c(par3EntityPlayer.field_70165_t);
        mapData.yCenter = MathHelper.func_76128_c(par3EntityPlayer.field_70163_u);
        mapData.field_76199_b = MathHelper.func_76128_c(par3EntityPlayer.field_70161_v);
        mapData.field_76197_d = 0;
        mapData.field_76200_c = par2World.field_73011_w.field_76574_g;
        mapData.func_76185_a();
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return this.mapOres ? EnumRarity.epic : EnumRarity.uncommon;
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return false;
    }

    public Packet func_150911_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        byte[] mapBytes = this.getMapData(par1ItemStack, par2World).func_76193_a(par1ItemStack, par2World, par3EntityPlayer);

        if (mapBytes == null) {
            return null;
        } else {
            short uniqueID = (short) par1ItemStack.func_77960_j();

            return TFMapPacketHandler.makeMagicMapPacket("mazemap", uniqueID, mapBytes);
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
