package commands;

import server.ProjectCollection;
import server.Server;

/**
 * Класс команды update_id
 */
public class UpdateId extends Comand {

    private ComandTypes comandType = ComandTypes.UPDATE_ID;
    private Long id;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "update_id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    public String execute(){
        Server.toSend = true;
        if(Server.collection.getCollection().stream().filter(s -> s.getId().equals(id)).count() > 0) {
            Server.collection.execute(Server.collection.getCollection().stream().map(s -> s.getId().equals(id) ? s.updateId() : s));
            return "id элемента изменен на " + (Server.idCounter - 1);
        } else
            return "Нет элемента с таким id";
    }

    public String execute(String argument){
        return this.execute();
    }
}
