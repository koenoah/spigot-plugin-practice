package me.nexodus.blockprotect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerJoinListener implements Listener {
    private BlockProtectPlugin plugin;
    private final String BOLD_CYAN = (ChatColor.BOLD + "" + ChatColor.AQUA);

    public PlayerJoinListener(BlockProtectPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
       Player player = event.getPlayer(); 
       plugin.getLogger().info("\"" + player.getName() + "\"" + " triggered onJoin event on PlayerJoinListener");
       
       boolean protectBlocks = plugin.getConfig().getBoolean("protect-blocks");
       if (protectBlocks) {
           String msg = "Plugin is enabled. Block breaking will be prohibited, access terminal to grant permission.";
           String joinMsg = ChatColor.BOLD + "[" + BOLD_CYAN + "Block-Protect" + ChatColor.WHITE + ChatColor.BOLD + "]" + ChatColor.RESET + " " + msg;
           player.sendMessage(joinMsg);
       }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        boolean protectBlocks = plugin.getConfig().getBoolean("protect-blocks");
        if (protectBlocks) {
            event.setCancelled(true);

            Player player = event.getPlayer();
            player.sendMessage("You're not allowed to break blocks right now.");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        boolean protectBlocks = plugin.getConfig().getBoolean("protect-blocks");
        if (protectBlocks) {
            event.setCancelled(true);

            Player player = event.getPlayer();
            player.sendMessage("You're not allowed to place blocks right now.");
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
       Player player = event.getPlayer(); 
       plugin.getLogger().info("\"" + player.getName() + "\"" + " triggered onLeave event on PlayerJoinListener");
    }
}
