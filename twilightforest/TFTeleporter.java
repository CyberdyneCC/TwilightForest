package twilightforest;

import cpw.mods.fml.common.FMLLog;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import twilightforest.biomes.TFBiomeBase;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFWorld;

public class TFTeleporter extends Teleporter {

    protected WorldServer myWorld;
    protected Random rand;

    public TFTeleporter(WorldServer par1WorldServer) {
        super(par1WorldServer);
        this.myWorld = par1WorldServer;
        if (this.rand == null) {
            this.rand = new Random();
        }

    }

    public void func_77185_a(Entity par1Entity, double x, double y, double z, float facing) {
        if (!this.func_77184_b(par1Entity, x, y, z, facing)) {
            if (par1Entity.field_70170_p.func_82736_K().func_82766_b("tfEnforcedProgression")) {
                int px = MathHelper.func_76128_c(par1Entity.field_70165_t);
                int pz = MathHelper.func_76128_c(par1Entity.field_70161_v);

                if (!this.isSafeBiomeAt(px, pz, par1Entity)) {
                    System.out.println("[TwilightForest] Portal destination looks unsafe, rerouting!");
                    ChunkCoordinates safeCoords = this.findSafeCoords(200, px, pz, par1Entity);

                    if (safeCoords != null) {
                        par1Entity.func_70012_b((double) safeCoords.field_71574_a, par1Entity.field_70163_u, (double) safeCoords.field_71573_c, 90.0F, 0.0F);
                        x = (double) safeCoords.field_71574_a;
                        z = (double) safeCoords.field_71573_c;
                        System.out.println("[TwilightForest] Safely rerouted!");
                    } else {
                        System.out.println("[TwilightForest] Did not find a safe spot at first try, trying again with longer range.");
                        safeCoords = this.findSafeCoords(400, px, pz, par1Entity);
                        if (safeCoords != null) {
                            par1Entity.func_70012_b((double) safeCoords.field_71574_a, par1Entity.field_70163_u, (double) safeCoords.field_71573_c, 90.0F, 0.0F);
                            x = (double) safeCoords.field_71574_a;
                            z = (double) safeCoords.field_71573_c;
                            System.out.println("[TwilightForest] Safely rerouted to long range portal.  Return trip not guaranteed.");
                        } else {
                            System.out.println("[TwilightForest] Did not find a safe spot.");
                        }
                    }
                }
            }

            this.func_85188_a(par1Entity);
            this.func_77184_b(par1Entity, x, y, z, facing);
        }

    }

    private ChunkCoordinates findSafeCoords(int range, int x, int z, Entity par1Entity) {
        for (int i = 0; i < 25; ++i) {
            int dx = x + (this.rand.nextInt(range) - this.rand.nextInt(range));
            int dz = z + (this.rand.nextInt(range) - this.rand.nextInt(range));

            if (this.isSafeBiomeAt(dx, dz, par1Entity)) {
                return new ChunkCoordinates(dx, 100, dz);
            }
        }

        return null;
    }

    boolean isSafeBiomeAt(int x, int z, Entity par1Entity) {
        BiomeGenBase biomeAt = this.myWorld.func_72807_a(x, z);

        if (biomeAt instanceof TFBiomeBase && par1Entity instanceof EntityPlayerMP) {
            TFBiomeBase tfBiome = (TFBiomeBase) biomeAt;
            EntityPlayerMP player = (EntityPlayerMP) par1Entity;

            return tfBiome.doesPlayerHaveRequiredAchievement(player);
        } else {
            return true;
        }
    }

    public boolean func_77184_b(Entity entity, double par3, double par5, double par7, float par9) {
        short c = 200;
        double d = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.func_76128_c(entity.field_70165_t);
        int i1 = MathHelper.func_76128_c(entity.field_70161_v);

        double portalZ;

        for (int k1 = l - c; k1 <= l + c; ++k1) {
            double l1 = (double) k1 + 0.5D - entity.field_70165_t;

            for (int portalX = i1 - c; portalX <= i1 + c; ++portalX) {
                double d3 = (double) portalX + 0.5D - entity.field_70161_v;

                for (int k2 = TFWorld.MAXHEIGHT - 1; k2 >= 0; --k2) {
                    if (this.isBlockPortal(this.myWorld, k1, k2, portalX)) {
                        while (this.isBlockPortal(this.myWorld, k1, k2 - 1, portalX)) {
                            --k2;
                        }

                        portalZ = (double) k2 + 0.5D - entity.field_70163_u;
                        double xOffset = l1 * l1 + portalZ * portalZ + d3 * d3;

                        if (d < 0.0D || xOffset < d) {
                            d = xOffset;
                            i = k1;
                            j = k2;
                            k = portalX;
                        }
                    }
                }
            }
        }

        if (d < 0.0D) {
            return false;
        } else {
            double d0 = (double) i + 0.5D;
            double portalY = (double) j + 0.5D;

            portalZ = (double) k + 0.5D;
            if (this.isBlockPortal(this.myWorld, i - 1, j, k)) {
                d0 -= 0.5D;
            }

            if (this.isBlockPortal(this.myWorld, i + 1, j, k)) {
                d0 += 0.5D;
            }

            if (this.isBlockPortal(this.myWorld, i, j, k - 1)) {
                portalZ -= 0.5D;
            }

            if (this.isBlockPortal(this.myWorld, i, j, k + 1)) {
                portalZ += 0.5D;
            }

            int i = 0;

            int zOffset;

            for (zOffset = 0; i == zOffset && i == 0; zOffset = this.rand.nextInt(3) - this.rand.nextInt(3)) {
                i = this.rand.nextInt(3) - this.rand.nextInt(3);
            }

            entity.func_70012_b(d0 + (double) i, portalY + 1.0D, portalZ + (double) zOffset, entity.field_70177_z, 0.0F);
            entity.field_70159_w = entity.field_70181_x = entity.field_70179_y = 0.0D;
            return true;
        }
    }

    public boolean isBlockPortal(World world, int x, int y, int z) {
        return world.func_147439_a(x, y, z) == TFBlocks.portal;
    }

    public boolean func_85188_a(Entity entity) {
        ChunkCoordinates spot = this.findPortalCoords(entity, true);

        if (spot != null) {
            FMLLog.info("[TwilightForest] Found ideal portal spot", new Object[0]);
            this.makePortalAt(this.myWorld, spot.field_71574_a, spot.field_71572_b, spot.field_71573_c);
            return true;
        } else {
            FMLLog.info("[TwilightForest] Did not find ideal portal spot, shooting for okay one", new Object[0]);
            spot = this.findPortalCoords(entity, false);
            if (spot != null) {
                FMLLog.info("[TwilightForest] Found okay portal spot", new Object[0]);
                this.makePortalAt(this.myWorld, spot.field_71574_a, spot.field_71572_b, spot.field_71573_c);
                return true;
            } else {
                FMLLog.info("[TwilightForest] Did not even find an okay portal spot, just making a random one", new Object[0]);
                double yFactor = this.myWorld.field_73011_w.field_76574_g == 0 ? 2.0D : 0.5D;
                int entityX = MathHelper.func_76128_c(entity.field_70165_t);
                int entityY = MathHelper.func_76128_c(entity.field_70163_u * yFactor);
                int entityZ = MathHelper.func_76128_c(entity.field_70161_v);

                this.makePortalAt(this.myWorld, entityX, entityY, entityZ);
                return false;
            }
        }
    }

    public ChunkCoordinates findPortalCoords(Entity entity, boolean ideal) {
        double yFactor = this.myWorld.field_73011_w.field_76574_g == 0 ? 2.0D : 0.5D;
        int entityX = MathHelper.func_76128_c(entity.field_70165_t);
        int entityZ = MathHelper.func_76128_c(entity.field_70161_v);
        double spotWeight = -1.0D;
        ChunkCoordinates spot = null;
        byte range = 16;

        for (int rx = entityX - range; rx <= entityX + range; ++rx) {
            double xWeight = (double) rx + 0.5D - entity.field_70165_t;

            for (int rz = entityZ - range; rz <= entityZ + range; ++rz) {
                double zWeight = (double) rz + 0.5D - entity.field_70161_v;

                for (int ry = 127; ry >= 0; --ry) {
                    if (this.myWorld.func_147437_c(rx, ry, rz)) {
                        while (ry > 0 && this.myWorld.func_147437_c(rx, ry - 1, rz)) {
                            --ry;
                        }

                        if (ideal) {
                            if (!this.isIdealPortal(rx, rz, ry)) {
                                continue;
                            }
                        } else if (!this.isOkayPortal(rx, rz, ry)) {
                            continue;
                        }

                        double yWeight = (double) ry + 0.5D - entity.field_70163_u * yFactor;
                        double rPosWeight = xWeight * xWeight + yWeight * yWeight + zWeight * zWeight;

                        if (spotWeight < 0.0D || rPosWeight < spotWeight) {
                            spotWeight = rPosWeight;
                            spot = new ChunkCoordinates(rx, ry, rz);
                        }
                    }
                }
            }
        }

        return spot;
    }

    public boolean isIdealPortal(int rx, int rz, int ry) {
        for (int potentialZ = 0; potentialZ < 4; ++potentialZ) {
            for (int potentialX = 0; potentialX < 4; ++potentialX) {
                for (int potentialY = -1; potentialY < 3; ++potentialY) {
                    int tx = rx + (potentialX - 1);
                    int ty = ry + potentialY;
                    int tz = rz + (potentialZ - 1);

                    if (potentialY == -1 && this.myWorld.func_147439_a(tx, ty, tz).func_149688_o() != Material.field_151577_b || potentialY >= 0 && !this.myWorld.func_147439_a(tx, ty, tz).func_149688_o().func_76222_j()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isOkayPortal(int rx, int rz, int ry) {
        for (int potentialZ = 0; potentialZ < 4; ++potentialZ) {
            for (int potentialX = 0; potentialX < 4; ++potentialX) {
                for (int potentialY = -1; potentialY < 3; ++potentialY) {
                    int tx = rx + (potentialX - 1);
                    int ty = ry + potentialY;
                    int tz = rz + (potentialZ - 1);

                    if (potentialY == -1 && !this.myWorld.func_147439_a(tx, ty, tz).func_149688_o().func_76220_a() || potentialY >= 0 && !this.myWorld.func_147439_a(tx, ty, tz).func_149688_o().func_76222_j()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void makePortalAt(World world, int px, int py, int pz) {
        if (py < 30) {
            py = 30;
        }

        world.getClass();
        if (py > 118) {
            world.getClass();
            py = 118;
        }

        --py;
        world.func_147449_b(px - 1, py + 0, pz - 1, Blocks.field_150349_c);
        world.func_147449_b(px + 0, py + 0, pz - 1, Blocks.field_150349_c);
        world.func_147449_b(px + 1, py + 0, pz - 1, Blocks.field_150349_c);
        world.func_147449_b(px + 2, py + 0, pz - 1, Blocks.field_150349_c);
        world.func_147449_b(px - 1, py + 0, pz + 0, Blocks.field_150349_c);
        world.func_147449_b(px + 2, py + 0, pz + 0, Blocks.field_150349_c);
        world.func_147449_b(px - 1, py + 0, pz + 1, Blocks.field_150349_c);
        world.func_147449_b(px + 2, py + 0, pz + 1, Blocks.field_150349_c);
        world.func_147449_b(px - 1, py + 0, pz + 2, Blocks.field_150349_c);
        world.func_147449_b(px + 0, py + 0, pz + 2, Blocks.field_150349_c);
        world.func_147449_b(px + 1, py + 0, pz + 2, Blocks.field_150349_c);
        world.func_147449_b(px + 2, py + 0, pz + 2, Blocks.field_150349_c);
        world.func_147449_b(px + 0, py - 1, pz + 0, Blocks.field_150346_d);
        world.func_147449_b(px + 1, py - 1, pz + 0, Blocks.field_150346_d);
        world.func_147449_b(px + 0, py - 1, pz + 1, Blocks.field_150346_d);
        world.func_147449_b(px + 1, py - 1, pz + 1, Blocks.field_150346_d);
        world.func_147465_d(px + 0, py + 0, pz + 0, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 1, py + 0, pz + 0, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 0, py + 0, pz + 1, TFBlocks.portal, 0, 2);
        world.func_147465_d(px + 1, py + 0, pz + 1, TFBlocks.portal, 0, 2);

        for (int dx = -1; dx <= 2; ++dx) {
            for (int dz = -1; dz <= 2; ++dz) {
                for (int dy = 1; dy <= 5; ++dy) {
                    world.func_147449_b(px + dx, py + dy, pz + dz, Blocks.field_150350_a);
                }
            }
        }

        world.func_147465_d(px - 1, py + 1, pz - 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 0, py + 1, pz - 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 1, py + 1, pz - 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 2, py + 1, pz - 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px - 1, py + 1, pz + 0, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 2, py + 1, pz + 0, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px - 1, py + 1, pz + 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 2, py + 1, pz + 1, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px - 1, py + 1, pz + 2, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 0, py + 1, pz + 2, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 1, py + 1, pz + 2, this.randNatureBlock(world.field_73012_v), 0, 2);
        world.func_147465_d(px + 2, py + 1, pz + 2, this.randNatureBlock(world.field_73012_v), 0, 2);
    }

    public Block randNatureBlock(Random random) {
        Block[] block = new Block[] { Blocks.field_150338_P, Blocks.field_150337_Q, Blocks.field_150329_H, Blocks.field_150328_O, Blocks.field_150327_N};

        return block[random.nextInt(block.length)];
    }
}
