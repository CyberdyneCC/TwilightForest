package twilightforest;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTFProgress extends CommandBase {

    String[] bosses = new String[] { "none", "naga", "lich", "mooshroom", "hydra", "knights", "urghast", "yeti", "snowqueen", "giants", "final"};

    public String func_71517_b() {
        return "tfprogress";
    }

    public String func_71518_a(ICommandSender sender) {
        return "tfprogress <player> <boss>";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException("tfprogress <player> <boss>", new Object[0]);
        } else {
            EntityPlayerMP player = func_82359_c(sender, args[0]);
            int bossIndex = this.getBossIndex(args[1]);

            func_152373_a(sender, this, "Setting player %s progress to past boss %s.", new Object[] { player.func_70005_c_(), this.bosses[bossIndex]});
            this.setProgress(player, bossIndex);
        }
    }

    private void setProgress(EntityPlayerMP player, int bossIndex) {
        if (bossIndex > 0) {
            for (int i = 0; i < bossIndex; ++i) {
                this.setProgress(player, i);
            }
        }

        switch (bossIndex) {
        case 0:
        default:
            break;

        case 1:
            player.func_71029_a(TFAchievementPage.twilightPortal);
            player.func_71029_a(TFAchievementPage.twilightArrival);
            player.func_71029_a(TFAchievementPage.twilightHunter);
            player.func_71029_a(TFAchievementPage.twilightKillNaga);
            player.func_71029_a(TFAchievementPage.twilightProgressNaga);
            break;

        case 2:
            player.func_71029_a(TFAchievementPage.twilightKillLich);
            player.func_71029_a(TFAchievementPage.twilightProgressLich);
            break;

        case 3:
            player.func_71029_a(TFAchievementPage.twilightProgressLabyrinth);
            break;

        case 4:
            player.func_71029_a(TFAchievementPage.twilightKillHydra);
            player.func_71029_a(TFAchievementPage.twilightProgressHydra);
            break;

        case 5:
            player.func_71029_a(TFAchievementPage.twilightProgressTrophyPedestal);
            player.func_71029_a(TFAchievementPage.twilightProgressKnights);
            break;

        case 6:
            player.func_71029_a(TFAchievementPage.twilightProgressUrghast);
            break;

        case 7:
            player.func_71029_a(TFAchievementPage.twilightProgressYeti);
            break;

        case 8:
            player.func_71029_a(TFAchievementPage.twilightProgressGlacier);
            break;

        case 9:
            player.func_71029_a(TFAchievementPage.twilightProgressTroll);
            break;

        case 10:
            player.func_71029_a(TFAchievementPage.twilightProgressThorns);
            player.func_71029_a(TFAchievementPage.twilightProgressCastle);
        }

    }

    private int getBossIndex(String string) {
        for (int i = 0; i < this.bosses.length; ++i) {
            if (this.bosses[i].equalsIgnoreCase(string)) {
                return i;
            }
        }

        return 0;
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] args) {
        return args.length == 1 ? func_71530_a(args, this.getListOfPlayers()) : (args.length == 2 ? func_71530_a(args, this.bosses) : null);
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
        return p_82358_2_ == 0;
    }
}
