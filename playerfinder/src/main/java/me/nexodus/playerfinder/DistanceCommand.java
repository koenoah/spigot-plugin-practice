package me.nexodus.playerfinder;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DistanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        Block targetBlock = player.getTargetBlockExact(20);

        if (targetBlock == null) {
            player.sendMessage("Look at a block within 20 blocks to run the check");
            return true;
        }

        Vector playerLoc = player.getLocation().toVector();
        Vector blockLoc = targetBlock.getLocation().toVector();

        Vector vectorToBlock = blockLoc.subtract(playerLoc);
        double vectorMagnitude = vectorToBlock.length();

        player.sendMessage("Vector Magnitude: " + String.format("%.2f", vectorMagnitude));

        return true;
    }
}
