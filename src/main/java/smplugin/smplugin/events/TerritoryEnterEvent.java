package smplugin.smplugin.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.selections.Selection;
import smplugin.smplugin.util.ClaimUtil;

import java.util.HashMap;
import java.util.UUID;

public class TerritoryEnterEvent implements Listener {
    private final HashMap<UUID, HashMap<UUID, Boolean>> enteredTerritories = new HashMap<>();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Location currentLocation = player.getLocation();
        HashMap<UUID, Selection> claims = ClaimUtil.getClaims();
        for (UUID key : claims.keySet()) {
            Selection claim = claims.get(key);
            if (claim.isWithin(currentLocation)) {
                if (!enteredTerritories.containsKey(playerUUID)) {
                    enteredTerritories.put(playerUUID, new HashMap<>());
                }
                if (!enteredTerritories.get(playerUUID).containsKey(key) || !enteredTerritories.get(playerUUID).get(key)) {
                    player.sendTitle("Entered", "\"" + claim.getTerritoryname() + "\"", 3, 20, 3);
                    enteredTerritories.get(playerUUID).put(key, true);
                }
            } else {
                if (enteredTerritories.containsKey(playerUUID) && enteredTerritories.get(playerUUID).containsKey(key) && enteredTerritories.get(playerUUID).get(key)) {
                    player.sendTitle("Left", "\"" + claim.getTerritoryname() + "\"", 3, 20, 3);
                    enteredTerritories.get(playerUUID).put(key, false);
                }
            }
        }
    }
}
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event) {
//        Player player = event.getPlayer();
//        Location currentLocation = player.getLocation();
//        HashMap<UUID, Selection> claims = ClaimUtil.getClaims();
//        for (UUID key : claims.keySet()) {
//            Selection claim = claims.get(key);
//            if (claim.isWithin(currentLocation)) {
//                if (!enteredRegions.containsKey(key) || !enteredRegions.get(key)) {
//                    player.sendTitle("Entered", "\"" + claim.getTerritoryname() + "\"", 3, 20, 3);                    enteredRegions.put(key, true);
//                }
//            } else {
//                if (enteredRegions.containsKey(key) && enteredRegions.get(key)) {
//                    player.sendTitle("Left", "\"" + claim.getTerritoryname() + "\"", 3, 20, 3);
//                    enteredRegions.put(key, false);
//                }
//            }
//        }
//    }
//}


