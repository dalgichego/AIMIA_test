package io.github.dalgiechgo.aimia_test;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class SocketServer {

    private final JavaPlugin plugin;
    public Socket socket;
    public ServerSocket serverSocket;
    public BufferedReader br;
    public BufferedWriter bw;

    public SocketServer(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public Socket getSocket(){
        return socket;
    }

    public void connect(){
        try{
            serverSocket = new ServerSocket(8112);
            plugin.getLogger().info("waiting for connect...");
            socket = serverSocket.accept();
            plugin.getLogger().info("client connected");

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            socket.close();
            serverSocket.close();
            br.close();
            bw.close();
            plugin.getLogger().info("finish");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String receive(){
        try {
            String rdata = br.readLine();
            return rdata;
        }
        catch (IOException e){
            e.printStackTrace();
            return "None";
        }
    }

    public void write(String msg){
        try {
            bw.write(msg);
            bw.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
