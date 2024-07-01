package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketServer {

    private final JavaPlugin plugin;

    public SocketServer(JavaPlugin plugin){this.plugin = plugin;}

    public void run(){
        try{
            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(8112);
            plugin.getLogger().info("waiting for connect...");
            Socket socket = serverSocket.accept();
            plugin.getLogger().info("client connected");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            String rev = br.readLine();
            plugin.getLogger().info(rev);

            br.close();
            socket.close();
            serverSocket.close();
            plugin.getLogger().info("finish");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
