package smplugin.smplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import smplugin.smplugin.SMPlugin;
import smplugin.smplugin.selections.Selection;
import java.util.HashMap;
import java.util.UUID;

public class JoinEvent implements Listener {
    Plugin plugin = SMPlugin.getPlugin(SMPlugin.class);
    public static HashMap<UUID, Selection> selections = new HashMap<>();
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(player.getDisplayName() + " has joined.");
        selections.put(event.getPlayer().getUniqueId(), new Selection(0, 0, 0, 0));
        }

}
