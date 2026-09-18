package me.nexodus.blockprotect;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

public class BlockProtectPlugin extends JavaPlugin implements Listener {
    private PlayerJoinListener playerJoinListener = new PlayerJoinListener(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        ToggleCommand command = new ToggleCommand(this);
        getCommand("toggle-blockprotect").setExecutor(command);
        getCommand("toggle-blockprotect").setTabCompleter(command);
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        if (event.getType() == ServerLoadEvent.LoadType.STARTUP) {
            getLogger().info("Detected Server STARTUP safely");

            getServer().getPluginManager().registerEvents(playerJoinListener, this);
        }
    }

    @Override
    public void onDisable() {
    }
}
