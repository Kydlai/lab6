package commands;

import server.ProjectCollection;
import server.Server;


/**
 * Класс команды reorder
 */
public class Reorder extends Comand {

    private ComandTypes comandType = ComandTypes.REORDER;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "reorder : отсортировать коллекцию в порядке, обратном нынешнему";
    }

    public String execute(){
        Server.isCompareReverse = !Server.isCompareReverse;
        Server.toSend = true;
        return "Коллекция отсортирована в обратном порядке";
    }

    public String execute(String argument){
        return this.execute();
    }
}
