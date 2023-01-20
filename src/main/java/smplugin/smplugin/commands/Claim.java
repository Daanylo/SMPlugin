package smplugin.smplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import smplugin.smplugin.SMPlugin;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.handlers.SelectionHandler;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.selections.Selection;
import smplugin.smplugin.util.ClaimUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Claim implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        TerritoryHandler territoryHandler = new TerritoryHandler();
        Selection selection = SelectionHandler.selections.get(player.getUniqueId());

        if (args.length == 0) {
            player.performCommand("help claim");
        } else {
            if (Objects.equals(args[0], "remove")) {
                if (territoryHandler.hasTerritory(player)) {
                    String territoryName = ClaimUtil.getClaim(uuid).getTerritoryname();
                    ClaimUtil.removeClaim(uuid);
                    player.sendMessage("Territory \"" + territoryName + "\" was removed successfully.");
                } else {
                    player.sendMessage("You don't have a territory yet.");
                }
            }

            if (args.length == 1) {
                if (!(Objects.equals(args[0], "set") | (Objects.equals(args[0], "remove")))) {
                    player.sendMessage("/claim \"" + args[0] + "\" is not a valid command.");
                }
            }
            if (args.length == 2 && !(Objects.equals(args[0], "set"))) {
                player.sendMessage("/claim \"" + args[0] + "\" is not a valid command.");
            }

            if ((Objects.equals(args[0], "set"))) {

                if (territoryHandler.hasTerritory(player)) {
                    player.sendMessage("You already have a territory!");
                } else {
                    if (args.length == 1 && !territoryHandler.hasTerritory(player)) {
                        player.sendMessage("Please use /claim (name)");
                    } else {
                        if (args.length > 2) {
                            player.sendMessage("Your territory name may not contain spaces.");
                        } else {
//                            if (selection.getArea() < 1000) {
//                                player.sendMessage("Please select an area larger than 1000 blocks");
//                            } else {
//                                if (selection.getArea() > 10000) {
//                                    player.sendMessage("Please select an area smaller than 10000 blocks");
//                                } else {
                                    selection.setTerritoryName(args[1]);
                                    ClaimUtil.addClaim(uuid, selection);
                                    player.sendMessage("Territory \"" + selection.getTerritoryname() + "\" was claimed successfully.");
                                }
                            }
                        }
                    }
                }
                if (args.length > 2 & (!(Objects.equals(args[0], "set") | (Objects.equals(args[0], "remove"))))) {
                    player.sendMessage("/claim \"" + args[0] + "\" is not a valid command.");
                }
//            }
//        }
        return true;
    }
}





