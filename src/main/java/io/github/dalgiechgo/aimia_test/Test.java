package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.github.dalgiechgo.aimia_test.ExampleTask.*;
import static io.github.dalgiechgo.aimia_test.SocketTask.*;

public final class Test extends JavaPlugin {

    private static Test instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getLogger().info("STARTED!!!!!!!!!");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
//        EventListener listener = new EventListener(this);

//        BukkitTask socketTask = new SocketTask(this).runTaskTimerAsynchronously(this, 0L, 1L);
//        BukkitTask extask = new ExampleTask(this).runTaskAsynchronously(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Test inst() {
        return instance;
    }
}

/*
TODO
socket, get/send json data with python
get player location on python
left(), right() on python
runtasktimer/runtask make program simultaneously
json
cuda
 */