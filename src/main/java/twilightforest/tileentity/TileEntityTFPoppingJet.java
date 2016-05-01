package twilightforest.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import twilightforest.block.TFBlocks;

public class TileEntityTFPoppingJet extends TileEntity {

    int counter;
    int nextMeta;

    public TileEntityTFPoppingJet() {
        this(10);
    }

    public TileEntityTFPoppingJet(int parNextMeta) {
        this.counter = 0;
        this.nextMeta = parNextMeta;
    }

    public void func_145845_h() {
        if (++this.counter >= 80) {
            this.counter = 0;
            if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == TFBlocks.fireJet) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFBlocks.fireJet, this.nextMeta, 3);
            }

            this.func_145843_s();
        } else if (this.counter % 20 == 0) {
            this.field_145850_b.func_72869_a("lava", (double) this.field_145851_c + 0.5D, (double) this.field_145848_d + 1.5D, (double) this.field_145849_e + 0.5D, 0.0D, 0.0D, 0.0D);
            this.field_145850_b.func_72908_a((double) this.field_145851_c, (double) this.field_145848_d, (double) this.field_145849_e, "liquid.lavapop", 0.2F + this.field_145850_b.field_73012_v.nextFloat() * 0.2F, 0.9F + this.field_145850_b.field_73012_v.nextFloat() * 0.15F);
        }

    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.nextMeta = par1NBTTagCompound.func_74762_e("NextMeta");
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("NextMeta", this.nextMeta);
    }
}
