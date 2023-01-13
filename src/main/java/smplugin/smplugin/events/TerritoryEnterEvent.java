package smplugin.smplugin.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import smplugin.smplugin.handlers.TerritoryHandler;

public class TerritoryEnterEvent implements Listener {

    TerritoryHandler territoryHandler = new TerritoryHandler();

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!territoryHandler.blockIsInWilderness(block)) {
            event.setCancelled(!territoryHandler.blockIsInTerritory(player, block));
        }
    }
}
