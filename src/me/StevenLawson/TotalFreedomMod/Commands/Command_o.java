package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Admin Chat - Talk privately with other admins. Using <command> itself will toggle Admin Chat on and off for all messages.", usage = "/<command> [message...]", aliases = "adminchat")
public class Command_o extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                playerMsg("Only in-game players can toggle admin chat.");
                return true;
            }

            TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);

            if (userinfo.inSeniorAdminChat())
            {
                userinfo.setSeniorAdminChat(!userinfo.inSeniorAdminChat());
            }

            if (userinfo.inDevChat())
            {
                userinfo.setDevChat(!userinfo.inDevChat());
            }

            if (userinfo.inTelnetAdminChat())
            {
                userinfo.setTelnetAdminChat(!userinfo.inTelnetAdminChat());
            }

            userinfo.setAdminChat(!userinfo.inAdminChat());
            playerMsg("Toggled Admin Chat " + (userinfo.inAdminChat() ? "on" : "off") + ".");
        }
        else
        {
            TFM_Util.adminChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }

        return true;
    }
}
