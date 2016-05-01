package twilightforest.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import twilightforest.client.model.ModelTFHydraHead;
import twilightforest.client.model.ModelTFLich;
import twilightforest.client.model.ModelTFNaga;
import twilightforest.client.model.ModelTFSnowQueen;
import twilightforest.client.model.ModelTFTowerBoss;
import twilightforest.tileentity.TileEntityTFTrophy;

public class TileEntityTFTrophyRenderer extends TileEntitySpecialRenderer {

    private ModelTFHydraHead hydraHeadModel = new ModelTFHydraHead();
    private static final ResourceLocation textureLocHydra = new ResourceLocation("twilightforest:textures/model/hydra4.png");
    private ModelTFNaga nagaHeadModel = new ModelTFNaga();
    private static final ResourceLocation textureLocNaga = new ResourceLocation("twilightforest:textures/model/nagahead.png");
    private ModelTFLich lichModel = new ModelTFLich();
    private static final ResourceLocation textureLocLich = new ResourceLocation("twilightforest:textures/model/twilightlich64.png");
    private ModelTFTowerBoss urGhastModel = new ModelTFTowerBoss();
    private static final ResourceLocation textureLocUrGhast = new ResourceLocation("twilightforest:textures/model/towerboss.png");
    private ModelTFSnowQueen snowQueenModel = new ModelTFSnowQueen();
    private static final ResourceLocation textureLocSnowQueen = new ResourceLocation("twilightforest:textures/model/snowqueen.png");

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float partialTime) {
        TileEntityTFTrophy trophy = (TileEntityTFTrophy) tileentity;

        GL11.glPushMatrix();
        GL11.glDisable(2884);
        int meta = trophy.func_145832_p() & 7;
        float rotation = (float) (trophy.func_145906_b() * 360) / 16.0F;
        boolean onGround = true;

        if (meta != 1) {
            switch (meta) {
            case 2:
                onGround = false;
                break;

            case 3:
                onGround = false;
                rotation = 180.0F;
                break;

            case 4:
                onGround = false;
                rotation = 270.0F;
                break;

            case 5:
            default:
                onGround = false;
                rotation = 90.0F;
            }
        }

        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        switch (trophy.func_145904_a()) {
        case 0:
            this.renderHydraHead(rotation, onGround);
            break;

        case 1:
            this.renderNagaHead(rotation, onGround);
            break;

        case 2:
            this.renderLichHead(rotation, onGround);
            break;

        case 3:
            this.renderUrGhastHead(trophy, rotation, onGround, partialTime);
            break;

        case 4:
            this.renderSnowQueenHead(rotation, onGround);
        }

        GL11.glPopMatrix();
    }

    private void renderHydraHead(float rotation, boolean onGround) {
        GL11.glScalef(0.25F, 0.25F, 0.25F);
        this.func_147499_a(TileEntityTFTrophyRenderer.textureLocHydra);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, onGround ? 1.0F : -0.0F, 1.5F);
        this.hydraHeadModel.openMouthForTrophy(onGround ? 0.0F : 0.25F);
        this.hydraHeadModel.func_78088_a((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, 0.0625F);
    }

    private void renderNagaHead(float rotation, boolean onGround) {
        GL11.glTranslatef(0.0F, -0.125F, 0.0F);
        GL11.glScalef(0.25F, 0.25F, 0.25F);
        this.func_147499_a(TileEntityTFTrophyRenderer.textureLocNaga);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, onGround ? 1.0F : -0.0F, onGround ? 0.0F : 1.0F);
        this.nagaHeadModel.func_78088_a((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, 0.0625F);
    }

    private void renderLichHead(float rotation, boolean onGround) {
        GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        this.func_147499_a(TileEntityTFTrophyRenderer.textureLocLich);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, onGround ? 1.75F : 1.5F, onGround ? 0.0F : 0.24F);
        this.lichModel.field_78116_c.func_78785_a(0.0625F);
        this.lichModel.field_78114_d.func_78785_a(0.0625F);
    }

    private void renderUrGhastHead(TileEntityTFTrophy trophy, float rotation, boolean onGround, float partialTime) {
        GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        this.func_147499_a(TileEntityTFTrophyRenderer.textureLocUrGhast);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, onGround ? 1.0F : 1.0F, onGround ? 0.0F : 0.0F);
        this.urGhastModel.func_78088_a((Entity) null, 0.0F, 0.0F, (float) trophy.ticksExisted + partialTime, 0.0F, 0.0F, 0.0625F);
    }

    private void renderSnowQueenHead(float rotation, boolean onGround) {
        GL11.glTranslatef(0.0F, 1.0F, 0.0F);
        this.func_147499_a(TileEntityTFTrophyRenderer.textureLocSnowQueen);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, onGround ? 1.5F : 1.25F, onGround ? 0.0F : 0.24F);
        this.snowQueenModel.field_78116_c.func_78785_a(0.0625F);
        this.snowQueenModel.field_78114_d.func_78785_a(0.0625F);
    }
}
