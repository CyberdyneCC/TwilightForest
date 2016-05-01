package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import twilightforest.entity.EntityTFProtectionBox;

public class ModelTFProtectionBox extends ModelBase {

    public ModelRenderer box;
    private int lastPixelsX;
    private int lastPixelsY;
    private int lastPixelsZ;

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {}

    public ModelTFProtectionBox() {
        this.field_78090_t = 16;
        this.field_78089_u = 16;
        this.box = new ModelRenderer(this, 0, 0);
        this.box.func_78790_a(0.0F, 0.0F, 0.0F, 16, 16, 16, 0.0F);
        this.box.func_78793_a(0.0F, 0.0F, 0.0F);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        EntityTFProtectionBox boxEntity = (EntityTFProtectionBox) par1Entity;
        int pixelsX = boxEntity.sizeX * 16 + 2;
        int pixelsY = boxEntity.sizeY * 16 + 2;
        int pixelsZ = boxEntity.sizeZ * 16 + 2;

        if (pixelsX != this.lastPixelsX || pixelsY != this.lastPixelsY || pixelsZ != this.lastPixelsZ) {
            this.resizeBoxElement(pixelsX, pixelsY, pixelsZ);
        }

        this.box.func_78785_a(par7);
    }

    private void resizeBoxElement(int pixelsX, int pixelsY, int pixelsZ) {
        this.box = new ModelRenderer(this, 0, 0);
        this.box.func_78790_a(-1.0F, -1.0F, -1.0F, pixelsX, pixelsY, pixelsZ, 0.0F);
        this.box.func_78793_a(0.0F, 0.0F, 0.0F);
        this.lastPixelsX = pixelsX;
        this.lastPixelsY = pixelsY;
        this.lastPixelsZ = pixelsZ;
    }
}
