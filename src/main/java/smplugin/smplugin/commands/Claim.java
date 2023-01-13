package smplugin.smplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.Yaml;
import smplugin.smplugin.SMPlugin;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.selections.Selection;
import smplugin.smplugin.util.ClaimUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.module.Configuration;
import java.util.Objects;

public class Claim implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration fc = ClaimUtil.loadClaims();
        Player player = (Player) sender;
        String uuid = String.valueOf(player.getUniqueId());
        TerritoryHandler territoryHandler = new TerritoryHandler();

        if (args.length == 0) {
            player.performCommand("help claim");
        } else {
            if (Objects.equals(args[0], "remove")) {
                if (territoryHandler.hasTerritory(player)) {
                    String territoryName = (String) fc.get(uuid + ".territory name");
                    fc.set(String.valueOf(player.getUniqueId()), null);
                    try {
                        fc.save(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
                        player.sendMessage("Territory \"" + territoryName + "\" was removed successfully.");
                    } catch (IOException e) {
                        e.printStackTrace();
                        player.sendMessage("Territory was not removed, please contact staff.");
                    }
                } else {
                    player.sendMessage("You don't have a territory yet.");
                }
            }

            if (args.length == 1) {
                if ((Objects.equals(args[0], "set"))) {
                    player.sendMessage("Please use /claim set (name)");
                }
                if (!(Objects.equals(args[0], "set") | (Objects.equals(args[0], "remove")))) {
                    player.sendMessage("/claim \"" + args[0] + "\" is not a valid command.");
                }
            }

            if ((Objects.equals(args[0], "set"))) {

                if (territoryHandler.hasTerritory(player)) {
                    player.sendMessage("You already have a territory!");
                } else {
                    if (args.length > 2) {
                        player.sendMessage("Your territory name may not contain spaces.");
                    } else {
                        String territoryName = args[1];
                        fc.set(uuid + ".territory name", territoryName);
                        fc.set(uuid + ".coordinates.xA", JoinEvent.selections.get(player.getUniqueId()).getxA());
                        fc.set(uuid + ".coordinates.zA", JoinEvent.selections.get(player.getUniqueId()).getzA());
                        fc.set(uuid + ".coordinates.xB", JoinEvent.selections.get(player.getUniqueId()).getxB());
                        fc.set(uuid + ".coordinates.zB", JoinEvent.selections.get(player.getUniqueId()).getzB());
                        try {
                            fc.save(SMPlugin.getInstance().getDataFolder() + "/claims/" + "claims.yml");
                            player.sendMessage("Territory \"" + territoryName + "\" was claimed successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                            player.sendMessage("Territory was not saved, please contact staff.");
                        }
                    }
                }
            }
            if (args.length > 2 & (!(Objects.equals(args[0], "set") | (Objects.equals(args[0], "remove"))))) {
                player.sendMessage("/claim \"" + args[0] + "\" is not a valid command.");
            }
        }
        return true;
    }
}




