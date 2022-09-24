package commands;

import items.Dragon;
import server.ProjectCollection;
import server.Server;

import java.util.ArrayList;


/**
 * Класс команды remove_lover
 */
public class    RemoveLover extends Comand {

    private ComandTypes comandType = ComandTypes.REMOVE_LOVER;
    private Dragon dragon;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "remove_lover {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    public String execute(){
        Server.toSend = true;
        int sizeBefore = Server.collection.getCollection().size();
        Server.collection.execute(Server.collection.getCollection().stream().filter(s -> s.getNumber() >= dragon.getNumber()));
        return "Все найденные элементы удалены (" + (sizeBefore - Server.collection.getCollection().size()) + ")";
    }

    public String execute(String argument){
        dragon = Dragon.fileRead();
        return this.execute();
    }
}
