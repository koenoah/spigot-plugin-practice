package com.example.spigot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import org.bukkit.entity.Player;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PVPCommand implements CommandExecutor, TabCompleter {
    private final App plugin;
    
    public PVPCommand(App plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;

        if (sender instanceof Player player) {
            player.sendMessage("This command was meant for the terminal!");
            return true;
        }
        if (sender instanceof ConsoleCommandSender) {
            plugin.getLogger().info("pvp-enabled set to: " + args[0]);
            plugin.getConfig().set("features.pvp-enabled", Boolean.parseBoolean(args[0]));
            plugin.saveConfig();
            
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            List<String> options = Arrays.asList("true", "false");

            StringUtil.copyPartialMatches(args[0], options, suggestions);
        } else if (args.length == 2) {
            List<String> options = Arrays.asList("world", "world_nether", "world_the_end");
            StringUtil.copyPartialMatches(args[1], options, suggestions);
        }

        return suggestions;
    }
}
