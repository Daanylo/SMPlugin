package smplugin.smplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import smplugin.smplugin.SMPlugin;


import java.util.HashMap;

public class ClaimWand implements CommandExecutor {

    Plugin plugin = SMPlugin.getPlugin(SMPlugin.class);
    public HashMap<String, Long> cooldowns = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        int cooldownTime = plugin.getConfig().getInt("claimwand cooldown");

        if(cooldowns.containsKey(sender.getName())) {
            long secondsLeft = ((cooldowns.get(sender.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft>0) {
                sender.sendMessage("You cant use that command for another "+ secondsLeft +" seconds!");
                return true;
            }
        }

        cooldowns.put(sender.getName(), System.currentTimeMillis());
        Player player = (Player) sender;
        ItemStack claimwand = new ItemStack(Material.STICK);
        ItemMeta meta = claimwand.getItemMeta();
        meta.setDisplayName("Claim Wand");
        claimwand.setItemMeta(meta);


        if (player.getInventory().containsAtLeast(claimwand, 1)) {

            sender.sendMessage("You already have a Claim Wand!");

        }

        else {
            player.getInventory().addItem(claimwand);
            sender.sendMessage("You have received a Claim Wand");
        }
        return true;

    }

}
