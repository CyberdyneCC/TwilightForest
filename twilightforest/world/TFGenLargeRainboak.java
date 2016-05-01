package twilightforest.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;

public class TFGenLargeRainboak extends TFTreeGenerator {

    static final byte[] otherCoordPairs = new byte[] { (byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1};
    Random rand;
    World worldObj;
    int[] basePos;
    int heightLimit;
    int height;
    double heightAttenuation;
    double branchDensity;
    double branchSlope;
    double scaleWidth;
    double leafDensity;
    int trunkSize;
    int heightLimitLimit;
    int leafDistanceLimit;
    int[][] leafNodes;

    public TFGenLargeRainboak(boolean par1) {
        super(par1);
        this.rand = new Random();
        this.basePos = new int[] { 0, 0, 0};
        this.heightLimit = 0;
        this.heightAttenuation = 0.618D;
        this.branchDensity = 1.0D;
        this.branchSlope = 0.381D;
        this.scaleWidth = 1.0D;
        this.leafDensity = 1.0D;
        this.trunkSize = 1;
        this.heightLimitLimit = 12;
        this.leafDistanceLimit = 4;
    }

    public TFGenLargeRainboak() {
        this(false);
    }

    void generateLeafNodeList() {
        this.height = (int) ((double) this.heightLimit * this.heightAttenuation);
        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }

        int i = (int) (1.382D + Math.pow(this.leafDensity * (double) this.heightLimit / 13.0D, 2.0D));

        if (i < 1) {
            i = 1;
        }

        int[][] aint = new int[i * this.heightLimit][4];
        int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        int k = 1;
        int l = this.basePos[1] + this.height;
        int i1 = j - this.basePos[1];

        aint[0][0] = this.basePos[0];
        aint[0][1] = j;
        aint[0][2] = this.basePos[2];
        aint[0][3] = l;
        --j;

        while (i1 >= 0) {
            int j1 = 0;
            float f = this.layerSize(i1);

            if (f < 0.0F) {
                --j;
                --i1;
            } else {
                for (double d0 = 0.5D; j1 < i; ++j1) {
                    double d1 = this.scaleWidth * (double) f * ((double) this.rand.nextFloat() + 0.328D);
                    double d2 = (double) this.rand.nextFloat() * 2.0D * 3.141592653589793D;
                    int k1 = MathHelper.func_76128_c(d1 * Math.sin(d2) + (double) this.basePos[0] + d0);
                    int l1 = MathHelper.func_76128_c(d1 * Math.cos(d2) + (double) this.basePos[2] + d0);
                    int[] aint1 = new int[] { k1, j, l1};
                    int[] aint2 = new int[] { k1, j + this.leafDistanceLimit, l1};

                    if (this.checkBlockLine(aint1, aint2) == -1) {
                        int[] aint3 = new int[] { this.basePos[0], this.basePos[1], this.basePos[2]};
                        double d3 = Math.sqrt(Math.pow((double) Math.abs(this.basePos[0] - aint1[0]), 2.0D) + Math.pow((double) Math.abs(this.basePos[2] - aint1[2]), 2.0D));
                        double d4 = d3 * this.branchSlope;

                        if ((double) aint1[1] - d4 > (double) l) {
                            aint3[1] = l;
                        } else {
                            aint3[1] = (int) ((double) aint1[1] - d4);
                        }

                        if (this.checkBlockLine(aint3, aint1) == -1) {
                            aint[k][0] = k1;
                            aint[k][1] = j;
                            aint[k][2] = l1;
                            aint[k][3] = aint3[1];
                            ++k;
                        }
                    }
                }

                --j;
                --i1;
            }
        }

        this.leafNodes = new int[k][4];
        System.arraycopy(aint, 0, this.leafNodes, 0, k);
    }

    void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block leaves, int meta) {
        int i = (int) ((double) par4 + 0.618D);
        byte b0 = TFGenLargeRainboak.otherCoordPairs[par5];
        byte b1 = TFGenLargeRainboak.otherCoordPairs[par5 + 3];
        int[] aint = new int[] { par1, par2, par3};
        int[] aint1 = new int[] { 0, 0, 0};
        int j = -i;
        int k = -i;

        for (aint1[par5] = aint[par5]; j <= i; ++j) {
            aint1[b0] = aint[b0] + j;
            k = -i;

            while (k <= i) {
                double d0 = Math.pow((double) Math.abs(j) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k) + 0.5D, 2.0D);

                if (d0 > (double) (par4 * par4)) {
                    ++k;
                } else {
                    aint1[b1] = aint[b1] + k;
                    Block block = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);

                    if (block != Blocks.field_150350_a && !block.canBeReplacedByLeaves(this.worldObj, aint1[0], aint1[1], aint1[2])) {
                        ++k;
                    } else {
                        this.setBlockAndMetadata(this.worldObj, aint1[0], aint1[1], aint1[2], leaves, meta);
                        ++k;
                    }
                }
            }
        }

    }

    float layerSize(int par1) {
        if ((double) par1 < (double) ((float) this.heightLimit) * 0.3D) {
            return -1.618F;
        } else {
            float f = (float) this.heightLimit / 2.0F;
            float f1 = (float) this.heightLimit / 2.0F - (float) par1;
            float f2;

            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                f2 = 0.0F;
            } else {
                f2 = (float) Math.sqrt(Math.pow((double) Math.abs(f), 2.0D) - Math.pow((double) Math.abs(f1), 2.0D));
            }

            f2 *= 0.5F;
            return f2;
        }
    }

    float leafSize(int par1) {
        return par1 >= 0 && par1 < this.leafDistanceLimit ? (par1 != 0 && par1 != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    void generateLeafNode(int par1, int par2, int par3) {
        int i = par2;

        for (int j = par2 + this.leafDistanceLimit; i < j; ++i) {
            float f = this.leafSize(i - par2);

            this.genTreeLayer(par1, i, par3, f, (byte) 1, TFBlocks.leaves, 3);
        }

    }

    void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block log) {
        int[] aint = new int[] { 0, 0, 0};
        byte b0 = 0;

        byte b1;

        for (b1 = 0; b0 < 3; ++b0) {
            aint[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if (Math.abs(aint[b0]) > Math.abs(aint[b1])) {
                b1 = b0;
            }
        }

        if (aint[b1] != 0) {
            byte b2 = TFGenLargeRainboak.otherCoordPairs[b1];
            byte b3 = TFGenLargeRainboak.otherCoordPairs[b1 + 3];
            byte b4;

            if (aint[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint[b2] / (double) aint[b1];
            double d1 = (double) aint[b3] / (double) aint[b1];
            int[] aint1 = new int[] { 0, 0, 0};
            int i = 0;

            for (int j = aint[b1] + b4; i != j; i += b4) {
                aint1[b1] = MathHelper.func_76128_c((double) (par1ArrayOfInteger[b1] + i) + 0.5D);
                aint1[b2] = MathHelper.func_76128_c((double) par1ArrayOfInteger[b2] + (double) i * d0 + 0.5D);
                aint1[b3] = MathHelper.func_76128_c((double) par1ArrayOfInteger[b3] + (double) i * d1 + 0.5D);
                byte b5 = 0;
                int k = Math.abs(aint1[0] - par1ArrayOfInteger[0]);
                int l = Math.abs(aint1[2] - par1ArrayOfInteger[2]);
                int i1 = Math.max(k, l);

                if (i1 > 0) {
                    if (k == i1) {
                        b5 = 4;
                    } else if (l == i1) {
                        b5 = 8;
                    }
                }

                this.setBlockAndMetadata(this.worldObj, aint1[0], aint1[1], aint1[2], log, b5);
            }
        }

    }

    void generateLeaves() {
        int i = 0;

        for (int j = this.leafNodes.length; i < j; ++i) {
            int k = this.leafNodes[i][0];
            int l = this.leafNodes[i][1];
            int i1 = this.leafNodes[i][2];

            this.generateLeafNode(k, l, i1);
        }

    }

    boolean leafNodeNeedsBase(int par1) {
        return (double) par1 >= (double) this.heightLimit * 0.2D;
    }

    void generateTrunk() {
        int i = this.basePos[0];
        int j = this.basePos[1];
        int k = this.basePos[1] + this.height;
        int l = this.basePos[2];
        int[] aint = new int[] { i, j, l};
        int[] aint1 = new int[] { i, k, l};

        this.placeBlockLine(aint, aint1, TFBlocks.log);
        if (this.trunkSize == 2) {
            ++aint[0];
            ++aint1[0];
            this.placeBlockLine(aint, aint1, TFBlocks.log);
            ++aint[2];
            ++aint1[2];
            this.placeBlockLine(aint, aint1, TFBlocks.log);
            aint[0] += -1;
            aint1[0] += -1;
            this.placeBlockLine(aint, aint1, TFBlocks.log);
        }

    }

    void generateLeafNodeBases() {
        int i = 0;
        int j = this.leafNodes.length;

        for (int[] aint = new int[] { this.basePos[0], this.basePos[1], this.basePos[2]}; i < j; ++i) {
            int[] aint1 = this.leafNodes[i];
            int[] aint2 = new int[] { aint1[0], aint1[1], aint1[2]};

            aint[1] = aint1[3];
            int k = aint[1] - this.basePos[1];

            if (this.leafNodeNeedsBase(k)) {
                this.placeBlockLine(aint, aint2, TFBlocks.log);
            }
        }

    }

    int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
        int[] aint = new int[] { 0, 0, 0};
        byte b0 = 0;

        byte b1;

        for (b1 = 0; b0 < 3; ++b0) {
            aint[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if (Math.abs(aint[b0]) > Math.abs(aint[b1])) {
                b1 = b0;
            }
        }

        if (aint[b1] == 0) {
            return -1;
        } else {
            byte b2 = TFGenLargeRainboak.otherCoordPairs[b1];
            byte b3 = TFGenLargeRainboak.otherCoordPairs[b1 + 3];
            byte b4;

            if (aint[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double) aint[b2] / (double) aint[b1];
            double d1 = (double) aint[b3] / (double) aint[b1];
            int[] aint1 = new int[] { 0, 0, 0};
            int i = 0;

            int j;

            for (j = aint[b1] + b4; i != j; i += b4) {
                aint1[b1] = par1ArrayOfInteger[b1] + i;
                aint1[b2] = MathHelper.func_76128_c((double) par1ArrayOfInteger[b2] + (double) i * d0);
                aint1[b3] = MathHelper.func_76128_c((double) par1ArrayOfInteger[b3] + (double) i * d1);
                Block block = this.worldObj.func_147439_a(aint1[0], aint1[1], aint1[2]);

                if (block != Blocks.field_150350_a && block != Blocks.field_150362_t) {
                    break;
                }
            }

            return i == j ? -1 : Math.abs(i);
        }
    }

    boolean validTreeLocation() {
        int[] aint = new int[] { this.basePos[0], this.basePos[1], this.basePos[2]};
        int[] aint1 = new int[] { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2]};
        Block block = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);

        if (block != Blocks.field_150346_d && block != Blocks.field_150349_c) {
            return false;
        } else {
            int i = this.checkBlockLine(aint, aint1);

            if (i == -1) {
                return true;
            } else if (i < 6) {
                return false;
            } else {
                this.heightLimit = i;
                return true;
            }
        }
    }

    public void func_76487_a(double par1, double par3, double par5) {
        this.heightLimitLimit = (int) (par1 * 12.0D);
        if (par1 > 0.5D) {
            this.leafDistanceLimit = 5;
        }

        this.scaleWidth = par3;
        this.leafDensity = par5;
    }

    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        this.worldObj = par1World;
        long i = par2Random.nextLong();

        this.rand.setSeed(i);
        this.basePos[0] = par3;
        this.basePos[1] = par4;
        this.basePos[2] = par5;
        if (this.heightLimit == 0) {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }

        if (!this.validTreeLocation()) {
            return false;
        } else {
            this.generateLeafNodeList();
            this.generateLeaves();
            this.generateTrunk();
            this.generateLeafNodeBases();
            return true;
        }
    }
}
