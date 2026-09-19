// This code and command is just for learning purposes meant to be used to learn async code execution
// Unrelated to servermessages
//
package me.nexodus.servermessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PrimeCommand implements CommandExecutor {
    private final JavaPlugin plugin;

    PrimeCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.sendMessage("Starting heavy calculations in the backgrounfd");

        new BukkitRunnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();

                int primeCount = 0;
                for (int i = 2; i <= 10_000_000; i++) {
                    for (int ix = 2; ix <= Math.sqrt(i); ix++) { // Intentionally dense operation to overload CPU and show it doesn't lag the server
                        if (!(ix % i == 0)) primeCount++;
                    }
                }

                long duration = System.currentTimeMillis() - startTime;
                String resultMessage = "Done! Found " + primeCount + " primes in " + duration + "ms.";

                Bukkit.getScheduler().runTask(plugin, () -> { // Reminder to myself: This is how we return to main thread.
                    player.sendMessage(resultMessage);        // We do this because interacting with the server in main thread is a must for safety.
                });
            }
        }.runTaskAsynchronously(plugin);

        return false;
    }
}
