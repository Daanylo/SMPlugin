package smplugin.smplugin.handlers;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import smplugin.smplugin.util.ClaimUtil;

public class CommandHandler implements CommandExecutor {

    SelectionHandler selectionHandler = new SelectionHandler();
    ClaimUtil claimUtil = new ClaimUtil();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("minmax")) {
            Player player = (Player) sender;
            player.sendMessage(String.valueOf(claimUtil.calculateRegion()));
        }
        return true;
    }
}
