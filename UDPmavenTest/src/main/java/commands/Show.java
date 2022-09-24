package commands;

import server.ProjectCollection;
import server.Server;


/**
 * Класс команды show
 */
public class Show extends Comand {

    private ComandTypes comandType = ComandTypes.SHOW;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    public String execute(){
        Server.toSend = true;
        return Server.collection.getCollection().stream().map(s -> s.getInfo()).reduce((s1, s2) -> s1 + s2).orElse("Коллекция пуста");
    }

    public String execute(String argument){
        return this.execute();
    }
}
