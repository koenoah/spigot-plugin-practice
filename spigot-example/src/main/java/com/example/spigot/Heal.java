package com.example.spigot;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import java.util.List;

public class Heal implements CommandExecutor, TabCompleter {
    private final App plugin;
    
    public Heal(App plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String string, String[] args) {
        if (commandSender instanceof Player player) {
            player.setHealth(20.0);
            player.sendMessage("You've been healed!");
            return true;
        } else {
            plugin.getLogger().warning("UNAUTHORIZED: Command only reachable to player instances");
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] args) {
        return null;
    }
}
