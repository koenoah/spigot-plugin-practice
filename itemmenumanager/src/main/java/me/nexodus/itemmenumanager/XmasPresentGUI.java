package me.nexodus.itemmenumanager;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class XmasPresentGUI {
    private final Inventory gui;
    private NamespacedKey itemKey;
    private ClickListener listener;
    private static final String title = ChatColor.YELLOW + "Christmas Presents";

    XmasPresentGUI(IMenuPlugin plugin) {
        itemKey = new NamespacedKey(plugin, "custom_active_item");
        listener = new ClickListener(itemKey);

        gui = Bukkit.createInventory(null, 9, title);
    }

    public static String getTitle() {
        return title;
    }

    public ClickListener getListener() {
        return listener;
    }

    private void refillInventory() {
        ItemStack coal = new ItemStack(Material.COAL);
        ItemMeta meta = coal.getItemMeta();

        List<String> lore = Arrays.asList(ChatColor.GRAY + "You've been a naughty one this year");
        meta.setLore(lore);
        meta.setDisplayName("§8Naughty Coal");
        meta.getPersistentDataContainer().set(itemKey, PersistentDataType.STRING, "naughty_coal");

        coal.setItemMeta(meta);

        gui.setItem(0, coal);
    }

    public void open(Player player) {
        refillInventory();
        player.openInventory(gui);
    }

}
