package me.nexodus.nbounties;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.tcoded.folialib.FoliaLib;

public class PlayerListener implements Listener {
    private FoliaLib foliaLib;

    PlayerListener(nBountiesPlugin plugin) {
        this.foliaLib = plugin.getFoliaLib();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(
            "top_bounties",
            Criteria.DUMMY,
            "nBounties"         
        );

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score legend = objective.getScore(" Name:  Bounty:");
        legend.setScore(11);

        Score line1 = objective.getScore("1. PlayerOne: $150,000");
        line1.setScore(10);

        Score line2 = objective.getScore("2. PlayerTwo: $120,000");
        line2.setScore(9);

        Score line3 = objective.getScore("3. PlayerThree: $90,000");
        line3.setScore(8);

        player.setScoreboard(scoreboard);

        foliaLib.getScheduler().runLater(() -> {
            player.sendMessage("[nBounties] nBounties activated. You can use /nbountes disable to turn off the GUI");
            //you actually can't but this is just a placeholder
        }, 3L, java.util.concurrent.TimeUnit.SECONDS);
    }
}
