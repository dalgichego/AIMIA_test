package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.github.dalgiechgo.aimia_test.ExampleTask.*;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("STARTED!!!!!!!!!");

        JSONObject jo = new JSONObject();
        jo.put("x", 0);
        jo.put("y", 1);
        UseJson jf = new UseJson("plugins\\jsonFiles\\data.json");
        try {
            jf.write(jo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            getLogger().info(jf.read().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BukkitTask extask = new ExampleTask(this).runTaskAsynchronously(this);
//        extask.run();
        getLogger().info("Next statement");

        for(int i=0; i<10; i++){
            getLogger().info(Integer.toString(i)+"java");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

/*
TODO
get player location on python
left(), right() on python
runtasktimer/runtask make program simultaneously
json
cuda
 */