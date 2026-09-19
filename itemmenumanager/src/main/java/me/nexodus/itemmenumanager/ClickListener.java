package me.nexodus.itemmenumanager;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ClickListener implements Listener {
    private final NamespacedKey itemKey;

    ClickListener(NamespacedKey key) {
        itemKey = key;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(XmasPresentGUI.getTitle())) return;

        if (event.isShiftClick()) return;
        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().equals(event.getView().getBottomInventory())) return;
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        Player player = (Player) event.getWhoClicked();
        if (!player.hasPermission("imenumanager.participation.xmas")) {
            event.setCancelled(true);
            player.sendMessage("You didn't participate in the event to claim your present!");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        ItemStack item = event.getItem();

        if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) return;

        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        
        if (pdc.has(itemKey, PersistentDataType.STRING)) {
            String value = pdc.get(itemKey, PersistentDataType.STRING);

            if ("naughty_coal".equals(value)) {
                item.setAmount(item.getAmount() - 1);
                Block block = event.getClickedBlock().getRelative(event.getBlockFace());
                block.setType(Material.FIRE);
            }
        }
    }
}
