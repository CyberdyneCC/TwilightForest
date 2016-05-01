package twilightforest.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import twilightforest.item.TFItems;

public class BlockTFShield extends Block {

    public static IIcon sprSide;
    private IIcon sprInside;
    private IIcon sprOutside;

    public BlockTFShield() {
        super(Material.field_151576_e);
        this.func_149722_s();
        this.func_149752_b(6000000.0F);
        this.func_149672_a(Block.field_149777_j);
        this.func_149647_a(TFItems.creativeTab);
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == meta ? this.sprInside : this.sprOutside;
    }

    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.sprInside = par1IconRegister.func_94245_a("TwilightForest:shield_inside");
        this.sprOutside = par1IconRegister.func_94245_a("TwilightForest:shield_outside");
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149701_w() {
        return 0;
    }

    public boolean func_149662_c() {
        return true;
    }

    public boolean func_149686_d() {
        return true;
    }

    public int func_149692_a(int meta) {
        return 0;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int l = BlockPistonBase.func_150071_a(par1World, par2, par3, par4, par5EntityLiving);

        par1World.func_72921_c(par2, par3, par4, l, 2);
    }

    public float func_149737_a(EntityPlayer player, World world, int x, int y, int z) {
        MovingObjectPosition mop = this.getPlayerPointVec(world, player, 6.0D);
        int facing = mop != null ? mop.field_72310_e : -1;
        int meta = world.func_72805_g(x, y, z);

        return facing == meta ? player.getBreakSpeed(Blocks.field_150348_b, false, 0, x, y, z) / 1.5F / 100.0F : super.func_149737_a(player, world, x, y, z);
    }

    private MovingObjectPosition getPlayerPointVec(World worldObj, EntityPlayer player, double range) {
        Vec3 position = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + (double) player.func_70047_e(), player.field_70161_v);
        Vec3 look = player.func_70676_i(1.0F);
        Vec3 dest = position.func_72441_c(look.field_72450_a * range, look.field_72448_b * range, look.field_72449_c * range);

        return worldObj.func_72933_a(position, dest);
    }
}
