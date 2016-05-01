package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFMagicMapData;

public class ItemTFEmptyMagicMap extends ItemMapBase {

    protected ItemTFEmptyMagicMap() {
        this.func_77637_a(TFItems.creativeTab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        ItemStack mapItem = new ItemStack(TFItems.magicMap, 1, par2World.func_72841_b("magicmap"));
        String mapName = "magicmap_" + mapItem.func_77960_j();
        TFMagicMapData mapData = new TFMagicMapData(mapName);

        par2World.func_72823_a(mapName, mapData);
        mapData.field_76197_d = 4;
        int step = 128 * (1 << mapData.field_76197_d);

        mapData.field_76201_a = (int) (Math.round(par3EntityPlayer.field_70165_t / (double) step) * (long) step);
        mapData.field_76199_b = (int) (Math.round(par3EntityPlayer.field_70161_v / (double) step) * (long) step);
        mapData.field_76200_c = (byte) par2World.field_73011_w.field_76574_g;
        mapData.func_76185_a();
        --par1ItemStack.field_77994_a;
        if (mapItem.func_77973_b() == TFItems.magicMap) {
            par3EntityPlayer.func_71029_a(TFAchievementPage.twilightMagicMap);
        }

        if (par1ItemStack.field_77994_a <= 0) {
            return mapItem;
        } else {
            if (!par3EntityPlayer.field_71071_by.func_70441_a(mapItem.func_77946_l())) {
                par3EntityPlayer.func_71019_a(mapItem, false);
            }

            return par1ItemStack;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
    }
}
