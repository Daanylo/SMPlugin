package smplugin.smplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Claim implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {

//        Bukkit.getLogger().info("Username: " + SelectionHandler.selection(username) + " [Point A] X: " + SelectionHandler.xA + " Z: " + SelectionHandler.zA + " [Point B] X: " + SelectionHandler.xB + " Z: " + SelectionHandler.zB);

        return true;
    }
}
