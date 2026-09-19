package me.nexodus.luckyblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class LuckyBlocksPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        PlayerListener listener = new PlayerListener();
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
