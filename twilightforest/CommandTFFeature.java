package twilightforest;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import twilightforest.world.ChunkProviderTwilightForest;
import twilightforest.world.TFWorldChunkManager;
import twilightforest.world.WorldProviderTwilightForest;

public class CommandTFFeature extends CommandBase {

    public String func_71517_b() {
        return "tffeature";
    }

    public String func_71518_a(ICommandSender sender) {
        return "tffeature accepts the following arguments: info";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            EntityPlayerMP player;
            int dx;
            int dz;

            if (args[0].equalsIgnoreCase("info")) {
                player = func_71521_c(sender);
                dx = MathHelper.func_76128_c(player.field_70165_t);
                dz = MathHelper.func_76128_c(player.field_70163_u);
                int cc = MathHelper.func_76128_c(player.field_70161_v);

                if (!(player.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest)) {
                    throw new WrongUsageException("commands.tffeature.not_in_twilight_forest", new Object[0]);
                }

                TFFeature wcm = ((TFWorldChunkManager) player.field_70170_p.field_73011_w.field_76578_c).getFeatureAt(dx, cc, player.field_70170_p);

                sender.func_145747_a(new ChatComponentTranslation("The nearest feature is %s", new Object[] { wcm.name}));
                ChunkProviderTwilightForest fc = ((WorldProviderTwilightForest) player.field_70170_p.field_73011_w).getChunkProvider();

                if (fc.isBlockInStructureBB(dx, dz, cc)) {
                    sender.func_145747_a(new ChatComponentTranslation("You are in the structure for that feature.", new Object[0]));
                    sender.func_145747_a(new ChatComponentTranslation("Structure conquer flag = %s.", new Object[] { Boolean.valueOf(fc.isStructureConquered(dx, dz, cc))}));
                } else {
                    sender.func_145747_a(new ChatComponentTranslation("You are not in the structure for that feature.", new Object[0]));
                }
            } else if (args[0].equalsIgnoreCase("reactivate")) {
                this.changeStructureActivity(sender, false);
            } else if (args[0].equalsIgnoreCase("conquer")) {
                this.changeStructureActivity(sender, true);
            } else {
                if (!args[0].equalsIgnoreCase("center")) {
                    throw new WrongUsageException("commands.tffeature.usage", new Object[0]);
                }

                player = func_71521_c(sender);
                dx = MathHelper.func_76128_c(player.field_70165_t);
                dz = MathHelper.func_76128_c(player.field_70161_v);
                ChunkCoordinates cc1 = TFFeature.getNearestCenterXYZ(dx >> 4, dz >> 4, player.field_70170_p);
                TFWorldChunkManager wcm1 = (TFWorldChunkManager) player.field_70170_p.field_73011_w.field_76578_c;
                boolean fc1 = wcm1.isInFeatureChunk(player.field_70170_p, dx, dz);

                sender.func_145747_a(new ChatComponentTranslation("Center of feature = %s.", new Object[] { cc1}));
                sender.func_145747_a(new ChatComponentTranslation("Are in feature chunk = %s.", new Object[] { Boolean.valueOf(fc1)}));
            }

        } else {
            throw new WrongUsageException("commands.tffeature.usage", new Object[0]);
        }
    }

    private void changeStructureActivity(ICommandSender sender, boolean flag) throws WrongUsageException {
        EntityPlayerMP player = func_71521_c(sender);
        int dx = MathHelper.func_76128_c(player.field_70165_t);
        int dy = MathHelper.func_76128_c(player.field_70163_u);
        int dz = MathHelper.func_76128_c(player.field_70161_v);

        if (!(player.field_70170_p.field_73011_w instanceof WorldProviderTwilightForest)) {
            throw new WrongUsageException("commands.tffeature.not_in_twilight_forest", new Object[0]);
        } else {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) player.field_70170_p.field_73011_w).getChunkProvider();

            if (chunkProvider.isBlockInStructureBB(dx, dy, dz)) {
                sender.func_145747_a(new ChatComponentTranslation("Structure conquer flag was %s.  Changing to %s.", new Object[] { Boolean.valueOf(chunkProvider.isStructureConquered(dx, dy, dz)), Boolean.valueOf(flag)}));
                chunkProvider.setStructureConquered(dx, dy, dz, flag);
            } else {
                sender.func_145747_a(new ChatComponentTranslation("You are not in a structure.", new Object[0]));
            }

        }
    }
}
