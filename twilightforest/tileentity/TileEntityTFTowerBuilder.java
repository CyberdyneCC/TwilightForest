package twilightforest.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import twilightforest.block.TFBlocks;

public class TileEntityTFTowerBuilder extends TileEntity {

    private static final int RANGE = 16;
    int ticksRunning = 0;
    int blockedCounter = 0;
    int ticksStopped = 0;
    public boolean makingBlocks = false;
    int blocksMade = 0;
    protected ChunkCoordinates lastBlockCoords;
    protected int nextFacing;
    protected EntityPlayer trackedPlayer;
    protected Block blockBuiltID;
    protected int blockBuiltMeta;

    public TileEntityTFTowerBuilder() {
        this.blockBuiltID = TFBlocks.towerTranslucent;
        this.blockBuiltMeta = 2;
    }

    public void startBuilding() {
        this.makingBlocks = true;
        this.blocksMade = 0;
        this.lastBlockCoords = new ChunkCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && this.makingBlocks) {
            if (this.trackedPlayer == null) {
                this.trackedPlayer = this.findClosestValidPlayer();
            }

            this.nextFacing = this.findNextFacing();
            ++this.ticksRunning;
            if (this.ticksRunning % 10 == 0 && this.lastBlockCoords != null && this.nextFacing != -1) {
                int nextX = this.lastBlockCoords.field_71574_a + Facing.field_71586_b[this.nextFacing];
                int nextY = this.lastBlockCoords.field_71572_b + Facing.field_71587_c[this.nextFacing];
                int nextZ = this.lastBlockCoords.field_71573_c + Facing.field_71585_d[this.nextFacing];

                if (this.blocksMade <= 16 && this.field_145850_b.func_147437_c(nextX, nextY, nextZ)) {
                    this.field_145850_b.func_147465_d(nextX, nextY, nextZ, this.blockBuiltID, this.blockBuiltMeta, 3);
                    this.field_145850_b.func_72926_e(1001, nextX, nextY, nextZ, 0);
                    this.lastBlockCoords.field_71574_a = nextX;
                    this.lastBlockCoords.field_71572_b = nextY;
                    this.lastBlockCoords.field_71573_c = nextZ;
                    this.blockedCounter = 0;
                    ++this.blocksMade;
                } else {
                    ++this.blockedCounter;
                }
            }

            if (this.blockedCounter > 0) {
                this.makingBlocks = false;
                this.trackedPlayer = null;
                this.ticksStopped = 0;
            }
        } else if (!this.field_145850_b.field_72995_K && !this.makingBlocks) {
            this.trackedPlayer = null;
            if (++this.ticksStopped == 60) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFBlocks.towerDevice, 8, 3);
                this.field_145850_b.func_147464_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFBlocks.towerDevice, 4);
            }
        }

    }

    private boolean isInBounds(int nextX, int nextY, int nextZ) {
        return nextX > this.field_145851_c - 16 && nextX < this.field_145851_c + 16 && nextY > this.field_145848_d - 16 && nextY < this.field_145848_d + 16 && nextZ > this.field_145849_e - 16 && nextZ < this.field_145849_e + 16;
    }

    private int findNextFacing() {
        if (this.trackedPlayer != null) {
            int pitch = MathHelper.func_76128_c((double) (this.trackedPlayer.field_70125_A * 4.0F / 360.0F) + 1.5D) & 3;

            if (pitch == 0) {
                return 1;
            }

            if (pitch == 2) {
                return 0;
            }

            int direction = MathHelper.func_76128_c((double) (this.trackedPlayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;

            switch (direction) {
            case 0:
                return 3;

            case 1:
                return 4;

            case 2:
                return 2;

            case 3:
                return 5;
            }
        }

        return -1;
    }

    private EntityPlayer findClosestValidPlayer() {
        return this.field_145850_b.func_72977_a((double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 0.5D, (double) this.field_145849_e + 0.5D, 16.0D);
    }
}
