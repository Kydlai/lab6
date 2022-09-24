package commands;

import items.ItemComparator;
import server.ProjectCollection;
import server.Server;


/**
 * Класс команды print_descending
 */
public class PrintDescending extends Comand {

    private ComandTypes comandType = ComandTypes.PRINT_DESCENDING;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }

    public String execute(){
        Server.toSend = true;
        return Server.collection.getCollection().stream().sorted(new ItemComparator(true)).map(s -> s.getInfo()).reduce((s1, s2) -> s1 + s2).get();
    }

    public String execute(String argument){
        return this.execute();
    }
}
