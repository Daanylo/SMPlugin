package smplugin.smplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import smplugin.smplugin.SMPlugin;
import smplugin.smplugin.selections.Selection;

import java.io.File;
import java.util.*;

public class ClaimUtil {

    public static void createClaims() {

        new File(SMPlugin.getInstance().getDataFolder() + "/claims").mkdir();
        File claims = new File(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
        FileConfiguration fc = new YamlConfiguration();

        if (!claims.exists()) {
            try {
                claims.createNewFile();
                fc.save(claims);
                Bukkit.getLogger().info("claims.yml created.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public static HashMap<UUID, Selection> claims = new HashMap<>();

    public static HashMap<UUID, Selection> getClaims() {
        return claims;
    }
    public static void addClaim(UUID uuid, Selection selection) {
        claims.put(uuid, selection);
    }
    public static void removeClaim(UUID uuid) {
        HashMap<UUID, Selection> claims = getClaims();
        claims.remove(uuid);
        }


    public static void loadClaims() {
        File claimfile = new File(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
        FileConfiguration fc = new YamlConfiguration();
        try {
            fc.load(claimfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!(fc.getKeys(false).size() == 0)) {
            Set<String> uuids = fc.getKeys(false);
            for (String uuid : uuids) {

                Selection selection = new Selection(null, 0, 0, 0, 0);

                String territoryName = fc.getString(uuid + ".territory name");
                int xA = fc.getInt(uuid + ".coordinates.xA");
                int xB = fc.getInt(uuid + ".coordinates.xB");
                int zA = fc.getInt(uuid + ".coordinates.zA");
                int zB = fc.getInt(uuid + ".coordinates.zB");

                selection.setTerritoryName(territoryName);
                selection.setxA(xA);
                selection.setzA(zA);
                selection.setxB(xB);
                selection.setzB(zB);

                claims.put(UUID.fromString(uuid), selection);
            }
        }
    }
    public static void saveClaims() {

        File claimfile = new File(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
        claimfile.delete();
        createClaims();

        FileConfiguration fc = new YamlConfiguration();

            for (UUID uuid : claims.keySet()) {
                Selection selection = claims.get(uuid);
                String territoryName = selection.getTerritoryname();
                int xA = selection.getxA();
                int zA = selection.getzA();
                int xB = selection.getxB();
                int zB = selection.getzB();
                fc.set(uuid + ".territory name", territoryName);
                fc.set(uuid + ".coordinates.xA", xA);
                fc.set(uuid + ".coordinates.zA", zA);
                fc.set(uuid + ".coordinates.xB", xB);
                fc.set(uuid + ".coordinates.zB", zB);
            }
        try {
            fc.save(claimfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Selection getClaim(UUID uuid) {

        Selection selection;
        if (claims.containsKey(uuid)) {
            selection = claims.get(uuid);
        } else {
            return null;
        }
        return selection;
    }
}

