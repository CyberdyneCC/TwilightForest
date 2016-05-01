package twilightforest.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import twilightforest.entity.EntityTFBlockGoblin;

public class RenderTFBlockGoblin extends RenderBiped {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/model/blockgoblin.png");

    public RenderTFBlockGoblin(ModelBiped par1ModelBiped, float par2) {
        super(par1ModelBiped, par2);
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityTFBlockGoblin goblin = (EntityTFBlockGoblin) entity;

        super.func_76986_a(entity, d, d1, d2, f, f1);
        RenderManager.field_78727_a.func_147937_a(goblin.block, f1);
        RenderManager.field_78727_a.func_147937_a(goblin.chain1, f1);
        RenderManager.field_78727_a.func_147937_a(goblin.chain2, f1);
        RenderManager.field_78727_a.func_147937_a(goblin.chain3, f1);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return RenderTFBlockGoblin.textureLoc;
    }
}
