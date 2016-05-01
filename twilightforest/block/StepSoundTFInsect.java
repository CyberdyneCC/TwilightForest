package twilightforest.block;

import net.minecraft.block.Block.SoundType;

public class StepSoundTFInsect extends SoundType {

    public StepSoundTFInsect(String par1Str, float par2, float par3) {
        super(par1Str, par2, par3);
    }

    public String func_150495_a() {
        return "mob.slime.big";
    }

    public String getStepSound() {
        return "mob.slime.big";
    }

    public String getPlaceSound() {
        return "mob.slime.big";
    }
}
