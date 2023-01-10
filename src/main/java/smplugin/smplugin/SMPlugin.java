package smplugin.smplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import smplugin.smplugin.commands.Claim;
import smplugin.smplugin.commands.ClaimWand;
import smplugin.smplugin.commands.Spawn;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.events.PvpEvent;
import smplugin.smplugin.handlers.CommandHandler;
import smplugin.smplugin.handlers.SelectionHandler;

public final class SMPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Loading SMPlugin!");

        getServer().getPluginManager().registerEvents(new SelectionHandler(), this);
        getServer().getPluginManager().registerEvents(new Spawn(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PvpEvent(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("claimwand").setExecutor(new ClaimWand());
        getCommand("cw").setExecutor(new ClaimWand());
        getCommand("claim").setExecutor(new Claim());
        getCommand("setspawn").setExecutor(new Spawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("minmax").setExecutor(new CommandHandler());

    }
    @Override
    public void onDisable() {

        Bukkit.getLogger().info("Closing SMPlugin!");
    }

}
