package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTFGiantBlock extends Block {

    private IIcon[][][] giantIcon;
    private Block baseBlock;
    private boolean isSelfDestructing;

    public BlockTFGiantBlock(Block baseBlock) {
        super(baseBlock.func_149688_o());
        this.func_149672_a(baseBlock.field_149762_H);
        this.baseBlock = baseBlock;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.giantIcon = new GiantBlockIcon[4][4][6];

        for (int x = 0; x < 4; ++x) {
            for (int y = 0; y < 4; ++y) {
                for (int side = 0; side < 6; ++side) {
                    this.giantIcon[x][y][side] = new GiantBlockIcon(this.baseBlock.func_149733_h(side), x, y);
                }
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        switch (side) {
        case 0:
        default:
            return this.giantIcon[x & 3][z & 3][side];

        case 1:
            return this.giantIcon[x & 3][z & 3][side];

        case 2:
            return this.giantIcon[3 - (x & 3)][3 - (y & 3)][side];

        case 3:
            return this.giantIcon[x & 3][3 - (y & 3)][side];

        case 4:
            return this.giantIcon[z & 3][3 - (y & 3)][side];

        case 5:
            return this.giantIcon[3 - (z & 3)][3 - (y & 3)][side];
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.giantIcon[0][0][side];
    }

    public boolean func_149742_c(World world, int x, int y, int z) {
        int bx = x >> 2 << 2;
        int by = y >> 2 << 2;
        int bz = z >> 2 << 2;
        boolean allReplaceable = true;

        for (int dx = 0; dx < 4; ++dx) {
            for (int dy = 0; dy < 4; ++dy) {
                for (int dz = 0; dz < 4; ++dz) {
                    allReplaceable &= world.func_147439_a(bx + dx, by + dy, bz + dz).isReplaceable(world, bx + dx, by + dy, bz + dz);
                }
            }
        }

        return super.func_149742_c(world, x, y, z) && allReplaceable;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int bx = x >> 2 << 2;
        int by = y >> 2 << 2;
        int bz = z >> 2 << 2;

        return AxisAlignedBB.func_72330_a((double) bx + this.field_149759_B, (double) by + this.field_149760_C, (double) bz + this.field_149754_D, (double) bx + this.field_149755_E * 4.0D, (double) by + this.field_149756_F * 4.0D, (double) bz + this.field_149757_G * 4.0D);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
        if (!world.field_72995_K) {
            int bx = x >> 2 << 2;
            int by = y >> 2 << 2;
            int bz = z >> 2 << 2;

            for (int dx = 0; dx < 4; ++dx) {
                for (int dy = 0; dy < 4; ++dy) {
                    for (int dz = 0; dz < 4; ++dz) {
                        world.func_147465_d(bx + dx, by + dy, bz + dz, this, 0, 2);
                    }
                }
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        int bx = x >> 2 << 2;
        int by = y >> 2 << 2;
        int bz = z >> 2 << 2;
        Block blockThere = world.func_147439_a(x, y, z);
        int metaThere = world.func_72805_g(x, y, z);
        byte b0 = 16;

        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < b0; ++k1) {
                    double d0 = (double) bx + ((double) i1 + 0.5D) / 4.0D;
                    double d1 = (double) by + ((double) j1 + 0.5D) / 4.0D;
                    double d2 = (double) bz + ((double) k1 + 0.5D) / 4.0D;

                    effectRenderer.func_78873_a((new EntityDiggingFX(world, d0, d1, d2, d0 - (double) x - 0.5D, d1 - (double) y - 0.5D, d2 - (double) z - 0.5D, blockThere, metaThere)).func_70596_a(x, y, z));
                }
            }
        }

        return true;
    }

    public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
        this.setGiantBlockToAir(world, x, y, z);
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        world.func_147468_f(x, y, z);
        this.setGiantBlockToAir(world, x, y, z);
    }

    public void func_149725_f(World world, int x, int y, int z, int meta) {
        if (!this.isSelfDestructing && !this.func_149718_j(world, x, y, z)) {
            this.setGiantBlockToAir(world, x, y, z);
        }

    }

    private void setGiantBlockToAir(World world, int x, int y, int z) {
        this.isSelfDestructing = true;
        int bx = x >> 2 << 2;
        int by = y >> 2 << 2;
        int bz = z >> 2 << 2;

        for (int dx = 0; dx < 4; ++dx) {
            for (int dy = 0; dy < 4; ++dy) {
                for (int dz = 0; dz < 4; ++dz) {
                    if ((x != bx + dx || y != by + dy || z != bz + dz) && world.func_147439_a(bx + dx, by + dy, bz + dz) == this) {
                        world.func_147468_f(bx + dx, by + dy, bz + dz);
                    }
                }
            }
        }

        this.isSelfDestructing = false;
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        boolean allThisBlock = true;
        int bx = x >> 2 << 2;
        int by = y >> 2 << 2;
        int bz = z >> 2 << 2;

        for (int dx = 0; dx < 4; ++dx) {
            for (int dy = 0; dy < 4; ++dy) {
                for (int dz = 0; dz < 4; ++dz) {
                    allThisBlock &= world.func_147439_a(bx + dx, by + dy, bz + dz) == this;
                }
            }
        }

        return allThisBlock;
    }

    public int func_149656_h() {
        return 2;
    }
}
