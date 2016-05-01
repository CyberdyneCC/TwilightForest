package twilightforest.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;

public class EntityTFMazeSlime extends EntitySlime {

    private String slimeParticleString;

    public EntityTFMazeSlime(World par1World) {
        super(par1World);
        this.func_70799_a(1 << 1 + this.field_70146_Z.nextInt(2));
    }

    protected EntitySlime func_70802_j() {
        return new EntityTFMazeSlime(this.field_70170_p);
    }

    public void func_70799_a(int par1) {
        super.func_70799_a(par1);
        this.field_70728_aV = par1 + 3;
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL && this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D) && this.isValidLightLevel();
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        int size = this.func_70809_q();

        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0D * (double) size * (double) size);
    }

    protected boolean func_70800_m() {
        return true;
    }

    protected int func_70805_n() {
        return super.func_70805_n() + 3;
    }

    protected String func_70801_i() {
        if (this.slimeParticleString == null) {
            this.slimeParticleString = "blockcrack_" + Block.func_149682_b(TFBlocks.mazestone) + "_1";
        }

        return this.slimeParticleString;
    }

    protected boolean isValidLightLevel() {
        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32)) {
            return false;
        } else {
            int l = this.field_70170_p.func_72957_l(i, j, k);

            if (this.field_70170_p.func_72911_I()) {
                int i1 = this.field_70170_p.field_73008_k;

                this.field_70170_p.field_73008_k = 10;
                l = this.field_70170_p.func_72957_l(i, j, k);
                this.field_70170_p.field_73008_k = i1;
            }

            return l <= this.field_70146_Z.nextInt(8);
        }
    }

    protected float func_70599_aP() {
        return 0.1F * (float) this.func_70809_q();
    }

    protected void func_70600_l(int par1) {
        this.func_145779_a(TFItems.charmOfKeeping1, 1);
    }
}
