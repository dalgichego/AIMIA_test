package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SocketTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public SocketTask(JavaPlugin plugin){this.plugin = plugin;}

    @Override
    public void run(){
        SocketServer serv = new SocketServer(plugin);
        serv.run();
    }

}
