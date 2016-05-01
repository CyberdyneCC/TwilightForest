package twilightforest;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.MapData;

public class TFMazeMapData extends MapData {

    public int yCenter;

    public TFMazeMapData(String par1Str) {
        super(par1Str);
    }

    public void func_76184_a(NBTTagCompound par1NBTTagCompound) {
        super.func_76184_a(par1NBTTagCompound);
        this.yCenter = par1NBTTagCompound.func_74762_e("yCenter");
    }

    public void func_76187_b(NBTTagCompound par1NBTTagCompound) {
        super.func_76187_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("yCenter", this.yCenter);
    }
}
