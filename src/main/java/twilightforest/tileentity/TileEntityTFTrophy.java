package twilightforest.tileentity;

import net.minecraft.tileentity.TileEntitySkull;

public class TileEntityTFTrophy extends TileEntitySkull {

    public int ticksExisted;

    public void func_145845_h() {
        super.func_145845_h();
        ++this.ticksExisted;
    }
}
