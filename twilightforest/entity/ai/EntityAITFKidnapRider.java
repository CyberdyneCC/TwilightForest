package twilightforest.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAITFKidnapRider extends EntityAIBase {

    private EntityCreature theEntityCreature;
    private float speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAITFKidnapRider(EntityCreature par1EntityCreature, float par2) {
        this.theEntityCreature = par1EntityCreature;
        this.speed = par2;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        if (this.theEntityCreature.field_70153_n != null && this.theEntityCreature.func_70681_au().nextInt(5) <= 0) {
            Vec3 vec3 = RandomPositionGenerator.func_75463_a(this.theEntityCreature, 5, 4);

            if (vec3 == null) {
                return false;
            } else {
                this.randPosX = vec3.field_72450_a;
                this.randPosY = vec3.field_72448_b;
                this.randPosZ = vec3.field_72449_c;
                return true;
            }
        } else {
            return false;
        }
    }

    public void func_75249_e() {
        this.theEntityCreature.func_70661_as().func_75492_a(this.randPosX, this.randPosY, this.randPosZ, (double) this.speed);
    }

    public boolean func_75253_b() {
        return !this.theEntityCreature.func_70661_as().func_75500_f();
    }
}
