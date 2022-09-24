package commands;

import server.ProjectCollection;
import server.Server;


/**
 * Класс команды info
 */
public class Info extends Comand {

    private ComandTypes comandType = ComandTypes.INFO;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    public String execute(){
        Server.toSend = true;
        String output = "Коллекция использует для хранения java.util.ArrayList\n";
        output += "Хранимый тип элементов - Dragon\n";
        output += "Дата создания - " + Server.collection.getCreationDate() + "\n";
        output += "Количество элементов в коллекции - " + Server.collection.getCollection().size() + "\n";
        return output;
    }

    public String execute(String argument){
        return this.execute();
    }
}
