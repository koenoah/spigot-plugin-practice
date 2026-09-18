package me.nexodus.iteminspector;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemInspectorPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
