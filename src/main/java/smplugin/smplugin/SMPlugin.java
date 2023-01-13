package smplugin.smplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;
import smplugin.smplugin.commands.Claim;
import smplugin.smplugin.commands.ClaimWand;
import smplugin.smplugin.commands.Spawn;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.events.PvpEvent;
import smplugin.smplugin.events.TerritoryEnterEvent;
import smplugin.smplugin.handlers.CommandHandler;
import smplugin.smplugin.handlers.SelectionHandler;
import smplugin.smplugin.handlers.TerritoryHandler;
import smplugin.smplugin.tabcompleters.ClaimTabCompleter;
import smplugin.smplugin.util.ClaimUtil;

import java.io.File;

public final class SMPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Loading SMPlugin!");

        getServer().getPluginManager().registerEvents(new SelectionHandler(), this);
        getServer().getPluginManager().registerEvents(new Spawn(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PvpEvent(), this);
        getServer().getPluginManager().registerEvents(new TerritoryEnterEvent(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ClaimUtil.createClaims();

        getCommand("claimwand").setExecutor(new ClaimWand());
        getCommand("cw").setExecutor(new ClaimWand());
        getCommand("claim").setExecutor(new Claim());
        getCommand("setspawn").setExecutor(new Spawn());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("minmax").setExecutor(new CommandHandler());

        getCommand("claim").setTabCompleter(new ClaimTabCompleter());

    }

    public static Plugin getInstance() {
        return SMPlugin.getPlugin(SMPlugin.class);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Closing SMPlugin!");
//        ClaimUtil.saveClaims();
    }

}
