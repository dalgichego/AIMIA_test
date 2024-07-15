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

    public SocketServer(JavaPlugin plugin){this.plugin = plugin;}

    public void run(){
        try{
            JSONParser parser = new JSONParser();
            JSONObject obj;

            ServerSocket serverSocket = new ServerSocket(8112);
            plugin.getLogger().info("waiting for connect...");
            Socket socket = serverSocket.accept();
            plugin.getLogger().info("client connected");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String rdata = br.readLine();
            plugin.getLogger().info("receive: " + rdata);
            obj = (JSONObject)parser.parse(rdata);
            int num = ((Long)obj.get("num")).intValue();
            obj.replace("contents", "this is a content from Java");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            String sdata = obj.toString();
//            String sdata = "{\"name\":\"server\",  \"contents\":\"this is a content from Java\", \"num\":\""+num+"\"}";
//            obj = (JSONObject)parser.parse(sdata);
            bw.write(sdata);
//            bw.newLine();
            bw.flush();

//            OutputStream sender = socket.getOutputStream();
//            String msg = "Hello python, This is Java. ";
//            byte[] data = msg.getBytes();
//            ByteBuffer b = ByteBuffer.allocate(4);
//            b.order(ByteOrder.LITTLE_ENDIAN);
//            b.putInt(data.length);
//            sender.write(b.array(), 0, 4);
//            sender.write(data);

//            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            out.println("Hello");
//            out.flush();

            socket.close();
            serverSocket.close();
            br.close();
            bw.close();
            plugin.getLogger().info("finish");
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

}
