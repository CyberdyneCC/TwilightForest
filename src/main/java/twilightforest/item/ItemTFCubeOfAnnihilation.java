package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import twilightforest.entity.EntityTFCubeOfAnnihilation;

public class ItemTFCubeOfAnnihilation extends ItemTF {

    private IIcon annihilateIcon;
    private HashMap launchedCubesMap = new HashMap();

    protected ItemTFCubeOfAnnihilation() {
        this.field_77777_bU = 1;
        this.func_77637_a(TFItems.creativeTab);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    public ItemStack func_77659_a(ItemStack stack, World worldObj, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        if (!worldObj.field_72995_K && !this.hasLaunchedCube(stack)) {
            EntityTFCubeOfAnnihilation launchedCube = new EntityTFCubeOfAnnihilation(worldObj, player);

            worldObj.func_72838_d(launchedCube);
            this.setLaunchedCube(stack, launchedCube);
            setCubeAsThrown(stack);
        }

        return stack;
    }

    public static void setCubeAsThrown(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }

        stack.func_77978_p().func_74757_a("thrown", true);
    }

    public static void setCubeAsReturned(ItemStack stack) {
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }

        stack.func_77978_p().func_74757_a("thrown", false);
    }

    public static boolean doesTalismanHaveCube(ItemStack stack) {
        return stack.func_77978_p() == null ? true : !stack.func_77978_p().func_74767_n("thrown");
    }

    public static void setCubeAsReturned(EntityPlayer player) {
        if (player != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == TFItems.cubeOfAnnihilation) {
            setCubeAsReturned(player.func_71045_bC());
        }

    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return doesTalismanHaveCube(stack) ? this.field_77791_bV : TFItems.cubeTalisman.func_77650_f(stack);
    }

    public boolean hasLaunchedCube(ItemStack stack) {
        Entity cube = (Entity) this.launchedCubesMap.get(stack);

        return cube != null && !cube.field_70128_L;
    }

    public void setLaunchedCube(ItemStack stack, EntityTFCubeOfAnnihilation launchedCube) {
        this.launchedCubesMap.put(stack, launchedCube);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {}

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("TwilightForest:" + this.func_77658_a().substring(5));
        this.annihilateIcon = par1IconRegister.func_94245_a("TwilightForest:annihilate_particle");
    }

    public IIcon getAnnihilateIcon() {
        return this.annihilateIcon;
    }
}
