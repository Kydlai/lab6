package commands;

//import main.*;

import server.ProjectCollection;
import server.Server;

/**
 * Класс команды exit
 */
public class Exit extends Comand {

    protected ComandTypes comandType = ComandTypes.EXIT;

    public ComandTypes getComandType() {
        return comandType;
    }

    @Override
    public String getName() {
        return comandType.toString();
    }

    public String getInfo(){
        return "exit : сохранить коллекцию и завершить программу";
    }

    @Override
    public String execute() {
        Server.toSend = false;
        return new Save().execute();
    }

    public String execute(String argument){
        return this.execute();
    }
}
