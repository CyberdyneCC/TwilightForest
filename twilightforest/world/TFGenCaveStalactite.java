package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TFGenCaveStalactite extends TFGenerator {

    public static TFGenCaveStalactite diamond = new TFGenCaveStalactite(Blocks.field_150482_ag, 0.5F, 4, 16);
    public static TFGenCaveStalactite lapis = new TFGenCaveStalactite(Blocks.field_150369_x, 0.8F, 8, 1);
    public static TFGenCaveStalactite emerald = new TFGenCaveStalactite(Blocks.field_150412_bA, 0.5F, 3, 12);
    public static TFGenCaveStalactite gold = new TFGenCaveStalactite(Blocks.field_150352_o, 0.6F, 6, 1);
    public static TFGenCaveStalactite redstone = new TFGenCaveStalactite(Blocks.field_150450_ax, 0.8F, 8, 1);
    public static TFGenCaveStalactite iron = new TFGenCaveStalactite(Blocks.field_150366_p, 0.7F, 8, 1);
    public static TFGenCaveStalactite coal = new TFGenCaveStalactite(Blocks.field_150365_q, 0.8F, 12, 1);
    public static TFGenCaveStalactite glowstone = new TFGenCaveStalactite(Blocks.field_150426_aN, 0.5F, 8, 1);
    public Block blockID;
    public boolean hang;
    public float sizeFactor;
    public int maxLength;
    public int minHeight;

    public TFGenCaveStalactite(Block blockType, float size, boolean down) {
        this.blockID = blockType;
        this.sizeFactor = size;
        this.maxLength = -1;
        this.minHeight = -1;
        this.hang = down;
    }

    public TFGenCaveStalactite(Block blockType, float size, int maxLength, int minHeight) {
        this.blockID = blockType;
        this.sizeFactor = size;
        this.maxLength = maxLength;
        this.minHeight = minHeight;
        this.hang = true;
    }

    public static TFGenCaveStalactite makeRandomOreStalactite(Random rand, int hillSize) {
        int s1;

        if (hillSize >= 3 || hillSize >= 2 && rand.nextInt(5) == 0) {
            s1 = rand.nextInt(13);
            if (s1 == 0 || s1 == 1) {
                return TFGenCaveStalactite.diamond;
            }

            if (s1 == 2 || s1 == 3) {
                return TFGenCaveStalactite.lapis;
            }

            if (s1 == 4) {
                return TFGenCaveStalactite.emerald;
            }
        }

        if (hillSize >= 2 || hillSize >= 1 && rand.nextInt(5) == 0) {
            s1 = rand.nextInt(6);
            if (s1 == 0) {
                return TFGenCaveStalactite.gold;
            }

            if (s1 == 1 || s1 == 2) {
                return TFGenCaveStalactite.redstone;
            }
        }

        s1 = rand.nextInt(5);
        return s1 != 0 && s1 != 1 ? (s1 != 2 && s1 != 3 ? TFGenCaveStalactite.glowstone : TFGenCaveStalactite.coal) : TFGenCaveStalactite.iron;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        int ceiling = Integer.MAX_VALUE;
        int floor = -1;

        int length;
        Material m;

        for (length = y; length < TFWorld.CHUNKHEIGHT; ++length) {
            m = world.func_147439_a(x, length, z).func_149688_o();
            if (m != Material.field_151579_a) {
                if (m != Material.field_151578_c && m != Material.field_151576_e) {
                    return false;
                }

                ceiling = length;
                break;
            }
        }

        if (ceiling == Integer.MAX_VALUE) {
            return false;
        } else {
            for (length = y; length > 4; --length) {
                m = world.func_147439_a(x, length, z).func_149688_o();
                if (m != Material.field_151579_a) {
                    if (m != Material.field_151578_c && m != Material.field_151576_e && !this.hang && m != Material.field_151586_h && !this.hang && m != Material.field_151587_i) {
                        return false;
                    }

                    floor = length;
                    break;
                }
            }

            length = (int) ((float) (ceiling - floor) * this.sizeFactor * random.nextFloat());
            if (this.maxLength > -1 && length > this.maxLength) {
                length = this.maxLength;
            }

            return this.minHeight > -1 && ceiling - floor - length < this.minHeight ? false : this.makeSpike(world, random, x, this.hang ? ceiling : floor, z, length);
        }
    }

    public boolean makeSpike(World world, Random random, int x, int y, int z, int maxLength) {
        int diameter = (int) ((double) maxLength / 4.5D);

        for (int dx = -diameter; dx <= diameter; ++dx) {
            for (int dz = -diameter; dz <= diameter; ++dz) {
                int absx = Math.abs(dx);
                int absz = Math.abs(dz);
                int dist = (int) ((double) Math.max(absx, absz) + (double) Math.min(absx, absz) * 0.5D);
                int spikeLength = 0;

                if (dist == 0) {
                    spikeLength = maxLength;
                }

                if (dist > 0) {
                    spikeLength = random.nextInt((int) ((double) maxLength / ((double) dist + 0.25D)));
                }

                int dir = this.hang ? -1 : 1;

                if (!world.func_147439_a(x + dx, y - dir, z + dz).func_149688_o().func_76220_a()) {
                    spikeLength = 0;
                }

                for (int dy = 0; dy != spikeLength * dir; dy += dir) {
                    this.setBlock(world, x + dx, y + dy, z + dz, this.blockID);
                }
            }
        }

        return true;
    }
}
