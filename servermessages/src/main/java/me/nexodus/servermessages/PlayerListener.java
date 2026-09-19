package me.nexodus.servermessages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {
    private ServerMessages plugin;

    PlayerListener(ServerMessages plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("§a[Server] Welcome to the server!"); //And then we put a guide like "do /guide to get guide!" or something
            }
        }.runTaskLater(plugin, 100L);
    }
}
