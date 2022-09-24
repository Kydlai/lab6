package commands;

import items.*;
import server.ProjectCollection;
import server.Server;

import java.util.ArrayList;
import java.util.stream.Stream;


/**
 * Класс команды remove_first
 */
public class RemoveFirst extends Comand {

    private ComandTypes comandType = ComandTypes.REMOVE_FIRST;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "remove_first : удалить первый элемент из коллекции";
    }

    public String execute(){
        Server.toSend = true;
        if(Server.collection.getCollection().size() > 1) {
            Dragon toDelete = Server.collection.getCollection().stream().findFirst().get();
            Server.collection.execute(Server.collection.getCollection().stream().filter(s -> !s.equals(toDelete)));
            return "Элемент удален с ID " + toDelete.getId();
        } else {
            return "Коллекция уже пуста";
        }
    }

    public String execute(String argument){
        return this.execute();
    }
}
