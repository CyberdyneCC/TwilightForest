package twilightforest.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.world.WorldProviderTwilightForest;

public class ItemTFMagicBeans extends ItemTF {

    public ItemTFMagicBeans() {
        this.makeRare();
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block blockAt = world.func_147439_a(x, y, z);
        int minY = y + 1;
        int maxY = Math.max(y + 100, (int) (this.getCloudHeight(world) + 25.0F));

        if (y < maxY && blockAt == TFBlocks.uberousSoil) {
            if (!world.field_72995_K) {
                this.makeHugeStalk(world, x, z, minY, maxY);
            }

            return true;
        } else {
            return false;
        }
    }

    private float getCloudHeight(World world) {
        if (world.field_73011_w instanceof WorldProviderTwilightForest) {
            return ((WorldProviderTwilightForest) world.field_73011_w).func_76571_f();
        } else {
            try {
                return world.field_73011_w.func_76571_f();
            } catch (NoSuchMethodError nosuchmethoderror) {
                return world.field_73011_w.field_76577_b.getCloudHeight();
            }
        }
    }

    private void makeHugeStalk(World world, int x, int z, int minY, int maxY) {
        int yOffset = world.field_73012_v.nextInt(100);
        float cScale = world.field_73012_v.nextFloat() * 0.25F + 0.125F;
        float rScale = world.field_73012_v.nextFloat() * 0.25F + 0.125F;
        float radius = 4.0F + MathHelper.func_76126_a((float) (minY + yOffset) * rScale) * 3.0F;

        x = (int) ((float) x - MathHelper.func_76126_a((float) (minY + yOffset) * cScale) * radius);
        z = (int) ((float) z - MathHelper.func_76134_b((float) (minY + yOffset) * cScale) * radius);
        int nextLeafY = minY + 10 + world.field_73012_v.nextInt(20);
        boolean isClear = true;

        for (int dy = minY; dy < maxY && isClear; ++dy) {
            radius = 5.0F + MathHelper.func_76126_a((float) (dy + yOffset) * rScale) * 2.5F;
            float cx = (float) x + MathHelper.func_76126_a((float) (dy + yOffset) * cScale) * radius;
            float cz = (float) z + MathHelper.func_76134_b((float) (dy + yOffset) * cScale) * radius;
            float stalkThickness = 2.5F;

            if (maxY - dy < 5) {
                stalkThickness *= (float) (maxY - dy) / 5.0F;
            }

            int minX = MathHelper.func_76141_d((float) x - radius - stalkThickness);
            int maxX = MathHelper.func_76123_f((float) x + radius + stalkThickness);
            int minZ = MathHelper.func_76141_d((float) z - radius - stalkThickness);
            int maxZ = MathHelper.func_76123_f((float) z + radius + stalkThickness);

            int lx;
            int lz;

            for (lx = minX; lx < maxX; ++lx) {
                for (lz = minZ; lz < maxZ; ++lz) {
                    if (((float) lx - cx) * ((float) lx - cx) + ((float) lz - cz) * ((float) lz - cz) < stalkThickness * stalkThickness) {
                        isClear &= this.tryToPlaceStalk(world, lx, dy, lz);
                    }
                }
            }

            if (dy == nextLeafY) {
                lx = (int) ((float) x + MathHelper.func_76126_a((float) (dy + yOffset) * cScale) * (radius + stalkThickness));
                lz = (int) ((float) z + MathHelper.func_76134_b((float) (dy + yOffset) * cScale) * (radius + stalkThickness));
                this.placeLeaves(world, lx, dy, lz);
                nextLeafY = dy + 5 + world.field_73012_v.nextInt(10);
            }
        }

    }

    private void placeLeaves(World world, int x, int y, int z) {
        world.func_147449_b(x, y, z, TFBlocks.hugeStalk);

        int dx;
        int dz;

        for (dx = -1; dx <= 1; ++dx) {
            for (dz = -1; dz <= 1; ++dz) {
                this.tryToPlaceLeaves(world, x + dx, y - 1, z + dz);
                this.tryToPlaceLeaves(world, x + dx, y + 1, z + dz);
            }
        }

        for (dx = -2; dx <= 2; ++dx) {
            for (dz = -2; dz <= 2; ++dz) {
                if (dx != 2 && dx != -2 || dz != 2 && dz != -2) {
                    this.tryToPlaceLeaves(world, x + dx, y + 0, z + dz);
                }
            }
        }

    }

    private boolean tryToPlaceStalk(World world, int x, int y, int z) {
        Block blockThere = world.func_147439_a(x, y, z);

        if (blockThere != Blocks.field_150350_a && !blockThere.isReplaceable(world, x, y, z) && !blockThere.canBeReplacedByLeaves(world, x, y, z) && !blockThere.isLeaves(world, x, y, z) && !blockThere.canSustainLeaves(world, x, y, z)) {
            return false;
        } else {
            world.func_147449_b(x, y, z, TFBlocks.hugeStalk);
            return true;
        }
    }

    private void tryToPlaceLeaves(World world, int x, int y, int z) {
        Block blockThere = world.func_147439_a(x, y, z);

        if (blockThere == Blocks.field_150350_a || blockThere.canBeReplacedByLeaves(world, x, y, z)) {
            world.func_147465_d(x, y, z, TFBlocks.leaves3, 1, 2);
        }

    }
}
