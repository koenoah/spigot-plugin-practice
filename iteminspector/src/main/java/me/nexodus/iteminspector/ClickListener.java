package me.nexodus.iteminspector;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnPlayerClick(PlayerInteractEvent event) {
        if (    event.getAction() != Action.RIGHT_CLICK_AIR 
                && event.getAction() != Action.RIGHT_CLICK_BLOCK    ) {
            return;
        }

        event.setCancelled(true);

        ItemStack item = event.getItem();
        if (item == null || item.getType() == Material.AIR) return;

        Player player = event.getPlayer();

        Material type = item.getType();
        int amount = item.getAmount();

        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            String displayName = meta.hasDisplayName() ? meta.getDisplayName() : type.name();

            player.sendMessage("You clicked with item: " + displayName + " ( Amount " + amount + " )");

            if (meta.hasLore()) {
                player.sendMessage("Lore: " + String.join(", ", meta.getLore()));
            }
        } else {
            player.sendMessage("You clicked with standard item: " + type.name() + " ( Amount " + amount + " )");
        }
    }
}
