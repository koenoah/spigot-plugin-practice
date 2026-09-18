package me.nexodus.playerfinder;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


public class PlayerFindCommand implements CommandExecutor, TabCompleter {
    private PlayerFinderPlugin plugin;

    private String Sp(double n) { return String.format(String.format("%.2f", n)); }
    private String Sp(float n) { return String.format("%.2f", n); }

    private String missingMsg(String string) {
        return ChatColor.RED + "Could not find player \"" + string + "\"";
    }
    private boolean targetIsNull(Player target, CommandSender sender, String arg) {
        if (target == null) { 
            if (sender instanceof Player player) {
                player.sendMessage(missingMsg(arg));
            } else { plugin.getLogger().warning(missingMsg(arg)); }
            return true; 
        }
        return false;
    }

    PlayerFindCommand(PlayerFinderPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //I tried to use modern switch here but due to needing more if checks I resorted to a normal else if nest
        if (label.equals("find")) {
            if (args.length < 1) return false;

            Player target = Bukkit.getPlayer(args[0]);
            if (targetIsNull(target, sender, args[0])) return true;

            Location loc = target.getLocation();
            // Needed Sp to parse in a single line and not be as bloated.
            String strLocation = String.join(", ", Sp(loc.getX()), Sp(loc.getY()), Sp(loc.getZ()), Sp(loc.getYaw()), Sp(loc.getPitch())); 
            String msg = target.getName() + "'s location is: " + strLocation;

            if (sender instanceof Player player) {
                player.sendMessage(msg);
            } else {
                plugin.getLogger().info(msg);
            }
            return true;
        } else if (label.equals("findb")) {
            if (args.length < 1) return false;

            Player target = Bukkit.getPlayer(args[0]);
            if (targetIsNull(target, sender, args[0])) return true;

            Location loc = target.getLocation();

            Block blockBelow = loc.clone().subtract(0, 1, 0).getBlock();
            Material type = blockBelow.getType();
            Location blockLoc = blockBelow.getLocation();

            String strLocation = String.join(", ", Sp(blockLoc.getX()), Sp(blockLoc.getY()), Sp(blockLoc.getZ()));
            String msg = target.getName() + "'s nearest block is " + type.name() + ". With coordinates: " + strLocation;

            if (sender instanceof Player player) {
                player.sendMessage(msg);
            } else {
                plugin.getLogger().info(msg);
            }
            return true;
        } else {
            if (!(sender instanceof Player player)) return false;

            Location loc = player.getLocation();

            String strLocation = String.join(", ", Sp(loc.getX()), Sp(loc.getY()), Sp(loc.getZ()), Sp(loc.getYaw()), Sp(loc.getPitch())); 
            String msg = "Your location is: " + strLocation;

            player.sendMessage(msg);
            return true;
        }
    }
        
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] args) {
        return null; //Null automatically makes TabComplete search for players
    }
}
