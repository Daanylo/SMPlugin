package smplugin.smplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import smplugin.smplugin.SMPlugin;

import java.io.File;
import java.util.UUID;

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
    public static FileConfiguration loadClaims() {
        File claims = new File(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
        FileConfiguration fc = new YamlConfiguration();
        try {
            fc.load(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fc;
    }
//    public static void saveClaims() {
//        File claims = new File(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
//        FileConfiguration fc = new YamlConfiguration();
//        try {
//            fc.save(claims);
//            Bukkit.getLogger().info("Claims.yml saved!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

