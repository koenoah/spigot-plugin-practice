package me.nexodus.itemmenumanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XmasCmd implements CommandExecutor {
    private final IMenuPlugin plugin;
    XmasCmd(IMenuPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        plugin.xmasInventory.open(player);

        return false;
    }
}
