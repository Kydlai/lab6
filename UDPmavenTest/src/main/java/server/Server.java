package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commands.*;
import exceptions.DontFindCommandException;
import gson.CoordinatesDeserializer;
import gson.DragonDeserializer;
import gson.DragonHeadDeserializer;
import items.Coordinates;
import items.Dragon;
import items.DragonHead;
import items.ItemComparator;
import xml.Parser;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Server {

    public static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Dragon.class, new DragonDeserializer())
            .registerTypeAdapter(DragonHead.class, new DragonHeadDeserializer())
            .registerTypeAdapter(Coordinates.class, new CoordinatesDeserializer())
            .create();
    public static Long idCounter = 1L;
    public static boolean toSend = false;
    public static boolean isReadFromFile = false;
    public static int fileReaderCounter = 0;
    public static String inputFile = null;
    public static boolean isCompareReverse = false;
    public static ProjectCollection collection = new ProjectCollection();
    public static ArrayList<Comand> commands = new ArrayList<Comand>();
    public static InetSocketAddress serverAddress = new InetSocketAddress("localhost", 9999);

    public static void main(String[] args) throws IOException {

        commands.add(new Help());
        commands.add(new Info());
        commands.add(new Show());
        commands.add(new Add());
        commands.add(new UpdateId());
        commands.add(new RemoveById());
        commands.add(new Clear());
        commands.add(new Exit());
        commands.add(new RemoveFirst());
        commands.add(new RemoveLover());
        commands.add(new Reorder());
        commands.add(new CountByCharacter());
        commands.add(new FilterStartsWithDescription());
        commands.add(new PrintDescending());
        commands.add(new ExecuteScript());

        Parser.readerXML(collection);

        DatagramChannel receiver = startReceiver(serverAddress); // ???????????????? ????????????

        while (true) {
            handle(receiveMessage(receiver));// ?????????????? ???????????????? ??????????????????
        }
    }

    public static void handle(String message){
        Comand comand = null;
        boolean toExecute = false;
        String result = null;
        try {
            comand = deserialize(message);
            //System.out.println("?????????? ????????????????????????????: " + comand.toString());
            toExecute = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(toExecute){
            toExecute = false;
            toSend = false;
            if(comand != null) {
                result = comand.execute();
                collection.sort(new ItemComparator());
            }
            if(toSend){
                try {
                    DatagramChannel channel = DatagramChannel.open();
                    sendMessage(channel, new Gson().toJson(result), comand.getPort());
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DatagramChannel startReceiver(InetSocketAddress inetSocketAddress) throws IOException {
        DatagramChannel receiver = DatagramChannel.open(); // ???????????????? ????????????
        InetSocketAddress address = inetSocketAddress; // ?????????????????? ????????????
        receiver.bind(address); // ?????????????????? ???? ????????????

        //System.out.println("Receiver started at:" + address);
        return receiver; // ?????????????? ????????????
    }

    public static String receiveMessage(DatagramChannel receiver){
        ByteBuffer buffer = ByteBuffer.allocate(8192); // ???????????????? ?????????????????????????? ??????????????????
        SocketAddress senderAddress;
        String message = null;
        try {
            senderAddress = receiver.receive(buffer); // ???????????????? ???????????? ???? ????????????
            message = extractMessage(buffer, receiver); // ?????????????????? ??????????????????
            //System.out.println("Received message from sender: " + senderAddress);

        } catch (IOException ex){}

        return message; // ?????????????? ??????????????????
    }

    public static void sendMessage(DatagramChannel sender, String message, int port) throws IOException {

        sender.send(StandardCharsets.UTF_8.newEncoder().encode(CharBuffer.wrap(message)), new InetSocketAddress("localhost", port));
        //System.out.println("?????????????????? ??????????: " + message);
    }

    private static String extractMessage(ByteBuffer buffer, DatagramChannel receiver) {
        buffer.flip(); // ???????????? -> ????????????
        byte[] bytes = new byte[buffer.remaining()]; // ???????????????? ?????????????? ?????????????? ?? ????????????
        buffer.get(bytes); // ???????????????? ???????????? ?? ????????????
        String msg = new String(bytes); // ???????????????? ???? ?????????????? ??????????????
        return msg; // ?????????????? ?????????????????? ?????????????????????? ????????????, ?????????????????????????? ?? ?????????? ??????????????
    }

    private static Comand deserialize(String message) throws DontFindCommandException {
        for (int i = 0; i < commands.size(); i++) {
            try {
                Comand result = gson.fromJson(message, commands.get(i).getClass());
                if (!result.isThisComand(commands.get(i)))
                    throw new DontFindCommandException();
                return result;
            } catch (DontFindCommandException e) {}
        }
        throw new DontFindCommandException();
    }

}