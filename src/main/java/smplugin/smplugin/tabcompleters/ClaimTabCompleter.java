package smplugin.smplugin.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import smplugin.smplugin.SMPlugin;
import smplugin.smplugin.handlers.TerritoryHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClaimTabCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String [] args) {
        if (command.getName().equals("claim") && args.length == 1) {
            ArrayList<String> subcommandsArguments = new ArrayList<>();
            TerritoryHandler territoryHandler = new TerritoryHandler();
            Player player = (Player) sender;
                if (!(territoryHandler.hasTerritory(player))) {
                    subcommandsArguments.add("set");
                }
                else {
                subcommandsArguments.add("remove");
                }
            return subcommandsArguments;
        }
        return null;
    }
}

