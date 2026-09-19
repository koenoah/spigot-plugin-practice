package me.nexodus.servermessages;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerMessages extends JavaPlugin {
    @Override
    public void onEnable() {
        PlayerListener listener = new PlayerListener(this);
        getServer().getPluginManager().registerEvents(listener, this);

        getCommand("primes").setExecutor(new PrimeCommand(this));

        new BukkitRunnable() {
           @Override
           public void run() {
               Bukkit.broadcastMessage("§a[Server] Remember to vote for the server!");
           }
       }.runTaskTimer(this, 0L, 200L);
    }
}
