package twilightforest.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import twilightforest.entity.TFCreatures;
import twilightforest.entity.TFEntityEggInfo;

public class ItemTFSpawnEgg extends ItemMonsterPlacer {

    protected ItemTFSpawnEgg() {
        this.func_77627_a(true);
        this.func_77637_a(TFItems.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        TFEntityEggInfo info = (TFEntityEggInfo) TFCreatures.entityEggs.get(Integer.valueOf(par1ItemStack.func_77960_j()));

        return info != null ? (par2 == 0 ? info.primaryColor : info.secondaryColor) : 16777215;
    }

    @SideOnly(Side.CLIENT)
    public String func_77653_i(ItemStack par1ItemStack) {
        String prefix = ("" + StatCollector.func_74838_a(this.func_77658_a() + ".name")).trim();
        String entityname = TFCreatures.getStringFromID(par1ItemStack.func_77960_j());

        if (entityname != null) {
            prefix = prefix + " " + StatCollector.func_74838_a(String.format("entity.%s.%s.name", new Object[] { "TwilightForest", entityname}));
        }

        return prefix;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.field_72995_K) {
            return true;
        } else {
            Block block = par3World.func_147439_a(par4, par5, par6);

            par4 += Facing.field_71586_b[par7];
            par5 += Facing.field_71587_c[par7];
            par6 += Facing.field_71585_d[par7];
            double d0 = 0.0D;

            if (par7 == 1 && block == Blocks.field_150422_aJ || block == Blocks.field_150386_bk) {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(par3World, par1ItemStack.func_77960_j(), (double) par4 + 0.5D, (double) par5 + d0, (double) par6 + 0.5D);

            if (entity != null) {
                if (entity instanceof EntityLiving && par1ItemStack.func_82837_s()) {
                    ((EntityLiving) entity).func_94058_c(par1ItemStack.func_82833_r());
                }

                if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
                    --par1ItemStack.field_77994_a;
                }
            }

            return true;
        }
    }

    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
        if (!TFCreatures.entityEggs.containsKey(Integer.valueOf(par1))) {
            return null;
        } else {
            Entity entityToSpawn = TFCreatures.createEntityByID(par1, par0World);

            if (entityToSpawn != null && entityToSpawn instanceof EntityLivingBase) {
                EntityLiving entityliving = (EntityLiving) entityToSpawn;

                entityToSpawn.func_70012_b(par2, par4, par6, par0World.field_73012_v.nextFloat() * 360.0F, 0.0F);
                entityliving.func_110161_a((IEntityLivingData) null);
                par0World.func_72838_d(entityToSpawn);
                ((EntityLiving) entityToSpawn).func_70642_aH();
            }

            return entityToSpawn;
        }
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        Iterator iterator = TFCreatures.entityEggs.values().iterator();

        while (iterator.hasNext()) {
            TFEntityEggInfo tfentityegginfo = (TFEntityEggInfo) iterator.next();

            par3List.add(new ItemStack(par1, 1, tfentityegginfo.spawnedID));
        }

    }

    public IIcon func_77618_c(int par1, int par2) {
        return Items.field_151063_bx.func_77618_c(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {}
}
