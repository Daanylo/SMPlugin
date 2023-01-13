package smplugin.smplugin.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.selections.Selection;


public class SelectionHandler implements Listener {

    @EventHandler
    public static void regionSelect(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if ((event.getHand() == EquipmentSlot.HAND)) {

            if (player.getInventory().getItemInMainHand().hasItemMeta()) {

                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Claim Wand")) {

                    Selection selection = JoinEvent.selections.get(player.getUniqueId());

                    if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        Location a = event.getClickedBlock().getLocation();
                            try {
                                selection.setxA(a.getBlockX());
                                selection.setzA(a.getBlockZ());

                                if (selection.getxB() == 0 & selection.getzB() == 0) {
                                    player.sendMessage("[Point A] X:" + a.getBlockX() + " Z:" + a.getBlockZ());
                                }
                                else {
                                    int sideX = Math.abs((selection.getxA()-selection.getxB())) + 1;
                                    int sideZ = Math.abs((selection.getzA()-selection.getzB())) + 1;
                                    int area = sideX * sideZ;
                                    player.sendMessage("[Point A] X:" + a.getBlockX() + " Z:" + a.getBlockZ() + " (" + area + ")");
                                }
                            } catch (Exception e) {
                                player.sendMessage("An error occured. Please relog.");
                            }
                        }
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Location b = event.getClickedBlock().getLocation();
                            try {
                            selection.setxB(b.getBlockX());
                            selection.setzB(b.getBlockZ());

                                if (selection.getxA() == 0 & selection.getzA() == 0) {
                                    player.sendMessage("[Point B] X:" + b.getBlockX() + " Z:" + b.getBlockZ());
                                }
                                else {
                                    int sideX = Math.abs((selection.getxA()-selection.getxB())) + 1;
                                    int sideZ = Math.abs((selection.getzA()-selection.getzB())) + 1;
                                    int area = sideX * sideZ;
                                    player.sendMessage("[Point B] X:" + b.getBlockX() + " Z:" + b.getBlockZ() + " (" + area + ")");
                                }
                            } catch (Exception e) {
                                player.sendMessage("An error occured. Please relog.");
                            }
                    }
                }
            }
        }
    }
}

