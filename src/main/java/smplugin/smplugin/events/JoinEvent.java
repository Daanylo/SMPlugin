package smplugin.smplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.selections.Selection;
import java.util.HashMap;
import java.util.UUID;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(player.getDisplayName() + " has joined.");
        }

}
