package me.nexodus.itemmenumanager;

import org.bukkit.plugin.java.JavaPlugin;

public class IMenuPlugin extends JavaPlugin {
    public XmasPresentGUI xmasInventory = new XmasPresentGUI(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(xmasInventory.getListener(), this);
        getCommand("xmas-gifts").setExecutor(new XmasCmd(this));
    }
}
