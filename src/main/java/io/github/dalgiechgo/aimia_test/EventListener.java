package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;


public class EventListener implements Listener {

//    private final Test plugin;
//
//    public EventListener(Test plugin){
//        this.plugin = plugin;
//    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        JavaPlugin plugin = Test.inst();
        plugin.getLogger().info("joined");
        e.setJoinMessage(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GOLD + " is Back!! aaaaah!!! WelcomeImissedYou!!");
//        BukkitTask socketTask = new SocketTask(plugin, e.getPlayer()).runTaskTimerAsynchronously(plugin, 20L, 100L);
        BukkitTask socketTask = new SocketTask(plugin, e.getPlayer()).runTaskAsynchronously(plugin);
        BukkitTask extask = new ExampleTask(plugin).runTaskAsynchronously(plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        e.setQuitMessage(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GOLD + "!! Noooooo! Dont left me!!");
    }
}
