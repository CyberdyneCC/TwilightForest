package twilightforest.entity.passive;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.entity.ai.EntityAITFEatLoose;
import twilightforest.entity.ai.EntityAITFFindLoose;
import twilightforest.item.TFItems;

public class EntityTFQuestRam extends EntityAnimal {

    private int randomTickDivider;

    public EntityTFQuestRam(World par1World) {
        super(par1World);
        this.func_70105_a(1.25F, 2.9F);
        this.randomTickDivider = 0;
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.3799999952316284D));
        this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Item.func_150898_a(Blocks.field_150325_L), false));
        this.field_70714_bg.func_75776_a(3, new EntityAITFEatLoose(this, Item.func_150898_a(Blocks.field_150325_L)));
        this.field_70714_bg.func_75776_a(4, new EntityAITFFindLoose(this, 1.0F, Item.func_150898_a(Blocks.field_150325_L)));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
    }

    public EntityAnimal createChild(EntityAgeable entityanimal) {
        return null;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(70.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
        this.field_70180_af.func_75682_a(17, Byte.valueOf((byte) 0));
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_70629_bd() {
        if (--this.randomTickDivider <= 0) {
            this.randomTickDivider = 70 + this.field_70146_Z.nextInt(50);
            int chunkX = MathHelper.func_76128_c(this.field_70165_t) / 16;
            int chunkZ = MathHelper.func_76128_c(this.field_70161_v) / 16;
            TFFeature nearFeature = TFFeature.getNearestFeature(chunkX, chunkZ, this.field_70170_p);

            if (nearFeature != TFFeature.questGrove) {
                this.func_110177_bN();
            } else {
                ChunkCoordinates cc = TFFeature.getNearestCenterXYZ(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v), this.field_70170_p);

                this.func_110171_b(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, 13);
            }

            if (this.countColorsSet() > 15 && !this.getRewarded()) {
                this.rewardQuest();
                this.setRewarded(true);
            }
        }

        super.func_70629_bd();
    }

    private void rewardQuest() {
        this.func_145778_a(Item.func_150898_a(Blocks.field_150484_ah), 1, 1.0F);
        this.func_145778_a(Item.func_150898_a(Blocks.field_150339_S), 1, 1.0F);
        this.func_145778_a(Item.func_150898_a(Blocks.field_150475_bE), 1, 1.0F);
        this.func_145778_a(Item.func_150898_a(Blocks.field_150340_R), 1, 1.0F);
        this.func_145778_a(Item.func_150898_a(Blocks.field_150368_y), 1, 1.0F);
        this.func_145778_a(TFItems.crumbleHorn, 1, 1.0F);
        this.rewardNearbyPlayers(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    private void rewardNearbyPlayers(World world, double posX, double posY, double posZ) {
        List nearbyPlayers = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).func_72314_b(16.0D, 16.0D, 16.0D));
        Iterator iterator = nearbyPlayers.iterator();

        while (iterator.hasNext()) {
            EntityPlayer player = (EntityPlayer) iterator.next();

            player.func_71029_a(TFAchievementPage.twilightQuestRam);
        }

    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        ItemStack currentItem = par1EntityPlayer.field_71071_by.func_70448_g();

        if (currentItem != null && currentItem.func_77973_b() == Item.func_150898_a(Blocks.field_150325_L) && !this.isColorPresent(currentItem.func_77960_j())) {
            this.setColorPresent(currentItem.func_77960_j());
            this.animateAddColor(currentItem.func_77960_j(), 50);
            if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
                --currentItem.field_77994_a;
                if (currentItem.field_77994_a <= 0) {
                    par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack) null);
                }
            }

            return true;
        } else {
            return super.func_70085_c(par1EntityPlayer);
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        this.checkAndAnimateColors();
    }

    public void checkAndAnimateColors() {
        if (this.countColorsSet() > 15 && !this.getRewarded()) {
            this.animateAddColor(this.field_70146_Z.nextInt(16), 5);
        }

    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("ColorFlags", this.getColorFlags());
        par1NBTTagCompound.func_74757_a("Rewarded", this.getRewarded());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setColorFlags(par1NBTTagCompound.func_74762_e("ColorFlags"));
        this.setRewarded(par1NBTTagCompound.func_74767_n("Rewarded"));
    }

    public int getColorFlags() {
        return this.field_70180_af.func_75679_c(16);
    }

    public void setColorFlags(int par1) {
        this.field_70180_af.func_75692_b(16, Integer.valueOf(par1));
    }

    public boolean isColorPresent(int color) {
        int flags = this.getColorFlags();

        return (flags & (int) Math.pow(2.0D, (double) color)) > 0;
    }

    public void setColorPresent(int color) {
        int flags = this.getColorFlags();

        this.setColorFlags(flags | (int) Math.pow(2.0D, (double) color));
    }

    public boolean getRewarded() {
        return this.field_70180_af.func_75683_a(17) != 0;
    }

    public void setRewarded(boolean par1) {
        this.field_70180_af.func_75692_b(17, par1 ? Byte.valueOf((byte) 1) : Byte.valueOf((byte) 0));
    }

    public void animateAddColor(int color, int iterations) {
        for (int i = 0; i < iterations; ++i) {
            this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 1.5D, this.field_70163_u + this.field_70146_Z.nextDouble() * (double) this.field_70131_O * 1.5D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double) this.field_70130_N * 1.5D, (double) EntitySheep.field_70898_d[color][0], (double) EntitySheep.field_70898_d[color][1], (double) EntitySheep.field_70898_d[color][2]);
        }

        this.func_70642_aH();
    }

    public int countColorsSet() {
        int count = 0;

        for (int i = 0; i < 16; ++i) {
            if (this.isColorPresent(i)) {
                ++count;
            }
        }

        return count;
    }

    public void func_70642_aH() {
        this.field_70170_p.func_72956_a(this, "mob.sheep.say", this.func_70599_aP(), this.func_70647_i());
    }

    protected float func_70599_aP() {
        return 5.0F;
    }

    protected float func_70647_i() {
        return (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 0.7F;
    }

    protected String func_70639_aQ() {
        return "mob.sheep.say";
    }

    protected String func_70621_aR() {
        return "mob.sheep.say";
    }

    protected String func_70673_aS() {
        return "mob.sheep.say";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.func_85030_a("mob.sheep.step", 0.15F, 1.0F);
    }

    public float getMaximumHomeDistance() {
        return this.func_110174_bM();
    }
}
