package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import org.json.simple.JSONObject;

public class SocketTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final Player player;
    private SocketServer serv;
    private int tick;

    public SocketTask(JavaPlugin plugin, Player player){
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run(){
        serv = new SocketServer(plugin);
        serv.connect();
        tick = 10;

        while(tick>0){

            Location loc = player.getLocation();
            JSONObject playerLocInfo = new JSONObject();
            playerLocInfo.put("x", loc.getX());
            playerLocInfo.put("y", loc.getY());
            playerLocInfo.put("z", loc.getZ());
            serv.write(playerLocInfo.toString());

            String rdata =  serv.receive();
            plugin.getLogger().info(rdata);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            tick--;

        }
        plugin.getLogger().info("socket task finished");
        serv.close();
        this.cancel();
        return;
    }

}
