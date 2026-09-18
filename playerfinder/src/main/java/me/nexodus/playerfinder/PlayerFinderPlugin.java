package me.nexodus.playerfinder;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerFinderPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        PlayerFindCommand command = new PlayerFindCommand(this);
        DistanceCommand dcommand = new DistanceCommand();

        getCommand("blockd").setExecutor(dcommand);

        getCommand("find-me").setExecutor(command);
        getCommand("find").setExecutor(command);
        getCommand("findb").setExecutor(command);

        getCommand("find-me").setTabCompleter(command);
        getCommand("find").setTabCompleter(command);
        getCommand("findb").setTabCompleter(command);
    }
}
