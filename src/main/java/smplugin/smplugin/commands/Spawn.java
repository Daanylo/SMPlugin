package smplugin.smplugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import smplugin.smplugin.SMPlugin;

public class Spawn implements CommandExecutor, Listener {

    Plugin plugin = SMPlugin.getPlugin(SMPlugin.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (player.hasPermission("setspawn")) {
                    setSpawn(player);
                    player.sendMessage("You have set the spawn.");
                }
            }
        if (command.getName().equalsIgnoreCase("spawn")) {
            teleportSpawn(player);
            player.sendMessage("You have been teleported to the spawn.");
        }
        return true;
    }
    private void teleportSpawn(Player player) {
        if(plugin.getConfig().contains("Spawn")) {
            player.teleport((Location) plugin.getConfig().get("Spawn"));
        }
        else {
            player.sendMessage("No spawn has been set.");
            }
        }

    private void setSpawn(Player player) {
        plugin.getConfig().set("Spawn", player.getLocation());
        plugin.saveConfig();
    }
    @EventHandler
    public void onFirstJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            teleportSpawn(player);
        }
    }
    @EventHandler
    public void onRespawn (PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        teleportSpawn(player);
    }
}
