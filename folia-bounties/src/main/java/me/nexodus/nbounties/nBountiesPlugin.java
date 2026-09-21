package me.nexodus.nbounties;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.tcoded.folialib.FoliaLib;

public class nBountiesPlugin extends JavaPlugin {
    private FoliaLib foliaLib;

    //This idea actually intrigues me, I might actually turn this into an actual project in the future

    @Override
    public void onEnable() {
        this.foliaLib = new FoliaLib(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        foliaLib.getScheduler().runNextTick(task -> {
            getLogger().info("nBounties is running! This plugin is running through Folia compatibility FoliaLib");
        });

        Location loc = getServer().getWorlds().get(0).getSpawnLocation();
        foliaLib.getScheduler().runAtLocation(loc, task -> {
            getLogger().info("Running a test task at chunk specific location");
        });
    }

    @Override
    public void onDisable() {
        if (foliaLib != null) {
            foliaLib.getScheduler().cancelAllTasks();
        }
    }

    public FoliaLib getFoliaLib() {
        return foliaLib;
    }
}
