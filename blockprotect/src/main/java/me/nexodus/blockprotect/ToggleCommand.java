package me.nexodus.blockprotect;

import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ToggleCommand implements CommandExecutor, TabCompleter {
    private BlockProtectPlugin plugin;

    ToggleCommand(BlockProtectPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) return false;

        if (sender instanceof Player player) {
            player.sendMessage("Only an admin can access this command");
        } else {
            plugin.getConfig().set("protect-blocks", Boolean.parseBoolean(args[0]));
            plugin.saveConfig();

            plugin.getLogger().info("Set BlockProtect to " + args[0]);
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
        }

        return suggestions;
    }
}
