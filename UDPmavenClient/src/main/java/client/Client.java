package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ServerIsUnavailableException;
import gson.CoordinatesSerializer;
import gson.DragonHeadSerializer;
import gson.DragonSerializer;
import items.*;
import commands.*;


import java.io.IOException;
import java.net.*;
import java.util.*;
//import com.google.gson.*;

public class Client {

    public static boolean isReadFromFile = false;
    public static int fileReaderCounter = 0;
    public static String inputFile = null;
    public static ComandManager comandManager = new ComandManager();

    public static long sendTime;
    public static boolean sendTimeFlag;
    private String host;
    private int port;
    public static int clientPort = 5001;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendMessage(String mes) {
        try {
            byte[] data = mes.getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket pack = new DatagramPacket(data, data.length, address, port);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
            sendTime = new Date().getTime();
            sendTimeFlag = true;
        } catch (IOException e) {
        }
    }

    public static void sendMessage(Comand comand){
        Client sender = new Client("localhost", 9999);
        comand.setPort();
        sender.sendMessage(serialize(comand));

        if(sendTime == -1)
            sendTime = new Date().getTime();
        else
            sendTime = Math.min(sendTime, new Date().getTime());
    }

    public static String serialize(Comand comand){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Dragon.class, new DragonSerializer())
                .registerTypeAdapter(DragonHead.class, new DragonHeadSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .create();
        return gson.toJson(comand);
    }

    private static void listen(){
        DatagramSocket socket = null;
        try {
            boolean bindFlag = true;
            while (bindFlag)
            try {
                socket = new DatagramSocket(clientPort);
                bindFlag = false;
            } catch (BindException ex){
                clientPort++;
            }
            byte[] buf = new byte[8192];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            sendTimeFlag = false;
            String message = new Gson().fromJson(new String(packet.getData(), 0, packet.getLength()), String.class);
            System.out.println(message);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    public static void main(String[] args){

        Thread checkTime = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(new Date().getTime() - sendTime > 1000 && sendTimeFlag && sendTime != -1){
                        try{
                            throw new ServerIsUnavailableException();
                        } catch (ServerIsUnavailableException e){
                            e.printStackTrace();
                            System.err.println("Клиент отключен");
                            System.exit(404);
                        }
                        sendTimeFlag = false;
                        sendTime = -1;
                    }
                }
            }, 250, 250);
        });

        checkTime.run();

        Thread downloader = new Thread(() -> {
                while(true)
                    Client.listen();
        });

        downloader.start();

        while(true){
            Scanner input = new Scanner(System.in);
            try {
                String fullInputString = input.nextLine();
                if(fullInputString.equals("^D")) {
                    System.exit(0);
                }
                String[] inputString = fullInputString.split("\n");
                Comand comand = comandManager.excecute(inputString[0]);
                if (comand != null)
                    if (comand.isToTransport())
                        sendMessage(comand);
            } catch (NoSuchElementException ex){
                System.exit(20);
            }
        }
    }

}