package smplugin.smplugin.handlers;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.selections.Selection;
import smplugin.smplugin.util.ClaimUtil;

import java.util.UUID;

public class CommandHandler implements CommandExecutor {


    ClaimUtil claimUtil = new ClaimUtil();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Selection selection = JoinEvent.selections.get(player.getUniqueId());
        if (command.getName().equals("minmax")) {
            UUID user = selection.getUuid();
            int minX = Math.min(selection.getxA(), selection.getxB());
            int maxX = Math.max(selection.getxA(), selection.getxB());
            int minZ = Math.min(selection.getzA(), selection.getzB());
            int maxZ = Math.max(selection.getzA(), selection.getzB());
            player.sendMessage(String.valueOf(user) + minX + maxX + minZ + maxZ);
        }
        return true;
    }
}
