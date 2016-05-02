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
    @Override
    public String getCommandName() {
        return "tffeature";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "tffeature accepts the following arguments: info";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            EntityPlayerMP player;
            int dx;
            int dz;

            if (args[0].equalsIgnoreCase("info")) {
                player = getCommandSenderAsPlayer(sender);
                dx = MathHelper.floor_double(player.posX);
                dz = MathHelper.floor_double(player.posZ);
                int cc = MathHelper.floor_double(player.posY);

                if (!(player.worldObj.provider instanceof WorldProviderTwilightForest)) {
                    throw new WrongUsageException("commands.tffeature.not_in_twilight_forest", new Object[0]);
                }
                //line below is possibly wrong?
                TFFeature wcm = ((TFWorldChunkManager) ((WorldProviderTwilightForest) player.worldObj.provider).func_76555_c()).getFeatureAt(dx, cc, player.worldObj);

                sender.addChatMessage(new ChatComponentTranslation("The nearest feature is %s", new Object[] { wcm.name}));
                ChunkProviderTwilightForest fc = ((WorldProviderTwilightForest) player.worldObj.provider).getChunkProvider();

                if (fc.isBlockInStructureBB(dx, dz, cc)) {
                    sender.addChatMessage(new ChatComponentTranslation("You are in the structure for that feature.", new Object[0]));
                    sender.addChatMessage(new ChatComponentTranslation("Structure conquer flag = %s.", new Object[] { Boolean.valueOf(fc.isStructureConquered(dx, dz, cc))}));
                } else {
                    sender.addChatMessage(new ChatComponentTranslation("You are not in the structure for that feature.", new Object[0]));
                }
            } else if (args[0].equalsIgnoreCase("reactivate")) {
                this.changeStructureActivity(sender, false);
            } else if (args[0].equalsIgnoreCase("conquer")) {
                this.changeStructureActivity(sender, true);
            } else {
                if (!args[0].equalsIgnoreCase("center")) {
                    throw new WrongUsageException("commands.tffeature.usage", new Object[0]);
                }

                player = getCommandSenderAsPlayer(sender);
                dx = MathHelper.floor_double(player.posX);
                dz = MathHelper.floor_double(player.posZ);
                ChunkCoordinates cc1 = TFFeature.getNearestCenterXYZ(dx >> 4, dz >> 4, player.worldObj);
                TFWorldChunkManager wcm1 = (TFWorldChunkManager) player.worldObj.getWorldChunkManager();
                boolean fc1 = wcm1.isInFeatureChunk(player.worldObj, dx, dz);

                sender.addChatMessage(new ChatComponentTranslation("Center of feature = %s.", new Object[] { cc1}));
                sender.addChatMessage(new ChatComponentTranslation("Are in feature chunk = %s.", new Object[] { Boolean.valueOf(fc1)}));
            }

        } else {
            throw new WrongUsageException("commands.tffeature.usage", new Object[0]);
        }
    }

    private void changeStructureActivity(ICommandSender sender, boolean flag) throws WrongUsageException {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        int dx = MathHelper.floor_double(player.posX);
        int dy = MathHelper.floor_double(player.posY);
        int dz = MathHelper.floor_double(player.posZ);

        if (!(player.worldObj.provider instanceof WorldProviderTwilightForest)) {
            throw new WrongUsageException("commands.tffeature.not_in_twilight_forest", new Object[0]);
        } else {
            ChunkProviderTwilightForest chunkProvider = ((WorldProviderTwilightForest) player.worldObj.provider).getChunkProvider();

            if (chunkProvider.isBlockInStructureBB(dx, dy, dz)) {
                sender.addChatMessage(new ChatComponentTranslation("Structure conquer flag was %s.  Changing to %s.", new Object[] { Boolean.valueOf(chunkProvider.isStructureConquered(dx, dy, dz)), Boolean.valueOf(flag)}));
                chunkProvider.setStructureConquered(dx, dy, dz, flag);
            } else {
                sender.addChatMessage(new ChatComponentTranslation("You are not in a structure.", new Object[0]));
            }

        }
    }
}
