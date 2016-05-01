package twilightforest.entity.ai;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Vec3;
import twilightforest.TFGenericPacketHandler;
import twilightforest.TwilightForestMod;

public class EntityAITFThrowRider extends EntityAIBase {

    private EntityCreature theEntityCreature;
    private int throwTimer;

    public EntityAITFThrowRider(EntityCreature par1EntityCreature, float par2) {
        this.theEntityCreature = par1EntityCreature;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        return this.theEntityCreature.field_70153_n != null && this.theEntityCreature.func_70681_au().nextInt(5) <= 0;
    }

    public void func_75249_e() {
        EntityLivingBase rider = (EntityLivingBase) this.theEntityCreature.field_70153_n;

        rider.func_70078_a((Entity) null);
        Vec3 throwVec = this.theEntityCreature.func_70040_Z();

        throwVec.field_72450_a *= 2.0D;
        throwVec.field_72448_b *= 2.0D;
        throwVec.field_72449_c *= 2.0D;
        throwVec.field_72448_b = 0.9D;
        rider.func_70024_g(throwVec.field_72450_a, throwVec.field_72448_b, throwVec.field_72449_c);
        if (rider instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) rider;
            FMLProxyPacket message = TFGenericPacketHandler.makeThrowPlayerPacket((float) throwVec.field_72450_a, (float) throwVec.field_72448_b, (float) throwVec.field_72449_c);

            TwilightForestMod.genericChannel.sendTo(message, player);
        }

        System.out.println("throw!");
        this.throwTimer = 0;
    }

    public boolean func_75253_b() {
        if (this.theEntityCreature.field_70153_n == null) {
            ++this.throwTimer;
        }

        return this.throwTimer <= 40;
    }
}
