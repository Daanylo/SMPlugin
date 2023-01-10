package smplugin.smplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import smplugin.smplugin.SMPlugin;

public class JoinEvent implements Listener {

    Plugin plugin = SMPlugin.getPlugin(SMPlugin.class);
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(player.getDisplayName() + " has joined.");
    }

}
