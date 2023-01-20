package smplugin.smplugin.handlers;

import net.md_5.bungee.chat.SelectorComponentSerializer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import smplugin.smplugin.selections.Selection;
import smplugin.smplugin.util.ClaimUtil;

import java.lang.reflect.Array;
import java.util.*;

public class TerritoryHandler {

    public boolean hasTerritory(Player player) {
        return ClaimUtil.getClaim(player.getUniqueId()) != null;
    }

    public boolean blockIsInTerritory(UUID uuid, Block block) {
        if (!(ClaimUtil.getClaim(uuid) == null)) {
            Selection selection = ClaimUtil.getClaim(uuid);
            int xA = selection.getxA();
            int xB = selection.getxB();
            int zA = selection.getzA();
            int zB = selection.getzB();
            int minX = Math.min(xA, xB);
            int maxX = Math.max(xA, xB);
            int minZ = Math.min(zA, zB);
            int maxZ = Math.max(zA, zB);
            int locX = block.getLocation().getBlockX();
            int locZ = block.getLocation().getBlockZ();
            return (minX <= locX & locX <= maxX) & (minZ <= locZ & locZ <= maxZ);
        }
        return false;
    }
    public boolean blockIsInWilderness(Block block) {

        HashMap<UUID, Selection> claims = ClaimUtil.getClaims();
        Set<UUID> uuids = claims.keySet();
        boolean b = true;

        for (UUID uuid : uuids) {
            Selection selection = claims.get(uuid);
            if (selection.isWithin(block.getLocation())){
                b = false;
            }
        }
        return b;
    }
    public static String territoryName;
    public static String playerIsInTerritory(Player player) {
        HashMap<UUID, Selection> claims = ClaimUtil.getClaims();
        Set<UUID> uuids = claims.keySet();
        int locX = player.getLocation().getBlockX();
        int locZ = player.getLocation().getBlockZ();
        for (UUID uuid : uuids) {
            Selection selection = claims.get(uuid);
            int xA = selection.getxA();
            int xB = selection.getxB();
            int zA = selection.getzA();
            int zB = selection.getzB();
            int minX = Math.min(xA, xB);
            int maxX = Math.max(xA, xB);
            int minZ = Math.min(zA, zB);
            int maxZ = Math.max(zA, zB);
            if ((minX <= locX && locX <= maxX) && (minZ <= locZ && locZ <= maxZ)) {
                territoryName = selection.getTerritoryname();
                playerLocation.put(uuid, territoryName);
            }
        }
        return territoryName;
    }
    public boolean playerIsInWilderness(Player player) {
        return !(Objects.equals(playerIsInTerritory(player), "Wilderness"));
    }
    public static HashMap<UUID, String> playerLocation = new HashMap<>();

    public static HashMap<UUID, String> getPlayerLocation() {
        return playerLocation;
    }
}
