package me.nexodus.luckyblocks;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {
    private String badblockmsg = "[" + ChatColor.RED + "BAD LUCK" + ChatColor.RESET + "] ";
    private String regularblockmsg = "[" + ChatColor.GRAY + "REGULAR LUCK" + ChatColor.RESET + "] ";
    private String luckyblockmsg = "[" + ChatColor.GREEN + "GOOD LUCK" + ChatColor.RESET + "] ";

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return; 
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block block = event.getClickedBlock();

        if (block.getType() == Material.DIAMOND_ORE) {
            if (!(item == null || item.getType() == Material.AIR)) return;
            ThreadLocalRandom random = ThreadLocalRandom.current();

            Location loc = block.getLocation();
            World world = loc.getWorld();

            world.spawnParticle(Particle.FIREWORK, loc, 10, 0.0, 0.0, 0.0, 0.1);
            world.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);

            block.setType(Material.AIR);

            int scenario = random.nextInt(5);

            switch(scenario) {
                case 0 -> {
                    world.dropItem(loc, new ItemStack(Material.DIAMOND, 5));
                    player.sendMessage(luckyblockmsg + "Rolled diamonds");
                }
                case 1 -> {
                    Vector direction = player.getLocation().getDirection();
                    Vector velocity = direction.multiply(1.5).setY(0.8);
                    player.setVelocity(velocity);

                    player.sendMessage(regularblockmsg + "Rolled a high jump");
                }
                case 2 -> {
                    world.dropItem(loc, new ItemStack(Material.COAL, 4));
                    player.sendMessage(regularblockmsg + "Rolled coal");
                }
                case 3 -> {
                    world.spawn(loc, Zombie.class);
                    player.sendMessage(badblockmsg + "Rolled zombie");
                }
                case 4 -> {
                    world.spawn(loc, Zombie.class, mob -> {
                        mob.setCustomName("King Zombie");
                        mob.setCustomNameVisible(true);
                        mob.setHealth(20.0);

                        mob.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 1200, 4));
                        mob.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 1200, 4));

                        AttributeInstance maxHealthAttr = mob.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        if (maxHealthAttr != null) {
                            maxHealthAttr.setBaseValue(50.0);
                            mob.setHealth(50.0);
                        }

                        AttributeInstance attackDamage = mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        if (maxHealthAttr != null) {
                            attackDamage.setBaseValue(8.0);
                        }
                    });
                    player.sendMessage(badblockmsg + "Rolled KING ZOMBIE");
                }
            }

            return;
        }

        if (item == null || item.getType() != Material.FEATHER) return;

        block.setType(Material.DIAMOND_ORE);

        item.setAmount(item.getAmount() - 1);

        return;
    }
}
