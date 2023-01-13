package smplugin.smplugin.handlers;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import smplugin.smplugin.util.ClaimUtil;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class TerritoryHandler {

    public boolean hasTerritory(Player player) {
        FileConfiguration fc = ClaimUtil.loadClaims();
        return fc.contains(String.valueOf(player.getUniqueId()));
    }

    public boolean blockIsInTerritory(Player player, Block block) {
        FileConfiguration fc = ClaimUtil.loadClaims();
        int xA = fc.getInt(player.getUniqueId() + ".coordinates.xA");
        int xB = fc.getInt(player.getUniqueId() + ".coordinates.xB");
        int zA = fc.getInt(player.getUniqueId() + ".coordinates.zA");
        int zB = fc.getInt(player.getUniqueId() + ".coordinates.zB");
        int minX = Math.min(xA, xB);
        int maxX = Math.max(xA, xB);
        int minZ = Math.min(zA, zB);
        int maxZ = Math.max(zA, zB);
        int locX = block.getLocation().getBlockX();
        int locZ = block.getLocation().getBlockZ();
        return (minX <= locX & locX <= maxX) & (minZ <= locZ & locZ <= maxZ);
    }
    public boolean blockIsInWilderness(Block block) {

        FileConfiguration fc = ClaimUtil.loadClaims();
        int blockX = block.getLocation().getBlockX();
        int blockZ = block.getLocation().getBlockZ();
        Set set = fc.getKeys(false);
        Iterator it = set.iterator();
        boolean b = true;

        while (it.hasNext()) {
            String uuid = (String) it.next();
            int locxA = fc.getInt(uuid + ".coordinates.xA");
            int locxB = fc.getInt(uuid + ".coordinates.xB");
            int loczA = fc.getInt(uuid + ".coordinates.zA");
            int loczB = fc.getInt(uuid + ".coordinates.zB");
            int minX = Math.min(locxA, locxB);
            int maxX = Math.max(locxA, locxB);
            int minZ = Math.min(loczA, loczB);
            int maxZ = Math.max(loczA, loczB);
            if ((minX <= blockX && blockX <= maxX) && (minZ <= blockZ && blockZ <= maxZ)) {
                b = false;
            }
        }
        return b;
    }
}
