package com.example.spigot;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("My Plugin Example has been enabled");
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().warning("This is a warning! But I'm just testing warnings to be honest");
        String welcome = getConfig().getString("welcome-message");
        getLogger().info(welcome);
        int maxHomes = getConfig().getInt("max-homes");
        boolean pvp = getConfig().getBoolean("features.pvp-enabled");
        getLogger().info("Max homes is set to: " + maxHomes);
        getLogger().info("is PVP enabled: " + pvp);

        PVPCommand pvpCommand = new PVPCommand(this);
        getCommand("set-pvp").setExecutor(pvpCommand);
        getCommand("set-pvp").setTabCompleter(pvpCommand);

        Heal heal = new Heal(this);
        getCommand("heal").setExecutor(heal);
        getCommand("heal").setTabCompleter(heal);
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        if (event.getType() == ServerLoadEvent.LoadType.STARTUP) {
            getLogger().info("Spigot Example has now detected that the server is booted up and ready!");
            getLogger().warning("PAY ATTENTION!");

            getConfig().set("max-homes", 5);
            getConfig().set("features.pvp-enabled", false);
            saveConfig();
            getLogger().info("Some configuration has been changed in startup!");

            int maxHomes = getConfig().getInt("max-homes");
            boolean pvp = getConfig().getBoolean("features.pvp-enabled");
            getLogger().info("Max homes is set to: " + maxHomes);
            getLogger().info("is PVP enabled: " + pvp);
        }
    }

    @Override
    public void onDisable() {
        for (int i = 0; i < 20; i++) {
            getLogger().info("My Plugin Example has been disabled");
        }
    }
}
