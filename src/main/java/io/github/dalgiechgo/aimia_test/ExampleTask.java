package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class ExampleTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public ExampleTask(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){
        try {
            ProcessBuilderTest.doTest(plugin.getLogger());
        } catch (IOException e) {
            e.printStackTrace();
        }
        plugin.getLogger().info("moving!!");
    }

}
