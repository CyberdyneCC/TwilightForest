package twilightforest.client;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import twilightforest.uncrafting.ContainerTFUncrafting;

public class GuiTFGoblinCrafting extends GuiContainer {

    private static final ResourceLocation textureLoc = new ResourceLocation("twilightforest:textures/gui/guigoblintinkering.png");

    public GuiTFGoblinCrafting(InventoryPlayer inventory, World world, int x, int y, int z) {
        super(new ContainerTFUncrafting(inventory, world, x, y, z));
    }

    protected void func_146979_b(int i, int j) {
        this.field_146289_q.func_78276_b("Uncrafting Table", 8, 6, 4210752);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a("container.inventory"), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    protected void func_146976_a(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(GuiTFGoblinCrafting.textureLoc);
        int frameX = (this.field_146294_l - this.field_146999_f) / 2;
        int frameY = (this.field_146295_m - this.field_147000_g) / 2;

        this.func_73729_b(frameX, frameY, 0, 0, this.field_146999_f, this.field_147000_g);
        ContainerTFUncrafting tfContainer = (ContainerTFUncrafting) this.field_147002_h;

        RenderHelper.func_74520_c();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) this.field_147003_i, (float) this.field_147009_r, 0.0F);

        for (int fontRendererObj = 0; fontRendererObj < 9; ++fontRendererObj) {
            Slot costVal = tfContainer.func_75139_a(2 + fontRendererObj);
            Slot color = tfContainer.func_75139_a(11 + fontRendererObj);

            if (costVal.func_75211_c() != null) {
                this.drawSlotAsBackground(costVal, color);
            }
        }

        GL11.glPopMatrix();
        FontRenderer fontrenderer = this.field_146297_k.field_71466_p;

        RenderHelper.func_74518_a();
        int k = tfContainer.getUncraftingCost();
        String cost;
        int l;

        if (k > 0) {
            if (this.field_146297_k.field_71439_g.field_71068_ca < k && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) {
                l = 10485760;
                cost = "" + k;
                fontrenderer.func_78261_a(cost, frameX + 48 - fontrenderer.func_78256_a(cost), frameY + 38, l);
            } else {
                l = 8453920;
                cost = "" + k;
                fontrenderer.func_78261_a(cost, frameX + 48 - fontrenderer.func_78256_a(cost), frameY + 38, l);
            }
        }

        k = tfContainer.getRecraftingCost();
        if (k > 0) {
            if (this.field_146297_k.field_71439_g.field_71068_ca < k && !this.field_146297_k.field_71439_g.field_71075_bZ.field_75098_d) {
                l = 10485760;
                cost = "" + k;
                fontrenderer.func_78261_a(cost, frameX + 130 - fontrenderer.func_78256_a(cost), frameY + 38, l);
            } else {
                l = 8453920;
                cost = "" + k;
                fontrenderer.func_78261_a(cost, frameX + 130 - fontrenderer.func_78256_a(cost), frameY + 38, l);
            }
        }

    }

    private void drawSlotAsBackground(Slot backgroundSlot, Slot appearSlot) {
        int screenX = appearSlot.field_75223_e;
        int screenY = appearSlot.field_75221_f;
        ItemStack itemStackToRender = backgroundSlot.func_75211_c();

        this.field_73735_i = 50.0F;
        GuiTFGoblinCrafting.field_146296_j.field_77023_b = 50.0F;
        GuiTFGoblinCrafting.field_146296_j.func_77015_a(this.field_146289_q, this.field_146297_k.field_71446_o, itemStackToRender, screenX, screenY);
        GuiTFGoblinCrafting.field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, itemStackToRender, screenX, screenY);
        boolean itemBroken = false;

        if (backgroundSlot.func_75216_d() && backgroundSlot.func_75211_c().field_77994_a == 0) {
            itemBroken = true;
        }

        GL11.glDisable(2896);
        GL11.glDisable(2929);
        Gui.func_73734_a(screenX, screenY, screenX + 16, screenY + 16, itemBroken ? -2130736245 : -1618244725);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GuiTFGoblinCrafting.field_146296_j.field_77023_b = 0.0F;
        this.field_73735_i = 0.0F;
    }
}
