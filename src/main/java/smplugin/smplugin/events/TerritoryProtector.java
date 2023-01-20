package smplugin.smplugin.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.util.ClaimUtil;

import java.util.UUID;

public class TerritoryProtector implements Listener {
    TerritoryHandler territoryHandler = new TerritoryHandler();
    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Block block = event.getClickedBlock();
        if (!(block == null)) {
            if (!territoryHandler.blockIsInWilderness(block)) {
                if (ClaimUtil.getClaim(uuid) == null) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(!territoryHandler.blockIsInTerritory(uuid, block));
                }
            }
        }
    }
    @EventHandler
    public void entityInteractEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Block block = event.getRightClicked().getLocation().getBlock();
        if (!territoryHandler.blockIsInWilderness(block)) {
            if (ClaimUtil.getClaim(uuid) == null) {
                event.setCancelled(true);
            } else {
                event.setCancelled(!territoryHandler.blockIsInTerritory(uuid, block));
            }
        }
    }
    @EventHandler
    public void entityHitEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            UUID uuid = player.getUniqueId();
            Block block = event.getEntity().getLocation().getBlock();
            if (!territoryHandler.blockIsInWilderness(block)) {
                if (ClaimUtil.getClaim(uuid) == null) {
                event.setCancelled(true);
                } else {
                event.setCancelled(!territoryHandler.blockIsInTerritory(uuid, block));
                }
            }
        }
    }
    @EventHandler
    public void cropBreakEvent(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            UUID uuid = player.getUniqueId();
            Block block = event.getBlock();
            if (!territoryHandler.blockIsInWilderness(block)) {
                if (ClaimUtil.getClaim(uuid) == null) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(!territoryHandler.blockIsInTerritory(uuid, block));
                }
            }
        }
    }


}
