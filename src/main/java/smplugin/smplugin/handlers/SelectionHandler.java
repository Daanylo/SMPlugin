package smplugin.smplugin.handlers;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class SelectionHandler implements Listener {
    public static class Selection {
        String username = "username";
        int xA;
        int zA;
        int xB;
        int zB;
        public void setUsername(String username) {
            this.username = username;
        }
        public int getxA() {
            return xA;
        }
        public int getzA() {
            return zA;
        }
        public int getxB() {
            return xB;
        }
        public int getzB() {
            return zB;
        }
        public void setxA(int xA) {
            this.xA = xA;
        }
        public void setzA(int zA) {
            this.zA = zA;
        }
        public void setxB(int xB) {
            this.xB = xB;
        }
        public void setzB(int zB) {
            this.zB = zB;
        }
    }
    static Selection selection = new Selection();

    @EventHandler
    public static void regionSelect(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if ((event.getHand() == EquipmentSlot.HAND)) {

            if (player.getInventory().getItemInMainHand().hasItemMeta()) {

                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Claim Wand")) {

                    selection.setUsername(player.getName());

                    if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        Block a = event.getClickedBlock();
                        selection.setxA(a.getLocation().getBlockX());
                        selection.setzA(a.getLocation().getBlockZ());
                            if (selection.getxB() == 0 & selection.getzB() == 0) {
                                player.sendMessage("[Point A] X:" + a.getLocation().getBlockX() + " Z:" + a.getLocation().getBlockZ());
                            }
                            else {
                                int sideX = Math.abs((selection.getxA()-selection.getxB())) + 1;
                                int sideZ = Math.abs((selection.getzA()-selection.getzB())) + 1;
                                int area = sideX * sideZ;
                                player.sendMessage("[Point A] X:" + a.getLocation().getBlockX() + " Z:" + a.getLocation().getBlockZ() + " (" + area + ")");
                            }
                        }
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Block b = event.getClickedBlock();
                        selection.setxB(b.getLocation().getBlockX());
                        selection.setzB(b.getLocation().getBlockZ());
                            if (selection.getxA() == 0 & selection.getzA() == 0) {
                                player.sendMessage("[Point B] X:" + b.getLocation().getBlockX() + " Z:" + b.getLocation().getBlockZ());
                            }
                            else {
                                int sideX = Math.abs((selection.getxA()-selection.getxB())) + 1;
                                int sideZ = Math.abs((selection.getzA()-selection.getzB())) + 1;
                                int area = sideX * sideZ;
                                player.sendMessage("[Point B] X:" + b.getLocation().getBlockX() + " Z:" + b.getLocation().getBlockZ() + " (" + area + ")");
                            }
                    }
                }
            }
        }
    }
    public int getMinX() {
        return Math.min(selection.getxA(), selection.getxB());
    }
    public int getMaxX() {
        return Math.max(selection.getxA(), selection.getxB());
    }
    public int getMinZ() {
        return Math.min(selection.getzA(), selection.getzB());
    }
    public int getMaxZ() {
        return Math.max(selection.getzA(), selection.getzB());
    }
    public String getUsername() {
        return selection.username;
    }
}

