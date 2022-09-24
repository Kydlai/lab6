package commands;

import items.*;
import server.ProjectCollection;
import server.Server;

import java.util.stream.Stream;

/**
 * Класс команды clear
 */
public class Clear extends Comand {

    private ComandTypes comandType = ComandTypes.CLEAR;

    public Clear() {
        comandType = ComandTypes.CLEAR;
    }

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "clear : очистить коллекцию";
    }

    public String execute(){
        Server.collection.execute(Stream.of());
        Server.toSend = true;
        return "Коллекция очищена";
    }

    public String execute(String argument){
        return this.execute();
    }

}
