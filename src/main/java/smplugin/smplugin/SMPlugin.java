package smplugin.smplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import smplugin.smplugin.commands.Claim;
import smplugin.smplugin.commands.ClaimWand;
import smplugin.smplugin.commands.Spawn;
import smplugin.smplugin.events.JoinEvent;
import smplugin.smplugin.events.PvpEvent;
import smplugin.smplugin.events.TerritoryEnterEvent;
import smplugin.smplugin.events.TerritoryProtector;
import smplugin.smplugin.handlers.SelectionHandler;
import smplugin.smplugin.tabcompleters.ClaimTabCompleter;
import smplugin.smplugin.util.ClaimUtil;

public final class SMPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Loading SMPlugin!");

        getServer().getPluginManager().registerEvents(new SelectionHandler(), this);
        getServer().getPluginManager().registerEvents(new Spawn(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PvpEvent(), this);
        getServer().getPluginManager().registerEvents(new TerritoryEnterEvent(), this);
        getServer().getPluginManager().registerEvents(new TerritoryProtector(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ClaimUtil.createClaims();
        ClaimUtil.loadClaims();

        getCommand("claimwand").setExecutor(new ClaimWand());
        getCommand("cw").setExecutor(new ClaimWand());
        getCommand("claim").setExecutor(new Claim());
        getCommand("setspawn").setExecutor(new Spawn());
        getCommand("spawn").setExecutor(new Spawn());

        getCommand("claim").setTabCompleter(new ClaimTabCompleter());

    }

    public static Plugin getInstance() {
        return SMPlugin.getPlugin(SMPlugin.class);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Closing SMPlugin!");
        ClaimUtil.saveClaims();
    }

}
