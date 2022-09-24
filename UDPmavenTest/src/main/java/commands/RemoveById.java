package commands;

import items.*;
import server.ProjectCollection;
import server.Server;

import java.util.ArrayList;


/**
 * Класс команды remove_by_id
 */
public class RemoveById extends Comand {

    private ComandTypes comandType = ComandTypes.REMOVE_BY_ID;
    private Long id;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    public String execute(){


        Server.toSend = true;
        ArrayList<Dragon> newCollection = new ArrayList<>();
        Server.collection.getCollection().stream().filter(s -> !s.getId().equals(id)).forEach(s -> newCollection.add(s));
        if(newCollection.size() == Server.collection.getCollection().size())
            return "Элемент не был найден";
        else {
            Server.collection.setCollection(newCollection);
            return "Элемент удален c ID " + id;
        }
    }

    public String execute(String argument){
        id = Long.parseLong(argument);
        return this.execute();
    }
}
