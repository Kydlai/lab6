package commands;

import items.*;
import server.ProjectCollection;
import server.Server;


/**
 * Класс команды add
 */
public class Add extends Comand {

    private ComandTypes comandType = ComandTypes.ADD;
    private Dragon dragon;


    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "add {element} : добавить новый элемент в коллекцию";
    }

    public Dragon getDragon() {
        return dragon;
    }

    public String execute(){
        Server.collection.add(dragon);
        Server.toSend = true;
        return "Элемент добавлен с id " + dragon.getId();
    }

    public String execute(String argument){
        dragon = Dragon.fileRead();
        return this.execute();
    }
}
